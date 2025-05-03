package presentacion.vuelos.CUs;

import negocio.vuelos.TransferVuelo;
import presentacion.vuelos.ControladorImp;
import presentacion.vuelos.Eventos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;


public class GUIEliminarVueloImp extends GUIEliminarVuelo {
    private JFrame frame;
    private JTable table;
    private JButton volverButton;
    private JButton eliminarButton;
    private List<TransferVuelo> vuelos;

    public GUIEliminarVueloImp() { }

    public void init(List<TransferVuelo> vuelos) {
        frame = new JFrame("Eliminar Vuelo");
        
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
        eliminarButton = new JButton("Eliminar");
        eliminarButton.setEnabled(false);

        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorImp.getInstancia().accion(Eventos.VOLVER_MENU, GUIEliminarVueloImp.this);
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ControladorImp.getInstancia().accion(Eventos.ELIMINAR_VUELO, GUIEliminarVueloImp.this);
            }
        });

        // Enable eliminar button on row selection
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    eliminarButton.setEnabled(true);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(volverButton);
        buttonPanel.add(eliminarButton);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 2 / 3.0);
        int height = (int) (screenSize.height * 3 / 4.0);
        frame.setSize(width, height);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(tablePanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public TransferVuelo getSelectedVuelo() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
        	return null;
        }
        
        return (vuelos.get(selectedRow));
    }
    
    private void eliminarVuelo(String vueloId) {
        int row = -1;
        for (TransferVuelo transferVuelo : vuelos) {
        	if (transferVuelo.getVueloId() == vueloId) {
        		row = vuelos.indexOf(transferVuelo);
        		break;
        	}
        }
        if (row == -1) {
        	return;
        }

        vuelos.remove(row);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(row);
    }
    
	@Override
	public void actualizar(Eventos evento, Object datos) {
		switch (evento) {
			case ELIMINAR_VUELO: {
				String vueloId = (String) datos;
				
				this.eliminarVuelo(vueloId);
				break;
			}
			default:
				break;
		}
		
	}
	
	public JFrame getFrame() {
		return (this.frame);
	}
}
