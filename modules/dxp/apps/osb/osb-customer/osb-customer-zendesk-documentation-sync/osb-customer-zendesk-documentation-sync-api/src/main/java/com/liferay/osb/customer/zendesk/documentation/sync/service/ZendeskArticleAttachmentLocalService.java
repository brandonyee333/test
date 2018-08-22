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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ZendeskArticleAttachment. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleAttachmentLocalServiceUtil
 * @see com.liferay.osb.customer.zendesk.documentation.sync.service.base.ZendeskArticleAttachmentLocalServiceBaseImpl
 * @see com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleAttachmentLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ZendeskArticleAttachmentLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ZendeskArticleAttachmentLocalServiceUtil} to access the zendesk article attachment local service. Add custom service methods to {@link com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleAttachmentLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public ZendeskArticleAttachment addZendeskArticleAttachment(
		long zendeskArticleId, String filePath, byte[] bytes)
		throws PortalException;

	/**
	* Adds the zendesk article attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachment the zendesk article attachment
	* @return the zendesk article attachment that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ZendeskArticleAttachment addZendeskArticleAttachment(
		ZendeskArticleAttachment zendeskArticleAttachment);

	/**
	* Creates a new zendesk article attachment with the primary key. Does not add the zendesk article attachment to the database.
	*
	* @param zendeskArticleAttachmentId the primary key for the new zendesk article attachment
	* @return the new zendesk article attachment
	*/
	@Transactional(enabled = false)
	public ZendeskArticleAttachment createZendeskArticleAttachment(
		long zendeskArticleAttachmentId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the zendesk article attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	* @return the zendesk article attachment that was removed
	* @throws PortalException if a zendesk article attachment with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ZendeskArticleAttachment deleteZendeskArticleAttachment(
		long zendeskArticleAttachmentId) throws PortalException;

	/**
	* Deletes the zendesk article attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachment the zendesk article attachment
	* @return the zendesk article attachment that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public ZendeskArticleAttachment deleteZendeskArticleAttachment(
		ZendeskArticleAttachment zendeskArticleAttachment)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ZendeskArticleAttachment fetchZendeskArticleAttachment(
		long zendeskArticleAttachmentId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the zendesk article attachment with the primary key.
	*
	* @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	* @return the zendesk article attachment
	* @throws PortalException if a zendesk article attachment with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ZendeskArticleAttachment getZendeskArticleAttachment(
		long zendeskArticleAttachmentId) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ZendeskArticleAttachment> getZendeskArticleAttachments(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ZendeskArticleAttachment> getZendeskArticleAttachments(
		long zendeskArticleId);

	/**
	* Returns the number of zendesk article attachments.
	*
	* @return the number of zendesk article attachments
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getZendeskArticleAttachmentsCount();

	public ZendeskArticleAttachment updateZendeskArticleAttachment(
		long zendeskArticleId, String filePath, byte[] bytes)
		throws PortalException;

	/**
	* Updates the zendesk article attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachment the zendesk article attachment
	* @return the zendesk article attachment that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ZendeskArticleAttachment updateZendeskArticleAttachment(
		ZendeskArticleAttachment zendeskArticleAttachment);
}