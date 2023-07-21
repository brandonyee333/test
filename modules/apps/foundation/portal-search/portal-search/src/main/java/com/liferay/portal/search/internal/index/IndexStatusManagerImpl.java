/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.index;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.search.IndexStatusManagerThreadLocal;
import com.liferay.portal.search.configuration.IndexStatusManagerConfiguration;
import com.liferay.portal.search.index.IndexStatusManager;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.search.configuration.IndexStatusManagerConfiguration",
	immediate = true, service = IndexStatusManager.class
)
public class IndexStatusManagerImpl implements IndexStatusManager {

	@Override
	public boolean isIndexReadOnly() {
		if (IndexStatusManagerThreadLocal.isIndexReadOnly() || _indexReadOnly) {
			return true;
		}

		return false;
	}

	@Override
	public void setIndexReadOnly(boolean indexReadOnly) {
		_indexReadOnly = indexReadOnly;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		IndexStatusManagerConfiguration indexStatusManagerConfiguration =
			ConfigurableUtil.createConfigurable(
				IndexStatusManagerConfiguration.class, properties);

		_indexReadOnly = indexStatusManagerConfiguration.indexReadOnly();
	}

	private volatile boolean _indexReadOnly;

}