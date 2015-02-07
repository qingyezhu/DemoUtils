package com.wangzhu.poi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.usermodel.HSSFAnchor;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Document;

import com.wangzhu.UuidGenerator;

public class ExcelConverter extends AbstractedConverter {

	@Override
	void converter(String sourcePath, String targetPath, String onlinePath)
			throws Exception {

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

		ExcelToHtmlConverter toHtmlConverter = new ExcelToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.newDocument());
		toHtmlConverter.setOutputColumnHeaders(false);
		toHtmlConverter.setOutputRowNumbers(false);

		HSSFWorkbook workbook = new HSSFWorkbook(
				new FileInputStream(sourcePath));
		List<HSSFPictureData> pics = workbook.getAllPictures();
		if (pics != null) {
			final String imagePath = imgPath;
			final String relativeImagePath = currentImgPath.toString();
			toHtmlConverter.setExcelImageManager(new ExcelImageManager() {

				@Override
				public List<String> getImagePath(List<HSSFShape> children) {
					List<String> imgPaths = new ArrayList<String>();
					for (HSSFShape shape : children) {
						if (shape instanceof HSSFPicture) {
							HSSFPicture pic = (HSSFPicture) shape;
							// int picIndex = pic.getPictureIndex();
							HSSFPictureData picData = pic.getPictureData();
							String ext = picData.suggestFileExtension();
							byte[] data = picData.getData();
							String uid = UuidGenerator.getUUid();
							String path = imagePath + uid;
							String relativePath = relativeImagePath + uid;
							if ("jpeg".equals(ext)) {
								path += ".jpg";
								relativePath += ".jpg";
							} else if ("png".equals(ext)) {
								path += ".png";
								relativePath += ".png";
							}
							HSSFAnchor anchor = pic.getAnchor();
							if (anchor instanceof HSSFClientAnchor) {
								HSSFClientAnchor clientAnchor = (HSSFClientAnchor) anchor;
								System.out.println("row1: "
										+ clientAnchor.getRow1() + " row2: "
										+ clientAnchor.getRow2());
								System.out.println("col1: "
										+ clientAnchor.getCol1() + " col2: "
										+ clientAnchor.getCol2());
							}
							imgPaths.add(relativePath
									+ "@"
									+ Math.abs((anchor.getDx1() - anchor
											.getDx2()) / 1440.0f)
									+ "@"
									+ (Math.abs((anchor.getDy1() - anchor
											.getDy2())) / 1440.0f));
							FileOutputStream fos = null;
							try {
								fos = new FileOutputStream(path);
								fos.write(data);
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								if (null != fos) {
									try {
										fos.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
					return imgPaths;
				}
			});

		}

		toHtmlConverter.processWorkbook(workbook);
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

	public static void main(String[] args) throws Exception {
		AbstractedConverter converter = ConverterFactory.getConverter("xls");
		converter.converter("e:/4.xls", "e:/4.html", "");
	}
}
