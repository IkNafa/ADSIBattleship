package packTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packModelo.packCoordenada.Coordenada;
import packModelo.packCoordenada.ListaCoordenadas;

public class ListaCoordenadasTest {

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testEstaEnLista() {
		Coordenada c = new Coordenada(3, 3);
		ListaCoordenadas l = new ListaCoordenadas();
		assertFalse(l.getCoordenadas().contains(c));
		l.addCoordenada(c);
		assertTrue(l.getCoordenadas().contains(c));
	}

	@Test
	public void testCalcularAdyacentes() {
		ListaCoordenadas l = new ListaCoordenadas();
		ArrayList<Coordenada> adyacentes = new ArrayList<Coordenada>();
		ArrayList<Coordenada> misAdyacentes = new ArrayList<Coordenada>();
		adyacentes = l.calcularAdyacentes();
		assertEquals(adyacentes.size(), 0);

		Coordenada c = new Coordenada(5, 5);
		l.addCoordenada(c);
		adyacentes = l.calcularAdyacentes();

		misAdyacentes.add(c);
		misAdyacentes.add(new Coordenada(5, 4));
		misAdyacentes.add(new Coordenada(5, 6));
		misAdyacentes.add(new Coordenada(4, 5));
		misAdyacentes.add(new Coordenada(6, 5));
		misAdyacentes.add(new Coordenada(4, 4));
		misAdyacentes.add(new Coordenada(4, 6));
		misAdyacentes.add(new Coordenada(6, 4));
		misAdyacentes.add(new Coordenada(6, 6));

		int cont = 0;
		// Miramos que ambos ArrayList contienen las mismas coordenadas
		for (int i = 0; i < adyacentes.size(); i++) {
			for (int j = 0; j < adyacentes.size(); j++) {
				if ((adyacentes.get(i).getX() == misAdyacentes.get(j).getX())
						&& adyacentes.get(i).getY() == misAdyacentes.get(j).getY()) {
					cont++;
				}
			}
		}
		assertEquals(cont, adyacentes.size());

		Coordenada c2 = new Coordenada(5, 7);
		l.addCoordenada(c2);
		adyacentes = l.calcularAdyacentes();
		misAdyacentes.add(c2);
		misAdyacentes.add(new Coordenada(5, 7));
		misAdyacentes.add(new Coordenada(4, 7));
		misAdyacentes.add(new Coordenada(6, 7));
		misAdyacentes.add(new Coordenada(4, 8));
		misAdyacentes.add(new Coordenada(5, 8));
		misAdyacentes.add(new Coordenada(6, 8));

		cont = 0;
		// Miramos que ambos ArrayList contienen las mismas coordenadas
		for (int i = 0; i < adyacentes.size(); i++) {
			for (int j = 0; j < adyacentes.size(); j++) {
				if ((adyacentes.get(i).getX() == misAdyacentes.get(j).getX())
						&& adyacentes.get(i).getY() == misAdyacentes.get(j).getY()) {
					cont++;
				}
			}
		}
		assertEquals(cont, adyacentes.size());

		Coordenada c3 = new Coordenada(5, 6);
		l.addCoordenada(c3);
		adyacentes = l.calcularAdyacentes();

		cont = 0;
		// Miramos que ambos ArrayList contienen las mismas coordenadas
		for (int i = 0; i < adyacentes.size(); i++) {
			for (int j = 0; j < adyacentes.size(); j++) {
				if ((adyacentes.get(i).getX() == misAdyacentes.get(j).getX())
						&& adyacentes.get(i).getY() == misAdyacentes.get(j).getY()) {
					cont++;
				}
			}
		}
		assertEquals(cont, adyacentes.size());
	}

	@Test
	public void testFueraDeLimites() {
		ListaCoordenadas l = new ListaCoordenadas();
		assertFalse(l.fueraDeLimites());
		Coordenada c = new Coordenada(3, 3);
		l.addCoordenada(c);
		assertFalse(l.fueraDeLimites());
		Coordenada c2 = new Coordenada(9, 0);
		l.addCoordenada(c2);
		assertFalse(l.fueraDeLimites());
		Coordenada c3 = new Coordenada(32, -3);
		l.addCoordenada(c3);
		assertTrue(l.fueraDeLimites());
		Coordenada c4 = new Coordenada(-1, -13);
		l.addCoordenada(c4);
		assertTrue(l.fueraDeLimites());
	}

	@Test
	public void testComprobarListas() {
		ListaCoordenadas l = new ListaCoordenadas();
		ListaCoordenadas l2 = new ListaCoordenadas();
		Coordenada c = new Coordenada(3, 3);
		Coordenada c2 = new Coordenada(9, 0);
		Coordenada c3 = new Coordenada(4, 5);
		Coordenada c4 = new Coordenada(2, 7);
		assertFalse(l.comprobarListas(l2));
		l.addCoordenada(c);
		assertFalse(l.comprobarListas(l2));
		l.addCoordenada(c2);
		l2.addCoordenada(c3);
		l2.addCoordenada(c4);
		assertFalse(l.comprobarListas(l2));
		l.addCoordenada(c3);
		assertTrue(l.comprobarListas(l2));
		assertTrue(l2.comprobarListas(l));
	}
}
