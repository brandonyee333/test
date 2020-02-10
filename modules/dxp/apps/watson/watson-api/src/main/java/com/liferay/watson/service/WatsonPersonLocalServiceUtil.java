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
 * Provides the local service utility for WatsonPerson. This utility wraps
 * <code>com.liferay.watson.service.impl.WatsonPersonLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonPersonLocalService
 * @generated
 */
public class WatsonPersonLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonPersonLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the watson person to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPerson the watson person
	 * @return the watson person that was added
	 */
	public static com.liferay.watson.model.WatsonPerson addWatsonPerson(
		com.liferay.watson.model.WatsonPerson watsonPerson) {

		return getService().addWatsonPerson(watsonPerson);
	}

	/**
	 * Creates a new watson person with the primary key. Does not add the watson person to the database.
	 *
	 * @param watsonPersonId the primary key for the new watson person
	 * @return the new watson person
	 */
	public static com.liferay.watson.model.WatsonPerson createWatsonPerson(
		long watsonPersonId) {

		return getService().createWatsonPerson(watsonPersonId);
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
	 * Deletes the watson person with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPersonId the primary key of the watson person
	 * @return the watson person that was removed
	 * @throws PortalException if a watson person with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonPerson deleteWatsonPerson(
			long watsonPersonId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteWatsonPerson(watsonPersonId);
	}

	/**
	 * Deletes the watson person from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPerson the watson person
	 * @return the watson person that was removed
	 */
	public static com.liferay.watson.model.WatsonPerson deleteWatsonPerson(
		com.liferay.watson.model.WatsonPerson watsonPerson) {

		return getService().deleteWatsonPerson(watsonPerson);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonPersonModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonPersonModelImpl</code>.
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

	public static com.liferay.watson.model.WatsonPerson fetchWatsonPerson(
		long watsonPersonId) {

		return getService().fetchWatsonPerson(watsonPersonId);
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
	 * Returns the watson person with the primary key.
	 *
	 * @param watsonPersonId the primary key of the watson person
	 * @return the watson person
	 * @throws PortalException if a watson person with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonPerson getWatsonPerson(
			long watsonPersonId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getWatsonPerson(watsonPersonId);
	}

	/**
	 * Returns a range of all the watson persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonPersonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson persons
	 * @param end the upper bound of the range of watson persons (not inclusive)
	 * @return the range of watson persons
	 */
	public static java.util.List<com.liferay.watson.model.WatsonPerson>
		getWatsonPersons(int start, int end) {

		return getService().getWatsonPersons(start, end);
	}

	/**
	 * Returns the number of watson persons.
	 *
	 * @return the number of watson persons
	 */
	public static int getWatsonPersonsCount() {
		return getService().getWatsonPersonsCount();
	}

	/**
	 * Updates the watson person in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPerson the watson person
	 * @return the watson person that was updated
	 */
	public static com.liferay.watson.model.WatsonPerson updateWatsonPerson(
		com.liferay.watson.model.WatsonPerson watsonPerson) {

		return getService().updateWatsonPerson(watsonPerson);
	}

	public static WatsonPersonLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonPersonLocalService, WatsonPersonLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonPersonLocalService.class);

		ServiceTracker<WatsonPersonLocalService, WatsonPersonLocalService>
			serviceTracker =
				new ServiceTracker
					<WatsonPersonLocalService, WatsonPersonLocalService>(
						bundle.getBundleContext(),
						WatsonPersonLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}