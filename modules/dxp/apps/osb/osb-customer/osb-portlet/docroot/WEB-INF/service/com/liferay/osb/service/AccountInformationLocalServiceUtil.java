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
 * Provides the local service utility for AccountInformation. This utility wraps
 * {@link com.liferay.osb.service.impl.AccountInformationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountInformationLocalService
 * @see com.liferay.osb.service.base.AccountInformationLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountInformationLocalServiceImpl
 * @generated
 */
@ProviderType
public class AccountInformationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AccountInformationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the account information to the database. Also notifies the appropriate model listeners.
	*
	* @param accountInformation the account information
	* @return the account information that was added
	*/
	public static com.liferay.osb.model.AccountInformation addAccountInformation(
		com.liferay.osb.model.AccountInformation accountInformation) {
		return getService().addAccountInformation(accountInformation);
	}

	/**
	* Creates a new account information with the primary key. Does not add the account information to the database.
	*
	* @param accountInformationId the primary key for the new account information
	* @return the new account information
	*/
	public static com.liferay.osb.model.AccountInformation createAccountInformation(
		long accountInformationId) {
		return getService().createAccountInformation(accountInformationId);
	}

	/**
	* Deletes the account information from the database. Also notifies the appropriate model listeners.
	*
	* @param accountInformation the account information
	* @return the account information that was removed
	*/
	public static com.liferay.osb.model.AccountInformation deleteAccountInformation(
		com.liferay.osb.model.AccountInformation accountInformation) {
		return getService().deleteAccountInformation(accountInformation);
	}

	/**
	* Deletes the account information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information that was removed
	* @throws PortalException if a account information with the primary key could not be found
	*/
	public static com.liferay.osb.model.AccountInformation deleteAccountInformation(
		long accountInformationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAccountInformation(accountInformationId);
	}

	public static com.liferay.osb.model.AccountInformation fetchAccountInformation(
		long accountInformationId) {
		return getService().fetchAccountInformation(accountInformationId);
	}

	/**
	* Returns the account information with the primary key.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information
	* @throws PortalException if a account information with the primary key could not be found
	*/
	public static com.liferay.osb.model.AccountInformation getAccountInformation(
		long accountInformationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountInformation(accountInformationId);
	}

	/**
	* Updates the account information in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountInformation the account information
	* @return the account information that was updated
	*/
	public static com.liferay.osb.model.AccountInformation updateAccountInformation(
		com.liferay.osb.model.AccountInformation accountInformation) {
		return getService().updateAccountInformation(accountInformation);
	}

	public static com.liferay.osb.model.AccountInformationDisplay getAccountInformationDisplay(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountInformationDisplay(accountEntryId);
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
	* Returns the number of account informations.
	*
	* @return the number of account informations
	*/
	public static int getAccountInformationsCount() {
		return getService().getAccountInformationsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.osb.model.AccountInformation> getAccountEntryAccountInformation(
		long accountEntryId) {
		return getService().getAccountEntryAccountInformation(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.AccountInformation> getAccountInformation(
		long accountEntryId, long accountProjectId) {
		return getService()
				   .getAccountInformation(accountEntryId, accountProjectId);
	}

	/**
	* Returns a range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of account informations
	*/
	public static java.util.List<com.liferay.osb.model.AccountInformation> getAccountInformations(
		int start, int end) {
		return getService().getAccountInformations(start, end);
	}

	public static java.util.List<com.liferay.osb.model.AccountInformation> updateAccountInformation(
		long userId, long accountEntryId, long accountProjectId,
		java.util.Map<java.lang.Integer, java.lang.String> data)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateAccountInformation(userId, accountEntryId,
			accountProjectId, data);
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

	public static void deleteAccountInformation(long accountEntryId,
		long accountProjectId) {
		getService().deleteAccountInformation(accountEntryId, accountProjectId);
	}

	public static void clearService() {
		_service = null;
	}

	public static AccountInformationLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AccountInformationLocalService.class.getName());

			if (invokableLocalService instanceof AccountInformationLocalService) {
				_service = (AccountInformationLocalService)invokableLocalService;
			}
			else {
				_service = new AccountInformationLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AccountInformationLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static AccountInformationLocalService _service;
}