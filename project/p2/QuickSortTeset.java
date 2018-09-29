package sjsu.Zhao.cs146.project2;

public class QuickSortTeset {

	public static void main(String[] args) {
		int[] array1 = arrayGenerater(15);
		int[] array2 = new int[15];
		int[] array3 = new int[15];
		
		for (int i = 0; i < array1.length; i++)
		{
			array2[i] = array1[i];
			array3[i] = array1[i];
		}
		
		for (int i = 0; i < array1.length; i++)
		{
			System.out.print(array1[i] + " ");
		}
		
		System.out.println();
		
		QuickSort sort = new QuickSort();
		
		sort.Quicksort(array1, 0, array1.length - 1);
		sort.quickSort2(array2, 0, array2.length - 1);
		sort.quicksort3(array3, 0, array2.length - 1);
		
		for (int i = 0; i < array1.length; i++)
		{
			System.out.print(array1[i] + " ");
		}
		System.out.println(sort.counter1);
		System.out.println();
		
		for (int i = 0; i < array2.length; i++)
		{
			System.out.print(array2[i] + " ");
		}
		System.out.println(sort.counter2);
		System.out.println();
		
		for (int i = 0; i < array3.length; i++)
		{
			System.out.print(array3[i] + " ");
		}
		System.out.println(sort.counter3);
	}
	
	static int[] arrayGenerater(int size)
	{
		int[] arr = new int[size];
		
		for (int i = 0; i < size; i++)
		{
			arr[i] = (int) (Math. random() * 100 + 1);
		}
		
		return arr;
	}

}
