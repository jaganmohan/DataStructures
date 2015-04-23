package jagan.heap;

import java.util.Arrays;

/**
 * 
 * @Reference {@link http://geeksquiz.com/heap-sort/}
 * 
 * @author Artist
 *
 */
public class HeapSort {
	
	private int arr[];
	private int size;
	
	public enum Heap{
		MAX, MIN;
	}
	
	public void swap(int pos1, int pos2){
		arr[pos1] = arr[pos1] + arr[pos2];
		arr[pos2] = arr[pos1] - arr[pos2];
		arr[pos1] = arr[pos1] - arr[pos2];
	}
	
	/**
	 * Forms a max heap - parent node is always greater than its child nodes
	 */
	public void maxheapify(int nodePos){
		
		int max = nodePos;
		int left = nodePos*2+1;
		int right = (nodePos+1)*2;
		
		if(left < size && arr[left] > arr[max])
			max = left;
		if(right < size && arr[right] > arr[max])
			max = right;
		
		if(max != nodePos){
			swap(nodePos, max);
			maxheapify(max);
		}
	}
	
	/**
	 * Forms a min heap - parent node is always smaller than its child nodes
	 */
	public void minheapify(int nodePos){
		int min = nodePos;
		int left = nodePos*2+1;
		int right = (nodePos+1)*2;
		
		if(left < size && arr[left] < arr[min])
			min = left;
		if(right < size && arr[right] < arr[min])
			min = right;
		
		if(min != nodePos){
			swap(nodePos, min);
			minheapify(min);
		}
	}
	
	/**
	 * Inserts a node into the heap
	 */
	public int[] insert(int ele, Heap maxOrmin){
		if(size == arr.length){
			arr = Arrays.copyOf(arr,size+size/2);
		}
		arr[size++] = ele;

		int elePos = size-1;
		if(maxOrmin == Heap.MAX){
			while(elePos-1 >= 0 && arr[elePos] > arr[(elePos-1)/2]){
				swap(elePos, (elePos-1)/2);
				elePos = (elePos-1)/2;
			}
		}else{
			while(elePos-1 >= 0 && arr[elePos] < arr[(elePos-1)/2]){
				swap(elePos, (elePos-1)/2);
				elePos = (elePos-1)/2;
			}
		}
		return arr;
	}
	
	/**
	 * Deletes a node from the heap
	 */
	public int delete(Heap maxOrmin){
		int val = arr[0];
		arr[0] = arr[--size];
		
		if(maxOrmin == Heap.MAX)
			maxheapify(0);
		else
			minheapify(0);
		
		return val;
	}
	
	/**
	 * Forms the heap using <b>maxheapify</b> or <b>minheapify</b> methods from a given list of elements
	 */
	public int[] buildheap(int list[], int size, Heap maxOrmin){
		arr = list;
		this.size = size;
		if(maxOrmin == Heap.MAX){
			for(int i = size/2-1; i>=0;i--)
				maxheapify(i);
		}else{
			for(int i = size/2-1; i>=0;i--)
				minheapify(i);
		}
		return arr;
	}
	
	/**
	 * Sorts a given list of elements using heap
	 * 
	 * @param list - int[]
	 * @param size - int
	 * @return int[]
	 */
	public int[] heapsort(int list[], int size, Heap maxOrmin){
		
		buildheap(list, size, maxOrmin);
		
		int sortedArr[] = new int [size];
		for(int i = 0; i < size; i++)
			sortedArr[i] = delete(maxOrmin);
		return sortedArr;
	}

}


