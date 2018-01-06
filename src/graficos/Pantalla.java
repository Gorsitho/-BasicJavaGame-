package graficos;

import Ente.criaturas.Jugador;
import mapa.cuadros.tiles;

public final class Pantalla {

	private final int ancho;
	private final int alto;

	public final int[] pixeles;

	private int diferenciaX;
	private int diferenciaY;

	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];

	}

	public void limpiar() {

		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0;
		}

	}

	public void mostrarCuadro(int compensacionX, int compensacionY, tiles cuadro) {

		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;
		for (int y = 0; y < cuadro.sprite.LadoGet(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < cuadro.sprite.LadoGet(); x++) {
				int posicionX = x + compensacionX;

				if (posicionX < -cuadro.sprite.LadoGet() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
					break;
				}
				if (posicionX < 0)
					posicionX = 0;

				pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.LadoGet()];
			}
		}

	}

	public void mostrarJugador(int compensacionX, int compensacionY, Jugador jugador) {
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;
		for (int y = 0; y < jugador.obtenSprite().LadoGet(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < jugador.obtenSprite().LadoGet(); x++) {
				int posicionX = x + compensacionX;

				if (posicionX < -jugador.obtenSprite().LadoGet() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;
				}
				if (posicionX < 0)
					posicionX = 0;

				// pixeles[posicionX + posicionY * ancho] =
				// jugador.obtenSprite().pixeles[x
				// + y * jugador.obtenSprite().LadoGet()];

				int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().LadoGet()];

				if (colorPixelJugador != 0xffff00ff) {
					pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
				}
			}
		}
	}

	public void establecerDiferencia(final int diferenciaX, final int diferenciaY) {
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;
	}

	public int anchoGet() {
		return ancho;
	}

	public int altoGet() {
		return alto;
	}

}
