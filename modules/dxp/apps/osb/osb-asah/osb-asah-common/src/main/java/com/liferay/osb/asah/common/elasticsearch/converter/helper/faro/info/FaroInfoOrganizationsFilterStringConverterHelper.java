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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class FaroInfoOrganizationsFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public QueryBuilder getLogicFunctionQueryBuilder(
		String fieldName, String operator, String valueString) {

		if (!fieldName.equalsIgnoreCase("parentId")) {
			return null;
		}

		JSONObject organizationJSONObject = _elasticsearchInvoker.fetch(
			"organizations", StringUtil.unquote(valueString));

		if (organizationJSONObject == null) {
			return null;
		}

		QueryBuilder queryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"dataSourceId",
				organizationJSONObject.getString("dataSourceId"))
		).filter(
			QueryBuilders.termQuery(
				"parentOrganizationPK",
				organizationJSONObject.get("organizationPK"))
		);

		if (operator.equalsIgnoreCase("eq")) {
			return queryBuilder;
		}

		if (operator.equalsIgnoreCase("ne")) {
			return BoolQueryBuilderUtil.mustNot(queryBuilder);
		}

		return null;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}