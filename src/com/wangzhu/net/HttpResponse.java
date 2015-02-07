package com.wangzhu.net;

import java.util.List;
import java.util.Map;

public class HttpResponse {
	private String url;
	private int code;
	private Map<String, List<String>> headers;
	private byte[] content;
	private String urlPath;

	public HttpResponse(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getContentType() {
		String contentType = null;
		List<String> contentTypeList = this.getHeader("Content-Type");
		if (contentTypeList != null) {
			contentType = contentTypeList.get(0);
		}
		return contentType;
	}

	public List<String> getHeader(String name) {
		return headers.get(name);
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
}
