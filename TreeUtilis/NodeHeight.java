package jagan.trees;


public class NodeHeight {

	private int key;
	private int height;
	private NodeHeight left;
	private NodeHeight right;
	
	public NodeHeight(int key){
		this.key = key;
		height = 0;
	}
	
	public int getKey(){
		return key;
	}
	public NodeHeight getLeft(){
		return left;
	}
	public void setLeft(NodeHeight node){
		left = node;
	}
	public NodeHeight getRight(){
		return right;
	}
	public void setRight(NodeHeight node){
		right = node;
	}
	public int getHeight(){
		return height;
	}
	public void setHeight(int height){
		this.height = height;
	}
}
