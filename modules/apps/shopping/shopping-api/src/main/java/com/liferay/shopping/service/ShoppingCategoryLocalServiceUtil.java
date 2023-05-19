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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.shopping.model.ShoppingCategory;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ShoppingCategory. This utility wraps
 * <code>com.liferay.shopping.service.impl.ShoppingCategoryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCategoryLocalService
 * @generated
 */
public class ShoppingCategoryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingCategoryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ShoppingCategory addCategory(
			long userId, long parentCategoryId, String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCategory(
			userId, parentCategoryId, name, description, serviceContext);
	}

	public static void addCategoryResources(
			long categoryId, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		getService().addCategoryResources(
			categoryId, addGroupPermissions, addGuestPermissions);
	}

	public static void addCategoryResources(
			long categoryId,
			com.liferay.portal.kernel.service.permission.ModelPermissions
				modelPermissions)
		throws PortalException {

		getService().addCategoryResources(categoryId, modelPermissions);
	}

	public static void addCategoryResources(
			ShoppingCategory category, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		getService().addCategoryResources(
			category, addGroupPermissions, addGuestPermissions);
	}

	public static void addCategoryResources(
			ShoppingCategory category,
			com.liferay.portal.kernel.service.permission.ModelPermissions
				modelPermissions)
		throws PortalException {

		getService().addCategoryResources(category, modelPermissions);
	}

	/**
	 * Adds the shopping category to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingCategory the shopping category
	 * @return the shopping category that was added
	 */
	public static ShoppingCategory addShoppingCategory(
		ShoppingCategory shoppingCategory) {

		return getService().addShoppingCategory(shoppingCategory);
	}

	/**
	 * Creates a new shopping category with the primary key. Does not add the shopping category to the database.
	 *
	 * @param categoryId the primary key for the new shopping category
	 * @return the new shopping category
	 */
	public static ShoppingCategory createShoppingCategory(long categoryId) {
		return getService().createShoppingCategory(categoryId);
	}

	public static void deleteCategories(long groupId) throws PortalException {
		getService().deleteCategories(groupId);
	}

	public static void deleteCategory(long categoryId) throws PortalException {
		getService().deleteCategory(categoryId);
	}

	public static void deleteCategory(ShoppingCategory category)
		throws PortalException {

		getService().deleteCategory(category);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the shopping category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param categoryId the primary key of the shopping category
	 * @return the shopping category that was removed
	 * @throws PortalException if a shopping category with the primary key could not be found
	 */
	public static ShoppingCategory deleteShoppingCategory(long categoryId)
		throws PortalException {

		return getService().deleteShoppingCategory(categoryId);
	}

	/**
	 * Deletes the shopping category from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingCategory the shopping category
	 * @return the shopping category that was removed
	 */
	public static ShoppingCategory deleteShoppingCategory(
		ShoppingCategory shoppingCategory) {

		return getService().deleteShoppingCategory(shoppingCategory);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static ShoppingCategory fetchShoppingCategory(long categoryId) {
		return getService().fetchShoppingCategory(categoryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<ShoppingCategory> getCategories(long groupId) {
		return getService().getCategories(groupId);
	}

	public static List<ShoppingCategory> getCategories(
		long groupId, long parentCategoryId, int start, int end) {

		return getService().getCategories(
			groupId, parentCategoryId, start, end);
	}

	public static int getCategoriesCount(long groupId, long parentCategoryId) {
		return getService().getCategoriesCount(groupId, parentCategoryId);
	}

	public static ShoppingCategory getCategory(long categoryId)
		throws PortalException {

		return getService().getCategory(categoryId);
	}

	public static ShoppingCategory getCategory(
		long groupId, String categoryName) {

		return getService().getCategory(groupId, categoryName);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<ShoppingCategory> getParentCategories(long categoryId)
		throws PortalException {

		return getService().getParentCategories(categoryId);
	}

	public static List<ShoppingCategory> getParentCategories(
			ShoppingCategory category)
		throws PortalException {

		return getService().getParentCategories(category);
	}

	public static ShoppingCategory getParentCategory(ShoppingCategory category)
		throws PortalException {

		return getService().getParentCategory(category);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the shopping categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of shopping categories
	 * @param end the upper bound of the range of shopping categories (not inclusive)
	 * @return the range of shopping categories
	 */
	public static List<ShoppingCategory> getShoppingCategories(
		int start, int end) {

		return getService().getShoppingCategories(start, end);
	}

	/**
	 * Returns the number of shopping categories.
	 *
	 * @return the number of shopping categories
	 */
	public static int getShoppingCategoriesCount() {
		return getService().getShoppingCategoriesCount();
	}

	/**
	 * Returns the shopping category with the primary key.
	 *
	 * @param categoryId the primary key of the shopping category
	 * @return the shopping category
	 * @throws PortalException if a shopping category with the primary key could not be found
	 */
	public static ShoppingCategory getShoppingCategory(long categoryId)
		throws PortalException {

		return getService().getShoppingCategory(categoryId);
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

	/**
	 * Updates the shopping category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingCategory the shopping category
	 * @return the shopping category that was updated
	 */
	public static ShoppingCategory updateShoppingCategory(
		ShoppingCategory shoppingCategory) {

		return getService().updateShoppingCategory(shoppingCategory);
	}

	public static ShoppingCategoryLocalService getService() {
		return _service;
	}

	public static void setService(ShoppingCategoryLocalService service) {
		_service = service;
	}

	private static volatile ShoppingCategoryLocalService _service;

}