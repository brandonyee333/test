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

package com.liferay.watson.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WatsonResourceLocalService}.
 *
 * @author Eddie Olson
 * @see WatsonResourceLocalService
 * @generated
 */
@ProviderType
public class WatsonResourceLocalServiceWrapper
	implements WatsonResourceLocalService,
		ServiceWrapper<WatsonResourceLocalService> {
	public WatsonResourceLocalServiceWrapper(
		WatsonResourceLocalService watsonResourceLocalService) {
		_watsonResourceLocalService = watsonResourceLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _watsonResourceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonResourceLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _watsonResourceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _watsonResourceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _watsonResourceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the watson resource to the database. Also notifies the appropriate model listeners.
	*
	* @param watsonResource the watson resource
	* @return the watson resource that was added
	*/
	@Override
	public com.liferay.watson.model.WatsonResource addWatsonResource(
		com.liferay.watson.model.WatsonResource watsonResource) {
		return _watsonResourceLocalService.addWatsonResource(watsonResource);
	}

	/**
	* Creates a new watson resource with the primary key. Does not add the watson resource to the database.
	*
	* @param watsonResourceId the primary key for the new watson resource
	* @return the new watson resource
	*/
	@Override
	public com.liferay.watson.model.WatsonResource createWatsonResource(
		long watsonResourceId) {
		return _watsonResourceLocalService.createWatsonResource(watsonResourceId);
	}

	/**
	* Deletes the watson resource from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonResource the watson resource
	* @return the watson resource that was removed
	*/
	@Override
	public com.liferay.watson.model.WatsonResource deleteWatsonResource(
		com.liferay.watson.model.WatsonResource watsonResource) {
		return _watsonResourceLocalService.deleteWatsonResource(watsonResource);
	}

	/**
	* Deletes the watson resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonResourceId the primary key of the watson resource
	* @return the watson resource that was removed
	* @throws PortalException if a watson resource with the primary key could not be found
	*/
	@Override
	public com.liferay.watson.model.WatsonResource deleteWatsonResource(
		long watsonResourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _watsonResourceLocalService.deleteWatsonResource(watsonResourceId);
	}

	@Override
	public com.liferay.watson.model.WatsonResource fetchWatsonResource(
		long watsonResourceId) {
		return _watsonResourceLocalService.fetchWatsonResource(watsonResourceId);
	}

	/**
	* Returns the watson resource with the primary key.
	*
	* @param watsonResourceId the primary key of the watson resource
	* @return the watson resource
	* @throws PortalException if a watson resource with the primary key could not be found
	*/
	@Override
	public com.liferay.watson.model.WatsonResource getWatsonResource(
		long watsonResourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _watsonResourceLocalService.getWatsonResource(watsonResourceId);
	}

	/**
	* Updates the watson resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param watsonResource the watson resource
	* @return the watson resource that was updated
	*/
	@Override
	public com.liferay.watson.model.WatsonResource updateWatsonResource(
		com.liferay.watson.model.WatsonResource watsonResource) {
		return _watsonResourceLocalService.updateWatsonResource(watsonResource);
	}

	/**
	* Returns the number of watson resources.
	*
	* @return the number of watson resources
	*/
	@Override
	public int getWatsonResourcesCount() {
		return _watsonResourceLocalService.getWatsonResourcesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _watsonResourceLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _watsonResourceLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _watsonResourceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.watson.model.impl.WatsonResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _watsonResourceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.watson.model.impl.WatsonResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _watsonResourceLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the watson resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.watson.model.impl.WatsonResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resources
	* @param end the upper bound of the range of watson resources (not inclusive)
	* @return the range of watson resources
	*/
	@Override
	public java.util.List<com.liferay.watson.model.WatsonResource> getWatsonResources(
		int start, int end) {
		return _watsonResourceLocalService.getWatsonResources(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _watsonResourceLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _watsonResourceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public WatsonResourceLocalService getWrappedService() {
		return _watsonResourceLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonResourceLocalService watsonResourceLocalService) {
		_watsonResourceLocalService = watsonResourceLocalService;
	}

	private WatsonResourceLocalService _watsonResourceLocalService;
}