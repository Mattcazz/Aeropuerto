package presentacion.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.Controlador;
import presentacion.Eventos;
import negocio.EstadoPlaza;

public class GUIModificarPlazaImp extends GUIModificarPlaza {
    private static GUIModificarPlazaImp instancia = new GUIModificarPlazaImp();
    public static GUIModificarPlazaImp getInstancia() { return instancia; }

    private JFrame frameNum;
    private JTextField txtNumero;
    private JFrame frameEstado;
    private JComboBox<EstadoPlaza> cbEstado;
    private int numero;

    public GUIModificarPlazaImp() {
        // Paso 1: pedir número de plaza
        frameNum = new JFrame("Modificar Plaza - Paso 1");
        frameNum.setLayout(new GridLayout(2, 2, 10, 10));
        frameNum.add(new JLabel("Número de plaza:"));
        txtNumero = new JTextField(); frameNum.add(txtNumero);
        JButton okNum = new JButton("Aceptar"), cancelNum = new JButton("Cancelar");
        okNum.addActionListener(e -> {
            try {
                numero = Integer.parseInt(txtNumero.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frameNum,
                    "Número inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            frameNum.setVisible(false);
            frameEstado.setVisible(true);
        });
        cancelNum.addActionListener(e -> {
            frameNum.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });
        frameNum.add(okNum); frameNum.add(cancelNum);
        frameNum.pack();
        frameNum.setLocationRelativeTo(null);

        // Paso 2: seleccionar nuevo estado
        frameEstado = new JFrame("Modificar Plaza - Paso 2");
        frameEstado.setLayout(new GridLayout(2, 2, 10, 10));
        frameEstado.add(new JLabel("Nuevo estado:"));
        cbEstado = new JComboBox<>(EstadoPlaza.values()); frameEstado.add(cbEstado);
        JButton okEst = new JButton("Modificar"), cancelEst = new JButton("Cancelar");
        okEst.addActionListener(e -> {
            EstadoPlaza est = (EstadoPlaza) cbEstado.getSelectedItem();
            frameEstado.setVisible(false);
            Controlador.getInstancia().accion(
                Eventos.CONFIRMAR_MODIFICAR_PLAZA,
                new Object[]{ numero, est }
            );
        });
        cancelEst.addActionListener(e -> {
            frameEstado.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });
        frameEstado.add(okEst); frameEstado.add(cancelEst);
        frameEstado.pack();
        frameEstado.setLocationRelativeTo(null);
    }

    @Override
    public void mostrar() {
        frameNum.setVisible(true);
    }
    
	@Override
	public void actualizar(int evento, Object datos) {
	}

}
