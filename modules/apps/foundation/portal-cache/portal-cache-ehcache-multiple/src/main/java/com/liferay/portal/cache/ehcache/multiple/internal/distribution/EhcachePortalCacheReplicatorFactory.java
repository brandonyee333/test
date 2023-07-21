/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache.multiple.internal.distribution;

import com.liferay.portal.cache.PortalCacheReplicator;
import com.liferay.portal.cache.PortalCacheReplicatorFactory;
import com.liferay.portal.cache.ehcache.multiple.configuration.EhcacheMultipleConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.InstanceFactory;

import java.io.Serializable;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Tina Tian
 */
@Component(
	configurationPid = "com.liferay.portal.cache.ehcache.multiple.configuration.EhcacheMultipleConfiguration",
	immediate = true, service = PortalCacheReplicatorFactory.class
)
public class EhcachePortalCacheReplicatorFactory
	implements PortalCacheReplicatorFactory {

	@Override
	public <K extends Serializable, V extends Serializable>
		PortalCacheReplicator<K, V> create(Properties properties) {

		String factoryClassName =
			_ehcacheMultipleConfiguration.cacheReplicatorFactoryClass();

		try {
			CacheEventListenerFactory cacheEventListenerFactory =
				(CacheEventListenerFactory)InstanceFactory.newInstance(
					getClassLoader(), factoryClassName);

			CacheEventListener cacheEventListener =
				cacheEventListenerFactory.createCacheEventListener(properties);

			return new EhcachePortalCacheReplicatorAdapter<>(
				cacheEventListener);
		}
		catch (Exception e) {
			throw new SystemException(
				"Unable to instantiate cache event listener factory " +
					factoryClassName,
				e);
		}
	}

	@Activate
	@Modified
	protected void activate(ComponentContext componentContext) {
		_ehcacheMultipleConfiguration = ConfigurableUtil.createConfigurable(
			EhcacheMultipleConfiguration.class,
			componentContext.getProperties());
	}

	protected ClassLoader getClassLoader() {
		Class<?> clazz = getClass();

		return clazz.getClassLoader();
	}

	private volatile EhcacheMultipleConfiguration _ehcacheMultipleConfiguration;

}