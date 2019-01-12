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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ZendeskArticle. This utility wraps
 * {@link com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleLocalService
 * @see com.liferay.osb.customer.zendesk.documentation.sync.service.base.ZendeskArticleLocalServiceBaseImpl
 * @see com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleLocalServiceImpl
 * @generated
 */
@ProviderType
public class ZendeskArticleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle addZendeskArticle(
		long zendeskSectionId, String documentationKey,
		String documentationOriginalURL,
		java.util.Map<String, String> titleMap,
		java.util.Map<String, String> bodyMap, int position,
		String[] labelNames, java.util.Map<String, byte[]> attachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addZendeskArticle(zendeskSectionId, documentationKey,
			documentationOriginalURL, titleMap, bodyMap, position, labelNames,
			attachments);
	}

	/**
	* Adds the zendesk article to the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticle the zendesk article
	* @return the zendesk article that was added
	*/
	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle addZendeskArticle(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle zendeskArticle) {
		return getService().addZendeskArticle(zendeskArticle);
	}

	/**
	* Creates a new zendesk article with the primary key. Does not add the zendesk article to the database.
	*
	* @param zendeskArticleId the primary key for the new zendesk article
	* @return the new zendesk article
	*/
	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle createZendeskArticle(
		long zendeskArticleId) {
		return getService().createZendeskArticle(zendeskArticleId);
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
	* Deletes the zendesk article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleId the primary key of the zendesk article
	* @return the zendesk article that was removed
	* @throws PortalException if a zendesk article with the primary key could not be found
	*/
	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle deleteZendeskArticle(
		long zendeskArticleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteZendeskArticle(zendeskArticleId);
	}

	/**
	* Deletes the zendesk article from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticle the zendesk article
	* @return the zendesk article that was removed
	* @throws PortalException
	*/
	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle deleteZendeskArticle(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle zendeskArticle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteZendeskArticle(zendeskArticle);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle fetchZendeskArticle(
		long zendeskArticleId) {
		return getService().fetchZendeskArticle(zendeskArticleId);
	}

	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle fetchZendeskArticle(
		long zendeskCategoryId, String documentationKey) {
		return getService()
				   .fetchZendeskArticle(zendeskCategoryId, documentationKey);
	}

	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle fetchZendeskArticle(
		String documentationOriginalURL) {
		return getService().fetchZendeskArticle(documentationOriginalURL);
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
	* Returns the zendesk article with the primary key.
	*
	* @param zendeskArticleId the primary key of the zendesk article
	* @return the zendesk article
	* @throws PortalException if a zendesk article with the primary key could not be found
	*/
	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle getZendeskArticle(
		long zendeskArticleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getZendeskArticle(zendeskArticleId);
	}

	public static int getZendeskArticleCount(long zendeskSectionId) {
		return getService().getZendeskArticleCount(zendeskSectionId);
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
	public static java.util.List<com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle> getZendeskArticles(
		int start, int end) {
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

	public static java.util.List<com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle> getZendeskCategoryArticles(
		long zendeskCategoryId) {
		return getService().getZendeskCategoryArticles(zendeskCategoryId);
	}

	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle updateZendeskArticle(
		long zendeskArticleId, long zendeskSectionId, String documentationKey,
		String documentationOriginalURL,
		java.util.Map<String, String> titleMap,
		java.util.Map<String, String> bodyMap, int position,
		String[] labelNames, java.util.Map<String, byte[]> attachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateZendeskArticle(zendeskArticleId, zendeskSectionId,
			documentationKey, documentationOriginalURL, titleMap, bodyMap,
			position, labelNames, attachments);
	}

	/**
	* Updates the zendesk article in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticle the zendesk article
	* @return the zendesk article that was updated
	*/
	public static com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle updateZendeskArticle(
		com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle zendeskArticle) {
		return getService().updateZendeskArticle(zendeskArticle);
	}

	public static ZendeskArticleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ZendeskArticleLocalService, ZendeskArticleLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ZendeskArticleLocalService.class);

		ServiceTracker<ZendeskArticleLocalService, ZendeskArticleLocalService> serviceTracker =
			new ServiceTracker<ZendeskArticleLocalService, ZendeskArticleLocalService>(bundle.getBundleContext(),
				ZendeskArticleLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}