package negocio.seguridad;

public interface SAAcceso {
   
    //Crea un acceso y registra el histórico.
    void crearAcceso(TransferAcceso datos, String rol) throws Exception;


    //Elimina un acceso existente.
    void eliminarAcceso(int idAcceso) throws Exception;


    //Modifica los datos de un acceso.
    void modificarAcceso(TransferAcceso datos) throws Exception;

    //Verifica la existencia de un acceso.
    boolean verificarAcceso(int idAcceso) throws Exception;
}
