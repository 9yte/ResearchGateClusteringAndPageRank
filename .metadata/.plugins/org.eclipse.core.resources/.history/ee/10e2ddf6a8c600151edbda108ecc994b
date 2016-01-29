package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {
	private BufferedReader br = null;

	public ReadFromFile(String fileUrl) {
		try {
			br = new BufferedReader(new FileReader(fileUrl));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String read() {
		String result = "";
		String sCurrentLine;
		try {
			while ((sCurrentLine = br.readLine()) != null) {
				result = result + sCurrentLine + "\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}