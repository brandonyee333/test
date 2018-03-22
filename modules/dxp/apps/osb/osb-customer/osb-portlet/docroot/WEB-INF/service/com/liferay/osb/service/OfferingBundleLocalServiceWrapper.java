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
 * Provides a wrapper for {@link OfferingBundleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundleLocalService
 * @generated
 */
@ProviderType
public class OfferingBundleLocalServiceWrapper
	implements OfferingBundleLocalService,
		ServiceWrapper<OfferingBundleLocalService> {
	public OfferingBundleLocalServiceWrapper(
		OfferingBundleLocalService offeringBundleLocalService) {
		_offeringBundleLocalService = offeringBundleLocalService;
	}

	@Override
	public boolean hasOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId) {
		return _offeringBundleLocalService.hasOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	@Override
	public boolean hasOfferingDefinitionOfferingBundles(
		long offeringDefinitionId) {
		return _offeringBundleLocalService.hasOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	/**
	* Adds the offering bundle to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was added
	*/
	@Override
	public com.liferay.osb.model.OfferingBundle addOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		return _offeringBundleLocalService.addOfferingBundle(offeringBundle);
	}

	@Override
	public com.liferay.osb.model.OfferingBundle addOfferingBundle(long userId,
		java.lang.String name, long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringBundleLocalService.addOfferingBundle(userId, name,
			offeringDefinitionIds);
	}

	/**
	* Creates a new offering bundle with the primary key. Does not add the offering bundle to the database.
	*
	* @param offeringBundleId the primary key for the new offering bundle
	* @return the new offering bundle
	*/
	@Override
	public com.liferay.osb.model.OfferingBundle createOfferingBundle(
		long offeringBundleId) {
		return _offeringBundleLocalService.createOfferingBundle(offeringBundleId);
	}

	/**
	* Deletes the offering bundle from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was removed
	*/
	@Override
	public com.liferay.osb.model.OfferingBundle deleteOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		return _offeringBundleLocalService.deleteOfferingBundle(offeringBundle);
	}

	/**
	* Deletes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle that was removed
	* @throws PortalException if a offering bundle with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.OfferingBundle deleteOfferingBundle(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringBundleLocalService.deleteOfferingBundle(offeringBundleId);
	}

	@Override
	public com.liferay.osb.model.OfferingBundle fetchOfferingBundle(
		long offeringBundleId) {
		return _offeringBundleLocalService.fetchOfferingBundle(offeringBundleId);
	}

	/**
	* Returns the offering bundle with the primary key.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle
	* @throws PortalException if a offering bundle with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.OfferingBundle getOfferingBundle(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringBundleLocalService.getOfferingBundle(offeringBundleId);
	}

	/**
	* Updates the offering bundle in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was updated
	*/
	@Override
	public com.liferay.osb.model.OfferingBundle updateOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		return _offeringBundleLocalService.updateOfferingBundle(offeringBundle);
	}

	@Override
	public com.liferay.osb.model.OfferingBundle updateOfferingBundle(
		long offeringBundleId, java.lang.String name,
		long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringBundleLocalService.updateOfferingBundle(offeringBundleId,
			name, offeringDefinitionIds);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _offeringBundleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _offeringBundleLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _offeringBundleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringBundleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringBundleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of offering bundles.
	*
	* @return the number of offering bundles
	*/
	@Override
	public int getOfferingBundlesCount() {
		return _offeringBundleLocalService.getOfferingBundlesCount();
	}

	@Override
	public int getOfferingDefinitionOfferingBundlesCount(
		long offeringDefinitionId) {
		return _offeringBundleLocalService.getOfferingDefinitionOfferingBundlesCount(offeringDefinitionId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _offeringBundleLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _offeringBundleLocalService.getOSGiServiceIdentifier();
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
		return _offeringBundleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _offeringBundleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _offeringBundleLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the offering bundles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @return the range of offering bundles
	*/
	@Override
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		int start, int end) {
		return _offeringBundleLocalService.getOfferingBundles(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId) {
		return _offeringBundleLocalService.getOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, int start, int end) {
		return _offeringBundleLocalService.getOfferingDefinitionOfferingBundles(offeringDefinitionId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.OfferingBundle> orderByComparator) {
		return _offeringBundleLocalService.getOfferingDefinitionOfferingBundles(offeringDefinitionId,
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
		return _offeringBundleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _offeringBundleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the offeringDefinitionIds of the offering definitions associated with the offering bundle.
	*
	* @param offeringBundleId the offeringBundleId of the offering bundle
	* @return long[] the offeringDefinitionIds of offering definitions associated with the offering bundle
	*/
	@Override
	public long[] getOfferingDefinitionPrimaryKeys(long offeringBundleId) {
		return _offeringBundleLocalService.getOfferingDefinitionPrimaryKeys(offeringBundleId);
	}

	@Override
	public void addOfferingDefinitionOfferingBundle(long offeringDefinitionId,
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		_offeringBundleLocalService.addOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundle);
	}

	@Override
	public void addOfferingDefinitionOfferingBundle(long offeringDefinitionId,
		long offeringBundleId) {
		_offeringBundleLocalService.addOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	@Override
	public void addOfferingDefinitionOfferingBundles(
		long offeringDefinitionId,
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
		_offeringBundleLocalService.addOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundles);
	}

	@Override
	public void addOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds) {
		_offeringBundleLocalService.addOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
	}

	@Override
	public void clearOfferingDefinitionOfferingBundles(
		long offeringDefinitionId) {
		_offeringBundleLocalService.clearOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	@Override
	public void deleteOfferingDefinitionOfferingBundle(
		long offeringDefinitionId,
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		_offeringBundleLocalService.deleteOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundle);
	}

	@Override
	public void deleteOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId) {
		_offeringBundleLocalService.deleteOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	@Override
	public void deleteOfferingDefinitionOfferingBundles(
		long offeringDefinitionId,
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
		_offeringBundleLocalService.deleteOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundles);
	}

	@Override
	public void deleteOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds) {
		_offeringBundleLocalService.deleteOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
	}

	@Override
	public void setOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds) {
		_offeringBundleLocalService.setOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
	}

	@Override
	public OfferingBundleLocalService getWrappedService() {
		return _offeringBundleLocalService;
	}

	@Override
	public void setWrappedService(
		OfferingBundleLocalService offeringBundleLocalService) {
		_offeringBundleLocalService = offeringBundleLocalService;
	}

	private OfferingBundleLocalService _offeringBundleLocalService;
}