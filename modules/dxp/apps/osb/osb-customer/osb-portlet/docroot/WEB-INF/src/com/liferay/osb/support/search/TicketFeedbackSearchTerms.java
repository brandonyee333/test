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

package com.liferay.osb.support.search;

import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.service.SupportTeamLocalServiceUtil;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Mate Thurzo
 */
public class TicketFeedbackSearchTerms extends TicketFeedbackDisplayTerms {

	public TicketFeedbackSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public String getActiveFilters() {
		StringBundler sb = new StringBundler(4);

		if (hasSearchableAssigneeValues()) {
			sb.append("assignee");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableDateValues()) {
			sb.append("date");
			sb.append(StringPool.COMMA);
		}

		return sb.toString();
	}

	public LinkedHashMap<String, Object> getParams() {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		if (!isAdvancedSearch()) {
			return params;
		}

		if ((assignedPartnerEntryIds.length > 0) ||
			(assignedSupportTeamIds.length > 0) ||
			(assignedUserIds.length > 0)) {

			if (assignedPartnerEntryIds.length > 0) {
				params.put("assignedPartnerEntry", assignedPartnerEntryIds);
			}

			if (assignedSupportTeamIds.length > 0) {
				params.put("assignedSupportTeam", assignedSupportTeamIds);
			}

			if (assignedUserIds.length > 0) {
				params.put("assignedTicketEntryWorker", assignedUserIds);
			}
		}
		else if (Validator.isNotNull(assignedTo)) {
			if (assignedTo.startsWith("partnerEntryId-")) {
				int pos = assignedTo.indexOf(CharPool.DASH);

				long partnerEntryId = GetterUtil.getLong(
					assignedTo.substring(pos + 1));

				long[] partnerEntryIds = SupportUtil.getPartnerEntryIds(
					partnerEntryId);

				params.put("partnerEntry", partnerEntryIds);
			}
			else if (assignedTo.startsWith("supportTeamId-")) {
				int pos = assignedTo.indexOf(CharPool.DASH);

				long supportTeamId = GetterUtil.getLong(
					assignedTo.substring(pos + 1));

				List<SupportTeam> childSupportTeams =
					SupportTeamLocalServiceUtil.getChildSupportTeams(
						supportTeamId, true);

				Long[] supportTeams = new Long[childSupportTeams.size() + 1];

				supportTeams[0] = supportTeamId;

				for (int i = 0; i < childSupportTeams.size(); i++) {
					SupportTeam childSupportTeam = childSupportTeams.get(i);

					supportTeams[i + 1] = childSupportTeam.getSupportTeamId();
				}

				params.put("supportTeam", supportTeams);
			}
			else {
				params.put("ticketEntryWorker", GetterUtil.getLong(assignedTo));
			}
		}

		return params;
	}

	public Integer getSatisfiedInt() {
		if (!hasSatisfied()) {
			return null;
		}

		if (isSatisfied()) {
			return TicketFeedbackConstants.SATISFIED_YES;
		}
		else {
			return TicketFeedbackConstants.SATISFIED_NO;
		}
	}

	public Integer getStatus() {
		if (!hasAnswered()) {
			return null;
		}

		if (isAnswered()) {
			return TicketFeedbackConstants.STATUS_ANSWERED;
		}
		else {
			return TicketFeedbackConstants.STATUS_UNANSWERED;
		}
	}

	public boolean hasSearchableAssigneeValues() {
		if ((assignedUserIds.length > 0) ||
			(assignedSupportTeamIds.length > 0) ||
			(assignedPartnerEntryIds.length > 0)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableDateValues() {
		if (((createDateGTDay > 0) && (createDateGTYear > 0)) ||
			((createDateLTDay > 0) && (createDateLTYear > 0))) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableValues() {
		if (hasSearchableAssigneeValues() || hasSearchableDateValues() ||
			Validator.isNotNull(keywords)) {

			return true;
		}
		else {
			return false;
		}
	}

}