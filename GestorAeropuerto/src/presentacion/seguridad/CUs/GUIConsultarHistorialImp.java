package presentacion.seguridad.CUs;

import javax.swing.*;

import integracion.seguridad.DAODispositivo;
import integracion.FactoriaDAO;

import java.awt.*;
import java.util.List;
import negocio.seguridad.TransferEventoDispositivo;
import presentacion.seguridad.Controlador;
import presentacion.seguridad.Eventos;

public class GUIConsultarHistorialImp extends GUIConsultarHistorial {
    private final JFrame marco;

    public GUIConsultarHistorialImp() {
        marco = new JFrame("Consultar Historial Dispositivo");
        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String input = JOptionPane.showInputDialog(
            marco,
            "Introduce ID de dispositivo:",
            "Consultar Historial",
            JOptionPane.QUESTION_MESSAGE
        );
        if (input == null) {
            volverMenu();
            return;
        }
        int dispositivoId = Integer.parseInt(input.trim());

        // Obtener eventos
        DAODispositivo daoDisp = FactoriaDAO.getInstancia().nuevoDAODispositivo();
        List<TransferEventoDispositivo> eventos = daoDisp.getEventosHistorico(dispositivoId);

        // Preparar datos para JTable
        String[] cols = {"Fecha/Hora", "Evento"};
        Object[][] data = new Object[eventos.size()][2];
        for (int i = 0; i < eventos.size(); i++) {
            var ev = eventos.get(i);
            data[i][0] = ev.getFecha().toString();
            data[i][1] = ev.getDescripcion();
        }

        JTable table = new JTable(data, cols);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(
            marco,
            scroll,
            "Historial Dispositivo " + dispositivoId,
            JOptionPane.PLAIN_MESSAGE
        );

        volverMenu();
    }

    private void volverMenu() {
        marco.setVisible(false);
        Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // No usado
    }

    @Override
    public JFrame getFrame() {
        return marco;
    }
}