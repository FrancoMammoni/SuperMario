package Mario;

import Enemigos.Enemigo;
import Juego.Jugador;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.SuperChampinion;
import Utils.Hitbox;

public class StateSuperMario extends StateJugador{
	
	public StateSuperMario() {
		this.altoSprite = 2;
		this.corriendo = "/super_mario_corriendo.gif";
		this.saltando = "/super_mario_saltando.png";
		this.quieto = "/super_mario_quieto.png";
		this.bajando = "/super_mario_bajando_banderaF.png";
		this.invencible=false;
	}
	
	public void decrementarEstado(Jugador j, int i) {
		j.setSuelo(j.getSuelo()+48);
		j.setPosY(j.getPosY()+48);
		j.setStateMario(new StateMarioNormal());
	}
	
	public void serAfectado(Jugador j, Enemigo k) {
		k.afectarJugador(j);
	}
	
	@Override
	public int getPuntaje(Estrella e) {
		int puntaje=30;
		if(esInvencible())
			puntaje+=5;
		return puntaje;
	}
	
	public void setInvencible(boolean t, Jugador j) {
		this.invencible = t;	
		if(t) {
			corriendo ="/super_mario_invencible_corriendo.gif";
			saltando= "/super_mario_invencible_saltando.gif";
			quieto="/super_mario_invencible_quieto.gif";
		}
		else
			volverAlEstado();
	}
	
	protected void volverAlEstado() {
		this.corriendo = "/super_mario_corriendo.gif";
		this.saltando = "/super_mario_saltando.png";
		this.quieto = "/super_mario_quieto.png";
	}

	@Override
	public int getPuntaje(FlorDeFuego f) {
		return 30;
	}
	
	@Override
	public int getPuntaje(SuperChampinion s) {
		return 50;
	}
	
	public Hitbox getHitbox(int posX, int posY) {
		return new Hitbox(posX,posY, 48, 96);	
	}


	
}
