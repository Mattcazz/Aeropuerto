package negocio.operaciones;

public class PeticionBloqueo {

	private TransferBloqueo bloqueo; 
	private String error;
	
	
	public TransferBloqueo getBloqueo() {
		return bloqueo;
	}
	public void setBloqueo(TransferBloqueo bloqueo) {
		this.bloqueo = bloqueo;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
