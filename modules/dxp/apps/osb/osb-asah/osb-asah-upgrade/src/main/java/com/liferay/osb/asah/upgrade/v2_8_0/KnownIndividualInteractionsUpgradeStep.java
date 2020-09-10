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

package com.liferay.osb.asah.upgrade.v2_8_0;

import com.google.common.collect.Lists;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class KnownIndividualInteractionsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		List<String> knownIndividualIds = _getKnownIndividualIds();

		if (_log.isInfoEnabled()) {
			_log.info(
				knownIndividualIds.size() +
					" known individuals to be processed");
		}

		int processedCount = 0;

		for (List<String> individualIds :
				Lists.partition(knownIndividualIds, 1000)) {

			_updatePagesAndAssets(individualIds, true);

			if (_log.isInfoEnabled()) {
				_log.info(
					(processedCount += individualIds.size()) +
						" individuals processed");
			}
		}

		List<String> anonymousIndividualIds = _getAnonymousIndividualIds();

		if (_log.isInfoEnabled()) {
			_log.info(
				anonymousIndividualIds.size() +
					" anonymous individuals to be processed");
		}

		processedCount = 0;

		for (List<String> individualIds :
				Lists.partition(anonymousIndividualIds, 1000)) {

			_updatePagesAndAssets(individualIds, false);

			if (_log.isInfoEnabled()) {
				_log.info(
					(processedCount += individualIds.size()) +
						" individuals processed");
			}
		}
	}

	private List<String> _getAnonymousIndividualIds() {
		return _getIndividualIds(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("demographics.email.value")));
	}

	private List<String> _getIndividualIds(QueryBuilder queryBuilder) {
		List<String> individualIds = new ArrayList<>();

		long keepAliveSeconds = 120;

		SearchResponse searchResponse =
			_faroInfoElasticsearchInvoker.searchScroll(
				"individuals",
				searchSourceBuilder -> {
					searchSourceBuilder.fetchSource("id", null);
					searchSourceBuilder.query(queryBuilder);
					searchSourceBuilder.size(500);
				},
				keepAliveSeconds);

		String scrollId = searchResponse.getScrollId();

		SearchHits searchHits = searchResponse.getHits();

		SearchHit[] hits = searchHits.getHits();

		while ((hits != null) && (hits.length > 0)) {
			Stream<SearchHit> stream = Arrays.stream(hits);

			individualIds.addAll(
				stream.map(
					searchHit -> new JSONObject(searchHit.getSourceAsString())
				).map(
					jsonObject -> jsonObject.getString("id")
				).collect(
					Collectors.toList()
				));

			searchResponse = _faroInfoElasticsearchInvoker.searchScroll(
				scrollId, keepAliveSeconds);

			scrollId = searchResponse.getScrollId();

			searchHits = searchResponse.getHits();

			hits = searchHits.getHits();
		}

		return individualIds;
	}

	private List<String> _getKnownIndividualIds() {
		return _getIndividualIds(
			QueryBuilders.existsQuery("demographics.email.value"));
	}

	@PostConstruct
	private void _init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private void _updatePagesAndAssets(
		List<String> individualIds, boolean knownIndividual) {

		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("individualId", individualIds)
			).filter(
				QueryBuilders.termQuery("knownIndividual", !knownIndividual)
			),
			false,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.knownIndividual = params.knownIndividual",
				Collections.singletonMap("knownIndividual", knownIndividual)),
			_COLLECTION_NAMES);
	}

	private static final String[] _COLLECTION_NAMES = {
		"blog-clicks", "blog-social-shares", "blog-traffic-sources", "blogs",
		"custom-assets", "document-libraries", "forms", "journal-clicks",
		"journals", "page-referrers", "pages"
	};

	private static final Log _log = LogFactory.getLog(
		KnownIndividualInteractionsUpgradeStep.class);

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}