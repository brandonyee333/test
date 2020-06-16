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

package com.liferay.osb.customer.zendesk.documentation.sync.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ZendeskArticleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleLocalService
 * @generated
 */
@ProviderType
public class ZendeskArticleLocalServiceWrapper
	implements ZendeskArticleLocalService,
		ServiceWrapper<ZendeskArticleLocalService> {
	public ZendeskArticleLocalServiceWrapper(
		ZendeskArticleLocalService zendeskArticleLocalService) {
		_zendeskArticleLocalService = zendeskArticleLocalService;
	}

	/**
	* Adds the zendesk article to the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticle the zendesk article
	* @return the zendesk article that was added
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle addZendeskArticle(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle zendeskArticle) {
		return _zendeskArticleLocalService.addZendeskArticle(zendeskArticle);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle addZendeskArticle(
		long zendeskSectionId, java.lang.String documentationKey,
		java.lang.String documentationOriginalURL,
		java.util.Map<java.util.Locale, java.lang.String> remoteTitleMap,
		java.util.Map<java.util.Locale, java.lang.String> remoteBodyMap,
		java.lang.String previousArticleDocumentationKey,
		java.lang.String nextArticleDocumentationKey, int position,
		long remoteUserSegmentId, java.lang.String[] labelNames,
		java.util.Map<java.lang.String, byte[]> attachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleLocalService.addZendeskArticle(zendeskSectionId,
			documentationKey, documentationOriginalURL, remoteTitleMap,
			remoteBodyMap, previousArticleDocumentationKey,
			nextArticleDocumentationKey, position, remoteUserSegmentId,
			labelNames, attachments);
	}

	/**
	* Creates a new zendesk article with the primary key. Does not add the zendesk article to the database.
	*
	* @param zendeskArticleId the primary key for the new zendesk article
	* @return the new zendesk article
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle createZendeskArticle(
		long zendeskArticleId) {
		return _zendeskArticleLocalService.createZendeskArticle(zendeskArticleId);
	}

	/**
	* Deletes the zendesk article from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticle the zendesk article
	* @return the zendesk article that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle deleteZendeskArticle(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle zendeskArticle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleLocalService.deleteZendeskArticle(zendeskArticle);
	}

	/**
	* Deletes the zendesk article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleId the primary key of the zendesk article
	* @return the zendesk article that was removed
	* @throws PortalException if a zendesk article with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle deleteZendeskArticle(
		long zendeskArticleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleLocalService.deleteZendeskArticle(zendeskArticleId);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle fetchZendeskArticle(
		java.lang.String documentationOriginalURL) {
		return _zendeskArticleLocalService.fetchZendeskArticle(documentationOriginalURL);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle fetchZendeskArticle(
		long zendeskArticleId) {
		return _zendeskArticleLocalService.fetchZendeskArticle(zendeskArticleId);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle fetchZendeskArticle(
		long zendeskCategoryId, java.lang.String documentationKey) {
		return _zendeskArticleLocalService.fetchZendeskArticle(zendeskCategoryId,
			documentationKey);
	}

	/**
	* Returns the zendesk article with the primary key.
	*
	* @param zendeskArticleId the primary key of the zendesk article
	* @return the zendesk article
	* @throws PortalException if a zendesk article with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle getZendeskArticle(
		long zendeskArticleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleLocalService.getZendeskArticle(zendeskArticleId);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle updateRemoteUserSegmentId(
		long zendeskArticleId, long remoteUserSegmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleLocalService.updateRemoteUserSegmentId(zendeskArticleId,
			remoteUserSegmentId);
	}

	/**
	* Updates the zendesk article in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticle the zendesk article
	* @return the zendesk article that was updated
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle updateZendeskArticle(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle zendeskArticle) {
		return _zendeskArticleLocalService.updateZendeskArticle(zendeskArticle);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle updateZendeskArticle(
		long zendeskArticleId, long zendeskSectionId,
		java.lang.String documentationKey,
		java.lang.String documentationOriginalURL,
		java.util.Map<java.util.Locale, java.lang.String> remoteTitleMap,
		java.util.Map<java.util.Locale, java.lang.String> remoteBodyMap,
		java.lang.String previousArticleDocumentationKey,
		java.lang.String nextArticleDocumentationKey, int position,
		long remoteUserSegmentId, java.lang.String[] labelNames,
		java.util.Map<java.lang.String, byte[]> attachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleLocalService.updateZendeskArticle(zendeskArticleId,
			zendeskSectionId, documentationKey, documentationOriginalURL,
			remoteTitleMap, remoteBodyMap, previousArticleDocumentationKey,
			nextArticleDocumentationKey, position, remoteUserSegmentId,
			labelNames, attachments);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle updateZendeskArticleTranslation(
		long zendeskArticleId, java.util.Locale locale,
		java.lang.String remoteTitle, java.lang.String remoteBody)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleLocalService.updateZendeskArticleTranslation(zendeskArticleId,
			locale, remoteTitle, remoteBody);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _zendeskArticleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _zendeskArticleLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _zendeskArticleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getZendeskArticleCount(long zendeskSectionId) {
		return _zendeskArticleLocalService.getZendeskArticleCount(zendeskSectionId);
	}

	/**
	* Returns the number of zendesk articles.
	*
	* @return the number of zendesk articles
	*/
	@Override
	public int getZendeskArticlesCount() {
		return _zendeskArticleLocalService.getZendeskArticlesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _zendeskArticleLocalService.getOSGiServiceIdentifier();
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
		return _zendeskArticleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _zendeskArticleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _zendeskArticleLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the zendesk articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @return the range of zendesk articles
	*/
	@Override
	public java.util.List<com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle> getZendeskArticles(
		int start, int end) {
		return _zendeskArticleLocalService.getZendeskArticles(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle> getZendeskCategoryArticles(
		long zendeskCategoryId) {
		return _zendeskArticleLocalService.getZendeskCategoryArticles(zendeskCategoryId);
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
		return _zendeskArticleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _zendeskArticleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ZendeskArticleLocalService getWrappedService() {
		return _zendeskArticleLocalService;
	}

	@Override
	public void setWrappedService(
		ZendeskArticleLocalService zendeskArticleLocalService) {
		_zendeskArticleLocalService = zendeskArticleLocalService;
	}

	private ZendeskArticleLocalService _zendeskArticleLocalService;
}