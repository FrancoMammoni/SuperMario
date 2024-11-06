package Enemigos;


import Fabricas.FabricaElementos;
import Fabricas.Sprite;
import Juego.Jugador;
import Utils.Visitor;

public class Lakitu extends Enemigo{

	protected int 				limiteIzq;
	protected int			 	limiteDer;
	protected long 				tiempoActual;
	protected long				tiempoAnterior;
	protected FabricaElementos	miFabrica;
	protected Sprite 			spriteSpiny;
	protected final int			LIMITE_IZQUIERDO=200;
	protected final int			LIMITE_DERECHO=700;

	public Lakitu(FabricaElementos fabricaElementos, Sprite spiny) {
		
		this.velocidad = 2;
		this.puntajeASumar=60;
		this.puntajeARestar = 0;
		this.tiempoAnterior = 0;
		this.miFabrica = fabricaElementos;
		this.spriteSpiny = spiny;
		
	}

	public void tirarSpiny() {
		this.tiempoActual = System.currentTimeMillis();
		
		if(this.tiempoActual >= this.tiempoAnterior + 5000) {
			
			Spiny s = this.miFabrica.generarSpiny(posX, posY, this.spriteSpiny);
			s.setMasterMind(this.miMasterMind);
			
			this.miMasterMind.getLinkedListAAgregar().addLast(s);
			this.tiempoAnterior = System.currentTimeMillis();
		}
	}

	
	public void moverse() {
		boolean enRangoActivacion = this.posX <= 750;
		
		if(enRangoActivacion) {

			this.setPosX(this.posX + this.velocidad * direccion);
			this.miHitbox.setLocation(this.posX, this.posY);
			this.tirarSpiny();

			if (this.getPosX() >= this.LIMITE_DERECHO) {
				direccion = -1; 
			} 
			else if (this.getPosX() <= LIMITE_IZQUIERDO) {
				direccion = 1;
			}
		}
	}

	public void caer() {}
}

