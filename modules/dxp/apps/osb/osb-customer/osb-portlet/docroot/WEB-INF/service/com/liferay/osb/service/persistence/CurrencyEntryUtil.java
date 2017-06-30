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

import com.liferay.osb.model.CurrencyEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the currency entry service. This utility wraps {@link CurrencyEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CurrencyEntryPersistence
 * @see CurrencyEntryPersistenceImpl
 * @generated
 */
public class CurrencyEntryUtil {
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
	public static void clearCache(CurrencyEntry currencyEntry) {
		getPersistence().clearCache(currencyEntry);
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
	public static List<CurrencyEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CurrencyEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CurrencyEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static CurrencyEntry update(CurrencyEntry currencyEntry,
		boolean merge) throws SystemException {
		return getPersistence().update(currencyEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static CurrencyEntry update(CurrencyEntry currencyEntry,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(currencyEntry, merge, serviceContext);
	}

	/**
	* Caches the currency entry in the entity cache if it is enabled.
	*
	* @param currencyEntry the currency entry
	*/
	public static void cacheResult(
		com.liferay.osb.model.CurrencyEntry currencyEntry) {
		getPersistence().cacheResult(currencyEntry);
	}

	/**
	* Caches the currency entries in the entity cache if it is enabled.
	*
	* @param currencyEntries the currency entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.CurrencyEntry> currencyEntries) {
		getPersistence().cacheResult(currencyEntries);
	}

	/**
	* Creates a new currency entry with the primary key. Does not add the currency entry to the database.
	*
	* @param currencyEntryId the primary key for the new currency entry
	* @return the new currency entry
	*/
	public static com.liferay.osb.model.CurrencyEntry create(
		long currencyEntryId) {
		return getPersistence().create(currencyEntryId);
	}

	/**
	* Removes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry that was removed
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry remove(
		long currencyEntryId)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(currencyEntryId);
	}

	public static com.liferay.osb.model.CurrencyEntry updateImpl(
		com.liferay.osb.model.CurrencyEntry currencyEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(currencyEntry, merge);
	}

	/**
	* Returns the currency entry with the primary key or throws a {@link com.liferay.osb.NoSuchCurrencyEntryException} if it could not be found.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry findByPrimaryKey(
		long currencyEntryId)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(currencyEntryId);
	}

	/**
	* Returns the currency entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry, or <code>null</code> if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry fetchByPrimaryKey(
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(currencyEntryId);
	}

	/**
	* Returns the currency entry where currencyCode = &#63; or throws a {@link com.liferay.osb.NoSuchCurrencyEntryException} if it could not be found.
	*
	* @param currencyCode the currency code
	* @return the matching currency entry
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry findByCurrencyCode(
		java.lang.String currencyCode)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCurrencyCode(currencyCode);
	}

	/**
	* Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param currencyCode the currency code
	* @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry fetchByCurrencyCode(
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByCurrencyCode(currencyCode);
	}

	/**
	* Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param currencyCode the currency code
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry fetchByCurrencyCode(
		java.lang.String currencyCode, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCurrencyCode(currencyCode, retrieveFromCache);
	}

	/**
	* Returns all the currency entries where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @return the matching currency entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMarketplaceEnabled(marketplaceEnabled);
	}

	/**
	* Returns a range of all the currency entries where marketplaceEnabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @return the range of matching currency entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMarketplaceEnabled(marketplaceEnabled, start, end);
	}

	/**
	* Returns an ordered range of all the currency entries where marketplaceEnabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching currency entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMarketplaceEnabled(marketplaceEnabled, start, end,
			orderByComparator);
	}

	/**
	* Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching currency entry
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry findByMarketplaceEnabled_First(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMarketplaceEnabled_First(marketplaceEnabled,
			orderByComparator);
	}

	/**
	* Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching currency entry, or <code>null</code> if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry fetchByMarketplaceEnabled_First(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMarketplaceEnabled_First(marketplaceEnabled,
			orderByComparator);
	}

	/**
	* Returns the last currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching currency entry
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry findByMarketplaceEnabled_Last(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMarketplaceEnabled_Last(marketplaceEnabled,
			orderByComparator);
	}

	/**
	* Returns the last currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching currency entry, or <code>null</code> if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry fetchByMarketplaceEnabled_Last(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMarketplaceEnabled_Last(marketplaceEnabled,
			orderByComparator);
	}

	/**
	* Returns the currency entries before and after the current currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param currencyEntryId the primary key of the current currency entry
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next currency entry
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry[] findByMarketplaceEnabled_PrevAndNext(
		long currencyEntryId, boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMarketplaceEnabled_PrevAndNext(currencyEntryId,
			marketplaceEnabled, orderByComparator);
	}

	/**
	* Returns all the currency entries.
	*
	* @return the currency entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CurrencyEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the currency entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @return the range of currency entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CurrencyEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the currency entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of currency entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CurrencyEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the currency entry where currencyCode = &#63; from the database.
	*
	* @param currencyCode the currency code
	* @return the currency entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry removeByCurrencyCode(
		java.lang.String currencyCode)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByCurrencyCode(currencyCode);
	}

	/**
	* Removes all the currency entries where marketplaceEnabled = &#63; from the database.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByMarketplaceEnabled(boolean marketplaceEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByMarketplaceEnabled(marketplaceEnabled);
	}

	/**
	* Removes all the currency entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of currency entries where currencyCode = &#63;.
	*
	* @param currencyCode the currency code
	* @return the number of matching currency entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCurrencyCode(java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCurrencyCode(currencyCode);
	}

	/**
	* Returns the number of currency entries where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @return the number of matching currency entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMarketplaceEnabled(boolean marketplaceEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMarketplaceEnabled(marketplaceEnabled);
	}

	/**
	* Returns the number of currency entries.
	*
	* @return the number of currency entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CurrencyEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CurrencyEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					CurrencyEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(CurrencyEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(CurrencyEntryPersistence persistence) {
	}

	private static CurrencyEntryPersistence _persistence;
}