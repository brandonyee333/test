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
public interface TrainingExamResultFinder {
	public int countByKeywords(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKeywords(long userId, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByU_EI_SD(long userId, java.lang.String testKey,
		java.util.Date startDateGT, java.util.Date startDateLT,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByFN_LN_EA_EI(java.lang.String firstName,
		java.lang.String lastName, java.lang.String emailAddress,
		java.lang.String candidateKey, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TrainingExamResult> findByKeywords(
		java.lang.String keywords, boolean groupByUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TrainingExamResult> findByKeywords(
		long userId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TrainingExamResult> findByU_EI_SD(
		long userId, java.lang.String testKey, java.util.Date startDateGT,
		java.util.Date startDateLT, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.TrainingExamResult> findByFN_LN_EA_EI(
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String emailAddress, java.lang.String candidateKey,
		boolean groupByUserId, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;
}