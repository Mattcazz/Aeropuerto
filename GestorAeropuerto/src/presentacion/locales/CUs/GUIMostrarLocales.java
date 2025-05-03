package presentacion.locales.CUs;

import javax.swing.JFrame;

public abstract class GUIMostrarLocales {

    static GUIMostrarLocales instancia = null;

    public static GUIMostrarLocales getInstancia() {
        if (instancia == null)
            instancia = new GUIMostrarLocalesImp(); // aseg�rate de que sea la clase correcta
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
    public abstract JFrame getFrame();
    public abstract void mostrar();

}