package packTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packModelo.Almacen;

public class AlmacenTest {

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testPuedeVender() {
		Almacen al = Almacen.getAlmacen();
		// Escudos (5)
		assertTrue(al.puedeVender(0));
		al.venderEscudo();
		assertTrue(al.puedeVender(0));
		al.venderEscudo();
		assertTrue(al.puedeVender(0));
		al.venderEscudo();
		assertTrue(al.puedeVender(0));
		al.venderEscudo();
		assertTrue(al.puedeVender(0));
		al.venderEscudo();
		assertFalse(al.puedeVender(0));

		// misiles (10)
		assertTrue(al.puedeVender(1));
		al.venderMisil();
		al.venderMisil();
		al.venderMisil();
		al.venderMisil();
		al.venderMisil();
		al.venderMisil();
		al.venderMisil();
		al.venderMisil();
		al.venderMisil();
		assertTrue(al.puedeVender(1));
		al.venderMisil();
		assertFalse(al.puedeVender(1));

		// misilNS
		assertTrue(al.puedeVender(2));
		al.venderMisilNS();
		assertFalse(al.puedeVender(2));

		// MisilEO
		assertTrue(al.puedeVender(3));
		al.venderMisilEO();
		assertFalse(al.puedeVender(3));

		// MisilBOOM
		assertTrue(al.puedeVender(4));
		al.venderMisilBOOM();
		assertFalse(al.puedeVender(4));
	}
}
