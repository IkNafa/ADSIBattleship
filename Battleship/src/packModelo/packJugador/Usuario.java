package packModelo.packJugador;

import java.util.ArrayList;

import packModelo.Battleship;
import packModelo.DatosJuego;
import packModelo.packBarcos.Barco;
import packModelo.packBarcos.BarcoNoEncException;
import packModelo.packCoordenada.Coordenada;

public class Usuario extends Jugador {

	public Usuario() {
		super();
	}

	public void colocarBarco(Barco pBarco) {
		getListaBarcos().addBarco(pBarco);
		Battleship.getBattleship().imprimirTableroUsuario();
	}

	public boolean puedePonerUs(Barco pBarco) {
		boolean puede = false;
		if (getListaBarcos().numBarcos() < DatosJuego.NUM_BARCOS) {
			puede = pBarco.puedePonerse(getListaBarcos());
		}
		return puede;
	}

	public void imprimirTablero() {
		System.out.println("\nBarcos del Jugador:\n");
		this.getListaBarcos().imprimirTablero();
	}

	public ArrayList<Coordenada>[] recibirEscaRadar() {
		ArrayList<Coordenada> escaneadas = super.getRadar().escanear();
		@SuppressWarnings("unchecked")
		ArrayList<Coordenada> listas[] = new ArrayList[2];
		ArrayList<Coordenada> objetivos = new ArrayList<Coordenada>();
		ArrayList<Coordenada> noDisparables = new ArrayList<Coordenada>();
		for (Coordenada co : escaneadas) {
			if (hayBarco(co)) {
				objetivos.add(co);
			} else {
				noDisparables.add(co);
			}
		}
		listas[0] = objetivos;
		listas[1] = noDisparables;
		return listas;
	}

	public int tocarBarco(Coordenada pCoordenada) {
		int resultado = 0;
		try {
			Barco barco = super.getListaBarcos().buscarBarco(pCoordenada);
			resultado = barco.tocar(pCoordenada);
			if (resultado == 1) {
				String cambios = "tocada;" + pCoordenada.getX() + "," + pCoordenada.getY();
				setChanged();
				notifyObservers(cambios);
			} else if (resultado == 3) {
				String cambios = "destruido";
				for (Coordenada co : barco.getPosicion().getCoordenadas()) {
					cambios += ";" + co.getX() + "," + co.getY();
				}
				setChanged();
				notifyObservers(cambios);
			} else if (resultado == 2) {
				String cambios = "escudo";
				for (Coordenada co : barco.getPosicion().getCoordenadas()) {
					cambios += ";" + co.getX() + "," + co.getY();
				}
				setChanged();
				notifyObservers(cambios);
			}
		} catch (BarcoNoEncException e) {
			String cambios = "agua;" + pCoordenada.getX() + "," + pCoordenada.getY();
			setChanged();
			notifyObservers(cambios);
		}
		return resultado;
	}

	public int destruirBarco(Coordenada pCoordenada) {
		int destruido;
		try {
			Barco barco = super.getListaBarcos().buscarBarco(pCoordenada);
			if (barco.destruir()) {
				destruido = 1;// Destruido
				String cambios = "destruido";
				for (Coordenada co : barco.getPosicion().getCoordenadas()) {
					cambios = cambios + ";" + co.getX() + "," + co.getY();
				}
				setChanged();
				notifyObservers(cambios);
			} else {
				destruido = 2;// Tenia escudo
				String cambios = "escudo";
				for (Coordenada co : barco.getPosicion().getCoordenadas()) {
					cambios += ";" + co.getX() + "," + co.getY();
				}
				setChanged();
				notifyObservers(cambios);
			}
		} catch (BarcoNoEncException e) {
			String cambios = "agua;" + pCoordenada.getX() + "," + pCoordenada.getY();
			setChanged();
			notifyObservers(cambios);
			destruido = 0;
		} // No hay barco
		return destruido;
	}

	public void usarBomba(Coordenada pCoordenada) {
		int res = Battleship.getBattleship().getOrdenador().tocarBarco(pCoordenada);
		if (res == 1) {
			getListaTocadasEnem().addCoordenada(pCoordenada);
		} else if (res == 2) {
			try {
				getBarcosEneDest().addBarco(Battleship.getBattleship().getOrdenador().getListaBarcos().buscarBarco(pCoordenada));
			} catch (BarcoNoEncException e) {}
		}
	}

	public void usarMisil(Coordenada pCoordenada) {
		if (Battleship.getBattleship().getOrdenador().destruirBarco(pCoordenada)) {
			try {
				getBarcosEneDest().addBarco(Battleship.getBattleship().getOrdenador().getListaBarcos().buscarBarco(pCoordenada));
			} catch (BarcoNoEncException e) {}
		}
		super.getArmamento().rmvMisil();
	}
}