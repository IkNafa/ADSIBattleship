package packControlador.ContInicio;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import packModelo.Battleship;
import packVista.Inicio;
import packVista.TableroJuego;

public class CBtnBarc implements MouseListener {

	@Override
	public void mousePressed(MouseEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.isEnabled()) {
			Battleship.getBattleship().ponerBarcosUsuario();
			if (Battleship.getBattleship().todosBarcosUsPuestos()) {
				Inicio.getInicio().dispose();
				TableroJuego frame = TableroJuego.getTableroJuego();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
