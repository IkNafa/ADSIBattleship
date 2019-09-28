package packModelo.packBarcos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import packModelo.DatosJuego;
import packModelo.packCoordenada.Coordenada;

public class ListaBarcos {
	private List<Barco> listaBarcos;

	public ListaBarcos() {
		listaBarcos = new ArrayList<Barco>();
	}

	public void addBarco(Barco pBarco) {
		if (!listaBarcos.contains(pBarco)) {
			listaBarcos.add(pBarco);
		}
	}

	public void delBarco(Barco pBarco) {
		if (listaBarcos.contains(pBarco)) {
			listaBarcos.remove(pBarco);
		}
	}

	public boolean contains(Barco pBarco) {
		return listaBarcos.contains(pBarco);
	}

	public int numBarcos() {
		return listaBarcos.size();
	}

	public int getNumBarcosRestantes(String pTipo) {
		int cuantos = 0;
		for (Barco b : listaBarcos) {
			if (b.getTipo() == pTipo) {
				cuantos++;
			}
		}
		return cuantos;
	}

	public Barco buscarBarco(Coordenada pCoordenada) throws BarcoNoEncException {
		Iterator<Barco> itr = getIterator();
		boolean encontrado = false;
		Barco barco = null;
		Barco b;
		while (itr.hasNext() && !encontrado) {
			b = itr.next();
			if (b.estaEnPos(pCoordenada)) {
				barco = b;
				encontrado = true;
			}
		}
		if (!encontrado) {
			throw new BarcoNoEncException();
		}
		return barco;
	}

	private Iterator<Barco> getIterator() {
		return listaBarcos.iterator();
	}

	public void imprimirTablero() {
		System.out.println("");
		for (int i = 0; i < DatosJuego.FILAS_TABLERO; i++) {
			for (int j = 0; j < DatosJuego.COLUMNAS_TABLERO; j++) {
				try {
					buscarBarco(new Coordenada(j, i));
					System.out.print("X ");
				} catch (BarcoNoEncException e) {
					System.out.print("O ");
				}
			}
			System.out.println("");
		}
		System.out.println("\n");
	}

	public boolean completa() {
		return numBarcos() > DatosJuego.NUM_BARCOS - 2;
	}
}