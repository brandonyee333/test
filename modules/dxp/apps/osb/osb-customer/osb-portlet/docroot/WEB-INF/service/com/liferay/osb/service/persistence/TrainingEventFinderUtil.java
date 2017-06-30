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
public class TrainingEventFinderUtil {
	public static int countByKeywords(java.lang.String keywords,
		java.lang.Long regionId, java.lang.Long countryId,
		java.util.Date startDateGT, java.util.Date startDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByKeywords(keywords, regionId, countryId, startDateGT,
			startDateLT, params);
	}

	public static int countByEndDate(java.util.Date endDateGT,
		java.util.Date endDateLT)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByEndDate(endDateGT, endDateLT);
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByUserId(userId);
	}

	public static int countByTCI_GtED(long[] trainingCourseIds,
		java.util.Date endDateGT)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByTCI_GtED(trainingCourseIds, endDateGT);
	}

	public static int countByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
		java.lang.Integer type, java.lang.String name, java.lang.String course,
		java.lang.Integer portalMinorVersion, java.lang.String city,
		java.lang.Long regionId, java.lang.Long countryId,
		java.lang.String language, java.lang.String partner,
		java.util.Date startDateGT, java.util.Date startDateLT,
		java.lang.String trainerFirstName, java.lang.String trainerLastName,
		java.lang.String trainerEmailAddress,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(type, name,
			course, portalMinorVersion, city, regionId, countryId, language,
			partner, startDateGT, startDateLT, trainerFirstName,
			trainerLastName, trainerEmailAddress, params, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByKeywords(
		java.lang.String keywords, java.lang.Long regionId,
		java.lang.Long countryId, java.util.Date startDateGT,
		java.util.Date startDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(keywords, regionId, countryId, startDateGT,
			startDateLT, params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByEndDate(
		java.util.Date endDateGT, java.util.Date endDateLT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByEndDate(endDateGT, endDateLT, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByParams(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByParams(params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByUserId(userId, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTCI_GtED(
		long[] trainingCourseIds, java.util.Date endDateGT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByTCI_GtED(trainingCourseIds, endDateGT, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
		java.lang.Integer type, java.lang.String name, java.lang.String course,
		java.lang.Integer portalMinorVersion, java.lang.String city,
		java.lang.Long regionId, java.lang.Long countryId,
		java.lang.String language, java.lang.String partner,
		java.util.Date startDateGT, java.util.Date startDateLT,
		java.lang.String trainerFirstName, java.lang.String trainerLastName,
		java.lang.String trainerEmailAddress,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(type, name,
			course, portalMinorVersion, city, regionId, countryId, language,
			partner, startDateGT, startDateLT, trainerFirstName,
			trainerLastName, trainerEmailAddress, params, andOperator, start,
			end, obc);
	}

	public static TrainingEventFinder getFinder() {
		if (_finder == null) {
			_finder = (TrainingEventFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingEventFinder.class.getName());

			ReferenceRegistry.registerReference(TrainingEventFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TrainingEventFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TrainingEventFinderUtil.class,
			"_finder");
	}

	private static TrainingEventFinder _finder;
}