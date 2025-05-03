package presentacion.personal.CUs;

import javax.swing.*;
import java.awt.*;

import negocio.personal.Codigos;
import negocio.personal.FactoriaSA;
import presentacion.personal.Controlador;
import presentacion.personal.Eventos;

public class GUIEliminar {

    public GUIEliminar() {
        JFrame marco = new JFrame("Eliminar Empleado");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Eliminar Empleado");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        // Formulario
        JPanel formulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("ID del Empleado:");
        JTextField idField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formulario.add(idLabel, gbc);
        gbc.gridx = 1;
        formulario.add(idField, gbc);

        panel.add(formulario, BorderLayout.CENTER);

        // Botones
        JButton eliminarButton = new JButton("Eliminar");
        JButton volverButton = new JButton("Volver");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(volverButton);
        buttonPanel.add(eliminarButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        eliminarButton.addActionListener(e -> {
            String idTexto = idField.getText();
            try {
                int id = Integer.parseInt(idTexto);
                int resultado = negocio.personal.FactoriaSA.getInstancia()
                        .nuevoSAEmpleado().eliminarEmpleado(id);

                if (resultado == Codigos.OK) {
                    JOptionPane.showMessageDialog(marco, "Empleado eliminado correctamente.");
                    marco.dispose();
                    Controlador.getInstancia().accion(Eventos.MENU_EMPLEADO, null);
                } else if (resultado == Codigos.NO_EXISTE) {
                    JOptionPane.showMessageDialog(marco, "El empleado no existe o ya está inactivo.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(marco, "Error interno al eliminar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(marco, "Por favor, ingrese un ID válido (número entero).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        volverButton.addActionListener(e -> {
            marco.dispose();
            Controlador.getInstancia().accion(Eventos.MENU_EMPLEADO, null);
        });

        marco.getContentPane().add(panel);
        marco.pack();
        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }
}
