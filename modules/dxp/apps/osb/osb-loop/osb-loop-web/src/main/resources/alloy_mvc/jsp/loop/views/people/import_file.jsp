<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/loop/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/loop/views/toolbar.jspf" %>

<c:set value="Please specify a file (xls or xlt):" var="title" />

<portlet:actionURL var="uploadFileURL">
	<portlet:param name="controller" value="people" />
	<portlet:param name="action" value="importExtraData" />
</portlet:actionURL>

<aui:form action="${uploadFileURL}" enctype="multipart/form-data" method="post">
	<portlet:renderURL var="viewLoopHomeURL">
		<portlet:param name="controller" value="home" />
		<portlet:param name="action" value="index" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewLoopHomeURL}" />

	<aui:input label="${title}" name="file" type="file" />

	<aui:button-row>
		<aui:button cssClass="btn-container" type="submit" value="upload" />

		<aui:button cssClass="btn-container cancel" href="${viewLoopHomeURL}" value="cancel" />
	</aui:button-row>
</aui:form>