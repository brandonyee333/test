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

package com.liferay.osb.hook.filter;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.ip.geocoder.IPGeocoder;
import com.liferay.ip.geocoder.IPInfo;
import com.liferay.ip.geocoder.internal.IPGeocoderImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Ryan Park
 */
public class IPGeocoderFilter extends BaseFilter {

	@Override
	public void init(FilterConfig filterConfig) {
		_languageIdsMap = new HashMap<String, String>(14);

		_languageIdsMap.put("AR", "es");
		_languageIdsMap.put("BR", "pt");
		_languageIdsMap.put("CL", "es");
		_languageIdsMap.put("CN", "zh");
		_languageIdsMap.put("DE", "de");
		_languageIdsMap.put("ES", "es");
		_languageIdsMap.put("FR", "fr");
		_languageIdsMap.put("GB", "en_GB");
		_languageIdsMap.put("IT", "it");
		_languageIdsMap.put("JP", "ja");
		_languageIdsMap.put("MX", "es");
		_languageIdsMap.put("NE", "es");
		_languageIdsMap.put("PT", "pt");
		_languageIdsMap.put("UY", "es");

		super.init(filterConfig);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	protected String getRedirect(HttpServletRequest request) throws Exception {
		String method = request.getMethod();

		if (method.equals(HttpMethods.POST)) {
			return null;
		}

		if (GetterUtil.getBoolean(request.getAttribute("WIDGET"))) {
			return null;
		}

		HttpSession session = request.getSession();

		Locale locale = (Locale)session.getAttribute(
			"org.apache.struts.action.LOCALE");

		if (locale != null) {
			return null;
		}

		IPGeocoder ipGeocoder = (IPGeocoder)request.getAttribute("IP_GEOCODER");

		IPInfo ipInfo = ipGeocoder.getIPInfo(request.getRemoteAddr());

		if ((ipInfo == null) || Validator.isNull(ipInfo.getCountryCode())) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to determine the location for IP " +
						request.getRemoteAddr());
			}

			return null;
		}

		String languageId = _languageIdsMap.get(ipInfo.getCountryCode());

		if (languageId == null) {
			return null;
		}

		locale = LocaleUtil.fromLanguageId(languageId);

		session.setAttribute("org.apache.struts.action.LOCALE", locale);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Redirecting to languageId " + languageId + " for IP " +
					request.getRemoteAddr());
		}

		String requestURI = request.getRequestURI();

		requestURI = StringUtil.replace(
			requestURI, StringPool.DOUBLE_SLASH, StringPool.SLASH);

		if (requestURI.startsWith("/web/guest")) {
			requestURI = requestURI.substring(10);
		}

		return StringPool.SLASH + languageId + requestURI;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		String redirect = getRedirect(request);

		if (redirect == null) {
			processFilter(
				IPGeocoderFilter.class.getName(), request, response, filterChain);

			return;
		}

		response.sendRedirect(redirect);
	}

	private static Log _log = LogFactoryUtil.getLog(IPGeocoderFilter.class);

	private static Map<String, String> _languageIdsMap;

}