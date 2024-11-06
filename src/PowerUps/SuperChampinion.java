package PowerUps;


import Juego.EntidadEstatica;
import Juego.Jugador;
import Sonidos.ReproductorSonido;
import Utils.Visitor;

public class SuperChampinion extends PowerUp {
	 
	protected ReproductorSonido repSuperChamp;
	
	public SuperChampinion() {
		this.repSuperChamp = new ReproductorSonido(5, this.misSonidos);
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
		this.miMasterMind.getLinkedListEntidades().remove(this);
		this.repSuperChamp.reproducir();
	}

	@Override
	public void afectarJugador(Jugador j) {
		j.getState().serAfectado(j, this);
	}
}
