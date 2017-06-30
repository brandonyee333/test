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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the currency entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CurrencyEntryPersistenceImpl
 * @see CurrencyEntryUtil
 * @generated
 */
public interface CurrencyEntryPersistence extends BasePersistence<CurrencyEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CurrencyEntryUtil} to access the currency entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the currency entry in the entity cache if it is enabled.
	*
	* @param currencyEntry the currency entry
	*/
	public void cacheResult(com.liferay.osb.model.CurrencyEntry currencyEntry);

	/**
	* Caches the currency entries in the entity cache if it is enabled.
	*
	* @param currencyEntries the currency entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.CurrencyEntry> currencyEntries);

	/**
	* Creates a new currency entry with the primary key. Does not add the currency entry to the database.
	*
	* @param currencyEntryId the primary key for the new currency entry
	* @return the new currency entry
	*/
	public com.liferay.osb.model.CurrencyEntry create(long currencyEntryId);

	/**
	* Removes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry that was removed
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry remove(long currencyEntryId)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.CurrencyEntry updateImpl(
		com.liferay.osb.model.CurrencyEntry currencyEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the currency entry with the primary key or throws a {@link com.liferay.osb.NoSuchCurrencyEntryException} if it could not be found.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry findByPrimaryKey(
		long currencyEntryId)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the currency entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry, or <code>null</code> if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry fetchByPrimaryKey(
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the currency entry where currencyCode = &#63; or throws a {@link com.liferay.osb.NoSuchCurrencyEntryException} if it could not be found.
	*
	* @param currencyCode the currency code
	* @return the matching currency entry
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry findByCurrencyCode(
		java.lang.String currencyCode)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param currencyCode the currency code
	* @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry fetchByCurrencyCode(
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param currencyCode the currency code
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry fetchByCurrencyCode(
		java.lang.String currencyCode, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the currency entries where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @return the matching currency entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching currency entry
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry findByMarketplaceEnabled_First(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching currency entry, or <code>null</code> if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry fetchByMarketplaceEnabled_First(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching currency entry
	* @throws com.liferay.osb.NoSuchCurrencyEntryException if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry findByMarketplaceEnabled_Last(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching currency entry, or <code>null</code> if a matching currency entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry fetchByMarketplaceEnabled_Last(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.CurrencyEntry[] findByMarketplaceEnabled_PrevAndNext(
		long currencyEntryId, boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the currency entries.
	*
	* @return the currency entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CurrencyEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.CurrencyEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.CurrencyEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the currency entry where currencyCode = &#63; from the database.
	*
	* @param currencyCode the currency code
	* @return the currency entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry removeByCurrencyCode(
		java.lang.String currencyCode)
		throws com.liferay.osb.NoSuchCurrencyEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the currency entries where marketplaceEnabled = &#63; from the database.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @throws SystemException if a system exception occurred
	*/
	public void removeByMarketplaceEnabled(boolean marketplaceEnabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the currency entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of currency entries where currencyCode = &#63;.
	*
	* @param currencyCode the currency code
	* @return the number of matching currency entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByCurrencyCode(java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of currency entries where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @return the number of matching currency entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByMarketplaceEnabled(boolean marketplaceEnabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of currency entries.
	*
	* @return the number of currency entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}