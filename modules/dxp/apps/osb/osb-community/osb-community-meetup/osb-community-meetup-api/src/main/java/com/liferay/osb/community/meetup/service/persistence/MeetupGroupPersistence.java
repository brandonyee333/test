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

package com.liferay.osb.community.meetup.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.meetup.exception.NoSuchMeetupGroupException;
import com.liferay.osb.community.meetup.model.MeetupGroup;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the meetup group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see com.liferay.osb.community.meetup.service.persistence.impl.MeetupGroupPersistenceImpl
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
	* Returns the meetup group with the primary key or throws a {@link NoSuchMeetupGroupException} if it could not be found.
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

	@Override
	public java.util.Map<java.io.Serializable, MeetupGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meetup groups
	* @param end the upper bound of the range of meetup groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of meetup groups
	*/
	public java.util.List<MeetupGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupGroup> orderByComparator);

	/**
	* Returns an ordered range of all the meetup groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeetupGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meetup groups
	* @param end the upper bound of the range of meetup groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of meetup groups
	*/
	public java.util.List<MeetupGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MeetupGroup> orderByComparator,
		boolean retrieveFromCache);

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