package presentacion.equipajes.CUs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.equipajes.TransferEquipaje;
import presentacion.equipajes.Controlador;
import presentacion.equipajes.Eventos;

public class GUIAnadirEquipajeImp extends GUIAnadirEquipaje {
	
	private final String titulo_panel = "Datos del nuevo equipaje";
	
    private JTextField idVueloField;
    private JTextField pesoField;
	
	JFrame marco;
	
	public GUIAnadirEquipajeImp() {
        initGUI();
    }
	
	
	private void initGUI() {
		// TODO Auto-generated method stub
		marco = new JFrame(titulo_panel);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(600, 300));
		
		JLabel title = new JLabel(titulo_panel);
		title.setFont(new Font("Dialog", Font.PLAIN, 20));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(title);
		
		// Labels y campos
		
        panel.add(new JLabel("ID Vuelo:"));
        idVueloField = new JTextField();
        idVueloField.setEditable(false);
        panel.add(idVueloField);

        panel.add(new JLabel("Peso (kg):"));
        pesoField = new JTextField();
        panel.add(pesoField);
        
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton anadir = new JButton("Añadir");
		buttonsPanel.add(anadir);
		
		JPanel volverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton volver = new JButton("Volver");
		volverPanel.add(volver);
		
		panel.add(buttonsPanel);
		panel.add(volverPanel);
		
		
		marco.setContentPane(panel);
		marco.pack();
		marco.setLocationRelativeTo(null);
		marco.setVisible(true);
		
		anadir.addActionListener(e-> {
			
			 try {
	                String idVuelo = idVueloField.getText();
	                String peso = pesoField.getText();
	                
	                TransferEquipaje te=new TransferEquipaje(idVuelo, Double.parseDouble(peso));
        			Controlador.getInstancia().accion(Eventos.REGISTRAR_EQUIPAJE, te);
	                marco.setVisible(false);
			 }catch(NumberFormatException ex){
				 JOptionPane.showMessageDialog(null, "Peso inválido.", "Error", JOptionPane.ERROR_MESSAGE);
				 
			 }		
		});
		
		volver.addActionListener(e -> {
			marco.setVisible(false);
		});
	}


	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
		if(evento == Eventos.ANADIR_EQUIPAJE && datos instanceof String) {
			
			String id_vuelo = (String)datos;
			idVueloField.setText(id_vuelo);
            pesoField.setText(String.valueOf(0));
		}
	}


	public void mostrar() {
		// TODO Auto-generated method stub
		marco.setVisible(true);
	}

}
