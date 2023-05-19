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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for ZendeskArticle. This utility wraps
 * <code>com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleLocalService
 * @generated
 */
public class ZendeskArticleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ZendeskArticle addZendeskArticle(
			long zendeskSectionId, String documentationKey,
			String documentationOriginalURL,
			Map<java.util.Locale, String> remoteTitleMap,
			Map<java.util.Locale, String> remoteBodyMap,
			String previousArticleDocumentationKey,
			String nextArticleDocumentationKey, int position,
			long remoteUserSegmentId, String[] labelNames,
			Map<String, byte[]> attachments)
		throws PortalException {

		return getService().addZendeskArticle(
			zendeskSectionId, documentationKey, documentationOriginalURL,
			remoteTitleMap, remoteBodyMap, previousArticleDocumentationKey,
			nextArticleDocumentationKey, position, remoteUserSegmentId,
			labelNames, attachments);
	}

	/**
	 * Adds the zendesk article to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskArticle the zendesk article
	 * @return the zendesk article that was added
	 */
	public static ZendeskArticle addZendeskArticle(
		ZendeskArticle zendeskArticle) {

		return getService().addZendeskArticle(zendeskArticle);
	}

	/**
	 * Creates a new zendesk article with the primary key. Does not add the zendesk article to the database.
	 *
	 * @param zendeskArticleId the primary key for the new zendesk article
	 * @return the new zendesk article
	 */
	public static ZendeskArticle createZendeskArticle(long zendeskArticleId) {
		return getService().createZendeskArticle(zendeskArticleId);
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
	 * Deletes the zendesk article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskArticleId the primary key of the zendesk article
	 * @return the zendesk article that was removed
	 * @throws PortalException if a zendesk article with the primary key could not be found
	 */
	public static ZendeskArticle deleteZendeskArticle(long zendeskArticleId)
		throws PortalException {

		return getService().deleteZendeskArticle(zendeskArticleId);
	}

	/**
	 * Deletes the zendesk article from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskArticle the zendesk article
	 * @return the zendesk article that was removed
	 * @throws PortalException
	 */
	public static ZendeskArticle deleteZendeskArticle(
			ZendeskArticle zendeskArticle)
		throws PortalException {

		return getService().deleteZendeskArticle(zendeskArticle);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl</code>.
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

	public static ZendeskArticle fetchZendeskArticle(long zendeskArticleId) {
		return getService().fetchZendeskArticle(zendeskArticleId);
	}

	public static ZendeskArticle fetchZendeskArticle(
		long zendeskCategoryId, String documentationKey) {

		return getService().fetchZendeskArticle(
			zendeskCategoryId, documentationKey);
	}

	public static ZendeskArticle fetchZendeskArticle(
		String documentationOriginalURL) {

		return getService().fetchZendeskArticle(documentationOriginalURL);
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
	 * Returns the zendesk article with the primary key.
	 *
	 * @param zendeskArticleId the primary key of the zendesk article
	 * @return the zendesk article
	 * @throws PortalException if a zendesk article with the primary key could not be found
	 */
	public static ZendeskArticle getZendeskArticle(long zendeskArticleId)
		throws PortalException {

		return getService().getZendeskArticle(zendeskArticleId);
	}

	public static int getZendeskArticleCount(long zendeskSectionId) {
		return getService().getZendeskArticleCount(zendeskSectionId);
	}

	/**
	 * Returns a range of all the zendesk articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk articles
	 * @param end the upper bound of the range of zendesk articles (not inclusive)
	 * @return the range of zendesk articles
	 */
	public static List<ZendeskArticle> getZendeskArticles(int start, int end) {
		return getService().getZendeskArticles(start, end);
	}

	/**
	 * Returns the number of zendesk articles.
	 *
	 * @return the number of zendesk articles
	 */
	public static int getZendeskArticlesCount() {
		return getService().getZendeskArticlesCount();
	}

	public static List<ZendeskArticle> getZendeskCategoryArticles(
		long zendeskCategoryId) {

		return getService().getZendeskCategoryArticles(zendeskCategoryId);
	}

	public static ZendeskArticle updateRemoteUserSegmentId(
			long zendeskArticleId, long remoteUserSegmentId)
		throws PortalException {

		return getService().updateRemoteUserSegmentId(
			zendeskArticleId, remoteUserSegmentId);
	}

	public static ZendeskArticle updateZendeskArticle(
			long zendeskArticleId, long zendeskSectionId,
			String documentationKey, String documentationOriginalURL,
			Map<java.util.Locale, String> remoteTitleMap,
			Map<java.util.Locale, String> remoteBodyMap,
			String previousArticleDocumentationKey,
			String nextArticleDocumentationKey, int position,
			long remoteUserSegmentId, String[] labelNames,
			Map<String, byte[]> attachments)
		throws PortalException {

		return getService().updateZendeskArticle(
			zendeskArticleId, zendeskSectionId, documentationKey,
			documentationOriginalURL, remoteTitleMap, remoteBodyMap,
			previousArticleDocumentationKey, nextArticleDocumentationKey,
			position, remoteUserSegmentId, labelNames, attachments);
	}

	/**
	 * Updates the zendesk article in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskArticle the zendesk article
	 * @return the zendesk article that was updated
	 */
	public static ZendeskArticle updateZendeskArticle(
		ZendeskArticle zendeskArticle) {

		return getService().updateZendeskArticle(zendeskArticle);
	}

	public static ZendeskArticle updateZendeskArticleTranslation(
			long zendeskArticleId, java.util.Locale locale, String remoteTitle,
			String remoteBody)
		throws PortalException {

		return getService().updateZendeskArticleTranslation(
			zendeskArticleId, locale, remoteTitle, remoteBody);
	}

	public static ZendeskArticleLocalService getService() {
		return _service;
	}

	public static void setService(ZendeskArticleLocalService service) {
		_service = service;
	}

	private static volatile ZendeskArticleLocalService _service;

}