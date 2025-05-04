package presentacion.vuelos.CUs;

import negocio.vuelos.TransferAvion;
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


public class GUIModificarAvionImp extends GUIModificarAvion {
    private JFrame frame;
    private JTable table;
    private JButton volverButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private List<TransferAvion> aviones;

    public GUIModificarAvionImp() {
        frame = new JFrame("Modificar Vuelo");
    }

    public void init(List<TransferAvion> aviones) {
        this.aviones = new ArrayList<>(aviones);

        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initComponents() {
        String[] columnNames = {
            "Avion ID", "Altura", "Anchura", "Longitud",
            "Peso", "Max Pasajeros", "Aerolinea"
        };

		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (TransferAvion avion : aviones) {
            model.addRow(new Object[]{
                avion.getAvionId(),
                avion.getAltura(),
                avion.getAnchura(),
                avion.getLongitud(),
                avion.getPeso(),
                avion.getMaxPasajeros(),
                avion.getAerolinea()
            });
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Table inside titled panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Aviones"));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        volverButton = new JButton("Volver");
        actualizarButton = new JButton("Actualizar");
        actualizarButton.setEnabled(false);
        eliminarButton = new JButton("Eliminar");
        eliminarButton.setEnabled(false);

        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ControladorImp.getInstancia().accion(Eventos.VOLVER_MENU, GUIModificarAvionImp.this);
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ControladorImp.getInstancia().accion(Eventos.ABRIR_SUBMENU_ACTUALIZAR_AVION, GUIModificarAvionImp.this);
            }
        });
        
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ControladorImp.getInstancia().accion(Eventos.ELIMINAR_AVION, GUIModificarAvionImp.this);
            }
        });

        // Enable actualizar button on row selection
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                	actualizarButton.setEnabled(true);
                	eliminarButton.setEnabled(true);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(volverButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 2 / 3.0);
        int height = (int) (screenSize.height * 3 / 4.0);
        frame.setSize(width, height);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(tablePanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public TransferAvion getSelectedAvion() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
        	return null;
        }
        
        return (aviones.get(selectedRow));
    }
    
    private void updateRow(TransferAvion avion) {
        int row = -1;
        for (TransferAvion transferAvion : aviones) {
        	if (transferAvion.getAvionId() == avion.getAvionId()) {
        		row = aviones.indexOf(transferAvion);
        		break;
        	}
        }
        if (row == -1) {
        	return;
        }

        aviones.set(row, avion);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setValueAt(avion.getAvionId(), row, 0);
        model.setValueAt(avion.getAltura(), row, 1);
        model.setValueAt(avion.getAnchura(), row, 2);
        model.setValueAt(avion.getLongitud(), row, 3);
        model.setValueAt(avion.getPeso(), row, 4);
        model.setValueAt(avion.getMaxPasajeros(), row, 5);
        model.setValueAt(avion.getAerolinea(), row, 6);
    }
    
    private void eliminarAvion(String avionId) {
        int row = -1;
        for (TransferAvion transferAvion : aviones) {
        	if (transferAvion.getAvionId() == avionId) {
        		row = aviones.indexOf(transferAvion);
        		break;
        	}
        }
        if (row == -1) {
        	return;
        }

        aviones.remove(row);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(row);
    }
    
	@Override
	public void actualizar(Eventos evento, Object datos) {
		switch (evento) {
			case ACTUALIZAR_AVION: {
				TransferAvion transferAvion = (TransferAvion) datos;
				
				this.updateRow(transferAvion);
				break;
			}
			case ELIMINAR_AVION: {
				String avionId = (String) datos;
				
				this.eliminarAvion(avionId);
				this.eliminarButton.setEnabled(false);
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
