package mapa;

import java.util.Random;

public class mapaGenerado extends mapa {

	private static final Random aleatorio = new Random();

	public mapaGenerado(int ancho, int alto) {
		super(ancho, alto);

	}

	protected void generarMapa() {

		for (int y = 0; y < alto; y++) {

			for (int x = 0; x < ancho; x++) {
				cuadros[x + y * ancho] = aleatorio.nextInt(7);
			}

		}
	}

}
