package Enemigos;

import Juego.EntidadEstatica;
import Juego.Jugador;
import Utils.Hitbox;

public class PiranhaPlant extends Enemigo{

	protected int 		topeSuperior;
	protected int 		topeInferior;
	protected boolean 	plantaAfuera;
	protected boolean  	bajando;

	public PiranhaPlant() {
		this.bajando=true;
		this.puntajeASumar = 30;
		this.puntajeARestar = 30;
	}

	
	public void moverse() {
		if(this.bajando) {
			bajar();
		}
		else
			subir();
	}

	
	private void subir() {
		posY -= velocidad;
		this.miHitbox.translate(0, -velocidad);

		if(posY == this.topeSuperior) {
			this.bajando=true;
		}
	}

	private void bajar() {
		posY += velocidad;
		this.miHitbox.translate(0, velocidad);

		if(posY == topeInferior) {
			this.bajando=false;
		}
	}

	public void afectarJugador(Jugador jugador) {
		
		this.plantaAfuera= posY<=this.topeSuperior + this.miHitbox.height;

		if(jugador.getInvencible()) {
			morir();
			jugador.getEstadistica().aumentarPuntos(puntajeASumar);
		}
		
		else {
			if(plantaAfuera) {
				jugador.comenzarColisionEnemigo(this.TIEMPO_COLISION);
				jugador.decrementarEstado(puntajeARestar);
			}
		}
	}

	public void setTopes(int posY) {
		this.topeSuperior = posY ;
		this.topeInferior = posY + this.miHitbox.height*2;
	} 

	public void setHitbox(int posX, int posY) {
		this.miHitbox = new Hitbox(posX,posY, 35, 35);
	}
	
	public void colision(String lugar, EntidadEstatica miBloque) {}
	
	public void caer() {}
	
	}
