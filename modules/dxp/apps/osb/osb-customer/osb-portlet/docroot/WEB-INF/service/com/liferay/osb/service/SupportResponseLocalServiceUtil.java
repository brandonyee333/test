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
 * Provides the local service utility for SupportResponse. This utility wraps
 * {@link com.liferay.osb.service.impl.SupportResponseLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportResponseLocalService
 * @see com.liferay.osb.service.base.SupportResponseLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportResponseLocalServiceImpl
 * @generated
 */
@ProviderType
public class SupportResponseLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SupportResponseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the support response to the database. Also notifies the appropriate model listeners.
	*
	* @param supportResponse the support response
	* @return the support response that was added
	*/
	public static com.liferay.osb.model.SupportResponse addSupportResponse(
		com.liferay.osb.model.SupportResponse supportResponse) {
		return getService().addSupportResponse(supportResponse);
	}

	public static com.liferay.osb.model.SupportResponse addSupportResponse(
		long userId, java.lang.String name, int supportLevel,
		int severity1Response, int severity1Resolution, int severity2Response,
		int severity2Resolution, int severity3Response, int severity3Resolution)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addSupportResponse(userId, name, supportLevel,
			severity1Response, severity1Resolution, severity2Response,
			severity2Resolution, severity3Response, severity3Resolution);
	}

	/**
	* Creates a new support response with the primary key. Does not add the support response to the database.
	*
	* @param supportResponseId the primary key for the new support response
	* @return the new support response
	*/
	public static com.liferay.osb.model.SupportResponse createSupportResponse(
		long supportResponseId) {
		return getService().createSupportResponse(supportResponseId);
	}

	/**
	* Deletes the support response from the database. Also notifies the appropriate model listeners.
	*
	* @param supportResponse the support response
	* @return the support response that was removed
	*/
	public static com.liferay.osb.model.SupportResponse deleteSupportResponse(
		com.liferay.osb.model.SupportResponse supportResponse) {
		return getService().deleteSupportResponse(supportResponse);
	}

	/**
	* Deletes the support response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response that was removed
	* @throws PortalException if a support response with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportResponse deleteSupportResponse(
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSupportResponse(supportResponseId);
	}

	public static com.liferay.osb.model.SupportResponse fetchSupportResponse(
		long supportResponseId) {
		return getService().fetchSupportResponse(supportResponseId);
	}

	public static com.liferay.osb.model.SupportResponse fetchSupportResponseByName(
		java.lang.String name) {
		return getService().fetchSupportResponseByName(name);
	}

	/**
	* Returns the support response with the primary key.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response
	* @throws PortalException if a support response with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportResponse getSupportResponse(
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSupportResponse(supportResponseId);
	}

	/**
	* Updates the support response in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportResponse the support response
	* @return the support response that was updated
	*/
	public static com.liferay.osb.model.SupportResponse updateSupportResponse(
		com.liferay.osb.model.SupportResponse supportResponse) {
		return getService().updateSupportResponse(supportResponse);
	}

	public static com.liferay.osb.model.SupportResponse updateSupportResponse(
		long supportResponseId, java.lang.String name, int supportLevel,
		int severity1Response, int severity1Resolution, int severity2Response,
		int severity2Resolution, int severity3Response, int severity3Resolution)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSupportResponse(supportResponseId, name,
			supportLevel, severity1Response, severity1Resolution,
			severity2Response, severity2Resolution, severity3Response,
			severity3Resolution);
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
	* Returns the number of support responses.
	*
	* @return the number of support responses
	*/
	public static int getSupportResponsesCount() {
		return getService().getSupportResponsesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the support responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @return the range of support responses
	*/
	public static java.util.List<com.liferay.osb.model.SupportResponse> getSupportResponses(
		int start, int end) {
		return getService().getSupportResponses(start, end);
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

	public static void clearService() {
		_service = null;
	}

	public static SupportResponseLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SupportResponseLocalService.class.getName());

			if (invokableLocalService instanceof SupportResponseLocalService) {
				_service = (SupportResponseLocalService)invokableLocalService;
			}
			else {
				_service = new SupportResponseLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SupportResponseLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SupportResponseLocalService _service;
}