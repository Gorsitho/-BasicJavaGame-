package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Ente.criaturas.Jugador;
import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.mapa;
import mapa.mapaCargado;

public class Juego extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	private static volatile boolean enFuncionamiento = false;

	private static final String NOMBRE = "Malvavisco";
	private static final String Autor = "Daniel Velasquez";
	private static int aps = 0;
	private static int fps = 0;

	private static Pantalla p1;
	private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private static int pixeles[] = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

	private static JFrame ventana;
	private static Thread thread;

	private static mapa mapa_1;
	private static Jugador malvavisco;

	private static Teclado teclado;

	private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono/iconoAmor.png"));

	private Juego() {
		setPreferredSize(new Dimension(ANCHO, ALTO));

		p1 = new Pantalla(ANCHO, ALTO);
		// mapa_1 = new mapaGenerado(128, 128);

		teclado = new Teclado();
		addKeyListener(teclado);

		mapa_1 = new mapaCargado("/texturas/MosaicoMapa.png");
		malvavisco = new Jugador(mapa_1, teclado, Sprite.malvaviscoFrente, 320, 320);

		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setIconImage(icono.getImage());
		ventana.setLayout(new BorderLayout());
		ventana.add(this, BorderLayout.CENTER);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

	}

	public static void main(String[] arg) {

		Juego juego = new Juego();
		juego.iniciar();

	}

	private synchronized void iniciar() {
		enFuncionamiento = true;
		thread = new Thread(this, "graficos");
		thread.start();
	}

	private synchronized void detener() {

		enFuncionamiento = false;
		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	private void actualizar() {

		teclado.actualizar();
		malvavisco.actualizar();

		aps++;
	}

	private void mostrar() {
		BufferStrategy estrategia = getBufferStrategy();

		if (estrategia == null) {
			createBufferStrategy(3);
			return;
		}

		// p1.limpiar();
		// p1.mostrar(x, y);
		mapa_1.mostrar(malvavisco.posicionX() - p1.anchoGet() / 2 + malvavisco.obtenSprite().LadoGet() / 2,
				malvavisco.posicionY() - p1.altoGet() / 2 + malvavisco.obtenSprite().LadoGet() / 2, p1);
		malvavisco.mostrar(p1);

		System.arraycopy(p1.pixeles, 0, pixeles, 0, pixeles.length);

		Graphics g = estrategia.getDrawGraphics();
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
		g.drawString(Autor, 0, ALTO - 5);
		g.drawString("X:" + malvavisco.posicionX(), 10, 50);
		g.drawString("Y:" + malvavisco.posicionY(), 10, 60);
		g.dispose();
		estrategia.show();
		fps++;
	}

	public void run() {
		final int NS_POR_SEGUNDO = 1000000000;
		final byte APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();
		double tiempoTranscurrido;
		double delta = 0;
		requestFocus();

		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
			while (delta >= 1) {

				actualizar();
				delta--;
			}

			mostrar();
			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				ventana.setTitle(NOMBRE + "||APS:" + aps + "||FPS :" + fps);
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}

		}

	}

}
