package jagan.trees;

/*
 * Represents a Node in a tree with integer key and left and right child pointers 
 */
public class Node{
	
	private int key;
	private Node left;
	private Node right;
	
	public Node(int key){
		this.key = key;
	}
	
	public int getKey(){
		return key;
	}
	public Node getLeft(){
		return left;
	}
	public void setLeft(Node node){
		left = node;
	}
	public Node getRight(){
		return right;
	}
	public void setRight(Node node){
		right = node;
	}
	
}
