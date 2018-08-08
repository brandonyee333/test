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
AccountEntry accountEntry = accountEntryViewDisplayContext.getAccountEntry();
%>

<aui:row>
	<aui:col width="<%= 25 %>">
		<div class="card">
			<h3 class="card-header">
				<liferay-ui:message key="details" />
			</h3>

			<table class="details-table">
				<tr>
					<td class="secondary-text-color">
						<liferay-ui:message key="status" />
					</td>
					<td>
						<span class="label label-<%= accountEntry.getStatusLabel() %>"><%= accountEntry.getStatusLabel() %></span>
					</td>
				</tr>
				<tr>
					<td class="secondary-text-color">
						<liferay-ui:message key="level" />
					</td>
					<td>

						<%
						SupportResponse supportResponse = SupportResponseLocalServiceUtil.getSupportResponse(accountEntry.getHighestSupportResponseId());
						%>

						<%= supportResponse.getName() %>
					</td>
				</tr>
				<tr>
					<td class="secondary-text-color">
						<liferay-ui:message key="support-region" />
					</td>
					<td>

						<%
						List<SupportRegion> supportRegions = accountEntry.getSupportRegions();

						SupportRegion supportRegion = supportRegions.get(0);
						%>

						<%= HtmlUtil.escape(supportRegion.getName()) %>
					</td>
				</tr>
				<tr>
					<td class="secondary-text-color">
						<liferay-ui:message key="partner" />
					</td>
					<td>
						<c:if test="<%= accountEntry.getPartnerEntryId() > 0 %>">

							<%
							PartnerEntry partnerEntry = accountEntry.getPartnerEntry();
							%>

							<%= HtmlUtil.escape(partnerEntry.getCode()) %>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="secondary-text-color">
						<liferay-ui:message key="partner-support" />
					</td>
					<td>
						<c:if test="<%= accountEntry.getPartnerEntryId() > 0 %>">
							<%= accountEntry.isPartnerManagedSupport() ? LanguageUtil.get(request, "yes") : LanguageUtil.get(request, "no") %>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="secondary-text-color">
						<liferay-ui:message key="tier" />
					</td>
					<td>
						<%= LanguageUtil.get(request, AccountEntryConstants.getTierLabel(accountEntry.getTier())) %>
					</td>
				</tr>
				<tr>
					<td class="secondary-text-color">
						<liferay-ui:message key="industry" />
					</td>
					<td>
						<%= LanguageUtil.get(request, accountEntry.getIndustryLabel()) %>
					</td>
				</tr>
				<tr>
					<td class="secondary-text-color">
						<liferay-ui:message key="last-modified" />
					</td>
					<td>
						<%= shortDateFormatDate.format(accountEntry.getModifiedDate()) %>
					</td>
				</tr>
				<tr>
					<td class="secondary-text-color">
						<liferay-ui:message key="modified-by" />
					</td>
					<td>
						<%= HtmlUtil.escape(PortalUtil.getUserName(accountEntry.getModifiedUserId(), accountEntry.getModifiedUserName())) %>
					</td>
				</tr>
			</table>
		</div>
	</aui:col>

	<aui:col width="<%= 40 %>">
		<liferay-util:include page="/account_entry_details/account_environments.jsp" servletContext="<%= application %>" />
	</aui:col>

	<aui:col width="<%= 33 %>">
		test3
	</aui:col>
</aui:row>