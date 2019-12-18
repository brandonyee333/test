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
 * Provides a wrapper for {@link AccountAttachmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountAttachmentLocalService
 * @generated
 */
public class AccountAttachmentLocalServiceWrapper
	implements AccountAttachmentLocalService,
			   ServiceWrapper<AccountAttachmentLocalService> {

	public AccountAttachmentLocalServiceWrapper(
		AccountAttachmentLocalService accountAttachmentLocalService) {

		_accountAttachmentLocalService = accountAttachmentLocalService;
	}

	/**
	 * Adds the account attachment to the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountAttachment the account attachment
	 * @return the account attachment that was added
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountAttachment
		addAccountAttachment(
			com.liferay.osb.customer.admin.model.AccountAttachment
				accountAttachment) {

		return _accountAttachmentLocalService.addAccountAttachment(
			accountAttachment);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountAttachment
			addAccountAttachment(
				long userId, long accountEntryId, long accountProjectId,
				com.liferay.portal.kernel.util.ObjectValuePair
					<String, java.io.File> fileOVP,
				int type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountAttachmentLocalService.addAccountAttachment(
			userId, accountEntryId, accountProjectId, fileOVP, type);
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.admin.model.AccountAttachment>
				addAccountAttachments(
					long userId, long accountEntryId, long accountProjectId,
					java.util.List
						<com.liferay.portal.kernel.util.ObjectValuePair
							<String, java.io.File>> files,
					java.util.List<Integer> types)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _accountAttachmentLocalService.addAccountAttachments(
			userId, accountEntryId, accountProjectId, files, types);
	}

	/**
	 * Creates a new account attachment with the primary key. Does not add the account attachment to the database.
	 *
	 * @param accountAttachmentId the primary key for the new account attachment
	 * @return the new account attachment
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountAttachment
		createAccountAttachment(long accountAttachmentId) {

		return _accountAttachmentLocalService.createAccountAttachment(
			accountAttachmentId);
	}

	/**
	 * Deletes the account attachment from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountAttachment the account attachment
	 * @return the account attachment that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountAttachment
			deleteAccountAttachment(
				com.liferay.osb.customer.admin.model.AccountAttachment
					accountAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountAttachmentLocalService.deleteAccountAttachment(
			accountAttachment);
	}

	/**
	 * Deletes the account attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment that was removed
	 * @throws PortalException if a account attachment with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountAttachment
			deleteAccountAttachment(long accountAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountAttachmentLocalService.deleteAccountAttachment(
			accountAttachmentId);
	}

	@Override
	public void deleteAccountAttachments(
			long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_accountAttachmentLocalService.deleteAccountAttachments(
			accountEntryId, accountProjectId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountAttachmentLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountAttachmentLocalService.dynamicQuery();
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

		return _accountAttachmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountAttachmentModelImpl</code>.
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

		return _accountAttachmentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountAttachmentModelImpl</code>.
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

		return _accountAttachmentLocalService.dynamicQuery(
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

		return _accountAttachmentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _accountAttachmentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountAttachment
		fetchAccountAttachment(long accountAttachmentId) {

		return _accountAttachmentLocalService.fetchAccountAttachment(
			accountAttachmentId);
	}

	/**
	 * Returns the account attachment with the primary key.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment
	 * @throws PortalException if a account attachment with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountAttachment
			getAccountAttachment(long accountAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountAttachmentLocalService.getAccountAttachment(
			accountAttachmentId);
	}

	/**
	 * Returns a range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of account attachments
	 */
	@Override
	public java.util.List
		<com.liferay.osb.customer.admin.model.AccountAttachment>
			getAccountAttachments(int start, int end) {

		return _accountAttachmentLocalService.getAccountAttachments(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.admin.model.AccountAttachment>
			getAccountAttachments(long accountEntryId) {

		return _accountAttachmentLocalService.getAccountAttachments(
			accountEntryId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.admin.model.AccountAttachment>
			getAccountAttachments(long accountEntryId, long accountProjectId) {

		return _accountAttachmentLocalService.getAccountAttachments(
			accountEntryId, accountProjectId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.admin.model.AccountAttachment>
			getAccountAttachments(
				long accountEntryId, long accountProjectId, int type) {

		return _accountAttachmentLocalService.getAccountAttachments(
			accountEntryId, accountProjectId, type);
	}

	/**
	 * Returns the number of account attachments.
	 *
	 * @return the number of account attachments
	 */
	@Override
	public int getAccountAttachmentsCount() {
		return _accountAttachmentLocalService.getAccountAttachmentsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _accountAttachmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.io.InputStream getFileAsStream(
			com.liferay.osb.customer.admin.model.AccountAttachment
				accountAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountAttachmentLocalService.getFileAsStream(
			accountAttachment);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _accountAttachmentLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountAttachmentLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountAttachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the account attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param accountAttachment the account attachment
	 * @return the account attachment that was updated
	 */
	@Override
	public com.liferay.osb.customer.admin.model.AccountAttachment
		updateAccountAttachment(
			com.liferay.osb.customer.admin.model.AccountAttachment
				accountAttachment) {

		return _accountAttachmentLocalService.updateAccountAttachment(
			accountAttachment);
	}

	@Override
	public AccountAttachmentLocalService getWrappedService() {
		return _accountAttachmentLocalService;
	}

	@Override
	public void setWrappedService(
		AccountAttachmentLocalService accountAttachmentLocalService) {

		_accountAttachmentLocalService = accountAttachmentLocalService;
	}

	private AccountAttachmentLocalService _accountAttachmentLocalService;

}