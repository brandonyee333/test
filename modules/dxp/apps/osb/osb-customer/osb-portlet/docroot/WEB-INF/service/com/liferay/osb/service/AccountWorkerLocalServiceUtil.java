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
 * Provides the local service utility for AccountWorker. This utility wraps
 * {@link com.liferay.osb.service.impl.AccountWorkerLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorkerLocalService
 * @see com.liferay.osb.service.base.AccountWorkerLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountWorkerLocalServiceImpl
 * @generated
 */
@ProviderType
public class AccountWorkerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AccountWorkerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasAccountWorker(long userId, long accountEntryId) {
		return getService().hasAccountWorker(userId, accountEntryId);
	}

	public static boolean hasAccountWorkerRole(long userId, int role) {
		return getService().hasAccountWorkerRole(userId, role);
	}

	/**
	* Adds the account worker to the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorker the account worker
	* @return the account worker that was added
	*/
	public static com.liferay.osb.model.AccountWorker addAccountWorker(
		com.liferay.osb.model.AccountWorker accountWorker) {
		return getService().addAccountWorker(accountWorker);
	}

	public static com.liferay.osb.model.AccountWorker addAccountWorker(
		long userId, java.lang.String emailAddress, long accountEntryId,
		int role) throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addAccountWorker(userId, emailAddress, accountEntryId, role);
	}

	public static com.liferay.osb.model.AccountWorker addAccountWorker(
		long userId, long workerUserId, long accountEntryId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addAccountWorker(userId, workerUserId, accountEntryId, role);
	}

	/**
	* Creates a new account worker with the primary key. Does not add the account worker to the database.
	*
	* @param accountWorkerId the primary key for the new account worker
	* @return the new account worker
	*/
	public static com.liferay.osb.model.AccountWorker createAccountWorker(
		long accountWorkerId) {
		return getService().createAccountWorker(accountWorkerId);
	}

	/**
	* Deletes the account worker from the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorker the account worker
	* @return the account worker that was removed
	*/
	public static com.liferay.osb.model.AccountWorker deleteAccountWorker(
		com.liferay.osb.model.AccountWorker accountWorker) {
		return getService().deleteAccountWorker(accountWorker);
	}

	/**
	* Deletes the account worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker that was removed
	* @throws PortalException if a account worker with the primary key could not be found
	*/
	public static com.liferay.osb.model.AccountWorker deleteAccountWorker(
		long accountWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAccountWorker(accountWorkerId);
	}

	public static com.liferay.osb.model.AccountWorker fetchAccountWorker(
		long accountWorkerId) {
		return getService().fetchAccountWorker(accountWorkerId);
	}

	/**
	* Returns the account worker with the primary key.
	*
	* @param accountWorkerId the primary key of the account worker
	* @return the account worker
	* @throws PortalException if a account worker with the primary key could not be found
	*/
	public static com.liferay.osb.model.AccountWorker getAccountWorker(
		long accountWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountWorker(accountWorkerId);
	}

	public static com.liferay.osb.model.AccountWorker getAccountWorker(
		long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountWorker(userId, accountEntryId);
	}

	/**
	* Updates the account worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountWorker the account worker
	* @return the account worker that was updated
	*/
	public static com.liferay.osb.model.AccountWorker updateAccountWorker(
		com.liferay.osb.model.AccountWorker accountWorker) {
		return getService().updateAccountWorker(accountWorker);
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
	* Returns the number of account workers.
	*
	* @return the number of account workers
	*/
	public static int getAccountWorkersCount() {
		return getService().getAccountWorkersCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the account workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account workers
	* @param end the upper bound of the range of account workers (not inclusive)
	* @return the range of account workers
	*/
	public static java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers(
		int start, int end) {
		return getService().getAccountWorkers(start, end);
	}

	public static java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers(
		long accountEntryId) {
		return getService().getAccountWorkers(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.AccountWorker> getAccountWorkers(
		long accountEntryId, int role) {
		return getService().getAccountWorkers(accountEntryId, role);
	}

	public static java.util.List<com.liferay.osb.model.AccountWorker> getUserAccountWorkers(
		long userId) {
		return getService().getUserAccountWorkers(userId);
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

	public static void deleteAccountEntryAccountWorkers(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteAccountEntryAccountWorkers(accountEntryId);
	}

	public static void deleteAccountWorker(long userId, long accountWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteAccountWorker(userId, accountWorkerId);
	}

	public static void deleteAccountWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteAccountWorkers(userId);
	}

	public static void updateAccountWorker(long userId, long accountWorkerId,
		int role) throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateAccountWorker(userId, accountWorkerId, role);
	}

	public static void clearService() {
		_service = null;
	}

	public static AccountWorkerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AccountWorkerLocalService.class.getName());

			if (invokableLocalService instanceof AccountWorkerLocalService) {
				_service = (AccountWorkerLocalService)invokableLocalService;
			}
			else {
				_service = new AccountWorkerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AccountWorkerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static AccountWorkerLocalService _service;
}