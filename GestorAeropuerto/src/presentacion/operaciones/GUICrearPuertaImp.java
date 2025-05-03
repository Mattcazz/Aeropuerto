package presentacion.operaciones;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negocio.InfoAeropuerto;
import negocio.operaciones.PeticionPuerta;
import negocio.operaciones.TransferPrepararGestionAsignacion;
import negocio.operaciones.TransferPuerta;

public class GUICrearPuertaImp extends GUICrearPuerta{

	
	JFrame marco;
	JComboBox<Integer> terminalField;
	JComboBox<String>  ubicacionField; 
	JComboBox<Integer> nivelField;
	JTextField pesoField;
	JTextField anchuraField ;
	JTextField alturaField ;
	JTextField longitudField;
	JComboBox<String> tipoPuertaCombo;
	JComboBox<String> aerolineaField;
	JCheckBox vipCheckBox;

	 
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		switch(evento){
		case Eventos.CREAR_PUERTA_RESP_OK:
			marco.setVisible(false);
			break;
		case Eventos.CREAR_PUERTA_RESP_ERROR:
			mostrarMensaje((String)datos);
			break;
		}
		
	}

	@Override
	public void InitGUI() {
		// TODO Auto-generated method stub
		marco = new JFrame();
		marco.setTitle("Crear Puerta");
        marco.setSize(400, 600);
        marco.setLocationRelativeTo(null);
        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        marco.setLayout(new GridLayout(0, 2, 10, 5));
        
		marco.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		terminalField = new JComboBox<>(InfoAeropuerto.terminales);
		ubicacionField = new JComboBox<>(InfoAeropuerto.zonas);
        nivelField = new JComboBox<>(InfoAeropuerto.niveles);
        pesoField = new JTextField();
        anchuraField = new JTextField();
        alturaField = new JTextField();
        longitudField = new JTextField();
        tipoPuertaCombo = new JComboBox<>(InfoAeropuerto.tiposVuelos);
        aerolineaField = new JComboBox<>(InfoAeropuerto.aerolineas);
        vipCheckBox = new JCheckBox("VIP");

        marco.add(new JLabel("Terminal:")); marco.add(terminalField);
        marco.add(new JLabel("Ubicación:")); marco.add(ubicacionField);
        marco.add(new JLabel("Nivel:")); marco.add(nivelField);
        marco.add(new JLabel("Peso máximo:")); marco.add(pesoField);
        marco.add(new JLabel("Anchura máxima:")); marco.add(anchuraField);
        marco.add(new JLabel("Altura máxima:")); marco.add(alturaField);
        marco.add(new JLabel("Longitud máxima:")); marco.add(longitudField);
        marco.add(new JLabel("Tipo de puerta:")); marco.add(tipoPuertaCombo);
        marco.add(new JLabel("Aerolínea preferida:")); marco.add(aerolineaField);
        marco.add(new JLabel("VIP:")); marco.add(vipCheckBox);
        
        JButton crearPuertaButton = new JButton("Crear");
        
        crearPuertaButton.addActionListener(e -> { 
        	
            if (!validarCampoNumericoConColor(pesoField, "Peso máximo")) return;
            if (!validarCampoNumericoConColor(anchuraField, "Anchura máxima"))  return ;
            if (!validarCampoNumericoConColor(alturaField, "Altura máxima"))  return ;
            if (!validarCampoNumericoConColor(longitudField, "Longitud máxima"))  return ;
            
        	PeticionPuerta peticionPuerta = new PeticionPuerta(); 
        	TransferPuerta nuevaPuerta = new TransferPuerta();
        	
        	nuevaPuerta.setTerminal((int)terminalField.getSelectedItem());	
        	nuevaPuerta.setNivel((int)nivelField.getSelectedItem());	
        	nuevaPuerta.setUbicacion((String)ubicacionField.getSelectedItem());	
        	nuevaPuerta.setPesoMax(Float.parseFloat(pesoField.getText()));
        	nuevaPuerta.setAnchuraMax(Float.parseFloat(anchuraField.getText()));
        	nuevaPuerta.setAlturaMax(Float.parseFloat(alturaField.getText()));
        	nuevaPuerta.setLongitudMax(Float.parseFloat(longitudField.getText()));
        	nuevaPuerta.setTipoPuerta((String) tipoPuertaCombo.getSelectedItem());
        	nuevaPuerta.setAerolineaPreferida(aerolineaField.getSelectedItem().toString());
        	nuevaPuerta.setVip(vipCheckBox.isSelected());
        	
        	peticionPuerta.setPuerta(nuevaPuerta);
        	
        	Controlador.getInstancia().accion(Eventos.GUARDAR_CREAR_PUERTA, peticionPuerta, null);
        });
        
        marco.add(crearPuertaButton);
        
        JButton volverButton = new JButton("Volver");
		volverButton.addActionListener(e -> {
			 marco.setVisible(false);
			Controlador.getInstancia().accion(Eventos.VOLVER_ATRAS, null, null);
			});
       
		 marco.add(volverButton);
		marco.setVisible(true);
	}

	private boolean validarCampoNumericoConColor(JTextField field, String nombreCampo) {
	    try {
	        Float.parseFloat(field.getText());
	        return true;
	    } catch (NumberFormatException e) {
	        field.setBackground(Color.PINK);
	        mostrarMensaje("El campo '" + nombreCampo + "' debe contener un número válido.");
	        return false;
	    }
	}
	@Override
	public void mostrarMensaje(String mensaje) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
