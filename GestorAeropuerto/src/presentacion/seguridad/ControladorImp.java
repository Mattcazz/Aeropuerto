package presentacion.seguridad;

import presentacion.seguridad.CUs.GUIMenuCUs;
import presentacion.seguridad.CUs.GUICrearAcceso;
import presentacion.seguridad.CUs.GUIEliminarAcceso;
import presentacion.seguridad.CUs.GUIModificarAcceso;
import presentacion.seguridad.CUs.GUIVerificarAcceso;
import presentacion.seguridad.CUs.GUIActivarDispositivo;
import presentacion.seguridad.CUs.GUIDesactivarDispositivo;
import presentacion.seguridad.CUs.GUIConfigurarDispositivo;
import presentacion.seguridad.CUs.GUIConsultarHistorial;

/**
 * Implementación del controlador central que despacha eventos
 * entre la capa de presentación y los servicios de negocio.
 */
public class ControladorImp extends Controlador {

    @Override
    public void accion(int evento, Object datos) {
        switch (evento) {
            case Eventos.CREAR_ACCESO: {
                GUICrearAcceso.getInstancia().getFrame().setVisible(true);
                break;
            }
            case Eventos.ELIMINAR_ACCESO: {
                GUIEliminarAcceso.getInstancia().getFrame().setVisible(true);
                break;
            }
            case Eventos.MODIFICAR_ACCESO: {
                GUIModificarAcceso.getInstancia().getFrame().setVisible(true);
                break;
            }
            case Eventos.VERIFICAR_ACCESO: {
                GUIVerificarAcceso.getInstancia().getFrame().setVisible(true);
                break;
            }
            case Eventos.ACTIVAR_DISPOSITIVO: {
                GUIActivarDispositivo.getInstancia().getFrame().setVisible(true);
                break;
            }
            case Eventos.DESACTIVAR_DISPOSITIVO: {
                GUIDesactivarDispositivo.getInstancia().getFrame().setVisible(true);
                break;
            }
            case Eventos.CONFIGURAR_DISPOSITIVO: {
                GUIConfigurarDispositivo.getInstancia().getFrame().setVisible(true);
                break;
            }
            case Eventos.CONSULTAR_HISTORIAL: {
                GUIConsultarHistorial.getInstancia().getFrame().setVisible(true);
                break;
            }
            case Eventos.VOLVER_MENU: {
                GUIMenuCUs.getInstancia().getFrame().setVisible(true);
                break;
            }
            case Eventos.ACEPTAR_CAMBIOS:
            case Eventos.CANCELAR_CAMBIOS:
            default:
                // Opciones de confirmación/cancelación se gestionan en cada GUI
                break;
        }
    }
}
