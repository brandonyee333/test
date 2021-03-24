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

package com.liferay.osb.asah.common.postgresql.converter;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.parser.FilterStringParser;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.json.JSONArray;

/**
 * @author Rachael Koestartyo
 */
public class FilterStringToConditionConverter {

	public static Condition convert(String filterString) throws Exception {
		return convert(filterString, new DefaultFilterStringConverterHelper());
	}

	public static Condition convert(
			String filterString,
			FilterStringConverterHelper filterStringConverterHelper)
		throws Exception {

		if (StringUtils.isEmpty(filterString)) {
			return DSL.noCondition();
		}

		LinkedList<Boolean> andOperators = new LinkedList<>();
		LinkedList<Condition> conditions = new LinkedList<>();

		return FilterStringParser.parse(
			filterString, () -> _getParseCondition(andOperators, conditions),
			innerFilterString -> {
				try {
					conditions.add(
						convert(
							innerFilterString, filterStringConverterHelper));
				}
				catch (Exception e) {
					return e;
				}

				return null;
			},
			logicalOperator -> _processLogicalOperator(
				andOperators, logicalOperator),
			terms -> _processLogicFunction(
				conditions, filterStringConverterHelper, terms),
			functionData -> _processStringFunction(
				conditions, filterStringConverterHelper, functionData));
	}

	private static Condition _getParseCondition(
		LinkedList<Boolean> andOperators, LinkedList<Condition> conditions) {

		Condition condition = conditions.poll();

		while (!conditions.isEmpty()) {
			Boolean andOperator = andOperators.poll();

			if ((andOperator == null) || andOperator) {
				condition = DSL.and(
					condition
				).and(
					conditions.poll()
				);
			}
			else {
				condition = DSL.or(
					condition
				).or(
					conditions.poll()
				);
			}
		}

		return condition;
	}

	private static Exception _processLogicalOperator(
		List<Boolean> andOperators, String logicalOperator) {

		if (logicalOperator.equalsIgnoreCase("and")) {
			andOperators.add(true);
		}
		else if (logicalOperator.equalsIgnoreCase("or")) {
			andOperators.add(false);
		}
		else {
			return new IllegalArgumentException(
				"Expected logical operator 'and' or 'or', but got '" +
					logicalOperator + "' instead");
		}

		return null;
	}

	private static Exception _processLogicFunction(
		List<Condition> conditions,
		FilterStringConverterHelper filterStringConverterHelper,
		String[] terms) {

		String fieldName = terms[0];
		String operator = terms[1];
		String valueString = terms[2];

		try {
			Condition condition =
				filterStringConverterHelper.getLogicFunctionCondition(
					fieldName, operator, valueString);

			if (condition != null) {
				conditions.add(condition);

				return null;
			}
		}
		catch (Exception e) {
			return e;
		}

		fieldName = _toFieldName(fieldName);

		Condition inferredCondition =
			filterStringConverterHelper.getInferredCondition(fieldName);

		Field<Object> field = DSL.field(fieldName);

		if (inferredCondition != null) {
			field = DSL.field("value");
		}

		Condition condition = null;

		Object value = StringUtil.toObject(valueString);

		if (operator.equalsIgnoreCase("eq")) {
			if (value == null) {
				condition = field.isNull();
			}
			else if (value instanceof JSONArray) {
				condition = field.in(JSONUtil.toObjectList((JSONArray)value));
			}
			else {
				condition = field.eq(value);
			}
		}
		else if (operator.equalsIgnoreCase("gt")) {
			condition = field.gt(value);
		}
		else if (operator.equalsIgnoreCase("ge")) {
			condition = field.ge(value);
		}
		else if (operator.equalsIgnoreCase("lt")) {
			condition = field.lt(value);
		}
		else if (operator.equalsIgnoreCase("le")) {
			condition = field.le(value);
		}
		else if (operator.equalsIgnoreCase("ne")) {
			if (value != null) {
				condition = field.ne(value);
			}
			else {
				condition = field.isNotNull();
			}
		}
		else {
			return new IllegalArgumentException(
				"Invalid operator: " + operator);
		}

		if (inferredCondition != null) {
			condition = condition.and(inferredCondition);
		}

		conditions.add(condition);

		return null;
	}

	private static Exception _processStringFunction(
		List<Condition> conditions,
		FilterStringConverterHelper filterStringConverterHelper,
		Object[] functionData) {

		List<String> arguments = (List<String>)functionData[0];
		boolean negated = (boolean)functionData[1];
		String stringFunction = (String)functionData[2];

		try {
			Condition condition =
				filterStringConverterHelper.getCustomFunctionCondition(
					arguments, stringFunction, negated);

			if (condition != null) {
				conditions.add(condition);

				return null;
			}
		}
		catch (Exception e) {
			return e;
		}

		if (stringFunction.equalsIgnoreCase("between")) {
			if (arguments.size() != 3) {
				return new IllegalArgumentException(
					"Expected 3 arguments for " + stringFunction +
						" function, got " + arguments.size() + " instead: " +
							arguments);
			}
		}
		else if (arguments.size() != 2) {
			return new IllegalArgumentException(
				"Expected 2 arguments for " + stringFunction + " function, " +
					"got " + arguments.size() + " instead: " + arguments);
		}

		String fieldName = _toFieldName(arguments.get(0));

		String fieldValue = arguments.get(1);

		String value = StringUtil.unquoteAndDecodeInnerQuotes(fieldValue);

		Condition inferredCondition =
			filterStringConverterHelper.getInferredCondition(fieldName);

		Field<Object> field = DSL.field(fieldName);

		if (inferredCondition != null) {
			field = DSL.field("value");
		}

		Condition condition = null;

		if (stringFunction.equalsIgnoreCase("between")) {
			condition = DSL.and(
				field.ge(value)
			).and(
				field.le(StringUtil.unquote(arguments.get(2)))
			);
		}
		else if (stringFunction.equalsIgnoreCase("contains")) {
			condition = field.containsIgnoreCase(value);
		}
		else if (stringFunction.equalsIgnoreCase("endsWith")) {
			condition = field.similarTo("%" + value);
		}
		else if (stringFunction.equalsIgnoreCase("startsWith")) {
			condition = field.similarTo(value + "%");
		}
		else {
			return new IllegalArgumentException(
				"Invalid string function: " + stringFunction);
		}

		if (inferredCondition != null) {
			condition = condition.and(inferredCondition);
		}

		if (negated) {
			conditions.add(DSL.not(condition));
		}
		else {
			conditions.add(condition);
		}

		return null;
	}

	private static String _toFieldName(String fieldName) {
		return fieldName.replace('/', '.');
	}

}