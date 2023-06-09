/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.elasticsearch7.internal;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexSearcher;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.generic.StringQuery;
import com.liferay.portal.kernel.search.suggest.QuerySuggester;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.aggregation.Aggregation;
import com.liferay.portal.search.aggregation.pipeline.PipelineAggregation;
import com.liferay.portal.search.constants.SearchContextAttributes;
import com.liferay.portal.search.elasticsearch7.constants.ElasticsearchSearchContextAttributes;
import com.liferay.portal.search.elasticsearch7.internal.configuration.ElasticsearchConfigurationWrapper;
import com.liferay.portal.search.elasticsearch7.internal.deep.pagination.configuration.DeepPaginationConfigurationWrapper;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.BaseSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.legacy.searcher.SearchResponseBuilderFactory;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.sort.Sorts;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
@Component(
	property = "search.engine.impl=Elasticsearch", service = IndexSearcher.class
)
public class ElasticsearchIndexSearcher extends BaseIndexSearcher {

	@Override
	public String getQueryString(SearchContext searchContext, Query query) {
		return StringPool.BLANK;
	}

	@Override
	public Hits search(SearchContext searchContext, Query query) {
		return _getHits();
	}

	@Override
	public long searchCount(SearchContext searchContext, Query query) {
		return 0;
	}

	protected SearchSearchRequest createSearchSearchRequest(
		SearchRequest searchRequest, SearchContext searchContext, Query query) {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		_prepare(searchSearchRequest, searchRequest, query, searchContext);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		searchSearchRequest.setAlternateUidFieldName(
			queryConfig.getAlternateUidFieldName());

		searchSearchRequest.setBasicFacetSelection(
			searchRequest.isBasicFacetSelection());

		searchSearchRequest.putAllFacets(searchContext.getFacets());
		searchSearchRequest.setFetchSource(searchRequest.getFetchSource());
		searchSearchRequest.setFetchSourceExcludes(
			searchRequest.getFetchSourceExcludes());
		searchSearchRequest.setFetchSourceIncludes(
			searchRequest.getFetchSourceIncludes());
		searchSearchRequest.setGroupBy(searchContext.getGroupBy());
		searchSearchRequest.setGroupByRequests(
			searchRequest.getGroupByRequests());
		searchSearchRequest.setHighlightEnabled(
			queryConfig.isHighlightEnabled());
		searchSearchRequest.setHighlightFieldNames(
			queryConfig.getHighlightFieldNames());
		searchSearchRequest.setHighlightFragmentSize(
			queryConfig.getHighlightFragmentSize());
		searchSearchRequest.setHighlightSnippetSize(
			queryConfig.getHighlightSnippetSize());
		searchSearchRequest.setLocale(queryConfig.getLocale());
		searchSearchRequest.setHighlightRequireFieldMatch(
			queryConfig.isHighlightRequireFieldMatch());
		searchSearchRequest.setLuceneSyntax(
			GetterUtil.getBoolean(
				searchContext.getAttribute(
					SearchContextAttributes.ATTRIBUTE_KEY_LUCENE_SYNTAX)));

		String preference = (String)searchContext.getAttribute(
			ElasticsearchSearchContextAttributes.
				ATTRIBUTE_KEY_SEARCH_REQUEST_PREFERENCE);

		if (!Validator.isBlank(preference)) {
			searchSearchRequest.setPreference(preference);
		}

		searchSearchRequest.setScoreEnabled(queryConfig.isScoreEnabled());
		searchSearchRequest.setSelectedFieldNames(
			queryConfig.getSelectedFieldNames());
		searchSearchRequest.setStats(searchContext.getStats());

		return searchSearchRequest;
	}

	@Override
	protected QuerySuggester getQuerySuggester() {
		return _querySuggester;
	}

	protected void setIndexNames(
		BaseSearchRequest baseSearchRequest, SearchRequest searchRequest,
		SearchContext searchContext) {

		baseSearchRequest.setIndexNames(
			_getIndexes(searchRequest, searchContext));
	}

	protected void setQuery(
		BaseSearchRequest baseSearchRequest, SearchRequest searchRequest) {

		baseSearchRequest.setQuery(searchRequest.getQuery());
	}

	private Hits _getHits() {
		Hits hits = new HitsImpl();

		hits.setCollatedSpellCheckResult(StringPool.BLANK);
		hits.setDocs(new Document[0]);
		hits.setLength(0);
		hits.setQuery(new StringQuery(StringPool.BLANK));
		hits.setQuerySuggestions(new String[0]);
		hits.setQueryTerms(new String[0]);
		hits.setLength(0);
		hits.setScores(new float[0]);
		hits.setSearchTime(0);
		hits.setSnippets(new String[0]);
		hits.setSpellCheckResults(_spellCheckResults);
		hits.setStart(0);

		return hits;
	}

	private String[] _getIndexes(
		SearchRequest searchRequest, SearchContext searchContext) {

		List<String> indexes = searchRequest.getIndexes();

		if (!indexes.isEmpty()) {
			return indexes.toArray(new String[0]);
		}

		String indexName = _indexNameBuilder.getIndexName(
			searchContext.getCompanyId());

		return new String[] {indexName};
	}

	private void _prepare(
		BaseSearchRequest baseSearchRequest, SearchRequest searchRequest,
		Query query, SearchContext searchContext) {

		baseSearchRequest.addComplexQueryParts(
			searchRequest.getComplexQueryParts());
		baseSearchRequest.setExplain(searchRequest.isExplain());
		baseSearchRequest.setHighlight(searchRequest.getHighlight());
		baseSearchRequest.setIncludeResponseString(
			searchRequest.isIncludeResponseString());
		baseSearchRequest.setPostFilterQuery(
			searchRequest.getPostFilterQuery());
		baseSearchRequest.addPostFilterComplexQueryParts(
			searchRequest.getPostFilterComplexQueryParts());
		baseSearchRequest.setRescores(searchRequest.getRescores());
		baseSearchRequest.setStatsRequests(searchRequest.getStatsRequests());
		baseSearchRequest.setTrackTotalHits(
			_elasticsearchConfigurationWrapper.trackTotalHits());

		_setAggregations(baseSearchRequest, searchRequest);
		_setConnectionId(baseSearchRequest, searchRequest);
		setIndexNames(baseSearchRequest, searchRequest, searchContext);
		_setLegacyQuery(baseSearchRequest, query);
		_setLegacyPostFilter(baseSearchRequest, query);
		_setPipelineAggregations(baseSearchRequest, searchRequest);
		setQuery(baseSearchRequest, searchRequest);
	}

	private void _setAggregations(
		BaseSearchRequest baseSearchRequest, SearchRequest searchRequest) {

		Map<String, Aggregation> map = searchRequest.getAggregationsMap();

		for (Aggregation aggregation : map.values()) {
			baseSearchRequest.addAggregation(aggregation);
		}
	}

	private void _setConnectionId(
		BaseSearchRequest baseSearchRequest, SearchRequest searchRequest) {

		baseSearchRequest.setConnectionId(searchRequest.getConnectionId());
	}

	private void _setLegacyPostFilter(
		BaseSearchRequest baseSearchRequest, Query query) {

		if (query != null) {
			baseSearchRequest.setPostFilter(query.getPostFilter());
		}
	}

	private void _setLegacyQuery(
		BaseSearchRequest baseSearchRequest, Query query) {

		baseSearchRequest.setQuery(query);
	}

	private void _setPipelineAggregations(
		BaseSearchRequest baseSearchRequest, SearchRequest searchRequest) {

		Map<String, PipelineAggregation> map =
			searchRequest.getPipelineAggregationsMap();

		for (PipelineAggregation aggregation : map.values()) {
			baseSearchRequest.addPipelineAggregation(aggregation);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ElasticsearchIndexSearcher.class);

	private static final Map<String, List<String>> _spellCheckResults =
		Collections.emptyMap();

	@Reference
	private volatile DeepPaginationConfigurationWrapper
		_deepPaginationConfigurationWrapper;

	@Reference
	private volatile ElasticsearchConfigurationWrapper
		_elasticsearchConfigurationWrapper;

	@Reference
	private IndexNameBuilder _indexNameBuilder;

	@Reference
	private Props _props;

	@Reference(target = "(search.engine.impl=Elasticsearch)")
	private QuerySuggester _querySuggester;

	@Reference(target = "(search.engine.impl=Elasticsearch)")
	private SearchEngineAdapter _searchEngineAdapter;

	@Reference
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

	@Reference
	private SearchResponseBuilderFactory _searchResponseBuilderFactory;

	@Reference
	private Sorts _sorts;

}