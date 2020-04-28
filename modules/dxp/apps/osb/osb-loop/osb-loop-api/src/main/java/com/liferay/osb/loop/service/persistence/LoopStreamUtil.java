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

import com.liferay.osb.loop.model.LoopStream;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the loop stream service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopStreamPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStreamPersistence
 * @generated
 */
public class LoopStreamUtil {

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
	public static void clearCache(LoopStream loopStream) {
		getPersistence().clearCache(loopStream);
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
	public static Map<Serializable, LoopStream> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoopStream> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopStream> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopStream> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopStream> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopStream update(LoopStream loopStream) {
		return getPersistence().update(loopStream);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopStream update(
		LoopStream loopStream, ServiceContext serviceContext) {

		return getPersistence().update(loopStream, serviceContext);
	}

	/**
	 * Caches the loop stream in the entity cache if it is enabled.
	 *
	 * @param loopStream the loop stream
	 */
	public static void cacheResult(LoopStream loopStream) {
		getPersistence().cacheResult(loopStream);
	}

	/**
	 * Caches the loop streams in the entity cache if it is enabled.
	 *
	 * @param loopStreams the loop streams
	 */
	public static void cacheResult(List<LoopStream> loopStreams) {
		getPersistence().cacheResult(loopStreams);
	}

	/**
	 * Creates a new loop stream with the primary key. Does not add the loop stream to the database.
	 *
	 * @param loopStreamId the primary key for the new loop stream
	 * @return the new loop stream
	 */
	public static LoopStream create(long loopStreamId) {
		return getPersistence().create(loopStreamId);
	}

	/**
	 * Removes the loop stream with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamId the primary key of the loop stream
	 * @return the loop stream that was removed
	 * @throws NoSuchLoopStreamException if a loop stream with the primary key could not be found
	 */
	public static LoopStream remove(long loopStreamId)
		throws com.liferay.osb.loop.exception.NoSuchLoopStreamException {

		return getPersistence().remove(loopStreamId);
	}

	public static LoopStream updateImpl(LoopStream loopStream) {
		return getPersistence().updateImpl(loopStream);
	}

	/**
	 * Returns the loop stream with the primary key or throws a <code>NoSuchLoopStreamException</code> if it could not be found.
	 *
	 * @param loopStreamId the primary key of the loop stream
	 * @return the loop stream
	 * @throws NoSuchLoopStreamException if a loop stream with the primary key could not be found
	 */
	public static LoopStream findByPrimaryKey(long loopStreamId)
		throws com.liferay.osb.loop.exception.NoSuchLoopStreamException {

		return getPersistence().findByPrimaryKey(loopStreamId);
	}

	/**
	 * Returns the loop stream with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopStreamId the primary key of the loop stream
	 * @return the loop stream, or <code>null</code> if a loop stream with the primary key could not be found
	 */
	public static LoopStream fetchByPrimaryKey(long loopStreamId) {
		return getPersistence().fetchByPrimaryKey(loopStreamId);
	}

	/**
	 * Returns all the loop streams.
	 *
	 * @return the loop streams
	 */
	public static List<LoopStream> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the loop streams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStreamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop streams
	 * @param end the upper bound of the range of loop streams (not inclusive)
	 * @return the range of loop streams
	 */
	public static List<LoopStream> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the loop streams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStreamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop streams
	 * @param end the upper bound of the range of loop streams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop streams
	 */
	public static List<LoopStream> findAll(
		int start, int end, OrderByComparator<LoopStream> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop streams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStreamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop streams
	 * @param end the upper bound of the range of loop streams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop streams
	 */
	public static List<LoopStream> findAll(
		int start, int end, OrderByComparator<LoopStream> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the loop streams from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of loop streams.
	 *
	 * @return the number of loop streams
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopStreamPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopStreamPersistence, LoopStreamPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopStreamPersistence.class);

		ServiceTracker<LoopStreamPersistence, LoopStreamPersistence>
			serviceTracker =
				new ServiceTracker
					<LoopStreamPersistence, LoopStreamPersistence>(
						bundle.getBundleContext(), LoopStreamPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}