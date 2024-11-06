package Juego;

import Fabricas.Sprite;
import Mario.BolaDeFuego;
import Sonidos.Sonido;
import Utils.Hitbox;

abstract public class Entidad {

	protected int 	 	posX; 
	protected int 	 	posY;
	protected Sprite 	miSprite;
	protected Hitbox 	miHitbox;
	protected Sonido 	misSonidos = new Sonido();

	public void serGolpeadoBolaDeFuego(BolaDeFuego bf) {}

	//Setters
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setMiSprite(Sprite miSprite) {
		this.miSprite = miSprite;
	}

	public void setDesplazamientoX(int d) {
		this.posX -= d;
		this.miHitbox.translate(-d, 0);
	}

	public void setHitbox(int posX2, int posY2) {
		this.miHitbox= new Hitbox(posX2, posY2, 48, 48);
	}

	//Getters
	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}


	public Sprite getMiSprite() {
		return this.miSprite;
	}

	public Hitbox getHitbox() {
		return this.miHitbox;
	}
}