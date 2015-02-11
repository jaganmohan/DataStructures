package jagan.trees;

public class Vertex{
	private int node;
	private boolean traversed = false;
	private Vertex link;
	private int weight = 0;
	
	public void setLink(Vertex node){
		link = node;
	}
	public Vertex getLink(){
		return link;
	}
	public void setNode(int key){
		node = key;
	}
	public int getNode(){
		return node;
	}
	public void setTraversed(boolean traversed){
		this.traversed = traversed;
	}
	public boolean getTraversed(){
		return traversed;
	}
	public int getWeight(){
		return weight;
	}
	public void setWeight(int edgeWeight){
		weight = edgeWeight;
	}
}

