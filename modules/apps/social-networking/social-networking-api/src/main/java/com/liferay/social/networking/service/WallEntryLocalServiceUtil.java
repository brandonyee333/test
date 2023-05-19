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

package com.liferay.social.networking.service;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.social.networking.model.WallEntry;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for WallEntry. This utility wraps
 * <code>com.liferay.social.networking.service.impl.WallEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see WallEntryLocalService
 * @generated
 */
public class WallEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.social.networking.service.impl.WallEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static WallEntry addWallEntry(
			long groupId, long userId, String comments,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws PortalException {

		return getService().addWallEntry(
			groupId, userId, comments, themeDisplay);
	}

	/**
	 * Adds the wall entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WallEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wallEntry the wall entry
	 * @return the wall entry that was added
	 */
	public static WallEntry addWallEntry(WallEntry wallEntry) {
		return getService().addWallEntry(wallEntry);
	}

	/**
	 * Creates a new wall entry with the primary key. Does not add the wall entry to the database.
	 *
	 * @param wallEntryId the primary key for the new wall entry
	 * @return the new wall entry
	 */
	public static WallEntry createWallEntry(long wallEntryId) {
		return getService().createWallEntry(wallEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static void deleteWallEntries(long groupId) throws PortalException {
		getService().deleteWallEntries(groupId);
	}

	/**
	 * Deletes the wall entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WallEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wallEntryId the primary key of the wall entry
	 * @return the wall entry that was removed
	 * @throws PortalException if a wall entry with the primary key could not be found
	 */
	public static WallEntry deleteWallEntry(long wallEntryId)
		throws PortalException {

		return getService().deleteWallEntry(wallEntryId);
	}

	/**
	 * Deletes the wall entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WallEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wallEntry the wall entry
	 * @return the wall entry that was removed
	 * @throws PortalException
	 */
	public static WallEntry deleteWallEntry(WallEntry wallEntry)
		throws PortalException {

		return getService().deleteWallEntry(wallEntry);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.WallEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.WallEntryModelImpl</code>.
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

	public static WallEntry fetchWallEntry(long wallEntryId) {
		return getService().fetchWallEntry(wallEntryId);
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

	/**
	 * Returns a range of all the wall entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.WallEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @return the range of wall entries
	 */
	public static List<WallEntry> getWallEntries(int start, int end) {
		return getService().getWallEntries(start, end);
	}

	public static List<WallEntry> getWallEntries(
		long groupId, int start, int end) {

		return getService().getWallEntries(groupId, start, end);
	}

	/**
	 * Returns the number of wall entries.
	 *
	 * @return the number of wall entries
	 */
	public static int getWallEntriesCount() {
		return getService().getWallEntriesCount();
	}

	public static int getWallEntriesCount(long groupId) {
		return getService().getWallEntriesCount(groupId);
	}

	/**
	 * Returns the wall entry with the primary key.
	 *
	 * @param wallEntryId the primary key of the wall entry
	 * @return the wall entry
	 * @throws PortalException if a wall entry with the primary key could not be found
	 */
	public static WallEntry getWallEntry(long wallEntryId)
		throws PortalException {

		return getService().getWallEntry(wallEntryId);
	}

	public static List<WallEntry> getWallToWallEntries(
		long groupId1, long groupId2, long userId1, long userId2, int start,
		int end) {

		return getService().getWallToWallEntries(
			groupId1, groupId2, userId1, userId2, start, end);
	}

	public static int getWallToWallEntriesCount(
		long groupId1, long groupId2, long userId1, long userId2) {

		return getService().getWallToWallEntriesCount(
			groupId1, groupId2, userId1, userId2);
	}

	public static WallEntry updateWallEntry(long wallEntryId, String comments)
		throws PortalException {

		return getService().updateWallEntry(wallEntryId, comments);
	}

	/**
	 * Updates the wall entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WallEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wallEntry the wall entry
	 * @return the wall entry that was updated
	 */
	public static WallEntry updateWallEntry(WallEntry wallEntry) {
		return getService().updateWallEntry(wallEntry);
	}

	public static WallEntryLocalService getService() {
		return _service;
	}

	public static void setService(WallEntryLocalService service) {
		_service = service;
	}

	private static volatile WallEntryLocalService _service;

}