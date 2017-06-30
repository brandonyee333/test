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
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the corp entry remote service. This utility wraps {@link com.liferay.osb.service.impl.CorpEntryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpEntryService
 * @see com.liferay.osb.service.base.CorpEntryServiceBaseImpl
 * @see com.liferay.osb.service.impl.CorpEntryServiceImpl
 * @generated
 */
public class CorpEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.CorpEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

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

	public static com.liferay.osb.model.CorpEntry addCorpEntry(
		java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId, java.lang.String contactEmailAddress,
		java.lang.String profileEmailAddress, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String website,
		java.lang.String dossieraAccountKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCorpEntry(name, descriptionMap, logoFile, street1,
			street2, street3, city, zip, regionId, countryId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, dossieraAccountKey, serviceContext);
	}

	public static void addCorpEntryUsers(long corpEntryId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addCorpEntryUsers(corpEntryId, userIds);
	}

	public static void addUserCorpEntryRoles(long corpEntryId, long[] userIds,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addUserCorpEntryRoles(corpEntryId, userIds, roleId);
	}

	public static void addUserCorpEntryRoles(long corpEntryId,
		java.lang.String[] userUuids, java.lang.String roleName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addUserCorpEntryRoles(corpEntryId, userUuids, roleName);
	}

	public static com.liferay.osb.model.CorpEntry deleteCorpEntry(
		long corpEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCorpEntry(corpEntryId);
	}

	public static void deleteUserCorpEntryRoles(long corpEntryId,
		long[] userIds, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserCorpEntryRoles(corpEntryId, userIds, roleId);
	}

	public static com.liferay.osb.model.CorpEntry getCorpEntry(long corpEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpEntry(corpEntryId);
	}

	public static boolean hasUserCorpEntry(long userId, long corpEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasUserCorpEntry(userId, corpEntryId);
	}

	public static com.liferay.osb.model.CorpEntry mergeCorpEntry(
		long corpEntryId, long mergeCorpEntryId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long logoId, java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String contactEmailAddress,
		java.lang.String profileEmailAddress, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String website,
		java.lang.String dossieraAccountKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .mergeCorpEntry(corpEntryId, mergeCorpEntryId, name,
			descriptionMap, logoId, street1, street2, street3, city, zip,
			regionId, countryId, contactEmailAddress, profileEmailAddress,
			phoneNumber, faxNumber, website, dossieraAccountKey, serviceContext);
	}

	public static void unsetCorpEntryUsers(long corpEntryId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsetCorpEntryUsers(corpEntryId, userIds);
	}

	public static com.liferay.osb.model.CorpEntry updateCorpEntry(
		long corpEntryId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId, java.lang.String contactEmailAddress,
		java.lang.String profileEmailAddress, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String website,
		java.lang.String dossieraAccountKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCorpEntry(corpEntryId, name, descriptionMap,
			logoFile, street1, street2, street3, city, zip, regionId,
			countryId, contactEmailAddress, profileEmailAddress, phoneNumber,
			faxNumber, website, dossieraAccountKey, serviceContext);
	}

	public static com.liferay.osb.model.CorpEntry updateStatus(
		long corpEntryId, int status, java.lang.String statusMessage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStatus(corpEntryId, status, statusMessage);
	}

	public static void clearService() {
		_service = null;
	}

	public static CorpEntryService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CorpEntryService.class.getName());

			if (invokableService instanceof CorpEntryService) {
				_service = (CorpEntryService)invokableService;
			}
			else {
				_service = new CorpEntryServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(CorpEntryServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(CorpEntryService service) {
	}

	private static CorpEntryService _service;
}