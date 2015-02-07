package com.wangzhu.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLUtil {
	public static HttpResponse getHttpResponse(String url) throws Exception {
		HttpResponse response = new HttpResponse(url);
		URL _URL = new URL(url);
		HttpURLConnection con = (HttpURLConnection) _URL.openConnection();
		response.setCode(con.getResponseCode());
		response.setUrlPath(_URL.getPath());
		response.setHeaders(con.getHeaderFields());
		InputStream inputStream = con.getInputStream();
		response.setContent(URLUtil.readInputStream(inputStream));
		return response;
	}

	/**
	 * 将二进制流转化为byte字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream)
			throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		inputStream.close();
		return outputStream.toByteArray();
	}

}
