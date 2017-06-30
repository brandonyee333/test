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

import com.liferay.osb.model.AccountLink;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the account link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountLinkPersistenceImpl
 * @see AccountLinkUtil
 * @generated
 */
public interface AccountLinkPersistence extends BasePersistence<AccountLink> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountLinkUtil} to access the account link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the account link in the entity cache if it is enabled.
	*
	* @param accountLink the account link
	*/
	public void cacheResult(com.liferay.osb.model.AccountLink accountLink);

	/**
	* Caches the account links in the entity cache if it is enabled.
	*
	* @param accountLinks the account links
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AccountLink> accountLinks);

	/**
	* Creates a new account link with the primary key. Does not add the account link to the database.
	*
	* @param accountLinkId the primary key for the new account link
	* @return the new account link
	*/
	public com.liferay.osb.model.AccountLink create(long accountLinkId);

	/**
	* Removes the account link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link that was removed
	* @throws com.liferay.osb.NoSuchAccountLinkException if a account link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink remove(long accountLinkId)
		throws com.liferay.osb.NoSuchAccountLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AccountLink updateImpl(
		com.liferay.osb.model.AccountLink accountLink, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account link with the primary key or throws a {@link com.liferay.osb.NoSuchAccountLinkException} if it could not be found.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link
	* @throws com.liferay.osb.NoSuchAccountLinkException if a account link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink findByPrimaryKey(
		long accountLinkId)
		throws com.liferay.osb.NoSuchAccountLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link, or <code>null</code> if a account link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink fetchByPrimaryKey(
		long accountLinkId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account links where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountLink> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account links where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @return the range of matching account links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountLink> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account links where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountLink> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account link
	* @throws com.liferay.osb.NoSuchAccountLinkException if a matching account link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account link, or <code>null</code> if a matching account link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account link
	* @throws com.liferay.osb.NoSuchAccountLinkException if a matching account link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account link, or <code>null</code> if a matching account link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account links before and after the current account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountLinkId the primary key of the current account link
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account link
	* @throws com.liferay.osb.NoSuchAccountLinkException if a account link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountLink[] findByAccountEntryId_PrevAndNext(
		long accountLinkId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountLinkException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account links.
	*
	* @return the account links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountLink> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @return the range of account links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountLink> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountLink> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account links where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account links from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account links where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account links
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account links.
	*
	* @return the number of account links
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}