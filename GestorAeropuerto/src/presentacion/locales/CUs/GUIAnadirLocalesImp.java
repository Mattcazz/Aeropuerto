package presentacion.locales.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import presentacion.locales.Controlador;
import presentacion.locales.Eventos;
import negocio.locales.TransferLocal;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class GUIAnadirLocalesImp extends GUIAnadirLocales {

    // Configuraci�n visual (sin cambiar tu estructura)
    String funcButtonName = "Anadir";
    String[] columnas = {"IDLocal", "Empresa", "Ingresos", "Gastos", "Fecha contrato"};
    String tituloTabla = "Informacion Local";
    String tituloPanel = "Anadir Locales";
    int eventoFunc = Eventos.ANADIR_LOCALES;
    String descFunc = "Se anadiran los datos";

    JFrame victoria;
    JTable tabla;
    TransferLocal localTemporal = null;

    public GUIAnadirLocalesImp() {
        victoria = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(500, 250));

        JPanel funcButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton cancelButton = new JButton("Cancelar");
        JButton acceptButton = new JButton("Aceptar");
        JButton funcButton = new JButton(funcButtonName);
        JButton returnButton = new JButton("Volver");

        buttonsPanel.add(acceptButton);
        buttonsPanel.add(cancelButton);
        returnButtonPanel.add(returnButton);
        funcButtonPanel.add(funcButton);

        // Creamos la tabla editable con 1 sola fila
        DefaultTableModel model = new DefaultTableModel(columnas, 1);
        tabla = new JTable(model);
        JScrollPane tableScroll = new JScrollPane(tabla);

        JLabel title = new JLabel(tituloTabla);
        title.setFont(new Font("Dialog", Font.PLAIN, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createHorizontalGlue());
        panel.add(funcButtonPanel);
        panel.add(tableScroll);
        panel.add(buttonsPanel);
        panel.add(returnButtonPanel);

        victoria.getContentPane().add(panel);
        victoria.setTitle(tituloPanel);
        victoria.pack();

        // FUNC: preparar TransferLocal desde la fila de la tabla
        funcButton.addActionListener(e -> {
            try {
                String empresa = (String) tabla.getValueAt(0, 1);
                double ingresos = Double.parseDouble((String) tabla.getValueAt(0, 2));
                double gastos = Double.parseDouble((String) tabla.getValueAt(0, 3));
                //LocalDate fechaContrato = LocalDate.parse((String) tabla.getValueAt(0, 4));
                Object valorFecha = tabla.getValueAt(0, 4);

                if (valorFecha == null) {
                    JOptionPane.showMessageDialog(victoria, "El campo de fecha no puede estar vac�o.");
                    return;
                }

                String fechaStr = valorFecha.toString().trim();

                if (fechaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(victoria, "El campo de fecha est� vac�o.");
                    return;
                }

                LocalDate fechaContrato;
                try {
                    fechaContrato = LocalDate.parse(fechaStr); // yyyy-MM-dd
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(victoria, "Formato de fecha inv�lido. Usa yyyy-MM-dd");
                    return;
                }

                
                
                localTemporal = new TransferLocal(0, empresa, ingresos, gastos, fechaContrato);
                JOptionPane.showMessageDialog(null, "Datos preparados. Pulsa ACEPTAR para confirmar.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en los datos: " + ex.getMessage());
            }
        });

        // ACEPTAR: lanzar al controlador
        acceptButton.addActionListener(e -> {
            if (localTemporal != null) {
                Controlador.getInstancia().accion(eventoFunc, localTemporal);
                localTemporal = null;
                limpiarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Primero pulsa 'Anadir' para validar los datos.");
            }
        });

        // CANCELAR: limpiar tabla
        cancelButton.addActionListener(e -> {
            limpiarTabla();
            JOptionPane.showMessageDialog(null, "Campos reiniciados.");
        });

        // VOLVER
        returnButton.addActionListener(e -> {
            victoria.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });

        victoria.setVisible(true);
    }

    private void limpiarTabla() {
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.setValueAt("", 0, i);
        }
    }

    public void actualizar(int evento, Object datos) {
        // No utilizado aqu�, pero se deja para estructura MVC
    }

    public JFrame getFrame() {
        return victoria;
    }
}
