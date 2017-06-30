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

import com.liferay.compat.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.compat.portal.kernel.servlet.HttpHeaders;
import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.AccountEntryCodeException;
import com.liferay.osb.AccountEntryCorpProjectException;
import com.liferay.osb.AccountEntryIndustryException;
import com.liferay.osb.AccountEntryLanguageIdException;
import com.liferay.osb.AccountEntryMaximumCustomersException;
import com.liferay.osb.AccountEntryNameException;
import com.liferay.osb.AccountEntryPartnerEntryException;
import com.liferay.osb.AccountEntrySupportRegionException;
import com.liferay.osb.AccountEnvironmentAttachmentSizeException;
import com.liferay.osb.AccountEnvironmentEnvASException;
import com.liferay.osb.AccountEnvironmentEnvDBException;
import com.liferay.osb.AccountEnvironmentEnvLFRException;
import com.liferay.osb.AccountEnvironmentEnvOSException;
import com.liferay.osb.AccountEnvironmentNameException;
import com.liferay.osb.AccountWorkerPartnerRoleException;
import com.liferay.osb.CurrencyEntryCodeException;
import com.liferay.osb.CurrencyEntryCountryException;
import com.liferay.osb.DuplicateAccountEntryException;
import com.liferay.osb.DuplicateAccountEnvironmentException;
import com.liferay.osb.DuplicateCurrencyEntryException;
import com.liferay.osb.DuplicateHolidayEntryException;
import com.liferay.osb.DuplicateOfferingBundleException;
import com.liferay.osb.DuplicateOfferingDefinitionException;
import com.liferay.osb.DuplicatePartnerEntryCodeException;
import com.liferay.osb.DuplicatePartnerEntryDossieraAccountKeyException;
import com.liferay.osb.DuplicateProductEntryException;
import com.liferay.osb.DuplicateSupportLaborException;
import com.liferay.osb.DuplicateSupportRegionException;
import com.liferay.osb.DuplicateSupportResponseException;
import com.liferay.osb.DuplicateSupportTeamException;
import com.liferay.osb.DuplicateSupportWorkerException;
import com.liferay.osb.DuplicateTicketCannedResponseException;
import com.liferay.osb.HolidayCalendarNameException;
import com.liferay.osb.HolidayEntryDateException;
import com.liferay.osb.HolidayEntryStartDateLaterThanEndDateException;
import com.liferay.osb.LicenseEntryNameException;
import com.liferay.osb.LicenseEntryPortalVersionException;
import com.liferay.osb.MarketingEventEndDateException;
import com.liferay.osb.MarketingEventHostedByException;
import com.liferay.osb.MarketingEventHostedByURLException;
import com.liferay.osb.MarketingEventRegistrationURLException;
import com.liferay.osb.MarketingEventStartDateException;
import com.liferay.osb.MarketingEventTitleException;
import com.liferay.osb.MarketingEventTitleURLException;
import com.liferay.osb.MarketingEventVideoTitleException;
import com.liferay.osb.NoSuchAccountEntryException;
import com.liferay.osb.NoSuchCurrencyEntryException;
import com.liferay.osb.NoSuchOfferingDefinitionException;
import com.liferay.osb.NoSuchProductEntryException;
import com.liferay.osb.NoSuchSupportResponseException;
import com.liferay.osb.NoSuchTrainingCourseException;
import com.liferay.osb.NoSuchTrainingLocationException;
import com.liferay.osb.OfferingBundleNameException;
import com.liferay.osb.OrderEntryActualStartDateException;
import com.liferay.osb.PartnerEntryCodeException;
import com.liferay.osb.PartnerEntryParentPartnerEntryException;
import com.liferay.osb.PartnerTemplateException;
import com.liferay.osb.ProductEntryEnvironmentException;
import com.liferay.osb.ProductEntryNameException;
import com.liferay.osb.RequiredAccountEntryException;
import com.liferay.osb.RequiredDDLRecordSetDDLRecordException;
import com.liferay.osb.RequiredLicenseEntryException;
import com.liferay.osb.RequiredOfferingDefinitionException;
import com.liferay.osb.RequiredOfferingEntryException;
import com.liferay.osb.RequiredPartnerEntryException;
import com.liferay.osb.RequiredProductEntryException;
import com.liferay.osb.RequiredSupportRegionException;
import com.liferay.osb.RequiredSupportResponseException;
import com.liferay.osb.RequiredSupportTeamException;
import com.liferay.osb.RequiredTrainingCertificateTemplateException;
import com.liferay.osb.RequiredTrainingCourseException;
import com.liferay.osb.RequiredTrainingEventException;
import com.liferay.osb.RequiredTrainingLocationException;
import com.liferay.osb.SupportLaborHourException;
import com.liferay.osb.SupportLaborNameException;
import com.liferay.osb.SupportRegionNameException;
import com.liferay.osb.SupportResponseNameException;
import com.liferay.osb.SupportResponseSupportLevelException;
import com.liferay.osb.SupportTeamContainsNoSupportWorkersException;
import com.liferay.osb.SupportTeamLocationException;
import com.liferay.osb.SupportTeamNameException;
import com.liferay.osb.SupportTeamSupportLaborException;
import com.liferay.osb.SupportWorkerMaxWorkException;
import com.liferay.osb.TicketCannedResponseContentException;
import com.liferay.osb.TicketCannedResponseNameException;
import com.liferay.osb.TrainingCertificateTemplateNameException;
import com.liferay.osb.TrainingCourseNameException;
import com.liferay.osb.TrainingCustomerCertificateException;
import com.liferay.osb.TrainingCustomerStatusException;
import com.liferay.osb.TrainingLocationNameException;
import com.liferay.osb.UserProfileFirstNameException;
import com.liferay.osb.UserProfileHistoryFirstNameException;
import com.liferay.osb.UserProfileHistoryLastNameException;
import com.liferay.osb.UserProfileLastNameException;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.admin.util.KeyGenerator;
import com.liferay.osb.model.AccountAttachment;
import com.liferay.osb.model.AccountAttachmentConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.model.AccountEnvironmentAttachmentConstants;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.HolidayEntry;
import com.liferay.osb.model.MarketingEvent;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.TicketSolutionConstants;
import com.liferay.osb.model.TrainingCertificate;
import com.liferay.osb.model.TrainingCertificateTemplate;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.TrainingLocation;
import com.liferay.osb.model.UserProfile;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.model.impl.OfferingEntryImpl;
import com.liferay.osb.rabbitmq.RabbitMQConsumerRouter;
import com.liferay.osb.service.AccountAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerServiceUtil;
import com.liferay.osb.service.ContractEntryServiceUtil;
import com.liferay.osb.service.CurrencyEntryLocalServiceUtil;
import com.liferay.osb.service.HolidayCalendarLocalServiceUtil;
import com.liferay.osb.service.HolidayCalendarRelLocalServiceUtil;
import com.liferay.osb.service.HolidayEntryLocalServiceUtil;
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;
import com.liferay.osb.service.MarketingEventLocalServiceUtil;
import com.liferay.osb.service.OSBCountryServiceUtil;
import com.liferay.osb.service.OSBRegionServiceUtil;
import com.liferay.osb.service.OfferingBundleLocalServiceUtil;
import com.liferay.osb.service.OfferingDefinitionLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.SupportLaborLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.osb.service.SupportTeamLanguageLocalServiceUtil;
import com.liferay.osb.service.SupportTeamLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerAccountTierLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerComponentLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerServiceUtil;
import com.liferay.osb.service.SupportWorkerSeverityLocalServiceUtil;
import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;
import com.liferay.osb.service.TicketCannedResponseLocalServiceUtil;
import com.liferay.osb.service.TrainingCertificateLocalServiceUtil;
import com.liferay.osb.service.TrainingCertificateTemplateLocalServiceUtil;
import com.liferay.osb.service.TrainingCourseLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;
import com.liferay.osb.service.TrainingExamLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultLocalServiceUtil;
import com.liferay.osb.service.TrainingLinkedUserLocalServiceUtil;
import com.liferay.osb.service.TrainingLocationLocalServiceUtil;
import com.liferay.osb.service.TrainingWorkerLocalServiceUtil;
import com.liferay.osb.service.UserProfileHistoryLocalServiceUtil;
import com.liferay.osb.service.UserProfileLocalServiceUtil;
import com.liferay.osb.training.exporter.TrainingSurveyResultsReportUtil;
import com.liferay.osb.training.importer.KryterionTrainingExamResultsImporterImpl;
import com.liferay.osb.training.importer.PrometricTrainingExamResultsImporterImpl;
import com.liferay.osb.training.importer.TrainingExamResultsImporter;
import com.liferay.osb.training.importer.TrainingExamResultsImporterFactoryUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.OSBRequestUtil;
import com.liferay.osb.util.OSBWebKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.AddressCityException;
import com.liferay.portal.AddressStreetException;
import com.liferay.portal.AddressZipException;
import com.liferay.portal.ContactFirstNameException;
import com.liferay.portal.ContactLastNameException;
import com.liferay.portal.CountryA2Exception;
import com.liferay.portal.CountryA3Exception;
import com.liferay.portal.CountryIddException;
import com.liferay.portal.CountryNameException;
import com.liferay.portal.CountryNumberException;
import com.liferay.portal.DuplicateUserEmailAddressException;
import com.liferay.portal.NoSuchListTypeException;
import com.liferay.portal.RegionCodeException;
import com.liferay.portal.RegionNameException;
import com.liferay.portal.ReservedUserEmailAddressException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouterUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.Base64InputStream;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.CSVUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.RegionServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.documentlibrary.FileExtensionException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.portlet.dynamicdatalists.RecordSetDDMStructureIdException;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatalists.util.DDLUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.storage.StorageEngineUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.usersadmin.util.UsersAdminUtil;
import com.liferay.rabbitmq.consumer.RabbitMQConsumer;

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

	public void addContractEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		int type = ParamUtil.getInteger(actionRequest, "type");
		Map<Locale, String> contentMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "content");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		ContractEntryServiceUtil.addContractEntry(
			ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
			ContractEntryConstants.DEFAULT_CLASS_PK, type, contentMap,
			serviceContext);
	}

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

	public void clockInOut(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long supportWorkerId = ParamUtil.getLong(
			actionRequest, "supportWorkerId");

		SupportWorkerServiceUtil.clockInOut(supportWorkerId);
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

				objectInputStream = new ObjectInputStream(
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
			message, StringPool.NEW_LINE, StringPool.BLANK);

		Map<String, Object> propertiesMap = MapUtil.toLinkedHashMap(properties);

		RabbitMQConsumer rabbitMQConsumer = new RabbitMQConsumerRouter();

		rabbitMQConsumer.parse(routingKey, message, propertiesMap);
	}

	public void deleteAccountEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		AccountEntryLocalServiceUtil.deleteAccountEntry(accountEntryId);
	}

	public void deleteCountry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long countryId = ParamUtil.getLong(actionRequest, "countryId");

		OSBCountryServiceUtil.deleteCountry(countryId);
	}

	public void deleteCurrencyEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long currencyEntryId = ParamUtil.getLong(
			actionRequest, "currencyEntryId");

		CurrencyEntryLocalServiceUtil.deleteCurrencyEntry(currencyEntryId);
	}

	public void deleteHolidayCalendar(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long holidayCalendarId = ParamUtil.getLong(
			actionRequest, "holidayCalendarId");

		HolidayCalendarLocalServiceUtil.deleteHolidayCalendar(
			holidayCalendarId);
	}

	public void deleteLicenseEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long licenseEntryId = ParamUtil.getLong(
			actionRequest, "licenseEntryId");

		LicenseEntryLocalServiceUtil.deleteLicenseEntry(licenseEntryId);
	}

	public void deleteMarketingEvent(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long marketingEventId = ParamUtil.getLong(
			actionRequest, "marketingEventId");

		MarketingEventLocalServiceUtil.deleteMarketingEvent(marketingEventId);
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

	public void deleteRegion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long regionId = ParamUtil.getLong(actionRequest, "regionId");

		OSBRegionServiceUtil.deleteRegion(regionId);
	}

	public void deleteSupportLabor(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long supportLaborId = ParamUtil.getLong(
			actionRequest, "supportLaborId");

		SupportLaborLocalServiceUtil.deleteSupportLabor(supportLaborId);
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

	public void deleteSupportTeam(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long supportTeamId = ParamUtil.getLong(actionRequest, "supportTeamId");

		SupportTeamLocalServiceUtil.deleteSupportTeam(supportTeamId);
	}

	public void deleteTicketCannedResponse(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ticketCannedResponseId = ParamUtil.getLong(
			actionRequest, "ticketCannedResponseId");

		TicketCannedResponseLocalServiceUtil.deleteTicketCannedResponse(
			ticketCannedResponseId);
	}

	public void deleteTrainingCertificateTemplate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingCertificateTemplateId = ParamUtil.getLong(
			actionRequest, "trainingCertificateTemplateId");

		TrainingCertificateTemplateLocalServiceUtil.
			deleteTrainingCertificateTemplate(trainingCertificateTemplateId);
	}

	public void deleteTrainingCourse(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingCourseId = ParamUtil.getLong(
			actionRequest, "trainingCourseId");

		TrainingCourseLocalServiceUtil.deleteTrainingCourse(trainingCourseId);
	}

	public void deleteTrainingCustomer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingCustomerId = ParamUtil.getLong(
			actionRequest, "trainingCustomerId");

		TrainingCustomerLocalServiceUtil.deleteTrainingCustomer(
			trainingCustomerId);
	}

	public void deleteTrainingCustomers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingUserId = ParamUtil.getLong(
			actionRequest, "trainingUserId");

		TrainingCustomerLocalServiceUtil.deleteTrainingCustomers(
			trainingUserId);
	}

	public void deleteTrainingEvent(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingEventId = ParamUtil.getLong(
			actionRequest, "trainingEventId");

		TrainingEventLocalServiceUtil.deleteTrainingEvent(trainingEventId);
	}

	public void deleteTrainingExamResult(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingExamResultId = ParamUtil.getLong(
			actionRequest, "trainingExamResultId");

		TrainingExamResultLocalServiceUtil.deleteTrainingExamResult(
			trainingExamResultId);
	}

	public void deleteTrainingLocation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingLocationId = ParamUtil.getLong(
			actionRequest, "trainingLocationId");

		TrainingLocationLocalServiceUtil.deleteTrainingLocation(
			trainingLocationId);
	}

	public void deleteTrainingSurvey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ddlRecordSetId = ParamUtil.getLong(
			actionRequest, "ddlRecordSetId");

		DDLRecordSetLocalServiceUtil.deleteRecordSet(ddlRecordSetId);
	}

	public void importTrainingCustomers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			File file = uploadPortletRequest.getFile("file");

			if (file == null) {
				return;
			}

			Document document = SAXReaderUtil.read(new FileReader(file));

			Element rootElement = document.getRootElement();

			for (Element trainingCustomerElement : rootElement.elements()) {
				updateTrainingCustomer(
					uploadPortletRequest, trainingCustomerElement);
			}
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void importTrainingSurveys(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			File file = uploadPortletRequest.getFile("file");

			if (file == null) {
				return;
			}

			String extension = FileUtil.getExtension(file.getName());

			if (!extension.equals("xml")) {
				throw new FileExtensionException();
			}

			Document document = SAXReaderUtil.read(new FileReader(file));

			Element rootElement = document.getRootElement();

			List<Element> invoiceRetElements = rootElement.elements(
				"SurveyResult");

			for (Element invoiceRetElement : invoiceRetElements) {
				try {
					updateTrainingSurvey(
						uploadPortletRequest, invoiceRetElement);
				}
				catch (Exception e) {
				}
			}
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void migrateTicketAttachments(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String[] attachmentsDirs = DLStoreUtil.getFileNames(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
			OSBConstants.ATTACHMENTS_DIR_SUPPORT);

		for (String attachmentsDir : attachmentsDirs) {
			long ticketEntryId = GetterUtil.getLong(
				FileUtil.getShortFileName(attachmentsDir));

			List<ObjectValuePair<String, File>> files =
				new ArrayList<ObjectValuePair<String, File>>();

			String[] ticketAttachments = DLStoreUtil.getFileNames(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
				attachmentsDir);

			for (String ticketAttachment : ticketAttachments) {
				String fileName = FileUtil.getShortFileName(ticketAttachment);

				File file = DLStoreUtil.getFile(
					OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
					ticketAttachment);

				ObjectValuePair<String, File> ovp =
					new ObjectValuePair<String, File>(fileName, file);

				files.add(ovp);
			}

			try {
				TicketAttachmentLocalServiceUtil.addTicketAttachments(
					OSBConstants.USER_DEFAULT_USER_ID, ticketEntryId,
					TicketSolutionConstants.DEFAULT_SOLUTION_ID, files, null,
					VisibilityConstants.PUBLIC,
					WorkflowConstants.STATUS_APPROVED, new ServiceContext());
			}
			catch (Exception e) {
			}
		}
	}

	public void recalculateUtilization(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		SupportWorkerLocalServiceUtil.recalculateUtilization();
	}

	public void reindex(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletId = ParamUtil.getString(actionRequest, "portletId");
		long modifiedDateOffset = ParamUtil.getLong(
			actionRequest, "modifiedDateOffset");

		AdminUtil.reindex(portletId, modifiedDateOffset);

		User user = themeDisplay.getUser();

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), portletId);

		AuditMessage auditMessage = new AuditMessage(
			"REINDEX", themeDisplay.getCompanyId(), user.getUserId(),
			user.getFullName(), Portlet.class.getName(),
			portlet.getDisplayName(), PortalUtil.getComputerName());

		AuditRouterUtil.route(auditMessage);
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

	public void sendTrainingCertificate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingCustomerId = ParamUtil.getLong(
			actionRequest, "trainingCustomerId");

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.getTrainingCustomer(
				trainingCustomerId);

		sendTrainingCertificate(trainingCustomer);
	}

	public void sendTrainingCertificates(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		List<TrainingCustomer> trainingCustomers =
			new ArrayList<TrainingCustomer>();

		long classPK = ParamUtil.getLong(actionRequest, "classPK");
		long classNameId = ParamUtil.getLong(actionRequest, "classNameId");

		if (classNameId == PortalUtil.getClassNameId(TrainingEvent.class)) {
			TrainingEvent trainingEvent =
				TrainingEventLocalServiceUtil.getTrainingEvent(classPK);

			trainingCustomers.addAll(trainingEvent.getTrainingCustomers());
		}
		else if (classNameId ==
					PortalUtil.getClassNameId(TrainingExamResult.class)) {

			TrainingExamResult trainingExamResult =
				TrainingExamResultLocalServiceUtil.getTrainingExamResult(
					classPK);

			trainingCustomers.add(trainingExamResult.getTrainingCustomer());
		}

		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			if (!TrainingCertificateLocalServiceUtil.
					hasTrainingCertificateCertifiedDate(
						trainingCustomer.getTrainingCustomerId())) {

				continue;
			}

			sendTrainingCertificate(trainingCustomer);
		}
	}

	public void sendTrainingSurvey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingCustomerId = ParamUtil.getLong(
			actionRequest, "trainingCustomerId");

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.getTrainingCustomer(
				trainingCustomerId);

		long trainingSurveyPlid = PortalUtil.getPlidFromPortletId(
			OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_TRAINING_SURVEY);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			PortalUtil.getHttpServletRequest(actionRequest),
			OSBPortletKeys.OSB_TRAINING_SURVEY, trainingSurveyPlid,
			PortletRequest.RENDER_PHASE);

		String className = trainingCustomer.getClassName();

		if (className.equals(TrainingEvent.class.getName())) {
			TrainingEvent trainingEvent =
				TrainingEventLocalServiceUtil.getTrainingEvent(
					trainingCustomer.getClassPK());

			portletURL.setParameter(
				"ddlRecordSetId",
				String.valueOf(trainingEvent.getDDLRecordSetId()));
		}

		sendTrainingSurvey(trainingCustomer, portletURL.toString());
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
			else if (resourceID.equals("exportAllSurveyResults")) {
				File file =
					TrainingSurveyResultsReportUtil.getAllSurveyResultsXLS(
						resourceRequest, resourceResponse);

				String contentType = MimeTypesUtil.getContentType(
					file.getName());

				PortletResponseUtil.sendFile(
					resourceRequest, resourceResponse, "all_survey_results.xls",
						new FileInputStream(file), contentType);
			}
			else if (resourceID.equals("exportSurveyResults")) {
				long ddlRecordSetId = ParamUtil.getLong(
					resourceRequest, "ddlRecordSetId");

				DDLRecordSet ddlRecordSet =
					DDLRecordSetLocalServiceUtil.getDDLRecordSet(
						ddlRecordSetId);

				TrainingEvent trainingEvent =
					TrainingEventLocalServiceUtil.
						getTrainingEventByDDLRecordSetId(ddlRecordSetId);

				Locale locale = LocaleUtil.fromLanguageId(
					trainingEvent.getLanguageId(), false);

				sendCsvFile(
					resourceRequest, resourceResponse,
					ddlRecordSet.getName(locale),
					getSurveyResultsCSV(ddlRecordSetId));
			}
			else if (resourceID.equals("generateCertificate")) {
				long trainingCustomerId = ParamUtil.getLong(
					resourceRequest, "trainingCustomerId");

				TrainingCertificateLocalServiceUtil.getTrainingCertificate(
					resourceResponse, trainingCustomerId);
			}
			else if (resourceID.equals("generateCertificateTemplate")) {
				long trainingCertificateTemplateId = ParamUtil.getLong(
					resourceRequest, "trainingCertificateTemplateId");

				if (trainingCertificateTemplateId > 0) {
					serveTrainingCertificateTemplate(
						resourceRequest, resourceResponse,
						trainingCertificateTemplateId);
				}
			}
			else if (resourceID.equals("getAddress")) {
				long trainingLocationId = ParamUtil.getLong(
					resourceRequest, "trainingLocationId");

				TrainingLocation trainingLocation =
					TrainingLocationLocalServiceUtil.getTrainingLocation(
						trainingLocationId);

				Address address = AddressLocalServiceUtil.getAddress(
					trainingLocation.getAddressId());

				JSONObject addressJSONObject = JSONFactoryUtil.createJSONObject(
					JSONFactoryUtil.looseSerialize(address));

				List<Region> regions = RegionServiceUtil.getRegions(
					address.getCountryId());

				addressJSONObject.put(
					"regions", JSONFactoryUtil.looseSerialize(regions));

				writeJSON(resourceRequest, resourceResponse, addressJSONObject);
			}
			else if (resourceID.equals("serveBadge")) {
				long trainingCertificateTemplateId = ParamUtil.getLong(
					resourceRequest, "trainingCertificateTemplateId");

				OSBRequestUtil.serveTrainingCertificateTemplateBadge(
					resourceRequest, resourceResponse,
					trainingCertificateTemplateId);
			}
			else if (resourceID.equals("ticketEnvironment")) {
				JSONObject jsonObject = OSBRequestUtil.getTicketEnvironment(
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
					accountEntry.getCorpProjectId());
			}
		}
		catch (Exception e) {
			_log.error("Unable to sync to LCS", e);

			SessionMessages.add(actionRequest, "lcsSyncFailed");

			addSuccessMessage(actionRequest, actionResponse);

			sendRedirect(actionRequest, actionResponse);
		}
	}

	public void updateAccountCustomers(
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

		AccountCustomerLocalServiceUtil.addAccountCustomers(
			themeDisplay.getUserId(), addUserIds, accountEntryId, roles,
			notifications);
		AccountCustomerLocalServiceUtil.deleteAccountCustomers(
			themeDisplay.getUserId(), removeUserIds, accountEntryId);
	}

	public void updateAccountEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		long corpProjectId = ParamUtil.getLong(actionRequest, "corpProjectId");
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
				themeDisplay.getUserId(), corpProjectId, StringPool.BLANK, name,
				code, type, industry, partnerEntryId, partnerManagedSupport,
				tier, maxCustomers, instructions, notes, languageIds,
				supportRegionIds, street1, street2, street3, city, zip,
				regionId, countryId, ewsaDossieraProjectKey);
		}
		else {
			AccountEntry accountEntry =
				AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

			AccountEntryLocalServiceUtil.updateAccountEntry(
				themeDisplay.getUserId(), accountEntryId, corpProjectId,
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

		List<ObjectValuePair<String, File>> files =
			new ArrayList<ObjectValuePair<String, File>>();

		List<Integer> types = new ArrayList<Integer>();

		String[] uploadFileNames = new String[] {"patch-level", "portal-ext"};

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

				ObjectValuePair<String, File> ovp =
					new ObjectValuePair<String, File>(fileName, file);

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

				Map<String, String> refreshPortletData =
					new HashMap<String, String>();

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

		AccountWorkerServiceUtil.addAccountWorkers(
			addUserIds, accountEntryId, roles, notifications);
		AccountWorkerServiceUtil.deleteAccountWorkers(
			removeUserIds, accountEntryId);
	}

	public void updateCountry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long countryId = ParamUtil.getLong(actionRequest, "countryId");

		String name = ParamUtil.getString(actionRequest, "name");
		String a2 = ParamUtil.getString(actionRequest, "a2");
		String a3 = ParamUtil.getString(actionRequest, "a3");
		String number = ParamUtil.getString(actionRequest, "number");
		String idd = ParamUtil.getString(actionRequest, "idd");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		if (countryId <= 0) {
			OSBCountryServiceUtil.addCountry(name, a2, a3, number, idd, active);
		}
		else {
			OSBCountryServiceUtil.updateCountry(
				countryId, name, a2, a3, number, idd, active);
		}
	}

	public void updateCurrencyEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long currencyEntryId = ParamUtil.getLong(
			actionRequest, "currencyEntryId");

		long countryId = ParamUtil.getLong(actionRequest, "countryId");
		String currencyCode = ParamUtil.getString(
			actionRequest, "currencyCode");
		boolean marketplaceEnabled = ParamUtil.getBoolean(
			actionRequest, "marketplaceEnabled");
		double marketplaceMinPrice = ParamUtil.getDouble(
			actionRequest, "marketplaceMinPrice");

		if (currencyEntryId <= 0) {
			CurrencyEntryLocalServiceUtil.addCurrencyEntry(
				countryId, currencyCode, marketplaceEnabled,
				marketplaceMinPrice);
		}
		else {
			CurrencyEntryLocalServiceUtil.updateCurrencyEntry(
				currencyEntryId, countryId, currencyCode, marketplaceEnabled,
				marketplaceMinPrice);
		}
	}

	public void updateHolidayCalendar(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long holidayCalendarId = ParamUtil.getLong(
			actionRequest, "holidayCalendarId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");

		int[] holidayEntriesIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "holidayEntriesIndexes"), 0);

		List<HolidayEntry> holidayEntries = new ArrayList<HolidayEntry>();

		for (int holidayEntriesIndex : holidayEntriesIndexes) {
			long holidayEntryId = ParamUtil.getLong(
				actionRequest, "holidayEntryId" + holidayEntriesIndex);

			String holidayEntryName = ParamUtil.getString(
				actionRequest, "holidayEntryName" + holidayEntriesIndex);
			String holidayEntryDescription = ParamUtil.getString(
				actionRequest, "holidayEntryDescription" + holidayEntriesIndex);

			int holidayEntryStartDay = ParamUtil.getInteger(
				actionRequest, "holidayEntryStartDay" + holidayEntriesIndex);
			int holidayEntryStartMonth = ParamUtil.getInteger(
				actionRequest, "holidayEntryStartMonth" + holidayEntriesIndex);
			int holidayEntryStartYear = ParamUtil.getInteger(
				actionRequest, "holidayEntryStartYear" + holidayEntriesIndex);
			int holidayEntryEndDay = ParamUtil.getInteger(
				actionRequest, "holidayEntryEndDay" + holidayEntriesIndex);
			int holidayEntryEndMonth = ParamUtil.getInteger(
				actionRequest, "holidayEntryEndMonth" + holidayEntriesIndex);
			int holidayEntryEndYear = ParamUtil.getInteger(
				actionRequest, "holidayEntryEndYear" + holidayEntriesIndex);

			boolean repeatYearly = ParamUtil.getBoolean(
				actionRequest,
				"holidayEntryRepeatYearly" + holidayEntriesIndex);

			if ((holidayEntryStartDay <= 0) ||
				(holidayEntryStartMonth < 0) ||
				(holidayEntryStartYear <= 0) ||
				(holidayEntryEndDay <= 0) ||
				(holidayEntryEndMonth < 0) ||
				(holidayEntryEndYear <= 0)) {

				continue;
			}

			Calendar holidayEntryStartCal = Calendar.getInstance();

			holidayEntryStartCal.set(Calendar.YEAR, holidayEntryStartYear);
			holidayEntryStartCal.set(Calendar.MONTH, holidayEntryStartMonth);
			holidayEntryStartCal.set(Calendar.DATE, holidayEntryStartDay);
			holidayEntryStartCal.set(Calendar.HOUR, 0);
			holidayEntryStartCal.set(Calendar.MINUTE, 0);
			holidayEntryStartCal.set(Calendar.SECOND, 0);

			Calendar holidayEntryEndCal = Calendar.getInstance();

			holidayEntryEndCal.set(Calendar.YEAR, holidayEntryEndYear);
			holidayEntryEndCal.set(Calendar.MONTH, holidayEntryEndMonth);
			holidayEntryEndCal.set(Calendar.DATE, holidayEntryEndDay);
			holidayEntryEndCal.set(Calendar.HOUR, 23);
			holidayEntryEndCal.set(Calendar.MINUTE, 59);
			holidayEntryEndCal.set(Calendar.SECOND, 59);

			if (holidayEntryStartCal.after(holidayEntryEndCal)) {
				throw new HolidayEntryStartDateLaterThanEndDateException();
			}

			HolidayEntry holidayEntry =
				HolidayEntryLocalServiceUtil.createHolidayEntry(holidayEntryId);

			holidayEntry.setHolidayCalendarId(holidayCalendarId);
			holidayEntry.setName(holidayEntryName);
			holidayEntry.setDescription(holidayEntryDescription);
			holidayEntry.setStartDate(holidayEntryStartCal.getTime());
			holidayEntry.setEndDate(holidayEntryEndCal.getTime());
			holidayEntry.setRepeatYearly(repeatYearly);

			holidayEntries.add(holidayEntry);
		}

		if (holidayCalendarId > 0) {
			HolidayCalendarLocalServiceUtil.updateHolidayCalendar(
				holidayCalendarId, name, description, holidayEntries);
		}
		else {
			HolidayCalendarLocalServiceUtil.addHolidayCalendar(
				name, description, holidayEntries);
		}
	}

	public void updateHolidayCalendarUsers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		long holidayCalendarId = ParamUtil.getLong(
			actionRequest, "holidayCalendarId");

		HolidayCalendarRelLocalServiceUtil.addUsers(
			holidayCalendarId, addUserIds);
		HolidayCalendarRelLocalServiceUtil.deleteHolidayCalendarRels(
			holidayCalendarId, removeUserIds);
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

	public void updateMarketingEvent(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long marketingEventId = ParamUtil.getLong(
			actionRequest, "marketingEventId");

		int type = ParamUtil.getInteger(actionRequest, "type");
		String defaultLanguageId = ParamUtil.getString(
			actionRequest, "defaultLanguageId");
		Map<Locale, String> titleMap =
			AdminUtil.getMarketingEventLocalizationMap(actionRequest, "title");
		String titleURL = ParamUtil.getString(actionRequest, "titleURL");
		String hostedBy = ParamUtil.getString(actionRequest, "hostedBy");
		String hostedByURL = ParamUtil.getString(actionRequest, "hostedByURL");
		Map<Locale, String> summaryMap =
			AdminUtil.getMarketingEventLocalizationMap(
				actionRequest, "summary");
		long imageFileEntryId = ParamUtil.getLong(
			actionRequest, "imageFileEntryId");
		long slidesFileEntryId = ParamUtil.getLong(
			actionRequest, "slidesFileEntryId");
		String videoTitle = ParamUtil.getString(actionRequest, "videoTitle");
		String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");
		boolean dateTBA = ParamUtil.getBoolean(actionRequest, "dateTBA");
		int globalRegion = ParamUtil.getInteger(actionRequest, "globalRegion");
		boolean online = ParamUtil.getBoolean(actionRequest, "online");
		int registrationType = ParamUtil.getInteger(
			actionRequest, "registrationType");
		String registrationURL = ParamUtil.getString(
			actionRequest, "registrationURL");

		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");
		int startDateHour = ParamUtil.getInteger(
			actionRequest, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(
			actionRequest, "startDateMinute");
		int startDateAmPm = ParamUtil.getInteger(
			actionRequest, "startDateAmPm");

		if (startDateAmPm == Calendar.PM) {
			startDateHour += 12;
		}

		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");
		int endDateHour = ParamUtil.getInteger(actionRequest, "endDateHour");
		int endDateMinute = ParamUtil.getInteger(
			actionRequest, "endDateMinute");
		int endDateAmPm = ParamUtil.getInteger(actionRequest, "endDateAmPm");

		if (endDateAmPm == Calendar.PM) {
			endDateHour += 12;
		}

		String addressStreet1 = ParamUtil.getString(
			actionRequest, "addressStreet1");
		String addressStreet2 = ParamUtil.getString(
			actionRequest, "addressStreet2");
		String addressStreet3 = ParamUtil.getString(
			actionRequest, "addressStreet3");
		String addressCity = ParamUtil.getString(actionRequest, "addressCity");
		String addressZip = ParamUtil.getString(actionRequest, "addressZip");
		long addressRegionId = ParamUtil.getLong(
			actionRequest, "addressRegionId");
		long addressCountryId = ParamUtil.getLong(
			actionRequest, "addressCountryId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			MarketingEvent.class.getName(), actionRequest);

		if (marketingEventId <= 0) {
			MarketingEventLocalServiceUtil.addMarketingEvent(
				themeDisplay.getUserId(), type, defaultLanguageId, titleMap,
				titleURL, hostedBy, hostedByURL, summaryMap, imageFileEntryId,
				slidesFileEntryId, videoTitle, timeZoneId, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, dateTBA, addressStreet1, addressStreet2,
				addressStreet3, addressCity, addressZip, addressRegionId,
				addressCountryId, globalRegion, online, registrationType,
				registrationURL, serviceContext);
		}
		else {
			MarketingEventLocalServiceUtil.updateMarketingEvent(
				marketingEventId, type, defaultLanguageId, titleMap, titleURL,
				hostedBy, hostedByURL, summaryMap, imageFileEntryId,
				slidesFileEntryId, videoTitle, timeZoneId, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, dateTBA, addressStreet1, addressStreet2,
				addressStreet3, addressCity, addressZip, addressRegionId,
				addressCountryId, globalRegion, online, registrationType,
				registrationURL, serviceContext);
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

		List<OfferingEntry> offeringEntries = new ArrayList<OfferingEntry>();

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

	public void updateRegion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long regionId = ParamUtil.getLong(actionRequest, "regionId");

		long countryId = ParamUtil.getLong(actionRequest, "countryId");
		String regionCode = ParamUtil.getString(actionRequest, "regionCode");
		String name = ParamUtil.getString(actionRequest, "name");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		if (regionId <= 0) {
			OSBRegionServiceUtil.addRegion(countryId, regionCode, name, active);
		}
		else {
			OSBRegionServiceUtil.updateRegion(
				regionId, countryId, regionCode, name, active);
		}
	}

	public void updateSupportLabor(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long supportLaborId = ParamUtil.getLong(
			actionRequest, "supportLaborId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");

		int sunOpen = ParamUtil.getInteger(actionRequest, "sunOpen");
		int sunClose = ParamUtil.getInteger(actionRequest, "sunClose");
		int monOpen = ParamUtil.getInteger(actionRequest, "monOpen");
		int monClose = ParamUtil.getInteger(actionRequest, "monClose");
		int tueOpen = ParamUtil.getInteger(actionRequest, "tueOpen");
		int tueClose = ParamUtil.getInteger(actionRequest, "tueClose");
		int wedOpen = ParamUtil.getInteger(actionRequest, "wedOpen");
		int wedClose = ParamUtil.getInteger(actionRequest, "wedClose");
		int thuOpen = ParamUtil.getInteger(actionRequest, "thuOpen");
		int thuClose = ParamUtil.getInteger(actionRequest, "thuClose");
		int friOpen = ParamUtil.getInteger(actionRequest, "friOpen");
		int friClose = ParamUtil.getInteger(actionRequest, "friClose");
		int satOpen = ParamUtil.getInteger(actionRequest, "satOpen");
		int satClose = ParamUtil.getInteger(actionRequest, "satClose");

		if (supportLaborId > 0) {
			long[] supportTeamIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "supportTeamIds"), 0L);

			SupportTeamLocalServiceUtil.setSupportLaborId(
				supportLaborId, supportTeamIds);

			SupportLaborLocalServiceUtil.updateSupportLabor(
				supportLaborId, name, description, timeZoneId, sunOpen,
				sunClose, monOpen, monClose, tueOpen, tueClose, wedOpen,
				wedClose, thuOpen, thuClose, friOpen, friClose, satOpen,
				satClose);
		}
		else {
			SupportLaborLocalServiceUtil.addSupportLabor(
				name, description, timeZoneId, sunOpen, sunClose, monOpen,
				monClose, tueOpen, tueClose, wedOpen, wedClose, thuOpen,
				thuClose, friOpen, friClose, satOpen, satClose);
		}
	}

	public void updateSupportLaborWorkers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] addSupportWorkerIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addSupportWorkerIds"), 0L);
		long[] removeSupportWorkerIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeSupportWorkerIds"), 0L);

		long supportLaborId = ParamUtil.getLong(
			actionRequest, "supportLaborId");

		SupportLaborLocalServiceUtil.addSupportWorkers(
			addSupportWorkerIds, supportLaborId);
		SupportLaborLocalServiceUtil.removeSupportWorkers(
			removeSupportWorkerIds);
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
			long[] supportTeamIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "supportTeamIds"), 0L);

			SupportRegionLocalServiceUtil.updateSupportRegion(
				supportRegionId, name, description, timeZoneId, supportTeamIds);
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

	public void updateSupportTeam(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long supportTeamId = ParamUtil.getLong(actionRequest, "supportTeamId");

		long parentSupportTeamId = ParamUtil.getLong(
			actionRequest, "parentSupportTeamId");
		long supportLaborId = ParamUtil.getLong(
			actionRequest, "supportLaborId");
		long locationSupportRegionId = ParamUtil.getLong(
			actionRequest, "locationSupportRegionId");
		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		int type = ParamUtil.getInteger(actionRequest, "type");

		if (supportTeamId > 0) {
			long[] accountEntryIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "accountEntryIds"), 0L);
			long[] supportRegionIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "supportRegionIds"), 0L);

			SupportTeamLocalServiceUtil.updateSupportTeam(
				supportTeamId, parentSupportTeamId, supportLaborId,
				locationSupportRegionId, name, description, type,
				accountEntryIds, supportRegionIds);

			long[] childSupportTeamIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "childSupportTeamIds"), 0L);
			String[] languageIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "languageIds"));

			SupportTeamLocalServiceUtil.setChildSupportTeams(
				supportTeamId, childSupportTeamIds);
			SupportTeamLanguageLocalServiceUtil.setSupportTeamLanguageIds(
				supportTeamId, languageIds);
		}
		else {
			SupportTeamLocalServiceUtil.addSupportTeam(
				themeDisplay.getUserId(), parentSupportTeamId, supportLaborId,
				locationSupportRegionId, name, description, type);
		}
	}

	public void updateSupportWorker(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long supportWorkerId = ParamUtil.getLong(
			actionRequest, "supportWorkerId");

		long supportTeamId = ParamUtil.getLong(actionRequest, "supportTeamId");
		boolean autoAssign = ParamUtil.getBoolean(actionRequest, "autoAssign");
		double maxWork = ParamUtil.getDouble(actionRequest, "maxWork");
		int escalationLevel = ParamUtil.getInteger(
			actionRequest, "escalationLevel");
		int escalationLevel2Role = ParamUtil.getInteger(
			actionRequest, "escalationLevel2Role",
			SupportWorkerConstants.ESCALATION_LEVEL_2_ROLE_PRIMARY);
		int notifications = ParamUtil.getInteger(
			actionRequest, "notifications");

		int[] severities = StringUtil.split(
			ParamUtil.getString(actionRequest, "severities"), 0);
		int[] components = StringUtil.split(
			ParamUtil.getString(actionRequest, "components"), 0);
		int[] accountTiers = StringUtil.split(
			ParamUtil.getString(actionRequest, "accountTiers"), 0);

		SupportWorkerLocalServiceUtil.updateSupportWorker(
			supportWorkerId, supportTeamId, autoAssign, maxWork,
			escalationLevel, escalationLevel2Role, notifications);

		SupportWorkerSeverityLocalServiceUtil.setSupportWorkerSeverities(
			supportWorkerId, severities);
		SupportWorkerComponentLocalServiceUtil.setSupportWorkerComponents(
			supportWorkerId, components);
		SupportWorkerAccountTierLocalServiceUtil.setSupportWorkerAccountTiers(
			supportWorkerId, accountTiers);
	}

	public void updateSupportWorkers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		long supportTeamId = ParamUtil.getLong(actionRequest, "supportTeamId");

		double[] maxWork = new double[addUserIds.length];
		int[] escalationLevels = new int[addUserIds.length];
		int[] roles = new int[addUserIds.length];
		int[] notifications = new int[addUserIds.length];

		for (int i = 0; i < addUserIds.length; i++) {
			long userId = addUserIds[i];

			escalationLevels[i] = ParamUtil.getInteger(
				actionRequest, "escalationLevel_" + userId);
			maxWork[i] = ParamUtil.getDouble(
				actionRequest, "maxWork_" + userId);
			roles[i] = ParamUtil.getInteger(actionRequest, "role_" + userId);
			notifications[i] = ParamUtil.getInteger(
				actionRequest, "notifications_" + userId);
		}

		SupportWorkerLocalServiceUtil.addSupportWorkers(
			addUserIds, supportTeamId, maxWork, escalationLevels, roles,
			notifications);
		SupportWorkerLocalServiceUtil.deleteSupportWorkers(
			removeUserIds, supportTeamId);
	}

	public void updateTicketCannedResponse(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (Validator.isNull(cmd)) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ticketCannedResponseId = ParamUtil.getLong(
			actionRequest, "ticketCannedResponseId");

		String defaultLanguageId = ParamUtil.getString(
			actionRequest, "defaultLanguageId");
		String languageId = ParamUtil.getString(actionRequest, "languageId");
		String name = ParamUtil.getString(actionRequest, "name");
		String content = ParamUtil.getString(actionRequest, "content");

		if (cmd.equals(Constants.DELETE_TRANSLATION)) {
			TicketCannedResponseLocalServiceUtil.removeCannedResponseLocale(
				ticketCannedResponseId, languageId);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			if (ticketCannedResponseId <= 0) {
				TicketCannedResponseLocalServiceUtil.addTicketCannedResponse(
					themeDisplay.getUserId(), languageId, name, content);
			}
			else {
				TicketCannedResponseLocalServiceUtil.updateTicketCannedResponse(
					ticketCannedResponseId, defaultLanguageId, languageId, name,
					content);
			}
		}
	}

	public void updateTrainingCertificate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingCustomerId = ParamUtil.getLong(
			actionRequest, "trainingCustomerId");

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.getTrainingCustomer(
				trainingCustomerId);

		String trainingCertificateKey =
			trainingCustomer.getTrainingCertificateKey();

		TrainingCertificate trainingCertificate =
			TrainingCertificateLocalServiceUtil.getTrainingCertificate(
				trainingCertificateKey);

		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String legalEntityName = ParamUtil.getString(
			actionRequest, "legalEntityName");

		TrainingCertificateLocalServiceUtil.updateUserProfileHistoryId(
			trainingCertificate.getTrainingCertificateId(),
			trainingCustomer.getUserId(), emailAddress, firstName, lastName,
			legalEntityName);
	}

	public void updateTrainingCertificateTemplate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long trainingCertificateTemplateId = ParamUtil.getLong(
			uploadPortletRequest, "trainingCertificateTemplateId");

		String name = ParamUtil.getString(uploadPortletRequest, "name");
		String description = ParamUtil.getString(
			uploadPortletRequest, "description");
		int type = ParamUtil.getInteger(uploadPortletRequest, "type");
		String badgeFileName = uploadPortletRequest.getFileName("badgeFile");
		String templateFileName = uploadPortletRequest.getFileName(
			"templateFile");

		InputStream badgeInputStream = null;

		InputStream templateInputStream = null;

		try {
			badgeInputStream = uploadPortletRequest.getFileAsStream(
				"badgeFile");

			templateInputStream = uploadPortletRequest.getFileAsStream(
				"templateFile");

			if (trainingCertificateTemplateId <= 0) {
				TrainingCertificateTemplateLocalServiceUtil.
					addTrainingCertificateTemplate(
						themeDisplay.getUserId(), name, description, type,
						templateFileName, templateInputStream, badgeFileName,
						badgeInputStream);
			}
			else {
				TrainingCertificateTemplateLocalServiceUtil.
					updateTrainingCertificateTemplate(
						themeDisplay.getCompanyId(),
						trainingCertificateTemplateId, name, description, type,
						templateFileName, templateInputStream, badgeFileName,
						badgeInputStream);
			}
		}
		finally {
			StreamUtil.cleanUp(badgeInputStream);

			StreamUtil.cleanUp(templateInputStream);

			uploadPortletRequest.cleanUp();
		}
	}

	public void updateTrainingCourse(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long trainingCourseId = ParamUtil.getLong(
			actionRequest, "trainingCourseId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		int creditAmount = ParamUtil.getInteger(actionRequest, "creditAmount");
		String courseURL = ParamUtil.getString(actionRequest, "courseURL");
		boolean archived = ParamUtil.getBoolean(actionRequest, "archived");

		if (trainingCourseId <= 0) {
			TrainingCourseLocalServiceUtil.addTrainingCourse(
				themeDisplay.getUserId(), name, description, creditAmount,
				courseURL, archived);
		}
		else {
			TrainingCourseLocalServiceUtil.updateTrainingCourse(
				trainingCourseId, name, description, creditAmount, courseURL,
				archived);
		}
	}

	public void updateTrainingCustomer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long trainingCustomerId = ParamUtil.getLong(
			actionRequest, "trainingCustomerId");

		long trainingEventId = ParamUtil.getLong(
			actionRequest, "trainingEventId");
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");

		String legalEntityName = StringPool.BLANK;

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(
				trainingCustomerId);

		if (trainingCustomer != null) {
			User user = trainingCustomer.getUser();

			ExpandoBridge expandoBridge = user.getExpandoBridge();

			legalEntityName = (String)expandoBridge.getAttribute(
				"osbCompany", false);
		}

		TrainingCustomerLocalServiceUtil.updateTrainingCustomer(
			themeDisplay.getUserId(), trainingCustomerId,
			PortalUtil.getClassNameId(TrainingEvent.class), trainingEventId,
			emailAddress, firstName, lastName, legalEntityName);
	}

	public void updateTrainingCustomerAttendence(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] trainingCustomerIds = ParamUtil.getLongValues(
			actionRequest, "trainingCustomerIds");

		List<Long> trainingCustomerIdsList = ListUtil.toList(
			trainingCustomerIds);

		Iterator<Long> itr = trainingCustomerIdsList.iterator();

		while (itr.hasNext()) {
			Long trainingCustomerId = itr.next();

			TrainingCustomer trainingCustomer =
				TrainingCustomerLocalServiceUtil.getTrainingCustomer(
					trainingCustomerId);

			if (trainingCustomer.getStatus() !=
					TrainingCustomerConstants.STATUS_REGISTERED) {

				itr.remove();
			}
		}

		trainingCustomerIds = ArrayUtil.toLongArray(trainingCustomerIdsList);

		long trainingEventId = ParamUtil.getLong(
			actionRequest, "trainingEventId");

		List<TrainingCustomer> trainingCustomers =
			TrainingCustomerLocalServiceUtil.updateStatuses(
				trainingCustomerIds, trainingEventId,
				TrainingCustomerConstants.
					STATUS_PENDING_PARTICIPANT_FORM_COMPLETION);

		long trainingSurveyPlid = PortalUtil.getPlidFromPortletId(
			OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_TRAINING_SURVEY);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			PortalUtil.getHttpServletRequest(actionRequest),
			OSBPortletKeys.OSB_TRAINING_SURVEY, trainingSurveyPlid,
			PortletRequest.RENDER_PHASE);

		TrainingEvent trainingEvent =
			TrainingEventLocalServiceUtil.getTrainingEvent(trainingEventId);

		portletURL.setParameter(
			"ddlRecordSetId",
			String.valueOf(trainingEvent.getDDLRecordSetId()));

		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			sendTrainingSurvey(trainingCustomer, portletURL.toString());
		}
	}

	public void updateTrainingCustomers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		long trainingEventId = ParamUtil.getLong(
			actionRequest, "trainingEventId");

		String userProfileIdsJSON = ParamUtil.getString(
			actionRequest, "userProfileIdsJSON");

		TrainingCustomerLocalServiceUtil.addTrainingCustomers(
			addUserIds, PortalUtil.getClassNameId(TrainingEvent.class),
			trainingEventId, getUserProfileIds(userProfileIdsJSON));
		TrainingCustomerLocalServiceUtil.deleteTrainingCustomers(
			removeUserIds, PortalUtil.getClassNameId(TrainingEvent.class),
			trainingEventId);
	}

	public void updateTrainingEvent(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long trainingEventId = ParamUtil.getLong(
			actionRequest, "trainingEventId");

		long ddlRecordSetId = ParamUtil.getLong(
			actionRequest, "ddlRecordSetId");
		long ddmStructureId = ParamUtil.getLong(
			actionRequest, "ddmStructureId");
		long partnerEntryId = ParamUtil.getLong(
			actionRequest, "partnerEntryId");
		long trainingCertificateTemplateId = ParamUtil.getLong(
			actionRequest, "trainingCertificateTemplateId");
		long trainingCourseId = ParamUtil.getLong(
			actionRequest, "trainingCourseId");
		long trainingLocationId = ParamUtil.getLong(
			actionRequest, "eventTrainingLocationId");
		String name = ParamUtil.getString(actionRequest, "name");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		int portalMinorVersion = ParamUtil.getInteger(
			actionRequest, "portalMinorVersion");
		int type = ParamUtil.getInteger(actionRequest, "type");
		String languageId = ParamUtil.getString(actionRequest, "languageId");
		boolean localizedSlides = ParamUtil.getBoolean(
			actionRequest, "localizedSlides");

		if (ArrayUtil.contains(
				PortletPropsValues.TRAINING_EVENT_LOCALIZED_SLIDES,
				languageId)) {

			localizedSlides = true;
		}

		String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");

		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");
		int startDateHour = ParamUtil.getInteger(
			actionRequest, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(
			actionRequest, "startDateMinute");
		int startDateAmPm = ParamUtil.getInteger(
			actionRequest, "startDateAmPm");

		if (startDateAmPm == Calendar.PM) {
			startDateHour += 12;
		}

		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");
		int endDateHour = ParamUtil.getInteger(actionRequest, "endDateHour");
		int endDateMinute = ParamUtil.getInteger(
			actionRequest, "endDateMinute");
		int endDateAmPm = ParamUtil.getInteger(actionRequest, "endDateAmPm");

		if (endDateAmPm == Calendar.PM) {
			endDateHour += 12;
		}

		String addressStreet1 = ParamUtil.getString(
			actionRequest, "addressStreet1");
		String addressStreet2 = ParamUtil.getString(
			actionRequest, "addressStreet2");
		String addressStreet3 = ParamUtil.getString(
			actionRequest, "addressStreet3");
		String addressCity = ParamUtil.getString(actionRequest, "addressCity");
		String addressZip = ParamUtil.getString(actionRequest, "addressZip");
		long addressRegionId = ParamUtil.getLong(
			actionRequest, "addressRegionId");
		long addressCountryId = ParamUtil.getLong(
			actionRequest, "addressCountryId");
		int maxCustomers = ParamUtil.getInteger(actionRequest, "maxCustomers");
		String enrollmentURL = ParamUtil.getString(
			actionRequest, "enrollmentURL");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDLRecordSet.class.getName(), actionRequest);

		TrainingEvent trainingEvent = null;

		if (trainingEventId <= 0) {
			trainingEvent = TrainingEventLocalServiceUtil.addTrainingEvent(
				themeDisplay.getUserId(), ddmStructureId, partnerEntryId,
				trainingCertificateTemplateId, trainingCourseId,
				trainingLocationId, name, emailAddress, portalMinorVersion,
				type, languageId, localizedSlides, timeZoneId, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, addressStreet1, addressStreet2, addressStreet3,
				addressCity, addressZip, addressRegionId, addressCountryId,
				maxCustomers, enrollmentURL, serviceContext);

			actionRequest.setAttribute(
				OSBWebKeys.OSB_TRAINING_EVENT_ID,
				trainingEvent.getTrainingEventId());
		}
		else {
			trainingEvent = TrainingEventLocalServiceUtil.updateTrainingEvent(
				trainingEventId, ddmStructureId, ddlRecordSetId, partnerEntryId,
				trainingCertificateTemplateId, trainingCourseId,
				trainingLocationId, name, emailAddress, portalMinorVersion,
				type, languageId, localizedSlides, timeZoneId, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, addressStreet1, addressStreet2, addressStreet3,
				addressCity, addressZip, addressRegionId, addressCountryId,
				maxCustomers, enrollmentURL, serviceContext);
		}
	}

	public void updateTrainingExam(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingExamId = ParamUtil.getLong(
			actionRequest, "trainingExamId");

		long trainingCertificateTemplateId = ParamUtil.getLong(
			actionRequest, "trainingCertificateTemplateId");

		TrainingExamLocalServiceUtil.updateTrainingExam(
			trainingExamId, trainingCertificateTemplateId);
	}

	public void updateTrainingExamResult(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long trainingExamResultId = ParamUtil.getLong(
			actionRequest, "trainingExamResultId");

		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String legalEntityName = ParamUtil.getString(
			actionRequest, "legalEntityName");

		TrainingExamResult trainingExamResult =
			TrainingExamResultLocalServiceUtil.getTrainingExamResult(
				trainingExamResultId);

		TrainingCustomer trainingCustomer =
			trainingExamResult.getTrainingCustomer();

		TrainingCertificate trainingCertificate =
			TrainingCertificateLocalServiceUtil.
				getTrainingCertificateByTrainingCustomerId(
					trainingCustomer.getTrainingCustomerId());

		TrainingCertificateLocalServiceUtil.updateUserProfileHistoryId(
			trainingCertificate.getTrainingCertificateId(),
			trainingCustomer.getUserId(), emailAddress, firstName, lastName,
			legalEntityName);
	}

	public void updateTrainingExamResults(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)uploadPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String trainingExamResultsImporterType = ParamUtil.getString(
			uploadPortletRequest, "trainingExamResultsImporterType");

		TrainingExamResultsImporter trainingExamResultsImporter =
			TrainingExamResultsImporterFactoryUtil.
				createTrainingExamResultsImporter(
					trainingExamResultsImporterType);

		if (trainingExamResultsImporter instanceof
				PrometricTrainingExamResultsImporterImpl) {

			InputStream inputStream = null;

			try {
				inputStream = uploadPortletRequest.getFileAsStream("file");

				PrometricTrainingExamResultsImporterImpl
					prometricTrainingExamResultsImporter =
						(PrometricTrainingExamResultsImporterImpl)
							trainingExamResultsImporter;

				prometricTrainingExamResultsImporter.importTrainingExamResult(
					inputStream);
			}
			finally {
				StreamUtil.cleanUp(inputStream);

				uploadPortletRequest.cleanUp();
			}
		}
		else if (trainingExamResultsImporter instanceof
					KryterionTrainingExamResultsImporterImpl) {

			KryterionTrainingExamResultsImporterImpl
				kryterionTrainingExamResultsImporterImpl =
					(KryterionTrainingExamResultsImporterImpl)
						trainingExamResultsImporter;

			int startDateDay = ParamUtil.getInteger(
				actionRequest, "startDateDay");
			int startDateMonth = ParamUtil.getInteger(
				actionRequest, "startDateMonth");
			int startDateYear = ParamUtil.getInteger(
				actionRequest, "startDateYear");

			Date startDate = PortalUtil.getDate(
				startDateMonth, startDateDay, startDateYear,
				themeDisplay.getTimeZone(), PortalException.class);

			int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
			int endDateMonth = ParamUtil.getInteger(
				actionRequest, "endDateMonth");
			int endDateYear = ParamUtil.getInteger(
				actionRequest, "endDateYear");

			Date endDate = PortalUtil.getDate(
				endDateMonth, endDateDay, endDateYear,
				themeDisplay.getTimeZone(), PortalException.class);

			kryterionTrainingExamResultsImporterImpl.importTrainingExamResults(
				startDate, endDate);
		}
	}

	public void updateTrainingLinkedUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long primaryUserId = ParamUtil.getLong(actionRequest, "primaryUserId");
		long oldPrimaryUserId = ParamUtil.getLong(
			actionRequest, "oldPrimaryUserId", primaryUserId);
		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		if (cmd.equals(Constants.UPDATE)) {
			TrainingLinkedUserLocalServiceUtil.updateTrainingLinkedUsers(
				primaryUserId, oldPrimaryUserId, addUserIds);
		}
		else {
			TrainingLinkedUserLocalServiceUtil.addTrainingLinkedUsers(
				primaryUserId, oldPrimaryUserId, addUserIds);
			TrainingLinkedUserLocalServiceUtil.unsetTrainingLinkedUsers(
				primaryUserId, removeUserIds);
		}
	}

	public void updateTrainingLocation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long trainingLocationId = ParamUtil.getLong(
			actionRequest, "trainingLocationId");

		String name = ParamUtil.getString(actionRequest, "name");
		String street1 = ParamUtil.getString(actionRequest, "street1");
		String street2 = ParamUtil.getString(actionRequest, "street2");
		String street3 = ParamUtil.getString(actionRequest, "street3");
		String city = ParamUtil.getString(actionRequest, "city");
		String zip = ParamUtil.getString(actionRequest, "zip");
		long regionId = ParamUtil.getLong(actionRequest, "regionId");
		long countryId = ParamUtil.getLong(actionRequest, "countryId");

		if (trainingLocationId <= 0) {
			TrainingLocationLocalServiceUtil.addTrainingLocation(
				themeDisplay.getUserId(), name, street1, street2, street3, city,
				zip, regionId, countryId);
		}
		else {
			TrainingLocationLocalServiceUtil.updateTrainingLocation(
				trainingLocationId, name, street1, street2, street3, city, zip,
				regionId, countryId);
		}
	}

	public void updateTrainingWorkers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		long trainingEventId = ParamUtil.getLong(
			actionRequest, "trainingEventId");

		String userProfileIdsJSON = ParamUtil.getString(
			actionRequest, "userProfileIdsJSON");

		TrainingWorkerLocalServiceUtil.addTrainingWorkers(
			addUserIds, PortalUtil.getClassNameId(TrainingEvent.class),
			trainingEventId, getUserProfileIds(userProfileIdsJSON));
		TrainingWorkerLocalServiceUtil.deleteTrainingWorkers(
			removeUserIds, PortalUtil.getClassNameId(TrainingEvent.class),
			trainingEventId);
	}

	public void updateUserProfile(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userId = ParamUtil.getLong(actionRequest, "userId");
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String legalEntityName = ParamUtil.getString(
			actionRequest, "legalEntityName");

		UserProfileLocalServiceUtil.addUserProfile(
			userId, emailAddress, firstName, lastName, legalEntityName);
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

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (actionName.equals("updateTrainingEvent")) {
			redirect = getTrainingEventRedirect(actionRequest);
		}

		if (Validator.isNull(redirect)) {
			redirect = ParamUtil.getString(
				actionRequest, "assignmentsRedirect");
		}

		if (Validator.isNull(redirect)) {
			redirect = super.getRedirect(actionRequest, actionResponse);
		}

		return redirect;
	}

	protected String getSurveyResultsCSV(long ddlRecordSetId) throws Exception {
		DDLRecordSet ddlRecordSet = DDLRecordSetLocalServiceUtil.getRecordSet(
			ddlRecordSetId);

		List<DDLRecord> ddlRecords = DDLRecordLocalServiceUtil.getRecords(
			ddlRecordSet.getRecordSetId());

		DDMStructure ddmStructure = ddlRecordSet.getDDMStructure(0);

		TrainingEvent trainingEvent =
			TrainingEventLocalServiceUtil.getTrainingEventByDDLRecordSetId(
				ddlRecordSetId);

		Map<String, Map<String, String>> fieldsMap = ddmStructure.getFieldsMap(
			trainingEvent.getLanguageId());

		StringBundler sb = new StringBundler(
			(ddlRecords.size() * ((fieldsMap.size() * 2) + 6)) +
				(fieldsMap.size() * 2) + 18);

		sb.append(
			"Type,Client Name,Course,Location,Partner,Start Date,Trainer\n");

		sb.append(
			CSVUtil.encode(
				StringUtil.upperCaseFirstLetter(trainingEvent.getTypeLabel())));
		sb.append(StringPool.COMMA);
		sb.append(CSVUtil.encode(trainingEvent.getName()));
		sb.append(StringPool.COMMA);
		sb.append(CSVUtil.encode(trainingEvent.getTrainingCourse().getName()));
		sb.append(StringPool.COMMA);
		sb.append(CSVUtil.encode(trainingEvent.getAddressDisplayHTML()));
		sb.append(StringPool.COMMA);

		PartnerEntry partnerEntry = trainingEvent.getPartnerEntry();

		if (partnerEntry != null) {
			sb.append(CSVUtil.encode(partnerEntry.getCode()));
		}
		else {
			sb.append(StringPool.BLANK);
		}

		sb.append(StringPool.COMMA);

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMMMM d, yyyy");

		sb.append(
			CSVUtil.encode(dateFormat.format(trainingEvent.getStartDate())));

		sb.append(StringPool.COMMA);

		sb.append(
			CSVUtil.encode(trainingEvent.getTrainingWorkersDisplayHTML()));
		sb.append(StringPool.NEW_LINE);
		sb.append(StringPool.NEW_LINE);
		sb.append("First Name,Last Name,Email Address,");

		for (Map<String, String> fields : fieldsMap.values()) {
			String label = fields.get(FieldConstants.LABEL);

			sb.append(CSVUtil.encode(label));
			sb.append(StringPool.COMMA);
		}

		sb.setIndex(sb.index() - 1);
		sb.append(StringPool.NEW_LINE);

		for (DDLRecord ddlRecord : ddlRecords) {
			TrainingCustomer trainingCustomer =
				TrainingCustomerLocalServiceUtil.getTrainingCustomer(
					ddlRecord.getUserId(),
					PortalUtil.getClassNameId(TrainingEvent.class),
					trainingEvent.getTrainingEventId());

			UserProfileHistory userProfileHistory =
				UserProfileHistoryLocalServiceUtil.getUserProfileHistory(
					trainingCustomer.getUserProfileHistoryId());

			sb.append(CSVUtil.encode(userProfileHistory.getFirstName()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(userProfileHistory.getLastName()));
			sb.append(StringPool.COMMA);
			sb.append(CSVUtil.encode(userProfileHistory.getEmailAddress()));
			sb.append(StringPool.COMMA);

			DDLRecordVersion recordVersion = ddlRecord.getRecordVersion();

			Fields fields = StorageEngineUtil.getFields(
				recordVersion.getDDMStorageId());

			for (Map<String, String> fieldMap : fieldsMap.values()) {
				String name = fieldMap.get(FieldConstants.NAME);
				String value = StringPool.BLANK;

				if (fields.contains(name)) {
					Field field = fields.get(name);

					value = field.getRenderedValue(
						LocaleUtil.fromLanguageId(
							trainingEvent.getLanguageId(), false));
				}

				sb.append(CSVUtil.encode(value));
				sb.append(StringPool.COMMA);
			}

			sb.setIndex(sb.index() - 1);
			sb.append(StringPool.NEW_LINE);
		}

		return sb.toString();
	}

	protected String getTrainingEventRedirect(ActionRequest actionRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, OSBPortletKeys.OSB_ADMIN, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/admin/edit_training_event.jsp");
		portletURL.setParameter(
			"backURL", ParamUtil.getString(actionRequest, "backURL"));

		Long trainingEventId = (Long)actionRequest.getAttribute(
			OSBWebKeys.OSB_TRAINING_EVENT_ID);

		if (trainingEventId == null) {
			return null;
		}

		portletURL.setParameter(
			"trainingEventId", String.valueOf(trainingEventId));

		return portletURL.toString();
	}

	protected Map<Long, Long> getUserProfileIds(String userProfileIdsJSON)
		throws Exception {

		Map<Long, Long> userProfileIdsMap = new HashMap<Long, Long>();

		if (Validator.isNotNull(userProfileIdsJSON)) {
			JSONObject userProfileIdsJSONObject =
				JSONFactoryUtil.createJSONObject(userProfileIdsJSON);

			Iterator<String> iterator = userProfileIdsJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				String value = userProfileIdsJSONObject.getString(key);

				userProfileIdsMap.put(
					GetterUtil.getLong(key), GetterUtil.getLong(value));
			}
		}

		return userProfileIdsMap;
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
					OSBConstants.ROLE_OSB_MARKETING_ADMIN_ID)) {

				return true;
			}

			if (RoleLocalServiceUtil.hasUserRole(
					themeDisplay.getUserId(),
					OSBConstants.ROLE_OSB_SUPPORT_ADMIN_ID)) {

				return true;
			}

			if (RoleLocalServiceUtil.hasUserRole(
					themeDisplay.getUserId(),
					OSBConstants.ROLE_OSB_TRAINING_ADMIN_ID)) {

				return true;
			}

			if (RoleLocalServiceUtil.hasUserRole(
					themeDisplay.getUserId(),
					OSBConstants.ROLE_OSB_TRAINING_TRAINER_ID)) {

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
			cause instanceof AccountEnvironmentEnvDBException ||
			cause instanceof AccountEnvironmentEnvASException ||
			cause instanceof AccountEnvironmentEnvLFRException ||
			cause instanceof AccountEnvironmentEnvOSException ||
			cause instanceof AccountEnvironmentNameException ||
			cause instanceof AccountWorkerPartnerRoleException ||
			cause instanceof AddressCityException ||
			cause instanceof AddressStreetException ||
			cause instanceof AddressZipException ||
			cause instanceof ContactFirstNameException ||
			cause instanceof ContactLastNameException ||
			cause instanceof CountryA2Exception ||
			cause instanceof CountryA3Exception ||
			cause instanceof CountryIddException ||
			cause instanceof CountryNameException ||
			cause instanceof CountryNumberException ||
			cause instanceof CurrencyEntryCodeException ||
			cause instanceof CurrencyEntryCountryException ||
			cause instanceof DuplicateAccountEntryException ||
			cause instanceof DuplicateAccountEnvironmentException ||
			cause instanceof DuplicateCurrencyEntryException ||
			cause instanceof DuplicateHolidayEntryException ||
			cause instanceof DuplicateOfferingBundleException ||
			cause instanceof DuplicateOfferingDefinitionException ||
			cause instanceof DuplicatePartnerEntryCodeException ||
			cause instanceof DuplicatePartnerEntryDossieraAccountKeyException ||
			cause instanceof DuplicateProductEntryException ||
			cause instanceof DuplicateSupportLaborException ||
			cause instanceof DuplicateSupportRegionException ||
			cause instanceof DuplicateSupportResponseException ||
			cause instanceof DuplicateSupportTeamException ||
			cause instanceof DuplicateSupportWorkerException ||
			cause instanceof DuplicateTicketCannedResponseException ||
			cause instanceof DuplicateUserEmailAddressException ||
			cause instanceof FileExtensionException ||
			cause instanceof FileNameException ||
			cause instanceof HolidayCalendarNameException ||
			cause instanceof HolidayEntryDateException ||
			cause instanceof HolidayEntryStartDateLaterThanEndDateException ||
			cause instanceof LicenseEntryNameException ||
			cause instanceof LicenseEntryPortalVersionException ||
			cause instanceof MarketingEventEndDateException ||
			cause instanceof MarketingEventHostedByException ||
			cause instanceof MarketingEventHostedByURLException ||
			cause instanceof MarketingEventRegistrationURLException ||
			cause instanceof MarketingEventStartDateException ||
			cause instanceof MarketingEventTitleException ||
			cause instanceof MarketingEventTitleURLException ||
			cause instanceof MarketingEventVideoTitleException ||
			cause instanceof NoSuchAccountEntryException ||
			cause instanceof NoSuchCurrencyEntryException ||
			cause instanceof NoSuchListTypeException ||
			cause instanceof NoSuchOfferingDefinitionException ||
			cause instanceof NoSuchProductEntryException ||
			cause instanceof NoSuchSupportResponseException ||
			cause instanceof NoSuchTrainingCourseException ||
			cause instanceof NoSuchTrainingLocationException ||
			cause instanceof OfferingBundleNameException ||
			cause instanceof OrderEntryActualStartDateException ||
			cause instanceof PrincipalException ||
			cause instanceof PartnerEntryCodeException ||
			cause instanceof PartnerEntryParentPartnerEntryException ||
			cause instanceof PartnerTemplateException ||
			cause instanceof ProductEntryEnvironmentException ||
			cause instanceof ProductEntryNameException ||
			cause instanceof RecordSetDDMStructureIdException ||
			cause instanceof RegionCodeException ||
			cause instanceof RegionNameException ||
			cause instanceof RequiredAccountEntryException ||
			cause instanceof RequiredDDLRecordSetDDLRecordException ||
			cause instanceof RequiredLicenseEntryException ||
			cause instanceof RequiredOfferingDefinitionException ||
			cause instanceof RequiredOfferingEntryException ||
			cause instanceof RequiredPartnerEntryException ||
			cause instanceof RequiredProductEntryException ||
			cause instanceof RequiredSupportRegionException ||
			cause instanceof RequiredSupportResponseException ||
			cause instanceof RequiredSupportTeamException ||
			cause instanceof RequiredTrainingCertificateTemplateException ||
			cause instanceof RequiredTrainingCourseException ||
			cause instanceof RequiredTrainingLocationException ||
			cause instanceof RequiredTrainingEventException ||
			cause instanceof ReservedUserEmailAddressException ||
			cause instanceof SupportLaborHourException ||
			cause instanceof SupportLaborNameException ||
			cause instanceof SupportRegionNameException ||
			cause instanceof SupportResponseNameException ||
			cause instanceof SupportResponseSupportLevelException ||
			cause instanceof SupportTeamContainsNoSupportWorkersException ||
			cause instanceof SupportTeamLocationException ||
			cause instanceof SupportTeamNameException ||
			cause instanceof SupportTeamSupportLaborException ||
			cause instanceof SupportWorkerMaxWorkException ||
			cause instanceof TicketCannedResponseContentException ||
			cause instanceof TicketCannedResponseNameException ||
			cause instanceof TrainingCertificateTemplateNameException ||
			cause instanceof TrainingCourseNameException ||
			cause instanceof TrainingCustomerCertificateException ||
			cause instanceof TrainingCustomerStatusException ||
			cause instanceof TrainingLocationNameException ||
			cause instanceof UserEmailAddressException ||
			cause instanceof UserProfileFirstNameException ||
			cause instanceof UserProfileHistoryFirstNameException ||
			cause instanceof UserProfileHistoryLastNameException ||
			cause instanceof UserProfileLastNameException) {

			return true;
		}
		else {
			return false;
		}
	}

	protected void sendCsvFile(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			String title, String content)
		throws Exception {

		try {
			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, title + ".csv",
				content.getBytes(), ContentTypes.TEXT_CSV_UTF8);
		}
		catch (Exception e) {
		}
	}

	protected void sendTrainingCertificate(TrainingCustomer trainingCustomer)
		throws Exception {

		Message message = new Message();

		Map<String, Object> values = new HashMap<String, Object>();

		values.put(
			"trainingCustomerId", trainingCustomer.getTrainingCustomerId());

		message.setValues(values);

		MessageBusUtil.sendMessage("liferay/osb_training_certificate", message);
	}

	protected void sendTrainingSurvey(
		TrainingCustomer trainingCustomer, String trainingSurveyURL) {

		Message message = new Message();

		Map<String, Object> values = new HashMap<String, Object>();

		values.put(
			"trainingCustomerId", trainingCustomer.getTrainingCustomerId());
		values.put("trainingSurveyURL", trainingSurveyURL);

		message.setValues(values);

		MessageBusUtil.sendMessage("liferay/osb_training_survey", message);
	}

	protected void serveAccountAttachment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException, SystemException {

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
		throws IOException, SystemException {

		String name = ParamUtil.getString(resourceRequest, "name");
		String code = ParamUtil.getString(resourceRequest, "code");

		name = name + StringPool.PERCENT;
		code = code + StringPool.PERCENT;

		List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.search(
			name, code);

		JSONArray accountEntriesArray = JSONFactoryUtil.createJSONArray();

		for (AccountEntry accountEntry : accountEntries) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			jsonArray.put(accountEntry.getName());
			jsonArray.put(accountEntry.getCode());

			accountEntriesArray.put(jsonArray);
		}

		writeJSON(resourceRequest, resourceResponse, accountEntriesArray);
	}

	protected void serveAccountEnvironmentAttachment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException, SystemException {

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

	protected void serveTrainingCertificateTemplate(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			long trainingCertificateTemplateId)
		throws Exception {

		TrainingCertificateTemplate trainingCertificateTemplate =
			TrainingCertificateTemplateLocalServiceUtil.
				getTrainingCertificateTemplate(trainingCertificateTemplateId);

		String[] fileNames = DLStoreUtil.getFileNames(
			CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
				trainingCertificateTemplate.getTemplatesDir());

		InputStream inputStream = DLStoreUtil.getFileAsStream(
			CompanyConstants.SYSTEM, CompanyConstants.SYSTEM, fileNames[0]);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileNames[0], inputStream, 0,
			ContentTypes.APPLICATION_PDF,
			HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
	}

	protected void syncToLCS(
			ActionRequest actionRequest, ActionResponse actionResponse,
			OrderEntry orderEntry)
		throws IOException {

		try {
			AccountEntry accountEntry = orderEntry.getAccountEntry();

			LCSSubscriptionEntryLocalServiceUtil.syncToLCS(
				accountEntry.getCorpProjectId());
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

			ObjectValuePair<String, File> ovp =
				new ObjectValuePair<String, File>(fileName, file);

			AccountAttachmentLocalServiceUtil.addAccountAttachment(
				themeDisplay.getUserId(), accountEntryId, accountProjectId, ovp,
				AccountAttachmentConstants.TYPE_OEM_INSTRUCTIONS);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	protected void updateTrainingCustomer(
			UploadPortletRequest uploadPortletRequest,
			Element trainingCustomerElement)
		throws Exception {

		long trainingEventId = GetterUtil.getLong(
			trainingCustomerElement.elementText("trainingEventId"));

		try {
			TrainingEventLocalServiceUtil.getTrainingEvent(trainingEventId);
		}
		catch (Exception e) {
			throw new RequiredTrainingEventException();
		}

		User user = null;

		String emailAddress = trainingCustomerElement.elementText("email");

		if (Validator.isNotNull(emailAddress)) {
			emailAddress = emailAddress.trim().toLowerCase();

			user = UserLocalServiceUtil.fetchUserByEmailAddress(
				OSBConstants.COMPANY_ID, emailAddress);
		}

		TrainingCustomer trainingCustomer = null;

		String firstName = trainingCustomerElement.elementText("firstName");
		String lastName = trainingCustomerElement.elementText("lastName");
		String legalEntityName = trainingCustomerElement.elementText("company");

		long userProfileId = 0;

		if (user != null) {
			trainingCustomer =
				TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(
					user.getUserId(),
					PortalUtil.getClassNameId(TrainingEvent.class),
					trainingEventId);

			if (trainingCustomer != null) {
				throw new RequiredTrainingEventException();
			}

			UserProfile userProfile =
				UserProfileLocalServiceUtil.addUserProfile(
					user.getUserId(), emailAddress, firstName, lastName,
					legalEntityName);

			userProfileId = userProfile.getUserProfileId();

			trainingCustomer =
				TrainingCustomerLocalServiceUtil.addTrainingCustomer(
					user.getUserId(),
					PortalUtil.getClassNameId(TrainingEvent.class),
					trainingEventId, userProfile.getUserProfileId());
		}
		else {
			trainingCustomer =
				TrainingCustomerLocalServiceUtil.updateTrainingCustomer(
					OSBConstants.USER_DEFAULT_USER_ID, 0,
					PortalUtil.getClassNameId(TrainingEvent.class),
					trainingEventId, emailAddress, firstName, lastName,
					legalEntityName);
		}

		String key = trainingCustomerElement.elementText(
			"previousCertificateKey");

		if (Validator.isNull(key)) {
			key = trainingCustomerElement.elementText("certificateKey");
		}

		key = key.replaceAll(StringPool.SPACE, StringPool.BLANK);

		try {
			TrainingCertificateLocalServiceUtil.getTrainingCertificate(key);

			return;
		}
		catch (Exception e) {
		}

		TrainingCertificateLocalServiceUtil.addTrainingCertificate(
			OSBConstants.USER_DEFAULT_USER_ID,
			trainingCustomer.getTrainingCustomerId(), key, 0, userProfileId);
	}

	protected void updateTrainingSurvey(
			UploadPortletRequest uploadPortletRequest,
			Element trainingSurveyElement)
		throws Exception {

		long userId = GetterUtil.getLong(
			trainingSurveyElement.elementText("UserId"));

		long trainingEventId = GetterUtil.getLong(
			trainingSurveyElement.elementText("TrainingEventId"));

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(
				userId, PortalUtil.getClassNameId(TrainingEvent.class),
				trainingEventId);

		if (trainingCustomer == null) {
			return;
		}
		else if (!TrainingCertificateLocalServiceUtil.hasTrainingCertificate(
					trainingCustomer.getTrainingCustomerId())) {

			TrainingCertificateLocalServiceUtil.addTrainingCertificate(
				OSBConstants.USER_DEFAULT_USER_ID,
				trainingCustomer.getTrainingCustomerId(), null, 0, 0);
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDLRecord.class.getName(), uploadPortletRequest);

		serviceContext.setUserId(userId);

		TrainingEvent trainingEvent =
			TrainingEventLocalServiceUtil.getTrainingEvent(trainingEventId);

		DDLRecordSet ddlRecordSet =
			DDLRecordSetLocalServiceUtil.getDDLRecordSet(
				trainingEvent.getDDLRecordSetId());

		DDMStructure ddmStructure = ddlRecordSet.getDDMStructure();

		Element customerResponseElement = trainingSurveyElement.element(
			"CustomerResponse");

		List<Element> elements = customerResponseElement.elements();

		for (Element element : elements) {
			String key = element.attributeValue("key");

			if (!ddmStructure.hasField(key)) {
				continue;
			}

			serviceContext.setAttribute(key, element.getStringValue());
		}

		long ddlRecordId = 0;

		List<DDLRecord> ddlRecords = DDLRecordLocalServiceUtil.getRecords(
			trainingEvent.getDDLRecordSetId(), userId);

		if (!ddlRecords.isEmpty()) {
			DDLRecord ddlRecord = ddlRecords.get(0);

			ddlRecordId = ddlRecord.getRecordId();
		}

		DDLUtil.updateRecord(
			ddlRecordId, trainingEvent.getDDLRecordSetId(), true, false,
			serviceContext);
	}

	private static Log _log = LogFactoryUtil.getLog(AdminPortlet.class);

}