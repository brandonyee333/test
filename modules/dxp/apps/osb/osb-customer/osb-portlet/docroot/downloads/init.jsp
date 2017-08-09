<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.portlet.documentlibrary.model.DLFileVersion" %>

<%
String currentLanguageId = LanguageUtil.getLanguageId(request);

Locale[] locales = {LocaleUtil.US, LocaleUtil.GERMAN, LocaleUtil.FRENCH, LocaleUtil.SPAIN, LocaleUtil.PORTUGAL};

String customerAccessPattern = PrefsParamUtil.getString(portletPreferences, request, "customerAccessPattern", StringPool.BLANK);
String guestAccessPattern = PrefsParamUtil.getString(portletPreferences, request, "guestAccessPattern", StringPool.BLANK);
String trialPattern = PrefsParamUtil.getString(portletPreferences, request, "trialPattern", StringPool.BLANK);

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