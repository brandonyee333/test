/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.PortletConfigFactory;
import com.liferay.portal.kernel.portlet.PortletContextFactory;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.security.lang.DoPrivilegedUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class PortletConfigFactoryImpl implements PortletConfigFactory {

	public PortletConfigFactoryImpl() {
		_pool = new ConcurrentHashMap<>();
	}

	@Override
	public PortletConfig create(
		Portlet portlet, ServletContext servletContext) {

		Map<String, PortletConfig> portletConfigs = _pool.get(
			portlet.getRootPortletId());

		if (portletConfigs == null) {
			portletConfigs = new ConcurrentHashMap<>();

			_pool.put(portlet.getRootPortletId(), portletConfigs);
		}

		PortletConfig portletConfig = null;

		if (portlet.isUndeployedPortlet()) {
			portletConfigs.remove(portlet.getPortletId());
		}
		else {
			portletConfig = portletConfigs.get(portlet.getPortletId());
		}

		if (portletConfig == null) {
			PortletContext portletContext = _portletContextFactory.create(
				portlet, servletContext);

			portletConfig = new PortletConfigImpl(portlet, portletContext);

			portletConfigs.put(portlet.getPortletId(), portletConfig);
		}

		return DoPrivilegedUtil.wrap(portletConfig);
	}

	@Override
	public void destroy(Portlet portlet) {
		_pool.remove(portlet.getRootPortletId());
	}

	@Override
	public PortletConfig get(Portlet portlet) {
		return get(portlet.getPortletId());
	}

	@Override
	public PortletConfig get(String portletId) {
		String rootPortletId = PortletIdCodec.decodePortletName(portletId);

		Map<String, PortletConfig> portletConfigs = _pool.get(rootPortletId);

		if (portletConfigs == null) {
			return null;
		}

		return portletConfigs.get(portletId);
	}

	public void setPortletContextFactory(
		PortletContextFactory portletContextFactory) {

		_portletContextFactory = portletContextFactory;
	}

	@Override
	public PortletConfig update(Portlet portlet) {
		Map<String, PortletConfig> portletConfigs = _pool.get(
			portlet.getRootPortletId());

		if (portletConfigs == null) {
			return null;
		}

		PortletConfig portletConfig = portletConfigs.get(
			portlet.getPortletId());

		PortletContext portletContext = portletConfig.getPortletContext();

		portletConfig = new PortletConfigImpl(portlet, portletContext);

		portletConfigs.put(portlet.getPortletId(), portletConfig);

		return DoPrivilegedUtil.wrap(portletConfig);
	}

	private final Map<String, Map<String, PortletConfig>> _pool;
	private PortletContextFactory _portletContextFactory;

}