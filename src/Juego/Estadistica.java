package Juego;

public class Estadistica {

	protected int 		puntos;
	protected int 		vida;
	protected int 		cantMonedas;
	protected int 		nivel;
	protected Partida	miPartida;
	
	public Estadistica(Partida p) {
		this.puntos = 0;
		this.vida = 3;
		this.cantMonedas = 0;
		this.nivel = 1;
		this.miPartida = p;
	}
	
	public void aumentarPuntos(int p) {
		this.puntos += p;
	}
	
	public void sacarPuntos(int p) {
		this.puntos -= p;
	}
	
	public void sacarVida() {
		if(this.vida > 1) {
			this.vida--;
			this.miPartida.reiniciarNivel();
		} 
		else {
			this.miPartida.gameOver();
		}
	}
	
	public void aumentarVidas() {
		this.vida++;
	}
	
	public void aumentarCantMonedas() {
		this.cantMonedas++;
	}
	
	//Setters
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	//Getters
	
	public int getNivel() {
		return this.nivel;
	}
	
	public int getCantMonedas() {
		return this.cantMonedas;
	}
	
	public int getVida() {
		return this.vida;
	}
	
	public int getPuntos() {
		return this.puntos;
	}
}