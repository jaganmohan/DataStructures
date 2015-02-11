package jagan.trees;

import java.lang.reflect.Array;

/**
 * 
 * @author Artist
 * @Reference {@link http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/}
 */
public class DepthFirstSearch {
	
	private Vertex[] vertices;
	
	public DepthFirstSearch(int noOfVertices){
		vertices = (Vertex[])Array.newInstance(Vertex.class, noOfVertices);
		for(int i = 0; i < noOfVertices; i++){
			vertices[i] = new Vertex();
			vertices[i].setNode(i);
		}
	}
	
	/**
	 * Adds the edge from vertex1 to vertex2 as a link to vertex1 in the form of adjacent list
	 * @param vertex1
	 * @param vertex2
	 */
	public void addEdge(int vertex1, int vertex2){
		Vertex v2 = new Vertex();
		v2.setNode(vertex2);
		Vertex v1 = vertices[vertex1];
		while(v1.getLink() != null)
			v1 = v1.getLink();
		v1.setLink(v2);
	}
	
	/*public void depthFirstTraversal(int node){
		Stack<Vertex> traversalStack = new Stack<Vertex>();
		traversalStack.add(vertices[node]);
		System.out.println("Depth First Traversal of the graph from node "+node+" is : ");
		while(!traversalStack.empty()){
			Vertex temp = traversalStack.pop();
			temp.setTraversed(true);
			System.out.print("->"+temp.getNode());
			temp = temp.getLink();
			while(temp != null && vertices[temp.getNode()].getTraversed() == false){
				traversalStack.add(vertices[temp.getNode()]);
				temp = temp.getLink();
			}
		}
	}*/
	
	/**
	 * Traversing the graph in depth first order starting from node using recursion(which implicitly uses stack)
	 * @param node
	 */
	public void depthFirstTraversal(int node){
		
		System.out.print("->"+node);
		vertices[node].setTraversed(true);
		
		Vertex next = vertices[node].getLink();
		
		// Traversing the adjacent nodes in depth first manner
		while(next != null && vertices[next.getNode()].getTraversed() == false){
			depthFirstTraversal(next.getNode());
			next = vertices[next.getLink().getNode()];
		}
	}
}
