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
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.repository.CustomAssetDashboardRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author André Miranda
 */
@Repository
public class ElasticsearchCustomAssetDashboardRepositoryImpl
	extends BaseElasticsearchRepository<CustomAssetDashboard, String>
	implements CustomAssetDashboardRepository {

	@Override
	public long countCustomAssetDashboards(Long channelId, String keywords) {
		return _cerebroInfoElasticsearchInvoker.count(
			getCollectionName(), _buildQueryBuilder(channelId, keywords));
	}

	@Override
	public List<CustomAssetDashboard> searchCustomAssetDashboards(
		Long channelId, String keywords, Pageable pageable) {

		return toList(
			new JSONArray(
				_cerebroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_buildQueryBuilder(channelId, keywords));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	protected String getCollectionName() {
		return "custom-asset-dashboards";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _cerebroInfoElasticsearchInvoker;
	}

	private QueryBuilder _buildQueryBuilder(Long channelId, String keywords) {
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

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

}