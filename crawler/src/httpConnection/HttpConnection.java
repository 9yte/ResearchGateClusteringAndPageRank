package httpConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import dictionary.Headers;
import dictionary.Pair;

public class HttpConnection {

	public static String get(String url, Headers dic) {

		try {
			URL server = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) server
					.openConnection();
			connection.setRequestMethod("GET");

			// add request header
			addHeaderProperties(connection, dic);

			int responseCode = connection.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			return response.toString();
		} catch (Exception e) {
			
		}
		return null;

	}

	private static void addHeaderProperties(HttpURLConnection connection,
			Headers h) {
		Pair[] p = h.getDic().getPairs();
		for (int i = 0; i < p.length; i++) {
			connection.setRequestProperty(p[i].getKey(), p[i].getValue());
		}
	}

}
