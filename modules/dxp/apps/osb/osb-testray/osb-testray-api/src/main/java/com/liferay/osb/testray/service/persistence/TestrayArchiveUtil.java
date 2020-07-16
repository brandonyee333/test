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

package com.liferay.osb.testray.service.persistence;

import com.liferay.osb.testray.model.TestrayArchive;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the testray archive service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayArchivePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayArchivePersistence
 * @generated
 */
public class TestrayArchiveUtil {

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
	public static void clearCache(TestrayArchive testrayArchive) {
		getPersistence().clearCache(testrayArchive);
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
	public static Map<Serializable, TestrayArchive> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayArchive> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayArchive> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayArchive> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayArchive> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayArchive update(TestrayArchive testrayArchive) {
		return getPersistence().update(testrayArchive);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayArchive update(
		TestrayArchive testrayArchive, ServiceContext serviceContext) {

		return getPersistence().update(testrayArchive, serviceContext);
	}

	/**
	 * Caches the testray archive in the entity cache if it is enabled.
	 *
	 * @param testrayArchive the testray archive
	 */
	public static void cacheResult(TestrayArchive testrayArchive) {
		getPersistence().cacheResult(testrayArchive);
	}

	/**
	 * Caches the testray archives in the entity cache if it is enabled.
	 *
	 * @param testrayArchives the testray archives
	 */
	public static void cacheResult(List<TestrayArchive> testrayArchives) {
		getPersistence().cacheResult(testrayArchives);
	}

	/**
	 * Creates a new testray archive with the primary key. Does not add the testray archive to the database.
	 *
	 * @param testrayArchiveId the primary key for the new testray archive
	 * @return the new testray archive
	 */
	public static TestrayArchive create(long testrayArchiveId) {
		return getPersistence().create(testrayArchiveId);
	}

	/**
	 * Removes the testray archive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive that was removed
	 * @throws NoSuchTestrayArchiveException if a testray archive with the primary key could not be found
	 */
	public static TestrayArchive remove(long testrayArchiveId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayArchiveException {

		return getPersistence().remove(testrayArchiveId);
	}

	public static TestrayArchive updateImpl(TestrayArchive testrayArchive) {
		return getPersistence().updateImpl(testrayArchive);
	}

	/**
	 * Returns the testray archive with the primary key or throws a <code>NoSuchTestrayArchiveException</code> if it could not be found.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive
	 * @throws NoSuchTestrayArchiveException if a testray archive with the primary key could not be found
	 */
	public static TestrayArchive findByPrimaryKey(long testrayArchiveId)
		throws com.liferay.osb.testray.exception.NoSuchTestrayArchiveException {

		return getPersistence().findByPrimaryKey(testrayArchiveId);
	}

	/**
	 * Returns the testray archive with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive, or <code>null</code> if a testray archive with the primary key could not be found
	 */
	public static TestrayArchive fetchByPrimaryKey(long testrayArchiveId) {
		return getPersistence().fetchByPrimaryKey(testrayArchiveId);
	}

	/**
	 * Returns all the testray archives.
	 *
	 * @return the testray archives
	 */
	public static List<TestrayArchive> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray archives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayArchiveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray archives
	 * @param end the upper bound of the range of testray archives (not inclusive)
	 * @return the range of testray archives
	 */
	public static List<TestrayArchive> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray archives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayArchiveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray archives
	 * @param end the upper bound of the range of testray archives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray archives
	 */
	public static List<TestrayArchive> findAll(
		int start, int end,
		OrderByComparator<TestrayArchive> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray archives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayArchiveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray archives
	 * @param end the upper bound of the range of testray archives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray archives
	 */
	public static List<TestrayArchive> findAll(
		int start, int end, OrderByComparator<TestrayArchive> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the testray archives from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray archives.
	 *
	 * @return the number of testray archives
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayArchivePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayArchivePersistence, TestrayArchivePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayArchivePersistence.class);

		ServiceTracker<TestrayArchivePersistence, TestrayArchivePersistence>
			serviceTracker =
				new ServiceTracker
					<TestrayArchivePersistence, TestrayArchivePersistence>(
						bundle.getBundleContext(),
						TestrayArchivePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}