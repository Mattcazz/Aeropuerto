package negocio.seguridad;

public class TransferDispositivo {
	private int id;                   // ID del dispositivo
    private String tipo;              // "Alarma" o "Cámara"
    private boolean estado;            // true = "activo" o false = "inactivo"
    private int nivelSensibilidad;    // Parámetro para configuración

    public TransferDispositivo() {}

    public TransferDispositivo(int id, String tipo, boolean estado, int nivelSensibilidad) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.nivelSensibilidad = nivelSensibilidad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public boolean getEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public int getNivelSensibilidad() { return nivelSensibilidad; }
    public void setNivelSensibilidad(int nivelSensibilidad) { this.nivelSensibilidad = nivelSensibilidad; }
}

