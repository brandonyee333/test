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
import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.multitenancy.exception.InvalidProjectIdException;
import com.liferay.osb.asah.common.servlet.util.ServletRequestUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.io.IOException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Shinn Lok
 */
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

			if (!matcher.find()) {
				filterChain.doFilter(httpServletRequest, httpServletResponse);

				return;
			}

			projectId = matcher.group(1);
		}

		if (!Objects.equals(projectId, ServiceConstants.LCP_PROJECT_ID) &&
			!ServiceConstants.OSB_ASAH_MULTITENANCY_ENABLED &&
			_log.isWarnEnabled()) {

			_log.warn(
				String.format(
					"Mismatched project ids: %s, %s", projectId,
					ServiceConstants.LCP_PROJECT_ID));
		}

		try {
			ProjectIdThreadLocal.setProjectId(projectId);

			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
		catch (InvalidProjectIdException ipie) {
			httpServletResponse.sendError(
				HttpServletResponse.SC_BAD_REQUEST, "INVALID_PROJECT_ID");
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	private static final Log _log = LogFactory.getLog(
		ProjectIdThreadLocalOncePerRequestFilter.class);

	private static final Pattern _urlPattern = Pattern.compile(
		"^https://osbasah(?:backend|monolith|publisher)-(\\w+)\\.lfr\\.cloud");

}