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
public class SupportWorkerFinderUtil {
	public static int countByKeywords(long supportLaborId,
		java.lang.String keywords) {
		return getFinder().countByKeywords(supportLaborId, keywords);
	}

	public static int countByU_E(java.lang.Boolean overUtilization,
		int escalationLevel,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getFinder().countByU_E(overUtilization, escalationLevel, params);
	}

	public static int countBySL_FN_MN_LN_SN_EA_STN(long supportLaborId,
		java.lang.String[] firstNames, java.lang.String[] middleNames,
		java.lang.String[] lastNames, java.lang.String[] screenNames,
		java.lang.String[] emailAddresses, java.lang.String[] supportTeamNames,
		boolean andOperator) {
		return getFinder()
				   .countBySL_FN_MN_LN_SN_EA_STN(supportLaborId, firstNames,
			middleNames, lastNames, screenNames, emailAddresses,
			supportTeamNames, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> findByKeywords(
		long supportLaborId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder()
				   .findByKeywords(supportLaborId, keywords, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> findBySupportTeamId(
		long supportTeamId) {
		return getFinder().findBySupportTeamId(supportTeamId);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> findByU_E(
		java.lang.Boolean overUtilization, int escalationLevel,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getFinder().findByU_E(overUtilization, escalationLevel, params);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> findByR_STT_SRI(
		int role, java.lang.Integer supportTeamType, long supportRegionId,
		java.lang.String roleComparator, boolean filterByAutoAssign,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getFinder()
				   .findByR_STT_SRI(role, supportTeamType, supportRegionId,
			roleComparator, filterByAutoAssign, params);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorker> findBySL_FN_MN_LN_SN_EA_STN(
		long supportLaborId, java.lang.String[] firstNames,
		java.lang.String[] middleNames, java.lang.String[] lastNames,
		java.lang.String[] screenNames, java.lang.String[] emailAddresses,
		java.lang.String[] supportTeamNames, boolean andOperator, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder()
				   .findBySL_FN_MN_LN_SN_EA_STN(supportLaborId, firstNames,
			middleNames, lastNames, screenNames, emailAddresses,
			supportTeamNames, andOperator, start, end, obc);
	}

	public static SupportWorkerFinder getFinder() {
		if (_finder == null) {
			_finder = (SupportWorkerFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportWorkerFinder.class.getName());

			ReferenceRegistry.registerReference(SupportWorkerFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SupportWorkerFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SupportWorkerFinderUtil.class,
			"_finder");
	}

	private static SupportWorkerFinder _finder;
}