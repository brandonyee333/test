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

package com.liferay.osb.loop.service.persistence;

import com.liferay.osb.loop.model.LoopExternalReferenceRel;
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
 * The persistence utility for the loop external reference rel service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopExternalReferenceRelPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopExternalReferenceRelPersistence
 * @generated
 */
public class LoopExternalReferenceRelUtil {

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
		LoopExternalReferenceRel loopExternalReferenceRel) {

		getPersistence().clearCache(loopExternalReferenceRel);
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
	public static Map<Serializable, LoopExternalReferenceRel>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoopExternalReferenceRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopExternalReferenceRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopExternalReferenceRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopExternalReferenceRel> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopExternalReferenceRel update(
		LoopExternalReferenceRel loopExternalReferenceRel) {

		return getPersistence().update(loopExternalReferenceRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopExternalReferenceRel update(
		LoopExternalReferenceRel loopExternalReferenceRel,
		ServiceContext serviceContext) {

		return getPersistence().update(
			loopExternalReferenceRel, serviceContext);
	}

	/**
	 * Returns the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; or throws a <code>NoSuchLoopExternalReferenceRelException</code> if it could not be found.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the matching loop external reference rel
	 * @throws NoSuchLoopExternalReferenceRelException if a matching loop external reference rel could not be found
	 */
	public static LoopExternalReferenceRel findByERP_ESN(
			String externalReferenceName, String externalReferencePK)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopExternalReferenceRelException {

		return getPersistence().findByERP_ESN(
			externalReferenceName, externalReferencePK);
	}

	/**
	 * Returns the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the matching loop external reference rel, or <code>null</code> if a matching loop external reference rel could not be found
	 */
	public static LoopExternalReferenceRel fetchByERP_ESN(
		String externalReferenceName, String externalReferencePK) {

		return getPersistence().fetchByERP_ESN(
			externalReferenceName, externalReferencePK);
	}

	/**
	 * Returns the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop external reference rel, or <code>null</code> if a matching loop external reference rel could not be found
	 */
	public static LoopExternalReferenceRel fetchByERP_ESN(
		String externalReferenceName, String externalReferencePK,
		boolean useFinderCache) {

		return getPersistence().fetchByERP_ESN(
			externalReferenceName, externalReferencePK, useFinderCache);
	}

	/**
	 * Removes the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; from the database.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the loop external reference rel that was removed
	 */
	public static LoopExternalReferenceRel removeByERP_ESN(
			String externalReferenceName, String externalReferencePK)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopExternalReferenceRelException {

		return getPersistence().removeByERP_ESN(
			externalReferenceName, externalReferencePK);
	}

	/**
	 * Returns the number of loop external reference rels where externalReferenceName = &#63; and externalReferencePK = &#63;.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the number of matching loop external reference rels
	 */
	public static int countByERP_ESN(
		String externalReferenceName, String externalReferencePK) {

		return getPersistence().countByERP_ESN(
			externalReferenceName, externalReferencePK);
	}

	/**
	 * Caches the loop external reference rel in the entity cache if it is enabled.
	 *
	 * @param loopExternalReferenceRel the loop external reference rel
	 */
	public static void cacheResult(
		LoopExternalReferenceRel loopExternalReferenceRel) {

		getPersistence().cacheResult(loopExternalReferenceRel);
	}

	/**
	 * Caches the loop external reference rels in the entity cache if it is enabled.
	 *
	 * @param loopExternalReferenceRels the loop external reference rels
	 */
	public static void cacheResult(
		List<LoopExternalReferenceRel> loopExternalReferenceRels) {

		getPersistence().cacheResult(loopExternalReferenceRels);
	}

	/**
	 * Creates a new loop external reference rel with the primary key. Does not add the loop external reference rel to the database.
	 *
	 * @param loopExternalReferenceRelId the primary key for the new loop external reference rel
	 * @return the new loop external reference rel
	 */
	public static LoopExternalReferenceRel create(
		long loopExternalReferenceRelId) {

		return getPersistence().create(loopExternalReferenceRelId);
	}

	/**
	 * Removes the loop external reference rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel that was removed
	 * @throws NoSuchLoopExternalReferenceRelException if a loop external reference rel with the primary key could not be found
	 */
	public static LoopExternalReferenceRel remove(
			long loopExternalReferenceRelId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopExternalReferenceRelException {

		return getPersistence().remove(loopExternalReferenceRelId);
	}

	public static LoopExternalReferenceRel updateImpl(
		LoopExternalReferenceRel loopExternalReferenceRel) {

		return getPersistence().updateImpl(loopExternalReferenceRel);
	}

	/**
	 * Returns the loop external reference rel with the primary key or throws a <code>NoSuchLoopExternalReferenceRelException</code> if it could not be found.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel
	 * @throws NoSuchLoopExternalReferenceRelException if a loop external reference rel with the primary key could not be found
	 */
	public static LoopExternalReferenceRel findByPrimaryKey(
			long loopExternalReferenceRelId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopExternalReferenceRelException {

		return getPersistence().findByPrimaryKey(loopExternalReferenceRelId);
	}

	/**
	 * Returns the loop external reference rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel, or <code>null</code> if a loop external reference rel with the primary key could not be found
	 */
	public static LoopExternalReferenceRel fetchByPrimaryKey(
		long loopExternalReferenceRelId) {

		return getPersistence().fetchByPrimaryKey(loopExternalReferenceRelId);
	}

	/**
	 * Returns all the loop external reference rels.
	 *
	 * @return the loop external reference rels
	 */
	public static List<LoopExternalReferenceRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the loop external reference rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopExternalReferenceRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop external reference rels
	 * @param end the upper bound of the range of loop external reference rels (not inclusive)
	 * @return the range of loop external reference rels
	 */
	public static List<LoopExternalReferenceRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the loop external reference rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopExternalReferenceRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop external reference rels
	 * @param end the upper bound of the range of loop external reference rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop external reference rels
	 */
	public static List<LoopExternalReferenceRel> findAll(
		int start, int end,
		OrderByComparator<LoopExternalReferenceRel> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop external reference rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopExternalReferenceRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop external reference rels
	 * @param end the upper bound of the range of loop external reference rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop external reference rels
	 */
	public static List<LoopExternalReferenceRel> findAll(
		int start, int end,
		OrderByComparator<LoopExternalReferenceRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the loop external reference rels from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of loop external reference rels.
	 *
	 * @return the number of loop external reference rels
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopExternalReferenceRelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LoopExternalReferenceRelPersistence,
		 LoopExternalReferenceRelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			LoopExternalReferenceRelPersistence.class);

		ServiceTracker
			<LoopExternalReferenceRelPersistence,
			 LoopExternalReferenceRelPersistence> serviceTracker =
				new ServiceTracker
					<LoopExternalReferenceRelPersistence,
					 LoopExternalReferenceRelPersistence>(
						 bundle.getBundleContext(),
						 LoopExternalReferenceRelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}