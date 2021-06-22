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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.jooq.Condition;
import org.jooq.Field;

/**
 * @author Leslie Wong
 */
public class NotEqualsFilterOperator extends FilterOperator {

	public NotEqualsFilterOperator(List<String> values) {
		super("ne", values);
	}

	@Override
	public Condition getCondition(Field field) {
		Object value = values.get(0);

		if (value != null) {
			return field.ne(value);
		}

		return field.isNotNull();
	}

	@Override
	public QueryBuilder getQueryBuilder(String fieldName) {
		Object value = values.get(0);

		if (value != null) {
			return BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termQuery(fieldName, value));
		}

		return QueryBuilders.existsQuery(fieldName);
	}

}