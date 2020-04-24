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

package com.liferay.osb.testray.dashboard.web.internal.auto.login;

import com.liferay.osb.testray.dashboard.web.internal.util.TestrayDashboardWebValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 */
@Component(immediate = true, service = AutoLogin.class)
public class TestrayIPAutoLogin extends BaseAutoLogin {

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws AutoLoginException {

		String requestURI = request.getRequestURI();

		if (!requestURI.endsWith(".json")) {
			return null;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Remote address: " + request.getRemoteAddr());
		}

		String[] credentials = getCredentials(_portal.getCompanyId(request));

		if ((credentials == null) || !isValidIP(request.getRemoteAddr())) {
			return null;
		}

		return credentials;
	}

	protected String[] getCredentials(long companyId) {
		if (!_autoLoginCredentialsMap.containsKey(companyId)) {
			try {
				String[] credentials = new String[3];

				User autoLoginUser = _userLocalService.getUserByEmailAddress(
					companyId,
					TestrayDashboardWebValues.AUTO_LOGIN_USER_EMAIL_ADDRESS);

				credentials[0] = String.valueOf(autoLoginUser.getUserId());
				credentials[1] = autoLoginUser.getPassword();
				credentials[2] = String.valueOf(
					autoLoginUser.isPasswordEncrypted());

				_autoLoginCredentialsMap.put(companyId, credentials);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return _autoLoginCredentialsMap.get(companyId);
	}

	protected boolean isValidIP(String remoteAddr) {
		for (String autoLoginIp : TestrayDashboardWebValues.AUTO_LOGIN_IPS) {
			if (remoteAddr.equals(autoLoginIp) ||
				remoteAddr.startsWith(autoLoginIp + ".")) {

				return true;
			}
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TestrayIPAutoLogin.class);

	private final Map<Long, String[]> _autoLoginCredentialsMap =
		Collections.synchronizedMap(new HashMap<Long, String[]>());

	@Reference
	private Portal _portal;

	@Reference
	private volatile UserLocalService _userLocalService;

}