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

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.watson.exception.NoSuchPersonException;
import com.liferay.watson.model.WatsonPerson;

/**
 * The persistence interface for the watson person service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonPersonPersistenceImpl
 * @see WatsonPersonUtil
 * @generated
 */
@ProviderType
public interface WatsonPersonPersistence extends BasePersistence<WatsonPerson> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonPersonUtil} to access the watson person persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson person in the entity cache if it is enabled.
	*
	* @param watsonPerson the watson person
	*/
	public void cacheResult(WatsonPerson watsonPerson);

	/**
	* Caches the watson persons in the entity cache if it is enabled.
	*
	* @param watsonPersons the watson persons
	*/
	public void cacheResult(java.util.List<WatsonPerson> watsonPersons);

	/**
	* Creates a new watson person with the primary key. Does not add the watson person to the database.
	*
	* @param watsonPersonId the primary key for the new watson person
	* @return the new watson person
	*/
	public WatsonPerson create(long watsonPersonId);

	/**
	* Removes the watson person with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonPersonId the primary key of the watson person
	* @return the watson person that was removed
	* @throws NoSuchPersonException if a watson person with the primary key could not be found
	*/
	public WatsonPerson remove(long watsonPersonId)
		throws NoSuchPersonException;

	public WatsonPerson updateImpl(WatsonPerson watsonPerson);

	/**
	* Returns the watson person with the primary key or throws a {@link NoSuchPersonException} if it could not be found.
	*
	* @param watsonPersonId the primary key of the watson person
	* @return the watson person
	* @throws NoSuchPersonException if a watson person with the primary key could not be found
	*/
	public WatsonPerson findByPrimaryKey(long watsonPersonId)
		throws NoSuchPersonException;

	/**
	* Returns the watson person with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonPersonId the primary key of the watson person
	* @return the watson person, or <code>null</code> if a watson person with the primary key could not be found
	*/
	public WatsonPerson fetchByPrimaryKey(long watsonPersonId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonPerson> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson persons.
	*
	* @return the watson persons
	*/
	public java.util.List<WatsonPerson> findAll();

	/**
	* Returns a range of all the watson persons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson persons
	* @param end the upper bound of the range of watson persons (not inclusive)
	* @return the range of watson persons
	*/
	public java.util.List<WatsonPerson> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson persons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson persons
	* @param end the upper bound of the range of watson persons (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson persons
	*/
	public java.util.List<WatsonPerson> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonPerson> orderByComparator);

	/**
	* Returns an ordered range of all the watson persons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson persons
	* @param end the upper bound of the range of watson persons (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson persons
	*/
	public java.util.List<WatsonPerson> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonPerson> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson persons from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson persons.
	*
	* @return the number of watson persons
	*/
	public int countAll();
}