/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class DDMFormFieldEvaluationResult {

	public DDMFormFieldEvaluationResult(String name, String instanceId) {
		_name = name;
		_instanceId = instanceId;
	}

	public String getErrorMessage() {
		return _errorMessage;
	}

	public String getInstanceId() {
		return _instanceId;
	}

	public String getName() {
		return _name;
	}

	@JSON(name = "nestedFields")
	public List<DDMFormFieldEvaluationResult>
		getNestedDDMFormFieldEvaluationResults() {

		return _nestedDDMFormFieldEvaluationResults;
	}

	public boolean isValid() {
		return _valid;
	}

	public boolean isVisible() {
		return _visible;
	}

	public void setErrorMessage(String errorMessage) {
		_errorMessage = errorMessage;
	}

	public void setNestedDDMFormFieldEvaluationResults(
		List<DDMFormFieldEvaluationResult>
			nestedDDMFormFieldEvaluationResults) {

		_nestedDDMFormFieldEvaluationResults =
			nestedDDMFormFieldEvaluationResults;
	}

	public void setValid(boolean valid) {
		_valid = valid;
	}

	public void setVisible(boolean visible) {
		_visible = visible;
	}

	private String _errorMessage = StringPool.BLANK;
	private final String _instanceId;
	private final String _name;
	private List<DDMFormFieldEvaluationResult>
		_nestedDDMFormFieldEvaluationResults = new ArrayList<>();
	private boolean _valid = true;
	private boolean _visible = true;

}