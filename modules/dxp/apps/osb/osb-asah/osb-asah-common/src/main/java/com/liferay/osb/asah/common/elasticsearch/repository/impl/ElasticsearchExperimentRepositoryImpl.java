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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.model.ExperimentStatus;
import com.liferay.osb.asah.common.repository.ExperimentRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcos Martins
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchExperimentRepositoryImpl
	extends BaseElasticsearchRepository<Experiment, Long>
	implements ExperimentRepository {

	@Override
	public List<Experiment> findByExperimentStatus(
		ExperimentStatus experimentStatus) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termQuery(
					"status", experimentStatus.toString())));
	}

	@Override
	public List<Experiment> searchExperimentsByChannelIdAndKeywords(
		Long channelId, String keywords, Pageable pageable) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.query(
					_buildKeywordsQueryBuilder(channelId, keywords));

				setSearchSourceBuilderPage(searchSourceBuilder, pageable);
			});

		List<Experiment> experiments = new ArrayList<>();

		try {
			for (SearchHit searchHit : searchResponse.getHits()) {
				experiments.add(
					_objectMapper.readValue(
						searchHit.getSourceAsString(), Experiment.class));
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return experiments;
	}

	@Override
	protected String getCollectionName() {
		return "experiments";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
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
							"name:*%1$s* OR description:*%1$s*",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.multiMatchQuery(
						keywords, "name", "description"
					).fuzziness(
						Fuzziness.AUTO
					)
				).should(
					QueryBuilders.wildcardQuery(
						"pageURL.search", "*" + keywords + "*")
				));
		}

		if (boolQueryBuilder.hasClauses()) {
			return boolQueryBuilder;
		}

		return QueryBuilders.matchAllQuery();
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchExperimentRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}