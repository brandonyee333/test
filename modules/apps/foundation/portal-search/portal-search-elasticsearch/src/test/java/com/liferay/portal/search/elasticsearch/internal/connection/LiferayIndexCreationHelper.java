/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.connection;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.search.elasticsearch.internal.index.LiferayDocumentTypeFactory;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.common.settings.Settings;

/**
 * @author André de Oliveira
 */
public class LiferayIndexCreationHelper implements IndexCreationHelper {

	public LiferayIndexCreationHelper(
		IndicesAdminClientSupplier indicesAdminClientSupplier) {

		_indicesAdminClientSupplier = indicesAdminClientSupplier;
	}

	@Override
	public void contribute(
		CreateIndexRequestBuilder createIndexRequestBuilder) {

		LiferayDocumentTypeFactory liferayDocumentTypeFactory =
			getLiferayDocumentTypeFactory();

		liferayDocumentTypeFactory.createRequiredDefaultTypeMappings(
			createIndexRequestBuilder);
	}

	@Override
	public void contributeIndexSettings(Settings.Builder builder) {
		LiferayDocumentTypeFactory liferayDocumentTypeFactory =
			getLiferayDocumentTypeFactory();

		liferayDocumentTypeFactory.createRequiredDefaultAnalyzers(builder);
	}

	@Override
	public void whenIndexCreated(String indexName) {
		LiferayDocumentTypeFactory liferayDocumentTypeFactory =
			getLiferayDocumentTypeFactory();

		liferayDocumentTypeFactory.createOptionalDefaultTypeMappings(indexName);
	}

	protected LiferayDocumentTypeFactory getLiferayDocumentTypeFactory() {
		return new LiferayDocumentTypeFactory(
			_indicesAdminClientSupplier.getIndicesAdminClient(),
			new JSONFactoryImpl());
	}

	private final IndicesAdminClientSupplier _indicesAdminClientSupplier;

}