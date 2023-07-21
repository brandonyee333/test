/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.type.checkbox.internal;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Renato Rego
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=checkbox",
	service = DDMFormFieldRenderer.class
)
public class CheckboxDDMFormFieldRenderer extends BaseDDMFormFieldRenderer {

	@Override
	public String getTemplateLanguage() {
		return TemplateConstants.LANG_TYPE_SOY;
	}

	@Override
	public String getTemplateNamespace() {
		return "ddm.checkbox";
	}

	@Override
	public TemplateResource getTemplateResource() {
		return _templateResource;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_templateResource = getTemplateResource(
			"/META-INF/resources/checkbox.soy");
	}

	@Deactivate
	protected void deactivate() {
		_templateResource = null;
	}

	protected String getStatus(String value, String predefinedValue) {
		String status = StringPool.BLANK;

		if (Objects.equals(value, "true")) {
			status = "checked";
		}
		else if (Objects.equals(predefinedValue, "true")) {
			status = "checked";
		}

		return status;
	}

	@Override
	protected void populateOptionalContext(
		Template template, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		template.put(
			"showAsSwitcher", ddmFormField.getProperty("showAsSwitcher"));

		LocalizedValue predefinedValue = ddmFormField.getPredefinedValue();

		Locale locale = ddmFormFieldRenderingContext.getLocale();

		String status = getStatus(
			ddmFormFieldRenderingContext.getValue(),
			predefinedValue.getString(locale));

		template.put("status", status);
	}

	private TemplateResource _templateResource;

}