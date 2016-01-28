package crawler;

public class Constant {
	static String citedInName = "Cited In";
	static String referencesName = "References";

	static String initialArticlesFileUrl = "initialArticles";

	public static int crawlDegree = 10;
	static String domain = "https://www.researchgate.net/";
	static private String citedInUrl = "https://www.researchgate.net/publicliterature.PublicationIncomingCitationsList.html?publicationUid=PUBLICATION_UID&showCitationsSorter=true&showAbstract=false&showType=true&showPublicationPreview=true&swapJournalAndAuthorPositions=false&limit=10000&offset=0";
	static private String referencesUrl = "https://www.researchgate.net/publicliterature.PublicationCitationsList.html?publicationUid=PUBLICATION_UID&showCitationsSorter=true&showAbstract=false&showType=true&showPublicationPreview=true&swapJournalAndAuthorPositions=false&limit=100000&offset=0";

	public static int maxNodes = 1000;
	
	public static String getCitedInUrl(int id) {
		return citedInUrl.replace("PUBLICATION_UID", id + "");
	}

	public static String getReferencesUrl(int id) {
		return referencesUrl.replace("PUBLICATION_UID", id + "");
	}
	
	public static String expandQuery(String url) {
		return domain + url;
	}
}
