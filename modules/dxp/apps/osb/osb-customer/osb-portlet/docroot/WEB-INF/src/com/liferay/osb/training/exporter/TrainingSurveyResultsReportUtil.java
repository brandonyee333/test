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

package com.liferay.osb.training.exporter;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;
import com.liferay.osb.service.UserProfileHistoryLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.storage.StorageEngineUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author Haote Chou
 */
public class TrainingSurveyResultsReportUtil {

	public static File getAllSurveyResultsXLS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		List<DDMStructure> ddmStructures =
			DDMStructureLocalServiceUtil.getClassStructures(
				PortalUtil.getClassNameId(TrainingEvent.class));

		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

		processDDMStructures(ddmStructures, hssfWorkbook);

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

	protected static void addSurveyResultSheetColumnTitleRow(
		DDMStructure ddmStructure, HSSFSheet hssfSheet,
		HSSFWorkbook hssfWorkbook, Map<String, Integer> indexCounterMap) {

		Font font = hssfWorkbook.createFont();

		font.setBoldweight(Font.BOLDWEIGHT_BOLD);

		CellStyle cellStyle = hssfWorkbook.createCellStyle();

		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFont(font);
		cellStyle.setWrapText(true);

		Row row = hssfSheet.createRow(
			incrementCounter(indexCounterMap, "rowIndex"));

		int columnIndex = 0;

		addSurveyResultSheetRowCell(row, columnIndex, cellStyle, "Course");
		addSurveyResultSheetRowCell(row, ++columnIndex, cellStyle, "Location");
		addSurveyResultSheetRowCell(
			row, ++columnIndex, cellStyle, "Start Date");
		addSurveyResultSheetRowCell(row, ++columnIndex, cellStyle, "Trainer");

		indexCounterMap.put("trainingEventDetailsColumnCount", columnIndex);

		addSurveyResultSheetRowCell(row, ++columnIndex, cellStyle, "Trainee");
		addSurveyResultSheetRowCell(row, ++columnIndex, cellStyle, "Email");
		addSurveyResultSheetRowCell(row, ++columnIndex, cellStyle, "Company");

		Map<String, Map<String, String>> fieldsMap =
			ddmStructure.getFieldsMap();

		for (Map<String, String> fields : fieldsMap.values()) {
			addSurveyResultSheetRowCell(
				row, ++columnIndex, cellStyle,
				fields.get(FieldConstants.LABEL));
		}

		indexCounterMap.put("totalColumnCount", columnIndex);
	}

	protected static void addSurveyResultSheetNameRow(
		DDMStructure ddmStructure, HSSFSheet hssfSheet,
		HSSFWorkbook hssfWorkbook, Map<String, Integer> indexCounterMap) {

		Font font = hssfWorkbook.createFont();

		font.setBoldweight(Font.BOLDWEIGHT_BOLD);

		CellStyle cellStyle = hssfWorkbook.createCellStyle();

		cellStyle.setFont(font);
		cellStyle.setWrapText(false);

		Row row = hssfSheet.createRow(indexCounterMap.get("rowIndex"));

		addSurveyResultSheetRowCell(row, 0, cellStyle, "Survey:");
		addSurveyResultSheetRowCell(
			row, 1, cellStyle, ddmStructure.getName(LocaleUtil.getDefault()));
	}

	protected static void addSurveyResultSheetRowCell(
		Row row, int column, CellStyle cellStyle, Object cellValue) {

		Cell cell = row.createCell(column);

		if (cellValue instanceof Date) {
			cell.setCellValue(
				DateUtil.getDate((Date)cellValue, "MMMMM dd, yyyy hh:mm:ss a z",
				LocaleUtil.getDefault()));
		}
		else if (cellValue instanceof Double) {
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));

			cell.setCellValue((Double)cellValue);
		}
		else {
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);

			cell.setCellValue(cellValue.toString());
		}

		cell.setCellStyle(cellStyle);
	}

	protected static DynamicQuery buildDynamicQuery(long structureId) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDLRecordSet.class, PortalClassLoaderUtil.getClassLoader());

		Property ddmStructureIdProperty = PropertyFactoryUtil.forName(
			"DDMStructureId");

		return dynamicQuery.add(ddmStructureIdProperty.eq(structureId));
	}

	protected static int incrementCounter(
		Map<String, Integer> indexCounterMap, String key) {

		indexCounterMap.put(key, indexCounterMap.get(key) + 1);

		return indexCounterMap.get(key);
	}

	protected static boolean isRadioButton(String name) {
		if (StringUtil.matchesIgnoreCase(name, "radio")) {
			return true;
		}

		return false;
	}

	protected static void processDDLRecords(
			List<DDLRecord> ddlRecords, DDMStructure ddmStructure,
			HSSFSheet hssfSheet, HSSFWorkbook hssfWorkbook,
			Map<String, Integer> indexCounterMap, TrainingEvent trainingEvent)
		throws Exception {

		CellStyle cellStyle = hssfWorkbook.createCellStyle();

		cellStyle.setWrapText(true);

		for (DDLRecord ddlRecord : ddlRecords) {
			Row row = hssfSheet.createRow(
				incrementCounter(indexCounterMap, "rowIndex"));

			processTrainingEventDetails(cellStyle, row, trainingEvent);

			processSurveyResultDetails(
				cellStyle,
				indexCounterMap.get("trainingEventDetailsColumnCount"),
				ddlRecord, ddmStructure, indexCounterMap, row, trainingEvent);
		}
	}

	protected static void processDDLRecordSets(
			List<DDLRecordSet> ddlRecordSets, DDMStructure ddmStructure,
			HSSFSheet hssfSheet, HSSFWorkbook hssfWorkbook,
			Map<String, Integer> indexCounterMap)
		throws Exception {

		for (DDLRecordSet ddlRecordSet : ddlRecordSets) {
			TrainingEvent trainingEvent =
				TrainingEventLocalServiceUtil.
					fetchTrainingEventByDDLRecordSetId(
						ddlRecordSet.getRecordSetId());

			if (trainingEvent == null) {
				continue;
			}

			List<DDLRecord> ddlRecords =
				DDLRecordLocalServiceUtil.getRecords(
					ddlRecordSet.getRecordSetId(), WorkflowConstants.STATUS_ANY,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			processDDLRecords(
				ddlRecords, ddmStructure, hssfSheet, hssfWorkbook,
				indexCounterMap, trainingEvent);
		}
	}

	protected static void processDDMStructures(
			List<DDMStructure> ddmStructures, HSSFWorkbook hssfWorkbook)
		throws Exception {

		for (DDMStructure ddmStructure : ddmStructures) {
			Map<String, Integer> indexCounterMap =
				new HashMap<String, Integer>();

			indexCounterMap.put("rowIndex", 0);
			indexCounterMap.put("totalColumnCount", 0);
			indexCounterMap.put("trainingEventDetailsColumnCount", 0);

			HSSFSheet hssfSheet = hssfWorkbook.createSheet(
				String.valueOf(ddmStructure.getStructureId()));

			addSurveyResultSheetNameRow(
				ddmStructure, hssfSheet, hssfWorkbook, indexCounterMap);

			addSurveyResultSheetColumnTitleRow(
				ddmStructure, hssfSheet, hssfWorkbook, indexCounterMap);

			List<DDLRecordSet> ddlRecordSets =
				DDLRecordSetLocalServiceUtil.dynamicQuery(
					buildDynamicQuery(ddmStructure.getStructureId()));

			processDDLRecordSets(
				ddlRecordSets, ddmStructure, hssfSheet, hssfWorkbook,
				indexCounterMap);

			for (int i = 0; i < indexCounterMap.get("totalColumnCount"); i++) {
				hssfSheet.setDefaultColumnWidth(_DEFAULT_COLUMN_WIDTH);
			}
		}
	}

	protected static void processSurveyResultDetails(
			CellStyle cellStyle, int columnIndex, DDLRecord ddlRecord,
			DDMStructure ddmStructure, Map<String, Integer> indexCounterMap,
			Row row, TrainingEvent trainingEvent)
		throws Exception {

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(
				ddlRecord.getUserId(),
				PortalUtil.getClassNameId(TrainingEvent.class),
				trainingEvent.getTrainingEventId());

		if (trainingCustomer == null) {
			int rowIndex = indexCounterMap.get("rowIndex");

			indexCounterMap.put("rowIndex", --rowIndex);

			return;
		}

		UserProfileHistory userProfileHistory =
			UserProfileHistoryLocalServiceUtil.getUserProfileHistory(
				trainingCustomer.getUserProfileHistoryId());

		addSurveyResultSheetRowCell(
			row, ++columnIndex, cellStyle, userProfileHistory.getFullName());
		addSurveyResultSheetRowCell(
			row, ++columnIndex, cellStyle,
			userProfileHistory.getEmailAddress());
		addSurveyResultSheetRowCell(
			row, ++columnIndex, cellStyle,
			userProfileHistory.getLegalEntityName());

		DDLRecordVersion recordVersion = ddlRecord.getRecordVersion();

		Map<String, Map<String, String>> fieldsMap = ddmStructure.getFieldsMap(
			trainingEvent.getLanguageId());

		for (Map<String, String> fieldMap : fieldsMap.values()) {
			String name = fieldMap.get(FieldConstants.NAME);
			String value = StringPool.BLANK;

			Fields fields = StorageEngineUtil.getFields(
				recordVersion.getDDMStorageId());

			if (fields.contains(name)) {
				Field field = fields.get(name);

				if (isRadioButton(name)) {
					value = StringUtil.replaceFirst(
						(String)field.getValue(), "[\"value ",
						StringPool.BLANK);

					value = StringUtil.replaceLast(
						value, "\"]", StringPool.BLANK);

					addSurveyResultSheetRowCell(
						row, ++columnIndex, cellStyle, Double.valueOf(value));
				}
				else {
					addSurveyResultSheetRowCell(
						row, ++columnIndex, cellStyle,
						field.getRenderedValue(
							LocaleUtil.fromLanguageId(
								trainingEvent.getLanguageId(), false)));
				}
			}
		}
	}

	protected static void processTrainingEventDetails(
			CellStyle cellStyle, Row row, TrainingEvent trainingEvent)
		throws Exception {

		int columnIndex = 0;

		TrainingCourse trainingCourse = trainingEvent.getTrainingCourse();

		addSurveyResultSheetRowCell(
			row, columnIndex, cellStyle, trainingCourse.getName());

		addSurveyResultSheetRowCell(
			row, ++columnIndex, cellStyle,
			trainingEvent.getAddressDisplayHTML());
		addSurveyResultSheetRowCell(
			row, ++columnIndex, cellStyle, trainingEvent.getStartDate());
		addSurveyResultSheetRowCell(
			row, ++columnIndex, cellStyle,
			trainingEvent.getTrainingWorkersDisplayHTML());
	}

	private static final int _DEFAULT_COLUMN_WIDTH = 20;

}