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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OfferingDefinitionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OfferingDefinitionLocalService
 * @generated
 */
public class OfferingDefinitionLocalServiceWrapper
	implements OfferingDefinitionLocalService,
		ServiceWrapper<OfferingDefinitionLocalService> {
	public OfferingDefinitionLocalServiceWrapper(
		OfferingDefinitionLocalService offeringDefinitionLocalService) {
		_offeringDefinitionLocalService = offeringDefinitionLocalService;
	}

	/**
	* Adds the offering definition to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition addOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.addOfferingDefinition(offeringDefinition);
	}

	/**
	* Creates a new offering definition with the primary key. Does not add the offering definition to the database.
	*
	* @param offeringDefinitionId the primary key for the new offering definition
	* @return the new offering definition
	*/
	public com.liferay.osb.model.OfferingDefinition createOfferingDefinition(
		long offeringDefinitionId) {
		return _offeringDefinitionLocalService.createOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Deletes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition that was removed
	* @throws PortalException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition deleteOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.deleteOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Deletes the offering definition from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition deleteOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.deleteOfferingDefinition(offeringDefinition);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _offeringDefinitionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.OfferingDefinition fetchOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.fetchOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Returns the offering definition with the primary key.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition
	* @throws PortalException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition getOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getOfferingDefinition(offeringDefinitionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the offering definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getOfferingDefinitions(start, end);
	}

	/**
	* Returns the number of offering definitions.
	*
	* @return the number of offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public int getOfferingDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getOfferingDefinitionsCount();
	}

	/**
	* Updates the offering definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.updateOfferingDefinition(offeringDefinition);
	}

	/**
	* Updates the offering definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @param merge whether to merge the offering definition with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the offering definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.updateOfferingDefinition(offeringDefinition,
			merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinitionLocalService.addOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingBundleOfferingDefinition(long offeringBundleId,
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinitionLocalService.addOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinition);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinitionLocalService.addOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingBundleOfferingDefinitions(long offeringBundleId,
		java.util.List<com.liferay.osb.model.OfferingDefinition> OfferingDefinitions)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinitionLocalService.addOfferingBundleOfferingDefinitions(offeringBundleId,
			OfferingDefinitions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearOfferingBundleOfferingDefinitions(long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinitionLocalService.clearOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinitionLocalService.deleteOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingBundleOfferingDefinition(long offeringBundleId,
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinitionLocalService.deleteOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinition);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinitionLocalService.deleteOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingBundleOfferingDefinitions(long offeringBundleId,
		java.util.List<com.liferay.osb.model.OfferingDefinition> OfferingDefinitions)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinitionLocalService.deleteOfferingBundleOfferingDefinitions(offeringBundleId,
			OfferingDefinitions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getOfferingBundleOfferingDefinitions(offeringBundleId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getOfferingBundleOfferingDefinitions(offeringBundleId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public int getOfferingBundleOfferingDefinitionsCount(long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getOfferingBundleOfferingDefinitionsCount(offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.hasOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasOfferingBundleOfferingDefinitions(long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.hasOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinitionLocalService.setOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _offeringDefinitionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_offeringDefinitionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _offeringDefinitionLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.OfferingDefinition addOfferingDefinition(
		long userId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.addOfferingDefinition(userId,
			productEntryId, supportResponseId, productDescription, licenses,
			unlimitedLicenses, maxConcurrentUsers, maxUsers, supportTickets);
	}

	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long[] productEntryIds, long[] supportResponseIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getOfferingDefinitions(productEntryIds,
			supportResponseIds, start, end);
	}

	public int getOfferingDefinitionsCount(long[] productEntryIds,
		long[] supportResponseIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getOfferingDefinitionsCount(productEntryIds,
			supportResponseIds);
	}

	public java.util.List<com.liferay.osb.model.OfferingDefinition> getProductEntryOfferingDefinitions(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getProductEntryOfferingDefinitions(productEntryId);
	}

	public java.util.List<com.liferay.osb.model.OfferingDefinition> getSupportResponseOfferingDefinitions(
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.getSupportResponseOfferingDefinitions(supportResponseId);
	}

	public com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinitionLocalService.updateOfferingDefinition(offeringDefinitionId,
			productEntryId, supportResponseId, productDescription, licenses,
			unlimitedLicenses, maxConcurrentUsers, maxUsers, supportTickets);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OfferingDefinitionLocalService getWrappedOfferingDefinitionLocalService() {
		return _offeringDefinitionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOfferingDefinitionLocalService(
		OfferingDefinitionLocalService offeringDefinitionLocalService) {
		_offeringDefinitionLocalService = offeringDefinitionLocalService;
	}

	public OfferingDefinitionLocalService getWrappedService() {
		return _offeringDefinitionLocalService;
	}

	public void setWrappedService(
		OfferingDefinitionLocalService offeringDefinitionLocalService) {
		_offeringDefinitionLocalService = offeringDefinitionLocalService;
	}

	private OfferingDefinitionLocalService _offeringDefinitionLocalService;
}