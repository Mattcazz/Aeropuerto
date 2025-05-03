package presentacion.personal.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.personal.Controlador;
import presentacion.personal.Eventos;
import negocio.personal.Codigos;

public class GUICrearNomina {

    public GUICrearNomina() {
        JFrame marco = new JFrame("Consultar Nómina");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Consulta de Nómina");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel idLabel = new JLabel("ID del Empleado:");
        JTextField idField = new JTextField();
        formPanel.add(idLabel);
        formPanel.add(idField);
        panel.add(formPanel, BorderLayout.CENTER);

        JButton consultarButton = new JButton("Consultar");
        JButton volverButton = new JButton("Volver");

        consultarButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                double resultado = Controlador.getInstancia().accion(Eventos.CREARNOMINA, id);

                if (resultado == Codigos.NO_EXISTE) {
                    JOptionPane.showMessageDialog(marco, "Empleado no encontrado o inactivo.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(marco, "La nómina del empleado es: " + resultado + " €", "Nómina", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(marco, "Por favor, introduce un ID válido (número entero).", "Formato inválido", JOptionPane.WARNING_MESSAGE);
            }
        });

        volverButton.addActionListener(e -> {
            marco.dispose();
            Controlador.getInstancia().accion(Eventos.MENU_EMPLEADO, null);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(volverButton);
        buttonPanel.add(consultarButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        marco.setContentPane(panel);
        marco.pack();
        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }
}
