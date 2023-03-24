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
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.math.BigDecimal;

import java.util.List;

import org.json.JSONArray;

import org.junit.jupiter.api.Assertions;
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
	public void testGetVisitedPagesTransformationActivePages() {
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
					"interestName eq 'ratio'", "1234567891011",
					"individual-segment", 1, 2,
					new String[] {"uniqueVisitsCount", "desc"}, true)),
			true);
	}

	@BQSQLResource(resourcePath = "test_visited_pages_dog.sql")
	@Test
	public void testGetVisitedPagesTransformationInactivePages() {
		JSONAssert.assertEquals(
			JSONUtil.put(
				JSONUtil.put(
					"title", "Basic Calculator"
				).put(
					"uniqueVisitsCount", BigDecimal.valueOf(0)
				).put(
					"url", "https://www.calculator.com/basic"
				)),
			new JSONArray(
				_visitedPagesDog.getVisitedPagesTransformations(
					"interestName eq 'ratio'", "1234567891011",
					"individual-segment", 1, 1, new String[] {"title", "desc"},
					false)),
			true);
	}

	private void _assertTransformations(
		List<Transformation> actualTransformations,
		List<Transformation> expectedTransformations) {

		Assertions.assertEquals(
			expectedTransformations.size(), actualTransformations.size());

		for (int i = 0; i < expectedTransformations.size(); i++) {
			Assertions.assertEquals(
				expectedTransformations.get(i), actualTransformations.get(i));
		}
	}

	@Autowired
	private VisitedPagesDog _visitedPagesDog;

}