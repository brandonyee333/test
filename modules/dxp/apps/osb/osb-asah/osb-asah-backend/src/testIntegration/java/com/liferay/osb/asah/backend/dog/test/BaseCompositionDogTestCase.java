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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.model.Composition;
import com.liferay.osb.asah.backend.model.CompositionResultBag;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;

/**
 * @author Shinn Lok
 */
public abstract class BaseCompositionDogTestCase {

	protected void checkResults(
		CompositionResultBag compositionResultBag,
		Map<String, Long> expectedResults, long expectedMaxCount,
		long expectedTotal, long expectedTotalCount) {

		List<Composition> results = compositionResultBag.getResults();

		Stream<Composition> stream = results.stream();

		Assert.assertEquals(
			expectedResults,
			stream.collect(
				Collectors.toMap(
					Composition::getName, Composition::getCount,
					(name, count) -> name, LinkedHashMap::new)));

		Assert.assertEquals(
			expectedMaxCount, compositionResultBag.getMaxCount());
		Assert.assertEquals(expectedTotal, compositionResultBag.getTotal());
		Assert.assertEquals(
			expectedTotalCount, compositionResultBag.getTotalCount());
	}

}