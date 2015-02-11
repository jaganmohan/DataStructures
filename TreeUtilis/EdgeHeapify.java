package jagan.utils;

import jagan.trees.Edge;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EdgeHeapify {
	
	private Edge[] edges;
	private int noOfEdges;
	
	public EdgeHeapify(int noOfEdges){
		edges = (Edge[])Array.newInstance(Edge.class, noOfEdges);
		this.noOfEdges = 0;
	}
	
	public void insert(Edge e){
		
		int length = noOfEdges++;
		edges[length] = e;
		
		while(length > 0){
			if(edges[(length-1)/2].compareTo(edges[length]) > 0){
				Edge temp = edges[(length-1)/2];
				edges[(length-1)/2] = edges[length];
				edges[length] = temp;
			}
			length = (length-1)/2;                       
		}			
	}
	
	public Edge remove(){
		
		Edge temp = edges[--noOfEdges];
		//edges[edges.length-1] = null;
		Edge min = edges[0];
		//edges[0] = temp;
		int length = 0, minLength = 0;
		while(length < noOfEdges/2){
			temp = edges[(2*length)+1];
			minLength = 2*length+1;
			if(temp.compareTo(edges[2*length+2]) > 0){
				 temp = edges[2*length+2];
				 ++minLength;
			}
			if(temp.compareTo(edges[noOfEdges]) < 0){
				edges[length] = temp;
				length = minLength;
			}else{
				break;
			}
		}
		edges[length] = edges[noOfEdges];
		edges[noOfEdges] = null;
		return min;
		
	}

}
