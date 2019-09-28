package packModelo;

public class Cantidades {
	private int misil;
	private int misilNS;
	private int misilEO;
	private int misilBOOM;
	private int escudo;

	public Cantidades() {}

	public void iniciarAlmacen() {
		misil = DatosJuego.CANT_MISIL;
		misilNS = DatosJuego.CANT_MISIL_NS;
		misilEO = DatosJuego.CANT_MISIL_EO;
		misilBOOM = DatosJuego.CANT_MISIL_BOOM;
		escudo = DatosJuego.CANT_ESCUDO;
	}

	public void iniciarJugador() {
		misil = DatosJuego.INI_MISIL;
		misilNS = DatosJuego.INI_MISIL_NS;
		misilEO = DatosJuego.INI_MISIL_EO;
		misilBOOM = DatosJuego.INI_MISIL_BOOM;
		escudo = DatosJuego.INI_ESCUDO;
	}

	public int getMisil() {
		return misil;
	}

	public void addMisil() {
		this.misil++;
	}

	public void rmvMisil() {
		this.misil--;
	}

	public int getMisilNS() {
		return misilNS;
	}

	public void addMisilNS() {
		this.misilNS++;
	}

	public void rmvMisilNS() {
		this.misilNS--;
	}

	public int getMisilEO() {
		return misilEO;
	}

	public void addMisilEO() {
		this.misilEO++;
	}

	public void rmvMisilEO() {
		this.misilEO--;
	}

	public int getMisilBOOM() {
		return misilBOOM;
	}

	public void addMisilBOOM() {
		this.misilBOOM++;
	}

	public void rmvMisilBOOM() {
		this.misilBOOM--;
	}

	public int getEscudo() {
		return escudo;
	}

	public void addEscudo() {
		this.escudo++;
	}

	public void rmvEscudo() {
		this.escudo--;
	}
}