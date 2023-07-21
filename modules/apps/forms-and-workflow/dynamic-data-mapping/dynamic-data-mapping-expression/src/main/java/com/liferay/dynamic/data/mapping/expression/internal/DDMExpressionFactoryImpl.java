/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression.internal;

import com.liferay.dynamic.data.mapping.expression.DDMExpression;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionException;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = DDMExpressionFactory.class)
public class DDMExpressionFactoryImpl implements DDMExpressionFactory {

	@Override
	public DDMExpression<Boolean> createBooleanDDMExpression(
			String expressionString)
		throws DDMExpressionException {

		return new DDMExpressionImpl<>(expressionString, Boolean.class);
	}

	@Override
	public DDMExpression<Double> createDoubleDDMExpression(
			String expressionString)
		throws DDMExpressionException {

		return new DDMExpressionImpl<>(expressionString, Double.class);
	}

	@Override
	public DDMExpression<Float> createFloatDDMExpression(
			String expressionString)
		throws DDMExpressionException {

		return new DDMExpressionImpl<>(expressionString, Float.class);
	}

	@Override
	public DDMExpression<Integer> createIntegerDDMExpression(
			String expressionString)
		throws DDMExpressionException {

		return new DDMExpressionImpl<>(expressionString, Integer.class);
	}

	@Override
	public DDMExpression<Long> createLongDDMExpression(String expressionString)
		throws DDMExpressionException {

		return new DDMExpressionImpl<>(expressionString, Long.class);
	}

	@Override
	public DDMExpression<String> createStringDDMExpression(
			String expressionString)
		throws DDMExpressionException {

		return new DDMExpressionImpl<>(expressionString, String.class);
	}

}