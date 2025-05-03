package negocio.operaciones;

import java.time.LocalDateTime;

public class TransferBloqueo {

	private int puertaId;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFinal;
    private String motivo;
    private String comentario;
    
	public int getPuertaId() {
		return puertaId;
	}
	public void setPuertaId(int puertaId) {
		this.puertaId = puertaId;
	}
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public LocalDateTime getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(LocalDateTime horaFinal) {
		this.horaFinal = horaFinal;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
    
    
}
