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
long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");

TicketEntry ticketEntry = TicketEntryServiceUtil.getTicketEntry(ticketEntryId);

long accountEntryId = ParamUtil.getLong(request, "accountEntryId", ticketEntry.getAccountEntryId());

OfferingEntry offeringEntry = ticketEntry.getOfferingEntry();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/select_offering_entry.jsp");
portletURL.setParameter("ticketEntryId", String.valueOf(ticketEntryId));
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));

boolean addTicketPermission = OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.ADD_TICKET);
%>

<portlet:renderURL var="selectOfferingEntryURL">
	<portlet:param name="mvcPath" value="/support/2/select_offering_entry.jsp" />
	<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntryId) %>" />
</portlet:renderURL>

<aui:form action="<%= selectOfferingEntryURL %>" method="post" name="fm" onSubmit="submitForm(this); return false;">
	<div class="unit">
		<div>
			<h2 class="section-heading">
				<liferay-ui:message key="project" />
			</h2>

			<%
			List<AccountEntry> accountEntries = null;

			if (liferayIncOrg || RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {
				accountEntries = AccountEntryLocalServiceUtil.getAccountEntries(new int[] {AccountEntryConstants.TYPE_TRIAL}, AccountEntryConstants.STATUSES_ACTIVE, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			}
			else {
				accountEntries = AccountEntryLocalServiceUtil.getUserAccountEntries(user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			}
			%>

			<div>
				<c:choose>
					<c:when test="<%= accountEntries.size() == 1 %>">

						<%
						AccountEntry curAccountEntry = accountEntries.get(0);

						accountEntryId = curAccountEntry.getAccountEntryId();

						addTicketPermission = OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.ADD_TICKET);
						%>

						<strong><%= HtmlUtil.escape(curAccountEntry.getName()) %></strong>

						<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntryId %>" />
					</c:when>
					<c:when test="<%= RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID) || RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_SUPPORT_ADMIN_ID) %>">
						<select name="<portlet:namespace />accountEntryId" onChange="submitForm(document.<portlet:namespace />fm);">

							<%
							for (AccountEntry curAccountEntry : accountEntries) {
							%>

								<option <%= (curAccountEntry.getAccountEntryId() == accountEntryId) ? "selected" : "" %> value="<%= curAccountEntry.getAccountEntryId() %>"><%= curAccountEntry.getName() %></option>

							<%
							}
							%>

						</select>
					</c:when>
					<c:otherwise>

						<%
						AccountEntry accountEntry = ticketEntry.getAccountEntry();
						%>

						<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntry.getAccountEntryId() %>" />

						<strong><%= HtmlUtil.escape(accountEntry.getName()) %></strong>
					</c:otherwise>
				</c:choose>
			</div>

			<h2 class="section-heading">
				<liferay-ui:message key="choose-support" />
			</h2>

			<div>
				<liferay-ui:search-container
						delta="<%= 10 %>"
						headerNames="product,support,start-date,support-end-date,tickets-used"
						iteratorURL="<%= portletURL %>"
					>

					<%
					LinkedHashMap params = new LinkedHashMap();

					params.put("validTicket", StringPool.BLANK);

					List<OfferingEntryGroup> offeringEntryGroups = SupportUtil.getOfferingEntryGroups(0, accountEntryId, new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true);
					%>

					<liferay-ui:search-container-results
						results="<%= ListUtil.subList(offeringEntryGroups, searchContainer.getStart(), searchContainer.getEnd()) %>"
						total="<%= offeringEntryGroups.size() %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.osb.model.OfferingEntryGroup"
						modelVar="offeringEntryGroup"
					>

						<%
						AccountEntry curAccountEntry = offeringEntryGroup.getAccountEntry();
						ProductEntry curProductEntry = offeringEntryGroup.getProductEntry();
						SupportResponse curSupportResponse = offeringEntryGroup.getSupportResponse();

						String rowHREF = null;

						Date now = new Date();

						String key = offeringEntryGroup.getKey();

						if (!key.equals(offeringEntry.getKey()) && offeringEntryGroup.hasAvailableSupportTickets()) {
							OfferingEntry availableOfferingEntry = offeringEntryGroup.getAvailableSupportOfferingEntry();

							StringBuilder sb = new StringBuilder();

							sb.append("javascript:opener.");
							sb.append(renderResponse.getNamespace());
							sb.append("selectOfferingEntry('");
							sb.append(curAccountEntry.getAccountEntryId());
							sb.append("', '");
							sb.append(availableOfferingEntry.getOfferingEntryId());
							sb.append("', '");
							sb.append(UnicodeFormatter.toString(curAccountEntry.getName()));
							sb.append("', '");
							sb.append(UnicodeFormatter.toString(curSupportResponse.getName()));
							sb.append("', '");
							sb.append(UnicodeFormatter.toString((curProductEntry.getName())));
							sb.append("'); window.close();");

							rowHREF = sb.toString();
						}
						%>

						<liferay-ui:search-container-column-text
							href="<%= rowHREF %>"
							name="product"
							value="<%= curProductEntry.getName() %>"
						/>

						<liferay-ui:search-container-column-text
							href="<%= rowHREF %>"
							name="sla"
							value="<%= curSupportResponse.getName() %>"
						/>

						<liferay-ui:search-container-column-text
							href="<%= rowHREF %>"
							name="start-date"
							value="<%= longDateFormatDate.format(offeringEntryGroup.getActualStartDate()) %>"
						/>

						<liferay-ui:search-container-column-text
							href="<%= rowHREF %>"
							name="support-end-date"
							value="<%= longDateFormatDate.format(offeringEntryGroup.getSupportEndDate()) %>"
						/>

						<liferay-ui:search-container-column-text
							href="<%= rowHREF %>"
							name="tickets-used"
						>
							<%= offeringEntryGroup.getTicketEntriesCount() %> / <%= offeringEntryGroup.isSupportTickets() ? LanguageUtil.get(pageContext, "unlimited") : "0" %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							href="<%= rowHREF %>"
						>
							<c:choose>
								<c:when test="<%= key.equals(offeringEntry.getKey()) %>">
									<liferay-ui:icon
										image="checked"
										label="<%= true %>"
										message="current"
									/>
								</c:when>
								<c:otherwise>
									<input class="aui-button-input" onClick="<%= rowHREF %>" type="button" value="<liferay-ui:message key="choose" />" />
								</c:otherwise>
							</c:choose>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
			</div>
		</div>
	</div>
</aui:form>