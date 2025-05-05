package presentacion.CUs;

import javax.swing.*;
import java.awt.*;
import presentacion.Controlador;
import presentacion.Eventos;
import negocio.EstadoPlaza;

public class GUICrearPlazaImp extends GUICrearPlaza {
	
    private static GUICrearPlazaImp inst=new GUICrearPlazaImp(); public static GUICrearPlazaImp getInstancia(){return inst;}
    private JFrame frame; private JTextField txtNum; private JComboBox<EstadoPlaza> cbEstado;
   
    public GUICrearPlazaImp(){
        frame=new JFrame("Crear Plaza"); frame.setLayout(new GridLayout(3,2,10,10));
        frame.add(new JLabel("Número plaza:")); txtNum=new JTextField(); frame.add(txtNum);
        frame.add(new JLabel("Estado:")); cbEstado=new JComboBox<>(EstadoPlaza.values()); frame.add(cbEstado);
        JButton ok=new JButton("Aceptar"), cancel=new JButton("Cancelar");
        
        ok.addActionListener(e->{
            try{
                int n=Integer.parseInt(txtNum.getText());
                EstadoPlaza est=(EstadoPlaza)cbEstado.getSelectedItem();
                frame.setVisible(false);
                Controlador.getInstancia().accion(Eventos.CONFIRMAR_CREAR_PLAZA,new Object[]{n,est});
            }catch(NumberFormatException ex){JOptionPane.showMessageDialog(frame,"Número inválido","Error",JOptionPane.ERROR_MESSAGE);}        
        });
        
        cancel.addActionListener(e->{frame.setVisible(false);Controlador.getInstancia().accion(Eventos.VOLVER_MENU,null);});
        frame.add(ok); frame.add(cancel); frame.pack(); frame.setLocationRelativeTo(null);
    }
    
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }
    
    @Override
	public void actualizar(int evento, Object datos) {		
	}
}
