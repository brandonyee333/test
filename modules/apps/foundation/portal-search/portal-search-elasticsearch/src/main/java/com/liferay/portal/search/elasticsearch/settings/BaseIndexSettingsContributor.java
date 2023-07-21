/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.settings;

/**
 * @author André de Oliveira
 */
public class BaseIndexSettingsContributor implements IndexSettingsContributor {

	public BaseIndexSettingsContributor(int priority) {
		_priority = priority;
	}

	@Override
	public int compareTo(IndexSettingsContributor indexSettingsContributor) {
		if (_priority > indexSettingsContributor.getPriority()) {
			return 1;
		}
		else if (_priority == indexSettingsContributor.getPriority()) {
			return 0;
		}

		return -1;
	}

	@Override
	public void contribute(
		String indexName, TypeMappingsHelper typeMappingsHelper) {
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link #contribute(String,
	 *             TypeMappingsHelper)}
	 */
	@Deprecated
	@Override
	public void contribute(TypeMappingsHelper typeMappingsHelper) {
	}

	@Override
	public int getPriority() {
		return _priority;
	}

	@Override
	public void populate(IndexSettingsHelper indexSettingsHelper) {
	}

	private final int _priority;

}