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

import com.liferay.osb.asah.common.servlet.util.ServletRequestUtil;
import com.liferay.osb.asah.common.util.ProjectThreadLocal;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Shinn Lok
 */
public class ProjectThreadLocalFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws IOException, ServletException {

		String projectId = httpServletRequest.getHeader("OSB-Asah-Project-Id");

		if (projectId == null) {
			Matcher matcher = _urlPattern.matcher(
				ServletRequestUtil.getOriginalURL(httpServletRequest));

			if (!matcher.find()) {
				filterChain.doFilter(httpServletRequest, httpServletResponse);

				return;
			}

			projectId = matcher.group(1);
		}

		try {
			ProjectThreadLocal.setProjectId(projectId);

			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
		finally {
			ProjectThreadLocal.remove();
		}
	}

	private static final Pattern _urlPattern = Pattern.compile(
		"^https://osbasah(?:backend|monolith|publisher)-(\\w+)\\.lfr\\.cloud");

}