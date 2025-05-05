package negocio.seguridad;

//Interfaz común para las políticas de creación/verificación de accesos.
public interface EstrategiaAcceso {
	void gestionarAcceso(TransferAcceso datos);
}
