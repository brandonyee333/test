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

import com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException;
import com.liferay.osb.loop.model.LoopDivisionRel;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the loop division rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.loop.service.persistence.impl.LoopDivisionRelPersistenceImpl
 * @see LoopDivisionRelUtil
 * @generated
 */
@ProviderType
public interface LoopDivisionRelPersistence extends BasePersistence<LoopDivisionRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopDivisionRelUtil} to access the loop division rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @return the matching loop division rel
	* @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	*/
	public LoopDivisionRel findByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId) throws NoSuchLoopDivisionRelException;

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public LoopDivisionRel fetchByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId);

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public LoopDivisionRel fetchByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId, boolean retrieveFromCache);

	/**
	* Removes the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; from the database.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @return the loop division rel that was removed
	*/
	public LoopDivisionRel removeByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId) throws NoSuchLoopDivisionRelException;

	/**
	* Returns the number of loop division rels where childLoopDivisionId = &#63; and loopPersonId = &#63;.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @return the number of matching loop division rels
	*/
	public int countByCLDI_LPI(long childLoopDivisionId, long loopPersonId);

	/**
	* Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	*
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the matching loop division rel
	* @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	*/
	public LoopDivisionRel findByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId) throws NoSuchLoopDivisionRelException;

	/**
	* Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public LoopDivisionRel fetchByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId);

	/**
	* Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public LoopDivisionRel fetchByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId, boolean retrieveFromCache);

	/**
	* Removes the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; from the database.
	*
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the loop division rel that was removed
	*/
	public LoopDivisionRel removeByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId) throws NoSuchLoopDivisionRelException;

	/**
	* Returns the number of loop division rels where loopPersonId = &#63; and parentLoopDivisionId = &#63;.
	*
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the number of matching loop division rels
	*/
	public int countByLPI_PLDI(long loopPersonId, long parentLoopDivisionId);

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the matching loop division rel
	* @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	*/
	public LoopDivisionRel findByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId)
		throws NoSuchLoopDivisionRelException;

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public LoopDivisionRel fetchByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId);

	/**
	* Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	*/
	public LoopDivisionRel fetchByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId, boolean retrieveFromCache);

	/**
	* Removes the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; from the database.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the loop division rel that was removed
	*/
	public LoopDivisionRel removeByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId)
		throws NoSuchLoopDivisionRelException;

	/**
	* Returns the number of loop division rels where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63;.
	*
	* @param childLoopDivisionId the child loop division ID
	* @param loopPersonId the loop person ID
	* @param parentLoopDivisionId the parent loop division ID
	* @return the number of matching loop division rels
	*/
	public int countByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId);

	/**
	* Caches the loop division rel in the entity cache if it is enabled.
	*
	* @param loopDivisionRel the loop division rel
	*/
	public void cacheResult(LoopDivisionRel loopDivisionRel);

	/**
	* Caches the loop division rels in the entity cache if it is enabled.
	*
	* @param loopDivisionRels the loop division rels
	*/
	public void cacheResult(java.util.List<LoopDivisionRel> loopDivisionRels);

	/**
	* Creates a new loop division rel with the primary key. Does not add the loop division rel to the database.
	*
	* @param loopDivisionRelId the primary key for the new loop division rel
	* @return the new loop division rel
	*/
	public LoopDivisionRel create(long loopDivisionRelId);

	/**
	* Removes the loop division rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivisionRelId the primary key of the loop division rel
	* @return the loop division rel that was removed
	* @throws NoSuchLoopDivisionRelException if a loop division rel with the primary key could not be found
	*/
	public LoopDivisionRel remove(long loopDivisionRelId)
		throws NoSuchLoopDivisionRelException;

	public LoopDivisionRel updateImpl(LoopDivisionRel loopDivisionRel);

	/**
	* Returns the loop division rel with the primary key or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	*
	* @param loopDivisionRelId the primary key of the loop division rel
	* @return the loop division rel
	* @throws NoSuchLoopDivisionRelException if a loop division rel with the primary key could not be found
	*/
	public LoopDivisionRel findByPrimaryKey(long loopDivisionRelId)
		throws NoSuchLoopDivisionRelException;

	/**
	* Returns the loop division rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopDivisionRelId the primary key of the loop division rel
	* @return the loop division rel, or <code>null</code> if a loop division rel with the primary key could not be found
	*/
	public LoopDivisionRel fetchByPrimaryKey(long loopDivisionRelId);

	@Override
	public java.util.Map<java.io.Serializable, LoopDivisionRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the loop division rels.
	*
	* @return the loop division rels
	*/
	public java.util.List<LoopDivisionRel> findAll();

	/**
	* Returns a range of all the loop division rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop division rels
	* @param end the upper bound of the range of loop division rels (not inclusive)
	* @return the range of loop division rels
	*/
	public java.util.List<LoopDivisionRel> findAll(int start, int end);

	/**
	* Returns an ordered range of all the loop division rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop division rels
	* @param end the upper bound of the range of loop division rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop division rels
	*/
	public java.util.List<LoopDivisionRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivisionRel> orderByComparator);

	/**
	* Returns an ordered range of all the loop division rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop division rels
	* @param end the upper bound of the range of loop division rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop division rels
	*/
	public java.util.List<LoopDivisionRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivisionRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the loop division rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of loop division rels.
	*
	* @return the number of loop division rels
	*/
	public int countAll();
}