/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator;

import com.liferay.portal.kernel.json.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class DDMFormEvaluationResult {

	@JSON(name = "fields")
	public List<DDMFormFieldEvaluationResult>
		getDDMFormFieldEvaluationResults() {

		return _ddmFormFieldEvaluationResults;
	}

	public Map<String, DDMFormFieldEvaluationResult>
		getDDMFormFieldEvaluationResultsMap() {

		Map<String, DDMFormFieldEvaluationResult>
			ddmFormFieldEvaluationResultsMap = new HashMap<>();

		populateDDMFormFieldEvaluationResultsMap(
			_ddmFormFieldEvaluationResults, ddmFormFieldEvaluationResultsMap);

		return ddmFormFieldEvaluationResultsMap;
	}

	public void setDDMFormFieldEvaluationResults(
		List<DDMFormFieldEvaluationResult> ddmFormFieldEvaluationResults) {

		_ddmFormFieldEvaluationResults = ddmFormFieldEvaluationResults;
	}

	protected void populateDDMFormFieldEvaluationResultsMap(
		List<DDMFormFieldEvaluationResult> ddmFormFieldEvaluationResults,
		Map<String, DDMFormFieldEvaluationResult>
			ddmFormFieldEvaluationResultsMap) {

		for (DDMFormFieldEvaluationResult ddmFormFieldEvaluationResult :
				ddmFormFieldEvaluationResults) {

			ddmFormFieldEvaluationResultsMap.put(
				ddmFormFieldEvaluationResult.getName(),
				ddmFormFieldEvaluationResult);

			populateDDMFormFieldEvaluationResultsMap(
				ddmFormFieldEvaluationResult.
					getNestedDDMFormFieldEvaluationResults(),
				ddmFormFieldEvaluationResultsMap);
		}
	}

	private List<DDMFormFieldEvaluationResult> _ddmFormFieldEvaluationResults =
		new ArrayList<>();

}