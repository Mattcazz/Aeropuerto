package presentacion.estacionamiento.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import negocio.estacionamiento.TransferPlaza;
import presentacion.estacionamiento.Eventos;
import presentacion.estacionamiento.Controlador;

public class GUIListaPlazasImp extends GUIListaPlazas {
    private JFrame frame;
    private DefaultTableModel model;
    private JTable table;

    public GUIListaPlazasImp() {
        frame = new JFrame("Lista de Plazas");
        frame.setLayout(new BorderLayout());
        String[] cols = {"Número de Plaza", "Estado", "Matrícula"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });
        frame.add(btnVolver, BorderLayout.SOUTH);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        if (evento == Eventos.LISTA_PLAZAS_OK) {
            @SuppressWarnings("unchecked")
			List<TransferPlaza> plazas = (List<TransferPlaza>) datos;
            model.setRowCount(0);
            for (TransferPlaza p : plazas) {
                model.addRow(new Object[]{p.getNumero(), p.getEstado(), p.getMatricula()});
            }
            frame.setVisible(true);
        } else if (evento == Eventos.LISTA_PLAZAS_KO) {
            JOptionPane.showMessageDialog(null, "Error al listar plazas", "Error", JOptionPane.ERROR_MESSAGE);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        }
    }

    @Override
    public void mostrar() {
        Controlador.getInstancia().accion(Eventos.LISTA_PLAZAS, null);
    }
}