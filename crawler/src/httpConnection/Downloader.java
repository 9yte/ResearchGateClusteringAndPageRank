package httpConnection;

import dictionary.Headers;

public class Downloader {
	public static String downloadUrl(String downloadUrl, Headers header){
		return HttpConnection.get(downloadUrl, header);
	}
}
