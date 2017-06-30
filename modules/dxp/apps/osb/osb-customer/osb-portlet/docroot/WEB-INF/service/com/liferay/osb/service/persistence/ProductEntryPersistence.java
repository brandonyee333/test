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

import com.liferay.osb.model.ProductEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the product entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryPersistenceImpl
 * @see ProductEntryUtil
 * @generated
 */
public interface ProductEntryPersistence extends BasePersistence<ProductEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductEntryUtil} to access the product entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the product entry in the entity cache if it is enabled.
	*
	* @param productEntry the product entry
	*/
	public void cacheResult(com.liferay.osb.model.ProductEntry productEntry);

	/**
	* Caches the product entries in the entity cache if it is enabled.
	*
	* @param productEntries the product entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.ProductEntry> productEntries);

	/**
	* Creates a new product entry with the primary key. Does not add the product entry to the database.
	*
	* @param productEntryId the primary key for the new product entry
	* @return the new product entry
	*/
	public com.liferay.osb.model.ProductEntry create(long productEntryId);

	/**
	* Removes the product entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param productEntryId the primary key of the product entry
	* @return the product entry that was removed
	* @throws com.liferay.osb.NoSuchProductEntryException if a product entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ProductEntry remove(long productEntryId)
		throws com.liferay.osb.NoSuchProductEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.ProductEntry updateImpl(
		com.liferay.osb.model.ProductEntry productEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the product entry with the primary key or throws a {@link com.liferay.osb.NoSuchProductEntryException} if it could not be found.
	*
	* @param productEntryId the primary key of the product entry
	* @return the product entry
	* @throws com.liferay.osb.NoSuchProductEntryException if a product entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ProductEntry findByPrimaryKey(
		long productEntryId)
		throws com.liferay.osb.NoSuchProductEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the product entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param productEntryId the primary key of the product entry
	* @return the product entry, or <code>null</code> if a product entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ProductEntry fetchByPrimaryKey(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the product entry where name = &#63; or throws a {@link com.liferay.osb.NoSuchProductEntryException} if it could not be found.
	*
	* @param name the name
	* @return the matching product entry
	* @throws com.liferay.osb.NoSuchProductEntryException if a matching product entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ProductEntry findByName(java.lang.String name)
		throws com.liferay.osb.NoSuchProductEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the product entry where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ProductEntry fetchByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the product entry where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ProductEntry fetchByName(
		java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the product entries.
	*
	* @return the product entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ProductEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the product entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of product entries
	* @param end the upper bound of the range of product entries (not inclusive)
	* @return the range of product entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ProductEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the product entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of product entries
	* @param end the upper bound of the range of product entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of product entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ProductEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the product entry where name = &#63; from the database.
	*
	* @param name the name
	* @return the product entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ProductEntry removeByName(
		java.lang.String name)
		throws com.liferay.osb.NoSuchProductEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the product entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of product entries where name = &#63;.
	*
	* @param name the name
	* @return the number of matching product entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of product entries.
	*
	* @return the number of product entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}