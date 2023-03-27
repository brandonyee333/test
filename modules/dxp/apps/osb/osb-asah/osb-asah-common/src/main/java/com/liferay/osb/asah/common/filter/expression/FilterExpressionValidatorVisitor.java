/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.filter.expression;

import com.liferay.osb.asah.common.filter.expression.parser.FilterExpressionBaseVisitor;
import com.liferay.osb.asah.common.filter.expression.parser.FilterExpressionParser;

import java.math.BigDecimal;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Ivica Cardic
 */
public class FilterExpressionValidatorVisitor
	extends FilterExpressionBaseVisitor<Object> {

	public Object visitFilterExpression(
		FilterExpressionParser.FilterExpressionContext
			filterExpressionContext) {

		String filterString = _parseFilterStringExpression(
			filterExpressionContext.filter);

		new FilterExpression(
			filterString.substring(1, filterString.length() - 1), this);

		return null;
	}

	@Override
	public Object visitToLiteral(
		FilterExpressionParser.ToLiteralContext toLiteralContext) {

		ParseTree childParseTree = toLiteralContext.getChild(0);

		if (NumberUtils.isCreatable(childParseTree.getText())) {
			BigDecimal bigDecimal = new BigDecimal(childParseTree.getText());

			BigDecimal maxIntegerBigDecimal = BigDecimal.valueOf(
				Integer.MAX_VALUE);

			if (bigDecimal.compareTo(maxIntegerBigDecimal) > 0) {
				throw new FilterExpressionParserException(
					"Invalid numeric value");
			}
		}

		return null;
	}

	private String _parseFilterStringExpression(Token filterToken) {
		String filterString = filterToken.getText();

		filterString = filterString.replaceAll("''\\)", "')");
		filterString = filterString.replaceAll("''\\s", "' ");
		filterString = filterString.replaceAll("\\s''", " '");

		return filterString;
	}

}