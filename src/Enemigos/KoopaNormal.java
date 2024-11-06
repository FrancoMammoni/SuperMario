package Enemigos;

import Fabricas.Sprite;
import Juego.EntidadDinamica;
import Juego.Jugador;
import Juego.MasterMind;

public class KoopaNormal implements StakeKt{
	
	protected Sprite spriteCaparazon; 

	public KoopaNormal(Sprite caparazon) {
		this.spriteCaparazon = caparazon;
	}

	public void afectarJugador(Jugador jugador, KoopaTroopa kt, MasterMind m) {
		
		boolean JugadorPorDebajo = jugador.getPosY() + jugador.getHitbox().height  >=  kt.getPosY() + kt.getHitbox().height/2;
		
		if(jugador.getInvencible()) {
			kt.morir();
			jugador.getEstadistica().aumentarPuntos(kt.getPuntajeASumar());
		}
		else {
			if(JugadorPorDebajo) { 
				jugador.comenzarColisionEnemigo(kt.TIEMPO_COLISION);
				jugador.decrementarEstado(kt.getPuntajeRestar());
			}
			else {
				jugador.getEstadistica().aumentarPuntos(kt.getPuntajeASumar());	
				jugador.rebotar();
				serGolpeadoCaparazon(kt,m);
			}
		}
	}

	public void afectarEntidad(EntidadDinamica ed, KoopaTroopa kt, MasterMind mm) {
		// El KoopaNormal no afecta a otros enemigos.
	}

	public void serGolpeadoCaparazon(KoopaTroopa kt, MasterMind m) {
		kt.setEstado(new KoopaCaparazon());
		kt.setMiSprite(spriteCaparazon);
		kt.setVelocidad(0);
		m.agregarCaparazon(kt);
	}
}
