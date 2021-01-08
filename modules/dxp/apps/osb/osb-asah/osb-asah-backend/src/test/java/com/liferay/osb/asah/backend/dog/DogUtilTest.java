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

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.search.aggregations.Aggregations;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author André Miranda
 */
public class DogUtilTest {

	@Test
	public void testEmptyAggregations() {
		Assert.assertTrue(
			DogUtil.isEmpty(new Aggregations(Collections.emptyList())));
	}

	@Test
	public void testLogSearchResponseErrors() {
		Exception exception1 = new Exception("exception1");
		Exception exception2 = new Exception("exception2");

		ShardSearchFailure[] shardSearchFailures = {
			new ShardSearchFailure(exception1),
			new ShardSearchFailure(exception2)
		};

		SearchResponse searchResponse = new SearchResponse();

		ReflectionTestUtils.setField(
			searchResponse, "shardFailures", shardSearchFailures);

		Log log = Mockito.mock(Log.class);

		DogUtil.logSearchResponseErrors(log, searchResponse);

		ArgumentCaptor<Throwable> argumentCaptor = ArgumentCaptor.forClass(
			Throwable.class);

		Mockito.verify(
			log, Mockito.times(2)
		).error(
			argumentCaptor.capture()
		);

		List<Throwable> values = argumentCaptor.getAllValues();

		Assert.assertEquals(exception1, values.get(0));
		Assert.assertEquals(exception2, values.get(1));
	}

	@Test
	public void testNotEmptyAggregations() {
		Assert.assertFalse(
			DogUtil.isEmpty(new Aggregations(Collections.singletonList(null))));
	}

	@Test
	public void testNullAggregations() {
		Assert.assertTrue(DogUtil.isEmpty(null));
	}

}