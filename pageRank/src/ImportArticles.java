import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

public class ImportArticles {

	private String baseUrl;
	private int numOfArticles;
	private Gson gson;

	public ImportArticles(String baseUrl, int numOfArticles) {
		this.baseUrl = baseUrl;
		this.numOfArticles = numOfArticles;
		gson = new Gson();
	}
	
	public Article[] importAllArticles() {
		Article[] articles = new Article[numOfArticles];
		for (int i = 0; i < numOfArticles; i++) {
			articles[i] = importOneArticle(i);
		}
		return articles;
	}

	private Article importOneArticle(int index) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader(baseUrl + "/" + index + ".json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.fromJson(br, Article.class);
	}
}
