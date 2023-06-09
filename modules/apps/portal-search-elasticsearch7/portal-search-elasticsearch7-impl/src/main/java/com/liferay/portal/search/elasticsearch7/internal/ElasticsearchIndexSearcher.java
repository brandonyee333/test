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
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.generic.StringQuery;
import com.liferay.portal.kernel.search.suggest.QuerySuggester;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.search.elasticsearch7.internal.configuration.ElasticsearchConfigurationWrapper;
import com.liferay.portal.search.elasticsearch7.internal.deep.pagination.configuration.DeepPaginationConfigurationWrapper;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.BaseSearchRequest;
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

	@Override
	protected QuerySuggester getQuerySuggester() {
		return _querySuggester;
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