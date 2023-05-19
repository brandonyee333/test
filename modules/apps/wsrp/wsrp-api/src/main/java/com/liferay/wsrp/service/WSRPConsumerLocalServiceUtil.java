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
import com.liferay.wsrp.model.WSRPConsumer;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for WSRPConsumer. This utility wraps
 * <code>com.liferay.wsrp.service.impl.WSRPConsumerLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerLocalService
 * @generated
 */
public class WSRPConsumerLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.wsrp.service.impl.WSRPConsumerLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static WSRPConsumer addWSRPConsumer(
			long companyId, String adminPortletId, String name, String url,
			String forwardCookies, String forwardHeaders,
			String markupCharacterSets,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addWSRPConsumer(
			companyId, adminPortletId, name, url, forwardCookies,
			forwardHeaders, markupCharacterSets, serviceContext);
	}

	/**
	 * Adds the wsrp consumer to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WSRPConsumerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wsrpConsumer the wsrp consumer
	 * @return the wsrp consumer that was added
	 */
	public static WSRPConsumer addWSRPConsumer(WSRPConsumer wsrpConsumer) {
		return getService().addWSRPConsumer(wsrpConsumer);
	}

	/**
	 * Creates a new wsrp consumer with the primary key. Does not add the wsrp consumer to the database.
	 *
	 * @param wsrpConsumerId the primary key for the new wsrp consumer
	 * @return the new wsrp consumer
	 */
	public static WSRPConsumer createWSRPConsumer(long wsrpConsumerId) {
		return getService().createWSRPConsumer(wsrpConsumerId);
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
	 * Deletes the wsrp consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WSRPConsumerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wsrpConsumerId the primary key of the wsrp consumer
	 * @return the wsrp consumer that was removed
	 * @throws PortalException if a wsrp consumer with the primary key could not be found
	 */
	public static WSRPConsumer deleteWSRPConsumer(long wsrpConsumerId)
		throws PortalException {

		return getService().deleteWSRPConsumer(wsrpConsumerId);
	}

	/**
	 * Deletes the wsrp consumer from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WSRPConsumerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wsrpConsumer the wsrp consumer
	 * @return the wsrp consumer that was removed
	 * @throws PortalException
	 */
	public static WSRPConsumer deleteWSRPConsumer(WSRPConsumer wsrpConsumer)
		throws PortalException {

		return getService().deleteWSRPConsumer(wsrpConsumer);
	}

	public static void deleteWSRPConsumers(long companyId)
		throws PortalException {

		getService().deleteWSRPConsumers(companyId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wsrp.model.impl.WSRPConsumerModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wsrp.model.impl.WSRPConsumerModelImpl</code>.
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

	public static WSRPConsumer fetchWSRPConsumer(long wsrpConsumerId) {
		return getService().fetchWSRPConsumer(wsrpConsumerId);
	}

	/**
	 * Returns the wsrp consumer with the matching UUID and company.
	 *
	 * @param uuid the wsrp consumer's UUID
	 * @param companyId the primary key of the company
	 * @return the matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer fetchWSRPConsumerByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchWSRPConsumerByUuidAndCompanyId(
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
	 * Returns the wsrp consumer with the primary key.
	 *
	 * @param wsrpConsumerId the primary key of the wsrp consumer
	 * @return the wsrp consumer
	 * @throws PortalException if a wsrp consumer with the primary key could not be found
	 */
	public static WSRPConsumer getWSRPConsumer(long wsrpConsumerId)
		throws PortalException {

		return getService().getWSRPConsumer(wsrpConsumerId);
	}

	public static WSRPConsumer getWSRPConsumer(String wsrpConsumerUuid)
		throws PortalException {

		return getService().getWSRPConsumer(wsrpConsumerUuid);
	}

	/**
	 * Returns the wsrp consumer with the matching UUID and company.
	 *
	 * @param uuid the wsrp consumer's UUID
	 * @param companyId the primary key of the company
	 * @return the matching wsrp consumer
	 * @throws PortalException if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer getWSRPConsumerByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getWSRPConsumerByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the wsrp consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wsrp.model.impl.WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @return the range of wsrp consumers
	 */
	public static List<WSRPConsumer> getWSRPConsumers(int start, int end) {
		return getService().getWSRPConsumers(start, end);
	}

	public static List<WSRPConsumer> getWSRPConsumers(
		long companyId, int start, int end) {

		return getService().getWSRPConsumers(companyId, start, end);
	}

	/**
	 * Returns the number of wsrp consumers.
	 *
	 * @return the number of wsrp consumers
	 */
	public static int getWSRPConsumersCount() {
		return getService().getWSRPConsumersCount();
	}

	public static int getWSRPConsumersCount(long companyId) {
		return getService().getWSRPConsumersCount(companyId);
	}

	public static WSRPConsumer registerWSRPConsumer(
			long wsrpConsumerId, String adminPortletId,
			com.liferay.portal.kernel.util.UnicodeProperties
				registrationProperties,
			String registrationHandle)
		throws PortalException {

		return getService().registerWSRPConsumer(
			wsrpConsumerId, adminPortletId, registrationProperties,
			registrationHandle);
	}

	public static void restartConsumer(long wsrpConsumerId)
		throws PortalException {

		getService().restartConsumer(wsrpConsumerId);
	}

	public static void updateServiceDescription(long wsrpConsumerId)
		throws PortalException {

		getService().updateServiceDescription(wsrpConsumerId);
	}

	public static WSRPConsumer updateWSRPConsumer(
			long wsrpConsumerId, String adminPortletId, String name, String url,
			String forwardCookies, String forwardHeaders,
			String markupCharacterSets)
		throws PortalException {

		return getService().updateWSRPConsumer(
			wsrpConsumerId, adminPortletId, name, url, forwardCookies,
			forwardHeaders, markupCharacterSets);
	}

	/**
	 * Updates the wsrp consumer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WSRPConsumerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wsrpConsumer the wsrp consumer
	 * @return the wsrp consumer that was updated
	 */
	public static WSRPConsumer updateWSRPConsumer(WSRPConsumer wsrpConsumer) {
		return getService().updateWSRPConsumer(wsrpConsumer);
	}

	public static WSRPConsumerLocalService getService() {
		return _service;
	}

	public static void setService(WSRPConsumerLocalService service) {
		_service = service;
	}

	private static volatile WSRPConsumerLocalService _service;

}