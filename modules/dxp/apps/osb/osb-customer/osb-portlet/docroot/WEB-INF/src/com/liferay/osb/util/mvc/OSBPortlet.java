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

package com.liferay.osb.util.mvc;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Ryan Park
 */
public class OSBPortlet extends MVCPortlet {

	protected void syncAccountEntriesToLCS(
			ActionRequest actionRequest, ActionResponse actionResponse,
			Set<Long> accountEntryIds)
		throws IOException, PortalException {

		for (long accountEntryId : accountEntryIds) {
			syncAccountEntryToLCS(
				actionRequest, actionResponse, accountEntryId);
		}
	}

	protected void syncAccountEntryToLCS(
			ActionRequest actionRequest, ActionResponse actionResponse,
			long accountEntryId)
		throws IOException, PortalException {

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
				"Unable to sync corp project " + corpProjectId + " to LCS", e);

			SessionMessages.add(actionRequest, "lcsSyncFailed");

			addSuccessMessage(actionRequest, actionResponse);

			sendRedirect(actionRequest, actionResponse);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(OSBPortlet.class);

}