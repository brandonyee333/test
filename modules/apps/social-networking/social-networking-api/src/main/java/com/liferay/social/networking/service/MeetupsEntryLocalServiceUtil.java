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
import com.liferay.social.networking.model.MeetupsEntry;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for MeetupsEntry. This utility wraps
 * <code>com.liferay.social.networking.service.impl.MeetupsEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntryLocalService
 * @generated
 */
public class MeetupsEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.social.networking.service.impl.MeetupsEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static MeetupsEntry addMeetupsEntry(
			long userId, String title, String description, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute,
			int totalAttendees, int maxAttendees, double price,
			byte[] thumbnail)
		throws PortalException {

		return getService().addMeetupsEntry(
			userId, title, description, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, totalAttendees,
			maxAttendees, price, thumbnail);
	}

	/**
	 * Adds the meetups entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsEntry the meetups entry
	 * @return the meetups entry that was added
	 */
	public static MeetupsEntry addMeetupsEntry(MeetupsEntry meetupsEntry) {
		return getService().addMeetupsEntry(meetupsEntry);
	}

	/**
	 * Creates a new meetups entry with the primary key. Does not add the meetups entry to the database.
	 *
	 * @param meetupsEntryId the primary key for the new meetups entry
	 * @return the new meetups entry
	 */
	public static MeetupsEntry createMeetupsEntry(long meetupsEntryId) {
		return getService().createMeetupsEntry(meetupsEntryId);
	}

	/**
	 * Deletes the meetups entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsEntryId the primary key of the meetups entry
	 * @return the meetups entry that was removed
	 * @throws PortalException if a meetups entry with the primary key could not be found
	 */
	public static MeetupsEntry deleteMeetupsEntry(long meetupsEntryId)
		throws PortalException {

		return getService().deleteMeetupsEntry(meetupsEntryId);
	}

	/**
	 * Deletes the meetups entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsEntry the meetups entry
	 * @return the meetups entry that was removed
	 */
	public static MeetupsEntry deleteMeetupsEntry(MeetupsEntry meetupsEntry) {
		return getService().deleteMeetupsEntry(meetupsEntry);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.MeetupsEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.MeetupsEntryModelImpl</code>.
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

	public static MeetupsEntry fetchMeetupsEntry(long meetupsEntryId) {
		return getService().fetchMeetupsEntry(meetupsEntryId);
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
	 * Returns a range of all the meetups entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.social.networking.model.impl.MeetupsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups entries
	 * @param end the upper bound of the range of meetups entries (not inclusive)
	 * @return the range of meetups entries
	 */
	public static List<MeetupsEntry> getMeetupsEntries(int start, int end) {
		return getService().getMeetupsEntries(start, end);
	}

	public static List<MeetupsEntry> getMeetupsEntriesByCompany(
		long companyId) {

		return getService().getMeetupsEntriesByCompany(companyId);
	}

	public static List<MeetupsEntry> getMeetupsEntriesByUser(long userId) {
		return getService().getMeetupsEntriesByUser(userId);
	}

	/**
	 * Returns the number of meetups entries.
	 *
	 * @return the number of meetups entries
	 */
	public static int getMeetupsEntriesCount() {
		return getService().getMeetupsEntriesCount();
	}

	/**
	 * Returns the meetups entry with the primary key.
	 *
	 * @param meetupsEntryId the primary key of the meetups entry
	 * @return the meetups entry
	 * @throws PortalException if a meetups entry with the primary key could not be found
	 */
	public static MeetupsEntry getMeetupsEntry(long meetupsEntryId)
		throws PortalException {

		return getService().getMeetupsEntry(meetupsEntryId);
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

	public static MeetupsEntry updateMeetupsEntry(
			long userId, long meetupsEntryId, String title, String description,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			int totalAttendees, int maxAttendees, double price,
			byte[] thumbnail)
		throws PortalException {

		return getService().updateMeetupsEntry(
			userId, meetupsEntryId, title, description, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			totalAttendees, maxAttendees, price, thumbnail);
	}

	/**
	 * Updates the meetups entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MeetupsEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param meetupsEntry the meetups entry
	 * @return the meetups entry that was updated
	 */
	public static MeetupsEntry updateMeetupsEntry(MeetupsEntry meetupsEntry) {
		return getService().updateMeetupsEntry(meetupsEntry);
	}

	public static MeetupsEntryLocalService getService() {
		return _service;
	}

	public static void setService(MeetupsEntryLocalService service) {
		_service = service;
	}

	private static volatile MeetupsEntryLocalService _service;

}