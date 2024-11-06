package Enemigos;


import Fabricas.Sprite;
import Juego.EntidadDinamica;
import Juego.Jugador;

public class KoopaTroopa extends Enemigo{

	protected StakeKt miEstado;

	public KoopaTroopa(Sprite caparazon) {
		this.miEstado = new KoopaNormal(caparazon);
		this.puntajeASumar = 90;
		this.puntajeARestar = 45;
	}
	
	public void afectarJugador(Jugador j) {
		this.miEstado.afectarJugador(j, this, this.miMasterMind);
	}

	public void afectarEntidad(EntidadDinamica d) {
		this.miEstado.afectarEntidad(d, this, this.miMasterMind);
	}

	public void serGolpeadoCaparazon() {
		this.miEstado.serGolpeadoCaparazon(this, this.miMasterMind);
	}
	
	// Setters
	public void setEstado(StakeKt kt) {
		this.miEstado = kt;
	}
	

}
