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
 * Provides a wrapper for {@link SupportWorkerSeverityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerSeverityLocalService
 * @generated
 */
@ProviderType
public class SupportWorkerSeverityLocalServiceWrapper
	implements SupportWorkerSeverityLocalService,
		ServiceWrapper<SupportWorkerSeverityLocalService> {
	public SupportWorkerSeverityLocalServiceWrapper(
		SupportWorkerSeverityLocalService supportWorkerSeverityLocalService) {
		_supportWorkerSeverityLocalService = supportWorkerSeverityLocalService;
	}

	/**
	* Adds the support worker severity to the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverity the support worker severity
	* @return the support worker severity that was added
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerSeverity addSupportWorkerSeverity(
		com.liferay.osb.model.SupportWorkerSeverity supportWorkerSeverity) {
		return _supportWorkerSeverityLocalService.addSupportWorkerSeverity(supportWorkerSeverity);
	}

	/**
	* Creates a new support worker severity with the primary key. Does not add the support worker severity to the database.
	*
	* @param supportWorkerSeverityId the primary key for the new support worker severity
	* @return the new support worker severity
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerSeverity createSupportWorkerSeverity(
		long supportWorkerSeverityId) {
		return _supportWorkerSeverityLocalService.createSupportWorkerSeverity(supportWorkerSeverityId);
	}

	/**
	* Deletes the support worker severity from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverity the support worker severity
	* @return the support worker severity that was removed
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerSeverity deleteSupportWorkerSeverity(
		com.liferay.osb.model.SupportWorkerSeverity supportWorkerSeverity) {
		return _supportWorkerSeverityLocalService.deleteSupportWorkerSeverity(supportWorkerSeverity);
	}

	/**
	* Deletes the support worker severity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity that was removed
	* @throws PortalException if a support worker severity with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerSeverity deleteSupportWorkerSeverity(
		long supportWorkerSeverityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerSeverityLocalService.deleteSupportWorkerSeverity(supportWorkerSeverityId);
	}

	@Override
	public com.liferay.osb.model.SupportWorkerSeverity fetchSupportWorkerSeverity(
		long supportWorkerSeverityId) {
		return _supportWorkerSeverityLocalService.fetchSupportWorkerSeverity(supportWorkerSeverityId);
	}

	/**
	* Returns the support worker severity with the primary key.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity
	* @throws PortalException if a support worker severity with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerSeverity getSupportWorkerSeverity(
		long supportWorkerSeverityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerSeverityLocalService.getSupportWorkerSeverity(supportWorkerSeverityId);
	}

	/**
	* Updates the support worker severity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverity the support worker severity
	* @return the support worker severity that was updated
	*/
	@Override
	public com.liferay.osb.model.SupportWorkerSeverity updateSupportWorkerSeverity(
		com.liferay.osb.model.SupportWorkerSeverity supportWorkerSeverity) {
		return _supportWorkerSeverityLocalService.updateSupportWorkerSeverity(supportWorkerSeverity);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _supportWorkerSeverityLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportWorkerSeverityLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _supportWorkerSeverityLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerSeverityLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorkerSeverityLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of support worker severities.
	*
	* @return the number of support worker severities
	*/
	@Override
	public int getSupportWorkerSeveritiesCount() {
		return _supportWorkerSeverityLocalService.getSupportWorkerSeveritiesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportWorkerSeverityLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _supportWorkerSeverityLocalService.getOSGiServiceIdentifier();
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
		return _supportWorkerSeverityLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportWorkerSeverityLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportWorkerSeverityLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the support worker severities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @return the range of support worker severities
	*/
	@Override
	public java.util.List<com.liferay.osb.model.SupportWorkerSeverity> getSupportWorkerSeverities(
		int start, int end) {
		return _supportWorkerSeverityLocalService.getSupportWorkerSeverities(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorkerSeverity> getSupportWorkerSeverities(
		long supportWorkerId) {
		return _supportWorkerSeverityLocalService.getSupportWorkerSeverities(supportWorkerId);
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
		return _supportWorkerSeverityLocalService.dynamicQueryCount(dynamicQuery);
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
		return _supportWorkerSeverityLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void setSupportWorkerSeverities(long supportWorkerId,
		int[] severities) {
		_supportWorkerSeverityLocalService.setSupportWorkerSeverities(supportWorkerId,
			severities);
	}

	@Override
	public SupportWorkerSeverityLocalService getWrappedService() {
		return _supportWorkerSeverityLocalService;
	}

	@Override
	public void setWrappedService(
		SupportWorkerSeverityLocalService supportWorkerSeverityLocalService) {
		_supportWorkerSeverityLocalService = supportWorkerSeverityLocalService;
	}

	private SupportWorkerSeverityLocalService _supportWorkerSeverityLocalService;
}