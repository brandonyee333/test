/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.util.HashUtil;

import java.io.Serializable;

import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class DDMFormFieldValidation implements Serializable {

	public DDMFormFieldValidation() {
	}

	public DDMFormFieldValidation(
		DDMFormFieldValidation ddmFormFieldValidation) {

		_expression = ddmFormFieldValidation._expression;
		_errorMessage = ddmFormFieldValidation._errorMessage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMFormFieldValidation)) {
			return false;
		}

		DDMFormFieldValidation ddmFormFieldValidation =
			(DDMFormFieldValidation)obj;

		if (Objects.equals(
				_errorMessage, ddmFormFieldValidation._errorMessage) &&
			Objects.equals(_expression, ddmFormFieldValidation._expression)) {

			return true;
		}

		return false;
	}

	public String getErrorMessage() {
		return _errorMessage;
	}

	public String getExpression() {
		return _expression;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _errorMessage);

		return HashUtil.hash(hash, _expression);
	}

	public void setErrorMessage(String errorMessage) {
		_errorMessage = errorMessage;
	}

	public void setExpression(String expression) {
		_expression = expression;
	}

	private String _errorMessage;
	private String _expression;

}