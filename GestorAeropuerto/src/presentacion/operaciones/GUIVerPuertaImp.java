package presentacion.operaciones;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negocio.InfoAeropuerto;
import negocio.operaciones.PeticionPuerta;
import negocio.operaciones.TransferPuerta;

public class GUIVerPuertaImp extends GUIVerPuerta{

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		switch (evento) {
		case Eventos.GUARDAR_MODIFICACION_PUERTA_RESP_OK:
			actualizarModificacionPuerta((PeticionPuerta) datos);
			JOptionPane.showMessageDialog(null, "Puerta modificada correctamente!", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
			break;
		case Eventos.GUARDAR_MODIFICACION_PUERTA_RESP_ERROR:
			mostrarError((String)datos);
			break;
		}
	}
	
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
	 TransferPuerta puerta;
	@Override
	public void InitGUI(TransferPuerta puerta) {
		this.puerta = puerta;
		marco = new JFrame();
		marco.setTitle("Editar Puerta ID " + puerta.getPuertaID());
	        marco.setSize(400, 600);
	        marco.setLocationRelativeTo(null);
	        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        marco.setLayout(new GridLayout(0, 2, 10, 5));
	        
			marco.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			terminalField = new JComboBox<>(InfoAeropuerto.terminales);
			terminalField.setSelectedItem(puerta.getTerminal());
			
			ubicacionField = new JComboBox<>(InfoAeropuerto.zonas);
	        ubicacionField.setSelectedItem(puerta.getUbicacion());
	        
			nivelField = new JComboBox<>(InfoAeropuerto.niveles);
	        nivelField.setSelectedItem(puerta.getNivel());
			
			pesoField = new JTextField(String.valueOf(puerta.getPesoMax()));
	         anchuraField = new JTextField(String.valueOf(puerta.getAnchuraMax()));
	         alturaField = new JTextField(String.valueOf(puerta.getAlturaMax()));
	         longitudField = new JTextField(String.valueOf(puerta.getLongitudMax()));
	        tipoPuertaCombo = new JComboBox<>(InfoAeropuerto.tiposVuelos);
	        tipoPuertaCombo.setSelectedItem(puerta.getTipoPuerta());
	        aerolineaField = new JComboBox<>(InfoAeropuerto.aerolineas);
	        aerolineaField.setSelectedItem(puerta.getAerolineaPreferida());
	         vipCheckBox = new JCheckBox("VIP", puerta.isVip());

	        Component[] campos = {
	            terminalField, ubicacionField, nivelField, pesoField,
	            anchuraField, alturaField, longitudField,
	            tipoPuertaCombo, aerolineaField, vipCheckBox
	        };
	        for (Component c : campos) c.setEnabled(false);

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

	        JButton modificarButton = new JButton("Modificar");

	        JButton guardarButton = new JButton("Guardar cambios");
	        
	        

	        marco.add(modificarButton);
	        marco.add(guardarButton);
	        
	        JButton volverButton = new JButton("Volver");
			volverButton.addActionListener(e -> {
				 marco.setVisible(false);
				Controlador.getInstancia().accion(Eventos.VOLVER_ATRAS, null, null);});
	       
			 marco.add(volverButton);
			 
			 JButton cancelarButton = new JButton("Cancelar");
			 cancelarButton.setEnabled(false);
			 
		        modificarButton.addActionListener((e) -> {
		            for (Component c : campos) {
		            	
		            	if (c != terminalField && c != ubicacionField && c != nivelField) {
			            	c.setEnabled(true);
		            	}
		            }
		            cancelarButton.setEnabled(true);
		            modificarButton.setEnabled(false);
		        });
		        
		        cancelarButton.addActionListener(e -> {
		        	cancelarModificacionPuerta(puerta);
		        	 for (Component c : campos) {
		        		 c.setEnabled(false);
		        		 c.setBackground(Color.WHITE);
		        	 }
		        	modificarButton.setEnabled(true);
		        	cancelarButton.setEnabled(false);
		        });
		        
		        guardarButton.addActionListener(e -> {
		        	if(guardarModificacionPuerta()) {
		        		for (Component c : campos) c.setEnabled(false);
			        	modificarButton.setEnabled(true);
			        	cancelarButton.setEnabled(false);	
		        	}
		        	
		        });

			 
			 
			 marco.add(cancelarButton);

			 marco.setVisible(true);
		
		
	}
	
private void cancelarModificacionPuerta(TransferPuerta puerta) {
	 terminalField.setSelectedItem(puerta.getTerminal());
     ubicacionField.setSelectedItem(puerta.getUbicacion());
     nivelField.setSelectedItem(puerta.getNivel());
     pesoField.setText(String.valueOf(puerta.getPesoMax()));
     anchuraField.setText(String.valueOf(puerta.getAnchuraMax()));
     alturaField.setText(String.valueOf(puerta.getAlturaMax()));
     longitudField.setText(String.valueOf(puerta.getLongitudMax()));
     tipoPuertaCombo.setSelectedItem(puerta.getTipoPuerta());
     aerolineaField.setSelectedItem(puerta.getAerolineaPreferida() != null ? puerta.getAerolineaPreferida() : "");
     vipCheckBox.setSelected(puerta.isVip());

}

private void actualizarModificacionPuerta(PeticionPuerta puerta) {

    pesoField.setText(String.valueOf(puerta.getPuerta().getPesoMax()));
    anchuraField.setText(String.valueOf(puerta.getPuerta().getAnchuraMax()));
    alturaField.setText(String.valueOf(puerta.getPuerta().getAlturaMax()));
    longitudField.setText(String.valueOf(puerta.getPuerta().getLongitudMax()));
    tipoPuertaCombo.setSelectedItem(puerta.getPuerta().getTipoPuerta());
    aerolineaField.setSelectedItem(puerta.getPuerta().getAerolineaPreferida() != null ? puerta.getPuerta().getAerolineaPreferida() : "");
    vipCheckBox.setSelected(puerta.getPuerta().isVip());

}

private boolean guardarModificacionPuerta() {
    if (!validarCampoNumericoConColor(pesoField, "Peso máximo")) return false;
    if (!validarCampoNumericoConColor(anchuraField, "Anchura máxima"))  return false;
    if (!validarCampoNumericoConColor(alturaField, "Altura máxima"))  return false;
    if (!validarCampoNumericoConColor(longitudField, "Longitud máxima"))  return false;

    PeticionPuerta peticionPuerta = new PeticionPuerta(); 
    TransferPuerta modifcacionPuerta = new TransferPuerta();

    modifcacionPuerta.setPuertaID(puerta.getPuertaID());	
    modifcacionPuerta.setPesoMax(Float.parseFloat(pesoField.getText()));
    modifcacionPuerta.setAnchuraMax(Float.parseFloat(anchuraField.getText()));
    modifcacionPuerta.setAlturaMax(Float.parseFloat(alturaField.getText()));
    modifcacionPuerta.setLongitudMax(Float.parseFloat(longitudField.getText()));
    modifcacionPuerta.setTipoPuerta((String) tipoPuertaCombo.getSelectedItem());
    modifcacionPuerta.setAerolineaPreferida(aerolineaField.getSelectedItem().toString());
    modifcacionPuerta.setVip(vipCheckBox.isSelected());

    peticionPuerta.setPuerta(modifcacionPuerta);
    
    Controlador.getInstancia().accion(Eventos.GUARDAR_MODIFICACION_PUERTA, peticionPuerta, null);
    
    return true;
}

private boolean validarCampoNumericoConColor(JTextField field, String nombreCampo) {
    try {
        Float.parseFloat(field.getText());
        return true;
    } catch (NumberFormatException e) {
        field.setBackground(Color.PINK);
        mostrarError("El campo '" + nombreCampo + "' debe contener un número válido.");
        return false;
    }
}


private void mostrarError(String error) {
	JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
}
	
	
}


