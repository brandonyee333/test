/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.facet;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.internal.facet.tag.AssetTagNamesFacet;
import com.liferay.portal.search.test.util.indexing.QueryContributor;
import com.liferay.portal.search.test.util.indexing.QueryContributors;

import java.util.Arrays;
import java.util.List;

/**
 * @author André de Oliveira
 */
public abstract class BaseAssetTagNamesFacetTestCase extends BaseFacetTestCase {

	protected void assertFacet(
			QueryContributor queryContributor, List<String> expectedTerms)
		throws Exception {

		assertFacet(
			searchContext -> initFacet(new AssetTagNamesFacet(searchContext)),
			queryContributor, expectedTerms);
	}

	@Override
	protected String getField() {
		return Field.ASSET_TAG_NAMES;
	}

	protected void testCaseInsensitiveSearchCaseSensitiveGrouping()
		throws Exception {

		addDocument("tag");
		addDocument("tAg");
		addDocument("TAG");
		addDocument(RandomTestUtil.randomString());

		assertFacet(
			QueryContributors.mustMatch(getField(), "tag"),
			Arrays.asList("TAG=1", "tAg=1", "tag=1"));
	}

	protected void testKeysWithSpaces() throws Exception {
		addDocument("Green-Blue Tag");
		addDocument("Green-Blue Tag", "Red Tag");
		addDocument("Tag");

		assertFacet(
			QueryContributors.dummy(),
			Arrays.asList("Green-Blue Tag=2", "Red Tag=1", "Tag=1"));
	}

}