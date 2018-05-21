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
List<String> warningMessages = (List<String>)request.getAttribute("warning_messages.jsp-warningMessages");
%>

<c:if test="<%= (warningMessages != null) && !warningMessages.isEmpty() %>">
	<h3>
		<liferay-ui:message key="warning-messages" />
	</h3>

	<ul>

		<%
		for (String warningMessage : warningMessages) {
		%>

			<li>
				<%= warningMessage %>
			</li>

		<%
		}
		%>

	</ul>
</c:if>