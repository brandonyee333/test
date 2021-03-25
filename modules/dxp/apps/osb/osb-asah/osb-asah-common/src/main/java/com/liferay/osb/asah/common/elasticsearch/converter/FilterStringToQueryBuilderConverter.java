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

package com.liferay.osb.asah.common.elasticsearch.converter;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.parser.FilterStringParser;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;

import org.json.JSONArray;

/**
 * @author Michael Bowerman
 */
public class FilterStringToQueryBuilderConverter {

	public static String buildIgnoreCaseRegExp(String value) {
		IntStream intStream = value.codePoints();

		return intStream.mapToObj(
			c -> (char)c
		).map(
			c -> {
				if (_CHARACTERS_TO_BE_ESCAPED_IN_REGEX.indexOf(c) >= 0) {
					return "\\" + c;
				}

				if (Character.isLowerCase(c)) {
					return "(" + c + "|" + Character.toUpperCase(c) + ")";
				}
				else if (Character.isUpperCase(c)) {
					return "(" + Character.toLowerCase(c) + "|" + c + ")";
				}
				else {
					return String.valueOf(c);
				}
			}
		).collect(
			Collectors.joining("", ".*?", ".*?")
		);
	}

	public static QueryBuilder convert(String filterString) throws Exception {
		return convert(filterString, new DefaultFilterStringConverterHelper());
	}

	public static QueryBuilder convert(
			String filterString,
			FilterStringConverterHelper filterStringConverterHelper)
		throws Exception {

		if (StringUtils.isEmpty(filterString)) {
			return null;
		}

		LinkedList<Boolean> andOperators = new LinkedList<>();
		LinkedList<QueryBuilder> queryBuilders = new LinkedList<>();

		return FilterStringParser.parse(
			filterString,
			() -> _getParseQueryBuilder(andOperators, queryBuilders),
			innerFilterString -> {
				try {
					queryBuilders.add(
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
				filterStringConverterHelper, queryBuilders, terms),
			functionData -> _processStringFunction(
				filterStringConverterHelper, functionData, queryBuilders));
	}

	private static QueryBuilder _getParseQueryBuilder(
		LinkedList<Boolean> andOperators,
		LinkedList<QueryBuilder> queryBuilders) {

		QueryBuilder queryBuilder = queryBuilders.poll();

		while (!queryBuilders.isEmpty()) {
			if (andOperators.poll()) {
				queryBuilder = BoolQueryBuilderUtil.filter(
					queryBuilder
				).filter(
					queryBuilders.poll()
				);
			}
			else {
				queryBuilder = BoolQueryBuilderUtil.should(
					queryBuilder
				).should(
					queryBuilders.poll()
				);
			}
		}

		return queryBuilder;
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
				"Expected logical operator \"and\" or \"or\", but got \"" +
					logicalOperator + "\" instead");
		}

		return null;
	}

	private static Exception _processLogicFunction(
		FilterStringConverterHelper filterStringConverterHelper,
		List<QueryBuilder> queryBuilders, String[] terms) {

		String fieldName = terms[0];
		String operator = terms[1];
		String valueString = terms[2];

		try {
			QueryBuilder queryBuilder =
				filterStringConverterHelper.getLogicFunctionQueryBuilder(
					fieldName, operator, valueString);

			if (queryBuilder != null) {
				queryBuilders.add(queryBuilder);

				return null;
			}
		}
		catch (Exception e) {
			return e;
		}

		fieldName = _toWeDeployFieldName(fieldName);

		Object value = StringUtil.toObject(valueString);

		if (operator.equalsIgnoreCase("eq")) {
			if (value == null) {
				queryBuilders.add(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.existsQuery(fieldName)));
			}
			else if (value instanceof JSONArray) {
				queryBuilders.add(
					QueryBuilders.termsQuery(
						fieldName, JSONUtil.toObjectList((JSONArray)value)));
			}
			else {
				queryBuilders.add(QueryBuilders.termQuery(fieldName, value));
			}
		}
		else if (operator.equalsIgnoreCase("gt")) {
			RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
				fieldName);

			rangeQueryBuilder.gt(value);
			rangeQueryBuilder.timeZone(TimeZoneDogUtil.getTimeZoneId());

			queryBuilders.add(rangeQueryBuilder);
		}
		else if (operator.equalsIgnoreCase("ge")) {
			RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
				fieldName);

			rangeQueryBuilder.gte(value);
			rangeQueryBuilder.timeZone(TimeZoneDogUtil.getTimeZoneId());

			queryBuilders.add(rangeQueryBuilder);
		}
		else if (operator.equalsIgnoreCase("lt")) {
			RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
				fieldName);

			rangeQueryBuilder.lt(value);
			rangeQueryBuilder.timeZone(TimeZoneDogUtil.getTimeZoneId());

			queryBuilders.add(rangeQueryBuilder);
		}
		else if (operator.equalsIgnoreCase("le")) {
			RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
				fieldName);

			rangeQueryBuilder.lte(value);
			rangeQueryBuilder.timeZone(TimeZoneDogUtil.getTimeZoneId());

			queryBuilders.add(rangeQueryBuilder);
		}
		else if (operator.equalsIgnoreCase("ne")) {
			if (value != null) {
				queryBuilders.add(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.termQuery(fieldName, value)));
			}
			else {
				queryBuilders.add(QueryBuilders.existsQuery(fieldName));
			}
		}
		else {
			return new IllegalArgumentException(
				"Invalid operator: " + operator);
		}

		return null;
	}

	private static Exception _processStringFunction(
		FilterStringConverterHelper filterStringConverterHelper,
		Object[] functionData, List<QueryBuilder> queryBuilders) {

		List<String> arguments = (List<String>)functionData[0];
		boolean negated = (boolean)functionData[1];
		String stringFunction = (String)functionData[2];

		try {
			QueryBuilder queryBuilder =
				filterStringConverterHelper.getCustomFunctionQueryBuilder(
					arguments, stringFunction, negated);

			if (queryBuilder != null) {
				queryBuilders.add(queryBuilder);

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

		QueryBuilder queryBuilder = null;

		String fieldName = _toWeDeployFieldName(arguments.get(0));

		String fieldValue = arguments.get(1);

		String value = StringUtil.unquoteAndDecodeInnerQuotes(fieldValue);

		if (stringFunction.equalsIgnoreCase("between")) {
			queryBuilder = QueryBuilders.rangeQuery(
				fieldName
			).gte(
				value
			).lte(
				StringUtil.unquote(arguments.get(2))
			).timeZone(
				TimeZoneDogUtil.getTimeZoneId()
			);
		}
		else if (stringFunction.equalsIgnoreCase("contains")) {
			queryBuilder = QueryBuilders.regexpQuery(
				fieldName, buildIgnoreCaseRegExp(value));
		}
		else if (stringFunction.equalsIgnoreCase("endsWith")) {
			queryBuilder = QueryBuilders.wildcardQuery(fieldName, "*" + value);
		}
		else if (stringFunction.equalsIgnoreCase("startsWith")) {
			queryBuilder = QueryBuilders.wildcardQuery(fieldName, value + "*");
		}
		else {
			return new IllegalArgumentException(
				"Invalid string function: " + stringFunction);
		}

		if (negated) {
			queryBuilders.add(BoolQueryBuilderUtil.mustNot(queryBuilder));
		}
		else {
			queryBuilders.add(queryBuilder);
		}

		return null;
	}

	private static String _toWeDeployFieldName(String fieldName) {
		return fieldName.replace('/', '.');
	}

	private static final String _CHARACTERS_TO_BE_ESCAPED_IN_REGEX =
		"([{\\^-=$!|]})?*+.<>\"#";

}