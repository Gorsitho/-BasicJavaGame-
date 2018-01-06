package Ente.criaturas;

import java.awt.event.KeyEvent;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.mapa;

public class Jugador extends Criaturas {
	private Teclado teclado;

	private int animacion;

	public Jugador(mapa mapa, Teclado teclado, Sprite sprite) {
		this.mapa = mapa;
		this.teclado = teclado;
		this.sprite = sprite;
	}

	public Jugador(mapa mapa, Teclado teclado, Sprite sprite, int posicionX, int posicionY) {
		this(mapa, teclado, sprite);
		this.x = posicionX;
		this.y = posicionY;
	}

	public void actualizar() {

		int desplazamientoX = 0;
		int desplazamientoY = 0;

		int velocidadMovimiento = 1;

		animacion = animacion < 32767 ? animacion += 1 : 0;
		if (teclado.teclasJuego(KeyEvent.VK_SHIFT)) {
			velocidadMovimiento = 2;
		}
		if (teclado.teclasJuego(KeyEvent.VK_UP)) {
			desplazamientoY -= velocidadMovimiento;
		}
		if (teclado.teclasJuego(KeyEvent.VK_DOWN)) {
			desplazamientoY += velocidadMovimiento;
		}
		if (teclado.teclasJuego(KeyEvent.VK_LEFT)) {
			desplazamientoX -= velocidadMovimiento;
		}
		if (teclado.teclasJuego(KeyEvent.VK_RIGHT)) {
			desplazamientoX += velocidadMovimiento;
		}
		if (desplazamientoX != 0 || desplazamientoY != 0) {

			mover(desplazamientoX, desplazamientoY);

			enMovimiento = true;
		} else {
			enMovimiento = false;
		}

		int resto = animacion % 40;
		if (direccion == 'n') {
			sprite = Sprite.malvaviscoAtras;
			if (enMovimiento)
				sprite = animacion % 30 > 15 ? sprite.malvaviscoAtrasPieI : sprite.malvaviscoAtrasPieD;
		}
		if (direccion == 's') {
			sprite = Sprite.malvaviscoFrente;
			if (enMovimiento)
				sprite = animacion % 30 > 15 ? sprite.malvaviscoFrentePieI : sprite.malvaviscoFrentePieD;
		}
		if (direccion == 'o') {
			sprite = Sprite.malvaviscoLadoI;
			if (enMovimiento) {
				if (resto > 10 && resto <= 20) {
					sprite = sprite.malvaviscoLadoIpieI;
				} else if (resto > 20 && resto <= 30) {
					sprite = Sprite.malvaviscoLadoI;
				} else if (resto > 30) {
					sprite = sprite.malvaviscoLadoIpieD;
				} else {
					sprite = Sprite.malvaviscoLadoI;
				}

			}

		}
		if (direccion == 'e') {
			sprite = Sprite.malvaviscoLadoD;
			if (enMovimiento) {
				if (resto > 10 && resto <= 20) {
					sprite = sprite.malvaviscoLadoDpieI;
				} else if (resto > 20 && resto <= 30) {
					sprite = Sprite.malvaviscoLadoD;
				} else if (resto > 30) {
					sprite = sprite.malvaviscoLadoDpieD;
				} else {
					sprite = Sprite.malvaviscoLadoD;
				}

			}
		}
	}

	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarJugador(x, y, this);
	}
}
