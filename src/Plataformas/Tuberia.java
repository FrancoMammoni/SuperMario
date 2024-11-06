package Plataformas;


import Juego.Jugador;
import Utils.Hitbox;

public class Tuberia extends Plataforma {
	
	protected int tamanio;
	
	public Tuberia(String tamanio) {
		if(tamanio.equals("Grande"))
		this.tamanio = 1;
		else if(tamanio.equals("Mediana"))
				this.tamanio=0;
		else this.tamanio=-1;
	}
	
	public Hitbox getHitbox() {
		return this.miHitbox;
	}
	
	public void setHitbox(int posX,int posY) {
		miHitbox = new Hitbox(posX,posY, 48, 96 + tamanio*48);
	}
	
	@Override
	public void colisionPorDebajo(Jugador miJugador) {
	}
}
