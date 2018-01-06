package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {
	private final int ancho;
	private final int alto;
	private final int[] pixeles;

	// Coleccion de hoja de sprite.

	protected static HojaSprites desierto = new HojaSprites("/texturas/HojaSprite.png", 320, 320);
	protected static HojaSprites personaje = new HojaSprites("/texturas/HojaPersonaje.png", 320, 320);
	// Fin de la coleccion.

	public HojaSprites(final String ruta, final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;
		pixeles = new int[ancho * alto];
		BufferedImage imagen;
		try {
			imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public int obtenAncho() {
		return ancho;
	}

	public int pixelesGet(int x) {
		return pixeles[x];
	}
}
