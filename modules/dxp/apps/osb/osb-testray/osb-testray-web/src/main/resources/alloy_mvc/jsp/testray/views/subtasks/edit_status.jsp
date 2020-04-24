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
	<liferay-util:param name="title" value="edit-status" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="updateStatusURL">
	<portlet:param name="controller" value="subtasks" />
	<portlet:param name="action" value="updateStatus" />
</portlet:actionURL>

<aui:form action="${updateStatusURL}" name="fm">
	<aui:input name="redirect" type="hidden" />

	<aui:input name="id" type="hidden" value="${testraySubtaskComposite.testraySubtaskId}" />

	<aui:select label="case-results-status" name="status" showEmptyOption="${false}">
		<aui:option label="${TestrayCaseResultConstants.LABEL_BLOCKED}" value="${TestrayCaseResultConstants.STATUS_BLOCKED}" />
		<aui:option label="${TestrayCaseResultConstants.LABEL_FAILED}" selected="${true}" value="${TestrayCaseResultConstants.STATUS_FAILED}" />
		<aui:option label="${TestrayCaseResultConstants.LABEL_PASSED}" value="${TestrayCaseResultConstants.STATUS_PASSED}" />
		<aui:option label="${TestrayCaseResultConstants.LABEL_TEST_FIX}" value="${TestrayCaseResultConstants.STATUS_TEST_FIX}" />
	</aui:select>

	<aui:input name="issues" type="text" />

	<aui:input name="comment" type="textarea" value="${testraySubtaskComposite.comment}" />

	<aui:button-row>
		<aui:button type="submit" value="save" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>