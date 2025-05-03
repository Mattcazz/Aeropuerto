package presentacion.incidencias.CUs;

import javax.swing.*;

import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIBorrarIncidenciaImp extends GUIBorrarIncidencia {

    private JFrame marco;
    private JComboBox<String> comboTipoIncidencia;
    private JTextField campoIdentificador;

    public GUIBorrarIncidenciaImp() {
        marco = new JFrame("Borrar Incidencia");
        marco.setSize(400, 250);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Borrar Incidencia", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        marco.add(titulo, BorderLayout.NORTH);

        // Panel central con los campos
        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCampos.add(new JLabel("Tipo de incidencia:"));
        comboTipoIncidencia = new JComboBox<>(new String[] { "Vuelo", "Equipaje" });
        panelCampos.add(comboTipoIncidencia);

        panelCampos.add(new JLabel("Identificador:"));
        campoIdentificador = new JTextField();
        panelCampos.add(campoIdentificador);

        marco.add(panelCampos, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonBorrar = new JButton("Borrar");
        JButton botonVolver = new JButton("Volver");
        panelBotones.add(botonVolver);
        panelBotones.add(botonBorrar);

        marco.add(panelBotones, BorderLayout.SOUTH);

        botonBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) comboTipoIncidencia.getSelectedItem();
                String identificador = campoIdentificador.getText();
                Controlador.getInstancia().accion(Eventos.BORRAR_INCIDENCIA, new String[] { tipo, identificador });
            }
        });

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                marco.setVisible(false);
                Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
            }
        });

        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
    	campoIdentificador.setText("");
        comboTipoIncidencia.setSelectedIndex(0); 
    }

    public JFrame getFrame() {
        return marco;
    }
}