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
 * Provides the local service utility for ContractEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.ContractEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntryLocalService
 * @see com.liferay.osb.service.base.ContractEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.ContractEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class ContractEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.ContractEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the contract entry to the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @return the contract entry that was added
	*/
	public static com.liferay.osb.model.ContractEntry addContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry) {
		return getService().addContractEntry(contractEntry);
	}

	public static com.liferay.osb.model.ContractEntry addContractEntry(
		long userId, java.lang.String className, long classPK, int type,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addContractEntry(userId, className, classPK, type,
			contentMap, serviceContext);
	}

	public static com.liferay.osb.model.ContractEntry addContractEntry(
		long userId, long classNameId, long classPK, int type,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addContractEntry(userId, classNameId, classPK, type,
			contentMap, serviceContext);
	}

	/**
	* Creates a new contract entry with the primary key. Does not add the contract entry to the database.
	*
	* @param contractEntryId the primary key for the new contract entry
	* @return the new contract entry
	*/
	public static com.liferay.osb.model.ContractEntry createContractEntry(
		long contractEntryId) {
		return getService().createContractEntry(contractEntryId);
	}

	/**
	* Deletes the contract entry from the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @return the contract entry that was removed
	*/
	public static com.liferay.osb.model.ContractEntry deleteContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry) {
		return getService().deleteContractEntry(contractEntry);
	}

	/**
	* Deletes the contract entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry that was removed
	* @throws PortalException if a contract entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.ContractEntry deleteContractEntry(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteContractEntry(contractEntryId);
	}

	public static com.liferay.osb.model.ContractEntry fetchContractEntry(
		long contractEntryId) {
		return getService().fetchContractEntry(contractEntryId);
	}

	/**
	* Returns the contract entry with the primary key.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry
	* @throws PortalException if a contract entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.ContractEntry getContractEntry(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getContractEntry(contractEntryId);
	}

	public static com.liferay.osb.model.ContractEntry getLatestContractEntry(
		java.lang.String className, long classPK, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLatestContractEntry(className, classPK, type);
	}

	public static com.liferay.osb.model.ContractEntry getLatestContractEntry(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLatestContractEntry(classNameId, classPK, type);
	}

	/**
	* Updates the contract entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @return the contract entry that was updated
	*/
	public static com.liferay.osb.model.ContractEntry updateContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry) {
		return getService().updateContractEntry(contractEntry);
	}

	public static com.liferay.osb.model.ContractEntry updateContractEntry(
		long userId, long contractEntryId,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateContractEntry(userId, contractEntryId, contentMap,
			serviceContext);
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

	/**
	* Returns the number of contract entries.
	*
	* @return the number of contract entries
	*/
	public static int getContractEntriesCount() {
		return getService().getContractEntriesCount();
	}

	public static int getContractEntriesCount(long classNameId, long classPK,
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getContractEntriesCount(classNameId, classPK, type);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<com.liferay.osb.model.ContractEntry> getContractEntries(
		int start, int end) {
		return getService().getContractEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.model.ContractEntry> getContractEntries(
		long classNameId, long classPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getContractEntries(classNameId, classPK, type, start, end);
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

	public static void clearService() {
		_service = null;
	}

	public static ContractEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ContractEntryLocalService.class.getName());

			if (invokableLocalService instanceof ContractEntryLocalService) {
				_service = (ContractEntryLocalService)invokableLocalService;
			}
			else {
				_service = new ContractEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ContractEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static ContractEntryLocalService _service;
}