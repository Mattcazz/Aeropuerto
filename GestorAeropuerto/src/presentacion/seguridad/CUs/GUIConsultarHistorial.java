package presentacion.seguridad.CUs;

import javax.swing.JFrame;


public abstract class GUIConsultarHistorial {
    protected static GUIConsultarHistorial instancia;
    public static GUIConsultarHistorial getInstancia() {
        if (instancia == null) {
            instancia = new GUIConsultarHistorialImp();
        }
        return instancia;
    }
    public abstract void actualizar(int evento, Object datos);
    public abstract JFrame getFrame();
}
