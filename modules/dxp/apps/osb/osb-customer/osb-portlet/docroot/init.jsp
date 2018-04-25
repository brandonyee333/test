<%--
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
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.document.library.kernel.exception.NoSuchFileEntryException" %><%@
page import="com.liferay.document.library.kernel.model.DLFileEntry" %><%@
page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %><%@
page import="com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil" %><%@
page import="com.liferay.journal.model.JournalArticle" %><%@
page import="com.liferay.journal.service.JournalArticleLocalServiceUtil" %><%@
page import="com.liferay.osb.admin.asset.AccountEntryAssetRenderer" %><%@
page import="com.liferay.osb.admin.search.AccountEntryDisplayTerms" %><%@
page import="com.liferay.osb.admin.search.AccountEntrySearch" %><%@
page import="com.liferay.osb.admin.search.AccountEntrySearchTerms" %><%@
page import="com.liferay.osb.admin.search.CorpProjectDisplayTerms" %><%@
page import="com.liferay.osb.admin.search.CorpProjectSearch" %><%@
page import="com.liferay.osb.admin.search.CorpProjectSearchTerms" %><%@
page import="com.liferay.osb.admin.search.OfferingDefinitionDisplayTerms" %><%@
page import="com.liferay.osb.admin.search.OfferingDefinitionSearch" %><%@
page import="com.liferay.osb.admin.search.OfferingDefinitionSearchTerms" %><%@
page import="com.liferay.osb.admin.search.OfferingEntryDisplayTerms" %><%@
page import="com.liferay.osb.admin.search.OfferingEntrySearch" %><%@
page import="com.liferay.osb.admin.search.OrderEntryDisplayTerms" %><%@
page import="com.liferay.osb.admin.search.OrderEntrySearch" %><%@
page import="com.liferay.osb.admin.search.OrderEntrySearchTerms" %><%@
page import="com.liferay.osb.admin.search.PartnerEntryDisplayTerms" %><%@
page import="com.liferay.osb.admin.search.PartnerEntrySearch" %><%@
page import="com.liferay.osb.admin.search.PartnerEntrySearchTerms" %><%@
page import="com.liferay.osb.admin.search.UserAccountWorkerChecker" %><%@
page import="com.liferay.osb.admin.search.UserDisplayTerms" %><%@
page import="com.liferay.osb.admin.search.UserPartnerWorkerChecker" %><%@
page import="com.liferay.osb.admin.search.UserSearch" %><%@
page import="com.liferay.osb.admin.util.AdminUtil" %><%@
page import="com.liferay.osb.exception.AccountEntryCodeException" %><%@
page import="com.liferay.osb.exception.AccountEntryCorpProjectException" %><%@
page import="com.liferay.osb.exception.AccountEntryIndustryException" %><%@
page import="com.liferay.osb.exception.AccountEntryLanguageIdException" %><%@
page import="com.liferay.osb.exception.AccountEntryMaximumCustomersException" %><%@
page import="com.liferay.osb.exception.AccountEntryNameException" %><%@
page import="com.liferay.osb.exception.AccountEntryPartnerEntryException" %><%@
page import="com.liferay.osb.exception.AccountEntrySupportRegionException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentAttachmentSizeException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvASException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvDBException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvLFRException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvOSException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentNameException" %><%@
page import="com.liferay.osb.exception.DuplicateAccountCustomerException" %><%@
page import="com.liferay.osb.exception.DuplicateAccountEntryException" %><%@
page import="com.liferay.osb.exception.DuplicateAccountEnvironmentException" %><%@
page import="com.liferay.osb.exception.DuplicateHostNameException" %><%@
page import="com.liferay.osb.exception.DuplicateIPAddressException" %><%@
page import="com.liferay.osb.exception.DuplicateMACAddressException" %><%@
page import="com.liferay.osb.exception.DuplicateOfferingBundleException" %><%@
page import="com.liferay.osb.exception.DuplicateOfferingDefinitionException" %><%@
page import="com.liferay.osb.exception.DuplicatePartnerEntryCodeException" %><%@
page import="com.liferay.osb.exception.DuplicatePartnerEntryDossieraAccountKeyException" %><%@
page import="com.liferay.osb.exception.DuplicateProductEntryException" %><%@
page import="com.liferay.osb.exception.DuplicateSupportRegionException" %><%@
page import="com.liferay.osb.exception.DuplicateSupportResponseException" %><%@
page import="com.liferay.osb.exception.LicenseEntryNameException" %><%@
page import="com.liferay.osb.exception.LicenseEntryPortalVersionException" %><%@
page import="com.liferay.osb.exception.LicenseKeyDescriptionException" %><%@
page import="com.liferay.osb.exception.LicenseKeyHostNameException" %><%@
page import="com.liferay.osb.exception.LicenseKeyIPAddressException" %><%@
page import="com.liferay.osb.exception.LicenseKeyMACAddressException" %><%@
page import="com.liferay.osb.exception.LicenseKeyMaxServersException" %><%@
page import="com.liferay.osb.exception.LicenseKeyOwnerException" %><%@
page import="com.liferay.osb.exception.LicenseKeyProductVersionException" %><%@
page import="com.liferay.osb.exception.LicenseKeyRegistrationException" %><%@
page import="com.liferay.osb.exception.LicenseKeyServerIdException" %><%@
page import="com.liferay.osb.exception.LicenseKeySetNameException" %><%@
page import="com.liferay.osb.exception.LicenseKeySingleUseException" %><%@
page import="com.liferay.osb.exception.MaximumLicenseKeyException" %><%@
page import="com.liferay.osb.exception.NoSuchAccountEntryException" %><%@
page import="com.liferay.osb.exception.NoSuchLicenseEntryException" %><%@
page import="com.liferay.osb.exception.NoSuchLicenseKeyException" %><%@
page import="com.liferay.osb.exception.NoSuchLicenseKeySetException" %><%@
page import="com.liferay.osb.exception.NoSuchOfferingEntryException" %><%@
page import="com.liferay.osb.exception.NoSuchPartnerEntryException" %><%@
page import="com.liferay.osb.exception.NoSuchProductEntryException" %><%@
page import="com.liferay.osb.exception.NoSuchSupportRegionException" %><%@
page import="com.liferay.osb.exception.NoSuchSupportResponseException" %><%@
page import="com.liferay.osb.exception.OfferingBundleNameException" %><%@
page import="com.liferay.osb.exception.OfferingEntryStatusException" %><%@
page import="com.liferay.osb.exception.OrderEntryActualStartDateException" %><%@
page import="com.liferay.osb.exception.PartnerEntryCodeException" %><%@
page import="com.liferay.osb.exception.PartnerEntryParentPartnerEntryException" %><%@
page import="com.liferay.osb.exception.ProductEntryEnvironmentException" %><%@
page import="com.liferay.osb.exception.ProductEntryNameException" %><%@
page import="com.liferay.osb.exception.RemoteServiceException" %><%@
page import="com.liferay.osb.exception.RequiredAccountEntryException" %><%@
page import="com.liferay.osb.exception.RequiredOfferingDefinitionException" %><%@
page import="com.liferay.osb.exception.RequiredOfferingEntryException" %><%@
page import="com.liferay.osb.exception.RequiredPartnerEntryException" %><%@
page import="com.liferay.osb.exception.RequiredProductEntryException" %><%@
page import="com.liferay.osb.exception.RequiredSupportRegionException" %><%@
page import="com.liferay.osb.exception.RequiredSupportResponseException" %><%@
page import="com.liferay.osb.exception.SupportResponseNameException" %><%@
page import="com.liferay.osb.exception.SupportResponseSupportLevelException" %><%@
page import="com.liferay.osb.license.search.LicenseKeyDisplayTerms" %><%@
page import="com.liferay.osb.license.search.LicenseKeySearch" %><%@
page import="com.liferay.osb.license.search.LicenseKeySearchTerms" %><%@
page import="com.liferay.osb.license.util.LicenseUtil" %><%@
page import="com.liferay.osb.model.AccountAttachment" %><%@
page import="com.liferay.osb.model.AccountAttachmentConstants" %><%@
page import="com.liferay.osb.model.AccountCustomer" %><%@
page import="com.liferay.osb.model.AccountCustomerConstants" %><%@
page import="com.liferay.osb.model.AccountEntry" %><%@
page import="com.liferay.osb.model.AccountEntryConstants" %><%@
page import="com.liferay.osb.model.AccountEnvironment" %><%@
page import="com.liferay.osb.model.AccountEnvironmentAttachment" %><%@
page import="com.liferay.osb.model.AccountEnvironmentAttachmentConstants" %><%@
page import="com.liferay.osb.model.AccountEnvironmentConstants" %><%@
page import="com.liferay.osb.model.AccountProjectConstants" %><%@
page import="com.liferay.osb.model.AccountWorker" %><%@
page import="com.liferay.osb.model.AccountWorkerConstants" %><%@
page import="com.liferay.osb.model.AuditEntry" %><%@
page import="com.liferay.osb.model.AuditEntryConstants" %><%@
page import="com.liferay.osb.model.CorpProject" %><%@
page import="com.liferay.osb.model.CorpProjectMessage" %><%@
page import="com.liferay.osb.model.FeedbackEntry" %><%@
page import="com.liferay.osb.model.FeedbackEntryConstants" %><%@
page import="com.liferay.osb.model.FileRepository" %><%@
page import="com.liferay.osb.model.LicenseEntry" %><%@
page import="com.liferay.osb.model.LicenseEntryConstants" %><%@
page import="com.liferay.osb.model.LicenseKey" %><%@
page import="com.liferay.osb.model.LicenseKeyConstants" %><%@
page import="com.liferay.osb.model.LicenseKeySet" %><%@
page import="com.liferay.osb.model.OfferingBundle" %><%@
page import="com.liferay.osb.model.OfferingDefinition" %><%@
page import="com.liferay.osb.model.OfferingDefinitionConstants" %><%@
page import="com.liferay.osb.model.OfferingEntry" %><%@
page import="com.liferay.osb.model.OfferingEntryConstants" %><%@
page import="com.liferay.osb.model.OfferingEntryGroup" %><%@
page import="com.liferay.osb.model.OrderEntry" %><%@
page import="com.liferay.osb.model.OrderEntryConstants" %><%@
page import="com.liferay.osb.model.PartnerEntry" %><%@
page import="com.liferay.osb.model.PartnerWorker" %><%@
page import="com.liferay.osb.model.PartnerWorkerConstants" %><%@
page import="com.liferay.osb.model.ProductEntry" %><%@
page import="com.liferay.osb.model.ProductEntryConstants" %><%@
page import="com.liferay.osb.model.SupportRegion" %><%@
page import="com.liferay.osb.model.SupportResponse" %><%@
page import="com.liferay.osb.model.SupportResponseConstants" %><%@
page import="com.liferay.osb.model.impl.AccountCustomerImpl" %><%@
page import="com.liferay.osb.model.impl.OfferingEntryImpl" %><%@
page import="com.liferay.osb.service.AccountAttachmentServiceUtil" %><%@
page import="com.liferay.osb.service.AccountCustomerLocalServiceUtil" %><%@
page import="com.liferay.osb.service.AccountEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.service.AccountEntryServiceUtil" %><%@
page import="com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil" %><%@
page import="com.liferay.osb.service.AccountEnvironmentLocalServiceUtil" %><%@
page import="com.liferay.osb.service.AccountWorkerLocalServiceUtil" %><%@
page import="com.liferay.osb.service.AuditEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.service.CorpProjectLocalServiceUtil" %><%@
page import="com.liferay.osb.service.CorpProjectMessageLocalServiceUtil" %><%@
page import="com.liferay.osb.service.LicenseEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.service.LicenseKeyLocalServiceUtil" %><%@
page import="com.liferay.osb.service.LicenseKeyServiceUtil" %><%@
page import="com.liferay.osb.service.LicenseKeySetLocalServiceUtil" %><%@
page import="com.liferay.osb.service.LicenseKeySetServiceUtil" %><%@
page import="com.liferay.osb.service.OfferingBundleLocalServiceUtil" %><%@
page import="com.liferay.osb.service.OfferingDefinitionLocalServiceUtil" %><%@
page import="com.liferay.osb.service.OfferingEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.service.OrderEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.service.PartnerEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.service.PartnerEntryServiceUtil" %><%@
page import="com.liferay.osb.service.PartnerWorkerLocalServiceUtil" %><%@
page import="com.liferay.osb.service.ProductEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.service.SupportRegionLocalServiceUtil" %><%@
page import="com.liferay.osb.service.SupportResponseLocalServiceUtil" %><%@
page import="com.liferay.osb.service.permission.OSBAccountEntryPermission" %><%@
page import="com.liferay.osb.service.permission.OSBLicenseKeyPermission" %><%@
page import="com.liferay.osb.service.permission.OSBLicenseKeySetPermission" %><%@
page import="com.liferay.osb.support.util.SupportUtil" %><%@
page import="com.liferay.osb.util.OSBActionKeys" %><%@
page import="com.liferay.osb.util.OSBConstants" %><%@
page import="com.liferay.osb.util.OSBPortletKeys" %><%@
page import="com.liferay.osb.util.OSBWebKeys" %><%@
page import="com.liferay.osb.util.PortletPropsValues" %><%@
page import="com.liferay.osb.util.SalesforceConstants" %><%@
page import="com.liferay.osb.util.WorkflowConstants" %><%@
page import="com.liferay.osb.util.comparator.AccountEntryNameComparator" %><%@
page import="com.liferay.osb.util.comparator.LicenseKeyExpirationDateComparator" %><%@
page import="com.liferay.osb.util.comparator.OfferingEntrySupportEndDateComparator" %><%@
page import="com.liferay.osb.util.comparator.OrderEntryStartDateComparator" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.bean.BeanPropertiesUtil" %><%@
page import="com.liferay.portal.kernel.captcha.CaptchaMaxChallengesException" %><%@
page import="com.liferay.portal.kernel.captcha.CaptchaTextException" %><%@
page import="com.liferay.portal.kernel.dao.orm.CustomSQLParam" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.exception.AddressCityException" %><%@
page import="com.liferay.portal.kernel.exception.AddressStreetException" %><%@
page import="com.liferay.portal.kernel.exception.AddressZipException" %><%@
page import="com.liferay.portal.kernel.exception.ContactFirstNameException" %><%@
page import="com.liferay.portal.kernel.exception.ContactLastNameException" %><%@
page import="com.liferay.portal.kernel.exception.EmailAddressException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchUserException" %><%@
page import="com.liferay.portal.kernel.exception.PortalException" %><%@
page import="com.liferay.portal.kernel.exception.RequiredFieldException" %><%@
page import="com.liferay.portal.kernel.exception.ReservedUserEmailAddressException" %><%@
page import="com.liferay.portal.kernel.exception.UserEmailAddressException" %><%@
page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Address" %><%@
page import="com.liferay.portal.kernel.model.Contact" %><%@
page import="com.liferay.portal.kernel.model.Group" %><%@
page import="com.liferay.portal.kernel.model.GroupConstants" %><%@
page import="com.liferay.portal.kernel.model.ListType" %><%@
page import="com.liferay.portal.kernel.model.ListTypeConstants" %><%@
page import="com.liferay.portal.kernel.model.ModelHintsConstants" %><%@
page import="com.liferay.portal.kernel.model.ModelHintsUtil" %><%@
page import="com.liferay.portal.kernel.model.Phone" %><%@
page import="com.liferay.portal.kernel.model.Portlet" %><%@
page import="com.liferay.portal.kernel.model.Role" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayPortletURL" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProvider" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProviderUtil" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLUtil" %><%@
page import="com.liferay.portal.kernel.security.auth.PrincipalException" %><%@
page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.ListTypeServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.OrganizationLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.PortletLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.permission.PortletPermissionUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.kernel.theme.ThemeDisplay" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.CharPool" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PortletKeys" %><%@
page import="com.liferay.portal.kernel.util.PrefsParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsUtil" %><%@
page import="com.liferay.portal.kernel.util.StackTraceUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Time" %><%@
page import="com.liferay.portal.kernel.util.TimeZoneUtil" %><%@
page import="com.liferay.portal.kernel.util.UnicodeFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.util.comparator.UserFirstNameComparator" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowInstance" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowTask" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowTaskAssignee" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil" %><%@
page import="com.liferay.taglib.search.ResultRow" %><%@
page import="com.liferay.util.dao.orm.CustomSQLUtil" %>

<%@ page import="java.io.Serializable" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.Format" %><%@
page import="java.text.NumberFormat" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Collections" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.LinkedHashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %><%@
page import="java.util.Properties" %><%@
page import="java.util.Set" %><%@
page import="java.util.TimeZone" %><%@
page import="java.util.TreeMap" %>

<%@ page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletResponse" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.ValidatorException" %><%@
page import="javax.portlet.WindowState" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();

String currentURL = PortalUtil.getCurrentURL(request);

Format fullDateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(DateFormat.FULL, DateFormat.FULL, locale, timeZone);

Format longDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.LONG, locale, timeZone);

Format longDateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(DateFormat.LONG, DateFormat.LONG, locale, timeZone);

Format longDateFormatTime = FastDateFormatFactoryUtil.getTime(DateFormat.LONG, locale, timeZone);

Format shortDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.SHORT, locale, timeZone);

Format shortDateFormatTime = FastDateFormatFactoryUtil.getTime(DateFormat.SHORT, locale, timeZone);

NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
%>