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

package com.liferay.osb.customer.zendesk.documentation.sync.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ZendeskCategoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskCategoryLocalService
 * @generated
 */
@ProviderType
public class ZendeskCategoryLocalServiceWrapper
	implements ZendeskCategoryLocalService,
		ServiceWrapper<ZendeskCategoryLocalService> {
	public ZendeskCategoryLocalServiceWrapper(
		ZendeskCategoryLocalService zendeskCategoryLocalService) {
		_zendeskCategoryLocalService = zendeskCategoryLocalService;
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory addZendeskCategory(
		String documentationKey, String documentationOriginalURL,
		String articleLabels, long remoteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskCategoryLocalService.addZendeskCategory(documentationKey,
			documentationOriginalURL, articleLabels, remoteId);
	}

	/**
	* Adds the zendesk category to the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskCategory the zendesk category
	* @return the zendesk category that was added
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory addZendeskCategory(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory zendeskCategory) {
		return _zendeskCategoryLocalService.addZendeskCategory(zendeskCategory);
	}

	/**
	* Creates a new zendesk category with the primary key. Does not add the zendesk category to the database.
	*
	* @param zendeskCategoryId the primary key for the new zendesk category
	* @return the new zendesk category
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory createZendeskCategory(
		long zendeskCategoryId) {
		return _zendeskCategoryLocalService.createZendeskCategory(zendeskCategoryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskCategoryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the zendesk category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskCategoryId the primary key of the zendesk category
	* @return the zendesk category that was removed
	* @throws PortalException if a zendesk category with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory deleteZendeskCategory(
		long zendeskCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskCategoryLocalService.deleteZendeskCategory(zendeskCategoryId);
	}

	/**
	* Deletes the zendesk category from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskCategory the zendesk category
	* @return the zendesk category that was removed
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory deleteZendeskCategory(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory zendeskCategory) {
		return _zendeskCategoryLocalService.deleteZendeskCategory(zendeskCategory);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _zendeskCategoryLocalService.dynamicQuery();
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
		return _zendeskCategoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _zendeskCategoryLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _zendeskCategoryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _zendeskCategoryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _zendeskCategoryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory fetchZendeskCategory(
		long zendeskCategoryId) {
		return _zendeskCategoryLocalService.fetchZendeskCategory(zendeskCategoryId);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory fetchZendeskCategory(
		String documentationKey) {
		return _zendeskCategoryLocalService.fetchZendeskCategory(documentationKey);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _zendeskCategoryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _zendeskCategoryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _zendeskCategoryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskCategoryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the zendesk categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk categories
	* @param end the upper bound of the range of zendesk categories (not inclusive)
	* @return the range of zendesk categories
	*/
	@Override
	public java.util.List<com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory> getZendeskCategories(
		int start, int end) {
		return _zendeskCategoryLocalService.getZendeskCategories(start, end);
	}

	/**
	* Returns the number of zendesk categories.
	*
	* @return the number of zendesk categories
	*/
	@Override
	public int getZendeskCategoriesCount() {
		return _zendeskCategoryLocalService.getZendeskCategoriesCount();
	}

	/**
	* Returns the zendesk category with the primary key.
	*
	* @param zendeskCategoryId the primary key of the zendesk category
	* @return the zendesk category
	* @throws PortalException if a zendesk category with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory getZendeskCategory(
		long zendeskCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskCategoryLocalService.getZendeskCategory(zendeskCategoryId);
	}

	@Override
	public void importDocumentationArchive(long zendeskCategoryId,
		String fileName, java.io.InputStream inputStream)
		throws Exception {
		_zendeskCategoryLocalService.importDocumentationArchive(zendeskCategoryId,
			fileName, inputStream);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory updateZendeskCategory(
		long zendeskCategoryId, String documentationOriginalURL,
		String articleLabels)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskCategoryLocalService.updateZendeskCategory(zendeskCategoryId,
			documentationOriginalURL, articleLabels);
	}

	/**
	* Updates the zendesk category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param zendeskCategory the zendesk category
	* @return the zendesk category that was updated
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory updateZendeskCategory(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory zendeskCategory) {
		return _zendeskCategoryLocalService.updateZendeskCategory(zendeskCategory);
	}

	@Override
	public ZendeskCategoryLocalService getWrappedService() {
		return _zendeskCategoryLocalService;
	}

	@Override
	public void setWrappedService(
		ZendeskCategoryLocalService zendeskCategoryLocalService) {
		_zendeskCategoryLocalService = zendeskCategoryLocalService;
	}

	private ZendeskCategoryLocalService _zendeskCategoryLocalService;
}