/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.type.select.internal;

import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
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
public class SelectDDMFormFieldContextHelperTest {

	@Test
	public void testGetOptionsWithAllSelectedValuesAndNoPredefinedValue() {
		List<Object> expectedOptions = new ArrayList<>();

		expectedOptions.add(createSelectedOption("Label 1", "value 1"));
		expectedOptions.add(createSelectedOption("Label 2", "value 2"));
		expectedOptions.add(createSelectedOption("Label 3", "value 3"));

		DDMFormFieldOptions ddmFormFieldOptions = createDDMFormFieldOptions();

		List<Object> actualOptions = getActualOptions(
			ddmFormFieldOptions, createValue("value 1", "value 2", "value 3"),
			createPredefinedValue(), LocaleUtil.US);

		Assert.assertEquals(expectedOptions, actualOptions);
	}

	@Test
	public void testGetOptionsWithNoneSelectedAndOnePredefinedValue() {
		List<Object> expectedOptions = new ArrayList<>();

		expectedOptions.add(createUnselectedOption("Label 1", "value 1"));
		expectedOptions.add(createSelectedOption("Label 2", "value 2"));
		expectedOptions.add(createUnselectedOption("Label 3", "value 3"));

		DDMFormFieldOptions ddmFormFieldOptions = createDDMFormFieldOptions();

		List<Object> actualOptions = getActualOptions(
			ddmFormFieldOptions, createValue(),
			createPredefinedValue("value 2"), LocaleUtil.US);

		Assert.assertEquals(expectedOptions, actualOptions);
	}

	@Test
	public void testGetOptionsWithNoneSelectedAndTwoPredefinedValues() {
		List<Object> expectedOptions = new ArrayList<>();

		expectedOptions.add(createSelectedOption("Label 1", "value 1"));
		expectedOptions.add(createUnselectedOption("Label 2", "value 2"));
		expectedOptions.add(createSelectedOption("Label 3", "value 3"));

		DDMFormFieldOptions ddmFormFieldOptions = createDDMFormFieldOptions();

		List<Object> actualOptions = getActualOptions(
			ddmFormFieldOptions, createValue(),
			createPredefinedValue("value 1", "value 3"), LocaleUtil.US);

		Assert.assertEquals(expectedOptions, actualOptions);
	}

	@Test
	public void testGetOptionsWithOneSelectedValueAndTwoPredefinedValues() {
		List<Object> expectedOptions = new ArrayList<>();

		expectedOptions.add(createUnselectedOption("Label 1", "value 1"));
		expectedOptions.add(createUnselectedOption("Label 2", "value 2"));
		expectedOptions.add(createSelectedOption("Label 3", "value 3"));

		DDMFormFieldOptions ddmFormFieldOptions = createDDMFormFieldOptions();

		List<Object> actualOptions = getActualOptions(
			ddmFormFieldOptions, createValue("value 3"),
			createPredefinedValue("value 1", "value 2"), LocaleUtil.US);

		Assert.assertEquals(expectedOptions, actualOptions);
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

	protected LocalizedValue createPredefinedValue(String... strings) {
		LocalizedValue localizedValue = new LocalizedValue();

		JSONArray jsonArray = toJSONArray(strings);

		localizedValue.addString(LocaleUtil.US, jsonArray.toString());

		return localizedValue;
	}

	protected Map<String, String> createSelectedOption(
		String label, String value) {

		return createOption(label, "selected", value);
	}

	protected Map<String, String> createUnselectedOption(
		String label, String value) {

		return createOption(label, StringPool.BLANK, value);
	}

	protected String createValue(String... strings) {
		JSONArray jsonArray = toJSONArray(strings);

		return jsonArray.toString();
	}

	protected List<Object> getActualOptions(
		DDMFormFieldOptions ddmFormFieldOptions, String value,
		LocalizedValue predefinedValue, Locale locale) {

		SelectDDMFormFieldContextHelper selectDDMFormFieldContextHelper =
			new SelectDDMFormFieldContextHelper(
				_jsonFactory, ddmFormFieldOptions, value, predefinedValue,
				locale);

		return selectDDMFormFieldContextHelper.getOptions();
	}

	protected JSONArray toJSONArray(String... strings) {
		JSONArray jsonArray = _jsonFactory.createJSONArray();

		for (String string : strings) {
			jsonArray.put(string);
		}

		return jsonArray;
	}

	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

}