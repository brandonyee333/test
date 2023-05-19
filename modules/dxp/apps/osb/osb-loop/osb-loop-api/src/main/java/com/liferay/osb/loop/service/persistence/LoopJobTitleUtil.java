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

import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the loop job title service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopJobTitlePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopJobTitlePersistence
 * @generated
 */
public class LoopJobTitleUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(LoopJobTitle loopJobTitle) {
		getPersistence().clearCache(loopJobTitle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, LoopJobTitle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoopJobTitle> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopJobTitle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopJobTitle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopJobTitle> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopJobTitle update(LoopJobTitle loopJobTitle) {
		return getPersistence().update(loopJobTitle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopJobTitle update(
		LoopJobTitle loopJobTitle, ServiceContext serviceContext) {

		return getPersistence().update(loopJobTitle, serviceContext);
	}

	/**
	 * Returns the loop job title where name = &#63; or throws a <code>NoSuchLoopJobTitleException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching loop job title
	 * @throws NoSuchLoopJobTitleException if a matching loop job title could not be found
	 */
	public static LoopJobTitle findByName(String name)
		throws com.liferay.osb.loop.exception.NoSuchLoopJobTitleException {

		return getPersistence().findByName(name);
	}

	/**
	 * Returns the loop job title where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching loop job title, or <code>null</code> if a matching loop job title could not be found
	 */
	public static LoopJobTitle fetchByName(String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	 * Returns the loop job title where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop job title, or <code>null</code> if a matching loop job title could not be found
	 */
	public static LoopJobTitle fetchByName(
		String name, boolean useFinderCache) {

		return getPersistence().fetchByName(name, useFinderCache);
	}

	/**
	 * Removes the loop job title where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the loop job title that was removed
	 */
	public static LoopJobTitle removeByName(String name)
		throws com.liferay.osb.loop.exception.NoSuchLoopJobTitleException {

		return getPersistence().removeByName(name);
	}

	/**
	 * Returns the number of loop job titles where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching loop job titles
	 */
	public static int countByName(String name) {
		return getPersistence().countByName(name);
	}

	/**
	 * Caches the loop job title in the entity cache if it is enabled.
	 *
	 * @param loopJobTitle the loop job title
	 */
	public static void cacheResult(LoopJobTitle loopJobTitle) {
		getPersistence().cacheResult(loopJobTitle);
	}

	/**
	 * Caches the loop job titles in the entity cache if it is enabled.
	 *
	 * @param loopJobTitles the loop job titles
	 */
	public static void cacheResult(List<LoopJobTitle> loopJobTitles) {
		getPersistence().cacheResult(loopJobTitles);
	}

	/**
	 * Creates a new loop job title with the primary key. Does not add the loop job title to the database.
	 *
	 * @param loopJobTitleId the primary key for the new loop job title
	 * @return the new loop job title
	 */
	public static LoopJobTitle create(long loopJobTitleId) {
		return getPersistence().create(loopJobTitleId);
	}

	/**
	 * Removes the loop job title with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title that was removed
	 * @throws NoSuchLoopJobTitleException if a loop job title with the primary key could not be found
	 */
	public static LoopJobTitle remove(long loopJobTitleId)
		throws com.liferay.osb.loop.exception.NoSuchLoopJobTitleException {

		return getPersistence().remove(loopJobTitleId);
	}

	public static LoopJobTitle updateImpl(LoopJobTitle loopJobTitle) {
		return getPersistence().updateImpl(loopJobTitle);
	}

	/**
	 * Returns the loop job title with the primary key or throws a <code>NoSuchLoopJobTitleException</code> if it could not be found.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title
	 * @throws NoSuchLoopJobTitleException if a loop job title with the primary key could not be found
	 */
	public static LoopJobTitle findByPrimaryKey(long loopJobTitleId)
		throws com.liferay.osb.loop.exception.NoSuchLoopJobTitleException {

		return getPersistence().findByPrimaryKey(loopJobTitleId);
	}

	/**
	 * Returns the loop job title with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title, or <code>null</code> if a loop job title with the primary key could not be found
	 */
	public static LoopJobTitle fetchByPrimaryKey(long loopJobTitleId) {
		return getPersistence().fetchByPrimaryKey(loopJobTitleId);
	}

	/**
	 * Returns all the loop job titles.
	 *
	 * @return the loop job titles
	 */
	public static List<LoopJobTitle> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<LoopJobTitle> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<LoopJobTitle> findAll(
		int start, int end, OrderByComparator<LoopJobTitle> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<LoopJobTitle> findAll(
		int start, int end, OrderByComparator<LoopJobTitle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the loop job titles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of loop job titles.
	 *
	 * @return the number of loop job titles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopJobTitlePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(LoopJobTitlePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile LoopJobTitlePersistence _persistence;

}