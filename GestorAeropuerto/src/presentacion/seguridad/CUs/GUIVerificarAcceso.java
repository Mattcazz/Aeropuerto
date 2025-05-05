package presentacion.seguridad.CUs;

import javax.swing.JFrame;


public abstract class GUIVerificarAcceso {
    protected static GUIVerificarAcceso instancia;

    public static GUIVerificarAcceso getInstancia() {
        if (instancia == null) instancia = new GUIVerificarAccesoImp();
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
    public abstract JFrame getFrame();
}
