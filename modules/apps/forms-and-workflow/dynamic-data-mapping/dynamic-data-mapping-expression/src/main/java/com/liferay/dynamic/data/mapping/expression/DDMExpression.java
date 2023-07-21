/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression;

import java.math.MathContext;

import java.util.Map;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
public interface DDMExpression<T> {

	public T evaluate() throws DDMExpressionException;

	public Map<String, VariableDependencies> getVariableDependenciesMap()
		throws DDMExpressionException;

	public default boolean hasVariable(String variableName) {
		throw new UnsupportedOperationException();
	}

	public void setBooleanVariableValue(
		String variableName, Boolean variableValue);

	public void setDoubleVariableValue(
		String variableName, Double variableValue);

	public void setExpressionStringVariableValue(
		String variableName, String variableValue);

	public void setFloatVariableValue(String variableName, Float variableValue);

	public void setIntegerVariableValue(
		String variableName, Integer variableValue);

	public void setLongVariableValue(String variableName, Long variableValue);

	public void setMathContext(MathContext mathContext);

	public void setStringVariableValue(
			String variableName, String variableValue)
		throws DDMExpressionException;

}