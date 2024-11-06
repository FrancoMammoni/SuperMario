package Grafica;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Pantalla extends JPanel {
	protected final int 		SIZE_ORIGINAL_BALDOZA = 16;
    protected final int 		ESCALA = 3;
	protected final int 		SIZE_BALDOZA = ESCALA * SIZE_ORIGINAL_BALDOZA;
	protected final int 		MAX_SIZE_COLUMNA = 20;
	protected final int 		MAX_SIZE_FILA = 14;
    protected final int 		ANCHO_PANTALLA = SIZE_BALDOZA * MAX_SIZE_FILA;
	protected final int 		ALTO_PANTALLA = SIZE_BALDOZA * MAX_SIZE_COLUMNA;
	protected final int			BORDE_PANTALLA_DERECHO =960;
	public abstract void actualizar();

	public  void paintComponent(Graphics grafico) {
		super.paintComponent(grafico);
	}
}