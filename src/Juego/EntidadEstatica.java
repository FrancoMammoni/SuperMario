package Juego;

import java.awt.Image;
import javax.swing.ImageIcon;

abstract public class EntidadEstatica extends Entidad {

	protected Mapa miMapa;

	public  void colisionPorDebajo(Jugador miJugador) {}

	public void afectarJugador(Jugador j, String lugar) {
		switch(lugar) {
		case "derecha":
			
			// Empuja la entidad hacia la izquierda, fuera del objeto estático
			j.setPosX(this.getHitbox().x - j.getHitbox().width);
			j.repColision.reproducir();
			j.bajar();
			break;
			
		case "izquierda":
			
			// Empuja la entidad hacia la derecha, fuera del objeto estático
			j.setPosX(this.getHitbox().x + j.getHitbox().width);
			j.repColision.reproducir();
			j.bajar();	            
			break;
			
		case "arriba":
			
			// Empuja la entidad hacia abajo, ajustando según la altura del objeto estático.
			colisionPorDebajo(j); 
			j.setPosY(this.getHitbox().y + this.getHitbox().height);
			j.repColision.reproducir();
			j.velY = 0;
			j.bajar();	            
			break;
			
		case "abajo":
			
			// Empuja la entidad hacia arriba considerando el alto de la misma.
			j.velY = 0; // Detener el movimiento vertical
			j.suelo=this.getHitbox().y - j.getHitbox().height;
			j.setPosY(this.getHitbox().y - j.getHitbox().height);
			break;
			
		}
	}
	
	//Setters
	public void setMimapa(Mapa miMapa) {
		this.miMapa=miMapa;
	}

	public void setMasterMind(MasterMind miMasterMind) {}
	
	public void setDesplazamientoX(int d) {
		this.posX -= d;
		this.miHitbox.translate(-d, 0);
	}
	
	//Getters
	public Image getImagen() {
		ImageIcon sprite = new ImageIcon(getMiSprite().getRutaImagen());
		return sprite.getImage();
	}
}