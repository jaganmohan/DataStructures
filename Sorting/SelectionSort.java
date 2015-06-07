import java.lang.*;
import java.io.*;
import java.util.*;

/*
	Selection Sort-Sorts the elements in a list by selecting the minimum or maximum element in the unsorted sublist and places it in its
	proper place, this way the elements placed form a sorted sublist
*/

class Selection{
	public void selectionSort(ArrayList<Integer> arr){
		for(int i=0;i<arr.size()-1;i++){
			int key=arr.get(i);int j=i+1;
			while(j<arr.size()){
				if(key>arr.get(j))
					key=arr.get(j);
				j++;
			}
			arr.set(arr.indexOf(key),arr.get(i));
			arr.set(i,key);
		}
		Iterator<Integer> iterate = arr.iterator();
		System.out.print("The sorted element are: ");
		while(iterate.hasNext())
			System.out.print(iterate.next()+" ");
	}
	public static void main(String []ar){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Scanner read = new Scanner(System.in);
		System.out.print("Enter the elements: ");
		try{
			while(read.hasNextInt())
				arr.add(read.nextInt());
		}catch(InputMismatchException e){
			System.out.println("Enter only integers");
		}
		new Selection().selectionSort(arr);
	}
}
