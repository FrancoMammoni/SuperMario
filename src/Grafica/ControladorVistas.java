package Grafica;

public interface ControladorVistas {
	
	public void accionarPantallaMenu();
	public void accionarPantallaRanking();
	public void accionarPantallaJuego(String skin);
	public void accionarPantallaNivel();
	public void accionarPantallaGameOver(int puntos, String resultado);
	
}
