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

import com.liferay.osb.loop.exception.NoSuchLoopJobTitleException;
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the loop job title service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopJobTitleUtil
 * @generated
 */
@ProviderType
public interface LoopJobTitlePersistence extends BasePersistence<LoopJobTitle> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopJobTitleUtil} to access the loop job title persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LoopJobTitle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the loop job title where name = &#63; or throws a <code>NoSuchLoopJobTitleException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching loop job title
	 * @throws NoSuchLoopJobTitleException if a matching loop job title could not be found
	 */
	public LoopJobTitle findByName(String name)
		throws NoSuchLoopJobTitleException;

	/**
	 * Returns the loop job title where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching loop job title, or <code>null</code> if a matching loop job title could not be found
	 */
	public LoopJobTitle fetchByName(String name);

	/**
	 * Returns the loop job title where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop job title, or <code>null</code> if a matching loop job title could not be found
	 */
	public LoopJobTitle fetchByName(String name, boolean useFinderCache);

	/**
	 * Removes the loop job title where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the loop job title that was removed
	 */
	public LoopJobTitle removeByName(String name)
		throws NoSuchLoopJobTitleException;

	/**
	 * Returns the number of loop job titles where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching loop job titles
	 */
	public int countByName(String name);

	/**
	 * Caches the loop job title in the entity cache if it is enabled.
	 *
	 * @param loopJobTitle the loop job title
	 */
	public void cacheResult(LoopJobTitle loopJobTitle);

	/**
	 * Caches the loop job titles in the entity cache if it is enabled.
	 *
	 * @param loopJobTitles the loop job titles
	 */
	public void cacheResult(java.util.List<LoopJobTitle> loopJobTitles);

	/**
	 * Creates a new loop job title with the primary key. Does not add the loop job title to the database.
	 *
	 * @param loopJobTitleId the primary key for the new loop job title
	 * @return the new loop job title
	 */
	public LoopJobTitle create(long loopJobTitleId);

	/**
	 * Removes the loop job title with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title that was removed
	 * @throws NoSuchLoopJobTitleException if a loop job title with the primary key could not be found
	 */
	public LoopJobTitle remove(long loopJobTitleId)
		throws NoSuchLoopJobTitleException;

	public LoopJobTitle updateImpl(LoopJobTitle loopJobTitle);

	/**
	 * Returns the loop job title with the primary key or throws a <code>NoSuchLoopJobTitleException</code> if it could not be found.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title
	 * @throws NoSuchLoopJobTitleException if a loop job title with the primary key could not be found
	 */
	public LoopJobTitle findByPrimaryKey(long loopJobTitleId)
		throws NoSuchLoopJobTitleException;

	/**
	 * Returns the loop job title with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title, or <code>null</code> if a loop job title with the primary key could not be found
	 */
	public LoopJobTitle fetchByPrimaryKey(long loopJobTitleId);

	/**
	 * Returns all the loop job titles.
	 *
	 * @return the loop job titles
	 */
	public java.util.List<LoopJobTitle> findAll();

	/**
	 * Returns a range of all the loop job titles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopJobTitleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop job titles
	 * @param end the upper bound of the range of loop job titles (not inclusive)
	 * @return the range of loop job titles
	 */
	public java.util.List<LoopJobTitle> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the loop job titles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopJobTitleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop job titles
	 * @param end the upper bound of the range of loop job titles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop job titles
	 */
	public java.util.List<LoopJobTitle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopJobTitle>
			orderByComparator);

	/**
	 * Returns an ordered range of all the loop job titles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopJobTitleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop job titles
	 * @param end the upper bound of the range of loop job titles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop job titles
	 */
	public java.util.List<LoopJobTitle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopJobTitle>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the loop job titles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of loop job titles.
	 *
	 * @return the number of loop job titles
	 */
	public int countAll();

}