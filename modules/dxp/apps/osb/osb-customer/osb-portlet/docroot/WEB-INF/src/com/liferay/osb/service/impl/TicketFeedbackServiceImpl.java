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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.service.base.TicketFeedbackServiceBaseImpl;
import com.liferay.osb.service.permission.OSBTicketFeedbackPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 * @author Mate Thurzo
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class TicketFeedbackServiceImpl extends TicketFeedbackServiceBaseImpl {

	public TicketFeedback addTicketFeedback(
			long ticketEntryId, int subject, int satisfied)
		throws PortalException {

		if (subject == TicketFeedbackConstants.SUBJECT_LIFERAY) {
			OSBTicketFeedbackPermission.checkSubjectLiferay(
				getPermissionChecker(), ticketEntryId, OSBActionKeys.UPDATE);
		}
		else {
			throw new PrincipalException();
		}

		return ticketFeedbackLocalService.addTicketFeedback(
			getUserId(), ticketEntryId, subject, satisfied);
	}

	public TicketFeedback fetchFirstOpenTicketFeedback(
			long userId, long ticketEntryId, int subject)
		throws PortalException {

		TicketFeedback ticketFeedback =
			ticketFeedbackLocalService.fetchFirstOpenTicketFeedback(
				userId, ticketEntryId, subject);

		if (ticketFeedback != null) {
			OSBTicketFeedbackPermission.check(
				getPermissionChecker(), ticketFeedback, OSBActionKeys.VIEW);
		}

		return ticketFeedback;
	}

	public TicketFeedback fetchFirstTicketFeedback(
			long ticketEntryId, int subject)
		throws PortalException {

		TicketFeedback ticketFeedback =
			ticketFeedbackPersistence.fetchByTEI_S_First(
				ticketEntryId, subject, null);

		if (ticketFeedback != null) {
			OSBTicketFeedbackPermission.check(
				getPermissionChecker(), ticketFeedback, OSBActionKeys.VIEW);
		}

		return ticketFeedback;
	}

	public TicketFeedback getTicketFeedback(long ticketFeedbackId)
		throws PortalException {

		TicketFeedback ticketFeedback =
			ticketFeedbackLocalService.getTicketFeedback(ticketFeedbackId);

		OSBTicketFeedbackPermission.check(
			getPermissionChecker(), ticketFeedback, OSBActionKeys.VIEW);

		return ticketFeedback;
	}

	public List<TicketFeedback> getTicketFeedbacks(
			long ticketEntryId, int subject)
		throws PortalException {

		List<TicketFeedback> ticketFeedbacks =
			ticketFeedbackLocalService.getTicketFeedbacks(
				ticketEntryId, subject);

		for (TicketFeedback ticketFeedback : ticketFeedbacks) {
			OSBTicketFeedbackPermission.check(
				getPermissionChecker(), ticketFeedback, OSBActionKeys.VIEW);
		}

		return ticketFeedbacks;
	}

	public List<TicketFeedback> search(
			String name, int createdGTDay, int createdGTMonth,
			int createdGTYear, int createdLTDay, int createdLTMonth,
			int createdLTYear, int modifiedGTDay, int modifiedGTMonth,
			int modifiedGTYear, int modifiedLTDay, int modifiedLTMonth,
			int modifiedLTYear, Integer satisfied, String comments,
			Integer status, Integer[] ratings1, Integer[] ratings2,
			Integer[] ratings3, Integer[] ratings4,
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end, OrderByComparator obc)
		throws PortalException {

		addAccountEntryMembershipParams(params);

		return ticketFeedbackLocalService.search(
			name, createdGTDay, createdGTMonth, createdGTYear, createdLTDay,
			createdLTMonth, createdLTYear, modifiedGTDay, modifiedGTMonth,
			modifiedGTYear, modifiedLTDay, modifiedLTMonth, modifiedLTYear,
			satisfied, comments, status, ratings1, ratings2, ratings3, ratings4,
			params, andSearch, start, end, obc);
	}

	public List<TicketFeedback> search(
			String keywords, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws PortalException {

		addAccountEntryMembershipParams(params);

		return ticketFeedbackLocalService.search(
			keywords, params, start, end, obc);
	}

	public int searchCount(
			String name, int createdGTDay, int createdGTMonth,
			int createdGTYear, int createdLTDay, int createdLTMonth,
			int createdLTYear, int modifiedGTDay, int modifiedGTMonth,
			int modifiedGTYear, int modifiedLTDay, int modifiedLTMonth,
			int modifiedLTYear, Integer satisfied, String comments,
			Integer status, Integer[] ratings1, Integer[] ratings2,
			Integer[] ratings3, Integer[] ratings4,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws PortalException {

		addAccountEntryMembershipParams(params);

		return ticketFeedbackLocalService.searchCount(
			name, createdGTDay, createdGTMonth, createdGTYear, createdLTDay,
			createdLTMonth, createdLTYear, modifiedGTDay, modifiedGTMonth,
			modifiedGTYear, modifiedLTDay, modifiedLTMonth, modifiedLTYear,
			satisfied, comments, status, ratings1, ratings2, ratings3, ratings4,
			params, andSearch);
	}

	public int searchCount(
			String keywords, LinkedHashMap<String, Object> params)
		throws PortalException {

		addAccountEntryMembershipParams(params);

		return ticketFeedbackLocalService.searchCount(keywords, params);
	}

	public TicketFeedback updateTicketFeedback(
			long ticketFeedbackId, int satisfied, int answer1, int answer2,
			int answer3, int rating1, int rating2, int rating3, int rating4,
			String comments)
		throws PortalException {

		OSBTicketFeedbackPermission.check(
			getPermissionChecker(), ticketFeedbackId, OSBActionKeys.UPDATE);

		return ticketFeedbackLocalService.updateTicketFeedback(
			getUserId(), ticketFeedbackId, satisfied, answer1, answer2, answer3,
			rating1, rating2, rating3, rating4, comments);
	}

	protected void addAccountEntryMembershipParams(
			LinkedHashMap<String, Object> params)
		throws PortalException {

		if (!organizationLocalService.hasUserOrganization(
				getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			params.put("accountEntryMembership", getUserId());
		}
	}

}