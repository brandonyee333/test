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

<%@ page import="com.liferay.osb.customer.account.entry.details.web.internal.configuration.AccountEntryDetailsConfiguration" %><%@
page import="com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsWebKeys" %><%@
page import="com.liferay.osb.customer.account.entry.details.web.internal.dao.search.TicketAttachmentResultRowSplitter" %><%@
page import="com.liferay.osb.customer.account.entry.details.web.internal.display.context.AccountEntrySearchDisplayContext" %><%@
page import="com.liferay.osb.customer.account.entry.details.web.internal.display.context.AccountEntryViewDisplayContext" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEnvironmentAttachmentSizeException" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEnvironmentEnvASException" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEnvironmentEnvBrowserException" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEnvironmentEnvCSException" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEnvironmentEnvDBException" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEnvironmentEnvLFRException" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEnvironmentEnvOSException" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEnvironmentEnvSearchException" %><%@
page import="com.liferay.osb.customer.admin.exception.AccountEnvironmentNameException" %><%@
page import="com.liferay.osb.customer.admin.exception.DuplicateAccountEnvironmentException" %><%@
page import="com.liferay.osb.customer.admin.exception.NoSuchAccountEntryException" %><%@
page import="com.liferay.osb.customer.admin.model.AccountEntry" %><%@
page import="com.liferay.osb.customer.admin.service.permission.AccountEnvironmentPermission" %><%@
page import="com.liferay.osb.customer.constants.OSBActionKeys" %><%@
page import="com.liferay.osb.customer.github.configuration.GitHubConfigurationValues" %><%@
page import="com.liferay.osb.customer.koroneiki.constants.ContactRoleConstants" %><%@
page import="com.liferay.osb.customer.ticket.exception.FileRepositoryConnectionException" %><%@
page import="com.liferay.osb.customer.ticket.exception.NoSuchTicketAttachmentException" %><%@
page import="com.liferay.osb.customer.ticket.model.TicketAttachment" %><%@
page import="com.liferay.osb.customer.ticket.repository.FileRepository" %><%@
page import="com.liferay.osb.customer.ticket.repository.FileRepositoryManager" %><%@
page import="com.liferay.osb.customer.ticket.repository.FileRepositoryWebService" %><%@
page import="com.liferay.osb.customer.ticket.service.permission.TicketAttachmentPermissionChecker" %><%@
page import="com.liferay.osb.customer.zendesk.exception.ZendeskTicketClosedException" %><%@
page import="com.liferay.osb.customer.zendesk.web.service.exception.NoSuchZendeskTicketException" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.AuditEntry" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchUserException" %><%@
page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Phone" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.security.auth.PrincipalException" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.TextFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.taglib.search.ResultRow" %>

<%@ page import="java.io.FileNotFoundException" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.Format" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.ResourceURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
AccountEntryDetailsConfiguration accountEntryDetailsConfiguration = (AccountEntryDetailsConfiguration)liferayPortletRequest.getAttribute(AccountEntryDetailsConfiguration.class.getName());
FileRepositoryManager fileRepositoryManager = (FileRepositoryManager)liferayPortletRequest.getAttribute(FileRepositoryManager.class.getName());
FileRepositoryWebService fileRepositoryWebService = (FileRepositoryWebService)liferayPortletRequest.getAttribute(FileRepositoryWebService.class.getName());

Format fullDateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(DateFormat.FULL, DateFormat.FULL, locale, timeZone);

Format shortDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.SHORT, locale, timeZone);

Format shortDateFormatTime = FastDateFormatFactoryUtil.getTime(DateFormat.SHORT, locale, timeZone);
%>

<aui:script>
	window.AccountDetailsConstants = {
		namespace: '${renderResponse.namespace}'
	};
</aui:script>