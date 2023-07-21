/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.renderer.internal;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.model.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.model.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class DDMFormLayoutTransformer {

	public DDMFormLayoutTransformer(
		DDMForm ddmForm, DDMFormLayout ddmFormLayout,
		Map<String, String> renderedDDMFormFieldsMap,
		boolean showRequiredFieldsWarning, Locale locale) {

		_ddmFormLayout = ddmFormLayout;
		_renderedDDMFormFieldsMap = renderedDDMFormFieldsMap;
		_showRequiredFieldsWarning = showRequiredFieldsWarning;
		_locale = locale;

		_ddmFormFieldsMap = ddmForm.getDDMFormFieldsMap(true);
	}

	public List<Object> getPages() {
		return getPages(_ddmFormLayout.getDDMFormLayoutPages());
	}

	protected boolean containsRequiredField(List<String> ddmFormFieldNames) {
		for (String ddmFormFieldName : ddmFormFieldNames) {
			DDMFormField ddmFormField = _ddmFormFieldsMap.get(ddmFormFieldName);

			if (ddmFormField.isRequired()) {
				return true;
			}
		}

		return false;
	}

	protected Map<String, Object> getColumn(
		DDMFormLayoutColumn ddmFormLayoutColumn) {

		Map<String, Object> column = new HashMap<>();

		column.put(
			"fields", getFields(ddmFormLayoutColumn.getDDMFormFieldNames()));
		column.put("size", ddmFormLayoutColumn.getSize());

		return column;
	}

	protected List<Object> getColumns(
		List<DDMFormLayoutColumn> ddmFormLayoutColumns) {

		List<Object> columns = new ArrayList<>();

		for (DDMFormLayoutColumn ddmFormLayoutColumn : ddmFormLayoutColumns) {
			columns.add(getColumn(ddmFormLayoutColumn));
		}

		return columns;
	}

	protected List<String> getFields(List<String> ddmFormFieldNames) {
		List<String> renderedDDMFormFields = new ArrayList<>();

		for (String ddmFormFieldName : ddmFormFieldNames) {
			renderedDDMFormFields.add(
				_renderedDDMFormFieldsMap.get(ddmFormFieldName));
		}

		return renderedDDMFormFields;
	}

	protected Map<String, Object> getPage(DDMFormLayoutPage ddmFormLayoutPage) {
		Map<String, Object> page = new HashMap<>();

		LocalizedValue description = ddmFormLayoutPage.getDescription();

		page.put("description", description.getString(_locale));

		page.put("rows", getRows(ddmFormLayoutPage.getDDMFormLayoutRows()));

		boolean showRequiredFieldsWarning = isShowRequiredFieldsWarning(
			ddmFormLayoutPage.getDDMFormLayoutRows());

		page.put("showRequiredFieldsWarning", showRequiredFieldsWarning);

		LocalizedValue title = ddmFormLayoutPage.getTitle();

		page.put("title", title.getString(_locale));

		return page;
	}

	protected List<Object> getPages(
		List<DDMFormLayoutPage> ddmFormLayoutPages) {

		List<Object> pages = new ArrayList<>();

		for (DDMFormLayoutPage ddmFormLayoutPage : ddmFormLayoutPages) {
			pages.add(getPage(ddmFormLayoutPage));
		}

		return pages;
	}

	protected Map<String, Object> getRow(DDMFormLayoutRow ddmFormLayoutRow) {
		Map<String, Object> row = new HashMap<>();

		row.put(
			"columns", getColumns(ddmFormLayoutRow.getDDMFormLayoutColumns()));

		return row;
	}

	protected List<Object> getRows(List<DDMFormLayoutRow> ddmFormLayoutRows) {
		List<Object> rows = new ArrayList<>();

		for (DDMFormLayoutRow ddmFormLayoutRow : ddmFormLayoutRows) {
			rows.add(getRow(ddmFormLayoutRow));
		}

		return rows;
	}

	protected boolean isShowRequiredFieldsWarning(
		List<DDMFormLayoutRow> ddmFormLayoutRows) {

		if (!_showRequiredFieldsWarning) {
			return false;
		}

		for (DDMFormLayoutRow ddmFormLayoutRow : ddmFormLayoutRows) {
			for (DDMFormLayoutColumn ddmFormLayoutColumn :
					ddmFormLayoutRow.getDDMFormLayoutColumns()) {

				if (containsRequiredField(
						ddmFormLayoutColumn.getDDMFormFieldNames())) {

					return true;
				}
			}
		}

		return false;
	}

	private final Map<String, DDMFormField> _ddmFormFieldsMap;
	private final DDMFormLayout _ddmFormLayout;
	private final Locale _locale;
	private final Map<String, String> _renderedDDMFormFieldsMap;
	private final boolean _showRequiredFieldsWarning;

}