package Mario;

import Enemigos.Enemigo;
import Juego.Jugador;
import PowerUps.*;
import Sonidos.ReproductorSonido;
import Sonidos.Sonido;
import Utils.Hitbox;

public class StateMarioDeFuego extends StateSuperMario {
	
	protected long              tiempoActual;
	protected long              tiempoAnterior;
	protected Sonido            sonidos;
	protected ReproductorSonido repBola;
	
	public StateMarioDeFuego(Jugador j) {
		this.sonidos = new Sonido();
		this.repBola = new ReproductorSonido(8, this.sonidos);
		this.tiempoAnterior =0;
		this.altoSprite = 2;
		this.corriendo = "/mario_de_fuego_corriendo.gif";
		this.quieto = "/mario_de_fuego_quieto.png";
		this.saltando = "/mario_de_fuego_saltando.png";
		this.bajando = "/mario_de_fuego_bajando_banderaF.png";
		this.invencible=false;
	}
	
	public void serAfectado(Jugador j,Enemigo k) {	
		k.afectarJugador(j);
	}
	
	public void decrementarEstado(Jugador j, int i) {
		j.setStateMario(new StateSuperMario());
	}
	
	public Hitbox getHitbox(int posX, int posY) {
		return new Hitbox(posX,posY, 48,96);
	}
	
	public int getPuntaje(FlorDeFuego f) {
		return 50;
	}
	
	
	protected void volverAlEstado() {
		this.corriendo = "/mario_de_fuego_corriendo.gif";
		this.quieto = "/mario_de_fuego_quieto.png";
		this.saltando = "/mario_de_fuego_saltando.png";
	}
	public void realizarAccion(Jugador j) {
		tiempoActual = System.currentTimeMillis();
		
		if(tiempoActual >= tiempoAnterior + 500) { // El cooldown es de minimo 500milisecs, o medio seg p/los bosteros
			this.repBola.reproducir();

			//Se crea la bola y se tira;
			
			j.generarBolaDeFuego();
			
			this.tiempoAnterior = System.currentTimeMillis();
		}
		
		
	}
}
