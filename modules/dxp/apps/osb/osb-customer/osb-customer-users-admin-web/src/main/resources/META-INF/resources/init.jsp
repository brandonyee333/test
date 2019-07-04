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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.osb.customer.users.admin.web.internal.constants.OSBPortletKeys" %><%@
page import="com.liferay.osb.model.AccountCustomer" %><%@
page import="com.liferay.osb.model.AccountWorker" %><%@
page import="com.liferay.osb.model.PartnerEntry" %><%@
page import="com.liferay.osb.model.PartnerWorker" %><%@
page import="com.liferay.osb.model.SupportRegion" %><%@
page import="com.liferay.osb.service.AccountCustomerLocalServiceUtil" %><%@
page import="com.liferay.osb.service.AccountEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.service.AccountWorkerLocalServiceUtil" %><%@
page import="com.liferay.osb.service.PartnerEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.service.PartnerWorkerLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>

<%@ page import="java.util.LinkedHashMap" %><%@
page import="java.util.List" %>

<%@ page import="javax.portlet.WindowState" %>

<liferay-theme:defineObjects />