package packModelo.packBarcos;

import java.util.ArrayList;

import packModelo.DatosJuego;
import packModelo.packCoordenada.Coordenada;
import packModelo.packCoordenada.ListaCoordenadas;

public abstract class Barco {
	private ListaCoordenadas posicion;
	private ListaCoordenadas tocadas;
	private String tipo;
	private int escudo = 0;

	public Barco() {
		posicion = new ListaCoordenadas();
		tocadas = new ListaCoordenadas();
	}

	public int tocar(Coordenada pCoordenada) {
		// 0 = No tocada
		// 1 = Tocada
		// 2 = EscudoQuitado
		// 3 = destruido
		// 4 = aun con escudo
		int tocada = 0;
		if (!tocadas.estaEnLista(pCoordenada)) {
			if (escudo >= 1) {
				escudo--;
				if (escudo == 0) {
					tocada = 2;
				} else {
					tocada = 4;
				}
			} else {
				tocadas.addCoordenada(pCoordenada);
				if (estaDestruido()) {
					tocada = 3;
				} else {
					tocada = 1;
				}
			}
		}
		return tocada;
	}

	public boolean estaDestruido() {
		return posicion.numCoordenadas() == tocadas.numCoordenadas();
	}

	public boolean ponerEscudo() {
		boolean puesto = false;
		if (escudo == 0) {
			escudo = DatosJuego.VALOR_ESCUDO;
			puesto = true;
		}
		return puesto;
	}

	public boolean destruir() {
		boolean destruido = false;
		if (escudo != 0) {
			escudo = 0;
		} else {
			tocadas = posicion;
			destruido = true;
		}
		return destruido;
	}

	public ArrayList<Coordenada> repararBarco() {
		ArrayList<Coordenada> tocadasAntes = tocadas.getCoordenadas();
		if (tocadas.numCoordenadas() >= 1 && !estaDestruido()) {
			tocadas = new ListaCoordenadas();
			return tocadasAntes;
		}
		return null;
	}

	public boolean estaEnPos(Coordenada pCoordenada) {
		return this.posicion.estaEnLista(pCoordenada);
	}

	public boolean fueraDeLimites() {
		return this.posicion.fueraDeLimites();
	}

	public ArrayList<Coordenada> calcularAdyacentes() {
		return this.posicion.calcularAdyacentes();
	}

	public ListaCoordenadas getPosicion() {
		return this.posicion;
	}

	public ListaCoordenadas getTocadas() {
		return this.tocadas;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String pTipo) {
		this.tipo = pTipo;
	}

	public abstract boolean puedePonerse(ListaBarcos listaBarcos);
}