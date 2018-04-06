<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/support/2/init.jsp" %>

<%

//Map<Integer, List<CorpProjectMessage>> corpProjectMessagesMap = CorpProjectMessageLocalServiceUtil.getCorpProjectMessagesMap(user.getUserId(), false, true);

%>

<div class="margin-container subscription-messages">
	<h1 class="page-title">
		<liferay-ui:message key="liferay-subscription-messages" />
	</h1>

	<%-- <c:choose>
		<c:when test="<%= !corpProjectMessagesMap.isEmpty() %>">

			<%
			for (Map.Entry<Integer, List<CorpProjectMessage>> entry : corpProjectMessagesMap.entrySet()) {
				List<CorpProjectMessage> corpProjectMessages = entry.getValue();

				CorpProjectMessage firstCorpProjectMessage = corpProjectMessages.get(0);

				String severityLevelLabel = firstCorpProjectMessage.getSeverityLevelLabel();
			%>

				<liferay-ui:panel-container
					id="<%= severityLevelLabel %>"
					persistState="<%= true %>"
				>
					<h2 class="section-title">
						<liferay-ui:message arguments="<%= corpProjectMessages.size() %>" key='<%= severityLevelLabel + "-messages-x" %>' />
					</h2>

					<%
					for (int i = 0; i < corpProjectMessages.size(); i++) {
						CorpProjectMessage corpProjectMessage = corpProjectMessages.get(i);

						AccountEntry accountEntry = corpProjectMessage.getAccountEntry();

						String title = corpProjectMessage.getTitle();

						if (Validator.isNull(title)) {
							title = corpProjectMessage.getContent();
						}

						String panelTitle = HtmlUtil.escape(accountEntry.getName() + ": " + title);
					%>

						<liferay-ui:panel
							collapsible="<%= true %>"
							cssClass="<%= severityLevelLabel %>"
							defaultState="closed"
							id="<%= severityLevelLabel + corpProjectMessage.getCorpProjectMessageId() %>"
							persistState="<%= true %>"
							title="<%= panelTitle %>"
						>
							<div class="inline">
								<%= HtmlUtil.escape(corpProjectMessage.getContent()) %>
							</div>
						</liferay-ui:panel>

					<%
					}
					%>

				</liferay-ui:panel-container>

			<%
			}
			%>

		</c:when>
		<c:otherwise>
			<div class="portlet-msg-info">
				<liferay-ui:message key="there-are-no-messages-regarding-your-liferay-subscription-at-this-time" />
			</div>
		</c:otherwise>
	</c:choose> --%>
</div>

<aui:script>
	<portlet:namespace />navSelect('messages');
</aui:script>