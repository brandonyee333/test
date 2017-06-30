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

import com.liferay.osb.model.CountryAppPricing;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the country app pricing service. This utility wraps {@link CountryAppPricingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CountryAppPricingPersistence
 * @see CountryAppPricingPersistenceImpl
 * @generated
 */
public class CountryAppPricingUtil {
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
	public static void clearCache(CountryAppPricing countryAppPricing) {
		getPersistence().clearCache(countryAppPricing);
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
	public static List<CountryAppPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CountryAppPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CountryAppPricing> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static CountryAppPricing update(
		CountryAppPricing countryAppPricing, boolean merge)
		throws SystemException {
		return getPersistence().update(countryAppPricing, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static CountryAppPricing update(
		CountryAppPricing countryAppPricing, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(countryAppPricing, merge, serviceContext);
	}

	/**
	* Caches the country app pricing in the entity cache if it is enabled.
	*
	* @param countryAppPricing the country app pricing
	*/
	public static void cacheResult(
		com.liferay.osb.model.CountryAppPricing countryAppPricing) {
		getPersistence().cacheResult(countryAppPricing);
	}

	/**
	* Caches the country app pricings in the entity cache if it is enabled.
	*
	* @param countryAppPricings the country app pricings
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.CountryAppPricing> countryAppPricings) {
		getPersistence().cacheResult(countryAppPricings);
	}

	/**
	* Creates a new country app pricing with the primary key. Does not add the country app pricing to the database.
	*
	* @param countryAppPricingId the primary key for the new country app pricing
	* @return the new country app pricing
	*/
	public static com.liferay.osb.model.CountryAppPricing create(
		long countryAppPricingId) {
		return getPersistence().create(countryAppPricingId);
	}

	/**
	* Removes the country app pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param countryAppPricingId the primary key of the country app pricing
	* @return the country app pricing that was removed
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing remove(
		long countryAppPricingId)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(countryAppPricingId);
	}

	public static com.liferay.osb.model.CountryAppPricing updateImpl(
		com.liferay.osb.model.CountryAppPricing countryAppPricing, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(countryAppPricing, merge);
	}

	/**
	* Returns the country app pricing with the primary key or throws a {@link com.liferay.osb.NoSuchCountryAppPricingException} if it could not be found.
	*
	* @param countryAppPricingId the primary key of the country app pricing
	* @return the country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing findByPrimaryKey(
		long countryAppPricingId)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(countryAppPricingId);
	}

	/**
	* Returns the country app pricing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param countryAppPricingId the primary key of the country app pricing
	* @return the country app pricing, or <code>null</code> if a country app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing fetchByPrimaryKey(
		long countryAppPricingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(countryAppPricingId);
	}

	/**
	* Returns all the country app pricings where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findByAppVersionId(
		long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppVersionId(appVersionId);
	}

	/**
	* Returns a range of all the country app pricings where appVersionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param start the lower bound of the range of country app pricings
	* @param end the upper bound of the range of country app pricings (not inclusive)
	* @return the range of matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findByAppVersionId(
		long appVersionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppVersionId(appVersionId, start, end);
	}

	/**
	* Returns an ordered range of all the country app pricings where appVersionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param start the lower bound of the range of country app pricings
	* @param end the upper bound of the range of country app pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findByAppVersionId(
		long appVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId(appVersionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first country app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing findByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_First(appVersionId, orderByComparator);
	}

	/**
	* Returns the first country app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing fetchByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppVersionId_First(appVersionId, orderByComparator);
	}

	/**
	* Returns the last country app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing findByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_Last(appVersionId, orderByComparator);
	}

	/**
	* Returns the last country app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing fetchByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppVersionId_Last(appVersionId, orderByComparator);
	}

	/**
	* Returns the country app pricings before and after the current country app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param countryAppPricingId the primary key of the current country app pricing
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing[] findByAppVersionId_PrevAndNext(
		long countryAppPricingId, long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_PrevAndNext(countryAppPricingId,
			appVersionId, orderByComparator);
	}

	/**
	* Returns all the country app pricings where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @return the matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findByAppPricingId(
		long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppPricingId(appPricingId);
	}

	/**
	* Returns a range of all the country app pricings where appPricingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPricingId the app pricing ID
	* @param start the lower bound of the range of country app pricings
	* @param end the upper bound of the range of country app pricings (not inclusive)
	* @return the range of matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findByAppPricingId(
		long appPricingId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppPricingId(appPricingId, start, end);
	}

	/**
	* Returns an ordered range of all the country app pricings where appPricingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appPricingId the app pricing ID
	* @param start the lower bound of the range of country app pricings
	* @param end the upper bound of the range of country app pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findByAppPricingId(
		long appPricingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPricingId(appPricingId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first country app pricing in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing findByAppPricingId_First(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPricingId_First(appPricingId, orderByComparator);
	}

	/**
	* Returns the first country app pricing in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing fetchByAppPricingId_First(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppPricingId_First(appPricingId, orderByComparator);
	}

	/**
	* Returns the last country app pricing in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing findByAppPricingId_Last(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPricingId_Last(appPricingId, orderByComparator);
	}

	/**
	* Returns the last country app pricing in the ordered set where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing fetchByAppPricingId_Last(
		long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppPricingId_Last(appPricingId, orderByComparator);
	}

	/**
	* Returns the country app pricings before and after the current country app pricing in the ordered set where appPricingId = &#63;.
	*
	* @param countryAppPricingId the primary key of the current country app pricing
	* @param appPricingId the app pricing ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing[] findByAppPricingId_PrevAndNext(
		long countryAppPricingId, long appPricingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppPricingId_PrevAndNext(countryAppPricingId,
			appPricingId, orderByComparator);
	}

	/**
	* Returns all the country app pricings where countryId = &#63;.
	*
	* @param countryId the country ID
	* @return the matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findByCountryId(
		long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCountryId(countryId);
	}

	/**
	* Returns a range of all the country app pricings where countryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param countryId the country ID
	* @param start the lower bound of the range of country app pricings
	* @param end the upper bound of the range of country app pricings (not inclusive)
	* @return the range of matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findByCountryId(
		long countryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCountryId(countryId, start, end);
	}

	/**
	* Returns an ordered range of all the country app pricings where countryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param countryId the country ID
	* @param start the lower bound of the range of country app pricings
	* @param end the upper bound of the range of country app pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findByCountryId(
		long countryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCountryId(countryId, start, end, orderByComparator);
	}

	/**
	* Returns the first country app pricing in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing findByCountryId_First(
		long countryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCountryId_First(countryId, orderByComparator);
	}

	/**
	* Returns the first country app pricing in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing fetchByCountryId_First(
		long countryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCountryId_First(countryId, orderByComparator);
	}

	/**
	* Returns the last country app pricing in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing findByCountryId_Last(
		long countryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCountryId_Last(countryId, orderByComparator);
	}

	/**
	* Returns the last country app pricing in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing fetchByCountryId_Last(
		long countryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCountryId_Last(countryId, orderByComparator);
	}

	/**
	* Returns the country app pricings before and after the current country app pricing in the ordered set where countryId = &#63;.
	*
	* @param countryAppPricingId the primary key of the current country app pricing
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing[] findByCountryId_PrevAndNext(
		long countryAppPricingId, long countryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCountryId_PrevAndNext(countryAppPricingId, countryId,
			orderByComparator);
	}

	/**
	* Returns the country app pricing where appVersionId = &#63; and countryId = &#63; or throws a {@link com.liferay.osb.NoSuchCountryAppPricingException} if it could not be found.
	*
	* @param appVersionId the app version ID
	* @param countryId the country ID
	* @return the matching country app pricing
	* @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing findByAVI_CI(
		long appVersionId, long countryId)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAVI_CI(appVersionId, countryId);
	}

	/**
	* Returns the country app pricing where appVersionId = &#63; and countryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appVersionId the app version ID
	* @param countryId the country ID
	* @return the matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing fetchByAVI_CI(
		long appVersionId, long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByAVI_CI(appVersionId, countryId);
	}

	/**
	* Returns the country app pricing where appVersionId = &#63; and countryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appVersionId the app version ID
	* @param countryId the country ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing fetchByAVI_CI(
		long appVersionId, long countryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAVI_CI(appVersionId, countryId, retrieveFromCache);
	}

	/**
	* Returns all the country app pricings.
	*
	* @return the country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the country app pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of country app pricings
	* @param end the upper bound of the range of country app pricings (not inclusive)
	* @return the range of country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the country app pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of country app pricings
	* @param end the upper bound of the range of country app pricings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CountryAppPricing> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the country app pricings where appVersionId = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAppVersionId(appVersionId);
	}

	/**
	* Removes all the country app pricings where appPricingId = &#63; from the database.
	*
	* @param appPricingId the app pricing ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAppPricingId(long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAppPricingId(appPricingId);
	}

	/**
	* Removes all the country app pricings where countryId = &#63; from the database.
	*
	* @param countryId the country ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCountryId(long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCountryId(countryId);
	}

	/**
	* Removes the country app pricing where appVersionId = &#63; and countryId = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @param countryId the country ID
	* @return the country app pricing that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CountryAppPricing removeByAVI_CI(
		long appVersionId, long countryId)
		throws com.liferay.osb.NoSuchCountryAppPricingException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByAVI_CI(appVersionId, countryId);
	}

	/**
	* Removes all the country app pricings from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of country app pricings where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the number of matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAppVersionId(appVersionId);
	}

	/**
	* Returns the number of country app pricings where appPricingId = &#63;.
	*
	* @param appPricingId the app pricing ID
	* @return the number of matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAppPricingId(long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAppPricingId(appPricingId);
	}

	/**
	* Returns the number of country app pricings where countryId = &#63;.
	*
	* @param countryId the country ID
	* @return the number of matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCountryId(long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCountryId(countryId);
	}

	/**
	* Returns the number of country app pricings where appVersionId = &#63; and countryId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param countryId the country ID
	* @return the number of matching country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAVI_CI(long appVersionId, long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAVI_CI(appVersionId, countryId);
	}

	/**
	* Returns the number of country app pricings.
	*
	* @return the number of country app pricings
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CountryAppPricingPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CountryAppPricingPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					CountryAppPricingPersistence.class.getName());

			ReferenceRegistry.registerReference(CountryAppPricingUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(CountryAppPricingPersistence persistence) {
	}

	private static CountryAppPricingPersistence _persistence;
}