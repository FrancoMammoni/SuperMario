package Enemigos;

import Juego.EntidadDinamica;
import Juego.Jugador;
import Juego.MasterMind;

public interface StakeKt {
	
	public void afectarJugador(Jugador j, KoopaTroopa kt, MasterMind m);
	public void afectarEntidad(EntidadDinamica ed, KoopaTroopa kt, MasterMind mm);
	public void serGolpeadoCaparazon(KoopaTroopa kt, MasterMind m);
	
}

