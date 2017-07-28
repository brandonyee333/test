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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for TicketFeedback. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketFeedbackServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackService
 * @see com.liferay.osb.service.base.TicketFeedbackServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketFeedbackServiceImpl
 * @generated
 */
@ProviderType
public class TicketFeedbackServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketFeedbackServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.TicketFeedback addTicketFeedback(
		long ticketEntryId, int subject, int satisfied)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addTicketFeedback(ticketEntryId, subject, satisfied);
	}

	public static com.liferay.osb.model.TicketFeedback fetchFirstOpenTicketFeedback(
		long userId, long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .fetchFirstOpenTicketFeedback(userId, ticketEntryId, subject);
	}

	public static com.liferay.osb.model.TicketFeedback fetchFirstTicketFeedback(
		long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchFirstTicketFeedback(ticketEntryId, subject);
	}

	public static com.liferay.osb.model.TicketFeedback getTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketFeedback(ticketFeedbackId);
	}

	public static com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		long ticketFeedbackId, int satisfied, int answer1, int answer2,
		int answer3, int rating1, int rating2, int rating3, int rating4,
		java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketFeedback(ticketFeedbackId, satisfied, answer1,
			answer2, answer3, rating1, rating2, rating3, rating4, comments);
	}

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().searchCount(keywords, params);
	}

	public static int searchCount(java.lang.String name, int createdGTDay,
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
		return getService()
				   .searchCount(name, createdGTDay, createdGTMonth,
			createdGTYear, createdLTDay, createdLTMonth, createdLTYear,
			modifiedGTDay, modifiedGTMonth, modifiedGTYear, modifiedLTDay,
			modifiedLTMonth, modifiedLTYear, satisfied, comments, status,
			ratings1, ratings2, ratings3, ratings4, params, andSearch);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketFeedbacks(ticketEntryId, subject);
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().search(keywords, params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> search(
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
		return getService()
				   .search(name, createdGTDay, createdGTMonth, createdGTYear,
			createdLTDay, createdLTMonth, createdLTYear, modifiedGTDay,
			modifiedGTMonth, modifiedGTYear, modifiedLTDay, modifiedLTMonth,
			modifiedLTYear, satisfied, comments, status, ratings1, ratings2,
			ratings3, ratings4, params, andSearch, start, end, obc);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketFeedbackService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketFeedbackService.class.getName());

			if (invokableService instanceof TicketFeedbackService) {
				_service = (TicketFeedbackService)invokableService;
			}
			else {
				_service = new TicketFeedbackServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(TicketFeedbackServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketFeedbackService _service;
}