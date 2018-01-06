package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mapa.cuadros.tiles;

public class mapaCargado extends mapa {

	private int[] pixeles;

	public mapaCargado(String ruta) {
		super(ruta);
	}

	protected void cargarMapa(String ruta) {

		try {
			BufferedImage imagenCargada = ImageIO.read(mapaCargado.class.getResource(ruta));

			ancho = imagenCargada.getWidth();
			alto = imagenCargada.getHeight();
			cuadrosCatalogo = new tiles[ancho * alto];
			pixeles = new int[ancho * alto];

			imagenCargada.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void generarMapa() {

		for (int i = 0; i < pixeles.length; i++) {

			switch (pixeles[i]) {
			case 0xffabd56e:
				cuadrosCatalogo[i] = tiles.pastoSprite;
				continue;
			case 0xffc12f00:
				cuadrosCatalogo[i] = tiles.florSprite;
				continue;
			case 0xfff88700:
				cuadrosCatalogo[i] = tiles.florgameSprite;
				continue;
			case 0xffb3b3b3:
				cuadrosCatalogo[i] = tiles.cajaSprite;
				continue;
			case 0xffedd207:
				cuadrosCatalogo[i] = tiles.grasaSprite;
				continue;
			case 0xffff7369:
				cuadrosCatalogo[i] = tiles.dulceSprite;
				continue;
			case 0xff5f3814:
				cuadrosCatalogo[i] = tiles.letreroSprite;
				continue;
			default:
				cuadrosCatalogo[i] = tiles.vacio;
			}

		}

	}

}
