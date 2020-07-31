/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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