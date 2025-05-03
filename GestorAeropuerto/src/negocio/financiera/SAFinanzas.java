package negocio.financiera;

import java.util.List;

public interface SAFinanzas {
    public void crearCuenta(TCuentaBancaria tCuenta) throws Exception;
    public void realizarOperacion(TFlujoCaja tFlujo) throws Exception;
    public List<TCuentaBancaria> listarCuentas() throws Exception;
    public List<TFlujoCaja> listarFlujos() throws Exception;
    public void completarFlujo(int id) throws Exception;
}

