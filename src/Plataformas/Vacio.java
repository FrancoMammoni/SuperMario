package Plataformas;

import Juego.EntidadEstatica;
import Juego.Jugador;
import Utils.Hitbox;

public class Vacio extends EntidadEstatica{
	
	public Vacio() {
	}
	
	public void afectarJugador(Jugador j, String lugar) {
		j.setSaltando(true);
	    j.setSuelo(j.getPosY() + 1000);
	    j.bajar();    
	}
	
	public void setHitbox(int posX, int posY) {
		this.miHitbox = new Hitbox(posX,posY, 48, 150);
	}



}
