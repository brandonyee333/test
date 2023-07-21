/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.util.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeSettings;
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.util.DDMFormFactory;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Marcellus Tavares
 */
@RunWith(Arquillian.class)
public class DDMFormFactoryTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testCreateDDMFormFieldTypeSettingsDDMForm() {
		DDMForm ddmForm = DDMFormFactory.create(DDMFormFieldTypeSettings.class);

		Assert.assertNotNull(ddmForm);

		assertRequiredDDMFormFieldTypeSettings(
			ddmForm.getDDMFormFieldsMap(false));
	}

	@Test
	public void testCreateDefaultDDMFormFieldTypeSettingsDDMForm() {
		DDMForm ddmForm = DDMFormFactory.create(
			DefaultDDMFormFieldTypeSettings.class);

		Assert.assertNotNull(ddmForm);

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(false);

		assertRequiredDDMFormFieldTypeSettings(ddmFormFieldsMap);

		DDMFormField indexTypeDDMFormField = ddmFormFieldsMap.get("indexType");

		Assert.assertNotNull(indexTypeDDMFormField);
		Assert.assertEquals("string", indexTypeDDMFormField.getDataType());
		Assert.assertEquals("select", indexTypeDDMFormField.getType());

		DDMFormField labelDDMFormField = ddmFormFieldsMap.get("label");

		Assert.assertNotNull(labelDDMFormField);
		Assert.assertEquals("string", labelDDMFormField.getDataType());
		Assert.assertEquals("key-value", labelDDMFormField.getType());
		Assert.assertTrue(labelDDMFormField.isLocalizable());

		DDMFormField localizableDDMFormField = ddmFormFieldsMap.get(
			"localizable");

		Assert.assertNotNull(localizableDDMFormField);
		Assert.assertEquals("boolean", localizableDDMFormField.getDataType());
		Assert.assertEquals("checkbox", localizableDDMFormField.getType());

		DDMFormField predefinedValueDDMFormField = ddmFormFieldsMap.get(
			"predefinedValue");

		Assert.assertNotNull(predefinedValueDDMFormField);
		Assert.assertEquals(
			"string", predefinedValueDDMFormField.getDataType());
		Assert.assertEquals("text", predefinedValueDDMFormField.getType());
		Assert.assertTrue(predefinedValueDDMFormField.isLocalizable());

		DDMFormField readOnlyDDMFormField = ddmFormFieldsMap.get("readOnly");

		Assert.assertNotNull(readOnlyDDMFormField);
		Assert.assertEquals("boolean", readOnlyDDMFormField.getDataType());
		Assert.assertEquals("checkbox", readOnlyDDMFormField.getType());

		DDMFormField repeatableDDMFormField = ddmFormFieldsMap.get(
			"repeatable");

		Assert.assertNotNull(repeatableDDMFormField);
		Assert.assertEquals("boolean", repeatableDDMFormField.getDataType());
		Assert.assertEquals("checkbox", repeatableDDMFormField.getType());

		DDMFormField requiredDDMFormField = ddmFormFieldsMap.get("readOnly");

		Assert.assertNotNull(requiredDDMFormField);
		Assert.assertEquals("boolean", requiredDDMFormField.getDataType());
		Assert.assertEquals("checkbox", requiredDDMFormField.getType());

		DDMFormField showLabelDDMFormField = ddmFormFieldsMap.get("showLabel");

		Assert.assertNotNull(showLabelDDMFormField);
		Assert.assertEquals("boolean", showLabelDDMFormField.getDataType());
		Assert.assertEquals("checkbox", showLabelDDMFormField.getType());

		DDMFormField tipDDMFormField = ddmFormFieldsMap.get("tip");

		Assert.assertNotNull(tipDDMFormField);
		Assert.assertEquals("string", tipDDMFormField.getDataType());
		Assert.assertEquals("text", tipDDMFormField.getType());
		Assert.assertTrue(tipDDMFormField.isLocalizable());
	}

	protected void assertRequiredDDMFormFieldTypeSettings(
		Map<String, DDMFormField> ddmFormFieldsMap) {

		DDMFormField dataTypeDDMFormField = ddmFormFieldsMap.get("dataType");

		Assert.assertNotNull(dataTypeDDMFormField);
		Assert.assertEquals("string", dataTypeDDMFormField.getDataType());
		Assert.assertEquals("text", dataTypeDDMFormField.getType());
		Assert.assertTrue(dataTypeDDMFormField.isRequired());
		Assert.assertFalse(dataTypeDDMFormField.isLocalizable());

		DDMFormField nameDDMFormField = ddmFormFieldsMap.get("name");

		Assert.assertNotNull(nameDDMFormField);
		Assert.assertEquals("string", nameDDMFormField.getDataType());
		Assert.assertEquals("text", nameDDMFormField.getType());
		Assert.assertTrue(nameDDMFormField.isRequired());
		Assert.assertFalse(nameDDMFormField.isLocalizable());

		DDMFormField typeDDMFormField = ddmFormFieldsMap.get("type");

		Assert.assertNotNull(typeDDMFormField);
		Assert.assertEquals("string", typeDDMFormField.getDataType());
		Assert.assertEquals("text", typeDDMFormField.getType());
		Assert.assertTrue(typeDDMFormField.isRequired());
		Assert.assertFalse(typeDDMFormField.isLocalizable());
	}

}