package Juego;

import Enemigos.Enemigo;
import Fabricas.Sprite;
import Mario.BolaDeFuego;
import Mario.StateJugador;
import Mario.StateMarioNormal;
import PowerUps.PowerUp;

import Sonidos.ReproductorSonido;
import Sonidos.Sonido;
import Utils.Hitbox;
import Utils.Visitor;
import Utils.VisitorColision;

public class Jugador extends EntidadDinamica {

	protected double 			velY;
	protected boolean 			saltando;
	protected boolean			colisionando;
	protected boolean 			inhabilitado;
	protected int				contadorColision;
	protected int				contadorEstrella; 
	protected int 				topeIzquierdo; 
	protected int 				LINEA_DE_META = 8850;
	protected int 				suelo;
	protected int 				techo;
	protected Partida			miPartida;
	protected StateJugador 		miEstado;
	protected Estadistica 		miEstadistica;
	protected Sonido 			misSonidos;
	protected ReproductorSonido repSalto;
	protected ReproductorSonido repColision; 
	protected VisitorColision 	visitor;

	public Jugador(Sprite s, Partida p, Estadistica e) {
		this.visitor=new VisitorColision(this);
		this.posX = 100;
		this.posY = 535;
		this.velocidad = 3;
		this.velY = 0;
		this.topeIzquierdo = 0;
		this.direccion = 1;
		this.miSprite = s;
		this.miPartida = p;
		this.miEstado = new StateMarioNormal();
		this.miEstadistica = e;
		this.suelo = 535;
		this.techo  = 535;
		this.misSonidos = new Sonido();
		this.repColision = new ReproductorSonido(2, this.misSonidos);
		this.repSalto = new ReproductorSonido(1, this.misSonidos);
		this.setHitbox(posX, posY);
		this.inhabilitado = false;
	}

	// Métodos existentes para mover al jugador
	public void derecha() {
		boolean llegueABandera = this.posX >= this.LINEA_DE_META;
		if(!inhabilitado) {
			this.posX += this.velocidad;
			this.miHitbox.translate(this.velocidad,0);
			if(!saltando) {
				this.miSprite = this.miPartida.getFabricaSprites().getCorriendo(this.miEstado.getCorriendo());
			}
			this.direccion = 1;
			if(llegueABandera) {
				this.setInvulnerable(true, 300);
				this.inhabilitado = true;
				this.gameWin();
			}
		}
	}

	public void izquierda() {
		if(!inhabilitado) {
			if (this.posX >= this.topeIzquierdo) {
				this.posX -= this.velocidad;
				this.miHitbox.translate(-this.velocidad,0);
			}
			this.miSprite = this.miPartida.getFabricaSprites().getCorriendo(this.miEstado.getCorriendo());
			this.direccion = -1;
		}
	}

	public void saltar() {
		if(!inhabilitado) {
			this.miSprite = this.miPartida.getFabricaSprites().getSaltando(this.miEstado.getSaltando());
			if (!this.saltando) {
				this.repSalto.reproducir();
				this.velY -= 6;
				this.saltando = true;
				this.miHitbox.translate(0,-6);
			}
			if (this.saltando) {
				this.posY += this.velY;
				this.velY += 0.1; // Gravedad
				if (this.posY+this.velY >= suelo || this.posY>=this.suelo) {// Llegar al suelo
					this.velY = 0;
					this.saltando = false;
					this.posY = suelo;
					this.miHitbox.translate(0,6);
				}
			}
		}
	}

	public void bajar() {
		boolean estoyCayendo = this.posY<=this.suelo && !this.saltando;
		boolean llegueAlSuelo = this.posY>=this.suelo && !this.saltando;
		if(llegueAlSuelo) {
			this.posY = this.suelo;
			this.velY = 0;
		}
		else if(estoyCayendo) {
			this.posY += this.velY;
			this.velY += 0.1;
		}
	}

	public void quieto() {
		this.miHitbox.setLocation(this.posX,this.posY);
		this.miSprite = this.miPartida.getFabricaSprites().getQuieto(this.miEstado.getQuieto());
	}

	public void serAfectado(Enemigo k) {
		this.miEstado.serAfectado(this,k);
	}

	public void serAfectado(PowerUp p) {
		this.visitor.visit(p);
	} 
	
	public void decrementarEstado(int i) {
		this.miEstado.decrementarEstado(this,i);
	}

	public void chequearYActualizarSuelo() {
		if(this.posY<=this.suelo) {
			bajar();
		}
		this.suelo=techo;	//si cambio el techo cambia
	}

	@Override
	public void colision(String s, EntidadEstatica e) {
		e.afectarJugador(this, s);
	}

	@Override
	public void accept(Visitor v) {
	}

	@Override
	public void afectarJugador(Jugador j) {
	}
	
	@Override
	public void moverse() {
	}
	
	@Override
	public boolean getEliminar() {
		return false;
	}

	public void decrementarContadorCol() {
		contadorColision--;
		if(contadorColision<=0)
			colisionando=false;
	}

	public void generarBolaDeFuego() {
		BolaDeFuego bf = new BolaDeFuego(this);
		this.miPartida.getMastermind().agregarBolaDeFuego(bf);
	}

	public void realizarAccion() {
		this.miEstado.realizarAccion(this);
	}

	public void rebotar() {
		setVelY(2);
		setSaltando(false);
		saltar();
	}

	public void comenzarColisionEnemigo(int i) {
		setInvulnerable(true, i);
	}

	public void decrementarContadorEstrella() {
		contadorEstrella--;
		if(contadorEstrella<=0)
			this.miEstado.setInvencible(false, this);
	}

	public void reiniciarMeta() {
		this.LINEA_DE_META = 8850;
	}

	public void gameWin() {
		boolean bajandoBandera = this.posY < this.suelo;
		boolean llegandoAlCastillo = this.posX <= this.LINEA_DE_META + 500;
		boolean llegueAlCastillo = this.posX >= 750;
		if(bajandoBandera) {
			this.miSprite = this.miPartida.getFabricaSprites().getSaltando(this.miEstado.getBajando());
			this.posY += 1;
		} 
		else if(llegandoAlCastillo) {
			this.posX += this.velocidad - 1;
			this.miSprite = this.miPartida.getFabricaSprites().getCorriendo(this.miEstado.getCorriendo());
		}
		if(llegueAlCastillo) {
			this.miPartida.siguienteNivel();
		}
	}
	
	// Setters
	public void setInhabilitado(boolean b) {
		this.inhabilitado = b;
	}
	
	public void setInvulnerable(Boolean t, int i) {
		this.setContadorInvulnerabilidad(i);
		this.colisionando = t;
	}
	
	public void setDesplazamientoX(int d) {
		this.posX -= d;
		this.miHitbox.translate(-d, 0);
		this.LINEA_DE_META -= d;
	}
	
	public void setContadorInvulnerabilidad(int ticks) {
		this.contadorColision = ticks;
	}
	
	public void setStateMario(StateJugador state) {
		this.miEstado = state;
	}
	
	public void setVelY(int i) {
		this.velY=i;
	}
	
	public void setSaltando(boolean b) {
		this.saltando = b;
	}
	
	public void setDesplazamientoY(int d) {
		this.miHitbox.translate(0, -d);
	}
	
	public void setSuelo(int i) {
		this.suelo = i;
		this.techo = i;
	}
	
	public void setContadorEstrella(int i) {
		contadorEstrella = i;
	}
	
	// Getters
	public boolean getInhabilitado() {
		return this.inhabilitado;
	}
	
	public boolean getInvulnerable() {
		return this.colisionando;
	}
	
	public boolean getInvencible() {
		return this.miEstado.esInvencible();
	}
	
	public Partida getPartida() {
		return this.miPartida;
	}
	
	public Estadistica getEstadistica() {
		return this.miEstadistica;
	}

	public Visitor getVisitor() {
		return this.visitor;
	}

	public boolean getColisionando() {
		return colisionando;
	}
	
	public Hitbox getHitbox() {
		return this.miEstado.getHitbox(this.posX,this.posY);

	}
	
	public int getAltoSprite() {
		return this.miEstado.getAltoSprite();
	}

	public int getSuelo() {
		return this.suelo;
	}

	public StateJugador getState() {
		return this.miEstado;
	}

	public double getVelocidadY() {
		return this.velY;
	}
	

	public boolean getSaltando() {
		return this.saltando;
	}
}