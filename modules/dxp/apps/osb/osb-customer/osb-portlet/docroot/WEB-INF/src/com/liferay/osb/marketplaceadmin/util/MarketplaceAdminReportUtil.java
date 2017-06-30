/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.marketplaceadmin.util;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.marketplace.util.MarketplaceUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.AssetStatsConstants;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author Douglas Wong
 * @author Joan Kim
 */
public class MarketplaceAdminReportUtil {

	public static final int MARKETPLACE_XLS_ROW_LIMIT = 65534;

	public static File getAssetReceiptsXLS(List<AssetReceipt> assetReceipts)
		throws Exception {

		if (assetReceipts.size() > MARKETPLACE_XLS_ROW_LIMIT) {
			throw new UnsupportedOperationException();
		}

		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

		HSSFSheet hssfSheet = hssfWorkbook.createSheet("Customers");

		addAssetReceiptsXLSHeader(hssfWorkbook, hssfSheet);

		CellStyle cellStyle = hssfWorkbook.createCellStyle();

		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		addAssetReceiptsXLSRows(
			hssfWorkbook, hssfSheet, cellStyle, assetReceipts);

		File file = FileUtil.createTempFile("xls");

		OutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(file);

			hssfWorkbook.write(outputStream);
		}
		finally {
			StreamUtil.cleanUp(outputStream);
		}

		return file;
	}

	public static File getAssetStatsXLS(
			Calendar startCalendar, Calendar endCalendar,
			LinkedHashMap<String, Object> params, String orderByCol,
			String orderByType)
		throws Exception {

		List<JSONObject> jsonObjects =
			MarketplaceAdminUtil.getAssetStatsJSONObjects(
				startCalendar, endCalendar, params, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, orderByCol, orderByType);

		if (jsonObjects.size() > MARKETPLACE_XLS_ROW_LIMIT) {
			throw new UnsupportedOperationException();
		}

		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

		HSSFSheet hssfSheet = hssfWorkbook.createSheet("Metrics");

		addAssetStatsXLSHeader(
			hssfWorkbook, hssfSheet, startCalendar, endCalendar, params);

		CellStyle cellStyle = hssfWorkbook.createCellStyle();

		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		addAssetStatsXLSRows(hssfWorkbook, hssfSheet, cellStyle, jsonObjects);

		File file = FileUtil.createTempFile("xls");

		OutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(file);

			hssfWorkbook.write(outputStream);
		}
		finally {
			StreamUtil.cleanUp(outputStream);
		}

		return file;
	}

	protected static void addAssetReceiptsXLSColumn(
			HSSFWorkbook hssfWorkbook, Row row, int column, CellStyle cellStyle,
			AssetReceipt assetReceipt)
		throws PortalException, SystemException {

		Cell cell = row.createCell(column);

		if (column == 0) {
			cell.setCellStyle(cellStyle);

			cell.setCellValue(assetReceipt.getCreateDate());
		}
		else if (column == 1) {
			AssetEntry assetEntry = assetReceipt.getAssetEntry();

			cell.setCellValue(assetEntry.getTitle());
		}
		else if (column == 2) {
			cell.setCellValue(PortalUtil.getUserName(assetReceipt));
		}
		else if (column == 3) {
			cell.setCellValue(assetReceipt.getLegalEntityName());
		}
		else if (column == 4) {
			User assetReceiptUser = UserLocalServiceUtil.fetchUser(
				assetReceipt.getUserId());

			if (assetReceiptUser != null) {
				cell.setCellValue(assetReceiptUser.getEmailAddress());
			}
		}
		else if (column == 5) {
			if (assetReceipt.isOwnerCorpProject()) {
				cell.setCellValue("Project");
			}
			else if (assetReceipt.isOwnerUser()) {
				cell.setCellValue("User");
			}
		}
		else if (column == 6) {
			cell.setCellValue(assetReceipt.getOwnerName());
		}
	}

	protected static void addAssetReceiptsXLSHeader(
		HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet) {

		Row row = hssfSheet.createRow(0);

		CellStyle cellStyle = hssfWorkbook.createCellStyle();

		Font font = hssfWorkbook.createFont();

		font.setBoldweight(Font.BOLDWEIGHT_BOLD);

		cellStyle.setFont(font);

		for (int i = 0; i < 7; i++) {
			Cell cell = row.createCell(i);

			cell.setCellStyle(cellStyle);

			if (i == 0) {
				cell.setCellValue("Purchase Date");
			}
			else if (i == 1) {
				cell.setCellValue("App");
			}
			else if (i == 2) {
				cell.setCellValue("User");
			}
			else if (i == 3) {
				cell.setCellValue("Legal Entity Name");
			}
			else if (i == 4) {
				cell.setCellValue("Email Address");
			}
			else if (i == 5) {
				cell.setCellValue("Owner Type");
			}
			else if (i == 6) {
				cell.setCellValue("Owner");
			}
		}
	}

	protected static void addAssetReceiptsXLSRows(
			HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, CellStyle cellStyle,
			List<AssetReceipt> assetReceipts)
		throws PortalException, SystemException {

		for (int i = 0; i < assetReceipts.size(); i++) {
			AssetReceipt assetReceipt = assetReceipts.get(i);

			Row row = hssfSheet.createRow(i + 1);

			for (int j = 0; j <= 6; j++) {
				addAssetReceiptsXLSColumn(
					hssfWorkbook, row, j, cellStyle, assetReceipt);
			}
		}

		for (int j = 0; j <= 6; j++) {
			hssfSheet.autoSizeColumn(j);
		}
	}

	protected static void addAssetStatsXLSColumn(
			HSSFWorkbook hssfWorkbook, Row row, int column, CellStyle cellStyle,
			JSONObject jsonObject)
		throws PortalException, SystemException {

		long classPK = jsonObject.getLong("classPK");

		AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(classPK);

		if (appEntry == null) {
			return;
		}

		Cell cell = row.createCell(column);

		if (column == 0) {
			cell.setCellStyle(cellStyle);

			String title = StringPool.BLANK;

			if (appEntry != null) {
				title = appEntry.getTitle();
			}

			cell.setCellValue(title);
		}
		else if (column == 1) {
			String developerEntryName = StringPool.BLANK;

			if (appEntry != null) {
				developerEntryName = HtmlUtil.escape(
					appEntry.getDeveloperEntryName());
			}

			cell.setCellValue(developerEntryName);
		}
		else if (column == 2) {
			String subcategoryName = StringPool.BLANK;

			if (appEntry != null) {
				subcategoryName = MarketplaceUtil.getSubcategoryName(
					appEntry.getAssetEntry());
			}

			cell.setCellValue(subcategoryName);
		}
		else if (column == 3) {
			long totalDownloadCount = jsonObject.getLong(
				AssetStatsConstants.STATS_TYPE_DOWNLOAD_COUNT_KEY);

			cell.setCellValue(String.valueOf(totalDownloadCount));
		}
		else if (column == 4) {
			long totalViewCount = jsonObject.getLong(
				AssetStatsConstants.STATS_TYPE_VIEW_COUNT_KEY);

			cell.setCellValue(String.valueOf(totalViewCount));
		}
	}

	protected static void addAssetStatsXLSHeader(
		HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, Calendar startCalendar,
		Calendar endCalendar, LinkedHashMap<String, Object> params) {

		CellStyle cellStyle = hssfWorkbook.createCellStyle();

		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

		Row row = hssfSheet.createRow(0);

		addAssetStatsXLSHeaderCell(row, 0, cellStyle, "Start Date");
		addAssetStatsXLSHeaderCell(row, 1, cellStyle, startCalendar);

		row = hssfSheet.createRow(1);

		addAssetStatsXLSHeaderCell(row, 0, cellStyle, "End Date");
		addAssetStatsXLSHeaderCell(row, 1, cellStyle, endCalendar);

		row = hssfSheet.createRow(2);

		addAssetStatsXLSHeaderCell(row, 0, cellStyle, "Hide Liferay Apps");

		long notDeveloperEntryId = GetterUtil.getLong(
			params.get("notDeveloperEntryId"));

		Boolean hideLiferayApps =
			(notDeveloperEntryId ==
				OSBConstants.DEVELOPER_ENTRY_LIFERAY_INC_ID);

		addAssetStatsXLSHeaderCell(row, 1, cellStyle, hideLiferayApps);

		Font font = hssfWorkbook.createFont();

		font.setBoldweight(Font.BOLDWEIGHT_BOLD);

		cellStyle.setFont(font);

		row = hssfSheet.createRow(4);

		for (int i = 0; i < 7; i++) {
			Cell cell = row.createCell(i);

			cell.setCellStyle(cellStyle);

			if (i == 0) {
				cell.setCellValue("Title");
			}
			else if (i == 1) {
				cell.setCellValue("Developer");
			}
			else if (i == 2) {
				cell.setCellValue("Category");
			}
			else if (i == 3) {
				cell.setCellValue("Downloads");
			}
			else if (i == 4) {
				cell.setCellValue("Views");
			}
		}
	}

	protected static void addAssetStatsXLSHeaderCell(
		Row row, int column, CellStyle cellStyle, Object cellValue) {

		Cell cell = row.createCell(column);

		cell.setCellStyle(cellStyle);

		if (cellValue instanceof Calendar) {
			cell.setCellValue((Calendar)cellValue);
		}
		else {
			cell.setCellValue(cellValue.toString());
		}
	}

	protected static void addAssetStatsXLSRows(
			HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, CellStyle cellStyle,
			List<JSONObject> jsonObjects)
		throws PortalException, SystemException {

		for (int i = 0; i < jsonObjects.size(); i++) {
			JSONObject jsonObject = jsonObjects.get(i);

			Row row = hssfSheet.createRow(i + 5);

			for (int j = 0; j <= 6; j++) {
				addAssetStatsXLSColumn(
					hssfWorkbook, row, j, cellStyle, jsonObject);
			}
		}

		for (int j = 0; j <= 6; j++) {
			hssfSheet.autoSizeColumn(j);
		}
	}

}