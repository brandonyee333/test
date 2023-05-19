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

import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for LoopPerson. This utility wraps
 * <code>com.liferay.osb.loop.service.impl.LoopPersonLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see LoopPersonLocalService
 * @generated
 */
public class LoopPersonLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.service.impl.LoopPersonLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static LoopPerson addLoopPerson(long userId, long personUserId)
		throws PortalException {

		return getService().addLoopPerson(userId, personUserId);
	}

	/**
	 * Adds the loop person to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoopPersonLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loopPerson the loop person
	 * @return the loop person that was added
	 */
	public static LoopPerson addLoopPerson(LoopPerson loopPerson) {
		return getService().addLoopPerson(loopPerson);
	}

	/**
	 * Creates a new loop person with the primary key. Does not add the loop person to the database.
	 *
	 * @param loopPersonId the primary key for the new loop person
	 * @return the new loop person
	 */
	public static LoopPerson createLoopPerson(long loopPersonId) {
		return getService().createLoopPerson(loopPersonId);
	}

	/**
	 * Deletes the loop person with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoopPersonLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loopPersonId the primary key of the loop person
	 * @return the loop person that was removed
	 * @throws PortalException if a loop person with the primary key could not be found
	 */
	public static LoopPerson deleteLoopPerson(long loopPersonId)
		throws PortalException {

		return getService().deleteLoopPerson(loopPersonId);
	}

	/**
	 * Deletes the loop person from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoopPersonLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loopPerson the loop person
	 * @return the loop person that was removed
	 */
	public static LoopPerson deleteLoopPerson(LoopPerson loopPerson) {
		return getService().deleteLoopPerson(loopPerson);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopPersonModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopPersonModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static LoopPerson fetchLoopPerson(long loopPersonId) {
		return getService().fetchLoopPerson(loopPersonId);
	}

	public static LoopPerson fetchLoopPersonByPersonUserId(long personUserId) {
		return getService().fetchLoopPersonByPersonUserId(personUserId);
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
	 * Returns the loop person with the primary key.
	 *
	 * @param loopPersonId the primary key of the loop person
	 * @return the loop person
	 * @throws PortalException if a loop person with the primary key could not be found
	 */
	public static LoopPerson getLoopPerson(long loopPersonId)
		throws PortalException {

		return getService().getLoopPerson(loopPersonId);
	}

	public static LoopPerson getLoopPersonByPersonUserId(long personUserId)
		throws PortalException {

		return getService().getLoopPersonByPersonUserId(personUserId);
	}

	/**
	 * Returns a range of all the loop persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopPersonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop persons
	 * @param end the upper bound of the range of loop persons (not inclusive)
	 * @return the range of loop persons
	 */
	public static List<LoopPerson> getLoopPersons(int start, int end) {
		return getService().getLoopPersons(start, end);
	}

	/**
	 * Returns the number of loop persons.
	 *
	 * @return the number of loop persons
	 */
	public static int getLoopPersonsCount() {
		return getService().getLoopPersonsCount();
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
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean hasLoopPerson(long personUserId) {
		return getService().hasLoopPerson(personUserId);
	}

	/**
	 * Updates the loop person in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LoopPersonLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param loopPerson the loop person
	 * @return the loop person that was updated
	 */
	public static LoopPerson updateLoopPerson(LoopPerson loopPerson) {
		return getService().updateLoopPerson(loopPerson);
	}

	public static LoopPersonLocalService getService() {
		return _service;
	}

	public static void setService(LoopPersonLocalService service) {
		_service = service;
	}

	private static volatile LoopPersonLocalService _service;

}