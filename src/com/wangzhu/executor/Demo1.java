package com.wangzhu.executor;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class Demo1 {

    public static void main(String[] args) throws Exception {
	Set<String> uriList = new TreeSet<String>();
	HttpURLConnection.setFollowRedirects(false);
	EditorKit kit = new HTMLEditorKit();
	Document doc = kit.createDefaultDocument();
	doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
	String uri = args[0];
	Reader reader = null;

	if ((uri != null) && uri.startsWith("http")) {
	    URLConnection conn = new URL(uri).openConnection();
	    reader = new InputStreamReader(conn.getInputStream());
	} else {
	    System.err.println("Usage: java ListUrls");
	    System.exit(-1);
	}

	kit.read(reader, doc, 0);
	ElementIterator iter = new ElementIterator(doc);
	javax.swing.text.Element elem;
	while ((elem = iter.next()) != null) {
	    AttributeSet s = (AttributeSet) elem.getAttributes().getAttribute(
		    HTML.Tag.A);
	    if (s != null) {
		String href = (String) s.getAttribute(HTML.Attribute.HREF);
		if (href == null) {
		    continue;
		} else if (href.startsWith("javascript:")) {
		    continue;
		} else if (href.startsWith("https:")) {

		} else if (!href.startsWith("http:")) {
		    href = uri + href;
		}
		uriList.add(href);
	    }
	}
	for (String element : uriList) {
	    System.out.printf(">>%s<<%n", element);
	}
	System.exit(0);
    }

}
