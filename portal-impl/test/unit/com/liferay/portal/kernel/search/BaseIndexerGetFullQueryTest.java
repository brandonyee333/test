/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.test.TestIndexerRegistry;
import com.liferay.portlet.documentlibrary.util.DLFileEntryIndexer;
import com.liferay.portlet.messageboards.util.MBMessageIndexer;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author André de Oliveira
 */
@PrepareOnlyThisForTest(SearchEngineHelperUtil.class)
@RunWith(PowerMockRunner.class)
public class BaseIndexerGetFullQueryTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		setUpJSONFactoryUtil();
		setUpPropsUtil();
		setUpRegistryUtil();
		setUpIndexerRegistry();
		setUpSearchEngineHelperUtil();

		_indexer = new TestIndexer();
	}

	@Test
	public void testGetFullQueryWithAttachments() throws Exception {
		_searchContext.setIncludeAttachments(true);

		_indexer.getFullQuery(_searchContext);

		assertEntryClassNames(_CLASS_NAME, DLFileEntry.class.getName());

		Assert.assertNull(_searchContext.getAttribute("discussion"));
		Assert.assertArrayEquals(
			new String[] {_CLASS_NAME},
			(String[])_searchContext.getAttribute("relatedEntryClassNames"));
	}

	@Test
	public void testGetFullQueryWithAttachmentsAndDiscussions()
		throws Exception {

		_searchContext.setIncludeAttachments(true);
		_searchContext.setIncludeDiscussions(true);

		_indexer.getFullQuery(_searchContext);

		assertEntryClassNames(
			_CLASS_NAME, DLFileEntry.class.getName(),
			MBMessage.class.getName());

		Assert.assertEquals(
			Boolean.TRUE, _searchContext.getAttribute("discussion"));
		Assert.assertArrayEquals(
			new String[] {_CLASS_NAME},
			(String[])_searchContext.getAttribute("relatedEntryClassNames"));
	}

	@Test
	public void testGetFullQueryWithDiscussions() throws Exception {
		_searchContext.setIncludeDiscussions(true);

		_indexer.getFullQuery(_searchContext);

		assertEntryClassNames(_CLASS_NAME, MBMessage.class.getName());

		Assert.assertEquals(
			Boolean.TRUE, _searchContext.getAttribute("discussion"));
		Assert.assertArrayEquals(
			new String[] {_CLASS_NAME},
			(String[])_searchContext.getAttribute("relatedEntryClassNames"));
	}

	@Test
	public void testGetFullQueryWithoutAttachmentsOrDiscussions()
		throws Exception {

		_indexer.getFullQuery(_searchContext);

		assertEntryClassNames(_CLASS_NAME);

		Assert.assertNull(_searchContext.getAttribute("discussion"));
		Assert.assertNull(
			_searchContext.getAttribute("relatedEntryClassNames"));
	}

	protected void assertEntryClassNames(String... expectedEntryClassNames) {
		Arrays.sort(expectedEntryClassNames);

		String[] actualEntryClassNames = _searchContext.getEntryClassNames();

		Arrays.sort(actualEntryClassNames);

		Assert.assertArrayEquals(
			expectedEntryClassNames, actualEntryClassNames);
	}

	protected void setUpIndexerRegistry() {
		Registry registry = RegistryUtil.getRegistry();

		registry.registerService(
			IndexerRegistry.class, new TestIndexerRegistry());
	}

	protected void setUpJSONFactoryUtil() {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		JSONFactory jsonFactory = mock(JSONFactory.class);

		when(
			jsonFactory.createJSONObject()
		).thenReturn(
			mock(JSONObject.class)
		);

		jsonFactoryUtil.setJSONFactory(jsonFactory);
	}

	protected void setUpPropsUtil() {
		PropsTestUtil.setProps(Collections.emptyMap());
	}

	protected void setUpRegistryUtil() throws Exception {
		Registry registry = new BasicRegistryImpl();

		RegistryUtil.setRegistry(registry);

		registry.registerService(Indexer.class, new DLFileEntryIndexer());
		registry.registerService(Indexer.class, new MBMessageIndexer());
	}

	protected void setUpSearchEngineHelperUtil() {
		mockStatic(SearchEngineHelperUtil.class, Mockito.CALLS_REAL_METHODS);

		stub(
			method(SearchEngineHelperUtil.class, "getDefaultSearchEngineId")
		).toReturn(
			SearchEngineHelper.SYSTEM_ENGINE_ID
		);

		stub(
			method(SearchEngineHelperUtil.class, "getEntryClassNames")
		).toReturn(
			new String[0]
		);

		stub(
			method(
				SearchEngineHelperUtil.class, "getSearchEngine", String.class)
		).toReturn(
			new BaseSearchEngine()
		);
	}

	private static final String _CLASS_NAME = RandomTestUtil.randomString();

	private Indexer<Object> _indexer;
	private final SearchContext _searchContext = new SearchContext();

	private static class TestIndexer extends BaseIndexer<Object> {

		@Override
		public String getClassName() {
			return _CLASS_NAME;
		}

		@Override
		protected void doDelete(Object object) throws Exception {
		}

		@Override
		protected Document doGetDocument(Object object) throws Exception {
			return null;
		}

		@Override
		protected Summary doGetSummary(
				Document document, Locale locale, String snippet,
				PortletRequest portletRequest, PortletResponse portletResponse)
			throws Exception {

			return null;
		}

		@Override
		protected void doReindex(Object object) throws Exception {
		}

		@Override
		protected void doReindex(String className, long classPK)
			throws Exception {
		}

		@Override
		protected void doReindex(String[] ids) throws Exception {
		}

	}

}