package sorting;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SortTests {

	@Test
	public void SortUtils_bubbleSortTest() {
		//Arrange
		List<Integer> ten = IntegerLists.create100RandomIntList();		
		
		//Act
		SortUtils.bubbleSort(ten);
		
		//Assert
		assert(isSorted(ten));
	}
	
	@Test
	public void SortUtils_selectionSortTest() {
		//Arrange
		List<Integer> ten = IntegerLists.create1000RandomIntList();		
		
		//Act
		SortUtils.selectionSort(ten);
		
		//Assert
		assert(isSorted(ten));
	}
	
	@Test
	public void SortUtils_insertionSortTest() {
		//Arrange
		List<Integer> ten = IntegerLists.create1000RandomIntList();		
		
		//Act
		SortUtils.insertionSort(ten);
		
		//Assert
		assert(isSorted(ten));
	}
	
	@Test
	public void SortUtils_mergeSortTest() {
		//Arrange
		List<Integer> ten = IntegerLists.create1000RandomIntList();		
		
		//Act
		SortUtils.mergeSort(ten);
		
		//Assert
		assert(isSorted(ten));
	}
	
	@Test
	public void SortUtils_quickSortTest() {
		//Arrange
		List<Integer> ten = IntegerLists.create1000RandomIntList();		
		
		//Act
		SortUtils.quickSort(ten);
		//Assert
		assert(isSorted(ten));
	}
	
	
	@Test
	public void SortUtils_countingSortTest() {
		//Arrange
		List<Integer> list = IntegerLists.create1000RandomIntList(50000);
		
		
		//Act		
		SortStats st = SortUtils.countingSort(list);
		
		
		//Assert
		assert(isSorted(list));
	}
	
	@Test
	public void SortUtils_radixSortTest() {
		//Arrange
		List<Integer> list = IntegerLists.create1000RandomIntList(50000);
		
		
		//Act		
		SortStats st = SortUtils.radixSort(list);
		
		
		//Assert
		assert(isSorted(list));
	}
	
	@Test
	public void SortUtils_shellSortTest() {
		//Arrange
		List<Integer> list = IntegerLists.create1000RandomIntList();
		
		
		//Act		
		SortStats st = SortUtils.shellSort(list);
		
		
		//Assert
		assert(isSorted(list));
	}
	
	@Test
	public void SortUtils_combSortTest() {
		//Arrange
		List<Integer> list = IntegerLists.create1000RandomIntList();
		
		
		//Act		
		SortStats st = SortUtils.combSort(list);
		
		
		//Assert
		assert(isSorted(list));
	}
	
	@Test
	public void SortUtils_cocktailSortTest() {
		//Arrange
		List<Integer> list = IntegerLists.create1000RandomIntList();
		
		
		//Act		
		SortStats st = SortUtils.cocktailSort(list);
		
		
		//Assert
		assert(isSorted(list));
//		System.out.print(st.toString());
	}
	
	
	
	
	
	private <T extends Comparable<T>> boolean isSorted(List<T> list){
		for(int i = 0; i < list.size() - 1; i++){
			if(list.get(i).compareTo(list.get(i + 1) ) > 0){
				return false;
			}
		}
		
		return true;
	}

}
