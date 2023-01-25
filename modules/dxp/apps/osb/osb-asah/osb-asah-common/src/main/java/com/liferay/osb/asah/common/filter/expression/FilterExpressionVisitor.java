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

import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.filter.expression.parser.FilterExpressionBaseVisitor;
import com.liferay.osb.asah.common.filter.expression.parser.FilterExpressionParser;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

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

	public FilterExpressionVisitor(
		String filterType,
		List<FilterStringConverterHelper>
			filterTypeFilterStringConverterHelpers,
		Set<String> includedTableNames) {

		_filterType = filterType;
		_filterTypeFilterStringConverterHelpers =
			filterTypeFilterStringConverterHelpers;
		_includedTableNames = includedTableNames;
	}

	@Override
	public Object visitAndExpression(
		FilterExpressionParser.AndExpressionContext andExpressionContext) {

		Condition leftCondition = _visitChild(andExpressionContext, 0);
		Condition rightCondition = _visitChild(andExpressionContext, 2);

		return leftCondition.and(rightCondition);
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

		Field rightField = _visitChild(equalsExpressionContext, 2);

		Field leftField = _getLeftField(equalsExpressionContext, rightField);

		if (rightField == null) {
			return leftField.isNull();
		}

		rightField = _checkRightField(leftField, rightField);

		Condition condition = _getLogicFunctionCondition(
			leftField, rightField, "eq");

		if (condition != null) {
			return condition;
		}

		return leftField.eq(rightField);
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
	public Object visitFilterByCountExpression(
		FilterExpressionParser.FilterByCountExpressionContext
			filterExpressionContext) {

		_includedTableNames.add("Identity");

		String filter = _parseFilterExpression(filterExpressionContext.filter);

		FilterExpression filterExpression = new FilterExpression(
			filter.substring(1, filter.length() - 1),
			filterExpressionContext.filterType.getText(),
			_filterTypeFilterStringConverterHelpers, _includedTableNames);

		String operator = filterExpressionContext.operator.getText();
		Integer value = Integer.parseInt(
			filterExpressionContext.value.getText());
		Field userIdField = DSL.field("Event.userId");

		Condition havingCondition = null;

		if (Objects.equals(StringUtil.unquote(operator), "ge")) {
			havingCondition = DSL.count(
				userIdField
			).ge(
				value
			);
		}
		else {
			havingCondition = DSL.count(
				userIdField
			).le(
				value
			);
		}

		return DSL.exists(
			DSL.select(
				userIdField
			).from(
				DSL.table(
					"BQEvent"
				).as(
					"Event"
				)
			).where(
				filterExpression.getCondition()
			).and(
				userIdField.eq(DSL.field("Identity.id"))
			).groupBy(
				userIdField
			).having(
				havingCondition
			));
	}

	@Override
	public Object visitFilterExpression(
		FilterExpressionParser.FilterExpressionContext
			filterExpressionContext) {

		String filter = _parseFilterExpression(filterExpressionContext.filter);

		FilterExpression filterExpression = new FilterExpression(
			filter.substring(1, filter.length() - 1),
			filterExpressionContext.filterType.getText(),
			_filterTypeFilterStringConverterHelpers, _includedTableNames);

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

		throw new FilterExpressionParserException(
			"Invalid string function: " + functionName);
	}

	@Override
	public Object visitFunctionParameters(
		FilterExpressionParser.FunctionParametersContext
			functionParametersContext) {

		List<Object> parameters = new ArrayList<>();

		ParseTree childIdentifierParseTree = functionParametersContext.getChild(
			0);

		ParseTree childLiteralParseTree = functionParametersContext.getChild(2);

		Object value = childLiteralParseTree.accept(this);

		parameters.add(_getField(childIdentifierParseTree.getText(), value));
		parameters.add(value);

		return parameters;
	}

	@Override
	public Object visitGreaterThanExpression(
		FilterExpressionParser.GreaterThanExpressionContext
			greaterThanExpressionContext) {

		Field rightField = _visitChild(greaterThanExpressionContext, 2);

		Field leftField = _getLeftField(
			greaterThanExpressionContext, rightField);

		Condition condition = _getLogicFunctionCondition(
			leftField, rightField, "gt");

		if (condition != null) {
			return condition;
		}

		return leftField.gt(rightField);
	}

	@Override
	public Object visitGreaterThanOrEqualsExpression(
		FilterExpressionParser.GreaterThanOrEqualsExpressionContext
			greaterThanOrEqualsExpressionContext) {

		Field rightField = _visitChild(greaterThanOrEqualsExpressionContext, 2);

		Field leftField = _getLeftField(
			greaterThanOrEqualsExpressionContext, rightField);

		Condition condition = _getLogicFunctionCondition(
			leftField, rightField, "ge");

		if (condition != null) {
			return condition;
		}

		return leftField.ge(rightField);
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

		Field rightField = _visitChild(lessThanExpressionContext, 2);

		Field leftField = _getLeftField(lessThanExpressionContext, rightField);

		Condition condition = _getLogicFunctionCondition(
			leftField, rightField, "lt");

		if (condition != null) {
			return condition;
		}

		return leftField.lt(rightField);
	}

	@Override
	public Object visitLessThanOrEqualsExpression(
		FilterExpressionParser.LessThanOrEqualsExpressionContext
			lessThanOrEqualsExpressionContext) {

		Field rightField = _visitChild(lessThanOrEqualsExpressionContext, 2);

		Field leftField = _getLeftField(
			lessThanOrEqualsExpressionContext, rightField);

		Condition condition = _getLogicFunctionCondition(
			leftField, rightField, "le");

		if (condition != null) {
			return condition;
		}

		return leftField.le(rightField);
	}

	@Override
	public Object visitNotEqualsExpression(
		FilterExpressionParser.NotEqualsExpressionContext
			notEqualsExpressionContext) {

		Field rightField = _visitChild(notEqualsExpressionContext, 2);

		Field leftField = _getLeftField(notEqualsExpressionContext, rightField);

		if (rightField == null) {
			return leftField.isNotNull();
		}

		Condition condition = _getLogicFunctionCondition(
			leftField, rightField, "ne");

		if (condition != null) {
			return condition;
		}

		return leftField.ne(rightField);
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

		Condition leftCondition = _visitChild(orExpressionContext, 0);
		Condition rightCondition = _visitChild(orExpressionContext, 2);

		return leftCondition.or(rightCondition);
	}

	@Override
	public Object visitStringLiteral(
		FilterExpressionParser.StringLiteralContext stringLiteralContext) {

		String string = stringLiteralContext.getText();

		return DSL.val(StringUtil.unquoteAndDecodeInnerQuotes(string));
	}

	private Field _checkRightField(Field leftField, Field rightField) {
		if (!(rightField instanceof Param)) {
			return rightField;
		}

		Param rightFieldParam = (Param)rightField;

		Object paramValue = rightFieldParam.getValue();

		if (!(paramValue instanceof String)) {
			return rightField;
		}

		FilterStringConverterHelper filterStringConverterHelper =
			_getFilterStringConverterHelper(_filterType);

		if (filterStringConverterHelper == null) {
			return rightField;
		}

		String valueString = (String)paramValue;

		String newValueString = StringUtil.unquote(valueString);

		Object value = null;

		Object processedValue = filterStringConverterHelper.processValue(
			leftField.getName(), newValueString);

		if ((processedValue == null) &&
			NumberUtils.isCreatable(newValueString) &&
			!newValueString.contains(".")) {

			value = Long.valueOf(newValueString);
		}
		else if (processedValue != null) {
			value = processedValue;
		}

		if (value != null) {
			return DSL.val(value);
		}

		return rightField;
	}

	private Field<?> _getField(String fieldName, Object value) {
		if (fieldName.contains("/")) {
			String[] fieldNames = fieldName.split("/");

			fieldName = fieldNames[1];
		}

		FilterStringConverterHelper filterStringConverterHelper =
			_getFilterStringConverterHelper(_filterType);

		if (filterStringConverterHelper != null) {
			Map<String, String> fieldNameConversionMap =
				filterStringConverterHelper.getFieldNameConversionMap();

			if (fieldNameConversionMap.containsKey(fieldName)) {
				fieldName = fieldNameConversionMap.get(fieldName);
			}

			if ((_includedTableNames != null) &&
				StringUtils.isNotEmpty(
					filterStringConverterHelper.getTableName())) {

				String tableName = filterStringConverterHelper.getTableName();

				_includedTableNames.add(tableName);

				if (!StringUtils.startsWithIgnoreCase(fieldName, tableName)) {
					fieldName = tableName + "." + fieldName;
				}
			}
		}

		Field field = null;

		if (value instanceof Long) {
			field = DSL.cast(DSL.field(fieldName), Long.class);
		}
		else {
			field = DSL.field(fieldName);
		}

		return field;
	}

	private FilterStringConverterHelper _getFilterStringConverterHelper(
		String filterType) {

		for (FilterStringConverterHelper filterStringConverterHelper :
				_filterTypeFilterStringConverterHelpers) {

			if (Objects.equals(
					filterStringConverterHelper.getFilterType(), filterType)) {

				return filterStringConverterHelper;
			}
		}

		return null;
	}

	private Field _getLeftField(
		ParserRuleContext parserRuleContext, Field rightField) {

		if (parserRuleContext.getChild(0) instanceof TerminalNode) {
			TerminalNode terminalNode =
				(TerminalNode)parserRuleContext.getChild(0);

			Object value = null;

			if (rightField instanceof Param) {
				Param rightFieldParma = (Param)rightField;

				value = rightFieldParma.getValue();
			}

			return _getField(terminalNode.getText(), value);
		}

		return _visitChild(parserRuleContext, 0);
	}

	private Condition _getLogicFunctionCondition(
		Field leftField, Field rightField, String operator) {

		if (!(rightField instanceof Param)) {
			return null;
		}

		Param<?> param = (Param<?>)rightField;

		if (!(param.getValue() instanceof String)) {
			return null;
		}

		FilterStringConverterHelper filterStringConverterHelper =
			_getFilterStringConverterHelper(_filterType);

		if (filterStringConverterHelper == null) {
			return null;
		}

		try {
			return filterStringConverterHelper.getLogicFunctionCondition(
				leftField.getName(), operator, false, (String)param.getValue());
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private String _parseFilterExpression(Token filterToken) {
		String filter = filterToken.getText();

		filter = filter.replaceAll("\\s''", " '");
		filter = filter.replaceAll("''\\s", "' ");
		filter = filter.replaceAll("''\\)", "')");

		return filter;
	}

	private <T> T _visitChild(
		ParserRuleContext parserRuleContext, int childIndex) {

		ParseTree parseTree = parserRuleContext.getChild(childIndex);

		return (T)parseTree.accept(this);
	}

	private final String _filterType;
	private final List<FilterStringConverterHelper>
		_filterTypeFilterStringConverterHelpers;
	private final Set<String> _includedTableNames;

}