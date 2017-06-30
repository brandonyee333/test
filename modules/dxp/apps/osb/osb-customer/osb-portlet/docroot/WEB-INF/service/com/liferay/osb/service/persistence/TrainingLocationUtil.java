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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.TrainingLocation;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the training location service. This utility wraps {@link TrainingLocationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingLocationPersistence
 * @see TrainingLocationPersistenceImpl
 * @generated
 */
public class TrainingLocationUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(TrainingLocation trainingLocation) {
		getPersistence().clearCache(trainingLocation);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TrainingLocation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingLocation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingLocation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TrainingLocation update(TrainingLocation trainingLocation,
		boolean merge) throws SystemException {
		return getPersistence().update(trainingLocation, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TrainingLocation update(TrainingLocation trainingLocation,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(trainingLocation, merge, serviceContext);
	}

	/**
	* Caches the training location in the entity cache if it is enabled.
	*
	* @param trainingLocation the training location
	*/
	public static void cacheResult(
		com.liferay.osb.model.TrainingLocation trainingLocation) {
		getPersistence().cacheResult(trainingLocation);
	}

	/**
	* Caches the training locations in the entity cache if it is enabled.
	*
	* @param trainingLocations the training locations
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingLocation> trainingLocations) {
		getPersistence().cacheResult(trainingLocations);
	}

	/**
	* Creates a new training location with the primary key. Does not add the training location to the database.
	*
	* @param trainingLocationId the primary key for the new training location
	* @return the new training location
	*/
	public static com.liferay.osb.model.TrainingLocation create(
		long trainingLocationId) {
		return getPersistence().create(trainingLocationId);
	}

	/**
	* Removes the training location with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLocationId the primary key of the training location
	* @return the training location that was removed
	* @throws com.liferay.osb.NoSuchTrainingLocationException if a training location with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLocation remove(
		long trainingLocationId)
		throws com.liferay.osb.NoSuchTrainingLocationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(trainingLocationId);
	}

	public static com.liferay.osb.model.TrainingLocation updateImpl(
		com.liferay.osb.model.TrainingLocation trainingLocation, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trainingLocation, merge);
	}

	/**
	* Returns the training location with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingLocationException} if it could not be found.
	*
	* @param trainingLocationId the primary key of the training location
	* @return the training location
	* @throws com.liferay.osb.NoSuchTrainingLocationException if a training location with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLocation findByPrimaryKey(
		long trainingLocationId)
		throws com.liferay.osb.NoSuchTrainingLocationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(trainingLocationId);
	}

	/**
	* Returns the training location with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingLocationId the primary key of the training location
	* @return the training location, or <code>null</code> if a training location with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLocation fetchByPrimaryKey(
		long trainingLocationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trainingLocationId);
	}

	/**
	* Returns all the training locations.
	*
	* @return the training locations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingLocation> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the training locations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training locations
	* @param end the upper bound of the range of training locations (not inclusive)
	* @return the range of training locations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingLocation> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the training locations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training locations
	* @param end the upper bound of the range of training locations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training locations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingLocation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the training locations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of training locations.
	*
	* @return the number of training locations
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrainingLocationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrainingLocationPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingLocationPersistence.class.getName());

			ReferenceRegistry.registerReference(TrainingLocationUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TrainingLocationPersistence persistence) {
	}

	private static TrainingLocationPersistence _persistence;
}