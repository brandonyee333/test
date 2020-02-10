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

package com.liferay.watson.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for WatsonActivity. This utility wraps
 * <code>com.liferay.watson.service.impl.WatsonActivityLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonActivityLocalService
 * @generated
 */
public class WatsonActivityLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonActivityLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the watson activity to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivity the watson activity
	 * @return the watson activity that was added
	 */
	public static com.liferay.watson.model.WatsonActivity addWatsonActivity(
		com.liferay.watson.model.WatsonActivity watsonActivity) {

		return getService().addWatsonActivity(watsonActivity);
	}

	/**
	 * Creates a new watson activity with the primary key. Does not add the watson activity to the database.
	 *
	 * @param watsonActivityId the primary key for the new watson activity
	 * @return the new watson activity
	 */
	public static com.liferay.watson.model.WatsonActivity createWatsonActivity(
		long watsonActivityId) {

		return getService().createWatsonActivity(watsonActivityId);
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
	 * Deletes the watson activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivityId the primary key of the watson activity
	 * @return the watson activity that was removed
	 * @throws PortalException if a watson activity with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonActivity deleteWatsonActivity(
			long watsonActivityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteWatsonActivity(watsonActivityId);
	}

	/**
	 * Deletes the watson activity from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivity the watson activity
	 * @return the watson activity that was removed
	 */
	public static com.liferay.watson.model.WatsonActivity deleteWatsonActivity(
		com.liferay.watson.model.WatsonActivity watsonActivity) {

		return getService().deleteWatsonActivity(watsonActivity);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonActivityModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonActivityModelImpl</code>.
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

	public static com.liferay.watson.model.WatsonActivity fetchWatsonActivity(
		long watsonActivityId) {

		return getService().fetchWatsonActivity(watsonActivityId);
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
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the watson activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activities
	 * @param end the upper bound of the range of watson activities (not inclusive)
	 * @return the range of watson activities
	 */
	public static java.util.List<com.liferay.watson.model.WatsonActivity>
		getWatsonActivities(int start, int end) {

		return getService().getWatsonActivities(start, end);
	}

	/**
	 * Returns the number of watson activities.
	 *
	 * @return the number of watson activities
	 */
	public static int getWatsonActivitiesCount() {
		return getService().getWatsonActivitiesCount();
	}

	/**
	 * Returns the watson activity with the primary key.
	 *
	 * @param watsonActivityId the primary key of the watson activity
	 * @return the watson activity
	 * @throws PortalException if a watson activity with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonActivity getWatsonActivity(
			long watsonActivityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getWatsonActivity(watsonActivityId);
	}

	/**
	 * Updates the watson activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivity the watson activity
	 * @return the watson activity that was updated
	 */
	public static com.liferay.watson.model.WatsonActivity updateWatsonActivity(
		com.liferay.watson.model.WatsonActivity watsonActivity) {

		return getService().updateWatsonActivity(watsonActivity);
	}

	public static WatsonActivityLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonActivityLocalService, WatsonActivityLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonActivityLocalService.class);

		ServiceTracker<WatsonActivityLocalService, WatsonActivityLocalService>
			serviceTracker =
				new ServiceTracker
					<WatsonActivityLocalService, WatsonActivityLocalService>(
						bundle.getBundleContext(),
						WatsonActivityLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}