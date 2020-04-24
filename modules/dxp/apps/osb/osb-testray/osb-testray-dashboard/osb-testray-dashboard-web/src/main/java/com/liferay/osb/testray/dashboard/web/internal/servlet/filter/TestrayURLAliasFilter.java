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

package com.liferay.osb.testray.dashboard.web.internal.servlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.servlet.RequestDispatcherUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = {
		"before-filter=SPA Filter", "dispatcher=FORWARD", "dispatcher=REQUEST",
		"servlet-context-name=", "servlet-filter-name=Testray URL Alias Filter",
		"url-pattern=/*"
	},
	service = Filter.class
)
public class TestrayURLAliasFilter extends BaseFilter {

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		String currentURL = (String)request.getAttribute(WebKeys.CURRENT_URL);

		if (!currentURL.startsWith("/dashboard")) {
			super.processFilter(request, response, filterChain);

			return;
		}

		String resourcePath = currentURL;

		if (currentURL.equals("/dashboard")) {
			resourcePath += "/index.html";
		}

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(resourcePath);

		String contentType = ContentTypes.TEXT_HTML;

		if (resourcePath.endsWith(".css")) {
			contentType = ContentTypes.TEXT_CSS;
		}
		else if (resourcePath.endsWith(".js")) {
			contentType = ContentTypes.TEXT_JAVASCRIPT;
		}

		response.setContentType(contentType);

		ObjectValuePair<String, Long> objectValuePair =
			RequestDispatcherUtil.getContentAndLastModifiedTime(
				requestDispatcher, request, response);

		String stringFileContent = objectValuePair.getKey();

		if (Validator.isNull(stringFileContent)) {
			filterChain.doFilter(request, response);
		}
		else {
			ServletResponseUtil.write(response, stringFileContent);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TestrayURLAliasFilter.class);

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.osb.testray.dashboard.web)",
		unbind = "-"
	)
	private ServletContext _servletContext;

}