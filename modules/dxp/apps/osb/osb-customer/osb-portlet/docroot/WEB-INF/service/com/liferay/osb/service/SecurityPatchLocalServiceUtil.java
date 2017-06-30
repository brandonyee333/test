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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the security patch local service. This utility wraps {@link com.liferay.osb.service.impl.SecurityPatchLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecurityPatchLocalService
 * @see com.liferay.osb.service.base.SecurityPatchLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SecurityPatchLocalServiceImpl
 * @generated
 */
public class SecurityPatchLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SecurityPatchLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the security patch to the database. Also notifies the appropriate model listeners.
	*
	* @param securityPatch the security patch
	* @return the security patch that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SecurityPatch addSecurityPatch(
		com.liferay.osb.model.SecurityPatch securityPatch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSecurityPatch(securityPatch);
	}

	/**
	* Creates a new security patch with the primary key. Does not add the security patch to the database.
	*
	* @param securityPatchId the primary key for the new security patch
	* @return the new security patch
	*/
	public static com.liferay.osb.model.SecurityPatch createSecurityPatch(
		long securityPatchId) {
		return getService().createSecurityPatch(securityPatchId);
	}

	/**
	* Deletes the security patch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch that was removed
	* @throws PortalException if a security patch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SecurityPatch deleteSecurityPatch(
		long securityPatchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSecurityPatch(securityPatchId);
	}

	/**
	* Deletes the security patch from the database. Also notifies the appropriate model listeners.
	*
	* @param securityPatch the security patch
	* @return the security patch that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SecurityPatch deleteSecurityPatch(
		com.liferay.osb.model.SecurityPatch securityPatch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSecurityPatch(securityPatch);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.SecurityPatch fetchSecurityPatch(
		long securityPatchId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSecurityPatch(securityPatchId);
	}

	/**
	* Returns the security patch with the primary key.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch
	* @throws PortalException if a security patch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SecurityPatch getSecurityPatch(
		long securityPatchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSecurityPatch(securityPatchId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the security patchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @return the range of security patchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SecurityPatch> getSecurityPatchs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSecurityPatchs(start, end);
	}

	/**
	* Returns the number of security patchs.
	*
	* @return the number of security patchs
	* @throws SystemException if a system exception occurred
	*/
	public static int getSecurityPatchsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSecurityPatchsCount();
	}

	/**
	* Updates the security patch in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param securityPatch the security patch
	* @return the security patch that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SecurityPatch updateSecurityPatch(
		com.liferay.osb.model.SecurityPatch securityPatch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSecurityPatch(securityPatch);
	}

	/**
	* Updates the security patch in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param securityPatch the security patch
	* @param merge whether to merge the security patch with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the security patch that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SecurityPatch updateSecurityPatch(
		com.liferay.osb.model.SecurityPatch securityPatch, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSecurityPatch(securityPatch, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.SecurityPatch addSecurityPatch(
		long userId, long accountEntryId, long ticketAttachmentId,
		java.lang.String portletId, int envLFR, java.lang.String name,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addSecurityPatch(userId, accountEntryId,
			ticketAttachmentId, portletId, envLFR, name, fileName);
	}

	public static com.liferay.osb.model.SecurityPatch addSecurityPatch(
		long userId, long ticketAttachmentId, java.lang.String portletId,
		int envLFR, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addSecurityPatch(userId, ticketAttachmentId, portletId,
			envLFR, fileName);
	}

	public static void deleteSecurityPatches(java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSecurityPatches(portletId);
	}

	public static java.util.List<com.liferay.osb.model.SecurityPatch> getSecurityPatches(
		long accountEntryId, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSecurityPatches(accountEntryId, portletId);
	}

	public static java.lang.String getSecurityPatchName(int envLFR,
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		return getService().getSecurityPatchName(envLFR, ticketAttachment);
	}

	public static com.liferay.osb.model.SecurityPatch updateSecurityPatch(
		long securityPatchId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSecurityPatch(securityPatchId, name);
	}

	public static void clearService() {
		_service = null;
	}

	public static SecurityPatchLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SecurityPatchLocalService.class.getName());

			if (invokableLocalService instanceof SecurityPatchLocalService) {
				_service = (SecurityPatchLocalService)invokableLocalService;
			}
			else {
				_service = new SecurityPatchLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SecurityPatchLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(SecurityPatchLocalService service) {
	}

	private static SecurityPatchLocalService _service;
}