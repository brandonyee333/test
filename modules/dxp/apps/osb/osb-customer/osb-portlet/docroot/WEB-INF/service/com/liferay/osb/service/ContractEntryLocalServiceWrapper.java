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
 * This class is a wrapper for {@link ContractEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContractEntryLocalService
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry addContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.addContractEntry(contractEntry);
	}

	/**
	* Creates a new contract entry with the primary key. Does not add the contract entry to the database.
	*
	* @param contractEntryId the primary key for the new contract entry
	* @return the new contract entry
	*/
	public com.liferay.osb.model.ContractEntry createContractEntry(
		long contractEntryId) {
		return _contractEntryLocalService.createContractEntry(contractEntryId);
	}

	/**
	* Deletes the contract entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry that was removed
	* @throws PortalException if a contract entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry deleteContractEntry(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.deleteContractEntry(contractEntryId);
	}

	/**
	* Deletes the contract entry from the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @return the contract entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry deleteContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.deleteContractEntry(contractEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contractEntryLocalService.dynamicQuery();
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
		return _contractEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _contractEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _contractEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _contractEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.ContractEntry fetchContractEntry(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.fetchContractEntry(contractEntryId);
	}

	/**
	* Returns the contract entry with the primary key.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry
	* @throws PortalException if a contract entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry getContractEntry(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getContractEntry(contractEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the contract entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @return the range of contract entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractEntry> getContractEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getContractEntries(start, end);
	}

	/**
	* Returns the number of contract entries.
	*
	* @return the number of contract entries
	* @throws SystemException if a system exception occurred
	*/
	public int getContractEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getContractEntriesCount();
	}

	/**
	* Updates the contract entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @return the contract entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry updateContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.updateContractEntry(contractEntry);
	}

	/**
	* Updates the contract entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @param merge whether to merge the contract entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the contract entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry updateContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.updateContractEntry(contractEntry,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _contractEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_contractEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _contractEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.ContractEntry addContractEntry(long userId,
		long classNameId, long classPK, int type,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.addContractEntry(userId, classNameId,
			classPK, type, contentMap, serviceContext);
	}

	public com.liferay.osb.model.ContractEntry addContractEntry(long userId,
		java.lang.String className, long classPK, int type,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.addContractEntry(userId, className,
			classPK, type, contentMap, serviceContext);
	}

	public java.util.List<com.liferay.osb.model.ContractEntry> getContractEntries(
		long classNameId, long classPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getContractEntries(classNameId,
			classPK, type, start, end);
	}

	public int getContractEntriesCount(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getContractEntriesCount(classNameId,
			classPK, type);
	}

	public com.liferay.osb.model.ContractEntry getLatestContractEntry(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getLatestContractEntry(classNameId,
			classPK, type);
	}

	public com.liferay.osb.model.ContractEntry getLatestContractEntry(
		java.lang.String className, long classPK, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.getLatestContractEntry(className,
			classPK, type);
	}

	public com.liferay.osb.model.ContractEntry updateContractEntry(
		long userId, long contractEntryId,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractEntryLocalService.updateContractEntry(userId,
			contractEntryId, contentMap, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ContractEntryLocalService getWrappedContractEntryLocalService() {
		return _contractEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedContractEntryLocalService(
		ContractEntryLocalService contractEntryLocalService) {
		_contractEntryLocalService = contractEntryLocalService;
	}

	public ContractEntryLocalService getWrappedService() {
		return _contractEntryLocalService;
	}

	public void setWrappedService(
		ContractEntryLocalService contractEntryLocalService) {
		_contractEntryLocalService = contractEntryLocalService;
	}

	private ContractEntryLocalService _contractEntryLocalService;
}