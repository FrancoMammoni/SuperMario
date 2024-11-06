package Juego;

import java.util.Iterator;
import java.util.LinkedList;

public class Mapa {
	
	protected LinkedList<EntidadEstatica> misBloques;
	
	public Mapa() {
		this.misBloques = new LinkedList<EntidadEstatica>();
	}
	
	public synchronized void agregarEntidad(EntidadEstatica e) {
		this.misBloques.addLast(e);
	}
	
	public synchronized LinkedList<EntidadEstatica> getLinkedListEntidades(){
		return this.misBloques;
	}
	
	public synchronized void desplazamientoElementosEstaticos(int d) {
		Iterator<EntidadEstatica> it = this.misBloques.iterator();
		Entidad entidadEstatica;
		
		while(it.hasNext()) {
			entidadEstatica = it.next();
			entidadEstatica.setDesplazamientoX(d);
		}
	}
	
	public synchronized void limpiarListas() {
		this.misBloques.clear();
	}
}
