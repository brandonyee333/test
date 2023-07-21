/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.exporter.impl;

import com.liferay.dynamic.data.lists.exporter.DDLExporter;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalService;
import com.liferay.dynamic.data.lists.service.DDLRecordSetService;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.dynamic.data.mapping.util.DDMFormValuesToFieldsConverter;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.CSVUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.time.format.DateTimeFormatter;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 * @author Manuel de la Peña
 */
@Component(immediate = true, service = DDLExporter.class)
public class DDLCSVExporter extends BaseDDLExporter {

	@Override
	public String getFormat() {
		return "csv";
	}

	@Override
	protected byte[] doExport(
			long recordSetId, int status, int start, int end,
			OrderByComparator<DDLRecord> orderByComparator)
		throws Exception {

		StringBundler sb = new StringBundler();

		DDLRecordSet recordSet = _ddlRecordSetService.getRecordSet(recordSetId);

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		List<DDMFormField> ddmFormFields = getDDMFormFields(ddmStructure);

		Locale locale = getLocale();

		for (DDMFormField ddmFormField : ddmFormFields) {
			LocalizedValue label = ddmFormField.getLabel();

			sb.append(CSVUtil.encode(label.getString(locale)));

			sb.append(CharPool.COMMA);
		}

		sb.append(LanguageUtil.get(locale, "status"));
		sb.append(CharPool.COMMA);
		sb.append(LanguageUtil.get(locale, "modified-date"));
		sb.append(CharPool.COMMA);
		sb.append(LanguageUtil.get(locale, "author"));
		sb.append(StringPool.NEW_LINE);

		List<DDLRecord> records = _ddlRecordLocalService.getRecords(
			recordSetId, status, start, end, orderByComparator);

		Iterator<DDLRecord> iterator = records.iterator();

		while (iterator.hasNext()) {
			DDLRecord record = iterator.next();

			DDLRecordVersion recordVersion = record.getRecordVersion();

			DDMFormValues ddmFormValues = _storageEngine.getDDMFormValues(
				recordVersion.getDDMStorageId());

			Fields fields = _ddmFormValuesToFieldsConverter.convert(
				ddmStructure, ddmFormValues);

			for (DDMFormField ddmFormField : ddmFormFields) {
				String name = ddmFormField.getName();
				String value = StringPool.BLANK;

				if (fields.contains(name)) {
					Field field = fields.get(name);

					value = field.getRenderedValue(locale);
				}

				sb.append(CSVUtil.encode(value));
				sb.append(CharPool.COMMA);
			}

			sb.append(getStatusMessage(recordVersion.getStatus()));
			sb.append(CharPool.COMMA);

			DateTimeFormatter dateTimeFormatter = getDateTimeFormatter();

			sb.append(
				formatDate(recordVersion.getStatusDate(), dateTimeFormatter));

			sb.append(CharPool.COMMA);
			sb.append(CSVUtil.encode(recordVersion.getUserName()));

			if (iterator.hasNext()) {
				sb.append(StringPool.NEW_LINE);
			}
		}

		String csv = sb.toString();

		return csv.getBytes();
	}

	@Reference(unbind = "-")
	protected void setDDLRecordLocalService(
		DDLRecordLocalService ddlRecordLocalService) {

		_ddlRecordLocalService = ddlRecordLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDLRecordSetService(
		DDLRecordSetService ddlRecordSetService) {

		_ddlRecordSetService = ddlRecordSetService;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesToFieldsConverter(
		DDMFormValuesToFieldsConverter ddmFormValuesToFieldsConverter) {

		_ddmFormValuesToFieldsConverter = ddmFormValuesToFieldsConverter;
	}

	@Reference(unbind = "-")
	protected void setStorageEngine(StorageEngine storageEngine) {
		_storageEngine = storageEngine;
	}

	private DDLRecordLocalService _ddlRecordLocalService;
	private DDLRecordSetService _ddlRecordSetService;
	private DDMFormValuesToFieldsConverter _ddmFormValuesToFieldsConverter;
	private StorageEngine _storageEngine;

}