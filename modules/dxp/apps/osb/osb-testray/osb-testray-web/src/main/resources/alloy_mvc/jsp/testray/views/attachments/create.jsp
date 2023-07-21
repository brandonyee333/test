<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="new-attachment" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="addAttachmentURL">
	<portlet:param name="controller" value="attachments" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<aui:form action="${addAttachmentURL}" enctype="multipart/form-data" method="post">
	<aui:input name="testrayCaseResultId" type="hidden" value="${param.testrayCaseResultId}" />

	<aui:input autoFocus="${true}" name="name" required="${true}" type="text" />

	<aui:input name="file" required="${true}" type="file" />

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>