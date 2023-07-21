/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.highlight;

import com.liferay.portal.search.elasticsearch.internal.ElasticsearchIndexingFixture;
import com.liferay.portal.search.elasticsearch.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.test.util.highlight.BaseHighlightTestCase;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
public class ElasticsearchHighlightTest extends BaseHighlightTestCase {

	@Override
	protected IndexingFixture createIndexingFixture() {
		return new ElasticsearchIndexingFixture(
			new ElasticsearchFixture(
				ElasticsearchHighlightTest.class.getSimpleName()),
			BaseIndexingTestCase.COMPANY_ID);
	}

}