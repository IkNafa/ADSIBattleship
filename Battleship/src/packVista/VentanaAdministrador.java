package packVista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JTextField;

public class VentanaAdministrador extends JFrame {
	private JTabbedPane tabbedPane;
	private JPanel panelJugadores;
	private JPanel panelPartida;
	private JList list;
	private JPanel panel;
	private JButton btnBorrar;
	private JButton btnInfo;
	private JComboBox comboBox;
	private JPanel panel_1;
	private JPanel panelEditLEvel;
	private JTextField txtPresupuesto;
	private JTextField txtArmamento;
	private JComboBox comboBox_1;
	private JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdministrador frame = new VentanaAdministrador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAdministrador() {

		initComponents();
	}
	private void initComponents() {
		setBounds(100, 100, 550, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getTabbedPane(), BorderLayout.CENTER);
	}

	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Jugadores", null, getPanelJugadores(), null);
			tabbedPane.addTab("Partida", null, getPanelPartida(), null);
			tabbedPane.addTab("Nivel", null, getPanel_2_1(), null);
		}
		return tabbedPane;
	}
	private JPanel getPanelJugadores() {
		if (panelJugadores == null) {
			panelJugadores = new JPanel();
			GridBagLayout gbl_panelJugadores = new GridBagLayout();
			gbl_panelJugadores.columnWidths = new int[]{529, 0};
			gbl_panelJugadores.rowHeights = new int[] {241, 41, 0, 0};
			gbl_panelJugadores.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panelJugadores.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			panelJugadores.setLayout(gbl_panelJugadores);
			GridBagConstraints gbc_list = new GridBagConstraints();
			gbc_list.anchor = GridBagConstraints.NORTH;
			gbc_list.insets = new Insets(0, 0, 5, 0);
			gbc_list.gridx = 0;
			gbc_list.gridy = 0;
			panelJugadores.add(getList(), gbc_list);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			panelJugadores.add(getPanel_2(), gbc_panel);
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 0;
			gbc_panel_1.gridy = 2;
			panelJugadores.add(getPanel_1_1(), gbc_panel_1);
		}
		return panelJugadores;
	}
	private JPanel getPanelPartida() {
		if (panelPartida == null) {
			panelPartida = new JPanel();
			panelPartida.add(getComboBox());
		}
		return panelPartida;
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			list.setModel(new AbstractListModel() {
				String[] values = new String[] {"Iker Nafarrate", "Gorka Rivero", "Ivan Perez", "Alvaro Luzuriaga", "Alba Arsuaga", "Iker Larrarte", "Aitor Martinez"};
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
		}
		return list;
	}
	private JPanel getPanel_2() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnBorrar());
			panel.add(getBtnInfo());
		}
		return panel;
	}
	private JButton getBtnBorrar() {
		if (btnBorrar == null) {
			btnBorrar = new JButton("Borrar");
			btnBorrar.setEnabled(false);
		}
		return btnBorrar;
	}
	private JButton getBtnInfo() {
		if (btnInfo == null) {
			btnInfo = new JButton("Info");
			btnInfo.setEnabled(false);
		}
		return btnInfo;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Barco 1x1", "Barco 1x2", "Barco 1x3", "Barco 1x4", "Barco 2x5"}));
		}
		return comboBox;
	}
	private JPanel getPanel_1_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
		}
		return panel_1;
	}
	private JPanel getPanel_2_1() {
		if (panelEditLEvel == null) {
			panelEditLEvel = new JPanel();
			panelEditLEvel.add(getComboBox_1());
			panelEditLEvel.add(getTxtPresupuesto());
			panelEditLEvel.add(getTxtArmamento());
			panelEditLEvel.add(getBtnGuardar());
		}
		return panelEditLEvel;
	}
	private JTextField getTxtPresupuesto() {
		if (txtPresupuesto == null) {
			txtPresupuesto = new JTextField();
			txtPresupuesto.setText("Presupuesto ");
			txtPresupuesto.setColumns(10);
		}
		return txtPresupuesto;
	}
	private JTextField getTxtArmamento() {
		if (txtArmamento == null) {
			txtArmamento = new JTextField();
			txtArmamento.setText("Armamento");
			txtArmamento.setColumns(10);
		}
		return txtArmamento;
	}
	private JComboBox getComboBox_1() {
		if (comboBox_1 == null) {
			comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Fácil", "Medio", "Díficil"}));
			comboBox_1.setSelectedIndex(1);
		}
		return comboBox_1;
	}
	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar");
		}
		return btnGuardar;
	}
}
