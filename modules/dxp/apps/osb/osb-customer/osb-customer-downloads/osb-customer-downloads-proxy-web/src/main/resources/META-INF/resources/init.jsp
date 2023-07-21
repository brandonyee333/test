<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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