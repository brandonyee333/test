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

package com.liferay.osb.community.meetup.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for MeetupGroup. This utility wraps
 * {@link com.liferay.osb.community.meetup.service.impl.MeetupGroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Jamie Sammons
 * @see MeetupGroupLocalService
 * @see com.liferay.osb.community.meetup.service.base.MeetupGroupLocalServiceBaseImpl
 * @see com.liferay.osb.community.meetup.service.impl.MeetupGroupLocalServiceImpl
 * @generated
 */
@ProviderType
public class MeetupGroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.community.meetup.service.impl.MeetupGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the meetup group to the database. Also notifies the appropriate model listeners.
	*
	* @param meetupGroup the meetup group
	* @return the meetup group that was added
	*/
	public static com.liferay.osb.community.meetup.model.MeetupGroup addMeetupGroup(
		com.liferay.osb.community.meetup.model.MeetupGroup meetupGroup) {
		return getService().addMeetupGroup(meetupGroup);
	}

	public static com.liferay.osb.community.meetup.model.MeetupGroup addMeetupGroup(
		java.lang.String name, java.lang.String city, int memberCount,
		java.lang.String description, java.lang.String url,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addMeetupGroup(name, city, memberCount, description, url,
			serviceContext);
	}

	/**
	* Creates a new meetup group with the primary key. Does not add the meetup group to the database.
	*
	* @param meetupGroupId the primary key for the new meetup group
	* @return the new meetup group
	*/
	public static com.liferay.osb.community.meetup.model.MeetupGroup createMeetupGroup(
		long meetupGroupId) {
		return getService().createMeetupGroup(meetupGroupId);
	}

	/**
	* Deletes the meetup group from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupGroup the meetup group
	* @return the meetup group that was removed
	*/
	public static com.liferay.osb.community.meetup.model.MeetupGroup deleteMeetupGroup(
		com.liferay.osb.community.meetup.model.MeetupGroup meetupGroup) {
		return getService().deleteMeetupGroup(meetupGroup);
	}

	/**
	* Deletes the meetup group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupGroupId the primary key of the meetup group
	* @return the meetup group that was removed
	* @throws PortalException if a meetup group with the primary key could not be found
	*/
	public static com.liferay.osb.community.meetup.model.MeetupGroup deleteMeetupGroup(
		long meetupGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMeetupGroup(meetupGroupId);
	}

	public static com.liferay.osb.community.meetup.model.MeetupGroup fetchMeetupGroup(
		long meetupGroupId) {
		return getService().fetchMeetupGroup(meetupGroupId);
	}

	/**
	* Returns the meetup group with the primary key.
	*
	* @param meetupGroupId the primary key of the meetup group
	* @return the meetup group
	* @throws PortalException if a meetup group with the primary key could not be found
	*/
	public static com.liferay.osb.community.meetup.model.MeetupGroup getMeetupGroup(
		long meetupGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMeetupGroup(meetupGroupId);
	}

	/**
	* Updates the meetup group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param meetupGroup the meetup group
	* @return the meetup group that was updated
	*/
	public static com.liferay.osb.community.meetup.model.MeetupGroup updateMeetupGroup(
		com.liferay.osb.community.meetup.model.MeetupGroup meetupGroup) {
		return getService().updateMeetupGroup(meetupGroup);
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
	* Returns the number of meetup groups.
	*
	* @return the number of meetup groups
	*/
	public static int getMeetupGroupsCount() {
		return getService().getMeetupGroupsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.community.meetup.model.impl.MeetupGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.community.meetup.model.impl.MeetupGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the meetup groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.community.meetup.model.impl.MeetupGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meetup groups
	* @param end the upper bound of the range of meetup groups (not inclusive)
	* @return the range of meetup groups
	*/
	public static java.util.List<com.liferay.osb.community.meetup.model.MeetupGroup> getMeetupGroups(
		int start, int end) {
		return getService().getMeetupGroups(start, end);
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

	public static void deleteMeetupGroups()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteMeetupGroups();
	}

	public static MeetupGroupLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MeetupGroupLocalService, MeetupGroupLocalService> _serviceTracker =
		ServiceTrackerFactory.open(MeetupGroupLocalService.class);
}