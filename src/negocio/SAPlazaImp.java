package negocio;

import java.util.List;
import java.time.Duration;
import java.util.ArrayList;
import integracion.FactoriaDAO;
import integracion.DAOPlaza;

public class SAPlazaImp implements SAPlaza {
    private DAOPlaza dao = FactoriaDAO.getInstancia().getDAOPlaza();
    private static final double PRECIO_POR_MINUTO = 0.05;

    // Lista de observadores
    private List<PlazaObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(PlazaObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(PlazaObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(TransferPlaza plaza) {
        for (PlazaObserver o : observers) {
            o.onPlazaChanged(plaza);
        }
    }

    @Override
    public List<TransferPlaza> listarPlazas() throws Exception {
        List<TransferPlaza> lista = dao.obtenerPlazas();
        // Notificar estado inicial de cada plaza
        for (TransferPlaza p : lista) {
            notifyObservers(p);
        }
        return lista;
    }

    @Override
    public TransferPlaza obtenerPlaza(int numero) throws Exception {
        return dao.obtenerPlaza(numero);
    }

    @Override
    public void llegaVehiculo(int numero, String matricula) throws Exception {
        dao.llegaVehiculo(numero, matricula);
        TransferPlaza p = obtenerPlaza(numero);
        notifyObservers(p);
    }

    @Override
    public double calcularCoste(int numero) throws Exception {
        TransferPlaza p = dao.obtenerPlaza(numero);
        if (p.getEstado() != EstadoPlaza.OCUPADA) throw new Exception("Plaza no ocupada");
        if (p.getLlegada() == null) throw new Exception("Sin hora de llegada");
        long minutos = Duration.between(p.getLlegada(), java.time.LocalDateTime.now()).toMinutes();
        return minutos * PRECIO_POR_MINUTO;
    }

    @Override
    public void abandonaVehiculo(int numero) throws Exception {
        dao.abandonaVehiculo(numero);
        TransferPlaza p = obtenerPlaza(numero);
        notifyObservers(p);
    }

    @Override
    public void mantenimientoPlaza(int numero) throws Exception {
        dao.mantenimientoPlaza(numero);
        TransferPlaza p = obtenerPlaza(numero);
        notifyObservers(p);
    }

    @Override
    public void iniciarMantenimiento(int numero) throws Exception {
        dao.iniciarMantenimiento(numero);
        TransferPlaza p = obtenerPlaza(numero);
        notifyObservers(p);
    }

    @Override
    public void finalizarMantenimiento(int numero) throws Exception {
        dao.finalizarMantenimiento(numero);
        TransferPlaza p = obtenerPlaza(numero);
        notifyObservers(p);
    }
    
    @Override
    public void crearPlaza(int numero, EstadoPlaza estado) throws Exception {
        dao.crearPlaza(new TransferPlaza(numero, estado, ""));
        TransferPlaza p = dao.obtenerPlaza(numero);
        notifyObservers(p);
    }
    
    @Override
    public void eliminarPlaza(int numero) throws Exception {
        TransferPlaza p = dao.obtenerPlaza(numero);
        dao.eliminarPlaza(numero);
        notifyObservers(p);
    }
    
    @Override
    public void modificarPlaza(int numero, EstadoPlaza nuevoEstado) throws Exception {
        dao.modificarPlaza(numero, nuevoEstado);
        notifyObservers(dao.obtenerPlaza(numero));
    }
}
