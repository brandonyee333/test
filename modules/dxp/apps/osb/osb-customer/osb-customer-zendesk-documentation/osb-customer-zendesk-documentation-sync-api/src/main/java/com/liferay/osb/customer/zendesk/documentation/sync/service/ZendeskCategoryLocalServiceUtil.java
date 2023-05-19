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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

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
public class ZendeskCategoryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskCategoryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ZendeskCategory addZendeskCategory(
			String documentationKey, String documentationOriginalURL,
			String articleLabels, long remoteId, long remoteUserSegmentId)
		throws PortalException {

		return getService().addZendeskCategory(
			documentationKey, documentationOriginalURL, articleLabels, remoteId,
			remoteUserSegmentId);
	}

	/**
	 * Adds the zendesk category to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskCategory the zendesk category
	 * @return the zendesk category that was added
	 */
	public static ZendeskCategory addZendeskCategory(
		ZendeskCategory zendeskCategory) {

		return getService().addZendeskCategory(zendeskCategory);
	}

	/**
	 * Creates a new zendesk category with the primary key. Does not add the zendesk category to the database.
	 *
	 * @param zendeskCategoryId the primary key for the new zendesk category
	 * @return the new zendesk category
	 */
	public static ZendeskCategory createZendeskCategory(
		long zendeskCategoryId) {

		return getService().createZendeskCategory(zendeskCategoryId);
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
	 * Deletes the zendesk category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskCategoryId the primary key of the zendesk category
	 * @return the zendesk category that was removed
	 * @throws PortalException if a zendesk category with the primary key could not be found
	 */
	public static ZendeskCategory deleteZendeskCategory(long zendeskCategoryId)
		throws PortalException {

		return getService().deleteZendeskCategory(zendeskCategoryId);
	}

	/**
	 * Deletes the zendesk category from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskCategory the zendesk category
	 * @return the zendesk category that was removed
	 */
	public static ZendeskCategory deleteZendeskCategory(
		ZendeskCategory zendeskCategory) {

		return getService().deleteZendeskCategory(zendeskCategory);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl</code>.
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

	public static ZendeskCategory fetchZendeskCategory(long zendeskCategoryId) {
		return getService().fetchZendeskCategory(zendeskCategoryId);
	}

	public static ZendeskCategory fetchZendeskCategory(
		String documentationKey) {

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

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the zendesk categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk categories
	 * @param end the upper bound of the range of zendesk categories (not inclusive)
	 * @return the range of zendesk categories
	 */
	public static List<ZendeskCategory> getZendeskCategories(
		int start, int end) {

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
	public static ZendeskCategory getZendeskCategory(long zendeskCategoryId)
		throws PortalException {

		return getService().getZendeskCategory(zendeskCategoryId);
	}

	public static ZendeskCategory updateZendeskCategory(
			long zendeskCategoryId, String documentationKey,
			String documentationOriginalURL, String articleLabels,
			long remoteUserSegmentId)
		throws PortalException {

		return getService().updateZendeskCategory(
			zendeskCategoryId, documentationKey, documentationOriginalURL,
			articleLabels, remoteUserSegmentId);
	}

	/**
	 * Updates the zendesk category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskCategoryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskCategory the zendesk category
	 * @return the zendesk category that was updated
	 */
	public static ZendeskCategory updateZendeskCategory(
		ZendeskCategory zendeskCategory) {

		return getService().updateZendeskCategory(zendeskCategory);
	}

	public static ZendeskCategoryLocalService getService() {
		return _service;
	}

	public static void setService(ZendeskCategoryLocalService service) {
		_service = service;
	}

	private static volatile ZendeskCategoryLocalService _service;

}