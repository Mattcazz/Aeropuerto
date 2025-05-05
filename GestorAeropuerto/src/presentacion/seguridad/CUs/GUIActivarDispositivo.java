package presentacion.seguridad.CUs;

import javax.swing.JFrame;


public abstract class GUIActivarDispositivo {
    protected static GUIActivarDispositivo instancia;

    public static GUIActivarDispositivo getInstancia() {
        if (instancia == null) {
            instancia = new GUIActivarDispositivoImp();
        }
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
    public abstract JFrame getFrame();
}
