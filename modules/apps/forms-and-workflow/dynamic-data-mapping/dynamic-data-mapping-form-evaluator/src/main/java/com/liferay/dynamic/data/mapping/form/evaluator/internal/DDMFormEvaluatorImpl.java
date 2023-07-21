/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFactory;
import com.liferay.dynamic.data.mapping.form.evaluator.DDMFormEvaluationException;
import com.liferay.dynamic.data.mapping.form.evaluator.DDMFormEvaluationResult;
import com.liferay.dynamic.data.mapping.form.evaluator.DDMFormEvaluator;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesTracker;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pablo Carvalho
 */
@Component(immediate = true)
public class DDMFormEvaluatorImpl implements DDMFormEvaluator {

	@Override
	public DDMFormEvaluationResult evaluate(
			DDMForm ddmForm, DDMFormValues ddmFormValues, Locale locale)
		throws DDMFormEvaluationException {

		try {
			DDMFormEvaluatorHelper ddmFormEvaluatorHelper =
				new DDMFormEvaluatorHelper(
					ddmForm, ddmFormValues, _ddmFormFieldTypeServicesTracker,
					locale);

			ddmFormEvaluatorHelper.setDDMExpressionFactory(
				_ddmExpressionFactory);
			ddmFormEvaluatorHelper.setJSONFactory(_jsonFactory);

			return ddmFormEvaluatorHelper.evaluate();
		}
		catch (PortalException pe) {
			throw new DDMFormEvaluationException(pe);
		}
	}

	@Reference
	private DDMExpressionFactory _ddmExpressionFactory;

	@Reference
	private DDMFormFieldTypeServicesTracker _ddmFormFieldTypeServicesTracker;

	@Reference
	private JSONFactory _jsonFactory;

}