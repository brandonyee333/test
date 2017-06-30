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

import com.liferay.osb.model.AppPricing;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the app pricing service. This utility wraps {@link AppPricingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPricingPersistence
 * @see AppPricingPersistenceImpl
 * @generated
 */
public class AppPricingUtil {
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
	public static void clearCache(AppPricing appPricing) {
		getPersistence().clearCache(appPricing);
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
	public static List<AppPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AppPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AppPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AppPricing update(AppPricing appPricing, boolean merge)
		throws SystemException {
		return getPersistence().update(appPricing, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AppPricing update(AppPricing appPricing, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(appPricing, merge, serviceContext);
	}

	/**
	* Caches the app pricing in the entity cache if it is enabled.
	*
	* @param appPricing the app pricing
	*/
	public static void cacheResult(com.liferay.osb.model.AppPricing appPricing) {
		getPersistence().cacheResult(appPricing);
	}

	/**
	* Caches the app pricings in the entity cache if it is enabled.
	*
	* @param appPricings the app pricings
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AppPricing> appPricings) {
		getPersistence().cacheResult(appPricings);
	}

	/**
	* Creates a new app pricing with the primary key. Does not add the app pricing to the database.
	*
	* @param appPricingId the primary key for the new app pricing
	* @return the new app pricing
	*/
	public static com.liferay.osb.model.AppPricing create(long appPricingId) {
		return getPersistence().create(appPricingId);
	}

	/**
	* Removes the app pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPricingId the primary key of the app pricing
	* @return the app pricing that was removed
	* @throws com.liferay.osb.NoSuchAppPricingException if a app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricing remove(long appPricingId)
		throws com.liferay.osb.NoSuchAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(appPricingId);
	}

	public static com.liferay.osb.model.AppPricing updateImpl(
		com.liferay.osb.model.AppPricing appPricing, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(appPricing, merge);
	}

	/**
	* Returns the app pricing with the primary key or throws a {@link com.liferay.osb.NoSuchAppPricingException} if it could not be found.
	*
	* @param appPricingId the primary key of the app pricing
	* @return the app pricing
	* @throws com.liferay.osb.NoSuchAppPricingException if a app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricing findByPrimaryKey(
		long appPricingId)
		throws com.liferay.osb.NoSuchAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(appPricingId);
	}

	/**
	* Returns the app pricing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appPricingId the primary key of the app pricing
	* @return the app pricing, or <code>null</code> if a app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricing fetchByPrimaryKey(
		long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(appPricingId);
	}

	/**
	* Returns all the app pricings where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the matching app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPricing> findByAppVersionId(
		long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppVersionId(appVersionId);
	}

	/**
	* Returns a range of all the app pricings where appVersionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param start the lower bound of the range of app pricings
	* @param end the upper bound of the range of app pricings (not inclusive)
	* @return the range of matching app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPricing> findByAppVersionId(
		long appVersionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppVersionId(appVersionId, start, end);
	}

	/**
	* Returns an ordered range of all the app pricings where appVersionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param start the lower bound of the range of app pricings
	* @param end the upper bound of the range of app pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPricing> findByAppVersionId(
		long appVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId(appVersionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing
	* @throws com.liferay.osb.NoSuchAppPricingException if a matching app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricing findByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_First(appVersionId, orderByComparator);
	}

	/**
	* Returns the first app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing, or <code>null</code> if a matching app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricing fetchByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppVersionId_First(appVersionId, orderByComparator);
	}

	/**
	* Returns the last app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing
	* @throws com.liferay.osb.NoSuchAppPricingException if a matching app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricing findByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_Last(appVersionId, orderByComparator);
	}

	/**
	* Returns the last app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing, or <code>null</code> if a matching app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricing fetchByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppVersionId_Last(appVersionId, orderByComparator);
	}

	/**
	* Returns the app pricings before and after the current app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appPricingId the primary key of the current app pricing
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app pricing
	* @throws com.liferay.osb.NoSuchAppPricingException if a app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricing[] findByAppVersionId_PrevAndNext(
		long appPricingId, long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_PrevAndNext(appPricingId, appVersionId,
			orderByComparator);
	}

	/**
	* Returns all the app pricings.
	*
	* @return the app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPricing> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the app pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app pricings
	* @param end the upper bound of the range of app pricings (not inclusive)
	* @return the range of app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPricing> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the app pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app pricings
	* @param end the upper bound of the range of app pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPricing> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the app pricings where appVersionId = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAppVersionId(appVersionId);
	}

	/**
	* Removes all the app pricings from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of app pricings where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the number of matching app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAppVersionId(appVersionId);
	}

	/**
	* Returns the number of app pricings.
	*
	* @return the number of app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AppPricingPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AppPricingPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppPricingPersistence.class.getName());

			ReferenceRegistry.registerReference(AppPricingUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AppPricingPersistence persistence) {
	}

	private static AppPricingPersistence _persistence;
}