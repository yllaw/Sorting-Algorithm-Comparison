package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntegerLists {
	
	public static List<Integer> create10RandomIntList() {
		return instantiateList(10, 1337L, Integer.MAX_VALUE);		 
	}
	
	public static List<Integer> create10RandomIntList(int bound) {
		return instantiateList(10, 1337L, bound);		 
	}
	
	public static List<Integer> create100RandomIntList() {
		return instantiateList(100, 1337L, Integer.MAX_VALUE);		 
	} 
	
	public static List<Integer> create100RandomIntList(int bound) {
		return instantiateList(100, 1337L, bound);		 
	}
	
	public static List<Integer> create1000RandomIntList() {
		return instantiateList(1000, 1337L, Integer.MAX_VALUE);		 
	}
	
	public static List<Integer> create1000RandomIntList(int bound) {
		return instantiateList(1000, 1337L, bound);		 
	}
	
	public static List<Integer> create10000RandomIntList() {
		return instantiateList(10000, 1337L, Integer.MAX_VALUE);		 
	}
	
	public static List<Integer> create10000RandomIntList(int bound) {
		return instantiateList(10000, 1337L, bound);		 
	}
	public static List<Integer> create100000RandomIntList() {
		return instantiateList(100000, 1337L, Integer.MAX_VALUE);		 
	}
	
	public static List<Integer> create100000RandomIntList(int bound) {
		return instantiateList(100000, 1337L, bound);		 
	}

	public static List<Integer> instantiateList(int count, long seed, int bound) {
		Random random = new Random();
		random.setSeed(seed); //sets seed
		
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < count; i++) {
			list.add(random.nextInt(bound));
		}
		return list;
	}
}
