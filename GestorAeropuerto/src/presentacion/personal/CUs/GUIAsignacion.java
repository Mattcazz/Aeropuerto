package presentacion.personal.CUs;

import java.awt.*;
import javax.swing.*;

import negocio.personal.Codigos;
import negocio.personal.TransferEmpleado;
import presentacion.personal.Controlador;
import presentacion.personal.Eventos;
/*
Si le pongo campo vacio en asignacion, que se quede el anterior y al asignar que me vuelva a mostar el menu
y si el dni no cumple formato que no me de error en consola
* 
* */


public class GUIAsignacion {

	JFrame marco;
    public GUIAsignacion() {
        marco = new JFrame();
        marco.setTitle("Asignación");

        JTextField dni = new JTextField(15);
        JTextField rol = new JTextField(15);
        String[] opts = {"Mañana", "Tarde", "Noche"};
        JComboBox<String> turno = new JComboBox<>(opts);
        JTextField funcion = new JTextField(15);

        JLabel dniLabel = new JLabel("DNI:");
        JLabel rolLabel = new JLabel("Rol:");
        JLabel turnoLabel = new JLabel("Turno:");
        JLabel funcionLabel = new JLabel("Función:");

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Asignación de Empleado");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        
        JPanel formulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formulario.add(dniLabel, gbc);
        gbc.gridx = 1;
        formulario.add(dni, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formulario.add(rolLabel, gbc);
        gbc.gridx = 1;
        formulario.add(rol, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formulario.add(turnoLabel, gbc);
        gbc.gridx = 1;
        formulario.add(turno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formulario.add(funcionLabel, gbc);
        gbc.gridx = 1;
        formulario.add(funcion, gbc);

        panel.add(formulario, BorderLayout.CENTER);

        // Botones OK y Volver Atrás
        JButton okButton = new JButton("OK");
        okButton.addActionListener((e) -> {
        	TransferEmpleado t = new TransferEmpleado(1, true, dni.getText(), "", rol.getText(), turno.getSelectedItem().toString(), funcion.getText(), 1.1);
            int resultado = Controlador.getInstancia().accion(Eventos.ASIGNACION, t);

            if (resultado == Codigos.NO_EXISTE) {
                JOptionPane.showMessageDialog(null, "El DNI no corresponde a ningún empleado existente.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (resultado == Codigos.OK) {
                JOptionPane.showMessageDialog(null, "Asignación realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                marco.dispose(); 
                Controlador.getInstancia().accion(Eventos.MENU_EMPLEADO, null);
            } else {
                JOptionPane.showMessageDialog(null, "Error interno al asignar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e ->{
        	marco.dispose();
        	Controlador.getInstancia().accion(Eventos.MENU_EMPLEADO, null);
        });
       

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(backButton);
        buttonPanel.add(okButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        marco.getContentPane().add(panel);
        marco.pack();
        marco.setLocationRelativeTo(null); // Centrar ventana
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);

    }
}
