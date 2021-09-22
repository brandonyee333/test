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

package com.liferay.osb.asah.common.repository.helper;

import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;

import java.util.Objects;

import org.elasticsearch.index.query.QueryBuilder;

import org.jooq.Condition;

/**
 * @author Inácio Nery
 */
public class FilterHelper {

	public static final FilterHelper EMPTY = new FilterHelper(null);

	public FilterHelper(
		FilterStringConverterHelper elasticsearchFilterStringConverterHelper,
		String filterString,
		FilterStringConverterHelper postgresqlFilterStringConverterHelper) {

		_filterString = filterString;

		_condition = ConditionUtil.toCondition(
			_filterString, postgresqlFilterStringConverterHelper);
		_queryBuilder = FilterStringToQueryBuilderConverter.convert(
			_filterString, elasticsearchFilterStringConverterHelper);
	}

	public FilterHelper(String filterString) {
		_filterString = filterString;

		_condition = ConditionUtil.toCondition(_filterString);
		_queryBuilder = FilterStringToQueryBuilderConverter.convert(
			_filterString);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FilterHelper)) {
			return false;
		}

		FilterHelper filterHelper = (FilterHelper)obj;

		if (Objects.equals(_condition, filterHelper._condition) &&
			Objects.equals(_filterString, filterHelper._filterString) &&
			Objects.equals(_queryBuilder, filterHelper._queryBuilder)) {

			return true;
		}

		return false;
	}

	public Condition getCondition() {
		return _condition;
	}

	public String getFilterString() {
		return _filterString;
	}

	public QueryBuilder getQueryBuilder() {
		return _queryBuilder;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_condition, _filterString, _queryBuilder);
	}

	private final Condition _condition;
	private final String _filterString;
	private final QueryBuilder _queryBuilder;

}