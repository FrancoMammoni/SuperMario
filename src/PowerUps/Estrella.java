package PowerUps;


import Juego.EntidadEstatica;
import Juego.Jugador;
import Sonidos.ReproductorSonido;
import Utils.Visitor;

public class Estrella extends PowerUp {
	
	protected ReproductorSonido repEstrella;

	public Estrella(){
		repEstrella = new ReproductorSonido(9, this.misSonidos);
	}
	@Override
	public int getVelocidad() {
		return 0;
	}

	@Override
	public void colision(String lugar, EntidadEstatica miBloque) {
	}

	@Override
	public void afectarJugador(Jugador j) {
		j.getState().serAfectado(j, this);
		repEstrella.reproducir();
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		this.eliminar = true;
	}
}
