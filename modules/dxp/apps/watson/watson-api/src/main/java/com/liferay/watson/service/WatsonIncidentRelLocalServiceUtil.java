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
 * Provides the local service utility for WatsonIncidentRel. This utility wraps
 * <code>com.liferay.watson.service.impl.WatsonIncidentRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonIncidentRelLocalService
 * @generated
 */
public class WatsonIncidentRelLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonIncidentRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the watson incident rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRel the watson incident rel
	 * @return the watson incident rel that was added
	 */
	public static com.liferay.watson.model.WatsonIncidentRel
		addWatsonIncidentRel(
			com.liferay.watson.model.WatsonIncidentRel watsonIncidentRel) {

		return getService().addWatsonIncidentRel(watsonIncidentRel);
	}

	/**
	 * Creates a new watson incident rel with the primary key. Does not add the watson incident rel to the database.
	 *
	 * @param watsonIncidentRelId the primary key for the new watson incident rel
	 * @return the new watson incident rel
	 */
	public static com.liferay.watson.model.WatsonIncidentRel
		createWatsonIncidentRel(long watsonIncidentRelId) {

		return getService().createWatsonIncidentRel(watsonIncidentRelId);
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
	 * Deletes the watson incident rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel that was removed
	 * @throws PortalException if a watson incident rel with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonIncidentRel
			deleteWatsonIncidentRel(long watsonIncidentRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteWatsonIncidentRel(watsonIncidentRelId);
	}

	/**
	 * Deletes the watson incident rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRel the watson incident rel
	 * @return the watson incident rel that was removed
	 */
	public static com.liferay.watson.model.WatsonIncidentRel
		deleteWatsonIncidentRel(
			com.liferay.watson.model.WatsonIncidentRel watsonIncidentRel) {

		return getService().deleteWatsonIncidentRel(watsonIncidentRel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelModelImpl</code>.
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

	public static com.liferay.watson.model.WatsonIncidentRel
		fetchWatsonIncidentRel(long watsonIncidentRelId) {

		return getService().fetchWatsonIncidentRel(watsonIncidentRelId);
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
	 * Returns the watson incident rel with the primary key.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel
	 * @throws PortalException if a watson incident rel with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonIncidentRel
			getWatsonIncidentRel(long watsonIncidentRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getWatsonIncidentRel(watsonIncidentRelId);
	}

	/**
	 * Returns a range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @return the range of watson incident rels
	 */
	public static java.util.List<com.liferay.watson.model.WatsonIncidentRel>
		getWatsonIncidentRels(int start, int end) {

		return getService().getWatsonIncidentRels(start, end);
	}

	/**
	 * Returns the number of watson incident rels.
	 *
	 * @return the number of watson incident rels
	 */
	public static int getWatsonIncidentRelsCount() {
		return getService().getWatsonIncidentRelsCount();
	}

	/**
	 * Updates the watson incident rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRel the watson incident rel
	 * @return the watson incident rel that was updated
	 */
	public static com.liferay.watson.model.WatsonIncidentRel
		updateWatsonIncidentRel(
			com.liferay.watson.model.WatsonIncidentRel watsonIncidentRel) {

		return getService().updateWatsonIncidentRel(watsonIncidentRel);
	}

	public static WatsonIncidentRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonIncidentRelLocalService, WatsonIncidentRelLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonIncidentRelLocalService.class);

		ServiceTracker
			<WatsonIncidentRelLocalService, WatsonIncidentRelLocalService>
				serviceTracker =
					new ServiceTracker
						<WatsonIncidentRelLocalService,
						 WatsonIncidentRelLocalService>(
							 bundle.getBundleContext(),
							 WatsonIncidentRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}