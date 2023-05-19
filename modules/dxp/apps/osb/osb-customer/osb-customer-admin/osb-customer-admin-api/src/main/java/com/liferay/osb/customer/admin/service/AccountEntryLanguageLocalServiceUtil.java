/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.osb.customer.admin.model.AccountEntryLanguage;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AccountEntryLanguage. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.AccountEntryLanguageLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguageLocalService
 * @generated
 */
public class AccountEntryLanguageLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEntryLanguageLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the account entry language to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEntryLanguageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEntryLanguage the account entry language
	 * @return the account entry language that was added
	 */
	public static AccountEntryLanguage addAccountEntryLanguage(
		AccountEntryLanguage accountEntryLanguage) {

		return getService().addAccountEntryLanguage(accountEntryLanguage);
	}

	/**
	 * Creates a new account entry language with the primary key. Does not add the account entry language to the database.
	 *
	 * @param accountEntryLanguageId the primary key for the new account entry language
	 * @return the new account entry language
	 */
	public static AccountEntryLanguage createAccountEntryLanguage(
		long accountEntryLanguageId) {

		return getService().createAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	 * Deletes the account entry language from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEntryLanguageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEntryLanguage the account entry language
	 * @return the account entry language that was removed
	 */
	public static AccountEntryLanguage deleteAccountEntryLanguage(
		AccountEntryLanguage accountEntryLanguage) {

		return getService().deleteAccountEntryLanguage(accountEntryLanguage);
	}

	/**
	 * Deletes the account entry language with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEntryLanguageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEntryLanguageId the primary key of the account entry language
	 * @return the account entry language that was removed
	 * @throws PortalException if a account entry language with the primary key could not be found
	 */
	public static AccountEntryLanguage deleteAccountEntryLanguage(
			long accountEntryLanguageId)
		throws PortalException {

		return getService().deleteAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEntryLanguageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEntryLanguageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static AccountEntryLanguage fetchAccountEntryLanguage(
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
	public static AccountEntryLanguage getAccountEntryLanguage(
			long accountEntryLanguageId)
		throws PortalException {

		return getService().getAccountEntryLanguage(accountEntryLanguageId);
	}

	/**
	 * Returns a range of all the account entry languages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEntryLanguageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entry languages
	 * @param end the upper bound of the range of account entry languages (not inclusive)
	 * @return the range of account entry languages
	 */
	public static List<AccountEntryLanguage> getAccountEntryLanguages(
		int start, int end) {

		return getService().getAccountEntryLanguages(start, end);
	}

	public static List<AccountEntryLanguage> getAccountEntryLanguages(
		long accountEntryId) {

		return getService().getAccountEntryLanguages(accountEntryId);
	}

	/**
	 * Returns the number of account entry languages.
	 *
	 * @return the number of account entry languages
	 */
	public static int getAccountEntryLanguagesCount() {
		return getService().getAccountEntryLanguagesCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static void setAccountEntryLanguageIds(
		long accountEntryId, String[] languageIds) {

		getService().setAccountEntryLanguageIds(accountEntryId, languageIds);
	}

	/**
	 * Updates the account entry language in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEntryLanguageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEntryLanguage the account entry language
	 * @return the account entry language that was updated
	 */
	public static AccountEntryLanguage updateAccountEntryLanguage(
		AccountEntryLanguage accountEntryLanguage) {

		return getService().updateAccountEntryLanguage(accountEntryLanguage);
	}

	public static AccountEntryLanguageLocalService getService() {
		return _service;
	}

	public static void setService(AccountEntryLanguageLocalService service) {
		_service = service;
	}

	private static volatile AccountEntryLanguageLocalService _service;

}