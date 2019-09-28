package packModelo;

import java.util.ArrayList;

import packModelo.packCoordenada.Coordenada;

public class Radar {
	private Coordenada posicion;
	private int usos;

	public Radar() {
		this.posicion = new Coordenada(0, 0);
		usos = DatosJuego.INI_USOS_RADAR;
	}

	public int getUsos() {
		return usos;
	}

	public void mover(Coordenada pC) {
		this.posicion = pC;
	}

	public ArrayList<Coordenada> escanear() {
		return posicion.getAdyacentesValidas();
	}

	public Coordenada getPosicion() {
		return posicion;
	}

	public boolean puedeUsarRadar() {
		boolean puede = false;
		if (usos >= 1 && posicion != null) {
			puede = true;
		}
		return puede;
	}

	public void restarUsoRadar() {
		usos--;
	}
}
