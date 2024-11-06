package Plataformas;

import Fabricas.FabricaMario;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Juego.Jugador;
import Juego.MasterMind;
import PowerUps.PowerUp;
import Utils.Hitbox;

public class BloqueEspecial extends Plataforma {
	
	protected PowerUp        powerUp;
	protected Sprite         spritePowerUp;
	protected FabricaSprites fabrica = new FabricaMario();
	protected MasterMind     miMasterMind;
	
	public BloqueEspecial(PowerUp p, Sprite sprite) {
		this.miHitbox= new Hitbox(this.posX,this.posY,48,48);
		this.powerUp=p;
		this.spritePowerUp = sprite;
	}
	
	public void colisionPorDebajo(Jugador j) {
		
		if(powerUp!=null) {
			crearPowerUp();
			powerUp=null;
		}
		this.setMiSprite(fabrica.getBloqueVacio());
	}
	public void crearPowerUp() {
		powerUp.setMasterMind(miMasterMind);
		powerUp.setPosX(posX);
		powerUp.setPosY(posY - miHitbox.height);
		powerUp.setMiHitbox(miHitbox.x, miHitbox.y - miHitbox.height);
		powerUp.setMiSprite(spritePowerUp);
		miMasterMind.getLinkedListEntidades().add(powerUp);
}
	
	public void setMasterMind(MasterMind mm) {
		this.miMasterMind = mm;
	}

}
