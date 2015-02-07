package com.wangzhu.poi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

public class WordConverter extends AbstractedConverter {

	@Override
	public void converter(String sourcePath, String targetPath,
			String onlinePath) throws Exception {
		String imgPath = null;
		final StringBuffer currentImgPath = new StringBuffer();
		if ((null != onlinePath) && !"".equals(onlinePath)) {
			currentImgPath.append(onlinePath);
		}
		currentImgPath.append("images/");

		int index = targetPath.lastIndexOf("/");
		imgPath = targetPath.substring(0, index + 1) + currentImgPath;
		File imgFile = new File(imgPath);
		if (!imgFile.exists()) {
			imgFile.mkdirs();
		}

		WordToHtmlConverter toHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.newDocument());
		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(
				sourcePath));
		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
		if (pics != null) {
			for (Picture pic : pics) {
				StringBuffer filePathAccum = new StringBuffer();
				filePathAccum.append(imgPath).append(pic.suggestFullFileName());
				FileOutputStream fos = new FileOutputStream(
						filePathAccum.toString());
				pic.writeImageContent(fos);
			}
		}
		toHtmlConverter.setPicturesManager(new PicturesManager() {

			@Override
			public String savePicture(byte[] content, PictureType pictureType,
					String suggestedName, float widthInches, float heightInches) {
				return currentImgPath + suggestedName;
			}
		});
		toHtmlConverter.processDocument(wordDocument);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult streamResult = new StreamResult(out);

		Document htmlDocument = toHtmlConverter.getDocument();
		DOMSource domSource = new DOMSource(htmlDocument);

		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING,
				AbstractedConverter.ENCODING);
		transformer.setOutputProperty(OutputKeys.INDENT,
				AbstractedConverter.INDENT);
		transformer.setOutputProperty(OutputKeys.METHOD,
				AbstractedConverter.METHOD);
		transformer.transform(domSource, streamResult);
		out.close();

		this.writeFile(out.toString(AbstractedConverter.ENCODING), targetPath);
	}

	public static void main(String[] args) {
		AbstractedConverter converter = ConverterFactory.getConverter("doc");
		try {
			converter.converter("e:/2.doc", "e:/2.html", "");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
