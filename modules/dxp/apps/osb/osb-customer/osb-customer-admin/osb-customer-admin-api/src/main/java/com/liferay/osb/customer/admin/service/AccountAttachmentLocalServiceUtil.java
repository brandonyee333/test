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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for AccountAttachment. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.AccountAttachmentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountAttachmentLocalService
 * @generated
 */
public class AccountAttachmentLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountAttachmentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the account attachment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountAttachment the account attachment
	 * @return the account attachment that was added
	 */
	public static com.liferay.osb.customer.admin.model.AccountAttachment
		addAccountAttachment(
			com.liferay.osb.customer.admin.model.AccountAttachment
				accountAttachment) {

		return getService().addAccountAttachment(accountAttachment);
	}

	public static com.liferay.osb.customer.admin.model.AccountAttachment
			addAccountAttachment(
				long userId, long accountEntryId, long accountProjectId,
				com.liferay.portal.kernel.util.ObjectValuePair
					<String, java.io.File> fileOVP,
				int type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addAccountAttachment(
			userId, accountEntryId, accountProjectId, fileOVP, type);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountAttachment>
				addAccountAttachments(
					long userId, long accountEntryId, long accountProjectId,
					java.util.List
						<com.liferay.portal.kernel.util.ObjectValuePair
							<String, java.io.File>> files,
					java.util.List<Integer> types)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addAccountAttachments(
			userId, accountEntryId, accountProjectId, files, types);
	}

	/**
	 * Creates a new account attachment with the primary key. Does not add the account attachment to the database.
	 *
	 * @param accountAttachmentId the primary key for the new account attachment
	 * @return the new account attachment
	 */
	public static com.liferay.osb.customer.admin.model.AccountAttachment
		createAccountAttachment(long accountAttachmentId) {

		return getService().createAccountAttachment(accountAttachmentId);
	}

	/**
	 * Deletes the account attachment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountAttachment the account attachment
	 * @return the account attachment that was removed
	 * @throws PortalException
	 */
	public static com.liferay.osb.customer.admin.model.AccountAttachment
			deleteAccountAttachment(
				com.liferay.osb.customer.admin.model.AccountAttachment
					accountAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAccountAttachment(accountAttachment);
	}

	/**
	 * Deletes the account attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment that was removed
	 * @throws PortalException if a account attachment with the primary key could not be found
	 */
	public static com.liferay.osb.customer.admin.model.AccountAttachment
			deleteAccountAttachment(long accountAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAccountAttachment(accountAttachmentId);
	}

	public static void deleteAccountAttachments(
			long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteAccountAttachments(accountEntryId, accountProjectId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountAttachmentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountAttachmentModelImpl</code>.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.osb.customer.admin.model.AccountAttachment
		fetchAccountAttachment(long accountAttachmentId) {

		return getService().fetchAccountAttachment(accountAttachmentId);
	}

	/**
	 * Returns the account attachment with the primary key.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment
	 * @throws PortalException if a account attachment with the primary key could not be found
	 */
	public static com.liferay.osb.customer.admin.model.AccountAttachment
			getAccountAttachment(long accountAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountAttachment(accountAttachmentId);
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
	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountAttachment>
			getAccountAttachments(int start, int end) {

		return getService().getAccountAttachments(start, end);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountAttachment>
			getAccountAttachments(long accountEntryId) {

		return getService().getAccountAttachments(accountEntryId);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountAttachment>
			getAccountAttachments(long accountEntryId, long accountProjectId) {

		return getService().getAccountAttachments(
			accountEntryId, accountProjectId);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountAttachment>
			getAccountAttachments(
				long accountEntryId, long accountProjectId, int type) {

		return getService().getAccountAttachments(
			accountEntryId, accountProjectId, type);
	}

	/**
	 * Returns the number of account attachments.
	 *
	 * @return the number of account attachments
	 */
	public static int getAccountAttachmentsCount() {
		return getService().getAccountAttachmentsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.io.InputStream getFileAsStream(
			com.liferay.osb.customer.admin.model.AccountAttachment
				accountAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFileAsStream(accountAttachment);
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
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the account attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountAttachment the account attachment
	 * @return the account attachment that was updated
	 */
	public static com.liferay.osb.customer.admin.model.AccountAttachment
		updateAccountAttachment(
			com.liferay.osb.customer.admin.model.AccountAttachment
				accountAttachment) {

		return getService().updateAccountAttachment(accountAttachment);
	}

	public static AccountAttachmentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<AccountAttachmentLocalService, AccountAttachmentLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AccountAttachmentLocalService.class);

		ServiceTracker
			<AccountAttachmentLocalService, AccountAttachmentLocalService>
				serviceTracker =
					new ServiceTracker
						<AccountAttachmentLocalService,
						 AccountAttachmentLocalService>(
							 bundle.getBundleContext(),
							 AccountAttachmentLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}