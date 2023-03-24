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
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Leslie Wong
 */
public class VisitedPagesDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BQSQLResource(resourcePath = "test_visited_pages_dog.sql")
	@Test
	public void testGetVisitedPagesTransformationActivePages() {
		Page<Transformation> visitedPagesTransformationsPage =
			_visitedPagesDog.getVisitedPagesTransformations(
				"interestName eq 'ratio'", "1234567891011",
				"individual-segment", 1, 2,
				new String[] {"uniqueVisitsCount", "desc"}, true);

		Assertions.assertEquals(
			5, visitedPagesTransformationsPage.getTotalElements());
		Assertions.assertEquals(
			3, visitedPagesTransformationsPage.getTotalPages());
		Assertions.assertEquals(1, visitedPagesTransformationsPage.getNumber());

		List<Transformation> transformations = new ArrayList<>();

		transformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Volume");
							put("uniqueVisitsCount", BigDecimal.valueOf(7));
							put(
								"url",
								"https://www.know-your-ratios.com/volume");
						}
					}),
				null));

		transformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Weight");
							put("uniqueVisitsCount", BigDecimal.valueOf(6));
							put(
								"url",
								"https://www.know-your-ratios.com/weight");
						}
					}),
				null));

		_assertTransformations(
			transformations, visitedPagesTransformationsPage.getContent());
	}

	@BQSQLResource(resourcePath = "test_visited_pages_dog.sql")
	@Test
	public void testGetVisitedPagesTransformationInactivePages() {
		Page<Transformation> visitedPagesTransformationsPage =
			_visitedPagesDog.getVisitedPagesTransformations(
				"interestName eq 'ratio'", "1234567891011",
				"individual-segment", 1, 1, new String[] {"title", "desc"},
				false);

		Assertions.assertEquals(
			2, visitedPagesTransformationsPage.getTotalElements());
		Assertions.assertEquals(
			2, visitedPagesTransformationsPage.getTotalPages());
		Assertions.assertEquals(1, visitedPagesTransformationsPage.getNumber());

		_assertTransformations(
			Collections.singletonList(
				new Transformation(
					new Transformation.Term(
						new HashMap<String, Object>() {
							{
								put("title", "Basic Calculator");
								put("uniqueVisitsCount", BigDecimal.valueOf(0));
								put("url", "https://www.calculator.com/basic");
							}
						}),
					null)),
			visitedPagesTransformationsPage.getContent());
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