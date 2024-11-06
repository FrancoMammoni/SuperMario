package Plataformas;


import Juego.Jugador;
import Mario.BolaDeFuego;
import PowerUps.PowerUp;

public class LadrilloSolido extends Plataforma {
	
	protected PowerUp powerUp;
	
	public LadrilloSolido() {
		
	}
	public void colisionPorDebajo(Jugador j) { 
		boolean romper= j.getState().romperBloque();
			if(romper) {
				this.miMapa.getLinkedListEntidades().remove(this);
				System.out.println("rompi");
			}
		
	}
	
	public void serGolpeadoBolaDeFuego(BolaDeFuego bf) {
		this.miMapa.getLinkedListEntidades().remove(this);
		bf.setEliminar();
	}
	

	
}
