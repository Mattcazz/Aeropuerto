package presentacion.locales.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

import negocio.locales.FiltroLocal;
import negocio.locales.TransferLocal;
import presentacion.locales.Controlador;
import presentacion.locales.Eventos;

public class GUIEliminarLocalesImp extends GUIEliminarLocales {

    String funcButtonName = "Mostrar";
    String[] columnas = {"IDLocal", "Empresa", "Ingresos", "Gastos", "Fecha contrato"};
    String tituloTabla = "Eliminar Locales";
    String tituloPanel = "Eliminar Locales";
    int eventoFunc = Eventos.MOSTRAR_PARA_ELIMINAR;
    String descFunc = "Se mostrarán los datos";

    JFrame victoria;
    JTable tabla;
    DefaultTableModel modeloTabla;

    JTextField campoNombre, campoIngresosMin, campoIngresosMax;
    JTextField campoGastosMin, campoGastosMax;
    JTextField campoFechaDesde, campoFechaHasta;

    public GUIEliminarLocalesImp() {
        victoria = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(500, 300));

        JPanel funcButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel filtroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JButton cancelButton = new JButton("Cancelar");
        JButton funcButton = new JButton(funcButtonName);
        JButton returnButton = new JButton("Volver");
        JButton botonFiltrar = new JButton("Filtrar");
        JButton botonEliminar = new JButton("Eliminar seleccionado");

        buttonsPanel.add(botonEliminar); // Ahora ocupa el lugar de Aceptar
        buttonsPanel.add(cancelButton);
        returnButtonPanel.add(returnButton);
        funcButtonPanel.add(funcButton);

        campoNombre = new JTextField(10);
        campoIngresosMin = new JTextField(5);
        campoIngresosMax = new JTextField(5);
        campoGastosMin = new JTextField(5);
        campoGastosMax = new JTextField(5);
        campoFechaDesde = new JTextField(8);
        campoFechaHasta = new JTextField(8);

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

        JScrollPane filtroScroll = new JScrollPane(filtroPanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        filtroScroll.setPreferredSize(new Dimension(800, 200)); 
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane table = new JScrollPane(tabla);

        JLabel title = new JLabel(tituloTabla);
        title.setFont(new Font("Dialog", Font.PLAIN, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(filtroScroll);
        panel.add(funcButtonPanel);
        panel.add(table);
        panel.add(buttonsPanel);
        panel.add(returnButtonPanel);

        victoria.getContentPane().add(panel);
        victoria.setTitle(tituloPanel);
        victoria.pack();

        funcButton.addActionListener(e -> {
            Controlador.getInstancia().accion(eventoFunc, null);
        });

        botonFiltrar.addActionListener(e -> {
            FiltroLocal filtro = new FiltroLocal();
            if (!campoNombre.getText().trim().isEmpty())
                filtro.setNombreParcial(campoNombre.getText().trim());
            try {
                if (!campoIngresosMin.getText().isEmpty())
                    filtro.setIngresosMin(Double.parseDouble(campoIngresosMin.getText().trim()));
                if (!campoIngresosMax.getText().isEmpty())
                    filtro.setIngresosMax(Double.parseDouble(campoIngresosMax.getText().trim()));
                if (!campoGastosMin.getText().isEmpty())
                    filtro.setGastosMin(Double.parseDouble(campoGastosMin.getText().trim()));
                if (!campoGastosMax.getText().isEmpty())
                    filtro.setGastosMax(Double.parseDouble(campoGastosMax.getText().trim()));
                if (!campoFechaDesde.getText().isEmpty())
                    filtro.setFechaDesde(LocalDate.parse(campoFechaDesde.getText().trim()));
                if (!campoFechaHasta.getText().isEmpty())
                    filtro.setFechaHasta(LocalDate.parse(campoFechaHasta.getText().trim()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(victoria, "Error en campos numéricos o de fecha.");
                return;
            }
            Controlador.getInstancia().accion(Eventos.FILTRAR_AVANZADO_ELIMINAR, filtro);
        });

        botonEliminar.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                int id = (int) tabla.getValueAt(filaSeleccionada, 0);
                Controlador.getInstancia().accion(Eventos.ELIMINAR_LOCALES, id);
                Controlador.getInstancia().accion(Eventos.MOSTRAR_PARA_ELIMINAR, null);
            } else {
                JOptionPane.showMessageDialog(victoria, "Selecciona un local a eliminar.");
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
        Controlador.getInstancia().accion(Eventos.MOSTRAR_PARA_ELIMINAR, null);
    }
} 
