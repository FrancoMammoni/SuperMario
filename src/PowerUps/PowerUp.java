package PowerUps;

import Juego.EntidadDinamica;
import Utils.Hitbox;

abstract public class PowerUp extends EntidadDinamica{
	
	public void moverse() {}
	public void caer() {}
	
	public void setMiHitbox(int posX, int posY) {
		this.miHitbox = new Hitbox(posX,posY,48,48);
	}
	
}
