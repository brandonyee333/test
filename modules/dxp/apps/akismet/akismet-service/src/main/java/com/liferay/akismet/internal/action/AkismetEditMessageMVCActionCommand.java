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

package com.liferay.osb.community.akismet.internal.action;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.message.boards.web.constants.MBPortletKeys;
import com.liferay.osb.community.akismet.client.AkismetClient;
import com.liferay.osb.community.akismet.client.util.AkismetServiceConfigurationUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.HashMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jamie Sammons
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
		"mvc.command.name=/message_boards/edit_message",
		"service.ranking:Integer=100"
	},
	service = MVCActionCommand.class
)
public class AkismetEditMessageMVCActionCommand extends BaseMVCActionCommand {

	protected void checkPermission(long scopeGroupId) throws PortalException {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!permissionChecker.hasPermission(
				scopeGroupId, "com.liferay.message.boards", scopeGroupId,
				ActionKeys.BAN_USER)) {

			throw new PrincipalException();
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals("updateStatus")) {
			try {
				updateStatus(actionRequest, actionResponse);

				String redirect = _portal.escapeRedirect(
					ParamUtil.getString(actionRequest, "redirect"));

				actionResponse.sendRedirect(redirect);
			}
			catch (PrincipalException pe) {
				throw pe;
			}
			catch (Exception e) {
				SessionErrors.add(actionRequest, e.getClass());
			}
		}

		mvcActionCommand.processAction(actionRequest, actionResponse);
	}

	protected void updateStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		checkPermission(themeDisplay.getScopeGroupId());

		long messageId = ParamUtil.getLong(actionRequest, "messageId");

		boolean spam = ParamUtil.getBoolean(actionRequest, "spam");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		if (spam) {
			MBMessage message = _mbMessageLocalService.updateStatus(
				themeDisplay.getUserId(), messageId,
				WorkflowConstants.STATUS_DENIED, serviceContext,
				new HashMap<String, Serializable>());

			if (AkismetServiceConfigurationUtil.isMessageBoardsEnabled()) {
				_akismetClient.submitSpam(message);
			}
		}
		else {
			MBMessage message = _mbMessageLocalService.updateStatus(
				themeDisplay.getUserId(), messageId,
				WorkflowConstants.STATUS_APPROVED, serviceContext,
				new HashMap<String, Serializable>());

			if (AkismetServiceConfigurationUtil.isMessageBoardsEnabled()) {
				_akismetClient.submitHam(message);
			}
		}
	}

	@Reference(
		target = "(component.name=com.liferay.message.boards.web.internal.portlet.action.EditMessageMVCActionCommand)"
	)
	protected MVCActionCommand mvcActionCommand;

	@Reference
	private AkismetClient _akismetClient;

	@Reference
	private MBMessageLocalService _mbMessageLocalService;

	@Reference
	private Portal _portal;

}