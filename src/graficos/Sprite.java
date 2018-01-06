package graficos;

public final class Sprite {
	private final int lado;

	private int x;
	private int y;

	public int[] pixeles;
	private HojaSprites hoja;

	// Coleccion de Sprite

	// Sprite personaje: malvavisco
	public static final Sprite malvaviscoFrente = new Sprite(32, 0, 0, 0, HojaSprites.personaje);
	public static final Sprite malvaviscoFrentePieI = new Sprite(32, 0, 1, 0, HojaSprites.personaje);
	public static final Sprite malvaviscoFrentePieD = new Sprite(32, 0, 2, 0, HojaSprites.personaje);

	public static final Sprite malvaviscoAtras = new Sprite(32, 1, 0, 0, HojaSprites.personaje);
	public static final Sprite malvaviscoAtrasPieI = new Sprite(32, 1, 1, 0, HojaSprites.personaje);
	public static final Sprite malvaviscoAtrasPieD = new Sprite(32, 1, 2, 0, HojaSprites.personaje);

	public static final Sprite malvaviscoLadoI = new Sprite(32, 2, 0, 0, HojaSprites.personaje);
	public static final Sprite malvaviscoLadoIpieI = new Sprite(32, 2, 1, 0, HojaSprites.personaje);
	public static final Sprite malvaviscoLadoIpieD = new Sprite(32, 2, 2, 0, HojaSprites.personaje);

	public static final Sprite malvaviscoLadoD = new Sprite(32, 2, 0, 1, HojaSprites.personaje);
	public static final Sprite malvaviscoLadoDpieI = new Sprite(32, 2, 1, 1, HojaSprites.personaje);
	public static final Sprite malvaviscoLadoDpieD = new Sprite(32, 2, 2, 1, HojaSprites.personaje);

	// Sprite mapa
	public static final Sprite pastoSprite = new Sprite(32, 0, 0, 0, HojaSprites.desierto);
	public static final Sprite florSprite = new Sprite(32, 1, 0, 0, HojaSprites.desierto);
	public static final Sprite florgameSprite = new Sprite(32, 2, 0, 0, HojaSprites.desierto);
	public static final Sprite cajaSprite = new Sprite(32, 3, 0, 0, HojaSprites.desierto);
	public static final Sprite grasaSprite = new Sprite(32, 4, 0, 0, HojaSprites.desierto);
	public static final Sprite dulceSprite = new Sprite(32, 5, 0, 0, HojaSprites.desierto);
	public static final Sprite letreroSprite = new Sprite(32, 6, 0, 0, HojaSprites.desierto);

	public static final Sprite vacio = new Sprite(32, 0);
	// Fin de coleccion de sprite

	public Sprite(final int lado, final int columna, final int fila, final int version, final HojaSprites hoja) {
		this.lado = lado;
		pixeles = new int[lado * lado];

		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;
		cargarSprite(version);
	}

	public Sprite(final int lado, final int color) {
		this.lado = lado;
		pixeles = new int[lado * lado];
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}
	}

	public int LadoGet() {
		return lado;
	}

	private void cargarSprite(int version) {
		if (version == 0) {
			cargarNormal();
		} else {

			cargaManipulada(version);

		}
	}
	private void cargarNormal() {
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixelesGet((x + this.x) + (y + this.y) * (hoja.obtenAncho()));
			}
		}
	}

	private void cargaManipulada(int version) {
		int[] pixelesTemporales = iniciarPixelesTemporales();

		switch (version) {

			case 1 :
				invertirX(pixelesTemporales);
				break;
			case 2 :
				invertirY(pixelesTemporales);
				break;
			case 3 :
				invertirXY(pixelesTemporales);
				break;
			case 4 :
				rotar90I(pixelesTemporales);
				break;
			case 5 :
				rotar90D(pixelesTemporales);
				break;
			case 6 :
				rotarI90InvertirY(pixelesTemporales);
				break;
			case 7 :
				rotarD90InvertirY(pixelesTemporales);
			default :
				cargarNormal();
				break;

		}

	}
	private int[] iniciarPixelesTemporales() {

		int[] pixelesTemporales = new int[lado * lado];
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixelesTemporales[x + y * lado] = hoja.pixelesGet((x + this.x) + (y + this.y) * (hoja.obtenAncho()));
			}
		}
		return pixelesTemporales;
	}

	private void invertirX(int[] pixelesTemporales) {
		int i = 0;
		for (int y = 0; y < lado; y++) {
			for (int x = lado - 1; x > -1; x--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}

	}
	private void invertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int y = lado - 1; y > -1; y--) {
			for (int x = 0; x < lado; x++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}

	}
	private void invertirXY(int[] pixelesTemporales) {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = pixelesTemporales[pixelesTemporales.length - 1 - i];
		}

	}
	private void rotar90I(int[] pixelesTemporales) {
		int i = 0;
		for (int x = lado - 1; x > -1; x--) {
			for (int y = 0; y < lado; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}

	}
	private void rotar90D(int[] pixelesTemporales) {
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = lado - 1; y > -1; y--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}

	}
	private void rotarI90InvertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = 0; y < lado; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}
	private void rotarD90InvertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int x = lado - 1; x > -1; x--) {
			for (int y = lado - 1; y > -1; y--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

}
