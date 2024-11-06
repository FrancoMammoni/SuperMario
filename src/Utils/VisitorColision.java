package Utils;


import Enemigos.Enemigo;
import PowerUps.PowerUp;
import Juego.Jugador;

public class VisitorColision implements Visitor {
	
	protected Jugador miJugador;
	
	
	public VisitorColision(Jugador j) {
		this.miJugador = j;
	}

	@Override
	public void visit(Enemigo k) {
		miJugador.serAfectado(k);
	}

	@Override
	public void visit(PowerUp b) {
		b.afectarJugador(miJugador);
	}

	

}
