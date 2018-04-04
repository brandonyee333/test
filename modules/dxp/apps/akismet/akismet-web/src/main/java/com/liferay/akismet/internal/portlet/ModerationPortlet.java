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

package com.liferay.akismet.internal.portlet;

import java.io.Serializable;
import java.util.HashMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.message.boards.kernel.exception.NoSuchMessageException;
import com.liferay.message.boards.kernel.exception.RequiredMessageException;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.message.boards.kernel.service.MBMessageService;
import com.liferay.akismet.client.AkismetClient;
import com.liferay.akismet.client.util.AkismetServiceConfigurationUtil;
import com.liferay.akismet.internal.constants.ModerationPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Jamie
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"javax.portlet.display-name=Spam Moderation",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ModerationPortletKeys.MODERATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ModerationPortlet extends MVCPortlet {

	public void deleteMBMessages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] mbMessageIds = ParamUtil.getLongValues(
			actionRequest, "deleteMBMessageIds");

		for (long mbMessageId : mbMessageIds) {
			_mbMessageService.deleteMessage(mbMessageId);
		}
	}

	public void markNotSpamMBMessages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] mbMessageIds = ParamUtil.getLongValues(
			actionRequest, "notSpamMBMessageIds");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		for (long mbMessageId : mbMessageIds) {
			MBMessage mbMessage = _mbMessageLocalService.updateStatus(
				themeDisplay.getUserId(), mbMessageId,
				WorkflowConstants.STATUS_APPROVED, serviceContext,
				new HashMap<String, Serializable>());

			if (AkismetServiceConfigurationUtil.isMessageBoardsEnabled()) {
				_akismetClient.submitHam(mbMessage);
			}
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof NoSuchMessageException ||
			cause instanceof PrincipalException ||
			cause instanceof RequiredMessageException) {

			return true;
		}

		return false;
	}

	@Reference
	private AkismetClient _akismetClient;

	@Reference
	private MBMessageLocalService _mbMessageLocalService;

	@Reference
	private MBMessageService _mbMessageService;

}