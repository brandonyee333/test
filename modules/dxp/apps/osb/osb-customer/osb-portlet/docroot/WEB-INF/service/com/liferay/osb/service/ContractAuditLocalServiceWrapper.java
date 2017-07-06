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
 * Provides a wrapper for {@link ContractAuditLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContractAuditLocalService
 * @generated
 */
@ProviderType
public class ContractAuditLocalServiceWrapper
	implements ContractAuditLocalService,
		ServiceWrapper<ContractAuditLocalService> {
	public ContractAuditLocalServiceWrapper(
		ContractAuditLocalService contractAuditLocalService) {
		_contractAuditLocalService = contractAuditLocalService;
	}

	@Override
	public boolean hasContractAudit(long classNameId, long classPK, int type,
		java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.hasContractAudit(classNameId,
			classPK, type, signatoryClassName, signatoryClassPK);
	}

	@Override
	public boolean hasContractAudit(long contractEntryId,
		java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.hasContractAudit(contractEntryId,
			signatoryClassName, signatoryClassPK);
	}

	@Override
	public boolean hasContractAudit(long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.hasContractAudit(userId,
			contractEntryId);
	}

	@Override
	public boolean hasLatestContractAudit(long classNameId, long classPK,
		int type, java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.hasLatestContractAudit(classNameId,
			classPK, type, signatoryClassName, signatoryClassPK);
	}

	/**
	* Adds the contract audit to the database. Also notifies the appropriate model listeners.
	*
	* @param contractAudit the contract audit
	* @return the contract audit that was added
	*/
	@Override
	public com.liferay.osb.model.ContractAudit addContractAudit(
		com.liferay.osb.model.ContractAudit contractAudit) {
		return _contractAuditLocalService.addContractAudit(contractAudit);
	}

	/**
	* Creates a new contract audit with the primary key. Does not add the contract audit to the database.
	*
	* @param contractAuditId the primary key for the new contract audit
	* @return the new contract audit
	*/
	@Override
	public com.liferay.osb.model.ContractAudit createContractAudit(
		long contractAuditId) {
		return _contractAuditLocalService.createContractAudit(contractAuditId);
	}

	/**
	* Deletes the contract audit from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAudit the contract audit
	* @return the contract audit that was removed
	*/
	@Override
	public com.liferay.osb.model.ContractAudit deleteContractAudit(
		com.liferay.osb.model.ContractAudit contractAudit) {
		return _contractAuditLocalService.deleteContractAudit(contractAudit);
	}

	/**
	* Deletes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit that was removed
	* @throws PortalException if a contract audit with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.ContractAudit deleteContractAudit(
		long contractAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractAuditLocalService.deleteContractAudit(contractAuditId);
	}

	@Override
	public com.liferay.osb.model.ContractAudit fetchContractAudit(
		long contractAuditId) {
		return _contractAuditLocalService.fetchContractAudit(contractAuditId);
	}

	/**
	* Returns the contract audit with the primary key.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit
	* @throws PortalException if a contract audit with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.ContractAudit getContractAudit(
		long contractAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractAuditLocalService.getContractAudit(contractAuditId);
	}

	/**
	* Updates the contract audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractAudit the contract audit
	* @return the contract audit that was updated
	*/
	@Override
	public com.liferay.osb.model.ContractAudit updateContractAudit(
		com.liferay.osb.model.ContractAudit contractAudit) {
		return _contractAuditLocalService.updateContractAudit(contractAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _contractAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contractAuditLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _contractAuditLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractAuditLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractAuditLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getContractAuditCount(long contractEntryId,
		java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.getContractAuditCount(contractEntryId,
			signatoryClassName, signatoryClassPK);
	}

	@Override
	public int getContractAuditCount(long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAuditLocalService.getContractAuditCount(userId,
			contractEntryId);
	}

	/**
	* Returns the number of contract audits.
	*
	* @return the number of contract audits
	*/
	@Override
	public int getContractAuditsCount() {
		return _contractAuditLocalService.getContractAuditsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _contractAuditLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _contractAuditLocalService.getOSGiServiceIdentifier();
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
		return _contractAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contractAuditLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contractAuditLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the contract audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @return the range of contract audits
	*/
	@Override
	public java.util.List<com.liferay.osb.model.ContractAudit> getContractAudits(
		int start, int end) {
		return _contractAuditLocalService.getContractAudits(start, end);
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
		return _contractAuditLocalService.dynamicQueryCount(dynamicQuery);
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
		return _contractAuditLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void addContractAudit(long userId, long contractEntryId,
		java.lang.String signatoryClassName, long signatoryClassPK,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_contractAuditLocalService.addContractAudit(userId, contractEntryId,
			signatoryClassName, signatoryClassPK, productClassName,
			productClassPK);
	}

	@Override
	public ContractAuditLocalService getWrappedService() {
		return _contractAuditLocalService;
	}

	@Override
	public void setWrappedService(
		ContractAuditLocalService contractAuditLocalService) {
		_contractAuditLocalService = contractAuditLocalService;
	}

	private ContractAuditLocalService _contractAuditLocalService;
}