package Grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import Juego.Partida;
import Juego.TopPlayers;

public class ControladorDeVistas implements ControladorVistas {
	
	protected Partida 			partidaActual;
	protected PantallaMenu 		pantallaMenu;
	protected JFrame 			ventana;
	protected PantallaJuego 	pantallaJuego;
	protected PantallaRanking 	pantallaRanking;
	protected PantallaGameOver 	pantallaGameOver;
	protected PantallaNivel 	pantallaNivel;
	
	public ControladorDeVistas() {
		this.ventana = new JFrame();
		this.configurarVentana();
		this.pantallaMenu = new PantallaMenu(this);
		this.pantallaRanking = new PantallaRanking(new TopPlayers(),this);
		this.pantallaNivel = new PantallaNivel(this);
		//agrego listener para cuando se cierrra la pestaña
		this.ventana.addWindowListener(new WindowAdapter() {
		       @Override
		       public void windowClosing(WindowEvent e) {
		        guardarDatos();				// Llamada al método para guardar datos antes de cerrar
		           System.exit(0);  // Cierra la aplicación después de guardar
		       }
		   });
		configurarVentana();
	}
	
	@Override
	public void accionarPantallaMenu() {
		this.ventana.getContentPane().removeAll();
		this.ventana.revalidate();  // Reorganiza los componentes
		this.ventana.repaint();     // Vuelve a pintar la ventana
		this.pantallaMenu.setLayout(new BorderLayout());
		this.ventana.add(pantallaMenu);
		this.pantallaMenu.setVisible(true);
		this.ventana.pack();
		this.ventana.setVisible(true);
		this.pantallaMenu.requestFocusInWindow();
	}
	
	@Override
	public void accionarPantallaRanking() {
		this.ventana.getContentPane().removeAll();
		this.ventana.add(this.pantallaRanking);
		this.ventana.pack();
		this.ventana.setLocationRelativeTo(null); // Centra la ventana en la pantalla
		this.ventana.setVisible(true);
		this.pantallaRanking.mostrarRanking();
	}
	
	@Override
	public void accionarPantallaJuego(String skin) {
		this.pantallaJuego = new PantallaJuego(this, skin);
	    this.ventana.getContentPane().removeAll();
	    // Configura el layout principal de la ventana
	    this.ventana.setLayout(new BorderLayout());
	    // Configura la pantallaJuego 
	    this.pantallaJuego.setPreferredSize(new Dimension(960, 672));   
	    //se añade el panel a la ventana
	    this.ventana.add(this.pantallaJuego);
	    // Ajusta el tamaño de la ventana y otros detalles
	    this.ventana.pack();
	    this.ventana.setLocationRelativeTo(null); // Centra la ventana en la pantalla
	    this.ventana.setVisible(true);
	    this.pantallaJuego.requestFocusInWindow();
	}
	
	public void reanudarPantallaJuego() {
		this.ventana.getContentPane().removeAll();
	    // Configura el layout principal de la ventana
	    this.ventana.setLayout(new BorderLayout());
	    // Configura la pantallaJuego 
	    this.pantallaJuego.setPreferredSize(new Dimension(960, 672));   
	    //se añade el panel a la ventana
	    this.ventana.add(this.pantallaJuego);
	    // Ajusta el tamaño de la ventana y otros detalles
	    this.ventana.pack();
	    this.ventana.setLocationRelativeTo(null); // Centra la ventana en la pantalla
	    this.ventana.setVisible(true);
	}
	
	public void actualizar() {	
		this.pantallaJuego.actualizar();
		this.pantallaJuego.repaint();
	}
	
	private void configurarVentana() {
		this.ventana.setSize(960,752); //Esto centra el frame 
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ventana.setResizable(false);
		this.ventana.setTitle("SuperMarioBros");
		this.ventana.setLocationRelativeTo(null);
		this.ventana.setVisible(true);
		this.ventana.pack();
		Image icono = Toolkit.getDefaultToolkit().getImage("src/Imagenes/mario_normal_quieto.png");
		this.ventana.setIconImage(icono);
	}
	
	@Override
	public void accionarPantallaGameOver(int puntos, String resultado) {
		this.ventana.getContentPane().removeAll();
	    // Asegurarse de que la ventana se actualice y repinte correctamente
	    this.ventana.revalidate();  // Reorganiza los componentes
	    this.ventana.repaint();     // Vuelve a pintar la ventana
		this.pantallaGameOver = new PantallaGameOver(this, puntos, resultado);
		this.pantallaGameOver.setLayout(new BorderLayout());
		this.ventana.add(this.pantallaGameOver);
		this.pantallaGameOver.requestFocusInWindow();
		this.pantallaGameOver.setVisible(true);
		this.ventana.pack();	
	}
	
	@Override
	public void accionarPantallaNivel() {
		this.ventana.getContentPane().removeAll();
	    this.ventana.revalidate();  
	    this.ventana.repaint();    
		this.pantallaNivel.setLayout(new BorderLayout());
		this.ventana.add(this.pantallaNivel);
		this.pantallaNivel.requestFocusInWindow();
		this.pantallaNivel.setVisible(true);
		this.ventana.pack();
	}
	
	private void guardarDatos() {
		this.pantallaRanking.getTopPlayers().guardarDatos();
	}
	
	public PantallaRanking getPantallaRanking() {
		return this.pantallaRanking;
	}

	public Partida getPartida() {
		return this.partidaActual;
	}
	
	public void setPartida(Partida p) {
		this.partidaActual = p;
	}

	public JFrame getVentana() {
		return this.ventana;
	}
}
