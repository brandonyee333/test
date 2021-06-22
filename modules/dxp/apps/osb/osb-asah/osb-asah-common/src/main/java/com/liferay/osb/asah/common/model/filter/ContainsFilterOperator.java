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

import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.jooq.Condition;
import org.jooq.Field;

/**
 * @author Leslie Wong
 */
public class ContainsFilterOperator extends FilterOperator {

	public ContainsFilterOperator(List<String> values) {
		super("contains", values);
	}

	@Override
	public List<String> formatStringValues() {
		return ListUtil.map(
			values,
			argument -> StringUtil.unquoteAndDecodeInnerQuotes(
				String.valueOf(argument)));
	}

	@Override
	public Condition getCondition(Field field) {
		List<String> values = formatStringValues();

		return field.containsIgnoreCase(values.get(0));
	}

	@Override
	public QueryBuilder getQueryBuilder(String fieldName) {
		List<String> values = formatStringValues();

		return QueryBuilders.regexpQuery(
			fieldName,
			FilterStringToQueryBuilderConverter.buildIgnoreCaseRegExp(
				values.get(0)));
	}

}