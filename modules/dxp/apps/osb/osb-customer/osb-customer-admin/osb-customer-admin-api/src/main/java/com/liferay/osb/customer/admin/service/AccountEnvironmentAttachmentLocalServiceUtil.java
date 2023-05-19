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

import com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

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

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentAttachmentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the account environment attachment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEnvironmentAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEnvironmentAttachment the account environment attachment
	 * @return the account environment attachment that was added
	 */
	public static AccountEnvironmentAttachment addAccountEnvironmentAttachment(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {

		return getService().addAccountEnvironmentAttachment(
			accountEnvironmentAttachment);
	}

	public static AccountEnvironmentAttachment addAccountEnvironmentAttachment(
			long userId, long accountEnvironmentId,
			com.liferay.portal.kernel.util.ObjectValuePair<String, java.io.File>
				fileOVP,
			int type)
		throws PortalException {

		return getService().addAccountEnvironmentAttachment(
			userId, accountEnvironmentId, fileOVP, type);
	}

	public static void addAccountEnvironmentAttachments(
			long userId, long accountEnvironmentId,
			List
				<com.liferay.portal.kernel.util.ObjectValuePair
					<String, java.io.File>> files,
			List<Integer> types)
		throws PortalException {

		getService().addAccountEnvironmentAttachments(
			userId, accountEnvironmentId, files, types);
	}

	/**
	 * Creates a new account environment attachment with the primary key. Does not add the account environment attachment to the database.
	 *
	 * @param accountEnvironmentAttachmentId the primary key for the new account environment attachment
	 * @return the new account environment attachment
	 */
	public static AccountEnvironmentAttachment
		createAccountEnvironmentAttachment(
			long accountEnvironmentAttachmentId) {

		return getService().createAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId);
	}

	/**
	 * Deletes the account environment attachment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEnvironmentAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEnvironmentAttachment the account environment attachment
	 * @return the account environment attachment that was removed
	 * @throws PortalException
	 */
	public static AccountEnvironmentAttachment
			deleteAccountEnvironmentAttachment(
				AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException {

		return getService().deleteAccountEnvironmentAttachment(
			accountEnvironmentAttachment);
	}

	/**
	 * Deletes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEnvironmentAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment that was removed
	 * @throws PortalException if a account environment attachment with the primary key could not be found
	 */
	public static AccountEnvironmentAttachment
			deleteAccountEnvironmentAttachment(
				long accountEnvironmentAttachmentId)
		throws PortalException {

		return getService().deleteAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEnvironmentAttachmentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.AccountEnvironmentAttachmentModelImpl</code>.
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

	public static AccountEnvironmentAttachment
		fetchAccountEnvironmentAttachment(long accountEnvironmentAttachmentId) {

		return getService().fetchAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId);
	}

	public static AccountEnvironmentAttachment
		fetchAccountEnvironmentAttachment(long accountEnvironmentId, int type) {

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
	public static AccountEnvironmentAttachment getAccountEnvironmentAttachment(
			long accountEnvironmentAttachmentId)
		throws PortalException {

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
	public static List<AccountEnvironmentAttachment>
		getAccountEnvironmentAttachments(int start, int end) {

		return getService().getAccountEnvironmentAttachments(start, end);
	}

	public static List<AccountEnvironmentAttachment>
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
			AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException {

		return getService().getFile(accountEnvironmentAttachment);
	}

	public static InputStream getFileAsStream(
			AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException {

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

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the account environment attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountEnvironmentAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountEnvironmentAttachment the account environment attachment
	 * @return the account environment attachment that was updated
	 */
	public static AccountEnvironmentAttachment
		updateAccountEnvironmentAttachment(
			AccountEnvironmentAttachment accountEnvironmentAttachment) {

		return getService().updateAccountEnvironmentAttachment(
			accountEnvironmentAttachment);
	}

	public static AccountEnvironmentAttachment
			updateAccountEnvironmentAttachment(
				long accountEnvironmentAttachmentId, long accountEnvironmentId,
				com.liferay.portal.kernel.util.ObjectValuePair
					<String, java.io.File> fileOVP,
				int type)
		throws PortalException {

		return getService().updateAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId, accountEnvironmentId, fileOVP,
			type);
	}

	public static void updateAccountEnvironmentAttachments(
			long userId, long accountEnvironmentId,
			List
				<com.liferay.portal.kernel.util.ObjectValuePair
					<String, java.io.File>> files,
			List<Integer> types)
		throws PortalException {

		getService().updateAccountEnvironmentAttachments(
			userId, accountEnvironmentId, files, types);
	}

	public static AccountEnvironmentAttachmentLocalService getService() {
		return _service;
	}

	public static void setService(
		AccountEnvironmentAttachmentLocalService service) {

		_service = service;
	}

	private static volatile AccountEnvironmentAttachmentLocalService _service;

}