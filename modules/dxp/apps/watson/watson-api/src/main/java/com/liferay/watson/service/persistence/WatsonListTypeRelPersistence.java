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

import com.liferay.watson.exception.NoSuchListTypeRelException;
import com.liferay.watson.model.WatsonListTypeRel;

/**
 * The persistence interface for the watson list type rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonListTypeRelPersistenceImpl
 * @see WatsonListTypeRelUtil
 * @generated
 */
@ProviderType
public interface WatsonListTypeRelPersistence extends BasePersistence<WatsonListTypeRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonListTypeRelUtil} to access the watson list type rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson list type rel in the entity cache if it is enabled.
	*
	* @param watsonListTypeRel the watson list type rel
	*/
	public void cacheResult(WatsonListTypeRel watsonListTypeRel);

	/**
	* Caches the watson list type rels in the entity cache if it is enabled.
	*
	* @param watsonListTypeRels the watson list type rels
	*/
	public void cacheResult(
		java.util.List<WatsonListTypeRel> watsonListTypeRels);

	/**
	* Creates a new watson list type rel with the primary key. Does not add the watson list type rel to the database.
	*
	* @param watsonListTypeRelId the primary key for the new watson list type rel
	* @return the new watson list type rel
	*/
	public WatsonListTypeRel create(long watsonListTypeRelId);

	/**
	* Removes the watson list type rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonListTypeRelId the primary key of the watson list type rel
	* @return the watson list type rel that was removed
	* @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	*/
	public WatsonListTypeRel remove(long watsonListTypeRelId)
		throws NoSuchListTypeRelException;

	public WatsonListTypeRel updateImpl(WatsonListTypeRel watsonListTypeRel);

	/**
	* Returns the watson list type rel with the primary key or throws a {@link NoSuchListTypeRelException} if it could not be found.
	*
	* @param watsonListTypeRelId the primary key of the watson list type rel
	* @return the watson list type rel
	* @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	*/
	public WatsonListTypeRel findByPrimaryKey(long watsonListTypeRelId)
		throws NoSuchListTypeRelException;

	/**
	* Returns the watson list type rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonListTypeRelId the primary key of the watson list type rel
	* @return the watson list type rel, or <code>null</code> if a watson list type rel with the primary key could not be found
	*/
	public WatsonListTypeRel fetchByPrimaryKey(long watsonListTypeRelId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonListTypeRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson list type rels.
	*
	* @return the watson list type rels
	*/
	public java.util.List<WatsonListTypeRel> findAll();

	/**
	* Returns a range of all the watson list type rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type rels
	* @param end the upper bound of the range of watson list type rels (not inclusive)
	* @return the range of watson list type rels
	*/
	public java.util.List<WatsonListTypeRel> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson list type rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type rels
	* @param end the upper bound of the range of watson list type rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson list type rels
	*/
	public java.util.List<WatsonListTypeRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonListTypeRel> orderByComparator);

	/**
	* Returns an ordered range of all the watson list type rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type rels
	* @param end the upper bound of the range of watson list type rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson list type rels
	*/
	public java.util.List<WatsonListTypeRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonListTypeRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson list type rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson list type rels.
	*
	* @return the number of watson list type rels
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}