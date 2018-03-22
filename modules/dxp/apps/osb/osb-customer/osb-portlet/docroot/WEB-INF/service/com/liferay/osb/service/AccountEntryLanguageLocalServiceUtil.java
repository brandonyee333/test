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
 * Provides the local service utility for AccountEntryLanguage. This utility wraps
 * {@link com.liferay.osb.service.impl.AccountEntryLanguageLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguageLocalService
 * @see com.liferay.osb.service.base.AccountEntryLanguageLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountEntryLanguageLocalServiceImpl
 * @generated
 */
@ProviderType
public class AccountEntryLanguageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AccountEntryLanguageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the account entry language to the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguage the account entry language
	* @return the account entry language that was added
	*/
	public static com.liferay.osb.model.AccountEntryLanguage addAccountEntryLanguage(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage) {
		return getService().addAccountEntryLanguage(accountEntryLanguage);
	}

	/**
	* Creates a new account entry language with the primary key. Does not add the account entry language to the database.
	*
	* @param accountEntryLanguageId the primary key for the new account entry language
	* @return the new account entry language
	*/
	public static com.liferay.osb.model.AccountEntryLanguage createAccountEntryLanguage(
		long accountEntryLanguageId) {
		return getService().createAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	* Deletes the account entry language from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguage the account entry language
	* @return the account entry language that was removed
	*/
	public static com.liferay.osb.model.AccountEntryLanguage deleteAccountEntryLanguage(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage) {
		return getService().deleteAccountEntryLanguage(accountEntryLanguage);
	}

	/**
	* Deletes the account entry language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language that was removed
	* @throws PortalException if a account entry language with the primary key could not be found
	*/
	public static com.liferay.osb.model.AccountEntryLanguage deleteAccountEntryLanguage(
		long accountEntryLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAccountEntryLanguage(accountEntryLanguageId);
	}

	public static com.liferay.osb.model.AccountEntryLanguage fetchAccountEntryLanguage(
		long accountEntryLanguageId) {
		return getService().fetchAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	* Returns the account entry language with the primary key.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language
	* @throws PortalException if a account entry language with the primary key could not be found
	*/
	public static com.liferay.osb.model.AccountEntryLanguage getAccountEntryLanguage(
		long accountEntryLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	* Updates the account entry language in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguage the account entry language
	* @return the account entry language that was updated
	*/
	public static com.liferay.osb.model.AccountEntryLanguage updateAccountEntryLanguage(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage) {
		return getService().updateAccountEntryLanguage(accountEntryLanguage);
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
	* Returns the number of account entry languages.
	*
	* @return the number of account entry languages
	*/
	public static int getAccountEntryLanguagesCount() {
		return getService().getAccountEntryLanguagesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the account entry languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @return the range of account entry languages
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntryLanguage> getAccountEntryLanguages(
		int start, int end) {
		return getService().getAccountEntryLanguages(start, end);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntryLanguage> getAccountEntryLanguages(
		long accountEntryId) {
		return getService().getAccountEntryLanguages(accountEntryId);
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

	public static void setAccountEntryLanguageIds(long accountEntryId,
		java.lang.String[] languageIds) {
		getService().setAccountEntryLanguageIds(accountEntryId, languageIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static AccountEntryLanguageLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AccountEntryLanguageLocalService.class.getName());

			if (invokableLocalService instanceof AccountEntryLanguageLocalService) {
				_service = (AccountEntryLanguageLocalService)invokableLocalService;
			}
			else {
				_service = new AccountEntryLanguageLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AccountEntryLanguageLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static AccountEntryLanguageLocalService _service;
}