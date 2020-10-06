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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.model.FieldMapping;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class FieldMappingDog {

	public ResultBag<FieldMapping> getFieldMappingResultBag(
		String context, String ownerType, int size, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"field-mappings", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				Collections.emptyList(), _buildQueryBuilder(context, ownerType),
				size, start));

		return DogUtil.createResultBag(FieldMapping.class, searchHits);
	}

	private QueryBuilder _buildQueryBuilder(String context, String ownerType) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (!StringUtils.isBlank(context)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("context", context));
		}

		if (!StringUtils.isBlank(context)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("ownerType", ownerType));
		}

		return boolQueryBuilder;
	}

	@Autowired
	private DataDog _dataDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}