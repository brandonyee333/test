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

import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the loop topic service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopTopicPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopTopicPersistence
 * @generated
 */
public class LoopTopicUtil {

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
	public static void clearCache(LoopTopic loopTopic) {
		getPersistence().clearCache(loopTopic);
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
	public static Map<Serializable, LoopTopic> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoopTopic> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopTopic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopTopic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopTopic> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopTopic update(LoopTopic loopTopic) {
		return getPersistence().update(loopTopic);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopTopic update(
		LoopTopic loopTopic, ServiceContext serviceContext) {

		return getPersistence().update(loopTopic, serviceContext);
	}

	/**
	 * Returns the loop topic where companyId = &#63; and name = &#63; or throws a <code>NoSuchLoopTopicException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching loop topic
	 * @throws NoSuchLoopTopicException if a matching loop topic could not be found
	 */
	public static LoopTopic findByC_N(long companyId, String name)
		throws com.liferay.osb.loop.exception.NoSuchLoopTopicException {

		return getPersistence().findByC_N(companyId, name);
	}

	/**
	 * Returns the loop topic where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching loop topic, or <code>null</code> if a matching loop topic could not be found
	 */
	public static LoopTopic fetchByC_N(long companyId, String name) {
		return getPersistence().fetchByC_N(companyId, name);
	}

	/**
	 * Returns the loop topic where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop topic, or <code>null</code> if a matching loop topic could not be found
	 */
	public static LoopTopic fetchByC_N(
		long companyId, String name, boolean useFinderCache) {

		return getPersistence().fetchByC_N(companyId, name, useFinderCache);
	}

	/**
	 * Removes the loop topic where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the loop topic that was removed
	 */
	public static LoopTopic removeByC_N(long companyId, String name)
		throws com.liferay.osb.loop.exception.NoSuchLoopTopicException {

		return getPersistence().removeByC_N(companyId, name);
	}

	/**
	 * Returns the number of loop topics where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching loop topics
	 */
	public static int countByC_N(long companyId, String name) {
		return getPersistence().countByC_N(companyId, name);
	}

	/**
	 * Caches the loop topic in the entity cache if it is enabled.
	 *
	 * @param loopTopic the loop topic
	 */
	public static void cacheResult(LoopTopic loopTopic) {
		getPersistence().cacheResult(loopTopic);
	}

	/**
	 * Caches the loop topics in the entity cache if it is enabled.
	 *
	 * @param loopTopics the loop topics
	 */
	public static void cacheResult(List<LoopTopic> loopTopics) {
		getPersistence().cacheResult(loopTopics);
	}

	/**
	 * Creates a new loop topic with the primary key. Does not add the loop topic to the database.
	 *
	 * @param loopTopicId the primary key for the new loop topic
	 * @return the new loop topic
	 */
	public static LoopTopic create(long loopTopicId) {
		return getPersistence().create(loopTopicId);
	}

	/**
	 * Removes the loop topic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic that was removed
	 * @throws NoSuchLoopTopicException if a loop topic with the primary key could not be found
	 */
	public static LoopTopic remove(long loopTopicId)
		throws com.liferay.osb.loop.exception.NoSuchLoopTopicException {

		return getPersistence().remove(loopTopicId);
	}

	public static LoopTopic updateImpl(LoopTopic loopTopic) {
		return getPersistence().updateImpl(loopTopic);
	}

	/**
	 * Returns the loop topic with the primary key or throws a <code>NoSuchLoopTopicException</code> if it could not be found.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic
	 * @throws NoSuchLoopTopicException if a loop topic with the primary key could not be found
	 */
	public static LoopTopic findByPrimaryKey(long loopTopicId)
		throws com.liferay.osb.loop.exception.NoSuchLoopTopicException {

		return getPersistence().findByPrimaryKey(loopTopicId);
	}

	/**
	 * Returns the loop topic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic, or <code>null</code> if a loop topic with the primary key could not be found
	 */
	public static LoopTopic fetchByPrimaryKey(long loopTopicId) {
		return getPersistence().fetchByPrimaryKey(loopTopicId);
	}

	/**
	 * Returns all the loop topics.
	 *
	 * @return the loop topics
	 */
	public static List<LoopTopic> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the loop topics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopTopicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topics
	 * @param end the upper bound of the range of loop topics (not inclusive)
	 * @return the range of loop topics
	 */
	public static List<LoopTopic> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the loop topics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopTopicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topics
	 * @param end the upper bound of the range of loop topics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop topics
	 */
	public static List<LoopTopic> findAll(
		int start, int end, OrderByComparator<LoopTopic> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop topics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopTopicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topics
	 * @param end the upper bound of the range of loop topics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop topics
	 */
	public static List<LoopTopic> findAll(
		int start, int end, OrderByComparator<LoopTopic> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the loop topics from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of loop topics.
	 *
	 * @return the number of loop topics
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopTopicPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(LoopTopicPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile LoopTopicPersistence _persistence;

}