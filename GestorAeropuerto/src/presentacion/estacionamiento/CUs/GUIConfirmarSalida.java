package presentacion.estacionamiento.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.estacionamiento.Controlador;
import presentacion.estacionamiento.Eventos;

public class GUIConfirmarSalida {
    private static GUIConfirmarSalida instancia = new GUIConfirmarSalida();
    public static GUIConfirmarSalida getInstancia() { return instancia; }

    private JFrame frame;
    private int numero;

    private GUIConfirmarSalida() {
        frame = new JFrame("Confirmar salida");
        frame.setLayout(new GridLayout(5, 2, 10, 10));
        frame.add(new JLabel("Número de plaza:"));
        JTextField txtNum = new JTextField(); txtNum.setEditable(false); frame.add(txtNum);
        frame.add(new JLabel("Matrícula:"));
        JTextField txtMat = new JTextField(); txtMat.setEditable(false); frame.add(txtMat);
        frame.add(new JLabel("Tiempo estacionado (min):"));
        JTextField txtTime = new JTextField(); txtTime.setEditable(false); frame.add(txtTime);
        frame.add(new JLabel("Coste (€):"));
        JTextField txtCost = new JTextField(); txtCost.setEditable(false); frame.add(txtCost);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        btnAceptar.addActionListener(e -> {
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.CONFIRMAR_SALIDA, numero);
        });
        btnCancelar.addActionListener(e -> {
            frame.setVisible(false);
            GUIIngresoSalida.getInstancia().mostrar();
        });
        frame.add(btnAceptar);
        frame.add(btnCancelar);
        frame.pack(); frame.setLocationRelativeTo(null);
    }

    public void mostrar(int numero, String matricula, long minutos, double coste) {
        this.numero = numero;
        ((JTextField)frame.getContentPane().getComponent(1)).setText(String.valueOf(numero));
        ((JTextField)frame.getContentPane().getComponent(3)).setText(matricula);
        ((JTextField)frame.getContentPane().getComponent(5)).setText(String.valueOf(minutos));
        ((JTextField)frame.getContentPane().getComponent(7)).setText(String.format("%.2f", coste));
        frame.setVisible(true);
    }
}