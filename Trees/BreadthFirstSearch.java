package jagan.trees;

import java.lang.reflect.Array;

import sun.misc.Queue;

/**
 * Time complexity - O(V+E) for adjacent lists and O(V^2) for adjacency matrix
 * 
 * @author Artist
 * @Reference  http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 */
public class BreadthFirstSearch {
	
	private Vertex[] vertices;
	//private Vertex[] traversalQ;
	
	public BreadthFirstSearch(int noOfVertices){
		
		//creates a dynamic array of Vertex
		vertices = (Vertex[]) Array.newInstance(Vertex.class, noOfVertices);

		// TODO optimize the code
		for(int i = 0; i < noOfVertices ; i++){
			vertices[i] = new Vertex();
			vertices[i].setNode(i+1);
		}
	}
	
	public void addEdge(int vertex1, int vertex2){
		Vertex v2 = new Vertex();
		v2.setNode(vertex2);
		Vertex v1 = vertices[vertex1-1];
		while(v1.getLink() != null)
			v1 = v1.getLink();
		v1.setLink(v2);
	}
	
	public void breadthFirstTraversal(int node){
		
		Queue<Vertex> traversalQ = new Queue<Vertex>();
		traversalQ.enqueue(vertices[node-1]);
		System.out.println("Breadth First Search traversal of the graph from "+node+" is :");
		try{
			
			//traversing the adjacent nodes in breadth first order
			while(!traversalQ.isEmpty()){
				Vertex temp = traversalQ.dequeue(100L);
				temp.setTraversed(true);
				System.out.print("->"+temp.getNode());
				temp = temp.getLink();
				
				//Adding all the adjacent nodes to the queue before traversing, and traversing the adjacent nodes in FIFO order
				while(temp != null && 
						vertices[temp.getNode()-1].getTraversed() == false){
					
					traversalQ.enqueue(vertices[temp.getNode()-1]);
					temp = temp.getLink();
				}
				
			}
		}catch(InterruptedException e){
			System.out.println("Exception occurred while enqueing and dequeing");
			e.printStackTrace();
		}
	}

}
