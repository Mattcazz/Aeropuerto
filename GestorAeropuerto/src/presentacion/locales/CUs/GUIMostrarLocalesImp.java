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

public class GUIMostrarLocalesImp extends GUIMostrarLocales {

    String funcButtonName = "Mostrar";
    String[] columnas = {"IDLocal", "Empresa", "Ingresos", "Gastos", "Fecha contrato"};
    String tituloTabla = "Informacion Local";
    String tituloPanel = "Mostrar Locales";
    int eventoFunc = Eventos.MOSTRAR_LOCALES;
    String descFunc = "Se mostraran los datos";

    JFrame victoria;
    JTable tabla;
    DefaultTableModel modeloTabla;
    JTextField campoFiltro;
    
    JTextField campoNombre, campoIngresosMin, campoIngresosMax;
    JTextField campoGastosMin, campoGastosMax;
    JTextField campoFechaDesde, campoFechaHasta;


    public GUIMostrarLocalesImp() {
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
        JButton funcButton = new JButton(funcButtonName);
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
        funcButtonPanel.add(funcButton);

        /*JLabel labelFiltro = new JLabel("Filtrar por empresa:");
        campoFiltro = new JTextField(20);
        filtroPanel.add(labelFiltro);
        filtroPanel.add(campoFiltro);*/
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

        modeloTabla = new DefaultTableModel(columnas, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;  
            }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane table = new JScrollPane(tabla);

        JLabel title = new JLabel(tituloTabla);
        title.setFont(new Font("Dialog", Font.PLAIN, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createHorizontalGlue());
        JScrollPane filtroScroll = new JScrollPane(filtroPanel);
        filtroScroll.setPreferredSize(new Dimension(800, 190)); // ajusta el alto si quieres
        panel.add(filtroScroll);
        panel.add(funcButtonPanel);
        panel.add(table);
        panel.add(buttonsPanel);
        panel.add(returnButtonPanel);

        victoria.getContentPane().add(panel);
        victoria.setTitle(tituloPanel);
        victoria.pack();

        // Mostrar todos los locales
        funcButton.addActionListener(e -> {
            Controlador.getInstancia().accion(eventoFunc, null);
        });

        // Filtrar por empresa
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
                    filtro.setFechaDesde(LocalDate.parse(campoFechaDesde.getText().trim()));  // yyyy-MM-dd
                if (!campoFechaHasta.getText().isEmpty())
                    filtro.setFechaHasta(LocalDate.parse(campoFechaHasta.getText().trim()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(victoria, "Las fechas deben tener formato yyyy-MM-dd.");
                return;
            }

            // Enviar el filtro al controlador
            Controlador.getInstancia().accion(Eventos.FILTRAR_AVANZADO, filtro);
        });


        acceptButton.addActionListener(e -> {
            Controlador.getInstancia().accion(Eventos.ACEPTAR_CAMBIOS, null);
        });

        cancelButton.addActionListener(e -> {
            Controlador.getInstancia().accion(Eventos.CANCELAR_CAMBIOS, null);
        });

        returnButton.addActionListener(e -> {
            victoria.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });

        victoria.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        if (datos instanceof List) {
            List<TransferLocal> lista = (List<TransferLocal>) datos;
            modeloTabla.setRowCount(0); // Limpiar tabla

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
        victoria.setVisible(true);
        Controlador.getInstancia().accion(Eventos.MOSTRAR_LOCALES, null);
    }
}
