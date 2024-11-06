package Enemigos;

import Juego.EntidadDinamica;
import Juego.Jugador;
import Juego.MasterMind;

public class KoopaCaparazon implements StakeKt{

	public void afectarJugador(Jugador jugador, KoopaTroopa kt, MasterMind mm) {
		
		boolean JugadorPorDebajo = jugador.getPosY() + jugador.getHitbox().height >= kt.getPosY() + kt.getHitbox().height/2;

		if(jugador.getInvencible()) {
			kt.morir();
			jugador.getEstadistica().aumentarPuntos(kt.getPuntajeASumar());
		}

		if(JugadorPorDebajo) { 
			if(kt.getVelocidad() == 0) {
				jugador.comenzarColisionEnemigo(kt.TIEMPO_COLISION);
				kt.setDireccion(jugador.getDireccion());
				kt.setVelocidad(5);
			}
			else {
				jugador.comenzarColisionEnemigo(kt.TIEMPO_COLISION);
				jugador.decrementarEstado(kt.getPuntajeRestar());
			}

		} 
		else {
			jugador.getEstadistica().aumentarPuntos(kt.getPuntajeASumar());
			jugador.rebotar();
			mm.getLinkedListEntidades().remove(kt);
			mm.getLinkedListCaparazones().remove(kt);
		}
	}

	public void afectarEntidad(EntidadDinamica ed, KoopaTroopa kt, MasterMind mm) {
		if (kt.getVelocidad() > 0) {
			ed.serGolpeadoCaparazon();	
		}
	}

	public void serGolpeadoCaparazon(KoopaTroopa kt, MasterMind m) {
		kt.setVelocidad(5);
	}
}
