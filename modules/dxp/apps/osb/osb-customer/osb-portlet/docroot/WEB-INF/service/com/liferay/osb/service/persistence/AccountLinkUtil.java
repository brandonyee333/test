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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the account link service. This utility wraps {@link AccountLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountLinkPersistence
 * @see AccountLinkPersistenceImpl
 * @generated
 */
public class AccountLinkUtil {
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
	public static void clearCache(AccountLink accountLink) {
		getPersistence().clearCache(accountLink);
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
	public static List<AccountLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AccountLink update(AccountLink accountLink, boolean merge)
		throws SystemException {
		return getPersistence().update(accountLink, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AccountLink update(AccountLink accountLink, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(accountLink, merge, serviceContext);
	}

	/**
	* Caches the account link in the entity cache if it is enabled.
	*
	* @param accountLink the account link
	*/
	public static void cacheResult(
		com.liferay.osb.model.AccountLink accountLink) {
		getPersistence().cacheResult(accountLink);
	}

	/**
	* Caches the account links in the entity cache if it is enabled.
	*
	* @param accountLinks the account links
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AccountLink> accountLinks) {
		getPersistence().cacheResult(accountLinks);
	}

	/**
	* Creates a new account link with the primary key. Does not add the account link to the database.
	*
	* @param accountLinkId the primary key for the new account link
	* @return the new account link
	*/
	public static com.liferay.osb.model.AccountLink create(long accountLinkId) {
		return getPersistence().create(accountLinkId);
	}

	/**
	* Removes the account link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link that was removed
	* @throws com.liferay.osb.NoSuchAccountLinkException if a account link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountLink remove(long accountLinkId)
		throws com.liferay.osb.NoSuchAccountLinkException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(accountLinkId);
	}

	public static com.liferay.osb.model.AccountLink updateImpl(
		com.liferay.osb.model.AccountLink accountLink, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(accountLink, merge);
	}

	/**
	* Returns the account link with the primary key or throws a {@link com.liferay.osb.NoSuchAccountLinkException} if it could not be found.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link
	* @throws com.liferay.osb.NoSuchAccountLinkException if a account link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountLink findByPrimaryKey(
		long accountLinkId)
		throws com.liferay.osb.NoSuchAccountLinkException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(accountLinkId);
	}

	/**
	* Returns the account link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link, or <code>null</code> if a account link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountLink fetchByPrimaryKey(
		long accountLinkId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(accountLinkId);
	}

	/**
	* Returns all the account links where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account links
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountLink> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.AccountLink> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AccountLink> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account link
	* @throws com.liferay.osb.NoSuchAccountLinkException if a matching account link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountLink findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountLinkException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account link, or <code>null</code> if a matching account link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountLink fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account link
	* @throws com.liferay.osb.NoSuchAccountLinkException if a matching account link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountLink findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountLinkException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account link, or <code>null</code> if a matching account link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountLink fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

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
	public static com.liferay.osb.model.AccountLink[] findByAccountEntryId_PrevAndNext(
		long accountLinkId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountLinkException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(accountLinkId,
			accountEntryId, orderByComparator);
	}

	/**
	* Returns all the account links.
	*
	* @return the account links
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountLink> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.AccountLink> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AccountLink> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the account links where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Removes all the account links from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account links where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account links
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of account links.
	*
	* @return the number of account links
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AccountLinkPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountLinkPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AccountLinkPersistence.class.getName());

			ReferenceRegistry.registerReference(AccountLinkUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AccountLinkPersistence persistence) {
	}

	private static AccountLinkPersistence _persistence;
}