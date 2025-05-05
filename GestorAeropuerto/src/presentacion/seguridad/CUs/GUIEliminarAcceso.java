package presentacion.seguridad.CUs;

import javax.swing.JFrame;


public abstract class GUIEliminarAcceso {
    protected static GUIEliminarAcceso instancia;

    public static GUIEliminarAcceso getInstancia() {
        if (instancia == null) instancia = new GUIEliminarAccesoImp();
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
    public abstract JFrame getFrame();
}