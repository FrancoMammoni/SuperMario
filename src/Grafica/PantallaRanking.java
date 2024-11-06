package Grafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Juego.DatosJugador;
import Juego.TopPlayers;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PantallaRanking extends Pantalla {

	protected TopPlayers 			miRanking;
	protected ControladorDeVistas 	miControladordeVistas;
	protected Image 				imagenFondo;
	protected JButton 				botonMenu;

	public PantallaRanking(TopPlayers t, ControladorDeVistas c) {
		this.miRanking = t;
		this.miControladordeVistas = c;
		this.setLayout(null);
		agregarImagenFondo();
	}

	private void agregarImagenFondo() {
		try {

			this.imagenFondo = ImageIO.read(new File("src/Imagenes/fondoRanking.png"));

		} catch (IOException e) { e.printStackTrace(); }

		setPreferredSize(new Dimension(this.ALTO_PANTALLA, this.ANCHO_PANTALLA));
		setBackground(Color.black);
	}

	public void mostrarRanking() {
		try {
			
			this.miControladordeVistas.getVentana().revalidate();
			this.miControladordeVistas.getVentana().repaint();
			agregarBotonMenu();
			
		} catch (FontFormatException | IOException e) { e.printStackTrace(); }
	}

	public void paintComponent(Graphics grafico) {
		try {
			
			super.paintComponent(grafico);
			Font nesFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/nintendo-nes-font.ttf")).deriveFont(Font.BOLD, 20);
			dibujarFondo(grafico);
			grafico.setFont(nesFont);
			grafico.setColor(Color.WHITE);
			grafico.drawString("MEJORES JUGADORES: ", 300, 200);
			
			miRanking.ordenarPorPuntaje();
			
			//Se muestra por pantalla el ranking
			generarRanking(grafico);

		} catch(FontFormatException |IOException e) { e.printStackTrace(); }
	}

	private void dibujarFondo(Graphics grafico) {
		if (this.imagenFondo != null) 
			grafico.drawImage(this.imagenFondo, 0, 0, this.ALTO_PANTALLA, this.ANCHO_PANTALLA, this);
	}

	private void generarRanking(Graphics grafico) {
		int posYTexto = 300;
		int posJugadadorEnRanking = 1;
		
		for (DatosJugador jugador : miRanking.getLista()) {
			
			if (posJugadadorEnRanking > 5) 
				break; // Detiene el ciclo para solo mostrar los primero 5 jugadores
			
			String texto = posJugadadorEnRanking + ". " + jugador.getName().toUpperCase() + " - " + jugador.getPuntaje();
			grafico.drawString(texto, 370, posYTexto);
			
			posYTexto += 30;
			posJugadadorEnRanking++;
		}
	}

	private void agregarBotonMenu() throws FontFormatException, IOException {
		this.botonMenu = new JButton("MENU");
		this.botonMenu.setBounds(385, 530, 200, 40);
		decorarBoton(this.botonMenu);
		registrarListenerMenu();  
		add(this.botonMenu);
	}

	protected void decorarBoton(JButton boton) throws FontFormatException, IOException {
		Font nesFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/nintendo-nes-font.ttf")).deriveFont(Font.BOLD, 20);
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		boton.setForeground(Color.WHITE); 
		boton.setFont(nesFont);
		
		boton.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1, true), 
						BorderFactory.createEmptyBorder(5, 15, 5, 15) ));
		
		boton.setBackground(new Color(255, 255, 255, 50)); 
	}

	private void registrarListenerMenu() {
		this.botonMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// Vuelve al menú principal
				miControladordeVistas.accionarPantallaMenu();  
			}
		});
	}

	public TopPlayers getTopPlayers() {
		return this.miRanking;
	}

	@Override
	public void actualizar() {}
}
