package jagan.graphs;

/**
 * Prints the cycles present in an undirected graph using DFS and BFS
 * Time Complexity - 
 * 
 * @author Artist
 *
 */
public class CyclesInUndirectedGraph {

	private class Vertex{
		public int node;
		public Vertex adjVertex;
		public Vertex(){}
		public Vertex(int v){node=v;}
	}
	
	private Vertex vertices[];
	private boolean traversed[];
	private int parentNode[];
	private int recurStack[];
	private int stackCount = -1;
	
	public CyclesInUndirectedGraph(){};
	
	public CyclesInUndirectedGraph(int noOfVertices){
		vertices = new Vertex[noOfVertices];
		traversed = new boolean[noOfVertices];
		parentNode = new int[noOfVertices];
		recurStack = new int[noOfVertices];
		for(int i = 0; i < noOfVertices; i++){
			vertices[i] = new Vertex(i+1);
			parentNode[i] = -1;
			recurStack[i] = -1;
		}
	}
	
	public void addEdge(int vertex1, int vertex2){
		Vertex v = vertices[vertex1-1];
		while(v.adjVertex != null)
			v = v.adjVertex;
		v.adjVertex = new Vertex(vertex2);
	}
	
	public boolean isCyclicUtil(int v){
		boolean flag = false;
		traversed[v] = true;
		recurStack[++stackCount] = v+1;
		for(Vertex ver = vertices[v].adjVertex; ver != null; ver = ver.adjVertex){
			if(!traversed[ver.node-1]){
				parentNode[ver.node-1] = v+1;
				if(isCyclicUtil(ver.node-1)) flag = true;
			}else if(ver.node != parentNode[v] || ver.node == v+1){ //no back edge or self loop
				System.out.print(ver.node+" ");
				for(int i = stackCount; i >= 0; i--){
					System.out.print(recurStack[i]+" ");
					if(recurStack[i] == ver.node)
						break;
				}System.out.println();
				flag = true;
			}
		}
		recurStack[stackCount--] = -1;
		return flag;
	}
	
	public boolean isCycle(){
		boolean flag = false;
		for(int i=0; i < vertices.length; i++){
			if(!traversed[i] && isCyclicUtil(i))
				flag = true;
		}
		return flag;
	}
	
	public void findCyclesUsingDFS(){
		 
	}
	
	public void findCyclesUsingBFS(){
		
	}
	
	public static void main(String[] ar){
		CyclesInUndirectedGraph graph = new CyclesInUndirectedGraph(3);
		graph.addEdge(1,2);
		graph.addEdge(1,3);
		graph.addEdge(2,1);
		graph.addEdge(2,3);
		graph.addEdge(3, 1);
		graph.addEdge(3, 2);
		graph.addEdge(2, 2);
		if(graph.isCycle())
			System.out.println("It has a cycle");
		else{
			System.out.println("It does not contain a cycle");
		}
	}
	
}
