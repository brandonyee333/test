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

/**
 * @author Brian Wing Shun Chan
 */
public interface TicketFeedbackFinder {
	public int countByAE_CD_MD_S_S_C_S_R_R_R_R(
		java.lang.String accountEntryName, java.util.Date createDateGT,
		java.util.Date createDateLT, java.util.Date modifiedDateGT,
		java.util.Date modifiedDateLT, java.lang.Integer subject,
		java.lang.Integer satisfied, java.lang.String comments,
		java.lang.Integer status, java.lang.Integer[] ratings1,
		java.lang.Integer[] ratings2, java.lang.Integer[] ratings3,
		java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TicketFeedback> findByKeywords(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKeywords(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TicketFeedback> findByAE_CD_MD_S_S_C_S_R_R_R_R(
		java.lang.String accountEntryName, java.util.Date createDateGT,
		java.util.Date createDateLT, java.util.Date modifiedDateGT,
		java.util.Date modifiedDateLT, java.lang.Integer subject,
		java.lang.Integer satisfied, java.lang.String comments,
		java.lang.Integer status, java.lang.Integer[] ratings1,
		java.lang.Integer[] ratings2, java.lang.Integer[] ratings3,
		java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;
}