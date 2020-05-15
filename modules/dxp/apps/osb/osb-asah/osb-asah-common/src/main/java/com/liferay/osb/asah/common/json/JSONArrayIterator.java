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

package com.liferay.osb.asah.common.json;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.function.UnsafeFunction;
import com.liferay.osb.asah.common.function.UnsafeRunnable;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
public class JSONArrayIterator {

	public static final String INTERRUPT = "INTERRUPT";

	public static JSONArrayIterator of(
		String collectionName, ElasticsearchInvoker elasticsearchInvoker,
		UnsafeFunction<JSONObject, Object, Exception>
			processJSONObjectUnsafeFunction) {

		return new JSONArrayIterator(
			collectionName, elasticsearchInvoker,
			processJSONObjectUnsafeFunction);
	}

	public void iterate() throws Exception {
		String id = "0";

		if (_trackerJSONObject != null) {
			id = _trackerJSONObject.optString(_trackerIdKey, "0");
		}

		long delta = 0;

		if ((_queueMonitorConsumer != null) && !id.equals("0")) {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"id"
				).lte(
					id
				));

			if (_queryBuilder != null) {
				boolQueryBuilder = boolQueryBuilder.filter(_queryBuilder);
			}

			delta = _elasticsearchInvoker.count(
				_collectionName, boolQueryBuilder);
		}

		int processedCount = 0;

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		if (_queryBuilder != null) {
			searchSourceBuilder.query(_queryBuilder);
		}

		searchSourceBuilder.size(_batchSize);
		searchSourceBuilder.sort("id");

		if ((_sourceExcludes != null) || (_sourceIncludes != null)) {
			searchSourceBuilder.fetchSource(_sourceIncludes, _sourceExcludes);
		}

		while (true) {
			searchSourceBuilder.searchAfter(new Object[] {id});

			SearchResponse searchResponse = _elasticsearchInvoker.search(
				_collectionName, searchSourceBuilder);

			SearchHits searchHits = searchResponse.getHits();

			SearchHit[] hits = searchHits.getHits();

			if (_queueMonitorConsumer != null) {
				long remaining =
					searchHits.getTotalHits() - delta - processedCount;

				_queueMonitorConsumer.accept((int)remaining);
			}

			if (hits.length == 0) {
				return;
			}

			Stream<SearchHit> stream = Arrays.stream(hits);

			JSONArray jsonArray = new JSONArray(
				stream.map(
					searchHit -> new JSONObject(searchHit.getSourceAsString())
				).collect(
					Collectors.toList()
				));

			Object object = null;

			if (_processJSONArrayUnsafeFunction != null) {
				try {
					object = _processJSONArrayUnsafeFunction.apply(jsonArray);
				}
				catch (Exception e) {
					if (_stopOnExceptions) {
						throw e;
					}
					else if (Objects.equals(INTERRUPT, object)) {
						return;
					}

					_log.error(e, e);
				}
			}

			if (_processJSONObjectUnsafeFunction != null) {
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						object = _processJSONObjectUnsafeFunction.apply(
							jsonArray.getJSONObject(i));

						if (object instanceof Exception) {
							throw (Exception)object;
						}
						else if (Objects.equals(INTERRUPT, object)) {
							return;
						}
					}
					catch (Exception e) {
						if (_stopOnExceptions) {
							throw e;
						}

						_log.error(e, e);
					}
				}
			}

			if (_log.isInfoEnabled() || (_queueMonitorConsumer != null)) {
				processedCount += jsonArray.length();
			}

			if (object instanceof ElasticsearchBulkRequestBuilder) {
				ElasticsearchBulkRequestBuilder
					elasticsearchBulkRequestBuilder =
						(ElasticsearchBulkRequestBuilder)object;

				if (elasticsearchBulkRequestBuilder.hasActions()) {
					BulkResponse bulkResponse =
						elasticsearchBulkRequestBuilder.get();

					if (bulkResponse.hasFailures()) {
						if (_log.isInfoEnabled()) {
							_log.error(
								"Unable to process bulk operation " +
									bulkResponse.buildFailureMessage());
						}

						throw new Exception(bulkResponse.buildFailureMessage());
					}

					if (_log.isInfoEnabled()) {
						_log.info(
							"Processed " + processedCount + " " +
								_collectionName);
					}
				}
			}

			JSONObject jsonObject = jsonArray.getJSONObject(
				jsonArray.length() - 1);

			id = jsonObject.getString("id");

			if (_trackerJSONObject != null) {
				_trackerJSONObject.put(_trackerIdKey, id);

				_updateTrackerUnsafeRunnable.run();
			}

			if (_processedCountMonitorConsumer != null) {
				_processedCountMonitorConsumer.accept(jsonArray.length());
			}
		}
	}

	public JSONArrayIterator setBatchSize(int batchSize) {
		_batchSize = batchSize;

		return this;
	}

	public JSONArrayIterator setMonitoringConsumers(
		Consumer<Integer> processedCountMonitorConsumer,
		Consumer<Integer> queueMonitorConsumer) {

		_processedCountMonitorConsumer = processedCountMonitorConsumer;
		_queueMonitorConsumer = queueMonitorConsumer;

		return this;
	}

	public JSONArrayIterator setProcessJSONArrayUnsafeFunction(
		UnsafeFunction<JSONArray, Object, Exception>
			processJSONArrayUnsafeFunction) {

		_processJSONArrayUnsafeFunction = processJSONArrayUnsafeFunction;

		return this;
	}

	public JSONArrayIterator setQueryBuilder(QueryBuilder queryBuilder) {
		_queryBuilder = queryBuilder;

		return this;
	}

	public JSONArrayIterator setSourceExcludes(String sourceExcludes) {
		if (sourceExcludes != null) {
			_sourceExcludes = new String[] {sourceExcludes};
		}

		return this;
	}

	public JSONArrayIterator setSourceExcludes(String[] sourceExcludes) {
		if (sourceExcludes != null) {
			_sourceExcludes = Arrays.copyOf(
				sourceExcludes, sourceExcludes.length);
		}

		return this;
	}

	public JSONArrayIterator setSourceIncludes(String sourceIncludes) {
		if (sourceIncludes != null) {
			_sourceIncludes = new String[] {sourceIncludes};
		}

		return this;
	}

	public JSONArrayIterator setSourceIncludes(String[] sourceIncludes) {
		if (sourceIncludes != null) {
			_sourceIncludes = Arrays.copyOf(
				sourceIncludes, sourceIncludes.length);
		}

		return this;
	}

	public JSONArrayIterator setStopOnExceptions(boolean stopOnExceptions) {
		_stopOnExceptions = stopOnExceptions;

		return this;
	}

	public JSONArrayIterator setTracker(
		JSONObject trackerJSONObject, String trackerIdKey,
		UnsafeRunnable<Exception> updateTrackerUnsafeRunnable) {

		_trackerJSONObject = trackerJSONObject;
		_trackerIdKey = trackerIdKey;
		_updateTrackerUnsafeRunnable = updateTrackerUnsafeRunnable;

		return this;
	}

	private JSONArrayIterator(
		String collectionName, ElasticsearchInvoker elasticsearchInvoker,
		UnsafeFunction<JSONObject, Object, Exception>
			processJSONObjectUnsafeFunction) {

		_collectionName = collectionName;
		_elasticsearchInvoker = elasticsearchInvoker;
		_processJSONObjectUnsafeFunction = processJSONObjectUnsafeFunction;
	}

	private static final Log _log = LogFactory.getLog(JSONArrayIterator.class);

	private int _batchSize = 500;
	private final String _collectionName;
	private final ElasticsearchInvoker _elasticsearchInvoker;
	private Consumer<Integer> _processedCountMonitorConsumer;
	private UnsafeFunction<JSONArray, Object, Exception>
		_processJSONArrayUnsafeFunction;
	private final UnsafeFunction<JSONObject, Object, Exception>
		_processJSONObjectUnsafeFunction;
	private QueryBuilder _queryBuilder;
	private Consumer<Integer> _queueMonitorConsumer;
	private String[] _sourceExcludes;
	private String[] _sourceIncludes;
	private boolean _stopOnExceptions = true;
	private String _trackerIdKey;
	private JSONObject _trackerJSONObject;
	private UnsafeRunnable<Exception> _updateTrackerUnsafeRunnable;

}