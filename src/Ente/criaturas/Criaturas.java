package Ente.criaturas;

import Ente.Ente;
import graficos.Sprite;

public abstract class Criaturas extends Ente {

	protected Sprite sprite;
	protected char direccion = 'n';
	protected boolean enMovimiento = false;

	public void actualizar() {

	}
	public void mostrar() {

	}

	public void mover(int desplazamientoX, int desplazamientoY) {
		if (desplazamientoX > 0) {

			direccion = 'e';
		}
		if (desplazamientoX < 0) {
			direccion = 'o';
		}
		if (desplazamientoY > 0) {
			direccion = 's';
		}
		if (desplazamientoY < 0) {
			direccion = 'n';
		}
		if (!estaEliminado()) {

			if (!enColision(desplazamientoX, 0))
				modPosicionX(desplazamientoX);

			if (!enColision(0, desplazamientoY))
				modPosicionY(desplazamientoY);
		}

	}

	public Sprite obtenSprite() {
		return sprite;
	}

	private boolean enColision(int desplazamientoX, int desplazamientoY) {
		boolean colision = false;

		int posicionX = x + desplazamientoX;
		int posicionY = y + desplazamientoY;

		int margenIzquierdo = -1;
		int margenDerecho = 18;
		int margenSuperior = -4;
		int margenInferior = 31;

		int bordeIzquierdo = (posicionX + margenDerecho) / sprite.LadoGet();
		int bordeDerecho = (posicionX + margenIzquierdo) / sprite.LadoGet();

		int bordeSuperior = (posicionY + margenInferior) / sprite.LadoGet();
		int bordeInferior = (posicionY + margenSuperior + margenInferior) / sprite.LadoGet();

		if (mapa.obtenCuadroCatalogo(bordeIzquierdo + bordeSuperior * mapa.anchoMapa()).esSolido()) {
			colision = true;
		}
		if (mapa.obtenCuadroCatalogo(bordeIzquierdo + bordeInferior * mapa.anchoMapa()).esSolido()) {
			colision = true;
		}
		if (mapa.obtenCuadroCatalogo(bordeDerecho + bordeSuperior * mapa.anchoMapa()).esSolido()) {
			colision = true;
		}
		if (mapa.obtenCuadroCatalogo(bordeDerecho + bordeInferior * mapa.anchoMapa()).esSolido()) {
			colision = true;
		}

		return colision;
	}
}
