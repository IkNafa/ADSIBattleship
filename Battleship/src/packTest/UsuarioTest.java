package packTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import packModelo.Battleship;
import packModelo.packBarcos.Barco;
import packModelo.packBarcos.Destructor;
import packModelo.packBarcos.Fragata;
import packModelo.packBarcos.Portaaviones;
import packModelo.packBarcos.Submarino;
import packModelo.packCoordenada.Coordenada;
import packModelo.packJugador.Ordenador;
import packModelo.packJugador.Usuario;

public class UsuarioTest {

	@Before
	public void setUp() throws Exception {
		Battleship.getBattleship().inicializar();
		// Para probar correctamente los misiles hay que hacer que el ordenador
		// no coloque ningun barco primero
	}

	@Test
	public void testPuedePonerUs() {
		Usuario u = new Usuario();
		Barco unBarco;

		// Puede poner un barco de longitud 4 sin tener m�s barcos de 4
		unBarco = new Portaaviones(new Coordenada(0, 0), true);
		assertTrue(u.puedePonerUs(unBarco));
		u.colocarBarco(unBarco);
		// No puede poner otro barco de longitud 4
		unBarco = new Portaaviones(new Coordenada(2, 0), false);
		assertFalse(u.puedePonerUs(unBarco));

		u = new Usuario();

		// Puede poner dos barcos de longitud 3
		unBarco = new Submarino(new Coordenada(4, 2), true);
		assertTrue(u.puedePonerUs(unBarco));
		u.colocarBarco(unBarco);
		unBarco = new Submarino(new Coordenada(6, 2), true);
		assertTrue(u.puedePonerUs(unBarco));
		u.colocarBarco(unBarco);
		// No puede poner otro de tama�o 3
		unBarco = new Submarino(new Coordenada(0, 0), false);
		assertFalse(u.puedePonerUs(unBarco));

		u = new Usuario();

		// Puede poner tres barcos de longitud 2
		unBarco = new Destructor(new Coordenada(0, 0), true);
		assertTrue(u.puedePonerUs(unBarco));
		u.colocarBarco(unBarco);
		unBarco = new Destructor(new Coordenada(2, 0), true);
		assertTrue(u.puedePonerUs(unBarco));
		u.colocarBarco(unBarco);
		unBarco = new Destructor(new Coordenada(4, 0), true);
		assertTrue(u.puedePonerUs(unBarco));
		u.colocarBarco(unBarco);
		// No puede poner otro de tama�o 2
		unBarco = new Destructor(new Coordenada(6, 0), true);
		assertFalse(u.puedePonerUs(unBarco));

		u = new Usuario();

		// Puede poner cuatro barcos de longitud 1
		unBarco = new Fragata(new Coordenada(0, 0));
		assertTrue(u.puedePonerUs(unBarco));
		u.colocarBarco(unBarco);
		unBarco = new Fragata(new Coordenada(2, 0));
		assertTrue(u.puedePonerUs(unBarco));
		u.colocarBarco(unBarco);
		unBarco = new Fragata(new Coordenada(4, 0));
		assertTrue(u.puedePonerUs(unBarco));
		u.colocarBarco(unBarco);
		unBarco = new Fragata(new Coordenada(6, 0));
		assertTrue(u.puedePonerUs(unBarco));
		u.colocarBarco(unBarco);
		// No puede poner otro de tama�o 1
		unBarco = new Fragata(new Coordenada(8, 0));
		assertFalse(u.puedePonerUs(unBarco));
	}

	@Test
	public void testusarMisilNS() {
		Usuario u = Battleship.getBattleship().getUsuario();
		Ordenador o = Battleship.getBattleship().getOrdenador();
		Coordenada c = new Coordenada(3, 8);
		Barco b1 = new Portaaviones(new Coordenada(3, 3), true);
		Barco b2 = new Destructor(c, false);
		Barco b3 = new Fragata(new Coordenada(3, 0));

		o.anadirBarcoProp(b1);
		o.anadirBarcoProp(b2);
		o.anadirBarcoProp(b3);

		u.usarMisilNS(new Coordenada(3, 2));

		assertTrue(b1.estaDestruido());
		assertTrue(b3.estaDestruido());
		assertEquals(b2.getTocadas().getCoordenadas().get(0), c);
	}

	@Test
	public void testusarMisilEO() {
		Usuario u = Battleship.getBattleship().getUsuario();
		Ordenador o = Battleship.getBattleship().getOrdenador();
		Coordenada c = new Coordenada(8, 3);
		Barco b1 = new Portaaviones(new Coordenada(3, 3), false);
		Barco b2 = new Destructor(c, false);
		Barco b3 = new Fragata(new Coordenada(0, 3));

		o.anadirBarcoProp(b1);
		o.anadirBarcoProp(b2);
		o.anadirBarcoProp(b3);

		u.usarMisilEO(new Coordenada(2, 3));

		assertTrue(b1.estaDestruido());
		assertTrue(b3.estaDestruido());
		assertEquals(b2.getTocadas().getCoordenadas().get(0), c);
	}

	@Test
	public void testusarMisilBOOM() {
		Usuario u = Battleship.getBattleship().getUsuario();
		Ordenador o = Battleship.getBattleship().getOrdenador();
		Coordenada c = new Coordenada(3, 8);

		Barco b1 = new Portaaviones(new Coordenada(3, 3), true);
		Barco b2 = new Destructor(c, false);
		Barco b3 = new Fragata(new Coordenada(3, 0));
		Barco b4 = new Destructor(new Coordenada(5, 2), false);

		o.anadirBarcoProp(b1);
		o.anadirBarcoProp(b2);
		o.anadirBarcoProp(b3);
		o.anadirBarcoProp(b4);

		u.usarMisilNS(new Coordenada(3, 2));

		assertTrue(b1.estaDestruido());
		assertTrue(b3.estaDestruido());
		assertTrue(b4.estaDestruido());
		assertEquals(b2.getTocadas().getCoordenadas().get(0), c);
	}
}
