/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.type.checkbox.multiple.internal;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueAccessor;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=checkbox_multiple",
	service = {
		CheckboxMultipleDDMFormFieldValueAccessor.class,
		DDMFormFieldValueAccessor.class
	}
)
public class CheckboxMultipleDDMFormFieldValueAccessor
	implements DDMFormFieldValueAccessor<JSONArray> {

	@Override
	public JSONArray getValue(
		DDMFormFieldValue ddmFormFieldValue, Locale locale) {

		Value value = ddmFormFieldValue.getValue();

		String valueString = value.getString(locale);

		try {
			return jsonFactory.createJSONArray(value.getString(locale));
		}
		catch (JSONException jsone) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to parse JSON array", jsone);
			}

			JSONArray jsonArray = jsonFactory.createJSONArray();

			if (Validator.isNotNull(valueString)) {
				jsonArray.put(valueString);
			}

			return jsonArray;
		}
	}

	@Override
	public boolean isEmpty(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		JSONArray jsonArray = getValue(ddmFormFieldValue, locale);

		if (jsonArray.length() > 0) {
			return false;
		}

		return true;
	}

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		CheckboxMultipleDDMFormFieldValueAccessor.class);

}