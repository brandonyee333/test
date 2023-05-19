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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for ZendeskSection. This utility wraps
 * <code>com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskSectionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskSectionLocalService
 * @generated
 */
public class ZendeskSectionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskSectionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ZendeskSection addZendeskSection(
			long zendeskCategoryId, String documentationKey,
			Map<java.util.Locale, String> remoteNameMap, int position)
		throws PortalException {

		return getService().addZendeskSection(
			zendeskCategoryId, documentationKey, remoteNameMap, position);
	}

	/**
	 * Adds the zendesk section to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskSectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskSection the zendesk section
	 * @return the zendesk section that was added
	 */
	public static ZendeskSection addZendeskSection(
		ZendeskSection zendeskSection) {

		return getService().addZendeskSection(zendeskSection);
	}

	/**
	 * Creates a new zendesk section with the primary key. Does not add the zendesk section to the database.
	 *
	 * @param zendeskSectionId the primary key for the new zendesk section
	 * @return the new zendesk section
	 */
	public static ZendeskSection createZendeskSection(long zendeskSectionId) {
		return getService().createZendeskSection(zendeskSectionId);
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
	 * Deletes the zendesk section with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskSectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskSectionId the primary key of the zendesk section
	 * @return the zendesk section that was removed
	 * @throws PortalException if a zendesk section with the primary key could not be found
	 */
	public static ZendeskSection deleteZendeskSection(long zendeskSectionId)
		throws PortalException {

		return getService().deleteZendeskSection(zendeskSectionId);
	}

	/**
	 * Deletes the zendesk section from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskSectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskSection the zendesk section
	 * @return the zendesk section that was removed
	 * @throws PortalException
	 */
	public static ZendeskSection deleteZendeskSection(
			ZendeskSection zendeskSection)
		throws PortalException {

		return getService().deleteZendeskSection(zendeskSection);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskSectionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskSectionModelImpl</code>.
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

	public static ZendeskSection fetchZendeskSection(long zendeskSectionId) {
		return getService().fetchZendeskSection(zendeskSectionId);
	}

	public static ZendeskSection fetchZendeskSection(
		long zendeskCategoryId, String documentationKey) {

		return getService().fetchZendeskSection(
			zendeskCategoryId, documentationKey);
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
	 * Returns the zendesk section with the primary key.
	 *
	 * @param zendeskSectionId the primary key of the zendesk section
	 * @return the zendesk section
	 * @throws PortalException if a zendesk section with the primary key could not be found
	 */
	public static ZendeskSection getZendeskSection(long zendeskSectionId)
		throws PortalException {

		return getService().getZendeskSection(zendeskSectionId);
	}

	/**
	 * Returns a range of all the zendesk sections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskSectionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk sections
	 * @param end the upper bound of the range of zendesk sections (not inclusive)
	 * @return the range of zendesk sections
	 */
	public static List<ZendeskSection> getZendeskSections(int start, int end) {
		return getService().getZendeskSections(start, end);
	}

	public static List<ZendeskSection> getZendeskSections(
		long zendeskCategoryId) {

		return getService().getZendeskSections(zendeskCategoryId);
	}

	/**
	 * Returns the number of zendesk sections.
	 *
	 * @return the number of zendesk sections
	 */
	public static int getZendeskSectionsCount() {
		return getService().getZendeskSectionsCount();
	}

	public static int getZendeskSectionsCount(long zendeskCategoryId) {
		return getService().getZendeskSectionsCount(zendeskCategoryId);
	}

	public static ZendeskSection updateZendeskSection(
			long zendeskSectionId, long zendeskCategoryId,
			String documentationKey,
			Map<java.util.Locale, String> remoteNameMap, int position)
		throws PortalException {

		return getService().updateZendeskSection(
			zendeskSectionId, zendeskCategoryId, documentationKey,
			remoteNameMap, position);
	}

	/**
	 * Updates the zendesk section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ZendeskSectionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param zendeskSection the zendesk section
	 * @return the zendesk section that was updated
	 */
	public static ZendeskSection updateZendeskSection(
		ZendeskSection zendeskSection) {

		return getService().updateZendeskSection(zendeskSection);
	}

	public static ZendeskSection updateZendeskSectionTranslation(
			long zendeskSectionId, java.util.Locale locale, String remoteName)
		throws PortalException {

		return getService().updateZendeskSectionTranslation(
			zendeskSectionId, locale, remoteName);
	}

	public static ZendeskSectionLocalService getService() {
		return _service;
	}

	public static void setService(ZendeskSectionLocalService service) {
		_service = service;
	}

	private static volatile ZendeskSectionLocalService _service;

}