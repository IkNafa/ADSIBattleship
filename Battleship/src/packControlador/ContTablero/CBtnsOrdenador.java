package packControlador.ContTablero;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import packModelo.Battleship;
import packModelo.DatosJuego;
import packModelo.packBarcos.BarcoNoEncException;
import packModelo.packCoordenada.Coordenada;
import packVista.TableroJuego;

public class CBtnsOrdenador implements MouseListener {
	@Override
	public void mousePressed(MouseEvent e) {
		JButton btn = (JButton) e.getSource();
		String coor[] = btn.getName().split(",");
		Coordenada c = new Coordenada(Integer.parseInt(coor[0]), Integer.parseInt(coor[1]));
		if (TableroJuego.getTableroJuego().getArma() == DatosJuego.NUM_MOVER_RADAR) {
			Battleship.getBattleship().moverRadar(c);
		} else if (btn.isEnabled()) {
			if (Battleship.getBattleship().getTurno()) {
				if (TableroJuego.getTableroJuego().getArma() != DatosJuego.NUM_ESCUDO
						&& TableroJuego.getTableroJuego().getArma() != DatosJuego.NUM_REPARAR) {
					if (Battleship.getBattleship().puedeUsar(TableroJuego.getTableroJuego().getArma())) {
						switch (TableroJuego.getTableroJuego().getArma()) {
						case DatosJuego.NUM_BOMBA:
							btn.setEnabled(false);
							Battleship.getBattleship().usarBomba(c);
							break;
						case DatosJuego.NUM_MISIL:
							btn.setEnabled(false);
							Battleship.getBattleship().usarMisil(c);
							break;
						case DatosJuego.NUM_MISIL_NS:
							TableroJuego.getTableroJuego().deshabilitarNS(btn);
							Battleship.getBattleship().usarMisilNS(c);
							break;
						case DatosJuego.NUM_MISIL_EO:
							TableroJuego.getTableroJuego().deshabilitarEO(btn);
							Battleship.getBattleship().usarMisilEO(c);
							break;
						case DatosJuego.NUM_MISIL_BOOM:
							TableroJuego.getTableroJuego().deshabilitarBOOM(btn);
							Battleship.getBattleship().usarMisilBOOM(c);
							break;
						}
						TableroJuego.getTableroJuego().actualizarCantidades();
						if (!Battleship.getBattleship().juegoFinalizado()) {
							try {
								Battleship.getBattleship().setTurno(false);
							} catch (BarcoNoEncException e1) {}
						}
					} else {
						JOptionPane.showMessageDialog(null, "¡No dispone de unidades de armamento suficiente!",
								"Alerta", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "¡Seleccione armamento correcto!", "Alerta",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "¡Espere su turno, por favor!", "Alerta",
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