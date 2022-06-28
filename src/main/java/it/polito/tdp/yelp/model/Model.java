package it.polito.tdp.yelp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


public class Model {
	
	/**
	 * CONTROLLO INSERIMETO CMB
	 */
	public void controlloInserimento() {
		Track c = this.cmbCanzone.getValue();
		if(c == null) {
			txtResult.appendText("Seleziona una canzone!");
			return;
		}
	}
	
	/**
	 * CONTROLLO INSERIMENTO NUMERICO
	 */
	public void controlloInserimentoNumerico() {
		int x;
		
		try {
//			x = Integer.parseInt(cmbBox.getText());
		} catch(NumberFormatException e) {
//			txtResult.appendText("Inserire valore numerico");
			return;
		}
	}
	
	/**
	 * RIORDINARE UNA LISTA
	 * !! AGGIUNGERE implements Comparable<Airport> NELLA CLASSE DELL'OGGETTO DA ORDINARE
	 * @return
	 */
	public List<Airport> getVertici() {
		List<Airport> vertici = new ArrayList<>(this.grafo.vertexSet());
		Collections.sort(vertici);
		return vertici;
	}
	
	/**
	 * CREAZIONE GRAFO
	 * @param genere
	 */
	public void creaGrafo(Genre genere) {
		// CREO IL GRAFO
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// AGGIUNGO I VERTICI
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(genere, idMap));
		
		// AGGIUNGO GLI ARCHI
		for(Adiacenza a : this.dao.getArchi(genere, idMap)) {
			Graphs.addEdgeWithVertices(this.grafo, a.getT1(), a.getT2(), a.getPeso());
		}
		
		System.out.println("Grafo Creato!");
		System.out.println(String.format("# VERTICI: %d", this.grafo.vertexSet().size()));
		System.out.println(String.format("# ARCHI: %d", this.grafo.edgeSet().size()));
	}
	
	
}

