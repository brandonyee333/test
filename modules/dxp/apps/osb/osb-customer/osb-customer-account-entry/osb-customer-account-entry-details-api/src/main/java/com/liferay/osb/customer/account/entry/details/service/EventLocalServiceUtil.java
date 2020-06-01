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

package com.liferay.osb.customer.account.entry.details.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Event. This utility wraps
 * <code>com.liferay.osb.customer.account.entry.details.service.impl.EventLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see EventLocalService
 * @generated
 */
public class EventLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.account.entry.details.service.impl.EventLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the event to the database. Also notifies the appropriate model listeners.
	 *
	 * @param event the event
	 * @return the event that was added
	 */
	public static com.liferay.osb.customer.account.entry.details.model.Event
		addEvent(
			com.liferay.osb.customer.account.entry.details.model.Event event) {

		return getService().addEvent(event);
	}

	public static com.liferay.osb.customer.account.entry.details.model.Event
			addEvent(
				long userId, java.util.Date occurDate, long accountEntryId,
				long classNameId, long classPK, int type, long typeClassNameId,
				long typeClassPK, String title, String summary,
				String additionalInfo)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addEvent(
			userId, occurDate, accountEntryId, classNameId, classPK, type,
			typeClassNameId, typeClassPK, title, summary, additionalInfo);
	}

	/**
	 * Creates a new event with the primary key. Does not add the event to the database.
	 *
	 * @param eventId the primary key for the new event
	 * @return the new event
	 */
	public static com.liferay.osb.customer.account.entry.details.model.Event
		createEvent(long eventId) {

		return getService().createEvent(eventId);
	}

	/**
	 * Deletes the event from the database. Also notifies the appropriate model listeners.
	 *
	 * @param event the event
	 * @return the event that was removed
	 */
	public static com.liferay.osb.customer.account.entry.details.model.Event
		deleteEvent(
			com.liferay.osb.customer.account.entry.details.model.Event event) {

		return getService().deleteEvent(event);
	}

	/**
	 * Deletes the event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the event
	 * @return the event that was removed
	 * @throws PortalException if a event with the primary key could not be found
	 */
	public static com.liferay.osb.customer.account.entry.details.model.Event
			deleteEvent(long eventId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteEvent(eventId);
	}

	public static void deleteEvents(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK) {

		getService().deleteEvents(
			classNameId, classPK, type, typeClassNameId, typeClassPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.account.entry.details.model.impl.EventModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.account.entry.details.model.impl.EventModelImpl</code>.
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

	public static com.liferay.osb.customer.account.entry.details.model.Event
		fetchEvent(long eventId) {

		return getService().fetchEvent(eventId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the event with the primary key.
	 *
	 * @param eventId the primary key of the event
	 * @return the event
	 * @throws PortalException if a event with the primary key could not be found
	 */
	public static com.liferay.osb.customer.account.entry.details.model.Event
			getEvent(long eventId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEvent(eventId);
	}

	/**
	 * Returns a range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.account.entry.details.model.impl.EventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of events
	 */
	public static java.util.List
		<com.liferay.osb.customer.account.entry.details.model.Event> getEvents(
			int start, int end) {

		return getService().getEvents(start, end);
	}

	/**
	 * Returns the number of events.
	 *
	 * @return the number of events
	 */
	public static int getEventsCount() {
		return getService().getEventsCount();
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
	 * Updates the event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param event the event
	 * @return the event that was updated
	 */
	public static com.liferay.osb.customer.account.entry.details.model.Event
		updateEvent(
			com.liferay.osb.customer.account.entry.details.model.Event event) {

		return getService().updateEvent(event);
	}

	public static EventLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EventLocalService, EventLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(EventLocalService.class);

		ServiceTracker<EventLocalService, EventLocalService> serviceTracker =
			new ServiceTracker<EventLocalService, EventLocalService>(
				bundle.getBundleContext(), EventLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}