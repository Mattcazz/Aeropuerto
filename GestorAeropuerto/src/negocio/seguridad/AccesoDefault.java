package negocio.seguridad;

import java.util.Set;

/**
 * Política básica: solo concede acceso a un conjunto predefinido de zonas.
 */
public class AccesoDefault implements EstrategiaAcceso {

    @Override
    public void gestionarAcceso(TransferAcceso datos) {
        String zona = datos.getZona();
        if (zona == null || zona.isBlank()) {
            throw new RuntimeException("Zona inválida para acceso");
        }
        if (!Zonas.DEFAULT.contains(datos.getZona())) {
            throw new RuntimeException(
                "Acceso DENEGADO a zona \"" + zona + 
                "\". Solo permitido en: " + Zonas.DEFAULT
            );
        }
        System.out.println("[Default] Acceso concedido para zona: " 
            + zona + " (ID empleado: " + datos.getEmpleadoDni() + ")");
    }
}
