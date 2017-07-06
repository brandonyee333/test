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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetReceiptLicenseLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptLicenseLocalService
 * @generated
 */
@ProviderType
public class AssetReceiptLicenseLocalServiceWrapper
	implements AssetReceiptLicenseLocalService,
		ServiceWrapper<AssetReceiptLicenseLocalService> {
	public AssetReceiptLicenseLocalServiceWrapper(
		AssetReceiptLicenseLocalService assetReceiptLicenseLocalService) {
		_assetReceiptLicenseLocalService = assetReceiptLicenseLocalService;
	}

	/**
	* Adds the asset receipt license to the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @return the asset receipt license that was added
	*/
	@Override
	public com.liferay.osb.model.AssetReceiptLicense addAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense) {
		return _assetReceiptLicenseLocalService.addAssetReceiptLicense(assetReceiptLicense);
	}

	/**
	* Creates a new asset receipt license with the primary key. Does not add the asset receipt license to the database.
	*
	* @param assetReceiptLicenseId the primary key for the new asset receipt license
	* @return the new asset receipt license
	*/
	@Override
	public com.liferay.osb.model.AssetReceiptLicense createAssetReceiptLicense(
		long assetReceiptLicenseId) {
		return _assetReceiptLicenseLocalService.createAssetReceiptLicense(assetReceiptLicenseId);
	}

	/**
	* Deletes the asset receipt license from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @return the asset receipt license that was removed
	*/
	@Override
	public com.liferay.osb.model.AssetReceiptLicense deleteAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense) {
		return _assetReceiptLicenseLocalService.deleteAssetReceiptLicense(assetReceiptLicense);
	}

	/**
	* Deletes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license that was removed
	* @throws PortalException if a asset receipt license with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AssetReceiptLicense deleteAssetReceiptLicense(
		long assetReceiptLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetReceiptLicenseLocalService.deleteAssetReceiptLicense(assetReceiptLicenseId);
	}

	@Override
	public com.liferay.osb.model.AssetReceiptLicense fetchAssetReceiptLicense(
		long assetReceiptLicenseId) {
		return _assetReceiptLicenseLocalService.fetchAssetReceiptLicense(assetReceiptLicenseId);
	}

	/**
	* Returns the asset receipt license with the primary key.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license
	* @throws PortalException if a asset receipt license with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AssetReceiptLicense getAssetReceiptLicense(
		long assetReceiptLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicense(assetReceiptLicenseId);
	}

	/**
	* Updates the asset receipt license in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @return the asset receipt license that was updated
	*/
	@Override
	public com.liferay.osb.model.AssetReceiptLicense updateAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense) {
		return _assetReceiptLicenseLocalService.updateAssetReceiptLicense(assetReceiptLicense);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _assetReceiptLicenseLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetReceiptLicenseLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _assetReceiptLicenseLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetReceiptLicenseLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetReceiptLicenseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of asset receipt licenses.
	*
	* @return the number of asset receipt licenses
	*/
	@Override
	public int getAssetReceiptLicensesCount() {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicensesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetReceiptLicenseLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _assetReceiptLicenseLocalService.getOSGiServiceIdentifier();
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
		return _assetReceiptLicenseLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetReceiptLicenseLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetReceiptLicenseLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the asset receipt licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of asset receipt licenses
	*/
	@Override
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		int start, int end) {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicenses(start,
			end);
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
		return _assetReceiptLicenseLocalService.dynamicQueryCount(dynamicQuery);
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
		return _assetReceiptLicenseLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public AssetReceiptLicenseLocalService getWrappedService() {
		return _assetReceiptLicenseLocalService;
	}

	@Override
	public void setWrappedService(
		AssetReceiptLicenseLocalService assetReceiptLicenseLocalService) {
		_assetReceiptLicenseLocalService = assetReceiptLicenseLocalService;
	}

	private AssetReceiptLicenseLocalService _assetReceiptLicenseLocalService;
}