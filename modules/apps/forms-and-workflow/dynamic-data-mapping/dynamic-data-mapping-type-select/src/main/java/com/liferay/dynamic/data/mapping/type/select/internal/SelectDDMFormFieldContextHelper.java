/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.type.select.internal;

import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Bruno Basto
 * @author Marcellus Tavares
 */
public class SelectDDMFormFieldContextHelper {

	public SelectDDMFormFieldContextHelper(
		JSONFactory jsonFactory, DDMFormFieldOptions ddmFormFieldOptions,
		String value, LocalizedValue predefinedValue, Locale locale) {

		_jsonFactory = jsonFactory;
		_ddmFormFieldOptions = ddmFormFieldOptions;
		_locale = locale;

		_values = toStringArray(value);
		_predefinedValues = toStringArray(predefinedValue.getString(locale));
	}

	public List<Object> getOptions() {
		List<Object> options = new ArrayList<>();

		for (String optionValue : _ddmFormFieldOptions.getOptionsValues()) {
			Map<String, String> optionMap = new HashMap<>();

			LocalizedValue optionLabel = _ddmFormFieldOptions.getOptionLabels(
				optionValue);

			optionMap.put("label", optionLabel.getString(_locale));

			optionMap.put(
				"status",
				isSelected(optionValue) ? "selected" : StringPool.BLANK);
			optionMap.put("value", optionValue);

			options.add(optionMap);
		}

		return options;
	}

	protected boolean isSelected(String optionValue) {
		if (ArrayUtil.isEmpty(_values)) {
			return ArrayUtil.contains(_predefinedValues, optionValue);
		}

		if (ArrayUtil.contains(_values, optionValue)) {
			return true;
		}

		return false;
	}

	protected String[] toStringArray(String value) {
		if (Validator.isNull(value)) {
			return GetterUtil.DEFAULT_STRING_VALUES;
		}

		try {
			JSONArray jsonArray = _jsonFactory.createJSONArray(value);

			return ArrayUtil.toStringArray(jsonArray);
		}
		catch (JSONException jsone) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(jsone, jsone);
			}

			return StringUtil.split(value);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SelectDDMFormFieldContextHelper.class);

	private final DDMFormFieldOptions _ddmFormFieldOptions;
	private final JSONFactory _jsonFactory;
	private final Locale _locale;
	private final String[] _predefinedValues;
	private final String[] _values;

}