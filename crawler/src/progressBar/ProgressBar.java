package progressBar;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ProgressBar {
	private char sign = '#';
	private static int numOfMaxWorks;
	private int scale = 20;
	private int max = 0;
	private static ProgressBar p;
	public static void initialize(int numOfMaxWorks) {
		ProgressBar.numOfMaxWorks = numOfMaxWorks;
	}
	
	public void update(int numOfWorks) {
		if(numOfWorks <= numOfMaxWorks) {
			int numOfChars = Math.floorDiv(numOfWorks, scale);
			String s = createNumOfChars(numOfChars, sign);
			double percentage = (numOfWorks * 100.0) / (numOfMaxWorks);
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.CEILING);
			print("%" + df.format(percentage) + " " + s);
			return;
		}
		if(numOfWorks == numOfMaxWorks){
			System.out.println();
		}
	}
	
	public void update2(int numOfCitations) {
		if((int) Math.floor(Math.log10(numOfCitations)) > max)
			max = (int) Math.floor(Math.log10(numOfCitations));
		print("still downloading " + String.format("%03d", numOfCitations) + " citations");
		if(numOfCitations <= 1)
			System.out.println();
	}
	
	public void finish() {
		System.err.println("\ndone.");
	}
	
	private String createNumOfChars(int numOfChars, char sign) {
		String s = "";
		for (int i = 0; i < numOfChars; i++) {
			s += sign;
		}
		return s;
	}

	private void print(String string) {
		System.out.print("\r" + string);
	}
	
	public static ProgressBar getInstance() {
		if(p == null){
			p = new ProgressBar();
		}
		return p;
	}
}
