package mapa;

import graficos.Pantalla;
import mapa.cuadros.tiles;

public abstract class mapa {
	protected int ancho;
	protected int alto;

	protected int[] cuadros;
	protected tiles[] cuadrosCatalogo;

	public mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;

		cuadros = new int[ancho * alto];
		generarMapa();
	}

	public mapa(String ruta) {
		cargarMapa(ruta);
		generarMapa();
	}

	protected void generarMapa() {

	}

	protected void cargarMapa(String ruta) {

	}

	public void actualizar() {

	}

	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {

		pantalla.establecerDiferencia(compensacionX, compensacionY);
		int oeste = compensacionX >> 5;
		int este = (compensacionX + pantalla.anchoGet() + tiles.LADO) >> 5;
		int norte = compensacionY >> 5;
		int sur = (compensacionY + pantalla.altoGet() + tiles.LADO) >> 5;

		for (int y = norte; y < sur; y++) {
			for (int x = oeste; x < este; x++) {
				// cuadroGet(x, y).mostrar(x, y, pantalla);
				if (x < 0 || y < 0 || x >= ancho || y >= alto) {
					tiles.vacio.mostrar(x, y, pantalla);
				} else {
					cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
				}
			}
		}

	}

	public tiles obtenCuadroCatalogo(int posicion) {
		return cuadrosCatalogo[posicion];
	}

	public int anchoMapa() {
		return ancho;
	}

	// public tiles cuadroGet(final int x, final int y) {
	// if (x < 0 || y < 0 || x >= ancho || y >= alto) {
	// return tiles.vacio;
	// }
	//
	// switch (cuadros[x + y * ancho]) {
	// case 0:
	// return tiles.pastoSprite;
	// case 1:
	// return tiles.florSprite;
	// case 2:
	// return tiles.florgameSprite;
	// case 3:
	// return tiles.cajaSprite;
	// case 4:
	// return tiles.grasaSprite;
	// case 5:
	// return tiles.dulceSprite;
	// case 6:
	// return tiles.letreroSprite;
	//
	// default:
	// return tiles.vacio;
	// }
	// }

}
