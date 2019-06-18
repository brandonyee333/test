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
 * Provides the local service utility for ZendeskCategory. This utility wraps
 * <code>com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskCategoryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskCategoryLocalService
 * @generated
 */
@ProviderType
public class ZendeskCategoryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskCategoryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static
		com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory addZendeskCategory(
					String documentationKey, String documentationOriginalURL,
					String articleLabels, long remoteId,
					long remoteUserSegmentId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addZendeskCategory(
			documentationKey, documentationOriginalURL, articleLabels, remoteId,
			remoteUserSegmentId);
	}

	/**
	 * Adds the zendesk category to the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskCategory the zendesk category
	 * @return the zendesk category that was added
	 */
	public static
		com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory addZendeskCategory(
				com.liferay.osb.customer.zendesk.documentation.sync.model.
					ZendeskCategory zendeskCategory) {

		return getService().addZendeskCategory(zendeskCategory);
	}

	/**
	 * Creates a new zendesk category with the primary key. Does not add the zendesk category to the database.
	 *
	 * @param zendeskCategoryId the primary key for the new zendesk category
	 * @return the new zendesk category
	 */
	public static
		com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory createZendeskCategory(long zendeskCategoryId) {

		return getService().createZendeskCategory(zendeskCategoryId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the zendesk category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskCategoryId the primary key of the zendesk category
	 * @return the zendesk category that was removed
	 * @throws PortalException if a zendesk category with the primary key could not be found
	 */
	public static
		com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory deleteZendeskCategory(long zendeskCategoryId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteZendeskCategory(zendeskCategoryId);
	}

	/**
	 * Deletes the zendesk category from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskCategory the zendesk category
	 * @return the zendesk category that was removed
	 */
	public static
		com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory deleteZendeskCategory(
				com.liferay.osb.customer.zendesk.documentation.sync.model.
					ZendeskCategory zendeskCategory) {

		return getService().deleteZendeskCategory(zendeskCategory);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static
		com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory fetchZendeskCategory(long zendeskCategoryId) {

		return getService().fetchZendeskCategory(zendeskCategoryId);
	}

	public static
		com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory fetchZendeskCategory(String documentationKey) {

		return getService().fetchZendeskCategory(documentationKey);
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

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the zendesk categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk categories
	 * @param end the upper bound of the range of zendesk categories (not inclusive)
	 * @return the range of zendesk categories
	 */
	public static java.util.List
		<com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory> getZendeskCategories(int start, int end) {

		return getService().getZendeskCategories(start, end);
	}

	/**
	 * Returns the number of zendesk categories.
	 *
	 * @return the number of zendesk categories
	 */
	public static int getZendeskCategoriesCount() {
		return getService().getZendeskCategoriesCount();
	}

	/**
	 * Returns the zendesk category with the primary key.
	 *
	 * @param zendeskCategoryId the primary key of the zendesk category
	 * @return the zendesk category
	 * @throws PortalException if a zendesk category with the primary key could not be found
	 */
	public static
		com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory getZendeskCategory(long zendeskCategoryId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getZendeskCategory(zendeskCategoryId);
	}

	public static
		com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory updateZendeskCategory(
					long zendeskCategoryId, String documentationKey,
					String documentationOriginalURL, String articleLabels,
					long remoteUserSegmentId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateZendeskCategory(
			zendeskCategoryId, documentationKey, documentationOriginalURL,
			articleLabels, remoteUserSegmentId);
	}

	/**
	 * Updates the zendesk category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskCategory the zendesk category
	 * @return the zendesk category that was updated
	 */
	public static
		com.liferay.osb.customer.zendesk.documentation.sync.model.
			ZendeskCategory updateZendeskCategory(
				com.liferay.osb.customer.zendesk.documentation.sync.model.
					ZendeskCategory zendeskCategory) {

		return getService().updateZendeskCategory(zendeskCategory);
	}

	public static ZendeskCategoryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ZendeskCategoryLocalService, ZendeskCategoryLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ZendeskCategoryLocalService.class);

		ServiceTracker<ZendeskCategoryLocalService, ZendeskCategoryLocalService>
			serviceTracker =
				new ServiceTracker
					<ZendeskCategoryLocalService, ZendeskCategoryLocalService>(
						bundle.getBundleContext(),
						ZendeskCategoryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}