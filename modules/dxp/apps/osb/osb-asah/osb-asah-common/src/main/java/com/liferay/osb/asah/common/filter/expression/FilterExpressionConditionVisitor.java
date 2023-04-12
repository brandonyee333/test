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
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;

/**
 * @author Marcellus Tavares
 * @author Ivica Cardic
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class FilterExpressionConditionVisitor
	extends FilterExpressionBaseVisitor<Object> {

	public FilterExpressionConditionVisitor(
		FilterExpression.FilterType filterType) {

		_filterType = filterType;

		if ((filterType != null) && _tableReferences.containsKey(filterType)) {
			_referencedTableNames.add(_tableReferences.get(filterType));
		}
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

		Token startToken = equalsExpressionContext.start;
		Token stopToken = equalsExpressionContext.stop;

		String fieldName = startToken.getText();
		String value = StringUtil.unquoteAndDecodeInnerQuotes(
			stopToken.getText());

		if (Objects.equals(
				_filterType, FilterExpression.FilterType.INDIVIDUALS) &&
			Objects.equals(fieldName, "userId")) {

			_referencedTableNames.add("User");
		}

		if (Objects.equals(
				_filterType, FilterExpression.FilterType.ORGANIZATIONS)) {

			return _visitOrganizationExpression(fieldName, "eq", value);
		}

		if (Objects.equals(_filterType, FilterExpression.FilterType.SESSIONS)) {
			if (Objects.equals(fieldName, "context/referrer")) {
				return DSL.condition(
					String.format("'%s' IN UNNEST(Session.referrers)", value));
			}

			if (Objects.equals(fieldName, "context/url")) {
				return DSL.condition(
					String.format("'%s' IN UNNEST(Session.urls)", value));
			}
		}

		if (fieldName.startsWith("custom/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getCustomFieldCondition(identifierParts[1], "eq", value);
		}
		else if (fieldName.startsWith("demographics/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getDemographicsFieldCondition(
				equalsExpressionContext, identifierParts[1], "eq", value);
		}

		Field leftField = _getLeftField(equalsExpressionContext);
		Field rightField = _getRightField(equalsExpressionContext);

		if (rightField == null) {
			return leftField.isNull();
		}

		if ((Objects.equals(
				leftField.getName(), "IdentityActivity.channelId") ||
			 Objects.equals(leftField.getName(), "Membership.segmentId")) &&
			(rightField instanceof Param)) {

			Param<String> param = (Param<String>)rightField;

			rightField = DSL.val(Long.parseLong(param.getValue()));
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
			FilterExpression.FilterType.of(
				filterByCountExpressionContext.filterType.getText()));

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

		Field identityIdField = DSL.field("Identity.id");

		return identityIdField.in(
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
			).groupBy(
				userIdField
			).having(
				havingCondition
			));
	}

	public Object visitFilterExpression(
		FilterExpressionParser.FilterExpressionContext
			filterExpressionContext) {

		String filterString = _parseFilterStringExpression(
			filterExpressionContext.filter);

		FilterExpression filterExpression = new FilterExpression(
			filterString.substring(1, filterString.length() - 1),
			FilterExpression.FilterType.of(
				filterExpressionContext.filterType.getText()));

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

		if (functionName.equalsIgnoreCase("cast")) {
			Param param = (Param)parameters.get(1);

			String type = String.valueOf(param.getValue());

			String attributeType = _attributeTypes.get(
				StringUtils.lowerCase(type));

			if (attributeType == null) {
				return new FilterExpressionParserException(
					"Invalid type " + type);
			}

			return DSL.field(
				String.format("SAFE_CAST({0} AS %s)", attributeType), field);
		}

		String fieldName = field.getName();

		String qualifiedFieldName = fieldName;

		if (StringUtils.startsWith(fieldName, "ExpandoValue.")) {
			String[] parts = fieldName.split("\\.", 2);

			qualifiedFieldName = parts[1];

			if (Objects.equals(
					_filterType, FilterExpression.FilterType.INDIVIDUALS)) {

				String alias = "IndividualFields_" + qualifiedFieldName;

				_referencedTableNames.add(alias);

				field = DSL.field(alias + ".value");
			}
			else {
				field = DSL.field(
					"ExpandoValue_" + qualifiedFieldName + ".value");
			}
		}

		Condition condition = null;

		if (functionName.equalsIgnoreCase("between")) {
			Param param1 = (Param)parameters.get(1);
			Param param2 = (Param)parameters.get(2);

			condition = field.between(param1.getValue(), param2.getValue());
		}
		else if (functionName.equalsIgnoreCase("contains")) {
			Param param = (Param)parameters.get(1);

			String value = String.valueOf(param.getValue());

			if (Objects.equals(fieldName, "Session.referrers")) {
				_referencedTableNames.add("SessionReferrers");

				field = DSL.field("SessionReferrer");
			}
			else if (Objects.equals(fieldName, "Session.urls")) {
				_referencedTableNames.add("SessionUrls");

				field = DSL.field("SessionUrl");
			}

			condition = DSL.condition(
				String.format(
					"LOWER(%s) LIKE '%s'", field,
					"%" + StringUtils.lowerCase(value) + "%"));
		}
		else if (functionName.equalsIgnoreCase("endsWith")) {
			Param param = (Param)parameters.get(1);

			condition = field.similarTo("%" + param.getValue());
		}
		else if (functionName.equalsIgnoreCase("isInterested")) {
			condition = _getIsInterestedCondition(field.toString());
		}
		else if (functionName.equalsIgnoreCase("isMember")) {
			Param param = (Param)parameters.get(1);

			condition = _getIsMemberCondition(
				(String)param.getValue(), fieldName.replace("Individual.", ""));
		}
		else if (functionName.equalsIgnoreCase("sha256Hex")) {
			return DSL.field(String.format("TO_HEX(SHA256(%s))", fieldName));
		}
		else if (functionName.equalsIgnoreCase("similarTo")) {
			Param param = (Param)parameters.get(1);

			condition = field.similarTo(
				StringUtils.replaceChars(
					String.valueOf(param.getValue()), ".*", "_%"));
		}
		else if (functionName.equalsIgnoreCase("startsWith")) {
			Param param = (Param)parameters.get(1);

			condition = field.similarTo(param.getValue() + "%");
		}
		else {
			throw new FilterExpressionParserException(
				"Invalid string function: " + functionName);
		}

		if (StringUtils.startsWith(field.getName(), "ExpandoValue_")) {
			condition = condition.and(
				DSL.field(
					"ExpandoValue_" + qualifiedFieldName + ".fieldName"
				).eq(
					qualifiedFieldName
				));
		}
		else if (StringUtils.startsWith(field.getName(), "IndividualFields_")) {
			String curFieldName = field.getName();

			String[] parts = curFieldName.split("\\.", 2);

			_referencedTableNames.add("ExpandoValue");

			condition = condition.and(
				DSL.field(
					parts[0] + ".name"
				).eq(
					qualifiedFieldName
				));
		}

		if (Objects.equals(
				_filterType, FilterExpression.FilterType.ORGANIZATIONS)) {

			if (StringUtils.startsWith(fieldName, "ExpandoValue.")) {
				return _getIndividualIdsInOrganizationCondition(
					condition, "ExpandoValue_" + qualifiedFieldName);
			}

			return _getIndividualIdsInOrganizationCondition(condition, null);
		}

		return condition;
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

		Token startToken = greaterThanExpressionContext.start;
		Token stopToken = greaterThanExpressionContext.stop;

		String fieldName = startToken.getText();
		String value = StringUtil.unquoteAndDecodeInnerQuotes(
			stopToken.getText());

		if (Objects.equals(
				_filterType, FilterExpression.FilterType.ORGANIZATIONS)) {

			return _visitOrganizationExpression(fieldName, "gt", value);
		}

		if (fieldName.startsWith("custom/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getCustomFieldCondition(identifierParts[1], "gt", value);
		}

		if (fieldName.startsWith("demographics/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getDemographicsFieldCondition(
				greaterThanExpressionContext, identifierParts[1], "gt", value);
		}

		Field leftField = _getLeftField(greaterThanExpressionContext);

		return leftField.gt(_getRightField(greaterThanExpressionContext));
	}

	@Override
	public Object visitGreaterThanOrEqualsExpression(
		FilterExpressionParser.GreaterThanOrEqualsExpressionContext
			greaterThanOrEqualsExpressionContext) {

		Token startToken = greaterThanOrEqualsExpressionContext.start;
		Token stopToken = greaterThanOrEqualsExpressionContext.stop;

		String fieldName = startToken.getText();
		String value = StringUtil.unquoteAndDecodeInnerQuotes(
			stopToken.getText());

		if (Objects.equals(
				_filterType, FilterExpression.FilterType.ORGANIZATIONS)) {

			return _visitOrganizationExpression(fieldName, "ge", value);
		}

		if (fieldName.startsWith("custom/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getCustomFieldCondition(identifierParts[1], "ge", value);
		}

		if (fieldName.startsWith("demographics/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getDemographicsFieldCondition(
				greaterThanOrEqualsExpressionContext, identifierParts[1], "ge",
				value);
		}

		Field leftField = _getLeftField(greaterThanOrEqualsExpressionContext);

		return leftField.ge(
			_getRightField(greaterThanOrEqualsExpressionContext));
	}

	@Override
	public Object visitIdentifier(
		FilterExpressionParser.IdentifierContext identifierContext) {

		String fieldName = identifierContext.getText();

		if (_fieldMappers.containsKey(fieldName)) {
			fieldName = _fieldMappers.get(fieldName);

			if (fieldName.contains(".")) {
				_checkReferencedTables(fieldName);

				return DSL.field(fieldName);
			}
		}

		if (StringUtils.contains(fieldName, "/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			if (Objects.equals(identifierParts[0], "custom")) {
				return DSL.field("ExpandoValue." + identifierParts[1]);
			}

			fieldName = identifierParts[1];
		}

		if (_filterType == null) {
			return DSL.field(_getTableNamespace() + fieldName);
		}

		String qualifiedFieldName = _fieldMappers.getOrDefault(
			_filterType.getName() + "." + fieldName,
			_getTableNamespace() + fieldName);

		_checkReferencedTables(qualifiedFieldName);

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

		Token startToken = lessThanExpressionContext.start;
		Token stopToken = lessThanExpressionContext.stop;

		String fieldName = startToken.getText();
		String value = StringUtil.unquoteAndDecodeInnerQuotes(
			stopToken.getText());

		if (Objects.equals(
				_filterType, FilterExpression.FilterType.ORGANIZATIONS)) {

			return _visitOrganizationExpression(fieldName, "lt", value);
		}

		if (fieldName.startsWith("custom/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getCustomFieldCondition(identifierParts[1], "lt", value);
		}

		if (fieldName.startsWith("demographics/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getDemographicsFieldCondition(
				lessThanExpressionContext, identifierParts[1], "lt", value);
		}

		Field leftField = _getLeftField(lessThanExpressionContext);

		return leftField.lt(_getRightField(lessThanExpressionContext));
	}

	@Override
	public Object visitLessThanOrEqualsExpression(
		FilterExpressionParser.LessThanOrEqualsExpressionContext
			lessThanOrEqualsExpressionContext) {

		Token startToken = lessThanOrEqualsExpressionContext.start;
		Token stopToken = lessThanOrEqualsExpressionContext.stop;

		String fieldName = startToken.getText();
		String value = StringUtil.unquoteAndDecodeInnerQuotes(
			stopToken.getText());

		if (Objects.equals(
				_filterType, FilterExpression.FilterType.ORGANIZATIONS)) {

			return _visitOrganizationExpression(fieldName, "le", value);
		}

		if (fieldName.startsWith("custom/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getCustomFieldCondition(identifierParts[1], "le", value);
		}

		if (fieldName.startsWith("demographics/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getDemographicsFieldCondition(
				lessThanOrEqualsExpressionContext, identifierParts[1], "le",
				value);
		}

		Field leftField = _getLeftField(lessThanOrEqualsExpressionContext);

		return leftField.le(_getRightField(lessThanOrEqualsExpressionContext));
	}

	@Override
	public Object visitNotEqualsExpression(
		FilterExpressionParser.NotEqualsExpressionContext
			notEqualsExpressionContext) {

		Token startToken = notEqualsExpressionContext.start;
		Token stopToken = notEqualsExpressionContext.stop;

		String fieldName = startToken.getText();
		String value = StringUtil.unquoteAndDecodeInnerQuotes(
			stopToken.getText());

		if (Objects.equals(
				_filterType, FilterExpression.FilterType.INDIVIDUALS) &&
			Objects.equals(fieldName, "userId")) {

			_referencedTableNames.add("User");
		}

		if (Objects.equals(
				_filterType, FilterExpression.FilterType.ORGANIZATIONS)) {

			return _visitOrganizationExpression(fieldName, "ne", value);
		}

		if (fieldName.startsWith("custom/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getCustomFieldCondition(identifierParts[1], "ne", value);
		}
		else if (fieldName.startsWith("demographics/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getDemographicsFieldCondition(
				notEqualsExpressionContext, identifierParts[1], "ne", value);
		}

		Field leftField = _getLeftField(notEqualsExpressionContext);
		Field rightField = _getRightField(notEqualsExpressionContext);

		if (rightField == null) {
			return leftField.isNotNull();
		}

		if (Objects.equals(leftField.getName(), "IdentityActivity.channelId") &&
			(rightField instanceof Param)) {

			Param<String> param = (Param<String>)rightField;

			rightField = DSL.val(Long.parseLong(param.getValue()));
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

	private void _checkReferencedTables(String fieldName) {
		for (String tableName : _tableReferences.values()) {
			if (fieldName.startsWith(tableName)) {
				_referencedTableNames.add(tableName);

				break;
			}
		}
	}

	private Condition _getCustomFieldCondition(
		String fieldName, String operator, String value) {

		_referencedTableNames.add("ExpandoValue");

		String alias = null;
		Condition condition = null;

		if (_filterType == FilterExpression.FilterType.INDIVIDUALS) {
			alias = "IndividualFields_" + fieldName;

			_referencedTableNames.add(alias);

			condition = DSL.field(
				alias + ".name"
			).eq(
				fieldName
			);
		}
		else {
			alias = "ExpandoValue_" + fieldName;

			condition = DSL.field(
				alias + ".fieldName"
			).eq(
				fieldName
			);
		}

		if (operator.equalsIgnoreCase("eq")) {
			if (StringUtil.isNull(value)) {
				condition = condition.and(
					DSL.field(
						alias + ".value"
					).isNull());
			}
			else {
				condition = condition.and(
					DSL.condition(
						String.join(
							"", "CASE WHEN STARTS_WITH(", alias,
							".value, '[') THEN LOWER(", alias,
							".value) LIKE '%", StringUtils.lowerCase(value),
							"%' ELSE LOWER(", alias, ".value) = '",
							StringUtils.lowerCase(value), "' END")));
			}
		}
		else if (operator.equalsIgnoreCase("ge")) {
			condition = condition.and(
				DSL.condition(
					String.join(
						"", "CASE WHEN SAFE_CAST(", alias,
						".value AS NUMERIC) IS NULL THEN false ELSE SAFE_CAST(",
						alias, ".value AS NUMERIC) >= SAFE_CAST(", value,
						" AS NUMERIC) END")));
		}
		else if (operator.equalsIgnoreCase("gt")) {
			condition = condition.and(
				DSL.condition(
					String.join(
						"", "CASE WHEN SAFE_CAST(", alias,
						".value AS NUMERIC) IS NULL THEN false ELSE SAFE_CAST(",
						alias, ".value AS NUMERIC) > SAFE_CAST(", value,
						" AS NUMERIC) END")));
		}
		else if (operator.equalsIgnoreCase("le")) {
			condition = condition.and(
				DSL.condition(
					String.join(
						"", "CASE WHEN SAFE_CAST(", alias,
						".value AS NUMERIC) IS NULL THEN false ELSE SAFE_CAST(",
						alias, ".value AS NUMERIC) <= SAFE_CAST(", value,
						" AS NUMERIC) END")));
		}
		else if (operator.equalsIgnoreCase("lt")) {
			condition = condition.and(
				DSL.condition(
					String.join(
						"", "CASE WHEN SAFE_CAST(", alias,
						".value AS NUMERIC) IS NULL THEN false ELSE SAFE_CAST(",
						alias, ".value AS NUMERIC) < SAFE_CAST(", value,
						" AS NUMERIC) END")));
		}
		else if (operator.equalsIgnoreCase("ne")) {
			if (StringUtil.isNull(value)) {
				condition = condition.and(
					DSL.field(
						alias + ".value"
					).isNotNull());
			}
			else {
				condition = condition.and(
					DSL.condition(
						String.join(
							"", "CASE WHEN STARTS_WITH(", alias,
							".value, '[') THEN LOWER(", alias,
							".value) NOT LIKE '%", StringUtils.lowerCase(value),
							"%' ELSE LOWER(", alias, ".value) != '",
							StringUtils.lowerCase(value), "' END")));
			}
		}

		return condition;
	}

	private Condition _getDemographicsFieldCondition(
		ParserRuleContext parserRuleContext, String fieldName, String operator,
		String value) {

		Field leftField = _getLeftField(parserRuleContext);

		if (StringUtil.isNull(value)) {
			if (operator.equalsIgnoreCase("eq")) {
				return leftField.isNull();
			}

			return leftField.isNotNull();
		}

		fieldName = fieldName.toLowerCase();

		if (StringUtils.endsWithIgnoreCase(fieldName, "date")) {
			leftField = DSL.date(leftField);
		}
		else {
			leftField = DSL.lower(leftField);

			value = value.toLowerCase();
		}

		if (operator.equalsIgnoreCase("eq")) {
			return leftField.eq(value);
		}
		else if (operator.equalsIgnoreCase("ge")) {
			return leftField.ge(value);
		}
		else if (operator.equalsIgnoreCase("gt")) {
			return leftField.gt(value);
		}
		else if (operator.equalsIgnoreCase("le")) {
			return leftField.le(value);
		}
		else if (operator.equalsIgnoreCase("lt")) {
			return leftField.lt(value);
		}

		return leftField.ne(value);
	}

	private Object _getIndividualIdsInOrganizationCondition(
		Condition condition, String expandoValueFieldName) {

		_referencedTableNames.add("Individual");

		SelectJoinStep selectJoinStep = DSL.selectDistinct(
			DSL.field("Individual.id", String.class)
		).from(
			DSL.table(
				"BQIndividual"
			).as(
				"Individual"
			)
		).crossJoin(
			DSL.table("UNNEST(Individual.memberships) AS IndividualMemberships")
		).join(
			DSL.table(
				"BQOrganization"
			).as(
				"Organization"
			)
		).on(
			DSL.condition(
				"Organization.id IN UNNEST(IndividualMemberships.ids)")
		);

		if (StringUtils.isNotBlank(expandoValueFieldName)) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQExpandoValue"
				).as(
					expandoValueFieldName
				)
			).on(
				DSL.and(
					DSL.field(
						expandoValueFieldName + ".classPK"
					).eq(
						DSL.field(
							"SAFE_CAST(Organization.organizationId AS STRING)")
					),
					DSL.field(
						expandoValueFieldName + ".classType"
					).eq(
						"com.liferay.portal.kernel.model.Organization"
					),
					DSL.field(
						expandoValueFieldName + ".dataSourceId"
					).eq(
						DSL.field("Organization.dataSourceId")
					))
			);
		}

		return DSL.field(
			"Individual.id", String.class
		).in(
			selectJoinStep.where(condition)
		);
	}

	private Condition _getIsInterestedCondition(String keyword) {
		SelectConditionStep<Record1<String>> selectConditionStep =
			DSL.selectDistinct(
				DSL.field("Identity.id", String.class)
			).from(
				DSL.table(
					"BQIdentity"
				).as(
					"Identity"
				)
			).join(
				DSL.table(
					"BQIdentityInterestScore"
				).as(
					"Interest"
				)
			).on(
				DSL.field(
					"Identity.id", String.class
				).eq(
					DSL.field("Interest.identityId", String.class)
				)
			).where(
				DSL.and(
					DSL.field(
						"Interest.keyword", String.class
					).eq(
						keyword
					),
					DSL.field(
						"Interest.interested", Boolean.class
					).eq(
						true
					))
			);

		Field<String> field = DSL.field("Identity.id", String.class);

		return field.in(selectConditionStep);
	}

	private Condition _getIsMemberCondition(String id, String type) {
		_referencedTableNames.add("Individual");

		SelectConditionStep<Record1<String>> selectConditionStep =
			DSL.selectDistinct(
				DSL.field("Individual.id", String.class)
			).from(
				DSL.table(
					"BQIndividual"
				).as(
					"Individual"
				)
			).crossJoin(
				DSL.table(
					"UNNEST(Individual.memberships) AS IndividualMemberships")
			).where(
				DSL.and(
					DSL.field(
						"IndividualMemberships.name"
					).eq(
						type
					),
					DSL.condition(
						String.format(
							"'%s' IN UNNEST(IndividualMemberships.ids)", id)))
			);

		Field<String> field = DSL.field("Individual.id", String.class);

		return field.in(selectConditionStep);
	}

	private Field _getLeftField(ParserRuleContext parserRuleContext) {
		return _visitChild(parserRuleContext, 0);
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

		filterString = filterString.replaceAll(",''", ", '");
		filterString = filterString.replaceAll("'',", "',");
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

	private Object _visitOrganizationExpression(
		String fieldName, String operator, String value) {

		if (fieldName.startsWith("custom/")) {
			String[] identifierParts = StringUtils.split(fieldName, "/");

			return _getIndividualIdsInOrganizationCondition(
				_getCustomFieldCondition(identifierParts[1], operator, value),
				"ExpandoValue_" + identifierParts[1]);
		}

		if (fieldName.equalsIgnoreCase("id")) {
			return DSL.field(
				"Individual.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Individual.id", String.class)
				).from(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					)
				).crossJoin(
					DSL.table(
						"UNNEST(Individual.memberships) AS " +
							"IndividualMemberships")
				).where(
					DSL.and(
						DSL.field(
							"IndividualMemberships.name"
						).eq(
							"organizationIds"
						),
						DSL.condition(
							String.format(
								"'%s' IN UNNEST(IndividualMemberships.ids)",
								value)))
				)
			);
		}

		if (fieldName.equalsIgnoreCase("parentId") ||
			fieldName.equalsIgnoreCase("parentOrganizationId")) {

			return DSL.field(
				"Individual.id", String.class
			).in(
				DSL.selectDistinct(
					DSL.field("Individual.id", String.class)
				).from(
					DSL.table(
						"BQIndividual"
					).as(
						"Individual"
					)
				).crossJoin(
					DSL.table(
						"UNNEST(Individual.memberships) AS " +
							"IndividualMemberships")
				).join(
					DSL.table(
						"BQOrganization"
					).as(
						"Organization"
					)
				).on(
					DSL.condition(
						"Organization.id IN UNNEST(IndividualMemberships.ids)")
				).join(
					DSL.table(
						"BQOrganization"
					).as(
						"ParentOrganization"
					)
				).on(
					DSL.and(
						DSL.field(
							"Organization.parentOrganizationId"
						).eq(
							DSL.field("ParentOrganization.organizationId")
						),
						DSL.field(
							"Organization.dataSourceId"
						).eq(
							DSL.field("ParentOrganization.dataSourceId")
						))
				).where(
					DSL.field(
						"ParentOrganization.id"
					).eq(
						value
					)
				)
			);
		}

		Condition condition = null;

		if (operator.equalsIgnoreCase("eq")) {
			if (StringUtil.isNull(value)) {
				condition = DSL.field(
					"Organization." + fieldName
				).isNull();
			}
			else {
				condition = DSL.field(
					"Organization." + fieldName
				).eq(
					value
				);
			}
		}
		else if (operator.equalsIgnoreCase("ne")) {
			if (StringUtil.isNull(value)) {
				condition = DSL.field(
					"Organization." + fieldName
				).isNotNull();
			}
			else {
				condition = DSL.field(
					"Organization." + fieldName
				).ne(
					value
				);
			}
		}

		return _getIndividualIdsInOrganizationCondition(condition, null);
	}

	private static final Map<String, String> _attributeTypes =
		new HashMap<String, String>() {
			{
				put("boolean", "BOOLEAN");
				put("date", "DATE");
				put("number", "NUMERIC");
				put("text", "STRING");
			}
		};
	private static final Set<String> _timeFrameParameterNames = SetUtil.of(
		"last24Hours", "last28Days", "last30Days", "last7Days", "last90Days",
		"yesterday");

	private final Map<String, String> _fieldMappers =
		new HashMap<String, String>() {
			{
				put("activities.day", "Event.eventDate");
				put("channelIds", "IdentityActivity.channelId");
				put("credentials/type", "credentialType");
				put("individuals.additionalName", "Individual.middleName");
				put("individuals.address", "Individual.addresses");
				put("individuals.birthDate", "Individual.birthday");
				put("individuals.email", "Individual.emailAddress");
				put("individuals.familyName", "Individual.lastName");
				put("individuals.givenName", "Individual.firstName");
				put(
					"individuals.lastEnrichmentDate",
					"Individual.modifiedDate");
				put("individualSegmentIds", "Membership.segmentId");
				put("interestName", "keyword");
				put("provider/type", "providerType");
				put("sessions.completeDate", "Session.sessionEnd");
				put("sessions.referrer", "Session.referrers");
				put("sessions.url", "Session.urls");
				put("userId", "User.id");
			}
		};
	private final FilterExpression.FilterType _filterType;
	private final Set<String> _referencedTableNames = new HashSet<>();
	private final Map<FilterExpression.FilterType, String> _tableReferences =
		new HashMap<FilterExpression.FilterType, String>() {
			{
				put(FilterExpression.FilterType.ACTIVITIES, "Event");
				put(FilterExpression.FilterType.INDIVIDUALS, "Individual");
				put(FilterExpression.FilterType.MEMBERSHIPS, "Membership");
				put(FilterExpression.FilterType.ORGANIZATIONS, "Organization");
				put(FilterExpression.FilterType.SESSIONS, "Session");
			}
		};

}