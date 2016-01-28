package info;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

import crawler.Constant;

public class ArticleFactory {
	private static HashMap<Integer, Article> articles = new HashMap<Integer, Article>();

	private static Semaphore sm = new Semaphore(1);

	public static Article createArticle(String abs, String title, String url,
			int id) throws InterruptedException {
		if (!articles.containsKey(id)) {
			Article a = new Article(abs, title, url, id, articles.size());
			sm.acquire();
			articles.put(id, a);
			System.out.println(articles.size());
			sm.release();
			return a;
		}
		return null;
	}

	private static Article getArticleById(int id) {
		if (!articles.containsKey(id)) {
			return null;
		} else
			return articles.get(id);
	}

	public static void addReference(int referencerId, int referenceId,
			String title) {
		getArticleById(referencerId).addReference(referenceId, title);
		// System.out.println(getArticleById(referencerId).toString());
	}

	public static void addCitedIn(int citedId, int citerId, String title) {
		getArticleById(citedId).addCitedIn(citerId, title);
		// System.out.println(getArticleById(citedId).toString());
	}
	
	public static boolean needForNewArticle() throws InterruptedException {
		sm.acquire();
		boolean r = articles.size() < Constant.maxNodes;
		sm.release();
		return r;
	}
	
	public static HashMap<Integer, Article> getArticles() {
		return articles;
	}

	public static void setArticles(HashMap<Integer, Article> articles) {
		ArticleFactory.articles = articles;
	}
}
