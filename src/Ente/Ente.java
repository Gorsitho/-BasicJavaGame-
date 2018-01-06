package Ente;

import mapa.mapa;

public abstract class Ente {

	protected int x;
	protected int y;
	private boolean eliminado = false;
	protected mapa mapa;

	public void actualizar() {

	}
	public void mostrar() {

	}
	public void eliminar() {
		eliminado = true;
	}
	public void modPosicionX(int desplazamientoX) {
		x += desplazamientoX;
	}
	public void modPosicionY(int desplazamientoY) {
		y += desplazamientoY;
	}
	public int posicionX() {
		return x;
	}
	public int posicionY() {
		return y;
	}
	public boolean estaEliminado() {
		return eliminado;
	}

}
