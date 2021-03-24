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

package com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Arrays;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class FaroInfoSessionsFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public QueryBuilder getCustomFunctionQueryBuilder(
		List<String> arguments, String customFunctionName, boolean negated) {

		String fieldName = arguments.get(0);

		if (!fieldName.startsWith("context/")) {
			return null;
		}

		QueryBuilder queryBuilder = null;

		fieldName = fieldName.replace("context/", "");

		if (_contextFieldNames.contains(fieldName)) {
			fieldName = fieldName + "s";
		}

		String fieldValue = arguments.get(1);

		String value = StringUtil.unquoteAndDecodeInnerQuotes(fieldValue);

		if (customFunctionName.equalsIgnoreCase("between")) {
			queryBuilder = QueryBuilders.rangeQuery(
				fieldName
			).gte(
				value
			).lte(
				StringUtil.unquote(arguments.get(2))
			).timeZone(
				_timeZoneDog.getTimeZoneId()
			);
		}
		else if (customFunctionName.equalsIgnoreCase("contains")) {
			queryBuilder = QueryBuilders.regexpQuery(
				fieldName,
				FilterStringToQueryBuilderConverter.buildIgnoreCaseRegExp(
					value));
		}
		else if (customFunctionName.equalsIgnoreCase("endsWith")) {
			queryBuilder = QueryBuilders.wildcardQuery(fieldName, "*" + value);
		}
		else if (customFunctionName.equalsIgnoreCase("startsWith")) {
			queryBuilder = QueryBuilders.wildcardQuery(fieldName, value + "*");
		}

		if (negated) {
			queryBuilder = BoolQueryBuilderUtil.mustNot(queryBuilder);
		}

		return queryBuilder;
	}

	@Override
	public QueryBuilder getLogicFunctionQueryBuilder(
			String fieldName, String operator, String valueString)
		throws Exception {

		QueryBuilder timeFrameQueryBuilder = getTimeFrameQueryBuilder(
			fieldName, operator, "sessions", valueString);

		if (timeFrameQueryBuilder != null) {
			return timeFrameQueryBuilder;
		}

		if (!fieldName.startsWith("context/")) {
			return null;
		}

		return _getContextQueryBuilder(fieldName, operator, valueString);
	}

	private QueryBuilder _getContextQueryBuilder(
		String fieldName, String operator, String valueString) {

		fieldName = fieldName.replace("context/", "");

		if (_contextFieldNames.contains(fieldName)) {
			fieldName = fieldName + "s";
		}

		Object value = StringUtil.toObject(valueString);

		if (operator.equalsIgnoreCase("eq")) {
			if (value != null) {
				return QueryBuilders.termQuery(fieldName, value);
			}

			return BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery(fieldName));
		}
		else if (operator.equalsIgnoreCase("gt")) {
			return QueryBuilders.rangeQuery(
				fieldName
			).gt(
				value
			).timeZone(
				_timeZoneDog.getTimeZoneId()
			);
		}
		else if (operator.equalsIgnoreCase("ge")) {
			return QueryBuilders.rangeQuery(
				fieldName
			).gte(
				value
			).timeZone(
				_timeZoneDog.getTimeZoneId()
			);
		}
		else if (operator.equalsIgnoreCase("lt")) {
			return QueryBuilders.rangeQuery(
				fieldName
			).lt(
				value
			).timeZone(
				_timeZoneDog.getTimeZoneId()
			);
		}
		else if (operator.equalsIgnoreCase("le")) {
			return QueryBuilders.rangeQuery(
				fieldName
			).lte(
				value
			).timeZone(
				_timeZoneDog.getTimeZoneId()
			);
		}
		else if (operator.equalsIgnoreCase("ne")) {
			if (value != null) {
				return BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery(fieldName, value));
			}

			return BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery(fieldName));
		}

		return null;
	}

	private final List<String> _contextFieldNames = Arrays.asList(
		"canonicalUrl", "referrer", "url");

	@Autowired
	private TimeZoneDog _timeZoneDog;

}