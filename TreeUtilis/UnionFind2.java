package jagan.utils;

import java.lang.reflect.Array;
import jagan.trees.Edge;

/**
 * @Assumptions <li>The graph is an undirected graph
 * 				<li>Doesn't contain any self loops
 * 				<li>Time complexity of Find and Union is O(log n), where n is the no. of vertices
 * 				<li>The idea of merging(union) is based on forming forests of common node as parent 
 * 					and adjacent nodes as children
 * @author Artist
 * @Reference {@link http://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/} <br/>
 * 			  {@link http://en.wikipedia.org/wiki/Disjoint-set_data_structure}
 */

class Subset{
	int parent;
	int rank = 0;
	
	public void setParent(int parent){
		this.parent = parent;
	}
	public int getParent(){
		return parent;
	}
	public void setRank(int rank){
		this.rank = rank;
	}
	public int getRank(){
		return rank;
	}
}

public class UnionFind2 {
	
	private Subset parent[];

	/**
	 * 
	 * @param noOfVertices Number of vertices or nodes in the graph
	 */
	public UnionFind2(int noOfVertices){
		Subset obj = new Subset();
		parent = (Subset[])Array.newInstance(Subset.class, noOfVertices);
		for(int i = 0; i < noOfVertices; i++){
			parent[i] = new Subset();
			parent[i].setParent(i);
		}
	}
	
	/**
	 * 
	 * @param vertex The vertex whose parent subset is searched
	 * @return parent subset to which the vertex belongs to
	 */
	public Subset find(int vertex){
		
		if(parent[vertex].getParent() != vertex)
			parent[vertex].setParent(find(parent[vertex].getParent()).getParent());
		
		return parent[vertex];
		
	}
	
	public boolean union(Edge e){
		
		Subset parentSrc = find(e.getSource());
		Subset parentDest = find(e.getDest());
		
		if(parentSrc.getParent() == parentDest.getParent())
			return true;
		
		if(parent[parentSrc.getParent()].getRank() > parent[parentDest.getParent()].getRank())
			parentDest.setParent(parentSrc.getParent());
		else if(parent[parentSrc.getParent()].getRank() < parent[parentDest.getParent()].getRank())
			parentSrc.setParent(parentDest.getParent());
		else{
			parent[parentDest.getParent()].setRank(parent[parentDest.getParent()].getRank()+1);
			parent[parentSrc.getParent()].setParent(parentDest.getParent());			
		}
		
		return false;
		
	}
	
}
