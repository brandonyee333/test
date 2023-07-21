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
public interface ShoppingCategoryFinder {

	public int countC_I_ByG_C(long groupId, long categoryId);

	public int filterCountC_I_ByG_C(long groupId, long categoryId);

	public java.util.List<Object> filterFindC_I_ByG_C(
		long groupId, long categoryId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<?> queryDefinition);

	public java.util.List<Object> findC_I_ByG_C(
		long groupId, long categoryId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<?> queryDefinition);

}