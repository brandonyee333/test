/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.stats;

import com.liferay.portal.kernel.search.Stats;
import com.liferay.portal.kernel.search.StatsResults;

import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.search.aggregations.Aggregation;

/**
 * @author Michael C. Han
 */
public interface StatsTranslator {

	public StatsResults translate(
		Map<String, Aggregation> aggregationMap, Stats stats);

	public void translate(
		SearchRequestBuilder searchRequestBuilder, Stats stats);

}