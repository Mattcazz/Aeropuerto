package presentacion.personal.CUs;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;

import negocio.personal.TransferEmpleado;
import presentacion.personal.Controlador;
import presentacion.personal.Eventos;
import negocio.personal.Codigos;

public class GUIGenerarInforme {

    private JFrame marco;
    private JTextArea informeArea;

    // Constructor 1: con campo para introducir ID y botón de generar
    public GUIGenerarInforme() {
        marco = new JFrame("Informe de Empleado");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("ID del empleado:");
        JTextField idField = new JTextField(10);
        JButton generarBtn = new JButton("Generar Informe");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(generarBtn);
        marco.add(inputPanel, BorderLayout.NORTH);

        informeArea = new JTextArea(20, 50);
        informeArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(informeArea);
        marco.add(scrollPane, BorderLayout.CENTER);

        JButton imprimirBtn = new JButton("Imprimir");
        JButton volverBtn = new JButton("Volver");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(volverBtn);
        buttonPanel.add(imprimirBtn);
        marco.add(buttonPanel, BorderLayout.SOUTH);

        generarBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                int resultado = Controlador.getInstancia().accion(Eventos.GENERARINFORME, id);
                if (resultado == Codigos.NO_EXISTE) {
                    informeArea.setText("");
                    JOptionPane.showMessageDialog(marco, "Empleado no encontrado o inactivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(marco, "ID inválido.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        imprimirBtn.addActionListener(e -> imprimir());
        volverBtn.addActionListener(e -> {
            marco.dispose();
            Controlador.getInstancia().accion(Eventos.MENU_EMPLEADO, null);
        });

        marco.pack();
        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }

    public GUIGenerarInforme(TransferEmpleado emp) {
        this();
        mostrarInforme(emp);
    }

    private void mostrarInforme(TransferEmpleado emp) {
    	
        StringBuilder sb = new StringBuilder();

        sb.append("╔════════════════════════════════════════════════════════════╗\n");
        sb.append("║                    INFORME DE EMPLEADO                     ║\n");
        sb.append("╚════════════════════════════════════════════════════════════╝\n\n");

        sb.append(String.format("%-18s: %s\n", "ID", emp.getId()));
        sb.append(String.format("%-18s: %s\n", "DNI", emp.getDni()));
        sb.append(String.format("%-18s: %s\n", "Nombre", emp.getNombre()));
        sb.append(String.format("%-18s: %s\n", "Rol", emp.getRol()));
        sb.append(String.format("%-18s: %s\n", "Turno", emp.getTurno()));
        sb.append(String.format("%-18s: %s\n", "Función", emp.getFuncion()));
        sb.append(String.format("%-18s: %.2f €\n", "Nómina", emp.getNomina()));
        sb.append(String.format("%-18s: %s\n", "Estado", emp.isActivo() ? "Activo" : "Inactivo"));

        sb.append("\n--------------------------------------------------------------\n");
        sb.append("Este informe ha sido generado automáticamente por el sistema.\n");

        informeArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        informeArea.setText(sb.toString());
   

    }

    private void imprimir() {
        try {
            boolean completo = informeArea.print();
            JOptionPane.showMessageDialog(marco,
                    completo ? "Impresión completada correctamente." : "Impresión cancelada.",
                    "Impresión",
                    completo ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(marco, "Error al imprimir: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
