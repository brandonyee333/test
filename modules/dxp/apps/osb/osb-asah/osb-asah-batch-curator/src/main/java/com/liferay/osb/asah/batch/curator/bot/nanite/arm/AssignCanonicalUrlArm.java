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

package com.liferay.osb.asah.batch.curator.bot.nanite.arm;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class AssignCanonicalUrlArm {

	public void execute() {
		_resolveURLs(
			(canonicalUrl, url) -> {
				_updateActivities(canonicalUrl, url);
				_updateAssets(canonicalUrl, url);
				_updatePages(canonicalUrl, url, "pages", "page-referrers");
				_updateVisitedPages(canonicalUrl, url);
			},
			"pages", this::_getPagesSearchResponse);

		_resolveURLs(
			this::_updateActivities, "activities",
			this::_getActivitiesSearchResponse);

		_resolveURLs(
			(canonicalUrl, url) -> _updatePages(
				canonicalUrl, url, "page-referrers"),
			"page-referrers", this::_getPagesSearchResponse);

		_resolveURLs(
			this::_updateVisitedPages, "visited-pages",
			this::_getVisitedPagesSearchResponse);

		for (String assetCollectionName : _ASSET_COLLECTION_NAMES) {
			_resolveURLs(
				this::_updateAssets, assetCollectionName,
				this::_getAssetsSearchResponse);
		}
	}

	private SearchResponse _getActivitiesSearchResponse(String collectionName) {
		return _getSearchResponse(
			collectionName, _faroInfoElasticsearchInvoker, "object.url",
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("object.canonicalUrl")));
	}

	private SearchResponse _getAssetsSearchResponse(String collectionName) {
		return _getSearchResponse(
			collectionName, _cerebroInfoElasticsearchInvoker, "tempUrls",
			QueryBuilders.scriptQuery(_ASSET_QUERY_SCRIPT));
	}

	private SearchResponse _getPagesSearchResponse(String collectionName) {
		return _getSearchResponse(
			collectionName, _cerebroInfoElasticsearchInvoker, "url",
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("canonicalUrl")));
	}

	private SearchResponse _getSearchResponse(
		String collectionName, ElasticsearchInvoker elasticsearchInvoker,
		String fieldName, QueryBuilder queryBuilder) {

		return elasticsearchInvoker.search(
			collectionName,
			searchSourceBuilder -> {
				TermsAggregationBuilder termsAggregationBuilder =
					AggregationBuilders.terms("urls");

				termsAggregationBuilder.field(fieldName);
				termsAggregationBuilder.size(1024);

				searchSourceBuilder.aggregation(termsAggregationBuilder);

				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.size(0);
			});
	}

	private List<? extends Terms.Bucket> _getTermsBuckets(
		Aggregations aggregations) {

		if (aggregations == null) {
			return Collections.emptyList();
		}

		Terms terms = aggregations.get("urls");

		if (terms == null) {
			return Collections.emptyList();
		}

		return terms.getBuckets();
	}

	private SearchResponse _getVisitedPagesSearchResponse(
		String collectionName) {

		return _getSearchResponse(
			collectionName, _faroInfoElasticsearchInvoker, "url",
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("canonicalUrl")));
	}

	@PostConstruct
	private void _init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		_updateAssetCanonicalUrlsScriptSource = ScriptUtil.loadScriptSource(
			getClass(), "update_asset_canonical_urls.painless");
	}

	private void _resolveURLs(
		BiConsumer<String, String> canonicalUrlSetterBiConsumer,
		String collectionName,
		Function<String, SearchResponse> searchFunction) {

		while (true) {
			SearchResponse searchResponse = searchFunction.apply(
				collectionName);

			List<? extends Terms.Bucket> termsBuckets = _getTermsBuckets(
				searchResponse.getAggregations());

			if (termsBuckets.isEmpty()) {
				break;
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"%d documents left to be processed in %s index.",
						HitsUtil.getTotalHitsCount(searchResponse.getHits()),
						collectionName));
			}

			for (Terms.Bucket termsBucket : termsBuckets) {
				String url = termsBucket.getKeyAsString();

				JSONObject jsonObject = _cerebroRawElasticsearchInvoker.fetch(
					"analytics-events",
					QueryBuilders.termsQuery("context.url", url), null,
					"context.canonicalUrl");

				String canonicalUrl = url;

				if (jsonObject != null) {
					canonicalUrl = String.valueOf(
						jsonObject.query("/context/canonicalUrl"));
				}

				canonicalUrlSetterBiConsumer.accept(canonicalUrl, url);
			}
		}
	}

	private void _updateActivities(String canonicalUrl, String url) {
		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("object.url", url)
			).mustNot(
				QueryBuilders.termsQuery("object.canonicalUrl", canonicalUrl)
			),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.object.canonicalUrl = params.canonicalUrl",
				Collections.singletonMap("canonicalUrl", canonicalUrl)),
			"activities");
	}

	private void _updateAssets(String canonicalUrl, String url) {
		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("urls", url)
			).filter(
				QueryBuilders.scriptQuery(_ASSET_QUERY_SCRIPT)
			),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				_updateAssetCanonicalUrlsScriptSource,
				new HashMap<String, Object>() {
					{
						put("canonicalUrl", canonicalUrl);
						put("url", url);
					}
				}),
			_ASSET_COLLECTION_NAMES);
	}

	private void _updatePages(
		String canonicalUrl, String url, String... collectionNames) {

		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("url", url)
			).mustNot(
				QueryBuilders.termsQuery("canonicalUrl", canonicalUrl)
			),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.canonicalUrl = params.canonicalUrl",
				Collections.singletonMap("canonicalUrl", canonicalUrl)),
			collectionNames);
	}

	private void _updateVisitedPages(String canonicalUrl, String url) {
		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("url", url)
			).mustNot(
				QueryBuilders.termsQuery("canonicalUrl", canonicalUrl)
			),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.canonicalUrl = params.canonicalUrl",
				Collections.singletonMap("canonicalUrl", canonicalUrl)),
			"visited-pages");
	}

	private static final String[] _ASSET_COLLECTION_NAMES = {
		"blog-clicks", "blog-social-shares", "blog-traffic-sources", "blogs",
		"custom-assets", "document-libraries", "forms", "journal-clicks",
		"journals"
	};

	private static final Script _ASSET_QUERY_SCRIPT = new Script(
		"doc.containsKey('tempUrls') && doc['tempUrls'].length > 0");

	private static final Log _log = LogFactory.getLog(
		AssignCanonicalUrlArm.class);

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;
	private String _updateAssetCanonicalUrlsScriptSource;

}