/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;

import java.util.Locale;

/**
 * @author Pablo Carvalho
 */
public interface DDMFormEvaluator {

	public DDMFormEvaluationResult evaluate(
			DDMForm ddmForm, DDMFormValues ddmFormValues, Locale locale)
		throws DDMFormEvaluationException;

}