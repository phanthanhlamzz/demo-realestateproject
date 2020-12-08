package com.laptrinhjavaweb.util;

import java.util.ArrayList;

public class ListConverter<T> {
	public ArrayList<T> arrayToArrayList(T[] list){
		ArrayList<T> arrayList= new ArrayList<T>();
		for(T obj: list) {
			arrayList.add(obj);
		}
		return arrayList;
	} 
}
