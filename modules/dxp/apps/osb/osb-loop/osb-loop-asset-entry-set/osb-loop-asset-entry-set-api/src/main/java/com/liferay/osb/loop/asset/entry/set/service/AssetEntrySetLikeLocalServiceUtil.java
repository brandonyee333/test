/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.loop.asset.entry.set.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for AssetEntrySetLike. This utility wraps
 * {@link com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetLikeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikeLocalService
 * @see com.liferay.osb.loop.asset.entry.set.service.base.AssetEntrySetLikeLocalServiceBaseImpl
 * @see com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetLikeLocalServiceImpl
 * @generated
 */
@ProviderType
public class AssetEntrySetLikeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetLikeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset entry set like to the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLike the asset entry set like
	* @return the asset entry set like that was added
	*/
	public static com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike addAssetEntrySetLike(
		com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike) {
		return getService().addAssetEntrySetLike(assetEntrySetLike);
	}

	/**
	* Creates a new asset entry set like with the primary key. Does not add the asset entry set like to the database.
	*
	* @param assetEntrySetLikePK the primary key for the new asset entry set like
	* @return the new asset entry set like
	*/
	public static com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike createAssetEntrySetLike(
		com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK) {
		return getService().createAssetEntrySetLike(assetEntrySetLikePK);
	}

	/**
	* Deletes the asset entry set like from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLike the asset entry set like
	* @return the asset entry set like that was removed
	*/
	public static com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike deleteAssetEntrySetLike(
		com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike) {
		return getService().deleteAssetEntrySetLike(assetEntrySetLike);
	}

	/**
	* Deletes the asset entry set like with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like that was removed
	* @throws PortalException if a asset entry set like with the primary key could not be found
	*/
	public static com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike deleteAssetEntrySetLike(
		com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAssetEntrySetLike(assetEntrySetLikePK);
	}

	public static void deleteAssetEntrySetLikes(long assetEntrySetId) {
		getService().deleteAssetEntrySetLikes(assetEntrySetId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
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
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike fetchAssetEntrySetLike(
		com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK) {
		return getService().fetchAssetEntrySetLike(assetEntrySetLikePK);
	}

	public static com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike fetchAssetEntrySetLike(
		long assetEntrySetId, long classNameId, long classPK) {
		return getService()
				   .fetchAssetEntrySetLike(assetEntrySetId, classNameId, classPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the asset entry set like with the primary key.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like
	* @throws PortalException if a asset entry set like with the primary key could not be found
	*/
	public static com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike getAssetEntrySetLike(
		com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAssetEntrySetLike(assetEntrySetLikePK);
	}

	public static int getAssetEntrySetLikeCount(long assetEntrySetId) {
		return getService().getAssetEntrySetLikeCount(assetEntrySetId);
	}

	/**
	* Returns a range of all the asset entry set likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @return the range of asset entry set likes
	*/
	public static java.util.List<com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike> getAssetEntrySetLikes(
		int start, int end) {
		return getService().getAssetEntrySetLikes(start, end);
	}

	public static java.util.List<com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike> getAssetEntrySetLikes(
		long assetEntrySetId, long classNameId, long classPK, int start, int end) {
		return getService()
				   .getAssetEntrySetLikes(assetEntrySetId, classNameId,
			classPK, start, end);
	}

	/**
	* Returns the number of asset entry set likes.
	*
	* @return the number of asset entry set likes
	*/
	public static int getAssetEntrySetLikesCount() {
		return getService().getAssetEntrySetLikesCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the asset entry set like in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLike the asset entry set like
	* @return the asset entry set like that was updated
	*/
	public static com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike updateAssetEntrySetLike(
		com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike) {
		return getService().updateAssetEntrySetLike(assetEntrySetLike);
	}

	public static AssetEntrySetLikeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssetEntrySetLikeLocalService, AssetEntrySetLikeLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AssetEntrySetLikeLocalService.class);

		ServiceTracker<AssetEntrySetLikeLocalService, AssetEntrySetLikeLocalService> serviceTracker =
			new ServiceTracker<AssetEntrySetLikeLocalService, AssetEntrySetLikeLocalService>(bundle.getBundleContext(),
				AssetEntrySetLikeLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}