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

import org.elasticsearch.action.search.SearchResponse;
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
	}

	public static class Builder {

		public ElasticsearchDump build() {
			return _elasticsearchDump;
		}

		public Builder from(
			String collection, ElasticsearchInvoker elasticsearchInvoker) {

			_elasticsearchDump._collection = collection;
			_elasticsearchDump._elasticsearchInvoker = elasticsearchInvoker;

			return this;
		}

		public Builder to(Storage storage) {
			_elasticsearchDump._storage = storage;

			return this;
		}

		private final ElasticsearchDump _elasticsearchDump =
			new ElasticsearchDump();

	}

	private ElasticsearchDump() {
	}

	private SearchHit[] _readBatch() {
		SearchResponse searchResponse = null;

		if (_scrollId == null) {
			searchResponse = _elasticsearchInvoker.searchScroll(
				_collection,
				searchSourceBuilder -> {
					searchSourceBuilder.trackTotalHits(true);
					searchSourceBuilder.size(500);
					searchSourceBuilder.sort("id", SortOrder.ASC);
				},
				120);

			_scrollId = searchResponse.getScrollId();
		}
		else {
			searchResponse = _elasticsearchInvoker.searchScroll(_scrollId, 120);

			_scrollId = searchResponse.getScrollId();
		}

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

	private String _collection;
	private ElasticsearchInvoker _elasticsearchInvoker;
	private String _scrollId;
	private Storage _storage;

}