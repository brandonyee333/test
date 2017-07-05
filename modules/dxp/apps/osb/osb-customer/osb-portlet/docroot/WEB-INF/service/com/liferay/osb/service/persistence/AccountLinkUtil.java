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

import com.liferay.osb.model.AccountLink;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the account link service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AccountLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountLinkPersistence
 * @see com.liferay.osb.service.persistence.impl.AccountLinkPersistenceImpl
 * @generated
 */
@ProviderType
public class AccountLinkUtil {
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
	public static void clearCache(AccountLink accountLink) {
		getPersistence().clearCache(accountLink);
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
	public static List<AccountLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountLink> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountLink update(AccountLink accountLink) {
		return getPersistence().update(accountLink);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountLink update(AccountLink accountLink,
		ServiceContext serviceContext) {
		return getPersistence().update(accountLink, serviceContext);
	}

	/**
	* Returns all the account links where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account links
	*/
	public static List<AccountLink> findByAccountEntryId(long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the account links where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @return the range of matching account links
	*/
	public static List<AccountLink> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account links where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account links
	*/
	public static List<AccountLink> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountLink> orderByComparator) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account links where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account links
	*/
	public static List<AccountLink> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account link
	* @throws NoSuchAccountLinkException if a matching account link could not be found
	*/
	public static AccountLink findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountLinkException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account link, or <code>null</code> if a matching account link could not be found
	*/
	public static AccountLink fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountLink> orderByComparator) {
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
	* @throws NoSuchAccountLinkException if a matching account link could not be found
	*/
	public static AccountLink findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountLinkException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account link, or <code>null</code> if a matching account link could not be found
	*/
	public static AccountLink fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountLink> orderByComparator) {
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
	* @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	*/
	public static AccountLink[] findByAccountEntryId_PrevAndNext(
		long accountLinkId, long accountEntryId,
		OrderByComparator<AccountLink> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountLinkException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(accountLinkId,
			accountEntryId, orderByComparator);
	}

	/**
	* Removes all the account links where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of account links where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account links
	*/
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Caches the account link in the entity cache if it is enabled.
	*
	* @param accountLink the account link
	*/
	public static void cacheResult(AccountLink accountLink) {
		getPersistence().cacheResult(accountLink);
	}

	/**
	* Caches the account links in the entity cache if it is enabled.
	*
	* @param accountLinks the account links
	*/
	public static void cacheResult(List<AccountLink> accountLinks) {
		getPersistence().cacheResult(accountLinks);
	}

	/**
	* Creates a new account link with the primary key. Does not add the account link to the database.
	*
	* @param accountLinkId the primary key for the new account link
	* @return the new account link
	*/
	public static AccountLink create(long accountLinkId) {
		return getPersistence().create(accountLinkId);
	}

	/**
	* Removes the account link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link that was removed
	* @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	*/
	public static AccountLink remove(long accountLinkId)
		throws com.liferay.osb.exception.NoSuchAccountLinkException {
		return getPersistence().remove(accountLinkId);
	}

	public static AccountLink updateImpl(AccountLink accountLink) {
		return getPersistence().updateImpl(accountLink);
	}

	/**
	* Returns the account link with the primary key or throws a {@link NoSuchAccountLinkException} if it could not be found.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link
	* @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	*/
	public static AccountLink findByPrimaryKey(long accountLinkId)
		throws com.liferay.osb.exception.NoSuchAccountLinkException {
		return getPersistence().findByPrimaryKey(accountLinkId);
	}

	/**
	* Returns the account link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link, or <code>null</code> if a account link with the primary key could not be found
	*/
	public static AccountLink fetchByPrimaryKey(long accountLinkId) {
		return getPersistence().fetchByPrimaryKey(accountLinkId);
	}

	public static java.util.Map<java.io.Serializable, AccountLink> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the account links.
	*
	* @return the account links
	*/
	public static List<AccountLink> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @return the range of account links
	*/
	public static List<AccountLink> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account links
	*/
	public static List<AccountLink> findAll(int start, int end,
		OrderByComparator<AccountLink> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account links
	*/
	public static List<AccountLink> findAll(int start, int end,
		OrderByComparator<AccountLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the account links from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account links.
	*
	* @return the number of account links
	*/
	public static int countAll() {
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

	private static AccountLinkPersistence _persistence;
}