<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.osb.customer.admin.constants.AccountAttachmentConstants" %><%@
page import="com.liferay.osb.customer.admin.constants.AccountEntryConstants" %><%@
page import="com.liferay.osb.customer.admin.constants.AccountEnvironmentConstants" %><%@
page import="com.liferay.osb.customer.admin.constants.LicenseEntryConstants" %><%@
page import="com.liferay.osb.customer.admin.constants.ProductEntryConstants" %><%@
page import="com.liferay.osb.customer.admin.constants.WorkflowConstants" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEntryLanguageIdException" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEntrySupportRegionException" %><%@
page import="com.liferay.osb.customer.admin.exception.DuplicateProductEntryException" %><%@
page import="com.liferay.osb.customer.admin.exception.DuplicateSupportRegionException" %><%@
page import="com.liferay.osb.customer.admin.exception.LicenseEntryNameException" %><%@
page import="com.liferay.osb.customer.admin.exception.LicenseEntryPortalVersionException" %><%@
page import="com.liferay.osb.customer.admin.exception.NoSuchLicenseEntryException" %><%@
page import="com.liferay.osb.customer.admin.exception.NoSuchSupportRegionException" %><%@
page import="com.liferay.osb.customer.admin.exception.ProductEntryEnvironmentException" %><%@
page import="com.liferay.osb.customer.admin.exception.ProductEntryNameException" %><%@
page import="com.liferay.osb.customer.admin.exception.RequiredProductEntryException" %><%@
page import="com.liferay.osb.customer.admin.exception.RequiredSupportRegionException" %><%@
page import="com.liferay.osb.customer.admin.exception.ZendeskTagException" %><%@
page import="com.liferay.osb.customer.admin.model.AccountAttachment" %><%@
page import="com.liferay.osb.customer.admin.model.AccountEntry" %><%@
page import="com.liferay.osb.customer.admin.model.LicenseEntry" %><%@
page import="com.liferay.osb.customer.admin.model.ProductEntry" %><%@
page import="com.liferay.osb.customer.admin.model.SupportRegion" %><%@
page import="com.liferay.osb.customer.admin.service.AccountAttachmentLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.admin.service.AccountEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.admin.service.LicenseEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.admin.service.ProductEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.admin.service.SupportRegionLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.admin.web.internal.configuration.CustomerAdminWebConfigurationValues" %><%@
page import="com.liferay.osb.customer.admin.web.internal.constants.CustomerAdminWebKeys" %><%@
page import="com.liferay.osb.customer.admin.web.internal.search.AccountEntryDisplayTerms" %><%@
page import="com.liferay.osb.customer.admin.web.internal.search.AccountEntrySearch" %><%@
page import="com.liferay.osb.customer.admin.web.internal.search.AccountEntrySearchTerms" %><%@
page import="com.liferay.osb.customer.constants.OSBCustomerConstants" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.ListType" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayPortletURL" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.portal.kernel.service.ListTypeServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.TimeZoneUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.taglib.search.ResultRow" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.List" %><%@
page import="java.util.Properties" %><%@
page import="java.util.TimeZone" %>

<%@ page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();

String currentURL = PortalUtil.getCurrentURL(request);
%>