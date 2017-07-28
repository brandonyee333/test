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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for HolidayCalendarRel. This utility wraps
 * {@link com.liferay.osb.service.impl.HolidayCalendarRelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarRelLocalService
 * @see com.liferay.osb.service.base.HolidayCalendarRelLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.HolidayCalendarRelLocalServiceImpl
 * @generated
 */
@ProviderType
public class HolidayCalendarRelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.HolidayCalendarRelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasHolidayCalendarRel(long holidayCalendarId,
		long userId) {
		return getService().hasHolidayCalendarRel(holidayCalendarId, userId);
	}

	/**
	* Adds the holiday calendar rel to the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRel the holiday calendar rel
	* @return the holiday calendar rel that was added
	*/
	public static com.liferay.osb.model.HolidayCalendarRel addHolidayCalendarRel(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel) {
		return getService().addHolidayCalendarRel(holidayCalendarRel);
	}

	/**
	* Creates a new holiday calendar rel with the primary key. Does not add the holiday calendar rel to the database.
	*
	* @param holidayCalendarRelId the primary key for the new holiday calendar rel
	* @return the new holiday calendar rel
	*/
	public static com.liferay.osb.model.HolidayCalendarRel createHolidayCalendarRel(
		long holidayCalendarRelId) {
		return getService().createHolidayCalendarRel(holidayCalendarRelId);
	}

	/**
	* Deletes the holiday calendar rel from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRel the holiday calendar rel
	* @return the holiday calendar rel that was removed
	*/
	public static com.liferay.osb.model.HolidayCalendarRel deleteHolidayCalendarRel(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel) {
		return getService().deleteHolidayCalendarRel(holidayCalendarRel);
	}

	/**
	* Deletes the holiday calendar rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel that was removed
	* @throws PortalException if a holiday calendar rel with the primary key could not be found
	*/
	public static com.liferay.osb.model.HolidayCalendarRel deleteHolidayCalendarRel(
		long holidayCalendarRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteHolidayCalendarRel(holidayCalendarRelId);
	}

	public static com.liferay.osb.model.HolidayCalendarRel fetchHolidayCalendarRel(
		long holidayCalendarRelId) {
		return getService().fetchHolidayCalendarRel(holidayCalendarRelId);
	}

	public static com.liferay.osb.model.HolidayCalendarRel getHolidayCalendarRel(
		long holidayCalendarId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getHolidayCalendarRel(holidayCalendarId, userId);
	}

	/**
	* Returns the holiday calendar rel with the primary key.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel
	* @throws PortalException if a holiday calendar rel with the primary key could not be found
	*/
	public static com.liferay.osb.model.HolidayCalendarRel getHolidayCalendarRel(
		long holidayCalendarRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getHolidayCalendarRel(holidayCalendarRelId);
	}

	/**
	* Updates the holiday calendar rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRel the holiday calendar rel
	* @return the holiday calendar rel that was updated
	*/
	public static com.liferay.osb.model.HolidayCalendarRel updateHolidayCalendarRel(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel) {
		return getService().updateHolidayCalendarRel(holidayCalendarRel);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of holiday calendar rels.
	*
	* @return the number of holiday calendar rels
	*/
	public static int getHolidayCalendarRelsCount() {
		return getService().getHolidayCalendarRelsCount();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the holiday calendar rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @return the range of holiday calendar rels
	*/
	public static java.util.List<com.liferay.osb.model.HolidayCalendarRel> getHolidayCalendarRels(
		int start, int end) {
		return getService().getHolidayCalendarRels(start, end);
	}

	public static java.util.List<com.liferay.osb.model.HolidayCalendarRel> getHolidayCalendarRels(
		long holidayCalendarId) {
		return getService().getHolidayCalendarRels(holidayCalendarId);
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

	public static void addUsers(long holidayCalendarId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUsers(holidayCalendarId, userIds);
	}

	public static void deleteHolidayCalendarRels(long holidayCalendarId) {
		getService().deleteHolidayCalendarRels(holidayCalendarId);
	}

	public static void deleteHolidayCalendarRels(long holidayCalendarId,
		long[] userIds) {
		getService().deleteHolidayCalendarRels(holidayCalendarId, userIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static HolidayCalendarRelLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					HolidayCalendarRelLocalService.class.getName());

			if (invokableLocalService instanceof HolidayCalendarRelLocalService) {
				_service = (HolidayCalendarRelLocalService)invokableLocalService;
			}
			else {
				_service = new HolidayCalendarRelLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(HolidayCalendarRelLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static HolidayCalendarRelLocalService _service;
}