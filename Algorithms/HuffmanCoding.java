package jagan.algo;

class MinheapNode{
	
	char data;
	int freq;
	MinheapNode left, right;
	
	public MinheapNode(){}
	
	public MinheapNode(char data, int freq){
		this.data = data;
		this.freq = freq;
		left = right = null;
	}
	
}

class MinHeap{
	
	int size;
	int capacity;
	MinheapNode array[];
	
	public MinHeap(){}
	
	public MinHeap(char data[], int freq[],int capacity){
		size = capacity;
		this.capacity = capacity;
		array = new MinheapNode[capacity];
		for(int i = 0; i < size ; i++){
			array[i] = new MinheapNode(data[i], freq[i]);
		}
		buildMinHeap();
	}
	
	public void swapNodes(int i, int j){
		MinheapNode temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
	
	public void minHeapify(int pos){
		int min = pos;
		int left = 2*pos+1;
		int right = 2*pos+2;
		
		if(left < size && array[left].freq < array[min].freq)
			min = left;
		if(right < size && array[right].freq < array[min].freq)
			min = right;
		
		if(min != pos){
			swapNodes(pos, min);
			minHeapify(min);
		}
	}
	
	public void insertNode(MinheapNode node){
		array[size++] = node;
		int pos = size-1;
		while(pos-1 >= 0 && array[pos].freq < array[(pos-1)/2].freq){
			swapNodes(pos, (pos-1)/2);
			pos = (pos-1)/2;
		}
	}
	
	public MinheapNode extractMin(){
		MinheapNode min = array[0];
		array[0] = array[size-1];
		array[--size] = null;
		minHeapify(0);
		return min;
	}
	
	public void buildMinHeap(){
		for(int i = (size-1)/2; i >= 0; i--){
			minHeapify(i);
		}
	}
	
}

public class HuffmanCoding {
	
	MinHeap minHeap;
	
	private MinheapNode buildHuffmanTree( char data[], int freq[]){
		minHeap = new MinHeap(data, freq, data.length);
		MinheapNode left, right, node = null;
		while(minHeap.size != 1){
			left = minHeap.extractMin();
			right = minHeap.extractMin();
			node = new MinheapNode('$', left.freq + right.freq);
			node.left = left;
			node.right = right;
			minHeap.insertNode(node);
		}
		return minHeap.extractMin();
	}
	
	private void printHuffmanCodes(MinheapNode root, int arr[], int top){
		if(root.left != null){
			arr[top] = 0;
			printHuffmanCodes(root.left, arr, top+1);
		}
		if(root.right != null){
			arr[top] = 1;
			printHuffmanCodes(root.right, arr, top+1);
		}
		if(root.left == null && root.right == null){
			System.out.print(root.data+" - ");
			for(int i=0; i<top; i++)
				System.out.print(arr[i]);
			System.out.println();
		}
	}
	
	public void getHuffmanCodes(char data[], int freq[]){
		
		int height = (int)Math.ceil(Math.log10(data.length)/Math.log(2));
		System.out.println(height);
		int arr[] = new int[data.length-1];
		
		MinheapNode root = buildHuffmanTree(data, freq);
		
		printHuffmanCodes(root, arr, 0);
		
	}
	
	public static void main(String []ar){
		HuffmanCoding codes = new HuffmanCoding();
		char[] arr={'a','b','c','d','e','f'};
		int freq[]={5, 9, 12, 13, 16, 45};
		codes.getHuffmanCodes(arr, freq);
	}

}
