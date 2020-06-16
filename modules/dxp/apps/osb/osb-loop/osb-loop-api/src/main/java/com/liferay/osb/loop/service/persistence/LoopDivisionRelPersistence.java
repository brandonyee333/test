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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException;
import com.liferay.osb.loop.model.LoopDivisionRel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the loop division rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopDivisionRelUtil
 * @generated
 */
@ProviderType
public interface LoopDivisionRelPersistence
	extends BasePersistence<LoopDivisionRel> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopDivisionRelUtil} to access the loop division rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LoopDivisionRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or throws a <code>NoSuchLoopDivisionRelException</code> if it could not be found.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the matching loop division rel
	 * @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	 */
	public LoopDivisionRel findByCLDI_LPI(
			long childLoopDivisionId, long loopPersonId)
		throws NoSuchLoopDivisionRelException;

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	public LoopDivisionRel fetchByCLDI_LPI(
		long childLoopDivisionId, long loopPersonId);

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	public LoopDivisionRel fetchByCLDI_LPI(
		long childLoopDivisionId, long loopPersonId, boolean useFinderCache);

	/**
	 * Removes the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; from the database.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the loop division rel that was removed
	 */
	public LoopDivisionRel removeByCLDI_LPI(
			long childLoopDivisionId, long loopPersonId)
		throws NoSuchLoopDivisionRelException;

	/**
	 * Returns the number of loop division rels where childLoopDivisionId = &#63; and loopPersonId = &#63;.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the number of matching loop division rels
	 */
	public int countByCLDI_LPI(long childLoopDivisionId, long loopPersonId);

	/**
	 * Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or throws a <code>NoSuchLoopDivisionRelException</code> if it could not be found.
	 *
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the matching loop division rel
	 * @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	 */
	public LoopDivisionRel findByLPI_PLDI(
			long loopPersonId, long parentLoopDivisionId)
		throws NoSuchLoopDivisionRelException;

	/**
	 * Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	public LoopDivisionRel fetchByLPI_PLDI(
		long loopPersonId, long parentLoopDivisionId);

	/**
	 * Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	public LoopDivisionRel fetchByLPI_PLDI(
		long loopPersonId, long parentLoopDivisionId, boolean useFinderCache);

	/**
	 * Removes the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; from the database.
	 *
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the loop division rel that was removed
	 */
	public LoopDivisionRel removeByLPI_PLDI(
			long loopPersonId, long parentLoopDivisionId)
		throws NoSuchLoopDivisionRelException;

	/**
	 * Returns the number of loop division rels where loopPersonId = &#63; and parentLoopDivisionId = &#63;.
	 *
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the number of matching loop division rels
	 */
	public int countByLPI_PLDI(long loopPersonId, long parentLoopDivisionId);

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or throws a <code>NoSuchLoopDivisionRelException</code> if it could not be found.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the matching loop division rel
	 * @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	 */
	public LoopDivisionRel findByCLDI_LPI_PLDI(
			long childLoopDivisionId, long loopPersonId,
			long parentLoopDivisionId)
		throws NoSuchLoopDivisionRelException;

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	public LoopDivisionRel fetchByCLDI_LPI_PLDI(
		long childLoopDivisionId, long loopPersonId, long parentLoopDivisionId);

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	public LoopDivisionRel fetchByCLDI_LPI_PLDI(
		long childLoopDivisionId, long loopPersonId, long parentLoopDivisionId,
		boolean useFinderCache);

	/**
	 * Removes the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; from the database.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the loop division rel that was removed
	 */
	public LoopDivisionRel removeByCLDI_LPI_PLDI(
			long childLoopDivisionId, long loopPersonId,
			long parentLoopDivisionId)
		throws NoSuchLoopDivisionRelException;

	/**
	 * Returns the number of loop division rels where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63;.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the number of matching loop division rels
	 */
	public int countByCLDI_LPI_PLDI(
		long childLoopDivisionId, long loopPersonId, long parentLoopDivisionId);

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
	 * Returns the loop division rel with the primary key or throws a <code>NoSuchLoopDivisionRelException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopDivisionRelModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopDivisionRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop division rels
	 * @param end the upper bound of the range of loop division rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop division rels
	 */
	public java.util.List<LoopDivisionRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivisionRel>
			orderByComparator);

	/**
	 * Returns an ordered range of all the loop division rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopDivisionRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop division rels
	 * @param end the upper bound of the range of loop division rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop division rels
	 */
	public java.util.List<LoopDivisionRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivisionRel>
			orderByComparator,
		boolean useFinderCache);

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