package sjsu.Zhao.cs146.project2;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class QuickSortJunit {

	   private QuickSort QS;

	   @Before
	   public void setUp() throws Exception
	   {
	      QS = new QuickSort();
	      QS.reset();
	   } // setUp()
	
	   /* Method to test the Sorting of an empty List
	    */
	   @Test
	   public void testEmpty() {
	             int[] array1 = new int[0];
	             int[]  array2= new int[0]; //correct sorted array
	             
	             QS.Quicksort(array1, 0, array1.length - 1);
	             assertArrayEquals(array1,array2);
	             QS.quickSort2(array1, 0, array1.length - 1);
	             assertArrayEquals(array1,array2);
	   }   

	  
	   /* Method to test the Sorting of an already sorted list:
	    */
	   @Test
	   public void testSorted() {
	        int[] array1 = new int[20];
	        int[] array2 = new int[20];
	 	int[] array3 = new int[20];
	              
		 for (int i = 0; i < 10; i++) {
	             array1[i] = i*2;
	             array2[i] = i*2;
		     array3[i] = i*2;

	        }
		// sort using Java's inbuilt sorting method
	        Arrays.sort(array3);
	         
	        // run QS1()
	        QS.Quicksort(array1, 0, array1.length - 1);
	        assertArrayEquals(array1,array3);
	        
	        // run QS2()
	        QS.quickSort2(array2, 0, array2.length - 1);
	        assertArrayEquals(array1,array3);
	        
	   }
	 
	  
	   /* Method to test the Sorting of a reverse sorted list:
	    */
	   @Test
	   public void testReverseSorted() {
	        int[] array1 = new int[10];
	        int[] array2 = new int[10];
	        
	        int[] array3 = new int[10];
	        
	        for (int i = 0; i < 10; i++) {
	             array1[i] = (100-i);
	             array2[i] = (100-i);
	             array3[i] = (100-i);
	        }
	        //sort array3 
	        Arrays.sort(array3); 
	   
	        // run QS1()
	        QS.Quicksort(array1, 0, array1.length - 1);
	        assertArrayEquals(array1,array3);
	        
	        // run QS2()
	        QS.quickSort2(array2, 0, array2.length - 1);
	        assertArrayEquals(array1,array3);
	        
	   }
	   
	   /*
	    * Method to test the select method 
	    */
	    @Test
	    public void testSelect() {
	      int[] array1 = new int[100];
	       
	      for (int i = 0; i < 100; i++) {
	            array1[i] = i;
	      }
	      // median is 49
	      int median=QS.RandomizedSelect(array1,0, array1.length-1, array1.length/2);
	      System.out.println("median:"+ QS.RandomizedSelect(array1,0, array1.length-1, array1.length/2));
	      assertEquals(median, 49);
	       
	    }
	 
	 
	   /*
	    * Method to test the randomness to the tests:
	    */
	    @Test
	     public void testRandom() {
	       int[] array1 = new int[10];
	       
	       for (int i = 0; i < 10; i++) {
	            array1[i] = (int) Math.random()*10;
	       } 
	 
	       //copy arrays
	       int[] array2 = Arrays.copyOf(array1, array1.length);  
	       int[] array3 = Arrays.copyOf(array1, array1.length); // correct sorted array 
	       Arrays.sort(array3);
	 
	       // run QS1()
	       QS.Quicksort(array1, 0, array1.length - 1);
	       assertArrayEquals(array1,array3);
	       
	       // run QS2()
	       QS.quickSort2(array2, 0, array2.length - 1);
	       assertArrayEquals(array1,array3);
	       
	     }

	   /* Method to test the timing of QS1
	    *
	    */
	   @Test
	   public void testQS1Timing()
	   {
	      // create an array and a sorted backup
		   int[] array1 = QS.populate(10000);
		   int[] array4 = QS.populate(100000);
		   int[] array2 = QS.populate(1000000);
		   int[] array3 = QS.populate(10000000);
		   int[] array5 = QS.populate(100000000);
	      
	      long start = System.currentTimeMillis();
	      QS.Quicksort(array1, 0, array1.length - 1);
	      long end = System.currentTimeMillis();
	      long elapsed = end - start;
	      System.out.println("QS1 time to sort 10000 elements in ms:"+ elapsed);
	      
	      start = System.currentTimeMillis();
	      QS.Quicksort(array4, 0, array4.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS3 time to sort 100000 elements in ms:"+ elapsed);

	      start = System.currentTimeMillis();
	      QS.Quicksort(array2, 0, array2.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS1 time to sort 1000000 elements in ms:"+ elapsed);
	      
	      start = System.currentTimeMillis();
	      QS.Quicksort(array3, 0, array3.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS1 time to sort 100000000 elements in ms:"+ elapsed);
	      
	      start = System.currentTimeMillis();
	      QS.Quicksort(array5, 0, array5.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS3 time to sort 1000000000 elements in ms:"+ elapsed);
	   }



	   /* Method to test the timing of QS2
	    *
	    */
	   @Test
	   public void testQS2Timing()
	   {
	      // create an array and a sorted backup
		      int[] array1 = QS.populate(10000);
		      int[] array4 = QS.populate(100000);
		      int[] array2 = QS.populate(1000000);
		      int[] array3 = QS.populate(10000000);
		      int[] array5 = QS.populate(100000000);
	      
	      long start = System.currentTimeMillis();
	      QS.quickSort2(array1, 0, array1.length - 1);
	      long end = System.currentTimeMillis();
	      long elapsed = end - start;
	      System.out.println("QS2 time to sort 10000 elements in ms:"+ elapsed);
	      
	      start = System.currentTimeMillis();
	      QS.quickSort2(array4, 0, array4.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS3 time to sort 100000 elements in ms:"+ elapsed);

	      start = System.currentTimeMillis();
	      QS.quickSort2(array2, 0, array2.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS2 time to sort 1000000 elements in ms:"+ elapsed);
	      
	      start = System.currentTimeMillis();
	      QS.quickSort2(array3, 0, array3.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS2 time to sort 100000000 elements in ms:"+ elapsed);
	      
	      start = System.currentTimeMillis();
	      QS.quickSort2(array5, 0, array5.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS3 time to sort 1000000000 elements in ms:"+ elapsed);
	   } 
	   
	   /* Method to test the timing of QS3
	    *
	    */
	   @Test
	   public void testQS3Timing()
	   {
	      // create an array and a sorted backup
	      int[] array1 = QS.populate(10000);
	      int[] array4 = QS.populate(100000);
	      int[] array2 = QS.populate(1000000);
	      int[] array3 = QS.populate(10000000);
	      int[] array5 = QS.populate(100000000);
	      
	      
	      long start = System.currentTimeMillis();
	      QS.quicksort3(array1, 0, array1.length - 1);
	      long end = System.currentTimeMillis();
	      long elapsed = end - start;
	      System.out.println("QS3 time to sort 10000 elements in ms:"+ elapsed);
	      
	      start = System.currentTimeMillis();
	      QS.quicksort3(array4, 0, array4.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS3 time to sort 100000 elements in ms:"+ elapsed);

	      start = System.currentTimeMillis();
	      QS.quicksort3(array2, 0, array2.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS3 time to sort 1000000 elements in ms:"+ elapsed);
	      
	      start = System.currentTimeMillis();
	      QS.quicksort3(array3, 0, array3.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS3 time to sort 100000000 elements in ms:"+ elapsed);
	      
	      start = System.currentTimeMillis();
	      QS.quicksort3(array5, 0, array5.length - 1);
	      end = System.currentTimeMillis();
	      elapsed = end - start;
	      System.out.println("QS3 time to sort 1000000000 elements in ms:"+ elapsed);
	   }
}
