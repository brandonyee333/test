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
import com.liferay.watson.model.WatsonIncidentRel;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the watson incident rel service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonIncidentRelPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonIncidentRelPersistence
 * @generated
 */
@ProviderType
public class WatsonIncidentRelUtil {

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
	public static void clearCache(WatsonIncidentRel watsonIncidentRel) {
		getPersistence().clearCache(watsonIncidentRel);
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
	public static Map<Serializable, WatsonIncidentRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonIncidentRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonIncidentRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonIncidentRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonIncidentRel> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonIncidentRel update(
		WatsonIncidentRel watsonIncidentRel) {

		return getPersistence().update(watsonIncidentRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonIncidentRel update(
		WatsonIncidentRel watsonIncidentRel, ServiceContext serviceContext) {

		return getPersistence().update(watsonIncidentRel, serviceContext);
	}

	/**
	 * Caches the watson incident rel in the entity cache if it is enabled.
	 *
	 * @param watsonIncidentRel the watson incident rel
	 */
	public static void cacheResult(WatsonIncidentRel watsonIncidentRel) {
		getPersistence().cacheResult(watsonIncidentRel);
	}

	/**
	 * Caches the watson incident rels in the entity cache if it is enabled.
	 *
	 * @param watsonIncidentRels the watson incident rels
	 */
	public static void cacheResult(List<WatsonIncidentRel> watsonIncidentRels) {
		getPersistence().cacheResult(watsonIncidentRels);
	}

	/**
	 * Creates a new watson incident rel with the primary key. Does not add the watson incident rel to the database.
	 *
	 * @param watsonIncidentRelId the primary key for the new watson incident rel
	 * @return the new watson incident rel
	 */
	public static WatsonIncidentRel create(long watsonIncidentRelId) {
		return getPersistence().create(watsonIncidentRelId);
	}

	/**
	 * Removes the watson incident rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel that was removed
	 * @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	 */
	public static WatsonIncidentRel remove(long watsonIncidentRelId)
		throws com.liferay.watson.exception.NoSuchIncidentRelException {

		return getPersistence().remove(watsonIncidentRelId);
	}

	public static WatsonIncidentRel updateImpl(
		WatsonIncidentRel watsonIncidentRel) {

		return getPersistence().updateImpl(watsonIncidentRel);
	}

	/**
	 * Returns the watson incident rel with the primary key or throws a <code>NoSuchIncidentRelException</code> if it could not be found.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel
	 * @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	 */
	public static WatsonIncidentRel findByPrimaryKey(long watsonIncidentRelId)
		throws com.liferay.watson.exception.NoSuchIncidentRelException {

		return getPersistence().findByPrimaryKey(watsonIncidentRelId);
	}

	/**
	 * Returns the watson incident rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel, or <code>null</code> if a watson incident rel with the primary key could not be found
	 */
	public static WatsonIncidentRel fetchByPrimaryKey(
		long watsonIncidentRelId) {

		return getPersistence().fetchByPrimaryKey(watsonIncidentRelId);
	}

	/**
	 * Returns all the watson incident rels.
	 *
	 * @return the watson incident rels
	 */
	public static List<WatsonIncidentRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonIncidentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @return the range of watson incident rels
	 */
	public static List<WatsonIncidentRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonIncidentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson incident rels
	 */
	public static List<WatsonIncidentRel> findAll(
		int start, int end,
		OrderByComparator<WatsonIncidentRel> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonIncidentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson incident rels
	 */
	public static List<WatsonIncidentRel> findAll(
		int start, int end,
		OrderByComparator<WatsonIncidentRel> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the watson incident rels from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of watson incident rels.
	 *
	 * @return the number of watson incident rels
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonIncidentRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonIncidentRelPersistence, WatsonIncidentRelPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonIncidentRelPersistence.class);

		ServiceTracker
			<WatsonIncidentRelPersistence, WatsonIncidentRelPersistence>
				serviceTracker =
					new ServiceTracker
						<WatsonIncidentRelPersistence,
						 WatsonIncidentRelPersistence>(
							 bundle.getBundleContext(),
							 WatsonIncidentRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}