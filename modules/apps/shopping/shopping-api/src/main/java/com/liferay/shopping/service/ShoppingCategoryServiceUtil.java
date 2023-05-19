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

package com.liferay.shopping.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.shopping.model.ShoppingCategory;

import java.util.List;

/**
 * Provides the remote service utility for ShoppingCategory. This utility wraps
 * <code>com.liferay.shopping.service.impl.ShoppingCategoryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCategoryService
 * @generated
 */
public class ShoppingCategoryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingCategoryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ShoppingCategory addCategory(
			long parentCategoryId, String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCategory(
			parentCategoryId, name, description, serviceContext);
	}

	public static void deleteCategory(long categoryId) throws PortalException {
		getService().deleteCategory(categoryId);
	}

	public static List<ShoppingCategory> getCategories(long groupId) {
		return getService().getCategories(groupId);
	}

	public static List<ShoppingCategory> getCategories(
		long groupId, long parentCategoryId, int start, int end) {

		return getService().getCategories(
			groupId, parentCategoryId, start, end);
	}

	public static List<Object> getCategoriesAndItems(
		long groupId, long categoryId, int start, int end,
		OrderByComparator<?> obc) {

		return getService().getCategoriesAndItems(
			groupId, categoryId, start, end, obc);
	}

	public static int getCategoriesAndItemsCount(
		long groupId, long categoryId) {

		return getService().getCategoriesAndItemsCount(groupId, categoryId);
	}

	public static int getCategoriesCount(long groupId, long parentCategoryId) {
		return getService().getCategoriesCount(groupId, parentCategoryId);
	}

	public static ShoppingCategory getCategory(long categoryId)
		throws PortalException {

		return getService().getCategory(categoryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void getSubcategoryIds(
		List<Long> categoryIds, long groupId, long categoryId) {

		getService().getSubcategoryIds(categoryIds, groupId, categoryId);
	}

	public static ShoppingCategory updateCategory(
			long categoryId, long parentCategoryId, String name,
			String description, boolean mergeWithParentCategory,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCategory(
			categoryId, parentCategoryId, name, description,
			mergeWithParentCategory, serviceContext);
	}

	public static ShoppingCategoryService getService() {
		return _service;
	}

	public static void setService(ShoppingCategoryService service) {
		_service = service;
	}

	private static volatile ShoppingCategoryService _service;

}