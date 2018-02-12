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
AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_account_entry.jsp-accountEntry");
PortletURL portletURL = (PortletURL)request.getAttribute("edit_account_entry.jsp-portletURL");
%>

<portlet:renderURL var="viewTicketsURL" windowState="<%= WindowState.NORMAL.toString() %>">
	<portlet:param name="mvcPath" value="/support/2/advanced_search.jsp" />
	<portlet:param name="accountEntryIds" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
</portlet:renderURL>

<aui:button onClick='<%= "location.href = '" + viewTicketsURL.toString() + "';" %>' value="view-tickets" />

<%
Map<String, String> dropDownList = new TreeMap<String, String>();

if (liferayIncOrg) {
	PortletURL buttonURL = renderResponse.createRenderURL();

	buttonURL.setParameter("mvcPath", "/support/2/add_ticket_entry.jsp");
	buttonURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
	buttonURL.setWindowState(LiferayWindowState.MAXIMIZED);

	dropDownList.put("add-ticket", "location.href='" + buttonURL.toString() + "';");
}

if (OSBAccountEntryPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.UPDATE_ACCOUNT_INFO) || OSBAccountEntryPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.UPDATE_ACCOUNT_INSTRUCTIONS)) {
	PortletURL buttonURL = renderResponse.createRenderURL();

	buttonURL.setParameter("mvcPath", "/support/2/edit_account_entry/edit_account_entry_dialog.jsp");
	buttonURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
	buttonURL.setWindowState(LiferayWindowState.POP_UP);

	dropDownList.put("edit", renderResponse.getNamespace() + "openDialog('" + UnicodeLanguageUtil.get(request, "edit-project") + ": " + HtmlUtil.escapeJS(accountEntry.getName()) + "', '" + buttonURL.toString() + "', '" + renderResponse.getNamespace() + "updateAccountEntry');");
}
%>

<c:if test="<%= clockedIn && !dropDownList.isEmpty() %>">
	<span class="three-dot" id="<portlet:namespace />threeDotMenu">
		<span class="three-dot-icon" id="<portlet:namespace />threeDotIcon">
			<span style="top: 6px;"></span>
			<span style="top: 14px;"></span>
			<span style="top: 22px;"></span>
		</span>

		<div class="drop-down-menu">
			<div class="drop-down-arrow">
				<div></div>
			</div>

			<ul>

				<%
				for (Map.Entry<String, String> entry : dropDownList.entrySet()) {
					String curLabel = entry.getKey();
					String curOnClick = entry.getValue();

					StringBundler sb = new StringBundler(5);

					sb.append("<a href=\"javascript:;\" onClick=\"");
					sb.append(curOnClick);
					sb.append("\">");
					sb.append(LanguageUtil.get(request, curLabel));
					sb.append("</a>");
				%>

					<li>
						<%= sb.toString() %>
					</li>

				<%
				}
				%>

			</ul>
		</div>
	</span>
</c:if>