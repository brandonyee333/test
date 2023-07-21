/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.elasticsearch.internal.connection.ElasticsearchConnection;
import com.liferay.portal.search.elasticsearch.internal.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch.internal.connection.TestElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch.internal.index.CompanyIdIndexNameBuilder;
import com.liferay.portal.search.elasticsearch.internal.index.CompanyIndexFactory;
import com.liferay.portal.search.elasticsearch.internal.index.IndexNameBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class ElasticsearchSearchEngineTest {

	@Before
	public void setUp() throws Exception {
		_elasticsearchFixture = new ElasticsearchFixture(
			ElasticsearchSearchEngineTest.class.getSimpleName());

		_elasticsearchFixture.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Test
	public void testInitializeAfterReconnect() {
		ElasticsearchConnectionManager elasticsearchConnectionManager =
			createElasticsearchConnectionManager();

		ElasticsearchSearchEngine elasticsearchSearchEngine =
			createElasticsearchSearchEngine(elasticsearchConnectionManager);

		long companyId = RandomTestUtil.randomLong();

		elasticsearchSearchEngine.initialize(companyId);

		reconnect(elasticsearchConnectionManager);

		elasticsearchSearchEngine.initialize(companyId);
	}

	protected CompanyIndexFactory createCompanyIndexFactory() {
		return new CompanyIndexFactory() {
			{
				indexNameBuilder = createIndexNameBuilder();
				jsonFactory = new JSONFactoryImpl();
			}
		};
	}

	protected ElasticsearchConnectionManager
		createElasticsearchConnectionManager() {

		return new TestElasticsearchConnectionManager(_elasticsearchFixture);
	}

	protected ElasticsearchSearchEngine createElasticsearchSearchEngine(
		final ElasticsearchConnectionManager elasticsearchConnectionManager2) {

		return new ElasticsearchSearchEngine() {
			{
				elasticsearchConnectionManager =
					elasticsearchConnectionManager2;
				indexFactory = createCompanyIndexFactory();
			}
		};
	}

	protected IndexNameBuilder createIndexNameBuilder() {
		return new CompanyIdIndexNameBuilder() {
			{
				setIndexNamePrefix(null);
			}
		};
	}

	protected void reconnect(
		ElasticsearchConnectionManager elasticsearchConnectionManager) {

		ElasticsearchConnection elasticsearchConnection =
			elasticsearchConnectionManager.getElasticsearchConnection();

		elasticsearchConnection.close();

		elasticsearchConnectionManager.connect();
	}

	private ElasticsearchFixture _elasticsearchFixture;

}