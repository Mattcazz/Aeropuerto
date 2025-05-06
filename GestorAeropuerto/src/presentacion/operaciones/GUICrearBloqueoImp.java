package presentacion.operaciones;

import java.awt.GridLayout;

import negocio.InfoAeropuerto;
import negocio.operaciones.PeticionBloqueo;
import negocio.operaciones.TransferBloqueo;
import negocio.operaciones.TransferPuerta;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class GUICrearBloqueoImp extends GUICrearBloqueo{


	    private JComboBox<String> horaInicioBox;
	    private JComboBox<String> horaFinBox;
	    private JComboBox<String> motivoBox;
	    private JTextArea comentarioArea;
	    private JButton crearBloqueoButton;
	    private JFrame marco; 
	    private TransferPuerta puerta; 

	    @Override
	    public void InitGUI(TransferPuerta puerta) {
	    	
	    	this.puerta = puerta; 
	    	marco = new JFrame();
	    	marco.setTitle("Crear Bloqueo de Puerta " + puerta.getPuertaID());
	    	marco.setSize(400, 300);
	    	marco.setLocationRelativeTo(null);
	    	marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	marco.setLayout(new BorderLayout());

	        // Panel central con los campos
	        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

	        // Horas (de 00:00 a 23:00)
	        Vector<String> horas = new Vector<>();
	        for (int h = 0; h < 24; h++) {
	            horas.add(String.format("%02d:00", h));
	        }

	        horaInicioBox = new JComboBox<>(horas);
	        horaFinBox = new JComboBox<>(horas);

	        motivoBox = new JComboBox<>(InfoAeropuerto.tiposBloqueos);
	        comentarioArea = new JTextArea(3, 20);
	        JScrollPane comentarioScroll = new JScrollPane(comentarioArea);

	        formPanel.add(new JLabel("Hora de inicio:"));
	        formPanel.add(horaInicioBox);
	        formPanel.add(new JLabel("Hora final:"));
	        formPanel.add(horaFinBox);
	        formPanel.add(new JLabel("Motivo:"));
	        formPanel.add(motivoBox);
	        formPanel.add(new JLabel("Comentario:"));
	        formPanel.add(comentarioScroll);

	        marco.add(formPanel, BorderLayout.CENTER);


	        crearBloqueoButton = new JButton("Crear Bloqueo");
	        crearBloqueoButton.addActionListener(e -> crearBloqueo());
	        
	        JButton volverButton = new JButton("Volver");
			volverButton.addActionListener(e -> {
				 marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_ATRAS, null, null);});


	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        buttonPanel.add(crearBloqueoButton);
	        buttonPanel.add(volverButton);

	        marco.add(buttonPanel, BorderLayout.SOUTH);
	        
	        marco.setVisible(true);
	    }

	    private void crearBloqueo() {
	    	
	    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

	    	    String horaInicioStr = (String) horaInicioBox.getSelectedItem();
	    	    String horaFinStr = (String) horaFinBox.getSelectedItem();

	    	    LocalTime horaInicio = LocalTime.parse(horaInicioStr, formatter);
	    	    LocalTime horaFin = LocalTime.parse(horaFinStr, formatter);

	    	    LocalDateTime inicio = LocalDateTime.of(LocalDate.now(), horaInicio); // Hago esto porque use LocalDateTime
	    	    LocalDateTime fin = LocalDateTime.of(LocalDate.now(), horaFin);
	    	
	    	TransferBloqueo bloq = new TransferBloqueo();
	    	PeticionBloqueo transfer = new PeticionBloqueo();	
	    	
	    	bloq.setPuertaId(puerta.getPuertaID());
	        bloq.setHoraInicio(inicio);
	        bloq.setHoraFinal(fin);
	        bloq.setMotivo((String)motivoBox.getSelectedItem());
	        bloq.setComentario(comentarioArea.getText());

	        if (horaInicio.compareTo(horaFin) >= 0) {
	            JOptionPane.showMessageDialog(null, "La hora final debe ser posterior a la hora de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        transfer.setBloqueo(bloq);
	        Controlador.getInstancia().accion(Eventos.GUARDAR_BLOQUEO, transfer, null);
	        
	    }



	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		switch (evento) {
		case Eventos.CREAR_BLOQUEO_OK:
			mostrarMensaje("Bloqueo creado con exito!");
			marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.INSPECCIONAR_PUERTA, puerta, null);
			break;
		case Eventos.CREAR_BLOQUEO_ERROR:
			mostrarError((String)datos);
			break;
		}
	}


	@Override
	public void mostrarMensaje(String mensaje) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void mostrarError(String error) {
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
