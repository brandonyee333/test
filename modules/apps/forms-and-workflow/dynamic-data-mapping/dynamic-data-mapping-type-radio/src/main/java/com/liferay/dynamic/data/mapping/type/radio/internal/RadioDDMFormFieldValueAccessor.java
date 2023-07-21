/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.type.radio.internal;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueAccessor;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Renato Rego
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=radio",
	service = {
		DDMFormFieldValueAccessor.class, RadioDDMFormFieldValueAccessor.class
	}
)
public class RadioDDMFormFieldValueAccessor
	implements DDMFormFieldValueAccessor<String> {

	@Override
	public String getValue(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		Value value = ddmFormFieldValue.getValue();

		if (value == null) {
			return StringPool.BLANK;
		}

		try {
			JSONArray jsonArray = jsonFactory.createJSONArray(
				value.getString(locale));

			return jsonArray.getString(0);
		}
		catch (JSONException jsone) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to parse JSON array", jsone);
			}

			return value.getString(locale);
		}
	}

	@Override
	public boolean isEmpty(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		String value = getValue(ddmFormFieldValue, locale);

		return Validator.isNull(value);
	}

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		RadioDDMFormFieldValueAccessor.class);

}