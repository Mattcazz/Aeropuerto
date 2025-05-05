package presentacion.seguridad.CUs;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class GUIMenuCUsImp extends GUIMenuCUs {

    private JFrame marco;

    public GUIMenuCUsImp() {
        marco = new JFrame("Menú de Gestión de Seguridad");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal con BoxLayout vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // == Sección Zonas Restringidas ==
        JPanel panelZonas = new JPanel(new GridLayout(2, 2, 10, 10));
        panelZonas.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Zonas Restringidas",
            TitledBorder.LEFT, TitledBorder.TOP
        ));
        JButton btnCrearAcceso      = new JButton("Crear Acceso");
        JButton btnEliminarAcceso   = new JButton("Eliminar Acceso");
        JButton btnModificarAcceso  = new JButton("Modificar Acceso");
        JButton btnVerificarAcceso  = new JButton("Verificar Acceso");
        panelZonas.add(btnCrearAcceso);
        panelZonas.add(btnEliminarAcceso);
        panelZonas.add(btnModificarAcceso);
        panelZonas.add(btnVerificarAcceso);

        // == Sección Dispositivos de Seguridad ==
        JPanel panelDisp = new JPanel(new GridLayout(2, 2, 10, 10));
        panelDisp.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Dispositivos de Seguridad",
            TitledBorder.LEFT, TitledBorder.TOP
        ));
        JButton btnActivarDispositivo    = new JButton("Activar Dispositivo");
        JButton btnDesactivarDispositivo = new JButton("Desactivar Dispositivo");
        JButton btnConfigurarDispositivo = new JButton("Configurar Dispositivo");
        JButton btnConsultarHistorial    = new JButton("Consultar Historial");
        panelDisp.add(btnActivarDispositivo);
        panelDisp.add(btnDesactivarDispositivo);
        panelDisp.add(btnConfigurarDispositivo);
        panelDisp.add(btnConsultarHistorial);

        // == Botón Salir ==
        JPanel panelSalir = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalir = new JButton("Salir");
        btnSalir.setPreferredSize(new Dimension(100, 30));
        panelSalir.add(btnSalir);

        // Añadimos todo a mainPanel
        mainPanel.add(panelZonas);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(panelDisp);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(panelSalir);

        marco.getContentPane().add(mainPanel);
        marco.pack();
        marco.setLocationRelativeTo(null);

        // == Listeners de Zonas Restringidas ==
        btnCrearAcceso.addActionListener(e -> {
            marco.setVisible(false);
            GUICrearAccesoImp v = (GUICrearAccesoImp) GUICrearAcceso.getInstancia();
            v.getFrame().setVisible(true);
        });
        btnEliminarAcceso.addActionListener(e -> {
            marco.setVisible(false);
            GUIEliminarAccesoImp v = (GUIEliminarAccesoImp) GUIEliminarAcceso.getInstancia();
            v.getFrame().setVisible(true);
        });
        btnModificarAcceso.addActionListener(e -> {
            marco.setVisible(false);
            GUIModificarAccesoImp v = (GUIModificarAccesoImp) GUIModificarAcceso.getInstancia();
            v.getFrame().setVisible(true);
        });
        btnVerificarAcceso.addActionListener(e -> {
            marco.setVisible(false);
            GUIVerificarAccesoImp v = (GUIVerificarAccesoImp) GUIVerificarAcceso.getInstancia();
            v.getFrame().setVisible(true);
        });

        // == Listeners de Dispositivos de Seguridad ==
        btnActivarDispositivo.addActionListener(e -> {
            marco.setVisible(false);
            GUIActivarDispositivoImp v = (GUIActivarDispositivoImp) GUIActivarDispositivo.getInstancia();
            v.getFrame().setVisible(true);
        });
        btnDesactivarDispositivo.addActionListener(e -> {
            marco.setVisible(false);
            GUIDesactivarDispositivoImp v = (GUIDesactivarDispositivoImp) GUIDesactivarDispositivo.getInstancia();
            v.getFrame().setVisible(true);
        });
        btnConfigurarDispositivo.addActionListener(e -> {
            marco.setVisible(false);
            GUIConfigurarDispositivoImp v = (GUIConfigurarDispositivoImp) GUIConfigurarDispositivo.getInstancia();
            v.getFrame().setVisible(true);
        });
        btnConsultarHistorial.addActionListener(e -> {
            marco.setVisible(false);
            GUIConsultarHistorialImp v = (GUIConsultarHistorialImp) GUIConsultarHistorial.getInstancia();
            v.getFrame().setVisible(true);
        });

        // == Listener Salir ==
        btnSalir.addActionListener(e -> marco.dispose());

        marco.setVisible(true);
    }


    public void actualizar(int evento, Object datos) {
        // Si en un futuro necesitas refrescar el menú, lo gestionas aquí.
    }


    public JFrame getFrame() {
        return marco;
    }
}
