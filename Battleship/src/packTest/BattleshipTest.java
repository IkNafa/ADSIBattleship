package packTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packModelo.Battleship;
import packModelo.packCoordenada.Coordenada;

public class BattleshipTest {

	@Before
	public void setUp() throws Exception {
		Battleship.getBattleship().inicializar();
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testJugar() {
		fail("Not yet implemented");
	}

	@Test
	public void testColocarBarcoUs() {
		Battleship.getBattleship().colocarBarcoUs("Portaaviones", new Coordenada(0, 0), true);
		assertTrue(Battleship.getBattleship().hayBarcoUsu(new Coordenada(0, 0)));
		assertTrue(Battleship.getBattleship().hayBarcoUsu(new Coordenada(0, 1)));
		assertTrue(Battleship.getBattleship().hayBarcoUsu(new Coordenada(0, 2)));
		assertTrue(Battleship.getBattleship().hayBarcoUsu(new Coordenada(0, 3)));
		assertFalse(Battleship.getBattleship().hayBarcoUsu(new Coordenada(0, 4)));

		Battleship.getBattleship().colocarBarcoUs("Submarino", new Coordenada(4, 4), false);
		assertTrue(Battleship.getBattleship().hayBarcoUsu(new Coordenada(4, 4)));
		assertTrue(Battleship.getBattleship().hayBarcoUsu(new Coordenada(5, 4)));
		assertTrue(Battleship.getBattleship().hayBarcoUsu(new Coordenada(6, 4)));
		assertFalse(Battleship.getBattleship().hayBarcoUsu(new Coordenada(3, 4)));
		assertFalse(Battleship.getBattleship().hayBarcoUsu(new Coordenada(7, 4)));

		Battleship.getBattleship().colocarBarcoUs("Destructor", new Coordenada(6, 6), false);
		assertTrue(Battleship.getBattleship().hayBarcoUsu(new Coordenada(6, 6)));
		assertTrue(Battleship.getBattleship().hayBarcoUsu(new Coordenada(7, 6)));
		assertFalse(Battleship.getBattleship().hayBarcoUsu(new Coordenada(5, 6)));
		assertFalse(Battleship.getBattleship().hayBarcoUsu(new Coordenada(8, 6)));

		Battleship.getBattleship().colocarBarcoUs("Fragata", new Coordenada(9, 9), true);
		assertTrue(Battleship.getBattleship().hayBarcoUsu(new Coordenada(9, 9)));
	}

	@Test
	public void testPuedeColocar() {
		assertTrue(Battleship.getBattleship().puedeColocar("Portaaviones", new Coordenada(0, 0), true));
		Battleship.getBattleship().colocarBarcoUs("Portaaviones", new Coordenada(0, 0), true);
		assertFalse(Battleship.getBattleship().puedeColocar("Fragata", new Coordenada(0, 2), false));

		assertTrue(Battleship.getBattleship().puedeColocar("Submarino", new Coordenada(4, 4), false));
		Battleship.getBattleship().colocarBarcoUs("Submarino", new Coordenada(4, 4), false);

		assertTrue(Battleship.getBattleship().puedeColocar("Destructor", new Coordenada(6, 6), false));
		Battleship.getBattleship().colocarBarcoUs("Destructor", new Coordenada(6, 6), false);

		assertFalse(Battleship.getBattleship().puedeColocar("Portaaviones", new Coordenada(5, 0), false));

		assertTrue(Battleship.getBattleship().puedeColocar("Fragata", new Coordenada(9, 9), true));
		Battleship.getBattleship().colocarBarcoUs("Fragata", new Coordenada(9, 9), true);
	}

	@Test
	public void testJuegoAcabado() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasGanado() {
		fail("Not yet implemented");
	}

	@Test
	public void testUsarArmamento() {
		fail("Not yet implemented");
	}
}
