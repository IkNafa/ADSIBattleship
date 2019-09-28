package packControlador.ContTablero;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import packModelo.DatosJuego;
import packVista.TableroJuego;

public class CBtnUsarMisilBoom implements MouseListener {
	@Override
	public void mousePressed(MouseEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.isEnabled()) {
			TableroJuego.getTableroJuego().setArma(DatosJuego.NUM_MISIL_BOOM);
			TableroJuego.getTableroJuego().getLblArmamento().setText("Armamento seleccionado: MisilBOOM");
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
