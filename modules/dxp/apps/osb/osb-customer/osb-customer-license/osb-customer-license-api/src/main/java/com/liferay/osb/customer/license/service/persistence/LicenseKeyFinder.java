/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface LicenseKeyFinder {

	public int countByKeywords(
		String keywords, java.util.LinkedHashMap<String, Object> params);

	public int countByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
		Long createUserId, java.util.Date createDateGT,
		java.util.Date createDateLT, Long modifiedUserId,
		java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
		String koroneikiAccountKey, String koroneikiProductPurchaseKey,
		String accountEntryName, String licenseKeySetName,
		java.util.Date startDateGT, java.util.Date startDateLT,
		long[] licenseEntryIds, long[] productEntryIds, String productEntryName,
		String productId, int[] productVersions, String owner,
		String description, String hostName, String ipAddress,
		String macAddress, String serverId, String key,
		java.util.Date expirationDateGT, java.util.Date expirationDateLT,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator);

	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		findByKeywords(
			String keywords, java.util.LinkedHashMap<String, Object> params,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc);

	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		findByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
			Long createUserId, java.util.Date createDateGT,
			java.util.Date createDateLT, Long modifiedUserId,
			java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
			String koroneikiAccountKey, String koroneikiProductPurchaseKey,
			String accountEntryName, String licenseKeySetName,
			java.util.Date startDateGT, java.util.Date startDateLT,
			long[] licenseEntryIds, long[] productEntryIds,
			String productEntryName, String productId, int[] productVersions,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			java.util.Date expirationDateGT, java.util.Date expirationDateLT,
			java.util.LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc);

}