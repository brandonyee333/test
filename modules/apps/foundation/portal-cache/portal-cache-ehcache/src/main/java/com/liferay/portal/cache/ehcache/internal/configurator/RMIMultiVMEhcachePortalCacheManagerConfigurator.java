/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache.internal.configurator;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.cache.configuration.PortalCacheManagerConfiguration;
import com.liferay.portal.kernel.util.PropsKeys;

import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.FactoryConfiguration;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Tina Tian
 */
@Component(
	enabled = false, immediate = true,
	service = MultiVMEhcachePortalCacheManagerConfigurator.class
)
public class RMIMultiVMEhcachePortalCacheManagerConfigurator
	extends MultiVMEhcachePortalCacheManagerConfigurator {

	@Activate
	@Override
	protected void activate() {
		super.activate();

		if (!clusterEnabled) {
			return;
		}

		_peerListenerFactoryClass = props.get(
			PropsKeys.EHCACHE_RMI_PEER_LISTENER_FACTORY_CLASS);
		_peerListenerFactoryPropertiesString = getPortalPropertiesString(
			PropsKeys.EHCACHE_RMI_PEER_LISTENER_FACTORY_PROPERTIES);
		_peerProviderFactoryClass = props.get(
			PropsKeys.EHCACHE_RMI_PEER_PROVIDER_FACTORY_CLASS);
		_peerProviderFactoryPropertiesString = getPortalPropertiesString(
			PropsKeys.EHCACHE_RMI_PEER_PROVIDER_FACTORY_PROPERTIES);
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected void manageConfiguration(
		Configuration configuration,
		PortalCacheManagerConfiguration portalCacheManagerConfiguration) {

		if (!clusterEnabled) {
			return;
		}

		super.manageConfiguration(
			configuration, portalCacheManagerConfiguration);

		FactoryConfiguration peerProviderFactoryConfiguration =
			new FactoryConfiguration();

		peerProviderFactoryConfiguration.setClass(_peerProviderFactoryClass);
		peerProviderFactoryConfiguration.setProperties(
			_peerProviderFactoryPropertiesString);
		peerProviderFactoryConfiguration.setPropertySeparator(StringPool.COMMA);

		configuration.addCacheManagerPeerProviderFactory(
			peerProviderFactoryConfiguration);

		FactoryConfiguration peerListenerFacotryConfiguration =
			new FactoryConfiguration();

		peerListenerFacotryConfiguration.setClass(_peerListenerFactoryClass);
		peerListenerFacotryConfiguration.setProperties(
			_peerListenerFactoryPropertiesString);
		peerListenerFacotryConfiguration.setPropertySeparator(StringPool.COMMA);

		configuration.addCacheManagerPeerListenerFactory(
			peerListenerFacotryConfiguration);
	}

	private String _peerListenerFactoryClass;
	private String _peerListenerFactoryPropertiesString;
	private String _peerProviderFactoryClass;
	private String _peerProviderFactoryPropertiesString;

}