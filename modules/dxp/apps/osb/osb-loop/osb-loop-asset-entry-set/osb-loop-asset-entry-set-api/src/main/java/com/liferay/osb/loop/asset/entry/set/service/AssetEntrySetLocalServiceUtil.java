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

package com.liferay.osb.loop.asset.entry.set.service;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AssetEntrySet. This utility wraps
 * <code>com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLocalService
 * @generated
 */
public class AssetEntrySetLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the asset entry set to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntrySetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntrySet the asset entry set
	 * @return the asset entry set that was added
	 */
	public static AssetEntrySet addAssetEntrySet(AssetEntrySet assetEntrySet) {
		return getService().addAssetEntrySet(assetEntrySet);
	}

	public static AssetEntrySet addAssetEntrySet(
			long userId, long parentAssetEntrySetId, long classNameId,
			long classPK, long creatorClassNameId, long creatorClassPK,
			com.liferay.portal.kernel.json.JSONObject payloadJSONObject,
			boolean privateAssetEntrySet, long stickyTime, String title,
			int type, int status)
		throws PortalException {

		return getService().addAssetEntrySet(
			userId, parentAssetEntrySetId, classNameId, classPK,
			creatorClassNameId, creatorClassPK, payloadJSONObject,
			privateAssetEntrySet, stickyTime, title, type, status);
	}

	public static com.liferay.portal.kernel.json.JSONObject addFileAttachment(
			long userId, java.io.File file)
		throws PortalException {

		return getService().addFileAttachment(userId, file);
	}

	/**
	 * Creates a new asset entry set with the primary key. Does not add the asset entry set to the database.
	 *
	 * @param assetEntrySetId the primary key for the new asset entry set
	 * @return the new asset entry set
	 */
	public static AssetEntrySet createAssetEntrySet(long assetEntrySetId) {
		return getService().createAssetEntrySet(assetEntrySetId);
	}

	/**
	 * Deletes the asset entry set from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntrySetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntrySet the asset entry set
	 * @return the asset entry set that was removed
	 * @throws PortalException
	 */
	public static AssetEntrySet deleteAssetEntrySet(AssetEntrySet assetEntrySet)
		throws PortalException {

		return getService().deleteAssetEntrySet(assetEntrySet);
	}

	/**
	 * Deletes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntrySetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set that was removed
	 * @throws PortalException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet deleteAssetEntrySet(long assetEntrySetId)
		throws PortalException {

		return getService().deleteAssetEntrySet(assetEntrySetId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetModelImpl</code>.
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

	public static AssetEntrySet fetchAssetEntrySet(long assetEntrySetId) {
		return getService().fetchAssetEntrySet(assetEntrySetId);
	}

	public static AssetEntrySet fetchAssetEntrySet(
		long classNameId, long classPK, String title) {

		return getService().fetchAssetEntrySet(classNameId, classPK, title);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the asset entry set with the primary key.
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set
	 * @throws PortalException if a asset entry set with the primary key could not be found
	 */
	public static AssetEntrySet getAssetEntrySet(long assetEntrySetId)
		throws PortalException {

		return getService().getAssetEntrySet(assetEntrySetId);
	}

	/**
	 * Returns a range of all the asset entry sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of asset entry sets
	 */
	public static List<AssetEntrySet> getAssetEntrySets(int start, int end) {
		return getService().getAssetEntrySets(start, end);
	}

	public static List<AssetEntrySet> getAssetEntrySets(
		long classNameId, long classPK) {

		return getService().getAssetEntrySets(classNameId, classPK);
	}

	/**
	 * Returns the number of asset entry sets.
	 *
	 * @return the number of asset entry sets
	 */
	public static int getAssetEntrySetsCount() {
		return getService().getAssetEntrySetsCount();
	}

	public static int getAssetEntrySetsCount(
		long classNameId, long classPK, int type) {

		return getService().getAssetEntrySetsCount(classNameId, classPK, type);
	}

	public static List<AssetEntrySet> getChildAssetEntrySets(
		long parentAssetEntrySetId) {

		return getService().getChildAssetEntrySets(parentAssetEntrySetId);
	}

	public static int getChildAssetEntrySetsCount(long parentAssetEntrySetId) {
		return getService().getChildAssetEntrySetsCount(parentAssetEntrySetId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static List<AssetEntrySet> getNewChildAssetEntrySets(
			long createTime, long parentAssetEntrySetId, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException {

		return getService().getNewChildAssetEntrySets(
			createTime, parentAssetEntrySetId, start, end, orderByComparator);
	}

	public static List<AssetEntrySet> getOldChildAssetEntrySets(
			long createTime, long parentAssetEntrySetId, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException {

		return getService().getOldChildAssetEntrySets(
			createTime, parentAssetEntrySetId, start, end, orderByComparator);
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

	public static AssetEntrySet likeAssetEntrySet(
			long userId, long assetEntrySetId)
		throws PortalException {

		return getService().likeAssetEntrySet(userId, assetEntrySetId);
	}

	public static AssetEntrySet unlikeAssetEntrySet(
			long userId, long assetEntrySetId)
		throws PortalException {

		return getService().unlikeAssetEntrySet(userId, assetEntrySetId);
	}

	public static void updateAssetEntry(
			long assetEntrySetId, String[] assetTagNames)
		throws PortalException {

		getService().updateAssetEntry(assetEntrySetId, assetTagNames);
	}

	/**
	 * Updates the asset entry set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntrySetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntrySet the asset entry set
	 * @return the asset entry set that was updated
	 */
	public static AssetEntrySet updateAssetEntrySet(
		AssetEntrySet assetEntrySet) {

		return getService().updateAssetEntrySet(assetEntrySet);
	}

	public static AssetEntrySet updateAssetEntrySet(
			long assetEntrySetId,
			com.liferay.portal.kernel.json.JSONObject payloadJSONObject,
			boolean privateAssetEntrySet, long stickyTime, String title,
			int type, int status)
		throws PortalException {

		return getService().updateAssetEntrySet(
			assetEntrySetId, payloadJSONObject, privateAssetEntrySet,
			stickyTime, title, type, status);
	}

	public static AssetEntrySetLocalService getService() {
		return _service;
	}

	public static void setService(AssetEntrySetLocalService service) {
		_service = service;
	}

	private static volatile AssetEntrySetLocalService _service;

}