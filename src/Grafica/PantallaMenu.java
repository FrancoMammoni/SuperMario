package Grafica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PantallaMenu extends Pantalla{

	private   ControladorDeVistas controladorVistas;
	protected JButton 			botonJugar;
	protected JButton 			botonRanking;
	protected JButton 			botonSkin;
	protected ImageIcon 		imagenFondo;
	protected String 			modo;

	public PantallaMenu(ControladorDeVistas controladorvistas) {
		try {

			this.modo = "NORMAL";
			this.controladorVistas = controladorvistas;
			
			setSize(this.ANCHO_PANTALLA, this.ALTO_PANTALLA);
			setLayout(null);
			agregarFondo(this.modo);
			agregarBotonIniciar();
			agregarBotonRanking();
			agregarBotonSkin();

		} catch(IOException e) {}
	}

	private void agregarFondo(String modo) throws IOException {

		if(modo.equals("NORMAL")) 
			this.imagenFondo = new ImageIcon("src/Imagenes/mario_fondo_menu.png");
		else
			this.imagenFondo = new ImageIcon("src/ImagenesSkinMinecraft/mario_fondo_menu.gif");

		setPreferredSize(new Dimension(this.ALTO_PANTALLA, this.ANCHO_PANTALLA));
		setBackground(Color.black);
		this.repaint();
	}

	private void agregarBotonIniciar() {
		this.botonJugar= new JButton();
		this.botonJugar.setBounds(410, 425, 150, 40);
		this.botonJugar= new JButton();
		decorarBotonJuego();
		registrarListenerJugar();
		transparentar_boton(this.botonJugar);
		add(this.botonJugar);
	}

	private void agregarBotonRanking() {
		this.botonRanking= new JButton();
		this.botonRanking.setBounds(385, 480, 200, 40);
		registrarListenerRanking();
		transparentar_boton(this.botonRanking);
		this.add(this.botonRanking);
	}

	private void agregarBotonSkin() {
		this.botonSkin= new JButton();
		this.botonSkin.setBounds(475, 525, 40, 40);
		registrarListenerSkin();
		transparentar_boton(this.botonSkin);
		this.add(this.botonSkin);
	}

	private void registrarListenerRanking() {
		this.botonRanking.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controladorVistas.accionarPantallaRanking();
			}
		});
	}

	private void registrarListenerJugar() {
		this.botonJugar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controladorVistas.accionarPantallaJuego(modo);
			}
		});
	}

	private void registrarListenerSkin() {
		this.botonSkin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cambiarModo();
				try {
					agregarFondo(modo);
				} catch (IOException e1) {}
			}
		});
	}

	private void cambiarModo() {
		
		if(this.modo.equals("NORMAL"))
			this.modo ="MINECRAFT";
		else
			this.modo = "NORMAL";
		
	}

	protected void transparentar_boton(JButton boton) {
		boton.setOpaque(true);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}

	public void paintComponent(Graphics grafico) {
		super.paintComponent(grafico);
		
		if(this.imagenFondo != null) {
			Image imagenF = imagenFondo.getImage();
			grafico.drawImage(imagenF, 0, 0, this.ALTO_PANTALLA, this.ANCHO_PANTALLA, this);
		}
		
		grafico.dispose(); 
	}

	private void decorarBotonJuego() {
		this.botonJugar.setBounds(410, 425, 150, 40);
	}
	
	@Override
	public void actualizar() {}
}