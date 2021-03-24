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

package com.liferay.osb.asah.common.converter.helper;

import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.sql.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
public class DefaultFilterStringConverterHelper
	implements FilterStringConverterHelper {

	@Override
	public Condition getCustomFunctionCondition(
			List<String> arguments, String customFunctionName, boolean negated)
		throws Exception {

		return null;
	}

	@Override
	public QueryBuilder getCustomFunctionQueryBuilder(
			List<String> arguments, String customFunctionName, boolean negated)
		throws Exception {

		return null;
	}

	@Override
	public Condition getInferredCondition(String fieldName) {
		return null;
	}

	@Override
	public Condition getLogicFunctionCondition(
			String fieldName, String operator, String valueString)
		throws Exception {

		return null;
	}

	@Override
	public QueryBuilder getLogicFunctionQueryBuilder(
			String fieldName, String operator, String valueString)
		throws Exception {

		return null;
	}

	public Condition getTimeFrameCondition(
		String fieldName, String operator, String type, String valueString) {

		if ((!fieldName.equalsIgnoreCase("completeDate") &&
			 type.equalsIgnoreCase("sessions")) ||
			(!fieldName.equalsIgnoreCase("day") &&
			 type.equalsIgnoreCase("activities"))) {

			return null;
		}

		String value = (String)StringUtil.toObject(valueString);

		if ((value == null) || value.equalsIgnoreCase("ever")) {
			return null;
		}

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
		else if (value.equalsIgnoreCase("yesterday")) {
			localDateTime = localDateTime.minusDays(1);
		}
		else {
			return null;
		}

		Field<Timestamp> field = DSL.timestamp(fieldName);

		Condition condition = field.gt(Timestamp.valueOf(localDateTime));

		if (!operator.equals("eq") && !operator.equals("ge") &&
			!operator.equals("gt")) {

			return DSL.not(condition);
		}

		return condition;
	}

	public QueryBuilder getTimeFrameQueryBuilder(
		String fieldName, String operator, String type, String valueString) {

		if ((!fieldName.equalsIgnoreCase("completeDate") &&
			 type.equalsIgnoreCase("sessions")) ||
			(!fieldName.equalsIgnoreCase("day") &&
			 type.equalsIgnoreCase("activities"))) {

			return null;
		}

		String value = (String)StringUtil.toObject(valueString);

		if ((value == null) || value.equalsIgnoreCase("ever")) {
			return null;
		}

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
		else if (value.equalsIgnoreCase("yesterday")) {
			localDateTime = localDateTime.minusDays(1);
		}
		else {
			return null;
		}

		QueryBuilder queryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.rangeQuery(
				fieldName
			).gt(
				localDateTime.toString()
			).timeZone(
				TimeZoneDogUtil.getTimeZoneId()
			));

		if (!operator.equals("eq") && !operator.equals("ge") &&
			!operator.equals("gt")) {

			return BoolQueryBuilderUtil.mustNot(queryBuilder);
		}

		return queryBuilder;
	}

}