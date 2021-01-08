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

package com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoActivitiesFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public QueryBuilder getLogicFunctionQueryBuilder(
			String fieldName, String operator, String valueString)
		throws Exception {

		if (fieldName.equals("accountId") &&
			_isIdFilter(operator, valueString)) {

			return _getAccountIdQueryBuilder(
				StringUtil.unquote(valueString),
				operator.equalsIgnoreCase("ne"));
		}

		return getTimeFrameQueryBuilder(
			fieldName, operator, "activities", valueString);
	}

	@PostConstruct
	public void init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	private QueryBuilder _getAccountIdQueryBuilder(
			String accountId, boolean negate)
		throws Exception {

		JSONObject individualSegmentJSONObject = _elasticsearchInvoker.fetch(
			"individual-segments",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("name", "Account: " + accountId)
			).filter(
				QueryBuilders.termQuery("status", "INACTIVE")
			));

		if (individualSegmentJSONObject == null) {
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
				"individualSegmentIds",
				individualSegmentJSONObject.getString("id"))
		).iterate();

		if (negate) {
			return BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termsQuery("ownerId", individualIds));
		}

		return QueryBuilders.termsQuery("ownerId", individualIds);
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

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}