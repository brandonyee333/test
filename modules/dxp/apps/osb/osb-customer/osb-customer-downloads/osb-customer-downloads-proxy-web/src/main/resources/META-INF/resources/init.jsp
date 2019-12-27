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

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PrefsParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.util.Locale" %>

<%@ page import="javax.portlet.WindowState" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String currentLanguageId = LanguageUtil.getLanguageId(request);

Locale[] locales = {LocaleUtil.US, LocaleUtil.GERMAN, LocaleUtil.FRENCH, LocaleUtil.SPAIN, LocaleUtil.PORTUGAL};

String customerAccessPattern = PrefsParamUtil.getString(portletPreferences, request, "customerAccessPattern", StringPool.BLANK);
String guestAccessPattern = PrefsParamUtil.getString(portletPreferences, request, "guestAccessPattern", StringPool.BLANK);
String trialPattern = PrefsParamUtil.getString(portletPreferences, request, "trialPattern", StringPool.BLANK);

String esaUrl = PrefsParamUtil.getString(portletPreferences, request, "esaUrl_" + currentLanguageId, StringPool.BLANK);
String esaVersion = PrefsParamUtil.getString(portletPreferences, request, "esaVersion_" + currentLanguageId, StringPool.BLANK);
String esaVersionRequired = PrefsParamUtil.getString(portletPreferences, request, "esaVersionRequired_" + currentLanguageId, StringPool.BLANK);

String evaluationEulaUrl = PrefsParamUtil.getString(portletPreferences, request, "evaluationEulaUrl_" + currentLanguageId, StringPool.BLANK);
String evaluationEulaVersion = PrefsParamUtil.getString(portletPreferences, request, "evaluationEulaVersion_" + currentLanguageId, StringPool.BLANK);
String evaluationEulaVersionRequired = PrefsParamUtil.getString(portletPreferences, request, "evaluationEulaVersionRequired_" + currentLanguageId, StringPool.BLANK);

String studioEulaUrl = PrefsParamUtil.getString(portletPreferences, request, "studioEulaUrl_" + currentLanguageId, StringPool.BLANK);
String studioEulaVersion = PrefsParamUtil.getString(portletPreferences, request, "studioEulaVersion_" + currentLanguageId, StringPool.BLANK);
String studioEulaVersionRequired = PrefsParamUtil.getString(portletPreferences, request, "studioEulaVersionRequired_" + currentLanguageId, StringPool.BLANK);
%>