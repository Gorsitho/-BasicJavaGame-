package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener {

	private final static int numeroTeclas = 120;
	private final boolean[] teclas = new boolean[numeroTeclas];

	private boolean arriba;
	private boolean abajo;
	private boolean izquierda;
	private boolean derecha;

	private boolean correr;

	public void actualizar() {
		arriba = teclas[KeyEvent.VK_UP];
		abajo = teclas[KeyEvent.VK_DOWN];
		izquierda = teclas[KeyEvent.VK_LEFT];
		derecha = teclas[KeyEvent.VK_RIGHT];

		correr = teclas[KeyEvent.VK_SHIFT];
	}

	public void keyPressed(KeyEvent e) {

		teclas[e.getKeyCode()] = true;

	}

	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

	public boolean teclasJuego(int x) {
		return teclas[x];
	}

}
