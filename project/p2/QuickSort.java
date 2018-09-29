package sjsu.Zhao.cs146.project2;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

	public int counter1;
	public int counter2;
	public int counter3;

	public QuickSort() {
		counter1 = 0;
		counter2 = 0;
		counter3 = 0;
	}

	private int partition(int[] A, int p, int r) {
		int pivot = A[r];
		int i = p - 1;

		for (int j = p; j <= r - 1; j++) {
			if (A[j] <= pivot) {
				i++;
				swap(A, i, j);
			}
		}
		swap(A, i + 1, r);

		return i + 1;
	}

	private void swap(int[] A, int i, int j) {
		if (i == j)
			return;

		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

	public void Quicksort(int[] A, int p, int r) {
		Quicksort1(A, p, r, 0);

	}

	private void Quicksort1(int[] A, int p, int r, int depth) {
		if ((r - p) < 20 || depth > 1000) {
			insertionSort(A, p, r);
			return;
		}

		if (p < r) {
			counter1++;
			depth++;
			int q = partition(A, p, r);
			Quicksort1(A, p, q - 1, depth);
			Quicksort1(A, q + 1, r, depth);
		}

	}

	public int RANDOMIZEDPARTITION(int[] A, int p, int r) {
		Random rand = new Random();
		int randNum;
		if (r > p) {
			randNum = rand.nextInt(r - p) + p;
			swap(A, r, randNum);
			return partition(A, p, r);
		} else
			return r - 1;
	}

	public int RandomizedSelect(int[] A, int p, int r, int i)
	{
		if (p == r) // base case
		{
			return p;
		}
		int q = RANDOMIZEDPARTITION(A, p, r); // index of pivot
		int k = q - p + 1; // order of pivot
		if (i == k) // if the pivot is i-th order statistic
		{
			return q;
		} else if (i < k) // ignore bigger else smaller than pivot
		{
			return RandomizedSelect(A, p, q - 1, i);
		} else
			return RandomizedSelect(A, q + 1, r, i - k);// look for (i-k)-th
														// since k smallest
														// removed
	}

	public void quickSort2(int[] A, int p, int r) {
		qs2(A, p, r, 0);
	}

	private void qs2(int[] A, int p, int r, int depth) {
		if ((r - p) < 50 || depth > 1000) {
			insertionSort(A, p, r);
			return;
		}

		if (p < r) {
			int mid;
			int q;

//			 mid = (int) Math.floor((r - p) / 2) + p;
//			
//			 if (mid > r + 1 || mid < p + 1) {
//			 mid = r + 1;
//			 }
			mid = r + 1;
			counter2++;
			depth++;
			q = RandomizedSelect(A, p, r, mid);
			qs2(A, p, q - 1, depth);
			qs2(A, q + 1, r, depth);
		}

	}

	public void reset() {

	}

	private void insertionSort(int[] arr, int first, int last) {
		int i;
		int key;

		for (int j = first + 1; j < last + 1; j++) {
			key = arr[j];
			i = j - 1;

			while (i >= 0 && arr[i] > key) {
				arr[i + 1] = arr[i];
				arr[i] = key;
				i = i - 1;
			}

			arr[i + 1] = key;
		}
	}

	public int[] populate(int size) {
		int[] arr = new int[size];

		for (int i = 0; i < size; i++) {
			arr[i] = (int) (Math.random() * 100 + 1);
		}

		return arr;
	}

	public void quicksort3(int[] A, int p, int r) {
		qs3(A, p, r, 0);
	}

	private void qs3(int[] A, int p, int r, int depth) {
		if ((r - p) < 50 || depth > 1000) {
			insertionSort(A, p, r);
			return;
		}
		
		if (p < r) {
			depth++;
			counter3++;
			int q = partitionMedian(A, p, r);
			qs3(A, p, q - 1, depth);
			qs3(A, q + 1, r, depth);
		}
	}

	private int partitionMedian(int[] A, int p, int r) {
		int pivot = getPivot(A, p, r);
		int i = p - 1;

		for (int j = p; j <= r - 1; j++) {
			if (A[j] <= pivot) {
				i++;
				swap(A, i, j);
			}
		}
		swap(A, i + 1, r);

		return i + 1;
	}
	
	private int getPivot(int[] A, int p, int r) {
		if (r - p + 1 <= 9) {
			Arrays.sort(A);
			return A[r];
		}

		int tempMed[] = null;
		int medians[] = new int[(int) Math.ceil((double) (r - p + 1) / 5)];
		int medianIndex = 0;

		while (p <= r) {
			tempMed = new int[Math.min(5, r - p + 1)];

			for (int j = 0; j < tempMed.length && p <= r; j++) {
				tempMed[j] = A[p];
				p++;
			}

			Arrays.sort(tempMed);

			medians[medianIndex] = tempMed[tempMed.length / 2];
			medianIndex++;
		}

		return getPivot(medians, 0, medians.length - 1);
	}
}
