package Grafica;

import Juego.EntidadDinamica;
import Juego.EntidadEstatica;
import Juego.Partida;
import Mario.BolaDeFuego;
import Sonidos.ReproductorSonido;
import Sonidos.Sonido;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class PantallaJuego extends Pantalla{

	protected Partida 				miPartida;
	protected ControladorDeVistas 	miControlador;
	protected InputListener 		miInputListener;
	protected Image 				fondo;
	protected ImageIcon 			jugador;
	protected ReproductorSonido 	sonidoFondo;
	protected Sonido 				sonidosPantalla;
	protected int 					desplazamientoHorizontal;
	protected int 					nivel;
	protected String 				skin;

	public PantallaJuego(ControladorDeVistas c, String skin) {
		this.nivel = 1;
		this.miControlador = c;
		this.skin = skin;
		this.agregarKeyListener();
		this.configurarPantalla();
		this.comenzarPartida(skin);
		this.miControlador.setPartida(this.miPartida);
	}

	protected void agregarKeyListener() {
		this.miInputListener = new InputListener();
		this.addKeyListener(this.miInputListener);
	}

	protected void configurarPantalla() {
		try {
			if(this.skin.equals("NORMAL")) 
				fondo = ImageIO.read(new File("src/Imagenes/mario_fondo_" + nivel + ".png"));			
			else
				fondo = ImageIO.read(new File("src/ImagenesSkinMinecraft/mario_fondo_" + nivel + ".png"));
		} catch(Exception e) { System.out.println("El archivo png de la imagen del fondo no fue encontrado."); }
		this.setPreferredSize(new Dimension(this.ALTO_PANTALLA, this.ANCHO_PANTALLA));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); 
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	public void comenzarPartida(String skin) {
		reproducirSonidoDeFondo();
		this.miControlador.setPartida(this.miPartida);
		this.miPartida = new Partida(this, skin);
	}

	private void reproducirSonidoDeFondo() {
		this.sonidosPantalla = new Sonido();
		this.sonidoFondo = new ReproductorSonido(0, this.sonidosPantalla);
		this.sonidoFondo.reproducir();
		this.sonidoFondo.loop();
	}
	public void actualizar() {
		boolean inhabilitado = this.miPartida.getJugador().getInhabilitado();
		// La pantalla lee el input del usuario:
		if(this.miInputListener.isSpacePressed() && !inhabilitado) {
			this.miPartida.realizarAccion();
		}
		if(this.miInputListener.isRightPressed() && !inhabilitado) {
			this.miPartida.moverDerecha();
			desplazamientoFondo();
		} 
		else if(this.miInputListener.isLeftPressed() && !inhabilitado) {
			this.miPartida.moverIzquierda();
		} 
		else if(!inhabilitado){ 
			this.miPartida.getJugador().quieto(); //Cambia la imagen de mario a quieto
		}
		if(this.miInputListener.isUpPressed() && !inhabilitado) {
			this.miPartida.saltar();
		} 
		else if(this.miPartida.getJugador().getSaltando()) {
			this.miPartida.saltar();
		}
		if(this.miPartida.getJugador().getInvencible()) {
			this.sonidoFondo.pausar();
		} 
		else {
			this.sonidoFondo.despausar();
		}
	}

	public synchronized void paintComponent(Graphics grafico) {
		super.paintComponent(grafico);
		this.dibujarFondo(grafico);
		this.dibujarMario(grafico);
		this.dibujarEntidadesDinamicas(grafico);
		this.dibujarEntidadesEstaticas(grafico);
		this.dibujarEstadisticas(grafico);
		grafico.dispose();
	}

	protected void dibujarFondo(Graphics grafico2d) {
		grafico2d.drawImage(this.fondo,0, 0, this.ALTO_PANTALLA,
							this.ANCHO_PANTALLA, this.desplazamientoHorizontal,
							0, this.ALTO_PANTALLA + this.desplazamientoHorizontal, this.ANCHO_PANTALLA, this);
	}

	protected synchronized void dibujarEntidadesEstaticas(Graphics grafico2d) {
		grafico2d.setColor(Color.green);
		
		for(EntidadEstatica entidad : new LinkedList<>(this.miPartida.getMapa().getLinkedListEntidades())) {
			
			boolean enPantalla = entidad.getPosX() >= - 48 && entidad.getPosX() < this.BORDE_PANTALLA_DERECHO;
			
			if(enPantalla) { 
				ImageIcon sprite = new ImageIcon(entidad.getMiSprite().getRutaImagen());
				grafico2d.drawImage(sprite.getImage(),entidad.getPosX(),entidad.getPosY(),
									entidad.getHitbox().width , entidad.getHitbox().height, this);
			}
		}
	}

	protected synchronized void dibujarEntidadesDinamicas(Graphics grafico2d) {
		grafico2d.setColor(Color.green);
		for(EntidadDinamica entidad: new LinkedList<>(this.miPartida.getMastermind().getLinkedListEntidades())) {
			
			boolean enPantalla = entidad.getPosX() >= - 48 && entidad.getPosX() < this.BORDE_PANTALLA_DERECHO;
			
			if(enPantalla) {
				ImageIcon sprite = new ImageIcon(entidad.getMiSprite().getRutaImagen());
				if(entidad.getDireccion() == 1) {
					grafico2d.drawImage(sprite.getImage() ,entidad.getPosX(),entidad.getPosY(), entidad.getHitbox().width, entidad.getHitbox().height, this);
				}
				else {
					grafico2d.drawImage(sprite.getImage() ,entidad.getPosX() + this.SIZE_BALDOZA, entidad.getPosY(), entidad.getHitbox().width * -1, entidad.getHitbox().height, this);
				}
			}
		}
		for(BolaDeFuego entidad : new LinkedList<>(this.miPartida.getMastermind().getLinkedListBolasDeFuego())) {
			
			boolean enPantalla = entidad.getPosX() >= - 48 && entidad.getPosX() < this.BORDE_PANTALLA_DERECHO;
			
			if(enPantalla) {
				ImageIcon sprite = new ImageIcon(entidad.getMiSprite().getRutaImagen());
				if(entidad.getDireccion() == 1) {
					grafico2d.drawImage(sprite.getImage() ,entidad.getPosX(),entidad.getPosY(), entidad.getHitbox().width, entidad.getHitbox().height, this);
				}
				else {
					grafico2d.drawImage(sprite.getImage() ,entidad.getPosX() + this.SIZE_BALDOZA, entidad.getPosY(), entidad.getHitbox().width * -1, entidad.getHitbox().height, this);
				}
			}
		}
	}

	protected void dibujarMario(Graphics grafico2d) {
		this.jugador = new ImageIcon(this.miPartida.getJugador().getMiSprite().getRutaImagen());
		// Mario mira a la derecha o quieto (0 o 1)
		if(this.miPartida.getJugador().getDireccion() >= 0) {
			dibujarMarioMirandoDerecha(grafico2d);
		} 
		else { 
			dibujarMarioMirandoIzquierda(grafico2d);
		}
	}

	private synchronized void dibujarEstadisticas(Graphics grafico2d) {
		try {
			Font fuente = Font.createFont(Font.TRUETYPE_FONT, new File("src/nintendo-nes-font.ttf")).deriveFont(Font.BOLD, 14);
			grafico2d.setFont(fuente);
			grafico2d.setColor(Color.WHITE);
			grafico2d.drawString("VIDAS " + this.miPartida.getEstadistica().getVida() + "    " +
								 "PUNTAJE " + this.miPartida.getEstadistica().getPuntos() + "    " +
								 "MONEDAS " + this.miPartida.getEstadistica().getCantMonedas()+"    " +
								 "TIEMPO " + this.miPartida.getTiempoRestante()+"    "+
								 "NIVEL " + (this.miPartida.getParser().getNivel()+1), 50, 25);
		} catch(Exception e) {}
	}

	private void dibujarMarioMirandoIzquierda(Graphics grafico2d) {
		grafico2d.drawImage(this.jugador.getImage(),
							this.miPartida.getJugador().getPosX() + this.SIZE_BALDOZA,
							this.miPartida.getJugador().getPosY(),
							this.SIZE_BALDOZA * this.miPartida.getJugador().getDireccion(),
							this.SIZE_BALDOZA * this.miPartida.getJugador().getAltoSprite(),
							this);
	}

	private void dibujarMarioMirandoDerecha(Graphics grafico2d) {
		grafico2d.drawImage(this.jugador.getImage(),
							this.miPartida.getJugador().getPosX(),
							this.miPartida.getJugador().getPosY(),
							this.SIZE_BALDOZA,
							this.SIZE_BALDOZA * this.miPartida.getJugador().getAltoSprite(),
							this);
	}

	protected synchronized void desplazamientoFondo() {
		// Se modifica la posicion del fondo y el tope izquierdo del jugador.
		if (this.miPartida.getJugador().getPosX() >= this.ALTO_PANTALLA/2) {
			int corrimiento = this.miPartida.getJugador().getVelocidad();
			this.desplazamientoHorizontal += corrimiento;
			this.miPartida.getMapa().desplazamientoElementosEstaticos(corrimiento);
			this.miPartida.getMastermind().desplazamientoElementosDinamicos(corrimiento);
			this.miPartida.getJugador().setDesplazamientoX(corrimiento);
		}
	}

	public synchronized void reiniciarNivel() {
		// Se resta el desplazamiento horizontal a las posiciones de los elementos del nivel, para que vuelvan a su posicion original.
		Iterator<EntidadEstatica> itEstatico = this.miPartida.getMapa().getLinkedListEntidades().iterator();
		while(itEstatico.hasNext()) {
			EntidadEstatica entidad = itEstatico.next();
			entidad.setDesplazamientoX(-desplazamientoHorizontal);
		}
		Iterator<EntidadDinamica> itDinamico = this.miPartida.getMastermind().getLinkedListEntidades().iterator();
		while(itDinamico.hasNext()) {
			EntidadDinamica entidad = itDinamico.next();
			entidad.setDesplazamientoX(-desplazamientoHorizontal);
		}
		this.desplazamientoHorizontal = 0;
	}
	
	public void gameOver(String resultado) {
		this.miControlador.accionarPantallaGameOver(this.miPartida.getEstadistica().getPuntos(), resultado);
		this.sonidoFondo.pausar();
		this.miPartida.getTimer().pararHilo();
	}

	public void cambioNivel() {
		this.nivel++;
		this.miControlador.accionarPantallaNivel();
		this.miPartida.getTimer().dormirHilo(1000);
		setearInput(false);
		this.miControlador.reanudarPantallaJuego();
		configurarPantalla();
	}

	public int getDesplazamiento() {
		return this.desplazamientoHorizontal;
	}

	private void setearInput(boolean f) {
		this.miInputListener.setLeftPressed(f);
		this.miInputListener.setRightPressed(f);
		this.miInputListener.setSpacePressed(f);
		this.miInputListener.setUpPressed(f);
	}
}