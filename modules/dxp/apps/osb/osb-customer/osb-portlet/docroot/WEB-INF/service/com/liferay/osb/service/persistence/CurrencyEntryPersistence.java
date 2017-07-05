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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchCurrencyEntryException;
import com.liferay.osb.model.CurrencyEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the currency entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.CurrencyEntryPersistenceImpl
 * @see CurrencyEntryUtil
 * @generated
 */
@ProviderType
public interface CurrencyEntryPersistence extends BasePersistence<CurrencyEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CurrencyEntryUtil} to access the currency entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the currency entry where currencyCode = &#63; or throws a {@link NoSuchCurrencyEntryException} if it could not be found.
	*
	* @param currencyCode the currency code
	* @return the matching currency entry
	* @throws NoSuchCurrencyEntryException if a matching currency entry could not be found
	*/
	public CurrencyEntry findByCurrencyCode(java.lang.String currencyCode)
		throws NoSuchCurrencyEntryException;

	/**
	* Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param currencyCode the currency code
	* @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	*/
	public CurrencyEntry fetchByCurrencyCode(java.lang.String currencyCode);

	/**
	* Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param currencyCode the currency code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	*/
	public CurrencyEntry fetchByCurrencyCode(java.lang.String currencyCode,
		boolean retrieveFromCache);

	/**
	* Removes the currency entry where currencyCode = &#63; from the database.
	*
	* @param currencyCode the currency code
	* @return the currency entry that was removed
	*/
	public CurrencyEntry removeByCurrencyCode(java.lang.String currencyCode)
		throws NoSuchCurrencyEntryException;

	/**
	* Returns the number of currency entries where currencyCode = &#63;.
	*
	* @param currencyCode the currency code
	* @return the number of matching currency entries
	*/
	public int countByCurrencyCode(java.lang.String currencyCode);

	/**
	* Returns all the currency entries where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @return the matching currency entries
	*/
	public java.util.List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled);

	/**
	* Returns a range of all the currency entries where marketplaceEnabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @return the range of matching currency entries
	*/
	public java.util.List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end);

	/**
	* Returns an ordered range of all the currency entries where marketplaceEnabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching currency entries
	*/
	public java.util.List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CurrencyEntry> orderByComparator);

	/**
	* Returns an ordered range of all the currency entries where marketplaceEnabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching currency entries
	*/
	public java.util.List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CurrencyEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching currency entry
	* @throws NoSuchCurrencyEntryException if a matching currency entry could not be found
	*/
	public CurrencyEntry findByMarketplaceEnabled_First(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator<CurrencyEntry> orderByComparator)
		throws NoSuchCurrencyEntryException;

	/**
	* Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching currency entry, or <code>null</code> if a matching currency entry could not be found
	*/
	public CurrencyEntry fetchByMarketplaceEnabled_First(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator<CurrencyEntry> orderByComparator);

	/**
	* Returns the last currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching currency entry
	* @throws NoSuchCurrencyEntryException if a matching currency entry could not be found
	*/
	public CurrencyEntry findByMarketplaceEnabled_Last(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator<CurrencyEntry> orderByComparator)
		throws NoSuchCurrencyEntryException;

	/**
	* Returns the last currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching currency entry, or <code>null</code> if a matching currency entry could not be found
	*/
	public CurrencyEntry fetchByMarketplaceEnabled_Last(
		boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator<CurrencyEntry> orderByComparator);

	/**
	* Returns the currency entries before and after the current currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param currencyEntryId the primary key of the current currency entry
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next currency entry
	* @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	*/
	public CurrencyEntry[] findByMarketplaceEnabled_PrevAndNext(
		long currencyEntryId, boolean marketplaceEnabled,
		com.liferay.portal.kernel.util.OrderByComparator<CurrencyEntry> orderByComparator)
		throws NoSuchCurrencyEntryException;

	/**
	* Removes all the currency entries where marketplaceEnabled = &#63; from the database.
	*
	* @param marketplaceEnabled the marketplace enabled
	*/
	public void removeByMarketplaceEnabled(boolean marketplaceEnabled);

	/**
	* Returns the number of currency entries where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @return the number of matching currency entries
	*/
	public int countByMarketplaceEnabled(boolean marketplaceEnabled);

	/**
	* Caches the currency entry in the entity cache if it is enabled.
	*
	* @param currencyEntry the currency entry
	*/
	public void cacheResult(CurrencyEntry currencyEntry);

	/**
	* Caches the currency entries in the entity cache if it is enabled.
	*
	* @param currencyEntries the currency entries
	*/
	public void cacheResult(java.util.List<CurrencyEntry> currencyEntries);

	/**
	* Creates a new currency entry with the primary key. Does not add the currency entry to the database.
	*
	* @param currencyEntryId the primary key for the new currency entry
	* @return the new currency entry
	*/
	public CurrencyEntry create(long currencyEntryId);

	/**
	* Removes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry that was removed
	* @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	*/
	public CurrencyEntry remove(long currencyEntryId)
		throws NoSuchCurrencyEntryException;

	public CurrencyEntry updateImpl(CurrencyEntry currencyEntry);

	/**
	* Returns the currency entry with the primary key or throws a {@link NoSuchCurrencyEntryException} if it could not be found.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry
	* @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	*/
	public CurrencyEntry findByPrimaryKey(long currencyEntryId)
		throws NoSuchCurrencyEntryException;

	/**
	* Returns the currency entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry, or <code>null</code> if a currency entry with the primary key could not be found
	*/
	public CurrencyEntry fetchByPrimaryKey(long currencyEntryId);

	@Override
	public java.util.Map<java.io.Serializable, CurrencyEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the currency entries.
	*
	* @return the currency entries
	*/
	public java.util.List<CurrencyEntry> findAll();

	/**
	* Returns a range of all the currency entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @return the range of currency entries
	*/
	public java.util.List<CurrencyEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the currency entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of currency entries
	*/
	public java.util.List<CurrencyEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CurrencyEntry> orderByComparator);

	/**
	* Returns an ordered range of all the currency entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of currency entries
	*/
	public java.util.List<CurrencyEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CurrencyEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the currency entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of currency entries.
	*
	* @return the number of currency entries
	*/
	public int countAll();
}