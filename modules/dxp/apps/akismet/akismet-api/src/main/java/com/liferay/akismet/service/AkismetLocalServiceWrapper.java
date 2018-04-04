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

package com.liferay.akismet.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AkismetLocalService}.
 *
 * @author Jamie Sammons
 * @see AkismetLocalService
 * @generated
 */
@ProviderType
public class AkismetLocalServiceWrapper implements AkismetLocalService,
	ServiceWrapper<AkismetLocalService> {
	public AkismetLocalServiceWrapper(AkismetLocalService akismetLocalService) {
		_akismetLocalService = akismetLocalService;
	}

	/**
	* Adds the akismet to the database. Also notifies the appropriate model listeners.
	*
	* @param akismet the akismet
	* @return the akismet that was added
	*/
	@Override
	public com.liferay.akismet.model.Akismet addAkismet(
		com.liferay.akismet.model.Akismet akismet) {
		return _akismetLocalService.addAkismet(akismet);
	}

	/**
	* Creates a new akismet with the primary key. Does not add the akismet to the database.
	*
	* @param akismetId the primary key for the new akismet
	* @return the new akismet
	*/
	@Override
	public com.liferay.akismet.model.Akismet createAkismet(
		long akismetId) {
		return _akismetLocalService.createAkismet(akismetId);
	}

	/**
	* Deletes the akismet from the database. Also notifies the appropriate model listeners.
	*
	* @param akismet the akismet
	* @return the akismet that was removed
	*/
	@Override
	public com.liferay.akismet.model.Akismet deleteAkismet(
		com.liferay.akismet.model.Akismet akismet) {
		return _akismetLocalService.deleteAkismet(akismet);
	}

	/**
	* Deletes the akismet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetId the primary key of the akismet
	* @return the akismet that was removed
	* @throws PortalException if a akismet with the primary key could not be found
	*/
	@Override
	public com.liferay.akismet.model.Akismet deleteAkismet(
		long akismetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _akismetLocalService.deleteAkismet(akismetId);
	}

	@Override
	public void deleteAkismetData(java.util.Date modifiedDate) {
		_akismetLocalService.deleteAkismetData(modifiedDate);
	}

	@Override
	public void deleteAkismetData(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		_akismetLocalService.deleteAkismetData(className, classPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _akismetLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _akismetLocalService.dynamicQuery();
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
		return _akismetLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _akismetLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _akismetLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _akismetLocalService.dynamicQueryCount(dynamicQuery);
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
		return _akismetLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.akismet.model.Akismet fetchAkismet(
		long akismetId) {
		return _akismetLocalService.fetchAkismet(akismetId);
	}

	@Override
	public com.liferay.akismet.model.Akismet fetchAkismetData(
		java.lang.String className, long classPK) {
		return _akismetLocalService.fetchAkismetData(className, classPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _akismetLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the akismet with the primary key.
	*
	* @param akismetId the primary key of the akismet
	* @return the akismet
	* @throws PortalException if a akismet with the primary key could not be found
	*/
	@Override
	public com.liferay.akismet.model.Akismet getAkismet(
		long akismetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _akismetLocalService.getAkismet(akismetId);
	}

	/**
	* Returns a range of all the akismets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @return the range of akismets
	*/
	@Override
	public java.util.List<com.liferay.akismet.model.Akismet> getAkismets(
		int start, int end) {
		return _akismetLocalService.getAkismets(start, end);
	}

	/**
	* Returns the number of akismets.
	*
	* @return the number of akismets
	*/
	@Override
	public int getAkismetsCount() {
		return _akismetLocalService.getAkismetsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _akismetLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _akismetLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _akismetLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the akismet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param akismet the akismet
	* @return the akismet that was updated
	*/
	@Override
	public com.liferay.akismet.model.Akismet updateAkismet(
		com.liferay.akismet.model.Akismet akismet) {
		return _akismetLocalService.updateAkismet(akismet);
	}

	@Override
	public com.liferay.akismet.model.Akismet updateAkismetData(
		java.lang.String className, long classPK, java.lang.String type,
		java.lang.String permalink, java.lang.String referrer,
		java.lang.String userAgent, java.lang.String userIP,
		java.lang.String userURL) {
		return _akismetLocalService.updateAkismetData(className, classPK, type,
			permalink, referrer, userAgent, userIP, userURL);
	}

	@Override
	public AkismetLocalService getWrappedService() {
		return _akismetLocalService;
	}

	@Override
	public void setWrappedService(AkismetLocalService akismetLocalService) {
		_akismetLocalService = akismetLocalService;
	}

	private AkismetLocalService _akismetLocalService;
}