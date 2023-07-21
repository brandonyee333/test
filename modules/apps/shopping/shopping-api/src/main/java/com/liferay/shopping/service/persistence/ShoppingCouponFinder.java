/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface ShoppingCouponFinder {

	public int countByG_C_C_A_DT(
		long groupId, long companyId, String code, boolean active,
		String discountType, boolean andOperator);

	public java.util.List<com.liferay.shopping.model.ShoppingCoupon>
		findByG_C_C_A_DT(
			long groupId, long companyId, String code, boolean active,
			String discountType, boolean andOperator, int start, int end);

}