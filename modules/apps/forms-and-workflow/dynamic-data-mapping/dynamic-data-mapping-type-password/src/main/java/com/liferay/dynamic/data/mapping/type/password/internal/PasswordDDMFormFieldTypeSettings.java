/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.type.password.internal;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;

/**
 * @author Marcellus Tavares
 */
@DDMForm
@DDMFormLayout(
	paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.SETTINGS_MODE,
	value = {
		@DDMFormLayoutPage(
			title = "basic",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12, value = {"label", "tip", "required"}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
			title = "advanced",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"predefinedValue", "placeholder",
								"visibilityExpression", "validation",
								"fieldNamespace", "indexType", "localizable",
								"readOnly", "dataType", "type", "name",
								"showLabel", "repeatable", "tooltip"
							}
						)
					}
				)
			}
		)
	}
)
public interface PasswordDDMFormFieldTypeSettings
	extends DefaultDDMFormFieldTypeSettings {

	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public String indexType();

	@DDMFormField(
		dataType = "string", label = "%placeholder-text",
		properties = {
			"placeholder=%enter-placeholder-text",
			"tooltip=%enter-text-that-assists-the-user-but-is-not-submitted-as-a-field-value"
		},
		type = "text"
	)
	public LocalizedValue placeholder();

	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public LocalizedValue predefinedValue();

	@DDMFormField(visibilityExpression = "FALSE")
	public LocalizedValue tooltip();

	@DDMFormField(
		dataType = "string", type = "validation", visibilityExpression = "FALSE"
	)
	@Override
	public DDMFormFieldValidation validation();

}