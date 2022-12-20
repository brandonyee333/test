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
public class FilterExpressionVisitor
	extends FilterExpressionBaseVisitor<Object> {

	@Override
	public Object visitAndExpression(
		FilterExpressionParser.AndExpressionContext context) {

		Condition left = _visitChild(context, 0);

		return left.and((Condition)_visitChild(context, 2));
	}

	@Override
	public Object visitBooleanLiteral(
		FilterExpressionParser.BooleanLiteralContext context) {

		return DSL.val(Boolean.parseBoolean(context.getText()));
	}

	@Override
	public Object visitBooleanParenthesis(
		FilterExpressionParser.BooleanParenthesisContext context) {

		return _visitChild(context, 1);
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object visitEqualsExpression(
		FilterExpressionParser.EqualsExpressionContext context) {

		Field left = _visitChild(context, 0);
		Field right = _visitChild(context, 2);

		if (right == null) {
			return left.isNull();
		}

		return left.eq(right);
	}

	@Override
	public Object visitExpression(
		FilterExpressionParser.ExpressionContext context) {

		FilterExpressionParser.LogicalOrExpressionContext
			logicalOrExpressionContext = context.logicalOrExpression();

		return logicalOrExpressionContext.accept(this);
	}

	@Override
	public Object visitFilterLiteral(
		FilterExpressionParser.FilterLiteralContext ctx) {

		String filter = ctx.filter.getText();

		filter = filter.replaceAll("\\s''", " '");
		filter = filter.replaceAll("''\\s", "' ");
		filter = filter.replaceAll("''\\)", "')");

		FilterExpression filterExpression = new FilterExpression(
			filter.substring(1, filter.length() - 1));

		return filterExpression.getCondition();
	}

	@Override
	public Object visitFloatingPointLiteral(
		FilterExpressionParser.FloatingPointLiteralContext context) {

		return DSL.val(Double.parseDouble(context.getText()));
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object visitFunctionCallExpression(
		FilterExpressionParser.FunctionCallExpressionContext context) {

		String functionName = context.functionName.getText();

		List<Object> parameters = _visitChild(context, 2);

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
		FilterExpressionParser.FunctionParametersContext context) {

		List<Object> parameters = new ArrayList<>();

		for (int i = 0; i < context.getChildCount(); i++) {
			ParseTree child = context.getChild(i);

			if (child instanceof TerminalNode) {
				continue;
			}

			parameters.add(child.accept(this));
		}

		return parameters;
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object visitGreaterThanExpression(
		FilterExpressionParser.GreaterThanExpressionContext context) {

		Field left = _visitChild(context, 0);

		return left.gt((Field)_visitChild(context, 2));
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object visitGreaterThanOrEqualsExpression(
		FilterExpressionParser.GreaterThanOrEqualsExpressionContext context) {

		Field left = _visitChild(context, 0);

		return left.ge((Field)_visitChild(context, 2));
	}

	@Override
	public Object visitIntegerLiteral(
		FilterExpressionParser.IntegerLiteralContext context) {

		return DSL.val(Long.parseLong(context.getText()));
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object visitLessThanExpression(
		FilterExpressionParser.LessThanExpressionContext context) {

		Field left = _visitChild(context, 0);

		return left.lt((Field)_visitChild(context, 2));
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object visitLessThanOrEqualsExpression(
		FilterExpressionParser.LessThanOrEqualsExpressionContext context) {

		Field left = _visitChild(context, 0);

		return left.le((Field)_visitChild(context, 2));
	}

	@Override
	public Object visitLogicalVariable(
		FilterExpressionParser.LogicalVariableContext context) {

		String fieldName = context.getText();

		if (fieldName.startsWith("context/") ||
			fieldName.startsWith("dataSourceAccountPKs/") ||
			fieldName.startsWith("demographics/") ||
			fieldName.startsWith("organization/")) {

			String[] fieldNames = fieldName.split("\\/");

			fieldName = fieldNames[1];
		}

		return DSL.field(fieldName);
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object visitNotEqualsExpression(
		FilterExpressionParser.NotEqualsExpressionContext context) {

		Field left = _visitChild(context, 0);
		Field right = _visitChild(context, 2);

		if (right == null) {
			return left.isNotNull();
		}

		return left.ne(right);
	}

	@Override
	public Object visitNotExpression(
		FilterExpressionParser.NotExpressionContext context) {

		return DSL.not((Condition)_visitChild(context, 1));
	}

	@Override
	public Object visitNullLiteral(
		FilterExpressionParser.NullLiteralContext context) {

		return null;
	}

	@Override
	public Object visitOrExpression(
		FilterExpressionParser.OrExpressionContext context) {

		Condition left = _visitChild(context, 0);

		return left.or((Condition)_visitChild(context, 2));
	}

	@Override
	public Object visitStringLiteral(
		FilterExpressionParser.StringLiteralContext context) {

		return DSL.val(
			StringUtil.unquoteAndDecodeInnerQuotes(context.getText()));
	}

	@SuppressWarnings("unchecked")
	private <T> T _visitChild(
		ParserRuleContext parserRuleContext, int childIndex) {

		ParseTree parseTree = parserRuleContext.getChild(childIndex);

		return (T)parseTree.accept(this);
	}

}