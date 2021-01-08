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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class DataDog {

	public SearchResponse query(
		String collectionName, ElasticsearchInvoker elasticsearchInvoker,
		SearchSourceBuilder searchSourceBuilder) {

		long start = System.currentTimeMillis();

		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Executing query \"" + searchSourceBuilder + "\" on " +
						"collection " + collectionName);
			}

			SearchResponse searchResponse = elasticsearchInvoker.search(
				collectionName, searchSourceBuilder);

			if (_log.isDebugEnabled()) {
				_log.debug("Response body: " + searchResponse);
			}

			return searchResponse;
		}
		catch (Exception e) {
			throw new RuntimeException("Unable to process search request", e);
		}
		finally {
			long elapsedTime = System.currentTimeMillis() - start;

			if (elapsedTime > _queryResponseThreshold) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						String.format(
							"Query execution exceeded %d ms threshold and " +
								"took: %d ms, query: %s",
							_queryResponseThreshold, elapsedTime,
							searchSourceBuilder.query()));
				}
			}
			else if (_log.isDebugEnabled()) {
				_log.debug(
					String.format("Query execution took: %d ms", elapsedTime));
			}
		}
	}

	public Aggregations queryAggregations(
		String collectionName, SearchSourceBuilder searchSourceBuilder) {

		SearchResponse searchResponse = query(
			collectionName, _elasticsearchInvokerFactory.forCerebroInfo(),
			searchSourceBuilder);

		if (searchResponse.status() != RestStatus.OK) {
			DogUtil.logSearchResponseErrors(_log, searchResponse);

			return null;
		}

		return searchResponse.getAggregations();
	}

	public SearchHits querySearchHits(
		String collectionName, ElasticsearchInvoker elasticsearchInvoker,
		SearchSourceBuilder searchSourceBuilder) {

		SearchResponse searchResponse = query(
			collectionName, elasticsearchInvoker, searchSourceBuilder);

		if (searchResponse.status() != RestStatus.OK) {
			DogUtil.logSearchResponseErrors(_log, searchResponse);

			return SearchHits.empty();
		}

		return searchResponse.getHits();
	}

	private static final Log _log = LogFactory.getLog(DataDog.class);

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Value("${osb.asah.data.dog.query.response.threshold:10000}")
	private int _queryResponseThreshold;

}