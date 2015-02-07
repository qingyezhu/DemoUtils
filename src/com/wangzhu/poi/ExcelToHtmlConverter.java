/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package com.wangzhu.poi;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.converter.AbstractExcelConverter;
import org.apache.poi.hssf.converter.AbstractExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hwpf.converter.HtmlDocumentFacade;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Converts xls files (97-2007) to HTML file.
 * 
 * @author Sergey Vladimirov (vlsergey {at} gmail {dot} com)
 */

public class ExcelToHtmlConverter extends AbstractExcelConverter {

	private static final POILogger logger = POILogFactory
			.getLogger(ExcelToHtmlConverter.class);

	private ExcelImageManager excelImageManager;

	/**
	 * Java main() interface to interact with {@link ExcelToHtmlConverter}
	 * 
	 * <p>
	 * Usage: ExcelToHtmlConverter infile outfile
	 * </p>
	 * Where infile is an input .xls file ( Word 97-2007) which will be rendered
	 * as HTML into outfile
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.err
					.println("Usage: ExcelToHtmlConverter <inputFile.xls> <saveTo.html>");
			return;
		}

		System.out.println("Converting " + args[0]);
		System.out.println("Saving output to " + args[1]);
		try {
			Document doc = ExcelToHtmlConverter.process(new File(args[0]));

			FileWriter out = new FileWriter(args[1]);
			DOMSource domSource = new DOMSource(doc);
			StreamResult streamResult = new StreamResult(out);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer serializer = tf.newTransformer();
			// TODO set encoding from a command argument
			serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "no");
			serializer.setOutputProperty(OutputKeys.METHOD, "html");
			serializer.transform(domSource, streamResult);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts Excel file (97-2007) into HTML file.
	 * 
	 * @param xlsFile
	 *            file to process
	 * @return DOM representation of result HTML
	 */
	public static Document process(File xlsFile) throws Exception {
		final HSSFWorkbook workbook = AbstractExcelUtils.loadXls(xlsFile);
		ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.newDocument());
		excelToHtmlConverter.processWorkbook(workbook);
		return excelToHtmlConverter.getDocument();
	}

	private String cssClassContainerCell = null;

	private String cssClassContainerDiv = null;

	private String cssClassPrefixCell = "c";

	private String cssClassPrefixDiv = "d";

	private String cssClassPrefixRow = "r";

	private String cssClassPrefixTable = "t";

	private Map excelStyleToClass = new LinkedHashMap();

	private final HtmlDocumentFacade htmlDocumentFacade;

	private boolean useDivsToSpan = false;

	public ExcelToHtmlConverter(Document doc) {
		this.htmlDocumentFacade = new HtmlDocumentFacade(doc);
	}

	public ExcelToHtmlConverter(HtmlDocumentFacade htmlDocumentFacade) {
		this.htmlDocumentFacade = htmlDocumentFacade;
	}

	protected String buildStyle(HSSFWorkbook workbook, HSSFCellStyle cellStyle) {
		StringBuffer style = new StringBuffer();

		style.append("white-space:pre-wrap;");
		ExcelToHtmlUtils.appendAlign(style, cellStyle.getAlignment());

		if (cellStyle.getFillPattern() == 0) {
			// no fill
		} else if (cellStyle.getFillPattern() == 1) {
			final HSSFColor foregroundColor = cellStyle
					.getFillForegroundColorColor();
			if (foregroundColor != null) {
				style.append("background-color:"
						+ AbstractExcelUtils.getColor(foregroundColor) + ";");
			}
		} else {
			final HSSFColor backgroundColor = cellStyle
					.getFillBackgroundColorColor();
			if (backgroundColor != null) {
				style.append("background-color:"
						+ AbstractExcelUtils.getColor(backgroundColor) + ";");
			}
		}

		this.buildStyle_border(workbook, style, "top",
				cellStyle.getBorderTop(), cellStyle.getTopBorderColor());
		this.buildStyle_border(workbook, style, "right",
				cellStyle.getBorderRight(), cellStyle.getRightBorderColor());
		this.buildStyle_border(workbook, style, "bottom",
				cellStyle.getBorderBottom(), cellStyle.getBottomBorderColor());
		this.buildStyle_border(workbook, style, "left",
				cellStyle.getBorderLeft(), cellStyle.getLeftBorderColor());

		HSSFFont font = cellStyle.getFont(workbook);
		this.buildStyle_font(workbook, style, font);

		return style.toString();
	}

	private void buildStyle_border(HSSFWorkbook workbook, StringBuffer style,
			String type, short xlsBorder, short borderColor) {
		if (xlsBorder == CellStyle.BORDER_NONE) {
			return;
		}

		StringBuffer borderStyle = new StringBuffer();
		borderStyle.append(AbstractExcelUtils.getBorderWidth(xlsBorder));
		borderStyle.append(' ');
		borderStyle.append(AbstractExcelUtils.getBorderStyle(xlsBorder));

		final HSSFColor color = workbook.getCustomPalette().getColor(
				borderColor);
		if (color != null) {
			borderStyle.append(' ');
			borderStyle.append(AbstractExcelUtils.getColor(color));
		}

		style.append("border-" + type + ":" + borderStyle + ";");
	}

	void buildStyle_font(HSSFWorkbook workbook, StringBuffer style,
			HSSFFont font) {
		switch (font.getBoldweight()) {
		case Font.BOLDWEIGHT_BOLD:
			style.append("font-weight:bold;");
			break;
		case Font.BOLDWEIGHT_NORMAL:
			// by default, not not increase HTML size
			// style.append( "font-weight: normal; " );
			break;
		}

		final HSSFColor fontColor = workbook.getCustomPalette().getColor(
				font.getColor());
		if (fontColor != null) {
			style.append("color: " + AbstractExcelUtils.getColor(fontColor)
					+ "; ");
		}

		if (font.getFontHeightInPoints() != 0) {
			style.append("font-size:" + font.getFontHeightInPoints() + "pt;");
		}

		if (font.getItalic()) {
			style.append("font-style:italic;");
		}
	}

	public String getCssClassPrefixCell() {
		return this.cssClassPrefixCell;
	}

	public String getCssClassPrefixDiv() {
		return this.cssClassPrefixDiv;
	}

	public String getCssClassPrefixRow() {
		return this.cssClassPrefixRow;
	}

	public String getCssClassPrefixTable() {
		return this.cssClassPrefixTable;
	}

	@Override
	public Document getDocument() {
		return this.htmlDocumentFacade.getDocument();
	}

	protected String getStyleClassName(HSSFWorkbook workbook,
			HSSFCellStyle cellStyle) {
		final Short cellStyleKey = new Short(cellStyle.getIndex());

		String knownClass = (String) this.excelStyleToClass.get(cellStyleKey);
		if (knownClass != null) {
			return knownClass;
		}

		String cssStyle = this.buildStyle(workbook, cellStyle);
		String cssClass = this.htmlDocumentFacade.getOrCreateCssClass(
				this.cssClassPrefixCell, cssStyle);
		this.excelStyleToClass.put(cellStyleKey, cssClass);
		return cssClass;
	}

	public boolean isUseDivsToSpan() {
		return this.useDivsToSpan;
	}

	protected boolean processCell(HSSFCell cell, Element tableCellElement,
			int normalWidthPx, int maxSpannedWidthPx, float normalHeightPt) {
		final HSSFCellStyle cellStyle = cell.getCellStyle();

		String value;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			// XXX: enrich
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_FORMULA:
			switch (cell.getCachedFormulaResultType()) {
			case Cell.CELL_TYPE_STRING:
				HSSFRichTextString str = cell.getRichStringCellValue();
				if ((str != null) && (str.length() > 0)) {
					value = (str.toString());
				} else {
					value = ExcelToHtmlUtils.EMPTY;
				}
				break;
			case Cell.CELL_TYPE_NUMERIC:
				HSSFCellStyle style = cellStyle;
				if (style == null) {
					value = String.valueOf(cell.getNumericCellValue());
				} else {
					value = (this._formatter.formatRawCellContents(
							cell.getNumericCellValue(), style.getDataFormat(),
							style.getDataFormatString()));
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR:
				value = ErrorEval.getText(cell.getErrorCellValue());
				break;
			default:
				ExcelToHtmlConverter.logger.log(
						POILogger.WARN,
						"Unexpected cell cachedFormulaResultType ("
								+ cell.getCachedFormulaResultType() + ")");
				value = ExcelToHtmlUtils.EMPTY;
				break;
			}
			break;
		case Cell.CELL_TYPE_BLANK:
			value = ExcelToHtmlUtils.EMPTY;
			break;
		case Cell.CELL_TYPE_NUMERIC:
			value = this._formatter.formatCellValue(cell);
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			value = ErrorEval.getText(cell.getErrorCellValue());
			break;
		default:
			ExcelToHtmlConverter.logger.log(POILogger.WARN,
					"Unexpected cell type (" + cell.getCellType() + ")");
			return true;
		}

		final boolean noText = ExcelToHtmlUtils.isEmpty(value);
		final boolean wrapInDivs = !noText && this.isUseDivsToSpan()
				&& !cellStyle.getWrapText();

		final short cellStyleIndex = cellStyle.getIndex();
		if (cellStyleIndex != 0) {
			HSSFWorkbook workbook = cell.getRow().getSheet().getWorkbook();
			String mainCssClass = this.getStyleClassName(workbook, cellStyle);
			if (wrapInDivs) {
				tableCellElement.setAttribute("class", mainCssClass + " "
						+ this.cssClassContainerCell);
			} else {
				tableCellElement.setAttribute("class", mainCssClass);
			}

			if (noText) {
				/*
				 * if cell style is defined (like borders, etc.) but cell text
				 * is empty, add "&nbsp;" to output, so browser won't collapse
				 * and ignore cell
				 */
				value = "\u00A0";
			}
		}

		if (this.isOutputLeadingSpacesAsNonBreaking() && value.startsWith(" ")) {
			StringBuffer builder = new StringBuffer();
			for (int c = 0; c < value.length(); c++) {
				if (value.charAt(c) != ' ') {
					break;
				}
				builder.append('\u00a0');
			}

			if (value.length() != builder.length()) {
				builder.append(value.substring(builder.length()));
			}

			value = builder.toString();
		}

		Text text = this.htmlDocumentFacade.createText(value);

		if (wrapInDivs) {
			Element outerDiv = this.htmlDocumentFacade.createBlock();
			outerDiv.setAttribute("class", this.cssClassContainerDiv);

			Element innerDiv = this.htmlDocumentFacade.createBlock();
			StringBuffer innerDivStyle = new StringBuffer();
			innerDivStyle.append("position:absolute;min-width:");
			innerDivStyle.append(normalWidthPx);
			innerDivStyle.append("px;");
			if (maxSpannedWidthPx != Integer.MAX_VALUE) {
				innerDivStyle.append("max-width:");
				innerDivStyle.append(maxSpannedWidthPx);
				innerDivStyle.append("px;");
			}
			innerDivStyle.append("overflow:hidden;max-height:");
			innerDivStyle.append(normalHeightPt);
			innerDivStyle.append("pt;white-space:nowrap;");
			ExcelToHtmlUtils.appendAlign(innerDivStyle,
					cellStyle.getAlignment());
			this.htmlDocumentFacade.addStyleClass(outerDiv,
					this.cssClassPrefixDiv, innerDivStyle.toString());

			innerDiv.appendChild(text);
			outerDiv.appendChild(innerDiv);
			tableCellElement.appendChild(outerDiv);
		} else {
			tableCellElement.appendChild(text);
		}

		return ExcelToHtmlUtils.isEmpty(value) && (cellStyleIndex == 0);
	}

	protected void processColumnHeaders(HSSFSheet sheet, int maxSheetColumns,
			Element table) {
		Element tableHeader = this.htmlDocumentFacade.createTableHeader();
		table.appendChild(tableHeader);

		Element tr = this.htmlDocumentFacade.createTableRow();

		if (this.isOutputRowNumbers()) {
			// empty row at left-top corner
			tr.appendChild(this.htmlDocumentFacade.createTableHeaderCell());
		}

		for (int c = 0; c < maxSheetColumns; c++) {
			if (!this.isOutputHiddenColumns() && sheet.isColumnHidden(c)) {
				continue;
			}

			Element th = this.htmlDocumentFacade.createTableHeaderCell();
			String text = this.getColumnName(c);
			th.appendChild(this.htmlDocumentFacade.createText(text));
			tr.appendChild(th);
		}
		tableHeader.appendChild(tr);
	}

	/**
	 * Creates COLGROUP element with width specified for all columns. (Except
	 * first if <tt>{@link #isOutputRowNumbers()}==true</tt>)
	 */
	protected void processColumnWidths(HSSFSheet sheet, int maxSheetColumns,
			Element table) {
		// draw COLS after we know max column number
		Element columnGroup = this.htmlDocumentFacade.createTableColumnGroup();
		if (this.isOutputRowNumbers()) {
			columnGroup
					.appendChild(this.htmlDocumentFacade.createTableColumn());
		}
		for (int c = 0; c < maxSheetColumns; c++) {
			if (!this.isOutputHiddenColumns() && sheet.isColumnHidden(c)) {
				continue;
			}

			Element col = this.htmlDocumentFacade.createTableColumn();
			col.setAttribute("width", String.valueOf(AbstractExcelConverter
					.getColumnWidth(sheet, c)));
			columnGroup.appendChild(col);
		}
		table.appendChild(columnGroup);
	}

	protected void processDocumentInformation(
			SummaryInformation summaryInformation) {
		if (ExcelToHtmlUtils.isNotEmpty(summaryInformation.getTitle())) {
			this.htmlDocumentFacade.setTitle(summaryInformation.getTitle());
		}

		if (ExcelToHtmlUtils.isNotEmpty(summaryInformation.getAuthor())) {
			this.htmlDocumentFacade.addAuthor(summaryInformation.getAuthor());
		}

		if (ExcelToHtmlUtils.isNotEmpty(summaryInformation.getKeywords())) {
			this.htmlDocumentFacade.addKeywords(summaryInformation
					.getKeywords());
		}

		if (ExcelToHtmlUtils.isNotEmpty(summaryInformation.getComments())) {
			this.htmlDocumentFacade.addDescription(summaryInformation
					.getComments());
		}
	}

	/**
	 * @return maximum 1-base index of column that were rendered, zero if none
	 */
	protected int processRow(CellRangeAddress[][] mergedRanges, HSSFRow row,
			Element tableRowElement) {
		final HSSFSheet sheet = row.getSheet();
		final short maxColIx = row.getLastCellNum();
		if (maxColIx <= 0) {
			return 0;
		}

		final List emptyCells = new ArrayList(maxColIx);

		if (this.isOutputRowNumbers()) {
			Element tableRowNumberCellElement = this.htmlDocumentFacade
					.createTableHeaderCell();
			this.processRowNumber(row, tableRowNumberCellElement);
			emptyCells.add(tableRowNumberCellElement);
		}

		int maxRenderedColumn = 0;
		for (int colIx = 0; colIx < maxColIx; colIx++) {
			if (!this.isOutputHiddenColumns() && sheet.isColumnHidden(colIx)) {
				continue;
			}

			CellRangeAddress range = AbstractExcelUtils.getMergedRange(
					mergedRanges, row.getRowNum(), colIx);

			if ((range != null)
					&& ((range.getFirstColumn() != colIx) || (range
							.getFirstRow() != row.getRowNum()))) {
				continue;
			}

			HSSFCell cell = row.getCell(colIx);

			int divWidthPx = 0;
			if (this.isUseDivsToSpan()) {
				divWidthPx = AbstractExcelConverter
						.getColumnWidth(sheet, colIx);

				boolean hasBreaks = false;
				for (int nextColumnIndex = colIx + 1; nextColumnIndex < maxColIx; nextColumnIndex++) {
					if (!this.isOutputHiddenColumns()
							&& sheet.isColumnHidden(nextColumnIndex)) {
						continue;
					}

					if ((row.getCell(nextColumnIndex) != null)
							&& !this.isTextEmpty(row.getCell(nextColumnIndex))) {
						hasBreaks = true;
						break;
					}

					divWidthPx += AbstractExcelConverter.getColumnWidth(sheet,
							nextColumnIndex);
				}

				if (!hasBreaks) {
					divWidthPx = Integer.MAX_VALUE;
				}
			}

			Element tableCellElement = this.htmlDocumentFacade
					.createTableCell();

			if (range != null) {
				if (range.getFirstColumn() != range.getLastColumn()) {
					tableCellElement.setAttribute("colspan", String
							.valueOf((range.getLastColumn() - range
									.getFirstColumn()) + 1));
				}
				if (range.getFirstRow() != range.getLastRow()) {
					tableCellElement.setAttribute("rowspan",
							String.valueOf((range.getLastRow() - range
									.getFirstRow()) + 1));
				}
			}

			boolean emptyCell;
			if (cell != null) {
				emptyCell = this.processCell(cell, tableCellElement,
						AbstractExcelConverter.getColumnWidth(sheet, colIx),
						divWidthPx, row.getHeight() / 20f);
			} else {
				emptyCell = true;
			}

			if (emptyCell) {
				emptyCells.add(tableCellElement);
			} else {
				for (Iterator iterator = emptyCells.iterator(); iterator
						.hasNext();) {
					Element emptyCellElement = (Element) iterator.next();
					tableRowElement.appendChild(emptyCellElement);
				}
				emptyCells.clear();

				tableRowElement.appendChild(tableCellElement);
				maxRenderedColumn = colIx;
			}
		}

		return maxRenderedColumn + 1;
	}

	protected void processRowNumber(HSSFRow row,
			Element tableRowNumberCellElement) {
		tableRowNumberCellElement.setAttribute("class", "rownumber");
		Text text = this.htmlDocumentFacade.createText(this.getRowName(row));
		tableRowNumberCellElement.appendChild(text);
	}

	protected void processSheet(HSSFSheet sheet) {
		// this.processSheetHeader(this.htmlDocumentFacade.getBody(), sheet);

		final int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		if (physicalNumberOfRows <= 0) {
			return;
		}

		Element table = this.htmlDocumentFacade.createTable();
		this.htmlDocumentFacade.addStyleClass(table, this.cssClassPrefixTable,
				"border-collapse:collapse;border-spacing:0;");

		Element tableBody = this.htmlDocumentFacade.createTableBody();

		final CellRangeAddress[][] mergedRanges = ExcelToHtmlUtils
				.buildMergedRangesMap(sheet);

		final List emptyRowElements = new ArrayList(physicalNumberOfRows);
		int maxSheetColumns = 1;
		for (int r = sheet.getFirstRowNum(); r <= sheet.getLastRowNum(); r++) {
			HSSFRow row = sheet.getRow(r);

			if (row == null) {
				continue;
			}

			if (!this.isOutputHiddenRows() && row.getZeroHeight()) {
				continue;
			}

			Element tableRowElement = this.htmlDocumentFacade.createTableRow();
			this.htmlDocumentFacade.addStyleClass(tableRowElement,
					this.cssClassPrefixRow, "height:" + (row.getHeight() / 20f)
							+ "pt;");

			int maxRowColumnNumber = this.processRow(mergedRanges, row,
					tableRowElement);

			if (maxRowColumnNumber == 0) {
				emptyRowElements.add(tableRowElement);
			} else {
				if (!emptyRowElements.isEmpty()) {
					for (Iterator iterator = emptyRowElements.iterator(); iterator
							.hasNext();) {
						Element emptyRowElement = (Element) iterator.next();
						tableBody.appendChild(emptyRowElement);
					}
					emptyRowElements.clear();
				}

				tableBody.appendChild(tableRowElement);
			}
			maxSheetColumns = Math.max(maxSheetColumns, maxRowColumnNumber);
		}

		this.processColumnWidths(sheet, maxSheetColumns, table);

		if (this.isOutputColumnHeaders()) {
			this.processColumnHeaders(sheet, maxSheetColumns, table);
		}

		table.appendChild(tableBody);

		this.htmlDocumentFacade.getBody().appendChild(table);

		if (null != this.getExcelImageManager()) {

			table = this.htmlDocumentFacade.createTable();
			this.htmlDocumentFacade.addStyleClass(table,
					this.cssClassPrefixTable,
					"border-collapse:collapse;border-spacing:0;");

			tableBody = this.htmlDocumentFacade.createTableBody();
			List<String> urlPaths = this.getExcelImageManager().getImagePath(
					sheet.getDrawingPatriarch().getChildren());
			if ((urlPaths != null) && (urlPaths.size() != 0)) {
				Document document = this.htmlDocumentFacade.getDocument();

				for (int i = 0, size = urlPaths.size(); i < size; i++) {
					Element tableRowElement = this.htmlDocumentFacade
							.createTableRow();
					String[] urlPathArr = urlPaths.get(i).split("@");
					Element result = document.createElement("img");
					result.setAttribute("src", urlPathArr[0]);
					String imageWidth = urlPathArr[1];
					String imageHeight = urlPathArr[2];
					result.setAttribute("style", "width:" + imageWidth
							+ "in;height:" + imageHeight
							+ "in;vertical-align:text-bottom;");

					Element tableCellElement = this.htmlDocumentFacade
							.createTableCell();
					tableCellElement.appendChild(result);
					tableRowElement.appendChild(tableCellElement);
					tableBody.appendChild(tableRowElement);
				}
				table.appendChild(tableBody);
				this.htmlDocumentFacade.getBody().appendChild(table);
			}
		}

	}

	protected void processSheetHeader(Element htmlBody, HSSFSheet sheet) {
		Element h2 = this.htmlDocumentFacade.createHeader2();
		h2.appendChild(this.htmlDocumentFacade.createText(sheet.getSheetName()));
		htmlBody.appendChild(h2);
	}

	public void processWorkbook(HSSFWorkbook workbook) {
		final SummaryInformation summaryInformation = workbook
				.getSummaryInformation();
		if (summaryInformation != null) {
			this.processDocumentInformation(summaryInformation);
		}

		if (this.isUseDivsToSpan()) {
			// prepare CSS classes for later usage
			this.cssClassContainerCell = this.htmlDocumentFacade
					.getOrCreateCssClass(this.cssClassPrefixCell,
							"padding:0;margin:0;align:left;vertical-align:top;");
			this.cssClassContainerDiv = this.htmlDocumentFacade
					.getOrCreateCssClass(this.cssClassPrefixDiv,
							"position:relative;");
		}

		for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
			HSSFSheet sheet = workbook.getSheetAt(s);
			this.processSheet(sheet);
		}

		this.htmlDocumentFacade.updateStylesheet();
	}

	public void setCssClassPrefixCell(String cssClassPrefixCell) {
		this.cssClassPrefixCell = cssClassPrefixCell;
	}

	public void setCssClassPrefixDiv(String cssClassPrefixDiv) {
		this.cssClassPrefixDiv = cssClassPrefixDiv;
	}

	public void setCssClassPrefixRow(String cssClassPrefixRow) {
		this.cssClassPrefixRow = cssClassPrefixRow;
	}

	public void setCssClassPrefixTable(String cssClassPrefixTable) {
		this.cssClassPrefixTable = cssClassPrefixTable;
	}

	/**
	 * Allows converter to wrap content into two additional DIVs with tricky
	 * styles, so it will wrap across empty cells (like in Excel).
	 * <p>
	 * <b>Warning:</b> after enabling this mode do not serialize result HTML
	 * with INDENT=YES option, because line breaks will make additional
	 * (unwanted) changes
	 */
	public void setUseDivsToSpan(boolean useDivsToSpan) {
		this.useDivsToSpan = useDivsToSpan;
	}

	public ExcelImageManager getExcelImageManager() {
		return this.excelImageManager;
	}

	public void setExcelImageManager(ExcelImageManager excelImageManager) {
		this.excelImageManager = excelImageManager;
	}
}
