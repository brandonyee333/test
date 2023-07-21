/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression.internal;

import java.math.BigDecimal;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
public class Variable {

	public Variable(String name) {
		_name = name;
	}

	public Variable(String name, BigDecimal value) {
		_name = name;
		_value = value;
	}

	public String getExpressionString() {
		return _expressionString;
	}

	public String getName() {
		return _name;
	}

	public BigDecimal getValue() {
		return _value;
	}

	public void setExpressionString(String expressionString) {
		_expressionString = expressionString;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setValue(BigDecimal value) {
		_value = value;
	}

	private String _expressionString;
	private String _name;
	private BigDecimal _value;

}