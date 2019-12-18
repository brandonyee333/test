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
 * Provides the local service utility for AccountEnvironmentAttachment. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentAttachmentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentLocalService
 * @generated
 */
public class AccountEnvironmentAttachmentLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentAttachmentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the account environment attachment to the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentAttachment the account environment attachment
	 * @return the account environment attachment that was added
	 */
	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
			addAccountEnvironmentAttachment(
				com.liferay.osb.customer.admin.model.
					AccountEnvironmentAttachment accountEnvironmentAttachment) {

		return getService().addAccountEnvironmentAttachment(
			accountEnvironmentAttachment);
	}

	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
				addAccountEnvironmentAttachment(
					long userId, long accountEnvironmentId,
					com.liferay.portal.kernel.util.ObjectValuePair
						<String, java.io.File> fileOVP,
					int type)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addAccountEnvironmentAttachment(
			userId, accountEnvironmentId, fileOVP, type);
	}

	public static void addAccountEnvironmentAttachments(
			long userId, long accountEnvironmentId,
			java.util.List
				<com.liferay.portal.kernel.util.ObjectValuePair
					<String, java.io.File>> files,
			java.util.List<Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().addAccountEnvironmentAttachments(
			userId, accountEnvironmentId, files, types);
	}

	/**
	 * Creates a new account environment attachment with the primary key. Does not add the account environment attachment to the database.
	 *
	 * @param accountEnvironmentAttachmentId the primary key for the new account environment attachment
	 * @return the new account environment attachment
	 */
	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
			createAccountEnvironmentAttachment(
				long accountEnvironmentAttachmentId) {

		return getService().createAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId);
	}

	/**
	 * Deletes the account environment attachment from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentAttachment the account environment attachment
	 * @return the account environment attachment that was removed
	 * @throws PortalException
	 */
	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
				deleteAccountEnvironmentAttachment(
					com.liferay.osb.customer.admin.model.
						AccountEnvironmentAttachment
							accountEnvironmentAttachment)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAccountEnvironmentAttachment(
			accountEnvironmentAttachment);
	}

	/**
	 * Deletes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment that was removed
	 * @throws PortalException if a account environment attachment with the primary key could not be found
	 */
	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
				deleteAccountEnvironmentAttachment(
					long accountEnvironmentAttachmentId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEnvironmentAttachmentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEnvironmentAttachmentModelImpl</code>.
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

	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
			fetchAccountEnvironmentAttachment(
				long accountEnvironmentAttachmentId) {

		return getService().fetchAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId);
	}

	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
			fetchAccountEnvironmentAttachment(
				long accountEnvironmentId, int type) {

		return getService().fetchAccountEnvironmentAttachment(
			accountEnvironmentId, type);
	}

	/**
	 * Returns the account environment attachment with the primary key.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment
	 * @throws PortalException if a account environment attachment with the primary key could not be found
	 */
	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
				getAccountEnvironmentAttachment(
					long accountEnvironmentAttachmentId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId);
	}

	/**
	 * Returns a range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @return the range of account environment attachments
	 */
	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment>
			getAccountEnvironmentAttachments(int start, int end) {

		return getService().getAccountEnvironmentAttachments(start, end);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment>
			getAccountEnvironmentAttachments(long accountEnvironmentId) {

		return getService().getAccountEnvironmentAttachments(
			accountEnvironmentId);
	}

	/**
	 * Returns the number of account environment attachments.
	 *
	 * @return the number of account environment attachments
	 */
	public static int getAccountEnvironmentAttachmentsCount() {
		return getService().getAccountEnvironmentAttachmentsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.io.File getFile(
			com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
				accountEnvironmentAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFile(accountEnvironmentAttachment);
	}

	public static java.io.InputStream getFileAsStream(
			com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
				accountEnvironmentAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFileAsStream(accountEnvironmentAttachment);
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

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the account environment attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentAttachment the account environment attachment
	 * @return the account environment attachment that was updated
	 */
	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
			updateAccountEnvironmentAttachment(
				com.liferay.osb.customer.admin.model.
					AccountEnvironmentAttachment accountEnvironmentAttachment) {

		return getService().updateAccountEnvironmentAttachment(
			accountEnvironmentAttachment);
	}

	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
				updateAccountEnvironmentAttachment(
					long accountEnvironmentAttachmentId,
					long accountEnvironmentId,
					com.liferay.portal.kernel.util.ObjectValuePair
						<String, java.io.File> fileOVP,
					int type)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId, accountEnvironmentId, fileOVP,
			type);
	}

	public static void updateAccountEnvironmentAttachments(
			long userId, long accountEnvironmentId,
			java.util.List
				<com.liferay.portal.kernel.util.ObjectValuePair
					<String, java.io.File>> files,
			java.util.List<Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateAccountEnvironmentAttachments(
			userId, accountEnvironmentId, files, types);
	}

	public static AccountEnvironmentAttachmentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<AccountEnvironmentAttachmentLocalService,
		 AccountEnvironmentAttachmentLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AccountEnvironmentAttachmentLocalService.class);

		ServiceTracker
			<AccountEnvironmentAttachmentLocalService,
			 AccountEnvironmentAttachmentLocalService> serviceTracker =
				new ServiceTracker
					<AccountEnvironmentAttachmentLocalService,
					 AccountEnvironmentAttachmentLocalService>(
						 bundle.getBundleContext(),
						 AccountEnvironmentAttachmentLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}