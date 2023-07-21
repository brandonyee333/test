/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.servlet;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.restful.servlet.SimpleRestfulServlet;
import com.liferay.osb.customer.restful.servlet.exception.NoResourceException;
import com.liferay.osb.customer.zendesk.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.http.HttpAuthManager;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
public abstract class ZendeskBaseServlet extends SimpleRestfulServlet {

	protected JSONObject getRequestJSONObject(HttpServletRequest request)
		throws PortalException {

		StringBundler sb = new StringBundler();

		String line = null;

		try {
			BufferedReader bufferedReader = request.getReader();

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}

			bufferedReader.close();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		return JSONFactoryUtil.createJSONObject(sb.toString());
	}

	@Override
	protected String getResourceKey(HttpServletRequest request) {
		return null;
	}

	@Override
	protected String getResourceName(HttpServletRequest request)
		throws NoResourceException {

		String pathInfo = http.fixPath(request.getPathInfo());

		if (Validator.isNull(pathInfo)) {
			throw new NoResourceException();
		}

		return pathInfo;
	}

	@Override
	protected boolean isAuthorized(HttpServletRequest request) {
		if (!request.isSecure()) {
			return false;
		}

		try {
			long basicUserId = httpAuthManager.getBasicUserId(request);

			long userId = userLocalService.getUserIdByEmailAddress(
				OSBCustomerConstants.COMPANY_ID,
				ZendeskConnectorConfigurationValues.ZENDESK_AUTH_USER_EMAIL);

			if (basicUserId != userId) {
				return false;
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe, pe);
			}

			return false;
		}

		return true;
	}

	@Reference
	protected Http http;

	@Reference
	protected HttpAuthManager httpAuthManager;

	@Reference
	protected UserLocalService userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskBaseServlet.class);

}