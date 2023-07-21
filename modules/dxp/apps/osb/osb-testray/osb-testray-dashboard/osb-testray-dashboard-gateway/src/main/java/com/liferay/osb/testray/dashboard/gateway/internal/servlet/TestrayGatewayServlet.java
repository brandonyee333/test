/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.dashboard.gateway.internal.servlet;

import com.liferay.osb.testray.dashboard.gateway.internal.util.APITranslator;
import com.liferay.osb.testray.dashboard.gateway.internal.util.ExternalAPIRequestProcessor;
import com.liferay.osb.testray.dashboard.gateway.internal.util.TestrayDashboardGatewayValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.servlet.name=com.liferay.osb.testray.dashboard.gateway.internal.servlet.TestrayGatewayServlet",
		"osgi.http.whiteboard.servlet.pattern=/testray-gateway-servlet/*",
		"servlet.init.httpMethods=GET,POST,HEAD"
	},
	service = Servlet.class
)
public class TestrayGatewayServlet extends HttpServlet {

	@Activate
	public void activate(Map<String, Object> config) {
		APITranslator.setAvailableAPIs(
			TestrayDashboardGatewayValues.AVAILABLE_APIS);

		_externalAPIRequestProcessor = new ExternalAPIRequestProcessor(
			TestrayDashboardGatewayValues.JENKINS_USER_NAME,
			TestrayDashboardGatewayValues.JENKINS_USER_TOKEN,
			TestrayDashboardGatewayValues.API_CACHE_TIMEOUT_SECONDS);

		if (_log.isDebugEnabled()) {
			String httpWhiteboardServletPattern = (String)config.get(
				HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN);

			_log.debug(
				"TestrayGatewayServlet is available at /o" +
					httpWhiteboardServletPattern);
		}
	}

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		String output = null;

		String apiName = request.getParameter("apiName");
		String server = request.getParameter("server");

		try {
			output = _externalAPIRequestProcessor.process(apiName, server);
		}
		catch (Exception e) {
			_log.error(e, e);

			output = e.getMessage();

			if (Validator.isNull(output)) {
				output = e.toString();
			}
		}

		response.setContentType(ContentTypes.APPLICATION_JSON);
		response.setStatus(HttpServletResponse.SC_OK);

		ServletResponseUtil.write(response, output);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TestrayGatewayServlet.class);

	private ExternalAPIRequestProcessor _externalAPIRequestProcessor;

}