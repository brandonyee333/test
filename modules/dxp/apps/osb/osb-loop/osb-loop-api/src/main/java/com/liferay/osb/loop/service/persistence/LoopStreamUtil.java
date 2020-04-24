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

import com.liferay.osb.loop.model.LoopStream;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the loop stream service. This utility wraps {@link com.liferay.osb.loop.service.persistence.impl.LoopStreamPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStreamPersistence
 * @see com.liferay.osb.loop.service.persistence.impl.LoopStreamPersistenceImpl
 * @generated
 */
@ProviderType
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
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	public static LoopStream update(LoopStream loopStream,
		ServiceContext serviceContext) {
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
	* Returns the loop stream with the primary key or throws a {@link NoSuchLoopStreamException} if it could not be found.
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

	public static java.util.Map<java.io.Serializable, LoopStream> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop streams
	* @param end the upper bound of the range of loop streams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop streams
	*/
	public static List<LoopStream> findAll(int start, int end,
		OrderByComparator<LoopStream> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the loop streams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop streams
	* @param end the upper bound of the range of loop streams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop streams
	*/
	public static List<LoopStream> findAll(int start, int end,
		OrderByComparator<LoopStream> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
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

	private static ServiceTracker<LoopStreamPersistence, LoopStreamPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopStreamPersistence.class);

		ServiceTracker<LoopStreamPersistence, LoopStreamPersistence> serviceTracker =
			new ServiceTracker<LoopStreamPersistence, LoopStreamPersistence>(bundle.getBundleContext(),
				LoopStreamPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}