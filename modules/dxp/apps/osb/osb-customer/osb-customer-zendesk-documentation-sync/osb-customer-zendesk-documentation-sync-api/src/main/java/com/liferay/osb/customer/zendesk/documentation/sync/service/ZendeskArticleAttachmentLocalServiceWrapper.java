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
 * Provides a wrapper for {@link ZendeskArticleAttachmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleAttachmentLocalService
 * @generated
 */
@ProviderType
public class ZendeskArticleAttachmentLocalServiceWrapper
	implements ZendeskArticleAttachmentLocalService,
		ServiceWrapper<ZendeskArticleAttachmentLocalService> {
	public ZendeskArticleAttachmentLocalServiceWrapper(
		ZendeskArticleAttachmentLocalService zendeskArticleAttachmentLocalService) {
		_zendeskArticleAttachmentLocalService = zendeskArticleAttachmentLocalService;
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment addZendeskArticleAttachment(
		long zendeskArticleId, String filePath, byte[] bytes)
		throws Exception {
		return _zendeskArticleAttachmentLocalService.addZendeskArticleAttachment(zendeskArticleId,
			filePath, bytes);
	}

	/**
	* Adds the zendesk article attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachment the zendesk article attachment
	* @return the zendesk article attachment that was added
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment addZendeskArticleAttachment(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment zendeskArticleAttachment) {
		return _zendeskArticleAttachmentLocalService.addZendeskArticleAttachment(zendeskArticleAttachment);
	}

	/**
	* Creates a new zendesk article attachment with the primary key. Does not add the zendesk article attachment to the database.
	*
	* @param zendeskArticleAttachmentId the primary key for the new zendesk article attachment
	* @return the new zendesk article attachment
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment createZendeskArticleAttachment(
		long zendeskArticleAttachmentId) {
		return _zendeskArticleAttachmentLocalService.createZendeskArticleAttachment(zendeskArticleAttachmentId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleAttachmentLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the zendesk article attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	* @return the zendesk article attachment that was removed
	* @throws PortalException if a zendesk article attachment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment deleteZendeskArticleAttachment(
		long zendeskArticleAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleAttachmentLocalService.deleteZendeskArticleAttachment(zendeskArticleAttachmentId);
	}

	/**
	* Deletes the zendesk article attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachment the zendesk article attachment
	* @return the zendesk article attachment that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment deleteZendeskArticleAttachment(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment zendeskArticleAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleAttachmentLocalService.deleteZendeskArticleAttachment(zendeskArticleAttachment);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _zendeskArticleAttachmentLocalService.dynamicQuery();
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
		return _zendeskArticleAttachmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _zendeskArticleAttachmentLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _zendeskArticleAttachmentLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _zendeskArticleAttachmentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _zendeskArticleAttachmentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment fetchZendeskArticleAttachment(
		long zendeskArticleAttachmentId) {
		return _zendeskArticleAttachmentLocalService.fetchZendeskArticleAttachment(zendeskArticleAttachmentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _zendeskArticleAttachmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _zendeskArticleAttachmentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _zendeskArticleAttachmentLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleAttachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the zendesk article attachment with the primary key.
	*
	* @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	* @return the zendesk article attachment
	* @throws PortalException if a zendesk article attachment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment getZendeskArticleAttachment(
		long zendeskArticleAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _zendeskArticleAttachmentLocalService.getZendeskArticleAttachment(zendeskArticleAttachmentId);
	}

	/**
	* Returns a range of all the zendesk article attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk article attachments
	* @param end the upper bound of the range of zendesk article attachments (not inclusive)
	* @return the range of zendesk article attachments
	*/
	@Override
	public java.util.List<com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment> getZendeskArticleAttachments(
		int start, int end) {
		return _zendeskArticleAttachmentLocalService.getZendeskArticleAttachments(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment> getZendeskArticleAttachments(
		long zendeskArticleId) {
		return _zendeskArticleAttachmentLocalService.getZendeskArticleAttachments(zendeskArticleId);
	}

	/**
	* Returns the number of zendesk article attachments.
	*
	* @return the number of zendesk article attachments
	*/
	@Override
	public int getZendeskArticleAttachmentsCount() {
		return _zendeskArticleAttachmentLocalService.getZendeskArticleAttachmentsCount();
	}

	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment updateZendeskArticleAttachment(
		long zendeskArticleId, String filePath, byte[] bytes)
		throws Exception {
		return _zendeskArticleAttachmentLocalService.updateZendeskArticleAttachment(zendeskArticleId,
			filePath, bytes);
	}

	/**
	* Updates the zendesk article attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachment the zendesk article attachment
	* @return the zendesk article attachment that was updated
	*/
	@Override
	public com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment updateZendeskArticleAttachment(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment zendeskArticleAttachment) {
		return _zendeskArticleAttachmentLocalService.updateZendeskArticleAttachment(zendeskArticleAttachment);
	}

	@Override
	public ZendeskArticleAttachmentLocalService getWrappedService() {
		return _zendeskArticleAttachmentLocalService;
	}

	@Override
	public void setWrappedService(
		ZendeskArticleAttachmentLocalService zendeskArticleAttachmentLocalService) {
		_zendeskArticleAttachmentLocalService = zendeskArticleAttachmentLocalService;
	}

	private ZendeskArticleAttachmentLocalService _zendeskArticleAttachmentLocalService;
}