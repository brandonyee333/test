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

package com.liferay.social.privatemessaging.service;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.social.privatemessaging.model.UserThread;

import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for UserThread. This utility wraps
 * <code>com.liferay.social.privatemessaging.service.impl.UserThreadLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadLocalService
 * @generated
 */
public class UserThreadLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.social.privatemessaging.service.impl.UserThreadLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.message.boards.kernel.model.MBMessage
			addPrivateMessage(
				long userId, long mbThreadId, String to, String subject,
				String body,
				List
					<com.liferay.portal.kernel.util.ObjectValuePair
						<String, InputStream>> inputStreamOVPs,
				com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws PortalException {

		return getService().addPrivateMessage(
			userId, mbThreadId, to, subject, body, inputStreamOVPs,
			themeDisplay);
	}

	public static com.liferay.message.boards.kernel.model.MBMessage
			addPrivateMessageBranch(
				long userId, long parentMBMessageId, String body,
				List
					<com.liferay.portal.kernel.util.ObjectValuePair
						<String, InputStream>> inputStreamOVPs,
				com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws PortalException {

		return getService().addPrivateMessageBranch(
			userId, parentMBMessageId, body, inputStreamOVPs, themeDisplay);
	}

	public static void addUserThread(
			long userId, long mbThreadId, long topMBMessageId, boolean read,
			boolean deleted)
		throws PortalException {

		getService().addUserThread(
			userId, mbThreadId, topMBMessageId, read, deleted);
	}

	/**
	 * Adds the user thread to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserThreadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userThread the user thread
	 * @return the user thread that was added
	 */
	public static UserThread addUserThread(UserThread userThread) {
		return getService().addUserThread(userThread);
	}

	/**
	 * Creates a new user thread with the primary key. Does not add the user thread to the database.
	 *
	 * @param userThreadId the primary key for the new user thread
	 * @return the new user thread
	 */
	public static UserThread createUserThread(long userThreadId) {
		return getService().createUserThread(userThreadId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static void deleteUser(long userId) throws PortalException {
		getService().deleteUser(userId);
	}

	/**
	 * Deletes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserThreadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userThreadId the primary key of the user thread
	 * @return the user thread that was removed
	 * @throws PortalException if a user thread with the primary key could not be found
	 */
	public static UserThread deleteUserThread(long userThreadId)
		throws PortalException {

		return getService().deleteUserThread(userThreadId);
	}

	public static void deleteUserThread(long userId, long mbThreadId)
		throws PortalException {

		getService().deleteUserThread(userId, mbThreadId);
	}

	/**
	 * Deletes the user thread from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserThreadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userThread the user thread
	 * @return the user thread that was removed
	 */
	public static UserThread deleteUserThread(UserThread userThread) {
		return getService().deleteUserThread(userThread);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.privatemessaging.model.impl.UserThreadModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.privatemessaging.model.impl.UserThreadModelImpl</code>.
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

	public static UserThread fetchUserThread(long userThreadId) {
		return getService().fetchUserThread(userThreadId);
	}

	public static UserThread fetchUserThread(long userId, long mbThreadId)
		throws PortalException {

		return getService().fetchUserThread(userId, mbThreadId);
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

	public static List<UserThread> getMBThreadUserThreads(long mbThreadId) {
		return getService().getMBThreadUserThreads(mbThreadId);
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
	 * Returns the user thread with the primary key.
	 *
	 * @param userThreadId the primary key of the user thread
	 * @return the user thread
	 * @throws PortalException if a user thread with the primary key could not be found
	 */
	public static UserThread getUserThread(long userThreadId)
		throws PortalException {

		return getService().getUserThread(userThreadId);
	}

	public static UserThread getUserThread(long userId, long mbThreadId)
		throws PortalException {

		return getService().getUserThread(userId, mbThreadId);
	}

	/**
	 * Returns a range of all the user threads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.privatemessaging.model.impl.UserThreadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of user threads
	 */
	public static List<UserThread> getUserThreads(int start, int end) {
		return getService().getUserThreads(start, end);
	}

	/**
	 * Returns the number of user threads.
	 *
	 * @return the number of user threads
	 */
	public static int getUserThreadsCount() {
		return getService().getUserThreadsCount();
	}

	public static int getUserUserThreadCount(long userId, boolean deleted) {
		return getService().getUserUserThreadCount(userId, deleted);
	}

	public static int getUserUserThreadCount(
		long userId, boolean read, boolean deleted) {

		return getService().getUserUserThreadCount(userId, read, deleted);
	}

	public static List<UserThread> getUserUserThreads(
		long userId, boolean deleted) {

		return getService().getUserUserThreads(userId, deleted);
	}

	public static List<UserThread> getUserUserThreads(
		long userId, boolean read, boolean deleted) {

		return getService().getUserUserThreads(userId, read, deleted);
	}

	public static List<UserThread> getUserUserThreads(
		long userId, boolean deleted, int start, int end) {

		return getService().getUserUserThreads(userId, deleted, start, end);
	}

	public static void markUserThreadAsRead(long userId, long mbThreadId)
		throws PortalException {

		getService().markUserThreadAsRead(userId, mbThreadId);
	}

	public static void markUserThreadAsUnread(long userId, long mbThreadId)
		throws PortalException {

		getService().markUserThreadAsUnread(userId, mbThreadId);
	}

	public static void updateUserName(
		com.liferay.portal.kernel.model.User user) {

		getService().updateUserName(user);
	}

	/**
	 * Updates the user thread in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserThreadLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userThread the user thread
	 * @return the user thread that was updated
	 */
	public static UserThread updateUserThread(UserThread userThread) {
		return getService().updateUserThread(userThread);
	}

	public static UserThreadLocalService getService() {
		return _service;
	}

	public static void setService(UserThreadLocalService service) {
		_service = service;
	}

	private static volatile UserThreadLocalService _service;

}