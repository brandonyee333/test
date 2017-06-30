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

import com.liferay.osb.model.LCSSubscriptionEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the l c s subscription entry service. This utility wraps {@link LCSSubscriptionEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LCSSubscriptionEntryPersistence
 * @see LCSSubscriptionEntryPersistenceImpl
 * @generated
 */
public class LCSSubscriptionEntryUtil {
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
	public static void clearCache(LCSSubscriptionEntry lcsSubscriptionEntry) {
		getPersistence().clearCache(lcsSubscriptionEntry);
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
	public static List<LCSSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static LCSSubscriptionEntry update(
		LCSSubscriptionEntry lcsSubscriptionEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(lcsSubscriptionEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static LCSSubscriptionEntry update(
		LCSSubscriptionEntry lcsSubscriptionEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(lcsSubscriptionEntry, merge, serviceContext);
	}

	/**
	* Caches the l c s subscription entry in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	*/
	public static void cacheResult(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry) {
		getPersistence().cacheResult(lcsSubscriptionEntry);
	}

	/**
	* Caches the l c s subscription entries in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntries the l c s subscription entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> lcsSubscriptionEntries) {
		getPersistence().cacheResult(lcsSubscriptionEntries);
	}

	/**
	* Creates a new l c s subscription entry with the primary key. Does not add the l c s subscription entry to the database.
	*
	* @param lcsSubscriptionEntryId the primary key for the new l c s subscription entry
	* @return the new l c s subscription entry
	*/
	public static com.liferay.osb.model.LCSSubscriptionEntry create(
		long lcsSubscriptionEntryId) {
		return getPersistence().create(lcsSubscriptionEntryId);
	}

	/**
	* Removes the l c s subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry that was removed
	* @throws com.liferay.osb.NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LCSSubscriptionEntry remove(
		long lcsSubscriptionEntryId)
		throws com.liferay.osb.NoSuchLCSSubscriptionEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(lcsSubscriptionEntryId);
	}

	public static com.liferay.osb.model.LCSSubscriptionEntry updateImpl(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(lcsSubscriptionEntry, merge);
	}

	/**
	* Returns the l c s subscription entry with the primary key or throws a {@link com.liferay.osb.NoSuchLCSSubscriptionEntryException} if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry
	* @throws com.liferay.osb.NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LCSSubscriptionEntry findByPrimaryKey(
		long lcsSubscriptionEntryId)
		throws com.liferay.osb.NoSuchLCSSubscriptionEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(lcsSubscriptionEntryId);
	}

	/**
	* Returns the l c s subscription entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry, or <code>null</code> if a l c s subscription entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LCSSubscriptionEntry fetchByPrimaryKey(
		long lcsSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(lcsSubscriptionEntryId);
	}

	/**
	* Returns all the l c s subscription entries.
	*
	* @return the l c s subscription entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the l c s subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @return the range of l c s subscription entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the l c s subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s subscription entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the l c s subscription entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s subscription entries.
	*
	* @return the number of l c s subscription entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static LCSSubscriptionEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LCSSubscriptionEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					LCSSubscriptionEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(LCSSubscriptionEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(LCSSubscriptionEntryPersistence persistence) {
	}

	private static LCSSubscriptionEntryPersistence _persistence;
}