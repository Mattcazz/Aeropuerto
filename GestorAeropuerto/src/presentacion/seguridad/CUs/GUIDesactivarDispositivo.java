package presentacion.seguridad.CUs;

import javax.swing.JFrame;


public abstract class GUIDesactivarDispositivo {
    protected static GUIDesactivarDispositivo instancia;

    public static GUIDesactivarDispositivo getInstancia() {
        if (instancia == null) {
            instancia = new GUIDesactivarDispositivoImp();
        }
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
    public abstract JFrame getFrame();
}
