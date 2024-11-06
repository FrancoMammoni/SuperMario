package Grafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class PantallaNivel extends Pantalla {

	protected ControladorDeVistas miControlador;
	protected Image 			  fondo;

	public PantallaNivel(ControladorDeVistas contralador) {
		this.miControlador = contralador;
		agregarImagenFondo();
	}

	private void agregarImagenFondo() {
		try {

			this.fondo = ImageIO.read(new File("src/Imagenes/fondoRanking.png"));

		} catch (IOException e) { e.printStackTrace(); }

		setPreferredSize(new Dimension(this.ALTO_PANTALLA, this.ANCHO_PANTALLA));
		setBackground(Color.black);
	}

	public void paintComponent(Graphics grafico) {
		try {
			super.paintComponent(grafico);

			if (this.fondo != null) 
				grafico.drawImage(this.fondo, 0, 0, this.ALTO_PANTALLA, this.ANCHO_PANTALLA, this);

			Font fuente;
			fuente = Font.createFont(Font.TRUETYPE_FONT, new File("src/nintendo-nes-font.ttf")).deriveFont(Font.BOLD, 70);
			grafico.setFont(fuente);
			grafico.setColor(Color.WHITE);

			grafico.drawString("NIVEL " + (this.miControlador.getPartida().getParser().getNivel() + 2), 250, 300);

		} catch (FontFormatException | IOException e) { e.printStackTrace(); }
	}

	@Override
	public void actualizar() {}
}
