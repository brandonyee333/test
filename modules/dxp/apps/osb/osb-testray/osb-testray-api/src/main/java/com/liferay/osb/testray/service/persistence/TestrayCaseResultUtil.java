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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.model.TestrayCaseResult;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the testray case result service. This utility wraps <code>com.liferay.osb.testray.service.persistence.impl.TestrayCaseResultPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultPersistence
 * @generated
 */
@ProviderType
public class TestrayCaseResultUtil {

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
	public static void clearCache(TestrayCaseResult testrayCaseResult) {
		getPersistence().clearCache(testrayCaseResult);
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
	public static Map<Serializable, TestrayCaseResult> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestrayCaseResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestrayCaseResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestrayCaseResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestrayCaseResult update(
		TestrayCaseResult testrayCaseResult) {

		return getPersistence().update(testrayCaseResult);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestrayCaseResult update(
		TestrayCaseResult testrayCaseResult, ServiceContext serviceContext) {

		return getPersistence().update(testrayCaseResult, serviceContext);
	}

	/**
	 * Returns all the testray case results where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching testray case results
	 */
	public static List<TestrayCaseResult> findByCreateDate(Date createDate) {
		return getPersistence().findByCreateDate(createDate);
	}

	/**
	 * Returns a range of all the testray case results where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByCreateDate(
		Date createDate, int start, int end) {

		return getPersistence().findByCreateDate(createDate, start, end);
	}

	/**
	 * Returns an ordered range of all the testray case results where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByCreateDate(
		Date createDate, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().findByCreateDate(
			createDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray case results where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByCreateDate(
		Date createDate, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByCreateDate(
			createDate, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first testray case result in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByCreateDate_First(
			Date createDate,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByCreateDate_First(
			createDate, orderByComparator);
	}

	/**
	 * Returns the first testray case result in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByCreateDate_First(
		Date createDate,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByCreateDate_First(
			createDate, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByCreateDate_Last(
			Date createDate,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByCreateDate_Last(
			createDate, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByCreateDate_Last(
		Date createDate,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByCreateDate_Last(
			createDate, orderByComparator);
	}

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where createDate = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public static TestrayCaseResult[] findByCreateDate_PrevAndNext(
			long testrayCaseResultId, Date createDate,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByCreateDate_PrevAndNext(
			testrayCaseResultId, createDate, orderByComparator);
	}

	/**
	 * Removes all the testray case results where createDate = &#63; from the database.
	 *
	 * @param createDate the create date
	 */
	public static void removeByCreateDate(Date createDate) {
		getPersistence().removeByCreateDate(createDate);
	}

	/**
	 * Returns the number of testray case results where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching testray case results
	 */
	public static int countByCreateDate(Date createDate) {
		return getPersistence().countByCreateDate(createDate);
	}

	/**
	 * Returns all the testray case results where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @return the matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayBuildId(
		long testrayBuildId) {

		return getPersistence().findByTestrayBuildId(testrayBuildId);
	}

	/**
	 * Returns a range of all the testray case results where testrayBuildId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayBuildId the testray build ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayBuildId(
		long testrayBuildId, int start, int end) {

		return getPersistence().findByTestrayBuildId(
			testrayBuildId, start, end);
	}

	/**
	 * Returns an ordered range of all the testray case results where testrayBuildId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayBuildId the testray build ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayBuildId(
		long testrayBuildId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().findByTestrayBuildId(
			testrayBuildId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray case results where testrayBuildId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayBuildId the testray build ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayBuildId(
		long testrayBuildId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByTestrayBuildId(
			testrayBuildId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first testray case result in the ordered set where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTestrayBuildId_First(
			long testrayBuildId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayBuildId_First(
			testrayBuildId, orderByComparator);
	}

	/**
	 * Returns the first testray case result in the ordered set where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTestrayBuildId_First(
		long testrayBuildId,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByTestrayBuildId_First(
			testrayBuildId, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTestrayBuildId_Last(
			long testrayBuildId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayBuildId_Last(
			testrayBuildId, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTestrayBuildId_Last(
		long testrayBuildId,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByTestrayBuildId_Last(
			testrayBuildId, orderByComparator);
	}

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where testrayBuildId = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param testrayBuildId the testray build ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public static TestrayCaseResult[] findByTestrayBuildId_PrevAndNext(
			long testrayCaseResultId, long testrayBuildId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayBuildId_PrevAndNext(
			testrayCaseResultId, testrayBuildId, orderByComparator);
	}

	/**
	 * Removes all the testray case results where testrayBuildId = &#63; from the database.
	 *
	 * @param testrayBuildId the testray build ID
	 */
	public static void removeByTestrayBuildId(long testrayBuildId) {
		getPersistence().removeByTestrayBuildId(testrayBuildId);
	}

	/**
	 * Returns the number of testray case results where testrayBuildId = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @return the number of matching testray case results
	 */
	public static int countByTestrayBuildId(long testrayBuildId) {
		return getPersistence().countByTestrayBuildId(testrayBuildId);
	}

	/**
	 * Returns all the testray case results where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @return the matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayCaseId(
		long testrayCaseId) {

		return getPersistence().findByTestrayCaseId(testrayCaseId);
	}

	/**
	 * Returns a range of all the testray case results where testrayCaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayCaseId the testray case ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayCaseId(
		long testrayCaseId, int start, int end) {

		return getPersistence().findByTestrayCaseId(testrayCaseId, start, end);
	}

	/**
	 * Returns an ordered range of all the testray case results where testrayCaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayCaseId the testray case ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayCaseId(
		long testrayCaseId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().findByTestrayCaseId(
			testrayCaseId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray case results where testrayCaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayCaseId the testray case ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayCaseId(
		long testrayCaseId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByTestrayCaseId(
			testrayCaseId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first testray case result in the ordered set where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTestrayCaseId_First(
			long testrayCaseId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayCaseId_First(
			testrayCaseId, orderByComparator);
	}

	/**
	 * Returns the first testray case result in the ordered set where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTestrayCaseId_First(
		long testrayCaseId,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByTestrayCaseId_First(
			testrayCaseId, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTestrayCaseId_Last(
			long testrayCaseId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayCaseId_Last(
			testrayCaseId, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTestrayCaseId_Last(
		long testrayCaseId,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByTestrayCaseId_Last(
			testrayCaseId, orderByComparator);
	}

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param testrayCaseId the testray case ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public static TestrayCaseResult[] findByTestrayCaseId_PrevAndNext(
			long testrayCaseResultId, long testrayCaseId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayCaseId_PrevAndNext(
			testrayCaseResultId, testrayCaseId, orderByComparator);
	}

	/**
	 * Removes all the testray case results where testrayCaseId = &#63; from the database.
	 *
	 * @param testrayCaseId the testray case ID
	 */
	public static void removeByTestrayCaseId(long testrayCaseId) {
		getPersistence().removeByTestrayCaseId(testrayCaseId);
	}

	/**
	 * Returns the number of testray case results where testrayCaseId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @return the number of matching testray case results
	 */
	public static int countByTestrayCaseId(long testrayCaseId) {
		return getPersistence().countByTestrayCaseId(testrayCaseId);
	}

	/**
	 * Returns all the testray case results where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @return the matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayComponentId(
		long testrayComponentId) {

		return getPersistence().findByTestrayComponentId(testrayComponentId);
	}

	/**
	 * Returns a range of all the testray case results where testrayComponentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayComponentId(
		long testrayComponentId, int start, int end) {

		return getPersistence().findByTestrayComponentId(
			testrayComponentId, start, end);
	}

	/**
	 * Returns an ordered range of all the testray case results where testrayComponentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayComponentId(
		long testrayComponentId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().findByTestrayComponentId(
			testrayComponentId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray case results where testrayComponentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayComponentId(
		long testrayComponentId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByTestrayComponentId(
			testrayComponentId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first testray case result in the ordered set where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTestrayComponentId_First(
			long testrayComponentId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayComponentId_First(
			testrayComponentId, orderByComparator);
	}

	/**
	 * Returns the first testray case result in the ordered set where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTestrayComponentId_First(
		long testrayComponentId,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByTestrayComponentId_First(
			testrayComponentId, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTestrayComponentId_Last(
			long testrayComponentId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayComponentId_Last(
			testrayComponentId, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTestrayComponentId_Last(
		long testrayComponentId,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByTestrayComponentId_Last(
			testrayComponentId, orderByComparator);
	}

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where testrayComponentId = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param testrayComponentId the testray component ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public static TestrayCaseResult[] findByTestrayComponentId_PrevAndNext(
			long testrayCaseResultId, long testrayComponentId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayComponentId_PrevAndNext(
			testrayCaseResultId, testrayComponentId, orderByComparator);
	}

	/**
	 * Removes all the testray case results where testrayComponentId = &#63; from the database.
	 *
	 * @param testrayComponentId the testray component ID
	 */
	public static void removeByTestrayComponentId(long testrayComponentId) {
		getPersistence().removeByTestrayComponentId(testrayComponentId);
	}

	/**
	 * Returns the number of testray case results where testrayComponentId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @return the number of matching testray case results
	 */
	public static int countByTestrayComponentId(long testrayComponentId) {
		return getPersistence().countByTestrayComponentId(testrayComponentId);
	}

	/**
	 * Returns all the testray case results where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @return the matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayRunId(
		long testrayRunId) {

		return getPersistence().findByTestrayRunId(testrayRunId);
	}

	/**
	 * Returns a range of all the testray case results where testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayRunId(
		long testrayRunId, int start, int end) {

		return getPersistence().findByTestrayRunId(testrayRunId, start, end);
	}

	/**
	 * Returns an ordered range of all the testray case results where testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayRunId(
		long testrayRunId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().findByTestrayRunId(
			testrayRunId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray case results where testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTestrayRunId(
		long testrayRunId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByTestrayRunId(
			testrayRunId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first testray case result in the ordered set where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTestrayRunId_First(
			long testrayRunId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayRunId_First(
			testrayRunId, orderByComparator);
	}

	/**
	 * Returns the first testray case result in the ordered set where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTestrayRunId_First(
		long testrayRunId,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByTestrayRunId_First(
			testrayRunId, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTestrayRunId_Last(
			long testrayRunId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayRunId_Last(
			testrayRunId, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTestrayRunId_Last(
		long testrayRunId,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByTestrayRunId_Last(
			testrayRunId, orderByComparator);
	}

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where testrayRunId = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public static TestrayCaseResult[] findByTestrayRunId_PrevAndNext(
			long testrayCaseResultId, long testrayRunId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTestrayRunId_PrevAndNext(
			testrayCaseResultId, testrayRunId, orderByComparator);
	}

	/**
	 * Removes all the testray case results where testrayRunId = &#63; from the database.
	 *
	 * @param testrayRunId the testray run ID
	 */
	public static void removeByTestrayRunId(long testrayRunId) {
		getPersistence().removeByTestrayRunId(testrayRunId);
	}

	/**
	 * Returns the number of testray case results where testrayRunId = &#63;.
	 *
	 * @param testrayRunId the testray run ID
	 * @return the number of matching testray case results
	 */
	public static int countByTestrayRunId(long testrayRunId) {
		return getPersistence().countByTestrayRunId(testrayRunId);
	}

	/**
	 * Returns all the testray case results where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @return the matching testray case results
	 */
	public static List<TestrayCaseResult> findByTCI_TRI(
		long testrayComponentId, long testrayRunId) {

		return getPersistence().findByTCI_TRI(testrayComponentId, testrayRunId);
	}

	/**
	 * Returns a range of all the testray case results where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTCI_TRI(
		long testrayComponentId, long testrayRunId, int start, int end) {

		return getPersistence().findByTCI_TRI(
			testrayComponentId, testrayRunId, start, end);
	}

	/**
	 * Returns an ordered range of all the testray case results where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTCI_TRI(
		long testrayComponentId, long testrayRunId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().findByTCI_TRI(
			testrayComponentId, testrayRunId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray case results where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching testray case results
	 */
	public static List<TestrayCaseResult> findByTCI_TRI(
		long testrayComponentId, long testrayRunId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByTCI_TRI(
			testrayComponentId, testrayRunId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first testray case result in the ordered set where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTCI_TRI_First(
			long testrayComponentId, long testrayRunId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTCI_TRI_First(
			testrayComponentId, testrayRunId, orderByComparator);
	}

	/**
	 * Returns the first testray case result in the ordered set where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTCI_TRI_First(
		long testrayComponentId, long testrayRunId,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByTCI_TRI_First(
			testrayComponentId, testrayRunId, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTCI_TRI_Last(
			long testrayComponentId, long testrayRunId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTCI_TRI_Last(
			testrayComponentId, testrayRunId, orderByComparator);
	}

	/**
	 * Returns the last testray case result in the ordered set where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTCI_TRI_Last(
		long testrayComponentId, long testrayRunId,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().fetchByTCI_TRI_Last(
			testrayComponentId, testrayRunId, orderByComparator);
	}

	/**
	 * Returns the testray case results before and after the current testray case result in the ordered set where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayCaseResultId the primary key of the current testray case result
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public static TestrayCaseResult[] findByTCI_TRI_PrevAndNext(
			long testrayCaseResultId, long testrayComponentId,
			long testrayRunId,
			OrderByComparator<TestrayCaseResult> orderByComparator)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTCI_TRI_PrevAndNext(
			testrayCaseResultId, testrayComponentId, testrayRunId,
			orderByComparator);
	}

	/**
	 * Removes all the testray case results where testrayComponentId = &#63; and testrayRunId = &#63; from the database.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 */
	public static void removeByTCI_TRI(
		long testrayComponentId, long testrayRunId) {

		getPersistence().removeByTCI_TRI(testrayComponentId, testrayRunId);
	}

	/**
	 * Returns the number of testray case results where testrayComponentId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayComponentId the testray component ID
	 * @param testrayRunId the testray run ID
	 * @return the number of matching testray case results
	 */
	public static int countByTCI_TRI(
		long testrayComponentId, long testrayRunId) {

		return getPersistence().countByTCI_TRI(
			testrayComponentId, testrayRunId);
	}

	/**
	 * Returns the testray case result where testrayCaseId = &#63; and testrayRunId = &#63; or throws a <code>NoSuchTestrayCaseResultException</code> if it could not be found.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param testrayRunId the testray run ID
	 * @return the matching testray case result
	 * @throws NoSuchTestrayCaseResultException if a matching testray case result could not be found
	 */
	public static TestrayCaseResult findByTCaI_TRI(
			long testrayCaseId, long testrayRunId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByTCaI_TRI(testrayCaseId, testrayRunId);
	}

	/**
	 * Returns the testray case result where testrayCaseId = &#63; and testrayRunId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param testrayRunId the testray run ID
	 * @return the matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTCaI_TRI(
		long testrayCaseId, long testrayRunId) {

		return getPersistence().fetchByTCaI_TRI(testrayCaseId, testrayRunId);
	}

	/**
	 * Returns the testray case result where testrayCaseId = &#63; and testrayRunId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param testrayRunId the testray run ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray case result, or <code>null</code> if a matching testray case result could not be found
	 */
	public static TestrayCaseResult fetchByTCaI_TRI(
		long testrayCaseId, long testrayRunId, boolean retrieveFromCache) {

		return getPersistence().fetchByTCaI_TRI(
			testrayCaseId, testrayRunId, retrieveFromCache);
	}

	/**
	 * Removes the testray case result where testrayCaseId = &#63; and testrayRunId = &#63; from the database.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param testrayRunId the testray run ID
	 * @return the testray case result that was removed
	 */
	public static TestrayCaseResult removeByTCaI_TRI(
			long testrayCaseId, long testrayRunId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().removeByTCaI_TRI(testrayCaseId, testrayRunId);
	}

	/**
	 * Returns the number of testray case results where testrayCaseId = &#63; and testrayRunId = &#63;.
	 *
	 * @param testrayCaseId the testray case ID
	 * @param testrayRunId the testray run ID
	 * @return the number of matching testray case results
	 */
	public static int countByTCaI_TRI(long testrayCaseId, long testrayRunId) {
		return getPersistence().countByTCaI_TRI(testrayCaseId, testrayRunId);
	}

	/**
	 * Caches the testray case result in the entity cache if it is enabled.
	 *
	 * @param testrayCaseResult the testray case result
	 */
	public static void cacheResult(TestrayCaseResult testrayCaseResult) {
		getPersistence().cacheResult(testrayCaseResult);
	}

	/**
	 * Caches the testray case results in the entity cache if it is enabled.
	 *
	 * @param testrayCaseResults the testray case results
	 */
	public static void cacheResult(List<TestrayCaseResult> testrayCaseResults) {
		getPersistence().cacheResult(testrayCaseResults);
	}

	/**
	 * Creates a new testray case result with the primary key. Does not add the testray case result to the database.
	 *
	 * @param testrayCaseResultId the primary key for the new testray case result
	 * @return the new testray case result
	 */
	public static TestrayCaseResult create(long testrayCaseResultId) {
		return getPersistence().create(testrayCaseResultId);
	}

	/**
	 * Removes the testray case result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result that was removed
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public static TestrayCaseResult remove(long testrayCaseResultId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().remove(testrayCaseResultId);
	}

	public static TestrayCaseResult updateImpl(
		TestrayCaseResult testrayCaseResult) {

		return getPersistence().updateImpl(testrayCaseResult);
	}

	/**
	 * Returns the testray case result with the primary key or throws a <code>NoSuchTestrayCaseResultException</code> if it could not be found.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result
	 * @throws NoSuchTestrayCaseResultException if a testray case result with the primary key could not be found
	 */
	public static TestrayCaseResult findByPrimaryKey(long testrayCaseResultId)
		throws com.liferay.osb.testray.exception.
			NoSuchTestrayCaseResultException {

		return getPersistence().findByPrimaryKey(testrayCaseResultId);
	}

	/**
	 * Returns the testray case result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result, or <code>null</code> if a testray case result with the primary key could not be found
	 */
	public static TestrayCaseResult fetchByPrimaryKey(
		long testrayCaseResultId) {

		return getPersistence().fetchByPrimaryKey(testrayCaseResultId);
	}

	/**
	 * Returns all the testray case results.
	 *
	 * @return the testray case results
	 */
	public static List<TestrayCaseResult> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the testray case results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of testray case results
	 */
	public static List<TestrayCaseResult> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the testray case results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray case results
	 */
	public static List<TestrayCaseResult> findAll(
		int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the testray case results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray case results
	 */
	public static List<TestrayCaseResult> findAll(
		int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the testray case results from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of testray case results.
	 *
	 * @return the number of testray case results
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of testray issues associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return long[] of the primaryKeys of testray issues associated with the testray case result
	 */
	public static long[] getTestrayIssuePrimaryKeys(long pk) {
		return getPersistence().getTestrayIssuePrimaryKeys(pk);
	}

	/**
	 * Returns all the testray issues associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return the testray issues associated with the testray case result
	 */
	public static List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(long pk) {

		return getPersistence().getTestrayIssues(pk);
	}

	/**
	 * Returns a range of all the testray issues associated with the testray case result.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case result
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of testray issues associated with the testray case result
	 */
	public static List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(long pk, int start, int end) {

		return getPersistence().getTestrayIssues(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray issues associated with the testray case result.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case result
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray issues associated with the testray case result
	 */
	public static List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestrayIssue>
				orderByComparator) {

		return getPersistence().getTestrayIssues(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray issues associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return the number of testray issues associated with the testray case result
	 */
	public static int getTestrayIssuesSize(long pk) {
		return getPersistence().getTestrayIssuesSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray issue is associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePK the primary key of the testray issue
	 * @return <code>true</code> if the testray issue is associated with the testray case result; <code>false</code> otherwise
	 */
	public static boolean containsTestrayIssue(long pk, long testrayIssuePK) {
		return getPersistence().containsTestrayIssue(pk, testrayIssuePK);
	}

	/**
	 * Returns <code>true</code> if the testray case result has any testray issues associated with it.
	 *
	 * @param pk the primary key of the testray case result to check for associations with testray issues
	 * @return <code>true</code> if the testray case result has any testray issues associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestrayIssues(long pk) {
		return getPersistence().containsTestrayIssues(pk);
	}

	/**
	 * Adds an association between the testray case result and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePK the primary key of the testray issue
	 */
	public static void addTestrayIssue(long pk, long testrayIssuePK) {
		getPersistence().addTestrayIssue(pk, testrayIssuePK);
	}

	/**
	 * Adds an association between the testray case result and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssue the testray issue
	 */
	public static void addTestrayIssue(
		long pk, com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		getPersistence().addTestrayIssue(pk, testrayIssue);
	}

	/**
	 * Adds an association between the testray case result and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePKs the primary keys of the testray issues
	 */
	public static void addTestrayIssues(long pk, long[] testrayIssuePKs) {
		getPersistence().addTestrayIssues(pk, testrayIssuePKs);
	}

	/**
	 * Adds an association between the testray case result and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssues the testray issues
	 */
	public static void addTestrayIssues(
		long pk,
		List<com.liferay.osb.testray.model.TestrayIssue> testrayIssues) {

		getPersistence().addTestrayIssues(pk, testrayIssues);
	}

	/**
	 * Clears all associations between the testray case result and its testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result to clear the associated testray issues from
	 */
	public static void clearTestrayIssues(long pk) {
		getPersistence().clearTestrayIssues(pk);
	}

	/**
	 * Removes the association between the testray case result and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePK the primary key of the testray issue
	 */
	public static void removeTestrayIssue(long pk, long testrayIssuePK) {
		getPersistence().removeTestrayIssue(pk, testrayIssuePK);
	}

	/**
	 * Removes the association between the testray case result and the testray issue. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssue the testray issue
	 */
	public static void removeTestrayIssue(
		long pk, com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		getPersistence().removeTestrayIssue(pk, testrayIssue);
	}

	/**
	 * Removes the association between the testray case result and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePKs the primary keys of the testray issues
	 */
	public static void removeTestrayIssues(long pk, long[] testrayIssuePKs) {
		getPersistence().removeTestrayIssues(pk, testrayIssuePKs);
	}

	/**
	 * Removes the association between the testray case result and the testray issues. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssues the testray issues
	 */
	public static void removeTestrayIssues(
		long pk,
		List<com.liferay.osb.testray.model.TestrayIssue> testrayIssues) {

		getPersistence().removeTestrayIssues(pk, testrayIssues);
	}

	/**
	 * Sets the testray issues associated with the testray case result, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssuePKs the primary keys of the testray issues to be associated with the testray case result
	 */
	public static void setTestrayIssues(long pk, long[] testrayIssuePKs) {
		getPersistence().setTestrayIssues(pk, testrayIssuePKs);
	}

	/**
	 * Sets the testray issues associated with the testray case result, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testrayIssues the testray issues to be associated with the testray case result
	 */
	public static void setTestrayIssues(
		long pk,
		List<com.liferay.osb.testray.model.TestrayIssue> testrayIssues) {

		getPersistence().setTestrayIssues(pk, testrayIssues);
	}

	/**
	 * Returns the primaryKeys of testray subtasks associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return long[] of the primaryKeys of testray subtasks associated with the testray case result
	 */
	public static long[] getTestraySubtaskPrimaryKeys(long pk) {
		return getPersistence().getTestraySubtaskPrimaryKeys(pk);
	}

	/**
	 * Returns all the testray subtasks associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return the testray subtasks associated with the testray case result
	 */
	public static List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(long pk) {

		return getPersistence().getTestraySubtasks(pk);
	}

	/**
	 * Returns a range of all the testray subtasks associated with the testray case result.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case result
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of testray subtasks associated with the testray case result
	 */
	public static List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(long pk, int start, int end) {

		return getPersistence().getTestraySubtasks(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the testray subtasks associated with the testray case result.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case result
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray subtasks associated with the testray case result
	 */
	public static List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.testray.model.TestraySubtask>
				orderByComparator) {

		return getPersistence().getTestraySubtasks(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray subtasks associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @return the number of testray subtasks associated with the testray case result
	 */
	public static int getTestraySubtasksSize(long pk) {
		return getPersistence().getTestraySubtasksSize(pk);
	}

	/**
	 * Returns <code>true</code> if the testray subtask is associated with the testray case result.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPK the primary key of the testray subtask
	 * @return <code>true</code> if the testray subtask is associated with the testray case result; <code>false</code> otherwise
	 */
	public static boolean containsTestraySubtask(
		long pk, long testraySubtaskPK) {

		return getPersistence().containsTestraySubtask(pk, testraySubtaskPK);
	}

	/**
	 * Returns <code>true</code> if the testray case result has any testray subtasks associated with it.
	 *
	 * @param pk the primary key of the testray case result to check for associations with testray subtasks
	 * @return <code>true</code> if the testray case result has any testray subtasks associated with it; <code>false</code> otherwise
	 */
	public static boolean containsTestraySubtasks(long pk) {
		return getPersistence().containsTestraySubtasks(pk);
	}

	/**
	 * Adds an association between the testray case result and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPK the primary key of the testray subtask
	 */
	public static void addTestraySubtask(long pk, long testraySubtaskPK) {
		getPersistence().addTestraySubtask(pk, testraySubtaskPK);
	}

	/**
	 * Adds an association between the testray case result and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtask the testray subtask
	 */
	public static void addTestraySubtask(
		long pk, com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		getPersistence().addTestraySubtask(pk, testraySubtask);
	}

	/**
	 * Adds an association between the testray case result and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPKs the primary keys of the testray subtasks
	 */
	public static void addTestraySubtasks(long pk, long[] testraySubtaskPKs) {
		getPersistence().addTestraySubtasks(pk, testraySubtaskPKs);
	}

	/**
	 * Adds an association between the testray case result and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtasks the testray subtasks
	 */
	public static void addTestraySubtasks(
		long pk,
		List<com.liferay.osb.testray.model.TestraySubtask> testraySubtasks) {

		getPersistence().addTestraySubtasks(pk, testraySubtasks);
	}

	/**
	 * Clears all associations between the testray case result and its testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result to clear the associated testray subtasks from
	 */
	public static void clearTestraySubtasks(long pk) {
		getPersistence().clearTestraySubtasks(pk);
	}

	/**
	 * Removes the association between the testray case result and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPK the primary key of the testray subtask
	 */
	public static void removeTestraySubtask(long pk, long testraySubtaskPK) {
		getPersistence().removeTestraySubtask(pk, testraySubtaskPK);
	}

	/**
	 * Removes the association between the testray case result and the testray subtask. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtask the testray subtask
	 */
	public static void removeTestraySubtask(
		long pk, com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		getPersistence().removeTestraySubtask(pk, testraySubtask);
	}

	/**
	 * Removes the association between the testray case result and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPKs the primary keys of the testray subtasks
	 */
	public static void removeTestraySubtasks(
		long pk, long[] testraySubtaskPKs) {

		getPersistence().removeTestraySubtasks(pk, testraySubtaskPKs);
	}

	/**
	 * Removes the association between the testray case result and the testray subtasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtasks the testray subtasks
	 */
	public static void removeTestraySubtasks(
		long pk,
		List<com.liferay.osb.testray.model.TestraySubtask> testraySubtasks) {

		getPersistence().removeTestraySubtasks(pk, testraySubtasks);
	}

	/**
	 * Sets the testray subtasks associated with the testray case result, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtaskPKs the primary keys of the testray subtasks to be associated with the testray case result
	 */
	public static void setTestraySubtasks(long pk, long[] testraySubtaskPKs) {
		getPersistence().setTestraySubtasks(pk, testraySubtaskPKs);
	}

	/**
	 * Sets the testray subtasks associated with the testray case result, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case result
	 * @param testraySubtasks the testray subtasks to be associated with the testray case result
	 */
	public static void setTestraySubtasks(
		long pk,
		List<com.liferay.osb.testray.model.TestraySubtask> testraySubtasks) {

		getPersistence().setTestraySubtasks(pk, testraySubtasks);
	}

	public static TestrayCaseResultPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayCaseResultPersistence, TestrayCaseResultPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayCaseResultPersistence.class);

		ServiceTracker
			<TestrayCaseResultPersistence, TestrayCaseResultPersistence>
				serviceTracker =
					new ServiceTracker
						<TestrayCaseResultPersistence,
						 TestrayCaseResultPersistence>(
							 bundle.getBundleContext(),
							 TestrayCaseResultPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}