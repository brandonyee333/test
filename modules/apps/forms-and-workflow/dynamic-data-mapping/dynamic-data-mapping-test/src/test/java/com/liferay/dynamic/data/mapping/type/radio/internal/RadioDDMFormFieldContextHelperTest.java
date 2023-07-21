/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.type.radio.internal;

import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Marcellus Tavares
 */
public class RadioDDMFormFieldContextHelperTest {

	@Test
	public void testGetOptionsWithNoCheckedValueAndOnePredefinedValue() {
		List<Object> expectedOptions = new ArrayList<>();

		expectedOptions.add(createUncheckedOption("Label 1", "value 1"));
		expectedOptions.add(createUncheckedOption("Label 2", "value 2"));
		expectedOptions.add(createCheckedOption("Label 3", "value 3"));

		DDMFormFieldOptions ddmFormFieldOptions = createDDMFormFieldOptions();

		List<Object> actualOptions = getActualOptions(
			ddmFormFieldOptions, "", createPredefinedValue("[\"value 3\"]"),
			LocaleUtil.US);

		Assert.assertEquals(expectedOptions, actualOptions);
	}

	@Test
	public void testGetOptionsWithOneCheckedValueAndNoPredefinedValue() {
		List<Object> expectedOptions = new ArrayList<>();

		expectedOptions.add(createUncheckedOption("Label 1", "value 1"));
		expectedOptions.add(createCheckedOption("Label 2", "value 2"));
		expectedOptions.add(createUncheckedOption("Label 3", "value 3"));

		DDMFormFieldOptions ddmFormFieldOptions = createDDMFormFieldOptions();

		List<Object> actualOptions = getActualOptions(
			ddmFormFieldOptions, "[\"value 2\"]", createPredefinedValue(""),
			LocaleUtil.US);

		Assert.assertEquals(expectedOptions, actualOptions);
	}

	protected Map<String, String> createCheckedOption(
		String label, String value) {

		return createOption(label, "checked", value);
	}

	protected DDMFormFieldOptions createDDMFormFieldOptions() {
		DDMFormFieldOptions ddmFormFieldOptions = new DDMFormFieldOptions();

		ddmFormFieldOptions.addOptionLabel("value 1", LocaleUtil.US, "Label 1");
		ddmFormFieldOptions.addOptionLabel("value 2", LocaleUtil.US, "Label 2");
		ddmFormFieldOptions.addOptionLabel("value 3", LocaleUtil.US, "Label 3");

		return ddmFormFieldOptions;
	}

	protected Map<String, String> createOption(
		String label, String status, String value) {

		Map<String, String> option = new HashMap<>();

		option.put("label", label);
		option.put("status", status);
		option.put("value", value);

		return option;
	}

	protected LocalizedValue createPredefinedValue(String string) {
		LocalizedValue localizedValue = new LocalizedValue();

		localizedValue.addString(LocaleUtil.US, string);

		return localizedValue;
	}

	protected Map<String, String> createUncheckedOption(
		String label, String value) {

		return createOption(label, StringPool.BLANK, value);
	}

	protected List<Object> getActualOptions(
		DDMFormFieldOptions ddmFormFieldOptions, String value,
		LocalizedValue predefinedValue, Locale locale) {

		RadioDDMFormFieldContextHelper radioDDMFormFieldContextHelper =
			new RadioDDMFormFieldContextHelper(
				new JSONFactoryImpl(), ddmFormFieldOptions, value,
				predefinedValue, locale);

		return radioDDMFormFieldContextHelper.getOptions();
	}

}