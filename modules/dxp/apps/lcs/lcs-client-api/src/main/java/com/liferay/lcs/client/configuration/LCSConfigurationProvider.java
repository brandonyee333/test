/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.lcs.client.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.lcs.client.configuration.LCSConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = LCSConfigurationProvider.class
)
public class LCSConfigurationProvider {

	public LCSConfiguration getLCSConfiguration() {
		return _lcsConfiguration;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_lcsConfiguration = ConfigurableUtil.createConfigurable(
			LCSConfiguration.class, properties);
	}

	@Deactivate
	protected void deactivate() {
		_lcsConfiguration = null;
	}

	private volatile LCSConfiguration _lcsConfiguration;

}