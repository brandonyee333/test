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
import com.liferay.osb.asah.common.util.StringUtil;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Map;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

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
	public String getFilterType() {
		return "activities";
	}

	@Override
	public Condition getLogicFunctionCondition(
		String fieldName, String operator, boolean processString,
		String valueString) {

		if (fieldName.equals("Event.activityKey")) {
			String[] values = valueString.split("#");

			Field<String> applicationIdField = DSL.field(
				"Event.applicationId", String.class);
			Field<String> eventIdField = DSL.field(
				"Event.eventId", String.class);
			Field<String> idField = DSL.field("Event.id", String.class);

			if (operator.equals("eq")) {
				Condition condition = applicationIdField.eq(values[0]);

				condition = condition.and(eventIdField.eq(values[1]));

				return condition.and(idField.eq(values[2]));
			}

			Condition condition = applicationIdField.ne(values[0]);

			condition = condition.and(eventIdField.ne(values[1]));

			return condition.and(idField.ne(values[2]));
		}

		if (fieldName.equals("channelId") ||
			fieldName.equals("Event.channelId") ||
			fieldName.equals("ownerId")) {

			Field<Long> field = DSL.field(
				fieldName
			).cast(
				Long.class
			);

			if (operator.equals("eq")) {
				return field.eq(Long.valueOf(StringUtil.unquote(valueString)));
			}

			if (operator.equals("ne")) {
				return field.ne(Long.valueOf(StringUtil.unquote(valueString)));
			}
		}

		Condition condition = getTimeFrameCondition(
			fieldName, operator, processString, valueString);

		if (condition != null) {
			return condition;
		}

		if (fieldName.equals("dayDate")) {
			return _getDayDateCondition(
				operator, StringUtil.unquote(valueString));
		}

		return null;
	}

	@Override
	public String getTableName() {
		return "Event";
	}

	private Condition _getDayDateCondition(
		String operator, String valueString) {

		Condition condition = null;

		Field<Object> dayDateField = DSL.field("dayDate AT TIME ZONE 'UTC'");
		Field<Timestamp> valueField = DSL.toTimestamp(
			valueString, "yyyy-MM-dd\\THH24:MI:SS\\Z");

		if (operator.equalsIgnoreCase("ge")) {
			condition = dayDateField.ge(valueField);
		}
		else if (operator.equalsIgnoreCase("lt")) {
			condition = dayDateField.lt(valueField);
		}

		return condition;
	}

}