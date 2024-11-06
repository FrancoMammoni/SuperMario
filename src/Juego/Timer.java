package Juego;

import Utils.Colision;

public class Timer implements Runnable{

	protected Partida 		miPartida;
	protected Thread 		miHilo;
	protected Colision 		miColision;
	protected MasterMind 	miMastermind;

	public Timer(Partida p, MasterMind mm) {
		this.miPartida = p;
		this.miColision = new Colision(p.getMastermind(),p.getMapa(),p.getJugador());
		this.miHilo = new Thread(this);
		this.miMastermind = mm;
		this.comenzarHilo();
	}

	public void comenzarHilo() {
		this.miHilo.start();
	}

	public void dormirHilo(int tiempo) {
		try {
			Thread.sleep(tiempo);
		} catch (InterruptedException e) { e.printStackTrace(); }
	}

	@SuppressWarnings("deprecation")
	public void pararHilo() {
		this.miHilo.stop();
	}
	
	@Override
	public void run() {
		
		int fps = 120;
		long intervaloTick = 1000 / fps;
		long siguienteIntervaloTick = System.currentTimeMillis() + intervaloTick;
		long tiempoInicial = System.currentTimeMillis();

		while(this.miHilo != null) {

			this.miMastermind.moverEntidades();
			this.miColision.chequearAdyacenciaEstaticaDeEntidades();
			this.miColision.chequearAdyacenciaDinamicaDeEntidad();
			this.miColision.chequearAdyacenciaJugador();
			this.miPartida.actualizar();
			this.miPartida.getPantallaJuego().repaint();
			this.miMastermind.agregarEntidades();
			
			try {

				long milisegundosRestantes = siguienteIntervaloTick - System.currentTimeMillis(); 
				
				if(milisegundosRestantes < 0)
					milisegundosRestantes = 0;
				
				Thread.sleep(milisegundosRestantes);

				siguienteIntervaloTick += intervaloTick;

				if(System.currentTimeMillis() - tiempoInicial >= 1000) {
					this.miPartida.decrementarTiempo();
					tiempoInicial = System.currentTimeMillis();
				}

			} catch (InterruptedException e) { e.printStackTrace(); }	
		}
	}
}
