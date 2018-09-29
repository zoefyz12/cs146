package sjsu.Zhao.cs146.project1;

public class MatrixMultiplicationTest {

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
	public static void main(String[] args) 
	{
		// create two double 2-D arrays
		double[][] a = newMatrix(4);
		double[][] b = newMatrix(4);
		Matrix m1 = new Matrix(a);
		Matrix m2 = new Matrix(b);
		System.out.println("This is Matrix 1: ");
		System.out.println(m1);
		System.out.println("This is Matrix 2: ");
		System.out.println(m2);
		Matrix productStrassen = m1.StrassenMethod(m2);
		Matrix productRegular = m1.straightForward(m2);
		System.out.println(productRegular);
		System.out.println(productStrassen);
		System.out.println("Are matrices the same? " + productRegular.equals(productStrassen));
	}

}
