package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMenuSubsistemasImp extends GUIMenuSubsistemas {
    JFrame marco;

    public GUIMenuSubsistemasImp() {
        marco = new JFrame();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Menú de Subsistemas");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(title);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 3, 15, 15));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setMaximumSize(new Dimension(600, 300));

        Eventos[] eventos = {
            Eventos.SUB_PANELES, Eventos.SUB_PERSONAL, Eventos.SUB_EQUIPAJE,
            Eventos.SUB_OPERACIONES, Eventos.SUB_FINANCIERA, Eventos.SUB_INCIDENCIAS,
            Eventos.SUB_LOCALES, Eventos.SUB_ESTACIONAMIENTO, Eventos.SUB_VUELOS,
            Eventos.SUB_SEGURIDAD
        };

        for (Eventos evento : eventos) {
            String nombre = formatearNombre(evento.name());
            JButton btn = new JButton(nombre);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> ControladorImp.getInstancia().accion(evento, marco));
            buttonsPanel.add(btn);
        }

        mainPanel.add(buttonsPanel);

        marco.setTitle("Menú Principal");
        marco.setContentPane(mainPanel);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.pack();
        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }

    private String formatearNombre(String raw) {
        String[] partes = raw.split("_");
        return partes.length > 1 ? capitalizar(partes[1].toLowerCase()) : capitalizar(raw.toLowerCase());
    }

    private String capitalizar(String texto) {
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }


    public JFrame getFrame() {
        return marco;
    }

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}
}
