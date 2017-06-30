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

import com.liferay.osb.model.SupportLabor;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the support labor service. This utility wraps {@link SupportLaborPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportLaborPersistence
 * @see SupportLaborPersistenceImpl
 * @generated
 */
public class SupportLaborUtil {
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
	public static void clearCache(SupportLabor supportLabor) {
		getPersistence().clearCache(supportLabor);
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
	public static List<SupportLabor> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportLabor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportLabor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SupportLabor update(SupportLabor supportLabor, boolean merge)
		throws SystemException {
		return getPersistence().update(supportLabor, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SupportLabor update(SupportLabor supportLabor, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(supportLabor, merge, serviceContext);
	}

	/**
	* Caches the support labor in the entity cache if it is enabled.
	*
	* @param supportLabor the support labor
	*/
	public static void cacheResult(
		com.liferay.osb.model.SupportLabor supportLabor) {
		getPersistence().cacheResult(supportLabor);
	}

	/**
	* Caches the support labors in the entity cache if it is enabled.
	*
	* @param supportLabors the support labors
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.SupportLabor> supportLabors) {
		getPersistence().cacheResult(supportLabors);
	}

	/**
	* Creates a new support labor with the primary key. Does not add the support labor to the database.
	*
	* @param supportLaborId the primary key for the new support labor
	* @return the new support labor
	*/
	public static com.liferay.osb.model.SupportLabor create(long supportLaborId) {
		return getPersistence().create(supportLaborId);
	}

	/**
	* Removes the support labor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportLaborId the primary key of the support labor
	* @return the support labor that was removed
	* @throws com.liferay.osb.NoSuchSupportLaborException if a support labor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportLabor remove(long supportLaborId)
		throws com.liferay.osb.NoSuchSupportLaborException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(supportLaborId);
	}

	public static com.liferay.osb.model.SupportLabor updateImpl(
		com.liferay.osb.model.SupportLabor supportLabor, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(supportLabor, merge);
	}

	/**
	* Returns the support labor with the primary key or throws a {@link com.liferay.osb.NoSuchSupportLaborException} if it could not be found.
	*
	* @param supportLaborId the primary key of the support labor
	* @return the support labor
	* @throws com.liferay.osb.NoSuchSupportLaborException if a support labor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportLabor findByPrimaryKey(
		long supportLaborId)
		throws com.liferay.osb.NoSuchSupportLaborException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(supportLaborId);
	}

	/**
	* Returns the support labor with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportLaborId the primary key of the support labor
	* @return the support labor, or <code>null</code> if a support labor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportLabor fetchByPrimaryKey(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(supportLaborId);
	}

	/**
	* Returns all the support labors.
	*
	* @return the support labors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportLabor> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the support labors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support labors
	* @param end the upper bound of the range of support labors (not inclusive)
	* @return the range of support labors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportLabor> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the support labors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support labors
	* @param end the upper bound of the range of support labors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support labors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportLabor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the support labors from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of support labors.
	*
	* @return the number of support labors
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SupportLaborPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SupportLaborPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportLaborPersistence.class.getName());

			ReferenceRegistry.registerReference(SupportLaborUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(SupportLaborPersistence persistence) {
	}

	private static SupportLaborPersistence _persistence;
}