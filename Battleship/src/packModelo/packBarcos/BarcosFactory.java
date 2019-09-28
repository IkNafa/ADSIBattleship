package packModelo.packBarcos;

import packModelo.packCoordenada.Coordenada;

public class BarcosFactory {
	private static BarcosFactory miBarcoFactory;

	private BarcosFactory() {}

	public static BarcosFactory getBarcoFactory() {
		if (miBarcoFactory == null) {
			miBarcoFactory = new BarcosFactory();
		}
		return miBarcoFactory;
	}

	public Barco crearBarco(String pTipo, Coordenada pC, boolean pV) {
		Barco unBarco = null;
		if (pTipo == "Fragata") {
			unBarco = new Fragata(pC);
		} else if (pTipo == "Submarino") {
			unBarco = new Submarino(pC, pV);
		} else if (pTipo == "Destructor") {
			unBarco = new Destructor(pC, pV);
		} else if (pTipo == "Portaaviones") {
			unBarco = new Portaaviones(pC, pV);
		}
		return unBarco;
	}
}