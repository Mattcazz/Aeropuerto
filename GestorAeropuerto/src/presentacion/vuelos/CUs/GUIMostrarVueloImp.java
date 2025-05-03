package presentacion.vuelos.CUs;

import negocio.vuelos.TransferVuelo;
import presentacion.vuelos.ControladorImp;
import presentacion.vuelos.Eventos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;


public class GUIMostrarVueloImp extends GUIMostrarVuelo {
    private JFrame frame;
    private JTable table;
    private JButton volverButton;
    private List<TransferVuelo> vuelos;

    public GUIMostrarVueloImp() { }

    public void init(List<TransferVuelo> vuelos) {
        frame = new JFrame("Vuelos");
        
        this.vuelos = new ArrayList<>(vuelos);

        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initComponents() {
        String[] columnNames = {
            "Vuelo ID", "Avion ID", "Origen", "Destino",
            "Salida", "Aterrizaje", "Tipo", "VIP"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (TransferVuelo vuelo : vuelos) {
            model.addRow(new Object[]{
                vuelo.getVueloId(),
                vuelo.getAvionId(),
                vuelo.getOrigen(),
                vuelo.getDestino(),
                vuelo.getTiempoSalida().toLocalTime().toString(),
                vuelo.getTiempoAterrizaje().toLocalTime().toString(),
                vuelo.getTipoVuelo(),
                vuelo.isVip() ? "SI" : "NO"
            });
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Table inside titled panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Vuelos"));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        volverButton = new JButton("Volver");

        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorImp.getInstancia().accion(Eventos.VOLVER_MENU, GUIMostrarVueloImp.this);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(volverButton);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 2 / 3.0);
        int height = (int) (screenSize.height * 3 / 4.0);
        frame.setSize(width, height);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(tablePanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
	@Override
	public void actualizar(Eventos evento, Object datos) {

	}
	
	public JFrame getFrame() {
		return (this.frame);
	}
}
