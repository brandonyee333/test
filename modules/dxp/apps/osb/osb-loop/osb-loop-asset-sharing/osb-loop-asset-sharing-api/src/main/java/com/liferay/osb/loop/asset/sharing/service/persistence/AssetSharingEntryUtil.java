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

package com.liferay.osb.loop.asset.sharing.service.persistence;

import com.liferay.osb.loop.asset.sharing.model.AssetSharingEntry;
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
 * The persistence utility for the asset sharing entry service. This utility wraps <code>com.liferay.osb.loop.asset.sharing.service.persistence.impl.AssetSharingEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetSharingEntryPersistence
 * @generated
 */
public class AssetSharingEntryUtil {

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
	public static void clearCache(AssetSharingEntry assetSharingEntry) {
		getPersistence().clearCache(assetSharingEntry);
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
	public static Map<Serializable, AssetSharingEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AssetSharingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetSharingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetSharingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetSharingEntry update(
		AssetSharingEntry assetSharingEntry) {

		return getPersistence().update(assetSharingEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetSharingEntry update(
		AssetSharingEntry assetSharingEntry, ServiceContext serviceContext) {

		return getPersistence().update(assetSharingEntry, serviceContext);
	}

	/**
	 * Returns all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_C(
		long classNameId, long classPK) {

		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	 * Returns a range of all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetSharingEntryPK the primary key of the current asset sharing entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	public static AssetSharingEntry[] findByC_C_PrevAndNext(
			AssetSharingEntryPK assetSharingEntryPK, long classNameId,
			long classPK,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByC_C_PrevAndNext(
			assetSharingEntryPK, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the asset sharing entries where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of asset sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching asset sharing entries
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Returns all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @return the matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByS_S(
		long sharedToClassNameId, long sharedToClassPK) {

		return getPersistence().findByS_S(sharedToClassNameId, sharedToClassPK);
	}

	/**
	 * Returns a range of all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByS_S(
		long sharedToClassNameId, long sharedToClassPK, int start, int end) {

		return getPersistence().findByS_S(
			sharedToClassNameId, sharedToClassPK, start, end);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByS_S(
		long sharedToClassNameId, long sharedToClassPK, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().findByS_S(
			sharedToClassNameId, sharedToClassPK, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByS_S(
		long sharedToClassNameId, long sharedToClassPK, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByS_S(
			sharedToClassNameId, sharedToClassPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry findByS_S_First(
			long sharedToClassNameId, long sharedToClassPK,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByS_S_First(
			sharedToClassNameId, sharedToClassPK, orderByComparator);
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry fetchByS_S_First(
		long sharedToClassNameId, long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().fetchByS_S_First(
			sharedToClassNameId, sharedToClassPK, orderByComparator);
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry findByS_S_Last(
			long sharedToClassNameId, long sharedToClassPK,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByS_S_Last(
			sharedToClassNameId, sharedToClassPK, orderByComparator);
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry fetchByS_S_Last(
		long sharedToClassNameId, long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().fetchByS_S_Last(
			sharedToClassNameId, sharedToClassPK, orderByComparator);
	}

	/**
	 * Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param assetSharingEntryPK the primary key of the current asset sharing entry
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	public static AssetSharingEntry[] findByS_S_PrevAndNext(
			AssetSharingEntryPK assetSharingEntryPK, long sharedToClassNameId,
			long sharedToClassPK,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByS_S_PrevAndNext(
			assetSharingEntryPK, sharedToClassNameId, sharedToClassPK,
			orderByComparator);
	}

	/**
	 * Removes all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63; from the database.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 */
	public static void removeByS_S(
		long sharedToClassNameId, long sharedToClassPK) {

		getPersistence().removeByS_S(sharedToClassNameId, sharedToClassPK);
	}

	/**
	 * Returns the number of asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @return the number of matching asset sharing entries
	 */
	public static int countByS_S(
		long sharedToClassNameId, long sharedToClassPK) {

		return getPersistence().countByS_S(
			sharedToClassNameId, sharedToClassPK);
	}

	/**
	 * Returns all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @return the matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharedToClassNameId) {

		return getPersistence().findByC_C_S(
			classNameId, classPK, sharedToClassNameId);
	}

	/**
	 * Returns a range of all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharedToClassNameId, int start,
		int end) {

		return getPersistence().findByC_C_S(
			classNameId, classPK, sharedToClassNameId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharedToClassNameId, int start,
		int end, OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().findByC_C_S(
			classNameId, classPK, sharedToClassNameId, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_C_S(
		long classNameId, long classPK, long sharedToClassNameId, int start,
		int end, OrderByComparator<AssetSharingEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C_S(
			classNameId, classPK, sharedToClassNameId, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry findByC_C_S_First(
			long classNameId, long classPK, long sharedToClassNameId,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByC_C_S_First(
			classNameId, classPK, sharedToClassNameId, orderByComparator);
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry fetchByC_C_S_First(
		long classNameId, long classPK, long sharedToClassNameId,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().fetchByC_C_S_First(
			classNameId, classPK, sharedToClassNameId, orderByComparator);
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry findByC_C_S_Last(
			long classNameId, long classPK, long sharedToClassNameId,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByC_C_S_Last(
			classNameId, classPK, sharedToClassNameId, orderByComparator);
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry fetchByC_C_S_Last(
		long classNameId, long classPK, long sharedToClassNameId,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().fetchByC_C_S_Last(
			classNameId, classPK, sharedToClassNameId, orderByComparator);
	}

	/**
	 * Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param assetSharingEntryPK the primary key of the current asset sharing entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	public static AssetSharingEntry[] findByC_C_S_PrevAndNext(
			AssetSharingEntryPK assetSharingEntryPK, long classNameId,
			long classPK, long sharedToClassNameId,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByC_C_S_PrevAndNext(
			assetSharingEntryPK, classNameId, classPK, sharedToClassNameId,
			orderByComparator);
	}

	/**
	 * Removes all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 */
	public static void removeByC_C_S(
		long classNameId, long classPK, long sharedToClassNameId) {

		getPersistence().removeByC_C_S(
			classNameId, classPK, sharedToClassNameId);
	}

	/**
	 * Returns the number of asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @return the number of matching asset sharing entries
	 */
	public static int countByC_C_S(
		long classNameId, long classPK, long sharedToClassNameId) {

		return getPersistence().countByC_C_S(
			classNameId, classPK, sharedToClassNameId);
	}

	/**
	 * Returns all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @return the matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_S_S(
		long classNameId, long sharedToClassNameId, long sharedToClassPK) {

		return getPersistence().findByC_S_S(
			classNameId, sharedToClassNameId, sharedToClassPK);
	}

	/**
	 * Returns a range of all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_S_S(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		int start, int end) {

		return getPersistence().findByC_S_S(
			classNameId, sharedToClassNameId, sharedToClassPK, start, end);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_S_S(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().findByC_S_S(
			classNameId, sharedToClassNameId, sharedToClassPK, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset sharing entries
	 */
	public static List<AssetSharingEntry> findByC_S_S(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_S_S(
			classNameId, sharedToClassNameId, sharedToClassPK, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry findByC_S_S_First(
			long classNameId, long sharedToClassNameId, long sharedToClassPK,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByC_S_S_First(
			classNameId, sharedToClassNameId, sharedToClassPK,
			orderByComparator);
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry fetchByC_S_S_First(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().fetchByC_S_S_First(
			classNameId, sharedToClassNameId, sharedToClassPK,
			orderByComparator);
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry findByC_S_S_Last(
			long classNameId, long sharedToClassNameId, long sharedToClassPK,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByC_S_S_Last(
			classNameId, sharedToClassNameId, sharedToClassPK,
			orderByComparator);
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	public static AssetSharingEntry fetchByC_S_S_Last(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().fetchByC_S_S_Last(
			classNameId, sharedToClassNameId, sharedToClassPK,
			orderByComparator);
	}

	/**
	 * Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param assetSharingEntryPK the primary key of the current asset sharing entry
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	public static AssetSharingEntry[] findByC_S_S_PrevAndNext(
			AssetSharingEntryPK assetSharingEntryPK, long classNameId,
			long sharedToClassNameId, long sharedToClassPK,
			OrderByComparator<AssetSharingEntry> orderByComparator)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByC_S_S_PrevAndNext(
			assetSharingEntryPK, classNameId, sharedToClassNameId,
			sharedToClassPK, orderByComparator);
	}

	/**
	 * Removes all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 */
	public static void removeByC_S_S(
		long classNameId, long sharedToClassNameId, long sharedToClassPK) {

		getPersistence().removeByC_S_S(
			classNameId, sharedToClassNameId, sharedToClassPK);
	}

	/**
	 * Returns the number of asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @return the number of matching asset sharing entries
	 */
	public static int countByC_S_S(
		long classNameId, long sharedToClassNameId, long sharedToClassPK) {

		return getPersistence().countByC_S_S(
			classNameId, sharedToClassNameId, sharedToClassPK);
	}

	/**
	 * Caches the asset sharing entry in the entity cache if it is enabled.
	 *
	 * @param assetSharingEntry the asset sharing entry
	 */
	public static void cacheResult(AssetSharingEntry assetSharingEntry) {
		getPersistence().cacheResult(assetSharingEntry);
	}

	/**
	 * Caches the asset sharing entries in the entity cache if it is enabled.
	 *
	 * @param assetSharingEntries the asset sharing entries
	 */
	public static void cacheResult(
		List<AssetSharingEntry> assetSharingEntries) {

		getPersistence().cacheResult(assetSharingEntries);
	}

	/**
	 * Creates a new asset sharing entry with the primary key. Does not add the asset sharing entry to the database.
	 *
	 * @param assetSharingEntryPK the primary key for the new asset sharing entry
	 * @return the new asset sharing entry
	 */
	public static AssetSharingEntry create(
		AssetSharingEntryPK assetSharingEntryPK) {

		return getPersistence().create(assetSharingEntryPK);
	}

	/**
	 * Removes the asset sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetSharingEntryPK the primary key of the asset sharing entry
	 * @return the asset sharing entry that was removed
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	public static AssetSharingEntry remove(
			AssetSharingEntryPK assetSharingEntryPK)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().remove(assetSharingEntryPK);
	}

	public static AssetSharingEntry updateImpl(
		AssetSharingEntry assetSharingEntry) {

		return getPersistence().updateImpl(assetSharingEntry);
	}

	/**
	 * Returns the asset sharing entry with the primary key or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param assetSharingEntryPK the primary key of the asset sharing entry
	 * @return the asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	public static AssetSharingEntry findByPrimaryKey(
			AssetSharingEntryPK assetSharingEntryPK)
		throws com.liferay.osb.loop.asset.sharing.exception.
			NoSuchEntryException {

		return getPersistence().findByPrimaryKey(assetSharingEntryPK);
	}

	/**
	 * Returns the asset sharing entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetSharingEntryPK the primary key of the asset sharing entry
	 * @return the asset sharing entry, or <code>null</code> if a asset sharing entry with the primary key could not be found
	 */
	public static AssetSharingEntry fetchByPrimaryKey(
		AssetSharingEntryPK assetSharingEntryPK) {

		return getPersistence().fetchByPrimaryKey(assetSharingEntryPK);
	}

	/**
	 * Returns all the asset sharing entries.
	 *
	 * @return the asset sharing entries
	 */
	public static List<AssetSharingEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the asset sharing entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of asset sharing entries
	 */
	public static List<AssetSharingEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset sharing entries
	 */
	public static List<AssetSharingEntry> findAll(
		int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of asset sharing entries
	 */
	public static List<AssetSharingEntry> findAll(
		int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the asset sharing entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of asset sharing entries.
	 *
	 * @return the number of asset sharing entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static AssetSharingEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<AssetSharingEntryPersistence, AssetSharingEntryPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AssetSharingEntryPersistence.class);

		ServiceTracker
			<AssetSharingEntryPersistence, AssetSharingEntryPersistence>
				serviceTracker =
					new ServiceTracker
						<AssetSharingEntryPersistence,
						 AssetSharingEntryPersistence>(
							 bundle.getBundleContext(),
							 AssetSharingEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}