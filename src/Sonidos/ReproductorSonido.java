package Sonidos;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class ReproductorSonido extends Sonido {
	
	protected Clip   clip;
	protected Sonido sonidos;
	protected int    frameSonido;
	
	public ReproductorSonido(int i, Sonido s) {
		this.sonidos = s;
		this.setPistaSonido(i);
	}
	
	public void reproducir() {
		this.clip.setFramePosition(0);
		this.clip.start();
	}
	
	@SuppressWarnings("static-access")
	public void loop() {
		this.clip.loop(clip.LOOP_CONTINUOUSLY);	
	}
	
	public void pausar() {
		this.frameSonido = clip.getFramePosition();
		this.clip.stop();
	}
	
	public void setPistaSonido(int i) {
		try {
			AudioInputStream flujoEntradaudio = AudioSystem.getAudioInputStream(this.sonidos.rutaSonido[i]);
			this.clip = AudioSystem.getClip();
			this.clip.open(flujoEntradaudio);
			
		} catch(Exception e ) {}	
	}

	public void despausar() {
		if(!clip.isRunning()) {
			this.clip.setFramePosition(frameSonido);
			this.clip.start();
		}
	}
}
