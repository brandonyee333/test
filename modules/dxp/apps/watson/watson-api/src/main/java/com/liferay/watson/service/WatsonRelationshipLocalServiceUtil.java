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
 * Provides the local service utility for WatsonRelationship. This utility wraps
 * <code>com.liferay.watson.service.impl.WatsonRelationshipLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonRelationshipLocalService
 * @generated
 */
public class WatsonRelationshipLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonRelationshipLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the watson relationship to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationship the watson relationship
	 * @return the watson relationship that was added
	 */
	public static com.liferay.watson.model.WatsonRelationship
		addWatsonRelationship(
			com.liferay.watson.model.WatsonRelationship watsonRelationship) {

		return getService().addWatsonRelationship(watsonRelationship);
	}

	/**
	 * Creates a new watson relationship with the primary key. Does not add the watson relationship to the database.
	 *
	 * @param watsonRelationshipId the primary key for the new watson relationship
	 * @return the new watson relationship
	 */
	public static com.liferay.watson.model.WatsonRelationship
		createWatsonRelationship(long watsonRelationshipId) {

		return getService().createWatsonRelationship(watsonRelationshipId);
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
	 * Deletes the watson relationship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationshipId the primary key of the watson relationship
	 * @return the watson relationship that was removed
	 * @throws PortalException if a watson relationship with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonRelationship
			deleteWatsonRelationship(long watsonRelationshipId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteWatsonRelationship(watsonRelationshipId);
	}

	/**
	 * Deletes the watson relationship from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationship the watson relationship
	 * @return the watson relationship that was removed
	 */
	public static com.liferay.watson.model.WatsonRelationship
		deleteWatsonRelationship(
			com.liferay.watson.model.WatsonRelationship watsonRelationship) {

		return getService().deleteWatsonRelationship(watsonRelationship);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonRelationshipModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonRelationshipModelImpl</code>.
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

	public static com.liferay.watson.model.WatsonRelationship
		fetchWatsonRelationship(long watsonRelationshipId) {

		return getService().fetchWatsonRelationship(watsonRelationshipId);
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
	 * Returns the watson relationship with the primary key.
	 *
	 * @param watsonRelationshipId the primary key of the watson relationship
	 * @return the watson relationship
	 * @throws PortalException if a watson relationship with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonRelationship
			getWatsonRelationship(long watsonRelationshipId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getWatsonRelationship(watsonRelationshipId);
	}

	/**
	 * Returns a range of all the watson relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationships
	 * @param end the upper bound of the range of watson relationships (not inclusive)
	 * @return the range of watson relationships
	 */
	public static java.util.List<com.liferay.watson.model.WatsonRelationship>
		getWatsonRelationships(int start, int end) {

		return getService().getWatsonRelationships(start, end);
	}

	/**
	 * Returns the number of watson relationships.
	 *
	 * @return the number of watson relationships
	 */
	public static int getWatsonRelationshipsCount() {
		return getService().getWatsonRelationshipsCount();
	}

	/**
	 * Updates the watson relationship in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationship the watson relationship
	 * @return the watson relationship that was updated
	 */
	public static com.liferay.watson.model.WatsonRelationship
		updateWatsonRelationship(
			com.liferay.watson.model.WatsonRelationship watsonRelationship) {

		return getService().updateWatsonRelationship(watsonRelationship);
	}

	public static WatsonRelationshipLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonRelationshipLocalService, WatsonRelationshipLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonRelationshipLocalService.class);

		ServiceTracker
			<WatsonRelationshipLocalService, WatsonRelationshipLocalService>
				serviceTracker =
					new ServiceTracker
						<WatsonRelationshipLocalService,
						 WatsonRelationshipLocalService>(
							 bundle.getBundleContext(),
							 WatsonRelationshipLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}