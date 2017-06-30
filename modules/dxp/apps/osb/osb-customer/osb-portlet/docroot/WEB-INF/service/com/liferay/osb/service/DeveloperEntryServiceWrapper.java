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
 * This class is a wrapper for {@link DeveloperEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DeveloperEntryService
 * @generated
 */
public class DeveloperEntryServiceWrapper implements DeveloperEntryService,
	ServiceWrapper<DeveloperEntryService> {
	public DeveloperEntryServiceWrapper(
		DeveloperEntryService developerEntryService) {
		_developerEntryService = developerEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _developerEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_developerEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _developerEntryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.DeveloperEntry addCompanyDeveloperEntry(
		java.lang.String emailAddress, java.lang.String legalEntityName,
		java.lang.String phoneNumber, java.lang.String faxNumber,
		java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId,
		java.util.Map<java.util.Locale, java.lang.String> profileDescriptionMap,
		java.lang.String profileEmailAddress, java.io.File profileLogo,
		java.lang.String profileWebsite, java.lang.String dossieraAccountKey,
		java.lang.String taxDocumentFileName, java.io.File taxDocumentFile,
		long tosContractEntryId, long developerContractEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryService.addCompanyDeveloperEntry(emailAddress,
			legalEntityName, phoneNumber, faxNumber, street1, street2, street3,
			city, zip, regionId, countryId, profileDescriptionMap,
			profileEmailAddress, profileLogo, profileWebsite,
			dossieraAccountKey, taxDocumentFileName, taxDocumentFile,
			tosContractEntryId, developerContractEntryId, serviceContext);
	}

	public com.liferay.osb.model.DeveloperEntry addUserDeveloperEntry(
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, long contractEntryId, long countryId,
		java.lang.String phoneNumber,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryService.addUserDeveloperEntry(screenName,
			emailAddress, firstName, middleName, lastName, contractEntryId,
			countryId, phoneNumber, serviceContext);
	}

	public com.liferay.osb.model.DeveloperEntry updateCompanyDeveloperEntry(
		long developerEntryId, java.lang.String emailAddress,
		java.lang.String legalEntityName, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId, java.io.File profileLogo,
		java.util.Map<java.util.Locale, java.lang.String> profileDescriptionMap,
		java.lang.String profileEmailAddress, java.lang.String profileWebsite,
		java.lang.String taxDocumentFileName, java.io.File taxDocumentFile,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryService.updateCompanyDeveloperEntry(developerEntryId,
			emailAddress, legalEntityName, phoneNumber, faxNumber, street1,
			street2, street3, city, zip, regionId, countryId, profileLogo,
			profileDescriptionMap, profileEmailAddress, profileWebsite,
			taxDocumentFileName, taxDocumentFile, serviceContext);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryService.updateDeveloperEntry(developerEntryId,
			dossieraAccountKey);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String domainName, int domainStatus,
		java.lang.String paymentFirstName, java.lang.String paymentLastName,
		java.lang.String paymentEmailAddress,
		java.util.Date subscriptionExpirationDate, int subscriptionStatus,
		double fatcaWithholdingPercentage,
		java.lang.String taxDocumentFileName, java.io.File taxDocumentFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryService.updateDeveloperEntry(developerEntryId,
			domainName, domainStatus, paymentFirstName, paymentLastName,
			paymentEmailAddress, subscriptionExpirationDate,
			subscriptionStatus, fatcaWithholdingPercentage,
			taxDocumentFileName, taxDocumentFile);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String paymentFirstName,
		java.lang.String paymentLastName, java.lang.String paymentEmailAddress)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryService.updateDeveloperEntry(developerEntryId,
			paymentFirstName, paymentLastName, paymentEmailAddress);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntryGoogleAnalyticsKey(
		long developerEntryId, java.lang.String googleAnalyticsKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryService.updateDeveloperEntryGoogleAnalyticsKey(developerEntryId,
			googleAnalyticsKey);
	}

	public com.liferay.osb.model.DeveloperEntry updateStatus(
		long developerEntryId, int status, java.lang.String statusMessage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryService.updateStatus(developerEntryId, status,
			statusMessage);
	}

	public com.liferay.osb.model.DeveloperEntry updateSubscription(
		long developerEntryId, java.util.Date subscriptionExpirationDate,
		int subscriptionStatus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryService.updateSubscription(developerEntryId,
			subscriptionExpirationDate, subscriptionStatus);
	}

	public com.liferay.osb.model.DeveloperEntry updateUserDeveloperEntry(
		long developerEntryId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String legalEntityName, java.lang.String phoneNumber,
		java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String taxDocumentFileName,
		java.io.File taxDocumentFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryService.updateUserDeveloperEntry(developerEntryId,
			firstName, middleName, lastName, legalEntityName, phoneNumber,
			street1, street2, street3, city, zip, regionId, countryId,
			taxDocumentFileName, taxDocumentFile);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DeveloperEntryService getWrappedDeveloperEntryService() {
		return _developerEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDeveloperEntryService(
		DeveloperEntryService developerEntryService) {
		_developerEntryService = developerEntryService;
	}

	public DeveloperEntryService getWrappedService() {
		return _developerEntryService;
	}

	public void setWrappedService(DeveloperEntryService developerEntryService) {
		_developerEntryService = developerEntryService;
	}

	private DeveloperEntryService _developerEntryService;
}