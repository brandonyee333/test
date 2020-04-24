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