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

import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.filter.expression.parser.FilterExpressionBaseVisitor;
import com.liferay.osb.asah.common.filter.expression.parser.FilterExpressionParser;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
 * @author Marcellus Tavares
 * @author Ivica Cardic
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class FilterExpressionVisitor
	extends FilterExpressionBaseVisitor<Object> {

	public FilterExpressionVisitor(String filterType) {
		_filterType = filterType;

		if (filterType != null) {
			_referencedTableNames.add(_tableReferences.get(filterType));
		}

		_fieldMappers.put("activities.day", "Event.eventDate");
		_fieldMappers.put("custom.name", "ExpandoValue.columnId");
		_fieldMappers.put("individuals.email", "Individual.emailAddress");
	}

	public Set<String> getReferencedTableNames() {
		return _referencedTableNames;
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

		Field rightField = _getRightField(equalsExpressionContext);

		Field leftField = _getLeftField(equalsExpressionContext, rightField);

		if (rightField == null) {
			return leftField.isNull();
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
			filterByCountExpressionContext) {

		String filterString = _parseFilterStringExpression(
			filterByCountExpressionContext.filter);

		FilterExpression filterExpression = new FilterExpression(
			filterString.substring(1, filterString.length() - 1),
			filterByCountExpressionContext.filterType.getText());

		_referencedTableNames.addAll(
			filterExpression.getReferencedTableNames());

		String operator = filterByCountExpressionContext.operator.getText();
		Integer value = Integer.parseInt(
			filterByCountExpressionContext.value.getText());
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

		String filterString = _parseFilterStringExpression(
			filterExpressionContext.filter);

		FilterExpression filterExpression = new FilterExpression(
			filterString.substring(1, filterString.length() - 1),
			filterExpressionContext.filterType.getText());

		_referencedTableNames.addAll(
			filterExpression.getReferencedTableNames());

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

		for (int i = 0; i < functionParametersContext.getChildCount(); i++) {
			ParseTree childParseTree = functionParametersContext.getChild(i);

			if (childParseTree instanceof TerminalNode) {
				continue;
			}

			parameters.add(childParseTree.accept(this));
		}

		return parameters;
	}

	@Override
	public Object visitGreaterThanExpression(
		FilterExpressionParser.GreaterThanExpressionContext
			greaterThanExpressionContext) {

		Field rightField = _getRightField(greaterThanExpressionContext);

		Field leftField = _getLeftField(
			greaterThanExpressionContext, rightField);

		return leftField.gt(rightField);
	}

	@Override
	public Object visitGreaterThanOrEqualsExpression(
		FilterExpressionParser.GreaterThanOrEqualsExpressionContext
			greaterThanOrEqualsExpressionContext) {

		Field rightField = _getRightField(greaterThanOrEqualsExpressionContext);

		Field leftField = _getLeftField(
			greaterThanOrEqualsExpressionContext, rightField);

		return leftField.ge(rightField);
	}

	@Override
	public Object visitIdentifier(
		FilterExpressionParser.IdentifierContext identifierContext) {

		String fieldName = identifierContext.getText();

		if (StringUtils.contains(fieldName, "/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			if (Objects.equals(identifierParts[0], "custom")) {
				_referencedTableNames.add("ExpandoValue");

				String qualifiedFieldName = _fieldMappers.getOrDefault(
					"custom." + identifierParts[1],
					"ExpandoValue." + identifierParts[1]);

				return DSL.field(qualifiedFieldName);
			}

			fieldName = identifierParts[1];
		}

		String qualifiedFieldName = _fieldMappers.getOrDefault(
			_filterType + "." + fieldName, _getTableNamespace() + fieldName);

		return DSL.field(qualifiedFieldName);
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

		Field rightField = _getRightField(lessThanExpressionContext);

		Field leftField = _getLeftField(lessThanExpressionContext, rightField);

		return leftField.lt(rightField);
	}

	@Override
	public Object visitLessThanOrEqualsExpression(
		FilterExpressionParser.LessThanOrEqualsExpressionContext
			lessThanOrEqualsExpressionContext) {

		Field rightField = _getRightField(lessThanOrEqualsExpressionContext);

		Field leftField = _getLeftField(
			lessThanOrEqualsExpressionContext, rightField);

		return leftField.le(rightField);
	}

	@Override
	public Object visitNotEqualsExpression(
		FilterExpressionParser.NotEqualsExpressionContext
			notEqualsExpressionContext) {

		Field rightField = _getRightField(notEqualsExpressionContext);

		Field leftField = _getLeftField(notEqualsExpressionContext, rightField);

		if (rightField == null) {
			return leftField.isNotNull();
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

	private Field _getLeftField(
		ParserRuleContext parserRuleContext, Field rightField) {

		Field leftField = _visitChild(parserRuleContext, 0);

		if (rightField == null) {
			return leftField;
		}

		Class<?> rightFieldType = rightField.getType();

		if (rightFieldType.isAssignableFrom(Long.class)) {
			return DSL.cast(leftField, Long.class);
		}

		return leftField;
	}

	private Field _getRightField(ParserRuleContext parserRuleContext) {
		Field rightField = _visitChild(parserRuleContext, 2);

		if (rightField == null) {
			return null;
		}

		Class<?> rightFieldType = rightField.getType();

		if ((rightField instanceof Param) &&
			rightFieldType.isAssignableFrom(String.class)) {

			Param<String> rightParam = (Param<String>)rightField;

			String value = rightParam.getValue();

			if (_timeFrameParameterNames.contains(value)) {
				return _getTimeFrameParam(value);
			}
		}

		return rightField;
	}

	private String _getTableNamespace() {
		String tableReference = _tableReferences.get(_filterType);

		if (tableReference == null) {
			return "";
		}

		return tableReference + ".";
	}

	private Param<LocalDateTime> _getTimeFrameParam(String value) {
		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(TimeZoneDogUtil.getZoneId()), LocalTime.MIDNIGHT);

		if (value.equalsIgnoreCase("last24Hours")) {
			localDateTime = localDateTime.minusHours(24);
		}
		else if (value.equalsIgnoreCase("last28Days")) {
			localDateTime = localDateTime.minusDays(28);
		}
		else if (value.equalsIgnoreCase("last30Days")) {
			localDateTime = localDateTime.minusDays(30);
		}
		else if (value.equalsIgnoreCase("last7Days")) {
			localDateTime = localDateTime.minusDays(7);
		}
		else if (value.equalsIgnoreCase("last90Days")) {
			localDateTime = localDateTime.minusDays(90);
		}
		else {
			localDateTime = localDateTime.minusDays(1);
		}

		return DSL.val(localDateTime);
	}

	private String _parseFilterStringExpression(Token filterToken) {
		String filterString = filterToken.getText();

		filterString = filterString.replaceAll("\\s''", " '");
		filterString = filterString.replaceAll("''\\s", "' ");
		filterString = filterString.replaceAll("''\\)", "')");

		return filterString;
	}

	private <T> T _visitChild(
		ParserRuleContext parserRuleContext, int childIndex) {

		ParseTree parseTree = parserRuleContext.getChild(childIndex);

		return (T)parseTree.accept(this);
	}

	private static final Set<String> _timeFrameParameterNames = SetUtil.of(
		"last24Hours", "last28Days", "last30Days", "last7Days", "last90Days",
		"yesterday");

	private final Map<String, String> _fieldMappers = new HashMap<>();
	private final String _filterType;
	private final Set<String> _referencedTableNames = new HashSet<>();
	private final Map<String, String> _tableReferences =
		new HashMap<String, String>() {
			{
				put("activities", "Event");
				put("individuals", "Individual");
				put("organizations", "Organization");
				put("sessions", "Session");
			}
		};

}