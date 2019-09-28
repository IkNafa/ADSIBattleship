package packModelo.packCoordenada;

import java.util.ArrayList;

import packModelo.DatosJuego;

public class Coordenada {
	private int x;
	private int y;

	public Coordenada(int pX, int pY) {
		this.x = pX;
		this.y = pY;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public ArrayList<Coordenada> getAdyacentes() {
		ArrayList<Coordenada> adyacentes = new ArrayList<Coordenada>();
		// La propia coordenada
		adyacentes.add(new Coordenada(x, y));
		// A los lados de la coordenada
		adyacentes.add(new Coordenada(x - 1, y));
		adyacentes.add(new Coordenada(x + 1, y));
		// Encima de la coordenada (de izq a der)
		adyacentes.add(new Coordenada(x - 1, y - 1));
		adyacentes.add(new Coordenada(x, y - 1));
		adyacentes.add(new Coordenada(x + 1, y - 1));
		// Debajo de la coordenada (de izq a der)
		adyacentes.add(new Coordenada(x - 1, y + 1));
		adyacentes.add(new Coordenada(x, y + 1));
		adyacentes.add(new Coordenada(x + 1, y + 1));
		return adyacentes;
	}

	public ArrayList<Coordenada> getAdyacentesValidas() {
		ArrayList<Coordenada> adyacentes = new ArrayList<Coordenada>();
		// La propia coordenada
		adyacentes.add(new Coordenada(x, y));
		// A los lados de la coordenada
		adyacentes.add(new Coordenada(x - 1, y));
		adyacentes.add(new Coordenada(x + 1, y));
		// Encima de la coordenada (de izq a der)
		adyacentes.add(new Coordenada(x - 1, y - 1));
		adyacentes.add(new Coordenada(x, y - 1));
		adyacentes.add(new Coordenada(x + 1, y - 1));
		// Debajo de la coordenada (de izq a der)
		adyacentes.add(new Coordenada(x - 1, y + 1));
		adyacentes.add(new Coordenada(x, y + 1));
		adyacentes.add(new Coordenada(x + 1, y + 1));

		ArrayList<Coordenada> adyacentesValidas = new ArrayList<Coordenada>();
		for (Coordenada c : adyacentes) {
			if (!c.fueraDeLimites()) {
				adyacentesValidas.add(c);
			}
		}
		return adyacentesValidas;
	}

	public boolean fueraDeLimites() {
		boolean fueraDeLimites = false;
		if (x < 0 || x > DatosJuego.COLUMNAS_TABLERO - 1 || y < 0 || y > DatosJuego.FILAS_TABLERO - 1) {
			fueraDeLimites = true;
		}
		return fueraDeLimites;
	}

	public boolean isEqual(Coordenada pC) {
		return (x == pC.getX() && y == pC.getY());
	}
}