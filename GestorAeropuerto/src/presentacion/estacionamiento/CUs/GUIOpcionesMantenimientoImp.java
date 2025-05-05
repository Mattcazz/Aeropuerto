package presentacion.estacionamiento.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.estacionamiento.Controlador;
import presentacion.estacionamiento.Eventos;

public class GUIOpcionesMantenimientoImp {
    private static GUIOpcionesMantenimientoImp instancia = new GUIOpcionesMantenimientoImp();
    public static GUIOpcionesMantenimientoImp getInstancia() { return instancia; }

    private JFrame frame;
    private int numero;

    private GUIOpcionesMantenimientoImp() {
        frame = new JFrame("Opciones mantenimiento");
        frame.setLayout(new GridLayout(1,3,10,10));
        JButton btnStart = new JButton("Empezar mantenimiento");
        JButton btnEnd   = new JButton("Finalizar mantenimiento");
        JButton btnCancel= new JButton("Cancelar");

        btnStart.addActionListener(e -> {
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.EMPEZAR_MANTENIMIENTO, numero);
        });
        btnEnd.addActionListener(e -> {
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.FINALIZAR_MANTENIMIENTO, numero);
        });
        btnCancel.addActionListener(e -> {
            frame.setVisible(false);
            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
        });

        frame.add(btnStart);
        frame.add(btnEnd);
        frame.add(btnCancel);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void mostrar(int numeroPlaza) {
        this.numero = numeroPlaza;
        frame.setVisible(true);
    }
}