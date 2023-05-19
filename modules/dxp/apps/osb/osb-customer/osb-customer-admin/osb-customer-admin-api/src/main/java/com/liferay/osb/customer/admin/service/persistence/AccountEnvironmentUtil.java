/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.admin.service.persistence;

import com.liferay.osb.customer.admin.model.AccountEnvironment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the account environment service. This utility wraps <code>com.liferay.osb.customer.admin.service.persistence.impl.AccountEnvironmentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentPersistence
 * @generated
 */
public class AccountEnvironmentUtil {

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
	public static void clearCache(AccountEnvironment accountEnvironment) {
		getPersistence().clearCache(accountEnvironment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, AccountEnvironment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccountEnvironment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountEnvironment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountEnvironment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountEnvironment update(
		AccountEnvironment accountEnvironment) {

		return getPersistence().update(accountEnvironment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountEnvironment update(
		AccountEnvironment accountEnvironment, ServiceContext serviceContext) {

		return getPersistence().update(accountEnvironment, serviceContext);
	}

	/**
	 * Returns all the account environments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account environments
	 */
	public static List<AccountEnvironment> findByAccountEntryId(
		long accountEntryId) {

		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns a range of all the account environments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @return the range of matching account environments
	 */
	public static List<AccountEnvironment> findByAccountEntryId(
		long accountEntryId, int start, int end) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the account environments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account environments
	 */
	public static List<AccountEnvironment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account environments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account environments
	 */
	public static List<AccountEnvironment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment
	 * @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	 */
	public static AccountEnvironment findByAccountEntryId_First(
			long accountEntryId,
			OrderByComparator<AccountEnvironment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentException {

		return getPersistence().findByAccountEntryId_First(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the first account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	public static AccountEnvironment fetchByAccountEntryId_First(
		long accountEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator) {

		return getPersistence().fetchByAccountEntryId_First(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment
	 * @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	 */
	public static AccountEnvironment findByAccountEntryId_Last(
			long accountEntryId,
			OrderByComparator<AccountEnvironment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentException {

		return getPersistence().findByAccountEntryId_Last(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	public static AccountEnvironment fetchByAccountEntryId_Last(
		long accountEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator) {

		return getPersistence().fetchByAccountEntryId_Last(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the account environments before and after the current account environment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEnvironmentId the primary key of the current account environment
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account environment
	 * @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 */
	public static AccountEnvironment[] findByAccountEntryId_PrevAndNext(
			long accountEnvironmentId, long accountEntryId,
			OrderByComparator<AccountEnvironment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentException {

		return getPersistence().findByAccountEntryId_PrevAndNext(
			accountEnvironmentId, accountEntryId, orderByComparator);
	}

	/**
	 * Removes all the account environments where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns the number of account environments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account environments
	 */
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @return the matching account environments
	 */
	public static List<AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId) {

		return getPersistence().findByAEI_PEI(accountEntryId, productEntryId);
	}

	/**
	 * Returns a range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @return the range of matching account environments
	 */
	public static List<AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId, int start, int end) {

		return getPersistence().findByAEI_PEI(
			accountEntryId, productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account environments
	 */
	public static List<AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId, int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator) {

		return getPersistence().findByAEI_PEI(
			accountEntryId, productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account environments
	 */
	public static List<AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId, int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAEI_PEI(
			accountEntryId, productEntryId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment
	 * @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	 */
	public static AccountEnvironment findByAEI_PEI_First(
			long accountEntryId, long productEntryId,
			OrderByComparator<AccountEnvironment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentException {

		return getPersistence().findByAEI_PEI_First(
			accountEntryId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the first account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	public static AccountEnvironment fetchByAEI_PEI_First(
		long accountEntryId, long productEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator) {

		return getPersistence().fetchByAEI_PEI_First(
			accountEntryId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment
	 * @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	 */
	public static AccountEnvironment findByAEI_PEI_Last(
			long accountEntryId, long productEntryId,
			OrderByComparator<AccountEnvironment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentException {

		return getPersistence().findByAEI_PEI_Last(
			accountEntryId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the last account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	public static AccountEnvironment fetchByAEI_PEI_Last(
		long accountEntryId, long productEntryId,
		OrderByComparator<AccountEnvironment> orderByComparator) {

		return getPersistence().fetchByAEI_PEI_Last(
			accountEntryId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the account environments before and after the current account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEnvironmentId the primary key of the current account environment
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account environment
	 * @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 */
	public static AccountEnvironment[] findByAEI_PEI_PrevAndNext(
			long accountEnvironmentId, long accountEntryId, long productEntryId,
			OrderByComparator<AccountEnvironment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentException {

		return getPersistence().findByAEI_PEI_PrevAndNext(
			accountEnvironmentId, accountEntryId, productEntryId,
			orderByComparator);
	}

	/**
	 * Removes all the account environments where accountEntryId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 */
	public static void removeByAEI_PEI(
		long accountEntryId, long productEntryId) {

		getPersistence().removeByAEI_PEI(accountEntryId, productEntryId);
	}

	/**
	 * Returns the number of account environments where accountEntryId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching account environments
	 */
	public static int countByAEI_PEI(long accountEntryId, long productEntryId) {
		return getPersistence().countByAEI_PEI(accountEntryId, productEntryId);
	}

	/**
	 * Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or throws a <code>NoSuchAccountEnvironmentException</code> if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the matching account environment
	 * @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	 */
	public static AccountEnvironment findByAEI_PEI_N(
			long accountEntryId, long productEntryId, String name)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentException {

		return getPersistence().findByAEI_PEI_N(
			accountEntryId, productEntryId, name);
	}

	/**
	 * Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	public static AccountEnvironment fetchByAEI_PEI_N(
		long accountEntryId, long productEntryId, String name) {

		return getPersistence().fetchByAEI_PEI_N(
			accountEntryId, productEntryId, name);
	}

	/**
	 * Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account environment, or <code>null</code> if a matching account environment could not be found
	 */
	public static AccountEnvironment fetchByAEI_PEI_N(
		long accountEntryId, long productEntryId, String name,
		boolean useFinderCache) {

		return getPersistence().fetchByAEI_PEI_N(
			accountEntryId, productEntryId, name, useFinderCache);
	}

	/**
	 * Removes the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the account environment that was removed
	 */
	public static AccountEnvironment removeByAEI_PEI_N(
			long accountEntryId, long productEntryId, String name)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentException {

		return getPersistence().removeByAEI_PEI_N(
			accountEntryId, productEntryId, name);
	}

	/**
	 * Returns the number of account environments where accountEntryId = &#63; and productEntryId = &#63; and name = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param productEntryId the product entry ID
	 * @param name the name
	 * @return the number of matching account environments
	 */
	public static int countByAEI_PEI_N(
		long accountEntryId, long productEntryId, String name) {

		return getPersistence().countByAEI_PEI_N(
			accountEntryId, productEntryId, name);
	}

	/**
	 * Caches the account environment in the entity cache if it is enabled.
	 *
	 * @param accountEnvironment the account environment
	 */
	public static void cacheResult(AccountEnvironment accountEnvironment) {
		getPersistence().cacheResult(accountEnvironment);
	}

	/**
	 * Caches the account environments in the entity cache if it is enabled.
	 *
	 * @param accountEnvironments the account environments
	 */
	public static void cacheResult(
		List<AccountEnvironment> accountEnvironments) {

		getPersistence().cacheResult(accountEnvironments);
	}

	/**
	 * Creates a new account environment with the primary key. Does not add the account environment to the database.
	 *
	 * @param accountEnvironmentId the primary key for the new account environment
	 * @return the new account environment
	 */
	public static AccountEnvironment create(long accountEnvironmentId) {
		return getPersistence().create(accountEnvironmentId);
	}

	/**
	 * Removes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment that was removed
	 * @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 */
	public static AccountEnvironment remove(long accountEnvironmentId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentException {

		return getPersistence().remove(accountEnvironmentId);
	}

	public static AccountEnvironment updateImpl(
		AccountEnvironment accountEnvironment) {

		return getPersistence().updateImpl(accountEnvironment);
	}

	/**
	 * Returns the account environment with the primary key or throws a <code>NoSuchAccountEnvironmentException</code> if it could not be found.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment
	 * @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	 */
	public static AccountEnvironment findByPrimaryKey(long accountEnvironmentId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentException {

		return getPersistence().findByPrimaryKey(accountEnvironmentId);
	}

	/**
	 * Returns the account environment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEnvironmentId the primary key of the account environment
	 * @return the account environment, or <code>null</code> if a account environment with the primary key could not be found
	 */
	public static AccountEnvironment fetchByPrimaryKey(
		long accountEnvironmentId) {

		return getPersistence().fetchByPrimaryKey(accountEnvironmentId);
	}

	/**
	 * Returns all the account environments.
	 *
	 * @return the account environments
	 */
	public static List<AccountEnvironment> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the account environments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @return the range of account environments
	 */
	public static List<AccountEnvironment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the account environments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account environments
	 */
	public static List<AccountEnvironment> findAll(
		int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account environments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environments
	 * @param end the upper bound of the range of account environments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of account environments
	 */
	public static List<AccountEnvironment> findAll(
		int start, int end,
		OrderByComparator<AccountEnvironment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the account environments from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of account environments.
	 *
	 * @return the number of account environments
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AccountEnvironmentPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		AccountEnvironmentPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile AccountEnvironmentPersistence _persistence;

}