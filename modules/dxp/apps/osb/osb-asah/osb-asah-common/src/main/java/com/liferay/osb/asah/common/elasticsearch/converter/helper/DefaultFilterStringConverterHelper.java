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

package com.liferay.osb.asah.common.elasticsearch.converter.helper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
public class DefaultFilterStringConverterHelper
	implements FilterStringConverterHelper {

	@Override
	public QueryBuilder getCustomFunctionQueryBuilder(
			List<String> arguments, String customFunctionName, boolean negated)
		throws Exception {

		return null;
	}

	@Override
	public QueryBuilder getLogicFunctionQueryBuilder(
			String fieldName, String operator, String valueString)
		throws Exception {

		return null;
	}

	public QueryBuilder getTimeFrameQueryBuilder(
			String fieldName, String operator, String type, String valueString)
		throws Exception {

		if ((!fieldName.equalsIgnoreCase("completeDate") &&
			 type.equalsIgnoreCase("sessions")) ||
			(!fieldName.equalsIgnoreCase("day") &&
			 type.equalsIgnoreCase("activities"))) {

			return null;
		}

		QueryBuilder queryBuilder = null;

		String value = (String)StringUtil.toObject(valueString);

		if ((value == null) || value.equalsIgnoreCase("ever")) {
			return null;
		}
		else if (value.equalsIgnoreCase("last24Hours")) {
			queryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					fieldName
				).gt(
					DateUtil.addHours(DateUtil.newDayDateString(), -24)
				));
		}
		else if (value.equalsIgnoreCase("last28Days")) {
			queryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					fieldName
				).gt(
					DateUtil.addDays(DateUtil.newDayDateString(), -28)
				));
		}
		else if (value.equalsIgnoreCase("last30Days")) {
			queryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					fieldName
				).gt(
					DateUtil.addDays(DateUtil.newDayDateString(), -30)
				));
		}
		else if (value.equalsIgnoreCase("last7Days")) {
			queryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					fieldName
				).gt(
					DateUtil.addDays(DateUtil.newDayDateString(), -7)
				));
		}
		else if (value.equalsIgnoreCase("last90Days")) {
			queryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					fieldName
				).gt(
					DateUtil.addDays(DateUtil.newDayDateString(), -90)
				));
		}
		else if (value.equalsIgnoreCase("yesterday")) {
			queryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					fieldName
				).gt(
					DateUtil.addDays(DateUtil.newDayDateString(), -1)
				));
		}
		else {
			return null;
		}

		if (!operator.equals("eq") && !operator.equals("ge") &&
			!operator.equals("gt")) {

			return BoolQueryBuilderUtil.mustNot(queryBuilder);
		}

		return queryBuilder;
	}

}