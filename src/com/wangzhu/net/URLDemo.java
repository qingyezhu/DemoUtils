package com.wangzhu.net;

public class URLDemo {
	public static void main(String[] args) throws Exception {
		HttpResponse response = URLUtil
				.getHttpResponse("http://www.stats.gov.cn/tjsj/tjgb/rkpcgb/qgrkpcgb/201209/t20120921_30330.html");
		System.out.println(response.getCode());
		System.out.println(response.getContentType());
	}
}
