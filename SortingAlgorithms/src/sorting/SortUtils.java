package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortUtils {

	public static <T extends Comparable<T>> SortStats bubbleSort(List<T> list) {
		SortStats st = new SortStats();
		st.sortName = "Bubble Sort";
		st.runtime = "Θ(n^2)";

		for (int i = 1; i < list.size(); i++) {
			for (int j = 0; j < list.size() - 1; j++) {
				//compare every value
				T temp = list.get(j + 1);
				st.numberOfComparison++;
				if (list.get(j).compareTo(temp) > 0) {
					list.set(j + 1, list.get(j));
					list.set(j, temp);
					st.numberOfSwap++;
				}
			}
		}
		st.stopExecutionTime();
		return st;
	}

	public static <T extends Comparable<T>> SortStats cocktailSort(List<T> list) {
		SortStats st = new SortStats();
		st.sortName = "Cocktail Sort ";
		st.runtime = "Θ(n^2)";
		boolean swapped = true;
		int start = 0;
		int end = list.size();

		while (swapped == true) {
			swapped = false;
			// bubble sort
			for (int i = start; i < end - 1; ++i) {
				st.numberOfComparison++;
				if (list.get(i).compareTo(list.get(i + 1)) > 0) {
					T temp = list.get(i);
					list.set(i, list.get(i + 1));
					list.set(i + 1, temp);
					swapped = true;
					st.numberOfSwap++;
				}
			}
			// check for sorted array
			if (swapped == false) {
				break;
			}
			swapped = false;
			//update last sorted on top
			end = end - 1;
			// bubble sort backwards
			for (int i = end - 1; i >= start; i--) {
				st.numberOfComparison++;
				if (list.get(i).compareTo(list.get(i + 1)) > 0) {
					T temp = list.get(i);
					list.set(i, list.get(i + 1));
					list.set(i + 1, temp);
					st.numberOfSwap++;
					swapped = true;
				}
			}
			// update last sorted on bottom
			start = start + 1;
		}
		st.stopExecutionTime();
		return st;
	}

	public static <T extends Comparable<T>> SortStats combSort(List<T> list) {
		SortStats st = new SortStats();
		st.sortName = "Comb Sort ";
		st.runtime = "Θ(n log(n)) - Θ (n^2)";
		//improved bubblesort
		int gap = list.size();

		boolean swapped = true;
		//if not more swaps, break
		while (gap != 1 || swapped == true) {
			// Find next gap
			gap = (gap * 10) / 13;// divide by empirical 1.3 factor
			if (gap < 1) {
				gap = 1;
			}
			//check for swap
			swapped = false;

			// Compare all elements with current gap
			for (int i = 0; i <  list.size() - gap; i++) {
				st.numberOfComparison++;
				if (list.get(i).compareTo(list.get(i + gap)) > 0) {
					//gap used to swap elements
					T temp = list.get(i);
					list.set(i, list.get(i + gap));
					list.set(i + gap, temp);
					st.numberOfSwap++;
					swapped = true;
				}
			}
		}//last iteration is bubblesort on optimized list
		st.stopExecutionTime();
		return st;
	}

	public static SortStats countingSort(List<Integer> list) {
		SortStats st = new SortStats();
		st.sortName = "Counting Sort";
		st.runtime = "Θ(n+k))";
		int[] minMaxArray = getMinAndMaxArray(list);
		int min = minMaxArray[0];
		int max = minMaxArray[1];

		int k = max - min + 1;
		int[] counts = new int[k];// frequency array

		for (int i = 0; i < list.size(); i++) {
			counts[list.get(i) - min]++;
			st.numberOfSwap++;
			// array is 0 based, so offset by min to save space.
			// offsetting by min also allows sorting for negative numbers.
		}

		int j = 0;
		for (int i = 0; i < counts.length; i++) {
			while (counts[i]-- > 0)// set numbers into the list until frequency
									// count is 0
			{
				list.set(j++, i + min);
				st.numberOfSwap++;
			}
		}
		st.stopExecutionTime();
		return st;
	}

	public static <T extends Comparable<T>> SortStats insertionSort(List<T> list) {
		SortStats st = new SortStats();
		st.sortName = "Insertion Sort";
		st.runtime = "Θ(n^2)";
		T temp;

		for (int i = 1; i < list.size(); i++) {
			for (int j = i; j > 0; j--) {
				st.numberOfComparison++;
				if (list.get(j).compareTo(list.get(j - 1)) < 0) { //insert between elements
					temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j - 1, temp);

					st.numberOfSwap++;

				}
			}
		}
		st.stopExecutionTime();
		return st;
	}

	private static <T extends Comparable<T>> void merge(List<T> list1, List<T> list2, List<T> result, SortStats st) {
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < list1.size() && j < list2.size()) {
			st.numberOfComparison++;
			if (list1.get(i).compareTo(list2.get(j)) < 0) {
				result.set(k, list1.get(i));
				i++;
				st.numberOfSwap++;
			} else {
				result.set(k, list2.get(j));
				j++;
				st.numberOfSwap++;
			}
			k++;
		}

		while (i < list1.size()) {
			result.set(k, list1.get(i));
			i++;
			k++;
			st.numberOfSwap++;
		}

		while (j < list2.size()) {
			result.set(k, list2.get(j));
			j++;
			k++;
			st.numberOfSwap++;
		}
	}

	public static <T extends Comparable<T>> SortStats mergeSort(List<T> list) {
		SortStats st = new SortStats();
		st.sortName = "Merge Sort";
		st.runtime = "Θ(n log(n))";

		if (list.size() > 1) {
			int mid = (list.size() - 1) / 2;
			int i = 0;
			List<T> left = new ArrayList<T>();
			for (; i <= mid; i++) {
				left.add(list.get(i));
			}
			mergeSort(left);

			List<T> right = new ArrayList<T>();
			for (; i < list.size(); i++) {
				right.add(list.get(i));
			}
			mergeSort(right);

			merge(left, right, list, st);

		}
		st.stopExecutionTime();
		return st;
	}

	private static <T extends Comparable<T>> int partition(List<T> list, int low, int high, SortStats st) {
		T pivot = list.get(high);
		int i = low - 1;

		for (int j = low; j <= high - 1; j++) {
			st.numberOfComparison++;
			if (list.get(j).compareTo(pivot) <= 0) {
				i++;
				T temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
				st.numberOfSwap++;
			}
		}
		T temp = list.get(i + 1);
		list.set(i + 1, list.get(high));
		list.set(high, temp);
		st.numberOfSwap++;

		return i + 1;
	}

	public static <T extends Comparable<T>> SortStats quickSort(List<T> list) {
		SortStats st = new SortStats();
		st.sortName = "Quick Sort";
		st.runtime = "Θ(n log(n))";
		quickSort(list, 0, list.size() - 1, st);
		st.stopExecutionTime();
		return st;
	}

	private static <T extends Comparable<T>> void quickSort(List<T> list, int low, int high, SortStats st) {
		if (low < high) {
			int p = partition(list, low, high, st);
			quickSort(list, low, p - 1, st);
			quickSort(list, p + 1, high, st);
		}
	}

	public static SortStats radixSort(List<Integer> list) {
		SortStats st = new SortStats();
		st.sortName = "Radix Sort";
		st.runtime = "Θ(nk)";

		int[] minMaxArray = getMinAndMaxArray(list);
		int max = minMaxArray[1];

		int i;
		int exp = 1;
		int[] b = new int[list.size()];

		while (max / exp > 0) {// counting sort using the i'th digit
			int[] bucket = new int[10];

			for (i = 0; i < list.size(); i++)
				bucket[(list.get(i) / exp) % 10]++;
			for (i = 1; i < 10; i++) {
				bucket[i] += bucket[i - 1];
				st.numberOfSwap++;
			}
			for (i = list.size() - 1; i >= 0; i--) {
				b[--bucket[(list.get(i) / exp) % 10]] = list.get(i);
				st.numberOfSwap++;
			}
			for (i = 0; i < list.size(); i++) {
				list.set(i, b[i]);
				st.numberOfSwap++;
			}
			exp *= 10;
		}

		st.stopExecutionTime();
		return st;
	}

	public static <T extends Comparable<T>> SortStats selectionSort(List<T> list) {
		SortStats st = new SortStats();
		st.sortName = "Selection Sort";
		st.runtime = "Θ(n^2)";
		for (int i = 0; i < list.size() - 1; i++) {
			int min = i;
			T temp = list.get(min); //bring min to the start
			for (int j = i + 1; j < list.size(); j++) {
				st.numberOfComparison++;
				if (list.get(j).compareTo(temp) < 0) {
					min = j;
					list.set(min, list.get(i));
					list.set(i, temp);

					st.numberOfSwap++;
				}
			}
		}
		st.stopExecutionTime();
		return st;
	}

	public static <T extends Comparable<T>> SortStats shellSort(List<T> list) {
		SortStats st = new SortStats();
		st.sortName = "Shell Sort";
		st.runtime = "Θ(n(log(n))^2)";

		//gap
		for (int gap = list.size() / 2; gap > 0; gap /= 2) {
			//insertion sort on gap
			for (int i = gap; i < list.size(); i += 1) {
				//inserted element
				T temp = list.get(i);
				//compare to set correct location for temp
				int j;
				st.numberOfComparison++;
				for (j = i; j >= gap && list.get(j - gap).compareTo(temp) > 0; j -= gap) {
					T temp2 = list.get(j);
					list.set(j, list.get(j - gap));
					list.set(j - gap, temp2);
					st.numberOfSwap++;
				}
				st.numberOfSwap++;
			}
		}
		st.stopExecutionTime();
		return st;
	}

	static int[] getMinAndMaxArray(List<Integer> list) {
		int max = list.get(0);
		int min = max;

		int[] minAndMax = new int[2];

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) > max) {
				max = list.get(i);
			} else if (list.get(i) < min) {
				min = list.get(i);
			}
		}

		minAndMax[0] = min;
		minAndMax[1] = max;

		return minAndMax;// java doesn't support tuples
	}

}
