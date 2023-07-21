/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;

/**
 * @author André de Oliveira
 */
public class FacetsAssert {

	public static void assertFrequencies(
		String facetName, SearchContext searchContext, List<String> expected) {

		Facet facet = searchContext.getFacet(facetName);

		FacetCollector facetCollector = facet.getFacetCollector();

		List<TermCollector> termCollectors = facetCollector.getTermCollectors();

		Assert.assertNotNull(termCollectors);

		Assert.assertEquals(
			(String)searchContext.getAttribute("queryString"),
			expected.toString(),
			_toString(
				termCollectors,
				termCollector ->
					termCollector.getTerm() + "=" +
						termCollector.getFrequency()));
	}

	private static <T> String _toString(
		List<? extends T> list, Function<? super T, String> function) {

		Stream<? extends T> stream = list.stream();

		return stream.map(
			function
		).collect(
			Collectors.toList()
		).toString();
	}

}