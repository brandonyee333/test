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

package com.liferay.osb.community.meetup.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MeetupGroupLocalService}.
 *
 * @author Jamie Sammons
 * @see MeetupGroupLocalService
 * @generated
 */
public class MeetupGroupLocalServiceWrapper
	implements MeetupGroupLocalService,
			   ServiceWrapper<MeetupGroupLocalService> {

	public MeetupGroupLocalServiceWrapper(
		MeetupGroupLocalService meetupGroupLocalService) {

		_meetupGroupLocalService = meetupGroupLocalService;
	}

	/**
	 * Adds the meetup group to the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetupGroup the meetup group
	 * @return the meetup group that was added
	 */
	@Override
	public com.liferay.osb.community.meetup.model.MeetupGroup addMeetupGroup(
		com.liferay.osb.community.meetup.model.MeetupGroup meetupGroup) {

		return _meetupGroupLocalService.addMeetupGroup(meetupGroup);
	}

	@Override
	public com.liferay.osb.community.meetup.model.MeetupGroup addMeetupGroup(
			String name, String city, int memberCount, String description,
			String url,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupGroupLocalService.addMeetupGroup(
			name, city, memberCount, description, url, serviceContext);
	}

	/**
	 * Creates a new meetup group with the primary key. Does not add the meetup group to the database.
	 *
	 * @param meetupGroupId the primary key for the new meetup group
	 * @return the new meetup group
	 */
	@Override
	public com.liferay.osb.community.meetup.model.MeetupGroup createMeetupGroup(
		long meetupGroupId) {

		return _meetupGroupLocalService.createMeetupGroup(meetupGroupId);
	}

	/**
	 * Deletes the meetup group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group that was removed
	 * @throws PortalException if a meetup group with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.community.meetup.model.MeetupGroup deleteMeetupGroup(
			long meetupGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupGroupLocalService.deleteMeetupGroup(meetupGroupId);
	}

	/**
	 * Deletes the meetup group from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetupGroup the meetup group
	 * @return the meetup group that was removed
	 */
	@Override
	public com.liferay.osb.community.meetup.model.MeetupGroup deleteMeetupGroup(
		com.liferay.osb.community.meetup.model.MeetupGroup meetupGroup) {

		return _meetupGroupLocalService.deleteMeetupGroup(meetupGroup);
	}

	@Override
	public void deleteMeetupGroups()
		throws com.liferay.portal.kernel.exception.PortalException {

		_meetupGroupLocalService.deleteMeetupGroups();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupGroupLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _meetupGroupLocalService.dynamicQuery();
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

		return _meetupGroupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.meetup.model.impl.MeetupGroupModelImpl</code>.
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

		return _meetupGroupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.meetup.model.impl.MeetupGroupModelImpl</code>.
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

		return _meetupGroupLocalService.dynamicQuery(
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

		return _meetupGroupLocalService.dynamicQueryCount(dynamicQuery);
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

		return _meetupGroupLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.community.meetup.model.MeetupGroup fetchMeetupGroup(
		long meetupGroupId) {

		return _meetupGroupLocalService.fetchMeetupGroup(meetupGroupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _meetupGroupLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _meetupGroupLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the meetup group with the primary key.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group
	 * @throws PortalException if a meetup group with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.community.meetup.model.MeetupGroup getMeetupGroup(
			long meetupGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupGroupLocalService.getMeetupGroup(meetupGroupId);
	}

	/**
	 * Returns a range of all the meetup groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.meetup.model.impl.MeetupGroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetup groups
	 * @param end the upper bound of the range of meetup groups (not inclusive)
	 * @return the range of meetup groups
	 */
	@Override
	public java.util.List<com.liferay.osb.community.meetup.model.MeetupGroup>
		getMeetupGroups(int start, int end) {

		return _meetupGroupLocalService.getMeetupGroups(start, end);
	}

	/**
	 * Returns the number of meetup groups.
	 *
	 * @return the number of meetup groups
	 */
	@Override
	public int getMeetupGroupsCount() {
		return _meetupGroupLocalService.getMeetupGroupsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _meetupGroupLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _meetupGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the meetup group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param meetupGroup the meetup group
	 * @return the meetup group that was updated
	 */
	@Override
	public com.liferay.osb.community.meetup.model.MeetupGroup updateMeetupGroup(
		com.liferay.osb.community.meetup.model.MeetupGroup meetupGroup) {

		return _meetupGroupLocalService.updateMeetupGroup(meetupGroup);
	}

	@Override
	public MeetupGroupLocalService getWrappedService() {
		return _meetupGroupLocalService;
	}

	@Override
	public void setWrappedService(
		MeetupGroupLocalService meetupGroupLocalService) {

		_meetupGroupLocalService = meetupGroupLocalService;
	}

	private MeetupGroupLocalService _meetupGroupLocalService;

}