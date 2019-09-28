package packControlador.ContTablero;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import packModelo.Battleship;
import packModelo.DatosJuego;
import packVista.TableroJuego;

public class CBtnCompMisilEO implements MouseListener {
	@Override
	public void mousePressed(MouseEvent e) {
		if (!Battleship.getBattleship().comprarArma(DatosJuego.NUM_MISIL_EO)) {
			JOptionPane.showMessageDialog(null, "¡No es posible comprar misiles EO!", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
		TableroJuego.getTableroJuego().actualizarCantidades();
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
