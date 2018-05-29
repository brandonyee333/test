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

import com.liferay.watson.model.WatsonDocumentAudit;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the watson document audit service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonDocumentAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonDocumentAuditPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonDocumentAuditPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonDocumentAuditUtil {
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
	public static void clearCache(WatsonDocumentAudit watsonDocumentAudit) {
		getPersistence().clearCache(watsonDocumentAudit);
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
	public static List<WatsonDocumentAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonDocumentAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonDocumentAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonDocumentAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonDocumentAudit update(
		WatsonDocumentAudit watsonDocumentAudit) {
		return getPersistence().update(watsonDocumentAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonDocumentAudit update(
		WatsonDocumentAudit watsonDocumentAudit, ServiceContext serviceContext) {
		return getPersistence().update(watsonDocumentAudit, serviceContext);
	}

	/**
	* Caches the watson document audit in the entity cache if it is enabled.
	*
	* @param watsonDocumentAudit the watson document audit
	*/
	public static void cacheResult(WatsonDocumentAudit watsonDocumentAudit) {
		getPersistence().cacheResult(watsonDocumentAudit);
	}

	/**
	* Caches the watson document audits in the entity cache if it is enabled.
	*
	* @param watsonDocumentAudits the watson document audits
	*/
	public static void cacheResult(
		List<WatsonDocumentAudit> watsonDocumentAudits) {
		getPersistence().cacheResult(watsonDocumentAudits);
	}

	/**
	* Creates a new watson document audit with the primary key. Does not add the watson document audit to the database.
	*
	* @param watsonDocumentAuditId the primary key for the new watson document audit
	* @return the new watson document audit
	*/
	public static WatsonDocumentAudit create(long watsonDocumentAuditId) {
		return getPersistence().create(watsonDocumentAuditId);
	}

	/**
	* Removes the watson document audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonDocumentAuditId the primary key of the watson document audit
	* @return the watson document audit that was removed
	* @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	*/
	public static WatsonDocumentAudit remove(long watsonDocumentAuditId)
		throws com.liferay.watson.exception.NoSuchDocumentAuditException {
		return getPersistence().remove(watsonDocumentAuditId);
	}

	public static WatsonDocumentAudit updateImpl(
		WatsonDocumentAudit watsonDocumentAudit) {
		return getPersistence().updateImpl(watsonDocumentAudit);
	}

	/**
	* Returns the watson document audit with the primary key or throws a {@link NoSuchDocumentAuditException} if it could not be found.
	*
	* @param watsonDocumentAuditId the primary key of the watson document audit
	* @return the watson document audit
	* @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	*/
	public static WatsonDocumentAudit findByPrimaryKey(
		long watsonDocumentAuditId)
		throws com.liferay.watson.exception.NoSuchDocumentAuditException {
		return getPersistence().findByPrimaryKey(watsonDocumentAuditId);
	}

	/**
	* Returns the watson document audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonDocumentAuditId the primary key of the watson document audit
	* @return the watson document audit, or <code>null</code> if a watson document audit with the primary key could not be found
	*/
	public static WatsonDocumentAudit fetchByPrimaryKey(
		long watsonDocumentAuditId) {
		return getPersistence().fetchByPrimaryKey(watsonDocumentAuditId);
	}

	public static java.util.Map<java.io.Serializable, WatsonDocumentAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the watson document audits.
	*
	* @return the watson document audits
	*/
	public static List<WatsonDocumentAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson document audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson document audits
	* @param end the upper bound of the range of watson document audits (not inclusive)
	* @return the range of watson document audits
	*/
	public static List<WatsonDocumentAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson document audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson document audits
	* @param end the upper bound of the range of watson document audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson document audits
	*/
	public static List<WatsonDocumentAudit> findAll(int start, int end,
		OrderByComparator<WatsonDocumentAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson document audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson document audits
	* @param end the upper bound of the range of watson document audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson document audits
	*/
	public static List<WatsonDocumentAudit> findAll(int start, int end,
		OrderByComparator<WatsonDocumentAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson document audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson document audits.
	*
	* @return the number of watson document audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonDocumentAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonDocumentAuditPersistence, WatsonDocumentAuditPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonDocumentAuditPersistence.class);

		ServiceTracker<WatsonDocumentAuditPersistence, WatsonDocumentAuditPersistence> serviceTracker =
			new ServiceTracker<WatsonDocumentAuditPersistence, WatsonDocumentAuditPersistence>(bundle.getBundleContext(),
				WatsonDocumentAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}