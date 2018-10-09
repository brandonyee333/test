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

<%@ include file="/init.jsp" %>

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

String fileDirectory = PrefsParamUtil.getString(portletPreferences, request, "fileDirectory", StringPool.BLANK);
String downloadPage = PrefsParamUtil.getString(portletPreferences, request, "downloadPage", StringPool.BLANK);

String studioEulaUrl = PrefsParamUtil.getString(portletPreferences, request, "studioEulaUrl_" + currentLanguageId, StringPool.BLANK);
String studioEulaVersion = PrefsParamUtil.getString(portletPreferences, request, "studioEulaVersion_" + currentLanguageId, StringPool.BLANK);
String studioEulaVersionRequired = PrefsParamUtil.getString(portletPreferences, request, "studioEulaVersionRequired_" + currentLanguageId, StringPool.BLANK);
%>