/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache.multiple.internal.distribution;

import com.liferay.portal.cache.PortalCacheReplicator;
import com.liferay.portal.cache.ehcache.event.EhcachePortalCacheListenerAdapter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Field;

import net.sf.ehcache.distribution.RMIAsynchronousCacheReplicator;
import net.sf.ehcache.event.CacheEventListener;

/**
 * @author Tina Tian
 */
public class EhcachePortalCacheReplicatorAdapter
	<K extends Serializable, V extends Serializable>
		extends EhcachePortalCacheListenerAdapter<K, V>
		implements PortalCacheReplicator<K, V> {

	public EhcachePortalCacheReplicatorAdapter(
		CacheEventListener cacheEventListener) {

		super(cacheEventListener);
	}

	@Override
	public void dispose() {
		super.dispose();

		if (!(cacheEventListener instanceof RMIAsynchronousCacheReplicator)) {
			return;
		}

		RMIAsynchronousCacheReplicator rmiAsynchronousCacheReplicator =
			(RMIAsynchronousCacheReplicator)cacheEventListener;

		try {
			Thread replicationThread = (Thread)_REPLICATION_THREAD_FIELD.get(
				rmiAsynchronousCacheReplicator);

			replicationThread.interrupt();

			replicationThread.join(_WAIT_TIME);

			if (replicationThread.isAlive() && _log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Give up waiting on thread ",
						String.valueOf(replicationThread),
						" after waiting for ", String.valueOf(_WAIT_TIME),
						"ms"));
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to dispose cache event listener " +
						cacheEventListener,
					e);
			}
		}
	}

	private static final Field _REPLICATION_THREAD_FIELD;

	private static final long _WAIT_TIME = 1000;

	private static final Log _log = LogFactoryUtil.getLog(
		EhcachePortalCacheReplicatorAdapter.class);

	static {
		try {
			_REPLICATION_THREAD_FIELD = ReflectionUtil.getDeclaredField(
				RMIAsynchronousCacheReplicator.class, "replicationThread");
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

}