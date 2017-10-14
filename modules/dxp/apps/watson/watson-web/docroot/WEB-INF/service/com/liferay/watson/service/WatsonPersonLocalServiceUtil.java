/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.watson.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for WatsonPerson. This utility wraps
 * {@link com.liferay.watson.service.impl.WatsonPersonLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Eddie Olson
 * @see WatsonPersonLocalService
 * @see com.liferay.watson.service.base.WatsonPersonLocalServiceBaseImpl
 * @see com.liferay.watson.service.impl.WatsonPersonLocalServiceImpl
 * @generated
 */
@ProviderType
public class WatsonPersonLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.watson.service.impl.WatsonPersonLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
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

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.watson.model.impl.WatsonPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.watson.model.impl.WatsonPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.watson.model.impl.WatsonPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson persons
	* @param end the upper bound of the range of watson persons (not inclusive)
	* @return the range of watson persons
	*/
	public static java.util.List<com.liferay.watson.model.WatsonPerson> getWatsonPersons(
		int start, int end) {
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

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
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

	public static void clearService() {
		_service = null;
	}

	public static WatsonPersonLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WatsonPersonLocalService.class.getName());

			if (invokableLocalService instanceof WatsonPersonLocalService) {
				_service = (WatsonPersonLocalService)invokableLocalService;
			}
			else {
				_service = new WatsonPersonLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(WatsonPersonLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static WatsonPersonLocalService _service;
}