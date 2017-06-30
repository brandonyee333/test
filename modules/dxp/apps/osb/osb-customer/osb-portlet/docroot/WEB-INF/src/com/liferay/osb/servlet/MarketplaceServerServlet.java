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

package com.liferay.osb.servlet;

import com.liferay.compat.portal.kernel.servlet.HttpHeaders;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.marketplaceserver.processor.MarketplaceServerResponseProcessor;
import com.liferay.osb.marketplaceserver.processor.MarketplaceServerURLProcessor;
import com.liferay.osb.marketplaceserver.servlet.MarketplaceServerServletResponse;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class MarketplaceServerServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		try {
			doService(request, response);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new IOException(e);
		}
	}

	protected void doService(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ServletContext servletContext = getPortalServletContext();

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher("/web/guest/mpserver");

		DynamicServletRequest dynamicServletRequest = new DynamicServletRequest(
			request);

		dynamicServletRequest.setAttribute("WIDGET", Boolean.TRUE);

		MarketplaceServerServletResponse marketplaceServerServletResponse =
			new MarketplaceServerServletResponse(response);

		requestDispatcher.forward(
			dynamicServletRequest, marketplaceServerServletResponse);

		writeResponse(request, response, marketplaceServerServletResponse);
	}

	protected String getCDNHost(HttpServletRequest request) {

		// Do not use PortalUtil.getCDNHost(HttpServletRequest request)

		if (request.isSecure()) {
			return PortalUtil.getCDNHostHttps(OSBConstants.COMPANY_ID);
		}
		else {
			return PortalUtil.getCDNHostHttp(OSBConstants.COMPANY_ID);
		}
	}

	protected ServletContext getPortalServletContext() {
		return ServletContextPool.get(PortalUtil.getPathContext());
	}

	protected void writeResponse(
			HttpServletRequest request, HttpServletResponse response,
			MarketplaceServerServletResponse marketplaceServerServletResponse)
		throws Exception {

		String contentType = marketplaceServerServletResponse.getContentType();

		if (marketplaceServerServletResponse.isCalledSendRedirect()) {
			writeResponseLocation(
				request, response, marketplaceServerServletResponse);
		}
		else if (contentType.startsWith(ContentTypes.TEXT_HTML)) {
			writeResponseHTML(
				request, response, marketplaceServerServletResponse);
		}
		else {
			ServletResponseUtil.write(
				response, marketplaceServerServletResponse.getBytes());
		}
	}

	protected void writeResponseHTML(
			HttpServletRequest request, HttpServletResponse response,
			MarketplaceServerServletResponse marketplaceServerServletResponse)
		throws Exception {

		MarketplaceServerResponseProcessor
			marketplaceServerResponseProcessor =
				new MarketplaceServerResponseProcessor();

		marketplaceServerResponseProcessor.setCDNHost(getCDNHost(request));

		String clientAuthToken = ParamUtil.getString(
			request, "clientAuthToken");

		marketplaceServerResponseProcessor.setClientAuthToken(clientAuthToken);

		String clientPortletId = ParamUtil.getString(
			request, "clientPortletId");

		marketplaceServerResponseProcessor.setClientPortletId(clientPortletId);

		String clientURL = ParamUtil.getString(request, "clientURL");

		marketplaceServerResponseProcessor.setClientURL(clientURL);

		marketplaceServerResponseProcessor.setHTML(
			marketplaceServerServletResponse.getString());
		marketplaceServerResponseProcessor.setPortalURL(
			PortalUtil.getPortalURL(request));
		marketplaceServerResponseProcessor.setServerPortletNamespace(
			_portletNamespace);

		ServletResponseUtil.write(
			response, marketplaceServerResponseProcessor.process());
	}

	protected void writeResponseLocation(
			HttpServletRequest request, HttpServletResponse response,
			MarketplaceServerServletResponse marketplaceServerServletResponse)
		throws Exception {

		String location = marketplaceServerServletResponse.getHeader(
			HttpHeaders.LOCATION);

		MarketplaceServerURLProcessor marketplaceServerURLProcessor =
			new MarketplaceServerURLProcessor();

		String clientAuthToken = ParamUtil.getString(
			request, "clientAuthToken");

		marketplaceServerURLProcessor.setClientAuthToken(clientAuthToken);

		String clientPortletId = ParamUtil.getString(
			request, "clientPortletId");

		marketplaceServerURLProcessor.setClientPortletId(clientPortletId);

		String clientURL = ParamUtil.getString(request, "clientURL");

		marketplaceServerURLProcessor.setClientURL(clientURL);

		marketplaceServerURLProcessor.setRedirect(true);
		marketplaceServerURLProcessor.setServerPortletNamespace(
			_portletNamespace);
		marketplaceServerURLProcessor.setURL(location);

		response.sendRedirect(marketplaceServerURLProcessor.process());
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceServerServlet.class);

	private static String _portletNamespace = PortalUtil.getPortletNamespace(
		OSBPortletKeys.OSB_MARKETPLACE_SERVER);

}