package negocio.seguridad;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RegistrosDeEstrategias {
    private final Map<String, EstrategiaAcceso> registro = new LinkedHashMap<>();

    public RegistrosDeEstrategias() {
        registro.put("Default",       new AccesoDefault());
        registro.put("Global",        new AccesoGlobal());
        registro.put("PorTurno",      new AccesoPorTurno());
        registro.put("Mantenimiento", new AccesoMantenimiento());
        registro.put("ControlAvanzado", new AccesoControlAvanzado());
    }

    /** Nombres de las estrategias (para poblar un combo). */
    public Set<String> getSetNombreEstrategias() {
        return registro.keySet();
    }

    /** Recupera la estrategia con este nombre. */
    public EstrategiaAcceso getEstrategia(String nombre) {
        EstrategiaAcceso e = registro.get(nombre);
        if (e == null) throw new IllegalArgumentException("Estrategia desconocida: " + nombre);
        return e;
    }
}
