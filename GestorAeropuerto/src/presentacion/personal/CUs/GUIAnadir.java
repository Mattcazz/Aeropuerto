package presentacion.personal.CUs;

import javax.swing.*;
import java.awt.*;
import negocio.personal.TransferEmpleado;
import negocio.personal.Codigos;
import presentacion.personal.Controlador;
import presentacion.personal.Eventos;

public class GUIAnadir {

    public GUIAnadir() {
        JFrame marco = new JFrame("Alta Empleado");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Alta de Empleado");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        // Formulario
        JPanel formulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField dniField = new JTextField(15);
        JTextField nombreField = new JTextField(15);
        JTextField rolField = new JTextField(15);
        JTextField turnoField = new JTextField(15);
        JTextField funcionField = new JTextField(15);
        JTextField nominaField = new JTextField(15);

        String[] labels = {"DNI:", "Nombre:", "Rol:", "Turno:", "Función:", "Nómina:"};
        JTextField[] fields = {dniField, nombreField, rolField, turnoField, funcionField, nominaField};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formulario.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            formulario.add(fields[i], gbc);
        }

        panel.add(formulario, BorderLayout.CENTER);

        // Botones
        JButton okButton = new JButton("Ok");
        JButton volverButton = new JButton("Volver");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(volverButton);
        buttonPanel.add(okButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        okButton.addActionListener(e -> {
            try {
                String dni = dniField.getText();
                String nombre = nombreField.getText();
                String rol = rolField.getText();
                String turno = turnoField.getText();
                String funcion = funcionField.getText();
                String nominatexto = nominaField.getText();
                double nomina = 0.0;
                if (!nominatexto.isEmpty()) {
                    nomina = Double.parseDouble(nominatexto);
                }

                TransferEmpleado t = new TransferEmpleado(1, true, dni, nombre, rol, turno, funcion, nomina);
                int resultado = negocio.personal.FactoriaSA.getInstancia().nuevoSAEmpleado().altaEmpleado(t);

                if (resultado == Codigos.YA_EXISTE) {
                    JOptionPane.showMessageDialog(marco, "El empleado ya existe y está activo.", "Atención", JOptionPane.WARNING_MESSAGE);
                } else if (resultado == Codigos.ERROR_INTERNO) {
                    JOptionPane.showMessageDialog(marco, "Error interno al dar de alta al empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(marco, "Empleado dado de alta correctamente con ID: " + resultado);
                    marco.dispose();
                    Controlador.getInstancia().accion(Eventos.MENU_EMPLEADO, null);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(marco, "Nómina debe ser un número decimal válido.", "Error", JOptionPane.ERROR_MESSAGE);
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
