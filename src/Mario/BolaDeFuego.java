package Mario;


import Juego.EntidadDinamica;
import Juego.EntidadEstatica;
import Juego.Jugador;
import Utils.Hitbox;
import Utils.Visitor;

public class BolaDeFuego extends EntidadDinamica {
	
	protected int     contadorVida;
	protected Jugador miJugador;
	protected boolean saltando;
	protected double  velY;
	protected int     miSuelo;
	protected boolean pisoTemporal = false;
	
	public BolaDeFuego(Jugador j) {
		contadorVida = 120 * 2; // 120 son los fps y el segundo numero (2) son los segundos de "vida" del objeto	
		this.direccion = j.getDireccion();
		this.miMasterMind = j.getPartida().getMastermind();
		this.miSprite = j.getPartida().getFabricaSprites().getBolaDeFuego();
		this.posX = j.getPosX();
		this.posY = j.getPosY()+48;
		this.velocidad = 5;
		miJugador=j;
		velY=0;
		saltando=false;
		this.setMiHitbox(this.posX, this.posY);
		this.miSuelo = j.getSuelo()+96;
	}

	public void setMiHitbox(int posX, int posY) {
		this.miHitbox= new Hitbox(posX,posY,15,15);
	}

	@Override
	public void moverse() {
		if(contadorVida == 0) 
			this.eliminar = true;
		else {
			saltar();
			//Movimiento
			this.posX += this.velocidad * direccion;	
			this.contadorVida--;
			setMiHitbox(this.posX,this.posY);
		}
	}

	@Override
	public int getVelocidad() {
		return this.velocidad;
	}
	
	public void saltar() {
    	if (!this.saltando) {
            this.velY -= 2;
            this.saltando = true;
            this.miHitbox.translate(0,-2);
        }


        if (this.saltando) {	
            this.posY += this.velY;
            this.velY += 0.5; // Gravedad
            if (this.posY+this.velY >= this.miSuelo || this.posY>=this.miSuelo) {// Llegar al suelo
                this.velY = 0;
                this.saltando = false;
                this.posY = this.miSuelo-48;
                this.miHitbox.translate(0,2);
            }
        }
    	
        // Verificar si deja de estar en contacto con el suelo temporal
        if (pisoTemporal && this.velY>0) {
            // Restablece el suelo original
            this.miSuelo = 535+48;
            pisoTemporal = false;
        }
	}

	@Override
	public void colision(String lugar, EntidadEstatica miBloque) {
		if(lugar=="abajo") {
			this.miSuelo=miBloque.getPosY();
			pisoTemporal = true;
		}else {
			this.eliminar= true;
		}
		miBloque.serGolpeadoBolaDeFuego(this);

	}

	@Override
	public void accept(Visitor v) {
		
		
	}

	@Override
	public void afectarJugador(Jugador j) {
		
	}

	@Override
	public boolean getEliminar() {
		return this.eliminar;
	}
	
	
	
	
}