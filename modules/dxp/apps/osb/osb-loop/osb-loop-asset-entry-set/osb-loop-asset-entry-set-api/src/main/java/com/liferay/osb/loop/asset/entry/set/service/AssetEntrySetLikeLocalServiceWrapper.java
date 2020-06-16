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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetEntrySetLikeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikeLocalService
 * @generated
 */
public class AssetEntrySetLikeLocalServiceWrapper
	implements AssetEntrySetLikeLocalService,
			   ServiceWrapper<AssetEntrySetLikeLocalService> {

	public AssetEntrySetLikeLocalServiceWrapper(
		AssetEntrySetLikeLocalService assetEntrySetLikeLocalService) {

		_assetEntrySetLikeLocalService = assetEntrySetLikeLocalService;
	}

	/**
	 * Adds the asset entry set like to the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntrySetLike the asset entry set like
	 * @return the asset entry set like that was added
	 */
	@Override
	public com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
		addAssetEntrySetLike(
			com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
				assetEntrySetLike) {

		return _assetEntrySetLikeLocalService.addAssetEntrySetLike(
			assetEntrySetLike);
	}

	/**
	 * Creates a new asset entry set like with the primary key. Does not add the asset entry set like to the database.
	 *
	 * @param assetEntrySetLikePK the primary key for the new asset entry set like
	 * @return the new asset entry set like
	 */
	@Override
	public com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
		createAssetEntrySetLike(
			com.liferay.osb.loop.asset.entry.set.service.persistence.
				AssetEntrySetLikePK assetEntrySetLikePK) {

		return _assetEntrySetLikeLocalService.createAssetEntrySetLike(
			assetEntrySetLikePK);
	}

	/**
	 * Deletes the asset entry set like from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntrySetLike the asset entry set like
	 * @return the asset entry set like that was removed
	 */
	@Override
	public com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
		deleteAssetEntrySetLike(
			com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
				assetEntrySetLike) {

		return _assetEntrySetLikeLocalService.deleteAssetEntrySetLike(
			assetEntrySetLike);
	}

	/**
	 * Deletes the asset entry set like with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntrySetLikePK the primary key of the asset entry set like
	 * @return the asset entry set like that was removed
	 * @throws PortalException if a asset entry set like with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
			deleteAssetEntrySetLike(
				com.liferay.osb.loop.asset.entry.set.service.persistence.
					AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetEntrySetLikeLocalService.deleteAssetEntrySetLike(
			assetEntrySetLikePK);
	}

	@Override
	public void deleteAssetEntrySetLikes(long assetEntrySetId) {
		_assetEntrySetLikeLocalService.deleteAssetEntrySetLikes(
			assetEntrySetId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetEntrySetLikeLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetEntrySetLikeLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _assetEntrySetLikeLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _assetEntrySetLikeLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _assetEntrySetLikeLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _assetEntrySetLikeLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _assetEntrySetLikeLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
		fetchAssetEntrySetLike(
			com.liferay.osb.loop.asset.entry.set.service.persistence.
				AssetEntrySetLikePK assetEntrySetLikePK) {

		return _assetEntrySetLikeLocalService.fetchAssetEntrySetLike(
			assetEntrySetLikePK);
	}

	@Override
	public com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
		fetchAssetEntrySetLike(
			long assetEntrySetId, long classNameId, long classPK) {

		return _assetEntrySetLikeLocalService.fetchAssetEntrySetLike(
			assetEntrySetId, classNameId, classPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _assetEntrySetLikeLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the asset entry set like with the primary key.
	 *
	 * @param assetEntrySetLikePK the primary key of the asset entry set like
	 * @return the asset entry set like
	 * @throws PortalException if a asset entry set like with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
			getAssetEntrySetLike(
				com.liferay.osb.loop.asset.entry.set.service.persistence.
					AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetEntrySetLikeLocalService.getAssetEntrySetLike(
			assetEntrySetLikePK);
	}

	@Override
	public int getAssetEntrySetLikeCount(long assetEntrySetId) {
		return _assetEntrySetLikeLocalService.getAssetEntrySetLikeCount(
			assetEntrySetId);
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
	@Override
	public java.util.List
		<com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike>
			getAssetEntrySetLikes(int start, int end) {

		return _assetEntrySetLikeLocalService.getAssetEntrySetLikes(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike>
			getAssetEntrySetLikes(
				long assetEntrySetId, long classNameId, long classPK, int start,
				int end) {

		return _assetEntrySetLikeLocalService.getAssetEntrySetLikes(
			assetEntrySetId, classNameId, classPK, start, end);
	}

	/**
	 * Returns the number of asset entry set likes.
	 *
	 * @return the number of asset entry set likes
	 */
	@Override
	public int getAssetEntrySetLikesCount() {
		return _assetEntrySetLikeLocalService.getAssetEntrySetLikesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _assetEntrySetLikeLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _assetEntrySetLikeLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetEntrySetLikeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the asset entry set like in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntrySetLike the asset entry set like
	 * @return the asset entry set like that was updated
	 */
	@Override
	public com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
		updateAssetEntrySetLike(
			com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike
				assetEntrySetLike) {

		return _assetEntrySetLikeLocalService.updateAssetEntrySetLike(
			assetEntrySetLike);
	}

	@Override
	public AssetEntrySetLikeLocalService getWrappedService() {
		return _assetEntrySetLikeLocalService;
	}

	@Override
	public void setWrappedService(
		AssetEntrySetLikeLocalService assetEntrySetLikeLocalService) {

		_assetEntrySetLikeLocalService = assetEntrySetLikeLocalService;
	}

	private AssetEntrySetLikeLocalService _assetEntrySetLikeLocalService;

}