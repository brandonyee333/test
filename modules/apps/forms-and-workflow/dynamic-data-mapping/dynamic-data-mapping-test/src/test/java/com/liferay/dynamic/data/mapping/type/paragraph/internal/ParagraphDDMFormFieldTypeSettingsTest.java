/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.type.paragraph.internal;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.type.BaseDDMFormFieldTypeSettingsTestCase;
import com.liferay.dynamic.data.mapping.util.DDMFormFactory;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Leonardo Barros
 */
@PrepareForTest({PortalClassLoaderUtil.class, ResourceBundleUtil.class})
@RunWith(PowerMockRunner.class)
public class ParagraphDDMFormFieldTypeSettingsTest
	extends BaseDDMFormFieldTypeSettingsTestCase {

	@Before
	public void setUp() {
		setUpLanguageUtil();
		setUpPortalUtil();
		setUpResourceBundleUtil();
	}

	@Test
	public void testCreateParagraphDDMFormFieldTypeSettingsDDMForm() {
		DDMForm ddmForm = DDMFormFactory.create(
			ParagraphDDMFormFieldTypeSettings.class);

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(false);

		DDMFormField labelDDMFormField = ddmFormFieldsMap.get("label");

		Assert.assertNotNull(labelDDMFormField);
		Assert.assertEquals("key-value", labelDDMFormField.getType());
		Assert.assertNotNull(labelDDMFormField.getLabel());

		Map<String, Object> properties = labelDDMFormField.getProperties();

		Assert.assertTrue(properties.containsKey("placeholder"));

		DDMFormField predefinedValueDDMFormField = ddmFormFieldsMap.get(
			"predefinedValue");

		Assert.assertNotNull(predefinedValueDDMFormField);
		Assert.assertEquals(
			"FALSE", predefinedValueDDMFormField.getVisibilityExpression());

		DDMFormField repeatableDDMFormField = ddmFormFieldsMap.get(
			"repeatable");

		Assert.assertNotNull(repeatableDDMFormField);
		Assert.assertEquals(
			"FALSE", repeatableDDMFormField.getVisibilityExpression());

		DDMFormField requiredDDMFormField = ddmFormFieldsMap.get("required");

		Assert.assertNotNull(requiredDDMFormField);
		Assert.assertEquals(
			"FALSE", requiredDDMFormField.getVisibilityExpression());

		DDMFormField showLabelDDMFormField = ddmFormFieldsMap.get("showLabel");

		Assert.assertNotNull(showLabelDDMFormField);
		Assert.assertEquals(
			"FALSE", showLabelDDMFormField.getVisibilityExpression());

		DDMFormField textDDMFormField = ddmFormFieldsMap.get("text");

		Assert.assertNotNull(textDDMFormField);

		Assert.assertEquals("string", textDDMFormField.getDataType());
		Assert.assertNotNull(textDDMFormField.getLabel());

		properties = textDDMFormField.getProperties();

		Assert.assertTrue(properties.containsKey("placeholder"));

		Assert.assertTrue(textDDMFormField.isRequired());
		Assert.assertEquals("editor", textDDMFormField.getType());

		DDMFormField tipDDMFormField = ddmFormFieldsMap.get("tip");

		Assert.assertNotNull(tipDDMFormField);
		Assert.assertEquals("FALSE", tipDDMFormField.getVisibilityExpression());

		DDMFormField validationDDMFormField = ddmFormFieldsMap.get(
			"validation");

		Assert.assertNotNull(validationDDMFormField);
		Assert.assertEquals(
			"ddm-validation", validationDDMFormField.getDataType());
		Assert.assertEquals("validation", validationDDMFormField.getType());
		Assert.assertEquals(
			"FALSE", validationDDMFormField.getVisibilityExpression());
	}

	protected void setUpLanguageUtil() {
		LanguageUtil languageUtil = new LanguageUtil();

		Language language = PowerMockito.mock(Language.class);

		languageUtil.setLanguage(language);
	}

	protected void setUpPortalUtil() {
		PortalUtil portalUtil = new PortalUtil();

		Portal portal = mock(Portal.class);

		ResourceBundle resourceBundle = mock(ResourceBundle.class);

		when(
			portal.getResourceBundle(Matchers.any(Locale.class))
		).thenReturn(
			resourceBundle
		);

		portalUtil.setPortal(portal);
	}

	protected void setUpResourceBundleUtil() {
		PowerMockito.mockStatic(ResourceBundleUtil.class);

		PowerMockito.when(
			ResourceBundleUtil.getBundle(
				Matchers.anyString(), Matchers.any(Locale.class),
				Matchers.any(ClassLoader.class))
		).thenReturn(
			ResourceBundleUtil.EMPTY_RESOURCE_BUNDLE
		);
	}

}