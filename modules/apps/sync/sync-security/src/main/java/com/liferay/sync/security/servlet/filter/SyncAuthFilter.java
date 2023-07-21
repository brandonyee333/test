/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.security.servlet.filter;

import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.service.access.policy.ServiceAccessPolicyThreadLocal;
import com.liferay.sync.security.service.access.policy.SyncSAPEntryActivator;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(
	immediate = true,
	property = {
		"servlet-context-name=", "servlet-filter-name=Sync Auth Filter",
		"url-pattern=/api/jsonws/*"
	},
	service = Filter.class
)
public class SyncAuthFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if ((permissionChecker != null) && permissionChecker.isSignedIn()) {
			ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName(
				String.valueOf(
					SyncSAPEntryActivator.SAP_ENTRY_OBJECT_ARRAYS[1][0]));
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

}