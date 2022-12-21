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

package com.liferay.osb.asah.common.filterexpression;

import com.liferay.osb.asah.common.filterexpression.parser.FilterExpressionBaseVisitor;
import com.liferay.osb.asah.common.filterexpression.parser.FilterExpressionParser;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Param;
import org.jooq.impl.DSL;

/**
 * @author Marcelllus Tavares
 * @author Ivica Cardic
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class FilterExpressionVisitor
	extends FilterExpressionBaseVisitor<Object> {

	@Override
	public Object visitAndExpression(
		FilterExpressionParser.AndExpressionContext andExpressionContext) {

		Condition left = _visitChild(andExpressionContext, 0);
		Condition right = _visitChild(andExpressionContext, 2);

		return left.and(right);
	}

	@Override
	public Object visitBooleanLiteral(
		FilterExpressionParser.BooleanLiteralContext booleanLiteralContext) {

		return DSL.val(Boolean.parseBoolean(booleanLiteralContext.getText()));
	}

	@Override
	public Object visitBooleanParenthesis(
		FilterExpressionParser.BooleanParenthesisContext
			booleanParenthesisContext) {

		return _visitChild(booleanParenthesisContext, 1);
	}

	@Override
	public Object visitEqualsExpression(
		FilterExpressionParser.EqualsExpressionContext
			equalsExpressionContext) {

		Field left = _visitChild(equalsExpressionContext, 0);
		Field right = _visitChild(equalsExpressionContext, 2);

		if (right == null) {
			return left.isNull();
		}

		return left.eq(right);
	}

	@Override
	public Object visitExpression(
		FilterExpressionParser.ExpressionContext expressionContext) {

		FilterExpressionParser.LogicalOrExpressionContext
			logicalOrExpressionContext =
				expressionContext.logicalOrExpression();

		return logicalOrExpressionContext.accept(this);
	}

	@Override
	public Object visitFilterExpression(
		FilterExpressionParser.FilterExpressionContext
			filterExpressionContext) {

		String filter = filterExpressionContext.filter.getText();

		filter = filter.replaceAll("\\s''", " '");
		filter = filter.replaceAll("''\\s", "' ");
		filter = filter.replaceAll("''\\)", "')");

		FilterExpression filterExpression = new FilterExpression(
			filter.substring(1, filter.length() - 1));

		return filterExpression.getCondition();
	}

	@Override
	public Object visitFloatingPointLiteral(
		FilterExpressionParser.FloatingPointLiteralContext
			floatingPointLiteralContext) {

		String doubleString = floatingPointLiteralContext.getText();

		return DSL.val(Double.parseDouble(doubleString));
	}

	@Override
	public Object visitFunctionCallExpression(
		FilterExpressionParser.FunctionCallExpressionContext
			functionCallExpressionContext) {

		Token functionNameToken = functionCallExpressionContext.functionName;

		String functionName = functionNameToken.getText();

		List<Object> parameters = _visitChild(functionCallExpressionContext, 2);

		Field field = (Field)parameters.get(0);

		if (functionName.equalsIgnoreCase("contains")) {
			return field.containsIgnoreCase(parameters.get(1));
		}
		else if (functionName.equalsIgnoreCase("endsWith")) {
			Param param = (Param)parameters.get(1);

			return field.similarTo("%" + param.getValue());
		}
		else if (functionName.equalsIgnoreCase("similarTo")) {
			Param param = (Param)parameters.get(1);

			return field.similarTo(
				StringUtils.replaceChars(
					String.valueOf(param.getValue()), ".*", "_%"));
		}
		else if (functionName.equalsIgnoreCase("startsWith")) {
			Param param = (Param)parameters.get(1);

			return field.similarTo(param.getValue() + "%");
		}

		return null;
	}

	@Override
	public Object visitFunctionParameters(
		FilterExpressionParser.FunctionParametersContext
			functionParametersContext) {

		List<Object> parameters = new ArrayList<>();

		for (int i = 0; i < functionParametersContext.getChildCount(); i++) {
			ParseTree child = functionParametersContext.getChild(i);

			if (child instanceof TerminalNode) {
				continue;
			}

			parameters.add(child.accept(this));
		}

		return parameters;
	}

	@Override
	public Object visitGreaterThanExpression(
		FilterExpressionParser.GreaterThanExpressionContext
			greaterThanExpressionContext) {

		Field left = _visitChild(greaterThanExpressionContext, 0);
		Field right = _visitChild(greaterThanExpressionContext, 2);

		return left.gt(right);
	}

	@Override
	public Object visitGreaterThanOrEqualsExpression(
		FilterExpressionParser.GreaterThanOrEqualsExpressionContext
			greaterThanOrEqualsExpressionContext) {

		Field left = _visitChild(greaterThanOrEqualsExpressionContext, 0);
		Field right = _visitChild(greaterThanOrEqualsExpressionContext, 2);

		return left.ge(right);
	}

	@Override
	public Object visitIntegerLiteral(
		FilterExpressionParser.IntegerLiteralContext integerLiteralContext) {

		String longString = integerLiteralContext.getText();

		return DSL.val(Long.parseLong(longString));
	}

	@Override
	public Object visitLessThanExpression(
		FilterExpressionParser.LessThanExpressionContext
			lessThanExpressionContext) {

		Field left = _visitChild(lessThanExpressionContext, 0);
		Field right = _visitChild(lessThanExpressionContext, 2);

		return left.lt(right);
	}

	@Override
	public Object visitLessThanOrEqualsExpression(
		FilterExpressionParser.LessThanOrEqualsExpressionContext
			lessThanOrEqualsExpressionContext) {

		Field left = _visitChild(lessThanOrEqualsExpressionContext, 0);
		Field right = _visitChild(lessThanOrEqualsExpressionContext, 2);

		return left.le(right);
	}

	@Override
	public Object visitLogicalVariable(
		FilterExpressionParser.LogicalVariableContext logicalVariableContext) {

		String fieldName = logicalVariableContext.getText();

		if (fieldName.startsWith("context/") ||
			fieldName.startsWith("dataSourceAccountPKs/") ||
			fieldName.startsWith("demographics/") ||
			fieldName.startsWith("organization/")) {

			String[] fieldNames = fieldName.split("/");

			fieldName = fieldNames[1];
		}

		return DSL.field(fieldName);
	}

	@Override
	public Object visitNotEqualsExpression(
		FilterExpressionParser.NotEqualsExpressionContext
			notEqualsExpressionContext) {

		Field left = _visitChild(notEqualsExpressionContext, 0);
		Field right = _visitChild(notEqualsExpressionContext, 2);

		if (right == null) {
			return left.isNotNull();
		}

		return left.ne(right);
	}

	@Override
	public Object visitNotExpression(
		FilterExpressionParser.NotExpressionContext notExpressionContext) {

		Condition condition = _visitChild(notExpressionContext, 1);

		return DSL.not(condition);
	}

	@Override
	public Object visitNullLiteral(
		FilterExpressionParser.NullLiteralContext nullLiteralContext) {

		return null;
	}

	@Override
	public Object visitOrExpression(
		FilterExpressionParser.OrExpressionContext orExpressionContext) {

		Condition left = _visitChild(orExpressionContext, 0);
		Condition right = _visitChild(orExpressionContext, 2);

		return left.or(right);
	}

	@Override
	public Object visitStringLiteral(
		FilterExpressionParser.StringLiteralContext stringLiteralContext) {

		String string = stringLiteralContext.getText();

		return DSL.val(StringUtil.unquoteAndDecodeInnerQuotes(string));
	}

	private <T> T _visitChild(
		ParserRuleContext parserRuleContext, int childIndex) {

		ParseTree parseTree = parserRuleContext.getChild(childIndex);

		return (T)parseTree.accept(this);
	}

}