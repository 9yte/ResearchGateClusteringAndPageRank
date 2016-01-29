import java.util.HashMap;

public class TransitionMatrix {
	private HashMap<Integer, Integer> idToIndex;
	private HashMap<Integer, Integer> indexToId;

	private Article[] articles;
	private int size;
	private double threshold;

	private double[][] transitionMatrix;

	public TransitionMatrix(Article[] articles, int size, double alpha) {
		idToIndex = new HashMap<Integer, Integer>();
		indexToId = new HashMap<Integer, Integer>();
		this.articles = articles;
		this.size = size;
		createIdAndIndexMapping();
		createTransitionMatrix();
		normalize();
		teleport(alpha);
	}

	public double[] findProbMatrix() {
		double[] probMatrix = new double[size];
		probMatrix[1] = 1;
		while (true) {
			double[] newProbMatrix = updateProbMatrix(probMatrix);
			if (compareToProbMatrix(probMatrix, newProbMatrix)) {
				probMatrix = newProbMatrix;
				break;
			}
			probMatrix = newProbMatrix;
		}
		return probMatrix;
	}
	
	private void testPageRank(double[] prob) {
		double sum = 0;
		for (int i = 0; i < size; i++) {
			sum += prob[i];
		}
		System.out.println(sum);
	}

	private double[] updateProbMatrix(double[] probMatrix) {
		double[] newProbMatrix = new double[size];
		for (int i = 0; i < size; i++) {
			double c = 0;
			for (int j = 0; j < size; j++) {
				c += transitionMatrix[j][i] * probMatrix[j];
			}
			newProbMatrix[i] = c;
		}
		return newProbMatrix;
	}

	private boolean compareToProbMatrix(double[] matrix1, double[] matrix2) {
		for (int i = 0; i < size; i++) {
			if (matrix1[i] - matrix2[i] > threshold)
				return false;
		}
		return true;
	}

	private void teleport(double alpha) {
		double d = 1 - alpha;
		double n = 1.0 / (size * 1.0);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				transitionMatrix[i][j] = d * transitionMatrix[i][j] + alpha * n;
			}
		}
	}

	private void createIdAndIndexMapping() {
		for (int i = 0; i < size; i++) {
			Article a = articles[i];
			idToIndex.put(a.id, a.getIndex());
			indexToId.put(a.getIndex(), a.id);
		}
	}

	private void createTransitionMatrix() {
		transitionMatrix = new double[size][size];
		for (int i = 0; i < size; i++) {
			Article a = articles[i];
			int[] refIds = a.getAllReferences();
			int len = refIds.length;
			for (int j = 0; j < len; j++) {
				if (idToIndex.containsKey(refIds[j])) {
					int id = idToIndex.get(refIds[j]);
					transitionMatrix[i][id] = 1;
				}
			}
		}
	}

	private void normalize() {
		for (int i = 0; i < size; i++) {
			int numOfNonZeroCells = numOfNonZero(i);
			if (numOfNonZeroCells == 0) {
				fillTheRow(i, 1.0 / size, 0);
			} else {
				fillTheRow(i, (1.0) / (numOfNonZeroCells * 1.0), 1);
			}
		}
	}

	private void fillTheRow(int rowNumber, double value,
			double valueShouldBeReplaced) {
		for (int i = 0; i < size; i++) {
			if (Math.abs(transitionMatrix[rowNumber][i] - valueShouldBeReplaced) < 0.000000001)
				transitionMatrix[rowNumber][i] = value;
		}
	}

	private int numOfNonZero(int rowNumber) {
		int counter = 0;
		for (int i = 0; i < size; i++) {
			if (transitionMatrix[rowNumber][i] > 0.00000001)
				counter++;
		}
		return counter;
	}

	private void print() {
		System.out.println("---------------");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(transitionMatrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---------------");
	}

	private void print2(double[] array) {
		for (int i = 0; i < size; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
