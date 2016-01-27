package dictionary;

import java.util.HashMap;

public class Dictionary {

	private HashMap<String, String> dic;

	public Dictionary(String[] keys, String[] values) {
		dic = new HashMap<String, String>();
		putAll(keys, values);
	}

	void put(String key, String value) {
		dic.put(key, value);
	}

	void putAll(String[] keys, String[] vals) {
		int len = keys.length;
		if (len != vals.length)
			return;
		else {
			for (int i = 0; i < len; i++) {
				put(keys[i], vals[i]);
			}
		}
	}

	boolean has(String key) {
		return dic.containsKey(key);
	}

	String get(String key) {
		if (has(key)) {
			return dic.get(key);
		}
		return null;
	}

	public Pair[] getPairs() {
		Pair[] p = new Pair[dic.size()];
		int i = 0;
		for (HashMap.Entry<String, String> entry : dic.entrySet()) {
			p[i] = new Pair(entry.getKey(), entry.getValue());
			i++;
		}
		return p;
	}
}
