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

package com.liferay.osb.asah.common.elasticsearch;

import org.apache.lucene.search.TotalHits;

import org.elasticsearch.search.SearchHits;

/**
 * @author Shinn Lok
 */
public class HitsUtil {

	public static long getTotalHitsCount(SearchHits searchHits) {
		TotalHits totalHits = searchHits.getTotalHits();

		if (totalHits == null) {
			return 0;
		}

		return totalHits.value;
	}

	public static boolean hasHits(SearchHits searchHits) {
		long totalHitsCount = getTotalHitsCount(searchHits);

		if (totalHitsCount > 0) {
			return true;
		}

		return false;
	}

}