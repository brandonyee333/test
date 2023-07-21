/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.kernel.model;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public interface MBCategoryDisplay {

	public List<MBCategory> getAllCategories();

	public int getAllCategoriesCount();

	public List<MBCategory> getCategories();

	public List<MBCategory> getCategories(MBCategory category);

	public MBCategory getRootCategory();

	public int getSubcategoriesCount(MBCategory category);

	public int getSubcategoriesMessagesCount(MBCategory category);

	public int getSubcategoriesThreadsCount(MBCategory category);

	public void getSubcategoryIds(MBCategory category, List<Long> categoryIds);

}