package Juego;
import java.util.Iterator;
import java.util.LinkedList;

import Enemigos.KoopaTroopa;
import Enemigos.Spiny;
import Mario.BolaDeFuego;

public class MasterMind {

	protected Partida 						miPartida;
	protected LinkedList<EntidadDinamica> 	misEntidades;
	protected LinkedList<BolaDeFuego> 		misBolasDeFuego;
	protected LinkedList<KoopaTroopa> 		misCaparazones;
	protected LinkedList<Spiny> 			aAgregar;

	public MasterMind(Partida p) {
		this.misEntidades = new LinkedList<EntidadDinamica>();
		this.misBolasDeFuego = new LinkedList<BolaDeFuego>();
		this.misCaparazones = new LinkedList<KoopaTroopa>();
		this.aAgregar = new LinkedList<Spiny>();
		this.miPartida = p;
	}

	public synchronized void agregarEntidad(EntidadDinamica e) {
		this.misEntidades.addLast(e);
	}

	public synchronized void agregarBolaDeFuego(BolaDeFuego bf) {
		this.misBolasDeFuego.addLast(bf);
	}

	public synchronized void agregarCaparazon(KoopaTroopa kt) {
		this.misCaparazones.addLast(kt);
	}

	public synchronized void desplazamientoElementosDinamicos(int d) {
		Iterator<EntidadDinamica> it = this.misEntidades.iterator();
		Entidad EntidadDinamica;

		while(it.hasNext()) {
			EntidadDinamica = it.next();
			EntidadDinamica.setDesplazamientoX(d);
		}
	}

	public synchronized void moverEntidades() {

		Iterator<EntidadDinamica> iterator = this.misEntidades.iterator();
		while (iterator.hasNext()) {
			EntidadDinamica entidad = iterator.next();

			if (entidad.getEliminar()) 
				iterator.remove();    
			else 
				entidad.moverse(); 
		}

		Iterator<BolaDeFuego> itBf=this.misBolasDeFuego.iterator();
		while (itBf.hasNext()) {
			EntidadDinamica entidad = itBf.next();

			if (entidad.getEliminar()) 
				itBf.remove();
			else 
				entidad.moverse();
		}
	}

	public synchronized void agregarEntidades() {
		Iterator<Spiny> it = this.aAgregar.iterator();

		while(it.hasNext()) {
			this.misEntidades.addLast(it.next());
		}

		this.aAgregar.clear();
	}

	public synchronized void limpiarListas() {
		this.misEntidades.clear();
		this.misBolasDeFuego.clear();
		this.misCaparazones.clear();
	}

	//Setters

	//Getters
	public Partida getMiPartida() {
		return this.miPartida;
	}
	public LinkedList<EntidadDinamica> getLinkedListEntidades(){
		return this.misEntidades;
	}

	public LinkedList<BolaDeFuego> getLinkedListBolasDeFuego(){
		return this.misBolasDeFuego;
	}

	public LinkedList<KoopaTroopa> getLinkedListCaparazones(){
		return this.misCaparazones;
	}

	public LinkedList<Spiny> getLinkedListAAgregar() {
		return this.aAgregar;
	}

}