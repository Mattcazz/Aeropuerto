package negocio.seguridad;

public class AccesoControlAvanzado implements EstrategiaAcceso {
    @Override
    public void gestionarAcceso(TransferAcceso datos) {
        if (!Zonas.CONTROL_AVANZADO.contains(datos.getZona())) {
            System.out.println("[ControlAvanzado] Acceso concedido a zona de control: " + datos.getZona());
        } else {
            throw new RuntimeException("Solo permitido en zonas de control");
        }
    }
}
