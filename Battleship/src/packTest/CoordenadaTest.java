package packTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packModelo.packCoordenada.Coordenada;

public class CoordenadaTest {

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testGetAdyacentes() {
		Coordenada c = new Coordenada(3, 3);
		ArrayList<Coordenada> adyacentes = c.getAdyacentes();
		ArrayList<Coordenada> adyacentes2 = new ArrayList<Coordenada>();
		adyacentes2.add(new Coordenada(3, 3));
		adyacentes2.add(new Coordenada(2, 3));
		adyacentes2.add(new Coordenada(4, 3));
		adyacentes2.add(new Coordenada(2, 2));
		adyacentes2.add(new Coordenada(3, 2));
		adyacentes2.add(new Coordenada(4, 2));
		adyacentes2.add(new Coordenada(2, 4));
		adyacentes2.add(new Coordenada(3, 4));
		adyacentes2.add(new Coordenada(4, 4));

		assertEquals(adyacentes.get(0).getX(), adyacentes2.get(0).getX());
		assertEquals(adyacentes.get(0).getY(), adyacentes2.get(0).getY());
		assertEquals(adyacentes.get(1).getX(), adyacentes2.get(1).getX());
		assertEquals(adyacentes.get(1).getY(), adyacentes2.get(1).getY());
		assertEquals(adyacentes.get(2).getX(), adyacentes2.get(2).getX());
		assertEquals(adyacentes.get(2).getY(), adyacentes2.get(2).getY());
		assertEquals(adyacentes.get(3).getX(), adyacentes2.get(3).getX());
		assertEquals(adyacentes.get(3).getY(), adyacentes2.get(3).getY());
		assertEquals(adyacentes.get(4).getX(), adyacentes2.get(4).getX());
		assertEquals(adyacentes.get(4).getY(), adyacentes2.get(4).getY());
		assertEquals(adyacentes.get(5).getX(), adyacentes2.get(5).getX());
		assertEquals(adyacentes.get(5).getY(), adyacentes2.get(5).getY());
		assertEquals(adyacentes.get(6).getX(), adyacentes2.get(6).getX());
		assertEquals(adyacentes.get(6).getY(), adyacentes2.get(6).getY());
		assertEquals(adyacentes.get(7).getX(), adyacentes2.get(7).getX());
		assertEquals(adyacentes.get(7).getY(), adyacentes2.get(7).getY());
		assertEquals(adyacentes.get(8).getX(), adyacentes2.get(8).getX());
		assertEquals(adyacentes.get(8).getY(), adyacentes2.get(8).getY());
	}
}
