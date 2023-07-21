/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.type.checkbox.multiple.internal;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=checkbox_multiple",
	service = DDMFormFieldRenderer.class
)
public class CheckboxMultipleDDMFormFieldRenderer
	extends BaseDDMFormFieldRenderer {

	@Override
	public String getTemplateLanguage() {
		return TemplateConstants.LANG_TYPE_SOY;
	}

	@Override
	public String getTemplateNamespace() {
		return "ddm.checkbox_multiple";
	}

	@Override
	public TemplateResource getTemplateResource() {
		return _templateResource;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_templateResource = getTemplateResource(
			"/META-INF/resources/checkbox-multiple.soy");
	}

	protected List<Object> getOptions(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		CheckboxMultipleDDMFormFieldContextHelper
			checkboxMultipleDDMFormFieldContextHelper =
				new CheckboxMultipleDDMFormFieldContextHelper(
					jsonFactory, ddmFormField.getDDMFormFieldOptions(),
					ddmFormFieldRenderingContext.getValue(),
					ddmFormField.getPredefinedValue(),
					ddmFormFieldRenderingContext.getLocale());

		return checkboxMultipleDDMFormFieldContextHelper.getOptions();
	}

	@Override
	protected void populateRequiredContext(
		Template template, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		super.populateRequiredContext(
			template, ddmFormField, ddmFormFieldRenderingContext);

		template.put(
			"inline",
			GetterUtil.getBoolean(ddmFormField.getProperty("inline")));

		template.put(
			"options", getOptions(ddmFormField, ddmFormFieldRenderingContext));

		template.put(
			"showAsSwitcher",
			GetterUtil.getBoolean(ddmFormField.getProperty("showAsSwitcher")));
	}

	@Reference
	protected JSONFactory jsonFactory;

	private TemplateResource _templateResource;

}