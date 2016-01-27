package dictionary;

public enum Headers {
	HTML_HEADER(new Dictionary(new String[] { "accept", "x-requested-with" },
			new String[] { "text/html", "XMLHttpRequest" })), JSON_HEADER_CITED_IN(
			new Dictionary(new String[] { "accept", "x-requested-with" },
					new String[] { "application/json", "XMLHttpRequest" })), JSON_HEADER_REFERENCES(
			new Dictionary(new String[] { "accept", "x-requested-with" },
					new String[] { "application/json", "XMLHttpRequest" }));
	private Dictionary dic;

	Headers(Dictionary dic) {
		this.setDic(dic);
	}

	public Dictionary getDic() {
		return dic;
	}

	public void setDic(Dictionary dic) {
		this.dic = dic;
	}
	
	public static boolean isCitedIn(Headers h) {
		if(h == JSON_HEADER_CITED_IN)
			return true;
		else
			return false;
	}
}
