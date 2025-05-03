package presentacion.incidencias.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.util.List;
import negocio.incidencias.TransferIncidencia;
import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;

public class GUIVisualizarIncidenciasVuelosImp extends GUIVisualizarIncidenciasVuelos{

    private JFrame marco;
    private JTable tablaIncidencias;
    private DefaultTableModel modeloTabla;

    public GUIVisualizarIncidenciasVuelosImp() {
        marco = new JFrame("Visualizar Incidencias de Vuelo");
        marco.setSize(700, 400);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Incidencias de Vuelo", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        marco.add(titulo, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"Vuelo", "Fecha", "Información", "Estado", "Solución"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaIncidencias = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaIncidencias);
        marco.add(scroll, BorderLayout.CENTER);

        TableColumn columnaInfo = tablaIncidencias.getColumnModel().getColumn(2); 
        columnaInfo.setPreferredWidth(300); 
        
        // Botón de volver
        JButton botonVolver = new JButton("Volver");
        botonVolver.setPreferredSize(new Dimension(120, 30));
        botonVolver.addActionListener(e -> {
            marco.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
           
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonVolver);
        marco.add(panelBoton, BorderLayout.SOUTH);

        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        if ( datos instanceof List) {
            modeloTabla.setRowCount(0); 
			List<TransferIncidencia> lista = (List<TransferIncidencia>) datos;
            for (TransferIncidencia t : lista) {
                modeloTabla.addRow(new Object[]{
                    t.getId(),
                    t.getFecha(),
                    t.getDescripcion(),
                    t.getEstado(),
                });
            }
        } else {
            JOptionPane.showMessageDialog(marco, "Error al cargar las incidencias", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getFrame() {
        return marco;
    }
}
