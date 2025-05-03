package presentacion.incidencias.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import negocio.incidencias.TransferIncidencia;
import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;

public class GUIVisualizarRegistroImp extends GUIVisualizarRegistro {

    private JFrame marco;
    private JTable tablaIncidencias;
    private DefaultTableModel modeloTabla;

    public GUIVisualizarRegistroImp() {
        marco = new JFrame("Historial de Incidencias Resueltas");
        marco.setSize(800, 400);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Registro de Incidencias Resueltas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        marco.add(titulo, BorderLayout.NORTH);

        // Tabla
        String[] columnas = { "ID", "Tipo", "Fecha", "Descripción", "Solución", "Compensación" };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaIncidencias = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaIncidencias);
        marco.add(scrollPane, BorderLayout.CENTER);

        // Botón volver
        JButton botonVolver = new JButton("Volver");
        botonVolver.setPreferredSize(new Dimension(120, 30));
        botonVolver.addActionListener(e -> {
            marco.dispose(); 
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.add(botonVolver);
        marco.add(panelBoton, BorderLayout.SOUTH);

        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        if (evento == Eventos.MOSTRAR_REGISTRO && datos instanceof List) {
            modeloTabla.setRowCount(0);
            List<TransferIncidencia> lista = (List<TransferIncidencia>) datos;
            for (TransferIncidencia t : lista) {
                modeloTabla.addRow(new Object[] {
                        t.getId(),
                        t.getTipo(),
                        t.getFecha(),
                        t.getDescripcion(),
                        t.getSolucion(),
                        t.getCompensacion()
                });
            }
        } else {
            JOptionPane.showMessageDialog(marco, "Error al cargar el registro", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getFrame() {
        return marco;
    }
}
