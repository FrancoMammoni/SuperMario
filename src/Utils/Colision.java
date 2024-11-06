package Utils;

import java.util.Iterator;
import Enemigos.KoopaTroopa;
import Juego.EntidadDinamica;
import Juego.EntidadEstatica;
import Juego.Jugador;
import Juego.Mapa;
import Juego.MasterMind;
import Mario.BolaDeFuego;


public class Colision implements Runnable {
	
	protected Jugador    miJugador;
	protected MasterMind miMastermind;
	protected Mapa       miMapa;
	
	public Colision(MasterMind mm, Mapa mapa, Jugador j) {
		 this.miJugador=j;
		 this.miMastermind= mm;
		 this.miMapa= mapa;
		
	}
	 
	public void chequearAdyacenciaJugador() {
		 
		 chequearAdyacenciaEstatica(miJugador);
		 EntidadDinamica entidad = chequearAdyacenciaDinamica();
		 if(entidad!=null) {
			 entidad.accept(miJugador.getVisitor());
		 }
	}
	 
	public void chequearAdyacenciaEstatica(EntidadDinamica e) {
		 Iterator<EntidadEstatica> itBloques= miMapa.getLinkedListEntidades().iterator();
		 EntidadEstatica miBloque = null;
		 boolean encontreColision=false;
		 while(itBloques.hasNext() && !encontreColision) {
			 miBloque= itBloques.next();
			 encontreColision= e.getHitbox().intersects(miBloque.getHitbox());
		 }
		    
		 if (encontreColision) {
			    String lugar = "";
			    
			    // Calcular las diferencias entre las hitboxes
			    int diffTop = Math.abs(e.getHitbox().y + e.getHitbox().height - miBloque.getHitbox().y);
			    int diffBottom = Math.abs(e.getHitbox().y - (miBloque.getHitbox().y + miBloque.getHitbox().height));
			    int diffLeft = Math.abs(e.getHitbox().x + e.getHitbox().width - miBloque.getHitbox().x);
			    int diffRight = Math.abs(e.getHitbox().x - (miBloque.getHitbox().x + miBloque.getHitbox().width));
			    
			    // Encontrar la menor diferencia
			    int minDiff = Math.min(Math.min(diffTop, diffBottom), Math.min(diffLeft, diffRight));
			    
			    // Determinar la dirección de la colisión
			    if (minDiff == diffLeft && e.getVelocidad() >= 0) {
			        lugar = "derecha";
			    }else if (minDiff == diffRight && e.getVelocidad() >= 0) {
			        lugar = "izquierda";
			    }else  if (minDiff == diffTop && miJugador.getVelocidadY() >= 0 ) {
			        lugar = "abajo";
			    	}
			    else if (minDiff == diffBottom && miJugador.getVelocidadY() < 0) {
			        lugar = "arriba";
			    }
			    if (lugar.isEmpty() && Math.abs(diffLeft - diffRight) < 5 && miJugador.getVelocidadY() > 0) {
			        lugar = "abajo";
			    }
			    
			    // Llamar al método de colisión si hay una dirección válida
			    if (!lugar.isEmpty()) {
			        e.colision(lugar, miBloque);
			       
			    }
		 }else {
			 miJugador.chequearYActualizarSuelo();
		 }
	}
	
	public void chequearAdyacenciaDinamicaDeEntidad() {
		for(EntidadDinamica e:this.miMastermind.getLinkedListEntidades()) {
			chequearAdyacenciaDinamicaConBolasDeFuego(e);
			chequearAdyacenciaDinamicaConCaparazones(e);
		}
	}
	
	public void chequearAdyacenciaDinamicaConBolasDeFuego(EntidadDinamica ed) {
	 	for(BolaDeFuego bf:this.miMastermind.getLinkedListBolasDeFuego()) {
	 			if(ed.getHitbox().intersects(bf.getHitbox())) {
	 				ed.serGolpeadoBolaDeFuego(bf);
	 				break;
	 			}
	 	}
	}
	
	public void chequearAdyacenciaDinamicaConCaparazones(EntidadDinamica ed) {
	 	for(KoopaTroopa kt:this.miMastermind.getLinkedListCaparazones()) {
	 			if(ed.getHitbox().intersects(kt.getHitbox())) {
	 				kt.afectarEntidad(ed);
	 				break;
	 			}
	 	}
	}
	
	public void chequearAdyacenciaEstaticaConEntidad(EntidadDinamica e) {
		Iterator<EntidadEstatica> itBloques= miMapa.getLinkedListEntidades().iterator();
		 EntidadEstatica miBloque = null;
		 boolean encontreColision=false;
		 while(itBloques.hasNext() && !encontreColision) {
			 miBloque= itBloques.next();
			 encontreColision= e.getHitbox().intersects(miBloque.getHitbox());
		 }
		 
		    
		 if (encontreColision) {
			    String lugar = "";
			    
			    // Calcular las diferencias entre las hitboxes
			    int diffTop = Math.abs(e.getHitbox().y + e.getHitbox().height - miBloque.getHitbox().y);
			    int diffBottom = Math.abs(e.getHitbox().y - (miBloque.getHitbox().y + miBloque.getHitbox().height));
			    int diffLeft = Math.abs(e.getHitbox().x + e.getHitbox().width - miBloque.getHitbox().x);
			    int diffRight = Math.abs(e.getHitbox().x - (miBloque.getHitbox().x + miBloque.getHitbox().width));
			    
			    // Encontrar la menor diferencia
			    int minDiff = Math.min(Math.min(diffTop, diffBottom), Math.min(diffLeft, diffRight));
			    
			    // Determinar la dirección de la colisión
			    if (minDiff == diffLeft && e.getVelocidad() >= 0) {
			        lugar = "derecha";
			    }else if (minDiff == diffRight && e.getVelocidad() >= 0) {
			        lugar = "izquierda";
			    }else  if (minDiff == diffTop && miJugador.getVelocidadY() >= 0 ) {
			        lugar = "abajo";
			    	}
			    else if (minDiff == diffBottom && miJugador.getVelocidadY() < 0) {
			        lugar = "arriba";
			    }
			    if (lugar.isEmpty() && Math.abs(diffLeft - diffRight) < 5 && miJugador.getVelocidadY() > 0) {
			        lugar = "abajo";
			    }
			    
			    // Llamar al método de colisión si hay una dirección válida
			    if (!lugar.isEmpty()) {
			        e.colision(lugar, miBloque);
			    }
		 }else
			    	e.caer();
		
	}
	
	public void chequearAdyacenciaEstaticaDeEntidades() {
		chequeoDinamicasConEstaticas();
		chequeoBolasDeFuegoConEstaticas();
	}
	 
	private void chequeoDinamicasConEstaticas() {
		Iterator<EntidadDinamica> it = this.miMastermind.getLinkedListEntidades().iterator();
		while(it.hasNext()) {
			chequearAdyacenciaEstaticaConEntidad(it.next());
		}
	}
	
	private void chequeoBolasDeFuegoConEstaticas() {
		Iterator<BolaDeFuego> bf = this.miMastermind.getLinkedListBolasDeFuego().iterator();
		while(bf.hasNext()) {
			chequearAdyacenciaEstaticaConEntidad(bf.next());
		}
	}
	
	public EntidadDinamica chequearAdyacenciaDinamica() {
	 	
	 	EntidadDinamica entidadD=null;
	 	
	 	for(EntidadDinamica e:this.miMastermind.getLinkedListEntidades()) {
	 		if(miJugador.getHitbox().intersects(e.getHitbox()) && !miJugador.getColisionando()) {
	 			entidadD=e;
	 			break;
	 		}
	 	}
		return entidadD;
	 }

	@Override
	public void run() {
		
	}
}
