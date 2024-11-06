package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import Juego.DatosJugador;
import Sonidos.ReproductorSonido;
import Sonidos.Sonido;

@SuppressWarnings("serial")
public class PantallaGameOver extends Pantalla {
	protected ControladorDeVistas miControlador;
	protected Image 			  fondoGameOver;
	protected int 				  puntaje;
	protected JButton 			  botonSalir;
	protected JButton 			  botonMenu;
	protected JButton 			  botonRegistrarMarca;
	protected String 		      resultado;
	protected Sonido 			  sonidos;
	protected ReproductorSonido   repMusicaFondo;

	public PantallaGameOver(ControladorDeVistas c, int i, String res) {
		try {
			this.sonidos = new Sonido();
			this.resultado = res;
			this.puntaje = i;
			this.miControlador = c;
			this.setSize(this.ANCHO_PANTALLA, this.ALTO_PANTALLA);
			this.setLayout(null);
			this.agregarImagenFondo();
			this.agregarBotonSalir();
			this.agregarBotonMenu();
			this.agregarBotonRegistrarMarca();
			if(this.resultado == "Ganar") {
				this.repMusicaFondo = new ReproductorSonido(6, this.sonidos);
			}else{
				this.repMusicaFondo = new ReproductorSonido(7, this.sonidos);
			}	
			this.repMusicaFondo.reproducir();
		}catch(FontFormatException | IOException e) {}
	}

	private void agregarImagenFondo() {
		try {
			if(this.resultado == "Perder") {
				this.fondoGameOver = ImageIO.read(new File("src/Imagenes/fondoGameOver.jpg"));
			}
			else if(this.resultado == "Ganar") {
				this.fondoGameOver = ImageIO.read(new File("src/Imagenes/fondoVictoria.png"));
			}
		} catch (IOException e) { e.printStackTrace(); }
		setPreferredSize(new Dimension(this.ALTO_PANTALLA, this.ANCHO_PANTALLA));
		setBackground(Color.black);
	}

	private void agregarBotonSalir() throws FontFormatException, IOException {
		this.botonSalir = new JButton("SALIR");
		this.botonSalir.setBounds(405, 580, 150, 40);
		decorarBoton(this.botonSalir);
		registrarListenerSalir();  
		add(this.botonSalir);
	}

	private void agregarBotonRegistrarMarca() throws FontFormatException, IOException {
		this.botonRegistrarMarca= new JButton("REGISTRAR MARCA");
		this.botonRegistrarMarca.setBounds(280, 480, 400, 40);
		decorarBoton(this.botonRegistrarMarca);
		registrarListenerMarca();  
		add(this.botonRegistrarMarca);
	}

	private void registrarListenerMarca() {
		this.botonRegistrarMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = "";
				boolean nombreValido = false;
				while (!nombreValido) {
					nombre = JOptionPane.showInputDialog(null, "Ingresa tu nombre (exactamente 3 caracteres):");
					if (nombre == null) {
						return;  // Salir del método si se cancela
					}
					if (nombre.length() == 3) {
						nombreValido = true;
					}
					else {
						mostrarErrorNombreInvalido();
					}
				}
				// Guardar los datos en el TopPlayers.
				miControlador.getPantallaRanking().getTopPlayers().addJugador(new DatosJugador(nombre, miControlador.getPartida().getEstadistica().getPuntos() ));
				System.out.println("Nombre registrado: " + nombre);
				botonRegistrarMarca.setEnabled(false);
			}
		});
	}

	private void mostrarErrorNombreInvalido() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel mensaje = new JLabel("<html><body><p style='text-align:center;'>El nombre debe tener exactamente 3 caracteres.</p></body></html>");
		JLabel icono = new JLabel(UIManager.getIcon("OptionPane.warningIcon"));
		mensaje.setFont(new Font("Arial", Font.BOLD, 14));
		mensaje.setForeground(Color.RED);
		panel.add(icono, BorderLayout.WEST);       // Icono a la izquierda
		panel.add(mensaje, BorderLayout.CENTER);   // Mensaje en el centro
		JOptionPane.showMessageDialog(null, panel, "Error en el Nombre", JOptionPane.ERROR_MESSAGE);
	}


	private void agregarBotonMenu() throws FontFormatException, IOException {
		this.botonMenu = new JButton("MENU");
		this.botonMenu.setBounds(380, 530, 200, 40);
		decorarBoton(this.botonMenu);
		registrarListenerMenu();
		add(this.botonMenu);
	}

	protected void decorarBoton(JButton boton) throws FontFormatException, IOException {
		Font nesFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/nintendo-nes-font.ttf")).deriveFont(Font.BOLD, 20);
		boton.setFont(nesFont);
		boton.setOpaque(true);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		boton.setForeground(Color.WHITE); 
		boton.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1, true), 
				BorderFactory.createEmptyBorder(5, 15, 5, 15) ));
	}

	public void paintComponent(Graphics grafico) {
		try {
			super.paintComponent(grafico);
			if (this.fondoGameOver != null) {
				grafico.drawImage(this.fondoGameOver, 0, 0, this.ALTO_PANTALLA, this.ANCHO_PANTALLA, this);
			}
			Font fuente;
			fuente = Font.createFont(Font.TRUETYPE_FONT, new File("src/nintendo-nes-font.ttf")).deriveFont(Font.BOLD, 20);
			grafico.setFont(fuente);
			grafico.setColor(Color.WHITE);
			grafico.drawString("PUNTAJE: " + puntaje, 350, 60);
		} catch (FontFormatException | IOException e) { e.printStackTrace(); }
	}

	private void registrarListenerSalir() {
		this.botonSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				miControlador.getPantallaRanking().getTopPlayers().guardarDatos();
				System.exit(0);  // Sale del juego
			}
		});
	}

	private void registrarListenerMenu() {
		this.botonMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				miControlador.accionarPantallaMenu();  // Vuelve al menú principal
			}
		});
	}

	@Override
	public void actualizar() {}
}

