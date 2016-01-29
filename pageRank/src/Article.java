

import java.util.ArrayList;
import java.util.HashMap;

public class Article {
	private String abs;
	String title;
	int id;
	private String url;
	private ArrayList<String> names;
	private HashMap<Integer, String> references;
	private HashMap<Integer, String> citedIn;
	private int index;
	
	public Article(String abs, String title, String url, int id, int index) {
		this.id = id;
		this.title = title;
		this.abs = abs;
		this.url = url;
		this.setIndex(index);
		names = new ArrayList<String>();
		references = new HashMap<Integer, String>();
		citedIn = new HashMap<Integer, String>();
	}
	
	public void addAuthor(String name) {
		names.add(name);
	}
	
	public void addReference(int id, String title) {
		if(!references.containsKey(id))
			references.put(id, title);
	}
	
	public void addCitedIn(int id, String title) {
		if(!citedIn.containsKey(id))
			citedIn.put(id, title);
	}
	
	public int getNumOfReferences() {
		return references.size();
	}
	
	public int getNumOfCitedIn() {
		return citedIn.size();
	}
	
	public int[] getAllReferences() {
		int[] referencesId = new int[getNumOfReferences()];
		int i = 0;
		for (HashMap.Entry<Integer, String> ref : references.entrySet()) {
			referencesId[i] = ref.getKey();
			i++;
		}
		return referencesId;
	}
	
	public int[] getAllCitedIns() {
		int[] referencesId = new int[getNumOfCitedIn()];
		int i = 0;
		for (HashMap.Entry<Integer, String> cited : citedIn.entrySet()) {
			referencesId[i] = cited.getKey();
			i++;
		}
		return referencesId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Article))
			return false;
		else
			return ((Article)obj).id == id;
	}
	
	@Override
	public String toString() {
		String p = title + "\n";
		for (String n : names) {
			p = p + n + ", ";
		}
		p = p.substring(0, p.length() - 2);
		p += "\nurl:\n" + url; 
		p += "\nreferences:\n";
		
		for (HashMap.Entry<Integer, String> entry : references.entrySet()) {
			p = p + entry.getValue() + "\n";
		}
		
		p += "\ncited in:\n";
		for (HashMap.Entry<Integer, String> entry : citedIn.entrySet()) {
			p = p + entry.getValue() + "\n";
		}
		
		return p;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
