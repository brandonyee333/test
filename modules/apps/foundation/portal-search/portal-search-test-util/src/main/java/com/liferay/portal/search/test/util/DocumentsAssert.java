/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util;

import com.liferay.portal.kernel.search.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;

/**
 * @author André de Oliveira
 */
public class DocumentsAssert {

	public static void assertCount(
		String message, Document[] documents, String field, int expectedCount) {

		if (documents.length == expectedCount) {
			return;
		}

		List<String> actualValues = _getValues(field, documents);

		Assert.assertEquals(
			message + "->" + actualValues, expectedCount, documents.length);
	}

	public static void assertValues(
		String message, Document[] documents, String field,
		List<String> expectedValues) {

		List<String> actualValues = _getValues(field, documents);

		Assert.assertEquals(
			message + "->" + actualValues, expectedValues.toString(),
			actualValues.toString());
	}

	public static void assertValuesIgnoreRelevance(
		String message, Document[] documents, String field,
		Collection<String> expectedValues) {

		List<String> actualValues = _getValues(field, documents);

		Assert.assertEquals(
			message + "->" + actualValues, _sort(expectedValues),
			_sort(actualValues));
	}

	private static List<String> _getValues(
		String field, Document... documents) {

		return Stream.of(
			documents
		).map(
			document -> document.get(field)
		).collect(
			Collectors.toList()
		);
	}

	private static String _sort(Collection<String> collection) {
		List<String> list = new ArrayList<>(collection);

		Collections.sort(list);

		return list.toString();
	}

}