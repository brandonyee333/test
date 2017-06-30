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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingExamResultFinderUtil {
	public static int countByKeywords(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByKeywords(keywords);
	}

	public static int countByKeywords(long userId, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByKeywords(userId, keywords);
	}

	public static int countByU_EI_SD(long userId, java.lang.String testKey,
		java.util.Date startDateGT, java.util.Date startDateLT,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByU_EI_SD(userId, testKey, startDateGT, startDateLT,
			andOperator);
	}

	public static int countByFN_LN_EA_EI(java.lang.String firstName,
		java.lang.String lastName, java.lang.String emailAddress,
		java.lang.String candidateKey, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByFN_LN_EA_EI(firstName, lastName, emailAddress,
			candidateKey, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findByKeywords(
		java.lang.String keywords, boolean groupByUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(keywords, groupByUserId, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findByKeywords(
		long userId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByKeywords(userId, keywords, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findByU_EI_SD(
		long userId, java.lang.String testKey, java.util.Date startDateGT,
		java.util.Date startDateLT, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByU_EI_SD(userId, testKey, startDateGT, startDateLT,
			andOperator, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findByFN_LN_EA_EI(
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String emailAddress, java.lang.String candidateKey,
		boolean groupByUserId, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByFN_LN_EA_EI(firstName, lastName, emailAddress,
			candidateKey, groupByUserId, andOperator, start, end, obc);
	}

	public static TrainingExamResultFinder getFinder() {
		if (_finder == null) {
			_finder = (TrainingExamResultFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingExamResultFinder.class.getName());

			ReferenceRegistry.registerReference(TrainingExamResultFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TrainingExamResultFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TrainingExamResultFinderUtil.class,
			"_finder");
	}

	private static TrainingExamResultFinder _finder;
}