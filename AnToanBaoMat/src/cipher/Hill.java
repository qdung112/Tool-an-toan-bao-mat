package cipher;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import gui.OptionPane;

public class Hill {

	private  String ALPHABET = "AĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXY";
	private  int ALPHABET_SIZE = 29;
	private double [][] keyMatrix;

	public String encrypt(String plaintext) {
		if(keyMatrix == null) {
			OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
			return null;
		}
		StringBuilder ciphertext = new StringBuilder();
		double[] plaintextVector = convertTextToVector(plaintext);

		RealMatrix key = new Array2DRowRealMatrix(keyMatrix);
		RealMatrix plaintextMatrix = MatrixUtils.createRealMatrix(convert1DTo2D(plaintextVector));

		RealMatrix ciphertextMatrix = key.preMultiply(plaintextMatrix);

		for (int i = 0; i < ciphertextMatrix.getRowDimension(); i++) {
			int j = 0;
			while (j < 2) {
				int value = (int) ciphertextMatrix.getEntry(i, j++) % ALPHABET_SIZE;
				ciphertext.append(ALPHABET.substring(value, value + 1));
			}
		}
		
		for (int i = 0; i < plaintext.length(); i++) {
			char temp = plaintext.charAt(i);
			if (ALPHABET.indexOf(Character.toUpperCase(temp)) == -1) {
				ciphertext.insert(i, temp);
				continue;
			}
			if (Character.isLowerCase(temp)) {
				ciphertext.setCharAt(i, Character.toLowerCase(ciphertext.charAt(i)));
			} else {
				ciphertext.setCharAt(i, Character.toUpperCase(ciphertext.charAt(i)));
			}
		}
		return ciphertext.toString();
	}

	public String decrypt(String ciphertext) {
		
		if(keyMatrix == null) {
			OptionPane.showError("Không tìm thấy khóa","Bạn cần tạo key hoặc nhập key của bạn");
			return null;
		}
		
		StringBuilder plaintext = new StringBuilder();
		double[] ciphertextVector = convertTextToVector(ciphertext);

		RealMatrix ciphertextMatrix = MatrixUtils.createRealMatrix(convert1DTo2D(ciphertextVector));
		int number = findMultiplicativeInverse((int) calculateDeterminant(keyMatrix));
		RealMatrix inverseKey = MatrixUtils.createRealMatrix(calculateAdjugateMatrix(reverseMatrix(keyMatrix), number));
		RealMatrix plaintextMatrix = inverseKey.preMultiply(ciphertextMatrix);

		for (int i = 0; i < plaintextMatrix.getRowDimension(); i++) {
			int j = 0;
			while (j < 2) {
				int value = (int) plaintextMatrix.getEntry(i, j++) % ALPHABET_SIZE;
				plaintext.append(ALPHABET.substring(value, value + 1));
			}
		}
		
		for (int i = 0; i < ciphertext.length(); i++) {
			char temp = ciphertext.charAt(i);
			if (ALPHABET.indexOf(Character.toUpperCase(temp)) == -1) {
				plaintext.insert(i, temp);
				continue;
			}
			if (Character.isLowerCase(temp)) {
				plaintext.setCharAt(i, Character.toLowerCase(plaintext.charAt(i)));
			} else {
				plaintext.setCharAt(i, Character.toUpperCase(plaintext.charAt(i)));
			}
		}
		
		return plaintext.toString();
	}

	
	  private double[] convertTextToVector(String text) { 
		  List<Double> temp = new ArrayList<>(); 
		  int i = 0;
		  String textUpcase = text.toUpperCase();
		  
		  for (char c : textUpcase.toCharArray()) { 
			  int index = ALPHABET.indexOf(c);
			  if(index != -1) { 
				  temp.add((double) index); 
				 } 
	  }
		  double[] vector = new double[temp.size()]; 
		  for(double d : temp) { 
			  	vector[i++] = d; 
			  }
		  return vector; 
	  }

	private double[][] convert1DTo2D(double[] arr) {
		int index = 0;
		double[][] result = new double[arr.length % 2 == 0 ? arr.length / 2 : arr.length / 2 + 1][2];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				if (index >= arr.length)
					break;
				result[i][j] = arr[index];
				index++;
			}
		}
		return result;
	}

	private double[][] calculateAdjugateMatrix(double[][] matrix, int number) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = (int) (matrix[i][j] * number) % ALPHABET_SIZE;
			}
		}
		return matrix;
	}

	private double[][] reverseMatrix(double[][] matrix) {

		double[][] result = new double[matrix.length][matrix[0].length];
		result[0][0] = matrix[1][1];
		result[0][1] = ALPHABET_SIZE - matrix[0][1];
		result[1][0] = ALPHABET_SIZE - matrix[1][0];
		result[1][1] = matrix[0][0];
		
		return result;
	}

	private double calculateDeterminant(double[][] matrix) {
		return Math.abs(matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % Math.abs(ALPHABET_SIZE);
	}

	private int findMultiplicativeInverse(int number) {
		int m = ALPHABET_SIZE; 
		int a = number;
		int b = m;
		int x0 = 0, x1 = 1, y0 = 1, y1 = 0;

		while (a > 1) {
			int q = a / b;
			int temp = b;

			b = a % b;
			a = temp;

			temp = x0;
			x0 = x1 - q * x0;
			x1 = temp;

			temp = y0;
			y0 = y1 - q * y0;
			y1 = temp;
		}

		int multiplicativeInverse = (x1 % m + m) % m;
		return multiplicativeInverse;
	}

		public double [][] setKey( double [][] secretKey ) {
			try {
				keyMatrix = secretKey;
				OptionPane.showInfo("Tạo key", "Tạo key thành công");
			} catch(Exception e) {
				return null;
			}
			return keyMatrix;
		}
		
		public String showKey() {
			String result = "";
			if(keyMatrix == null) return null;
			for(double [] i : keyMatrix ) {
				for(double j : i) {
					result += j + " ";
				}
			}
			return result;
		}

		public  void setALPHABET(String aLPHABET) {
			ALPHABET = aLPHABET;
		}

		public  void setALPHABET_SIZE(int aLPHABET_SIZE) {
			ALPHABET_SIZE = aLPHABET_SIZE;
		}
		

		public int getALPHABET_SIZE() {
			return ALPHABET_SIZE;
		}

		public void createKey() {
			keyMatrix = new double[2][2];
			keyMatrix[0][0] = (int)(Math.random() * 10) + 1;	 
			keyMatrix[0][1]	= (int)(Math.random() * 10) + 1;
			keyMatrix[1][0]	= (int)(Math.random() * 10) + 1;
			keyMatrix[1][1]	= (int)(Math.random() * 10) + 1;
			OptionPane.showInfo("Tạo key","Key " + "HILL" + " đã được tạo");
		}
		
}
