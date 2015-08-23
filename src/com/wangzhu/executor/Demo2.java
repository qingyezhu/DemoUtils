package com.wangzhu.executor;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class Demo2 {

    private static Set<String> allUriList = Collections
	    .synchronizedSet(new TreeSet<String>());

    public static void main(String[] args) throws Exception {
	HttpURLConnection.setFollowRedirects(false);
	String uri = args[0];
	ExecutorService service = Executors.newFixedThreadPool(5);
	service.execute(new Crawler(service, uri, uri));
	service.awaitTermination(300, TimeUnit.SECONDS);
	for (String element : allUriList) {
	    System.out.printf(">>%s<<%n", element);
	}
    }

    private static class Crawler implements Runnable {
	ExecutorService service;
	String uri;
	String base;

	public Crawler(ExecutorService service, String uri, String base) {
	    this.service = service;
	    this.uri = uri;
	    this.base = base;
	}

	@Override
	public void run() {
	    this.crawl(uri, base);
	}

	private void crawl(String uri, String base) {
	    if (uri == null) {
		return;
	    } else if (uri.startsWith("http")) {
		System.out.println("Crawling....." + uri + "/" + base);
		Reader reader;
		Document doc;
		try {
		    URLConnection conn = new URL(uri).openConnection();
		    reader = new InputStreamReader(conn.getInputStream());
		    EditorKit kit = new HTMLEditorKit();
		    doc = kit.createDefaultDocument();
		    doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
		    kit.read(reader, doc, 0);
		} catch (Exception e) {
		    return;
		}
		ElementIterator iter = new ElementIterator(doc);
		javax.swing.text.Element elem;
		while ((elem = iter.next()) != null) {
		    AttributeSet s = (AttributeSet) elem.getAttributes()
			    .getAttribute(HTML.Tag.A);
		    if (s != null) {
			String href = (String) s
				.getAttribute(HTML.Attribute.HREF);
			if (href == null) {
			    continue;
			} else if (href.startsWith("javascript:")) {
			    continue;
			} else if (href.startsWith("#")) {
			    continue;
			} else if ("".startsWith("mailto:")) {
			    continue;
			} else if (href.startsWith("ftp:")) {

			} else if (href.startsWith("https:")) {

			} else if (!href.startsWith("http:")) {
			    if (uri.endsWith("/")) {
				href = uri + href;
			    } else {
				int pos = uri.lastIndexOf("/");
				href = uri.substring(0, pos + 1) + href;
			    }
			}
			if (!allUriList.contains(href)) {
			    allUriList.add(href);
			    if (href.startsWith(base)) {
				service.execute(new Crawler(service, href, base));
			    }
			}
		    }
		}
	    }
	    ThreadPoolExecutor pool = (ThreadPoolExecutor) service;
	    if ((pool.getActiveCount() == 1) && (pool.getQueue().size() == 0)) {
		pool.shutdown();
	    }
	}

    }
}
