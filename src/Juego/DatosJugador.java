package Juego;

import java.io.Serializable;

public class DatosJugador implements Serializable {
	
	protected String name;
	protected int    puntaje;
	
	public DatosJugador(String n, int p) {
		this.name = n;
		this.puntaje = p;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
	
	public String getName() {
		return this.name;
	}


}
