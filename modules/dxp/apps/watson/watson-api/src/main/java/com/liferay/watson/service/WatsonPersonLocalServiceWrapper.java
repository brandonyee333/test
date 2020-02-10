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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WatsonPersonLocalService}.
 *
 * @author Steven Smith
 * @see WatsonPersonLocalService
 * @generated
 */
public class WatsonPersonLocalServiceWrapper
	implements ServiceWrapper<WatsonPersonLocalService>,
			   WatsonPersonLocalService {

	public WatsonPersonLocalServiceWrapper(
		WatsonPersonLocalService watsonPersonLocalService) {

		_watsonPersonLocalService = watsonPersonLocalService;
	}

	/**
	 * Adds the watson person to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPerson the watson person
	 * @return the watson person that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonPerson addWatsonPerson(
		com.liferay.watson.model.WatsonPerson watsonPerson) {

		return _watsonPersonLocalService.addWatsonPerson(watsonPerson);
	}

	/**
	 * Creates a new watson person with the primary key. Does not add the watson person to the database.
	 *
	 * @param watsonPersonId the primary key for the new watson person
	 * @return the new watson person
	 */
	@Override
	public com.liferay.watson.model.WatsonPerson createWatsonPerson(
		long watsonPersonId) {

		return _watsonPersonLocalService.createWatsonPerson(watsonPersonId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonPersonLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson person with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPersonId the primary key of the watson person
	 * @return the watson person that was removed
	 * @throws PortalException if a watson person with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonPerson deleteWatsonPerson(
			long watsonPersonId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonPersonLocalService.deleteWatsonPerson(watsonPersonId);
	}

	/**
	 * Deletes the watson person from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPerson the watson person
	 * @return the watson person that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonPerson deleteWatsonPerson(
		com.liferay.watson.model.WatsonPerson watsonPerson) {

		return _watsonPersonLocalService.deleteWatsonPerson(watsonPerson);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonPersonLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _watsonPersonLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _watsonPersonLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _watsonPersonLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _watsonPersonLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _watsonPersonLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonPerson fetchWatsonPerson(
		long watsonPersonId) {

		return _watsonPersonLocalService.fetchWatsonPerson(watsonPersonId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonPersonLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonPersonLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonPersonLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonPersonLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson person with the primary key.
	 *
	 * @param watsonPersonId the primary key of the watson person
	 * @return the watson person
	 * @throws PortalException if a watson person with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonPerson getWatsonPerson(
			long watsonPersonId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonPersonLocalService.getWatsonPerson(watsonPersonId);
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
	@Override
	public java.util.List<com.liferay.watson.model.WatsonPerson>
		getWatsonPersons(int start, int end) {

		return _watsonPersonLocalService.getWatsonPersons(start, end);
	}

	/**
	 * Returns the number of watson persons.
	 *
	 * @return the number of watson persons
	 */
	@Override
	public int getWatsonPersonsCount() {
		return _watsonPersonLocalService.getWatsonPersonsCount();
	}

	/**
	 * Updates the watson person in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPerson the watson person
	 * @return the watson person that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonPerson updateWatsonPerson(
		com.liferay.watson.model.WatsonPerson watsonPerson) {

		return _watsonPersonLocalService.updateWatsonPerson(watsonPerson);
	}

	@Override
	public WatsonPersonLocalService getWrappedService() {
		return _watsonPersonLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonPersonLocalService watsonPersonLocalService) {

		_watsonPersonLocalService = watsonPersonLocalService;
	}

	private WatsonPersonLocalService _watsonPersonLocalService;

}