package presentacion.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.Controlador;
import presentacion.Eventos;

public class GUIEliminarPlazaImp extends GUIEliminarPlaza {
    private static GUIEliminarPlazaImp instancia = new GUIEliminarPlazaImp();
    public static GUIEliminarPlazaImp getInstancia() { return instancia; }

    private JFrame frame;
    private JTextField txtNumero;

    public GUIEliminarPlazaImp() {
        frame = new JFrame("Eliminar Plaza");
        frame.setLayout(new GridLayout(2, 2, 10, 10));
        frame.add(new JLabel("Número de plaza"));
        txtNumero = new JTextField(); frame.add(txtNumero);
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        btnAceptar.addActionListener(e -> {
            int numero;
            try {
                numero = Integer.parseInt(txtNumero.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Número inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.CONFIRMAR_ELIMINAR_PLAZA, numero);
        });
        btnCancelar.addActionListener(e -> {
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });
        frame.add(btnAceptar);
        frame.add(btnCancelar);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
	public void actualizar(int evento, Object datos) {		
	}
    
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }
}
