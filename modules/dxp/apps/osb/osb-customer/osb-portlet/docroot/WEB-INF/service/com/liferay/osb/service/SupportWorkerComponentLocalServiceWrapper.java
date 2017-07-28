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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SupportWorkerComponentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerComponentLocalService
 * @generated
 */
@ProviderType
public class SupportWorkerComponentLocalServiceWrapper
	implements SupportWorkerComponentLocalService,
		ServiceWrapper<SupportWorkerComponentLocalService> {
	public SupportWorkerComponentLocalServiceWrapper(
		SupportWorkerComponentLocalService supportWorkerComponentLocalService) {
		_supportWorkerComponentLocalService = supportWorkerComponentLocalService;
	}

	/**
	* Adds the support worker component to the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponent the support worker component
	* @return the support worker component that was added
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerComponent addSupportWorkerComponent(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent) {
		return _supportWorkerComponentLocalService.addSupportWorkerComponent(supportWorkerComponent);
	}

	/**
	* Creates a new support worker component with the primary key. Does not add the support worker component to the database.
	*
	* @param supportWorkerComponentId the primary key for the new support worker component
	* @return the new support worker component
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerComponent createSupportWorkerComponent(
		long supportWorkerComponentId) {
		return _supportWorkerComponentLocalService.createSupportWorkerComponent(supportWorkerComponentId);
	}

	/**
	* Deletes the support worker component from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponent the support worker component
	* @return the support worker component that was removed
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerComponent deleteSupportWorkerComponent(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent) {
		return _supportWorkerComponentLocalService.deleteSupportWorkerComponent(supportWorkerComponent);
	}

	/**
	* Deletes the support worker component with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component that was removed
	* @throws PortalException if a support worker component with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerComponent deleteSupportWorkerComponent(
		long supportWorkerComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerComponentLocalService.deleteSupportWorkerComponent(supportWorkerComponentId);
	}

	@Override
	public com.liferay.osb.model.SupportWorkerComponent fetchSupportWorkerComponent(
		long supportWorkerComponentId) {
		return _supportWorkerComponentLocalService.fetchSupportWorkerComponent(supportWorkerComponentId);
	}

	/**
	* Returns the support worker component with the primary key.
	*
	* @param supportWorkerComponentId the primary key of the support worker component
	* @return the support worker component
	* @throws PortalException if a support worker component with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerComponent getSupportWorkerComponent(
		long supportWorkerComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerComponentLocalService.getSupportWorkerComponent(supportWorkerComponentId);
	}

	/**
	* Updates the support worker component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerComponent the support worker component
	* @return the support worker component that was updated
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerComponent updateSupportWorkerComponent(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent) {
		return _supportWorkerComponentLocalService.updateSupportWorkerComponent(supportWorkerComponent);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _supportWorkerComponentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportWorkerComponentLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _supportWorkerComponentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerComponentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerComponentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of support worker components.
	*
	* @return the number of support worker components
	*/
	@Override
	public int getSupportWorkerComponentsCount() {
		return _supportWorkerComponentLocalService.getSupportWorkerComponentsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportWorkerComponentLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _supportWorkerComponentLocalService.getOSGiServiceIdentifier();
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
		return _supportWorkerComponentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportWorkerComponentLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportWorkerComponentLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the support worker components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker components
	* @param end the upper bound of the range of support worker components (not inclusive)
	* @return the range of support worker components
	*/
	@Override
	public java.util.List<com.liferay.osb.model.SupportWorkerComponent> getSupportWorkerComponents(
		int start, int end) {
		return _supportWorkerComponentLocalService.getSupportWorkerComponents(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorkerComponent> getSupportWorkerComponents(
		long supportWorkerId) {
		return _supportWorkerComponentLocalService.getSupportWorkerComponents(supportWorkerId);
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
		return _supportWorkerComponentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _supportWorkerComponentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void setSupportWorkerComponents(long supportWorkerId,
		int[] components) {
		_supportWorkerComponentLocalService.setSupportWorkerComponents(supportWorkerId,
			components);
	}

	@Override
	public SupportWorkerComponentLocalService getWrappedService() {
		return _supportWorkerComponentLocalService;
	}

	@Override
	public void setWrappedService(
		SupportWorkerComponentLocalService supportWorkerComponentLocalService) {
		_supportWorkerComponentLocalService = supportWorkerComponentLocalService;
	}

	private SupportWorkerComponentLocalService _supportWorkerComponentLocalService;
}