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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TicketFeedbackService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackService
 * @generated
 */
@ProviderType
public class TicketFeedbackServiceWrapper implements TicketFeedbackService,
	ServiceWrapper<TicketFeedbackService> {
	public TicketFeedbackServiceWrapper(
		TicketFeedbackService ticketFeedbackService) {
		_ticketFeedbackService = ticketFeedbackService;
	}

	@Override
	public com.liferay.osb.model.TicketFeedback addTicketFeedback(
		long ticketEntryId, int subject, int satisfied)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackService.addTicketFeedback(ticketEntryId, subject,
			satisfied);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback fetchFirstOpenTicketFeedback(
		long userId, long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackService.fetchFirstOpenTicketFeedback(userId,
			ticketEntryId, subject);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback fetchFirstTicketFeedback(
		long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackService.fetchFirstTicketFeedback(ticketEntryId,
			subject);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback getTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackService.getTicketFeedback(ticketFeedbackId);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		long ticketFeedbackId, int satisfied, int answer1, int answer2,
		int answer3, int rating1, int rating2, int rating3, int rating4,
		java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackService.updateTicketFeedback(ticketFeedbackId,
			satisfied, answer1, answer2, answer3, rating1, rating2, rating3,
			rating4, comments);
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackService.searchCount(keywords, params);
	}

	@Override
	public int searchCount(java.lang.String name, int createdGTDay,
		int createdGTMonth, int createdGTYear, int createdLTDay,
		int createdLTMonth, int createdLTYear, int modifiedGTDay,
		int modifiedGTMonth, int modifiedGTYear, int modifiedLTDay,
		int modifiedLTMonth, int modifiedLTYear, java.lang.Integer satisfied,
		java.lang.String comments, java.lang.Integer status,
		java.lang.Integer[] ratings1, java.lang.Integer[] ratings2,
		java.lang.Integer[] ratings3, java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackService.searchCount(name, createdGTDay,
			createdGTMonth, createdGTYear, createdLTDay, createdLTMonth,
			createdLTYear, modifiedGTDay, modifiedGTMonth, modifiedGTYear,
			modifiedLTDay, modifiedLTMonth, modifiedLTYear, satisfied,
			comments, status, ratings1, ratings2, ratings3, ratings4, params,
			andSearch);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketFeedbackService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketFeedbackService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackService.getTicketFeedbacks(ticketEntryId, subject);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketFeedback> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackService.search(keywords, params, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketFeedback> search(
		java.lang.String name, int createdGTDay, int createdGTMonth,
		int createdGTYear, int createdLTDay, int createdLTMonth,
		int createdLTYear, int modifiedGTDay, int modifiedGTMonth,
		int modifiedGTYear, int modifiedLTDay, int modifiedLTMonth,
		int modifiedLTYear, java.lang.Integer satisfied,
		java.lang.String comments, java.lang.Integer status,
		java.lang.Integer[] ratings1, java.lang.Integer[] ratings2,
		java.lang.Integer[] ratings3, java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedbackService.search(name, createdGTDay,
			createdGTMonth, createdGTYear, createdLTDay, createdLTMonth,
			createdLTYear, modifiedGTDay, modifiedGTMonth, modifiedGTYear,
			modifiedLTDay, modifiedLTMonth, modifiedLTYear, satisfied,
			comments, status, ratings1, ratings2, ratings3, ratings4, params,
			andSearch, start, end, obc);
	}

	@Override
	public TicketFeedbackService getWrappedService() {
		return _ticketFeedbackService;
	}

	@Override
	public void setWrappedService(TicketFeedbackService ticketFeedbackService) {
		_ticketFeedbackService = ticketFeedbackService;
	}

	private TicketFeedbackService _ticketFeedbackService;
}