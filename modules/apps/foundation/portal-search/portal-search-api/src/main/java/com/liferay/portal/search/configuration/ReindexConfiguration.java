/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.configuration;

import aQute.bnd.annotation.ProviderType;
import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Bryan Engler
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.portal.search.configuration.ReindexConfiguration",
	localization = "content/Language", name = "reindex-configuration-name"
)
@ProviderType
public interface ReindexConfiguration {

	@Meta.AD(
		deflt = "com.liferay.journal.model.JournalArticle=10000",
		description = "indexing-batch-sizes-help",
		name = "indexing-batch-sizes", required = false
	)
	public String[] indexingBatchSizes();

}