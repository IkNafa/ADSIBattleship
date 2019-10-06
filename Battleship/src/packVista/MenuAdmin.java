package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuAdmin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdmin window = new MenuAdmin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnEditar = new JButton("Editar");
		panel.add(btnEditar);
		
		JButton btnLogros = new JButton("Logros");
		panel.add(btnLogros);
		
		JButton btnRnking = new JButton("Ránking");
		panel.add(btnRnking);
		
		JButton btnTienda = new JButton("Tienda");
		panel.add(btnTienda);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Fácil", "Medio", "Difícil"}));
		comboBox.setSelectedIndex(1);
		panel_1.add(comboBox);
		
		JButton btnJugar = new JButton("Jugar");
		panel_1.add(btnJugar);
	}
}
