package jagan.utils;

import java.lang.reflect.Array;

public class Queue<E> {
	
	private E queue[];
	
	@SuppressWarnings("unchecked")
	public Queue(Class<E> dataType, int length){
		try{
			queue = (E[]) Array.newInstance(dataType, length);
		}catch(Exception e){
			System.out.println("Typecasting error, Please check the provided datatype and generic type ");
			e.printStackTrace();
		}
	}

}
