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
 * Provides a wrapper for {@link ProductEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryLocalService
 * @generated
 */
@ProviderType
public class ProductEntryLocalServiceWrapper implements ProductEntryLocalService,
	ServiceWrapper<ProductEntryLocalService> {
	public ProductEntryLocalServiceWrapper(
		ProductEntryLocalService productEntryLocalService) {
		_productEntryLocalService = productEntryLocalService;
	}

	/**
	* Adds the product entry to the database. Also notifies the appropriate model listeners.
	*
	* @param productEntry the product entry
	* @return the product entry that was added
	*/
	@Override
	public com.liferay.osb.model.ProductEntry addProductEntry(
		com.liferay.osb.model.ProductEntry productEntry) {
		return _productEntryLocalService.addProductEntry(productEntry);
	}

	@Override
	public com.liferay.osb.model.ProductEntry addProductEntry(long userId,
		java.lang.String name, int type, int environment,
		java.lang.String versionsListType, java.lang.String[] dossieraIdMappings)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _productEntryLocalService.addProductEntry(userId, name, type,
			environment, versionsListType, dossieraIdMappings);
	}

	/**
	* Creates a new product entry with the primary key. Does not add the product entry to the database.
	*
	* @param productEntryId the primary key for the new product entry
	* @return the new product entry
	*/
	@Override
	public com.liferay.osb.model.ProductEntry createProductEntry(
		long productEntryId) {
		return _productEntryLocalService.createProductEntry(productEntryId);
	}

	/**
	* Deletes the product entry from the database. Also notifies the appropriate model listeners.
	*
	* @param productEntry the product entry
	* @return the product entry that was removed
	*/
	@Override
	public com.liferay.osb.model.ProductEntry deleteProductEntry(
		com.liferay.osb.model.ProductEntry productEntry) {
		return _productEntryLocalService.deleteProductEntry(productEntry);
	}

	/**
	* Deletes the product entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param productEntryId the primary key of the product entry
	* @return the product entry that was removed
	* @throws PortalException if a product entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.ProductEntry deleteProductEntry(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _productEntryLocalService.deleteProductEntry(productEntryId);
	}

	@Override
	public com.liferay.osb.model.ProductEntry fetchProductEntry(
		long productEntryId) {
		return _productEntryLocalService.fetchProductEntry(productEntryId);
	}

	@Override
	public com.liferay.osb.model.ProductEntry fetchProductEntryByName(
		java.lang.String name) {
		return _productEntryLocalService.fetchProductEntryByName(name);
	}

	/**
	* Returns the product entry with the primary key.
	*
	* @param productEntryId the primary key of the product entry
	* @return the product entry
	* @throws PortalException if a product entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.ProductEntry getProductEntry(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _productEntryLocalService.getProductEntry(productEntryId);
	}

	@Override
	public com.liferay.osb.model.ProductEntry getProductEntryByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _productEntryLocalService.getProductEntryByName(name);
	}

	/**
	* Updates the product entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param productEntry the product entry
	* @return the product entry that was updated
	*/
	@Override
	public com.liferay.osb.model.ProductEntry updateProductEntry(
		com.liferay.osb.model.ProductEntry productEntry) {
		return _productEntryLocalService.updateProductEntry(productEntry);
	}

	@Override
	public com.liferay.osb.model.ProductEntry updateProductEntry(
		long productEntryId, java.lang.String name, int type, int environment,
		java.lang.String versionsListType, java.lang.String[] dossieraIdMappings)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _productEntryLocalService.updateProductEntry(productEntryId,
			name, type, environment, versionsListType, dossieraIdMappings);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _productEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _productEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _productEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _productEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _productEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of product entries.
	*
	* @return the number of product entries
	*/
	@Override
	public int getProductEntriesCount() {
		return _productEntryLocalService.getProductEntriesCount();
	}

	@Override
	public int searchCount(java.lang.String name,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return _productEntryLocalService.searchCount(name, params);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _productEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _productEntryLocalService.getOSGiServiceIdentifier();
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
		return _productEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ProductEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _productEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ProductEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _productEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the product entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ProductEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of product entries
	* @param end the upper bound of the range of product entries (not inclusive)
	* @return the range of product entries
	*/
	@Override
	public java.util.List<com.liferay.osb.model.ProductEntry> getProductEntries(
		int start, int end) {
		return _productEntryLocalService.getProductEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.ProductEntry> getProductEntries(
		long accountEntryId) {
		return _productEntryLocalService.getProductEntries(accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.ProductEntry> search(
		java.lang.String name,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end) {
		return _productEntryLocalService.search(name, params, start, end);
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
		return _productEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _productEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ProductEntryLocalService getWrappedService() {
		return _productEntryLocalService;
	}

	@Override
	public void setWrappedService(
		ProductEntryLocalService productEntryLocalService) {
		_productEntryLocalService = productEntryLocalService;
	}

	private ProductEntryLocalService _productEntryLocalService;
}