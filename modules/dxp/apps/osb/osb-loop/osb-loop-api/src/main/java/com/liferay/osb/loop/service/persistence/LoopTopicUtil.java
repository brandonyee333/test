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

import com.liferay.osb.loop.model.LoopTopic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the loop topic service. This utility wraps {@link com.liferay.osb.loop.service.persistence.impl.LoopTopicPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopTopicPersistence
 * @see com.liferay.osb.loop.service.persistence.impl.LoopTopicPersistenceImpl
 * @generated
 */
@ProviderType
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
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	public static LoopTopic update(LoopTopic loopTopic,
		ServiceContext serviceContext) {
		return getPersistence().update(loopTopic, serviceContext);
	}

	/**
	* Returns the loop topic where companyId = &#63; and name = &#63; or throws a {@link NoSuchLoopTopicException} if it could not be found.
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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop topic, or <code>null</code> if a matching loop topic could not be found
	*/
	public static LoopTopic fetchByC_N(long companyId, String name,
		boolean retrieveFromCache) {
		return getPersistence().fetchByC_N(companyId, name, retrieveFromCache);
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
	* Returns the loop topic with the primary key or throws a {@link NoSuchLoopTopicException} if it could not be found.
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

	public static java.util.Map<java.io.Serializable, LoopTopic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop topics
	* @param end the upper bound of the range of loop topics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop topics
	*/
	public static List<LoopTopic> findAll(int start, int end,
		OrderByComparator<LoopTopic> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the loop topics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop topics
	* @param end the upper bound of the range of loop topics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop topics
	*/
	public static List<LoopTopic> findAll(int start, int end,
		OrderByComparator<LoopTopic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
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
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopTopicPersistence, LoopTopicPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopTopicPersistence.class);

		ServiceTracker<LoopTopicPersistence, LoopTopicPersistence> serviceTracker =
			new ServiceTracker<LoopTopicPersistence, LoopTopicPersistence>(bundle.getBundleContext(),
				LoopTopicPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}