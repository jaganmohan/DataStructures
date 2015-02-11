package jagan.trees;

import java.util.ArrayList;
import java.util.Iterator;

import jagan.utils.UnionFind2;

public class PrimsMST {
	
	private int noOfVertices;
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Edge> minSpanningTree = new ArrayList<Edge>();
	private UnionFind2 cycle;
	
	public PrimsMST(int vertices){
		noOfVertices = vertices;
		cycle = new UnionFind2(vertices);
	}
	
	public void addEdge(int src, int dest, int weight){
		edges.add(new Edge(src, dest, weight));
	}
	
	public ArrayList<Edge> minimumSpanningTree(){
		
		ArrayList<Integer> verticesIncluded = new ArrayList<Integer>(); 
		ArrayList<Integer> verticesNotIncluded = new ArrayList<Integer>();
		ArrayList<Edge> edgeTemp = (ArrayList<Edge>) edges.clone();
		verticesIncluded.add(0);
		for(int i = 1; i < noOfVertices; i++)
			verticesNotIncluded.add(i);
		
		Iterator<Integer> verIncluded = verticesIncluded.iterator();
		Iterator<Edge> edge = edges.iterator();
		int minWeight;
		Edge minEdge = null;
		
		while(!verticesNotIncluded.isEmpty()){
			minWeight = 68000;
			verIncluded = verticesIncluded.iterator();
			while(verIncluded.hasNext()){
				int vertex = verIncluded.next();
				edge = edgeTemp.iterator();
				while(edge.hasNext()){
					Edge e = edge.next();
					if((e.getSource() == vertex || e.getDest() == vertex) && e.getWeight() < minWeight){
						minWeight = e.getWeight();
						minEdge = e;
					}
				}
			}

			if(minEdge != null && !cycle.union(minEdge) && 
					(!verticesIncluded.contains(minEdge.getDest()) || !verticesIncluded.contains(minEdge.getSource()))){
				int vertex;
				if(!verticesIncluded.contains(minEdge.getDest())){
					vertex = minEdge.getDest();
				}else{
					vertex = minEdge.getSource();
				}
				verticesNotIncluded.remove(verticesNotIncluded.indexOf(vertex));
				verticesIncluded.add(vertex);
				minSpanningTree.add(minEdge);
				
			}
			edgeTemp.remove(minEdge);
		}
		
		return minSpanningTree;
	}
	
	public void printMinSpanningTree(){
		Iterator<Edge> edge = minSpanningTree.iterator();
		int totalWeight = 0;
		while(edge.hasNext()){
			Edge e = edge.next();
			totalWeight += e.getWeight();
			System.out.println(e);
		}
		System.out.println("Total weight of the minimum spanning tree formed = " + totalWeight);
	}
	
}
