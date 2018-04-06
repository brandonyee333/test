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
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

String url1 = ParamUtil.getString(request, "url1");
String url2 = ParamUtil.getString(request, "url2");
String url3 = ParamUtil.getString(request, "url3");
int visibility = ParamUtil.getInteger(request, "visibility");

int[] userVisibilities = VisibilityConstants.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());

List<TicketLink> ticketLinks = TicketLinkLocalServiceUtil.getTicketLinks(ticketEntry.getTicketEntryId(), userVisibilities);

PortletURL portletURL = (PortletURL)request.getAttribute("edit_ticket_entry.jsp-portletURL");
%>

<liferay-ui:error exception="<%= TicketLinkURLException.class %>" message="please-enter-a-valid-url" />
<liferay-ui:error exception="<%= TicketLinkVisibilityException.class %>" message="please-enter-a-valid-visibility" />

<div class="links">
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-links"
		headerNames="url,visibility"
		total="<%= ticketLinks.size() %>"
	>
		<liferay-ui:search-container-results
			results="<%= ticketLinks %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.TicketLink"
			escapedModel="<%= true %>"
			keyProperty="ticketLinkId"
			modelVar="ticketLink"
		>
			<liferay-ui:search-container-column-text
				href="<%= ticketLink.getUrl() %>"
				name="url"
				target="_blank"
				value="<%= StringUtil.shorten(ticketLink.getUrl(), 115) %>"
			/>

			<c:if test="<%= userVisibilities.length > 1 %>">
				<liferay-ui:search-container-column-text
					href="<%= ticketLink.getUrl() %>"
					name="visibility"
					target="_blank"
					translate="<%= true %>"
					value="<%= ticketLink.getVisibilityLabel() %>"
				/>
			</c:if>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/support/2/edit_ticket_entry/ticket_link_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>

	<br />

	<c:if test="<%= !screenShareMode && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ADD_LINK) %>">
		<portlet:actionURL name="addTicketLink" var="addTicketLinkURL">
			<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
			<portlet:param name="generalTab" value="links" />
		</portlet:actionURL>

		<aui:form action="<%= addTicketLinkURL %>" method="post" name="fm4">
			<input name="<portlet:namespace />redirect" type="hidden" value="<%= portletURL.toString() %>" />
			<input name="<portlet:namespace />ticketEntryId" type="hidden" value="<%= ticketEntry.getTicketEntryId() %>" />

			<fieldset>
				<legend><liferay-ui:message key="add-link" /></legend>

				<table class="lfr-table">
					<tr>
						<td>
							<liferay-ui:message key="url" /> 1
						</td>
						<td>
							<input class="lfr-input-text" name="<portlet:namespace />url1" style="width: 350px;" type="text" value="<%= HtmlUtil.escapeAttribute(url1) %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="url" /> 2
						</td>
						<td>
							<input class="lfr-input-text" name="<portlet:namespace />url2" style="width: 350px;" type="text" value="<%= HtmlUtil.escapeAttribute(url2) %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="url" /> 3
						</td>
						<td>
							<input class="lfr-input-text" name="<portlet:namespace />url3" style="width: 350px;" type="text" value="<%= HtmlUtil.escapeAttribute(url3) %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="visibility" />
						</td>
						<td>
							<select name="<portlet:namespace />visibility">

								<%
								for (int curVisibility : userVisibilities) {
								%>

									<option <%= (curVisibility == visibility) ? "selected" : "" %> value="<%= curVisibility %>"><%= LanguageUtil.get(request, VisibilityConstants.toLabel(curVisibility)) %></option>

								<%
								}
								%>

							</select>
						</td>
					</tr>
				</table>
			</fieldset>

			<br />

			<input class="aui-button-input" type="submit" value="<liferay-ui:message key="save" />" />
		</aui:form>
	</c:if>
</div>