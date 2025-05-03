package negocio.financiera;

public class TCuentaBancariaBuilder {

    private String iban = "ES00000000000000000000";
    private String nombre = "Nueva  cuenta";
    private float saldo = 0.0f;  // valor por defecto
    private String banco = "Santander";

    public TCuentaBancariaBuilder conIban(String iban) {
        this.iban = iban;
        return this;
    }

    public TCuentaBancariaBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public TCuentaBancariaBuilder conSaldo(float saldo) {
        this.saldo = saldo;
        return this;
    }

    public TCuentaBancariaBuilder conBanco(String banco) {
        this.banco = banco;
        return this;
    }

    public TCuentaBancaria build() {
        return new TCuentaBancaria(iban, nombre, saldo, banco);
    }
}
