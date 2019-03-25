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

package com.liferay.osb.customer.release.tool.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for JIRAComponent. This utility wraps
 * <code>com.liferay.osb.customer.release.tool.service.impl.JIRAComponentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAComponentLocalService
 * @generated
 */
@ProviderType
public class JIRAComponentLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.release.tool.service.impl.JIRAComponentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the jira component to the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraComponent the jira component
	 * @return the jira component that was added
	 */
	public static com.liferay.osb.customer.release.tool.model.JIRAComponent
		addJIRAComponent(
			com.liferay.osb.customer.release.tool.model.JIRAComponent
				jiraComponent) {

		return getService().addJIRAComponent(jiraComponent);
	}

	public static com.liferay.osb.customer.release.tool.model.JIRAComponent
			addJIRAComponent(long remoteId, String name, boolean visible)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addJIRAComponent(remoteId, name, visible);
	}

	/**
	 * Creates a new jira component with the primary key. Does not add the jira component to the database.
	 *
	 * @param jiraComponentId the primary key for the new jira component
	 * @return the new jira component
	 */
	public static com.liferay.osb.customer.release.tool.model.JIRAComponent
		createJIRAComponent(long jiraComponentId) {

		return getService().createJIRAComponent(jiraComponentId);
	}

	/**
	 * Deletes the jira component from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraComponent the jira component
	 * @return the jira component that was removed
	 */
	public static com.liferay.osb.customer.release.tool.model.JIRAComponent
		deleteJIRAComponent(
			com.liferay.osb.customer.release.tool.model.JIRAComponent
				jiraComponent) {

		return getService().deleteJIRAComponent(jiraComponent);
	}

	/**
	 * Deletes the jira component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component that was removed
	 * @throws PortalException if a jira component with the primary key could not be found
	 */
	public static com.liferay.osb.customer.release.tool.model.JIRAComponent
			deleteJIRAComponent(long jiraComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteJIRAComponent(jiraComponentId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.tool.model.impl.JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.tool.model.impl.JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.customer.release.tool.model.JIRAComponent
		fetchJIRAComponent(long jiraComponentId) {

		return getService().fetchJIRAComponent(jiraComponentId);
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
	 * Returns the jira component with the primary key.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component
	 * @throws PortalException if a jira component with the primary key could not be found
	 */
	public static com.liferay.osb.customer.release.tool.model.JIRAComponent
			getJIRAComponent(long jiraComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getJIRAComponent(jiraComponentId);
	}

	public static java.util.List
		<com.liferay.osb.customer.release.tool.model.JIRAComponent>
			getJIRAComponents(boolean visible) {

		return getService().getJIRAComponents(visible);
	}

	/**
	 * Returns a range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.tool.model.impl.JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of jira components
	 */
	public static java.util.List
		<com.liferay.osb.customer.release.tool.model.JIRAComponent>
			getJIRAComponents(int start, int end) {

		return getService().getJIRAComponents(start, end);
	}

	/**
	 * Returns the number of jira components.
	 *
	 * @return the number of jira components
	 */
	public static int getJIRAComponentsCount() {
		return getService().getJIRAComponentsCount();
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
	 * Updates the jira component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param jiraComponent the jira component
	 * @return the jira component that was updated
	 */
	public static com.liferay.osb.customer.release.tool.model.JIRAComponent
		updateJIRAComponent(
			com.liferay.osb.customer.release.tool.model.JIRAComponent
				jiraComponent) {

		return getService().updateJIRAComponent(jiraComponent);
	}

	public static com.liferay.osb.customer.release.tool.model.JIRAComponent
			updateJIRAComponent(long jiraComponentId, boolean visible)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateJIRAComponent(jiraComponentId, visible);
	}

	public static com.liferay.osb.customer.release.tool.model.JIRAComponent
			updateJIRAComponent(long remoteId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateJIRAComponent(remoteId, name);
	}

	public static JIRAComponentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<JIRAComponentLocalService, JIRAComponentLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			JIRAComponentLocalService.class);

		ServiceTracker<JIRAComponentLocalService, JIRAComponentLocalService>
			serviceTracker =
				new ServiceTracker
					<JIRAComponentLocalService, JIRAComponentLocalService>(
						bundle.getBundleContext(),
						JIRAComponentLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}