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

public class CBtnsUsuario implements MouseListener {
	@Override
	public void mousePressed(MouseEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.isEnabled()) {
			if (Battleship.getBattleship().getTurno()) {
				String coor[] = btn.getName().split(",");
				Coordenada c = new Coordenada(Integer.parseInt(coor[0]), Integer.parseInt(coor[1]));
				if (TableroJuego.getTableroJuego().getArma() == DatosJuego.NUM_ESCUDO) {
					if (!Battleship.getBattleship().usarEscudo(c)) {
						JOptionPane.showMessageDialog(null, "¡No se puede poner Escudo!", "Alerta",
								JOptionPane.WARNING_MESSAGE);
					} else {
						try {
							Battleship.getBattleship().setTurno(false);
						} catch (BarcoNoEncException e1) {}
						TableroJuego.getTableroJuego().actualizarCantidades();
					}
				} else if (TableroJuego.getTableroJuego().getArma() == DatosJuego.NUM_REPARAR) {
					if (!Battleship.getBattleship().repararBarco(c)) {
						JOptionPane.showMessageDialog(null, "¡No se puede reparar!", "Alerta",
								JOptionPane.WARNING_MESSAGE);
					} else {
						try {
							Battleship.getBattleship().setTurno(false);
						} catch (BarcoNoEncException e1) {}
						TableroJuego.getTableroJuego().actualizarCantidades();
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
