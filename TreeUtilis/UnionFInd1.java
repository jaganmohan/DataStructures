package jagan.utils;

import java.lang.reflect.Array;
import jagan.trees.Edge;

/**
 * @Assumptions <li>The graph is an undirected graph
 * 				<li>Doesn't contain any self loops
 * 				<li>Time complexity of Find and Union is O(n), where n is the no. of vertices
 * 				<li>The idea of merging(union) is based on forming forests of common node as parent 
 * 					and adjacent nodes as children
 * @author Artist
 * @Reference {@link http://www.geeksforgeeks.org/union-find/} <br/>
 * 			  {@link http://en.wikipedia.org/wiki/Disjoint-set_data_structure}
 */
public class UnionFind1 {

	private int[] parent;
	
	public UnionFind1(int noOfVertices){
		parent = (int[])Array.newInstance(int.class, noOfVertices);
		for(int i= 0; i < noOfVertices; i++)
			parent[i] = -1;
	}
	
	/**
	 * Finds to which subset the vertex belongs to
	 * 
	 * @param vertex
	 * @return parent of vertex
	 */
	public int find(int vertex){
		if(parent[vertex] == -1)
			return vertex;
		//flattening of the tree
		parent[vertex] = find(parent[vertex]);
		return parent[vertex];
	}
	
	/**
	 * 
	 * @param e is an Edge between source and destination(it is two way for undirected graph)
	 * @return whether edge e forms a cycle (true/ false)
	 */
	public boolean union(Edge e){
		int parentSrc = find(e.getSource());
		int parentDest = find(e.getDest());
		
		if(parentSrc != parentDest)
			parent[parentDest] = parentSrc;
		else
			return true;
		
		return false;
	}
	
}
