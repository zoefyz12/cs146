package sjsu.Zhao.cs146.project1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JunitTest {
	@Before
	public void setUp() throws Exception 
	{
		
	}

	@Test
	public void testMatrix() 
	{
		double[][] a = {{1,2}, {1,2}};
		double[][] b = {{4,5}, {4,5}};
		Matrix m1 = new Matrix(a);
		Matrix m2 = new Matrix(b);
		Matrix productStrassen = m1.StrassenMethod(m2);
		Matrix productRegular = m1.straightForward(m2);
		assertEquals(productStrassen.toString(), productRegular.toString());
	}
	@Test
	public void testMultiplicationTiming()
	{    
		double[][] a1 = newMatrix(4);
		double[][] a2 = newMatrix(16);
		double[][] a3 = newMatrix(512);
		double[][] a4 = newMatrix(1024);
		double[][] b1 = newMatrix(4);
		double[][] b2 = newMatrix(16);
		double[][] b3 = newMatrix(512);
		double[][] b4 = newMatrix(1024);
		Matrix m1 = new Matrix(a1);
		Matrix m2 = new Matrix(b1);
		Matrix m3 = new Matrix(a2);
		Matrix m4 = new Matrix(b2);
		Matrix m5 = new Matrix(a3);
		Matrix m6 = new Matrix(b3);
		Matrix m7 = new Matrix(a4);
		Matrix m8 = new Matrix(b4);
		long startTime = System.currentTimeMillis();
		Matrix final1 = m1;
		final1.straightForward(m2);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("When size of the matrix is 4");
		System.out.println("Straight Forward method using time: "+ estimatedTime);
		System.out.println("Counter in Straight Forward method: " + final1.sCounter);
		startTime = System.currentTimeMillis();
		Matrix final2 = m3;
		final2.straightForward(m4);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("When size of the matrix is 16");
		System.out.println("Straight Forward method using time: "+ estimatedTime);
		System.out.println("Counter in Straight Forward method: " + final2.sCounter);
		startTime = System.currentTimeMillis();
		Matrix final3 = m5;
		final3.straightForward(m6);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("When size of the matrix is 512");
		System.out.println("Straight Forward method using time: "+ estimatedTime);
		System.out.println("Counter in Straight Forward method: " + final3.sCounter);
		startTime = System.currentTimeMillis();
		Matrix final4 = m7;
		final4.straightForward(m8);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("When size of the matrix is 1024");
		System.out.println("Straight Forward method using time: "+ estimatedTime);
		System.out.println("Counter in Straight Forward method: " + final4.sCounter);

	}
	@Test
	public void StrassenTiming() 
	{
		double[][] a1 = newMatrix(4);
		double[][] a2 = newMatrix(16);
		double[][] a3 = newMatrix(512);
		double[][] a4 = newMatrix(1024);
		double[][] b1 = newMatrix(4);
		double[][] b2 = newMatrix(16);
		double[][] b3 = newMatrix(512);
		double[][] b4 = newMatrix(1024);
		long startTime = System.currentTimeMillis();
		Matrix final1 = new Matrix();
		final1.Strassen(a1,b1);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("When size of the matrix is 4");
		System.out.println("Strassen method using time: "+ estimatedTime);
		System.out.println("Counter of the Strassen method: " + final1.stCounter);
		startTime = System.currentTimeMillis();
		Matrix final2 = new Matrix();
		final2.Strassen(a2,b2);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("When size of the matrix is 16");
		System.out.println("Strassen method using time: "+ estimatedTime);
		System.out.println("Counter of the Strassen method: " + final2.stCounter);
		startTime = System.currentTimeMillis();
		Matrix final3 = new Matrix();
		final3.Strassen(a3,b3);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("When size of the matrix is 512");
		System.out.println("Strassen method using time: "+ estimatedTime);
		System.out.println("Counter of the Strassen method: " + final3.stCounter);
		startTime = System.currentTimeMillis();
		Matrix final4 = new Matrix();
		final4.Strassen(a4,b4);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("When size of the matrix is 1024");
		System.out.println("Strassen method using time: "+ estimatedTime);
		System.out.println("Counter of the Strassen method: " + final4.stCounter);
		
	}
	
	public static double[][] newMatrix(int size)
	{
		double[][] matrix = new double[size][size];
		
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				matrix[j][i] = Math.round(Math. random() *(9-1+1));
			}
		}
		
		return matrix;
	}

}
