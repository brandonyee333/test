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

package com.liferay.osb.asah.common.postgresql.converter.helper;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.model.Segment;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilders;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class ActivitiesFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public Map<String, String> getFieldNameConversionMap() {
		return Collections.singletonMap("day", "dayDate");
	}

	@Override
	public Condition getLogicFunctionCondition(
			String fieldName, String operator, String valueString)
		throws Exception {

		if (fieldName.equals("accountId") &&
			_isIdFilter(operator, valueString)) {

			return _getAccountIdCondition(
				StringUtil.unquote(valueString),
				operator.equalsIgnoreCase("ne"));
		}

		if (fieldName.equals("channelId") || fieldName.equals("ownerId")) {
			Field<Long> field = DSL.field(
				fieldName
			).cast(
				Long.class
			);

			if (operator.equals("eq")) {
				return field.eq(Long.valueOf(StringUtil.unquote(valueString)));
			}
			else if (operator.equals("ne")) {
				return field.ne(Long.valueOf(StringUtil.unquote(valueString)));
			}
		}

		Condition condition = getTimeFrameCondition(
			fieldName, operator, "activities", valueString);

		if (condition != null) {
			return condition;
		}

		if (fieldName.equals("dayDate")) {
			return _getDayDateCondition(
				operator, StringUtil.unquote(valueString));
		}

		return null;
	}

	private Condition _getAccountIdCondition(String accountId, boolean negate)
		throws Exception {

		Segment segment = _segmentDog.fetchSegment(
			"Account: " + accountId, "INACTIVE");

		if (segment == null) {
			return null;
		}

		List<String> individualIds = new ArrayList<>();

		JSONArrayIterator.of(
			"individuals", _elasticsearchInvoker,
			individualJSONObject -> {
				individualIds.add(individualJSONObject.getString("id"));

				return null;
			}
		).setQueryBuilder(
			QueryBuilders.termQuery(
				"individualSegmentIds", String.valueOf(segment.getId()))
		).iterate();

		Condition condition = null;

		if (individualIds.isEmpty()) {
			condition = DSL.field(
				"ownerId"
			).eq(
				0
			);
		}
		else {
			condition = DSL.field(
				"ownerId"
			).in(
				individualIds
			);
		}

		if (negate) {
			return DSL.not(condition);
		}

		return condition;
	}

	private Condition _getDayDateCondition(
		String operator, String valueString) {

		Condition condition = null;

		Field<Object> dayDateField = DSL.field("dayDate");
		Field<Timestamp> valueField = DSL.toTimestamp(
			valueString, "yyyy-MM-dd\\THH24:MI:SS\\Z");

		if (operator.equalsIgnoreCase("ge")) {
			condition = dayDateField.ge(
				DSL.field("{0} AT TIME ZONE 'UTC'", valueField));
		}
		else if (operator.equalsIgnoreCase("lt")) {
			condition = dayDateField.lt(
				DSL.field("{0} AT TIME ZONE 'UTC'", valueField));
		}

		return condition;
	}

	private boolean _isIdFilter(String operator, String valueString) {
		if ((operator.equalsIgnoreCase("eq") ||
			 operator.equalsIgnoreCase("ne")) &&
			(valueString.length() >= 2) && valueString.startsWith("'") &&
			valueString.endsWith("'")) {

			return true;
		}

		return false;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private SegmentDog _segmentDog;

}