<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="<%= windowState.equals(WindowState.NORMAL) %>">
		<liferay-ui:success key="invitationSent" message="your-invitations-have-been-sent" />

		<portlet:renderURL var="viewURL" windowState="<%= WindowState.MAXIMIZED.toString() %>" />

		<aui:a href="<%= viewURL %>" label="invite-friends" />
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="/invitation/view" var="portletURL" />

		<portlet:renderURL var="redirectURL" windowState="<%= WindowState.NORMAL.toString() %>" />

		<aui:form action="<%= portletURL %>" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />

			<div class="alert alert-info">

				<%
				int emailMessageMaxRecipients = InvitationUtil.getEmailMessageMaxRecipients(portletPreferences);
				%>

				<liferay-ui:message arguments="<%= emailMessageMaxRecipients %>" key="enter-up-to-x-email-addresses-of-friends-you-would-like-to-invite" translateArguments="<%= false %>" />
			</div>

			<%
			Set invalidEmailAddresses = (Set)SessionErrors.get(renderRequest, "emailAddresses");

			for (int i = 0; i < emailMessageMaxRecipients; i++) {
			%>

				<c:if test='<%= (invalidEmailAddresses != null) && invalidEmailAddresses.contains("emailAddress" + i) %>'>
					<div class="alert alert-danger">
						<liferay-ui:message key="please-enter-a-valid-email-address" />
					</div>
				</c:if>

				<aui:input cssClass="lfr-input-text-container" label="" name='<%= "emailAddress" + i %>' size="65" title='<%= LanguageUtil.get(request, "email-address") + StringPool.SPACE + (i + 1) %>' type="text" value='<%= ParamUtil.getString(request, "emailAddress" + i) %>' />

			<%
			}
			%>

			<aui:button-row>
				<aui:button cssClass="btn-lg" type="submit" value="invite-friends" />
			</aui:button-row>
		</aui:form>
	</c:otherwise>
</c:choose>