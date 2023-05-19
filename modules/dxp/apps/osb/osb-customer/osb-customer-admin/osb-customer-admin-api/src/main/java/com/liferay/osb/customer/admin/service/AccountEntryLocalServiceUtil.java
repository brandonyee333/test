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

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AccountEntry. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.AccountEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLocalService
 * @generated
 */
public class AccountEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the account entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEntry the account entry
	 * @return the account entry that was added
	 */
	public static AccountEntry addAccountEntry(AccountEntry accountEntry) {
		return getService().addAccountEntry(accountEntry);
	}

	public static AccountEntry addAccountEntry(
			long userId, String koroneikiAccountKey, String dossieraAccountKey,
			String corpProjectUuid, long corpProjectId, String name,
			String code, String instructions, boolean activeSupport,
			boolean activeTicketSupport, int status, String[] languageIds)
		throws PortalException {

		return getService().addAccountEntry(
			userId, koroneikiAccountKey, dossieraAccountKey, corpProjectUuid,
			corpProjectId, name, code, instructions, activeSupport,
			activeTicketSupport, status, languageIds);
	}

	/**
	 * Creates a new account entry with the primary key. Does not add the account entry to the database.
	 *
	 * @param accountEntryId the primary key for the new account entry
	 * @return the new account entry
	 */
	public static AccountEntry createAccountEntry(long accountEntryId) {
		return getService().createAccountEntry(accountEntryId);
	}

	/**
	 * Deletes the account entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEntry the account entry
	 * @return the account entry that was removed
	 */
	public static AccountEntry deleteAccountEntry(AccountEntry accountEntry) {
		return getService().deleteAccountEntry(accountEntry);
	}

	/**
	 * Deletes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry that was removed
	 * @throws PortalException if a account entry with the primary key could not be found
	 */
	public static AccountEntry deleteAccountEntry(long accountEntryId)
		throws PortalException {

		return getService().deleteAccountEntry(accountEntryId);
	}

	public static AccountEntry deleteAccountEntry(
			long userId, long accountEntryId)
		throws PortalException {

		return getService().deleteAccountEntry(userId, accountEntryId);
	}

	public static AccountEntry deleteAccountEntry(String koroneikiAccountKey)
		throws PortalException {

		return getService().deleteAccountEntry(koroneikiAccountKey);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEntryModelImpl</code>.
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

	public static AccountEntry fetchAccountEntry(long accountEntryId) {
		return getService().fetchAccountEntry(accountEntryId);
	}

	public static AccountEntry fetchCorpProjectAccountEntry(
		String corpProjectUuid) {

		return getService().fetchCorpProjectAccountEntry(corpProjectUuid);
	}

	public static AccountEntry fetchKoroneikiAccountEntry(
		String koroneikiAccountKey) {

		return getService().fetchKoroneikiAccountEntry(koroneikiAccountKey);
	}

	/**
	 * Returns a range of all the account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of account entries
	 */
	public static List<AccountEntry> getAccountEntries(int start, int end) {
		return getService().getAccountEntries(start, end);
	}

	public static List<AccountEntry> getAccountEntries(
		String dossieraAccountKey) {

		return getService().getAccountEntries(dossieraAccountKey);
	}

	/**
	 * Returns the number of account entries.
	 *
	 * @return the number of account entries
	 */
	public static int getAccountEntriesCount() {
		return getService().getAccountEntriesCount();
	}

	/**
	 * Returns the account entry with the primary key.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry
	 * @throws PortalException if a account entry with the primary key could not be found
	 */
	public static AccountEntry getAccountEntry(long accountEntryId)
		throws PortalException {

		return getService().getAccountEntry(accountEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<AccountEntry> getExpiredSupportAccountEntries(
		int start, int end) {

		return getService().getExpiredSupportAccountEntries(start, end);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static AccountEntry getKoroneikiAccountEntry(
			String koroneikiAccountKey)
		throws PortalException {

		return getService().getKoroneikiAccountEntry(koroneikiAccountKey);
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

	public static List<AccountEntry> search(
		String keywords, java.util.LinkedHashMap<String, Object> params,
		int start, int end, OrderByComparator obc) {

		return getService().search(keywords, params, start, end, obc);
	}

	public static List<AccountEntry> search(String name, String code) {
		return getService().search(name, code);
	}

	public static List<AccountEntry> search(
		String koroneikiAccountKey, String dossieraAccountKey, String name,
		String code, int[] statuses, String instructions,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end, OrderByComparator obc) {

		return getService().search(
			koroneikiAccountKey, dossieraAccountKey, name, code, statuses,
			instructions, params, andOperator, start, end, obc);
	}

	public static int searchCount(
		String keywords, java.util.LinkedHashMap<String, Object> params) {

		return getService().searchCount(keywords, params);
	}

	public static int searchCount(
		String koroneikiAccountKey, String dossieraAccountKey, String name,
		String code, int[] statuses, String instructions,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator) {

		return getService().searchCount(
			koroneikiAccountKey, dossieraAccountKey, name, code, statuses,
			instructions, params, andOperator);
	}

	/**
	 * Updates the account entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEntry the account entry
	 * @return the account entry that was updated
	 */
	public static AccountEntry updateAccountEntry(AccountEntry accountEntry) {
		return getService().updateAccountEntry(accountEntry);
	}

	public static AccountEntry updateAccountEntry(
			long accountEntryId, boolean activeSupport,
			boolean activeTicketSupport, int status)
		throws PortalException {

		return getService().updateAccountEntry(
			accountEntryId, activeSupport, activeTicketSupport, status);
	}

	public static AccountEntry updateAccountEntry(
			long userId, long accountEntryId, String koroneikiAccountKey,
			String dossieraAccountKey)
		throws PortalException {

		return getService().updateAccountEntry(
			userId, accountEntryId, koroneikiAccountKey, dossieraAccountKey);
	}

	public static AccountEntry updateAccountEntry(
			long userId, long accountEntryId, String koroneikiAccountKey,
			String dossieraAccountKey, String corpProjectUuid,
			long corpProjectId, String name, String code, String instructions,
			boolean activeSupport, boolean activeTicketSupport, int status,
			String[] languageIds)
		throws PortalException {

		return getService().updateAccountEntry(
			userId, accountEntryId, koroneikiAccountKey, dossieraAccountKey,
			corpProjectUuid, corpProjectId, name, code, instructions,
			activeSupport, activeTicketSupport, status, languageIds);
	}

	public static AccountEntry updateInstructions(
			long userId, long accountEntryId, String instructions)
		throws PortalException {

		return getService().updateInstructions(
			userId, accountEntryId, instructions);
	}

	public static void updateLastZendeskAuditDate(
			long userId, long accountEntryId, String auditLabel,
			String auditValue)
		throws PortalException {

		getService().updateLastZendeskAuditDate(
			userId, accountEntryId, auditLabel, auditValue);
	}

	public static AccountEntryLocalService getService() {
		return _service;
	}

	public static void setService(AccountEntryLocalService service) {
		_service = service;
	}

	private static volatile AccountEntryLocalService _service;

}