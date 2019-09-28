package packControlador.ContInicio;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import packModelo.Battleship;
import packModelo.packCoordenada.Coordenada;
import packVista.Inicio;
import packVista.TableroJuego;

public class CBtnsColocar implements MouseListener {

	@Override
	public void mousePressed(MouseEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.isEnabled()) {
			if (Inicio.getInicio().getTipo() != null) {
				String coor[] = btn.getName().split(",");
				Coordenada c = new Coordenada(Integer.parseInt(coor[0]), Integer.parseInt(coor[1]));
				if (!Battleship.getBattleship().puedeColocar(Inicio.getInicio().getTipo(), c,
						Inicio.getInicio().isVertical())) {
					JOptionPane.showMessageDialog(null, "¡Seleccione una posición correcta, por favor!", "Alerta",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Battleship.getBattleship().colocarBarcoUs(Inicio.getInicio().getTipo(), c,
							Inicio.getInicio().isVertical());
					Inicio.getInicio().deshabilitarBotones(btn);
					Inicio.getInicio().pintarBarcoPuesto(btn);
					Inicio.getInicio().decrementarCont();
					Inicio.getInicio().getBtnColocarBarcos().setEnabled(false);
					if (Battleship.getBattleship().todosBarcosUsPuestos()) {
						Inicio.getInicio().dispose();
						TableroJuego frame = TableroJuego.getTableroJuego();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Seleccione algún barco para comenzar la colocación.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (Inicio.getInicio().getTipo() != null) {
			JButton btn = (JButton) e.getSource();
			Inicio.getInicio().pintarBarcoMoviendo(btn);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (Inicio.getInicio().getTipo() != null) {
			JButton btn = (JButton) e.getSource();
			Inicio.getInicio().despintarBarco(btn);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
