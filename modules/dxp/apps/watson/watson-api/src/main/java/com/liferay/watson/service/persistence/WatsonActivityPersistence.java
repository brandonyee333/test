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

import com.liferay.watson.exception.NoSuchActivityException;
import com.liferay.watson.model.WatsonActivity;

/**
 * The persistence interface for the watson activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonActivityPersistenceImpl
 * @see WatsonActivityUtil
 * @generated
 */
@ProviderType
public interface WatsonActivityPersistence extends BasePersistence<WatsonActivity> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonActivityUtil} to access the watson activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson activity in the entity cache if it is enabled.
	*
	* @param watsonActivity the watson activity
	*/
	public void cacheResult(WatsonActivity watsonActivity);

	/**
	* Caches the watson activities in the entity cache if it is enabled.
	*
	* @param watsonActivities the watson activities
	*/
	public void cacheResult(java.util.List<WatsonActivity> watsonActivities);

	/**
	* Creates a new watson activity with the primary key. Does not add the watson activity to the database.
	*
	* @param watsonActivityId the primary key for the new watson activity
	* @return the new watson activity
	*/
	public WatsonActivity create(long watsonActivityId);

	/**
	* Removes the watson activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonActivityId the primary key of the watson activity
	* @return the watson activity that was removed
	* @throws NoSuchActivityException if a watson activity with the primary key could not be found
	*/
	public WatsonActivity remove(long watsonActivityId)
		throws NoSuchActivityException;

	public WatsonActivity updateImpl(WatsonActivity watsonActivity);

	/**
	* Returns the watson activity with the primary key or throws a {@link NoSuchActivityException} if it could not be found.
	*
	* @param watsonActivityId the primary key of the watson activity
	* @return the watson activity
	* @throws NoSuchActivityException if a watson activity with the primary key could not be found
	*/
	public WatsonActivity findByPrimaryKey(long watsonActivityId)
		throws NoSuchActivityException;

	/**
	* Returns the watson activity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonActivityId the primary key of the watson activity
	* @return the watson activity, or <code>null</code> if a watson activity with the primary key could not be found
	*/
	public WatsonActivity fetchByPrimaryKey(long watsonActivityId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonActivity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson activities.
	*
	* @return the watson activities
	*/
	public java.util.List<WatsonActivity> findAll();

	/**
	* Returns a range of all the watson activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activities
	* @param end the upper bound of the range of watson activities (not inclusive)
	* @return the range of watson activities
	*/
	public java.util.List<WatsonActivity> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activities
	* @param end the upper bound of the range of watson activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson activities
	*/
	public java.util.List<WatsonActivity> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonActivity> orderByComparator);

	/**
	* Returns an ordered range of all the watson activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activities
	* @param end the upper bound of the range of watson activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson activities
	*/
	public java.util.List<WatsonActivity> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson activities from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson activities.
	*
	* @return the number of watson activities
	*/
	public int countAll();
}