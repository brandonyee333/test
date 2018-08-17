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

package com.liferay.osb.customer.zendesk.documentation.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ZendeskArticleAttachment. This utility wraps
 * {@link com.liferay.osb.customer.zendesk.documentation.service.impl.ZendeskArticleAttachmentLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleAttachmentLocalService
 * @see com.liferay.osb.customer.zendesk.documentation.service.base.ZendeskArticleAttachmentLocalServiceBaseImpl
 * @see com.liferay.osb.customer.zendesk.documentation.service.impl.ZendeskArticleAttachmentLocalServiceImpl
 * @generated
 */
@ProviderType
public class ZendeskArticleAttachmentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.customer.zendesk.documentation.service.impl.ZendeskArticleAttachmentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment addZendeskArticleAttachment(
		long zendeskArticleId, String filePath, java.io.File file)
		throws Exception {
		return getService()
				   .addZendeskArticleAttachment(zendeskArticleId, filePath, file);
	}

	/**
	* Adds the zendesk article attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachment the zendesk article attachment
	* @return the zendesk article attachment that was added
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment addZendeskArticleAttachment(
		com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment zendeskArticleAttachment) {
		return getService().addZendeskArticleAttachment(zendeskArticleAttachment);
	}

	/**
	* Creates a new zendesk article attachment with the primary key. Does not add the zendesk article attachment to the database.
	*
	* @param zendeskArticleAttachmentId the primary key for the new zendesk article attachment
	* @return the new zendesk article attachment
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment createZendeskArticleAttachment(
		long zendeskArticleAttachmentId) {
		return getService()
				   .createZendeskArticleAttachment(zendeskArticleAttachmentId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the zendesk article attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	* @return the zendesk article attachment that was removed
	* @throws PortalException if a zendesk article attachment with the primary key could not be found
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment deleteZendeskArticleAttachment(
		long zendeskArticleAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteZendeskArticleAttachment(zendeskArticleAttachmentId);
	}

	/**
	* Deletes the zendesk article attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachment the zendesk article attachment
	* @return the zendesk article attachment that was removed
	* @throws PortalException
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment deleteZendeskArticleAttachment(
		com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment zendeskArticleAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteZendeskArticleAttachment(zendeskArticleAttachment);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment fetchZendeskArticleAttachment(
		long zendeskArticleAttachmentId) {
		return getService()
				   .fetchZendeskArticleAttachment(zendeskArticleAttachmentId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the zendesk article attachment with the primary key.
	*
	* @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	* @return the zendesk article attachment
	* @throws PortalException if a zendesk article attachment with the primary key could not be found
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment getZendeskArticleAttachment(
		long zendeskArticleAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getZendeskArticleAttachment(zendeskArticleAttachmentId);
	}

	/**
	* Returns a range of all the zendesk article attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk article attachments
	* @param end the upper bound of the range of zendesk article attachments (not inclusive)
	* @return the range of zendesk article attachments
	*/
	public static java.util.List<com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment> getZendeskArticleAttachments(
		int start, int end) {
		return getService().getZendeskArticleAttachments(start, end);
	}

	public static java.util.List<com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment> getZendeskArticleAttachments(
		long zendeskArticleId) {
		return getService().getZendeskArticleAttachments(zendeskArticleId);
	}

	/**
	* Returns the number of zendesk article attachments.
	*
	* @return the number of zendesk article attachments
	*/
	public static int getZendeskArticleAttachmentsCount() {
		return getService().getZendeskArticleAttachmentsCount();
	}

	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment updateZendeskArticleAttachment(
		long zendeskArticleId, String filePath, java.io.File file)
		throws Exception {
		return getService()
				   .updateZendeskArticleAttachment(zendeskArticleId, filePath,
			file);
	}

	/**
	* Updates the zendesk article attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleAttachment the zendesk article attachment
	* @return the zendesk article attachment that was updated
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment updateZendeskArticleAttachment(
		com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticleAttachment zendeskArticleAttachment) {
		return getService()
				   .updateZendeskArticleAttachment(zendeskArticleAttachment);
	}

	public static ZendeskArticleAttachmentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ZendeskArticleAttachmentLocalService, ZendeskArticleAttachmentLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ZendeskArticleAttachmentLocalService.class);

		ServiceTracker<ZendeskArticleAttachmentLocalService, ZendeskArticleAttachmentLocalService> serviceTracker =
			new ServiceTracker<ZendeskArticleAttachmentLocalService, ZendeskArticleAttachmentLocalService>(bundle.getBundleContext(),
				ZendeskArticleAttachmentLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}