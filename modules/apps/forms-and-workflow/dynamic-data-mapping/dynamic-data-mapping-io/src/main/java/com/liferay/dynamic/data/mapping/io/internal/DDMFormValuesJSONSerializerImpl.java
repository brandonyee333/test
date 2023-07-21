/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.io.internal;

import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONSerializer;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true)
public class DDMFormValuesJSONSerializerImpl
	implements DDMFormValuesJSONSerializer {

	@Override
	public String serialize(DDMFormValues ddmFormValues) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		addAvailableLanguageIds(
			jsonObject, ddmFormValues.getAvailableLocales());
		addDefaultLanguageId(jsonObject, ddmFormValues.getDefaultLocale());
		addFieldValues(jsonObject, ddmFormValues.getDDMFormFieldValues());

		return jsonObject.toString();
	}

	protected void addAvailableLanguageIds(
		JSONObject jsonObject, Set<Locale> availableLocales) {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		for (Locale availableLocale : availableLocales) {
			jsonArray.put(LocaleUtil.toLanguageId(availableLocale));
		}

		jsonObject.put("availableLanguageIds", jsonArray);
	}

	protected void addDefaultLanguageId(
		JSONObject jsonObject, Locale defaultLocale) {

		jsonObject.put(
			"defaultLanguageId", LocaleUtil.toLanguageId(defaultLocale));
	}

	protected void addFieldValues(
		JSONObject jsonObject, List<DDMFormFieldValue> ddmFormFieldValues) {

		jsonObject.put("fieldValues", toJSONArray(ddmFormFieldValues));
	}

	protected void addNestedFieldValues(
		JSONObject jsonObject,
		List<DDMFormFieldValue> nestedDDMFormFieldValues) {

		if (nestedDDMFormFieldValues.isEmpty()) {
			return;
		}

		jsonObject.put(
			"nestedFieldValues", toJSONArray(nestedDDMFormFieldValues));
	}

	protected void addValue(JSONObject jsonObject, Value value) {
		if (value == null) {
			return;
		}

		if (value.isLocalized()) {
			jsonObject.put("value", toJSONObject(value));
		}
		else {
			jsonObject.put("value", value.getString(LocaleUtil.ROOT));
		}
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	protected JSONArray toJSONArray(
		List<DDMFormFieldValue> ddmFormFieldValues) {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		for (DDMFormFieldValue ddmFormFieldValue : ddmFormFieldValues) {
			jsonArray.put(toJSONObject(ddmFormFieldValue));
		}

		return jsonArray;
	}

	protected JSONObject toJSONObject(DDMFormFieldValue ddmFormFieldValue) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put("instanceId", ddmFormFieldValue.getInstanceId());
		jsonObject.put("name", ddmFormFieldValue.getName());

		addNestedFieldValues(
			jsonObject, ddmFormFieldValue.getNestedDDMFormFieldValues());
		addValue(jsonObject, ddmFormFieldValue.getValue());

		return jsonObject;
	}

	protected JSONObject toJSONObject(Value value) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		for (Locale availableLocale : value.getAvailableLocales()) {
			jsonObject.put(
				LocaleUtil.toLanguageId(availableLocale),
				value.getString(availableLocale));
		}

		return jsonObject;
	}

	private JSONFactory _jsonFactory;

}