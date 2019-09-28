package packTest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import packModelo.Battleship;
import packModelo.packBarcos.Barco;
import packModelo.packBarcos.Fragata;
import packModelo.packBarcos.Portaaviones;
import packModelo.packBarcos.Submarino;
import packModelo.packCoordenada.Coordenada;
import packModelo.packJugador.Usuario;

public class JugadorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Battleship.getBattleship().inicializar();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Test
	public void testcomprarArma() {
		Usuario u = new Usuario();
		assertEquals(u.getDinero(), 150);
		u.comprarArma(0);
		assertEquals(u.getDinero(), 125);
		u.comprarArma(1);
		assertEquals(u.getDinero(), 115);
		u.comprarArma(2);
		assertEquals(u.getDinero(), 70);
		u.comprarArma(3);
		assertEquals(u.getDinero(), 25);
	}

	@Test
	public void testpuedeColocar() {
		Usuario u = new Usuario();
		Barco b = new Portaaviones(new Coordenada(0, 0), false);
		if (u.puedeColocar(b)) {
			u.anadirAdyacentesBarco(b);
			u.colocarBarco(b);
		}

		Barco b2 = new Submarino(new Coordenada(0, 0), true);
		assertFalse(u.puedeColocar(b2));
		b2 = new Submarino(new Coordenada(1, 1), true);
		assertFalse(u.puedeColocar(b2));
		b2 = new Fragata(new Coordenada(9, 9));
		assertTrue(u.puedeColocar(b2));
		b2 = new Submarino(new Coordenada(8, 8), true);
		assertFalse(u.puedeColocar(b2));

		Barco b4 = new Submarino(new Coordenada(5, 5), true);
		assertTrue(u.puedeColocar(b4));
		if (u.puedeColocar(b4)) {
			u.anadirAdyacentesBarco(b4);
			u.colocarBarco(b4);
		}

		Barco b3 = new Submarino(new Coordenada(3, 6), false);
		assertFalse(u.puedeColocar(b3));
	}
}
