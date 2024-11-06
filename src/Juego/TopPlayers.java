package Juego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class TopPlayers implements Serializable {

	protected List<DatosJugador> ranking;

	public TopPlayers() {
		this.ranking = new ArrayList<DatosJugador>();
		cargarDatos();
	}

	public void addJugador(DatosJugador d) {
		this.ranking.add(d);
	}

	public void ordenarPorPuntaje() {
		// Ordena la lista en orden descendente de puntos
		Collections.sort(this.ranking, (jugador1, jugador2) -> Integer.compare(jugador2.getPuntaje(), jugador1.getPuntaje()));
	}

	public void guardarDatos() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("jugadores.txt"))) {
			
			for (DatosJugador jugador : this.ranking) {
				String linea = jugador.getName() + "," + jugador.getPuntaje();
				writer.println(linea);
			}
			
		} catch (IOException e) { e.printStackTrace(); }
	} 

	public void cargarDatos() {
		// Limpia la lista actual antes de cargar nuevos datos
		this.ranking.clear(); 
		
		try (BufferedReader reader = new BufferedReader(new FileReader("jugadores.txt"))) {
			
			String linea, nombre;
			int puntos;
			
			while ((linea = reader.readLine()) != null) {
				
				String[] partes = linea.split(",");
				nombre = partes[0];
				puntos = Integer.parseInt(partes[1]);

				// Creo un objeto DatosJugador con los datos leidos
				DatosJugador jugador = new DatosJugador(nombre, puntos);
				this.ranking.add(jugador);
			}

		} catch (IOException e) { e.printStackTrace(); }
	}
	
	//Getters
	public List<DatosJugador> getLista(){
		return this.ranking;
	}
}