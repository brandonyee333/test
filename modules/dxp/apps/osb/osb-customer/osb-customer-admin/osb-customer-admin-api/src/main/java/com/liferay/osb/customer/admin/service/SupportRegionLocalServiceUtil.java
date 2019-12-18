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

package com.liferay.osb.customer.admin.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SupportRegion. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.SupportRegionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegionLocalService
 * @generated
 */
public class SupportRegionLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.SupportRegionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportRegionLocalServiceUtil} to access the support region local service. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.SupportRegionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static void addAccountEntrySupportRegion(
		long accountEntryId, long supportRegionId) {

		getService().addAccountEntrySupportRegion(
			accountEntryId, supportRegionId);
	}

	public static void addAccountEntrySupportRegion(
		long accountEntryId,
		com.liferay.osb.customer.admin.model.SupportRegion supportRegion) {

		getService().addAccountEntrySupportRegion(
			accountEntryId, supportRegion);
	}

	public static void addAccountEntrySupportRegions(
		long accountEntryId,
		java.util.List<com.liferay.osb.customer.admin.model.SupportRegion>
			supportRegions) {

		getService().addAccountEntrySupportRegions(
			accountEntryId, supportRegions);
	}

	public static void addAccountEntrySupportRegions(
		long accountEntryId, long[] supportRegionIds) {

		getService().addAccountEntrySupportRegions(
			accountEntryId, supportRegionIds);
	}

	public static com.liferay.osb.customer.admin.model.SupportRegion
			addSupportRegion(
				long userId, String name, String description, String timeZoneId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addSupportRegion(
			userId, name, description, timeZoneId);
	}

	/**
	 * Adds the support region to the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegion the support region
	 * @return the support region that was added
	 */
	public static com.liferay.osb.customer.admin.model.SupportRegion
		addSupportRegion(
			com.liferay.osb.customer.admin.model.SupportRegion supportRegion) {

		return getService().addSupportRegion(supportRegion);
	}

	public static void clearAccountEntrySupportRegions(long accountEntryId) {
		getService().clearAccountEntrySupportRegions(accountEntryId);
	}

	/**
	 * Creates a new support region with the primary key. Does not add the support region to the database.
	 *
	 * @param supportRegionId the primary key for the new support region
	 * @return the new support region
	 */
	public static com.liferay.osb.customer.admin.model.SupportRegion
		createSupportRegion(long supportRegionId) {

		return getService().createSupportRegion(supportRegionId);
	}

	public static void deleteAccountEntrySupportRegion(
		long accountEntryId, long supportRegionId) {

		getService().deleteAccountEntrySupportRegion(
			accountEntryId, supportRegionId);
	}

	public static void deleteAccountEntrySupportRegion(
		long accountEntryId,
		com.liferay.osb.customer.admin.model.SupportRegion supportRegion) {

		getService().deleteAccountEntrySupportRegion(
			accountEntryId, supportRegion);
	}

	public static void deleteAccountEntrySupportRegions(
		long accountEntryId,
		java.util.List<com.liferay.osb.customer.admin.model.SupportRegion>
			supportRegions) {

		getService().deleteAccountEntrySupportRegions(
			accountEntryId, supportRegions);
	}

	public static void deleteAccountEntrySupportRegions(
		long accountEntryId, long[] supportRegionIds) {

		getService().deleteAccountEntrySupportRegions(
			accountEntryId, supportRegionIds);
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
	 * Deletes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region that was removed
	 * @throws PortalException if a support region with the primary key could not be found
	 */
	public static com.liferay.osb.customer.admin.model.SupportRegion
			deleteSupportRegion(long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteSupportRegion(supportRegionId);
	}

	/**
	 * Deletes the support region from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegion the support region
	 * @return the support region that was removed
	 */
	public static com.liferay.osb.customer.admin.model.SupportRegion
		deleteSupportRegion(
			com.liferay.osb.customer.admin.model.SupportRegion supportRegion) {

		return getService().deleteSupportRegion(supportRegion);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.SupportRegionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.SupportRegionModelImpl</code>.
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

	public static com.liferay.osb.customer.admin.model.SupportRegion
		fetchSupportRegion(long supportRegionId) {

		return getService().fetchSupportRegion(supportRegionId);
	}

	public static com.liferay.osb.customer.admin.model.SupportRegion
		fetchSupportRegionByName(String name) {

		return getService().fetchSupportRegionByName(name);
	}

	/**
	 * Returns the accountEntryIds of the account entries associated with the support region.
	 *
	 * @param supportRegionId the supportRegionId of the support region
	 * @return long[] the accountEntryIds of account entries associated with the support region
	 */
	public static long[] getAccountEntryPrimaryKeys(long supportRegionId) {
		return getService().getAccountEntryPrimaryKeys(supportRegionId);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.SupportRegion>
			getAccountEntrySupportRegions(long accountEntryId) {

		return getService().getAccountEntrySupportRegions(accountEntryId);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.SupportRegion>
			getAccountEntrySupportRegions(
				long accountEntryId, int start, int end) {

		return getService().getAccountEntrySupportRegions(
			accountEntryId, start, end);
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.SupportRegion>
			getAccountEntrySupportRegions(
				long accountEntryId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.osb.customer.admin.model.SupportRegion>
						orderByComparator) {

		return getService().getAccountEntrySupportRegions(
			accountEntryId, start, end, orderByComparator);
	}

	public static int getAccountEntrySupportRegionsCount(long accountEntryId) {
		return getService().getAccountEntrySupportRegionsCount(accountEntryId);
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
	 * Returns the support region with the primary key.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region
	 * @throws PortalException if a support region with the primary key could not be found
	 */
	public static com.liferay.osb.customer.admin.model.SupportRegion
			getSupportRegion(long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getSupportRegion(supportRegionId);
	}

	/**
	 * Returns a range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of support regions
	 */
	public static java.util.List
		<com.liferay.osb.customer.admin.model.SupportRegion> getSupportRegions(
			int start, int end) {

		return getService().getSupportRegions(start, end);
	}

	/**
	 * Returns the number of support regions.
	 *
	 * @return the number of support regions
	 */
	public static int getSupportRegionsCount() {
		return getService().getSupportRegionsCount();
	}

	public static boolean hasAccountEntrySupportRegion(
		long accountEntryId, long supportRegionId) {

		return getService().hasAccountEntrySupportRegion(
			accountEntryId, supportRegionId);
	}

	public static boolean hasAccountEntrySupportRegions(long accountEntryId) {
		return getService().hasAccountEntrySupportRegions(accountEntryId);
	}

	public static void setAccountEntrySupportRegions(
		long accountEntryId, long[] supportRegionIds) {

		getService().setAccountEntrySupportRegions(
			accountEntryId, supportRegionIds);
	}

	public static com.liferay.osb.customer.admin.model.SupportRegion
			updateSupportRegion(
				long supportRegionId, String name, String description,
				String timeZoneId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateSupportRegion(
			supportRegionId, name, description, timeZoneId);
	}

	/**
	 * Updates the support region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegion the support region
	 * @return the support region that was updated
	 */
	public static com.liferay.osb.customer.admin.model.SupportRegion
		updateSupportRegion(
			com.liferay.osb.customer.admin.model.SupportRegion supportRegion) {

		return getService().updateSupportRegion(supportRegion);
	}

	public static SupportRegionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<SupportRegionLocalService, SupportRegionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			SupportRegionLocalService.class);

		ServiceTracker<SupportRegionLocalService, SupportRegionLocalService>
			serviceTracker =
				new ServiceTracker
					<SupportRegionLocalService, SupportRegionLocalService>(
						bundle.getBundleContext(),
						SupportRegionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}