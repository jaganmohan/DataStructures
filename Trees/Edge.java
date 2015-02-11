package jagan.trees;

import java.util.Comparator;

public class Edge implements Comparable<Edge>{

	private int source;
	private int dest;
	private int weight;
	
	public int getSource(){
		return source;
	}
	
	public int getDest(){
		return dest;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public Edge(int v1, int v2, int weight){
		source = v1;
		dest = v2;
		this.weight = weight;
	}

	public int compareTo(Edge arg0) {
		if(arg0 !=null && this.weight < arg0.getWeight())
			return -1;
		else if(arg0 != null && this.weight > arg0.getWeight())
			return 1;
		else return 0;
	}
	
	@Override
	public String toString(){
		return "Source: "+ this.source +" Destination: " + this.dest + " Weight: " + this.weight;
	}
	
}

class EdgeCompare implements Comparator<Edge>{
	
	public int compare(Edge e1, Edge e2){
		return e1.compareTo(e2);
	}
	
}
