/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.settings;

import aQute.bnd.annotation.ProviderType;

/**
 * @author André de Oliveira
 */
@ProviderType
public interface IndexSettingsContributor
	extends Comparable<IndexSettingsContributor> {

	public void contribute(
		String indexName, TypeMappingsHelper typeMappingsHelper);

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link #contribute(String,
	 *             TypeMappingsHelper)}
	 */
	@Deprecated
	public void contribute(TypeMappingsHelper typeMappingsHelper);

	public int getPriority();

	public void populate(IndexSettingsHelper indexSettingsHelper);

}