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
import com.liferay.osb.asah.common.filterexpression.parser.FilterExpressionLexer;
import com.liferay.osb.asah.common.filterexpression.parser.FilterExpressionParser;
import com.liferay.osb.asah.common.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Param;
import org.jooq.impl.DSL;
import org.springframework.util.Assert;

/**
 * @author Marcelllus Tavares
 */
public class FilterExpressionVisitor
	extends FilterExpressionBaseVisitor<Object> {


	@Override
	public Object visitExpression(FilterExpressionParser.ExpressionContext context) {
		FilterExpressionParser.LogicalOrExpressionContext logicalOrExpressionContext = context.logicalOrExpression();

		return logicalOrExpressionContext.accept(this);
	}

	@Override
	public Object visitEqualsExpression(
		FilterExpressionParser.EqualsExpressionContext context) {

		Field left = visitChild(context, 0);
		Field right = visitChild(context, 2);

		if (right == null) {
			return left.isNull();
		}

		return left.eq(right);
	}



	@Override
	public Object visitNotEqualsExpression(FilterExpressionParser.NotEqualsExpressionContext context) {
		Field left = visitChild(context, 0);
		Field right = visitChild(context, 2);

		if (right == null) {
			return left.isNotNull();
		}

		return left.ne(right);
	}

	@Override
	public Object visitBooleanParenthesis(FilterExpressionParser.BooleanParenthesisContext context) {
		return visitChild(context, 1);
	}

	@Override
	public Object visitAndExpression(FilterExpressionParser.AndExpressionContext context) {
		Condition left = visitChild(context, 0);
		Condition right = visitChild(context, 2);

		return left.and(right);
	}

	@Override
	public Object visitOrExpression(FilterExpressionParser.OrExpressionContext context) {
		Condition left = visitChild(context, 0);
		Condition right = visitChild(context, 2);

		return left.or(right);
	}


	@Override
	public Object visitLogicalVariable(
		FilterExpressionParser.LogicalVariableContext context) {

		return DSL.field(context.getText());
	}

	@Override
	public Object visitStringLiteral(FilterExpressionParser.StringLiteralContext context) {
		return DSL.val(StringUtil.unquoteAndDecodeInnerQuotes(context.getText()));
	}

	@Override
	public Object visitGreaterThanExpression(
		FilterExpressionParser.GreaterThanExpressionContext context) {
		Field left = visitChild(context, 0);
		Field right = visitChild(context, 2);

		return left.gt(right);
	}

	@Override
	public Object visitLessThanExpression(
		FilterExpressionParser.LessThanExpressionContext context) {
		Field left = visitChild(context, 0);
		Field right = visitChild(context, 2);

		return left.lt(right);
	}

	@Override
	public Object visitLessThanOrEqualsExpression(
		FilterExpressionParser.LessThanOrEqualsExpressionContext context) {

		Field left = visitChild(context, 0);
		Field right = visitChild(context, 2);

		return left.le(right);
	}

	@Override
	public Object visitIntegerLiteral(
		FilterExpressionParser.IntegerLiteralContext context) {

		return DSL.val(Long.parseLong(context.getText()));
	}

	@Override
	public Object visitNullLiteral(FilterExpressionParser.NullLiteralContext context) {
		return null;
	}

	@Override
	public Object visitFunctionCallExpression(FilterExpressionParser.FunctionCallExpressionContext context) {
		String functionName = context.functionName.getText();
		List<Object> parameters = visitChild(context, 2);


		Field field = (Field)parameters.get(0);

		if (functionName.equalsIgnoreCase("between")) {
//			if (arguments.size() != 3) {
//				return new IllegalArgumentException(
//					"Expected 3 arguments for " + stringFunction +
//						" function, got " + arguments.size() + " instead: " +
//						arguments);
//			}
		}
		else if (parameters.size() != 2) {
//			return new IllegalArgumentException(
//				"Expected 2 arguments for " + stringFunction + " function, " +
//					"got " + arguments.size() + " instead: " + arguments);
		}


		if (functionName.equalsIgnoreCase("between")) {
//			Field<Long> longField = DSL.field(fieldName, Long.class);
//
//			condition = DSL.and(
//				longField.ge(Long.valueOf(value)),
//				longField.le(
//					Long.valueOf(StringUtil.unquote(arguments.get(2)))));
		}
		else if (functionName.equalsIgnoreCase("contains")) {
			return  field.containsIgnoreCase(parameters.get(1));
		}
		else if (functionName.equalsIgnoreCase("endsWith")) {
			Param param = (Param)parameters.get(1);

			return  field.similarTo("%" + param.getValue());
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
		else {
//			return new IllegalArgumentException(
//				"Invalid string function: " + stringFunction);
		}

		return  null;
	}

	@Override
	public Object visitFunctionParameters(FilterExpressionParser.FunctionParametersContext context) {
		List<Object> parameters = new ArrayList<>();

		for (int i = 0; i < context.getChildCount(); i++ ) {
			ParseTree child = context.getChild(i);

			if (child instanceof TerminalNode) {
				continue;
			}

			parameters.add(child.accept(this));
		}

		return parameters;
	}

	@Override
	public Object visitGreaterThanOrEqualsExpression(
		FilterExpressionParser.GreaterThanOrEqualsExpressionContext context) {
		Field left = visitChild(context, 0);
		Field right = visitChild(context, 2);

		return left.ge(right);

	}

	@Override
	public Object visitFloatingPointLiteral(FilterExpressionParser.FloatingPointLiteralContext context) {
		return DSL.val(Double.parseDouble(context.getText()));
	}

	@Override
	public Object visitBooleanLiteral(FilterExpressionParser.BooleanLiteralContext context) {
		return DSL.val(Boolean.parseBoolean(context.getText()));
	}

	protected <T> T visitChild(
		ParserRuleContext parserRuleContext, int childIndex) {

		ParseTree parseTree = parserRuleContext.getChild(childIndex);

		return (T)parseTree.accept(this);
	}

	private Stack<Field> _fieldStack;
}
