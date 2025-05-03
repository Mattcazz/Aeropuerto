package presentacion.incidencias.CUs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import negocio.incidencias.EstadoIncidencia;
import negocio.incidencias.IncidenciaFactory;
import negocio.incidencias.TipoIncidencia;
import negocio.incidencias.TransferIncidencia;
import presentacion.incidencias.Controlador;
import presentacion.incidencias.Eventos;
import presentacion.incidencias.FechaParser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICrearIncidenciaVueloImp extends GUICrearIncidenciaVuelo {

	private String funcButtonName = "Continuar";
	private String[] columnas = { "Vuelo", "Fecha", "Información", "Estado", "Solución" };
	private String tituloTabla = "CREAR INCIDENCIA DE VUELO";
	private String tituloPanel = "Añadir Incidencia";
	private String descFunc = "Se registrará la incidencia del vuelo.";

	
	private JTextField infoVuelo;
	private JTextField infoFecha;
	private JTextField infoIncidencia;
	private JTextField infoEstado;
	private JTextField infoSolucion;
	
	JFrame marco;

	public GUICrearIncidenciaVueloImp() {
		marco = new JFrame();
		marco.setSize(500, 400);
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
		JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Campos de información
		
		// campo "vuelo"
		panel.add(new JLabel("Vuelo:"));
		infoVuelo = new JTextField();
		infoVuelo.setEditable(true);
		panel.add(infoVuelo);

		// campo "fecha"
		panel.add(new JLabel("Fecha(YY/MM/DD):"));
		infoFecha = new JTextField();
		infoFecha.setEditable(true);
		panel.add(infoFecha);

		// campo "informacion"
		panel.add(new JLabel("Información:"));
		infoIncidencia = new JTextField();
		infoIncidencia.setEditable(true);
		panel.add(infoIncidencia);

		// campo "estado"
		panel.add(new JLabel("Estado:"));
		infoEstado = new JTextField("Sin solucionar");
		infoEstado.setEditable(false);
		panel.add(infoEstado);

		// campo "solucion"
		panel.add(new JLabel("Solución:"));
		infoSolucion = new JTextField();
		infoSolucion.setEditable(false);
		panel.add(infoSolucion);
		
		marco.add(panel, BorderLayout.CENTER);

		// Panel de botones
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		JButton backButton = new JButton("Volver");
		JButton continueButton = new JButton("Continuar");

		buttonPanel.add(backButton);
		buttonPanel.add(continueButton);
		marco.add(buttonPanel, BorderLayout.SOUTH);

		// Acción del botón Continuar
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vuelo = infoVuelo.getText().trim();
		        java.sql.Date fechaSQL = new java.sql.Date(FechaParser.convertirAFecha(infoFecha.getText()).getTime());
		        String informacion = infoIncidencia.getText().trim();
		        EstadoIncidencia estado = EstadoIncidencia.NO_RESUELTA;
		        String solucion = null;

		        if (vuelo.isBlank()) {
		            JOptionPane.showMessageDialog(marco, "El número de vuelo es obligatorio");
		            return;
		        }

		        try {
		            TransferIncidencia incidencia = IncidenciaFactory.
		                    crearIncidencia(TipoIncidencia.VUELO, fechaSQL, informacion, estado, solucion, 0, vuelo);
		            Controlador.getInstancia().accion(Eventos.CREAR_INCIDENCIA_VUELO, incidencia);
		            JOptionPane.showMessageDialog(marco, "Incidencia de vuelo creada correctamente");
		            marco.setVisible(false);
		            Controlador.getInstancia().accion(Eventos.VOLVER_MENU, null); //////////
		        } catch (Exception excep) {
		            JOptionPane.showMessageDialog(marco, "Error: " + excep.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
