//Jason Hetland
//1324246
//cmps012b
//7/2/14
//using recursive functions to get a max and a min of a function in a 
//array

public class Extrema{
	public static void main(String[] args) {
		int[] B = { -1, 2, 6, 3, 9, 2, -3, -2, 11, 5, 7 };

		System.out.println("max = " + maxArray(B, 0, B.length - 1));
		System.out.println("min = " + minArray(B, 0, B.length - 1));

	}

	public static int minArray(int[] array, int p, int r) {
		int q;
		if (p < r) {
			q = (p + r) / 2;
			int min1 = minArray(array, p, q);
			int min2 = minArray(array, q + 1, r);
			return min(min1, min2);
		} else {
			return array[p];
		}
	}

	public static int maxArray(int[] array, int p, int r) {
		int q;
		if (p < r) {
			q = (p + r) / 2;
			int max1 = maxArray(array, p, q);
			int max2 = maxArray(array, q + 1, r);
			return max(max1, max2);
		} else {
			return array[p];
		}
	}

	public static int min(int valueA, int valueB) {
		if (valueA < valueB) {
			return valueA;
		} else {
			return valueB;
		}
	}

	public static int max(int valueA, int valueB) {
		if (valueA > valueB) {
			return valueA;
		} else {
			return valueB;
		}
	}

}
