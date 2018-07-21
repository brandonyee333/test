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

<%@ page import="com.liferay.osb.customer.support.project.details.web.internal.constants.SupportProjectDetailsWebKeys" %><%@
page import="com.liferay.osb.model.AccountEntry" %><%@
page import="com.liferay.osb.model.AccountEntryConstants" %><%@
page import="com.liferay.osb.model.PartnerEntry" %><%@
page import="com.liferay.osb.model.SupportRegion" %><%@
page import="com.liferay.osb.model.SupportResponse" %><%@
page import="com.liferay.osb.service.AccountEntryServiceUtil" %><%@
page import="com.liferay.osb.service.SupportResponseLocalServiceUtil" %><%@
page import="com.liferay.osb.util.comparator.AccountEntryNameComparator" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.Format" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
Format shortDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.SHORT, locale, timeZone);
%>