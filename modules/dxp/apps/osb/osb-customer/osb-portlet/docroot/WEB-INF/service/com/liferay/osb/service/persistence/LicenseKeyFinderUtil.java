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
public class LicenseKeyFinderUtil {
	public static int countByKeywords(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getFinder().countByKeywords(keywords, params);
	}

	public static int countByU_C_M_M_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
		java.lang.Long createUserId, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.Long modifiedUserId,
		java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
		java.lang.String accountEntryName, java.lang.String licenseKeySetName,
		java.util.Date startDateGT, java.util.Date startDateLT,
		long[] licenseEntryIds, long[] productEntryIds,
		java.lang.String productEntryName, java.lang.String productId,
		int[] productVersions, java.lang.String owner,
		java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddress, java.lang.String macAddress,
		java.lang.String serverId, java.lang.String key,
		java.util.Date expirationDateGT, java.util.Date expirationDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getFinder()
				   .countByU_C_M_M_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(createUserId,
			createDateGT, createDateLT, modifiedUserId, modifiedDateGT,
			modifiedDateLT, accountEntryName, licenseKeySetName, startDateGT,
			startDateLT, licenseEntryIds, productEntryIds, productEntryName,
			productId, productVersions, owner, description, hostName,
			ipAddress, macAddress, serverId, key, expirationDateGT,
			expirationDateLT, params, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> findByKeywords(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder().findByKeywords(keywords, params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> findByU_C_M_M_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
		java.lang.Long createUserId, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.Long modifiedUserId,
		java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
		java.lang.String accountEntryName, java.lang.String licenseKeySetName,
		java.util.Date startDateGT, java.util.Date startDateLT,
		long[] licenseEntryIds, long[] productEntryIds,
		java.lang.String productEntryName, java.lang.String productId,
		int[] productVersions, java.lang.String owner,
		java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddress, java.lang.String macAddress,
		java.lang.String serverId, java.lang.String key,
		java.util.Date expirationDateGT, java.util.Date expirationDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder()
				   .findByU_C_M_M_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(createUserId,
			createDateGT, createDateLT, modifiedUserId, modifiedDateGT,
			modifiedDateLT, accountEntryName, licenseKeySetName, startDateGT,
			startDateLT, licenseEntryIds, productEntryIds, productEntryName,
			productId, productVersions, owner, description, hostName,
			ipAddress, macAddress, serverId, key, expirationDateGT,
			expirationDateLT, params, andOperator, start, end, obc);
	}

	public static LicenseKeyFinder getFinder() {
		if (_finder == null) {
			_finder = (LicenseKeyFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					LicenseKeyFinder.class.getName());

			ReferenceRegistry.registerReference(LicenseKeyFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(LicenseKeyFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(LicenseKeyFinderUtil.class,
			"_finder");
	}

	private static LicenseKeyFinder _finder;
}