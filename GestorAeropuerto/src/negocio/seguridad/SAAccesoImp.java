package negocio.seguridad;

import integracion.seguridad.DAOAcceso;
import integracion.FactoriaDAO;

public class SAAccesoImp implements SAAcceso {
    private DAOAcceso dao = FactoriaDAO.getInstancia().nuevoDAOAcceso();
    private SelectorEstrategiaPorRol selector = new SelectorEstrategiaPorRol();

    @Override
    public void crearAcceso(TransferAcceso datos, String rol) throws Exception {
    	if (datos.getZona() == null || datos.getZona().isBlank() || datos.getDuracion() <= 0)
            throw new IllegalArgumentException("Zona y duración son obligatorios");

        // Seleccionar estrategia según rol
        var estrategia = selector.getEstrategiaParaRol(rol);
        estrategia.gestionarAcceso(datos);

        // Crear el acceso en la base de datos
        boolean ok = dao.crearAcceso(datos);
        if (!ok) throw new Exception("Error al crear acceso en la base de datos");

        // Registrar en historial de accesos
        dao.guardarHistoricoAcceso(datos.getAccesoId(), "Acceso creado para zona " + datos.getZona());
    }

    @Override
    public void eliminarAcceso(int accesoId) throws Exception {
        // 1) Registrar evento ANTES de eliminar, para no romper la FK
        dao.guardarHistoricoAcceso(
            accesoId,
            "Acceso eliminado"
        );

        // 2) Eliminar acceso
        boolean ok = dao.eliminarAcceso(accesoId);
        if (!ok) {
            throw new Exception("Error al eliminar acceso id=" + accesoId);
        }
    }

    @Override
    public void modificarAcceso(TransferAcceso datos) throws Exception {
        boolean ok = dao.modificarAcceso(datos);
        if (!ok) throw new Exception("Error al modificar acceso id=" + datos.getAccesoId());

        dao.guardarHistoricoAcceso(datos.getAccesoId(), "Acceso modificado: zona=" + datos.getZona());
    }

    @Override
    public boolean verificarAcceso(int idAcceso) throws Exception {
        boolean existe = dao.verificarAcceso(idAcceso);
        // Opcional: registrar verificación
        dao.guardarHistoricoAcceso(idAcceso, "Verificación de acceso, existe=" + existe);
        return existe;
    }
}