package presentacion.locales;

import javax.swing.*;
import java.util.List;

import negocio.locales.FactoriaSA;
import negocio.locales.FiltroLocal;
import negocio.locales.SALocales;
import negocio.locales.TransferLocal;
import presentacion.locales.CUs.GUIEliminarLocales;
import presentacion.locales.CUs.GUIMenuCUs;
import presentacion.locales.CUs.GUIMenuCUsImp;
import presentacion.locales.CUs.GUIModificarLocales;
import presentacion.locales.CUs.GUIMostrarLocales;

public class ControladorImp extends Controlador {

    private final SALocales sa = FactoriaSA.getInstancia().crearSALocales();

    @Override
    public void accion(int evento, Object datos) {

        switch (evento) {

            case Eventos.ANADIR_LOCALES: {
                TransferLocal local = (TransferLocal) datos;
                int id = sa.anadir(local);
                JOptionPane.showMessageDialog(null, "Local a�adido con ID: " + id);
                break;
            }

            case Eventos.ELIMINAR_LOCALES: {
                int id = (int) datos;
                boolean ok = sa.eliminar(id);
                if (ok)
                    JOptionPane.showMessageDialog(null, "Local eliminado con �xito");
                else
                    JOptionPane.showMessageDialog(null, "Error al eliminar el local");
                break;
            }

            case Eventos.MODIFICAR_LOCALES: {
                TransferLocal local = (TransferLocal) datos;
                boolean ok = sa.modificar(local);
                if (ok)
                    JOptionPane.showMessageDialog(null, "Local modificado correctamente");
                else
                    JOptionPane.showMessageDialog(null, "Error al modificar el local");
                break;
            }
            
            case Eventos.MOSTRAR_PARA_MODIFICAR: {
                List<TransferLocal> lista = sa.mostrar();
                presentacion.locales.CUs.GUIModificarLocalesImp gui = (presentacion.locales.CUs.GUIModificarLocalesImp) GUIModificarLocales.getInstancia();
                gui.actualizar(evento, lista);
                break;
            }



            case Eventos.MOSTRAR_LOCALES: {
            	try {
                List<TransferLocal> lista = sa.mostrar();
                presentacion.locales.CUs.GUIMostrarLocalesImp guiMostrar = (presentacion.locales.CUs.GUIMostrarLocalesImp) GUIMostrarLocales.getInstancia();
                guiMostrar.actualizar(evento, lista);
            	}catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al cargar los locales: " + ex.getMessage());
                }
                break;
            }

            case Eventos.FILTRAR_LOCALES: {
                String subcadena = (String) datos;
                List<TransferLocal> filtrados = sa.filtrarPorNombre(subcadena);
                presentacion.locales.CUs.GUIMostrarLocalesImp guiMostrar = (presentacion.locales.CUs.GUIMostrarLocalesImp) GUIMostrarLocales.getInstancia();
                guiMostrar.actualizar(evento, filtrados);
                break;
            }


            case Eventos.ACEPTAR_CAMBIOS: {
                JOptionPane.showMessageDialog(null, "Se aceptar�an los cambios");
                break;
            }

            case Eventos.CANCELAR_CAMBIOS: {
                JOptionPane.showMessageDialog(null, "Se cancelar�an los cambios");
                break;
            }

            case Eventos.VOLVER_MENU: {
                GUIMenuCUsImp menu = (GUIMenuCUsImp) GUIMenuCUs.getInstancia();
                JFrame menuFrame = menu.getFrame();
                menuFrame.setVisible(true);
                break;
            }
            
            case Eventos.FILTRAR_AVANZADO: {
                FiltroLocal filtro = (FiltroLocal) datos;
                List<TransferLocal> resultado = sa.filtrarAvanzado(filtro);

                // Si este c�digo es para GUIMostrarLocales:
                presentacion.locales.CUs.GUIMostrarLocalesImp gui = (presentacion.locales.CUs.GUIMostrarLocalesImp) GUIMostrarLocales.getInstancia();
                gui.actualizar(evento, resultado);
                break;
            }

            case Eventos.FILTRAR_AVANZADO_MODIFICAR: {
                FiltroLocal filtro = (FiltroLocal) datos;
                List<TransferLocal> resultado = sa.filtrarAvanzado(filtro);
                presentacion.locales.CUs.GUIModificarLocalesImp gui = (presentacion.locales.CUs.GUIModificarLocalesImp) GUIModificarLocales.getInstancia();
                gui.actualizar(evento, resultado);
                break;
            }
            
            case Eventos.FILTRAR_AVANZADO_ELIMINAR: {
                FiltroLocal filtro = (FiltroLocal) datos;
                List<TransferLocal> resultado = sa.filtrarAvanzado(filtro);
                presentacion.locales.CUs.GUIEliminarLocalesImp gui = (presentacion.locales.CUs.GUIEliminarLocalesImp) GUIEliminarLocales.getInstancia();
                gui.actualizar(evento, resultado);
                break;
            }
            
            case Eventos.MOSTRAR_PARA_ELIMINAR: {
                List<TransferLocal> lista = sa.mostrar();
                presentacion.locales.CUs.GUIEliminarLocalesImp gui = (presentacion.locales.CUs.GUIEliminarLocalesImp) GUIEliminarLocales.getInstancia();
                gui.actualizar(evento, lista);
                break;
            }




        }
    }
}
