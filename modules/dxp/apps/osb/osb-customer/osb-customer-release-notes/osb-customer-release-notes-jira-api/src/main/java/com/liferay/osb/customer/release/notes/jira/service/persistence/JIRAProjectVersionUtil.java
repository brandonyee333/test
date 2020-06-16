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

package com.liferay.osb.customer.release.notes.jira.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the jira project version service. This utility wraps {@link com.liferay.osb.customer.release.notes.jira.service.persistence.impl.JIRAProjectVersionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectVersionPersistence
 * @see com.liferay.osb.customer.release.notes.jira.service.persistence.impl.JIRAProjectVersionPersistenceImpl
 * @generated
 */
@ProviderType
public class JIRAProjectVersionUtil {
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
	public static void clearCache(JIRAProjectVersion jiraProjectVersion) {
		getPersistence().clearCache(jiraProjectVersion);
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
	public static List<JIRAProjectVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAProjectVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAProjectVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<JIRAProjectVersion> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static JIRAProjectVersion update(
		JIRAProjectVersion jiraProjectVersion) {
		return getPersistence().update(jiraProjectVersion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static JIRAProjectVersion update(
		JIRAProjectVersion jiraProjectVersion, ServiceContext serviceContext) {
		return getPersistence().update(jiraProjectVersion, serviceContext);
	}

	/**
	* Caches the jira project version in the entity cache if it is enabled.
	*
	* @param jiraProjectVersion the jira project version
	*/
	public static void cacheResult(JIRAProjectVersion jiraProjectVersion) {
		getPersistence().cacheResult(jiraProjectVersion);
	}

	/**
	* Caches the jira project versions in the entity cache if it is enabled.
	*
	* @param jiraProjectVersions the jira project versions
	*/
	public static void cacheResult(List<JIRAProjectVersion> jiraProjectVersions) {
		getPersistence().cacheResult(jiraProjectVersions);
	}

	/**
	* Creates a new jira project version with the primary key. Does not add the jira project version to the database.
	*
	* @param jiraProjectVersionId the primary key for the new jira project version
	* @return the new jira project version
	*/
	public static JIRAProjectVersion create(long jiraProjectVersionId) {
		return getPersistence().create(jiraProjectVersionId);
	}

	/**
	* Removes the jira project version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProjectVersionId the primary key of the jira project version
	* @return the jira project version that was removed
	* @throws NoSuchJIRAProjectVersionException if a jira project version with the primary key could not be found
	*/
	public static JIRAProjectVersion remove(long jiraProjectVersionId)
		throws com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAProjectVersionException {
		return getPersistence().remove(jiraProjectVersionId);
	}

	public static JIRAProjectVersion updateImpl(
		JIRAProjectVersion jiraProjectVersion) {
		return getPersistence().updateImpl(jiraProjectVersion);
	}

	/**
	* Returns the jira project version with the primary key or throws a {@link NoSuchJIRAProjectVersionException} if it could not be found.
	*
	* @param jiraProjectVersionId the primary key of the jira project version
	* @return the jira project version
	* @throws NoSuchJIRAProjectVersionException if a jira project version with the primary key could not be found
	*/
	public static JIRAProjectVersion findByPrimaryKey(long jiraProjectVersionId)
		throws com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAProjectVersionException {
		return getPersistence().findByPrimaryKey(jiraProjectVersionId);
	}

	/**
	* Returns the jira project version with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraProjectVersionId the primary key of the jira project version
	* @return the jira project version, or <code>null</code> if a jira project version with the primary key could not be found
	*/
	public static JIRAProjectVersion fetchByPrimaryKey(
		long jiraProjectVersionId) {
		return getPersistence().fetchByPrimaryKey(jiraProjectVersionId);
	}

	public static java.util.Map<java.io.Serializable, JIRAProjectVersion> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the jira project versions.
	*
	* @return the jira project versions
	*/
	public static List<JIRAProjectVersion> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the jira project versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira project versions
	* @param end the upper bound of the range of jira project versions (not inclusive)
	* @return the range of jira project versions
	*/
	public static List<JIRAProjectVersion> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the jira project versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira project versions
	* @param end the upper bound of the range of jira project versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of jira project versions
	*/
	public static List<JIRAProjectVersion> findAll(int start, int end,
		OrderByComparator<JIRAProjectVersion> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the jira project versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira project versions
	* @param end the upper bound of the range of jira project versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of jira project versions
	*/
	public static List<JIRAProjectVersion> findAll(int start, int end,
		OrderByComparator<JIRAProjectVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the jira project versions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of jira project versions.
	*
	* @return the number of jira project versions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static JIRAProjectVersionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JIRAProjectVersionPersistence, JIRAProjectVersionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(JIRAProjectVersionPersistence.class);
}