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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.dog.VisitedPagesDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.math.BigDecimal;

import org.json.JSONArray;

import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leslie Wong
 */
public class VisitedPagesDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BQSQLResource(resourcePath = "test_visited_pages_dog.sql")
	@Test
	public void testGetVisitedPagesTransformationsActivePages() {
		JSONAssert.assertEquals(
			JSONUtil.putAll(
				JSONUtil.put(
					"title", "Know Your Ratios - Volume"
				).put(
					"uniqueVisitsCount", BigDecimal.valueOf(7)
				).put(
					"url", "https://www.know-your-ratios.com/volume"
				),
				JSONUtil.put(
					"title", "Know Your Ratios - Weight"
				).put(
					"uniqueVisitsCount", BigDecimal.valueOf(6)
				).put(
					"url", "https://www.know-your-ratios.com/weight"
				)),
			new JSONArray(
				_visitedPagesDog.getVisitedPagesTransformations(
					1L, "interestName eq 'ratio'", "1234567891011",
					"individual-segment", 1, 2,
					new String[] {"uniqueVisitsCount", "desc"}, true)),
			true);
	}

	@BQSQLResource(resourcePath = "test_visited_pages_dog.sql")
	@Test
	public void testGetVisitedPagesTransformationsInactivePages() {
		JSONAssert.assertEquals(
			JSONUtil.put(
				JSONUtil.put(
					"title", "Know Your Ratios - Distance"
				).put(
					"uniqueVisitsCount", BigDecimal.valueOf(0)
				).put(
					"url", "https://www.know-your-ratios.com/distance"
				)),
			new JSONArray(
				_visitedPagesDog.getVisitedPagesTransformations(
					1L, "interestName eq 'ratio'", "3456789101112",
					"individual-segment", 1, 2, new String[] {"title", "desc"},
					false)),
			true);
	}

	@Autowired
	private VisitedPagesDog _visitedPagesDog;

}