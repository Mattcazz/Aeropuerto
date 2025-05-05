package integracion.estacionamiento;

import negocio.estacionamiento.TransferPlaza;
import negocio.estacionamiento.EstadoPlaza;

import java.sql.SQLException;
import java.util.List;

public class DAOPlazaProxy implements DAOPlaza {
    private final DAOPlazaImp daoReal;

    public DAOPlazaProxy() {
        this.daoReal = new DAOPlazaImp();
    }

    @Override
    public List<TransferPlaza> obtenerPlazas() throws SQLException {
        System.out.println("[Proxy DAO] Antes de obtenerPlazas");
        List<TransferPlaza> plazas = daoReal.obtenerPlazas();
        System.out.println("[Proxy DAO] Después de obtenerPlazas: " + plazas.size() + " plazas");
        return plazas;
    }

    @Override
    public TransferPlaza obtenerPlaza(int numero) throws Exception {
        System.out.println("[Proxy DAO] Antes de obtenerPlaza #" + numero);
        TransferPlaza p = daoReal.obtenerPlaza(numero);
        System.out.println("[Proxy DAO] Después de obtenerPlaza: " + p);
        return p;
    }

    @Override
    public boolean llegaVehiculo(int numero, String matricula) throws Exception {
        System.out.println("[Proxy DAO] Antes de llegaVehiculo: plaza " + numero + ", matrícula " + matricula);
        boolean res = daoReal.llegaVehiculo(numero, matricula);
        System.out.println("[Proxy DAO] Después de llegaVehiculo: " + res);
        return res;
    }

    @Override
    public boolean abandonaVehiculo(int numero) throws Exception {
        System.out.println("[Proxy DAO] Antes de abandonaVehiculo: plaza " + numero);
        boolean res = daoReal.abandonaVehiculo(numero);
        System.out.println("[Proxy DAO] Después de abandonaVehiculo: " + res);
        return res;
    }

    @Override
    public boolean mantenimientoPlaza(int numero) throws Exception {
        System.out.println("[Proxy DAO] Antes de mantenimientoPlaza: plaza " + numero);
        boolean res = daoReal.mantenimientoPlaza(numero);
        System.out.println("[Proxy DAO] Después de mantenimientoPlaza: " + res);
        return res;
    }

    @Override
    public boolean iniciarMantenimiento(int numero) throws Exception {
        System.out.println("[Proxy DAO] Antes de iniciarMantenimiento: plaza " + numero);
        boolean res = daoReal.iniciarMantenimiento(numero);
        System.out.println("[Proxy DAO] Después de iniciarMantenimiento: " + res);
        return res;
    }

    @Override
    public boolean finalizarMantenimiento(int numero) throws Exception {
        System.out.println("[Proxy DAO] Antes de finalizarMantenimiento: plaza " + numero);
        boolean res = daoReal.finalizarMantenimiento(numero);
        System.out.println("[Proxy DAO] Después de finalizarMantenimiento: " + res);
        return res;
    }

    @Override
    public boolean crearPlaza(TransferPlaza p) throws Exception {
        System.out.println("[Proxy DAO] Antes de crearPlaza: " + p);
        boolean res = daoReal.crearPlaza(p);
        System.out.println("[Proxy DAO] Después de crearPlaza: " + res);
        return res;
    }

    @Override
    public boolean eliminarPlaza(int numero) throws Exception {
        System.out.println("[Proxy DAO] Antes de eliminarPlaza: plaza " + numero);
        boolean res = daoReal.eliminarPlaza(numero);
        System.out.println("[Proxy DAO] Después de eliminarPlaza: " + res);
        return res;
    }

    @Override
    public boolean modificarPlaza(int numero, EstadoPlaza nuevoEstado) throws Exception {
        System.out.println("[Proxy DAO] Antes de modificarPlaza: plaza " + numero + ", estado " + nuevoEstado);
        boolean res = daoReal.modificarPlaza(numero, nuevoEstado);
        System.out.println("[Proxy DAO] Después de modificarPlaza: " + res);
        return res;
    }
}
