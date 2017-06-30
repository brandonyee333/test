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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.base.DeveloperEntryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBDeveloperEntryPermission;
import com.liferay.osb.service.permission.OSBMarketplacePermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.service.ServiceContext;

import java.io.File;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 * @author Haote Chou
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class DeveloperEntryServiceImpl extends DeveloperEntryServiceBaseImpl {

	public DeveloperEntry addCompanyDeveloperEntry(
			String emailAddress, String legalEntityName, String phoneNumber,
			String faxNumber, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId,
			Map<Locale, String> profileDescriptionMap,
			String profileEmailAddress, File profileLogo, String profileWebsite,
			String dossieraAccountKey, String taxDocumentFileName,
			File taxDocumentFile, long tosContractEntryId,
			long developerContractEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		OSBMarketplacePermission.check(
			getPermissionChecker(), OSBActionKeys.ADD_DEVELOPER_ENTRY);

		return developerEntryLocalService.addCompanyDeveloperEntry(
			getUserId(), emailAddress, legalEntityName, phoneNumber, faxNumber,
			street1, street2, street3, city, zip, regionId, countryId,
			profileDescriptionMap, profileEmailAddress, profileLogo,
			profileWebsite, dossieraAccountKey, taxDocumentFileName,
			taxDocumentFile, tosContractEntryId, developerContractEntryId,
			serviceContext);
	}

	public DeveloperEntry addUserDeveloperEntry(
			String screenName, String emailAddress, String firstName,
			String middleName, String lastName, long contractEntryId,
			long countryId, String phoneNumber, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return developerEntryLocalService.addUserDeveloperEntry(
			getUserId(), screenName, emailAddress, firstName, middleName,
			lastName, contractEntryId, countryId, phoneNumber, serviceContext);
	}

	public DeveloperEntry updateCompanyDeveloperEntry(
			long developerEntryId, String emailAddress, String legalEntityName,
			String phoneNumber, String faxNumber, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId, File profileLogo,
			Map<Locale, String> profileDescriptionMap,
			String profileEmailAddress, String profileWebsite,
			String taxDocumentFileName, File taxDocumentFile,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		OSBDeveloperEntryPermission.check(
			getPermissionChecker(), developerEntryId, OSBActionKeys.UPDATE);

		return developerEntryLocalService.updateCompanyDeveloperEntry(
			getUserId(), developerEntryId, emailAddress, legalEntityName,
			phoneNumber, faxNumber, street1, street2, street3, city, zip,
			regionId, countryId, profileLogo, profileDescriptionMap,
			profileEmailAddress, profileWebsite, taxDocumentFileName,
			taxDocumentFile, serviceContext);
	}

	public DeveloperEntry updateDeveloperEntry(
			long developerEntryId, String dossieraAccountKey)
		throws PortalException, SystemException {

		OSBDeveloperEntryPermission.check(
			getPermissionChecker(), developerEntryId, OSBActionKeys.UPDATE);

		return developerEntryLocalService.updateDeveloperEntry(
			developerEntryId, dossieraAccountKey);
	}

	public DeveloperEntry updateDeveloperEntry(
			long developerEntryId, String domainName, int domainStatus,
			String paymentFirstName, String paymentLastName,
			String paymentEmailAddress, Date subscriptionExpirationDate,
			int subscriptionStatus, double fatcaWithholdingPercentage,
			String taxDocumentFileName, File taxDocumentFile)
		throws PortalException, SystemException {

		OSBDeveloperEntryPermission.check(
			getPermissionChecker(), developerEntryId, OSBActionKeys.UPDATE);

		return developerEntryLocalService.updateDeveloperEntry(
			getUserId(), developerEntryId, domainName, domainStatus,
			paymentFirstName, paymentLastName, paymentEmailAddress,
			subscriptionExpirationDate, subscriptionStatus,
			fatcaWithholdingPercentage, taxDocumentFileName, taxDocumentFile);
	}

	public DeveloperEntry updateDeveloperEntry(
			long developerEntryId, String paymentFirstName,
			String paymentLastName, String paymentEmailAddress)
		throws PortalException, SystemException {

		OSBDeveloperEntryPermission.check(
			getPermissionChecker(), developerEntryId, OSBActionKeys.UPDATE);

		return developerEntryLocalService.updateDeveloperEntry(
			developerEntryId, paymentFirstName, paymentLastName,
			paymentEmailAddress);
	}

	public DeveloperEntry updateDeveloperEntryGoogleAnalyticsKey(
			long developerEntryId, String googleAnalyticsKey)
		throws PortalException, SystemException {

		OSBDeveloperEntryPermission.check(
			getPermissionChecker(), developerEntryId, OSBActionKeys.UPDATE);

		return
			developerEntryLocalService.updateDeveloperEntryGoogleAnalyticsKey(
				developerEntryId, googleAnalyticsKey);
	}

	public DeveloperEntry updateStatus(
			long developerEntryId, int status, String statusMessage)
		throws PortalException, SystemException {

		OSBDeveloperEntryPermission.check(
			getPermissionChecker(), developerEntryId, OSBActionKeys.UPDATE);

		return developerEntryLocalService.updateStatus(
			getUserId(), developerEntryId, status, statusMessage);
	}

	public DeveloperEntry updateSubscription(
			long developerEntryId, Date subscriptionExpirationDate,
			int subscriptionStatus)
		throws PortalException, SystemException {

		OSBDeveloperEntryPermission.check(
			getPermissionChecker(), developerEntryId, OSBActionKeys.UPDATE);

		return developerEntryLocalService.updateSubscription(
			developerEntryId, subscriptionExpirationDate, subscriptionStatus);
	}

	public DeveloperEntry updateUserDeveloperEntry(
			long developerEntryId, String firstName, String middleName,
			String lastName, String legalEntityName, String phoneNumber,
			String street1, String street2, String street3, String city,
			String zip, long regionId, long countryId,
			String taxDocumentFileName, File taxDocumentFile)
		throws PortalException, SystemException {

		OSBDeveloperEntryPermission.check(
			getPermissionChecker(), developerEntryId, OSBActionKeys.UPDATE);

		return developerEntryLocalService.updateUserDeveloperEntry(
			getUserId(), developerEntryId, firstName, middleName, lastName,
			legalEntityName, phoneNumber, street1, street2, street3, city, zip,
			regionId, countryId, taxDocumentFileName, taxDocumentFile);
	}

}