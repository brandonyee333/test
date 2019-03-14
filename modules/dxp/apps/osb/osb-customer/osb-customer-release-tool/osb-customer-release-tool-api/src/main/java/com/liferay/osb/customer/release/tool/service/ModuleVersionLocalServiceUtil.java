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
 * Provides the local service utility for ModuleVersion. This utility wraps
 * <code>com.liferay.osb.customer.release.tool.service.impl.ModuleVersionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleVersionLocalService
 * @generated
 */
@ProviderType
public class ModuleVersionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.release.tool.service.impl.ModuleVersionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the module version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleVersion the module version
	 * @return the module version that was added
	 */
	public static com.liferay.osb.customer.release.tool.model.ModuleVersion
		addModuleVersion(
			com.liferay.osb.customer.release.tool.model.ModuleVersion
				moduleVersion) {

		return getService().addModuleVersion(moduleVersion);
	}

	/**
	 * Creates a new module version with the primary key. Does not add the module version to the database.
	 *
	 * @param moduleVersionId the primary key for the new module version
	 * @return the new module version
	 */
	public static com.liferay.osb.customer.release.tool.model.ModuleVersion
		createModuleVersion(long moduleVersionId) {

		return getService().createModuleVersion(moduleVersionId);
	}

	/**
	 * Deletes the module version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version that was removed
	 * @throws PortalException if a module version with the primary key could not be found
	 */
	public static com.liferay.osb.customer.release.tool.model.ModuleVersion
			deleteModuleVersion(long moduleVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteModuleVersion(moduleVersionId);
	}

	/**
	 * Deletes the module version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleVersion the module version
	 * @return the module version that was removed
	 */
	public static com.liferay.osb.customer.release.tool.model.ModuleVersion
		deleteModuleVersion(
			com.liferay.osb.customer.release.tool.model.ModuleVersion
				moduleVersion) {

		return getService().deleteModuleVersion(moduleVersion);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.tool.model.impl.ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.tool.model.impl.ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.customer.release.tool.model.ModuleVersion
		fetchModuleVersion(long moduleVersionId) {

		return getService().fetchModuleVersion(moduleVersionId);
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
	 * Returns the module version with the primary key.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version
	 * @throws PortalException if a module version with the primary key could not be found
	 */
	public static com.liferay.osb.customer.release.tool.model.ModuleVersion
			getModuleVersion(long moduleVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getModuleVersion(moduleVersionId);
	}

	/**
	 * Returns a range of all the module versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.tool.model.impl.ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module versions
	 * @param end the upper bound of the range of module versions (not inclusive)
	 * @return the range of module versions
	 */
	public static java.util.List
		<com.liferay.osb.customer.release.tool.model.ModuleVersion>
			getModuleVersions(int start, int end) {

		return getService().getModuleVersions(start, end);
	}

	/**
	 * Returns the number of module versions.
	 *
	 * @return the number of module versions
	 */
	public static int getModuleVersionsCount() {
		return getService().getModuleVersionsCount();
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
	 * Updates the module version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param moduleVersion the module version
	 * @return the module version that was updated
	 */
	public static com.liferay.osb.customer.release.tool.model.ModuleVersion
		updateModuleVersion(
			com.liferay.osb.customer.release.tool.model.ModuleVersion
				moduleVersion) {

		return getService().updateModuleVersion(moduleVersion);
	}

	public static ModuleVersionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ModuleVersionLocalService, ModuleVersionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ModuleVersionLocalService.class);

		ServiceTracker<ModuleVersionLocalService, ModuleVersionLocalService>
			serviceTracker =
				new ServiceTracker
					<ModuleVersionLocalService, ModuleVersionLocalService>(
						bundle.getBundleContext(),
						ModuleVersionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}