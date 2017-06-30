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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AssetReceiptRedeemTokenLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceiptRedeemTokenLocalService
 * @generated
 */
public class AssetReceiptRedeemTokenLocalServiceWrapper
	implements AssetReceiptRedeemTokenLocalService,
		ServiceWrapper<AssetReceiptRedeemTokenLocalService> {
	public AssetReceiptRedeemTokenLocalServiceWrapper(
		AssetReceiptRedeemTokenLocalService assetReceiptRedeemTokenLocalService) {
		_assetReceiptRedeemTokenLocalService = assetReceiptRedeemTokenLocalService;
	}

	/**
	* Adds the asset receipt redeem token to the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptRedeemToken the asset receipt redeem token
	* @return the asset receipt redeem token that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken addAssetReceiptRedeemToken(
		com.liferay.osb.model.AssetReceiptRedeemToken assetReceiptRedeemToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.addAssetReceiptRedeemToken(assetReceiptRedeemToken);
	}

	/**
	* Creates a new asset receipt redeem token with the primary key. Does not add the asset receipt redeem token to the database.
	*
	* @param AssetReceiptRedeemTokenId the primary key for the new asset receipt redeem token
	* @return the new asset receipt redeem token
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken createAssetReceiptRedeemToken(
		long AssetReceiptRedeemTokenId) {
		return _assetReceiptRedeemTokenLocalService.createAssetReceiptRedeemToken(AssetReceiptRedeemTokenId);
	}

	/**
	* Deletes the asset receipt redeem token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	* @return the asset receipt redeem token that was removed
	* @throws PortalException if a asset receipt redeem token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken deleteAssetReceiptRedeemToken(
		long AssetReceiptRedeemTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.deleteAssetReceiptRedeemToken(AssetReceiptRedeemTokenId);
	}

	/**
	* Deletes the asset receipt redeem token from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptRedeemToken the asset receipt redeem token
	* @return the asset receipt redeem token that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken deleteAssetReceiptRedeemToken(
		com.liferay.osb.model.AssetReceiptRedeemToken assetReceiptRedeemToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.deleteAssetReceiptRedeemToken(assetReceiptRedeemToken);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetReceiptRedeemTokenLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetReceiptRedeemToken fetchAssetReceiptRedeemToken(
		long AssetReceiptRedeemTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.fetchAssetReceiptRedeemToken(AssetReceiptRedeemTokenId);
	}

	/**
	* Returns the asset receipt redeem token with the primary key.
	*
	* @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	* @return the asset receipt redeem token
	* @throws PortalException if a asset receipt redeem token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken getAssetReceiptRedeemToken(
		long AssetReceiptRedeemTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.getAssetReceiptRedeemToken(AssetReceiptRedeemTokenId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset receipt redeem tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt redeem tokens
	* @param end the upper bound of the range of asset receipt redeem tokens (not inclusive)
	* @return the range of asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptRedeemToken> getAssetReceiptRedeemTokens(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.getAssetReceiptRedeemTokens(start,
			end);
	}

	/**
	* Returns the number of asset receipt redeem tokens.
	*
	* @return the number of asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetReceiptRedeemTokensCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.getAssetReceiptRedeemTokensCount();
	}

	/**
	* Updates the asset receipt redeem token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptRedeemToken the asset receipt redeem token
	* @return the asset receipt redeem token that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken updateAssetReceiptRedeemToken(
		com.liferay.osb.model.AssetReceiptRedeemToken assetReceiptRedeemToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.updateAssetReceiptRedeemToken(assetReceiptRedeemToken);
	}

	/**
	* Updates the asset receipt redeem token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptRedeemToken the asset receipt redeem token
	* @param merge whether to merge the asset receipt redeem token with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset receipt redeem token that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken updateAssetReceiptRedeemToken(
		com.liferay.osb.model.AssetReceiptRedeemToken assetReceiptRedeemToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.updateAssetReceiptRedeemToken(assetReceiptRedeemToken,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetReceiptRedeemTokenLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetReceiptRedeemTokenLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetReceiptRedeemTokenLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.AssetReceiptRedeemToken addAssetReceiptRedeemToken(
		long userId, long classNameId, long classPK,
		java.lang.String redeemEmailAddress)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.addAssetReceiptRedeemToken(userId,
			classNameId, classPK, redeemEmailAddress);
	}

	public com.liferay.osb.model.AssetReceiptRedeemToken fetchUnredeemedAssetReceiptRedeemToken(
		java.lang.String redeemEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.fetchUnredeemedAssetReceiptRedeemToken(redeemEmailAddress);
	}

	public com.liferay.osb.model.AssetReceiptRedeemToken redeemAssetReceiptRedeemToken(
		java.lang.String redeemEmailAddress)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemTokenLocalService.redeemAssetReceiptRedeemToken(redeemEmailAddress);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetReceiptRedeemTokenLocalService getWrappedAssetReceiptRedeemTokenLocalService() {
		return _assetReceiptRedeemTokenLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetReceiptRedeemTokenLocalService(
		AssetReceiptRedeemTokenLocalService assetReceiptRedeemTokenLocalService) {
		_assetReceiptRedeemTokenLocalService = assetReceiptRedeemTokenLocalService;
	}

	public AssetReceiptRedeemTokenLocalService getWrappedService() {
		return _assetReceiptRedeemTokenLocalService;
	}

	public void setWrappedService(
		AssetReceiptRedeemTokenLocalService assetReceiptRedeemTokenLocalService) {
		_assetReceiptRedeemTokenLocalService = assetReceiptRedeemTokenLocalService;
	}

	private AssetReceiptRedeemTokenLocalService _assetReceiptRedeemTokenLocalService;
}