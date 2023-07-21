/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.auth.verifier.internal.request.parameter.module;

import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.security.auth.verifier.internal.module.BaseAuthVerifierPublisher;
import com.liferay.portal.security.auth.verifier.request.parameter.RequestParameterAuthVerifier;

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tomas Polesovsky
 */
@Component(
	configurationPid = "com.liferay.portal.security.auth.verifier.request.parameter.module.configuration.RequestParameterAuthVerifierConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE
)
public class RequestParameterAuthVerifierPublisher
	extends BaseAuthVerifierPublisher {

	@Activate
	@Override
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		_authVerifier = new RequestParameterAuthVerifier(
			_configurationProvider, _portal, _userLocalService);

		super.activate(bundleContext, properties);
	}

	@Deactivate
	@Override
	protected void deactivate() {
		super.deactivate();
	}

	@Override
	protected AuthVerifier getAuthVerifierInstance() {
		return _authVerifier;
	}

	@Modified
	@Override
	protected void modified(
		BundleContext bundleContext, Map<String, Object> properties) {

		super.modified(bundleContext, properties);
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	@Reference(unbind = "-")
	protected void setPortal(Portal portal) {
		_portal = portal;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private AuthVerifier _authVerifier;
	private ConfigurationProvider _configurationProvider;
	private Portal _portal;
	private UserLocalService _userLocalService;

}