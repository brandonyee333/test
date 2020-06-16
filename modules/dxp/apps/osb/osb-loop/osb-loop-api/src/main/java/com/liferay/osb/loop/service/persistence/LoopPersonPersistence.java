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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopPersonException;
import com.liferay.osb.loop.model.LoopPerson;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the loop person service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.loop.service.persistence.impl.LoopPersonPersistenceImpl
 * @see LoopPersonUtil
 * @generated
 */
@ProviderType
public interface LoopPersonPersistence extends BasePersistence<LoopPerson> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopPersonUtil} to access the loop person persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the loop person where personUserId = &#63; or throws a {@link NoSuchLoopPersonException} if it could not be found.
	*
	* @param personUserId the person user ID
	* @return the matching loop person
	* @throws NoSuchLoopPersonException if a matching loop person could not be found
	*/
	public LoopPerson findByPersonUserId(long personUserId)
		throws NoSuchLoopPersonException;

	/**
	* Returns the loop person where personUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param personUserId the person user ID
	* @return the matching loop person, or <code>null</code> if a matching loop person could not be found
	*/
	public LoopPerson fetchByPersonUserId(long personUserId);

	/**
	* Returns the loop person where personUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param personUserId the person user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop person, or <code>null</code> if a matching loop person could not be found
	*/
	public LoopPerson fetchByPersonUserId(long personUserId,
		boolean retrieveFromCache);

	/**
	* Removes the loop person where personUserId = &#63; from the database.
	*
	* @param personUserId the person user ID
	* @return the loop person that was removed
	*/
	public LoopPerson removeByPersonUserId(long personUserId)
		throws NoSuchLoopPersonException;

	/**
	* Returns the number of loop persons where personUserId = &#63;.
	*
	* @param personUserId the person user ID
	* @return the number of matching loop persons
	*/
	public int countByPersonUserId(long personUserId);

	/**
	* Caches the loop person in the entity cache if it is enabled.
	*
	* @param loopPerson the loop person
	*/
	public void cacheResult(LoopPerson loopPerson);

	/**
	* Caches the loop persons in the entity cache if it is enabled.
	*
	* @param loopPersons the loop persons
	*/
	public void cacheResult(java.util.List<LoopPerson> loopPersons);

	/**
	* Creates a new loop person with the primary key. Does not add the loop person to the database.
	*
	* @param loopPersonId the primary key for the new loop person
	* @return the new loop person
	*/
	public LoopPerson create(long loopPersonId);

	/**
	* Removes the loop person with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopPersonId the primary key of the loop person
	* @return the loop person that was removed
	* @throws NoSuchLoopPersonException if a loop person with the primary key could not be found
	*/
	public LoopPerson remove(long loopPersonId)
		throws NoSuchLoopPersonException;

	public LoopPerson updateImpl(LoopPerson loopPerson);

	/**
	* Returns the loop person with the primary key or throws a {@link NoSuchLoopPersonException} if it could not be found.
	*
	* @param loopPersonId the primary key of the loop person
	* @return the loop person
	* @throws NoSuchLoopPersonException if a loop person with the primary key could not be found
	*/
	public LoopPerson findByPrimaryKey(long loopPersonId)
		throws NoSuchLoopPersonException;

	/**
	* Returns the loop person with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopPersonId the primary key of the loop person
	* @return the loop person, or <code>null</code> if a loop person with the primary key could not be found
	*/
	public LoopPerson fetchByPrimaryKey(long loopPersonId);

	@Override
	public java.util.Map<java.io.Serializable, LoopPerson> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the loop persons.
	*
	* @return the loop persons
	*/
	public java.util.List<LoopPerson> findAll();

	/**
	* Returns a range of all the loop persons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop persons
	* @param end the upper bound of the range of loop persons (not inclusive)
	* @return the range of loop persons
	*/
	public java.util.List<LoopPerson> findAll(int start, int end);

	/**
	* Returns an ordered range of all the loop persons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop persons
	* @param end the upper bound of the range of loop persons (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop persons
	*/
	public java.util.List<LoopPerson> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopPerson> orderByComparator);

	/**
	* Returns an ordered range of all the loop persons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop persons
	* @param end the upper bound of the range of loop persons (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop persons
	*/
	public java.util.List<LoopPerson> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopPerson> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the loop persons from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of loop persons.
	*
	* @return the number of loop persons
	*/
	public int countAll();
}