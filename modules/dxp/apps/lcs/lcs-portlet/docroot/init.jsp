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
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor" %><%@
page import="com.liferay.lcs.exception.LCSExceptionHandler" %><%@
page import="com.liferay.lcs.rest.LCSClusterEntryClientUtil" %><%@
page import="com.liferay.lcs.rest.LCSClusterNodeClientUtil" %><%@
page import="com.liferay.lcs.rest.LCSProjectClientUtil" %><%@
page import="com.liferay.lcs.rest.client.LCSClusterEntry" %><%@
page import="com.liferay.lcs.rest.client.LCSClusterNode" %><%@
page import="com.liferay.lcs.rest.client.LCSProject" %><%@
page import="com.liferay.lcs.task.scheduler.TaskSchedulerService" %><%@
page import="com.liferay.lcs.util.ClusterNodeUtil" %><%@
page import="com.liferay.lcs.util.LCSAlert" %><%@
page import="com.liferay.lcs.util.LCSConnectionManagerUtil" %><%@
page import="com.liferay.lcs.util.LCSUtil" %><%@
page import="com.liferay.lcs.util.PortletPropsValues" %><%@
page import="com.liferay.portal.kernel.bean.BeanLocator" %><%@
page import="com.liferay.portal.kernel.bean.PortletBeanLocatorUtil" %><%@
page import="com.liferay.portal.kernel.cluster.ClusterExecutorUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatConstants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.Date" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %><%@
page import="java.util.Set" %><%@
page import="java.util.TimeZone" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
Format dateFormatDate = FastDateFormatFactoryUtil.getDateTime(FastDateFormatConstants.MEDIUM, FastDateFormatConstants.MEDIUM, locale, timeZone);
Format intervalDateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("HH:mm:ss", TimeZone.getTimeZone(StringPool.UTC));
%>