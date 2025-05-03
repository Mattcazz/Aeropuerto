package presentacion.operaciones;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.operaciones.TransferBloqueo;

public class GUIVerBloqueoImp extends GUIVerBloqueo{

    private JFrame marco; 
    private TransferBloqueo bloqueo;
    
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void InitGUI(TransferBloqueo bloqueo) {
		// TODO Auto-generated method stub
		
		this.bloqueo = bloqueo;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		marco = new JFrame();
    	marco.setTitle("Ver Bloqueo en Puerta " + bloqueo.getPuertaId());
    	marco.setSize(400, 300);
    	marco.setLocationRelativeTo(null);
    	marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	marco.setLayout(new BorderLayout());
    	
    	JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
    	
    	JTextField horaInicioField = new JTextField(bloqueo.getHoraInicio().format(formatter));
    	horaInicioField.setEditable(false);
    	JTextField horaFinField = new JTextField(bloqueo.getHoraFinal().format(formatter));
    	horaFinField.setEditable(false);
    	JTextField motivoField = new JTextField(bloqueo.getMotivo());
    	motivoField.setEditable(false);
    	
    	
    	
		JTextArea comentarioArea =  new JTextArea(3, 20);
		comentarioArea.setText(bloqueo.getComentario());
		comentarioArea.setEditable(false);
        JScrollPane comentarioScroll = new JScrollPane(comentarioArea);

        
        formPanel.add(new JLabel("Hora de inicio:"));
        formPanel.add(horaInicioField);
        formPanel.add(new JLabel("Hora final:"));
        formPanel.add(horaFinField);
        formPanel.add(new JLabel("Motivo:"));
        formPanel.add(motivoField);
        formPanel.add(new JLabel("Comentario:"));
        formPanel.add(comentarioScroll);
        

        marco.add(formPanel, BorderLayout.CENTER);
        
        JButton borrarBloqueoButton = new JButton("Borrar Bloqueo");
        borrarBloqueoButton.addActionListener(e -> borrarBloqueoFunc());
        
        JButton volverButton = new JButton("Volver");
		volverButton.addActionListener(e -> {
			 marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.VOLVER_ATRAS, null, null);});


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(borrarBloqueoButton);
        buttonPanel.add(volverButton);

        marco.add(buttonPanel, BorderLayout.SOUTH);
        
        marco.setVisible(true);

	}
	
	private void borrarBloqueoFunc() {
		int respuesta = JOptionPane.showConfirmDialog(
		        null,
		        "¿Estás seguro de que quieres borrar esta bloqueo? Esta acción es irreversible.",
		        "Confirmar eliminación",
		        JOptionPane.YES_NO_OPTION,
		        JOptionPane.WARNING_MESSAGE
		    );
		if (respuesta == JOptionPane.YES_OPTION) {
			Controlador.getInstancia().accion(Eventos.BORRAR_BLOQUEO, bloqueo, null);	
			marco.setVisible(false);

		}else {
			return;
		}
	}

}
