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
public class TicketFeedbackFinderUtil {
	public static int countByKeywords(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getFinder().countByKeywords(keywords, params);
	}

	public static int countByAE_CD_MD_S_S_C_S_R_R_R_R(
		java.lang.String accountEntryName, java.util.Date createDateGT,
		java.util.Date createDateLT, java.util.Date modifiedDateGT,
		java.util.Date modifiedDateLT, java.lang.Integer subject,
		java.lang.Integer satisfied, java.lang.String comments,
		java.lang.Integer status, java.lang.Integer[] ratings1,
		java.lang.Integer[] ratings2, java.lang.Integer[] ratings3,
		java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getFinder()
				   .countByAE_CD_MD_S_S_C_S_R_R_R_R(accountEntryName,
			createDateGT, createDateLT, modifiedDateGT, modifiedDateLT,
			subject, satisfied, comments, status, ratings1, ratings2, ratings3,
			ratings4, params, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByKeywords(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder().findByKeywords(keywords, params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TicketFeedback> findByAE_CD_MD_S_S_C_S_R_R_R_R(
		java.lang.String accountEntryName, java.util.Date createDateGT,
		java.util.Date createDateLT, java.util.Date modifiedDateGT,
		java.util.Date modifiedDateLT, java.lang.Integer subject,
		java.lang.Integer satisfied, java.lang.String comments,
		java.lang.Integer status, java.lang.Integer[] ratings1,
		java.lang.Integer[] ratings2, java.lang.Integer[] ratings3,
		java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder()
				   .findByAE_CD_MD_S_S_C_S_R_R_R_R(accountEntryName,
			createDateGT, createDateLT, modifiedDateGT, modifiedDateLT,
			subject, satisfied, comments, status, ratings1, ratings2, ratings3,
			ratings4, params, andOperator, start, end, obc);
	}

	public static TicketFeedbackFinder getFinder() {
		if (_finder == null) {
			_finder = (TicketFeedbackFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketFeedbackFinder.class.getName());

			ReferenceRegistry.registerReference(TicketFeedbackFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TicketFeedbackFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TicketFeedbackFinderUtil.class,
			"_finder");
	}

	private static TicketFeedbackFinder _finder;
}