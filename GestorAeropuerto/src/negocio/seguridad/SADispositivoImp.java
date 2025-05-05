package negocio.seguridad;

import java.util.List;

import integracion.seguridad.DAODispositivo;
import integracion.FactoriaDAO;

public class SADispositivoImp implements SADispositivo {
    private final DAODispositivo dao;

    public SADispositivoImp() {
        this.dao = FactoriaDAO.getInstancia().nuevoDAODispositivo();
    }

    @Override
    public void activarDispositivo(TransferDispositivo disp) throws Exception {
        // 0) Recuperar desde BD y validar tipo
        TransferDispositivo real = dao.getDispositivo(disp.getId());
        if (real == null) {
            throw new Exception("Dispositivo ID=" + disp.getId() + " no existe");
        }
        if (!real.getTipo().equalsIgnoreCase(disp.getTipo())) {
            throw new Exception("Tipo incorrecto: ID=" + disp.getId() +
                                " es '" + real.getTipo() + "', no '" + disp.getTipo() + "'");
        }

        // 1) Activar
        boolean ok = dao.activar(disp);
        if (!ok) {
            throw new Exception("No se pudo activar dispositivo ID=" + disp.getId());
        }

        // 2) Registrar evento
        boolean histOk = dao.registrarEvento(disp, "Activación de dispositivo");
        if (!histOk) {
            throw new Exception("Error al registrar evento en historial para ID=" + disp.getId());
        }
    }

    @Override
    public void desactivarDispositivo(TransferDispositivo disp) throws Exception {
        // 0) Validar tipo
        TransferDispositivo real = dao.getDispositivo(disp.getId());
        if (real == null) {
            throw new Exception("Dispositivo ID=" + disp.getId() + " no existe");
        }
        if (!real.getTipo().equalsIgnoreCase(disp.getTipo())) {
            throw new Exception("Tipo incorrecto: ID=" + disp.getId() +
                                " es '" + real.getTipo() + "', no '" + disp.getTipo() + "'");
        }

        // 1) Desactivar
        boolean ok = dao.desactivar(disp);
        if (!ok) {
            throw new Exception("No se pudo desactivar dispositivo ID=" + disp.getId());
        }

        // 2) Registrar evento
        boolean histOk = dao.registrarEvento(disp, "Desactivación de dispositivo");
        if (!histOk) {
            throw new Exception("Error al registrar evento en historial para ID=" + disp.getId());
        }
    }

    @Override
    public void configurarDispositivo(TransferDispositivo disp) throws Exception {
        // 0) Validar tipo
        TransferDispositivo real = dao.getDispositivo(disp.getId());
        if (real == null) {
            throw new Exception("Dispositivo ID=" + disp.getId() + " no existe");
        }
        if (!real.getTipo().equalsIgnoreCase(disp.getTipo())) {
            throw new Exception("Tipo incorrecto: ID=" + disp.getId() +
                                " es '" + real.getTipo() + "', no '" + disp.getTipo() + "'");
        }

        // 1) Configurar
        boolean ok = dao.configurar(disp);
        if (!ok) {
            throw new Exception("No se pudo configurar dispositivo ID=" + disp.getId());
        }

        // 2) Registrar evento
        boolean histOk = dao.registrarEvento(disp,
            "Configuración nivelSensibilidad=" + disp.getNivelSensibilidad());
        if (!histOk) {
            throw new Exception("Error al registrar evento en historial para ID=" + disp.getId());
        }
    }

	@Override
	public List<TransferDispositivo> consultarHistorial(int dispositivoId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
