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
 * Provides the local service utility for LoopTopic. This utility wraps
 * <code>com.liferay.osb.loop.service.impl.LoopTopicLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see LoopTopicLocalService
 * @generated
 */
public class LoopTopicLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.service.impl.LoopTopicLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.loop.model.LoopTopic addLoopTopic(
			long userId, String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addLoopTopic(userId, name, description);
	}

	/**
	 * Adds the loop topic to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopic the loop topic
	 * @return the loop topic that was added
	 */
	public static com.liferay.osb.loop.model.LoopTopic addLoopTopic(
		com.liferay.osb.loop.model.LoopTopic loopTopic) {

		return getService().addLoopTopic(loopTopic);
	}

	/**
	 * Creates a new loop topic with the primary key. Does not add the loop topic to the database.
	 *
	 * @param loopTopicId the primary key for the new loop topic
	 * @return the new loop topic
	 */
	public static com.liferay.osb.loop.model.LoopTopic createLoopTopic(
		long loopTopicId) {

		return getService().createLoopTopic(loopTopicId);
	}

	/**
	 * Deletes the loop topic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic that was removed
	 * @throws PortalException if a loop topic with the primary key could not be found
	 */
	public static com.liferay.osb.loop.model.LoopTopic deleteLoopTopic(
			long loopTopicId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLoopTopic(loopTopicId);
	}

	/**
	 * Deletes the loop topic from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopic the loop topic
	 * @return the loop topic that was removed
	 */
	public static com.liferay.osb.loop.model.LoopTopic deleteLoopTopic(
		com.liferay.osb.loop.model.LoopTopic loopTopic) {

		return getService().deleteLoopTopic(loopTopic);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopTopicModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopTopicModelImpl</code>.
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

	public static com.liferay.osb.loop.model.LoopTopic fetchLoopTopic(
		long loopTopicId) {

		return getService().fetchLoopTopic(loopTopicId);
	}

	public static com.liferay.osb.loop.model.LoopTopic fetchLoopTopic(
		long companyId, String name) {

		return getService().fetchLoopTopic(companyId, name);
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
	 * Returns the loop topic with the primary key.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic
	 * @throws PortalException if a loop topic with the primary key could not be found
	 */
	public static com.liferay.osb.loop.model.LoopTopic getLoopTopic(
			long loopTopicId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLoopTopic(loopTopicId);
	}

	public static com.liferay.osb.loop.model.LoopTopic getLoopTopic(
			long companyId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLoopTopic(companyId, name);
	}

	/**
	 * Returns a range of all the loop topics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopTopicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topics
	 * @param end the upper bound of the range of loop topics (not inclusive)
	 * @return the range of loop topics
	 */
	public static java.util.List<com.liferay.osb.loop.model.LoopTopic>
		getLoopTopics(int start, int end) {

		return getService().getLoopTopics(start, end);
	}

	/**
	 * Returns the number of loop topics.
	 *
	 * @return the number of loop topics
	 */
	public static int getLoopTopicsCount() {
		return getService().getLoopTopicsCount();
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

	public static boolean isLoopTopic(long companyId, String name) {
		return getService().isLoopTopic(companyId, name);
	}

	public static void updateLoopTopic(
			long userId, String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateLoopTopic(userId, name, description);
	}

	/**
	 * Updates the loop topic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopic the loop topic
	 * @return the loop topic that was updated
	 */
	public static com.liferay.osb.loop.model.LoopTopic updateLoopTopic(
		com.liferay.osb.loop.model.LoopTopic loopTopic) {

		return getService().updateLoopTopic(loopTopic);
	}

	public static LoopTopicLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopTopicLocalService, LoopTopicLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopTopicLocalService.class);

		ServiceTracker<LoopTopicLocalService, LoopTopicLocalService>
			serviceTracker =
				new ServiceTracker
					<LoopTopicLocalService, LoopTopicLocalService>(
						bundle.getBundleContext(), LoopTopicLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}