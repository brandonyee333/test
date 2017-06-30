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

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.ecommerce.access.messaging.ECAccessUtil;
import com.liferay.osb.DeveloperEntryDomainNameException;
import com.liferay.osb.DeveloperEntryDomainStatusException;
import com.liferay.osb.DeveloperEntryDossieraAccountKeyException;
import com.liferay.osb.DeveloperEntryEmailAddressException;
import com.liferay.osb.DeveloperEntryFatcaWithholdingPercentageException;
import com.liferay.osb.DeveloperEntryFaxNumberException;
import com.liferay.osb.DeveloperEntryNameException;
import com.liferay.osb.DeveloperEntryPaymentAccountException;
import com.liferay.osb.DeveloperEntryPaymentEmailAddressException;
import com.liferay.osb.DeveloperEntryPhoneNumberException;
import com.liferay.osb.DeveloperEntryProfileDescriptionException;
import com.liferay.osb.DeveloperEntryProfileEmailAddressException;
import com.liferay.osb.DeveloperEntryProfileLogoException;
import com.liferay.osb.DeveloperEntryProfileWebsiteException;
import com.liferay.osb.DeveloperEntryStatusException;
import com.liferay.osb.DeveloperEntryTaxDocumentException;
import com.liferay.osb.DeveloperEntryTaxDocumentFileNameException;
import com.liferay.osb.DuplicateDeveloperEntryDossieraAccountKeyException;
import com.liferay.osb.DuplicateDeveloperEntryException;
import com.liferay.osb.NoSuchDeveloperEntryException;
import com.liferay.osb.RestrictedCountryException;
import com.liferay.osb.RestrictedLiferayEmailAddressException;
import com.liferay.osb.marketplaceadmin.util.MarketplaceAdminDiscussionUtil;
import com.liferay.osb.marketplaceregistration.util.MarketplaceRegistrationEmailUtil;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.model.DeveloperEntryConstants;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.service.base.DeveloperEntryLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.OSBSubscriptionSender;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.AddressCityException;
import com.liferay.portal.AddressStreetException;
import com.liferay.portal.AddressZipException;
import com.liferay.portal.NoSuchRegionException;
import com.liferay.portal.PhoneNumberException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.NotificationThreadLocal;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.messageboards.model.MBMessageDisplay;
import com.liferay.portlet.messageboards.model.MBThread;

import java.io.File;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 * @author Haote Chou
 */
public class DeveloperEntryLocalServiceImpl
	extends DeveloperEntryLocalServiceBaseImpl {

	public DeveloperEntry addCompanyDeveloperEntry(
			long userId, String emailAddress, String legalEntityName,
			String phoneNumber, String faxNumber, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId,
			Map<Locale, String> profileDescriptionMap,
			String profileEmailAddress, File profileLogo, String profileWebsite,
			String dossieraAccountKey, String taxDocumentFileName,
			File taxDocumentFile, long tosContractEntryId,
			long developerContractEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Developer entry

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		String defaultLanguageId = ParamUtil.getString(
			serviceContext, "profileDescriptionDefaultLanguageId",
			LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

		validateCompany(
			0, emailAddress, legalEntityName, phoneNumber, faxNumber, regionId,
			countryId, profileDescriptionMap, defaultLanguageId,
			profileEmailAddress, profileWebsite, profileLogo,
			taxDocumentFileName, taxDocumentFile);

		long developerEntryId = counterLocalService.increment();

		DeveloperEntry developerEntry = developerEntryPersistence.create(
			developerEntryId);

		developerEntry.setUserId(userId);
		developerEntry.setUserName(user.getFullName());
		developerEntry.setCreateDate(now);
		developerEntry.setEmailAddress(emailAddress);
		developerEntry.setLegalEntityName(legalEntityName);
		developerEntry.setContractEntryId(developerContractEntryId);
		developerEntry.setPhoneNumber(phoneNumber);
		developerEntry.setFaxNumber(faxNumber);

		Address address = updateAddress(
			0, userId, developerEntryId, street1, street2, street3, city, zip,
			regionId, countryId);

		developerEntry.setAddressId(address.getAddressId());

		developerEntry.setCountryId(countryId);

		Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

		developerEntry.setProfileDescriptionMap(profileDescriptionMap, locale);

		developerEntry.setProfileEmailAddress(profileEmailAddress);

		if ((profileLogo != null) && profileLogo.exists()) {
			AssetAttachment assetAttachment =
				assetAttachmentLocalService.addAssetAttachment(
					userId, DeveloperEntry.class.getName(), developerEntryId,
					profileLogo.getName(), AssetAttachmentConstants.TYPE_ICON,
					0, profileLogo);

			developerEntry.setProfileLogoId(
				assetAttachment.getAssetAttachmentId());
		}

		developerEntry.setProfileWebsite(profileWebsite);
		developerEntry.setSubscriptionStatus(WorkflowConstants.STATUS_INACTIVE);
		developerEntry.setFatcaWithholdingPercentage(
			DeveloperEntryConstants.FATCA_WITHOLDING_PERCENTAGE_DEFAULT);
		developerEntry.setDossieraAccountKey(dossieraAccountKey);
		developerEntry.setType(DeveloperEntryConstants.TYPE_COMPANY);
		developerEntry.setStatus(WorkflowConstants.STATUS_PENDING);
		developerEntry.setStatusByUserId(user.getUserId());
		developerEntry.setStatusByUserName(user.getFullName());
		developerEntry.setStatusDate(now);

		developerEntryPersistence.update(developerEntry, false);

		// Asset attachment

		if ((taxDocumentFile != null) && taxDocumentFile.exists()) {
			assetAttachmentLocalService.addAssetAttachment(
				userId, DeveloperEntry.class.getName(), developerEntryId,
				taxDocumentFileName, AssetAttachmentConstants.TYPE_DOCUMENT, 0,
				taxDocumentFile);
		}

		// Contract entry

		contractAuditLocalService.addContractAudit(
			userId, tosContractEntryId, DeveloperEntry.class.getName(),
			developerEntryId, StringPool.BLANK, 0);

		contractAuditLocalService.addContractAudit(
			userId, developerContractEntryId, DeveloperEntry.class.getName(),
			developerEntryId, StringPool.BLANK, 0);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			DeveloperEntry.class);

		indexer.reindex(developerEntry);

		// Email

		sendDeveloperEntryAdminNotificationEmail(developerEntry);
		sendDeveloperEntryConfirmationEmail(developerEntry);

		return developerEntry;
	}

	public DeveloperEntry addUserDeveloperEntry(
			long userId, String screenName, String emailAddress,
			String firstName, String middleName, String lastName,
			long contractEntryId, long countryId, String phoneNumber,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Developer entry

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validateUser(
			userId, emailAddress, contractEntryId, countryId, phoneNumber);

		Contact contact = user.getContact();

		Calendar birthday = Calendar.getInstance(user.getTimeZone());

		birthday.setTime(user.getBirthday());

		userLocalService.updateUser(
			userId, user.getPasswordUnencrypted(), null, null, false, null,
			null, screenName, emailAddress, user.getFacebookId(),
			user.getOpenId(), user.getLanguageId(), user.getTimeZoneId(),
			user.getGreeting(), user.getComments(), firstName, middleName,
			lastName, contact.getPrefixId(), contact.getSuffixId(),
			user.isMale(), birthday.get(Calendar.MONTH),
			birthday.get(Calendar.DATE), birthday.get(Calendar.YEAR),
			contact.getSmsSn(), contact.getAimSn(), contact.getFacebookSn(),
			contact.getIcqSn(), contact.getJabberSn(), contact.getMsnSn(),
			contact.getMySpaceSn(), contact.getSkypeSn(),
			contact.getTwitterSn(), contact.getYmSn(), user.getJobTitle(),
			user.getGroupIds(), user.getOrganizationIds(), null, null, null,
			serviceContext);

		long developerEntryId = counterLocalService.increment();

		DeveloperEntry developerEntry = developerEntryPersistence.create(
			developerEntryId);

		developerEntry.setUserId(userId);
		developerEntry.setUserName(user.getFullName());
		developerEntry.setCreateDate(now);
		developerEntry.setScreenName(screenName);
		developerEntry.setEmailAddress(emailAddress);
		developerEntry.setFirstName(firstName);
		developerEntry.setMiddleName(middleName);
		developerEntry.setLastName(lastName);
		developerEntry.setContractEntryId(contractEntryId);
		developerEntry.setCountryId(countryId);
		developerEntry.setPhoneNumber(phoneNumber);
		developerEntry.setFatcaWithholdingPercentage(
			DeveloperEntryConstants.FATCA_WITHOLDING_PERCENTAGE_DEFAULT);
		developerEntry.setType(DeveloperEntryConstants.TYPE_USER);
		developerEntry.setStatus(WorkflowConstants.STATUS_APPROVED);
		developerEntry.setStatusByUserId(user.getUserId());
		developerEntry.setStatusByUserName(user.getFullName());
		developerEntry.setStatusDate(now);

		developerEntryPersistence.update(developerEntry, false);

		// Site member

		addGroupUser(developerEntry);

		// Contract entry

		contractAuditLocalService.addContractAudit(
			userId, contractEntryId, User.class.getName(), userId,
			DeveloperEntry.class.getName(), developerEntryId);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			DeveloperEntry.class);

		indexer.reindex(developerEntry);

		// Email

		sendUserConfirmationEmail(developerEntry, user);

		return developerEntry;
	}

	@Override
	public DeveloperEntry deleteDeveloperEntry(DeveloperEntry developerEntry)
		throws PortalException, SystemException {

		developerEntryPersistence.remove(developerEntry);

		appEntryLocalService.expireAppEntries(
			developerEntry.getDeveloperEntryId());

		return developerEntry;
	}

	@Override
	public DeveloperEntry deleteDeveloperEntry(long developerEntryId)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByPrimaryKey(developerEntryId);

		return deleteDeveloperEntry(developerEntry);
	}

	@Override
	public DeveloperEntry deleteUserDeveloperEntry(long userId)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByUI_T_First(
				userId, DeveloperEntryConstants.TYPE_USER, null);

		developerEntryPersistence.remove(developerEntry);

		return developerEntry;
	}

	public DeveloperEntry fetchDeveloperEntry(String dossieraAccountKey)
		throws PortalException, SystemException {

		return developerEntryPersistence.fetchByDossieraAccountKey(
			dossieraAccountKey);
	}

	public DeveloperEntry fetchUserDeveloperEntry(long userId)
		throws PortalException, SystemException {

		try {
			return developerEntryPersistence.findByUI_T_First(
				userId, DeveloperEntryConstants.TYPE_USER, null);
		}
		catch (NoSuchDeveloperEntryException nsdee) {
			return null;
		}
	}

	public List<DeveloperEntry> getDeveloperEntries(
			int status, int start, int end)
		throws SystemException {

		return developerEntryPersistence.findByStatus(status, start, end);
	}

	public List<DeveloperEntry> getDeveloperEntries(
			int type, int status, int start, int end)
		throws SystemException {

		return developerEntryPersistence.findByT_S(type, status, start, end);
	}

	public List<DeveloperEntry> getDeveloperEntries(
			long userId, int type, int status, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return developerEntryPersistence.findByUI_T_S(
			userId, type, status, start, end, obc);
	}

	public int getDeveloperEntriesCount(int status) throws SystemException {
		return developerEntryPersistence.countByStatus(status);
	}

	public int getDeveloperEntriesCount(int type, int status)
		throws SystemException {

		return developerEntryPersistence.countByT_S(type, status);
	}

	public DeveloperEntry getDeveloperEntryByGroupId(long groupId)
		throws PortalException, SystemException {

		Group group = groupPersistence.findByPrimaryKey(groupId);

		if (group.isOrganization()) {
			CorpEntry corpEntry =
				corpEntryLocalService.getOrganizationCorpEntry(
					group.getOrganizationId());

			return developerEntryPersistence.findByDossieraAccountKey(
				corpEntry.getDossieraAccountKey());
		}
		else if (group.isUser()) {
			return developerEntryPersistence.findByUI_T_First(
				group.getClassPK(), DeveloperEntryConstants.TYPE_USER, null);
		}
		else {
			throw new NoSuchDeveloperEntryException();
		}
	}

	public DeveloperEntry getUserDeveloperEntry(long userId)
		throws PortalException, SystemException {

		return developerEntryPersistence.findByUI_T_First(
			userId, DeveloperEntryConstants.TYPE_USER, null);
	}

	public DeveloperEntry updateCompanyDeveloperEntry(
			long userId, long developerEntryId, String emailAddress,
			String legalEntityName, String phoneNumber, String faxNumber,
			String street1, String street2, String street3, String city,
			String zip, long regionId, long countryId, File profileLogo,
			Map<Locale, String> profileDescriptionMap,
			String profileEmailAddress, String profileWebsite,
			String taxDocumentFileName, File taxDocumentFile,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByPrimaryKey(developerEntryId);

		String defaultLanguageId = ParamUtil.getString(
			serviceContext, "profileDescriptionDefaultLanguageId",
				LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

		validateCompany(
			developerEntryId, emailAddress, legalEntityName, phoneNumber,
			faxNumber, regionId, countryId, profileDescriptionMap,
			defaultLanguageId, profileEmailAddress, profileWebsite);

		developerEntry.setEmailAddress(emailAddress);
		developerEntry.setLegalEntityName(legalEntityName);
		developerEntry.setPhoneNumber(phoneNumber);
		developerEntry.setFaxNumber(faxNumber);

		Address address = updateAddress(
			developerEntry.getAddressId(), userId, developerEntryId, street1,
			street2, street3, city, zip, regionId, countryId);

		developerEntry.setAddressId(address.getAddressId());

		developerEntry.setCountryId(countryId);

		if ((profileLogo != null) && profileLogo.exists()) {
			AssetAttachment assetAttachment =
				assetAttachmentLocalService.addAssetAttachment(
					userId, DeveloperEntry.class.getName(), developerEntryId,
					profileLogo.getName(), AssetAttachmentConstants.TYPE_ICON,
					0, profileLogo);

			developerEntry.setProfileLogoId(
					assetAttachment.getAssetAttachmentId());
		}

		if (profileDescriptionMap != null) {
			Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

			developerEntry.setProfileDescriptionMap(
				profileDescriptionMap, locale);
		}

		developerEntry.setProfileEmailAddress(profileEmailAddress);
		developerEntry.setProfileWebsite(profileWebsite);

		if ((taxDocumentFile != null) && taxDocumentFile.exists()) {
			assetAttachmentLocalService.addAssetAttachment(
				userId, DeveloperEntry.class.getName(), developerEntryId,
				taxDocumentFileName, AssetAttachmentConstants.TYPE_DOCUMENT, 0,
				taxDocumentFile);
		}

		developerEntryPersistence.update(developerEntry, false);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			DeveloperEntry.class);

		indexer.reindex(developerEntry);

		return developerEntry;
	}

	public DeveloperEntry updateDeveloperEntry(
			long userId, long developerEntryId, String domainName,
			int domainStatus, String paymentFirstName, String paymentLastName,
			String paymentEmailAddress, Date subscriptionExpirationDate,
			int subscriptionStatus, double fatcaWithholdingPercentage,
			String taxDocumentFileName, File taxDocumentFile)
		throws PortalException, SystemException {

		// Developer entry

		User user = userPersistence.findByPrimaryKey(userId);

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByPrimaryKey(developerEntryId);

		if (Validator.isNotNull(paymentEmailAddress) &&
			!paymentEmailAddress.equals(
				developerEntry.getPaymentEmailAddress())) {

			validate(paymentFirstName, paymentLastName, paymentEmailAddress);
		}

		validate(
			developerEntry, domainName, domainStatus,
			fatcaWithholdingPercentage);

		developerEntry.setPaymentEmailAddress(paymentEmailAddress);
		developerEntry.setDomainName(domainName);
		developerEntry.setDomainStatus(domainStatus);
		developerEntry.setFatcaWithholdingPercentage(
			fatcaWithholdingPercentage);

		developerEntryPersistence.update(developerEntry, false);

		// Asset attachment

		if ((taxDocumentFile != null) && taxDocumentFile.exists()) {
			assetAttachmentLocalService.addAssetAttachment(
				userId, DeveloperEntry.class.getName(), developerEntryId,
				taxDocumentFileName, AssetAttachmentConstants.TYPE_DOCUMENT, 0,
				taxDocumentFile);
		}

		// Discussion message

		addDiscussionMessage(
			userId, user.getFullName(), developerEntryId, domainStatus,
			domainName);

		// Subscription

		updateSubscription(
			developerEntryId, subscriptionExpirationDate, subscriptionStatus);

		return developerEntry;
	}

	public DeveloperEntry updateDeveloperEntry(
			long developerEntryId, String dossieraAccountKey)
		throws PortalException, SystemException {

		validate(developerEntryId, dossieraAccountKey);

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByPrimaryKey(developerEntryId);

		developerEntry.setDossieraAccountKey(dossieraAccountKey);

		developerEntryPersistence.update(developerEntry, false);

		return developerEntry;
	}

	public DeveloperEntry updateDeveloperEntry(
			long developerEntryId, String paymentFirstName,
			String paymentLastName, String paymentEmailAddress)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByPrimaryKey(developerEntryId);

		validate(paymentFirstName, paymentLastName, paymentEmailAddress);

		developerEntry.setPaymentEmailAddress(paymentEmailAddress);

		developerEntryPersistence.update(developerEntry, false);

		return developerEntry;
	}

	public DeveloperEntry updateDeveloperEntryGoogleAnalyticsKey(
			long developerEntryId, String googleAnalyticsKey)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByPrimaryKey(developerEntryId);

		developerEntry.setGoogleAnalyticsKey(googleAnalyticsKey);

		developerEntryPersistence.update(developerEntry, false);

		return developerEntry;
	}

	public DeveloperEntry updateStatus(
			long userId, long developerEntryId, int status,
			String statusMessage)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByPrimaryKey(developerEntryId);

		User user = userPersistence.findByPrimaryKey(userId);

		developerEntry.setStatus(status);
		developerEntry.setStatusByUserId(userId);
		developerEntry.setStatusByUserName(user.getFullName());
		developerEntry.setStatusDate(new Date());
		developerEntry.setStatusMessage(statusMessage);

		developerEntryPersistence.update(developerEntry, false);

		// Site member

		addGroupUser(developerEntry);

		// App entry

		if (developerEntry.isCompany()) {
			CorpEntry corpEntry = corpEntryPersistence.findByDossieraAccountKey(
				developerEntry.getDossieraAccountKey());

			if (status == WorkflowConstants.STATUS_APPROVED) {
				corpEntryLocalService.updateStatus(
					developerEntry.getUserId(), corpEntry.getCorpEntryId(),
					WorkflowConstants.STATUS_APPROVED, StringPool.BLANK);

				corpEntryLocalService.updateSite(
					corpEntry.getCorpEntryId(),
					WorkflowConstants.STATUS_APPROVED, true);
			}
			else if ((status == WorkflowConstants.STATUS_DENIED) ||
					 (status == WorkflowConstants.STATUS_EXPIRED)) {

				appEntryLocalService.expireAppEntries(
					developerEntry.getDeveloperEntryId());
			}
			else {
				throw new DeveloperEntryStatusException();
			}

			sendDeveloperEntryAdminNotificationEmail(developerEntry);
			sendDeveloperEntryStatusMail(developerEntry, corpEntry);
		}
		else if (developerEntry.isUser()) {
			User developerUser = userPersistence.findByPrimaryKey(
				developerEntry.getUserId());

			if (status == WorkflowConstants.STATUS_EXPIRED) {
				appEntryLocalService.expireAppEntries(
					developerEntry.getDeveloperEntryId());
			}
			else if (status != WorkflowConstants.STATUS_APPROVED) {
				throw new DeveloperEntryStatusException();
			}

			sendUserStatusMail(developerEntry, developerUser);
		}

		addDiscussionMessage(
			userId, user.getFullName(), developerEntry.getDeveloperEntryId(),
			status, statusMessage);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			DeveloperEntry.class);

		indexer.reindex(developerEntry);

		return developerEntry;
	}

	public DeveloperEntry updateSubscription(
			long developerEntryId, Date subscriptionExpirationDate,
			int subscriptionStatus)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByPrimaryKey(developerEntryId);

		if (((subscriptionStatus == WorkflowConstants.STATUS_APPROVED) ||
			 (subscriptionStatus == WorkflowConstants.STATUS_DRAFT)) &&
			(subscriptionExpirationDate != null)) {

			Address address = developerEntry.getAddress();

			validate(
				developerEntry.getFirstName(), developerEntry.getLastName(),
				developerEntry.getPhoneNumber(), address.getStreet1(),
				address.getCity(), address.getZip(), address.getRegionId(),
				address.getCountryId(),
				developerEntry.getPaymentEmailAddress());
		}

		developerEntry.setSubscriptionExpirationDate(
			subscriptionExpirationDate);
		developerEntry.setSubscriptionStatus(subscriptionStatus);

		developerEntryPersistence.update(developerEntry, false);

		return developerEntry;
	}

	public DeveloperEntry updateUserDeveloperEntry(
			long userId, long developerEntryId, String firstName,
			String middleName, String lastName, String legalEntityName,
			String phoneNumber, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId,
			String taxDocumentFileName, File taxDocumentFile)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByPrimaryKey(developerEntryId);

		validateUser(
			firstName, lastName, phoneNumber, street1, city, zip, regionId,
			countryId, developerEntry.getType(), taxDocumentFileName,
			taxDocumentFile);

		developerEntry.setFirstName(firstName);
		developerEntry.setMiddleName(middleName);
		developerEntry.setLastName(lastName);

		if (Validator.isNotNull(legalEntityName)) {
			developerEntry.setLegalEntityName(legalEntityName);
		}

		developerEntry.setPhoneNumber(phoneNumber);

		if (Validator.isNotNull(street1)) {
			Address address = updateAddress(
				developerEntry.getAddressId(), userId, developerEntryId,
				street1, street2, street3, city, zip, regionId, countryId);

			developerEntry.setAddressId(address.getAddressId());
		}

		if ((taxDocumentFile != null) && taxDocumentFile.exists()) {
			assetAttachmentLocalService.addAssetAttachment(
				userId, DeveloperEntry.class.getName(), developerEntryId,
				taxDocumentFileName, AssetAttachmentConstants.TYPE_DOCUMENT, 0,
				taxDocumentFile);
		}

		developerEntryPersistence.update(developerEntry, false);

		return developerEntry;
	}

	protected void addDiscussionMessage(
			long userId, String userName, long developerEntryId, int status,
			String statusMessage)
		throws PortalException, SystemException {

		String className =
			MarketplaceAdminDiscussionUtil.getNamespacedClassName(
				DeveloperEntry.class.getName());

		MBMessageDisplay mbMessageDisplay =
			MarketplaceAdminDiscussionUtil.getDiscussionMBMessageDisplay(
				DeveloperEntry.class.getName(), developerEntryId);

		MBThread mbThread = mbMessageDisplay.getThread();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("status", status);
		jsonObject.put("statusMessage", statusMessage);
		jsonObject.put("userId", userId);
		jsonObject.put("userName", userName);

		mbMessageLocalService.addDiscussionMessage(
			OSBConstants.USER_DEFAULT_USER_ID, "System",
			OSBConstants.GROUP_GLOBAL_ID, className, developerEntryId,
			mbThread.getThreadId(), mbThread.getRootMessageId(),
			StringPool.BLANK, jsonObject.toString(), new ServiceContext());
	}

	protected void addGroupUser(DeveloperEntry developerEntry)
		throws PortalException, SystemException {

		if (!developerEntry.isApproved()) {
			return;
		}

		if (developerEntry.isUser()) {
			userLocalService.addGroupUsers(
				OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID,
				new long[] {developerEntry.getUserId()});

			return;
		}

		if (!developerEntry.isCompany()) {
			return;
		}

		CorpEntry corpEntry = corpEntryPersistence.findByDossieraAccountKey(
			developerEntry.getDossieraAccountKey());

		List<User> users = userLocalService.getOrganizationUsers(
			corpEntry.getOrganizationId());

		long[] userIds = new long[0];

		for (User user : users) {
			if (userGroupRoleLocalService.hasUserGroupRole(
					user.getUserId(), corpEntry.getGroupId(),
					OSBConstants.ROLE_OSB_CORP_ADMIN_ID) ||
				userGroupRoleLocalService.hasUserGroupRole(
					user.getUserId(), corpEntry.getGroupId(),
					OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID)) {

				userIds = ArrayUtil.append(userIds, user.getUserId());
			}
		}

		userLocalService.addGroupUsers(
			OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID, userIds);
	}

	protected void sendDeveloperEntryAdminNotificationEmail(
			DeveloperEntry developerEntry)
		throws PortalException, SystemException {

		if (!NotificationThreadLocal.isEnabled()) {
			return;
		}

		String fromName = "Liferay Marketplace";
		String fromAddress = "noreply@liferay.com";

		Map<Locale, String> subjectMap =
			MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailCorpEntryAdminNotificationSubject");
		Map<Locale, String> bodyMap =
			MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailCorpEntryAdminNotificationBody");

		User user = userPersistence.findByPrimaryKey(
			developerEntry.getUserId());

		List<User> users = userLocalService.getRoleUsers(
			OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID);

		for (User curUser : users) {
			OSBSubscriptionSender subscriptionSender =
				new OSBSubscriptionSender();

			subscriptionSender.setCompanyId(user.getCompanyId());
			subscriptionSender.setContextAttributes(developerEntry, null, null);
			subscriptionSender.setContextAttributes(
				developerEntry, curUser.getLocale(), curUser.getTimeZone());
			subscriptionSender.setFrom(fromAddress, fromName);
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setLocalizedBodyMap(bodyMap);
			subscriptionSender.setLocalizedSubjectMap(subjectMap);
			subscriptionSender.setMailId(
				"developer_entry_id", developerEntry.getDeveloperEntryId());
			subscriptionSender.setUserId(user.getUserId());

			subscriptionSender.addRuntimeSubscribers(
				curUser.getEmailAddress(), curUser.getFullName());

			subscriptionSender.flushNotificationsAsync();
		}
	}

	protected void sendDeveloperEntryConfirmationEmail(
			DeveloperEntry developerEntry)
		throws PortalException, SystemException {

		if (!NotificationThreadLocal.isEnabled()) {
			return;
		}

		String fromName = "Liferay Marketplace";
		String fromAddress = "noreply@liferay.com";

		Map<Locale, String> subjectMap =
			MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailCorpEntryConfirmationSubject");
		Map<Locale, String> bodyMap =
			MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailCorpEntryConfirmationBody");

		User user = userPersistence.findByPrimaryKey(
			developerEntry.getUserId());

		String toName = user.getFullName();
		String toAddress = developerEntry.getEmailAddress();

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());
		subscriptionSender.setContextAttributes(
			"[$CORP_NAME$]", developerEntry.getLegalEntityName(),
			"[$MARKETPLACE_FAX_NUMBER$]", OSBConstants.MARKETPLACE_FAX_NUMBER,
			"[$USER_FULL_NAME$]", user.getFullName());
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"developer_entry_id", developerEntry.getDeveloperEntryId());
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(toAddress, toName);

		subscriptionSender.flushNotificationsAsync();
	}

	protected void sendDeveloperEntryStatusMail(
			DeveloperEntry developerEntry, CorpEntry corpEntry)
		throws PortalException, SystemException {

		if (!NotificationThreadLocal.isEnabled()) {
			return;
		}

		String fromName = "Liferay Marketplace";
		String fromAddress = "noreply@liferay.com";

		Map<Locale, String> subjectMap = null;
		Map<Locale, String> bodyMap = null;

		if (developerEntry.getStatus() == WorkflowConstants.STATUS_APPROVED) {
			subjectMap = MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailCorpEntryStatusApprovedSubject");
			bodyMap = MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailCorpEntryStatusApprovedBody");
		}
		else {
			subjectMap = MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailCorpEntryStatusSubject");
			bodyMap = MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailCorpEntryStatusBody");
		}

		User user = userPersistence.findByPrimaryKey(
			developerEntry.getUserId());

		String toName = user.getFullName();
		String toAddress = corpEntry.getContactEmailAddress();

		String developerAppsURL = PortalUtil.getLayoutFullURL(
			developerEntry.getGroupId(),
			OSBPortletKeys.OSB_MARKETPLACE_DEVELOPER_APPS);

		String developerProfileURL = PortalUtil.getLayoutFullURL(
			developerEntry.getGroupId(), OSBPortletKeys.OSB_PROFILE_APPS);

		String status = LanguageUtil.get(
			user.getLocale(),
			WorkflowConstants.toLabel(developerEntry.getStatus()));

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());
		subscriptionSender.setContextAttributes(
			"[$CORP_NAME$]", corpEntry.getName(), "[$DEVELOPER_APPS_URL$]",
			developerAppsURL, "[$DEVELOPER_PROFILE_URL$]", developerProfileURL,
			"[$STATUS$]", status, "[$STATUS_MESSAGE$]",
			developerEntry.getStatusMessage(), "[$USER_FULL_NAME$]",
			user.getFullName());
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"developer_entry_id", developerEntry.getDeveloperEntryId());
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(toAddress, toName);

		subscriptionSender.flushNotificationsAsync();
	}

	protected void sendUserConfirmationEmail(
			DeveloperEntry developerEntry, User user)
		throws PortalException, SystemException {

		if (!NotificationThreadLocal.isEnabled()) {
			return;
		}

		String fromName = "Liferay Marketplace";
		String fromAddress = "noreply@liferay.com";

		Map<Locale, String> subjectMap =
			MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailUserConfirmationSubject");
		Map<Locale, String> bodyMap =
			MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailUserConfirmationBody");

		String developerAppsURL = PortalUtil.getLayoutFullURL(
			developerEntry.getGroupId(),
			OSBPortletKeys.OSB_MARKETPLACE_DEVELOPER_APPS);

		String toName = user.getFullName();
		String toAddress = developerEntry.getEmailAddress();

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());
		subscriptionSender.setContextAttributes(
			"[$DEVELOPER_APPS_URL$]", developerAppsURL, "[$USER_FULL_NAME$]",
			user.getFullName(), "[$USER_SCREEN_NAME$]", user.getScreenName());
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"developer_entry_id", developerEntry.getDeveloperEntryId());
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(toAddress, toName);

		subscriptionSender.flushNotificationsAsync();
	}

	protected void sendUserStatusMail(DeveloperEntry developerEntry, User user)
		throws PortalException, SystemException {

		if (!NotificationThreadLocal.isEnabled()) {
			return;
		}

		String fromName = "Liferay Marketplace";
		String fromAddress = "noreply@liferay.com";

		Map<Locale, String> subjectMap =
			MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailUserStatusSubject");
		Map<Locale, String> bodyMap =
			MarketplaceRegistrationEmailUtil.getLocalizationMap(
				"emailUserStatusBody");

		String toName = user.getFullName();
		String toAddress = developerEntry.getEmailAddress();

		String status = LanguageUtil.get(
			user.getLocale(),
			WorkflowConstants.toLabel(developerEntry.getStatus()));

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());
		subscriptionSender.setContextAttributes(
			"[$USER_FULL_NAME$]", user.getFullName(), "[$STATUS$]", status,
			"[$STATUS_MESSAGE$]", developerEntry.getStatusMessage());
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"developer_entry_id", developerEntry.getDeveloperEntryId());
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(toAddress, toName);

		subscriptionSender.flushNotificationsAsync();
	}

	protected Address updateAddress(
			long addressId, long userId, long developerEntryId, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId)
		throws PortalException, SystemException {

		if (addressId > 0) {
			Address address = addressLocalService.getAddress(addressId);

			return addressLocalService.updateAddress(
				address.getAddressId(), street1, street2, street3, city, zip,
				regionId, countryId, address.getTypeId(), address.getMailing(),
				address.isPrimary());
		}
		else {
			return addressLocalService.addAddress(
				userId, DeveloperEntry.class.getName(), developerEntryId,
				street1, street2, street3, city, zip, regionId, countryId, 0,
				true, true);
		}
	}

	protected void validate(
			DeveloperEntry developerEntry, String domainName, int domainStatus,
			double fatcaWithholdingPercentage)
		throws PortalException, SystemException {

		if (domainStatus == WorkflowConstants.STATUS_APPROVED) {
			if (!Validator.isDomain(domainName)) {
				throw new DeveloperEntryDomainNameException();
			}

			List<DeveloperEntry> developerEntries =
				developerEntryPersistence.findByDN_DS(
					domainName, WorkflowConstants.STATUS_APPROVED);

			if (!developerEntries.isEmpty() &&
				!developerEntries.contains(developerEntry)) {

				throw new DeveloperEntryDomainStatusException();
			}
		}

		if (fatcaWithholdingPercentage ==
				DeveloperEntryConstants.FATCA_WITHOLDING_PERCENTAGE_DEFAULT) {

			return;
		}

		if ((fatcaWithholdingPercentage >= 100) ||
			(fatcaWithholdingPercentage <= 0)) {

			throw new DeveloperEntryFatcaWithholdingPercentageException();
		}
	}

	protected void validate(long developerEntryId, String dossieraAccountKey)
		throws PortalException, SystemException {

		if (Validator.isNull(dossieraAccountKey) ||
			(dossieraAccountKey.length() != 18)) {

			throw new DeveloperEntryDossieraAccountKeyException();
		}

		DeveloperEntry developerEntry =
			developerEntryPersistence.fetchByDossieraAccountKey(
				dossieraAccountKey);

		if ((developerEntry != null) &&
			(developerEntry.getDeveloperEntryId() != developerEntryId)) {

			throw new DuplicateDeveloperEntryDossieraAccountKeyException();
		}
	}

	protected void validate(
			String firstName, String lastName, String emailAddress)
		throws PortalException, SystemException {

		if (!Validator.isEmailAddress(emailAddress)) {
			throw new DeveloperEntryPaymentEmailAddressException();
		}

		if (!ECAccessUtil.isAccount(
				OSBConstants.GROUP_GUEST_ID, firstName, lastName,
				emailAddress)) {

			throw new DeveloperEntryPaymentAccountException();
		}
	}

	protected void validate(
			String firstName, String lastName, String phoneNumber,
			String street1, String city, String zip, long regionId,
			long countryId)
		throws PortalException, SystemException {

		if (Validator.isNull(phoneNumber) ||
			!Validator.isPhoneNumber(phoneNumber)) {

			throw new PhoneNumberException();
		}

		if (Validator.isNull(street1)) {
			throw new AddressStreetException();
		}

		Country country = countryService.getCountry(countryId);

		if (ArrayUtil.contains(
				PortletPropsValues.MARKETPLACE_RESTRICTED_COUNTRIES,
				country.getA2())) {

			throw new RestrictedCountryException();
		}

		if (regionId <= 0) {
			List<Region> regions = regionService.getRegions(countryId);

			if (!regions.isEmpty()) {
				throw new NoSuchRegionException();
			}
		}

		if (Validator.isNull(city)) {
			throw new AddressCityException();
		}

		if (Validator.isNull(zip)) {
			throw new AddressZipException();
		}

		if (Validator.isNull(firstName) || Validator.isNull(lastName)) {
			throw new DeveloperEntryNameException();
		}
	}

	protected void validate(
			String firstName, String lastName, String phoneNumber,
			String street1, String city, String zip, long regionId,
			long countryId, String paymentEmailAddress)
		throws PortalException, SystemException {

		validate(
			firstName, lastName, phoneNumber, street1, city, zip, regionId,
			countryId);

		if (!Validator.isEmailAddress(paymentEmailAddress)) {
			throw new DeveloperEntryPaymentEmailAddressException();
		}
	}

	protected void validateCompany(
			long developerEntryId, String emailAddress, String legalEntityName,
			String phoneNumber, String faxNumber, long regionId, long countryId,
			Map<Locale, String> profileDescriptionMap, String defaultLanguageId,
			String profileEmailAddress, String profileWebsite)
		throws PortalException, SystemException {

		if (!Validator.isEmailAddress(emailAddress)) {
			throw new DeveloperEntryEmailAddressException();
		}

		if (Validator.isNull(legalEntityName)) {
			throw new DeveloperEntryNameException();
		}

		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(
				developerEntryId);

		if (developerEntry != null) {
			List<DeveloperEntry> approvedDeveloperEntries =
				developerEntryPersistence.findByLEN_T_S(
					legalEntityName, DeveloperEntryConstants.TYPE_COMPANY,
					WorkflowConstants.STATUS_APPROVED, 0, 2);

			for (DeveloperEntry approvedDeveloperEntry :
					approvedDeveloperEntries) {

				if (developerEntryId !=
						approvedDeveloperEntry.getDeveloperEntryId()) {

					throw new DuplicateDeveloperEntryException();
				}
			}
		}

		if (!Validator.isPhoneNumber(phoneNumber)) {
			throw new DeveloperEntryPhoneNumberException();
		}

		if (Validator.isNotNull(faxNumber) &&
			!Validator.isPhoneNumber(faxNumber)) {

			throw new DeveloperEntryFaxNumberException();
		}

		Country country = countryService.getCountry(countryId);

		if (ArrayUtil.contains(
				PortletPropsValues.MARKETPLACE_RESTRICTED_COUNTRIES,
			country.getA2())) {

			throw new RestrictedCountryException();
		}

		if (regionId <= 0) {
			List<Region> regions = regionService.getRegions(countryId);

			if (!regions.isEmpty()) {
				throw new NoSuchRegionException();
			}
		}

		Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

		if (profileDescriptionMap.isEmpty() || (locale == null) ||
			!profileDescriptionMap.containsKey(locale) ||
			Validator.isNull(profileDescriptionMap.get(locale))) {

			throw new DeveloperEntryProfileDescriptionException();
		}

		if (Validator.isNotNull(profileEmailAddress) &&
			!Validator.isEmailAddress(profileEmailAddress)) {

			throw new DeveloperEntryProfileEmailAddressException();
		}

		if (Validator.isNotNull(profileWebsite) &&
			!Validator.isUrl(profileWebsite)) {

			throw new DeveloperEntryProfileWebsiteException();
		}
	}

	protected void validateCompany(
			long developerEntryId, String emailAddress, String legalEntityName,
			String phoneNumber, String faxNumber, long regionId, long countryId,
			Map<Locale, String> profileDescriptionMap, String defaultLanguageId,
			String profileEmailAddress, String profileWebsite,
			File profileLogoFile, String taxDocumentFileName,
			File taxDocumentFile)
		throws PortalException, SystemException {

		validateCompany(
			developerEntryId, emailAddress, legalEntityName, phoneNumber,
			faxNumber, regionId, countryId, profileDescriptionMap,
			defaultLanguageId, profileEmailAddress, profileWebsite);

		if ((profileLogoFile == null) || !profileLogoFile.exists()) {
			throw new DeveloperEntryProfileLogoException(
				DeveloperEntryProfileLogoException.TYPE_LOGO_INVALID);
		}

		boolean validExtension = false;

		for (String extension :
				PortletPropsValues.DEVELOPER_ENTRY_LOGO_EXTENSIONS) {

			if (StringUtil.endsWith(profileLogoFile.getName(), extension)) {
				validExtension = true;

				break;
			}
		}

		if (!validExtension) {
			throw new DeveloperEntryProfileLogoException(
				DeveloperEntryProfileLogoException.TYPE_LOGO_EXTENSION);
		}

		if (Validator.isNull(taxDocumentFileName)) {
			throw new DeveloperEntryTaxDocumentFileNameException();
		}

		if ((taxDocumentFile == null) || !taxDocumentFile.exists()) {
			throw new DeveloperEntryTaxDocumentException();
		}
	}

	protected void validateUser(
			long userId, String emailAddress, long contractEntryId,
			long countryId, String phoneNumber)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		String currentEmailAddress = user.getEmailAddress();

		if (!currentEmailAddress.equals(emailAddress) &&
			currentEmailAddress.contains("@liferay.com")) {

			throw new RestrictedLiferayEmailAddressException();
		}

		contractEntryPersistence.findByPrimaryKey(contractEntryId);

		Country country = countryService.getCountry(countryId);

		if (ArrayUtil.contains(
				PortletPropsValues.MARKETPLACE_RESTRICTED_COUNTRIES,
				country.getA2())) {

			throw new RestrictedCountryException();
		}

		if (Validator.isNull(phoneNumber) ||
			!Validator.isPhoneNumber(phoneNumber)) {

			throw new PhoneNumberException();
		}
	}

	protected void validateUser(
			String firstName, String lastName, String phoneNumber,
			String street1, String city, String zip, long regionId,
			long countryId, int type, String taxDocumentFileName,
			File taxDocumentFile)
		throws PortalException, SystemException {

		validate(
			firstName, lastName, phoneNumber, street1, city, zip, regionId,
			countryId);

		if (Validator.isNull(taxDocumentFileName)) {
			throw new DeveloperEntryTaxDocumentFileNameException();
		}

		if ((taxDocumentFile == null) || !taxDocumentFile.exists()) {
			Country country = countryService.getCountry(countryId);

			if ((type != DeveloperEntryConstants.TYPE_COMPANY) ||
				!country.getA2().equals("US")) {

				throw new DeveloperEntryTaxDocumentException();
			}
		}
	}

}