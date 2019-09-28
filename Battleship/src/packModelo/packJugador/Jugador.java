package packModelo.packJugador;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import packModelo.Almacen;
import packModelo.Battleship;
import packModelo.Cantidades;
import packModelo.DatosJuego;
import packModelo.Radar;
import packModelo.packBarcos.Barco;
import packModelo.packBarcos.BarcoNoEncException;
import packModelo.packBarcos.Destructor;
import packModelo.packBarcos.Fragata;
import packModelo.packBarcos.ListaBarcos;
import packModelo.packBarcos.Portaaviones;
import packModelo.packBarcos.Submarino;
import packModelo.packCoordenada.Coordenada;
import packModelo.packCoordenada.ListaCoordenadas;

public abstract class Jugador extends Observable {

	private Radar radar;
	private ListaBarcos listaBarcos;
	private Cantidades armamento;
	private int dinero;
	private ListaBarcos barcosEneDest;
	private ListaCoordenadas listaTocadasEnem;
	private ListaCoordenadas listaNoPonerB;

	public Jugador() {
		armamento = new Cantidades();
		armamento.iniciarJugador();
		listaBarcos = new ListaBarcos();
		barcosEneDest = new ListaBarcos();
		listaTocadasEnem = new ListaCoordenadas();
		listaNoPonerB = new ListaCoordenadas();
		dinero = DatosJuego.DINERO_INICIAL;
		radar = new Radar();
	}

	public boolean haGanado() {
		return barcosEneDest.completa();
	}

	public int[] getCantidades() {
		int[] cantidades = new int[6];
		cantidades[0] = armamento.getMisil();
		cantidades[1] = armamento.getMisilNS();
		cantidades[2] = armamento.getMisilEO();
		cantidades[3] = armamento.getMisilBOOM();
		cantidades[4] = radar.getUsos();
		cantidades[5] = armamento.getEscudo();
		return cantidades;
	}

	public int getDinero() {
		return dinero;
	}

	protected ListaBarcos getBarcosEneDest() {
		return barcosEneDest;
	}

	protected ListaCoordenadas getListaTocadasEnem() {
		return listaTocadasEnem;
	}

	protected Cantidades getArmamento() {
		return armamento;
	}

	public boolean ponerEscudo(Coordenada pCoordenada) {
		boolean exito = false;
		try {
			Barco unBarco = this.listaBarcos.buscarBarco(pCoordenada);
			if (!unBarco.estaDestruido()) {
				if (armamento.getEscudo() >= 1) {
					if (unBarco.ponerEscudo()) {
						exito = true;
						armamento.rmvEscudo();
					}
				}
			}
		} catch (BarcoNoEncException e) {}
		return exito;
	}

	public boolean comprarArma(int pArma) {
		boolean exito = false;
		if (Almacen.getAlmacen().puedeVender(pArma)) {
			if (meLlega(pArma)) {
				exito = true;
				switch (pArma) {
				case DatosJuego.NUM_ESCUDO:
					Almacen.getAlmacen().venderEscudo();
					dinero = dinero - DatosJuego.PRECIO_ESCUDO;
					armamento.addEscudo();
					break;
				case DatosJuego.NUM_MISIL:
					Almacen.getAlmacen().venderMisil();
					dinero = dinero - DatosJuego.PRECIO_MISIL;
					armamento.addMisil();
					break;
				case DatosJuego.NUM_MISIL_NS:
					Almacen.getAlmacen().venderMisilNS();
					dinero = dinero - DatosJuego.PRECIO_MISIL_NS;
					armamento.addMisilNS();
					break;
				case DatosJuego.NUM_MISIL_EO:
					Almacen.getAlmacen().venderMisilEO();
					dinero = dinero - DatosJuego.PRECIO_MISIL_EO;
					armamento.addMisilEO();
					break;
				case DatosJuego.NUM_MISIL_BOOM:
					Almacen.getAlmacen().venderMisilBOOM();
					dinero = dinero - DatosJuego.PRECIO_MISIL_BOOM;
					armamento.addMisilBOOM();
					break;
				}
			}
		}
		return exito;
	}

	private boolean meLlega(int pArma) {
		boolean suficiente = false;
		switch (pArma) {
		case DatosJuego.NUM_ESCUDO:
			if (dinero >= DatosJuego.PRECIO_ESCUDO) {
				suficiente = true;
			}
			break;
		case DatosJuego.NUM_MISIL:
			if (dinero >= DatosJuego.PRECIO_MISIL) {
				suficiente = true;
			}
			break;
		case DatosJuego.NUM_MISIL_NS:
			if (dinero >= DatosJuego.PRECIO_MISIL_NS) {
				suficiente = true;
			}
			break;
		case DatosJuego.NUM_MISIL_EO:
			if (dinero >= DatosJuego.PRECIO_MISIL_EO) {
				suficiente = true;
			}
			break;
		case DatosJuego.NUM_MISIL_BOOM:
			if (dinero >= DatosJuego.PRECIO_MISIL_BOOM) {
				suficiente = true;
			}
			break;
		}
		return suficiente;
	}

	public void anadirBarcoProp(Barco pBarco) {
		listaBarcos.addBarco(pBarco);
	}

	public ListaBarcos getListaBarcos() {
		return this.listaBarcos;
	}

	public void anadirAdyacentesBarco(Barco pBarco) {
		listaNoPonerB.addCoordenadas(pBarco.calcularAdyacentes());
	}

	public boolean puedeColocar(Barco pBarco) {
		boolean puede = false;
		if (!pBarco.fueraDeLimites()) {
			if (!pBarco.getPosicion().comprobarListas(listaNoPonerB)) {
				puede = true;
			}
		}
		return puede;
	}

	public boolean hayBarco(Coordenada pC) {
		try {
			listaBarcos.buscarBarco(pC);
			return true;
		} catch (BarcoNoEncException e) {
			return false;
		}
	}

	public Radar getRadar() {
		return radar;
	}

	public void moverRadar(Coordenada pCoordenada) {
		radar.mover(pCoordenada);
		if (Battleship.getBattleship().getTurno()) {
			String infoRadar = "move;" + pCoordenada.getX() + "," + pCoordenada.getY();
			setChanged();
			notifyObservers(infoRadar);
		}
	}

	public boolean puedeUsar(int pArma) {
		boolean puede = false;
		switch (pArma) {
		case DatosJuego.NUM_ESCUDO:
			if (armamento.getEscudo() > 0) {
				puede = true;
			}
			break;
		case DatosJuego.NUM_BOMBA:
			puede = true;
			break;
		case DatosJuego.NUM_MISIL:
			if (armamento.getMisil() > 0) {
				puede = true;
			}
			break;
		case DatosJuego.NUM_MISIL_NS:
			if (armamento.getMisilNS() > 0) {
				puede = true;
			}
			break;
		case DatosJuego.NUM_MISIL_EO:
			if (armamento.getMisilEO() > 0) {
				puede = true;
			}
			break;
		case DatosJuego.NUM_MISIL_BOOM:
			if (armamento.getMisilBOOM() > 0) {
				puede = true;
			}
			break;
		}
		return puede;
	}

	public void notificarRadar(String pInfoRadar) {
		setChanged();
		notifyObservers(pInfoRadar);
	}

	public void usarMisilNS(Coordenada pCoordenada) {
		ListaBarcos listaBa = new ListaBarcos();
		Coordenada c;
		for (int y = 0; y < DatosJuego.FILAS_TABLERO; y++) {
			c = new Coordenada(pCoordenada.getX(), y);
			try {
				listaBa.buscarBarco(c);
			} catch (BarcoNoEncException e) {
				if (Battleship.getBattleship().getTurno()) {
					armamento.addMisil();
					Battleship.getBattleship().getUsuario().usarMisil(c);
					try {
						listaBa.addBarco(Battleship.getBattleship().getOrdenador().getListaBarcos().buscarBarco(c));
					} catch (BarcoNoEncException e1) {}
				} else {
					armamento.addMisil();
					Battleship.getBattleship().getOrdenador().usarMisil(c);
					try {
						listaBa.addBarco(Battleship.getBattleship().getUsuario().getListaBarcos().buscarBarco(c));
					} catch (BarcoNoEncException e1) {
						Battleship.getBattleship().getOrdenador().addCoordenadaNoDisparar(c);
					}
				}
			}
		}
		getArmamento().rmvMisilNS();
	}

	public void usarMisilEO(Coordenada pCoordenada) {
		ListaBarcos listaBa = new ListaBarcos();
		Coordenada c;
		for (int x = 0; x < DatosJuego.COLUMNAS_TABLERO; x++) {
			c = new Coordenada(x, pCoordenada.getY());
			try {
				listaBa.buscarBarco(c);
			} catch (BarcoNoEncException e) {
				if (Battleship.getBattleship().getTurno()) {
					armamento.addMisil();
					Battleship.getBattleship().getUsuario().usarMisil(c);
					try {
						listaBa.addBarco(Battleship.getBattleship().getOrdenador().getListaBarcos().buscarBarco(c));
					}catch (BarcoNoEncException e1) {}
				} else {
					armamento.addMisil();
					Battleship.getBattleship().getOrdenador().usarMisil(c);
					try {
						listaBa.addBarco(Battleship.getBattleship().getUsuario().getListaBarcos().buscarBarco(c));
					}catch (BarcoNoEncException e1) {}
				}
			}
		}
		getArmamento().rmvMisilEO();
	}

	public void usarMisilBOOM(Coordenada pCoordenada) {
		getArmamento().addMisilNS();
		usarMisilNS(pCoordenada);
		ListaBarcos listaBa = new ListaBarcos();
		Coordenada c;
		for (int x = 0; x < DatosJuego.COLUMNAS_TABLERO; x++) {
			c = new Coordenada(x, pCoordenada.getY());
			try {
				listaBa.buscarBarco(c);
			} catch (BarcoNoEncException e) {
				if (!pCoordenada.isEqual(c)) {
					if (Battleship.getBattleship().getTurno()) {
						armamento.addMisil();
						Battleship.getBattleship().getUsuario().usarMisil(c);
						try {
							listaBa.addBarco(Battleship.getBattleship().getOrdenador().getListaBarcos().buscarBarco(c));
						}catch (BarcoNoEncException e1) {}
					} else {
						armamento.addMisil();
						Battleship.getBattleship().getOrdenador().usarMisil(c);
						try {
							listaBa.addBarco(Battleship.getBattleship().getUsuario().getListaBarcos().buscarBarco(c));
						}catch (BarcoNoEncException e1) {}
					}
				}
			}
		}
		getArmamento().rmvMisilBOOM();
	}

	public boolean repararBarco(Coordenada pCoordenada) {
		boolean exito = false;
		try {
			if (dinero >= DatosJuego.PRECIO_REPARAR) {
				Barco unBarco = this.listaBarcos.buscarBarco(pCoordenada);
				ArrayList<Coordenada> resultado = unBarco.repararBarco();
				if (resultado != null) {
					exito = true;
					dinero = dinero - DatosJuego.PRECIO_REPARAR;
					if (Battleship.getBattleship().getTurno()) {
						Battleship.getBattleship().getOrdenador().addCoordenadasDisparar(resultado);
					}
				}
			}
		} catch (BarcoNoEncException e) {}
		return exito;
	}

	public void colocarBarcosAleatorios() {
		Random rdn = new Random();
		int barcosPuestos;
		Barco unBarco;

		// Portaaviones (1)
		boolean puesto = false;
		while (!puesto) {
			unBarco = new Portaaviones(new Coordenada(rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1),
					rdn.nextInt(DatosJuego.FILAS_TABLERO - 1)), rdn.nextBoolean());
			if (puedeColocar(unBarco)) {
				anadirAdyacentesBarco(unBarco);
				anadirBarcoProp(unBarco);
				puesto = true;
			}
		}

		// Submarinos (2)
		barcosPuestos = 0;
		while (barcosPuestos < DatosJuego.NUM_SUBMARINO) {
			unBarco = new Submarino(new Coordenada(rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1),
					rdn.nextInt(DatosJuego.FILAS_TABLERO - 1)), rdn.nextBoolean());
			if (puedeColocar(unBarco)) {
				anadirAdyacentesBarco(unBarco);
				anadirBarcoProp(unBarco);
				barcosPuestos++;
			}
		}

		// Destructores (3)
		barcosPuestos = 0;
		while (barcosPuestos < DatosJuego.NUM_DESTRUCTOR) {
			unBarco = new Destructor(new Coordenada(rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1),
					rdn.nextInt(DatosJuego.FILAS_TABLERO - 1)), rdn.nextBoolean());
			if (puedeColocar(unBarco)) {
				anadirAdyacentesBarco(unBarco);
				anadirBarcoProp(unBarco);
				barcosPuestos++;
			}
		}

		// Fragata (4)
		barcosPuestos = 0;
		while (barcosPuestos < DatosJuego.NUM_FRAGATA) {
			unBarco = new Fragata(new Coordenada(rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1),
					rdn.nextInt(DatosJuego.FILAS_TABLERO - 1)));
			if (puedeColocar(unBarco)) {
				anadirAdyacentesBarco(unBarco);
				anadirBarcoProp(unBarco);
				barcosPuestos++;
			}
		}
	}
}