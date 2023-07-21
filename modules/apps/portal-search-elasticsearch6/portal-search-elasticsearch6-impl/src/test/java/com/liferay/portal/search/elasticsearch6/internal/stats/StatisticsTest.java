/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.stats;

import com.liferay.portal.search.elasticsearch6.internal.ElasticsearchIndexingFixture;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.search.test.util.stats.BaseStatisticsTestCase;

import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class StatisticsTest extends BaseStatisticsTestCase {

	@Override
	@Test
	public void testGetStats() throws Exception {
		super.testGetStats();
	}

	@Override
	protected IndexingFixture createIndexingFixture() {
		return new ElasticsearchIndexingFixture(
			new ElasticsearchFixture(StatisticsTest.class.getSimpleName()),
			BaseIndexingTestCase.COMPANY_ID);
	}

}