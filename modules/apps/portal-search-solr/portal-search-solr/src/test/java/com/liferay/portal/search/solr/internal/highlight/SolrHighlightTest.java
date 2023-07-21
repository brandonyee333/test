/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.highlight;

import com.liferay.portal.search.solr.internal.SolrIndexingFixture;
import com.liferay.portal.search.test.util.highlight.BaseHighlightTestCase;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
public class SolrHighlightTest extends BaseHighlightTestCase {

	@Override
	protected IndexingFixture createIndexingFixture() throws Exception {
		return new SolrIndexingFixture();
	}

}