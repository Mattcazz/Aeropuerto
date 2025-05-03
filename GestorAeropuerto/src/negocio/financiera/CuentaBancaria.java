package negocio.financiera;

public class CuentaBancaria {
    private String iban;
    private String nombre;  // Nombre de uso interno: "Nómina", "Locales", etc.
    private float saldo;
    private String banco;

    public CuentaBancaria(TCuentaBancaria tCuenta) {
        this.iban = tCuenta.getIban();
        this.nombre = tCuenta.getNombre();
        this.saldo = tCuenta.getSaldo();
        this.banco = tCuenta.getBanco();
    }

    public CuentaBancaria(String iban, String nombre, float saldo, String banco) {
        this.iban = iban;
        this.nombre = nombre;
        this.saldo = saldo;
        this.banco = banco;
    }
   
    public String getIban() {
        return iban;
    }

    public String getNombre() {
        return nombre;
    }

    public float getSaldo() {
        return saldo;
    }

    public String getBanco() {
        return banco;
    }

	public void setSaldo(float nuevoSaldo) {
		this.saldo = nuevoSaldo;
	}
    
    public void abonar(float monto) {
        if (monto > 0) {
            saldo += monto;
        } else {
            throw new IllegalArgumentException("El monto debe ser positivo.");
        }
    }

    public void retirar(float monto) {
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        saldo -= monto;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "iban='" + iban + '\'' +
                ", nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                ", banco='" + banco + '\'' +
                '}';
    }
}
