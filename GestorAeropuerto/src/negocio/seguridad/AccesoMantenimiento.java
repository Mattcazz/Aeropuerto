package negocio.seguridad;

/**
 * Permite accesos a Mantenimiento incluso fuera de horario laboral estándar.
 */
public class AccesoMantenimiento implements EstrategiaAcceso {
    @Override
    public void gestionarAcceso(TransferAcceso datos) {
        java.time.LocalTime ahora = java.time.LocalTime.now();
        // Horario estándar 08–18h
        boolean horarioLaboral = !ahora.isBefore(java.time.LocalTime.of(8,0))
                               && !ahora.isAfter(java.time.LocalTime.of(18,0));
        if (horarioLaboral) {
            System.out.println("[Mantenimiento] Acceso en horario laboral permitido.");
        } else {
            System.out.println("[Mantenimiento] Acceso FUERA de horario laboral permitido para mantenimiento.");
        }
    }
}
