/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.util;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.wsrp.configuration.WSRPGroupServiceConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Peter Fellwock
 */
@Component(
	configurationPid = "com.liferay.wsrp.web.configuration.WSRPConfiguration",
	immediate = true, service = WSRPConfigurationUtil.class
)
public class WSRPConfigurationUtil {

	public static WSRPGroupServiceConfiguration getWSRPConfiguration() {
		return _getInstance()._wsrpGroupServiceConfiguration;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_wsrpGroupServiceConfiguration = ConfigurableUtil.createConfigurable(
			WSRPGroupServiceConfiguration.class, properties);
	}

	private static WSRPConfigurationUtil _getInstance() {
		return _instance;
	}

	private static final WSRPConfigurationUtil _instance =
		new WSRPConfigurationUtil();

	private static volatile WSRPGroupServiceConfiguration
		_wsrpGroupServiceConfiguration;

}