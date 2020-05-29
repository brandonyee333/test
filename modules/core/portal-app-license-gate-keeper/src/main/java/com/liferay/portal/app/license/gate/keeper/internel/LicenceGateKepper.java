/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.app.license.gate.keeper.internel;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.app.license.AppLicenseVerifier;
import com.liferay.portal.app.license.gate.keeper.internel.configuration.LicenseGateKeeperConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Tina Tian
 */
@Component(immediate = true, service = {})
public class LicenceGateKepper {

	@Activate
	protected void activate(ComponentContext componentContext) {
		_licenseGateKeeperConfiguration = ConfigurableUtil.createConfigurable(
			LicenseGateKeeperConfiguration.class,
			componentContext.getProperties());

		_appLicenseVerifierServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				componentContext.getBundleContext(), AppLicenseVerifier.class,
				"version");

		_verifyEnterpriseProducts();
	}

	private Map<String, URL> _getBundles(String dir) {

		// lpkg?

		return Collections.emptyMap();
	}

	private void _installBundles(Collection<URL> URL) {
		// install
		// enable all components
	}

	private void _verifyEnterpriseProducts() {
		AppLicenseVerifier appLicenseVerifier =
			_appLicenseVerifierServiceTrackerMap.getService(
				_licenseGateKeeperConfiguration.licenseVersion());

		for (String enterpriseProduct :
				_licenseGateKeeperConfiguration.enterpriseProducts()) {

			Map<String, String> properties = _parseEnterpriseProduct(
				enterpriseProduct);

			if (appLicenseVerifier != null) {
				Map<String, URL> bundles = _getBundles(properties.get("dir"));

				Set<String> bundleSymbolicNames = bundles.keySet();

				try {
					appLicenseVerifier.verify(
						properties.get("id"),
						properties.get("version"),
						properties.get("type"),
						bundleSymbolicNames.toArray(new String[0]));

					_installBundles(bundles.values());

				}
				catch (Exception exception) {
					// fail
				}
			}
		}
	}

	private Map<String, String> _parseEnterpriseProduct(
		String enterpriseProduct) {

		Map<String, String> properties = new HashMap<>();

		for (String propertyString :
				enterpriseProduct.split(StringPool.COMMA)) {

			int index = propertyString.indexOf(CharPool.EQUAL);

			if (index > 0) {
				properties.put(
					propertyString.substring(0, index),
					propertyString.substring(index + 1));
			}
		}

		return properties;
	}

	@Deactivate
	protected void deactivate() {

		_appLicenseVerifierServiceTrackerMap.close();
	}

	private LicenseGateKeeperConfiguration _licenseGateKeeperConfiguration;

	private ServiceTrackerMap<String, AppLicenseVerifier>
		_appLicenseVerifierServiceTrackerMap;

	private Map<String, Boolean> _enterpriseProducts;

}
