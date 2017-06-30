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
 * This class is a wrapper for {@link ContractAuditLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContractAuditLocalService
 * @generated
 */
public class ContractAuditLocalServiceWrapper
	implements ContractAuditLocalService,
		ServiceWrapper<ContractAuditLocalService> {
	public ContractAuditLocalServiceWrapper(
		ContractAuditLocalService contractAuditLocalService) {
		_contractAuditLocalService = contractAuditLocalService;
	}

	/**
	* Adds the contract audit to the database. Also notifies the appropriate model listeners.
	*
	* @param contractAudit the contract audit
	* @return the contract audit that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit addContractAudit(
		com.liferay.osb.model.ContractAudit contractAudit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.addContractAudit(contractAudit);
	}

	/**
	* Creates a new contract audit with the primary key. Does not add the contract audit to the database.
	*
	* @param contractAuditId the primary key for the new contract audit
	* @return the new contract audit
	*/
	public com.liferay.osb.model.ContractAudit createContractAudit(
		long contractAuditId) {
		return _contractAuditLocalService.createContractAudit(contractAuditId);
	}

	/**
	* Deletes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit that was removed
	* @throws PortalException if a contract audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit deleteContractAudit(
		long contractAuditId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.deleteContractAudit(contractAuditId);
	}

	/**
	* Deletes the contract audit from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAudit the contract audit
	* @return the contract audit that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit deleteContractAudit(
		com.liferay.osb.model.ContractAudit contractAudit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.deleteContractAudit(contractAudit);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contractAuditLocalService.dynamicQuery();
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
		return _contractAuditLocalService.dynamicQuery(dynamicQuery);
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
		return _contractAuditLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _contractAuditLocalService.dynamicQuery(dynamicQuery, start,
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
		return _contractAuditLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.ContractAudit fetchContractAudit(
		long contractAuditId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.fetchContractAudit(contractAuditId);
	}

	/**
	* Returns the contract audit with the primary key.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit
	* @throws PortalException if a contract audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit getContractAudit(
		long contractAuditId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.getContractAudit(contractAuditId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the contract audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @return the range of contract audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractAudit> getContractAudits(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.getContractAudits(start, end);
	}

	/**
	* Returns the number of contract audits.
	*
	* @return the number of contract audits
	* @throws SystemException if a system exception occurred
	*/
	public int getContractAuditsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.getContractAuditsCount();
	}

	/**
	* Updates the contract audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractAudit the contract audit
	* @return the contract audit that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit updateContractAudit(
		com.liferay.osb.model.ContractAudit contractAudit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.updateContractAudit(contractAudit);
	}

	/**
	* Updates the contract audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractAudit the contract audit
	* @param merge whether to merge the contract audit with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the contract audit that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit updateContractAudit(
		com.liferay.osb.model.ContractAudit contractAudit, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.updateContractAudit(contractAudit,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _contractAuditLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_contractAuditLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _contractAuditLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addContractAudit(long userId, long contractEntryId,
		java.lang.String signatoryClassName, long signatoryClassPK,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_contractAuditLocalService.addContractAudit(userId, contractEntryId,
			signatoryClassName, signatoryClassPK, productClassName,
			productClassPK);
	}

	public int getContractAuditCount(long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.getContractAuditCount(userId,
			contractEntryId);
	}

	public int getContractAuditCount(long contractEntryId,
		java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.getContractAuditCount(contractEntryId,
			signatoryClassName, signatoryClassPK);
	}

	public boolean hasContractAudit(long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.hasContractAudit(userId,
			contractEntryId);
	}

	public boolean hasContractAudit(long classNameId, long classPK, int type,
		java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.hasContractAudit(classNameId,
			classPK, type, signatoryClassName, signatoryClassPK);
	}

	public boolean hasContractAudit(long contractEntryId,
		java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.hasContractAudit(contractEntryId,
			signatoryClassName, signatoryClassPK);
	}

	public boolean hasLatestContractAudit(long classNameId, long classPK,
		int type, java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.hasLatestContractAudit(classNameId,
			classPK, type, signatoryClassName, signatoryClassPK);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ContractAuditLocalService getWrappedContractAuditLocalService() {
		return _contractAuditLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedContractAuditLocalService(
		ContractAuditLocalService contractAuditLocalService) {
		_contractAuditLocalService = contractAuditLocalService;
	}

	public ContractAuditLocalService getWrappedService() {
		return _contractAuditLocalService;
	}

	public void setWrappedService(
		ContractAuditLocalService contractAuditLocalService) {
		_contractAuditLocalService = contractAuditLocalService;
	}

	private ContractAuditLocalService _contractAuditLocalService;
}