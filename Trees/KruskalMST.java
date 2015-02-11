package jagan.trees;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import jagan.utils.UnionFind1;

/**
 * @Time-complexity O(ElogE) for sorting edges and O(ElogV) for Union if UnionFind2 is used.<br/> Total time complexity is O(ElogE+ElogV).
 * 					Overall time complexity is O(ElogE) or O(ElogV) since E can be utmost V^2 for complete graph
 * @author Artist
 *
 */
public class KruskalMST {
	
	//Check whether needed or not
	//private Vertex[] vertices;
	private int noOfVertices;
	
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Edge> minSpanningTree = new ArrayList<Edge>();
	private UnionFind1 cycle;
	
	public KruskalMST(int noOfVertices){
		//vertices = (Vertex[])Array.newInstance(Vertex.class, noOfVertices);
		this.noOfVertices = noOfVertices;
		cycle = new UnionFind1(noOfVertices);
	}
	
	public void addEdge(int src,int dest, int weight){
		edges.add(new Edge(src, dest, weight));
	}
	
	/**
	 * 
	 * @return minimum spanning tree using kruskal's algorithm
	 */
	public ArrayList<Edge> minimumSpanningTree(){
		
		int noOfEdges = 0;
		edges.sort(new EdgeCompare());
		Iterator<Edge> edge = edges.iterator();
		while(noOfEdges < noOfVertices-1 && edge.hasNext()){
			Edge e = edge.next();
			if(!cycle.union(e)){
				minSpanningTree.add(e);
				++noOfEdges;
			}
		}
		
		return minSpanningTree;
	}
	
	/**
	 * Prints minimum spanning tree's edges
	 */
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
