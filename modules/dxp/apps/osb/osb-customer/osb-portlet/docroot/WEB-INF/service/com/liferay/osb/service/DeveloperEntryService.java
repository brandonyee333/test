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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * The interface for the developer entry remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DeveloperEntryServiceUtil
 * @see com.liferay.osb.service.base.DeveloperEntryServiceBaseImpl
 * @see com.liferay.osb.service.impl.DeveloperEntryServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DeveloperEntryService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeveloperEntryServiceUtil} to access the developer entry remote service. Add custom service methods to {@link com.liferay.osb.service.impl.DeveloperEntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

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
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.DeveloperEntry addUserDeveloperEntry(
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, long contractEntryId, long countryId,
		java.lang.String phoneNumber,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String domainName, int domainStatus,
		java.lang.String paymentFirstName, java.lang.String paymentLastName,
		java.lang.String paymentEmailAddress,
		java.util.Date subscriptionExpirationDate, int subscriptionStatus,
		double fatcaWithholdingPercentage,
		java.lang.String taxDocumentFileName, java.io.File taxDocumentFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String paymentFirstName,
		java.lang.String paymentLastName, java.lang.String paymentEmailAddress)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntryGoogleAnalyticsKey(
		long developerEntryId, java.lang.String googleAnalyticsKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.DeveloperEntry updateStatus(
		long developerEntryId, int status, java.lang.String statusMessage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.DeveloperEntry updateSubscription(
		long developerEntryId, java.util.Date subscriptionExpirationDate,
		int subscriptionStatus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.DeveloperEntry updateUserDeveloperEntry(
		long developerEntryId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String legalEntityName, java.lang.String phoneNumber,
		java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String taxDocumentFileName,
		java.io.File taxDocumentFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}