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
 * Provides a wrapper for {@link OfferingDefinitionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinitionLocalService
 * @generated
 */
@ProviderType
public class OfferingDefinitionLocalServiceWrapper
	implements OfferingDefinitionLocalService,
		ServiceWrapper<OfferingDefinitionLocalService> {
	public OfferingDefinitionLocalServiceWrapper(
		OfferingDefinitionLocalService offeringDefinitionLocalService) {
		_offeringDefinitionLocalService = offeringDefinitionLocalService;
	}

	@Override
	public boolean hasOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId) {
		return _offeringDefinitionLocalService.hasOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	@Override
	public boolean hasOfferingBundleOfferingDefinitions(long offeringBundleId) {
		return _offeringDefinitionLocalService.hasOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	/**
	* Adds the offering definition to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was added
	*/
	@Override
	public com.liferay.osb.model.OfferingDefinition addOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		return _offeringDefinitionLocalService.addOfferingDefinition(offeringDefinition);
	}

	@Override
	public com.liferay.osb.model.OfferingDefinition addOfferingDefinition(
		long userId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringDefinitionLocalService.addOfferingDefinition(userId,
			productEntryId, supportResponseId, productDescription, licenses,
			unlimitedLicenses, maxConcurrentUsers, maxUsers, supportTickets);
	}

	/**
	* Creates a new offering definition with the primary key. Does not add the offering definition to the database.
	*
	* @param offeringDefinitionId the primary key for the new offering definition
	* @return the new offering definition
	*/
	@Override
	public com.liferay.osb.model.OfferingDefinition createOfferingDefinition(
		long offeringDefinitionId) {
		return _offeringDefinitionLocalService.createOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Deletes the offering definition from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was removed
	*/
	@Override
	public com.liferay.osb.model.OfferingDefinition deleteOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		return _offeringDefinitionLocalService.deleteOfferingDefinition(offeringDefinition);
	}

	/**
	* Deletes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition that was removed
	* @throws PortalException if a offering definition with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.OfferingDefinition deleteOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringDefinitionLocalService.deleteOfferingDefinition(offeringDefinitionId);
	}

	@Override
	public com.liferay.osb.model.OfferingDefinition fetchOfferingDefinition(
		long offeringDefinitionId) {
		return _offeringDefinitionLocalService.fetchOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Returns the offering definition with the primary key.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition
	* @throws PortalException if a offering definition with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.OfferingDefinition getOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringDefinitionLocalService.getOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Updates the offering definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was updated
	*/
	@Override
	public com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		return _offeringDefinitionLocalService.updateOfferingDefinition(offeringDefinition);
	}

	@Override
	public com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringDefinitionLocalService.updateOfferingDefinition(offeringDefinitionId,
			productEntryId, supportResponseId, productDescription, licenses,
			unlimitedLicenses, maxConcurrentUsers, maxUsers, supportTickets);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _offeringDefinitionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _offeringDefinitionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _offeringDefinitionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringDefinitionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringDefinitionLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getOfferingBundleOfferingDefinitionsCount(long offeringBundleId) {
		return _offeringDefinitionLocalService.getOfferingBundleOfferingDefinitionsCount(offeringBundleId);
	}

	/**
	* Returns the number of offering definitions.
	*
	* @return the number of offering definitions
	*/
	@Override
	public int getOfferingDefinitionsCount() {
		return _offeringDefinitionLocalService.getOfferingDefinitionsCount();
	}

	@Override
	public int getOfferingDefinitionsCount(long[] productEntryIds,
		long[] supportResponseIds) {
		return _offeringDefinitionLocalService.getOfferingDefinitionsCount(productEntryIds,
			supportResponseIds);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _offeringDefinitionLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _offeringDefinitionLocalService.getOSGiServiceIdentifier();
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
		return _offeringDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _offeringDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _offeringDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId) {
		return _offeringDefinitionLocalService.getOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end) {
		return _offeringDefinitionLocalService.getOfferingBundleOfferingDefinitions(offeringBundleId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.OfferingDefinition> orderByComparator) {
		return _offeringDefinitionLocalService.getOfferingBundleOfferingDefinitions(offeringBundleId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the offering definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of offering definitions
	*/
	@Override
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		int start, int end) {
		return _offeringDefinitionLocalService.getOfferingDefinitions(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long[] productEntryIds, long[] supportResponseIds, int start, int end) {
		return _offeringDefinitionLocalService.getOfferingDefinitions(productEntryIds,
			supportResponseIds, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getProductEntryOfferingDefinitions(
		long productEntryId) {
		return _offeringDefinitionLocalService.getProductEntryOfferingDefinitions(productEntryId);
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
		return _offeringDefinitionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _offeringDefinitionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the offeringBundleIds of the offering bundles associated with the offering definition.
	*
	* @param offeringDefinitionId the offeringDefinitionId of the offering definition
	* @return long[] the offeringBundleIds of offering bundles associated with the offering definition
	*/
	@Override
	public long[] getOfferingBundlePrimaryKeys(long offeringDefinitionId) {
		return _offeringDefinitionLocalService.getOfferingBundlePrimaryKeys(offeringDefinitionId);
	}

	@Override
	public void addOfferingBundleOfferingDefinition(long offeringBundleId,
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		_offeringDefinitionLocalService.addOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinition);
	}

	@Override
	public void addOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId) {
		_offeringDefinitionLocalService.addOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	@Override
	public void addOfferingBundleOfferingDefinitions(long offeringBundleId,
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		_offeringDefinitionLocalService.addOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitions);
	}

	@Override
	public void addOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds) {
		_offeringDefinitionLocalService.addOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	@Override
	public void clearOfferingBundleOfferingDefinitions(long offeringBundleId) {
		_offeringDefinitionLocalService.clearOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	@Override
	public void deleteOfferingBundleOfferingDefinition(long offeringBundleId,
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		_offeringDefinitionLocalService.deleteOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinition);
	}

	@Override
	public void deleteOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId) {
		_offeringDefinitionLocalService.deleteOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	@Override
	public void deleteOfferingBundleOfferingDefinitions(long offeringBundleId,
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		_offeringDefinitionLocalService.deleteOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitions);
	}

	@Override
	public void deleteOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds) {
		_offeringDefinitionLocalService.deleteOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	@Override
	public void setOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds) {
		_offeringDefinitionLocalService.setOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	@Override
	public OfferingDefinitionLocalService getWrappedService() {
		return _offeringDefinitionLocalService;
	}

	@Override
	public void setWrappedService(
		OfferingDefinitionLocalService offeringDefinitionLocalService) {
		_offeringDefinitionLocalService = offeringDefinitionLocalService;
	}

	private OfferingDefinitionLocalService _offeringDefinitionLocalService;
}