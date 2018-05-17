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

package com.liferay.osb.admin.portlet;

import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.osb.admin.util.KeyGenerator;
import com.liferay.osb.exception.AccountEntryCodeException;
import com.liferay.osb.exception.AccountEntryCorpProjectException;
import com.liferay.osb.exception.AccountEntryIndustryException;
import com.liferay.osb.exception.AccountEntryLanguageIdException;
import com.liferay.osb.exception.AccountEntryMaximumCustomersException;
import com.liferay.osb.exception.AccountEntryNameException;
import com.liferay.osb.exception.AccountEntryPartnerEntryException;
import com.liferay.osb.exception.AccountEntrySupportRegionException;
import com.liferay.osb.exception.AccountEnvironmentAttachmentSizeException;
import com.liferay.osb.exception.AccountEnvironmentEnvASException;
import com.liferay.osb.exception.AccountEnvironmentEnvDBException;
import com.liferay.osb.exception.AccountEnvironmentEnvLFRException;
import com.liferay.osb.exception.AccountEnvironmentEnvOSException;
import com.liferay.osb.exception.AccountEnvironmentNameException;
import com.liferay.osb.exception.DuplicateAccountCustomerException;
import com.liferay.osb.exception.DuplicateAccountEntryException;
import com.liferay.osb.exception.DuplicateAccountEnvironmentException;
import com.liferay.osb.exception.DuplicateOfferingBundleException;
import com.liferay.osb.exception.DuplicateOfferingDefinitionException;
import com.liferay.osb.exception.DuplicatePartnerEntryCodeException;
import com.liferay.osb.exception.DuplicatePartnerEntryDossieraAccountKeyException;
import com.liferay.osb.exception.DuplicateProductEntryException;
import com.liferay.osb.exception.DuplicateSupportRegionException;
import com.liferay.osb.exception.DuplicateSupportResponseException;
import com.liferay.osb.exception.LicenseEntryNameException;
import com.liferay.osb.exception.LicenseEntryPortalVersionException;
import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.exception.NoSuchOfferingDefinitionException;
import com.liferay.osb.exception.NoSuchProductEntryException;
import com.liferay.osb.exception.NoSuchSupportResponseException;
import com.liferay.osb.exception.OfferingBundleNameException;
import com.liferay.osb.exception.OrderEntryActualStartDateException;
import com.liferay.osb.exception.PartnerEntryCodeException;
import com.liferay.osb.exception.PartnerEntryParentPartnerEntryException;
import com.liferay.osb.exception.ProductEntryEnvironmentException;
import com.liferay.osb.exception.ProductEntryNameException;
import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.osb.exception.RequiredAccountEntryException;
import com.liferay.osb.exception.RequiredOfferingDefinitionException;
import com.liferay.osb.exception.RequiredOfferingEntryException;
import com.liferay.osb.exception.RequiredPartnerEntryException;
import com.liferay.osb.exception.RequiredProductEntryException;
import com.liferay.osb.exception.RequiredSupportRegionException;
import com.liferay.osb.exception.RequiredSupportResponseException;
import com.liferay.osb.exception.SupportRegionNameException;
import com.liferay.osb.exception.SupportResponseNameException;
import com.liferay.osb.exception.SupportResponseSupportLevelException;
import com.liferay.osb.model.AccountAttachment;
import com.liferay.osb.model.AccountAttachmentConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.model.AccountEnvironmentAttachmentConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.impl.OfferingEntryImpl;
import com.liferay.osb.rabbitmq.ProvisioningCreateRabbitMQConsumer;
import com.liferay.osb.service.AccountAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;
import com.liferay.osb.service.OfferingBundleLocalServiceUtil;
import com.liferay.osb.service.OfferingDefinitionLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.OSBRequestUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.AddressCityException;
import com.liferay.portal.kernel.exception.AddressStreetException;
import com.liferay.portal.kernel.exception.AddressZipException;
import com.liferay.portal.kernel.exception.ContactFirstNameException;
import com.liferay.portal.kernel.exception.ContactLastNameException;
import com.liferay.portal.kernel.exception.DuplicateUserEmailAddressException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.ReservedUserEmailAddressException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.io.Base64InputStream;
import com.liferay.portal.kernel.io.ProtectedObjectInputStream;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
/* TODO update rabbitMQ integration
import com.liferay.osb.rabbitmq.RabbitMQConsumerRouter;
import com.liferay.rabbitmq.consumer.RabbitMQConsumer;
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 * @author Haote Chou
 */
public class AdminPortlet extends MVCPortlet {

	public void auditAccountEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		AccountEntryLocalServiceUtil.auditAccountEntry(
			themeDisplay.getUserId(), accountEntryId);
	}

	public void debugLicenseFiles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;

		try {

			// License file

			File licenseFile = uploadPortletRequest.getFile("license");

			if ((licenseFile != null) && (licenseFile.length() > 0)) {
				fileInputStream = new FileInputStream(licenseFile);

				objectInputStream = new ProtectedObjectInputStream(
					new Base64InputStream(fileInputStream));

				int licenseVersion = objectInputStream.readInt();

				if (licenseVersion == 3) {
					Properties properties = getLicenseProperties(
						objectInputStream);

					actionRequest.setAttribute("licenseProperties", properties);
				}
			}

			// Server ID

			File serverIdFile = uploadPortletRequest.getFile("serverId");

			if ((serverIdFile != null) && (serverIdFile.length() > 0)) {
				byte[] bytes = FileUtil.getBytes(serverIdFile);

				Properties serverProperties = KeyGenerator.decryptServerId(
					bytes);

				actionRequest.setAttribute(
					"serverProperties", serverProperties);

				String serverId = serverProperties.getProperty("serverId");

				byte[] serverIdBytes = (byte[])Base64.stringToObject(serverId);

				Properties serverIdProperties = KeyGenerator.decryptServerId(
					serverIdBytes);

				actionRequest.setAttribute(
					"serverIdProperties", serverIdProperties);
			}
		}
		finally {
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				}
				catch (IOException ioe) {
				}
			}

			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				}
				catch (IOException ioe) {
				}
			}

			uploadPortletRequest.cleanUp();
		}
	}

	public void debugRabbitMQ(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			return;
		}

		String routingKey = ParamUtil.getString(actionRequest, "routingKey");
		String message = ParamUtil.getString(actionRequest, "message");
		String[] properties = StringUtil.split(
			ParamUtil.getString(actionRequest, "properties"),
			StringPool.NEW_LINE);

		message = StringUtil.replace(
			message, CharPool.NEW_LINE, StringPool.BLANK);

		Map<String, Object> propertiesMap = MapUtil.toLinkedHashMap(properties);

		ProvisioningCreateRabbitMQConsumer rabbitMQConsumer =
			new ProvisioningCreateRabbitMQConsumer();

		rabbitMQConsumer.parse(routingKey, message, propertiesMap);
	}

	public void deleteAccountCustomer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountCustomerId = ParamUtil.getLong(
			actionRequest, "accountCustomerId");

		AccountCustomerLocalServiceUtil.deleteAccountCustomer(
			themeDisplay.getUserId(), accountCustomerId);
	}

	public void deleteAccountEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		AccountEntryLocalServiceUtil.deleteAccountEntry(accountEntryId);
	}

	public void deleteLicenseEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long licenseEntryId = ParamUtil.getLong(
			actionRequest, "licenseEntryId");

		LicenseEntryLocalServiceUtil.deleteLicenseEntry(licenseEntryId);
	}

	public void deleteOfferingBundle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long offeringBundleId = ParamUtil.getLong(
			actionRequest, "offeringBundleId");

		OfferingBundleLocalServiceUtil.deleteOfferingBundle(offeringBundleId);
	}

	public void deleteOfferingDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long offeringDefinitionId = ParamUtil.getLong(
			actionRequest, "offeringDefinitionId");

		OfferingDefinitionLocalServiceUtil.deleteOfferingDefinition(
			offeringDefinitionId);
	}

	public void deleteOrderEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long orderEntryId = ParamUtil.getLong(actionRequest, "orderEntryId");

		OrderEntry orderEntry = OrderEntryLocalServiceUtil.deleteOrderEntry(
			orderEntryId);

		syncToLCS(actionRequest, actionResponse, orderEntry);
	}

	public void deletePartnerEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long partnerEntryId = ParamUtil.getLong(
			actionRequest, "partnerEntryId");

		PartnerEntryLocalServiceUtil.deletePartnerEntry(partnerEntryId);
	}

	public void deleteProductEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");

		ProductEntryLocalServiceUtil.deleteProductEntry(productEntryId);
	}

	public void deleteSupportRegion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long supportRegionId = ParamUtil.getLong(
			actionRequest, "supportRegionId");

		SupportRegionLocalServiceUtil.deleteSupportRegion(supportRegionId);
	}

	public void deleteSupportResponse(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long supportResponseId = ParamUtil.getLong(
			actionRequest, "supportResponseId");

		SupportResponseLocalServiceUtil.deleteSupportResponse(
			supportResponseId);
	}

	public void renewOrderEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long orderEntryId = ParamUtil.getLong(actionRequest, "orderEntryId");

		int renewCount = ParamUtil.getInteger(actionRequest, "renewCount");

		OrderEntry orderEntry = OrderEntryLocalServiceUtil.renewOrderEntry(
			themeDisplay.getUserId(), orderEntryId, renewCount);

		syncToLCS(actionRequest, actionResponse, orderEntry);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("accountAttachment")) {
				serveAccountAttachment(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("accountEntries")) {
				serveAccountEntries(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("accountEnvironmentAttachment")) {
				serveAccountEnvironmentAttachment(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("accountEnvironment")) {
				JSONObject jsonObject = OSBRequestUtil.getAccountEnvironment(
					resourceRequest, resourceResponse);

				writeJSON(resourceRequest, resourceResponse, jsonObject);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void syncToLCS(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			List<AccountEntry> accountEntries =
				AccountEntryLocalServiceUtil.getAccountEntries(
					AccountEntryConstants.STATUSES_ACTIVE, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (AccountEntry accountEntry : accountEntries) {
				LCSSubscriptionEntryLocalServiceUtil.syncToLCS(
					accountEntry.getAccountEntryId());
			}
		}
		catch (Exception e) {
			_log.error("Unable to sync to LCS", e);

			SessionMessages.add(actionRequest, "lcsSyncFailed");

			addSuccessMessage(actionRequest, actionResponse);

			sendRedirect(actionRequest, actionResponse);
		}
	}

	public void updateAccountCustomer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountCustomerId = ParamUtil.getLong(
			actionRequest, "accountCustomerId");

		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");
		int role = ParamUtil.getInteger(
			actionRequest, "role_" + accountCustomerId);
		int notifications = ParamUtil.getInteger(
			actionRequest, "notifications_" + accountCustomerId);

		if (accountCustomerId > 0) {
			AccountCustomerLocalServiceUtil.updateAccountCustomer(
				themeDisplay.getUserId(), accountCustomerId, role,
				notifications);
		}
		else {
			AccountCustomerLocalServiceUtil.addAccountCustomer(
				themeDisplay.getUserId(), emailAddress, accountEntryId, role,
				notifications);
		}
	}

	public void updateAccountEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		String corpProjectUuid = ParamUtil.getString(
			actionRequest, "corpProjectUuid");
		String name = ParamUtil.getString(actionRequest, "name");
		String code = ParamUtil.getString(actionRequest, "code");
		int type = ParamUtil.getInteger(actionRequest, "type");
		int industry = ParamUtil.getInteger(actionRequest, "industry");
		long partnerEntryId = ParamUtil.getLong(
			actionRequest, "partnerEntryId");
		boolean partnerManagedSupport = ParamUtil.getBoolean(
			actionRequest, "partnerManagedSupport");
		int tier = ParamUtil.getInteger(actionRequest, "tier");
		int maxCustomers = ParamUtil.getInteger(actionRequest, "maxCustomers");
		String instructions = ParamUtil.getString(
			actionRequest, "instructions");
		String notes = ParamUtil.getString(actionRequest, "notes");
		long addressId = ParamUtil.getLong(actionRequest, "addressId");
		String street1 = ParamUtil.getString(actionRequest, "street1");
		String street2 = ParamUtil.getString(actionRequest, "street2");
		String street3 = ParamUtil.getString(actionRequest, "street3");
		String city = ParamUtil.getString(actionRequest, "city");
		String zip = ParamUtil.getString(actionRequest, "zip");
		long regionId = ParamUtil.getLong(actionRequest, "regionId");
		long countryId = ParamUtil.getLong(actionRequest, "countryId");
		String ewsaDossieraProjectKey = ParamUtil.getString(
			actionRequest, "ewsaDossieraProjectKey");

		String[] languageIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "languageIds"));
		long[] supportRegionIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "supportRegionIds"), 0L);

		if (accountEntryId <= 0) {
			AccountEntryLocalServiceUtil.addAccountEntry(
				themeDisplay.getUserId(), corpProjectUuid, StringPool.BLANK,
				name, code, type, industry, partnerEntryId,
				partnerManagedSupport, tier, maxCustomers, instructions, notes,
				languageIds, supportRegionIds, street1, street2, street3, city,
				zip, regionId, countryId, ewsaDossieraProjectKey);
		}
		else {
			AccountEntry accountEntry =
				AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

			AccountEntryLocalServiceUtil.updateAccountEntry(
				themeDisplay.getUserId(), accountEntryId, corpProjectUuid,
				accountEntry.getCorpEntryName(), name, code, type, industry,
				partnerEntryId, partnerManagedSupport, tier, maxCustomers,
				instructions, notes, languageIds, supportRegionIds, addressId,
				street1, street2, street3, city, zip, regionId, countryId,
				ewsaDossieraProjectKey);
		}

		updateAccountAttachment(actionRequest);
	}

	public void updateAccountEnvironment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEnvironmentId = ParamUtil.getLong(
			actionRequest, "accountEnvironmentId");

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");
		String name = ParamUtil.getString(actionRequest, "name");
		int envOS = ParamUtil.getInteger(actionRequest, "envOS");
		String envOSCustom = ParamUtil.getString(actionRequest, "envOSCustom");
		int envDB = ParamUtil.getInteger(actionRequest, "envDB");
		int envJVM = ParamUtil.getInteger(actionRequest, "envJVM");
		int envAS = ParamUtil.getInteger(actionRequest, "envAS");
		int envLFR = ParamUtil.getInteger(actionRequest, "envLFR");

		List<ObjectValuePair<String, File>> files = new ArrayList<>();

		List<Integer> types = new ArrayList<>();

		String[] uploadFileNames = {"patch-level", "portal-ext"};

		try {
			for (String uploadFileName : uploadFileNames) {
				String fileName = uploadPortletRequest.getFileName(
					uploadFileName);

				if (Validator.isNull(fileName)) {
					continue;
				}

				File file = uploadPortletRequest.getFile(uploadFileName);

				if (file == null) {
					continue;
				}

				if (file.length() <= 0) {
					throw new AccountEnvironmentAttachmentSizeException(
						AccountEnvironmentAttachmentSizeException.EMPTY_FILE);
				}

				ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
					fileName, file);

				files.add(ovp);

				if (uploadFileName.equals("portal-ext")) {
					types.add(
						AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
				}
				else {
					types.add(
						AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
				}
			}

			if (accountEnvironmentId > 0) {
				AccountEnvironmentLocalServiceUtil.updateAccountEnvironment(
					themeDisplay.getUserId(), accountEnvironmentId,
					productEntryId, name, envOS, envOSCustom, envDB, envJVM,
					envAS, envLFR, files, types);

				SessionMessages.add(
					actionRequest,
					OSBPortletKeys.OSB_ADMIN +
						SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
					OSBPortletKeys.OSB_ADMIN);

				Map<String, String> refreshPortletData = new HashMap<>();

				refreshPortletData.put(
					actionResponse.getNamespace() + "productEntryId",
					String.valueOf(productEntryId));

				SessionMessages.add(
					actionRequest,
					OSBPortletKeys.OSB_ADMIN +
						SessionMessages.KEY_SUFFIX_REFRESH_PORTLET_DATA,
					refreshPortletData);
			}
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void updateAccountWorkers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		int[] roles = new int[addUserIds.length];
		int[] notifications = new int[addUserIds.length];

		for (int i = 0; i < addUserIds.length; i++) {
			long userId = addUserIds[i];

			roles[i] = ParamUtil.getInteger(actionRequest, "role_" + userId);
			notifications[i] = ParamUtil.getInteger(
				actionRequest, "notifications_" + userId);
		}

		AccountWorkerLocalServiceUtil.addAccountWorkers(
			themeDisplay.getUserId(), addUserIds, accountEntryId, roles,
			notifications);
		AccountWorkerLocalServiceUtil.deleteAccountWorkers(
			themeDisplay.getUserId(), removeUserIds, accountEntryId);
	}

	public void updateLicenseEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long licenseEntryId = ParamUtil.getLong(
			actionRequest, "licenseEntryId");

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");
		String name = ParamUtil.getString(actionRequest, "name");
		String type = ParamUtil.getString(actionRequest, "type");
		int portalVersionMin = ParamUtil.getInteger(
			actionRequest, "portalVersionMin");
		int portalVersionMax = ParamUtil.getInteger(
			actionRequest, "portalVersionMax");

		if (licenseEntryId <= 0) {
			LicenseEntryLocalServiceUtil.addLicenseEntry(
				themeDisplay.getUserId(), productEntryId, name, type,
				portalVersionMin, portalVersionMax);
		}
		else {
			LicenseEntryLocalServiceUtil.updateLicenseEntry(
				licenseEntryId, productEntryId, name, type, portalVersionMin,
				portalVersionMax);
		}
	}

	public void updateOfferingBundle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long offeringBundleId = ParamUtil.getLong(
			actionRequest, "offeringBundleId");

		String name = ParamUtil.getString(actionRequest, "name");
		long[] offeringDefinitionIds = ParamUtil.getLongValues(
			actionRequest, "offeringDefinitionIds");

		if (offeringBundleId > 0) {
			OfferingBundleLocalServiceUtil.updateOfferingBundle(
				offeringBundleId, name, offeringDefinitionIds);
		}
		else {
			OfferingBundleLocalServiceUtil.addOfferingBundle(
				themeDisplay.getUserId(), name, offeringDefinitionIds);
		}
	}

	public void updateOfferingDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long offeringDefinitionId = ParamUtil.getLong(
			actionRequest, "offeringDefinitionId");

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");
		long supportResponseId = ParamUtil.getLong(
			actionRequest, "supportResponseId");
		String productDescription = ParamUtil.getString(
			actionRequest, "productDescription");
		boolean licenses = ParamUtil.getBoolean(actionRequest, "licenses");
		boolean unlimitedLicenses = ParamUtil.getBoolean(
			actionRequest, "unlimitedLicenses");
		long maxConcurrentUsers = ParamUtil.getInteger(
			actionRequest, "maxConcurrentUsers");
		long maxUsers = ParamUtil.getInteger(actionRequest, "maxUsers");
		boolean supportTickets = ParamUtil.getBoolean(
			actionRequest, "supportTickets");

		if (offeringDefinitionId <= 0) {
			OfferingDefinitionLocalServiceUtil.addOfferingDefinition(
				themeDisplay.getUserId(), productEntryId, supportResponseId,
				productDescription, licenses, unlimitedLicenses,
				maxConcurrentUsers, maxUsers, supportTickets);
		}
		else {
			OfferingDefinitionLocalServiceUtil.updateOfferingDefinition(
				offeringDefinitionId, productEntryId, supportResponseId,
				productDescription, licenses, unlimitedLicenses,
				maxConcurrentUsers, maxUsers, supportTickets);
		}
	}

	public void updateOfferingEntryStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String key = ParamUtil.getString(actionRequest, "key");

		long[] offeringEntryIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "offeringEntryIds"), 0L);
		int status = ParamUtil.getInteger(actionRequest, "status_" + key);

		for (long offeringEntryId : offeringEntryIds) {
			OfferingEntryServiceUtil.updateStatus(offeringEntryId, status);
		}
	}

	public void updateOrderEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long orderEntryId = ParamUtil.getLong(actionRequest, "orderEntryId");

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");
		String salesforceOpportunityKey = ParamUtil.getString(
			actionRequest, "salesforceOpportunityKey");
		String purchaseOrderKey = ParamUtil.getString(
			actionRequest, "purchaseOrderKey");

		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");

		boolean prorated = ParamUtil.getBoolean(actionRequest, "prorated");

		int actualStartDateMonth = ParamUtil.getInteger(
			actionRequest, "actualStartDateMonth");
		int actualStartDateDay = ParamUtil.getInteger(
			actionRequest, "actualStartDateDay");
		int actualStartDateYear = ParamUtil.getInteger(
			actionRequest, "actualStartDateYear");

		List<OfferingEntry> offeringEntries = new ArrayList<>();

		int offeringEntriesCount = ParamUtil.getInteger(
			actionRequest, "offeringEntriesCount");

		for (int i = 0; i < offeringEntriesCount; i++) {
			int quantity = ParamUtil.getInteger(actionRequest, "quantity_" + i);

			if (quantity <= 0) {
				continue;
			}

			long offeringEntryId = ParamUtil.getLong(
				actionRequest, "offeringEntryId_" + i);

			long productEntryId = ParamUtil.getLong(
				actionRequest, "productEntryId_" + i);
			long supportResponseId = ParamUtil.getLong(
				actionRequest, "supportResponseId_" + i);
			String productDescription = ParamUtil.getString(
				actionRequest, "productDescription_" + i);
			int version = ParamUtil.getInteger(actionRequest, "version_" + i);
			boolean licenses = ParamUtil.getBoolean(
				actionRequest, "licenses_" + i);
			long licenseLifetime = ParamUtil.getLong(
				actionRequest, "licenseLifetime_" + i);
			boolean supportTickets = ParamUtil.getBoolean(
				actionRequest, "supportTickets_" + i);
			long supportLifetime = ParamUtil.getLong(
				actionRequest, "supportLifetime_" + i);
			int sizing = ParamUtil.getInteger(actionRequest, "sizing_" + i);

			OfferingEntry offeringEntry = new OfferingEntryImpl();

			offeringEntry.setOfferingEntryId(offeringEntryId);
			offeringEntry.setProductEntryId(productEntryId);
			offeringEntry.setSupportResponseId(supportResponseId);
			offeringEntry.setProductDescription(productDescription);
			offeringEntry.setType(OfferingEntryConstants.TYPE_REGULAR);
			offeringEntry.setVersion(version);
			offeringEntry.setLicenses(licenses);
			offeringEntry.setLicenseLifetime(licenseLifetime);
			offeringEntry.setSupportTickets(supportTickets);
			offeringEntry.setSupportLifetime(supportLifetime);
			offeringEntry.setSizing(sizing);
			offeringEntry.setQuantity(quantity);

			offeringEntries.add(offeringEntry);
		}

		OrderEntry orderEntry = null;

		if (orderEntryId <= 0) {
			orderEntry = OrderEntryLocalServiceUtil.addOrderEntry(
				themeDisplay.getUserId(), accountEntryId, purchaseOrderKey,
				startDateMonth, startDateDay, startDateYear, prorated,
				actualStartDateMonth, actualStartDateDay, actualStartDateYear,
				WorkflowConstants.STATUS_APPROVED, salesforceOpportunityKey,
				offeringEntries);
		}
		else {
			orderEntry = OrderEntryLocalServiceUtil.updateOrderEntry(
				themeDisplay.getUserId(), orderEntryId, accountEntryId,
				purchaseOrderKey, startDateMonth, startDateDay, startDateYear,
				prorated, actualStartDateMonth, actualStartDateDay,
				actualStartDateYear, salesforceOpportunityKey, offeringEntries);
		}

		syncToLCS(actionRequest, actionResponse, orderEntry);
	}

	public void updatePartnerEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long partnerEntryId = ParamUtil.getLong(
			actionRequest, "partnerEntryId");

		long parentPartnerEntryId = ParamUtil.getLong(
			actionRequest, "parentPartnerEntryId");
		String dossieraAccountKey = ParamUtil.getString(
			actionRequest, "dossieraAccountKey");
		String code = ParamUtil.getString(actionRequest, "code");
		String notes = ParamUtil.getString(actionRequest, "notes");
		int status = ParamUtil.getInteger(actionRequest, "status");
		long[] supportRegionIds = ParamUtil.getLongValues(
			actionRequest, "supportRegionId");

		PartnerEntry partnerEntry = null;

		if (partnerEntryId <= 0) {
			partnerEntry = PartnerEntryLocalServiceUtil.addPartnerEntry(
				themeDisplay.getUserId(), parentPartnerEntryId,
				dossieraAccountKey, code, notes, supportRegionIds);
		}
		else {
			partnerEntry = PartnerEntryLocalServiceUtil.updatePartnerEntry(
				themeDisplay.getUserId(), partnerEntryId, dossieraAccountKey,
				code, notes, status, supportRegionIds);
		}

		List<Address> addresses = UsersAdminUtil.getAddresses(actionRequest);

		UsersAdminUtil.updateAddresses(
			PartnerEntry.class.getName(), partnerEntry.getPartnerEntryId(),
			addresses);

		List<Phone> phones = UsersAdminUtil.getPhones(actionRequest);

		UsersAdminUtil.updatePhones(
			PartnerEntry.class.getName(), partnerEntry.getPartnerEntryId(),
			phones);
	}

	public void updatePartnerWorkers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		long partnerEntryId = ParamUtil.getLong(
			actionRequest, "partnerEntryId");

		int[] roles = new int[addUserIds.length];
		int[] notifications = new int[addUserIds.length];

		for (int i = 0; i < addUserIds.length; i++) {
			long userId = addUserIds[i];

			roles[i] = ParamUtil.getInteger(actionRequest, "role_" + userId);
			notifications[i] = ParamUtil.getInteger(
				actionRequest, "notifications_" + userId);
		}

		PartnerWorkerLocalServiceUtil.addPartnerWorkers(
			addUserIds, partnerEntryId, roles, notifications);
		PartnerWorkerLocalServiceUtil.deletePartnerWorkers(
			removeUserIds, partnerEntryId);
	}

	public void updateProductEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");

		String name = ParamUtil.getString(actionRequest, "name");
		int type = ParamUtil.getInteger(actionRequest, "type");
		int environment = ParamUtil.getInteger(actionRequest, "environment");
		String versionsListType = ParamUtil.getString(
			actionRequest, "versionsListType");
		String[] dossieraIdMappings = StringUtil.split(
			ParamUtil.getString(actionRequest, "dossieraIdMappings"),
			StringPool.NEW_LINE);

		if (productEntryId <= 0) {
			ProductEntryLocalServiceUtil.addProductEntry(
				themeDisplay.getUserId(), name, type, environment,
				versionsListType, dossieraIdMappings);
		}
		else {
			ProductEntryLocalServiceUtil.updateProductEntry(
				productEntryId, name, type, environment, versionsListType,
				dossieraIdMappings);
		}
	}

	public void updateSupportRegion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long supportRegionId = ParamUtil.getLong(
			actionRequest, "supportRegionId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");

		if (supportRegionId > 0) {
			SupportRegionLocalServiceUtil.updateSupportRegion(
				supportRegionId, name, description, timeZoneId);
		}
		else {
			SupportRegionLocalServiceUtil.addSupportRegion(
				themeDisplay.getUserId(), name, description, timeZoneId);
		}
	}

	public void updateSupportResponse(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long supportResponseId = ParamUtil.getLong(
			actionRequest, "supportResponseId");

		String name = ParamUtil.getString(actionRequest, "name");
		int supportLevel = ParamUtil.getInteger(actionRequest, "supportLevel");
		int severity1Response = ParamUtil.getInteger(
			actionRequest, "severity1Response");
		int severity1Resolution = ParamUtil.getInteger(
			actionRequest, "severity1Resolution");
		int severity2Response = ParamUtil.getInteger(
			actionRequest, "severity2Response");
		int severity2Resolution = ParamUtil.getInteger(
			actionRequest, "severity2Resolution");
		int severity3Response = ParamUtil.getInteger(
			actionRequest, "severity3Response");
		int severity3Resolution = ParamUtil.getInteger(
			actionRequest, "severity3Resolution");

		if (supportResponseId <= 0) {
			SupportResponseLocalServiceUtil.addSupportResponse(
				themeDisplay.getUserId(), name, supportLevel, severity1Response,
				severity1Resolution, severity2Response, severity2Resolution,
				severity3Response, severity3Resolution);
		}
		else {
			SupportResponseLocalServiceUtil.updateSupportResponse(
				supportResponseId, name, supportLevel, severity1Response,
				severity1Resolution, severity2Response, severity2Resolution,
				severity3Response, severity3Resolution);
		}
	}

	protected Properties getLicenseProperties(
			ObjectInputStream objectInputStream)
		throws ClassNotFoundException, IOException {

		String accountEntryName = objectInputStream.readUTF();
		String description = objectInputStream.readUTF();
		Date expirationDate = (Date)objectInputStream.readObject();
		String[] hostNames = (String[])objectInputStream.readObject();
		String[] ipAddresses = (String[])objectInputStream.readObject();
		String key = objectInputStream.readUTF();
		long lastAccessedTime = objectInputStream.readLong();
		String licenseEntryName = objectInputStream.readUTF();
		String licenseEntryType = objectInputStream.readUTF();
		String licenseVersion = objectInputStream.readUTF();
		String[] macAddresses = (String[])objectInputStream.readObject();
		int maxHttpSessions = objectInputStream.readInt();
		int maxServers = objectInputStream.readInt();
		long maxConcurrentUsers = objectInputStream.readLong();
		long maxUsers = objectInputStream.readLong();
		String owner = objectInputStream.readUTF();
		String productEntryName = objectInputStream.readUTF();
		String productId = objectInputStream.readUTF();
		String productVersion = objectInputStream.readUTF();
		String[] serverIds = (String[])objectInputStream.readObject();
		Date startDate = (Date)objectInputStream.readObject();

		DateFormat longDateFormatDateTime = DateFormat.getDateTimeInstance(
			DateFormat.FULL, DateFormat.FULL, LocaleUtil.US);

		Properties properties = new Properties();

		properties.put("accountEntryName", accountEntryName);
		properties.put("description", description);
		properties.put(
			"expirationDate", longDateFormatDateTime.format(expirationDate));
		properties.put("hostNames", StringUtil.merge(hostNames));
		properties.put("ipAddresses", StringUtil.merge(ipAddresses));
		properties.put("key", key);
		properties.put("lastAccessedTime", lastAccessedTime);
		properties.put("licenseEntryName", licenseEntryName);
		properties.put("licenseEntryType", licenseEntryType);
		properties.put("licenseVersion", licenseVersion);
		properties.put("macAddresses", StringUtil.merge(macAddresses));
		properties.put("maxHttpSessions", maxHttpSessions);
		properties.put("maxServers", maxServers);
		properties.put("maxConcurrentUsers", maxConcurrentUsers);
		properties.put("maxUsers", maxUsers);
		properties.put("owner", owner);
		properties.put("productEntryName", productEntryName);
		properties.put("productId", productId);
		properties.put("productVersion", productVersion);
		properties.put("serverIds", StringUtil.merge(serverIds));
		properties.put("startDate", longDateFormatDateTime.format(startDate));

		return properties;
	}

	@Override
	protected String getRedirect(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String redirect = null;

		if (Validator.isNull(redirect)) {
			redirect = ParamUtil.getString(
				actionRequest, "assignmentsRedirect");
		}

		if (Validator.isNull(redirect)) {
			redirect = super.getRedirect(actionRequest, actionResponse);
		}

		return redirect;
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			if (RoleLocalServiceUtil.hasUserRole(
					themeDisplay.getUserId(),
					OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {

				return true;
			}

			if (RoleLocalServiceUtil.hasUserRole(
					themeDisplay.getUserId(),
					OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

				return true;
			}

			if (RoleLocalServiceUtil.hasUserRole(
					themeDisplay.getUserId(),
					OSBConstants.ROLE_OSB_SUPPORT_ADMIN_ID)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof AccountEntryCodeException ||
			cause instanceof AccountEntryCorpProjectException ||
			cause instanceof AccountEntryIndustryException ||
			cause instanceof AccountEntryLanguageIdException ||
			cause instanceof AccountEntryMaximumCustomersException ||
			cause instanceof AccountEntryNameException ||
			cause instanceof AccountEntryPartnerEntryException ||
			cause instanceof AccountEntrySupportRegionException ||
			cause instanceof AccountEnvironmentAttachmentSizeException ||
			cause instanceof AccountEnvironmentEnvASException ||
			cause instanceof AccountEnvironmentEnvDBException ||
			cause instanceof AccountEnvironmentEnvLFRException ||
			cause instanceof AccountEnvironmentEnvOSException ||
			cause instanceof AccountEnvironmentNameException ||
			cause instanceof AddressCityException ||
			cause instanceof AddressStreetException ||
			cause instanceof AddressZipException ||
			cause instanceof ContactFirstNameException ||
			cause instanceof ContactLastNameException ||
			cause instanceof DuplicateAccountCustomerException ||
			cause instanceof DuplicateAccountEntryException ||
			cause instanceof DuplicateAccountEnvironmentException ||
			cause instanceof DuplicateOfferingBundleException ||
			cause instanceof DuplicateOfferingDefinitionException ||
			cause instanceof DuplicatePartnerEntryCodeException ||
			cause instanceof DuplicatePartnerEntryDossieraAccountKeyException ||
			cause instanceof DuplicateProductEntryException ||
			cause instanceof DuplicateSupportRegionException ||
			cause instanceof DuplicateSupportResponseException ||
			cause instanceof DuplicateUserEmailAddressException ||
			cause instanceof FileExtensionException ||
			cause instanceof FileNameException ||
			cause instanceof LicenseEntryNameException ||
			cause instanceof LicenseEntryPortalVersionException ||
			cause instanceof NoSuchAccountEntryException ||
			cause instanceof NoSuchListTypeException ||
			cause instanceof NoSuchOfferingDefinitionException ||
			cause instanceof NoSuchProductEntryException ||
			cause instanceof NoSuchSupportResponseException ||
			cause instanceof NoSuchUserException ||
			cause instanceof OfferingBundleNameException ||
			cause instanceof OrderEntryActualStartDateException ||
			cause instanceof PartnerEntryCodeException ||
			cause instanceof PartnerEntryParentPartnerEntryException ||
			cause instanceof PrincipalException ||
			cause instanceof ProductEntryEnvironmentException ||
			cause instanceof ProductEntryNameException ||
			cause instanceof RequiredAccountEntryException ||
			cause instanceof RequiredOfferingDefinitionException ||
			cause instanceof RequiredOfferingEntryException ||
			cause instanceof RequiredPartnerEntryException ||
			cause instanceof RequiredProductEntryException ||
			cause instanceof RequiredSupportRegionException ||
			cause instanceof RequiredSupportResponseException ||
			cause instanceof ReservedUserEmailAddressException ||
			cause instanceof SupportRegionNameException ||
			cause instanceof SupportResponseNameException ||
			cause instanceof SupportResponseSupportLevelException ||
			cause instanceof UserEmailAddressException) {

			return true;
		}
		else if (cause instanceof RemoteServiceException) {
			_log.error(cause, cause);

			return true;
		}
		else {
			return false;
		}
	}

	protected void serveAccountAttachment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException {

		long accountAttachmentId = ParamUtil.getLong(
			resourceRequest, "accountAttachmentId");

		AccountAttachment accountAttachment =
			AccountAttachmentLocalServiceUtil.getAccountAttachment(
				accountAttachmentId);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, accountAttachment.getFileName(),
			AccountAttachmentLocalServiceUtil.getFileAsStream(
				accountAttachment),
			accountAttachment.getContentLength(),
			MimeTypesUtil.getContentType(accountAttachment.getFileName()),
			HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
	}

	protected void serveAccountEntries(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String code = ParamUtil.getString(resourceRequest, "code");
		String name = ParamUtil.getString(resourceRequest, "name");

		List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.search(
			name + StringPool.PERCENT, code + StringPool.PERCENT);

		JSONArray accountEntriesArray = JSONFactoryUtil.createJSONArray();

		for (AccountEntry accountEntry : accountEntries) {
			if (Validator.isNotNull(code)) {
				accountEntriesArray.put(accountEntry.getCode());
			}

			if (Validator.isNotNull(name)) {
				accountEntriesArray.put(accountEntry.getName());
			}
		}

		writeJSON(resourceRequest, resourceResponse, accountEntriesArray);
	}

	protected void serveAccountEnvironmentAttachment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException {

		long accountEnvironmentAttachmentId = ParamUtil.getLong(
			resourceRequest, "accountEnvironmentAttachmentId");

		AccountEnvironmentAttachment accountEnvironmentAttachment =
			AccountEnvironmentAttachmentServiceUtil.
				getAccountEnvironmentAttachment(accountEnvironmentAttachmentId);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse,
			accountEnvironmentAttachment.getFileName(),
			AccountEnvironmentAttachmentLocalServiceUtil.getFileAsStream(
				accountEnvironmentAttachment),
			accountEnvironmentAttachment.getContentLength(),
			MimeTypesUtil.getContentType(
				accountEnvironmentAttachment.getFileName()),
			HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
	}

	protected void syncToLCS(
			ActionRequest actionRequest, ActionResponse actionResponse,
			OrderEntry orderEntry)
		throws IOException {

		try {
			AccountEntry accountEntry = orderEntry.getAccountEntry();

			LCSSubscriptionEntryLocalServiceUtil.syncToLCS(
				accountEntry.getAccountEntryId());
		}
		catch (Exception e) {
			_log.error(e, e);

			SessionMessages.add(actionRequest, "lcsSyncFailed");

			addSuccessMessage(actionRequest, actionResponse);

			sendRedirect(actionRequest, actionResponse);
		}
	}

	protected void updateAccountAttachment(ActionRequest actionRequest)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long accountAttachmentId = ParamUtil.getLong(
			uploadPortletRequest, "accountAttachmentId");

		boolean deleteAccountAttachment = ParamUtil.getBoolean(
			uploadPortletRequest, "deleteAccountAttachment");

		if (deleteAccountAttachment) {
			AccountAttachmentLocalServiceUtil.deleteAccountAttachment(
				accountAttachmentId);
		}

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)uploadPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			long accountEntryId = ParamUtil.getLong(
				uploadPortletRequest, "accountEntryId");
			long accountProjectId = ParamUtil.getLong(
				uploadPortletRequest, "accountProjectId");

			String fileName = uploadPortletRequest.getFileName(
				"accountAttachment");

			File file = uploadPortletRequest.getFile("accountAttachment");

			if ((file == null) || (file.length() <= 0)) {
				return;
			}

			ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
				fileName, file);

			AccountAttachmentLocalServiceUtil.addAccountAttachment(
				themeDisplay.getUserId(), accountEntryId, accountProjectId, ovp,
				AccountAttachmentConstants.TYPE_OEM_INSTRUCTIONS);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(AdminPortlet.class);

}