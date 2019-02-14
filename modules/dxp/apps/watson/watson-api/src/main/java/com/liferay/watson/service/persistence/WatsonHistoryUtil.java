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

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.watson.model.WatsonHistory;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the watson history service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonHistoryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonHistoryPersistence
 * @generated
 */
@ProviderType
public class WatsonHistoryUtil {
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
	public static void clearCache(WatsonHistory watsonHistory) {
		getPersistence().clearCache(watsonHistory);
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
	public static Map<Serializable, WatsonHistory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonHistory> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonHistory update(WatsonHistory watsonHistory) {
		return getPersistence().update(watsonHistory);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonHistory update(WatsonHistory watsonHistory,
		ServiceContext serviceContext) {
		return getPersistence().update(watsonHistory, serviceContext);
	}

	/**
	* Caches the watson history in the entity cache if it is enabled.
	*
	* @param watsonHistory the watson history
	*/
	public static void cacheResult(WatsonHistory watsonHistory) {
		getPersistence().cacheResult(watsonHistory);
	}

	/**
	* Caches the watson histories in the entity cache if it is enabled.
	*
	* @param watsonHistories the watson histories
	*/
	public static void cacheResult(List<WatsonHistory> watsonHistories) {
		getPersistence().cacheResult(watsonHistories);
	}

	/**
	* Creates a new watson history with the primary key. Does not add the watson history to the database.
	*
	* @param watsonHistoryId the primary key for the new watson history
	* @return the new watson history
	*/
	public static WatsonHistory create(long watsonHistoryId) {
		return getPersistence().create(watsonHistoryId);
	}

	/**
	* Removes the watson history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonHistoryId the primary key of the watson history
	* @return the watson history that was removed
	* @throws NoSuchHistoryException if a watson history with the primary key could not be found
	*/
	public static WatsonHistory remove(long watsonHistoryId)
		throws com.liferay.watson.exception.NoSuchHistoryException {
		return getPersistence().remove(watsonHistoryId);
	}

	public static WatsonHistory updateImpl(WatsonHistory watsonHistory) {
		return getPersistence().updateImpl(watsonHistory);
	}

	/**
	* Returns the watson history with the primary key or throws a <code>NoSuchHistoryException</code> if it could not be found.
	*
	* @param watsonHistoryId the primary key of the watson history
	* @return the watson history
	* @throws NoSuchHistoryException if a watson history with the primary key could not be found
	*/
	public static WatsonHistory findByPrimaryKey(long watsonHistoryId)
		throws com.liferay.watson.exception.NoSuchHistoryException {
		return getPersistence().findByPrimaryKey(watsonHistoryId);
	}

	/**
	* Returns the watson history with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonHistoryId the primary key of the watson history
	* @return the watson history, or <code>null</code> if a watson history with the primary key could not be found
	*/
	public static WatsonHistory fetchByPrimaryKey(long watsonHistoryId) {
		return getPersistence().fetchByPrimaryKey(watsonHistoryId);
	}

	/**
	* Returns all the watson histories.
	*
	* @return the watson histories
	*/
	public static List<WatsonHistory> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson histories
	* @param end the upper bound of the range of watson histories (not inclusive)
	* @return the range of watson histories
	*/
	public static List<WatsonHistory> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson histories
	* @param end the upper bound of the range of watson histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson histories
	*/
	public static List<WatsonHistory> findAll(int start, int end,
		OrderByComparator<WatsonHistory> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson histories
	* @param end the upper bound of the range of watson histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson histories
	*/
	public static List<WatsonHistory> findAll(int start, int end,
		OrderByComparator<WatsonHistory> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson histories from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson histories.
	*
	* @return the number of watson histories
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonHistoryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonHistoryPersistence, WatsonHistoryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonHistoryPersistence.class);

		ServiceTracker<WatsonHistoryPersistence, WatsonHistoryPersistence> serviceTracker =
			new ServiceTracker<WatsonHistoryPersistence, WatsonHistoryPersistence>(bundle.getBundleContext(),
				WatsonHistoryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}