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

package com.liferay.osb.asah.common.servlet.filter;

import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.dog.exception.InvalidProjectIdException;
import com.liferay.osb.asah.common.servlet.util.ServletRequestUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Shinn Lok
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProjectIdThreadLocalOncePerRequestFilter
	extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws IOException, ServletException {

		String projectId = httpServletRequest.getHeader(
			HeaderConstants.PROJECT_ID);

		if (projectId == null) {
			Matcher matcher = _urlPattern.matcher(
				ServletRequestUtil.getOriginalURL(httpServletRequest));

			if (matcher.find()) {
				projectId = matcher.group(1);
			}
		}

		try {
			ProjectIdThreadLocal.setProjectId(projectId);

			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
		catch (InvalidProjectIdException invalidProjectIdException) {
			httpServletResponse.sendError(
				HttpServletResponse.SC_BAD_REQUEST, "INVALID_PROJECT_ID");
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest httpServletRequest) {
		String method = httpServletRequest.getMethod();
		String requestURI = httpServletRequest.getRequestURI();

		if ((method.equals(HttpMethod.GET.name()) &&
			 (requestURI.equals("/") || requestURI.equals("/context") ||
			  requestURI.startsWith("/actuator") ||
			  requestURI.startsWith("/projects"))) ||
			(method.equals(HttpMethod.OPTIONS.name()) &&
			 (requestURI.equals("/") || requestURI.equals("/identity")))) {

			return true;
		}

		return false;
	}

	private static final Pattern _urlPattern = Pattern.compile(
		"^https://osbasah(?:backend|monolith|publisher)-(\\w+)\\.lfr\\.cloud");

}