package com.woodare.framework.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @corpor: 公司：深讯信科
 * @author: 作者：谭又中
 * @explain: 释义：数据导出到Excel
 * @version: 日期：2012-9-14 下午05:50:06
 */
@SuppressWarnings("deprecation")
public class ExcelUtils {
	private static HSSFWorkbook wb;

	private static CellStyle headStyle;         // 表头行样式
	private static Font headFont;               // 表头行字体
	private static CellStyle contentStyle;     // 内容行样式
	private static Font contentFont;            // 内容行字体

	/**
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @Description: 将Map里的集合对象数据输出Excel数据流
	 */
	public static void export2Excel(ExportSetInfo setInfo) throws IOException, IllegalArgumentException, IllegalAccessException {
		export2ExcelGeneric(setInfo);
	}

	/**
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @Description: 将Map里的集合对象数据输出Excel数据流
	 */
	public static <T> void export2ExcelGeneric(GenericExportSetInfo<T> setInfo) throws IOException, IllegalArgumentException, IllegalAccessException {
		init();
		Set<Entry<String, List<?>>> set = setInfo.getObjsMap().entrySet();
		String[] sheetNames = new String[setInfo.getObjsMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, List<?>> entry : set) {
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		HSSFSheet[] sheets = getSheets(setInfo.getObjsMap().size(), sheetNames);
		int sheetNum = 0;
		for (Entry<String, List<?>> entry : set) {
			// Sheet
			List<?> objs = entry.getValue();
			// // 标题行
			// createTableTitleRow(setInfo, sheets, sheetNum);
			// // 日期行
			// createTableDateRow(setInfo, sheets, sheetNum);
			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum);
			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
			int rowNum = 1;
			for (Object tobj : objs) {
				@SuppressWarnings("unchecked")
				T obj = (T) tobj;
				HSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
				contentRow.setHeight((short) 300);
				HSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames().get(sheetNum).length);
				int cellNum = 1; // 去掉一列序号，因此从1开始
				if (fieldNames != null) {
					for (int num = 0; num < fieldNames.length; num++) {
						Object value = null;
						if (setInfo.getFunctionMap() != null && setInfo.getFunctionMap().containsKey(fieldNames[num])) {
							value = setInfo.getFunctionMap().get(fieldNames[num]).value(obj);
						}
						else {
							value = ReflectionUtils.invokeGetterMethod(obj, fieldNames[num]);
						}
						CellFormatter fomater = setInfo.getFomatterMap().get(fieldNames[num]);
						Object s = null;
						if (fomater != null) {
							value = fomater.format(value);
						}
						s = value == null ? "" : value;
						if (s instanceof BigDecimal) {
							cells[cellNum].setCellValue(((BigDecimal) s).setScale(2, BigDecimal.ROUND_UP).toString());
						}
						else if (s instanceof Long) {
							cells[cellNum].setCellValue((long) s);
						}
						else if (s instanceof Integer) {
							cells[cellNum].setCellValue((int) s);
						}
						else if (s instanceof Number) {
							cells[cellNum].setCellValue((double) s);
						}
						else {
							cells[cellNum].setCellValue(s.toString());
						}
						cellNum++;
					}
				}
				rowNum++;
			}
			// adjustColumnSize(sheets, sheetNum, fieldNames); // 自动调整列宽
			sheetNum++;
		}
		wb.write(setInfo.getOut());
	}

	/**
	 * @Description: 初始化
	 */
	private static void init() {
		wb = new HSSFWorkbook();

		headStyle = wb.createCellStyle();
		headFont = wb.createFont();
		contentStyle = wb.createCellStyle();
		contentFont = wb.createFont();

		initHeadCellStyle();
		initHeadFont();
		initContentCellStyle();
		initContentFont();
	}

	/**
	 * @Description: 自动调整列宽
	 */
	@SuppressWarnings("unused")
	private static void adjustColumnSize(HSSFSheet[] sheets, int sheetNum, String[] fieldNames) {
		for (int i = 0; i < fieldNames.length + 1; i++) {
			sheets[sheetNum].autoSizeColumn(i, true);
		}
	}

	/**
	 * @Description: 创建表头行(需合并单元格)
	 */
	private static <T> void creatTableHeadRow(GenericExportSetInfo<T> setInfo, HSSFSheet[] sheets, int sheetNum) {
		// 表头
		HSSFRow headRow = sheets[sheetNum].createRow(0);
		headRow.setHeight((short) 350);
		// 序号列
		HSSFCell snCell = headRow.createCell(0);
		snCell.setCellStyle(headStyle);
		snCell.setCellValue("序号");
		// 列头名称
		for (int num = 1, len = setInfo.getHeadNames().get(sheetNum).length; num <= len; num++) {
			String[] headerNames = setInfo.getHeadNames().get(sheetNum)[num - 1].split(",");
			HSSFCell headCell = headRow.createCell(num);
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(headerNames[0]);

			if (headerNames.length > 1) {
				int width = Integer.parseInt(headerNames[1]) * 256;
				sheets[sheetNum].setColumnWidth(num, width);
			}
			else if (headerNames[0].length() > 1) {
				int width = headerNames[0].getBytes().length;
				if (width > 20) {
					width = 20;
				}
				else if (width < 4) {
					width = 4;
				}
				sheets[sheetNum].setColumnWidth(num, (int) (width * 256 * 1.5));
			}
		}
	}

	/**
	 * @Description: 创建所有的Sheet
	 */
	private static HSSFSheet[] getSheets(int num, String[] names) {
		HSSFSheet[] sheets = new HSSFSheet[num];
		for (int i = 0; i < num; i++) {
			sheets[i] = wb.createSheet(names[i]);
		}
		return sheets;
	}

	/**
	 * @Description: 创建内容行的每一列(附加一列序号)
	 */
	private static HSSFCell[] getCells(HSSFRow contentRow, int num) {
		HSSFCell[] cells = new HSSFCell[num + 1];

		for (int i = 0, len = cells.length; i < len; i++) {
			cells[i] = contentRow.createCell(i);
			cells[i].setCellStyle(contentStyle);
		}
		// 设置序号列值，因为出去标题行和日期行，所有-2
		cells[0].setCellValue(contentRow.getRowNum());

		return cells;
	}

	/**
	 * @Description: 初始化表头行样式
	 */
	private static void initHeadCellStyle() {
		headStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headStyle.setFont(headFont);
		headStyle.setFillBackgroundColor(IndexedColors.DARK_YELLOW.index);
		headStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		headStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headStyle.setBorderRight(CellStyle.BORDER_THIN);
		headStyle.setTopBorderColor(IndexedColors.BLUE_GREY.index);
		headStyle.setBottomBorderColor(IndexedColors.BLUE_GREY.index);
		headStyle.setLeftBorderColor(IndexedColors.BLUE_GREY.index);
		headStyle.setRightBorderColor(IndexedColors.BLUE_GREY.index);
	}

	/**
	 * @Description: 初始化内容行样式
	 */
	private static void initContentCellStyle() {
		contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		contentStyle.setFont(contentFont);
		// contentStyle.setBorderTop(CellStyle.BORDER_THIN);
		// contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
		// contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
		// contentStyle.setBorderRight(CellStyle.BORDER_THIN);
		// contentStyle.setTopBorderColor(IndexedColors.BLUE.index);
		// contentStyle.setBottomBorderColor(IndexedColors.BLUE.index);
		// contentStyle.setLeftBorderColor(IndexedColors.BLUE.index);
		// contentStyle.setRightBorderColor(IndexedColors.BLUE.index);
		contentStyle.setWrapText(true); // 字段换行
	}

	/**
	 * @Description: 初始化表头行字体
	 */
	private static void initHeadFont() {
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 11);
		headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headFont.setCharSet(Font.DEFAULT_CHARSET);
		headFont.setColor(IndexedColors.DARK_BLUE.index);
	}

	/**
	 * @Description: 初始化内容行字体
	 */
	private static void initContentFont() {
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		contentFont.setCharSet(Font.DEFAULT_CHARSET);
		contentFont.setColor(IndexedColors.BLACK.index);
	}

	public static interface CellFormatter {
		String format(Object o);
	}

	public static interface CellFunction extends GenericCellFunction<Object> {
	}

	public static interface GenericCellFunction<T> {
		String value(T o);
	}

	/**
	 * @Description: 封装Excel导出的设置信息
	 * @author: 谭又中
	 */
	public static class ExportSetInfo extends GenericExportSetInfo<Object> {

	}

	/**
	 * @Description: 封装Excel导出的设置信息
	 * @author: 谭又中
	 */
	public static class GenericExportSetInfo<T> {
		private LinkedHashMap<String, List<?>> objsMap;

		private String[] titles;

		private List<String[]> headNames;

		private List<String[]> fieldNames;

		private OutputStream out;

		private LinkedHashMap<String, CellFormatter> fomatterMap = new LinkedHashMap<String, CellFormatter>();;

		private LinkedHashMap<String, GenericCellFunction<T>> functionMap = new LinkedHashMap<String, GenericCellFunction<T>>();;

		public LinkedHashMap<String, List<?>> getObjsMap() {
			return objsMap;
		}

		/**
		 * @param objMap
		 *            导出数据
		 *            泛型
		 *            String : 代表sheet名称
		 *            List : 代表单个sheet里的所有行数据
		 */
		public void setObjsMap(LinkedHashMap<String, List<?>> objsMap) {
			this.objsMap = objsMap;
		}

		public List<String[]> getFieldNames() {
			return fieldNames;
		}

		/**
		 * @param clazz
		 *            对应每个sheet里的每行数据的对象的属性名称
		 */
		public void setFieldNames(List<String[]> fieldNames) {
			this.fieldNames = fieldNames;
		}

		public String[] getTitles() {
			return titles;
		}

		/**
		 * @param titles
		 *            对应每个sheet里的标题，即顶部大字
		 */
		public void setTitles(String[] titles) {
			this.titles = titles;
		}

		public List<String[]> getHeadNames() {
			return headNames;
		}

		/**
		 * @param headNames
		 *            对应每个页签的表头的每一列的名称
		 */
		public void setHeadNames(List<String[]> headNames) {
			this.headNames = headNames;
		}

		public OutputStream getOut() {
			return out;
		}

		/**
		 * @param out
		 *            Excel数据将输出到该输出流
		 */
		public void setOut(OutputStream out) {
			this.out = out;
		}

		/**
		 * @return the fomatterMap
		 */
		public LinkedHashMap<String, CellFormatter> getFomatterMap() {
			return fomatterMap;
		}

		/**
		 * @param fomatterMap
		 *            the fomatterMap to set
		 */
		public void setFomatterMap(LinkedHashMap<String, CellFormatter> fomatterMap) {
			this.fomatterMap = fomatterMap;
		}

		/**
		 * @return the functionMap
		 */
		public LinkedHashMap<String, GenericCellFunction<T>> getFunctionMap() {
			return functionMap;
		}

		/**
		 * @param functionMap
		 *            the functionMap to set
		 */
		public void setFunctionMap(LinkedHashMap<String, GenericCellFunction<T>> functionMap) {
			this.functionMap = functionMap;
		}

		public void addFomatter(String name, CellFormatter formatter) {
			this.fomatterMap.put(name, formatter);
		}

		public void addFunction(String name, GenericCellFunction<T> function) {
			this.functionMap.put(name, function);
		}

	}
}