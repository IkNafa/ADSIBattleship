package packModelo.packJugador;

import java.util.ArrayList;
import java.util.Random;

import packModelo.Battleship;
import packModelo.DatosJuego;
import packModelo.packBarcos.Barco;
import packModelo.packBarcos.BarcoNoEncException;
import packModelo.packCoordenada.Coordenada;
import packModelo.packCoordenada.ListaCoordenadas;

public class Ordenador extends Jugador {

	private ListaCoordenadas listNoDisparar;
	private ListaCoordenadas listDisparar;

	public Ordenador() {
		super();
		listNoDisparar = new ListaCoordenadas();
		listDisparar = new ListaCoordenadas();
	}

	public void jugar() throws BarcoNoEncException {
		System.out.println("--- Nueva acción ---");
		if (Math.random() <= 0.65) { // 65% probabilidad de usar bomba
			dispararBomba();
			Battleship.getBattleship().setTurno(true);
		} else { // 35% probabilidad de hacer otra cosa
			double mr = Math.random();
			if (mr <= 0.40) { // 40% Misil
				dispararMisil();
				Battleship.getBattleship().setTurno(true);
			} else if (mr > 0.40 && mr <= 0.55) { // 15% MisilNS
				dispararMisilNS();
				Battleship.getBattleship().setTurno(true);
			} else if (mr > 0.55 && mr <= 0.70) { // 15% MisilEO
				dispararMisilEO();
				Battleship.getBattleship().setTurno(true);
			} else if (mr > 0.70 && mr <= 0.80) { // 10% Escudo
				ponerseEscudo();
				Battleship.getBattleship().setTurno(true);
			} else if (mr > 0.80 && mr <= 0.85) { // 5% Mover el radar
				System.out.println("Radar movido\n");
				Random rdn = new Random();
				int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
				int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
				Coordenada co = new Coordenada(x, y);
				Battleship.getBattleship().getUsuario().moverRadar(co);
				jugar();
			} else if (mr > 0.85 && mr <= 0.90) { // 5% Reparar barco
				repararseBarco();
				Battleship.getBattleship().setTurno(true);
			} else if (mr > 0.90 && mr <= 0.95) { // 5% MisilBOOM
				dispararMisilBOOM();
				Battleship.getBattleship().setTurno(true);
			} else if (mr > 0.95 && mr <= 1) { // 5% Radar
				System.out.println("Intenta usar radar");
				if (getRadar().puedeUsarRadar()) {
					usarRadar();
					System.out.println("Radar utilizado\n");
				} else {
					System.out.println("Radar no utilizado\n");
				}
				jugar();
			}
		}
	}

	private void dispararBomba() {
		Random rdn = new Random();
		/*
		 * Si la lista a donde disparar esta vacia dispara aleatoriamente a una
		 * coordenada que no este en la lista donde no disparar
		 */
		if (listDisparar.vacia()) {
			boolean disparado = false;
			while (!disparado) {
				int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO);
				int y = rdn.nextInt(DatosJuego.FILAS_TABLERO);
				Coordenada co = new Coordenada(x, y);
				if (!listNoDisparar.estaEnLista(co)) {
					usarBomba(co);
					disparado = true;
				}
			}
			/*
			 * Si no, dispara a una coordenada aleatoria de la lista a donde
			 * tiene que disparar
			 */
		} else {
			Coordenada co = listDisparar.getRandomCo();
			usarBomba(co);
		}
	}

	private void dispararMisil() {
		System.out.println("Intenta usar Misil\n");
		Random rdn = new Random();
		if (getArmamento().getMisil() >= 1) { // Si tiene misil dispara
			if (listDisparar.vacia()) {
				boolean disparado = false;
				while (!disparado) {
					int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
					int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
					Coordenada co = new Coordenada(x, y);
					if (!listNoDisparar.estaEnLista(co)) {
						usarMisil(co);
						disparado = true;
					}
				}
			} else {
				Coordenada co = listDisparar.getRandomCo();
				usarMisil(co);
			}
		} else { // Si no tiene misil, intenta comprar
			if (comprarArma(DatosJuego.NUM_MISIL)) {
				System.out.println("Misil comprado\n");
				if (listDisparar.vacia()) {
					boolean disparado = false;
					while (!disparado) {
						int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
						int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
						Coordenada co = new Coordenada(x, y);
						if (!listNoDisparar.estaEnLista(co)) {
							usarMisil(co);
							disparado = true;
						}
					}
				} else {
					Coordenada co = listDisparar.getRandomCo();
					usarMisil(co);
				}
			} else { // Si no puede comprar, dispara bomba
				System.out.println("Misil no usado\n");
				dispararBomba();
			}
		}
	}

	private void dispararMisilNS() {
		System.out.println("Intenta usar MisilNS\n");
		Random rdn = new Random();
		if (getArmamento().getMisilNS() >= 1) { // Si tiene misilNS dispara
			if (listDisparar.vacia()) {
				boolean disparado = false;
				while (!disparado) {
					int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
					int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
					Coordenada co = new Coordenada(x, y);
					if (!listNoDisparar.estaEnLista(co)) {
						usarMisilNS(co);
						System.out.println("MisilNS usado\n");
						disparado = true;
					}
				}
			} else {
				Coordenada co = listDisparar.getRandomCo();
				System.out.println("MisilNS usado\n");
				usarMisilNS(co);
			}
		} else { // Si no tiene misilNS, intenta comprar
			if (comprarArma(DatosJuego.NUM_MISIL_NS)) {
				System.out.println("MisilNS comprado\n");
				if (listDisparar.vacia()) {
					boolean disparado = false;
					while (!disparado) {
						int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
						int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
						Coordenada co = new Coordenada(x, y);
						if (!listNoDisparar.estaEnLista(co)) {
							usarMisilNS(co);
							System.out.println("MisilNS usado\n");
							disparado = true;
						}
					}
				} else {
					Coordenada co = listDisparar.getRandomCo();
					System.out.println("MisilNS usado\n");
					usarMisilNS(co);
				}
			} else { // Si no puede comprar, dispara bomba
				System.out.println("MisilNS no usado\n");
				dispararBomba();
			}
		}
	}

	private void dispararMisilEO() {
		System.out.println("Intenta usar MisilEO\n");
		Random rdn = new Random();
		if (getArmamento().getMisilEO() >= 1) { // Si tiene misilEO dispara
			if (listDisparar.vacia()) {
				boolean disparado = false;
				while (!disparado) {
					int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
					int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
					Coordenada co = new Coordenada(x, y);
					if (!listNoDisparar.estaEnLista(co)) {
						usarMisilEO(co);
						System.out.println("MisilEO usado\n");
						disparado = true;
					}
				}
			} else {
				Coordenada co = listDisparar.getRandomCo();
				System.out.println("MisilEO usado\n");
				usarMisilEO(co);
			}
		} else { // Si no tiene misilEO, intenta comprar
			if (comprarArma(DatosJuego.NUM_MISIL_EO)) {
				System.out.println("MisilEO comprado\n");
				if (listDisparar.vacia()) {
					boolean disparado = false;
					while (!disparado) {
						int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
						int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
						Coordenada co = new Coordenada(x, y);
						if (!listNoDisparar.estaEnLista(co)) {
							usarMisilEO(co);
							System.out.println("MisilEO usado\n");
							disparado = true;
						}
					}
				} else {
					Coordenada co = listDisparar.getRandomCo();
					System.out.println("MisilEO usado\n");
					usarMisilEO(co);
				}
			} else { // Si no puede comprar, dispara bomba
				System.out.println("MisilEO no usado\n");
				dispararBomba();
			}
		}
	}

	private void ponerseEscudo() {
		System.out.println("Intenta poner Escudo\n");
		Random rdn = new Random();
		if (getArmamento().getEscudo() >= 1) { // Si tiene escudo se lo pone
			boolean puesto = false;
			int cont = 0;
			while (!puesto && cont < 10) {
				int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
				int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
				Coordenada co = new Coordenada(x, y);
				if (ponerEscudo(co)) {
					System.out.println("Escudo puesto en (" + co.getX() + "," + co.getY() + ")\n");
					puesto = true;
				}
				cont++;
			}
			if (!puesto) {
				System.out.println("Escudo no puesto\n");
				dispararBomba();
			}
		} else if (comprarArma(DatosJuego.NUM_ESCUDO)) {
			System.out.println("Escudo comprado\n");
			boolean puesto = false;
			int cont = 0;
			while (!puesto && cont < DatosJuego.COLUMNAS_TABLERO * DatosJuego.COLUMNAS_TABLERO / 2) {
				int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
				int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
				Coordenada co = new Coordenada(x, y);
				if (ponerEscudo(co)) {
					System.out.println("Escudo puesto en (" + co.getX() + "," + co.getY() + ")\n");
					puesto = true;
				}
			}
			if (puesto = false) {
				dispararBomba();
			}
		} else {
			System.out.println("Escudo no puesto");
			dispararBomba();
		}
	}

	private void dispararMisilBOOM() {
		System.out.println("Intenta usar MisilBOOM\n");
		Random rdn = new Random();
		if (getArmamento().getMisilBOOM() >= 1) { // Si tiene misil dispara
			if (listDisparar.vacia()) {
				boolean disparado = false;
				while (!disparado) {
					int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
					int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
					Coordenada co = new Coordenada(x, y);
					if (!listNoDisparar.estaEnLista(co)) {
						usarMisilBOOM(co);
						System.out.println("MisilBOOM usado\n");
						disparado = true;
					}
				}
			} else {
				Coordenada co = listDisparar.getRandomCo();
				System.out.println("MisilBOOM usado\n");
				usarMisilBOOM(co);
			}
		} else { // Si no tiene misil, intenta comprar
			if (comprarArma(DatosJuego.NUM_MISIL_BOOM)) {
				System.out.println("MisilBOOM comprado\n");
				if (listDisparar.vacia()) {
					boolean disparado = false;
					while (!disparado) {
						int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
						int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
						Coordenada co = new Coordenada(x, y);
						if (!listNoDisparar.estaEnLista(co)) {
							usarMisilBOOM(co);
							System.out.println("MisilBOOM usado\n");
							disparado = true;
						}
					}
				} else {
					Coordenada co = listDisparar.getRandomCo();
					System.out.println("MisilBOOM usado\n");
					usarMisilBOOM(co);
				}
			} else { // Si no puede comprar, dispara bomba
				System.out.println("MisilBOOM no usado\n");
				dispararBomba();
			}
		}
	}
	
	private void repararseBarco() throws BarcoNoEncException {
		System.out.println("Intenta Reparar\n");
		Random rdn = new Random();
		if (getDinero() >= DatosJuego.PRECIO_REPARAR) { // Si le llega repara
			boolean reparado = false;
			int cont = 0;
			while (!reparado && cont < 10) {
				int x = rdn.nextInt(DatosJuego.COLUMNAS_TABLERO - 1);
				int y = rdn.nextInt(DatosJuego.FILAS_TABLERO - 1);
				Coordenada co = new Coordenada(x, y);
				if (repararBarco(co)) {
					System.out.println("Barco reparado en (" + co.getX() + "," + co.getY() + ")\n");
					String cambios = "reparar";
					for (Coordenada c : getListaBarcos().buscarBarco(co).getPosicion().getCoordenadas()) {
						cambios += ";" + c.getX() + "," + c.getY();
					}
					setChanged();
					notifyObservers(cambios);
					reparado = true;
				}
				cont++;
			}
			if (!reparado) {
				System.out.println("Ningún barco reparado\n");
				dispararBomba();
			}
		} else {
			System.out.println("Dinero insuficiente, ningún barco reparado\n");
			dispararBomba();
		}
	}

	public void addCoordenadasDisparar(ArrayList<Coordenada> pLista) {
		listDisparar.addCoordenadas(pLista);
	}
	
	public void addCoordenadaNoDisparar(Coordenada pC) {
		listNoDisparar.addCoordenada(pC);
	}
	
	public void imprimirTablero() {
		System.out.println("\nBarcos del ordenador:\n");
		getListaBarcos().imprimirTablero();
	}

	public void recibirEscaRadar() {
		String infoRadar;
		boolean detectado = false;
		;
		infoRadar = "scan";
		ArrayList<Coordenada> escaneadas = super.getRadar().escanear();
		for (Coordenada co : escaneadas) {
			if (hayBarco(co)) {
				infoRadar = infoRadar + ";" + co.getX() + "," + co.getY(); // Barcos
				detectado = true;
			}
		}
		if (detectado)
			notificarRadar(infoRadar);
	}

	private void usarRadar() {
		ArrayList<Coordenada> listas[] = Battleship.getBattleship().getUsuario().recibirEscaRadar();
		listDisparar.addCoordenadas(listas[0]);
		listNoDisparar.addCoordenadas(listas[1]);
		getRadar().restarUsoRadar();
	}

	public int tocarBarco(Coordenada pCoordenada) {
		int resultado = 0;
		try {
			switch (super.getListaBarcos().buscarBarco(pCoordenada).tocar(pCoordenada)) {
			case 1:
				String cambios = "tocada;" + pCoordenada.getX() + "," + pCoordenada.getY();
				setChanged();
				notifyObservers(cambios);
				resultado = 1;
				break;
			case 2:
				String cambios1 = "escudo;" + pCoordenada.getX() + "," + pCoordenada.getY();
				setChanged();
				notifyObservers(cambios1);
				break;
			case 3:
				Barco barco1 = super.getListaBarcos().buscarBarco(pCoordenada);
				String cambios11 = "destruido";
				for (Coordenada co : barco1.getPosicion().getCoordenadas()) {
					cambios11 = cambios11 + ";" + co.getX() + "," + co.getY();
				}
				setChanged();
				notifyObservers(cambios11);
				resultado = 2;
				break;
			case 4:
				String cambios111 = "detectado;" + pCoordenada.getX() + "," + pCoordenada.getY();
				setChanged();
				notifyObservers(cambios111);
				break;
			}
		} catch (BarcoNoEncException e) {
			String cambios = "agua;" + pCoordenada.getX() + "," + pCoordenada.getY();
			setChanged();
			notifyObservers(cambios);
		}
		return resultado;
	}

	public boolean destruirBarco(Coordenada pCoordenada) {
		boolean dest = false;
		try {
			if (super.getListaBarcos().buscarBarco(pCoordenada).destruir()) {
				Barco barco = super.getListaBarcos().buscarBarco(pCoordenada);
				String cambios = "destruido";
				for (Coordenada co : barco.getPosicion().getCoordenadas()) {
					cambios = cambios + ";" + co.getX() + "," + co.getY();
				}
				setChanged();
				notifyObservers(cambios);
				dest = true;
			} else {
				Barco barco = super.getListaBarcos().buscarBarco(pCoordenada);
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
		return dest;
	}

	private void usarBomba(Coordenada pCoordenada) {
		System.out.println("Bomba usada en (" + pCoordenada.getX() + "," + pCoordenada.getY() + ")\n");
		switch (Battleship.getBattleship().getUsuario().tocarBarco(pCoordenada)) {
		case 0:
			listNoDisparar.addCoordenada(pCoordenada);
			listDisparar.delCoordenada(pCoordenada);
			break;
		case 1:
			listNoDisparar.addCoordenada(pCoordenada);
			listDisparar.delCoordenada(pCoordenada);
			break;
		case 2:
			if (!listDisparar.estaEnLista(pCoordenada)) {
				listDisparar.addCoordenada(pCoordenada);
			}
			if (listNoDisparar.estaEnLista(pCoordenada)) {
				listNoDisparar.delCoordenada(pCoordenada);
			}
			break;
		case 4:
			if (!listDisparar.estaEnLista(pCoordenada)) {
				listDisparar.addCoordenada(pCoordenada);
			}
			if (listNoDisparar.estaEnLista(pCoordenada)) {
				listNoDisparar.delCoordenada(pCoordenada);
			}
			break;
		case 3:
			Barco barco;
			try {
				barco = Battleship.getBattleship().getUsuario().getListaBarcos().buscarBarco(pCoordenada);
				getBarcosEneDest().addBarco(barco);
				listNoDisparar.addCoordenadas(barco.calcularAdyacentes());
				listDisparar.delCoordenadas(barco.calcularAdyacentes());
			} catch (BarcoNoEncException e) {
			}
			break;
		}
	}

	public void usarMisil(Coordenada pCoordenada) {
		System.out.println("Misil usado en (" + pCoordenada.getX() + "," + pCoordenada.getY() + ")\n");
		switch (Battleship.getBattleship().getUsuario().destruirBarco(pCoordenada)) {
		case 0:
			listNoDisparar.addCoordenada(pCoordenada);
			listDisparar.delCoordenada(pCoordenada);
			break;
		case 1:
			Barco barco;
			try {
				barco = Battleship.getBattleship().getUsuario().getListaBarcos().buscarBarco(pCoordenada);
				getBarcosEneDest().addBarco(barco);
				listNoDisparar.addCoordenadas(barco.calcularAdyacentes());
				listDisparar.delCoordenadas(barco.calcularAdyacentes());
			} catch (BarcoNoEncException e) {
			}
			break;
		case 2:
			listDisparar.addCoordenada(pCoordenada);
			break;
		}
		super.getArmamento().rmvMisil();
	}

	public void usuarioReparado(Coordenada pC, Barco pBarco) {
		for (Coordenada c : pBarco.getPosicion().getCoordenadas()) {
			listNoDisparar.delCoordenada(c);
		}
		listDisparar.addCoordenada(pC);
	}
}