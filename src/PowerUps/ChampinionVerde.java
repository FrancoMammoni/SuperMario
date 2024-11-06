 package PowerUps;

import Juego.EntidadEstatica;
import Juego.Jugador;
import Sonidos.ReproductorSonido;
import Utils.Hitbox;
import Utils.Visitor;

public class ChampinionVerde extends PowerUp{
	
	protected ReproductorSonido repChampinionVerde;
	
	
	public ChampinionVerde() {
		this.repChampinionVerde = new ReproductorSonido(4, this.misSonidos);
	}
	
	@Override
	public int getVelocidad() {
		return 0;
	}

	@Override
	public void colision(String lugar, EntidadEstatica miBloque) {}


	@Override
	public void afectarJugador(Jugador j) {
		j.getState().serAfectado(j, this);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		this.repChampinionVerde.reproducir();
		this.eliminar = true;
	}	
}
