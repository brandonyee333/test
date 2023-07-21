<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
SearchEntry entry = (SearchEntry)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW_ENTRY);

AnnouncementsDelivery delivery = (AnnouncementsDelivery)row.getObject();

boolean defaultValue = false;
boolean disabled = false;
String messageKey = StringPool.BLANK;
String param = "announcementsType" + delivery.getType();

int index = entry.getIndex();

if (index == 1) {
	defaultValue = delivery.isEmail();
	messageKey = "receive-x-announcements-via-email";
	param += "Email";
}
else if (index == 2) {
	defaultValue = delivery.isSms();
	messageKey = "receive-x-announcements-via-sms";
	param += "Sms";
}
else if (index == 3) {
	defaultValue = delivery.isWebsite();
	disabled = true;
	messageKey = "receive-x-announcements-via-website";
	param += "Website";
}
%>

<aui:input disabled="<%= disabled %>" label="" name="<%= param %>" title="<%= LanguageUtil.format(request, messageKey, delivery.getType()) %>" type="checkbox" value="<%= defaultValue %>" />