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

import com.liferay.osb.testray.model.TestrayCaseResultWarning;
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
 * The persistence utility for the testray case result warning service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayCaseResultWarningPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultWarningPersistence
 * @generated
 */
public class TestrayCaseResultWarningUtil {

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
	public static void clearCache(
		TestrayCaseResultWarning testrayCaseResultWarning) {

		getPersistence().clearCache(testrayCaseResultWarning);
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
	public static Map<Serializable, TestrayCaseResultWarning>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayCaseResultWarning> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayCaseResultWarning> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayCaseResultWarning> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayCaseResultWarning update(
		TestrayCaseResultWarning testrayCaseResultWarning) {

		return getPersistence().update(testrayCaseResultWarning);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayCaseResultWarning update(
		TestrayCaseResultWarning testrayCaseResultWarning,
		ServiceContext serviceContext) {

		return getPersistence().update(
			testrayCaseResultWarning, serviceContext);
	}

	/**
	 * Returns all the testray case result warnings where testrayCaseResultId = &#63;.
	 *
	 * @param testrayCaseResultId the testray case result ID
	 * @return the matching testray case result warnings
	 */
	public static List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId) {

		return getPersistence().findByTestrayCaseResultId(testrayCaseResultId);
	}

	/**
	 * Returns a range of all the testray case result warnings where testrayCaseResultId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayCaseResultWarningModelImpl</code>.
	 * </p>
	 *
	 * @param testrayCaseResultId the testray case result ID
	 * @param start the lower bound of the range of testray case result warnings
	 * @param end the upper bound of the range of testray case result warnings (not inclusive)
	 * @return the range of matching testray case result warnings
	 */
	public static List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId, int start, int end) {

		return getPersistence().findByTestrayCaseResultId(
			testrayCaseResultId, start, end);
	}

	/**
	 * Returns an ordered range of all the testray case result warnings where testrayCaseResultId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayCaseResultWarningModelImpl</code>.
	 * </p>
	 *
	 * @param testrayCaseResultId the testray case result ID
	 * @param start the lower bound of the range of testray case result warnings
	 * @param end the upper bound of the range of testray case result warnings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case result warnings
	 */
	public static List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId, int start, int end,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator) {

		return getPersistence().findByTestrayCaseResultId(
			testrayCaseResultId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray case result warnings where testrayCaseResultId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayCaseResultWarningModelImpl</code>.
	 * </p>
	 *
	 * @param testrayCaseResultId the testray case result ID
	 * @param start the lower bound of the range of testray case result warnings
	 * @param end the upper bound of the range of testray case result warnings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching testray case result warnings
	 */
	public static List<TestrayCaseResultWarning> findByTestrayCaseResultId(
		long testrayCaseResultId, int start, int end,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByTestrayCaseResultId(
			testrayCaseResultId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	 *
	 * @param testrayCaseResultId the testray case result ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result warning
	 * @throws NoSuchTestrayCaseResultWarningException if a matching testray case result warning could not be found
	 */
	public static TestrayCaseResultWarning findByTestrayCaseResultId_First(
			long testrayCaseResultId,
			OrderByComparator<TestrayCaseResultWarning> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultWarningException {

		return getPersistence().findByTestrayCaseResultId_First(
			testrayCaseResultId, orderByComparator);
	}

	/**
	 * Returns the first testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	 *
	 * @param testrayCaseResultId the testray case result ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result warning, or <code>null</code> if a matching testray case result warning could not be found
	 */
	public static TestrayCaseResultWarning fetchByTestrayCaseResultId_First(
		long testrayCaseResultId,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator) {

		return getPersistence().fetchByTestrayCaseResultId_First(
			testrayCaseResultId, orderByComparator);
	}

	/**
	 * Returns the last testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	 *
	 * @param testrayCaseResultId the testray case result ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result warning
	 * @throws NoSuchTestrayCaseResultWarningException if a matching testray case result warning could not be found
	 */
	public static TestrayCaseResultWarning findByTestrayCaseResultId_Last(
			long testrayCaseResultId,
			OrderByComparator<TestrayCaseResultWarning> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultWarningException {

		return getPersistence().findByTestrayCaseResultId_Last(
			testrayCaseResultId, orderByComparator);
	}

	/**
	 * Returns the last testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	 *
	 * @param testrayCaseResultId the testray case result ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result warning, or <code>null</code> if a matching testray case result warning could not be found
	 */
	public static TestrayCaseResultWarning fetchByTestrayCaseResultId_Last(
		long testrayCaseResultId,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator) {

		return getPersistence().fetchByTestrayCaseResultId_Last(
			testrayCaseResultId, orderByComparator);
	}

	/**
	 * Returns the testray case result warnings before and after the current testray case result warning in the ordered set where testrayCaseResultId = &#63;.
	 *
	 * @param testrayCaseResultWarningId the primary key of the current testray case result warning
	 * @param testrayCaseResultId the testray case result ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result warning
	 * @throws NoSuchTestrayCaseResultWarningException if a testray case result warning with the primary key could not be found
	 */
	public static TestrayCaseResultWarning[]
			findByTestrayCaseResultId_PrevAndNext(
				long testrayCaseResultWarningId, long testrayCaseResultId,
				OrderByComparator<TestrayCaseResultWarning> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultWarningException {

		return getPersistence().findByTestrayCaseResultId_PrevAndNext(
			testrayCaseResultWarningId, testrayCaseResultId, orderByComparator);
	}

	/**
	 * Removes all the testray case result warnings where testrayCaseResultId = &#63; from the database.
	 *
	 * @param testrayCaseResultId the testray case result ID
	 */
	public static void removeByTestrayCaseResultId(long testrayCaseResultId) {
		getPersistence().removeByTestrayCaseResultId(testrayCaseResultId);
	}

	/**
	 * Returns the number of testray case result warnings where testrayCaseResultId = &#63;.
	 *
	 * @param testrayCaseResultId the testray case result ID
	 * @return the number of matching testray case result warnings
	 */
	public static int countByTestrayCaseResultId(long testrayCaseResultId) {
		return getPersistence().countByTestrayCaseResultId(testrayCaseResultId);
	}

	/**
	 * Caches the testray case result warning in the entity cache if it is enabled.
	 *
	 * @param testrayCaseResultWarning the testray case result warning
	 */
	public static void cacheResult(
		TestrayCaseResultWarning testrayCaseResultWarning) {

		getPersistence().cacheResult(testrayCaseResultWarning);
	}

	/**
	 * Caches the testray case result warnings in the entity cache if it is enabled.
	 *
	 * @param testrayCaseResultWarnings the testray case result warnings
	 */
	public static void cacheResult(
		List<TestrayCaseResultWarning> testrayCaseResultWarnings) {

		getPersistence().cacheResult(testrayCaseResultWarnings);
	}

	/**
	 * Creates a new testray case result warning with the primary key. Does not add the testray case result warning to the database.
	 *
	 * @param testrayCaseResultWarningId the primary key for the new testray case result warning
	 * @return the new testray case result warning
	 */
	public static TestrayCaseResultWarning create(
		long testrayCaseResultWarningId) {

		return getPersistence().create(testrayCaseResultWarningId);
	}

	/**
	 * Removes the testray case result warning with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResultWarningId the primary key of the testray case result warning
	 * @return the testray case result warning that was removed
	 * @throws NoSuchTestrayCaseResultWarningException if a testray case result warning with the primary key could not be found
	 */
	public static TestrayCaseResultWarning remove(
			long testrayCaseResultWarningId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultWarningException {

		return getPersistence().remove(testrayCaseResultWarningId);
	}

	public static TestrayCaseResultWarning updateImpl(
		TestrayCaseResultWarning testrayCaseResultWarning) {

		return getPersistence().updateImpl(testrayCaseResultWarning);
	}

	/**
	 * Returns the testray case result warning with the primary key or throws a <code>NoSuchTestrayCaseResultWarningException</code> if it could not be found.
	 *
	 * @param testrayCaseResultWarningId the primary key of the testray case result warning
	 * @return the testray case result warning
	 * @throws NoSuchTestrayCaseResultWarningException if a testray case result warning with the primary key could not be found
	 */
	public static TestrayCaseResultWarning findByPrimaryKey(
			long testrayCaseResultWarningId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultWarningException {

		return getPersistence().findByPrimaryKey(testrayCaseResultWarningId);
	}

	/**
	 * Returns the testray case result warning with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayCaseResultWarningId the primary key of the testray case result warning
	 * @return the testray case result warning, or <code>null</code> if a testray case result warning with the primary key could not be found
	 */
	public static TestrayCaseResultWarning fetchByPrimaryKey(
		long testrayCaseResultWarningId) {

		return getPersistence().fetchByPrimaryKey(testrayCaseResultWarningId);
	}

	/**
	 * Returns all the testray case result warnings.
	 *
	 * @return the testray case result warnings
	 */
	public static List<TestrayCaseResultWarning> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray case result warnings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayCaseResultWarningModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case result warnings
	 * @param end the upper bound of the range of testray case result warnings (not inclusive)
	 * @return the range of testray case result warnings
	 */
	public static List<TestrayCaseResultWarning> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray case result warnings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayCaseResultWarningModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case result warnings
	 * @param end the upper bound of the range of testray case result warnings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray case result warnings
	 */
	public static List<TestrayCaseResultWarning> findAll(
		int start, int end,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray case result warnings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayCaseResultWarningModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case result warnings
	 * @param end the upper bound of the range of testray case result warnings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray case result warnings
	 */
	public static List<TestrayCaseResultWarning> findAll(
		int start, int end,
		OrderByComparator<TestrayCaseResultWarning> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the testray case result warnings from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray case result warnings.
	 *
	 * @return the number of testray case result warnings
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestrayCaseResultWarningPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayCaseResultWarningPersistence,
		 TestrayCaseResultWarningPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayCaseResultWarningPersistence.class);

		ServiceTracker
			<TestrayCaseResultWarningPersistence,
			 TestrayCaseResultWarningPersistence> serviceTracker =
				new ServiceTracker
					<TestrayCaseResultWarningPersistence,
					 TestrayCaseResultWarningPersistence>(
						 bundle.getBundleContext(),
						 TestrayCaseResultWarningPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}