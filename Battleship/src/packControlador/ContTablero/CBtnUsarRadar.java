package packControlador.ContTablero;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import packModelo.Battleship;
import packVista.TableroJuego;

public class CBtnUsarRadar implements MouseListener {
	@Override
	public void mousePressed(MouseEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.isEnabled()) {
			if (Battleship.getBattleship().usarRadar()) {
				TableroJuego.getTableroJuego().marcarRadar();
				TableroJuego.getTableroJuego().actualizarCantidades();
			} else {
				JOptionPane.showMessageDialog(null, "¡No dispone de mas usos de radar!", "Alerta",
						JOptionPane.WARNING_MESSAGE);
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
