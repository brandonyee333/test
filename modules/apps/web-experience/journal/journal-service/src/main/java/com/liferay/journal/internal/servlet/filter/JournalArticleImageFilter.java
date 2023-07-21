/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.servlet.filter;

import com.liferay.journal.model.JournalArticleImage;
import com.liferay.journal.service.JournalArticleImageLocalService;
import com.liferay.journal.service.permission.JournalArticlePermission;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Balázs Sáfrány-Kovalik
 */
@Component(
	immediate = true,
	property = {
		"servlet-context-name=",
		"servlet-filter-name=Journal Article Image Filter",
		"url-pattern=/image/journal/article/*"
	},
	service = Filter.class
)
public class JournalArticleImageFilter extends BaseFilter {

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		long imageId = ParamUtil.getLong(request, "img_id");

		if (imageId > 0) {
			JournalArticleImage journalArticleImage =
				_journalArticleImageLocalService.fetchJournalArticleImage(
					imageId);

			if (journalArticleImage != null) {
				User user = _getUser(request);

				PermissionChecker permissionChecker =
					PermissionCheckerFactoryUtil.create(user);

				try {
					JournalArticlePermission.check(
						permissionChecker, journalArticleImage.getGroupId(),
						journalArticleImage.getArticleId(), ActionKeys.VIEW);
				}
				catch (PrincipalException pe) {
					_processPrincipalException(pe, user, request, response);

					return;
				}
			}
		}

		processFilter(
			JournalArticleImageFilter.class.getName(), request, response,
			filterChain);
	}

	private User _getUser(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();

		if (PortalSessionThreadLocal.getHttpSession() == null) {
			PortalSessionThreadLocal.setHttpSession(session);
		}

		User user = _portal.getUser(request);

		if (user != null) {
			return user;
		}

		String userIdString = (String)session.getAttribute("j_username");
		String password = (String)session.getAttribute("j_password");

		if ((userIdString != null) && (password != null)) {
			long userId = GetterUtil.getLong(userIdString);

			user = _userLocalService.getUser(userId);
		}
		else {
			long companyId = _portal.getCompanyId(request);

			Company company = _companyLocalService.getCompany(companyId);

			user = company.getDefaultUser();
		}

		return user;
	}

	private void _processPrincipalException(
			Throwable t, User user, HttpServletRequest request,
			HttpServletResponse response)
		throws IOException, ServletException {

		if (!user.isDefaultUser()) {
			_portal.sendError(
				HttpServletResponse.SC_UNAUTHORIZED, (Exception)t, request,
				response);

			return;
		}

		String redirect = _portal.getPathMain() + "/portal/login";

		String currentURL = _portal.getCurrentURL(request);

		redirect = _http.addParameter(redirect, "redirect", currentURL);

		response.sendRedirect(redirect);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalArticleImageFilter.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private Http _http;

	@Reference
	private JournalArticleImageLocalService _journalArticleImageLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}