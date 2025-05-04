package presentacion.equipajes.CUs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.*;

import presentacion.equipajes.Controlador;
import presentacion.equipajes.Eventos;

public class GUISeleccionVueloImp extends GUISeleccionVuelo{
	
	private final String titulo_panel = "Vuelos disponibles";
	
	
	private JComboBox<String> comboBox;
	
	JFrame marco;

	
	public GUISeleccionVueloImp(){
		initGUI();
	}
	
	private void initGUI() {
		marco = new JFrame(titulo_panel);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(600, 300));
		
		//TTítulo
		JLabel title = new JLabel(titulo_panel);
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//ComboBox
		comboBox = new JComboBox<>();
		
		//Botones
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton acceder = new JButton("Acceder");

		JPanel volverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton volver = new JButton("Volver");
		
		buttonsPanel.add(acceder);
		volverPanel.add(volver);
		
		//Panel principal
		panel.add(title);
		panel.add(comboBox);
		panel.add(buttonsPanel);
		panel.add(volverPanel);
		
		marco.setContentPane(panel);
		marco.pack();
		marco.setLocationRelativeTo(null);
		marco.setVisible(true);
		
		acceder.addActionListener(e -> Controlador.getInstancia().accion(Eventos.ACCEDER_VUELO, (String)comboBox.getSelectedItem()));
		
		volver.addActionListener(e -> {
			marco.setVisible(false);
		});
		
	
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		if (evento == Eventos.ACCESO && datos instanceof List) {
            List<String> vuelos = (List<String>) datos;
            comboBox.removeAllItems();
            for (String vuelo : vuelos) {
                comboBox.addItem(vuelo);
            }
        }
	}

	public void mostrar() {
		marco.setVisible(true);
	}

}
