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

package com.liferay.osb.support;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryDiscussion;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.Encryptor;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * @author Amos Fong
 * @author Mate Thurzo
 */
public class SupportFriendlyURLMapper extends DefaultFriendlyURLMapper {

	@Override
	public String buildPath(LiferayPortletURL portletURL) {
		String friendlyURLPath = null;

		boolean friendly = GetterUtil.getBoolean(
			portletURL.getParameter("friendly"));

		if (!friendly) {
			return friendlyURLPath;
		}

		String mvcPath = GetterUtil.getString(
			portletURL.getParameter("mvcPath"));

		if (Validator.isNull(mvcPath)) {
			return friendlyURLPath;
		}

		if (mvcPath.equals("/support/edit_ticket_entry.jsp") ||
			mvcPath.equals("/support/2/edit_ticket_entry.jsp")) {

			long ticketEntryId = GetterUtil.getLong(
				portletURL.getParameter("ticketEntryId"));

			if (ticketEntryId > 0) {
				try {
					TicketEntry ticketEntry =
						TicketEntryLocalServiceUtil.getTicketEntry(
							ticketEntryId);

					friendlyURLPath =
						"/support/ticket/" + ticketEntry.getDisplayId();

					portletURL.addParameterIncludedInPath("ticketEntryId");
				}
				catch (Exception e) {
				}

				long discussionId = GetterUtil.getLong(
					portletURL.getParameter("discussionId"));

				if (discussionId > 0) {
					String discussionType = GetterUtil.getString(
						portletURL.getParameter("discussionType"));

					if (Validator.isNotNull(discussionType)) {
						friendlyURLPath +=
							StringPool.SLASH + discussionType +
								StringPool.SLASH + discussionId;

						portletURL.addParameterIncludedInPath("discussionId");
						portletURL.addParameterIncludedInPath("discussionType");
					}
				}
			}
		}
		else if (mvcPath.equals("/support/edit_account_entry.jsp") ||
				 mvcPath.equals("/support/2/edit_account_entry.jsp")) {

			long accountEntryId = GetterUtil.getLong(
				portletURL.getParameter("accountEntryId"));

			String tabs1 = GetterUtil.getString(
				portletURL.getParameter("tabs1"));

			if (accountEntryId > 0) {
				try {
					AccountEntry accountEntry =
						AccountEntryLocalServiceUtil.getAccountEntry(
							accountEntryId);

					friendlyURLPath =
						"/support/ticket/" + accountEntry.getCode();

					if (tabs1.equals("project-details")) {
						friendlyURLPath += "/project_details";

						portletURL.addParameterIncludedInPath("tabs1");
					}

					portletURL.addParameterIncludedInPath("accountEntryId");
				}
				catch (Exception e) {
				}
			}
		}
		else if (mvcPath.equals("/support/edit_liferay_ticket_feedback.jsp")) {
			long ticketFeedbackId = GetterUtil.getLong(
				portletURL.getParameter("ticketFeedbackId"));

			if (ticketFeedbackId > 0) {
				friendlyURLPath = "/support/feedback/" + ticketFeedbackId;
			}
		}
		else if (mvcPath.equals("/support/view.jsp")) {
			long searchFilterId = GetterUtil.getLong(
				portletURL.getParameter("searchFilterId"));

			if (searchFilterId > 0) {
				friendlyURLPath = "/support/search/" + searchFilterId;
			}
		}

		if (Validator.isNotNull(friendlyURLPath)) {
			WindowState windowState = portletURL.getWindowState();

			if ((windowState != null) &&
				!windowState.equals(WindowState.NORMAL)) {

				friendlyURLPath += StringPool.SLASH + windowState;
			}

			portletURL.addParameterIncludedInPath("mvcPath");
			portletURL.addParameterIncludedInPath("friendly");

			addParametersIncludedInPath(
				portletURL, new HashMap<String, String>());
		}

		return friendlyURLPath;
	}

	@Override
	public String getMapping() {
		return _MAPPING;
	}

	@Override
	public String getPortletId() {
		return _PORTLET_ID;
	}

	@Override
	public void populateParams(
		String friendlyURLPath, Map<String, String[]> parameters,
		Map<String, Object> requestContext) {

		addParameter(parameters, "p_p_id", _PORTLET_ID);
		addParameter(parameters, "p_p_lifecycle", "1");
		addParameter(parameters, "p_p_mode", PortletMode.VIEW);

		int x = friendlyURLPath.indexOf("/", 1);

		if ((x + 1) == friendlyURLPath.length()) {
			addParameter(parameters, "mvcPath", "/support/view.jsp");

			return;
		}

		String[] urlFragments = StringUtil.split(
			friendlyURLPath.substring(x + 1), StringPool.SLASH);

		if (urlFragments.length >= 1) {
			String urlFragment0 = urlFragments[0];

			if (urlFragment0.equals("ticket")) {
				if (urlFragments.length >= 2) {
					String ticketDisplayId = urlFragments[1];

					addParameter(
						parameters, "mvcPath",
						"/support/edit_ticket_entry.jsp");
					addParameter(
						parameters, "ticketDisplayId", ticketDisplayId);
				}

				if (urlFragments.length >= 3) {
					String urlFragment2 = urlFragments[2];

					if (urlFragment2.equals("project_details")) {
						addParameter(parameters, "tabs1", "project-details");
					}
				}

				if (urlFragments.length >= 4) {
					String urlFragment2 = urlFragments[2];

					if (urlFragment2.equals(
							TicketEntryDiscussion.TYPE_ATTACHMENT) ||
						urlFragment2.equals(
							TicketEntryDiscussion.TYPE_COMMENT) ||
						urlFragment2.equals(TicketEntryDiscussion.TYPE_LINK)) {

						addParameter(
							parameters, "discussionId", urlFragments[3]);
						addParameter(
							parameters, "discussionType", urlFragment2);
					}
				}

				if (urlFragments.length >= 5) {
					String windowState = urlFragments[4];

					addParameter(parameters, "p_p_state", windowState);
				}
				else {
					addParameter(parameters, "p_p_state", WindowState.NORMAL);
				}
			}
			else if (urlFragment0.equals("feedback")) {
				String ticketFeedbackId = urlFragments[1];

				addParameter(parameters, "p_p_state", WindowState.NORMAL);
				addParameter(
					parameters, "mvcPath",
					"/support/edit_liferay_ticket_feedback.jsp");
				addParameter(parameters, "ticketFeedbackId", ticketFeedbackId);
			}
			else if (urlFragment0.equals("partner_feedback")) {
				String ticketFeedbackId = urlFragments[1];

				addParameter(parameters, "p_p_state", WindowState.NORMAL);
				addParameter(
					parameters, "mvcPath",
					"/support/edit_partner_ticket_feedback.jsp");
				addParameter(parameters, "ticketFeedbackId", ticketFeedbackId);
			}
			else if (urlFragment0.equals("search")) {
				addParameter(parameters, "p_p_state", WindowState.NORMAL);
				addParameter(parameters, "mvcPath", "/support/view.jsp");
				addParameter(parameters, "searchFilterId", urlFragments[1]);
			}

			String propertiesAuthenticatonTokenSharedSecret =
				Encryptor.digest(
					PropsUtil.get(PropsKeys.AUTH_TOKEN_SHARED_SECRET));

			addParameter(
				parameters, "p_auth_secret",
				propertiesAuthenticatonTokenSharedSecret);
		}
	}

	private static final String _MAPPING = "support";

	private static final String _PORTLET_ID = OSBPortletKeys.OSB_SUPPORT;

}