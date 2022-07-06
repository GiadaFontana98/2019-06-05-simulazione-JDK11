package it.polito.tdp.crimes.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;




public class Model {
	
	EventsDao dao ;
	Graph<Adiacenza,DefaultWeightedEdge> grafo;
    private List<Adiacenza>adiacenze;
    private List<Integer>distretti;
	
	public Model()
	{
		dao = new EventsDao();
		
	}
	public List<Integer>getDistretti()
	{
		this.distretti= dao.distretti();
		return distretti;
		
	}
	public List<Integer>getAnni()
	{
		return dao.listAllAnni();
	}
	public List<Integer>getMesi()
	{
		return dao.listAllMese();
	}
	public List<Integer>getGiorno()
	{
		return dao.listAllgiorno();
	}
	List<Integer>id= new LinkedList<>();
	
	public List<Adiacenza>getVertici(int anno)
	{
	     return dao.vertici(anno);
	}
		
	public String creaGrafo(int anno)
	{
		grafo= new SimpleWeightedGraph<Adiacenza,DefaultWeightedEdge>(DefaultWeightedEdge.class);
	
	    
		Graphs.addAllVertices(grafo,getVertici(anno));
		
		for(Adiacenza c1 : dao.vertici(anno)) {
			for(Adiacenza c2 : dao.vertici(anno)) {
				if(!c1.equals(c2)) {
					double peso = LatLngTool.distance(c1.getCentro(), c2.getCentro(), LengthUnit.KILOMETER);
					Graphs.addEdge(grafo, c1, c2, peso);
				}
			}
		}
		
		
		
        return "#Archi =" + grafo.edgeSet().size()  + "Vertici" + grafo.vertexSet().size();
	}
	
	public List<Vicini>getVicini(Adiacenza a)
	{
		List<Vicini>result= new ArrayList<>();
		List<Adiacenza>vicinivicini=Graphs.neighborListOf(grafo, a);
		for(Adiacenza aa : vicinivicini)
		{
			result.add(new Vicini(aa.getDistretto(), 
					this.grafo.getEdgeWeight(this.grafo.getEdge(a, aa)))) ;
		}
		
		Collections.sort(result);
		return result;
	}
}
