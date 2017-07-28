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

<%@ include file="/support/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

String url1 = ParamUtil.getString(request, "url1");
String url2 = ParamUtil.getString(request, "url2");
String url3 = ParamUtil.getString(request, "url3");
int visibility = ParamUtil.getInteger(request, "visibility");

int[] userVisibilities = TicketEntryLocalServiceUtil.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());

List<TicketLink> ticketLinks = TicketLinkLocalServiceUtil.getTicketLinks(ticketEntry.getTicketEntryId(), userVisibilities);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/edit_ticket_entry/edit_ticket_links.jsp");

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<c:if test="<%= OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ADD_LINK) %>">
	<portlet:actionURL name="addTicketLink" var="addTicketLinkURL">
		<portlet:param name="mvcPath" value="/support/edit_ticket_entry/edit_ticket_links.jsp" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
	</portlet:actionURL>

	<aui:form action="<%= addTicketLinkURL %>" method="post" name="fm">
		<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escapeAttribute(redirect) %>" />
		<input name="<portlet:namespace />ticketEntryId" type="hidden" value="<%= ticketEntry.getTicketEntryId() %>" />

		<div class="cleared section">
			<div class="fl">
				Edit Links for Ticket: <%= ticketEntry.getDisplayId() %>
			</div>

			<div class="fr">
				<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
			</div>
		</div>

		<liferay-ui:error exception="<%= TicketLinkURLException.class %>" message="please-enter-a-valid-url" />
		<liferay-ui:error exception="<%= TicketLinkVisibilityException.class %>" message="please-enter-a-valid-visibility" />

		<h1 class="section-heading">
			<liferay-ui:message key="links" />
		</h1>

		<div class="callout-a">
			<div class="callout-content">
				<liferay-ui:search-container
					emptyResultsMessage="there-are-no-links"
					headerNames="url,visibility"
				>
					<liferay-ui:search-container-results
						results="<%= ticketLinks %>"
						total="<%= ticketLinks.size() %>"
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
							value="<%= StringUtil.shorten(ticketLink.getUrl(), 115) %>"
						/>

						<liferay-ui:search-container-column-text
							href="<%= ticketLink.getUrl() %>"
							name="visibility"
							translate="<%= true %>"
							value="<%= ticketLink.getVisibilityLabel() %>"
						/>

						<liferay-ui:search-container-column-jsp
							align="right"
							path="/support/edit_ticket_entry/ticket_link_action.jsp"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator paginate="<%= false %>" />
				</liferay-ui:search-container>

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

										<option <%= (curVisibility == visibility) ? "selected" : "" %> value="<%= curVisibility %>"><%= LanguageUtil.get(pageContext, VisibilityConstants.toLabel(curVisibility)) %></option>

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

				<input class="aui-button-input" onClick="parent.location = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
			</div>
		</div>
	</aui:form>
</c:if>