package file;

import info.Article;
import info.ArticleFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;

public class SaveResultsIntoJson {
	
	private Gson gson;
	
	public SaveResultsIntoJson() {
		gson = new Gson();
	}
	
	public void saveArticles() {
		String baseUrl = createDirectory();
		System.out.println(baseUrl);
		HashMap<Integer, Article> articles = ArticleFactory.getArticles();
		for (HashMap.Entry<Integer, Article> article : articles.entrySet()) {
			saveOneArticle(article.getValue(), baseUrl);
		}
	}
	
	private void saveOneArticle(Article a, String baseUrl) {
		int index = a.getIndex();
		String url = baseUrl + "/" + index + ".json";
		String json = gson.toJson(a);
		
		try {
			File file = new File(url);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json);
			bw.close();
		} catch (Exception e) {
		}
	}
	
	private String createDirectory() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String s = dateFormat.format(date);
		new File(s).mkdirs();
		return s;
	}
}
