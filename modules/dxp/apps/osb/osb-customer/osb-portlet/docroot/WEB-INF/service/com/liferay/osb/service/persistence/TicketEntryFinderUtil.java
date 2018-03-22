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

package com.liferay.osb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketEntryFinderUtil {
	public static int countByKeywords(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getFinder().countByKeywords(keywords, params);
	}

	public static int countByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
		long reportedByUserId, java.lang.String name, int[] accountEntryTiers,
		java.lang.Boolean satisfiedDueDate, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.String subject,
		java.lang.String description, java.lang.String body, int[] statuses,
		int[] severities, int[] weights, int[] escalationLevels, long[] envOS,
		long[] envDB, long[] envJVM, long[] envAS, long[] envLFR,
		int[] components, int[] resolutions, java.util.Date closedDateGT,
		java.util.Date closedDateLT, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.util.Date customerModifiedDateGT,
		java.util.Date customerModifiedDateLT,
		java.util.Date workerModifiedDateGT,
		java.util.Date workerModifiedDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getFinder()
				   .countByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(reportedByUserId,
			name, accountEntryTiers, satisfiedDueDate, createDateGT,
			createDateLT, subject, description, body, statuses, severities,
			weights, escalationLevels, envOS, envDB, envJVM, envAS, envLFR,
			components, resolutions, closedDateGT, closedDateLT, dueDateGT,
			dueDateLT, customerModifiedDateGT, customerModifiedDateLT,
			workerModifiedDateGT, workerModifiedDateLT, params, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> findByKeywords(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder().findByKeywords(keywords, params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> findByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
		long reportedByUserId, java.lang.String name, int[] accountEntryTiers,
		java.lang.Boolean satisfiedDueDate, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.String subject,
		java.lang.String description, java.lang.String body, int[] statuses,
		int[] severities, int[] weights, int[] escalationLevels, long[] envOS,
		long[] envDB, long[] envJVM, long[] envAS, long[] envLFR,
		int[] components, int[] resolutions, java.util.Date closedDateGT,
		java.util.Date closedDateLT, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.util.Date customerModifiedDateGT,
		java.util.Date customerModifiedDateLT,
		java.util.Date workerModifiedDateGT,
		java.util.Date workerModifiedDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder()
				   .findByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(reportedByUserId,
			name, accountEntryTiers, satisfiedDueDate, createDateGT,
			createDateLT, subject, description, body, statuses, severities,
			weights, escalationLevels, envOS, envDB, envJVM, envAS, envLFR,
			components, resolutions, closedDateGT, closedDateLT, dueDateGT,
			dueDateLT, customerModifiedDateGT, customerModifiedDateLT,
			workerModifiedDateGT, workerModifiedDateLT, params, andOperator,
			start, end, obc);
	}

	public static TicketEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (TicketEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketEntryFinder.class.getName());

			ReferenceRegistry.registerReference(TicketEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TicketEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TicketEntryFinderUtil.class,
			"_finder");
	}

	private static TicketEntryFinder _finder;
}