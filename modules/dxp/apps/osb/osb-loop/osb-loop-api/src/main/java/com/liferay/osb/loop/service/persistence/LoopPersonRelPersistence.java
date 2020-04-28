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

import com.liferay.osb.loop.exception.NoSuchLoopPersonRelException;
import com.liferay.osb.loop.model.LoopPersonRel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the loop person rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopPersonRelUtil
 * @generated
 */
@ProviderType
public interface LoopPersonRelPersistence
	extends BasePersistence<LoopPersonRel> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopPersonRelUtil} to access the loop person rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LoopPersonRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the loop person rel in the entity cache if it is enabled.
	 *
	 * @param loopPersonRel the loop person rel
	 */
	public void cacheResult(LoopPersonRel loopPersonRel);

	/**
	 * Caches the loop person rels in the entity cache if it is enabled.
	 *
	 * @param loopPersonRels the loop person rels
	 */
	public void cacheResult(java.util.List<LoopPersonRel> loopPersonRels);

	/**
	 * Creates a new loop person rel with the primary key. Does not add the loop person rel to the database.
	 *
	 * @param loopPersonRelId the primary key for the new loop person rel
	 * @return the new loop person rel
	 */
	public LoopPersonRel create(long loopPersonRelId);

	/**
	 * Removes the loop person rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopPersonRelId the primary key of the loop person rel
	 * @return the loop person rel that was removed
	 * @throws NoSuchLoopPersonRelException if a loop person rel with the primary key could not be found
	 */
	public LoopPersonRel remove(long loopPersonRelId)
		throws NoSuchLoopPersonRelException;

	public LoopPersonRel updateImpl(LoopPersonRel loopPersonRel);

	/**
	 * Returns the loop person rel with the primary key or throws a <code>NoSuchLoopPersonRelException</code> if it could not be found.
	 *
	 * @param loopPersonRelId the primary key of the loop person rel
	 * @return the loop person rel
	 * @throws NoSuchLoopPersonRelException if a loop person rel with the primary key could not be found
	 */
	public LoopPersonRel findByPrimaryKey(long loopPersonRelId)
		throws NoSuchLoopPersonRelException;

	/**
	 * Returns the loop person rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopPersonRelId the primary key of the loop person rel
	 * @return the loop person rel, or <code>null</code> if a loop person rel with the primary key could not be found
	 */
	public LoopPersonRel fetchByPrimaryKey(long loopPersonRelId);

	/**
	 * Returns all the loop person rels.
	 *
	 * @return the loop person rels
	 */
	public java.util.List<LoopPersonRel> findAll();

	/**
	 * Returns a range of all the loop person rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopPersonRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop person rels
	 * @param end the upper bound of the range of loop person rels (not inclusive)
	 * @return the range of loop person rels
	 */
	public java.util.List<LoopPersonRel> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the loop person rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopPersonRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop person rels
	 * @param end the upper bound of the range of loop person rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop person rels
	 */
	public java.util.List<LoopPersonRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopPersonRel>
			orderByComparator);

	/**
	 * Returns an ordered range of all the loop person rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopPersonRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop person rels
	 * @param end the upper bound of the range of loop person rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop person rels
	 */
	public java.util.List<LoopPersonRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopPersonRel>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the loop person rels from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of loop person rels.
	 *
	 * @return the number of loop person rels
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}