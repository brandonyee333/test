/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.type.checkbox.multiple.internal;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=checkbox_multiple"
)
public class CheckboxMultipleDDMFormFieldValueRenderer
	implements DDMFormFieldValueRenderer {

	@Override
	public String render(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		JSONArray optionsValuesJSONArray =
			checkboxMultipleDDMFormFieldValueAccessor.getValue(
				ddmFormFieldValue, locale);

		DDMFormFieldOptions ddmFormFieldOptions = getDDMFormFieldOptions(
			ddmFormFieldValue);

		if (optionsValuesJSONArray.length() == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(
			(optionsValuesJSONArray.length() * 2) - 1);

		for (int i = 0; i < optionsValuesJSONArray.length(); i++) {
			String optionValue = optionsValuesJSONArray.getString(i);

			LocalizedValue optionLabel = ddmFormFieldOptions.getOptionLabels(
				optionValue);

			if (optionLabel == null) {
				sb.append(optionValue);
			}
			else {
				sb.append(optionLabel.getString(locale));
			}

			sb.append(StringPool.COMMA_AND_SPACE);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	protected DDMFormFieldOptions getDDMFormFieldOptions(
		DDMFormFieldValue ddmFormFieldValue) {

		DDMFormField ddmFormField = ddmFormFieldValue.getDDMFormField();

		return ddmFormField.getDDMFormFieldOptions();
	}

	@Reference
	protected CheckboxMultipleDDMFormFieldValueAccessor
		checkboxMultipleDDMFormFieldValueAccessor;

}