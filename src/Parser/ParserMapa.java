package Parser;

import java.io.FileReader;

import Enemigos.BuzzyBeetle;
import Enemigos.Goomba;
import Enemigos.PiranhaPlant;
import Enemigos.Spiny;
import java.io.BufferedReader;
import Fabricas.FabricaElementos;
import Fabricas.FabricaSprites;
import Juego.Entidad;
import Juego.EntidadDinamica;
import Juego.EntidadEstatica;
import Juego.Estadistica;
import Juego.Mapa;
import Juego.MasterMind;
import Juego.Partida;
import Plataformas.LadrilloEspecial;
import Plataformas.LadrilloSolido;
import Plataformas.Vacio;
import PowerUps.ChampinionVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.SuperChampinion;

public class ParserMapa {

    protected Mapa             miMapa;
    protected MasterMind       miMasterMind;
    protected FabricaElementos miFabricaElementos;
    protected Niveles          misNiveles;
    protected FabricaSprites   miFabricaSprites;
    protected int              nivel=0;
    protected Entidad          miEntidad;
    protected Estadistica      miEstadistica;

    public ParserMapa() {
        this.miFabricaElementos = new FabricaElementos();
        this.misNiveles = new Niveles();
    }
	
	public void cargarNivel(FabricaSprites fabrica, Mapa mapa, MasterMind mastermind) {
		this.miMapa = mapa;
		this.miMasterMind = mastermind;
		this.miFabricaSprites= fabrica;
		generarNivel();
	}
	
	public void siguienteNivel(FabricaSprites fabrica, Mapa mapa, MasterMind mastermind, Partida p) {	
		if(nivel<2) {
			p.cambioNivel();
			this.nivel++;
			this.miMapa = mapa;
			this.miMasterMind = mastermind;
			this.miFabricaSprites = fabrica;
			p.getPantallaJuego().reiniciarNivel();
			p.reiniciarPosicionJugador();
			generarNivel();
	}
		else
			p.gameWin();
	}
	
	public void generarNivel() {
		BufferedReader lector;
		String linea;
		char caracter;
		int posX, posY;
		try {
			lector = new BufferedReader(new FileReader(this.misNiveles.getNivel(nivel)));
			linea = lector.readLine();
			while(linea != null) {
				
				// partes contiene la linea con el formato: char posX posY;
				String[] partes = linea.split(" ");
				caracter = partes[0].charAt(0);
				posX = Integer.parseInt(partes[1]);
				posY = Integer.parseInt(partes[2]);
				
				traducir(caracter, posX, posY);
				linea = lector.readLine();
			}	
			} catch(Exception e) {
				
			}
	}
	public  void traducir(char c, int posX, int posY) {
		synchronized(this.miMapa.getLinkedListEntidades()) {
			synchronized(this.miMasterMind.getLinkedListEntidades()) {
				switch(c) {
				// Entidades Estaticas
				case 'V':{
					EntidadEstatica e = new Vacio();
					this.miFabricaElementos.generarEntidad(e, posX, posY, miFabricaSprites.getVacio());
					agregarEntidad(e);
					break;
				}
				case '+':{
					EntidadEstatica e = new LadrilloEspecial(new Estrella(), miFabricaSprites.getEstrella());
					this.miFabricaElementos.generarEntidad(e, posX, posY, miFabricaSprites.getLadrilloSolido());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case '/':{
					EntidadEstatica e = new LadrilloEspecial(new ChampinionVerde(), miFabricaSprites.getChampinionVerde());
					this.miFabricaElementos.generarEntidad(e, posX, posY, miFabricaSprites.getLadrilloSolido());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case '#':{
					EntidadEstatica e = new LadrilloSolido();
					this.miFabricaElementos.generarEntidad(e, posX, posY, miFabricaSprites.getLadrilloSolido());
					agregarEntidad(e);
					e.setMimapa(miMapa);
					break;
				}
				case '?':{
					EntidadEstatica e;
					e = this.miFabricaElementos.generarBloqueDePregunta(posX, posY, miFabricaSprites.getBloquePregunta(), new SuperChampinion(), miFabricaSprites.getSuperChampinion());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case '{':{
					EntidadEstatica e;
					e = this.miFabricaElementos.generarBloqueDePregunta(posX, posY, miFabricaSprites.getBloquePregunta(), new Moneda(), miFabricaSprites.getMoneda());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case '}':{
					EntidadEstatica e;
					e = this.miFabricaElementos.generarBloqueDePregunta(posX, posY, miFabricaSprites.getBloquePregunta(), new FlorDeFuego(), miFabricaSprites.getFlorDeFuego());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 't':{
					EntidadEstatica e;
					e = miFabricaElementos.generarTuberia(posX, posY, miFabricaSprites.getTuberiaMediana(),"Mediana");
					agregarEntidad(e);
					break;
				}
				case 'T':{
					EntidadEstatica e;
					e = miFabricaElementos.generarTuberia(posX, posY, miFabricaSprites.getTuberiaGrande(),"Grande");
					agregarEntidad(e);
					break;
				}
				case 'O':{
					EntidadEstatica e;
					e = this.miFabricaElementos.generarBloqueSolido(posX, posY, miFabricaSprites.getBloqueSolido());
					agregarEntidad(e);
					break;
				}

				// Entidades Dinamicas
				case 'G':{
					EntidadDinamica e = new Goomba();
					e = miFabricaElementos.generarGoomba(posX, posY, miFabricaSprites.getGoomba());
					this.agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 'B':{
					EntidadDinamica e = new BuzzyBeetle();
					e = this.miFabricaElementos.generarBuzzyBeetle( posX, posY, miFabricaSprites.getBuzzyBeetle());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 'K':{
					EntidadDinamica e;
					e = this.miFabricaElementos.generarKoopa(posX, posY, miFabricaSprites.getKoopaTroopa(), miFabricaSprites.getKoopaTroopaCaparazon());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 'L':{
					EntidadDinamica e;
					e = this.miFabricaElementos.generarLakitu(posX, posY, miFabricaSprites.getLakitu(), miFabricaSprites.getSpiny());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 'P':{
					EntidadDinamica e = new PiranhaPlant();
					e= this.miFabricaElementos.generarPiranhaPlant(posX, posY, miFabricaSprites.getPiranhaPlant());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 'S':{
					EntidadDinamica e = new Spiny();
					e = this.miFabricaElementos.generarSpiny(posX, posY, miFabricaSprites.getSpiny());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 'v':{
					EntidadDinamica e;
					e = this.miFabricaElementos.generarChampinionVerde(posX, posY, miFabricaSprites.getChampinionVerde());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 's':{
					EntidadDinamica e;
					e = this.miFabricaElementos.generarSuperChampinion(posX, posY, miFabricaSprites.getSuperChampinion());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 'm':{
					EntidadDinamica e;
					e = this.miFabricaElementos.generarMoneda(posX, posY, miFabricaSprites.getMoneda());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 'e':{
					EntidadDinamica e;
					e = this.miFabricaElementos.generarEstrella(posX, posY, miFabricaSprites.getEstrella());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				case 'f':{
					EntidadDinamica e;
					e = this.miFabricaElementos.generarFlorDeFuego(posX, posY, miFabricaSprites.getFlorDeFuego());
					agregarEntidad(e);
					e.setMasterMind(miMasterMind);
					break;
				}
				}
			}}
	}
	
	public synchronized void agregarEntidad(EntidadDinamica entidad) {
		this.miMasterMind.agregarEntidad(entidad);
	}
	
	public synchronized void agregarEntidad(EntidadEstatica entidad) {
		this.miMapa.agregarEntidad(entidad);
	}
	
	public int getNivel() {
		return this.nivel;
	}
}
