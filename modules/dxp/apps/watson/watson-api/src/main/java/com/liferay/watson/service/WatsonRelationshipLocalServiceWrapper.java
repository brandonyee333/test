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
 * Provides a wrapper for {@link WatsonRelationshipLocalService}.
 *
 * @author Steven Smith
 * @see WatsonRelationshipLocalService
 * @generated
 */
@ProviderType
public class WatsonRelationshipLocalServiceWrapper
	implements WatsonRelationshipLocalService,
		ServiceWrapper<WatsonRelationshipLocalService> {
	public WatsonRelationshipLocalServiceWrapper(
		WatsonRelationshipLocalService watsonRelationshipLocalService) {
		_watsonRelationshipLocalService = watsonRelationshipLocalService;
	}

	/**
	* Adds the watson relationship to the database. Also notifies the appropriate model listeners.
	*
	* @param watsonRelationship the watson relationship
	* @return the watson relationship that was added
	*/
	@Override
	public com.liferay.watson.model.WatsonRelationship addWatsonRelationship(
		com.liferay.watson.model.WatsonRelationship watsonRelationship) {
		return _watsonRelationshipLocalService.addWatsonRelationship(watsonRelationship);
	}

	/**
	* Creates a new watson relationship with the primary key. Does not add the watson relationship to the database.
	*
	* @param watsonRelationshipId the primary key for the new watson relationship
	* @return the new watson relationship
	*/
	@Override
	public com.liferay.watson.model.WatsonRelationship createWatsonRelationship(
		long watsonRelationshipId) {
		return _watsonRelationshipLocalService.createWatsonRelationship(watsonRelationshipId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _watsonRelationshipLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the watson relationship with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonRelationshipId the primary key of the watson relationship
	* @return the watson relationship that was removed
	* @throws PortalException if a watson relationship with the primary key could not be found
	*/
	@Override
	public com.liferay.watson.model.WatsonRelationship deleteWatsonRelationship(
		long watsonRelationshipId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _watsonRelationshipLocalService.deleteWatsonRelationship(watsonRelationshipId);
	}

	/**
	* Deletes the watson relationship from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonRelationship the watson relationship
	* @return the watson relationship that was removed
	*/
	@Override
	public com.liferay.watson.model.WatsonRelationship deleteWatsonRelationship(
		com.liferay.watson.model.WatsonRelationship watsonRelationship) {
		return _watsonRelationshipLocalService.deleteWatsonRelationship(watsonRelationship);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonRelationshipLocalService.dynamicQuery();
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
		return _watsonRelationshipLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.watson.model.impl.WatsonRelationshipModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _watsonRelationshipLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.watson.model.impl.WatsonRelationshipModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _watsonRelationshipLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _watsonRelationshipLocalService.dynamicQueryCount(dynamicQuery);
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
		return _watsonRelationshipLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.watson.model.WatsonRelationship fetchWatsonRelationship(
		long watsonRelationshipId) {
		return _watsonRelationshipLocalService.fetchWatsonRelationship(watsonRelationshipId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _watsonRelationshipLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _watsonRelationshipLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _watsonRelationshipLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _watsonRelationshipLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the watson relationship with the primary key.
	*
	* @param watsonRelationshipId the primary key of the watson relationship
	* @return the watson relationship
	* @throws PortalException if a watson relationship with the primary key could not be found
	*/
	@Override
	public com.liferay.watson.model.WatsonRelationship getWatsonRelationship(
		long watsonRelationshipId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _watsonRelationshipLocalService.getWatsonRelationship(watsonRelationshipId);
	}

	/**
	* Returns a range of all the watson relationships.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.watson.model.impl.WatsonRelationshipModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson relationships
	* @param end the upper bound of the range of watson relationships (not inclusive)
	* @return the range of watson relationships
	*/
	@Override
	public java.util.List<com.liferay.watson.model.WatsonRelationship> getWatsonRelationships(
		int start, int end) {
		return _watsonRelationshipLocalService.getWatsonRelationships(start, end);
	}

	/**
	* Returns the number of watson relationships.
	*
	* @return the number of watson relationships
	*/
	@Override
	public int getWatsonRelationshipsCount() {
		return _watsonRelationshipLocalService.getWatsonRelationshipsCount();
	}

	/**
	* Updates the watson relationship in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param watsonRelationship the watson relationship
	* @return the watson relationship that was updated
	*/
	@Override
	public com.liferay.watson.model.WatsonRelationship updateWatsonRelationship(
		com.liferay.watson.model.WatsonRelationship watsonRelationship) {
		return _watsonRelationshipLocalService.updateWatsonRelationship(watsonRelationship);
	}

	@Override
	public WatsonRelationshipLocalService getWrappedService() {
		return _watsonRelationshipLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonRelationshipLocalService watsonRelationshipLocalService) {
		_watsonRelationshipLocalService = watsonRelationshipLocalService;
	}

	private WatsonRelationshipLocalService _watsonRelationshipLocalService;
}