package presentacion.seguridad.CUs;

import javax.swing.JFrame;

public abstract class GUICrearAcceso {
    protected static GUICrearAcceso instancia;

    /** Singleton: obtiene la instancia de la GUI. */
    public static GUICrearAcceso getInstancia() {
        if (instancia == null) {
            instancia = new GUICrearAccesoImp();
        }
        return instancia;
    }

    /**
     * Actualiza la vista según el evento recibido.
     * @param evento código de evento
     * @param datos  datos asociados (si los hay)
     */
    public abstract void actualizar(int evento, Object datos);

    /**
     * Permite al controlador u otras GUIs acceder al JFrame.
     * @return el JFrame raíz de esta GUI
     */
    public abstract JFrame getFrame();
}
