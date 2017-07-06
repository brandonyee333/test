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
 * Provides a wrapper for {@link ContractEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntryLocalService
 * @generated
 */
@ProviderType
public class ContractEntryLocalServiceWrapper
	implements ContractEntryLocalService,
		ServiceWrapper<ContractEntryLocalService> {
	public ContractEntryLocalServiceWrapper(
		ContractEntryLocalService contractEntryLocalService) {
		_contractEntryLocalService = contractEntryLocalService;
	}

	/**
	* Adds the contract entry to the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @return the contract entry that was added
	*/
	@Override
	public com.liferay.osb.model.ContractEntry addContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry) {
		return _contractEntryLocalService.addContractEntry(contractEntry);
	}

	@Override
	public com.liferay.osb.model.ContractEntry addContractEntry(long userId,
		java.lang.String className, long classPK, int type,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.addContractEntry(userId, className,
			classPK, type, contentMap, serviceContext);
	}

	@Override
	public com.liferay.osb.model.ContractEntry addContractEntry(long userId,
		long classNameId, long classPK, int type,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.addContractEntry(userId, classNameId,
			classPK, type, contentMap, serviceContext);
	}

	/**
	* Creates a new contract entry with the primary key. Does not add the contract entry to the database.
	*
	* @param contractEntryId the primary key for the new contract entry
	* @return the new contract entry
	*/
	@Override
	public com.liferay.osb.model.ContractEntry createContractEntry(
		long contractEntryId) {
		return _contractEntryLocalService.createContractEntry(contractEntryId);
	}

	/**
	* Deletes the contract entry from the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @return the contract entry that was removed
	*/
	@Override
	public com.liferay.osb.model.ContractEntry deleteContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry) {
		return _contractEntryLocalService.deleteContractEntry(contractEntry);
	}

	/**
	* Deletes the contract entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry that was removed
	* @throws PortalException if a contract entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.ContractEntry deleteContractEntry(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractEntryLocalService.deleteContractEntry(contractEntryId);
	}

	@Override
	public com.liferay.osb.model.ContractEntry fetchContractEntry(
		long contractEntryId) {
		return _contractEntryLocalService.fetchContractEntry(contractEntryId);
	}

	/**
	* Returns the contract entry with the primary key.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry
	* @throws PortalException if a contract entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.ContractEntry getContractEntry(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractEntryLocalService.getContractEntry(contractEntryId);
	}

	@Override
	public com.liferay.osb.model.ContractEntry getLatestContractEntry(
		java.lang.String className, long classPK, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getLatestContractEntry(className,
			classPK, type);
	}

	@Override
	public com.liferay.osb.model.ContractEntry getLatestContractEntry(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getLatestContractEntry(classNameId,
			classPK, type);
	}

	/**
	* Updates the contract entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @return the contract entry that was updated
	*/
	@Override
	public com.liferay.osb.model.ContractEntry updateContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry) {
		return _contractEntryLocalService.updateContractEntry(contractEntry);
	}

	@Override
	public com.liferay.osb.model.ContractEntry updateContractEntry(
		long userId, long contractEntryId,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.updateContractEntry(userId,
			contractEntryId, contentMap, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _contractEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contractEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _contractEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of contract entries.
	*
	* @return the number of contract entries
	*/
	@Override
	public int getContractEntriesCount() {
		return _contractEntryLocalService.getContractEntriesCount();
	}

	@Override
	public int getContractEntriesCount(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getContractEntriesCount(classNameId,
			classPK, type);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _contractEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _contractEntryLocalService.getOSGiServiceIdentifier();
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
		return _contractEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contractEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contractEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the contract entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @return the range of contract entries
	*/
	@Override
	public java.util.List<com.liferay.osb.model.ContractEntry> getContractEntries(
		int start, int end) {
		return _contractEntryLocalService.getContractEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.ContractEntry> getContractEntries(
		long classNameId, long classPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getContractEntries(classNameId,
			classPK, type, start, end);
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
		return _contractEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _contractEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ContractEntryLocalService getWrappedService() {
		return _contractEntryLocalService;
	}

	@Override
	public void setWrappedService(
		ContractEntryLocalService contractEntryLocalService) {
		_contractEntryLocalService = contractEntryLocalService;
	}

	private ContractEntryLocalService _contractEntryLocalService;
}