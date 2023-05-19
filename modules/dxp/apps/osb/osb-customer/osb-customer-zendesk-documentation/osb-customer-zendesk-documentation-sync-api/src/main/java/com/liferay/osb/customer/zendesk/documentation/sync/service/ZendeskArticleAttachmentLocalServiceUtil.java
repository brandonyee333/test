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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ZendeskArticleAttachment. This utility wraps
 * <code>com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleAttachmentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleAttachmentLocalService
 * @generated
 */
public class ZendeskArticleAttachmentLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleAttachmentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ZendeskArticleAttachment addZendeskArticleAttachment(
			long zendeskArticleId, String filePath, byte[] bytes)
		throws PortalException {

		return getService().addZendeskArticleAttachment(
			zendeskArticleId, filePath, bytes);
	}

	/**
	 * Adds the zendesk article attachment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskArticleAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskArticleAttachment the zendesk article attachment
	 * @return the zendesk article attachment that was added
	 */
	public static ZendeskArticleAttachment addZendeskArticleAttachment(
		ZendeskArticleAttachment zendeskArticleAttachment) {

		return getService().addZendeskArticleAttachment(
			zendeskArticleAttachment);
	}

	/**
	 * Creates a new zendesk article attachment with the primary key. Does not add the zendesk article attachment to the database.
	 *
	 * @param zendeskArticleAttachmentId the primary key for the new zendesk article attachment
	 * @return the new zendesk article attachment
	 */
	public static ZendeskArticleAttachment createZendeskArticleAttachment(
		long zendeskArticleAttachmentId) {

		return getService().createZendeskArticleAttachment(
			zendeskArticleAttachmentId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the zendesk article attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskArticleAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	 * @return the zendesk article attachment that was removed
	 * @throws PortalException if a zendesk article attachment with the primary key could not be found
	 */
	public static ZendeskArticleAttachment deleteZendeskArticleAttachment(
			long zendeskArticleAttachmentId)
		throws PortalException {

		return getService().deleteZendeskArticleAttachment(
			zendeskArticleAttachmentId);
	}

	/**
	 * Deletes the zendesk article attachment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskArticleAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskArticleAttachment the zendesk article attachment
	 * @return the zendesk article attachment that was removed
	 * @throws PortalException
	 */
	public static ZendeskArticleAttachment deleteZendeskArticleAttachment(
			ZendeskArticleAttachment zendeskArticleAttachment)
		throws PortalException {

		return getService().deleteZendeskArticleAttachment(
			zendeskArticleAttachment);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static ZendeskArticleAttachment fetchZendeskArticleAttachment(
		long zendeskArticleAttachmentId) {

		return getService().fetchZendeskArticleAttachment(
			zendeskArticleAttachmentId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

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

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the zendesk article attachment with the primary key.
	 *
	 * @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	 * @return the zendesk article attachment
	 * @throws PortalException if a zendesk article attachment with the primary key could not be found
	 */
	public static ZendeskArticleAttachment getZendeskArticleAttachment(
			long zendeskArticleAttachmentId)
		throws PortalException {

		return getService().getZendeskArticleAttachment(
			zendeskArticleAttachmentId);
	}

	/**
	 * Returns a range of all the zendesk article attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk article attachments
	 * @param end the upper bound of the range of zendesk article attachments (not inclusive)
	 * @return the range of zendesk article attachments
	 */
	public static List<ZendeskArticleAttachment> getZendeskArticleAttachments(
		int start, int end) {

		return getService().getZendeskArticleAttachments(start, end);
	}

	public static List<ZendeskArticleAttachment> getZendeskArticleAttachments(
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

	public static ZendeskArticleAttachment updateZendeskArticleAttachment(
			long zendeskArticleId, String filePath, byte[] bytes)
		throws PortalException {

		return getService().updateZendeskArticleAttachment(
			zendeskArticleId, filePath, bytes);
	}

	/**
	 * Updates the zendesk article attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskArticleAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskArticleAttachment the zendesk article attachment
	 * @return the zendesk article attachment that was updated
	 */
	public static ZendeskArticleAttachment updateZendeskArticleAttachment(
		ZendeskArticleAttachment zendeskArticleAttachment) {

		return getService().updateZendeskArticleAttachment(
			zendeskArticleAttachment);
	}

	public static ZendeskArticleAttachmentLocalService getService() {
		return _service;
	}

	public static void setService(
		ZendeskArticleAttachmentLocalService service) {

		_service = service;
	}

	private static volatile ZendeskArticleAttachmentLocalService _service;

}