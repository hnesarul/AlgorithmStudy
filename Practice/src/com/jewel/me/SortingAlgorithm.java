package com.jewel.me;

public class SortingAlgorithm {
	
	public static void normalSort() {
		
	}
	
	

	public static void quickSort(int[] a, int l, int r) {
//		int[] a = { 10, 16, 8, 12, 15, 6, 3, 9, 5 };	
		if (l < r) {
			int p = split(a, l, r);
			quickSort(a, l, p-1);
			quickSort(a, p+1, r);
		}

	}

	private static int split(int[] a, int l, int r) {
		
		int pivot = a[l], i = l, j = r+1;
		while (i < j) {
			do {
				i++;
			} while (a[i] <= pivot && i < j);

			do {
				j--;
			} while (a[j] > pivot && j > i);

			if (a[i] > a[j] && i < j) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
		if (a[j] < pivot) {
			a[l] = a[j];
			a[j] = pivot;
		}
		return j;
	}

}
