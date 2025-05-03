package presentacion.incidencias.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import negocio.incidencias.TransferIncidencia;
import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;

import java.awt.*;
import java.util.List;

public class GUIVisualizarIncidenciasEquipajesImp extends GUIVisualizarIncidenciasEquipajes {
    private JFrame marco;
    private JTable tablaIncidencias;
    private DefaultTableModel modeloTabla;

    public GUIVisualizarIncidenciasEquipajesImp() {
        marco = new JFrame("Visualizar Incidencias de Equipaje");
        marco.setSize(600, 400);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Incidencias de Equipaje", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        marco.add(titulo, BorderLayout.NORTH);

        String[] columnas = { "ID Maleta", "Fecha", "Información", "Estado", "Solución" };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaIncidencias = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaIncidencias);
        marco.add(scrollPane, BorderLayout.CENTER);
        
        TableColumn columnaInfo = tablaIncidencias.getColumnModel().getColumn(2); 
        columnaInfo.setPreferredWidth(300); 

        JPanel panelBotones = new JPanel();
        JButton botonVolver = new JButton("Volver");
        panelBotones.add(botonVolver);
        marco.add(panelBotones, BorderLayout.SOUTH);

        botonVolver.addActionListener(e -> {
            marco.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });

        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
    	if (evento == Eventos.MOSTRAR_INCIDENCIAS_EQUIPAJE_OK && datos instanceof List) {
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
    		
            }
        } else {
            JOptionPane.showMessageDialog(marco, "Error al cargar las incidencias de equipaje", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getFrame() {
        return marco;
    }
}
