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
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketFlagConstants;
import com.liferay.osb.service.SupportTeamLocalServiceUtil;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class TicketEntrySearchTerms extends TicketEntryDisplayTerms {

	public TicketEntrySearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		if (ArrayUtil.contains(statuses, TicketEntryConstants.STATUS_OPEN)) {
			statuses = ArrayUtil.remove(
				statuses, TicketEntryConstants.STATUS_OPEN);

			if (isTicketWorker()) {
				statuses = ArrayUtil.append(
					statuses, TicketEntryConstants.STATUSES_WORKER_OPEN);
			}
			else {
				statuses = ArrayUtil.append(
					statuses, TicketEntryConstants.STATUSES_OPEN);
			}
		}

		if (searchFilterBy.equals("answered")) {
			statuses = ArrayUtil.append(
				statuses, TicketEntryConstants.STATUSES_OPEN);
		}
	}

	public TicketEntrySearchTerms(
		PortletRequest portletRequest, String searchFilter) {

		super(portletRequest, searchFilter);

		if (ArrayUtil.contains(statuses, TicketEntryConstants.STATUS_OPEN)) {
			statuses = ArrayUtil.remove(
				statuses, TicketEntryConstants.STATUS_OPEN);

			if (isTicketWorker()) {
				statuses = ArrayUtil.append(
					statuses, TicketEntryConstants.STATUSES_WORKER_OPEN);
			}
			else {
				statuses = ArrayUtil.append(
					statuses, TicketEntryConstants.STATUSES_OPEN);
			}
		}

		if (searchFilterBy.equals("answered")) {
			statuses = ArrayUtil.append(
				statuses, TicketEntryConstants.STATUSES_OPEN);
		}
	}

	public String getActiveFilters() {
		StringBundler sb = new StringBundler(20);

		if (hasSearchableAccountValues()) {
			sb.append("account");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableAssigneeValues()) {
			sb.append("assignee");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableComponentValues()) {
			sb.append("component");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableDateValues()) {
			sb.append("date");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableEnvValues()) {
			sb.append("environment");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableFeedbackValues()) {
			sb.append("feedback");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchablePendingValues()) {
			sb.append("pending");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableSeverityValues()) {
			sb.append("severity");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableStatusValues()) {
			sb.append("status");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableSupportRegionValues()) {
			sb.append("support-region");
			sb.append(StringPool.COMMA);
		}

		return sb.toString();
	}

	public LinkedHashMap<String, Object> getParams() {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		if (!isAdvancedSearch()) {
			return params;
		}

		if (searchFilterBy.equals("answered")) {
			params.put("pendingCustomer", StringPool.BLANK);

			params.put(
				"pendingTypes",
				new int[] {TicketFlagConstants.TYPE_PENDING_CUSTOMER});
		}

		if (accountEntryIds.length > 0) {
			params.put("accountEntryIds", accountEntryIds);
		}

		if ((accountWorkerRoles.length > 0) &&
			(accountWorkerUserIds.length > 0)) {

			long[][] accountWorkers = new long[accountWorkerUserIds.length][2];

			for (int i = 0; i < accountWorkerUserIds.length; i++) {
				accountWorkers[i][0] = accountWorkerUserIds[i];
				accountWorkers[i][1] = accountWorkerRoles[i];
			}

			params.put("accountWorkers", accountWorkers);
		}

		if (pendingTypes.length > 0) {
			params.put("pendingTypes", pendingTypes);
		}

		long[] productEntryIds = getProductEntryIds();

		if (productEntryIds.length > 0) {
			params.put("productEntryIds", productEntryIds);
		}

		if (feedbackAvailable != null) {
			params.put("feedbackAvailable", feedbackAvailable);
		}

		if (searchAttachments != null) {
			params.put("searchAttachments", searchAttachments);
		}
		else {
			params.put("searchAttachments", true);
		}

		if (supportRegionIds.length > 0) {
			params.put("supportRegionIds", supportRegionIds);
		}

		if ((assignedPartnerEntryIds.length > 0) ||
			(assignedSupportTeamIds.length > 0) ||
			(assignedUserIds.length > 0)) {

			if (assignedPartnerEntryIds.length > 0) {
				params.put("partnerEntryIds", assignedPartnerEntryIds);
			}

			if (assignedSupportTeamIds.length > 0) {
				params.put("supportTeamIds", assignedSupportTeamIds);
			}

			if (assignedUserIds.length > 0) {
				params.put("ticketWorkerUserIds", assignedUserIds);
			}
		}
		else if (Validator.isNull(assignedTo)) {
			return params;
		}
		else if (assignedTo.equals("no-one")) {
			params.put("ticketWorkerUserIdsCount", 0L);
		}
		else if (assignedTo.startsWith("partnerEntryId-")) {
			int pos = assignedTo.indexOf(CharPool.DASH);

			long partnerEntryId = GetterUtil.getLong(
				assignedTo.substring(pos + 1));

			long[] partnerEntryIds = SupportUtil.getPartnerEntryIds(
				partnerEntryId);

			params.put("partnerEntryIds", partnerEntryIds);
		}
		else if (assignedTo.startsWith("supportTeamId-")) {
			int pos = assignedTo.indexOf(CharPool.DASH);

			long supportTeamId = GetterUtil.getLong(
				assignedTo.substring(pos + 1));

			List<SupportTeam> childSupportTeams =
				SupportTeamLocalServiceUtil.getChildSupportTeams(
					supportTeamId, true);

			long[] supportTeams = new long[childSupportTeams.size() + 1];

			supportTeams[0] = supportTeamId;

			for (int i = 0; i < childSupportTeams.size(); i++) {
				SupportTeam childSupportTeam = childSupportTeams.get(i);

				supportTeams[i + 1] = childSupportTeam.getSupportTeamId();
			}

			params.put("supportTeamIds", supportTeams);
		}
		else {
			params.put(
				"ticketWorkerUserIds",
				new long[] {GetterUtil.getLong(assignedTo)});
		}

		return params;
	}

	public boolean hasSearchableAccountValues() {
		if ((accountEntryIds.length > 0) || (accountEntryTiers.length > 0)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableAssigneeValues() {
		if ((accountWorkerUserIds.length > 0) || (assignedUserIds.length > 0) ||
			(assignedSupportTeamIds.length > 0) ||
			(assignedPartnerEntryIds.length > 0)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableComponentValues() {
		if (components.length > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableDateValues() {
		if (((closedDateGTDay > 0) && (closedDateGTYear > 0)) ||
			((closedDateLTDay > 0) && (closedDateLTYear > 0)) ||
			((createDateGTDay > 0) && (createDateGTYear > 0)) ||
			((createDateLTDay > 0) && (createDateLTYear > 0)) ||
			((dueDateGTDay > 0) && (dueDateGTYear > 0)) ||
			((dueDateLTDay > 0) && (dueDateLTYear > 0)) ||
			(satisfiedDueDate != null)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableEnvValues() {
		if ((envASIds.length > 0) || (envDBIds.length > 0) ||
			(envJVMIds.length > 0) || (envLFRIds.length > 0) ||
			(envOSIds.length > 0) || (productEntryIds.length > 0)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableFeedbackValues() {
		if (feedbackAvailable != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchablePendingValues() {
		if (pendingTypes.length > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableSeverityValues() {
		if ((escalationLevels.length > 0) || (severities.length > 0)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableStatusValues() {
		if ((resolutions.length > 0) || (statuses.length > 0)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableSupportRegionValues() {
		if (supportRegionIds.length > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableValues() {
		if (hasSearchableAccountValues() || hasSearchableAssigneeValues() ||
			hasSearchableComponentValues() || hasSearchableDateValues() ||
			hasSearchableEnvValues() || hasSearchableFeedbackValues() ||
			hasSearchablePendingValues() || hasSearchableSeverityValues() ||
			hasSearchableStatusValues() || hasSearchableSupportRegionValues() ||
			Validator.isNotNull(keywords)) {

			return true;
		}
		else {
			return false;
		}
	}

}