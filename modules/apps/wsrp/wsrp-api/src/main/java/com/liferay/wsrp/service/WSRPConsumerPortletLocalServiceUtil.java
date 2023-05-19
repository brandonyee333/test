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

package com.liferay.wsrp.service;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.wsrp.model.WSRPConsumerPortlet;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for WSRPConsumerPortlet. This utility wraps
 * <code>com.liferay.wsrp.service.impl.WSRPConsumerPortletLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortletLocalService
 * @generated
 */
public class WSRPConsumerPortletLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.wsrp.service.impl.WSRPConsumerPortletLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static WSRPConsumerPortlet addWSRPConsumerPortlet(
			long wsrpConsumerId, String name, String portletHandle,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addWSRPConsumerPortlet(
			wsrpConsumerId, name, portletHandle, serviceContext);
	}

	public static WSRPConsumerPortlet addWSRPConsumerPortlet(
			String wsrpConsumerUuid, String name, String portletHandle,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addWSRPConsumerPortlet(
			wsrpConsumerUuid, name, portletHandle, serviceContext);
	}

	/**
	 * Adds the wsrp consumer portlet to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WSRPConsumerPortletLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wsrpConsumerPortlet the wsrp consumer portlet
	 * @return the wsrp consumer portlet that was added
	 */
	public static WSRPConsumerPortlet addWSRPConsumerPortlet(
		WSRPConsumerPortlet wsrpConsumerPortlet) {

		return getService().addWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	/**
	 * Creates a new wsrp consumer portlet with the primary key. Does not add the wsrp consumer portlet to the database.
	 *
	 * @param wsrpConsumerPortletId the primary key for the new wsrp consumer portlet
	 * @return the new wsrp consumer portlet
	 */
	public static WSRPConsumerPortlet createWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {

		return getService().createWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the wsrp consumer portlet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WSRPConsumerPortletLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wsrpConsumerPortletId the primary key of the wsrp consumer portlet
	 * @return the wsrp consumer portlet that was removed
	 * @throws PortalException if a wsrp consumer portlet with the primary key could not be found
	 */
	public static WSRPConsumerPortlet deleteWSRPConsumerPortlet(
			long wsrpConsumerPortletId)
		throws PortalException {

		return getService().deleteWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public static void deleteWSRPConsumerPortlet(String wsrpConsumerPortletUuid)
		throws PortalException {

		getService().deleteWSRPConsumerPortlet(wsrpConsumerPortletUuid);
	}

	/**
	 * Deletes the wsrp consumer portlet from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WSRPConsumerPortletLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wsrpConsumerPortlet the wsrp consumer portlet
	 * @return the wsrp consumer portlet that was removed
	 * @throws PortalException
	 */
	public static WSRPConsumerPortlet deleteWSRPConsumerPortlet(
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException {

		return getService().deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public static void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws PortalException {

		getService().deleteWSRPConsumerPortlets(wsrpConsumerId);
	}

	public static void destroyWSRPConsumerPortlet(
		long wsrpConsumerPortletId, String wsrpConsumerPortletUuid,
		String url) {

		getService().destroyWSRPConsumerPortlet(
			wsrpConsumerPortletId, wsrpConsumerPortletUuid, url);
	}

	public static void destroyWSRPConsumerPortlets() throws PortalException {
		getService().destroyWSRPConsumerPortlets();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl</code>.
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

	public static WSRPConsumerPortlet fetchWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {

		return getService().fetchWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	/**
	 * Returns the wsrp consumer portlet with the matching UUID and company.
	 *
	 * @param uuid the wsrp consumer portlet's UUID
	 * @param companyId the primary key of the company
	 * @return the matching wsrp consumer portlet, or <code>null</code> if a matching wsrp consumer portlet could not be found
	 */
	public static WSRPConsumerPortlet
		fetchWSRPConsumerPortletByUuidAndCompanyId(
			String uuid, long companyId) {

		return getService().fetchWSRPConsumerPortletByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
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
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the wsrp consumer portlet with the primary key.
	 *
	 * @param wsrpConsumerPortletId the primary key of the wsrp consumer portlet
	 * @return the wsrp consumer portlet
	 * @throws PortalException if a wsrp consumer portlet with the primary key could not be found
	 */
	public static WSRPConsumerPortlet getWSRPConsumerPortlet(
			long wsrpConsumerPortletId)
		throws PortalException {

		return getService().getWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public static WSRPConsumerPortlet getWSRPConsumerPortlet(
			long wsrpConsumerId, String portletHandle)
		throws PortalException {

		return getService().getWSRPConsumerPortlet(
			wsrpConsumerId, portletHandle);
	}

	public static WSRPConsumerPortlet getWSRPConsumerPortlet(
			String wsrpConsumerPortletUuid)
		throws PortalException {

		return getService().getWSRPConsumerPortlet(wsrpConsumerPortletUuid);
	}

	/**
	 * Returns the wsrp consumer portlet with the matching UUID and company.
	 *
	 * @param uuid the wsrp consumer portlet's UUID
	 * @param companyId the primary key of the company
	 * @return the matching wsrp consumer portlet
	 * @throws PortalException if a matching wsrp consumer portlet could not be found
	 */
	public static WSRPConsumerPortlet getWSRPConsumerPortletByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getWSRPConsumerPortletByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of all the wsrp consumer portlets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp consumer portlets
	 * @param end the upper bound of the range of wsrp consumer portlets (not inclusive)
	 * @return the range of wsrp consumer portlets
	 */
	public static List<WSRPConsumerPortlet> getWSRPConsumerPortlets(
		int start, int end) {

		return getService().getWSRPConsumerPortlets(start, end);
	}

	public static List<WSRPConsumerPortlet> getWSRPConsumerPortlets(
		long wsrpConsumerId, int start, int end) {

		return getService().getWSRPConsumerPortlets(wsrpConsumerId, start, end);
	}

	/**
	 * Returns the number of wsrp consumer portlets.
	 *
	 * @return the number of wsrp consumer portlets
	 */
	public static int getWSRPConsumerPortletsCount() {
		return getService().getWSRPConsumerPortletsCount();
	}

	public static int getWSRPConsumerPortletsCount(long wsrpConsumerId) {
		return getService().getWSRPConsumerPortletsCount(wsrpConsumerId);
	}

	public static void initFailedWSRPConsumerPortlets() {
		getService().initFailedWSRPConsumerPortlets();
	}

	public static void initWSRPConsumerPortlet(
			long companyId, long wsrpConsumerId, long wsrpConsumerPortletId,
			String wsrpConsumerPortletUuid, String name, String portletHandle)
		throws PortalException {

		getService().initWSRPConsumerPortlet(
			companyId, wsrpConsumerId, wsrpConsumerPortletId,
			wsrpConsumerPortletUuid, name, portletHandle);
	}

	public static void initWSRPConsumerPortlets() {
		getService().initWSRPConsumerPortlets();
	}

	public static WSRPConsumerPortlet updateWSRPConsumerPortlet(
			long wsrpConsumerPortletId, String name)
		throws PortalException {

		return getService().updateWSRPConsumerPortlet(
			wsrpConsumerPortletId, name);
	}

	/**
	 * Updates the wsrp consumer portlet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WSRPConsumerPortletLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wsrpConsumerPortlet the wsrp consumer portlet
	 * @return the wsrp consumer portlet that was updated
	 */
	public static WSRPConsumerPortlet updateWSRPConsumerPortlet(
		WSRPConsumerPortlet wsrpConsumerPortlet) {

		return getService().updateWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public static WSRPConsumerPortletLocalService getService() {
		return _service;
	}

	public static void setService(WSRPConsumerPortletLocalService service) {
		_service = service;
	}

	private static volatile WSRPConsumerPortletLocalService _service;

}