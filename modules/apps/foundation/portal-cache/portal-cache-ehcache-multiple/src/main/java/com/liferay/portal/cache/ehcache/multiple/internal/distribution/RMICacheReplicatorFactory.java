/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache.multiple.internal.distribution;

import com.liferay.portal.cache.PortalCacheReplicator;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Properties;

/**
 * @author Tina Tian
 */
public class RMICacheReplicatorFactory
	extends net.sf.ehcache.distribution.RMICacheReplicatorFactory {

	@Override
	protected boolean extractReplicatePuts(Properties properties) {
		return GetterUtil.getBoolean(
			properties.getProperty(PortalCacheReplicator.REPLICATE_PUTS),
			PortalCacheReplicator.DEFAULT_REPLICATE_PUTS);
	}

	@Override
	protected boolean extractReplicatePutsViaCopy(Properties properties) {
		return GetterUtil.getBoolean(
			properties.getProperty(
				PortalCacheReplicator.REPLICATE_PUTS_VIA_COPY),
			PortalCacheReplicator.DEFAULT_REPLICATE_PUTS_VIA_COPY);
	}

	@Override
	protected boolean extractReplicateRemovals(Properties properties) {
		return GetterUtil.getBoolean(
			properties.getProperty(PortalCacheReplicator.REPLICATE_REMOVALS),
			PortalCacheReplicator.DEFAULT_REPLICATE_REMOVALS);
	}

	@Override
	protected boolean extractReplicateUpdates(Properties properties) {
		return GetterUtil.getBoolean(
			properties.getProperty(PortalCacheReplicator.REPLICATE_UPDATES),
			PortalCacheReplicator.DEFAULT_REPLICATE_UPDATES);
	}

	@Override
	protected boolean extractReplicateUpdatesViaCopy(Properties properties) {
		return GetterUtil.getBoolean(
			properties.getProperty(
				PortalCacheReplicator.REPLICATE_UPDATES_VIA_COPY),
			PortalCacheReplicator.DEFAULT_REPLICATE_UPDATES_VIA_COPY);
	}

}