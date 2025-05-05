package presentacion.estacionamiento.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.estacionamiento.Controlador;
import presentacion.estacionamiento.Eventos;

public class GUIInputMatriculaPlazaImp extends GUIInputMatriculaPlaza {
    private static GUIInputMatriculaPlazaImp instancia = new GUIInputMatriculaPlazaImp();
    public static GUIInputMatriculaPlazaImp getInstancia() { return instancia; }

    private JFrame frame;
    private JTextField txtMatricula;
    private int numeroPlaza;

    private GUIInputMatriculaPlazaImp() {
        frame = new JFrame("Introducir Matrícula");
        frame.setLayout(new GridLayout(2, 2, 10, 10));
        frame.add(new JLabel("Matrícula (1234-ABC):"));
        txtMatricula = new JTextField(); frame.add(txtMatricula);
        JButton ok = new JButton("Aceptar"), cancel = new JButton("Cancelar");
        ok.addActionListener(e -> {
            String mat = txtMatricula.getText().trim();
            // validación del formato de la matricula
            if (!mat.matches("\\d{4}[- ]?[A-Z]{3}")) {
                JOptionPane.showMessageDialog(frame,
                    "Formato inválido. Use 1234-ABC, 1234ABC o 1234 ABC",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            frame.setVisible(false);
            Controlador.getInstancia().accion(
                Eventos.CONFIRMAR_INPUT_MATRICULA,
                new Object[]{ numeroPlaza, mat }
            );
        });
        cancel.addActionListener(e -> {
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });
        frame.add(ok); frame.add(cancel);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void mostrar(int numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
        txtMatricula.setText("");
        frame.setVisible(true);
    }
}