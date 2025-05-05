package negocio.seguridad;

public class AccesoGlobal implements EstrategiaAcceso {
    @Override
    public void gestionarAcceso(TransferAcceso datos) {
        // No se realizan validaciones extra
        System.out.println("[Global] Acceso TOTAL concedido para ID=" + datos.getEmpleadoDni()
            + " en zona: " + datos.getZona());
    }
}
