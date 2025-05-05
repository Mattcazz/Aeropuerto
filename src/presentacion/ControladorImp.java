package presentacion;

import java.util.List;
import javax.swing.JOptionPane;

import negocio.EstadoPlaza;
import negocio.FactoriaSA;
import negocio.SAPlaza;
import negocio.TransferPlaza;
import presentacion.CUs.GUIListaPlazas;
import presentacion.CUs.GUIConfirmarSalida;
import presentacion.CUs.GUICrearPlaza;
import presentacion.CUs.GUIEliminarPlaza;
import presentacion.CUs.GUIIngresoSalida;
import presentacion.CUs.GUIInputMatriculaPlaza;
import presentacion.CUs.GUIMenuCUs;
import presentacion.CUs.GUIModificarPlaza;
import presentacion.CUs.GUIOpcionesMantenimientoImp;

public class ControladorImp extends Controlador {
    private SAPlaza sa = FactoriaSA.getInstancia().getSAPlaza();

    @Override
    public void accion(int evento, Object datos) {
        try {
            switch (evento) {
                case Eventos.LISTA_PLAZAS:
                    List<TransferPlaza> plazas = sa.listarPlazas();
                    GUIListaPlazas.getInstancia().actualizar(Eventos.LISTA_PLAZAS_OK, plazas);
                    break;
                    
                case Eventos.INICIAR_SALIDA:
                    int num = (Integer) datos;
                    // Verificar ocupada
                    TransferPlaza p = sa.obtenerPlaza(num);
                    if (p.getEstado() != negocio.EstadoPlaza.OCUPADA) {
                        JOptionPane.showMessageDialog(null, "La plaza no está ocupada", "Error", JOptionPane.ERROR_MESSAGE);
                        GUIIngresoSalida.getInstancia().mostrar();
                    } else {
                        double coste = sa.calcularCoste(num);
                        GUIConfirmarSalida.getInstancia().mostrar(p.getNumero(), p.getMatricula(),
                            (long) java.time.Duration.between(p.getLlegada(), java.time.LocalDateTime.now()).toMinutes(), coste);
                    }
                    break;
                    
                case Eventos.CONFIRMAR_SALIDA:
                    int numSalida = (Integer) datos;
                    sa.abandonaVehiculo(numSalida);
                    JOptionPane.showMessageDialog(null, "Salida registrada. Plaza " + numSalida + " disponible.");
                    // Mostrar plazas actualizadas
                    GUIListaPlazas.getInstancia().actualizar(Eventos.LISTA_PLAZAS_OK, sa.listarPlazas());
                    break;
                    
                case Eventos.LLEGA_VEHICULO:
                    TransferPlaza tLlega = (TransferPlaza) datos;
                    sa.llegaVehiculo(tLlega.getNumero(), tLlega.getMatricula());
                    JOptionPane.showMessageDialog(null, "El vehículo ha llegado a la plaza " + tLlega.getNumero());
                    GUIListaPlazas.getInstancia().actualizar(Eventos.LISTA_PLAZAS_OK, sa.listarPlazas());
                    volverMenu();
                    break;
                    
                case Eventos.ABANDONA_VEHICULO:
                    TransferPlaza tAbandona = (TransferPlaza) datos;
                    sa.abandonaVehiculo(tAbandona.getNumero());
                    JOptionPane.showMessageDialog(null, "El vehículo ha abandonado la plaza " + tAbandona.getNumero());
                    GUIListaPlazas.getInstancia().actualizar(Eventos.LISTA_PLAZAS_OK, sa.listarPlazas());
                    volverMenu();

                    break;
                    
                case Eventos.MANTENIMIENTO_PLAZA:
                    TransferPlaza tMant = (TransferPlaza) datos;
                    sa.mantenimientoPlaza(tMant.getNumero());
                    JOptionPane.showMessageDialog(null, "Plaza " + tMant.getNumero() + " en mantenimiento");
                    volverMenu();
                    break;
                    
                case Eventos.INICIAR_MANTENIMIENTO:
                    GUIOpcionesMantenimientoImp.getInstancia().mostrar((Integer)datos);
                    break;

                case Eventos.EMPEZAR_MANTENIMIENTO:
                    sa.iniciarMantenimiento((Integer)datos);
                    JOptionPane.showMessageDialog(null, "Mantenimiento iniciado en plaza " + datos);
                    GUIListaPlazas.getInstancia().actualizar(Eventos.LISTA_PLAZAS_OK, sa.listarPlazas());
                    break;

                case Eventos.FINALIZAR_MANTENIMIENTO:
                    sa.finalizarMantenimiento((Integer)datos);
                    JOptionPane.showMessageDialog(null, "Mantenimiento finalizado. Plaza " + datos + " disponible.");
                    GUIListaPlazas.getInstancia().actualizar(Eventos.LISTA_PLAZAS_OK, sa.listarPlazas());
                    break;
                    
                case Eventos.VOLVER_MENU:
                    GUIMenuCUs.getInstancia().mostrar();
                    break;
                    
                case Eventos.CREAR_PLAZA:
                    GUICrearPlaza.getInstancia().mostrar(); 
                    break;
                    
                case Eventos.CONFIRMAR_CREAR_PLAZA:
                    Object[] d=(Object[])datos;
                    sa.crearPlaza((Integer)d[0],(EstadoPlaza)d[1]);
                    JOptionPane.showMessageDialog(null,"Plaza creada: " + d[0]);
                    GUIListaPlazas.getInstancia().actualizar(Eventos.LISTA_PLAZAS_OK, sa.listarPlazas());
                    break;
                    
                case Eventos.ELIMINAR_PLAZA:
                    GUIEliminarPlaza.getInstancia().mostrar();
                    break;
                    
                case Eventos.CONFIRMAR_ELIMINAR_PLAZA:
                    int num1 = (Integer) datos;
                    sa.eliminarPlaza(num1);
                    JOptionPane.showMessageDialog(null, "Plaza " + num1 + " eliminada.");
                    GUIListaPlazas.getInstancia().actualizar(Eventos.LISTA_PLAZAS_OK, sa.listarPlazas());
                    break;
                
                case Eventos.MODIFICAR_PLAZA:
                    GUIModificarPlaza.getInstancia().mostrar();
                    break;
                    
                case Eventos.CONFIRMAR_MODIFICAR_PLAZA:
                    Object[] dm = (Object[]) datos;
                    int num11 = (Integer) dm[0];
                    EstadoPlaza est = (EstadoPlaza) dm[1];
                    if (est == EstadoPlaza.OCUPADA) {
                        GUIInputMatriculaPlaza.getInstancia().mostrar(num11);
                    } else {
                        sa.modificarPlaza(num11, est);
                        JOptionPane.showMessageDialog(null,
                            "Plaza " + num11 + " modificada a " + est);
                        GUIListaPlazas.getInstancia().actualizar(Eventos.LISTA_PLAZAS_OK, sa.listarPlazas());
                    }
                    break;
                    
                case Eventos.CONFIRMAR_INPUT_MATRICULA:
                    Object[] mi = (Object[]) datos;
                    int numMat = (Integer) mi[0];
                    String mat = (String) mi[1];
                    sa.llegaVehiculo(numMat, mat);
                    JOptionPane.showMessageDialog(null,
                        "Plaza " + numMat + " ahora ocupada con matrícula " + mat);
                    GUIListaPlazas.getInstancia().actualizar(Eventos.LISTA_PLAZAS_OK, sa.listarPlazas());
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            volverMenu();
        }
    }

    private void volverMenu() {
        GUIMenuCUs.getInstancia().mostrar();
    }
}
