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

<%@ page import="com.liferay.document.library.kernel.model.DLFileVersion" %>

<%
String currentLanguageId = LanguageUtil.getLanguageId(request);

Locale[] locales = {LocaleUtil.US, LocaleUtil.GERMAN, LocaleUtil.FRENCH, LocaleUtil.SPAIN, LocaleUtil.PORTUGAL};

String customerAccessPattern = PrefsParamUtil.getString(portletPreferences, request, "customerAccessPattern", StringPool.BLANK);
String guestAccessPattern = PrefsParamUtil.getString(portletPreferences, request, "guestAccessPattern", StringPool.BLANK);
String trialPattern = PrefsParamUtil.getString(portletPreferences, request, "trialPattern", StringPool.BLANK);

long evaluationEulaFileEntryId = PrefsParamUtil.getLong(portletPreferences, request, "evaluationEulaFileEntryId_" + currentLanguageId, 0L);
String evaluationEulaVersion = PrefsParamUtil.getString(portletPreferences, request, "evaluationEulaVersion_" + currentLanguageId, StringPool.BLANK);
String evaluationEulaVersionRequired = PrefsParamUtil.getString(portletPreferences, request, "evaluationEulaVersionRequired_" + currentLanguageId, StringPool.BLANK);

String evaluationEulaFileTitle = StringPool.BLANK;

if (evaluationEulaFileEntryId > 0) {
	try {
		DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(evaluationEulaFileEntryId);

		evaluationEulaFileTitle = fileEntry.getTitle();
	}
	catch (NoSuchFileEntryException nsfee) {
	}
}

String fileDirectory = PrefsParamUtil.getString(portletPreferences, request, "fileDirectory", StringPool.BLANK);
String downloadPage = PrefsParamUtil.getString(portletPreferences, request, "downloadPage", StringPool.BLANK);

long studioEulaFileEntryId = PrefsParamUtil.getLong(portletPreferences, request, "studioEulaFileEntryId_" + currentLanguageId, 0L);
String studioEulaVersion = PrefsParamUtil.getString(portletPreferences, request, "studioEulaVersion_" + currentLanguageId, StringPool.BLANK);
String studioEulaVersionRequired = PrefsParamUtil.getString(portletPreferences, request, "studioEulaVersionRequired_" + currentLanguageId, StringPool.BLANK);

DLFileEntry fileEntry = null;

String studioEulaFileTitle = StringPool.BLANK;

if (studioEulaFileEntryId > 0) {
	try {
		fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(studioEulaFileEntryId);

		studioEulaFileTitle = fileEntry.getTitle();
	}
	catch (NoSuchFileEntryException nsfee) {
	}
}
%>