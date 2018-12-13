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

<style type="text/css">
	.task-change-status-link [id*="closetaskChangeStatusLink"] {
		display: none;
	}
</style>

<liferay-util:buffer
	var="html"
>
	<liferay-util:include page="/workflow_task_action.portal.jsp" servletContext="<%= application %>" />
</liferay-util:buffer>

<%
html = html.replace(renderResponse.getNamespace() + "mvcPath", renderResponse.getNamespace() + "osbMVCPath");
html = html.replace(renderResponse.getNamespace() + "workflowTaskId", renderResponse.getNamespace() + "osbWorkflowTaskId");
%>

<%= html %>