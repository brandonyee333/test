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