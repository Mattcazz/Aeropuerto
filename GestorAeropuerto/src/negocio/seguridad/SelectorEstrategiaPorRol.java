package negocio.seguridad;

public class SelectorEstrategiaPorRol {

    private final RegistrosDeEstrategias catalogo;

    public SelectorEstrategiaPorRol() {
        this.catalogo = new RegistrosDeEstrategias();
    }

    /**
     * Retorna la estrategia según el rol:
     * - "JefeAeropuerto"   → AccesoGlobal
     * - "Coordinador"      → AccesoGlobal
     * - "Mantenimiento"    → AccesoMantenimiento
     * - "Facturación"      → AccesoPorTurno
     * - "Azafata"          → AccesoPorHorario
     * - "Controlador"      → AccesoControlAvanzado
     * - cualquier otro    → AccesoDefault
     */
    public EstrategiaAcceso getEstrategiaParaRol(String rol) {
        switch (rol) {
            case "JefeAeropuerto":
            case "Coordinador":
                return catalogo.getEstrategia("Global");
            case "Mantenimiento":
                return catalogo.getEstrategia("Mantenimiento");
            case "Facturación":
            case "Azafata":
                return catalogo.getEstrategia("PorTurno");
            case "Controlador":
                return catalogo.getEstrategia("ControlAvanzado");
            default:
                return catalogo.getEstrategia("Default");
        }
    }
}