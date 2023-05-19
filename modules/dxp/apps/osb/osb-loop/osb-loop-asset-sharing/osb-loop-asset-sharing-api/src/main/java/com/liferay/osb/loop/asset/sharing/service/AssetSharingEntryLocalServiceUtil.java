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

package com.liferay.osb.loop.asset.sharing.service;

import com.liferay.osb.loop.asset.sharing.model.AssetSharingEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Provides the local service utility for AssetSharingEntry. This utility wraps
 * <code>com.liferay.osb.loop.asset.sharing.service.impl.AssetSharingEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetSharingEntryLocalService
 * @generated
 */
public class AssetSharingEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.asset.sharing.service.impl.AssetSharingEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addAssetSharingEntries(
		long classNameId, long classPK,
		Map<Long, Set<Long>> sharedToClassPKsMap) {

		getService().addAssetSharingEntries(
			classNameId, classPK, sharedToClassPKsMap);
	}

	/**
	 * Adds the asset sharing entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetSharingEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetSharingEntry the asset sharing entry
	 * @return the asset sharing entry that was added
	 */
	public static AssetSharingEntry addAssetSharingEntry(
		AssetSharingEntry assetSharingEntry) {

		return getService().addAssetSharingEntry(assetSharingEntry);
	}

	public static void addAssetSharingEntry(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK) {

		getService().addAssetSharingEntry(
			classNameId, classPK, sharedToClassNameId, sharedToClassPK);
	}

	/**
	 * Creates a new asset sharing entry with the primary key. Does not add the asset sharing entry to the database.
	 *
	 * @param assetSharingEntryPK the primary key for the new asset sharing entry
	 * @return the new asset sharing entry
	 */
	public static AssetSharingEntry createAssetSharingEntry(
		com.liferay.osb.loop.asset.sharing.service.persistence.
			AssetSharingEntryPK assetSharingEntryPK) {

		return getService().createAssetSharingEntry(assetSharingEntryPK);
	}

	public static void deleteAssetSharingEntries(
		long classNameId, long classPK) {

		getService().deleteAssetSharingEntries(classNameId, classPK);
	}

	/**
	 * Deletes the asset sharing entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetSharingEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetSharingEntry the asset sharing entry
	 * @return the asset sharing entry that was removed
	 */
	public static AssetSharingEntry deleteAssetSharingEntry(
		AssetSharingEntry assetSharingEntry) {

		return getService().deleteAssetSharingEntry(assetSharingEntry);
	}

	/**
	 * Deletes the asset sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetSharingEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetSharingEntryPK the primary key of the asset sharing entry
	 * @return the asset sharing entry that was removed
	 * @throws PortalException if a asset sharing entry with the primary key could not be found
	 */
	public static AssetSharingEntry deleteAssetSharingEntry(
			com.liferay.osb.loop.asset.sharing.service.persistence.
				AssetSharingEntryPK assetSharingEntryPK)
		throws PortalException {

		return getService().deleteAssetSharingEntry(assetSharingEntryPK);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static void deleteSharedToAssetSharingEntries(
		long sharedToClassNameId, long sharedToClassPK) {

		getService().deleteSharedToAssetSharingEntries(
			sharedToClassNameId, sharedToClassPK);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static AssetSharingEntry fetchAssetSharingEntry(
		com.liferay.osb.loop.asset.sharing.service.persistence.
			AssetSharingEntryPK assetSharingEntryPK) {

		return getService().fetchAssetSharingEntry(assetSharingEntryPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the asset sharing entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of asset sharing entries
	 */
	public static List<AssetSharingEntry> getAssetSharingEntries(
		int start, int end) {

		return getService().getAssetSharingEntries(start, end);
	}

	public static List<AssetSharingEntry> getAssetSharingEntries(
		long classNameId, long classPK) {

		return getService().getAssetSharingEntries(classNameId, classPK);
	}

	public static List<AssetSharingEntry> getAssetSharingEntries(
		long classNameId, long classPK, long sharedToClassNameId) {

		return getService().getAssetSharingEntries(
			classNameId, classPK, sharedToClassNameId);
	}

	/**
	 * Returns the number of asset sharing entries.
	 *
	 * @return the number of asset sharing entries
	 */
	public static int getAssetSharingEntriesCount() {
		return getService().getAssetSharingEntriesCount();
	}

	/**
	 * Returns the asset sharing entry with the primary key.
	 *
	 * @param assetSharingEntryPK the primary key of the asset sharing entry
	 * @return the asset sharing entry
	 * @throws PortalException if a asset sharing entry with the primary key could not be found
	 */
	public static AssetSharingEntry getAssetSharingEntry(
			com.liferay.osb.loop.asset.sharing.service.persistence.
				AssetSharingEntryPK assetSharingEntryPK)
		throws PortalException {

		return getService().getAssetSharingEntry(assetSharingEntryPK);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static List<AssetSharingEntry> getSharedToAssetSharingEntries(
		long sharedToClassNameId, long sharedToClassPK, int start, int end) {

		return getService().getSharedToAssetSharingEntries(
			sharedToClassNameId, sharedToClassPK, start, end);
	}

	public static List<AssetSharingEntry> getSharedToAssetSharingEntries(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		int start, int end) {

		return getService().getSharedToAssetSharingEntries(
			classNameId, sharedToClassNameId, sharedToClassPK, start, end);
	}

	public static int getSharedToAssetSharingEntriesCount(
		long sharedToClassNameId, long sharedToClassPK) {

		return getService().getSharedToAssetSharingEntriesCount(
			sharedToClassNameId, sharedToClassPK);
	}

	public static int getSharedToAssetSharingEntriesCount(
		long classNameId, long sharedToClassNameId, long sharedToClassPK) {

		return getService().getSharedToAssetSharingEntriesCount(
			classNameId, sharedToClassNameId, sharedToClassPK);
	}

	/**
	 * Updates the asset sharing entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetSharingEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetSharingEntry the asset sharing entry
	 * @return the asset sharing entry that was updated
	 */
	public static AssetSharingEntry updateAssetSharingEntry(
		AssetSharingEntry assetSharingEntry) {

		return getService().updateAssetSharingEntry(assetSharingEntry);
	}

	public static AssetSharingEntryLocalService getService() {
		return _service;
	}

	public static void setService(AssetSharingEntryLocalService service) {
		_service = service;
	}

	private static volatile AssetSharingEntryLocalService _service;

}