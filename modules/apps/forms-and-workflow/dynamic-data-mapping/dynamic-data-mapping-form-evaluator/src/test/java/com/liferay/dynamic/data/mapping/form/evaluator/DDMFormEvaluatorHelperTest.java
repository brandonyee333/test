/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator;

import com.liferay.dynamic.data.mapping.expression.DDMExpression;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFactory;
import com.liferay.dynamic.data.mapping.form.evaluator.internal.DDMFormEvaluatorHelper;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesTracker;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.RegistryUtil;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Leonardo Barros
 */
@PrepareForTest({LanguageUtil.class, ResourceBundleUtil.class})
@RunWith(PowerMockRunner.class)
public class DDMFormEvaluatorHelperTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		RegistryUtil.setRegistry(new BasicRegistryImpl());

		setDDMExpressionFactory();
		setUpLanguageUtil();
		setUpResourceBundleUtil();
	}

	@Test
	public void testInvalidEmailFieldWithCustomMessage() throws Exception {
		when(
			_ddmExpression.evaluate()
		).thenReturn(
			false
		);

		DDMForm ddmForm = new DDMForm();

		DDMFormField ddmFormField = new DDMFormField("emailField", "text");

		DDMFormFieldValidation ddmFormFieldValidation =
			new DDMFormFieldValidation();

		ddmFormFieldValidation.setExpression("isEmailAddress(emailField)");
		ddmFormFieldValidation.setErrorMessage(
			"This is an invalid email address.");

		ddmFormField.setDDMFormFieldValidation(ddmFormFieldValidation);

		ddmForm.addDDMFormField(ddmFormField);

		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setName("emailField");
		ddmFormFieldValue.setValue(new UnlocalizedValue("invalidEmail"));

		ddmFormValues.addDDMFormFieldValue(ddmFormFieldValue);

		DDMFormEvaluatorHelper ddmFormEvaluatorHelper =
			createDDMFormEvaluatorHelper(ddmForm, ddmFormValues, LocaleUtil.US);

		DDMFormEvaluationResult ddmFormEvaluationResult =
			ddmFormEvaluatorHelper.evaluate();

		List<DDMFormFieldEvaluationResult> ddmFormFieldEvaluationResults =
			ddmFormEvaluationResult.getDDMFormFieldEvaluationResults();

		Assert.assertEquals(
			ddmFormFieldEvaluationResults.toString(), 1,
			ddmFormFieldEvaluationResults.size());

		DDMFormFieldEvaluationResult ddmFormFieldEvaluationResult =
			ddmFormFieldEvaluationResults.get(0);

		Assert.assertFalse(ddmFormFieldEvaluationResult.isValid());

		Assert.assertEquals(
			"This is an invalid email address.",
			ddmFormFieldEvaluationResult.getErrorMessage());
	}

	@Test
	public void testInvalidEmailFieldWithoutCustomMessage() throws Exception {
		when(
			_ddmExpression.evaluate()
		).thenReturn(
			false
		);

		DDMForm ddmForm = new DDMForm();

		DDMFormField ddmFormField = new DDMFormField("emailField", "text");

		DDMFormFieldValidation ddmFormFieldValidation =
			new DDMFormFieldValidation();

		ddmFormFieldValidation.setExpression("isEmailAddress(emailField)");

		ddmFormField.setDDMFormFieldValidation(ddmFormFieldValidation);

		ddmForm.addDDMFormField(ddmFormField);

		DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setName("emailField");
		ddmFormFieldValue.setValue(new UnlocalizedValue("invalidEmail"));

		ddmFormValues.addDDMFormFieldValue(ddmFormFieldValue);

		DDMFormEvaluatorHelper ddmFormEvaluatorHelper =
			createDDMFormEvaluatorHelper(ddmForm, ddmFormValues, LocaleUtil.US);

		DDMFormEvaluationResult ddmFormEvaluationResult =
			ddmFormEvaluatorHelper.evaluate();

		List<DDMFormFieldEvaluationResult> ddmFormFieldEvaluationResults =
			ddmFormEvaluationResult.getDDMFormFieldEvaluationResults();

		Assert.assertEquals(
			ddmFormFieldEvaluationResults.toString(), 1,
			ddmFormFieldEvaluationResults.size());

		DDMFormFieldEvaluationResult ddmFormFieldEvaluationResult =
			ddmFormFieldEvaluationResults.get(0);

		Assert.assertFalse(ddmFormFieldEvaluationResult.isValid());

		Assert.assertEquals(
			"This field is invalid.",
			ddmFormFieldEvaluationResult.getErrorMessage());
	}

	protected DDMFormEvaluatorHelper createDDMFormEvaluatorHelper(
			DDMForm ddmForm, DDMFormValues ddmFormValues, Locale locale)
		throws Exception {

		DDMFormEvaluatorHelper ddmFormEvaluatorHelper =
			new DDMFormEvaluatorHelper(
				ddmForm, ddmFormValues, _ddmFormFieldTypeServicesTracker,
				locale);

		field(
			DDMFormEvaluatorHelper.class, "_ddmExpressionFactory"
		).set(
			ddmFormEvaluatorHelper, _ddmExpressionFactory
		);

		field(
			DDMFormEvaluatorHelper.class, "_jsonFactory"
		).set(
			ddmFormEvaluatorHelper, _jsonFactory
		);

		return ddmFormEvaluatorHelper;
	}

	protected void setDDMExpressionFactory() throws Exception {
		when(
			_ddmExpressionFactory.createBooleanDDMExpression(
				Matchers.anyString())
		).thenReturn(
			_ddmExpression
		);
	}

	protected void setUpLanguageUtil() {
		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(_language);

		when(
			_language.get(_resourceBundle, "this-field-is-invalid")
		).thenReturn(
			"This field is invalid."
		);
	}

	protected void setUpResourceBundleUtil() {
		mockStatic(ResourceBundleUtil.class);

		when(
			ResourceBundleUtil.getBundle(
				Matchers.anyString(), Matchers.any(Locale.class),
				Matchers.any(ClassLoader.class))
		).thenReturn(
			_resourceBundle
		);
	}

	@Mock
	private DDMExpression<Boolean> _ddmExpression;

	@Mock
	private DDMExpressionFactory _ddmExpressionFactory;

	@Mock
	private DDMFormFieldTypeServicesTracker _ddmFormFieldTypeServicesTracker;

	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

	@Mock
	private Language _language;

	@Mock
	private ResourceBundle _resourceBundle;

}