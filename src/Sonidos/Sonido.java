package Sonidos;
import java.net.URL;


public class Sonido {
	
	protected URL[] rutaSonido = new URL[30]; // Contiene las rutas a todas las pistas de audio.

	public Sonido() {
		this.rutaSonido[0] = getClass().getResource("FondoTheme.wav");
		this.rutaSonido[1] = getClass().getResource("SaltoNormal.wav");
		this.rutaSonido[2] = getClass().getResource("Choque.wav");
		this.rutaSonido[3] = getClass().getResource("moneda.wav");
		this.rutaSonido[4] = getClass().getResource("MasUnaVida.wav");
		this.rutaSonido[5] = getClass().getResource("SuperChampi.wav");
		this.rutaSonido[6] = getClass().getResource("StageClear.wav");
		this.rutaSonido[7] = getClass().getResource("smb_gameover.wav");
		this.rutaSonido[8] = getClass().getResource("BolaDeFuego.wav");
		this.rutaSonido[9] = getClass().getResource("Estrella.wav");
	}
}