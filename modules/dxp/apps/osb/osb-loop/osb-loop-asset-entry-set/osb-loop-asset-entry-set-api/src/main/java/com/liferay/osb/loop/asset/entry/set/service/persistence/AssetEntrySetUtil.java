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

package com.liferay.osb.loop.asset.entry.set.service.persistence;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the asset entry set service. This utility wraps <code>com.liferay.osb.loop.asset.entry.set.service.persistence.impl.AssetEntrySetPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetPersistence
 * @generated
 */
public class AssetEntrySetUtil {

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
	public static void clearCache(AssetEntrySet assetEntrySet) {
		getPersistence().clearCache(assetEntrySet);
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
	public static Map<Serializable, AssetEntrySet> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AssetEntrySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetEntrySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetEntrySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetEntrySet update(AssetEntrySet assetEntrySet) {
		return getPersistence().update(assetEntrySet);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetEntrySet update(
		AssetEntrySet assetEntrySet, ServiceContext serviceContext) {

		return getPersistence().update(assetEntrySet, serviceContext);
	}

	/**
	 * Returns all the asset entry sets where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the matching asset entry sets
	 */
	public static List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId) {

		return getPersistence().findByParentAssetEntrySetId(
			parentAssetEntrySetId);
	}

	/**
	 * Returns a range of all the asset entry sets where parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end) {

		return getPersistence().findByParentAssetEntrySetId(
			parentAssetEntrySetId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().findByParentAssetEntrySetId(
			parentAssetEntrySetId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByParentAssetEntrySetId(
			parentAssetEntrySetId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByParentAssetEntrySetId_First(
			long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByParentAssetEntrySetId_First(
			parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByParentAssetEntrySetId_First(
		long parentAssetEntrySetId,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByParentAssetEntrySetId_First(
			parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByParentAssetEntrySetId_Last(
			long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByParentAssetEntrySetId_Last(
			parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByParentAssetEntrySetId_Last(
		long parentAssetEntrySetId,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByParentAssetEntrySetId_Last(
			parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] findByParentAssetEntrySetId_PrevAndNext(
			long assetEntrySetId, long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByParentAssetEntrySetId_PrevAndNext(
			assetEntrySetId, parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByParentAssetEntrySetId(
		long parentAssetEntrySetId) {

		return getPersistence().filterFindByParentAssetEntrySetId(
			parentAssetEntrySetId);
	}

	/**
	 * Returns a range of all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end) {

		return getPersistence().filterFindByParentAssetEntrySetId(
			parentAssetEntrySetId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets that the user has permissions to view where parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().filterFindByParentAssetEntrySetId(
			parentAssetEntrySetId, start, end, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] filterFindByParentAssetEntrySetId_PrevAndNext(
			long assetEntrySetId, long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().filterFindByParentAssetEntrySetId_PrevAndNext(
			assetEntrySetId, parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Removes all the asset entry sets where parentAssetEntrySetId = &#63; from the database.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 */
	public static void removeByParentAssetEntrySetId(
		long parentAssetEntrySetId) {

		getPersistence().removeByParentAssetEntrySetId(parentAssetEntrySetId);
	}

	/**
	 * Returns the number of asset entry sets where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the number of matching asset entry sets
	 */
	public static int countByParentAssetEntrySetId(long parentAssetEntrySetId) {
		return getPersistence().countByParentAssetEntrySetId(
			parentAssetEntrySetId);
	}

	/**
	 * Returns the number of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the number of matching asset entry sets that the user has permission to view
	 */
	public static int filterCountByParentAssetEntrySetId(
		long parentAssetEntrySetId) {

		return getPersistence().filterCountByParentAssetEntrySetId(
			parentAssetEntrySetId);
	}

	/**
	 * Returns all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the matching asset entry sets
	 */
	public static List<AssetEntrySet> findByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId) {

		return getPersistence().findByGtCT_PAESI(
			createTime, parentAssetEntrySetId);
	}

	/**
	 * Returns a range of all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end) {

		return getPersistence().findByGtCT_PAESI(
			createTime, parentAssetEntrySetId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().findByGtCT_PAESI(
			createTime, parentAssetEntrySetId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGtCT_PAESI(
			createTime, parentAssetEntrySetId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByGtCT_PAESI_First(
			long createTime, long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByGtCT_PAESI_First(
			createTime, parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the first asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByGtCT_PAESI_First(
		long createTime, long parentAssetEntrySetId,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByGtCT_PAESI_First(
			createTime, parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByGtCT_PAESI_Last(
			long createTime, long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByGtCT_PAESI_Last(
			createTime, parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByGtCT_PAESI_Last(
		long createTime, long parentAssetEntrySetId,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByGtCT_PAESI_Last(
			createTime, parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] findByGtCT_PAESI_PrevAndNext(
			long assetEntrySetId, long createTime, long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByGtCT_PAESI_PrevAndNext(
			assetEntrySetId, createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	 * Returns all the asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId) {

		return getPersistence().filterFindByGtCT_PAESI(
			createTime, parentAssetEntrySetId);
	}

	/**
	 * Returns a range of all the asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end) {

		return getPersistence().filterFindByGtCT_PAESI(
			createTime, parentAssetEntrySetId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets that the user has permissions to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().filterFindByGtCT_PAESI(
			createTime, parentAssetEntrySetId, start, end, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] filterFindByGtCT_PAESI_PrevAndNext(
			long assetEntrySetId, long createTime, long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().filterFindByGtCT_PAESI_PrevAndNext(
			assetEntrySetId, createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	 * Removes all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63; from the database.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 */
	public static void removeByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId) {

		getPersistence().removeByGtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	 * Returns the number of asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the number of matching asset entry sets
	 */
	public static int countByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId) {

		return getPersistence().countByGtCT_PAESI(
			createTime, parentAssetEntrySetId);
	}

	/**
	 * Returns the number of asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the number of matching asset entry sets that the user has permission to view
	 */
	public static int filterCountByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId) {

		return getPersistence().filterCountByGtCT_PAESI(
			createTime, parentAssetEntrySetId);
	}

	/**
	 * Returns all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the matching asset entry sets
	 */
	public static List<AssetEntrySet> findByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId) {

		return getPersistence().findByLtCT_PAESI(
			createTime, parentAssetEntrySetId);
	}

	/**
	 * Returns a range of all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end) {

		return getPersistence().findByLtCT_PAESI(
			createTime, parentAssetEntrySetId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().findByLtCT_PAESI(
			createTime, parentAssetEntrySetId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByLtCT_PAESI(
			createTime, parentAssetEntrySetId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByLtCT_PAESI_First(
			long createTime, long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByLtCT_PAESI_First(
			createTime, parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the first asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByLtCT_PAESI_First(
		long createTime, long parentAssetEntrySetId,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByLtCT_PAESI_First(
			createTime, parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByLtCT_PAESI_Last(
			long createTime, long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByLtCT_PAESI_Last(
			createTime, parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByLtCT_PAESI_Last(
		long createTime, long parentAssetEntrySetId,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByLtCT_PAESI_Last(
			createTime, parentAssetEntrySetId, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] findByLtCT_PAESI_PrevAndNext(
			long assetEntrySetId, long createTime, long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByLtCT_PAESI_PrevAndNext(
			assetEntrySetId, createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	 * Returns all the asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId) {

		return getPersistence().filterFindByLtCT_PAESI(
			createTime, parentAssetEntrySetId);
	}

	/**
	 * Returns a range of all the asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end) {

		return getPersistence().filterFindByLtCT_PAESI(
			createTime, parentAssetEntrySetId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets that the user has permissions to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().filterFindByLtCT_PAESI(
			createTime, parentAssetEntrySetId, start, end, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] filterFindByLtCT_PAESI_PrevAndNext(
			long assetEntrySetId, long createTime, long parentAssetEntrySetId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().filterFindByLtCT_PAESI_PrevAndNext(
			assetEntrySetId, createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	 * Removes all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63; from the database.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 */
	public static void removeByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId) {

		getPersistence().removeByLtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	 * Returns the number of asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the number of matching asset entry sets
	 */
	public static int countByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId) {

		return getPersistence().countByLtCT_PAESI(
			createTime, parentAssetEntrySetId);
	}

	/**
	 * Returns the number of asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the number of matching asset entry sets that the user has permission to view
	 */
	public static int filterCountByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId) {

		return getPersistence().filterCountByLtCT_PAESI(
			createTime, parentAssetEntrySetId);
	}

	/**
	 * Returns all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @return the matching asset entry sets
	 */
	public static List<AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId) {

		return getPersistence().findByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId);
	}

	/**
	 * Returns a range of all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start,
		int end) {

		return getPersistence().findByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().findByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByPAESI_CCNI_First(
			long parentAssetEntrySetId, long creatorClassNameId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByPAESI_CCNI_First(
			parentAssetEntrySetId, creatorClassNameId, orderByComparator);
	}

	/**
	 * Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByPAESI_CCNI_First(
		long parentAssetEntrySetId, long creatorClassNameId,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByPAESI_CCNI_First(
			parentAssetEntrySetId, creatorClassNameId, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByPAESI_CCNI_Last(
			long parentAssetEntrySetId, long creatorClassNameId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByPAESI_CCNI_Last(
			parentAssetEntrySetId, creatorClassNameId, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByPAESI_CCNI_Last(
		long parentAssetEntrySetId, long creatorClassNameId,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByPAESI_CCNI_Last(
			parentAssetEntrySetId, creatorClassNameId, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] findByPAESI_CCNI_PrevAndNext(
			long assetEntrySetId, long parentAssetEntrySetId,
			long creatorClassNameId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByPAESI_CCNI_PrevAndNext(
			assetEntrySetId, parentAssetEntrySetId, creatorClassNameId,
			orderByComparator);
	}

	/**
	 * Returns all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @return the matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId) {

		return getPersistence().filterFindByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId);
	}

	/**
	 * Returns a range of all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start,
		int end) {

		return getPersistence().filterFindByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets that the user has permissions to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().filterFindByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId, start, end,
			orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] filterFindByPAESI_CCNI_PrevAndNext(
			long assetEntrySetId, long parentAssetEntrySetId,
			long creatorClassNameId,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().filterFindByPAESI_CCNI_PrevAndNext(
			assetEntrySetId, parentAssetEntrySetId, creatorClassNameId,
			orderByComparator);
	}

	/**
	 * Removes all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; from the database.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 */
	public static void removeByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId) {

		getPersistence().removeByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId);
	}

	/**
	 * Returns the number of asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @return the number of matching asset entry sets
	 */
	public static int countByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId) {

		return getPersistence().countByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId);
	}

	/**
	 * Returns the number of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @return the number of matching asset entry sets that the user has permission to view
	 */
	public static int filterCountByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId) {

		return getPersistence().filterCountByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId);
	}

	/**
	 * Returns all the asset entry sets where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching asset entry sets
	 */
	public static List<AssetEntrySet> findByCNI_CPK(
		long classNameId, long classPK) {

		return getPersistence().findByCNI_CPK(classNameId, classPK);
	}

	/**
	 * Returns a range of all the asset entry sets where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByCNI_CPK(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByCNI_CPK(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByCNI_CPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().findByCNI_CPK(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByCNI_CPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCNI_CPK(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset entry set in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByCNI_CPK_First(
			long classNameId, long classPK,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByCNI_CPK_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first asset entry set in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByCNI_CPK_First(
		long classNameId, long classPK,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByCNI_CPK_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByCNI_CPK_Last(
			long classNameId, long classPK,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByCNI_CPK_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByCNI_CPK_Last(
		long classNameId, long classPK,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByCNI_CPK_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] findByCNI_CPK_PrevAndNext(
			long assetEntrySetId, long classNameId, long classPK,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByCNI_CPK_PrevAndNext(
			assetEntrySetId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns all the asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByCNI_CPK(
		long classNameId, long classPK) {

		return getPersistence().filterFindByCNI_CPK(classNameId, classPK);
	}

	/**
	 * Returns a range of all the asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByCNI_CPK(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().filterFindByCNI_CPK(
			classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets that the user has permissions to view where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByCNI_CPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().filterFindByCNI_CPK(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] filterFindByCNI_CPK_PrevAndNext(
			long assetEntrySetId, long classNameId, long classPK,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().filterFindByCNI_CPK_PrevAndNext(
			assetEntrySetId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the asset entry sets where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByCNI_CPK(long classNameId, long classPK) {
		getPersistence().removeByCNI_CPK(classNameId, classPK);
	}

	/**
	 * Returns the number of asset entry sets where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching asset entry sets
	 */
	public static int countByCNI_CPK(long classNameId, long classPK) {
		return getPersistence().countByCNI_CPK(classNameId, classPK);
	}

	/**
	 * Returns the number of asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching asset entry sets that the user has permission to view
	 */
	public static int filterCountByCNI_CPK(long classNameId, long classPK) {
		return getPersistence().filterCountByCNI_CPK(classNameId, classPK);
	}

	/**
	 * Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or throws a <code>NoSuchAssetEntrySetException</code> if it could not be found.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param creatorClassPK the creator class pk
	 * @return the matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByPAESI_CCNI_CCPK(
			long parentAssetEntrySetId, long creatorClassNameId,
			long creatorClassPK)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByPAESI_CCNI_CCPK(
			parentAssetEntrySetId, creatorClassNameId, creatorClassPK);
	}

	/**
	 * Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param creatorClassPK the creator class pk
	 * @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByPAESI_CCNI_CCPK(
		long parentAssetEntrySetId, long creatorClassNameId,
		long creatorClassPK) {

		return getPersistence().fetchByPAESI_CCNI_CCPK(
			parentAssetEntrySetId, creatorClassNameId, creatorClassPK);
	}

	/**
	 * Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param creatorClassPK the creator class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByPAESI_CCNI_CCPK(
		long parentAssetEntrySetId, long creatorClassNameId,
		long creatorClassPK, boolean useFinderCache) {

		return getPersistence().fetchByPAESI_CCNI_CCPK(
			parentAssetEntrySetId, creatorClassNameId, creatorClassPK,
			useFinderCache);
	}

	/**
	 * Removes the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; from the database.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param creatorClassPK the creator class pk
	 * @return the asset entry set that was removed
	 */
	public static AssetEntrySet removeByPAESI_CCNI_CCPK(
			long parentAssetEntrySetId, long creatorClassNameId,
			long creatorClassPK)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().removeByPAESI_CCNI_CCPK(
			parentAssetEntrySetId, creatorClassNameId, creatorClassPK);
	}

	/**
	 * Returns the number of asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param creatorClassPK the creator class pk
	 * @return the number of matching asset entry sets
	 */
	public static int countByPAESI_CCNI_CCPK(
		long parentAssetEntrySetId, long creatorClassNameId,
		long creatorClassPK) {

		return getPersistence().countByPAESI_CCNI_CCPK(
			parentAssetEntrySetId, creatorClassNameId, creatorClassPK);
	}

	/**
	 * Returns the asset entry set where classNameId = &#63; and classPK = &#63; and title = &#63; or throws a <code>NoSuchAssetEntrySetException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param title the title
	 * @return the matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByCNI_CPK_Title(
			long classNameId, long classPK, String title)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByCNI_CPK_Title(
			classNameId, classPK, title);
	}

	/**
	 * Returns the asset entry set where classNameId = &#63; and classPK = &#63; and title = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param title the title
	 * @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByCNI_CPK_Title(
		long classNameId, long classPK, String title) {

		return getPersistence().fetchByCNI_CPK_Title(
			classNameId, classPK, title);
	}

	/**
	 * Returns the asset entry set where classNameId = &#63; and classPK = &#63; and title = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param title the title
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByCNI_CPK_Title(
		long classNameId, long classPK, String title, boolean useFinderCache) {

		return getPersistence().fetchByCNI_CPK_Title(
			classNameId, classPK, title, useFinderCache);
	}

	/**
	 * Removes the asset entry set where classNameId = &#63; and classPK = &#63; and title = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param title the title
	 * @return the asset entry set that was removed
	 */
	public static AssetEntrySet removeByCNI_CPK_Title(
			long classNameId, long classPK, String title)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().removeByCNI_CPK_Title(
			classNameId, classPK, title);
	}

	/**
	 * Returns the number of asset entry sets where classNameId = &#63; and classPK = &#63; and title = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param title the title
	 * @return the number of matching asset entry sets
	 */
	public static int countByCNI_CPK_Title(
		long classNameId, long classPK, String title) {

		return getPersistence().countByCNI_CPK_Title(
			classNameId, classPK, title);
	}

	/**
	 * Returns all the asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching asset entry sets
	 */
	public static List<AssetEntrySet> findByCNI_CPK_Type(
		long classNameId, long classPK, int type) {

		return getPersistence().findByCNI_CPK_Type(classNameId, classPK, type);
	}

	/**
	 * Returns a range of all the asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByCNI_CPK_Type(
		long classNameId, long classPK, int type, int start, int end) {

		return getPersistence().findByCNI_CPK_Type(
			classNameId, classPK, type, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByCNI_CPK_Type(
		long classNameId, long classPK, int type, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().findByCNI_CPK_Type(
			classNameId, classPK, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry sets
	 */
	public static List<AssetEntrySet> findByCNI_CPK_Type(
		long classNameId, long classPK, int type, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCNI_CPK_Type(
			classNameId, classPK, type, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first asset entry set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByCNI_CPK_Type_First(
			long classNameId, long classPK, int type,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByCNI_CPK_Type_First(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the first asset entry set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByCNI_CPK_Type_First(
		long classNameId, long classPK, int type,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByCNI_CPK_Type_First(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set
	 * @throws NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 */
	public static AssetEntrySet findByCNI_CPK_Type_Last(
			long classNameId, long classPK, int type,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByCNI_CPK_Type_Last(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the last asset entry set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 */
	public static AssetEntrySet fetchByCNI_CPK_Type_Last(
		long classNameId, long classPK, int type,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().fetchByCNI_CPK_Type_Last(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] findByCNI_CPK_Type_PrevAndNext(
			long assetEntrySetId, long classNameId, long classPK, int type,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByCNI_CPK_Type_PrevAndNext(
			assetEntrySetId, classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns all the asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByCNI_CPK_Type(
		long classNameId, long classPK, int type) {

		return getPersistence().filterFindByCNI_CPK_Type(
			classNameId, classPK, type);
	}

	/**
	 * Returns a range of all the asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByCNI_CPK_Type(
		long classNameId, long classPK, int type, int start, int end) {

		return getPersistence().filterFindByCNI_CPK_Type(
			classNameId, classPK, type, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets that the user has permissions to view where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets that the user has permission to view
	 */
	public static List<AssetEntrySet> filterFindByCNI_CPK_Type(
		long classNameId, long classPK, int type, int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().filterFindByCNI_CPK_Type(
			classNameId, classPK, type, start, end, orderByComparator);
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet[] filterFindByCNI_CPK_Type_PrevAndNext(
			long assetEntrySetId, long classNameId, long classPK, int type,
			OrderByComparator<AssetEntrySet> orderByComparator)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().filterFindByCNI_CPK_Type_PrevAndNext(
			assetEntrySetId, classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Removes all the asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 */
	public static void removeByCNI_CPK_Type(
		long classNameId, long classPK, int type) {

		getPersistence().removeByCNI_CPK_Type(classNameId, classPK, type);
	}

	/**
	 * Returns the number of asset entry sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching asset entry sets
	 */
	public static int countByCNI_CPK_Type(
		long classNameId, long classPK, int type) {

		return getPersistence().countByCNI_CPK_Type(classNameId, classPK, type);
	}

	/**
	 * Returns the number of asset entry sets that the user has permission to view where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching asset entry sets that the user has permission to view
	 */
	public static int filterCountByCNI_CPK_Type(
		long classNameId, long classPK, int type) {

		return getPersistence().filterCountByCNI_CPK_Type(
			classNameId, classPK, type);
	}

	/**
	 * Caches the asset entry set in the entity cache if it is enabled.
	 *
	 * @param assetEntrySet the asset entry set
	 */
	public static void cacheResult(AssetEntrySet assetEntrySet) {
		getPersistence().cacheResult(assetEntrySet);
	}

	/**
	 * Caches the asset entry sets in the entity cache if it is enabled.
	 *
	 * @param assetEntrySets the asset entry sets
	 */
	public static void cacheResult(List<AssetEntrySet> assetEntrySets) {
		getPersistence().cacheResult(assetEntrySets);
	}

	/**
	 * Creates a new asset entry set with the primary key. Does not add the asset entry set to the database.
	 *
	 * @param assetEntrySetId the primary key for the new asset entry set
	 * @return the new asset entry set
	 */
	public static AssetEntrySet create(long assetEntrySetId) {
		return getPersistence().create(assetEntrySetId);
	}

	/**
	 * Removes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set that was removed
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet remove(long assetEntrySetId)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().remove(assetEntrySetId);
	}

	public static AssetEntrySet updateImpl(AssetEntrySet assetEntrySet) {
		return getPersistence().updateImpl(assetEntrySet);
	}

	/**
	 * Returns the asset entry set with the primary key or throws a <code>NoSuchAssetEntrySetException</code> if it could not be found.
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set
	 * @throws NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet findByPrimaryKey(long assetEntrySetId)
		throws com.liferay.osb.loop.asset.entry.set.exception.
			NoSuchAssetEntrySetException {

		return getPersistence().findByPrimaryKey(assetEntrySetId);
	}

	/**
	 * Returns the asset entry set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set, or <code>null</code> if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet fetchByPrimaryKey(long assetEntrySetId) {
		return getPersistence().fetchByPrimaryKey(assetEntrySetId);
	}

	/**
	 * Returns all the asset entry sets.
	 *
	 * @return the asset entry sets
	 */
	public static List<AssetEntrySet> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the asset entry sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of asset entry sets
	 */
	public static List<AssetEntrySet> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entry sets
	 */
	public static List<AssetEntrySet> findAll(
		int start, int end,
		OrderByComparator<AssetEntrySet> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of asset entry sets
	 */
	public static List<AssetEntrySet> findAll(
		int start, int end, OrderByComparator<AssetEntrySet> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the asset entry sets from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of asset entry sets.
	 *
	 * @return the number of asset entry sets
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AssetEntrySetPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(AssetEntrySetPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile AssetEntrySetPersistence _persistence;

}