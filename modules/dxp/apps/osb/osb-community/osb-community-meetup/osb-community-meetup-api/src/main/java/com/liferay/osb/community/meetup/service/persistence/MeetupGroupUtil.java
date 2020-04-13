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

package com.liferay.osb.community.meetup.service.persistence;

import com.liferay.osb.community.meetup.model.MeetupGroup;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the meetup group service. This utility wraps <code>com.liferay.osb.community.meetup.service.persistence.impl.MeetupGroupPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see MeetupGroupPersistence
 * @generated
 */
public class MeetupGroupUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(MeetupGroup meetupGroup) {
		getPersistence().clearCache(meetupGroup);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, MeetupGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MeetupGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MeetupGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MeetupGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MeetupGroup> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MeetupGroup update(MeetupGroup meetupGroup) {
		return getPersistence().update(meetupGroup);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MeetupGroup update(
		MeetupGroup meetupGroup, ServiceContext serviceContext) {

		return getPersistence().update(meetupGroup, serviceContext);
	}

	/**
	 * Caches the meetup group in the entity cache if it is enabled.
	 *
	 * @param meetupGroup the meetup group
	 */
	public static void cacheResult(MeetupGroup meetupGroup) {
		getPersistence().cacheResult(meetupGroup);
	}

	/**
	 * Caches the meetup groups in the entity cache if it is enabled.
	 *
	 * @param meetupGroups the meetup groups
	 */
	public static void cacheResult(List<MeetupGroup> meetupGroups) {
		getPersistence().cacheResult(meetupGroups);
	}

	/**
	 * Creates a new meetup group with the primary key. Does not add the meetup group to the database.
	 *
	 * @param meetupGroupId the primary key for the new meetup group
	 * @return the new meetup group
	 */
	public static MeetupGroup create(long meetupGroupId) {
		return getPersistence().create(meetupGroupId);
	}

	/**
	 * Removes the meetup group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group that was removed
	 * @throws NoSuchMeetupGroupException if a meetup group with the primary key could not be found
	 */
	public static MeetupGroup remove(long meetupGroupId)
		throws com.liferay.osb.community.meetup.exception.
			NoSuchMeetupGroupException {

		return getPersistence().remove(meetupGroupId);
	}

	public static MeetupGroup updateImpl(MeetupGroup meetupGroup) {
		return getPersistence().updateImpl(meetupGroup);
	}

	/**
	 * Returns the meetup group with the primary key or throws a <code>NoSuchMeetupGroupException</code> if it could not be found.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group
	 * @throws NoSuchMeetupGroupException if a meetup group with the primary key could not be found
	 */
	public static MeetupGroup findByPrimaryKey(long meetupGroupId)
		throws com.liferay.osb.community.meetup.exception.
			NoSuchMeetupGroupException {

		return getPersistence().findByPrimaryKey(meetupGroupId);
	}

	/**
	 * Returns the meetup group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group, or <code>null</code> if a meetup group with the primary key could not be found
	 */
	public static MeetupGroup fetchByPrimaryKey(long meetupGroupId) {
		return getPersistence().fetchByPrimaryKey(meetupGroupId);
	}

	/**
	 * Returns all the meetup groups.
	 *
	 * @return the meetup groups
	 */
	public static List<MeetupGroup> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the meetup groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupGroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetup groups
	 * @param end the upper bound of the range of meetup groups (not inclusive)
	 * @return the range of meetup groups
	 */
	public static List<MeetupGroup> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the meetup groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupGroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetup groups
	 * @param end the upper bound of the range of meetup groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of meetup groups
	 */
	public static List<MeetupGroup> findAll(
		int start, int end, OrderByComparator<MeetupGroup> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the meetup groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MeetupGroupModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetup groups
	 * @param end the upper bound of the range of meetup groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of meetup groups
	 */
	public static List<MeetupGroup> findAll(
		int start, int end, OrderByComparator<MeetupGroup> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the meetup groups from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of meetup groups.
	 *
	 * @return the number of meetup groups
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MeetupGroupPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<MeetupGroupPersistence, MeetupGroupPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MeetupGroupPersistence.class);

		ServiceTracker<MeetupGroupPersistence, MeetupGroupPersistence>
			serviceTracker =
				new ServiceTracker
					<MeetupGroupPersistence, MeetupGroupPersistence>(
						bundle.getBundleContext(), MeetupGroupPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}