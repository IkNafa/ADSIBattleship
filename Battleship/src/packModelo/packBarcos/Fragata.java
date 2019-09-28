package packModelo.packBarcos;

import packModelo.DatosJuego;
import packModelo.packCoordenada.Coordenada;

public class Fragata extends Barco {

	public Fragata(Coordenada pC) {
		super();
		setTipo("Fragata");
		getPosicion().addCoordenada(pC);
	}

	public boolean puedePonerse(ListaBarcos listaBarcos) {
		return listaBarcos.getNumBarcosRestantes(this.getTipo()) < DatosJuego.NUM_FRAGATA;
	}
}