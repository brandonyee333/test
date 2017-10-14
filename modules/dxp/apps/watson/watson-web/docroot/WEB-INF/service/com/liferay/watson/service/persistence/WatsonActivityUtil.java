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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import com.liferay.watson.model.WatsonActivity;

import java.util.List;

/**
 * The persistence utility for the watson activity service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonActivityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eddie Olson
 * @see WatsonActivityPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonActivityPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonActivityUtil {
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
	public static void clearCache(WatsonActivity watsonActivity) {
		getPersistence().clearCache(watsonActivity);
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
	public static List<WatsonActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonActivity> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonActivity update(WatsonActivity watsonActivity) {
		return getPersistence().update(watsonActivity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonActivity update(WatsonActivity watsonActivity,
		ServiceContext serviceContext) {
		return getPersistence().update(watsonActivity, serviceContext);
	}

	/**
	* Caches the watson activity in the entity cache if it is enabled.
	*
	* @param watsonActivity the watson activity
	*/
	public static void cacheResult(WatsonActivity watsonActivity) {
		getPersistence().cacheResult(watsonActivity);
	}

	/**
	* Caches the watson activities in the entity cache if it is enabled.
	*
	* @param watsonActivities the watson activities
	*/
	public static void cacheResult(List<WatsonActivity> watsonActivities) {
		getPersistence().cacheResult(watsonActivities);
	}

	/**
	* Creates a new watson activity with the primary key. Does not add the watson activity to the database.
	*
	* @param watsonActivityId the primary key for the new watson activity
	* @return the new watson activity
	*/
	public static WatsonActivity create(long watsonActivityId) {
		return getPersistence().create(watsonActivityId);
	}

	/**
	* Removes the watson activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonActivityId the primary key of the watson activity
	* @return the watson activity that was removed
	* @throws NoSuchActivityException if a watson activity with the primary key could not be found
	*/
	public static WatsonActivity remove(long watsonActivityId)
		throws com.liferay.watson.exception.NoSuchActivityException {
		return getPersistence().remove(watsonActivityId);
	}

	public static WatsonActivity updateImpl(WatsonActivity watsonActivity) {
		return getPersistence().updateImpl(watsonActivity);
	}

	/**
	* Returns the watson activity with the primary key or throws a {@link NoSuchActivityException} if it could not be found.
	*
	* @param watsonActivityId the primary key of the watson activity
	* @return the watson activity
	* @throws NoSuchActivityException if a watson activity with the primary key could not be found
	*/
	public static WatsonActivity findByPrimaryKey(long watsonActivityId)
		throws com.liferay.watson.exception.NoSuchActivityException {
		return getPersistence().findByPrimaryKey(watsonActivityId);
	}

	/**
	* Returns the watson activity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonActivityId the primary key of the watson activity
	* @return the watson activity, or <code>null</code> if a watson activity with the primary key could not be found
	*/
	public static WatsonActivity fetchByPrimaryKey(long watsonActivityId) {
		return getPersistence().fetchByPrimaryKey(watsonActivityId);
	}

	public static java.util.Map<java.io.Serializable, WatsonActivity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the watson activities.
	*
	* @return the watson activities
	*/
	public static List<WatsonActivity> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activities
	* @param end the upper bound of the range of watson activities (not inclusive)
	* @return the range of watson activities
	*/
	public static List<WatsonActivity> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activities
	* @param end the upper bound of the range of watson activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson activities
	*/
	public static List<WatsonActivity> findAll(int start, int end,
		OrderByComparator<WatsonActivity> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activities
	* @param end the upper bound of the range of watson activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson activities
	*/
	public static List<WatsonActivity> findAll(int start, int end,
		OrderByComparator<WatsonActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson activities from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson activities.
	*
	* @return the number of watson activities
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonActivityPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WatsonActivityPersistence)PortletBeanLocatorUtil.locate(com.liferay.watson.service.ClpSerializer.getServletContextName(),
					WatsonActivityPersistence.class.getName());

			ReferenceRegistry.registerReference(WatsonActivityUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static WatsonActivityPersistence _persistence;
}