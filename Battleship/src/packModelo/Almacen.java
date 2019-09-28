package packModelo;

import java.util.Observable;

public class Almacen extends Observable {

	private static Almacen miAlmacen;
	private Cantidades stock = new Cantidades();
	private int[] info; // Para la vista

	private Almacen() {
		stock.iniciarAlmacen();
		info = new int[2];
	}

	public static Almacen getAlmacen() {
		if (miAlmacen == null) {
			miAlmacen = new Almacen();
		}
		return miAlmacen;
	}

	public boolean puedeVender(int pArma) {
		boolean puede = false;
		switch (pArma) {
		case DatosJuego.NUM_ESCUDO:
			if (stock.getEscudo() > 0) {
				puede = true;
			}
			break;
		case DatosJuego.NUM_MISIL:
			if (stock.getMisil() > 0) {
				puede = true;
			}
			break;
		case DatosJuego.NUM_MISIL_NS:
			if (stock.getMisilNS() > 0) {
				puede = true;
			}
			break;
		case DatosJuego.NUM_MISIL_EO:
			if (stock.getMisilEO() > 0) {
				puede = true;
			}
			break;
		case DatosJuego.NUM_MISIL_BOOM:
			if (stock.getMisilBOOM() > 0) {
				puede = true;
			}
			break;
		}
		return puede;
	}

	public void venderEscudo() {
		stock.rmvEscudo();
		info[0] = DatosJuego.NUM_ESCUDO;
		info[1] = stock.getEscudo();
		notificar();
	}

	public void venderMisil() {
		stock.rmvMisil();
		info[0] = DatosJuego.NUM_MISIL;
		info[1] = stock.getMisil();
		notificar();
	}

	public void venderMisilNS() {
		stock.rmvMisilNS();
		;
		info[0] = DatosJuego.NUM_MISIL_NS;
		info[1] = stock.getMisilNS();
		notificar();
	}

	public void venderMisilEO() {
		stock.rmvMisilEO();
		info[0] = DatosJuego.NUM_MISIL_EO;
		info[1] = stock.getMisilEO();
		notificar();
	}

	public void venderMisilBOOM() {
		stock.rmvMisilBOOM();
		info[0] = DatosJuego.NUM_MISIL_BOOM;
		info[1] = stock.getMisilBOOM();
		notificar();
	}

	private void notificar() {
		setChanged();
		notifyObservers(info);
	}
}