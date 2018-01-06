package mapa.cuadros;

import graficos.Pantalla;
import graficos.Sprite;

public class tiles {
	private int x;
	private int y;

	public Sprite sprite;
	private boolean solido;
	public static final int LADO = 32;
	// Coleccion de cuadros

	public static final tiles pastoSprite = new tiles(Sprite.pastoSprite);
	public static final tiles florSprite = new tiles(Sprite.florSprite, true);
	public static final tiles florgameSprite = new tiles(Sprite.florgameSprite, true);
	public static final tiles cajaSprite = new tiles(Sprite.cajaSprite, true);
	public static final tiles grasaSprite = new tiles(Sprite.grasaSprite);
	public static final tiles dulceSprite = new tiles(Sprite.dulceSprite);
	public static final tiles letreroSprite = new tiles(Sprite.letreroSprite, true);
	public static final tiles vacio = new tiles(Sprite.vacio, true);

	// Fin de la coleccion de cuadros

	public tiles(Sprite sprite) {
		this.sprite = sprite;
		solido = false;
	}
	public tiles(Sprite sprite, boolean solido) {
		this.sprite = sprite;
		this.solido = solido;
	}

	public void mostrar(int x, int y, Pantalla pantalla) {
		pantalla.mostrarCuadro(x << 5, y << 5, this);
	}

	public boolean esSolido() {
		return solido;
	}
}
