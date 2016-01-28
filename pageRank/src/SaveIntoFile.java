import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class SaveIntoFile {
	public static void saveIntoFile(double[] a, String baseUrl) {
		int len = a.length;
		String s = "";
		for (int i = 0; i < len; i++) {
			s = s + a[i] + " ";
		}
		s = s.substring(0, s.length() - 1);
		String url = baseUrl + "/pageRank";
		
		try {
			File file = new File(url);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(s);
			bw.close();
		} catch (Exception e) {
		}
	}
}
