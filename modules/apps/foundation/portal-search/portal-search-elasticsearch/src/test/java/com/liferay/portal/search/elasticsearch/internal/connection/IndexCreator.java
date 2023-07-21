/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.connection;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;

import org.mockito.Mockito;

/**
 * @author André de Oliveira
 */
public class IndexCreator {

	public IndexCreator(IndicesAdminClientSupplier indicesAdminClientSupplier) {
		_indicesAdminClientSupplier = indicesAdminClientSupplier;
	}

	public Index createIndex(IndexName indexName) {
		IndicesAdminClient indicesAdminClient = getIndicesAdminClient();

		String name = indexName.getName();

		DeleteIndexRequestBuilder deleteIndexRequestBuilder =
			indicesAdminClient.prepareDelete(name);

		deleteIndexRequestBuilder.setIndicesOptions(
			IndicesOptions.lenientExpandOpen());

		deleteIndexRequestBuilder.get();

		CreateIndexRequestBuilder createIndexRequestBuilder =
			indicesAdminClient.prepareCreate(name);

		IndexCreationHelper indexCreationHelper = _indexCreationHelper;

		if (indexCreationHelper == null) {
			indexCreationHelper = Mockito.mock(IndexCreationHelper.class);
		}

		indexCreationHelper.contribute(createIndexRequestBuilder);

		Settings.Builder builder = Settings.settingsBuilder();

		indexCreationHelper.contributeIndexSettings(builder);

		createIndexRequestBuilder.setSettings(builder);

		createIndexRequestBuilder.get();

		indexCreationHelper.whenIndexCreated(name);

		return new Index(indexName);
	}

	public void setIndexCreationHelper(
		IndexCreationHelper indexCreationHelper) {

		_indexCreationHelper = indexCreationHelper;
	}

	protected final IndicesAdminClient getIndicesAdminClient() {
		return _indicesAdminClientSupplier.getIndicesAdminClient();
	}

	private IndexCreationHelper _indexCreationHelper;
	private final IndicesAdminClientSupplier _indicesAdminClientSupplier;

}