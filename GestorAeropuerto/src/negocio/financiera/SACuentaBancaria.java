package negocio.financiera;

import java.util.List;

public interface SACuentaBancaria {

    public void crearCuenta(TCuentaBancaria cuenta) throws Exception;

    public TCuentaBancaria buscarPorNombre(String nombre) throws Exception;

    public List<TCuentaBancaria> listarCuentas() throws Exception;

    public void actualizarSaldo(String nombreCuenta, float nuevoSaldo) throws Exception;
    
    void realizarOperacion(String nombreCuenta, String tipo, float monto, String concepto) throws Exception;
}

