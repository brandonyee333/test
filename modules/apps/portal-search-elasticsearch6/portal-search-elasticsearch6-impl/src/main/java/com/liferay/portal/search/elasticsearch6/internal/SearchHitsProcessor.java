/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;

import org.elasticsearch.search.SearchHits;

/**
 * @author Michael C. Han
 */
public interface SearchHitsProcessor {

	public void processSearchHits(
			SearchContext searchContext, SearchHits searchHits)
		throws SearchException;

}