package presentacion.seguridad.CUs;

import javax.swing.JFrame;


public abstract class GUIModificarAcceso {
    protected static GUIModificarAcceso instancia;

    public static GUIModificarAcceso getInstancia() {
        if (instancia == null) instancia = new GUIModificarAccesoImp();
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
    public abstract JFrame getFrame();
}
