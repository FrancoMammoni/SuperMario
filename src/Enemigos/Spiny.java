package Enemigos;
import Juego.Jugador;

public class Spiny extends Enemigo{

	public Spiny() {
		this.puntajeASumar = 60;
		this.puntajeARestar = 30;
	}

	public void afectarJugador(Jugador jugador) {
		if(jugador.getInvencible()) {
			morir();
			jugador.getEstadistica().aumentarPuntos(this.puntajeASumar);
		} 
		else {
			jugador.comenzarColisionEnemigo(this.TIEMPO_COLISION);
			jugador.decrementarEstado(this.puntajeARestar);
		}
	}
	
}

