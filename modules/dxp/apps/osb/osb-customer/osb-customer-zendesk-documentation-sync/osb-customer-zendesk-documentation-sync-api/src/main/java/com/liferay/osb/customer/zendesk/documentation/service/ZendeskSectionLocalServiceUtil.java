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
 * Provides the local service utility for ZendeskSection. This utility wraps
 * {@link com.liferay.osb.customer.zendesk.documentation.service.impl.ZendeskSectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskSectionLocalService
 * @see com.liferay.osb.customer.zendesk.documentation.service.base.ZendeskSectionLocalServiceBaseImpl
 * @see com.liferay.osb.customer.zendesk.documentation.service.impl.ZendeskSectionLocalServiceImpl
 * @generated
 */
@ProviderType
public class ZendeskSectionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.customer.zendesk.documentation.service.impl.ZendeskSectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection addZendeskSection(
		long zendeskCategoryId, String documentationKey,
		java.util.Map<String, String> nameMap,
		java.util.Map<String, String> descriptionMap, int position)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addZendeskSection(zendeskCategoryId, documentationKey,
			nameMap, descriptionMap, position);
	}

	/**
	* Adds the zendesk section to the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskSection the zendesk section
	* @return the zendesk section that was added
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection addZendeskSection(
		com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection zendeskSection) {
		return getService().addZendeskSection(zendeskSection);
	}

	/**
	* Creates a new zendesk section with the primary key. Does not add the zendesk section to the database.
	*
	* @param zendeskSectionId the primary key for the new zendesk section
	* @return the new zendesk section
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection createZendeskSection(
		long zendeskSectionId) {
		return getService().createZendeskSection(zendeskSectionId);
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
	* Deletes the zendesk section with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskSectionId the primary key of the zendesk section
	* @return the zendesk section that was removed
	* @throws PortalException if a zendesk section with the primary key could not be found
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection deleteZendeskSection(
		long zendeskSectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteZendeskSection(zendeskSectionId);
	}

	/**
	* Deletes the zendesk section from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskSection the zendesk section
	* @return the zendesk section that was removed
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection deleteZendeskSection(
		com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection zendeskSection) {
		return getService().deleteZendeskSection(zendeskSection);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection fetchZendeskSection(
		long zendeskSectionId) {
		return getService().fetchZendeskSection(zendeskSectionId);
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
	* Returns the zendesk section with the primary key.
	*
	* @param zendeskSectionId the primary key of the zendesk section
	* @return the zendesk section
	* @throws PortalException if a zendesk section with the primary key could not be found
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection getZendeskSection(
		long zendeskSectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getZendeskSection(zendeskSectionId);
	}

	/**
	* Returns a range of all the zendesk sections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk sections
	* @param end the upper bound of the range of zendesk sections (not inclusive)
	* @return the range of zendesk sections
	*/
	public static java.util.List<com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection> getZendeskSections(
		int start, int end) {
		return getService().getZendeskSections(start, end);
	}

	/**
	* Returns the number of zendesk sections.
	*
	* @return the number of zendesk sections
	*/
	public static int getZendeskSectionsCount() {
		return getService().getZendeskSectionsCount();
	}

	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection updateZendeskSection(
		long zendeskSectionId, long zendeskCategoryId, String documentationKey,
		java.util.Map<String, String> nameMap,
		java.util.Map<String, String> descriptionMap, int position)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateZendeskSection(zendeskSectionId, zendeskCategoryId,
			documentationKey, nameMap, descriptionMap, position);
	}

	/**
	* Updates the zendesk section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param zendeskSection the zendesk section
	* @return the zendesk section that was updated
	*/
	public static com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection updateZendeskSection(
		com.liferay.osb.customer.zendesk.documentation.model.ZendeskSection zendeskSection) {
		return getService().updateZendeskSection(zendeskSection);
	}

	public static ZendeskSectionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ZendeskSectionLocalService, ZendeskSectionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ZendeskSectionLocalService.class);

		ServiceTracker<ZendeskSectionLocalService, ZendeskSectionLocalService> serviceTracker =
			new ServiceTracker<ZendeskSectionLocalService, ZendeskSectionLocalService>(bundle.getBundleContext(),
				ZendeskSectionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}