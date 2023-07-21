/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.auth.verifier.request.parameter;

import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.security.auto.login.request.parameter.RequestParameterAutoLogin;

import java.util.Properties;

/**
 * @author Tomas Polesovsky
 */
public class RequestParameterAuthVerifier
	extends RequestParameterAutoLogin implements AuthVerifier {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #RequestParameterAuthVerifier(ConfigurationProvider, Portal,
	 *             UserLocalService)}
	 */
	@Deprecated
	public RequestParameterAuthVerifier() {
	}

	public RequestParameterAuthVerifier(
		ConfigurationProvider configurationProvider, Portal portal,
		UserLocalService userLocalService) {

		setConfigurationProvider(configurationProvider);
		setPortal(portal);
		setUserLocalService(userLocalService);
	}

	@Override
	public String getAuthType() {
		Class<?> clazz = getClass();

		return clazz.getSimpleName();
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		try {
			AuthVerifierResult authVerifierResult = new AuthVerifierResult();

			String[] credentials = login(
				accessControlContext.getRequest(),
				accessControlContext.getResponse());

			if (credentials != null) {
				authVerifierResult.setPassword(credentials[1]);
				authVerifierResult.setPasswordBasedAuthentication(true);
				authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
				authVerifierResult.setUserId(Long.valueOf(credentials[0]));
			}

			return authVerifierResult;
		}
		catch (AutoLoginException ale) {
			throw new AuthException(ale);
		}
	}

	@Override
	protected boolean isEnabled(long companyId) {
		return true;
	}

}