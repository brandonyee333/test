/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface AccountEntryFinder {

	public int countByKeywords(
		String keywords, java.util.LinkedHashMap<String, Object> params);

	public int countByKAK_DAK_N_C_I_S(
		String koroneikiAccountKey, String dossieraAccountKey, String name,
		String code, int[] statuses, String instructions,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator);

	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		findByExpiredSupport(int start, int end);

	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		findByKeywords(
			String keywords, java.util.LinkedHashMap<String, Object> params,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc);

	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		findByKAK_DAK_N_C_I_S(
			String koroneikiAccountKey, String dossieraAccountKey, String name,
			String code, int[] statuses, String instructions,
			java.util.LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc);

}