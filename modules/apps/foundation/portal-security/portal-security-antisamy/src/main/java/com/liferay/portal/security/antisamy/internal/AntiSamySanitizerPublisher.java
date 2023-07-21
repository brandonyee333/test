/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.antisamy.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.security.antisamy.configuration.AntiSamyConfiguration;

import java.net.URL;

import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Tomas Polesovsky
 */
@Component(
	configurationPid = "com.liferay.portal.security.antisamy.configuration.AntiSamyConfiguration",
	immediate = true
)
public class AntiSamySanitizerPublisher {

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		AntiSamyConfiguration antiSamyConfiguration =
			ConfigurableUtil.createConfigurable(
				AntiSamyConfiguration.class, properties);

		if (!antiSamyConfiguration.enabled()) {
			return;
		}

		Bundle bundle = bundleContext.getBundle();

		URL url = bundle.getResource(
			antiSamyConfiguration.configurationFileURL());

		if (url == null) {
			throw new IllegalStateException(
				"Unable to find " +
					antiSamyConfiguration.configurationFileURL());
		}

		Sanitizer sanitizer = new AntiSamySanitizerImpl(
			antiSamyConfiguration.blacklist(), url,
			antiSamyConfiguration.whitelist());

		_sanitizerServiceRegistration = bundleContext.registerService(
			Sanitizer.class, sanitizer, null);
	}

	@Deactivate
	protected void deactivate() {
		if (_sanitizerServiceRegistration != null) {
			_sanitizerServiceRegistration.unregister();

			_sanitizerServiceRegistration = null;
		}
	}

	@Modified
	protected void modified(
		BundleContext bundleContext, Map<String, Object> properties) {

		deactivate();

		activate(bundleContext, properties);
	}

	private ServiceRegistration<Sanitizer> _sanitizerServiceRegistration;

}