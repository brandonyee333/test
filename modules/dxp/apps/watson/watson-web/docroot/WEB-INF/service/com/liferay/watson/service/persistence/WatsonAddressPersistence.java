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

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.watson.exception.NoSuchAddressException;
import com.liferay.watson.model.WatsonAddress;

/**
 * The persistence interface for the watson address service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eddie Olson
 * @see com.liferay.watson.service.persistence.impl.WatsonAddressPersistenceImpl
 * @see WatsonAddressUtil
 * @generated
 */
@ProviderType
public interface WatsonAddressPersistence extends BasePersistence<WatsonAddress> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonAddressUtil} to access the watson address persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson address in the entity cache if it is enabled.
	*
	* @param watsonAddress the watson address
	*/
	public void cacheResult(WatsonAddress watsonAddress);

	/**
	* Caches the watson addresses in the entity cache if it is enabled.
	*
	* @param watsonAddresses the watson addresses
	*/
	public void cacheResult(java.util.List<WatsonAddress> watsonAddresses);

	/**
	* Creates a new watson address with the primary key. Does not add the watson address to the database.
	*
	* @param watsonAddressId the primary key for the new watson address
	* @return the new watson address
	*/
	public WatsonAddress create(long watsonAddressId);

	/**
	* Removes the watson address with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonAddressId the primary key of the watson address
	* @return the watson address that was removed
	* @throws NoSuchAddressException if a watson address with the primary key could not be found
	*/
	public WatsonAddress remove(long watsonAddressId)
		throws NoSuchAddressException;

	public WatsonAddress updateImpl(WatsonAddress watsonAddress);

	/**
	* Returns the watson address with the primary key or throws a {@link NoSuchAddressException} if it could not be found.
	*
	* @param watsonAddressId the primary key of the watson address
	* @return the watson address
	* @throws NoSuchAddressException if a watson address with the primary key could not be found
	*/
	public WatsonAddress findByPrimaryKey(long watsonAddressId)
		throws NoSuchAddressException;

	/**
	* Returns the watson address with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonAddressId the primary key of the watson address
	* @return the watson address, or <code>null</code> if a watson address with the primary key could not be found
	*/
	public WatsonAddress fetchByPrimaryKey(long watsonAddressId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonAddress> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson addresses.
	*
	* @return the watson addresses
	*/
	public java.util.List<WatsonAddress> findAll();

	/**
	* Returns a range of all the watson addresses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson addresses
	* @param end the upper bound of the range of watson addresses (not inclusive)
	* @return the range of watson addresses
	*/
	public java.util.List<WatsonAddress> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson addresses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson addresses
	* @param end the upper bound of the range of watson addresses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson addresses
	*/
	public java.util.List<WatsonAddress> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonAddress> orderByComparator);

	/**
	* Returns an ordered range of all the watson addresses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson addresses
	* @param end the upper bound of the range of watson addresses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson addresses
	*/
	public java.util.List<WatsonAddress> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonAddress> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson addresses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson addresses.
	*
	* @return the number of watson addresses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}