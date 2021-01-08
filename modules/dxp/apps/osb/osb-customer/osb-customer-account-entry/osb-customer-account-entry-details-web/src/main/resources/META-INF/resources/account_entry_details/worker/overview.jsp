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

<%@ include file="/account_entry_details/init.jsp" %>

<%
Account koroneikiAccount = accountEntryViewDisplayContext.getAccount();
%>

<aui:row>
	<aui:col width="<%= 25 %>">
		<div class="card">
			<div class="card-header small-title">
				<liferay-ui:message key="details" />
			</div>

			<table class="details-table">
				<tr>
					<td>
						<liferay-ui:message key="project-code" />
					</td>
					<td>
						<c:if test="<%= Validator.isNotNull(koroneikiAccount.getCode()) %>">
							<%= HtmlUtil.escape(koroneikiAccount.getCode()) %>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="state" />
					</td>
					<td>

						<%
						String state = accountEntryViewDisplayContext.getState();
						%>

						<span class="label label-<%= state.toLowerCase() %>"><%= state %></span>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="level" />
					</td>
					<td>
						<c:choose>
							<c:when test="<%= Validator.isNotNull(accountEntryViewDisplayContext.getSupportLevel()) %>">
								<%= accountEntryViewDisplayContext.getSupportLevel() %>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="not-applicable" />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="support-language" />
					</td>
					<td>
						<%= HtmlUtil.escape(accountEntryViewDisplayContext.getSupportLanguage()) %>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="support-region" />
					</td>
					<td>
						<c:choose>
							<c:when test="<%= koroneikiAccount.getRegion() != null %>">
								<%= HtmlUtil.escape(koroneikiAccount.getRegionAsString()) %>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="partner" />
					</td>
					<td>
						<%= accountEntryViewDisplayContext.getPartnerName() %>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="partner-support" />
					</td>
					<td>
						<%= accountEntryViewDisplayContext.getPartnerManagedSupportName() %>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="tier" />
					</td>
					<td>
						<c:if test="<%= Validator.isNotNull(koroneikiAccount.getTierAsString()) %>">
							<%= koroneikiAccount.getTier() %>
						</c:if>
					</td>
				</tr>
			</table>
		</div>
	</aui:col>

	<aui:col width="<%= 40 %>">
		<liferay-util:include page="/account_entry_details/account_environments.jsp" servletContext="<%= application %>" />
	</aui:col>

	<aui:col width="<%= 35 %>">
		<liferay-util:include page="/account_entry_details/support_instructions.jsp" servletContext="<%= application %>" />
	</aui:col>
</aui:row>