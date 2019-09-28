package packModelo.packBarcos;

import packModelo.DatosJuego;
import packModelo.packCoordenada.Coordenada;

public class Submarino extends Barco {

	public Submarino(Coordenada pC, boolean pVertical) {
		super();
		setTipo("Submarino");
		getPosicion().addCoordenada(pC);
		int i = 0;
		if (pVertical) {
			i = pC.getY() + 1;
			while (getPosicion().numCoordenadas() < DatosJuego.LONG_SUBMARINO) {
				getPosicion().addCoordenada(new Coordenada(pC.getX(), i++));
			}
		} else {
			i = pC.getX() + 1;
			while (getPosicion().numCoordenadas() < DatosJuego.LONG_SUBMARINO) {
				getPosicion().addCoordenada(new Coordenada(i++, pC.getY()));
			}
		}
	}

	public boolean puedePonerse(ListaBarcos listaBarcos) {
		return listaBarcos.getNumBarcosRestantes(this.getTipo()) < DatosJuego.NUM_SUBMARINO;
	}
}