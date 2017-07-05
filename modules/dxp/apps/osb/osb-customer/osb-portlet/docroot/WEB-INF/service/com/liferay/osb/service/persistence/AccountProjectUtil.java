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

import com.liferay.osb.model.AccountProject;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the account project service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AccountProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountProjectPersistence
 * @see com.liferay.osb.service.persistence.impl.AccountProjectPersistenceImpl
 * @generated
 */
@ProviderType
public class AccountProjectUtil {
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
	public static void clearCache(AccountProject accountProject) {
		getPersistence().clearCache(accountProject);
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
	public static List<AccountProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountProject> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountProject update(AccountProject accountProject) {
		return getPersistence().update(accountProject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountProject update(AccountProject accountProject,
		ServiceContext serviceContext) {
		return getPersistence().update(accountProject, serviceContext);
	}

	/**
	* Returns all the account projects where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account projects
	*/
	public static List<AccountProject> findByAccountEntryId(long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the account projects where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @return the range of matching account projects
	*/
	public static List<AccountProject> findByAccountEntryId(
		long accountEntryId, int start, int end) {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account projects where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account projects
	*/
	public static List<AccountProject> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<AccountProject> orderByComparator) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account projects where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account projects
	*/
	public static List<AccountProject> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<AccountProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account project in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account project
	* @throws NoSuchAccountProjectException if a matching account project could not be found
	*/
	public static AccountProject findByAccountEntryId_First(
		long accountEntryId, OrderByComparator<AccountProject> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountProjectException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first account project in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account project, or <code>null</code> if a matching account project could not be found
	*/
	public static AccountProject fetchByAccountEntryId_First(
		long accountEntryId, OrderByComparator<AccountProject> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account project in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account project
	* @throws NoSuchAccountProjectException if a matching account project could not be found
	*/
	public static AccountProject findByAccountEntryId_Last(
		long accountEntryId, OrderByComparator<AccountProject> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountProjectException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last account project in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account project, or <code>null</code> if a matching account project could not be found
	*/
	public static AccountProject fetchByAccountEntryId_Last(
		long accountEntryId, OrderByComparator<AccountProject> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the account projects before and after the current account project in the ordered set where accountEntryId = &#63;.
	*
	* @param accountProjectId the primary key of the current account project
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account project
	* @throws NoSuchAccountProjectException if a account project with the primary key could not be found
	*/
	public static AccountProject[] findByAccountEntryId_PrevAndNext(
		long accountProjectId, long accountEntryId,
		OrderByComparator<AccountProject> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountProjectException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(accountProjectId,
			accountEntryId, orderByComparator);
	}

	/**
	* Removes all the account projects where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of account projects where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account projects
	*/
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Caches the account project in the entity cache if it is enabled.
	*
	* @param accountProject the account project
	*/
	public static void cacheResult(AccountProject accountProject) {
		getPersistence().cacheResult(accountProject);
	}

	/**
	* Caches the account projects in the entity cache if it is enabled.
	*
	* @param accountProjects the account projects
	*/
	public static void cacheResult(List<AccountProject> accountProjects) {
		getPersistence().cacheResult(accountProjects);
	}

	/**
	* Creates a new account project with the primary key. Does not add the account project to the database.
	*
	* @param accountProjectId the primary key for the new account project
	* @return the new account project
	*/
	public static AccountProject create(long accountProjectId) {
		return getPersistence().create(accountProjectId);
	}

	/**
	* Removes the account project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project that was removed
	* @throws NoSuchAccountProjectException if a account project with the primary key could not be found
	*/
	public static AccountProject remove(long accountProjectId)
		throws com.liferay.osb.exception.NoSuchAccountProjectException {
		return getPersistence().remove(accountProjectId);
	}

	public static AccountProject updateImpl(AccountProject accountProject) {
		return getPersistence().updateImpl(accountProject);
	}

	/**
	* Returns the account project with the primary key or throws a {@link NoSuchAccountProjectException} if it could not be found.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project
	* @throws NoSuchAccountProjectException if a account project with the primary key could not be found
	*/
	public static AccountProject findByPrimaryKey(long accountProjectId)
		throws com.liferay.osb.exception.NoSuchAccountProjectException {
		return getPersistence().findByPrimaryKey(accountProjectId);
	}

	/**
	* Returns the account project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project, or <code>null</code> if a account project with the primary key could not be found
	*/
	public static AccountProject fetchByPrimaryKey(long accountProjectId) {
		return getPersistence().fetchByPrimaryKey(accountProjectId);
	}

	public static java.util.Map<java.io.Serializable, AccountProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the account projects.
	*
	* @return the account projects
	*/
	public static List<AccountProject> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @return the range of account projects
	*/
	public static List<AccountProject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account projects
	*/
	public static List<AccountProject> findAll(int start, int end,
		OrderByComparator<AccountProject> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account projects
	*/
	public static List<AccountProject> findAll(int start, int end,
		OrderByComparator<AccountProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the account projects from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account projects.
	*
	* @return the number of account projects
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AccountProjectPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountProjectPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AccountProjectPersistence.class.getName());

			ReferenceRegistry.registerReference(AccountProjectUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static AccountProjectPersistence _persistence;
}