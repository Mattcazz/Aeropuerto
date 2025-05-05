package negocio.seguridad;

import java.time.LocalDateTime;

/**
 * DTO para los eventos del historial de un dispositivo.
 */
public class TransferEventoDispositivo {
    private LocalDateTime fecha;
    private String descripcion;

    public TransferEventoDispositivo() {}
    public TransferEventoDispositivo(LocalDateTime fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
