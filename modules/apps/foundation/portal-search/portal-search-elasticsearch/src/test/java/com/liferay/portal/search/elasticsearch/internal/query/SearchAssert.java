/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.query;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.search.test.util.IdempotentRetryAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;

import org.junit.Assert;

/**
 * @author André de Oliveira
 */
public class SearchAssert {

	public static void assertNoHits(
			Client client, String field, QueryBuilder queryBuilder)
		throws Exception {

		assertSearch(client, field, queryBuilder, new String[0]);
	}

	public static void assertSearch(
			final Client client, final String field,
			final QueryBuilder queryBuilder, final String... expectedValues)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			10, TimeUnit.SECONDS,
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					Assert.assertEquals(
						sort(Arrays.asList(expectedValues)),
						sort(getValues(search(client, queryBuilder), field)));

					return null;
				}

			});
	}

	protected static List<String> getValues(
		SearchHits searchHits, String field) {

		List<String> values = new ArrayList<>();

		for (SearchHit searchHit : searchHits.hits()) {
			SearchHitField searchHitField = searchHit.field(field);

			String value = searchHitField.value();

			values.add(value);
		}

		return values;
	}

	protected static SearchHits search(
		Client client, QueryBuilder queryBuilder) {

		SearchRequestBuilder searchRequestBuilder = client.prepareSearch();

		searchRequestBuilder.addField(StringPool.STAR);

		searchRequestBuilder.setQuery(queryBuilder);

		SearchResponse searchResponse = searchRequestBuilder.get();

		return searchResponse.getHits();
	}

	protected static String sort(Collection<String> collection) {
		List<String> list = new ArrayList<>(collection);

		Collections.sort(list);

		return list.toString();
	}

}