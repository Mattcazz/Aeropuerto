package presentacion.operaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMenuCUsImp extends GUIMenuCUs {

    private JFrame marco;

    public GUIMenuCUsImp() {
        marco = new JFrame("Menú Principal");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Menú de Operaciones");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        mainPanel.add(title);

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setMaximumSize(new Dimension(500, 200));

        JButton gestionManualBtn = crearBoton("Gestión Manual Asignaciones", Eventos.GESTION_MANUAL_ASIGNACIONES);
        JButton consultarPuertasBtn = crearBoton("Gestionar Puertas", Eventos.CONSULTAR_PUERTAS);
        JButton salirBtn = new JButton("Salir");

        estilizarBoton(salirBtn);
        salirBtn.addActionListener(e -> marco.setVisible(false));

        buttonsPanel.add(gestionManualBtn);
        buttonsPanel.add(consultarPuertasBtn);
        buttonsPanel.add(salirBtn);

        mainPanel.add(buttonsPanel);

        marco.setContentPane(mainPanel);
        marco.pack();
        marco.setLocationRelativeTo(null);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);
    }

    private JButton crearBoton(String texto, int evento) {
        JButton boton = new JButton(texto);
        estilizarBoton(boton);
        boton.addActionListener(e -> {
            marco.setVisible(false);
            Controlador.getInstancia().accion(evento, null, marco);
        });
        return boton;
    }

    private void estilizarBoton(JButton boton) {
        boton.setFocusPainted(false);
        boton.setMargin(new Insets(10, 10, 10, 10));
    }


    public JFrame getFrame() {
        return marco;
    }

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}
}
