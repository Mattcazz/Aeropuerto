package presentacion.seguridad.CUs;

import javax.swing.JFrame;


public abstract class GUIConfigurarDispositivo {
    protected static GUIConfigurarDispositivo instancia;

    public static GUIConfigurarDispositivo getInstancia() {
        if (instancia == null) {
            instancia = new GUIConfigurarDispositivoImp();
        }
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
    public abstract JFrame getFrame();
}
