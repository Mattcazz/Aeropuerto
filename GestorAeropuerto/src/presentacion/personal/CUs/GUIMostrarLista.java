package presentacion.personal.CUs;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Collection;
import negocio.personal.TransferEmpleado;
import presentacion.personal.Controlador;
import presentacion.personal.Eventos;

public class GUIMostrarLista {

    public GUIMostrarLista(Collection<TransferEmpleado> empleados) {
        JFrame marco = new JFrame("Lista de Empleados");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Lista de Empleados");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        // Columnas y datos
        String[] columnas = {"ID", "Activo", "DNI", "Nombre", "Rol", "Turno", "Función", "Nómina"};
        Object[][] datos = new Object[empleados.size()][8];

        int fila = 0;
        for (TransferEmpleado empleado : empleados) {
            datos[fila][0] = empleado.getId();
            datos[fila][1] = empleado.isActivo();
            datos[fila][2] = empleado.getDni();
            datos[fila][3] = empleado.getNombre();
            datos[fila][4] = empleado.getRol();
            datos[fila][5] = empleado.getTurno();
            datos[fila][6] = empleado.getFuncion();
            datos[fila][7] = empleado.getNomina();
            fila++;
        }

        JTable tabla = new JTable(datos, columnas);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        int[] anchos = {50, 60, 120, 150, 100, 100, 150, 100};
        for (int i = 0; i < anchos.length; i++) {
            TableColumn columna = tabla.getColumnModel().getColumn(i);
            columna.setPreferredWidth(anchos[i]);
        }

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> {
            marco.dispose();
            Controlador.getInstancia().accion(Eventos.MENU_EMPLEADO, null);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(volverButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        marco.getContentPane().add(panel);
        marco.setSize(900, 400); 
        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }
}
