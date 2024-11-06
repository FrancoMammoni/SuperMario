package PowerUps;


import Juego.EntidadEstatica;
import Juego.Jugador;
import Sonidos.ReproductorSonido;
import Utils.Hitbox;
import Utils.Visitor;

public class Moneda extends PowerUp {
	
	protected ReproductorSonido repMoneda;
	
	public Moneda() {
		this.repMoneda = new ReproductorSonido(3, this.misSonidos);
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
		this.repMoneda.reproducir();
		this.eliminar = true;
	}

	@Override
	public void afectarJugador(Jugador j) {
		j.getEstadistica().aumentarPuntos(5);
		j.getEstadistica().aumentarCantMonedas();
	}
	
	public void caer() {}


}
