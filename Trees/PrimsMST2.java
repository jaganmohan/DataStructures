package jagan.trees;

import java.lang.reflect.Array;

import jagan.utils.EdgeHeapify;
import jagan.utils.UnionFind2;

import java.util.ArrayList;
import java.util.Iterator;

public class PrimsMST2 {

	private int noOfVertices;
	private boolean[] verticesIncluded;
	private int[] makeSetKeys;
	private Vertex[] vertices;
	private int[][] edgeCost;
	private int[] parent;
	private EdgeHeapify heap;
	private UnionFind2 cycle;
	private ArrayList<Edge> mstEdges = new ArrayList<Edge>();
	
	public PrimsMST2(int noOfVertices, int noOfEdges){
		
		this.noOfVertices = noOfVertices;
		verticesIncluded = (boolean[])Array.newInstance(boolean.class, noOfVertices);
		// Stores the edge weights in a cost matrix
		edgeCost = (int[][])Array.newInstance(int.class, noOfVertices, noOfVertices);
		makeSetKeys = (int[])Array.newInstance(int.class, noOfVertices);
		vertices= (Vertex[])Array.newInstance(Vertex.class, noOfVertices);
		parent = (int[])Array.newInstance(int.class, noOfVertices);
		vertices[0] = new Vertex();
		parent[0] = -1;	// Initializing parent of initial node with -1 as it is the root of the minimum spanning tree
		// makeSetKeys finds the vertex among the non included vertices which has the minimum key
		for(int i = 1; i < noOfVertices; i++){
			makeSetKeys[i] = Integer.MAX_VALUE;
			vertices[i] = new Vertex();
		}
		for(int i = 0; i < noOfVertices; i++)
			for(int j = 0; j< noOfVertices; j++)
				edgeCost[i][j] = -1;
		
		heap = new EdgeHeapify(noOfEdges);
		cycle = new UnionFind2(9);
		
	}
	
	public int minKey(){
		int minkey = Integer.MAX_VALUE;
		Integer key = null;
		for(int i = 0; i < noOfVertices; i++){
			if(!verticesIncluded[i] && makeSetKeys[i] < minkey){
				key = i;
				minkey = makeSetKeys[i];
			}
		}
		return key;
	}
	
	public void addEdge(int src, int dest, int weight){
		vertices[src].setWeight(weight);
		Vertex destination = new Vertex();
		destination.setNode(dest);
		Vertex source = vertices[src];
		while(source.getLink() != null)
			source = source.getLink();
		source.setLink(destination);
	}
	
	public void addEdgeCostMatrix(int src, int dest, int weight){
		edgeCost[src][dest] = weight;
		heap.insert(new Edge(src, dest, weight));
		//edges.add(new Edge(src, dest, weight));
	}
	
	public void minimumSpanningTree(){
		
		//code for heapifying the edges and getting the minimum edge
		
		while(mstEdges.size() < noOfVertices-1){
			Edge temp = heap.remove();
			if(!cycle.union(temp))
				mstEdges.add(temp);
		}
		
		// Time complexity V^2 where for each new vertex included its adjacent vertices are updated with minimum edge cost as key
		for(int i = 0; i < noOfVertices-1; i++){
			int u = minKey();
			verticesIncluded[u] = true;
			for(int j = 0; j < noOfVertices; j++){
				if(edgeCost[u][j] != -1 && !verticesIncluded[j] && makeSetKeys[j] > edgeCost[u][j]){
					makeSetKeys[j] = edgeCost[u][j];
					parent[j] = u;
				}
			}
		}
	}
	
	public void printMinSpanningTree(){
		for(int i = 1; i < noOfVertices; i++){
			System.out.println("source : "+parent[i]+" destination : "+i+" weight : "+edgeCost[parent[i]][i]);
		}
	}
	
	public void printMinSpanningTree2(){
		Iterator<Edge> edge = mstEdges.iterator();
		int totalWeight = 0;
		while(edge.hasNext()){
			Edge e = edge.next();
			totalWeight += e.getWeight();
			System.out.println(e);
		}
		System.out.println("Total weight of the minimum spanning tree formed = " + totalWeight);
	}
	
}
