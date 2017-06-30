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

package com.liferay.osb.corpprojectadmin.portlet;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.WebKeys;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.AccountEntryCorpProjectException;
import com.liferay.osb.CorpProjectMessageContentException;
import com.liferay.osb.CorpProjectMessageSeverityLevelException;
import com.liferay.osb.CorpProjectMessageTitleException;
import com.liferay.osb.CorpProjectMessageTypeException;
import com.liferay.osb.CorpProjectSalesforceProjectKeyException;
import com.liferay.osb.DuplicateCorpProjectException;
import com.liferay.osb.NoSuchCorpProjectException;
import com.liferay.osb.model.CorpProjectMessage;
import com.liferay.osb.service.CorpProjectMessageLocalServiceUtil;
import com.liferay.osb.service.CorpProjectServiceUtil;
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Ryan Schuhler
 * @author Amos Fong
 */
public class CorpProjectAdminPortlet extends MVCPortlet {

	public void addCorpProjectUsers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long corpProjectId = ParamUtil.getLong(actionRequest, "corpProjectId");

		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");

		long userId = UserServiceUtil.getUserIdByEmailAddress(
			themeDisplay.getCompanyId(), emailAddress);

		CorpProjectServiceUtil.addCorpProjectUsers(
			corpProjectId, new long[] {userId});
	}

	public void deleteCorpProjectMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long corpProjectMessageId = ParamUtil.getLong(
			actionRequest, "corpProjectMessageId");

		CorpProjectMessage corpProjectMessage =
			CorpProjectMessageLocalServiceUtil.deleteCorpProjectMessage(
				corpProjectMessageId);

		try {
			CorpProjectMessageLocalServiceUtil.deleteFromLCS(
				corpProjectMessage);
		}
		catch (Exception e) {
			_log.error(e, e);

			SessionMessages.add(actionRequest, "lcsSyncFailed");

			addSuccessMessage(actionRequest, actionResponse);

			sendRedirect(actionRequest, actionResponse);
		}
	}

	public void setCorpProjectUserRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long corpProjectId = ParamUtil.getLong(actionRequest, "corpProjectId");

		long userId = ParamUtil.getLong(actionRequest, "userId");
		long roleId = ParamUtil.getLong(actionRequest, "roleId");
		boolean assignRole = ParamUtil.getBoolean(actionRequest, "assignRole");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			if (assignRole) {
				CorpProjectServiceUtil.addUserCorpProjectRoles(
					corpProjectId, new long[] {userId}, roleId);
			}
			else {
				CorpProjectServiceUtil.deleteUserCorpProjectRoles(
					corpProjectId, new long[] {userId}, roleId);
			}

			jsonObject.put("message", "success");
		}
		catch (Exception e) {
			jsonObject.put("exception", e.getClass().getName());
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void syncToLCS(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			long corpProjectId = ParamUtil.getLong(
				actionRequest, "corpProjectId");

			LCSSubscriptionEntryLocalServiceUtil.syncToLCS(corpProjectId);
		}
		catch (Exception e) {
			_log.error(e, e);

			SessionMessages.add(actionRequest, "lcsSyncFailed");

			addSuccessMessage(actionRequest, actionResponse);

			sendRedirect(actionRequest, actionResponse);
		}
	}

	public void unsetCorpProjectUsers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long corpProjectId = ParamUtil.getLong(actionRequest, "corpProjectId");

		long[] userIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "userIds"), 0L);

		CorpProjectServiceUtil.unsetCorpProjectUsers(corpProjectId, userIds);
	}

	public void updateCorpProject(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long corpProjectId = ParamUtil.getLong(actionRequest, "corpProjectId");

		String dossieraProjectKey = ParamUtil.getString(
			actionRequest, "dossieraProjectKey");
		String salesforceProjectKey = ParamUtil.getString(
			actionRequest, "salesforceProjectKey");
		String name = ParamUtil.getString(actionRequest, "name");
		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		if (corpProjectId <= 0) {
			CorpProjectServiceUtil.addCorpProject(
				dossieraProjectKey, salesforceProjectKey, name);
		}
		else {
			CorpProjectServiceUtil.updateCorpProject(
				corpProjectId, dossieraProjectKey, salesforceProjectKey, name,
				accountEntryId);
		}
	}

	public void updateCorpProjectMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long corpProjectMessageId = ParamUtil.getLong(
			actionRequest, "corpProjectMessageId");

		long corpProjectId = ParamUtil.getLong(actionRequest, "corpProjectId");
		int type = ParamUtil.getInteger(actionRequest, "type");
		int severityLevel = ParamUtil.getInteger(
			actionRequest, "severityLevel");
		String title = ParamUtil.getString(actionRequest, "title");
		String content = ParamUtil.getString(actionRequest, "content");
		boolean displayCP = ParamUtil.getBoolean(actionRequest, "displayCP");
		boolean displayLCS = ParamUtil.getBoolean(actionRequest, "displayLCS");
		boolean displayLESA = ParamUtil.getBoolean(
			actionRequest, "displayLESA");

		CorpProjectMessage corpProjectMessage = null;

		if (corpProjectMessageId > 0) {
			corpProjectMessage =
				CorpProjectMessageLocalServiceUtil.updateCorpProjectMessage(
					themeDisplay.getUserId(), corpProjectMessageId, type,
					severityLevel, title, content, displayCP, displayLCS,
					displayLESA);
		}
		else {
			corpProjectMessage =
				CorpProjectMessageLocalServiceUtil.addCorpProjectMessage(
					themeDisplay.getUserId(), corpProjectId, type,
					severityLevel, title, content, displayCP, displayLCS,
					displayLESA);
		}

		try {
			CorpProjectMessageLocalServiceUtil.syncToLCS(
				corpProjectMessage.getCorpProjectMessageId());
		}
		catch (Exception e) {
			_log.error(e, e);

			SessionMessages.add(actionRequest, "lcsSyncFailed");

			addSuccessMessage(actionRequest, actionResponse);

			sendRedirect(actionRequest, actionResponse);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof AccountEntryCorpProjectException ||
			cause instanceof CorpProjectMessageContentException ||
			cause instanceof CorpProjectMessageSeverityLevelException ||
			cause instanceof CorpProjectMessageTitleException ||
			cause instanceof CorpProjectMessageTypeException ||
			cause instanceof CorpProjectSalesforceProjectKeyException ||
			cause instanceof DuplicateCorpProjectException ||
			cause instanceof NoSuchCorpProjectException ||
			cause instanceof NoSuchUserException) {

			return true;
		}
		else {
			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CorpProjectAdminPortlet.class);

}