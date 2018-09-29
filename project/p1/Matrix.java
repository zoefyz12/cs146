package sjsu.Zhao.cs146.project1;


public class Matrix {
	
	private double[][] matrix;
	public int sCounter = 0;
	public int stCounter = 0;
	public int size;

	public Matrix()
	{
		
	}
	public Matrix(double[][] m)
	{
		matrix = m;
		size = m.length;
	}
	
	public Matrix straightForward(Matrix m)
	{
		double[][] result = new double[matrix.length][matrix.length];
		
		for (int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix.length; j++)
			{
				for(int k = 0; k < matrix.length; k++)
				{
					sCounter++;
					result[i][j] += matrix[i][k] * m.matrix[k][j];
				}
			}
		}
		return new Matrix(result);
	}	
	
	public double[][] add(double[][] Matrix1, double[][] Matrix2)
	{
		int size = Matrix1.length;
		double[][] result1 = new double[size][size];
	    for (int i = 0; i < size; i++) 
	    {
	        for (int j = 0; j < size; j++) 
	        {
	            result1[i][j] = Matrix1[i][j] + Matrix2[i][j];
	        }
	    }
	    
	    return result1;
	}
	public double[][] sub(double[][] Matrix1, double[][] Matrix2)
	{
		int size = Matrix1.length;
		double[][] result = new double[size][size];
	    for (int i = 0; i < size; i++) 
	    {
	        for (int j = 0; j < size; j++) 
	        {
	            result[i][j] = Matrix1[i][j] - Matrix2[i][j];
	        }
	    }
	    
	    return result;
	}

	public double[][] Strassen(double[][] Matrix1, double[][] Matrix2)
	{
		stCounter++;
		int size = Matrix1.length;
		if (size == 1)
		{
			double[][] result = new double[size][size];
			for (int i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
				{
					for(int x = 0; x < size; x++)
					{
						result[i][j] += Matrix1[i][x] * Matrix2[x][j];
					}
				}
			}
			return result; 
		}
		else
		{
			int halfsize = size / 2;
			double[][] A11 = new double[halfsize][halfsize];
			double[][] A12 = new double[halfsize][halfsize];
			double[][] A21 = new double[halfsize][halfsize];
			double[][] A22 = new double[halfsize][halfsize];
			double[][] B11 = new double[halfsize][halfsize];
			double[][] B12 = new double[halfsize][halfsize];
			double[][] B21 = new double[halfsize][halfsize];
			double[][] B22 = new double[halfsize][halfsize];
			
			double[][] M1;  
			double[][] M2;  
			double[][] M3;  
			double[][] M4;  
			double[][] M5;  
			double[][] M6;  
			double[][] M7;  
			
			double[][] C1; 
			double[][] C2; 
			double[][] C3; 
			double[][] C4; 
			
			double[][] result = new double[size][size];
			for (int i = 0; i < halfsize; i++)
			{
				for (int j = 0; j < halfsize; j++)
				{
					A11[i][j] = Matrix1[i][j];
					A12[i][j] = Matrix1[i][j+halfsize];
					A21[i][j] = Matrix1[i+halfsize][j];
					A22[i][j] = Matrix1[i+halfsize][j+halfsize];
					B11[i][j] = Matrix2[i][j];
					B12[i][j] = Matrix2[i][j+halfsize];
					B21[i][j] = Matrix2[i+halfsize][j];
					B22[i][j] = Matrix2[i+halfsize][j+halfsize];
					
				}
			}
			
			M1 = Strassen(add(A11,A22),add(B11,B22));		
			M2 = Strassen(add(A21,A22),B11);
			M3 = Strassen(A11,sub(B12,B22));
			M4 = Strassen(A22,sub(B21,B11));
			M5 = Strassen(add(A11,A12),B22);
			M6 = Strassen(sub(A21,A11),add(B11,B12));
			M7 = Strassen(sub(A12,A22),add(B21,B22));
		
			C1 = sub(add(add(M1,M4),M7),M5);
			C2 = add(M3,M5);
			C3 = add(M2,M4);
			C4 = sub(add(add(M1,M3),M6),M2);
			
			for (int i = 0; i < halfsize; i++)
			{
				for (int j = 0; j < halfsize; j++)
				{
					result[i][j] = C1[i][j];
					result[i][j+halfsize] = C2[i][j];
					result[i+halfsize][j] = C3[i][j];
					result[i+halfsize][j+halfsize] = C4[i][j];
				}
			}
			return result;
		}
	}
	public Matrix StrassenMethod(Matrix m) 
	{	
		return new Matrix(Strassen(matrix, m.matrix));
	}
	
	public String toString() 
	{
		String s = "";
		
		for(int i = 0; i < matrix.length; i++)
		{
			for( int j = 0; j < matrix.length; j++)
			{
				s = s + Double.toString(matrix[i][j]) + " ";
			}
			
			s = s + "\n";
		}

		return s;
	}
	
	public boolean equals(Matrix m)
	{
		if(matrix.length != m.size)
			
			return false;
		
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix.length; j++)
			{
				if (matrix[i][j] != m.matrix[i][j])
					
					return false;
			}
		}
		
		return true;
	}
}

