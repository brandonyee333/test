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

package com.liferay.osb.corpmembers.portlet;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.UnverifiedUserException;
import com.liferay.osb.service.CorpEntryServiceUtil;
import com.liferay.osb.service.CorpMembershipRequestServiceUtil;
import com.liferay.osb.service.CorpProjectServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Ryan Park
 */
public class CorpMembersPortlet extends MVCPortlet {

	public void addCorpProjectUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long corpProjectId = ParamUtil.getLong(actionRequest, "corpProjectId");

		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");

		User user = UserLocalServiceUtil.getUserByEmailAddress(
			themeDisplay.getCompanyId(), emailAddress);

		if (!RoleLocalServiceUtil.hasUserRole(
				themeDisplay.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID)) {

			throw new UnverifiedUserException();
		}

		CorpProjectServiceUtil.addCorpProjectUsers(
			corpProjectId, new long[] {user.getUserId()});
	}

	public void sendEmailAddressVerification(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		User user = themeDisplay.getUser();

		UserLocalServiceUtil.sendEmailAddressVerification(
			user, user.getEmailAddress(), serviceContext);
	}

	public void setCorpEntryUserRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long corpEntryId = ParamUtil.getLong(actionRequest, "corpEntryId");

		long userId = ParamUtil.getLong(actionRequest, "userId");
		long roleId = ParamUtil.getLong(actionRequest, "roleId");
		boolean assignRole = ParamUtil.getBoolean(actionRequest, "assignRole");

		if (assignRole) {
			CorpEntryServiceUtil.addUserCorpEntryRoles(
				corpEntryId, new long[] {userId}, roleId);
		}
		else {
			CorpEntryServiceUtil.deleteUserCorpEntryRoles(
				corpEntryId, new long[] {userId}, roleId);
		}
	}

	public void setCorpProjectUserRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long corpProjectId = ParamUtil.getLong(actionRequest, "corpProjectId");

		long userId = ParamUtil.getLong(actionRequest, "userId");
		long roleId = ParamUtil.getLong(actionRequest, "roleId");
		boolean assignRole = ParamUtil.getBoolean(actionRequest, "assignRole");

		if (assignRole) {
			CorpProjectServiceUtil.addUserCorpProjectRoles(
				corpProjectId, new long[] {userId}, roleId);
		}
		else {
			CorpProjectServiceUtil.deleteUserCorpProjectRoles(
				corpProjectId, new long[] {userId}, roleId);
		}
	}

	public void unsetCorpEntryUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long corpEntryId = ParamUtil.getLong(actionRequest, "corpEntryId");
		long userId = ParamUtil.getLong(actionRequest, "userId");

		CorpEntryServiceUtil.unsetCorpEntryUsers(
			corpEntryId, new long[] {userId});
	}

	public void unsetCorpProjectUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long corpProjectId = ParamUtil.getLong(actionRequest, "corpProjectId");
		long userId = ParamUtil.getLong(actionRequest, "userId");

		CorpProjectServiceUtil.unsetCorpProjectUsers(
			corpProjectId, new long[] {userId});

		if (themeDisplay.getUserId() == userId) {
			LiferayPortletResponse liferayPortletResponse =
				(LiferayPortletResponse)actionResponse;

			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter("mvcPath", "/corp_members/view.jsp");
			portletURL.setParameter("corpProjectId", "0");

			actionResponse.sendRedirect(portletURL.toString());
		}
	}

	public void updateCorpMembershipRequestStatuses(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] corpMembershipRequestIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "corpMembershipRequestIds"), 0L);
		int status = ParamUtil.getInteger(actionRequest, "status");

		for (long corpMembershipRequestId : corpMembershipRequestIds) {
			CorpMembershipRequestServiceUtil.updateStatus(
				corpMembershipRequestId, status);
		}
	}

	public void updateCorpProject(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long corpProjectId = ParamUtil.getLong(actionRequest, "corpProjectId");

		String name = ParamUtil.getString(actionRequest, "name");

		CorpProjectServiceUtil.updateCorpProject(corpProjectId, name);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

		super.doDispatch(renderRequest, renderResponse);
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (_log.isDebugEnabled()) {
			_log.debug(cause, cause);
		}

		if (cause instanceof UnverifiedUserException) {
			return true;
		}

		return super.isSessionErrorException(cause);
	}

	private static Log _log = LogFactoryUtil.getLog(CorpMembersPortlet.class);

}