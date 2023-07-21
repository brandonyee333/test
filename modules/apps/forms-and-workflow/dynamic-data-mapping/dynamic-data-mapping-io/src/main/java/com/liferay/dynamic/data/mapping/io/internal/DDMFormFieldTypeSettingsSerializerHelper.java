/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.io.internal;

import com.liferay.dynamic.data.mapping.io.DDMFormJSONSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormLayoutJSONSerializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.util.DDMFormFactory;
import com.liferay.dynamic.data.mapping.util.DDMFormLayoutFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Marcellus Tavares
 */
public class DDMFormFieldTypeSettingsSerializerHelper {

	public DDMFormFieldTypeSettingsSerializerHelper(
		Class<?> ddmFormFieldTypeSettings,
		DDMFormJSONSerializer ddmFormJSONSerializer,
		DDMFormLayoutJSONSerializer ddmFormLayoutJSONSerializer,
		JSONFactory jsonFactory) {

		_ddmFormFieldTypeSettings = ddmFormFieldTypeSettings;
		_ddmFormJSONSerializer = ddmFormJSONSerializer;
		_ddmFormLayoutJSONSerializer = ddmFormLayoutJSONSerializer;
		_jsonFactory = jsonFactory;
	}

	public JSONObject getSettingsJSONObject() throws PortalException {
		DDMForm ddmForm = DDMFormFactory.create(_ddmFormFieldTypeSettings);

		String serializedDDMForm = _ddmFormJSONSerializer.serialize(ddmForm);

		return _jsonFactory.createJSONObject(serializedDDMForm);
	}

	public JSONObject getSettingsLayoutJSONObject() throws PortalException {
		DDMFormLayout ddmFormLayout = DDMFormLayoutFactory.create(
			_ddmFormFieldTypeSettings);

		String serializedDDMFormLayout = _ddmFormLayoutJSONSerializer.serialize(
			ddmFormLayout);

		return _jsonFactory.createJSONObject(serializedDDMFormLayout);
	}

	private final Class<?> _ddmFormFieldTypeSettings;
	private final DDMFormJSONSerializer _ddmFormJSONSerializer;
	private final DDMFormLayoutJSONSerializer _ddmFormLayoutJSONSerializer;
	private final JSONFactory _jsonFactory;

}