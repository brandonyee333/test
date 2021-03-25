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

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.model.Segment;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

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

	private QueryBuilder _getAccountIdQueryBuilder(
			String accountId, boolean negate)
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

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private SegmentDog _segmentDog;

}