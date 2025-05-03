package presentacion.incidencias.CUs;

import javax.swing.*;

import negocio.incidencias.EstadoIncidencia;
import negocio.incidencias.IncidenciaFactory;
import negocio.incidencias.TipoIncidencia;
import negocio.incidencias.TransferIncidencia;
import negocio.incidencias.TransferIncidenciaEquipaje;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;
import presentacion.incidencias.FechaParser;

public class GUICrearIncidenciaEquipajeImp extends GUICrearIncidenciaEquipaje {

	private String funcButtonName = "Continuar";
	private String tituloTabla = "CREAR INCIDENCIA DE EQUIPAJE";
	private String descFunc = "Se registrará la incidencia de equipaje.";

	private JTextField infoNombre;
	private JTextField infoApellido;
	private JTextField infoFecha;
	private JTextField infoIncidencia;
	private JTextField infoEstado;
	private JTextField infoSolucion;

	JFrame marco;

	public GUICrearIncidenciaEquipajeImp() {
        marco = new JFrame();
        marco.setSize(500, 450);
        marco.setLayout(new BorderLayout());

        // Panel del título
        JLabel titleLabel = new JLabel(tituloTabla, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(80, 90, 110));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setPreferredSize(new Dimension(500, 50));
        marco.add(titleLabel, BorderLayout.NORTH);

        // Panel de contenido
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Campos de información
        panel.add(new JLabel("Nombre:"));
        infoNombre = new JTextField();
        panel.add(infoNombre);

        panel.add(new JLabel("Apellido:"));
        infoApellido = new JTextField();
        panel.add(infoApellido);

        panel.add(new JLabel("Fecha (YY/MM/DD):"));
        infoFecha = new JTextField();
        panel.add(infoFecha);

        panel.add(new JLabel("Información:"));
        infoIncidencia = new JTextField();
        panel.add(infoIncidencia);

        panel.add(new JLabel("Estado:"));
        infoEstado = new JTextField("Sin solucionar");
		infoEstado.setEditable(false);
        panel.add(infoEstado);

        panel.add(new JLabel("Solución:"));
        infoSolucion = new JTextField();
        panel.add(infoSolucion);
        infoSolucion.setEditable(false);
        marco.add(panel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Volver");
        JButton continueButton = new JButton(funcButtonName);

        buttonPanel.add(backButton);
        buttonPanel.add(continueButton);
        marco.add(buttonPanel, BorderLayout.SOUTH);

        // Acción del botón Continuar
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String apellido = infoApellido.getText().trim();
            	String nombre = infoNombre.getText().trim();
            	java.sql.Date fechaSQL = new java.sql.Date(FechaParser.convertirAFecha(infoFecha.getText()).getTime());
            	String informacion = infoIncidencia.getText().trim();
            	EstadoIncidencia estado = EstadoIncidencia.NO_RESUELTA;
            	String solucion = null;
            	
            	if(apellido.isBlank() || nombre.isBlank()) {
            		JOptionPane.showMessageDialog(marco, "Nombre y apellido son obligatorios");
            	}
            	
            	try {
            		TransferIncidencia incidencia = IncidenciaFactory.
            				crearIncidencia(TipoIncidencia.EQUIPAJE,fechaSQL, informacion, estado, solucion, 0, nombre + " "+ apellido);
            	Controlador.getInstancia().accion(Eventos.CREAR_INCIDENCIA_EQUIPAJE,incidencia);
            	JOptionPane.showMessageDialog(marco, "Incidencia creada correctamente");
            	marco.setVisible(false);
            	Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null); ////////////
            	} catch(Exception excep) {
            		JOptionPane.showMessageDialog(marco, "Error: " + excep.getMessage(),"Error: ",JOptionPane.ERROR_MESSAGE);
            	}
            }
        });

        // Acción del botón Volver
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marco.setVisible(false);
                Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null);
            }
        });

        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }

	public void actualizar(int evento, Object datos) {
	}

	public JFrame getFrame() {
		return marco;
	}
}
