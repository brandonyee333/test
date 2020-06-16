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

package com.liferay.osb.loop.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LoopJobTitle. This utility wraps
 * <code>com.liferay.osb.loop.service.impl.LoopJobTitleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see LoopJobTitleLocalService
 * @generated
 */
public class LoopJobTitleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.service.impl.LoopJobTitleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the loop job title to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopJobTitle the loop job title
	 * @return the loop job title that was added
	 */
	public static com.liferay.osb.loop.model.LoopJobTitle addLoopJobTitle(
		com.liferay.osb.loop.model.LoopJobTitle loopJobTitle) {

		return getService().addLoopJobTitle(loopJobTitle);
	}

	/**
	 * Creates a new loop job title with the primary key. Does not add the loop job title to the database.
	 *
	 * @param loopJobTitleId the primary key for the new loop job title
	 * @return the new loop job title
	 */
	public static com.liferay.osb.loop.model.LoopJobTitle createLoopJobTitle(
		long loopJobTitleId) {

		return getService().createLoopJobTitle(loopJobTitleId);
	}

	/**
	 * Deletes the loop job title with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title that was removed
	 * @throws PortalException if a loop job title with the primary key could not be found
	 */
	public static com.liferay.osb.loop.model.LoopJobTitle deleteLoopJobTitle(
			long loopJobTitleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLoopJobTitle(loopJobTitleId);
	}

	/**
	 * Deletes the loop job title from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopJobTitle the loop job title
	 * @return the loop job title that was removed
	 */
	public static com.liferay.osb.loop.model.LoopJobTitle deleteLoopJobTitle(
		com.liferay.osb.loop.model.LoopJobTitle loopJobTitle) {

		return getService().deleteLoopJobTitle(loopJobTitle);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopJobTitleModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopJobTitleModelImpl</code>.
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

	public static com.liferay.osb.loop.model.LoopJobTitle fetchLoopJobTitle(
		long loopJobTitleId) {

		return getService().fetchLoopJobTitle(loopJobTitleId);
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
	 * Returns the loop job title with the primary key.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title
	 * @throws PortalException if a loop job title with the primary key could not be found
	 */
	public static com.liferay.osb.loop.model.LoopJobTitle getLoopJobTitle(
			long loopJobTitleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLoopJobTitle(loopJobTitleId);
	}

	/**
	 * Returns a range of all the loop job titles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopJobTitleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop job titles
	 * @param end the upper bound of the range of loop job titles (not inclusive)
	 * @return the range of loop job titles
	 */
	public static java.util.List<com.liferay.osb.loop.model.LoopJobTitle>
		getLoopJobTitles(int start, int end) {

		return getService().getLoopJobTitles(start, end);
	}

	/**
	 * Returns the number of loop job titles.
	 *
	 * @return the number of loop job titles
	 */
	public static int getLoopJobTitlesCount() {
		return getService().getLoopJobTitlesCount();
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
	 * Updates the loop job title in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopJobTitle the loop job title
	 * @return the loop job title that was updated
	 */
	public static com.liferay.osb.loop.model.LoopJobTitle updateLoopJobTitle(
		com.liferay.osb.loop.model.LoopJobTitle loopJobTitle) {

		return getService().updateLoopJobTitle(loopJobTitle);
	}

	public static LoopJobTitleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LoopJobTitleLocalService, LoopJobTitleLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopJobTitleLocalService.class);

		ServiceTracker<LoopJobTitleLocalService, LoopJobTitleLocalService>
			serviceTracker =
				new ServiceTracker
					<LoopJobTitleLocalService, LoopJobTitleLocalService>(
						bundle.getBundleContext(),
						LoopJobTitleLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}