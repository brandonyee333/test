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

package com.liferay.osb.util.mvc;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Ryan Park
 */
public class OSBPortlet extends MVCPortlet {

	protected void syncAccountEntriesToLCS(
			ActionRequest actionRequest, ActionResponse actionResponse,
			Set<Long> accountEntryIds)
		throws IOException, PortalException, SystemException {

		for (long accountEntryId : accountEntryIds) {
			syncAccountEntryToLCS(
				actionRequest, actionResponse, accountEntryId);
		}
	}

	protected void syncAccountEntryToLCS(
			ActionRequest actionRequest, ActionResponse actionResponse,
			long accountEntryId)
		throws IOException, PortalException, SystemException {

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		syncToLCS(
			actionRequest, actionResponse, accountEntry.getCorpProjectId());
	}

	protected void syncToLCS(
			ActionRequest actionRequest, ActionResponse actionResponse,
			long corpProjectId)
		throws IOException {

		try {
			LCSSubscriptionEntryLocalServiceUtil.syncToLCS(corpProjectId);
		}
		catch (Exception e) {
			_log.error(
				"Unable to sync corp project to LCS: " + corpProjectId, e);

			SessionMessages.add(actionRequest, "lcsSyncFailed");

			addSuccessMessage(actionRequest, actionResponse);

			sendRedirect(actionRequest, actionResponse);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(OSBPortlet.class);

}