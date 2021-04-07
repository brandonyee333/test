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

package com.liferay.osb.asah.common.dog;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.model.Suppression;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class SuppressionDog {

	public ResultBag<Suppression> getSuppressionResultBag(
		String keywords, int size, Sort sort, int start) {

		QueryBuilder queryBuilder = null;

		if (StringUtils.isBlank(keywords)) {
			queryBuilder = QueryBuilders.matchAllQuery();
		}
		else {
			queryBuilder = BoolQueryBuilderUtil.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.queryStringQuery(
						String.format(
							"%s:*%s*", "emailAddress",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.matchQuery(
						"emailAddress", keywords
					).fuzziness(
						Fuzziness.AUTO
					)
				));
		}

		return DogUtil.createResultBag(
			Suppression.class, _objectMapper,
			_dataDog.querySearchHits(
				"suppressions", _faroInfoElasticsearchInvoker,
				DogUtil.buildSearchSourceBuilder(
					SortBuilderUtil.fieldSort(sort), queryBuilder, size,
					start)));
	}

	@Autowired
	private DataDog _dataDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}