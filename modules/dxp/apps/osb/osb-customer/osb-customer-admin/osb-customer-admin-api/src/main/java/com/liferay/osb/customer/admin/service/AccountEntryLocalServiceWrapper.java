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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLocalService
 * @generated
 */
public class AccountEntryLocalServiceWrapper
	implements AccountEntryLocalService,
			   ServiceWrapper<AccountEntryLocalService> {

	public AccountEntryLocalServiceWrapper(
		AccountEntryLocalService accountEntryLocalService) {

		_accountEntryLocalService = accountEntryLocalService;
	}

	/**
	 * Adds the account entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntry the account entry
	 * @return the account entry that was added
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry addAccountEntry(
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry) {

		return _accountEntryLocalService.addAccountEntry(accountEntry);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry addAccountEntry(
			long userId, String koroneikiAccountKey, String dossieraAccountKey,
			String name, String code, String instructions, int status,
			String[] languageIds, long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.addAccountEntry(
			userId, koroneikiAccountKey, dossieraAccountKey, name, code,
			instructions, status, languageIds, supportRegionIds);
	}

	@Override
	public void addSupportRegionAccountEntries(
		long supportRegionId,
		java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries) {

		_accountEntryLocalService.addSupportRegionAccountEntries(
			supportRegionId, accountEntries);
	}

	@Override
	public void addSupportRegionAccountEntries(
		long supportRegionId, long[] accountEntryIds) {

		_accountEntryLocalService.addSupportRegionAccountEntries(
			supportRegionId, accountEntryIds);
	}

	@Override
	public void addSupportRegionAccountEntry(
		long supportRegionId,
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry) {

		_accountEntryLocalService.addSupportRegionAccountEntry(
			supportRegionId, accountEntry);
	}

	@Override
	public void addSupportRegionAccountEntry(
		long supportRegionId, long accountEntryId) {

		_accountEntryLocalService.addSupportRegionAccountEntry(
			supportRegionId, accountEntryId);
	}

	@Override
	public void clearSupportRegionAccountEntries(long supportRegionId) {
		_accountEntryLocalService.clearSupportRegionAccountEntries(
			supportRegionId);
	}

	/**
	 * Creates a new account entry with the primary key. Does not add the account entry to the database.
	 *
	 * @param accountEntryId the primary key for the new account entry
	 * @return the new account entry
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry createAccountEntry(
		long accountEntryId) {

		return _accountEntryLocalService.createAccountEntry(accountEntryId);
	}

	/**
	 * Deletes the account entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntry the account entry
	 * @return the account entry that was removed
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry deleteAccountEntry(
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry) {

		return _accountEntryLocalService.deleteAccountEntry(accountEntry);
	}

	/**
	 * Deletes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry that was removed
	 * @throws PortalException if a account entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry deleteAccountEntry(
			long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.deleteAccountEntry(accountEntryId);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry deleteAccountEntry(
			long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.deleteAccountEntry(
			userId, accountEntryId);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry deleteAccountEntry(
			String koroneikiAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.deleteAccountEntry(
			koroneikiAccountKey);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteSupportRegionAccountEntries(
		long supportRegionId,
		java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries) {

		_accountEntryLocalService.deleteSupportRegionAccountEntries(
			supportRegionId, accountEntries);
	}

	@Override
	public void deleteSupportRegionAccountEntries(
		long supportRegionId, long[] accountEntryIds) {

		_accountEntryLocalService.deleteSupportRegionAccountEntries(
			supportRegionId, accountEntryIds);
	}

	@Override
	public void deleteSupportRegionAccountEntry(
		long supportRegionId,
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry) {

		_accountEntryLocalService.deleteSupportRegionAccountEntry(
			supportRegionId, accountEntry);
	}

	@Override
	public void deleteSupportRegionAccountEntry(
		long supportRegionId, long accountEntryId) {

		_accountEntryLocalService.deleteSupportRegionAccountEntry(
			supportRegionId, accountEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountEntryLocalService.dynamicQuery();
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

		return _accountEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _accountEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _accountEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _accountEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _accountEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry fetchAccountEntry(
		long accountEntryId) {

		return _accountEntryLocalService.fetchAccountEntry(accountEntryId);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry
		fetchCorpProjectAccountEntry(String corpProjectUuid) {

		return _accountEntryLocalService.fetchCorpProjectAccountEntry(
			corpProjectUuid);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry
		fetchKoroneikiAccountEntry(String koroneikiAccountKey) {

		return _accountEntryLocalService.fetchKoroneikiAccountEntry(
			koroneikiAccountKey);
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
	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(int start, int end) {

		return _accountEntryLocalService.getAccountEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(String dossieraAccountKey) {

		return _accountEntryLocalService.getAccountEntries(dossieraAccountKey);
	}

	/**
	 * Returns the number of account entries.
	 *
	 * @return the number of account entries
	 */
	@Override
	public int getAccountEntriesCount() {
		return _accountEntryLocalService.getAccountEntriesCount();
	}

	/**
	 * Returns the account entry with the primary key.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry
	 * @throws PortalException if a account entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry getAccountEntry(
			long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.getAccountEntry(accountEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _accountEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry
		getCorpProjectAccountEntry(String corpProjectUuid) {

		return _accountEntryLocalService.getCorpProjectAccountEntry(
			corpProjectUuid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _accountEntryLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry
			getKoroneikiAccountEntry(String koroneikiAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.getKoroneikiAccountEntry(
			koroneikiAccountKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		getSupportRegionAccountEntries(long supportRegionId) {

		return _accountEntryLocalService.getSupportRegionAccountEntries(
			supportRegionId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		getSupportRegionAccountEntries(
			long supportRegionId, int start, int end) {

		return _accountEntryLocalService.getSupportRegionAccountEntries(
			supportRegionId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		getSupportRegionAccountEntries(
			long supportRegionId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.customer.admin.model.AccountEntry>
					orderByComparator) {

		return _accountEntryLocalService.getSupportRegionAccountEntries(
			supportRegionId, start, end, orderByComparator);
	}

	@Override
	public int getSupportRegionAccountEntriesCount(long supportRegionId) {
		return _accountEntryLocalService.getSupportRegionAccountEntriesCount(
			supportRegionId);
	}

	/**
	 * Returns the supportRegionIds of the support regions associated with the account entry.
	 *
	 * @param accountEntryId the accountEntryId of the account entry
	 * @return long[] the supportRegionIds of support regions associated with the account entry
	 */
	@Override
	public long[] getSupportRegionPrimaryKeys(long accountEntryId) {
		return _accountEntryLocalService.getSupportRegionPrimaryKeys(
			accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		getUserActiveAccountEntries(long userId, int start, int end) {

		return _accountEntryLocalService.getUserActiveAccountEntries(
			userId, start, end);
	}

	@Override
	public boolean hasSupportRegionAccountEntries(long supportRegionId) {
		return _accountEntryLocalService.hasSupportRegionAccountEntries(
			supportRegionId);
	}

	@Override
	public boolean hasSupportRegionAccountEntry(
		long supportRegionId, long accountEntryId) {

		return _accountEntryLocalService.hasSupportRegionAccountEntry(
			supportRegionId, accountEntryId);
	}

	@Override
	public boolean hasValidLicenseAccountEntry(long userId) {
		return _accountEntryLocalService.hasValidLicenseAccountEntry(userId);
	}

	@Override
	public boolean hasValidSupportAccountEntry(
		long userId, boolean ticketSupport) {

		return _accountEntryLocalService.hasValidSupportAccountEntry(
			userId, ticketSupport);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		search(
			String keywords, java.util.LinkedHashMap<String, Object> params,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _accountEntryLocalService.search(
			keywords, params, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		search(
			String koroneikiAccountKey, String dossieraAccountKey, String name,
			String code, int[] statuses, String instructions,
			java.util.LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _accountEntryLocalService.search(
			koroneikiAccountKey, dossieraAccountKey, name, code, statuses,
			instructions, params, andOperator, start, end, obc);
	}

	@Override
	public int searchCount(
		String keywords, java.util.LinkedHashMap<String, Object> params) {

		return _accountEntryLocalService.searchCount(keywords, params);
	}

	@Override
	public int searchCount(
		String koroneikiAccountKey, String dossieraAccountKey, String name,
		String code, int[] statuses, String instructions,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator) {

		return _accountEntryLocalService.searchCount(
			koroneikiAccountKey, dossieraAccountKey, name, code, statuses,
			instructions, params, andOperator);
	}

	@Override
	public void setSupportRegionAccountEntries(
		long supportRegionId, long[] accountEntryIds) {

		_accountEntryLocalService.setSupportRegionAccountEntries(
			supportRegionId, accountEntryIds);
	}

	/**
	 * Updates the account entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntry the account entry
	 * @return the account entry that was updated
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry updateAccountEntry(
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry) {

		return _accountEntryLocalService.updateAccountEntry(accountEntry);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry updateAccountEntry(
			long userId, long accountEntryId, String koroneikiAccountKey,
			String dossieraAccountKey, String name, String code,
			String instructions, int status, String[] languageIds,
			long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.updateAccountEntry(
			userId, accountEntryId, koroneikiAccountKey, dossieraAccountKey,
			name, code, instructions, status, languageIds, supportRegionIds);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry updateInstructions(
			long userId, long accountEntryId, String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.updateInstructions(
			userId, accountEntryId, instructions);
	}

	@Override
	public void updateLastZendeskAuditDate(
			long userId, long accountEntryId, String auditLabel,
			String auditValue)
		throws com.liferay.portal.kernel.exception.PortalException {

		_accountEntryLocalService.updateLastZendeskAuditDate(
			userId, accountEntryId, auditLabel, auditValue);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry updateStatus(
			long userId, long accountEntryId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountEntryLocalService.updateStatus(
			userId, accountEntryId, status, serviceContext);
	}

	@Override
	public AccountEntryLocalService getWrappedService() {
		return _accountEntryLocalService;
	}

	@Override
	public void setWrappedService(
		AccountEntryLocalService accountEntryLocalService) {

		_accountEntryLocalService = accountEntryLocalService;
	}

	private AccountEntryLocalService _accountEntryLocalService;

}