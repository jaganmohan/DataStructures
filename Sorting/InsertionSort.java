import java.lang.*;
import java.io.*;
import java.util.*;

/*  Insertion sort-Sorts all the elements by selecting an element or a key
    from the list and inserting the element in the already sorted sublist in its proper place
  
	  It runs with an average time complexity of O(n)=nlogn 
 */
class Insertion{
	
	public void insertionSort(ArrayList<Integer> arr){
		System.out.println(arr.size());
		for(int j=1; j<arr.size();j++){
			int i=j-1;int key=arr.get(j);
			while(i>=0 && key<arr.get(i)){
				arr.set(i+1,arr.get(i));
				i--;
			}
			arr.set(i+1,key);
		}
		Iterator<Integer> iterate = arr.iterator();
		System.out.print("The sorted element are: ");
		while(iterate.hasNext())
			System.out.print(iterate.next()+" ");
	}
	
	public static void main(String []args){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		//Reader to read the input unsorted list from a file
		Scanner read = new Scanner(System.in);
		System.out.print("Enter the elements: ");
		try{
			while(read.hasNextInt()){
				arr.add(read.nextInt());
			}
		}catch(InputMismatchException e){
			System.out.println("Enter only integers");
		}
		new Insertion().insertionSort(arr);
	}
};
