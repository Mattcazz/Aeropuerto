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


public class GUIActualizarVueloImp extends GUIActualizarVuelo {
    private JFrame frame;
    private JTable table;
    private JButton volverButton;
    private JButton actualizarButton;
    private List<TransferVuelo> vuelos;

    public GUIActualizarVueloImp() {
        frame = new JFrame("Actualizar Vuelo");
    }

    public void init(List<TransferVuelo> vuelos) {
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
        actualizarButton = new JButton("Actualizar");
        actualizarButton.setEnabled(false);

        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ControladorImp.getInstancia().accion(Eventos.VOLVER_MENU, GUIActualizarVueloImp.this);
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ControladorImp.getInstancia().accion(Eventos.ABRIR_SUBMENU_ACTUALIZAR_VUELO, GUIActualizarVueloImp.this);
            }
        });

        // Enable actualizar button on row selection
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    actualizarButton.setEnabled(true);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(volverButton);
        buttonPanel.add(actualizarButton);

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
    
    private void updateRow(TransferVuelo vuelo) {
        int row = -1;
        for (TransferVuelo transferVuelo : vuelos) {
        	if (transferVuelo.getVueloId() == vuelo.getVueloId()) {
        		row = vuelos.indexOf(transferVuelo);
        		break;
        	}
        }
        if (row == -1) {
        	return;
        }

        vuelos.set(row, vuelo);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setValueAt(vuelo.getVueloId(), row, 0);
        model.setValueAt(vuelo.getAvionId(), row, 1);
        model.setValueAt(vuelo.getOrigen(), row, 2);
        model.setValueAt(vuelo.getDestino(), row, 3);
        model.setValueAt(vuelo.getTiempoSalida().toLocalTime().toString(), row, 4);
        model.setValueAt(vuelo.getTiempoAterrizaje().toLocalTime().toString(), row, 5);
        model.setValueAt(vuelo.getTipoVuelo(), row, 6);
        model.setValueAt(vuelo.isVip() ? "SI" : "NO", row, 7);
    }
    
	@Override
	public void actualizar(Eventos evento, Object datos) {
		switch (evento) {
			case ACTUALIZAR_VUELO: {
				TransferVuelo transferVuelo = (TransferVuelo) datos;
				
				this.updateRow(transferVuelo);
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
