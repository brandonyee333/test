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

package com.liferay.osb.asah.common.elasticsearch;

import com.liferay.osb.asah.common.storage.Storage;

import java.util.Arrays;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;

/**
 * @author Marcellus Tavares
 */
public class ElasticsearchDump {

	public void dump() {
		for (SearchHit[] searchHits = _readBatch(); searchHits.length > 0;
			 searchHits = _readBatch()) {

			_writeBatch(searchHits);
		}

		_clearScroll();
	}

	public static class Builder {

		public ElasticsearchDump build() {
			return _elasticsearchDump;
		}

		public Builder from(
			String collection, ElasticsearchInvoker elasticsearchInvoker) {

			return from(collection, elasticsearchInvoker, null);
		}

		public Builder from(
			String collection, ElasticsearchInvoker elasticsearchInvoker,
			QueryBuilder queryBuilder) {

			_elasticsearchDump._collection = collection;
			_elasticsearchDump._elasticsearchInvoker = elasticsearchInvoker;
			_elasticsearchDump._queryBuilder = queryBuilder;

			return this;
		}

		public Builder to(Storage storage) {
			_elasticsearchDump._storage = storage;

			return this;
		}

		public Builder withQueryBuilder(QueryBuilder queryBuilder) {
			_elasticsearchDump._queryBuilder = queryBuilder;

			return this;
		}

		private final ElasticsearchDump _elasticsearchDump =
			new ElasticsearchDump();

	}

	private ElasticsearchDump() {
	}

	private void _clearScroll() {
		if (_scrollId == null) {
			return;
		}

		try {
			_elasticsearchInvoker.clearScroll(_scrollId);
		}
		catch (Exception exception) {
			_log.error("Unable to clear scroll ID " + _scrollId, exception);
		}
	}

	private SearchHit[] _readBatch() {
		SearchResponse searchResponse = null;

		if (_scrollId == null) {
			searchResponse = _elasticsearchInvoker.searchScroll(
				_collection,
				searchSourceBuilder -> {
					if (_queryBuilder != null) {
						searchSourceBuilder.query(_queryBuilder);
					}

					searchSourceBuilder.trackTotalHits(true);
					searchSourceBuilder.size(500);
					searchSourceBuilder.sort("id", SortOrder.ASC);
				},
				120);
		}
		else {
			searchResponse = _elasticsearchInvoker.searchScroll(_scrollId, 120);
		}

		_scrollId = searchResponse.getScrollId();

		SearchHits searchHits = searchResponse.getHits();

		return searchHits.getHits();
	}

	private void _writeBatch(SearchHit[] searchHits) {
		Stream<SearchHit> stream = Arrays.stream(searchHits);

		stream.map(
			SearchHit::getSourceAsString
		).forEach(
			_storage::write
		);
	}

	private static final Log _log = LogFactory.getLog(ElasticsearchDump.class);

	private String _collection;
	private ElasticsearchInvoker _elasticsearchInvoker;
	private QueryBuilder _queryBuilder;
	private String _scrollId;
	private Storage _storage;

}