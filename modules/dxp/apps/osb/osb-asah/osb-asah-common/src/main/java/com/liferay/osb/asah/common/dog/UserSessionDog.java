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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoSessionsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.BQSession;
import com.liferay.osb.asah.common.repository.BQSessionRepository;
import com.liferay.osb.asah.common.spring.annotation.VisibleForTestingOnly;
import com.liferay.osb.asah.common.util.IndividualIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Component
public class UserSessionDog {

	@VisibleForTestingOnly
	public BQSession addBQSession(
		long channelId, String id, Date sessionEndDate, Date sessionStartDate) {

		BQSession bqSession = new BQSession();

		bqSession.setChannelId(channelId);
		bqSession.setId(id);
		bqSession.setSessionEnd(sessionEndDate);
		bqSession.setSessionStart(sessionStartDate);

		return _bqSessionRepository.save(bqSession);
	}

	public List<BQSession> findByIds(Collection<String> ids) {
		return IterableUtils.toList(_bqSessionRepository.findAllById(ids));
	}

	public List<Long> getIndividualIds(String filterString) {
		List<Long> individualIds = new ArrayList<>();

		SearchResponse searchResponse = _cerebroInfoElasticsearchInvoker.search(
			"user-sessions",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"individualIds"
					).field(
						"individualId"
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(_createQueryBuilder(filterString));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations != null) {
			Terms terms = aggregations.get("individualIds");

			for (Terms.Bucket bucket : terms.getBuckets()) {
				individualIds.add(Long.valueOf(bucket.getKeyAsString()));
			}
		}

		return individualIds;
	}

	private QueryBuilder _createQueryBuilder(String filterString) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		Long individualId = IndividualIdThreadLocal.getIndividualId();

		if (individualId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("individualId", individualId));
		}

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString, _faroInfoSessionsFilterStringConverterHelper);

		if (queryBuilder != null) {
			boolQueryBuilder.filter(queryBuilder);
		}

		return boolQueryBuilder;
	}

	@Autowired
	private BQSessionRepository _bqSessionRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoSessionsFilterStringConverterHelper
		_faroInfoSessionsFilterStringConverterHelper;

	@Autowired
	private ObjectMapper _objectMapper;

}