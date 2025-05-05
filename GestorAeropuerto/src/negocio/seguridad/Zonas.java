package negocio.seguridad;

import java.util.List;

public class Zonas {
    public static final String[] TODAS = {
        "SalaComandancia",
        "Oficinas",
        "Almacén",
        "Control",
        "Torre",
        "Tráfico"
    };
    
    public static final List<String> DEFAULT = List.of(
        "SalaComandancia",
        "Oficinas",
        "Almacén"
    );

    public static final List<String> CONTROL_AVANZADO = List.of(
        "Control",
        "Torre",
        "Tráfico"
    );
}

