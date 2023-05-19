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

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AssetEntrySetLike. This utility wraps
 * <code>com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetLikeLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikeLocalService
 * @generated
 */
public class AssetEntrySetLikeLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetLikeLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the asset entry set like to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntrySetLikeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntrySetLike the asset entry set like
	 * @return the asset entry set like that was added
	 */
	public static AssetEntrySetLike addAssetEntrySetLike(
		AssetEntrySetLike assetEntrySetLike) {

		return getService().addAssetEntrySetLike(assetEntrySetLike);
	}

	/**
	 * Creates a new asset entry set like with the primary key. Does not add the asset entry set like to the database.
	 *
	 * @param assetEntrySetLikePK the primary key for the new asset entry set like
	 * @return the new asset entry set like
	 */
	public static AssetEntrySetLike createAssetEntrySetLike(
		com.liferay.osb.loop.asset.entry.set.service.persistence.
			AssetEntrySetLikePK assetEntrySetLikePK) {

		return getService().createAssetEntrySetLike(assetEntrySetLikePK);
	}

	/**
	 * Deletes the asset entry set like from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntrySetLikeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntrySetLike the asset entry set like
	 * @return the asset entry set like that was removed
	 */
	public static AssetEntrySetLike deleteAssetEntrySetLike(
		AssetEntrySetLike assetEntrySetLike) {

		return getService().deleteAssetEntrySetLike(assetEntrySetLike);
	}

	/**
	 * Deletes the asset entry set like with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntrySetLikeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntrySetLikePK the primary key of the asset entry set like
	 * @return the asset entry set like that was removed
	 * @throws PortalException if a asset entry set like with the primary key could not be found
	 */
	public static AssetEntrySetLike deleteAssetEntrySetLike(
			com.liferay.osb.loop.asset.entry.set.service.persistence.
				AssetEntrySetLikePK assetEntrySetLikePK)
		throws PortalException {

		return getService().deleteAssetEntrySetLike(assetEntrySetLikePK);
	}

	public static void deleteAssetEntrySetLikes(long assetEntrySetId) {
		getService().deleteAssetEntrySetLikes(assetEntrySetId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl</code>.
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

	public static AssetEntrySetLike fetchAssetEntrySetLike(
		com.liferay.osb.loop.asset.entry.set.service.persistence.
			AssetEntrySetLikePK assetEntrySetLikePK) {

		return getService().fetchAssetEntrySetLike(assetEntrySetLikePK);
	}

	public static AssetEntrySetLike fetchAssetEntrySetLike(
		long assetEntrySetId, long classNameId, long classPK) {

		return getService().fetchAssetEntrySetLike(
			assetEntrySetId, classNameId, classPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the asset entry set like with the primary key.
	 *
	 * @param assetEntrySetLikePK the primary key of the asset entry set like
	 * @return the asset entry set like
	 * @throws PortalException if a asset entry set like with the primary key could not be found
	 */
	public static AssetEntrySetLike getAssetEntrySetLike(
			com.liferay.osb.loop.asset.entry.set.service.persistence.
				AssetEntrySetLikePK assetEntrySetLikePK)
		throws PortalException {

		return getService().getAssetEntrySetLike(assetEntrySetLikePK);
	}

	public static int getAssetEntrySetLikeCount(long assetEntrySetId) {
		return getService().getAssetEntrySetLikeCount(assetEntrySetId);
	}

	/**
	 * Returns a range of all the asset entry set likes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry set likes
	 * @param end the upper bound of the range of asset entry set likes (not inclusive)
	 * @return the range of asset entry set likes
	 */
	public static List<AssetEntrySetLike> getAssetEntrySetLikes(
		int start, int end) {

		return getService().getAssetEntrySetLikes(start, end);
	}

	public static List<AssetEntrySetLike> getAssetEntrySetLikes(
		long assetEntrySetId, long classNameId, long classPK, int start,
		int end) {

		return getService().getAssetEntrySetLikes(
			assetEntrySetId, classNameId, classPK, start, end);
	}

	/**
	 * Returns the number of asset entry set likes.
	 *
	 * @return the number of asset entry set likes
	 */
	public static int getAssetEntrySetLikesCount() {
		return getService().getAssetEntrySetLikesCount();
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

	/**
	 * Updates the asset entry set like in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntrySetLikeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntrySetLike the asset entry set like
	 * @return the asset entry set like that was updated
	 */
	public static AssetEntrySetLike updateAssetEntrySetLike(
		AssetEntrySetLike assetEntrySetLike) {

		return getService().updateAssetEntrySetLike(assetEntrySetLike);
	}

	public static AssetEntrySetLikeLocalService getService() {
		return _service;
	}

	public static void setService(AssetEntrySetLikeLocalService service) {
		_service = service;
	}

	private static volatile AssetEntrySetLikeLocalService _service;

}