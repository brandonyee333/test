<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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