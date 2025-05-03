package negocio.financiera;

public class TCuentaBancaria {

	private String iban;
	private String nombre; 
	private float saldo;
	private String banco;

	public TCuentaBancaria(String iban, String nombre, float saldo, String banco) {
		this.iban = iban;
		this.nombre = nombre;
		this.saldo = saldo;
		this.banco = banco;
	}

	public TCuentaBancaria() {
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

	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public void setBanco(String banco) {
		this.banco = banco;
	}

}
