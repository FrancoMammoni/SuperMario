package Enemigos;

import Juego.EntidadDinamica;
import Juego.EntidadEstatica;
import Juego.Jugador;
import Mario.BolaDeFuego;
import Utils.Visitor;

public abstract class Enemigo extends EntidadDinamica{
	
	protected final int 	TIEMPO_COLISION =  60;
	protected boolean 		eliminar = false;
	protected int 			suelo = 535; 
	protected int 			velY;
	protected int			puntajeASumar;
	protected int			puntajeARestar;
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public void moverse() {
		boolean mirandoHaciaDerecha = direccion == 1;
		
		if(mirandoHaciaDerecha) {
			this.posX += this.velocidad;
			this.miHitbox.translate(this.velocidad, 0);	

		}
		else {
			this.posX -= this.velocidad;
			this.miHitbox.translate(-this.velocidad, 0);
		}
	}
	
	public void serGolpeadoBolaDeFuego(BolaDeFuego bf) {
		this.miMasterMind.getMiPartida().getEstadistica().aumentarPuntos(this.puntajeASumar);
		this.eliminar = true;
		bf.setEliminar();
	}

	public void serGolpeadoCaparazon() {
		this.miMasterMind.getMiPartida().getEstadistica().aumentarPuntos(this.puntajeASumar);
		this.eliminar = true;
	}

	public void colision(String lugar, EntidadEstatica miBloque) {
		if(lugar.equals("izquierda")) {
			this.direccion = 1; 		// La direccion cambia hacia la derecha.
		}

		if(lugar.equals("derecha")) {
			this.direccion = -1; 		// La direccion cambia hacia la izquierda.
		}

		if(lugar.equals("abajo")) {
			this.suelo = miBloque.getPosY() - miBloque.getHitbox().height;
			this.setPosY(suelo);
			this.getHitbox().setLocation(posX, suelo);
		}
	}

	public void morir() {
		this.eliminar = true;
	}
	
	public void afectarJugador(Jugador jugador) {

		boolean JugadorPorDebajo = jugador.getPosY() + jugador.getHitbox().height >= this.getPosY() + this.getHitbox().height/2;

		if(jugador.getInvencible()) {
			morir();
			jugador.getEstadistica().aumentarPuntos(puntajeASumar);
		} 
		else {
			if(JugadorPorDebajo) { 
				jugador.comenzarColisionEnemigo(TIEMPO_COLISION);
				getPuntajeRestar();
				jugador.decrementarEstado(this.puntajeARestar);
			} 
			else {
				jugador.getEstadistica().aumentarPuntos(puntajeASumar);
				jugador.rebotar();
				morir();
			}
		}
	}
	
	public int getPuntajeASumar() {
		return this.puntajeASumar;
	}
	
	public int getPuntajeRestar() {
		return this.puntajeARestar;
	}
	
	public boolean getEliminar() {
		return this.eliminar;
	}
	
	public void cambioDireccion() {
		this.direccion = this.direccion * (-1);
	}
}
