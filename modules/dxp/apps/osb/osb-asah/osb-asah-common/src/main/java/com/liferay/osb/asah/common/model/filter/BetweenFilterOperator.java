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

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leslie Wong
 */
public class BetweenFilterOperator extends FilterOperator {

	public BetweenFilterOperator(List<String> values) {
		super(2, "between", values);
	}

	@Override
	public Condition getCondition(Field field) {
		return DSL.and(field.ge(values.get(0)), field.le(values.get(1)));
	}

	@Override
	public QueryBuilder getQueryBuilder(String fieldName) {
		return QueryBuilders.rangeQuery(
			fieldName
		).gte(
			values.get(0)
		).lte(
			values.get(1)
		).timeZone(
			_timeZoneDog.getTimeZoneId()
		);
	}

	@Autowired
	private TimeZoneDog _timeZoneDog;

}