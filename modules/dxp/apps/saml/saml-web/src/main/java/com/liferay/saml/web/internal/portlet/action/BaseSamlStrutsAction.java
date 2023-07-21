/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.saml.runtime.configuration.SamlProviderConfigurationHelper;
import com.liferay.saml.runtime.exception.StatusException;
import com.liferay.saml.util.JspUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 */
public abstract class BaseSamlStrutsAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (!isEnabled()) {
			return "/common/referer_js.jsp";
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			Class<? extends BaseSamlStrutsAction> clazz = getClass();

			currentThread.setContextClassLoader(clazz.getClassLoader());

			return doExecute(request, response);
		}
		catch (Exception e) {
			_log.error(e, e);

			Class<?> clazz = e.getClass();

			SessionErrors.add(request, clazz.getName());

			if (e instanceof StatusException) {
				StatusException statusException = (StatusException)e;

				SessionErrors.add(
					request, "statusCodeURI", statusException.getMessage());
			}

			JspUtil.dispatch(
				request, response, JspUtil.PATH_PORTAL_SAML_ERROR, "status");
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return null;
	}

	public boolean isEnabled() {
		return samlProviderConfigurationHelper.isEnabled();
	}

	public void setSamlProviderConfigurationHelper(
		SamlProviderConfigurationHelper samlProviderConfigurationHelper) {

		this.samlProviderConfigurationHelper = samlProviderConfigurationHelper;
	}

	protected abstract String doExecute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception;

	protected SamlProviderConfigurationHelper samlProviderConfigurationHelper;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseSamlStrutsAction.class);

}