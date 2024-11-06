package Mario;

import Enemigos.Enemigo;
import Juego.Jugador;
import PowerUps.*;
import Utils.Hitbox;

public class StateMarioNormal extends StateJugador {
	
	public StateMarioNormal() {
		this.altoSprite = 1;
		this.corriendo = "/mario_normal_corriendo.gif";
		this.saltando = "/mario_normal_saltando.png";
		this.quieto = "/mario_normal_quieto.png";
		this.bajando = "/mario_normal_bajando_banderaF.png";
		this.invencible = false;
	}
	
	public void serAfectado(Jugador j,Enemigo k) {	
		k.afectarJugador(j);
	}
	
	public void decrementarEstado(Jugador j,int i) {
		j.getEstadistica().sacarPuntos(i);
		j.getEstadistica().sacarVida();
	}
	
	public Hitbox getHitbox(int posX, int posY) {
		return new Hitbox(posX,posY, 48,48);
	}
	
	public void setInvencible(boolean t, Jugador j) {
		this.invencible = t;	
		if(t) {
			
			corriendo ="/mario_normal_invencible_corriendo.gif";
			saltando= "/mario_normal_invencible_saltando.gif";
			quieto="/mario_normal_invencible_quieto.gif";
		}
		else 
			volverAlEstado();
	}
	
	private void volverAlEstado() {
		this.corriendo = "/mario_normal_corriendo.gif";
		this.saltando = "/mario_normal_saltando.png";
		this.quieto = "/mario_normal_quieto.png"; 
	}

	@Override
	public int getPuntaje(FlorDeFuego f) {
		return 5;
	}
	
	@Override
	public int getPuntaje(Estrella e) {
		int puntaje=20;
		if(esInvencible())
			puntaje+=15;
		return puntaje;
	}
	
	@Override
	public int getPuntaje(SuperChampinion s) {
		return 10;
	}
	
	public boolean romperBloque() {
		return false;
	}
	
}
