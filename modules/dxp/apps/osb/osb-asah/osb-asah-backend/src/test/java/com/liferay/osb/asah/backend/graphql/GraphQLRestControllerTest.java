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

package com.liferay.osb.asah.backend.graphql;

import com.liferay.osb.asah.common.spring.resource.ResourceUtil;

import java.time.LocalDate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Leslie Wong
 */
public class GraphQLRestControllerTest {

	@Test
	public void testSkipCacheWithCustomRange1() throws Exception {
		String query = ResourceUtil.readResourceToString(
			"cacheable_query.graphql", this);

		LocalDate rangeEndLocalDate = LocalDate.now();

		LocalDate rangeStartLocalDate = rangeEndLocalDate.minusDays(3);

		Map<String, Object> variables = new HashMap<String, Object>() {
			{
				put("rangeEnd", rangeEndLocalDate.toString());
				put("rangeStart", rangeStartLocalDate.toString());
			}
		};

		Assert.assertTrue(GraphQLRestController.skipCache(query, variables));
	}

	@Test
	public void testSkipCacheWithCustomRange2() throws Exception {
		String query = ResourceUtil.readResourceToString(
			"cacheable_query.graphql", this);

		LocalDate localDate = LocalDate.now();

		LocalDate rangeEndLocalDate = localDate.minusDays(7);

		LocalDate rangeStartLocalDate = rangeEndLocalDate.minusDays(3);

		Map<String, Object> variables = new HashMap<String, Object>() {
			{
				put("rangeEnd", rangeEndLocalDate.toString());
				put("rangeStart", rangeStartLocalDate.toString());
			}
		};

		Assert.assertFalse(GraphQLRestController.skipCache(query, variables));
	}

	@Test
	public void testSkipCacheWithNoncacheableQuery() throws Exception {
		String query = ResourceUtil.readResourceToString(
			"noncacheable_query.graphql", this);

		Assert.assertTrue(GraphQLRestController.skipCache(query, null));
	}

	@Test
	public void testSkipCacheWithRangeKey() throws Exception {
		String query = ResourceUtil.readResourceToString(
			"cacheable_query.graphql", this);

		Assert.assertTrue(
			GraphQLRestController.skipCache(
				query, Collections.singletonMap("rangeKey", 0)));
		Assert.assertFalse(
			GraphQLRestController.skipCache(
				query, Collections.singletonMap("rangeKey", 1)));
		Assert.assertFalse(
			GraphQLRestController.skipCache(
				query, Collections.singletonMap("rangeKey", 7)));
		Assert.assertFalse(
			GraphQLRestController.skipCache(
				query, Collections.singletonMap("rangeKey", 28)));
		Assert.assertFalse(
			GraphQLRestController.skipCache(
				query, Collections.singletonMap("rangeKey", 30)));
		Assert.assertFalse(
			GraphQLRestController.skipCache(
				query, Collections.singletonMap("rangeKey", 90)));
		Assert.assertFalse(
			GraphQLRestController.skipCache(
				query, Collections.singletonMap("rangeKey", 180)));
		Assert.assertFalse(
			GraphQLRestController.skipCache(
				query, Collections.singletonMap("rangeKey", 365)));
	}

}