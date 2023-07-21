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
public interface ShoppingItemFinder {

	public int countByFeatured(long groupId, long[] categoryIds);

	public int countByKeywords(
		long groupId, long[] categoryIds, String keywords);

	public int countByKeywords(
		long groupId, long[] categoryIds, String keywords,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.shopping.model.ShoppingItem> obc);

	public int countBySale(long groupId, long[] categoryIds);

	public int countByG_C(long groupId, java.util.List<Long> categoryIds);

	public int filterCountByG_C(long groupId, java.util.List<Long> categoryIds);

	public java.util.List<com.liferay.shopping.model.ShoppingItem>
		findByFeatured(long groupId, long[] categoryIds, int numOfItems);

	public java.util.List<com.liferay.shopping.model.ShoppingItem>
		findByKeywords(
			long groupId, long[] categoryIds, String keywords, int start,
			int end);

	public java.util.List<com.liferay.shopping.model.ShoppingItem>
		findByKeywords(
			long groupId, long[] categoryIds, String keywords, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.shopping.model.ShoppingItem> obc);

	public java.util.List<com.liferay.shopping.model.ShoppingItem> findBySale(
		long groupId, long[] categoryIds, int numOfItems);

}