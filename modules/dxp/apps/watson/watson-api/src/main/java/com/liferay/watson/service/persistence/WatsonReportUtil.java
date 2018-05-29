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

import com.liferay.watson.model.WatsonReport;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the watson report service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonReportPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonReportPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonReportPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonReportUtil {
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
	public static void clearCache(WatsonReport watsonReport) {
		getPersistence().clearCache(watsonReport);
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
	public static List<WatsonReport> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonReport> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonReport> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonReport> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonReport update(WatsonReport watsonReport) {
		return getPersistence().update(watsonReport);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonReport update(WatsonReport watsonReport,
		ServiceContext serviceContext) {
		return getPersistence().update(watsonReport, serviceContext);
	}

	/**
	* Caches the watson report in the entity cache if it is enabled.
	*
	* @param watsonReport the watson report
	*/
	public static void cacheResult(WatsonReport watsonReport) {
		getPersistence().cacheResult(watsonReport);
	}

	/**
	* Caches the watson reports in the entity cache if it is enabled.
	*
	* @param watsonReports the watson reports
	*/
	public static void cacheResult(List<WatsonReport> watsonReports) {
		getPersistence().cacheResult(watsonReports);
	}

	/**
	* Creates a new watson report with the primary key. Does not add the watson report to the database.
	*
	* @param watsonReportId the primary key for the new watson report
	* @return the new watson report
	*/
	public static WatsonReport create(long watsonReportId) {
		return getPersistence().create(watsonReportId);
	}

	/**
	* Removes the watson report with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonReportId the primary key of the watson report
	* @return the watson report that was removed
	* @throws NoSuchReportException if a watson report with the primary key could not be found
	*/
	public static WatsonReport remove(long watsonReportId)
		throws com.liferay.watson.exception.NoSuchReportException {
		return getPersistence().remove(watsonReportId);
	}

	public static WatsonReport updateImpl(WatsonReport watsonReport) {
		return getPersistence().updateImpl(watsonReport);
	}

	/**
	* Returns the watson report with the primary key or throws a {@link NoSuchReportException} if it could not be found.
	*
	* @param watsonReportId the primary key of the watson report
	* @return the watson report
	* @throws NoSuchReportException if a watson report with the primary key could not be found
	*/
	public static WatsonReport findByPrimaryKey(long watsonReportId)
		throws com.liferay.watson.exception.NoSuchReportException {
		return getPersistence().findByPrimaryKey(watsonReportId);
	}

	/**
	* Returns the watson report with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonReportId the primary key of the watson report
	* @return the watson report, or <code>null</code> if a watson report with the primary key could not be found
	*/
	public static WatsonReport fetchByPrimaryKey(long watsonReportId) {
		return getPersistence().fetchByPrimaryKey(watsonReportId);
	}

	public static java.util.Map<java.io.Serializable, WatsonReport> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the watson reports.
	*
	* @return the watson reports
	*/
	public static List<WatsonReport> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson reports
	* @param end the upper bound of the range of watson reports (not inclusive)
	* @return the range of watson reports
	*/
	public static List<WatsonReport> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson reports
	* @param end the upper bound of the range of watson reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson reports
	*/
	public static List<WatsonReport> findAll(int start, int end,
		OrderByComparator<WatsonReport> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson reports
	* @param end the upper bound of the range of watson reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson reports
	*/
	public static List<WatsonReport> findAll(int start, int end,
		OrderByComparator<WatsonReport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson reports from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson reports.
	*
	* @return the number of watson reports
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonReportPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonReportPersistence, WatsonReportPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonReportPersistence.class);

		ServiceTracker<WatsonReportPersistence, WatsonReportPersistence> serviceTracker =
			new ServiceTracker<WatsonReportPersistence, WatsonReportPersistence>(bundle.getBundleContext(),
				WatsonReportPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}