package Juego;

import Fabricas.FabricaMario;
import Fabricas.FabricaMarioMinecraft;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Grafica.PantallaJuego;
import Mario.StateMarioNormal;
import Parser.ParserMapa;

public class Partida {

	protected Mapa 				miMapa;
	protected MasterMind 		miMastermind;
	protected ParserMapa 		miParser;
	protected Timer 			miTimer;
	protected PantallaJuego 	miPantallaJuego;
	protected Jugador 			miJugador;
	protected Estadistica 		miEstadistica;
	protected FabricaSprites 	miFabricaSprites;
	protected int 				tiempoRestante;

	public Partida(PantallaJuego p, String skin) {
		this.miMapa = new Mapa();
		this.miEstadistica = new Estadistica(this);
		this.miMastermind = new MasterMind(this);
		this.miParser = new ParserMapa();
		this.miJugador = new Jugador(new Sprite("src/Imagenes/mario_normal_quieto.png"),this,this.miEstadistica);
		this.miPantallaJuego = p;

		if(skin.equals("NORMAL")) 
			this.miFabricaSprites = new FabricaMario();
		else if(skin.equals("MINECRAFT")) 
			this.miFabricaSprites = new FabricaMarioMinecraft();

		this.tiempoRestante = 300;
		this.miParser.cargarNivel(this.miFabricaSprites, this.miMapa, this.miMastermind);
		this.miTimer = new Timer(this, miMastermind);
	}

	public void actualizar() {
		if(this.miJugador.getInhabilitado()) 
			this.miJugador.gameWin();

		boolean jugadorCayoAlVacio = this.miJugador.getPosY()>=670;
		if (jugadorCayoAlVacio) {
			this.miJugador.getEstadistica().sacarPuntos(15);
			this.miJugador.getEstadistica().sacarVida();
		}

		if(this.miJugador.getInvulnerable()) 
			this.miJugador.decrementarContadorCol();

		if(this.miJugador.getInvencible())
			this.miJugador.decrementarContadorEstrella();

		this.miPantallaJuego.actualizar();
	}


	public void moverDerecha() {
		this.miJugador.derecha();
	}

	public void moverIzquierda() {
		this.miJugador.izquierda();
	}

	public void realizarAccion() {
		this.miJugador.realizarAccion();
	}

	public void saltar() {
		this.miJugador.saltar();
	}

	public void reiniciarNivel() { 
		this.miPantallaJuego.reiniciarNivel();
		this.miJugador.setStateMario(new StateMarioNormal());
		reiniciarPosicionJugador();
	}

	public void reiniciarPosicionJugador() {
		this.miJugador.setPosX(100);
		this.miJugador.setPosY(535 - 48 * (miJugador.getAltoSprite() - 1)); 
		this.miJugador.setSuelo( miJugador.getPosY() );
		this.miJugador.reiniciarMeta();
		this.tiempoRestante = 300;
	}	

	public void gameOver() {
		this.miPantallaJuego.gameOver("Perder");
	}

	public void gameWin() {
		this.miPantallaJuego.gameOver("Ganar");
	}

	public void siguienteNivel() {
		setearListas();
		this.miParser.siguienteNivel(this.miFabricaSprites, this.miMapa, this.miMastermind, this);
	}

	private void setearListas() {
		this.miMapa.limpiarListas();
		this.miMastermind.limpiarListas();
	}

	public void cambioNivel() {
		this.miPantallaJuego.cambioNivel();
		this.miJugador.setInhabilitado(false);
	}

	public void decrementarTiempo() {
		boolean sinTiempo = (this.tiempoRestante--) <= 0;
		if(sinTiempo) {
			gameOver();
		}
	}
	//Setters y Getters.
	public void setMapa(Mapa m) {
		this.miMapa = m;	
	}

	public Mapa getMapa() {
		return this.miMapa;
	}

	public void setMastermind(MasterMind m) {
		this.miMastermind = m;
	}

	public MasterMind getMastermind() {
		return this.miMastermind;
	}

	public void setParser(ParserMapa p) {
		this.miParser = p;
	}

	public ParserMapa getParser() {
		return this.miParser;
	}

	public void setTimer(Timer t) {
		this.miTimer = t;
	}

	public Timer getTimer() {
		return this.miTimer;
	}

	public void setPantallaJuego(PantallaJuego p) {
		this.miPantallaJuego = p;
	}

	public PantallaJuego getPantallaJuego() {
		return this.miPantallaJuego;
	}

	public void setJugador(Jugador j) {
		this.miJugador = j;
	}

	public Jugador getJugador() {
		return this.miJugador;
	}

	public void setEstadistica(Estadistica e) {
		this.miEstadistica = e;
	}

	public Estadistica getEstadistica() {
		return this.miEstadistica;
	}

	public void setFabricaSprites(FabricaSprites f) {
		this.miFabricaSprites = f;
	}

	public FabricaSprites getFabricaSprites() {
		return this.miFabricaSprites;
	}

	public int getTiempoRestante() {
		return this.tiempoRestante;
	}
}