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

import com.liferay.osb.model.CurrencyEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the currency entry service. This utility wraps {@link com.liferay.osb.service.persistence.impl.CurrencyEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CurrencyEntryPersistence
 * @see com.liferay.osb.service.persistence.impl.CurrencyEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class CurrencyEntryUtil {
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
	public static void clearCache(CurrencyEntry currencyEntry) {
		getPersistence().clearCache(currencyEntry);
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
	public static List<CurrencyEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CurrencyEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CurrencyEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CurrencyEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CurrencyEntry update(CurrencyEntry currencyEntry) {
		return getPersistence().update(currencyEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CurrencyEntry update(CurrencyEntry currencyEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(currencyEntry, serviceContext);
	}

	/**
	* Returns the currency entry where currencyCode = &#63; or throws a {@link NoSuchCurrencyEntryException} if it could not be found.
	*
	* @param currencyCode the currency code
	* @return the matching currency entry
	* @throws NoSuchCurrencyEntryException if a matching currency entry could not be found
	*/
	public static CurrencyEntry findByCurrencyCode(
		java.lang.String currencyCode)
		throws com.liferay.osb.exception.NoSuchCurrencyEntryException {
		return getPersistence().findByCurrencyCode(currencyCode);
	}

	/**
	* Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param currencyCode the currency code
	* @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	*/
	public static CurrencyEntry fetchByCurrencyCode(
		java.lang.String currencyCode) {
		return getPersistence().fetchByCurrencyCode(currencyCode);
	}

	/**
	* Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param currencyCode the currency code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	*/
	public static CurrencyEntry fetchByCurrencyCode(
		java.lang.String currencyCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCurrencyCode(currencyCode, retrieveFromCache);
	}

	/**
	* Removes the currency entry where currencyCode = &#63; from the database.
	*
	* @param currencyCode the currency code
	* @return the currency entry that was removed
	*/
	public static CurrencyEntry removeByCurrencyCode(
		java.lang.String currencyCode)
		throws com.liferay.osb.exception.NoSuchCurrencyEntryException {
		return getPersistence().removeByCurrencyCode(currencyCode);
	}

	/**
	* Returns the number of currency entries where currencyCode = &#63;.
	*
	* @param currencyCode the currency code
	* @return the number of matching currency entries
	*/
	public static int countByCurrencyCode(java.lang.String currencyCode) {
		return getPersistence().countByCurrencyCode(currencyCode);
	}

	/**
	* Returns all the currency entries where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @return the matching currency entries
	*/
	public static List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled) {
		return getPersistence().findByMarketplaceEnabled(marketplaceEnabled);
	}

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
	public static List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end) {
		return getPersistence()
				   .findByMarketplaceEnabled(marketplaceEnabled, start, end);
	}

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
	public static List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end,
		OrderByComparator<CurrencyEntry> orderByComparator) {
		return getPersistence()
				   .findByMarketplaceEnabled(marketplaceEnabled, start, end,
			orderByComparator);
	}

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
	public static List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end,
		OrderByComparator<CurrencyEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMarketplaceEnabled(marketplaceEnabled, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching currency entry
	* @throws NoSuchCurrencyEntryException if a matching currency entry could not be found
	*/
	public static CurrencyEntry findByMarketplaceEnabled_First(
		boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCurrencyEntryException {
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
	*/
	public static CurrencyEntry fetchByMarketplaceEnabled_First(
		boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator) {
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
	* @throws NoSuchCurrencyEntryException if a matching currency entry could not be found
	*/
	public static CurrencyEntry findByMarketplaceEnabled_Last(
		boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCurrencyEntryException {
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
	*/
	public static CurrencyEntry fetchByMarketplaceEnabled_Last(
		boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator) {
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
	* @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	*/
	public static CurrencyEntry[] findByMarketplaceEnabled_PrevAndNext(
		long currencyEntryId, boolean marketplaceEnabled,
		OrderByComparator<CurrencyEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCurrencyEntryException {
		return getPersistence()
				   .findByMarketplaceEnabled_PrevAndNext(currencyEntryId,
			marketplaceEnabled, orderByComparator);
	}

	/**
	* Removes all the currency entries where marketplaceEnabled = &#63; from the database.
	*
	* @param marketplaceEnabled the marketplace enabled
	*/
	public static void removeByMarketplaceEnabled(boolean marketplaceEnabled) {
		getPersistence().removeByMarketplaceEnabled(marketplaceEnabled);
	}

	/**
	* Returns the number of currency entries where marketplaceEnabled = &#63;.
	*
	* @param marketplaceEnabled the marketplace enabled
	* @return the number of matching currency entries
	*/
	public static int countByMarketplaceEnabled(boolean marketplaceEnabled) {
		return getPersistence().countByMarketplaceEnabled(marketplaceEnabled);
	}

	/**
	* Caches the currency entry in the entity cache if it is enabled.
	*
	* @param currencyEntry the currency entry
	*/
	public static void cacheResult(CurrencyEntry currencyEntry) {
		getPersistence().cacheResult(currencyEntry);
	}

	/**
	* Caches the currency entries in the entity cache if it is enabled.
	*
	* @param currencyEntries the currency entries
	*/
	public static void cacheResult(List<CurrencyEntry> currencyEntries) {
		getPersistence().cacheResult(currencyEntries);
	}

	/**
	* Creates a new currency entry with the primary key. Does not add the currency entry to the database.
	*
	* @param currencyEntryId the primary key for the new currency entry
	* @return the new currency entry
	*/
	public static CurrencyEntry create(long currencyEntryId) {
		return getPersistence().create(currencyEntryId);
	}

	/**
	* Removes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry that was removed
	* @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	*/
	public static CurrencyEntry remove(long currencyEntryId)
		throws com.liferay.osb.exception.NoSuchCurrencyEntryException {
		return getPersistence().remove(currencyEntryId);
	}

	public static CurrencyEntry updateImpl(CurrencyEntry currencyEntry) {
		return getPersistence().updateImpl(currencyEntry);
	}

	/**
	* Returns the currency entry with the primary key or throws a {@link NoSuchCurrencyEntryException} if it could not be found.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry
	* @throws NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	*/
	public static CurrencyEntry findByPrimaryKey(long currencyEntryId)
		throws com.liferay.osb.exception.NoSuchCurrencyEntryException {
		return getPersistence().findByPrimaryKey(currencyEntryId);
	}

	/**
	* Returns the currency entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry, or <code>null</code> if a currency entry with the primary key could not be found
	*/
	public static CurrencyEntry fetchByPrimaryKey(long currencyEntryId) {
		return getPersistence().fetchByPrimaryKey(currencyEntryId);
	}

	public static java.util.Map<java.io.Serializable, CurrencyEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the currency entries.
	*
	* @return the currency entries
	*/
	public static List<CurrencyEntry> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CurrencyEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CurrencyEntry> findAll(int start, int end,
		OrderByComparator<CurrencyEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CurrencyEntry> findAll(int start, int end,
		OrderByComparator<CurrencyEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the currency entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of currency entries.
	*
	* @return the number of currency entries
	*/
	public static int countAll() {
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

	private static CurrencyEntryPersistence _persistence;
}