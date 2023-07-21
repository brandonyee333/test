/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.facet.faceted.searcher.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.search.facet.tag.AssetTagNamesFacetFactory;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Collections;
import java.util.Map;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Andrew Betts
 * @author André de Oliveira
 */
@RunWith(Arquillian.class)
public class AssetTagNamesFacetedSearcherTest
	extends BaseFacetedSearcherTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testSearchByFacet() throws Exception {
		String tag = "enterprise. open-source for life";

		addUser(tag);

		SearchContext searchContext = getSearchContext(tag);

		Facet facet = _assetTagNamesFacetFactory.newInstance(searchContext);

		searchContext.addFacet(facet);

		search(searchContext);

		Map<String, Integer> frequencies = Collections.singletonMap(tag, 1);

		assertFrequencies(facet.getFieldName(), searchContext, frequencies);
	}

	@Test
	public void testSearchQuoted() throws Exception {
		String[] assetTagNames = {"Enterprise", "Open Source", "For   Life"};

		User user = addUser(assetTagNames);

		Map<String, String> expected = userSearchFixture.toMap(
			user, assetTagNames);

		assertTags("\"Enterprise\"", expected);
		assertTags("\"Open\"", expected);
		assertTags("\"Source\"", expected);
		assertTags("\"Open Source\"", expected);
		assertTags("\"For   Life\"", expected);
	}

	protected User addUser(String... assetTagNames) throws Exception {
		Group group = userSearchFixture.addGroup();

		return addUser(group, assetTagNames);
	}

	protected void assertTags(String keywords, Map<String, String> expected)
		throws Exception {

		SearchContext searchContext = getSearchContext(keywords);

		Hits hits = search(searchContext);

		assertTags(keywords, hits, expected);
	}

	@Inject
	private static AssetTagNamesFacetFactory _assetTagNamesFacetFactory;

}