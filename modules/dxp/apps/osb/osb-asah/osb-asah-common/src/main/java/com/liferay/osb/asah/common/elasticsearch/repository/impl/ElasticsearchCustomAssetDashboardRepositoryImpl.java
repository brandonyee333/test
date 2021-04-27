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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.repository.CustomAssetDashboardRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author André Miranda
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchCustomAssetDashboardRepositoryImpl
	extends BaseElasticsearchRepository<CustomAssetDashboard, String>
	implements CustomAssetDashboardRepository {

	@Override
	public ResultBag<CustomAssetDashboard> searchCustomAssetDashboard(
		Long channelId, String keywords, Pageable pageable) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.query(
			_buildKeywordsQueryBuilder(channelId, keywords));

		setSearchSourceBuilderPage(searchSourceBuilder, pageable);

		SearchResponse searchResponse = _cerebroInfoElasticsearchInvoker.search(
			"custom-asset-dashboards", searchSourceBuilder);

		ResultBag<CustomAssetDashboard> resultBag = new ResultBag<>();

		if (searchResponse.status() != RestStatus.OK) {
			for (ShardSearchFailure shardSearchFailure :
					searchResponse.getShardFailures()) {

				_log.error(shardSearchFailure.getCause());
			}

			return resultBag;
		}

		SearchHits searchHits = searchResponse.getHits();

		List<CustomAssetDashboard> customAssetDashboards = new ArrayList<>();

		for (SearchHit searchHit : searchHits) {
			customAssetDashboards.add(
				toEntity(new JSONObject(searchHit.getSourceAsString())));
		}

		resultBag.setResults(customAssetDashboards);
		resultBag.setTotal(HitsUtil.getTotalHitsCount(searchHits));

		return resultBag;
	}

	@Override
	protected String getCollectionName() {
		return "custom-asset-dashboards";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _cerebroInfoElasticsearchInvoker;
	}

	private QueryBuilder _buildKeywordsQueryBuilder(
		Long channelId, String keywords) {

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelId", String.valueOf(channelId));

		if (StringUtils.isNotBlank(keywords)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.queryStringQuery(
						String.format(
							"%s:*%s*", "assetTitle.search",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.matchQuery(
						"assetTitle.search", keywords
					).fuzziness(
						Fuzziness.AUTO
					)
				));
		}

		if (boolQueryBuilder.hasClauses()) {
			return boolQueryBuilder;
		}

		return QueryBuilders.matchAllQuery();
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchCustomAssetDashboardRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

}