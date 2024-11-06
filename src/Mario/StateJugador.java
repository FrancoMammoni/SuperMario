package Mario;

import Enemigos.Enemigo;
import Juego.Jugador;
import PowerUps.ChampinionVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.SuperChampinion;
import Utils.Hitbox;

public abstract class StateJugador {
	
	protected int     altoSprite; // Tamanio en baldozas.
	protected String  corriendo;
	protected String  saltando; 
	protected String  quieto;
	protected String  bajando;
	protected boolean invencible;
	
	public void serAfectado(Jugador j, Estrella estrella){
		j.setContadorEstrella(1200);
		j.getState().setInvencible(true, j);
		j.getEstadistica().aumentarPuntos(getPuntaje(estrella));
		
	}
	
	public void serAfectado(Jugador j, SuperChampinion superChampinion) {
		if(!j.getInvencible()) {
			if(j.getAltoSprite()<2) {
				j.setSuelo(j.getSuelo()-48);
				j.setPosY(j.getPosY()-48);
				j.setStateMario(new StateSuperMario());				
			}
			j.getEstadistica().aumentarPuntos(getPuntaje(superChampinion));		
			}
	}
	
	public void serAfectado(Jugador j, FlorDeFuego f) {
		if(!j.getInvencible()) {
			if (j.getAltoSprite()==1) {
				j.setSuelo(j.getSuelo()-48);	
				j.setPosY(j.getPosY()-48);

			}
			j.setStateMario(new StateMarioDeFuego(j));
			j.getEstadistica().aumentarPuntos(this.getPuntaje(f));
		}
	}
	
	public void serAfectado(Jugador j, ChampinionVerde v) {
		j.getEstadistica().aumentarVidas();
		j.getEstadistica().aumentarPuntos(100);
	}
	
	public void realizarAccion(Jugador j) {}
	
	public String getCorriendo() {
		return this.corriendo;
	}
	
	public void serAfectado(Jugador j,Enemigo k) {}
	
	public String getSaltando() {
		return this.saltando;
	}
	
	public String getBajando() {
		return this.bajando;
	}
	
	public String getQuieto() {
		return this.quieto;
	}
	
	public int getAltoSprite() {
		return this.altoSprite;
	}
	
	public boolean romperBloque() {
		return true;
	}
	public boolean esInvencible() {
		return this.invencible;
	}
	
	public abstract void setInvencible(boolean t, Jugador j);
	
	public abstract int getPuntaje(FlorDeFuego f);
	
	public abstract int getPuntaje(Estrella e);
	
	public abstract int getPuntaje(SuperChampinion s);
	
	public abstract Hitbox getHitbox(int posX, int posY);
	
	public abstract void decrementarEstado(Jugador j, int i);
}
