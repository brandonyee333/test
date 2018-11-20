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

<%
String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "assigned-to-my-roles");
%>

<c:choose>
	<c:when test='<%= tabs1.equals("other-assignees") %>'>
		<liferay-util:include page="/other_assignees.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>

		<%
		DateSearchEntry dateSearchEntry = new DateSearchEntry();

		String displayStyle = workflowTaskDisplayContext.getDisplayStyle();
		%>

		<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>" />

		<div class="container-fluid-1280 main-content-body">
			<c:choose>
				<c:when test='<%= tabs1.equals("assigned-to-me") %>'>

					<%
					WorkflowTaskSearch workflowTaskSearch = workflowTaskDisplayContext.getTasksAssignedToMe();
					%>

					<%@ include file="/workflow_tasks.jspf" %>
				</c:when>
				<c:otherwise>

					<%
					WorkflowTaskSearch workflowTaskSearch = workflowTaskDisplayContext.getTasksAssignedToMyRoles();
					%>

					<%@ include file="/workflow_tasks.jspf" %>
				</c:otherwise>
			</c:choose>
		</div>
	</c:otherwise>
</c:choose>