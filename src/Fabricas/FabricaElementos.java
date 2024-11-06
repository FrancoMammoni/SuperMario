package Fabricas;

import Enemigos.BuzzyBeetle;
import Enemigos.Goomba;
import Enemigos.KoopaTroopa;
import Enemigos.Lakitu;
import Enemigos.PiranhaPlant;
import Enemigos.Spiny;
import Juego.Entidad;
import Juego.EntidadDinamica;
import Juego.EntidadEstatica;
import Plataformas.BloqueDePregunta;
import Plataformas.BloqueSolido;
import Plataformas.LadrilloSolido;
import Plataformas.Tuberia;
import PowerUps.ChampinionVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.PowerUp;
import PowerUps.SuperChampinion;

public class FabricaElementos{

	public void generarEntidad(Entidad e,int posX, int posY, Sprite s) {
		e.setPosX(posX);
		e.setPosY(posY);
		e.setMiSprite(s);
		e.setHitbox(posX, posY);
	}
	
	public Tuberia generarTuberia(int posX, int posY, Sprite tuberia, String tamanio) {
		Tuberia t= new Tuberia(tamanio);
		inicializarAtributos(t,posX,posY,tuberia);
		return t;
	}
	
	public PiranhaPlant generarPiranhaPlant(int posX, int posY, Sprite piranha) {
		PiranhaPlant p= new PiranhaPlant();
		inicializarAtributos(p,posX,posY,piranha);
		p.setTopes(posY);
		return p;

	}
	
	public Estrella generarEstrella(int posX, int posY, Sprite estrella) {
		Estrella e = new Estrella();
		inicializarAtributos(e,posX,posY,estrella);
		return e;

	} 
	public ChampinionVerde generarChampinionVerde(int posX, int posY, Sprite champinionVerde) {
		ChampinionVerde c = new ChampinionVerde();
		inicializarAtributos(c,posX,posY,champinionVerde);
		return c;
	}

	public Moneda generarMoneda(int posX, int posY, Sprite moneda) {
		Moneda m = new Moneda();
		inicializarAtributos(m,posX,posY,moneda);
		return m;
	}

	public FlorDeFuego generarFlorDeFuego(int posX, int posY, Sprite florDeFuego) {
		FlorDeFuego f = new FlorDeFuego();
		inicializarAtributos(f,posX,posY,florDeFuego);
		return f;
	}

	public SuperChampinion generarSuperChampinion(int posX, int posY, Sprite superChampinion) {
		SuperChampinion s =new SuperChampinion();
		inicializarAtributos(s,posX,posY,superChampinion);
		return  s;
	}

	public BloqueDePregunta generarBloqueDePregunta(int posX, int posY, Sprite bloqueDePregunta, PowerUp powerUp, Sprite spritePower) {
		BloqueDePregunta bp =new BloqueDePregunta(powerUp, spritePower);
		inicializarAtributos(bp,posX,posY,bloqueDePregunta);
		return bp;
	}

	public LadrilloSolido generarLadrilloSolido(int posX, int posY, Sprite ladrilloSolido) {
		LadrilloSolido ls= new LadrilloSolido();
		inicializarAtributos(ls,posX,posY,ladrilloSolido);
		return ls;
	}

	public BloqueSolido generarBloqueSolido(int posX, int posY, Sprite bloqueSolido) {
		BloqueSolido bs= new BloqueSolido();
		inicializarAtributos(bs,posX,posY,bloqueSolido);
		return bs;
	}
	
	public KoopaTroopa generarKoopa(int posX, int posY, Sprite koopa, Sprite caparazon) {
		KoopaTroopa kt= new KoopaTroopa(caparazon);
		inicializarAtributos(kt,posX,posY,koopa);
		return kt;
	}

	public Goomba generarGoomba(int posX, int posY, Sprite goomba) {
		Goomba g= new Goomba();
		inicializarAtributos(g,posX,posY,goomba);
		return g;
	}

	public BuzzyBeetle generarBuzzyBeetle(int posX, int posY, Sprite buzzyBeetle) {
		BuzzyBeetle bb= new BuzzyBeetle();
		inicializarAtributos(bb,posX,posY,buzzyBeetle);
		return bb;
	}
	
	public Lakitu generarLakitu(int posX, int posY, Sprite lakitu, Sprite spiny) {
		Lakitu l= new Lakitu(this, spiny);
		inicializarAtributos(l,posX,posY,lakitu);
		return l;
	}
	
	public Spiny generarSpiny(int posX, int posY, Sprite spiny) {
		Spiny s= new Spiny();
		inicializarAtributos(s,posX,posY,spiny);
		return s;
	}

	private void inicializarAtributos(Entidad entidad,int posX, int posY, Sprite sprite) {
		entidad.setPosX(posX);
		entidad.setPosY(posY);
		entidad.setMiSprite(sprite);
		entidad.setHitbox(posX,posY);
		
	}

}
