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

		_elasticsearchFilterStringConverterHelper =
			elasticsearchFilterStringConverterHelper;
		_filterString = filterString;
		_postgresqlFilterStringConverterHelper =
			postgresqlFilterStringConverterHelper;
	}

	public FilterHelper(String filterString) {
		_filterString = filterString;

		_elasticsearchFilterStringConverterHelper = null;
		_postgresqlFilterStringConverterHelper = null;
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

		if (Objects.equals(
				_elasticsearchFilterStringConverterHelper,
				filterHelper._elasticsearchFilterStringConverterHelper) &&
			Objects.equals(_filterString, filterHelper._filterString) &&
			Objects.equals(
				_postgresqlFilterStringConverterHelper,
				filterHelper._postgresqlFilterStringConverterHelper)) {

			return true;
		}

		return false;
	}

	public Condition getCondition() {
		if (_postgresqlFilterStringConverterHelper == null) {
			return ConditionUtil.toCondition(_filterString);
		}

		return ConditionUtil.toCondition(
			_filterString, _postgresqlFilterStringConverterHelper);
	}

	public String getFilterString() {
		return _filterString;
	}

	public QueryBuilder getQueryBuilder() {
		if (_elasticsearchFilterStringConverterHelper == null) {
			return FilterStringToQueryBuilderConverter.convert(_filterString);
		}

		return FilterStringToQueryBuilderConverter.convert(
			_filterString, _elasticsearchFilterStringConverterHelper);
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_elasticsearchFilterStringConverterHelper, _filterString,
			_postgresqlFilterStringConverterHelper);
	}

	private final FilterStringConverterHelper
		_elasticsearchFilterStringConverterHelper;
	private final String _filterString;
	private final FilterStringConverterHelper
		_postgresqlFilterStringConverterHelper;

}