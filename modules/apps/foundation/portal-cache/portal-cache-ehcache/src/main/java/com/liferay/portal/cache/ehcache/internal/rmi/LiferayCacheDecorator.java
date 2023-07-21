/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache.internal.rmi;

import com.liferay.portal.kernel.cache.SkipReplicationThreadLocal;

import java.io.Serializable;

import java.util.Collection;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.EhcacheDecoratorAdapter;

/**
 * @author Tina Tian
 */
public class LiferayCacheDecorator extends EhcacheDecoratorAdapter {

	public LiferayCacheDecorator(Ehcache underlyingCache) {
		super(underlyingCache);
	}

	@Override
	public boolean equals(Object object) {
		return underlyingCache.equals(object);
	}

	public Ehcache getUnderlyingCache() {
		return underlyingCache;
	}

	@Override
	public int hashCode() {
		return underlyingCache.hashCode();
	}

	@Override
	public void put(Element element, boolean doNotNotifyCacheReplicators) {
		boolean enabled = SkipReplicationThreadLocal.isEnabled();

		SkipReplicationThreadLocal.setEnabled(doNotNotifyCacheReplicators);

		try {
			super.put(element, doNotNotifyCacheReplicators);
		}
		finally {
			SkipReplicationThreadLocal.setEnabled(enabled);
		}
	}

	@Override
	public Element putIfAbsent(
		Element element, boolean doNotNotifyCacheReplicators) {

		boolean enabled = SkipReplicationThreadLocal.isEnabled();

		SkipReplicationThreadLocal.setEnabled(doNotNotifyCacheReplicators);

		try {
			return super.putIfAbsent(element, doNotNotifyCacheReplicators);
		}
		finally {
			SkipReplicationThreadLocal.setEnabled(enabled);
		}
	}

	@Override
	public boolean remove(Object key, boolean doNotNotifyCacheReplicators) {
		boolean enabled = SkipReplicationThreadLocal.isEnabled();

		SkipReplicationThreadLocal.setEnabled(doNotNotifyCacheReplicators);

		try {
			return super.remove(key, doNotNotifyCacheReplicators);
		}
		finally {
			SkipReplicationThreadLocal.setEnabled(enabled);
		}
	}

	@Override
	public boolean remove(
		Serializable key, boolean doNotNotifyCacheReplicators) {

		return remove((Object)key, doNotNotifyCacheReplicators);
	}

	@Override
	public void removeAll(boolean doNotNotifyCacheReplicators) {
		boolean enabled = SkipReplicationThreadLocal.isEnabled();

		SkipReplicationThreadLocal.setEnabled(doNotNotifyCacheReplicators);

		try {
			super.removeAll(doNotNotifyCacheReplicators);
		}
		finally {
			SkipReplicationThreadLocal.setEnabled(enabled);
		}
	}

	@Override
	public void removeAll(
		Collection<?> keys, boolean doNotNotifyCacheReplicators) {

		boolean enabled = SkipReplicationThreadLocal.isEnabled();

		SkipReplicationThreadLocal.setEnabled(doNotNotifyCacheReplicators);

		try {
			super.removeAll(keys, doNotNotifyCacheReplicators);
		}
		finally {
			SkipReplicationThreadLocal.setEnabled(enabled);
		}
	}

}