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
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.osb.customer.account.entry.details.web.configuration.AccountEntryDetailsConfiguration" %><%@
page import="com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsWebKeys" %><%@
page import="com.liferay.osb.customer.account.entry.details.web.internal.display.context.AccountEntryViewDisplayContext" %><%@
page import="com.liferay.osb.customer.ticket.exception.NoSuchTicketAttachmentException" %><%@
page import="com.liferay.osb.customer.ticket.repository.FileRepository" %><%@
page import="com.liferay.osb.customer.ticket.repository.FileRepositoryManager" %><%@
page import="com.liferay.osb.customer.ticket.repository.FileRepositoryWebService" %><%@
page import="com.liferay.osb.customer.zendesk.exception.ZendeskTicketClosedException" %><%@
page import="com.liferay.osb.customer.zendesk.web.service.exception.NoSuchZendeskTicketException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentAttachmentSizeException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvASException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvBrowserException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvCSException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvDBException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvLFRException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvOSException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentEnvSearchException" %><%@
page import="com.liferay.osb.exception.AccountEnvironmentNameException" %><%@
page import="com.liferay.osb.exception.DuplicateAccountEnvironmentException" %><%@
page import="com.liferay.osb.exception.NoSuchAccountEntryException" %><%@
page import="com.liferay.osb.model.AccountEntry" %><%@
page import="com.liferay.osb.model.AccountEntryConstants" %><%@
page import="com.liferay.osb.model.AuditEntry" %><%@
page import="com.liferay.osb.model.AuditEntryConstants" %><%@
page import="com.liferay.osb.model.OfferingEntryConstants" %><%@
page import="com.liferay.osb.model.OfferingEntryGroupFactoryUtil" %><%@
page import="com.liferay.osb.model.PartnerEntry" %><%@
page import="com.liferay.osb.model.ProductEntry" %><%@
page import="com.liferay.osb.model.SupportRegion" %><%@
page import="com.liferay.osb.model.SupportResponse" %><%@
page import="com.liferay.osb.service.AccountCustomerLocalServiceUtil" %><%@
page import="com.liferay.osb.service.AccountEntryServiceUtil" %><%@
page import="com.liferay.osb.service.AccountWorkerLocalServiceUtil" %><%@
page import="com.liferay.osb.service.AuditEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.service.SupportResponseLocalServiceUtil" %><%@
page import="com.liferay.osb.service.permission.OSBAccountEnvironmentPermission" %><%@
page import="com.liferay.osb.util.OSBActionKeys" %><%@
page import="com.liferay.osb.util.OSBConstants" %><%@
page import="com.liferay.osb.util.VisibilityConstants" %><%@
page import="com.liferay.osb.util.comparator.AccountEntryNameComparator" %><%@
page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Phone" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.io.FileNotFoundException" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.Format" %>

<%@ page import="java.util.LinkedHashMap" %><%@
page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
AccountEntryDetailsConfiguration accountEntryDetailsConfiguration = (AccountEntryDetailsConfiguration)liferayPortletRequest.getAttribute(AccountEntryDetailsConfiguration.class.getName());
FileRepositoryManager fileRepositoryManager = (FileRepositoryManager)liferayPortletRequest.getAttribute(FileRepositoryManager.class.getName());
FileRepositoryWebService fileRepositoryWebService = (FileRepositoryWebService)liferayPortletRequest.getAttribute(FileRepositoryWebService.class.getName());

Format fullDateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(DateFormat.FULL, DateFormat.FULL, locale, timeZone);

Format longDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.LONG, locale, timeZone);

Format shortDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.SHORT, locale, timeZone);

Format shortDateFormatTime = FastDateFormatFactoryUtil.getTime(DateFormat.SHORT, locale, timeZone);
%>

<aui:script>
	window.AccountDetailsConstants = {
		namespace: '${renderResponse.namespace}'
	};
</aui:script>