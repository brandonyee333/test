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

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryLocalService
 * @generated
 */
public class ProductEntryLocalServiceWrapper
	implements ProductEntryLocalService,
			   ServiceWrapper<ProductEntryLocalService> {

	public ProductEntryLocalServiceWrapper(
		ProductEntryLocalService productEntryLocalService) {

		_productEntryLocalService = productEntryLocalService;
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry addProductEntry(
			long userId, String koroneikiProductKey, String name,
			int environment, boolean licenses, String versionsListType,
			String zendeskTag)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.addProductEntry(
			userId, koroneikiProductKey, name, environment, licenses,
			versionsListType, zendeskTag);
	}

	/**
	 * Adds the product entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param productEntry the product entry
	 * @return the product entry that was added
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry addProductEntry(
		com.liferay.osb.customer.admin.model.ProductEntry productEntry) {

		return _productEntryLocalService.addProductEntry(productEntry);
	}

	/**
	 * Creates a new product entry with the primary key. Does not add the product entry to the database.
	 *
	 * @param productEntryId the primary key for the new product entry
	 * @return the new product entry
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry createProductEntry(
		long productEntryId) {

		return _productEntryLocalService.createProductEntry(productEntryId);
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

	/**
	 * Deletes the product entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry that was removed
	 * @throws PortalException if a product entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry deleteProductEntry(
			long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.deleteProductEntry(productEntryId);
	}

	/**
	 * Deletes the product entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productEntry the product entry
	 * @return the product entry that was removed
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry deleteProductEntry(
		com.liferay.osb.customer.admin.model.ProductEntry productEntry) {

		return _productEntryLocalService.deleteProductEntry(productEntry);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry deleteProductEntry(
			String koroneikiProductKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.deleteProductEntry(
			koroneikiProductKey);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _productEntryLocalService.dynamicQuery();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.ProductEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.ProductEntryModelImpl</code>.
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

		return _productEntryLocalService.dynamicQuery(
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

		return _productEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry fetchProductEntry(
		long productEntryId) {

		return _productEntryLocalService.fetchProductEntry(productEntryId);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry
		fetchProductEntryByKoroneikiKey(String koroneikiProductKey) {

		return _productEntryLocalService.fetchProductEntryByKoroneikiKey(
			koroneikiProductKey);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry
		fetchProductEntryByName(String name) {

		return _productEntryLocalService.fetchProductEntryByName(name);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _productEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _productEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.ProductEntry>
		getProductEntries(boolean licenses) {

		return _productEntryLocalService.getProductEntries(licenses);
	}

	/**
	 * Returns a range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of product entries
	 */
	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.ProductEntry>
		getProductEntries(int start, int end) {

		return _productEntryLocalService.getProductEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.ProductEntry>
		getProductEntries(long accountEntryId) {

		return _productEntryLocalService.getProductEntries(accountEntryId);
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

	/**
	 * Returns the product entry with the primary key.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry
	 * @throws PortalException if a product entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry getProductEntry(
			long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.getProductEntry(productEntryId);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry
			getProductEntryByKoroneikiKey(String koroneikiProductKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.getProductEntryByKoroneikiKey(
			koroneikiProductKey);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry
			getProductEntryByName(String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.getProductEntryByName(name);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.ProductEntry>
		search(
			String name, java.util.LinkedHashMap<String, Object> params,
			int start, int end) {

		return _productEntryLocalService.search(name, params, start, end);
	}

	@Override
	public int searchCount(
		String name, java.util.LinkedHashMap<String, Object> params) {

		return _productEntryLocalService.searchCount(name, params);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry updateName(
			long productEntryId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.updateName(productEntryId, name);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry updateProductEntry(
			long productEntryId, String koroneikiProductKey, String name,
			int environment, boolean licenses, String versionsListType,
			String zendeskTag)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.updateProductEntry(
			productEntryId, koroneikiProductKey, name, environment, licenses,
			versionsListType, zendeskTag);
	}

	/**
	 * Updates the product entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param productEntry the product entry
	 * @return the product entry that was updated
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProductEntry updateProductEntry(
		com.liferay.osb.customer.admin.model.ProductEntry productEntry) {

		return _productEntryLocalService.updateProductEntry(productEntry);
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