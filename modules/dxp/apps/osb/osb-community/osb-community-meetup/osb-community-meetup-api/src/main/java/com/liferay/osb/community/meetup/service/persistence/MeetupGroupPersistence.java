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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.meetup.exception.NoSuchMeetupGroupException;
import com.liferay.osb.community.meetup.model.MeetupGroup;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the meetup group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see MeetupGroupUtil
 * @generated
 */
@ProviderType
public interface MeetupGroupPersistence extends BasePersistence<MeetupGroup> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MeetupGroupUtil} to access the meetup group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, MeetupGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the meetup group in the entity cache if it is enabled.
	 *
	 * @param meetupGroup the meetup group
	 */
	public void cacheResult(MeetupGroup meetupGroup);

	/**
	 * Caches the meetup groups in the entity cache if it is enabled.
	 *
	 * @param meetupGroups the meetup groups
	 */
	public void cacheResult(java.util.List<MeetupGroup> meetupGroups);

	/**
	 * Creates a new meetup group with the primary key. Does not add the meetup group to the database.
	 *
	 * @param meetupGroupId the primary key for the new meetup group
	 * @return the new meetup group
	 */
	public MeetupGroup create(long meetupGroupId);

	/**
	 * Removes the meetup group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group that was removed
	 * @throws NoSuchMeetupGroupException if a meetup group with the primary key could not be found
	 */
	public MeetupGroup remove(long meetupGroupId)
		throws NoSuchMeetupGroupException;

	public MeetupGroup updateImpl(MeetupGroup meetupGroup);

	/**
	 * Returns the meetup group with the primary key or throws a <code>NoSuchMeetupGroupException</code> if it could not be found.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group
	 * @throws NoSuchMeetupGroupException if a meetup group with the primary key could not be found
	 */
	public MeetupGroup findByPrimaryKey(long meetupGroupId)
		throws NoSuchMeetupGroupException;

	/**
	 * Returns the meetup group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param meetupGroupId the primary key of the meetup group
	 * @return the meetup group, or <code>null</code> if a meetup group with the primary key could not be found
	 */
	public MeetupGroup fetchByPrimaryKey(long meetupGroupId);

	/**
	 * Returns all the meetup groups.
	 *
	 * @return the meetup groups
	 */
	public java.util.List<MeetupGroup> findAll();

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
	public java.util.List<MeetupGroup> findAll(int start, int end);

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
	public java.util.List<MeetupGroup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupGroup>
			orderByComparator);

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
	public java.util.List<MeetupGroup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupGroup>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the meetup groups from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of meetup groups.
	 *
	 * @return the number of meetup groups
	 */
	public int countAll();

}