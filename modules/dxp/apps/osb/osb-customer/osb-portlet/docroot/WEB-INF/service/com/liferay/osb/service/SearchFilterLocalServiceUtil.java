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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for SearchFilter. This utility wraps
 * {@link com.liferay.osb.service.impl.SearchFilterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SearchFilterLocalService
 * @see com.liferay.osb.service.base.SearchFilterLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SearchFilterLocalServiceImpl
 * @generated
 */
@ProviderType
public class SearchFilterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SearchFilterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the search filter to the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilter the search filter
	* @return the search filter that was added
	*/
	public static com.liferay.osb.model.SearchFilter addSearchFilter(
		com.liferay.osb.model.SearchFilter searchFilter) {
		return getService().addSearchFilter(searchFilter);
	}

	public static com.liferay.osb.model.SearchFilter addSearchFilter(
		long userId, long classNameId, java.lang.String name,
		java.lang.String filter, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addSearchFilter(userId, classNameId, name, filter,
			visibility);
	}

	/**
	* Creates a new search filter with the primary key. Does not add the search filter to the database.
	*
	* @param searchFilterId the primary key for the new search filter
	* @return the new search filter
	*/
	public static com.liferay.osb.model.SearchFilter createSearchFilter(
		long searchFilterId) {
		return getService().createSearchFilter(searchFilterId);
	}

	/**
	* Deletes the search filter from the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilter the search filter
	* @return the search filter that was removed
	*/
	public static com.liferay.osb.model.SearchFilter deleteSearchFilter(
		com.liferay.osb.model.SearchFilter searchFilter) {
		return getService().deleteSearchFilter(searchFilter);
	}

	/**
	* Deletes the search filter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter that was removed
	* @throws PortalException if a search filter with the primary key could not be found
	*/
	public static com.liferay.osb.model.SearchFilter deleteSearchFilter(
		long searchFilterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSearchFilter(searchFilterId);
	}

	public static com.liferay.osb.model.SearchFilter fetchSearchFilter(
		long searchFilterId) {
		return getService().fetchSearchFilter(searchFilterId);
	}

	/**
	* Returns the search filter with the primary key.
	*
	* @param searchFilterId the primary key of the search filter
	* @return the search filter
	* @throws PortalException if a search filter with the primary key could not be found
	*/
	public static com.liferay.osb.model.SearchFilter getSearchFilter(
		long searchFilterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSearchFilter(searchFilterId);
	}

	/**
	* Updates the search filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param searchFilter the search filter
	* @return the search filter that was updated
	*/
	public static com.liferay.osb.model.SearchFilter updateSearchFilter(
		com.liferay.osb.model.SearchFilter searchFilter) {
		return getService().updateSearchFilter(searchFilter);
	}

	public static com.liferay.osb.model.SearchFilter updateSearchFilter(
		long searchFilterId, java.lang.String name, java.lang.String filter,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSearchFilter(searchFilterId, name, filter, visibility);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of search filters.
	*
	* @return the number of search filters
	*/
	public static int getSearchFiltersCount() {
		return getService().getSearchFiltersCount();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the search filters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SearchFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search filters
	* @param end the upper bound of the range of search filters (not inclusive)
	* @return the range of search filters
	*/
	public static java.util.List<com.liferay.osb.model.SearchFilter> getSearchFilters(
		int start, int end) {
		return getService().getSearchFilters(start, end);
	}

	public static java.util.List<com.liferay.osb.model.SearchFilter> getSearchFilters(
		long userId, long classNameId) {
		return getService().getSearchFilters(userId, classNameId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
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
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void deleteSearchFilters(long userId) {
		getService().deleteSearchFilters(userId);
	}

	public static void clearService() {
		_service = null;
	}

	public static SearchFilterLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SearchFilterLocalService.class.getName());

			if (invokableLocalService instanceof SearchFilterLocalService) {
				_service = (SearchFilterLocalService)invokableLocalService;
			}
			else {
				_service = new SearchFilterLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SearchFilterLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SearchFilterLocalService _service;
}