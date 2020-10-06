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

import com.liferay.osb.asah.backend.model.PageAsset;
import com.liferay.osb.asah.backend.model.PropertyFilter;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
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
public class AssetDog {

	public ResultBag<PageAsset> getPageAssetResultBag(
		String keywords, List<PropertyFilter> propertyFilters, int size,
		Sort sort, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"assets", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				SortBuilderUtil.fieldSort(sort),
				_buildQueryBuilder("Page", keywords, propertyFilters), size,
				start));

		return DogUtil.createResultBag(PageAsset.class, searchHits);
	}

	private QueryBuilder _buildQueryBuilder(
		String assetType, String keywords,
		List<PropertyFilter> propertyFilters) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("assetType", assetType));

		if (StringUtils.isNotBlank(keywords)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.wildcardQuery(
						"canonicalUrl", "*" + keywords + "*")
				).should(
					QueryBuilders.queryStringQuery(
						String.format(
							"name:*%1$s* OR description:*%1$s*",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.multiMatchQuery(
						keywords, "name", "description"
					).fuzziness(
						Fuzziness.AUTO
					)
				).should(
					QueryBuilders.wildcardQuery("url", "*" + keywords + "*")
				));
		}

		DogUtil.addBoolQueryBuilderPropertyFilters(
			boolQueryBuilder, propertyFilters);

		return boolQueryBuilder;
	}

	@Autowired
	private DataDog _dataDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}