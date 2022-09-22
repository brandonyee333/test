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

package com.liferay.frontend.js.spa.web.internal.servlet.taglib.util;

import com.liferay.frontend.js.spa.web.configuration.SPAConfiguration;
import com.liferay.frontend.js.spa.web.configuration.SPAConfigurationActivator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.ServletResponseConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.lang.reflect.Field;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Bruno Basto
 */
@Component(
	configurationPid = "com.liferay.frontend.js.spa.web.configuration.SPAConfiguration",
	service = SPAUtil.class
)
public class SPAUtil {

	public long getCacheExpirationTime(long companyId) {
		long cacheExpirationTime = 0;

		SPAConfiguration spaConfiguration =
			_spaConfigurationActivator.getSPAConfiguration();

		cacheExpirationTime = GetterUtil.getLong(
			spaConfiguration.cacheExpirationTime(), cacheExpirationTime);

		if (cacheExpirationTime > 0) {
			cacheExpirationTime *= Time.MINUTE;
		}

		return cacheExpirationTime;
	}

	public String getExcludedPaths() {
		SPAConfiguration spaConfiguration =
			_spaConfigurationActivator.getSPAConfiguration();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (String excludedPath : _SPA_DEFAULT_EXCLUDED_PATHS) {
			jsonArray.put(_portal.getPathContext() + excludedPath);
		}

		String[] customExcludedPaths = spaConfiguration.customExcludedPaths();

		if (ArrayUtil.isEmpty(customExcludedPaths)) {
			return jsonArray.toString();
		}

		for (String customExcludedPath : customExcludedPaths) {
			jsonArray.put(_portal.getPathContext() + customExcludedPath);
		}

		return jsonArray.toString();
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	public ResourceBundle getLanguageResourceBundle(Locale locale) {
		return ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());
	}

	public ResourceBundle getLanguageResourceBundle(
		String servletContextName, Locale locale) {

		ResourceBundleLoader resourceBundleLoader =
			ResourceBundleLoaderUtil.
				getResourceBundleLoaderByServletContextName(servletContextName);

		return resourceBundleLoader.loadResourceBundle(locale);
	}

	public String getLoginRedirect(HttpServletRequest request) {
		return ParamUtil.getString(request, _REDIRECT_PARAM_NAME);
	}

	public String getPortletsBlacklist(ThemeDisplay themeDisplay) {
		StringBundler sb = new StringBundler();

		sb.append(StringPool.OPEN_CURLY_BRACE);

		_portletLocalService.visitPortlets(
			themeDisplay.getCompanyId(),
			portlet -> {
				if (!portlet.isSinglePageApplication() &&
					!portlet.isUndeployedPortlet() && portlet.isActive() &&
					portlet.isReady()) {

					sb.append(StringPool.QUOTE);
					sb.append(portlet.getPortletId());
					sb.append("\":true,");
				}
			});

		if (sb.index() == 1) {
			sb.append(StringPool.CLOSE_CURLY_BRACE);
		}
		else {
			sb.setIndex(sb.index() - 1);

			sb.append("\":true}");
		}

		return sb.toString();
	}

	public int getRequestTimeout() {
		int requestTimeout = 0;

		SPAConfiguration spaConfiguration =
			_spaConfigurationActivator.getSPAConfiguration();

		return GetterUtil.getInteger(
			spaConfiguration.requestTimeout(), requestTimeout);
	}

	public int getUserNotificationTimeout() {
		int userNotificationTimeout = 0;

		SPAConfiguration spaConfiguration =
			_spaConfigurationActivator.getSPAConfiguration();

		return GetterUtil.getInteger(
			spaConfiguration.userNotificationTimeout(),
			userNotificationTimeout);
	}

	public String getValidStatusCodes() {
		return _VALID_STATUS_CODES;
	}

	public boolean isClearScreensCache(
		HttpServletRequest request, HttpSession session) {

		boolean singlePageApplicationClearCache = GetterUtil.getBoolean(
			request.getAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE));

		if (singlePageApplicationClearCache) {
			return true;
		}

		String portletId = request.getParameter("p_p_id");

		if (Validator.isNull(portletId)) {
			return false;
		}

		String singlePageApplicationLastPortletId =
			(String)session.getAttribute(
				WebKeys.SINGLE_PAGE_APPLICATION_LAST_PORTLET_ID);

		if (Validator.isNotNull(singlePageApplicationLastPortletId) &&
			!Objects.equals(portletId, singlePageApplicationLastPortletId)) {

			return true;
		}

		return false;
	}

	public boolean isDisabled(HttpServletRequest httpServletRequest) {
		SPAConfiguration spaConfiguration =
			_spaConfigurationActivator.getSPAConfiguration();

		if (BrowserSnifferUtil.isIe(httpServletRequest)) {
			if (spaConfiguration.disableInInternetExplorer()) {
				return true;
			}

			double majorVersion = BrowserSnifferUtil.getMajorVersion(
				httpServletRequest);

			if (majorVersion == 11.0) {
				return spaConfiguration.disableInInternetExplorer11();
			}
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setPortletLocalService(
		PortletLocalService portletLocalService) {

		_portletLocalService = portletLocalService;
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setSPAConfigurationActivator(
		SPAConfigurationActivator spaConfigurationActivator) {

		_spaConfigurationActivator = spaConfigurationActivator;
	}

	protected void unsetSPAConfigurationActivator(
		SPAConfigurationActivator spaConfigurationActivator) {

		_spaConfigurationActivator = null;
	}

	private static final String _REDIRECT_PARAM_NAME;

	private static final String[] _SPA_DEFAULT_EXCLUDED_PATHS = {
		"/c/document_library", "/documents", "/image"
	};

	private static final String _VALID_STATUS_CODES;

	static {
		Class<?> clazz = ServletResponseConstants.class;

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Field field : clazz.getDeclaredFields()) {
			try {
				jsonArray.put(field.getInt(null));
			}
			catch (Exception e) {
			}
		}

		_VALID_STATUS_CODES = jsonArray.toJSONString();

		String portletNamespace = PortalUtil.getPortletNamespace(
			PropsUtil.get(PropsKeys.AUTH_LOGIN_PORTLET_NAME));

		_REDIRECT_PARAM_NAME = portletNamespace.concat("redirect");
	}

	@Reference
	private Portal _portal;

	private PortletLocalService _portletLocalService;
	private SPAConfigurationActivator _spaConfigurationActivator;

}