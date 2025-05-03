package presentacion.financiera.CUs;

public abstract class GUICrearCuenta {
    private static GUICrearCuenta instancia;

    public static GUICrearCuenta getInstancia() {
        if (instancia == null)
            instancia = new GUICrearCuentaImp();
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
}
