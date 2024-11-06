package PowerUps;


import Juego.EntidadEstatica;
import Juego.Jugador;
import Utils.Hitbox;
import Utils.Visitor;

public class FlorDeFuego extends PowerUp{

	public FlorDeFuego() {
	}
	
	@Override
	public int getVelocidad() {
		return 0;	
		}

	@Override
	public void colision(String lugar, EntidadEstatica miBloque) {
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		this.eliminar = true;
	}

	@Override
	public void afectarJugador(Jugador j) {
		j.getState().serAfectado(j, this);
	}
}
