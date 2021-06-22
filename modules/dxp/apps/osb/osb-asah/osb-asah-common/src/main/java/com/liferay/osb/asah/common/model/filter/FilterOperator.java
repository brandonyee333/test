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

package com.liferay.osb.asah.common.model.filter;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Date;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;

import org.jooq.Condition;
import org.jooq.Field;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leslie Wong
 */
public abstract class FilterOperator {

	public static FilterOperator of(String name, List<String> values) {
		if (name.equals("between")) {
			return new BetweenFilterOperator(values);
		}
		else if (name.equals("contains")) {
			return new ContainsFilterOperator(values);
		}
		else if (name.equals("endsWith")) {
			return new EndsWithFilterOperator(values);
		}
		else if (name.equals("eq")) {
			return new EqualsFilterOperator(values);
		}
		else if (name.equals("ge")) {
			return new GreaterThanEqualsFilterOperator(values);
		}
		else if (name.equals("gt")) {
			return new GreaterThanFilterOperator(values);
		}
		else if (name.equals("le")) {
			return new LessThanEqualsFilterOperator(values);
		}
		else if (name.equals("lt")) {
			return new LessThanFilterOperator(values);
		}
		else if (name.equals("ne")) {
			return new NotEqualsFilterOperator(values);
		}
		else if (name.equals("similarTo")) {
			return new SimilarToFilterOperator(values);
		}
		else if (name.equals("startsWith")) {
			return new StartsWithFilterOperator(values);
		}

		throw new IllegalArgumentException("Invalid operator: " + name);
	}

	public abstract Condition getCondition(Field field);

	public abstract QueryBuilder getQueryBuilder(String fieldName);

	protected FilterOperator(
		int expectedArgumentCount, String name, List<String> values) {

		_validate(expectedArgumentCount, name, values);

		this.values = values;
	}

	protected FilterOperator(String name, List<String> values) {
		_validate(1, name, values);

		this.values = values;
	}

	protected List<Date> formatDateValues() {
		return ListUtil.map(
			values,
			value -> {
				LocalDate localDate = LocalDate.parse(value);

				return DateUtil.toDate(
					localDate.atStartOfDay(),
					ZoneId.of(_timeZoneDog.getTimeZoneId()));
			});
	}

	protected List<Long> formatNumberValues() {
		return ListUtil.map(values, Long::valueOf);
	}

	protected List<String> formatStringValues() {
		return ListUtil.map(values, StringUtil::unquoteAndDecodeInnerQuotes);
	}

	protected List<String> values;

	private void _validate(
		int expectedArgumentCount, String name, List<String> values) {

		if (expectedArgumentCount != values.size()) {
			throw new IllegalArgumentException(
				String.format(
					"Expected %d value(s) for %s operation, got %d instead: %s",
					expectedArgumentCount, name, values.size(), values));
		}
	}

	@Autowired
	private TimeZoneDog _timeZoneDog;

}