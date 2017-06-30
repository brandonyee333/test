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
 * The utility for the account environment local service. This utility wraps {@link com.liferay.osb.service.impl.AccountEnvironmentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentLocalService
 * @see com.liferay.osb.service.base.AccountEnvironmentLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountEnvironmentLocalServiceImpl
 * @generated
 */
public class AccountEnvironmentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AccountEnvironmentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the account environment to the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironment the account environment
	* @return the account environment that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEnvironment addAccountEnvironment(
		com.liferay.osb.model.AccountEnvironment accountEnvironment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAccountEnvironment(accountEnvironment);
	}

	/**
	* Creates a new account environment with the primary key. Does not add the account environment to the database.
	*
	* @param accountEnvironmentId the primary key for the new account environment
	* @return the new account environment
	*/
	public static com.liferay.osb.model.AccountEnvironment createAccountEnvironment(
		long accountEnvironmentId) {
		return getService().createAccountEnvironment(accountEnvironmentId);
	}

	/**
	* Deletes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentId the primary key of the account environment
	* @return the account environment that was removed
	* @throws PortalException if a account environment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEnvironment deleteAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAccountEnvironment(accountEnvironmentId);
	}

	/**
	* Deletes the account environment from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironment the account environment
	* @return the account environment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEnvironment deleteAccountEnvironment(
		com.liferay.osb.model.AccountEnvironment accountEnvironment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAccountEnvironment(accountEnvironment);
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

	public static com.liferay.osb.model.AccountEnvironment fetchAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAccountEnvironment(accountEnvironmentId);
	}

	/**
	* Returns the account environment with the primary key.
	*
	* @param accountEnvironmentId the primary key of the account environment
	* @return the account environment
	* @throws PortalException if a account environment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEnvironment getAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEnvironment(accountEnvironmentId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account environments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @return the range of account environments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEnvironment> getAccountEnvironments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEnvironments(start, end);
	}

	/**
	* Returns the number of account environments.
	*
	* @return the number of account environments
	* @throws SystemException if a system exception occurred
	*/
	public static int getAccountEnvironmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEnvironmentsCount();
	}

	/**
	* Updates the account environment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironment the account environment
	* @return the account environment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEnvironment updateAccountEnvironment(
		com.liferay.osb.model.AccountEnvironment accountEnvironment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAccountEnvironment(accountEnvironment);
	}

	/**
	* Updates the account environment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironment the account environment
	* @param merge whether to merge the account environment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account environment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEnvironment updateAccountEnvironment(
		com.liferay.osb.model.AccountEnvironment accountEnvironment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAccountEnvironment(accountEnvironment, merge);
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

	public static com.liferay.osb.model.AccountEnvironment addAccountEnvironment(
		long userId, long accountEntryId, long productEntryId,
		java.lang.String name, int envOS, java.lang.String envOSCustom,
		int envDB, int envJVM, int envAS, int envLFR,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAccountEnvironment(userId, accountEntryId,
			productEntryId, name, envOS, envOSCustom, envDB, envJVM, envAS,
			envLFR, files, types);
	}

	public static com.liferay.osb.model.AccountEnvironment fetchAccountEnvironment(
		long accountEntryId, long productEntryId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchAccountEnvironment(accountEntryId, productEntryId, name);
	}

	public static java.util.List<com.liferay.osb.model.AccountEnvironment> getAccountEnvironments(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEnvironments(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.AccountEnvironment> getAccountEnvironments(
		long accountEntryId, long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAccountEnvironments(accountEntryId, productEntryId);
	}

	public static java.util.Map<java.lang.String, java.util.List<com.liferay.osb.model.AccountEnvironment>> getAccountEnvironmentsMap(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEnvironmentsMap(accountEntryId);
	}

	public static com.liferay.osb.model.AccountEnvironment updateAccountEnvironment(
		long userId, long accountEnvironmentId, long productEntryId,
		java.lang.String name, int envOS, java.lang.String envOSCustom,
		int envDB, int envJVM, int envAS, int envLFR,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAccountEnvironment(userId, accountEnvironmentId,
			productEntryId, name, envOS, envOSCustom, envDB, envJVM, envAS,
			envLFR, files, types);
	}

	public static void clearService() {
		_service = null;
	}

	public static AccountEnvironmentLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AccountEnvironmentLocalService.class.getName());

			if (invokableLocalService instanceof AccountEnvironmentLocalService) {
				_service = (AccountEnvironmentLocalService)invokableLocalService;
			}
			else {
				_service = new AccountEnvironmentLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AccountEnvironmentLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AccountEnvironmentLocalService service) {
	}

	private static AccountEnvironmentLocalService _service;
}