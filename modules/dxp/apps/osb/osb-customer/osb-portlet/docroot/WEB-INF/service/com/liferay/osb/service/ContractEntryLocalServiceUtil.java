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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the contract entry local service. This utility wraps {@link com.liferay.osb.service.impl.ContractEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntryLocalService
 * @see com.liferay.osb.service.base.ContractEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.ContractEntryLocalServiceImpl
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractEntry addContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addContractEntry(contractEntry);
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
	* Deletes the contract entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry that was removed
	* @throws PortalException if a contract entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractEntry deleteContractEntry(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteContractEntry(contractEntryId);
	}

	/**
	* Deletes the contract entry from the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @return the contract entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractEntry deleteContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteContractEntry(contractEntry);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.ContractEntry fetchContractEntry(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchContractEntry(contractEntryId);
	}

	/**
	* Returns the contract entry with the primary key.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry
	* @throws PortalException if a contract entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractEntry getContractEntry(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getContractEntry(contractEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.osb.model.ContractEntry> getContractEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getContractEntries(start, end);
	}

	/**
	* Returns the number of contract entries.
	*
	* @return the number of contract entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getContractEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getContractEntriesCount();
	}

	/**
	* Updates the contract entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @return the contract entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractEntry updateContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateContractEntry(contractEntry);
	}

	/**
	* Updates the contract entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractEntry the contract entry
	* @param merge whether to merge the contract entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the contract entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractEntry updateContractEntry(
		com.liferay.osb.model.ContractEntry contractEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateContractEntry(contractEntry, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.ContractEntry addContractEntry(
		long userId, long classNameId, long classPK, int type,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addContractEntry(userId, classNameId, classPK, type,
			contentMap, serviceContext);
	}

	public static com.liferay.osb.model.ContractEntry addContractEntry(
		long userId, java.lang.String className, long classPK, int type,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addContractEntry(userId, className, classPK, type,
			contentMap, serviceContext);
	}

	public static java.util.List<com.liferay.osb.model.ContractEntry> getContractEntries(
		long classNameId, long classPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getContractEntries(classNameId, classPK, type, start, end);
	}

	public static int getContractEntriesCount(long classNameId, long classPK,
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getContractEntriesCount(classNameId, classPK, type);
	}

	public static com.liferay.osb.model.ContractEntry getLatestContractEntry(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLatestContractEntry(classNameId, classPK, type);
	}

	public static com.liferay.osb.model.ContractEntry getLatestContractEntry(
		java.lang.String className, long classPK, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLatestContractEntry(className, classPK, type);
	}

	public static com.liferay.osb.model.ContractEntry updateContractEntry(
		long userId, long contractEntryId,
		java.util.Map<java.util.Locale, java.lang.String> contentMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateContractEntry(userId, contractEntryId, contentMap,
			serviceContext);
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

	/**
	 * @deprecated
	 */
	public void setService(ContractEntryLocalService service) {
	}

	private static ContractEntryLocalService _service;
}