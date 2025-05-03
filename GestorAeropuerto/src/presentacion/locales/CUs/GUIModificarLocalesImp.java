package presentacion.locales.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import presentacion.locales.Controlador;
import presentacion.locales.Eventos;
import negocio.locales.FiltroLocal;
import negocio.locales.TransferLocal;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class GUIModificarLocalesImp extends GUIModificarLocales {

    String funcButtonName = "Modificar";
    String[] columnas = {"IDLocal", "Empresa", "Ingresos", "Gastos", "Fecha contrato"};
    String tituloTabla = "Información Local";
    String tituloPanel = "Modificar Locales";
    int eventoFunc = Eventos.MOSTRAR_PARA_MODIFICAR;
    String descFunc = "Se cargarán los datos para modificar";

    JFrame victoria;
    JTable tabla;
    DefaultTableModel modeloTabla;
    JTextField campoFiltro;
    
    JTextField campoNombre, campoIngresosMin, campoIngresosMax;
    JTextField campoGastosMin, campoGastosMax;
    JTextField campoFechaDesde, campoFechaHasta;


    public GUIModificarLocalesImp() {
        victoria = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(500, 300));

        JPanel funcButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel filtroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        
        JButton cancelButton = new JButton("Cancelar");
        JButton acceptButton = new JButton("Aceptar");
        //JButton funcButton = new JButton(funcButtonName);
        JButton returnButton = new JButton("Volver");
        JButton botonFiltrar = new JButton("Filtrar");

        
        campoNombre = new JTextField(10);
        campoIngresosMin = new JTextField(5);
        campoIngresosMax = new JTextField(5);
        campoGastosMin = new JTextField(5);
        campoGastosMax = new JTextField(5);
        campoFechaDesde = new JTextField(8);
        campoFechaHasta = new JTextField(8);

        buttonsPanel.add(acceptButton);
        buttonsPanel.add(cancelButton);
        returnButtonPanel.add(returnButton);
        //funcButtonPanel.add(funcButton);

        JLabel labelFiltro = new JLabel("");
        campoFiltro = new JTextField(20);
        filtroPanel.add(labelFiltro);
        //filtroPanel.add(campoFiltro);
        filtroPanel.add(botonFiltrar);
        
        filtroPanel.add(new JLabel("Nombre contiene:"));
        filtroPanel.add(campoNombre);
        filtroPanel.add(new JLabel("Ingresos ≥"));
        filtroPanel.add(campoIngresosMin);
        filtroPanel.add(new JLabel("≤"));
        filtroPanel.add(campoIngresosMax);
        filtroPanel.add(new JLabel("Gastos ≥"));
        filtroPanel.add(campoGastosMin);
        filtroPanel.add(new JLabel("≤"));
        filtroPanel.add(campoGastosMax);
        filtroPanel.add(new JLabel("Fecha desde:"));
        filtroPanel.add(campoFechaDesde);
        filtroPanel.add(new JLabel("hasta:"));
        filtroPanel.add(campoFechaHasta);

     // Añadir scroll al filtro
        filtroPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane filtroScroll = new JScrollPane(filtroPanel);
        filtroScroll.setPreferredSize(new Dimension(800, 250));

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane table = new JScrollPane(tabla);

        JLabel title = new JLabel(tituloTabla);
        title.setFont(new Font("Dialog", Font.PLAIN, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createHorizontalGlue());
       // panel.add(filtroPanel);
        panel.add(filtroScroll);
        panel.add(funcButtonPanel);
        panel.add(table);
        panel.add(buttonsPanel);
        panel.add(returnButtonPanel);

        
        
        victoria.getContentPane().add(panel);
        victoria.setTitle(tituloPanel);
        victoria.pack();

       /* // MOSTRAR TODOS
        funcButton.addActionListener(e -> {
            Controlador.getInstancia().accion(eventoFunc, null);
        });*/

        // FILTRAR
        botonFiltrar.addActionListener(e -> {
            FiltroLocal filtro = new FiltroLocal();

            // Nombre
            String nombre = campoNombre.getText().trim();
            if (!nombre.isEmpty()) filtro.setNombreParcial(nombre);

            // Ingresos
            try {
                if (!campoIngresosMin.getText().isEmpty())
                    filtro.setIngresosMin(Double.parseDouble(campoIngresosMin.getText().trim()));
                if (!campoIngresosMax.getText().isEmpty())
                    filtro.setIngresosMax(Double.parseDouble(campoIngresosMax.getText().trim()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(victoria, "Los ingresos deben ser números válidos.");
                return;
            }

            // Gastos
            try {
                if (!campoGastosMin.getText().isEmpty())
                    filtro.setGastosMin(Double.parseDouble(campoGastosMin.getText().trim()));
                if (!campoGastosMax.getText().isEmpty())
                    filtro.setGastosMax(Double.parseDouble(campoGastosMax.getText().trim()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(victoria, "Los gastos deben ser números válidos.");
                return;
            }

            // Fechas
            try {
                if (!campoFechaDesde.getText().isEmpty())
                    filtro.setFechaDesde(LocalDate.parse(campoFechaDesde.getText().trim()));
                if (!campoFechaHasta.getText().isEmpty())
                    filtro.setFechaHasta(LocalDate.parse(campoFechaHasta.getText().trim()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(victoria, "Las fechas deben tener formato yyyy-MM-dd.");
                return;
            }

            Controlador.getInstancia().accion(Eventos.FILTRAR_AVANZADO_MODIFICAR, filtro);
        });


        // ACEPTAR MODIFICACIÓN (de fila seleccionada)
        acceptButton.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                try {
                	if (tabla.isEditing()) {
                	    tabla.getCellEditor().stopCellEditing();
                	}

                    int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                    String empresa = tabla.getValueAt(fila, 1).toString();
                    double ingresos = Double.parseDouble(tabla.getValueAt(fila, 2).toString());
                    double gastos = Double.parseDouble(tabla.getValueAt(fila, 3).toString());
                    LocalDate fecha = LocalDate.parse(tabla.getValueAt(fila, 4).toString());

                    TransferLocal modificado = new TransferLocal(id, empresa, ingresos, gastos, fecha);
                    Controlador.getInstancia().accion(Eventos.MODIFICAR_LOCALES, modificado);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(victoria, "Error al modificar: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(victoria, "Selecciona una fila para modificar.");
            }
        });

        cancelButton.addActionListener(e -> {
            Controlador.getInstancia().accion(Eventos.CANCELAR_CAMBIOS, null);
        });

        returnButton.addActionListener(e -> {
            victoria.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });
        
        victoria.setVisible(true);
       //Controlador.getInstancia().accion(Eventos.MOSTRAR_PARA_MODIFICAR, null);
       
    }

    @Override
    public void actualizar(int evento, Object datos) {
        if (datos instanceof List) {
            List<TransferLocal> lista = (List<TransferLocal>) datos;
            modeloTabla.setRowCount(0);

            for (TransferLocal l : lista) {
                Object[] fila = {
                    l.getId(),
                    l.getEmpresa(),
                    l.getIngresos(),
                    l.getGastos(),
                    l.getFechaContrato()
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    public JFrame getFrame() {
        return victoria;
    }
    @Override
    public void mostrar() {
        this.victoria.setVisible(true);
        Controlador.getInstancia().accion(Eventos.MOSTRAR_PARA_MODIFICAR, null);
    }
}
