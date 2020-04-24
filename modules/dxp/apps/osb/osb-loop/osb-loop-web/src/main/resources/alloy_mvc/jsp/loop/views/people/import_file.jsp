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