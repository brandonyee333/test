/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.indexing;

import java.util.Date;

/**
 * @author André de Oliveira
 */
public class DocumentCreationHelpers {

	public static DocumentCreationHelper singleDate(
		String fieldName, Date value) {

		return document -> document.addDate(fieldName, value);
	}

	public static DocumentCreationHelper singleGeoLocation(
		String fieldName, double latitude, double longitude) {

		return document -> document.addGeoLocation(
			fieldName, latitude, longitude);
	}

	public static DocumentCreationHelper singleKeyword(
		String fieldName, String value) {

		return document -> document.addKeyword(fieldName, value);
	}

	public static DocumentCreationHelper singleNumber(
		String fieldName, double value) {

		return document -> document.addNumber(fieldName, value);
	}

	public static DocumentCreationHelper singleNumberSortable(
		String fieldName, double value) {

		return document -> document.addNumberSortable(fieldName, value);
	}

	public static DocumentCreationHelper singleText(
		String fieldName, String... values) {

		return document -> document.addText(fieldName, values);
	}

	public static DocumentCreationHelper twoKeywords(
		String fieldName1, String value1, String fieldName2, String value2) {

		return document -> {
			document.addKeyword(fieldName1, value1);
			document.addKeyword(fieldName2, value2);
		};
	}

}