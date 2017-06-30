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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the app pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPricingPersistenceImpl
 * @see AppPricingUtil
 * @generated
 */
public interface AppPricingPersistence extends BasePersistence<AppPricing> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AppPricingUtil} to access the app pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the app pricing in the entity cache if it is enabled.
	*
	* @param appPricing the app pricing
	*/
	public void cacheResult(com.liferay.osb.model.AppPricing appPricing);

	/**
	* Caches the app pricings in the entity cache if it is enabled.
	*
	* @param appPricings the app pricings
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AppPricing> appPricings);

	/**
	* Creates a new app pricing with the primary key. Does not add the app pricing to the database.
	*
	* @param appPricingId the primary key for the new app pricing
	* @return the new app pricing
	*/
	public com.liferay.osb.model.AppPricing create(long appPricingId);

	/**
	* Removes the app pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPricingId the primary key of the app pricing
	* @return the app pricing that was removed
	* @throws com.liferay.osb.NoSuchAppPricingException if a app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing remove(long appPricingId)
		throws com.liferay.osb.NoSuchAppPricingException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppPricing updateImpl(
		com.liferay.osb.model.AppPricing appPricing, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app pricing with the primary key or throws a {@link com.liferay.osb.NoSuchAppPricingException} if it could not be found.
	*
	* @param appPricingId the primary key of the app pricing
	* @return the app pricing
	* @throws com.liferay.osb.NoSuchAppPricingException if a app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing findByPrimaryKey(long appPricingId)
		throws com.liferay.osb.NoSuchAppPricingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app pricing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appPricingId the primary key of the app pricing
	* @return the app pricing, or <code>null</code> if a app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing fetchByPrimaryKey(long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app pricings where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the matching app pricings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricing> findByAppVersionId(
		long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPricing> findByAppVersionId(
		long appVersionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPricing> findByAppVersionId(
		long appVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing
	* @throws com.liferay.osb.NoSuchAppPricingException if a matching app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing findByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app pricing, or <code>null</code> if a matching app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing fetchByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing
	* @throws com.liferay.osb.NoSuchAppPricingException if a matching app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing findByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app pricing in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app pricing, or <code>null</code> if a matching app pricing could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing fetchByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AppPricing[] findByAppVersionId_PrevAndNext(
		long appPricingId, long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppPricingException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app pricings.
	*
	* @return the app pricings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricing> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPricing> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AppPricing> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app pricings where appVersionId = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app pricings from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app pricings where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the number of matching app pricings
	* @throws SystemException if a system exception occurred
	*/
	public int countByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app pricings.
	*
	* @return the number of app pricings
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}