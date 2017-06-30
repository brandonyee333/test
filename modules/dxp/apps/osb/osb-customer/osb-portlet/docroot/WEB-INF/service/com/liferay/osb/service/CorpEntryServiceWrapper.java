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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CorpEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpEntryService
 * @generated
 */
public class CorpEntryServiceWrapper implements CorpEntryService,
	ServiceWrapper<CorpEntryService> {
	public CorpEntryServiceWrapper(CorpEntryService corpEntryService) {
		_corpEntryService = corpEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _corpEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_corpEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _corpEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.CorpEntry addCorpEntry(java.lang.String name,
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
		return _corpEntryService.addCorpEntry(name, descriptionMap, logoFile,
			street1, street2, street3, city, zip, regionId, countryId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, dossieraAccountKey, serviceContext);
	}

	public void addCorpEntryUsers(long corpEntryId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpEntryService.addCorpEntryUsers(corpEntryId, userIds);
	}

	public void addUserCorpEntryRoles(long corpEntryId, long[] userIds,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpEntryService.addUserCorpEntryRoles(corpEntryId, userIds, roleId);
	}

	public void addUserCorpEntryRoles(long corpEntryId,
		java.lang.String[] userUuids, java.lang.String roleName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpEntryService.addUserCorpEntryRoles(corpEntryId, userUuids, roleName);
	}

	public com.liferay.osb.model.CorpEntry deleteCorpEntry(long corpEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryService.deleteCorpEntry(corpEntryId);
	}

	public void deleteUserCorpEntryRoles(long corpEntryId, long[] userIds,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpEntryService.deleteUserCorpEntryRoles(corpEntryId, userIds, roleId);
	}

	public com.liferay.osb.model.CorpEntry getCorpEntry(long corpEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryService.getCorpEntry(corpEntryId);
	}

	public boolean hasUserCorpEntry(long userId, long corpEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryService.hasUserCorpEntry(userId, corpEntryId);
	}

	public com.liferay.osb.model.CorpEntry mergeCorpEntry(long corpEntryId,
		long mergeCorpEntryId, java.lang.String name,
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
		return _corpEntryService.mergeCorpEntry(corpEntryId, mergeCorpEntryId,
			name, descriptionMap, logoId, street1, street2, street3, city, zip,
			regionId, countryId, contactEmailAddress, profileEmailAddress,
			phoneNumber, faxNumber, website, dossieraAccountKey, serviceContext);
	}

	public void unsetCorpEntryUsers(long corpEntryId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpEntryService.unsetCorpEntryUsers(corpEntryId, userIds);
	}

	public com.liferay.osb.model.CorpEntry updateCorpEntry(long corpEntryId,
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
		return _corpEntryService.updateCorpEntry(corpEntryId, name,
			descriptionMap, logoFile, street1, street2, street3, city, zip,
			regionId, countryId, contactEmailAddress, profileEmailAddress,
			phoneNumber, faxNumber, website, dossieraAccountKey, serviceContext);
	}

	public com.liferay.osb.model.CorpEntry updateStatus(long corpEntryId,
		int status, java.lang.String statusMessage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryService.updateStatus(corpEntryId, status, statusMessage);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CorpEntryService getWrappedCorpEntryService() {
		return _corpEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCorpEntryService(CorpEntryService corpEntryService) {
		_corpEntryService = corpEntryService;
	}

	public CorpEntryService getWrappedService() {
		return _corpEntryService;
	}

	public void setWrappedService(CorpEntryService corpEntryService) {
		_corpEntryService = corpEntryService;
	}

	private CorpEntryService _corpEntryService;
}