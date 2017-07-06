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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for ContractAudit. This utility wraps
 * {@link com.liferay.osb.service.impl.ContractAuditLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContractAuditLocalService
 * @see com.liferay.osb.service.base.ContractAuditLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.ContractAuditLocalServiceImpl
 * @generated
 */
@ProviderType
public class ContractAuditLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.ContractAuditLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasContractAudit(long classNameId, long classPK,
		int type, java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasContractAudit(classNameId, classPK, type,
			signatoryClassName, signatoryClassPK);
	}

	public static boolean hasContractAudit(long contractEntryId,
		java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasContractAudit(contractEntryId, signatoryClassName,
			signatoryClassPK);
	}

	public static boolean hasContractAudit(long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasContractAudit(userId, contractEntryId);
	}

	public static boolean hasLatestContractAudit(long classNameId,
		long classPK, int type, java.lang.String signatoryClassName,
		long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasLatestContractAudit(classNameId, classPK, type,
			signatoryClassName, signatoryClassPK);
	}

	/**
	* Adds the contract audit to the database. Also notifies the appropriate model listeners.
	*
	* @param contractAudit the contract audit
	* @return the contract audit that was added
	*/
	public static com.liferay.osb.model.ContractAudit addContractAudit(
		com.liferay.osb.model.ContractAudit contractAudit) {
		return getService().addContractAudit(contractAudit);
	}

	/**
	* Creates a new contract audit with the primary key. Does not add the contract audit to the database.
	*
	* @param contractAuditId the primary key for the new contract audit
	* @return the new contract audit
	*/
	public static com.liferay.osb.model.ContractAudit createContractAudit(
		long contractAuditId) {
		return getService().createContractAudit(contractAuditId);
	}

	/**
	* Deletes the contract audit from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAudit the contract audit
	* @return the contract audit that was removed
	*/
	public static com.liferay.osb.model.ContractAudit deleteContractAudit(
		com.liferay.osb.model.ContractAudit contractAudit) {
		return getService().deleteContractAudit(contractAudit);
	}

	/**
	* Deletes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit that was removed
	* @throws PortalException if a contract audit with the primary key could not be found
	*/
	public static com.liferay.osb.model.ContractAudit deleteContractAudit(
		long contractAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteContractAudit(contractAuditId);
	}

	public static com.liferay.osb.model.ContractAudit fetchContractAudit(
		long contractAuditId) {
		return getService().fetchContractAudit(contractAuditId);
	}

	/**
	* Returns the contract audit with the primary key.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit
	* @throws PortalException if a contract audit with the primary key could not be found
	*/
	public static com.liferay.osb.model.ContractAudit getContractAudit(
		long contractAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getContractAudit(contractAuditId);
	}

	/**
	* Updates the contract audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractAudit the contract audit
	* @return the contract audit that was updated
	*/
	public static com.liferay.osb.model.ContractAudit updateContractAudit(
		com.liferay.osb.model.ContractAudit contractAudit) {
		return getService().updateContractAudit(contractAudit);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static int getContractAuditCount(long contractEntryId,
		java.lang.String signatoryClassName, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getContractAuditCount(contractEntryId, signatoryClassName,
			signatoryClassPK);
	}

	public static int getContractAuditCount(long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getContractAuditCount(userId, contractEntryId);
	}

	/**
	* Returns the number of contract audits.
	*
	* @return the number of contract audits
	*/
	public static int getContractAuditsCount() {
		return getService().getContractAuditsCount();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<com.liferay.osb.model.ContractAudit> getContractAudits(
		int start, int end) {
		return getService().getContractAudits(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void addContractAudit(long userId, long contractEntryId,
		java.lang.String signatoryClassName, long signatoryClassPK,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addContractAudit(userId, contractEntryId, signatoryClassName,
			signatoryClassPK, productClassName, productClassPK);
	}

	public static void clearService() {
		_service = null;
	}

	public static ContractAuditLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ContractAuditLocalService.class.getName());

			if (invokableLocalService instanceof ContractAuditLocalService) {
				_service = (ContractAuditLocalService)invokableLocalService;
			}
			else {
				_service = new ContractAuditLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ContractAuditLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static ContractAuditLocalService _service;
}